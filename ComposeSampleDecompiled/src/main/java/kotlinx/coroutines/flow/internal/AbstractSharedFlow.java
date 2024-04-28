package kotlinx.coroutines.flow.internal;

import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.SharedFlowImpl;

/* loaded from: classes.dex */
public abstract class AbstractSharedFlow {
    public SubscriptionCountStateFlow _subscriptionCount;
    public int nCollectors;
    public int nextIndex;
    public AbstractSharedFlowSlot[] slots;

    public final AbstractSharedFlowSlot allocateSlot() {
        AbstractSharedFlowSlot abstractSharedFlowSlot;
        SubscriptionCountStateFlow subscriptionCountStateFlow;
        synchronized (this) {
            try {
                AbstractSharedFlowSlot[] abstractSharedFlowSlotArr = this.slots;
                if (abstractSharedFlowSlotArr == null) {
                    abstractSharedFlowSlotArr = createSlotArray();
                    this.slots = abstractSharedFlowSlotArr;
                } else if (this.nCollectors >= abstractSharedFlowSlotArr.length) {
                    Object[] copyOf = Arrays.copyOf(abstractSharedFlowSlotArr, abstractSharedFlowSlotArr.length * 2);
                    ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
                    this.slots = (AbstractSharedFlowSlot[]) copyOf;
                    abstractSharedFlowSlotArr = (AbstractSharedFlowSlot[]) copyOf;
                }
                int i = this.nextIndex;
                do {
                    abstractSharedFlowSlot = abstractSharedFlowSlotArr[i];
                    if (abstractSharedFlowSlot == null) {
                        abstractSharedFlowSlot = createSlot();
                        abstractSharedFlowSlotArr[i] = abstractSharedFlowSlot;
                    }
                    i++;
                    if (i >= abstractSharedFlowSlotArr.length) {
                        i = 0;
                    }
                } while (!abstractSharedFlowSlot.allocateLocked(this));
                this.nextIndex = i;
                this.nCollectors++;
                subscriptionCountStateFlow = this._subscriptionCount;
            } catch (Throwable th) {
                throw th;
            }
        }
        if (subscriptionCountStateFlow != null) {
            subscriptionCountStateFlow.increment(1);
        }
        return abstractSharedFlowSlot;
    }

    public abstract AbstractSharedFlowSlot createSlot();

    public abstract AbstractSharedFlowSlot[] createSlotArray();

    public final void freeSlot(AbstractSharedFlowSlot abstractSharedFlowSlot) {
        SubscriptionCountStateFlow subscriptionCountStateFlow;
        int i;
        Continuation[] freeLocked;
        synchronized (this) {
            try {
                int i2 = this.nCollectors - 1;
                this.nCollectors = i2;
                subscriptionCountStateFlow = this._subscriptionCount;
                if (i2 == 0) {
                    this.nextIndex = 0;
                }
                ResultKt.checkNotNull(abstractSharedFlowSlot, "null cannot be cast to non-null type kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot<kotlin.Any>");
                freeLocked = abstractSharedFlowSlot.freeLocked(this);
            } catch (Throwable th) {
                throw th;
            }
        }
        for (Continuation continuation : freeLocked) {
            if (continuation != null) {
                continuation.resumeWith(Unit.INSTANCE);
            }
        }
        if (subscriptionCountStateFlow != null) {
            subscriptionCountStateFlow.increment(-1);
        }
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [kotlinx.coroutines.flow.internal.SubscriptionCountStateFlow, kotlinx.coroutines.flow.SharedFlowImpl] */
    public final SubscriptionCountStateFlow getSubscriptionCount() {
        SubscriptionCountStateFlow subscriptionCountStateFlow;
        synchronized (this) {
            SubscriptionCountStateFlow subscriptionCountStateFlow2 = this._subscriptionCount;
            subscriptionCountStateFlow = subscriptionCountStateFlow2;
            if (subscriptionCountStateFlow2 == null) {
                int i = this.nCollectors;
                ?? sharedFlowImpl = new SharedFlowImpl(1, Integer.MAX_VALUE, BufferOverflow.DROP_OLDEST);
                sharedFlowImpl.tryEmit(Integer.valueOf(i));
                this._subscriptionCount = sharedFlowImpl;
                subscriptionCountStateFlow = sharedFlowImpl;
            }
        }
        return subscriptionCountStateFlow;
    }
}
