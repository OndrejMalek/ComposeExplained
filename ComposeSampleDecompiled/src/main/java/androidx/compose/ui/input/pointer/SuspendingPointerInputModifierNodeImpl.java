package androidx.compose.ui.input.pointer;

import _COROUTINE._BOUNDARY;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.node.Snake;
import androidx.compose.ui.unit.Density;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.AbstractMap$toString$1;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.SafeContinuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.internal.ChildCancelledException;

/* loaded from: classes.dex */
public final class SuspendingPointerInputModifierNodeImpl extends Modifier.Node implements SuspendingPointerInputModifierNode, PointerInputScope, Density {
    public long boundsSize;
    public PointerEvent currentEvent;
    public final MutableVector dispatchingPointerHandlers;
    public PointerEvent lastPointerEvent;
    public final MutableVector pointerHandlers;
    public Function2 pointerInputHandler;
    public StandaloneCoroutine pointerInputJob;

    /* loaded from: classes.dex */
    public final class PointerEventHandlerCoroutine implements Density, Continuation {
        public final /* synthetic */ SuspendingPointerInputModifierNodeImpl $$delegate_0;
        public final Continuation completion;
        public CancellableContinuation pointerAwaiter;
        public PointerEventPass awaitPass = PointerEventPass.Main;
        public final EmptyCoroutineContext context = EmptyCoroutineContext.INSTANCE;

        public PointerEventHandlerCoroutine(CancellableContinuationImpl cancellableContinuationImpl) {
            this.completion = cancellableContinuationImpl;
            this.$$delegate_0 = SuspendingPointerInputModifierNodeImpl.this;
        }

        public final Object awaitPointerEvent(PointerEventPass pointerEventPass, Continuation continuation) {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, ResultKt.intercepted(continuation));
            cancellableContinuationImpl.initCancellability();
            this.awaitPass = pointerEventPass;
            this.pointerAwaiter = cancellableContinuationImpl;
            return cancellableContinuationImpl.getResult();
        }

        @Override // kotlin.coroutines.Continuation
        public final CoroutineContext getContext() {
            return this.context;
        }

        @Override // androidx.compose.ui.unit.Density
        public final float getDensity() {
            return this.$$delegate_0.getDensity();
        }

        /* renamed from: getExtendedTouchPadding-NH-jbRc, reason: not valid java name */
        public final long m158getExtendedTouchPaddingNHjbRc() {
            SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = SuspendingPointerInputModifierNodeImpl.this;
            suspendingPointerInputModifierNodeImpl.getClass();
            long mo33toSizeXkaWNTQ = suspendingPointerInputModifierNodeImpl.mo33toSizeXkaWNTQ(Snake.requireLayoutNode(suspendingPointerInputModifierNodeImpl).viewConfiguration.mo174getMinimumTouchTargetSizeMYxV2XQ());
            long j = suspendingPointerInputModifierNodeImpl.boundsSize;
            return _BOUNDARY.Size(Math.max(0.0f, Size.m85getWidthimpl(mo33toSizeXkaWNTQ) - ((int) (j >> 32))) / 2.0f, Math.max(0.0f, Size.m83getHeightimpl(mo33toSizeXkaWNTQ) - ((int) (j & 4294967295L))) / 2.0f);
        }

        @Override // androidx.compose.ui.unit.Density
        public final float getFontScale() {
            return this.$$delegate_0.getFontScale();
        }

        @Override // kotlin.coroutines.Continuation
        public final void resumeWith(Object obj) {
            SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = SuspendingPointerInputModifierNodeImpl.this;
            synchronized (suspendingPointerInputModifierNodeImpl.pointerHandlers) {
                suspendingPointerInputModifierNodeImpl.pointerHandlers.remove(this);
            }
            this.completion.resumeWith(obj);
        }

        @Override // androidx.compose.ui.unit.Density
        /* renamed from: toPx--R2X_6o */
        public final float mo31toPxR2X_6o(long j) {
            return this.$$delegate_0.mo31toPxR2X_6o(j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* renamed from: toPx-0680j_4 */
        public final float mo32toPx0680j_4(float f) {
            return this.$$delegate_0.getDensity() * f;
        }

        @Override // androidx.compose.ui.unit.Density
        /* renamed from: toSize-XkaWNTQ */
        public final long mo33toSizeXkaWNTQ(long j) {
            return this.$$delegate_0.mo33toSizeXkaWNTQ(j);
        }
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v3, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public SuspendingPointerInputModifierNodeImpl(Function2 function2) {
        ResultKt.checkNotNullParameter(function2, "pointerInputHandler");
        this.pointerInputHandler = function2;
        this.currentEvent = SuspendingPointerInputFilterKt.EmptyPointerEvent;
        ?? obj = new Object();
        obj.content = new PointerEventHandlerCoroutine[16];
        obj.size = 0;
        this.pointerHandlers = obj;
        ?? obj2 = new Object();
        obj2.content = new PointerEventHandlerCoroutine[16];
        obj2.size = 0;
        this.dispatchingPointerHandlers = obj2;
        this.boundsSize = 0L;
    }

    public final Object awaitPointerEventScope(Function2 function2, Continuation continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, ResultKt.intercepted(continuation));
        cancellableContinuationImpl.initCancellability();
        PointerEventHandlerCoroutine pointerEventHandlerCoroutine = new PointerEventHandlerCoroutine(cancellableContinuationImpl);
        synchronized (this.pointerHandlers) {
            this.pointerHandlers.add(pointerEventHandlerCoroutine);
            new SafeContinuation(ResultKt.intercepted(ResultKt.createCoroutineUnintercepted(pointerEventHandlerCoroutine, pointerEventHandlerCoroutine, function2))).resumeWith(Unit.INSTANCE);
        }
        cancellableContinuationImpl.invokeOnCancellation(new AbstractMap$toString$1(11, pointerEventHandlerCoroutine));
        return cancellableContinuationImpl.getResult();
    }

    public final void dispatchPointerEvent(PointerEvent pointerEvent, PointerEventPass pointerEventPass) {
        CancellableContinuation cancellableContinuation;
        CancellableContinuation cancellableContinuation2;
        synchronized (this.pointerHandlers) {
            MutableVector mutableVector = this.dispatchingPointerHandlers;
            mutableVector.addAll(mutableVector.size, this.pointerHandlers);
        }
        try {
            int ordinal = pointerEventPass.ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    MutableVector mutableVector2 = this.dispatchingPointerHandlers;
                    int i = mutableVector2.size;
                    if (i > 0) {
                        int i2 = i - 1;
                        Object[] objArr = mutableVector2.content;
                        do {
                            PointerEventHandlerCoroutine pointerEventHandlerCoroutine = (PointerEventHandlerCoroutine) objArr[i2];
                            if (pointerEventPass == pointerEventHandlerCoroutine.awaitPass && (cancellableContinuation2 = pointerEventHandlerCoroutine.pointerAwaiter) != null) {
                                pointerEventHandlerCoroutine.pointerAwaiter = null;
                                cancellableContinuation2.resumeWith(pointerEvent);
                            }
                            i2--;
                        } while (i2 >= 0);
                    }
                } else if (ordinal != 2) {
                }
            }
            MutableVector mutableVector3 = this.dispatchingPointerHandlers;
            int i3 = mutableVector3.size;
            if (i3 > 0) {
                Object[] objArr2 = mutableVector3.content;
                int i4 = 0;
                do {
                    PointerEventHandlerCoroutine pointerEventHandlerCoroutine2 = (PointerEventHandlerCoroutine) objArr2[i4];
                    if (pointerEventPass == pointerEventHandlerCoroutine2.awaitPass && (cancellableContinuation = pointerEventHandlerCoroutine2.pointerAwaiter) != null) {
                        pointerEventHandlerCoroutine2.pointerAwaiter = null;
                        cancellableContinuation.resumeWith(pointerEvent);
                    }
                    i4++;
                } while (i4 < i3);
            }
        } finally {
            this.dispatchingPointerHandlers.clear();
        }
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getDensity() {
        return Snake.requireLayoutNode(this).density.getDensity();
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getFontScale() {
        return Snake.requireLayoutNode(this).density.getFontScale();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final void onCancelPointerInput() {
        PointerEvent pointerEvent = this.lastPointerEvent;
        if (pointerEvent == null) {
            return;
        }
        List list = pointerEvent.changes;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (!(!((PointerInputChange) list.get(i)).pressed)) {
                ArrayList arrayList = new ArrayList(list.size());
                int size2 = list.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    PointerInputChange pointerInputChange = (PointerInputChange) list.get(i2);
                    long j = pointerInputChange.id;
                    long j2 = Offset.Zero;
                    boolean z = pointerInputChange.pressed;
                    long j3 = pointerInputChange.uptimeMillis;
                    long j4 = pointerInputChange.position;
                    arrayList.add(new PointerInputChange(j, j3, j4, false, pointerInputChange.pressure, j3, j4, z, z, 1, j2));
                }
                PointerEvent pointerEvent2 = new PointerEvent(arrayList, null);
                this.currentEvent = pointerEvent2;
                dispatchPointerEvent(pointerEvent2, PointerEventPass.Initial);
                dispatchPointerEvent(pointerEvent2, PointerEventPass.Main);
                dispatchPointerEvent(pointerEvent2, PointerEventPass.Final);
                this.lastPointerEvent = null;
                return;
            }
        }
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final void onDensityChange() {
        resetPointerInputHandler();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        resetPointerInputHandler();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* renamed from: onPointerEvent-H0pRuoY */
    public final void mo25onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j) {
        this.boundsSize = j;
        if (pointerEventPass == PointerEventPass.Initial) {
            this.currentEvent = pointerEvent;
        }
        if (this.pointerInputJob == null) {
            this.pointerInputJob = ResultKt.launch$default(getCoroutineScope(), null, 4, new SuspendingPointerInputModifierNodeImpl$onPointerEvent$1(this, null), 1);
        }
        dispatchPointerEvent(pointerEvent, pointerEventPass);
        List list = pointerEvent.changes;
        int size = list.size();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= size) {
                z = true;
                break;
            } else if (!_BOUNDARY.changedToUpIgnoreConsumed((PointerInputChange) list.get(i))) {
                break;
            } else {
                i++;
            }
        }
        if (!(!z)) {
            pointerEvent = null;
        }
        this.lastPointerEvent = pointerEvent;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final void onViewConfigurationChange() {
        resetPointerInputHandler();
    }

    public final void resetPointerInputHandler() {
        StandaloneCoroutine standaloneCoroutine = this.pointerInputJob;
        if (standaloneCoroutine != null) {
            standaloneCoroutine.cancel(new ChildCancelledException(4));
            this.pointerInputJob = null;
        }
    }
}
