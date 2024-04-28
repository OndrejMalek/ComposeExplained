package androidx.compose.foundation.interaction;

import androidx.compose.runtime.MutableState;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlowImpl;

/* loaded from: classes.dex */
public final class FocusInteractionKt$collectIsFocusedAsState$1$1 extends SuspendLambda implements Function2 {
    public final /* synthetic */ MutableState $isFocused;
    public final /* synthetic */ InteractionSource $this_collectIsFocusedAsState;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FocusInteractionKt$collectIsFocusedAsState$1$1(InteractionSource interactionSource, MutableState mutableState, Continuation continuation) {
        super(2, continuation);
        this.$this_collectIsFocusedAsState = interactionSource;
        this.$isFocused = mutableState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FocusInteractionKt$collectIsFocusedAsState$1$1(this.$this_collectIsFocusedAsState, this.$isFocused, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FocusInteractionKt$collectIsFocusedAsState$1$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        ArrayList arrayList = new ArrayList();
        SharedFlowImpl sharedFlowImpl = ((MutableInteractionSourceImpl) this.$this_collectIsFocusedAsState).interactions;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(arrayList, this.$isFocused, 0);
        this.label = 1;
        sharedFlowImpl.getClass();
        SharedFlowImpl.collect$suspendImpl(sharedFlowImpl, anonymousClass1, this);
        return coroutineSingletons;
    }

    /* renamed from: androidx.compose.foundation.interaction.FocusInteractionKt$collectIsFocusedAsState$1$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ List $focusInteractions;
        public final /* synthetic */ MutableState $isFocused;
        public final /* synthetic */ int $r8$classId;

        public /* synthetic */ AnonymousClass1(ArrayList arrayList, MutableState mutableState, int i) {
            this.$r8$classId = i;
            this.$focusInteractions = arrayList;
            this.$isFocused = mutableState;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
            int i = this.$r8$classId;
            Unit unit = Unit.INSTANCE;
            switch (i) {
                case 0:
                    emit((Interaction) obj);
                    return unit;
                case 1:
                    emit((Interaction) obj);
                    return unit;
                default:
                    emit((Interaction) obj);
                    return unit;
            }
        }

        public final Object emit(Interaction interaction) {
            Unit unit = Unit.INSTANCE;
            int i = this.$r8$classId;
            MutableState mutableState = this.$isFocused;
            List list = this.$focusInteractions;
            switch (i) {
                case 0:
                    if (interaction instanceof FocusInteraction$Focus) {
                        list.add(interaction);
                    } else if (interaction instanceof FocusInteraction$Unfocus) {
                        list.remove(((FocusInteraction$Unfocus) interaction).focus);
                    }
                    mutableState.setValue(Boolean.valueOf(!list.isEmpty()));
                    return unit;
                case 1:
                    if (interaction instanceof HoverInteraction$Enter) {
                        list.add(interaction);
                    } else if (interaction instanceof HoverInteraction$Exit) {
                        list.remove(((HoverInteraction$Exit) interaction).enter);
                    }
                    mutableState.setValue(Boolean.valueOf(!list.isEmpty()));
                    return unit;
                default:
                    if (interaction instanceof PressInteraction$Press) {
                        list.add(interaction);
                    } else if (interaction instanceof PressInteraction$Release) {
                        list.remove(((PressInteraction$Release) interaction).press);
                    } else if (interaction instanceof PressInteraction$Cancel) {
                        list.remove(((PressInteraction$Cancel) interaction).press);
                    }
                    mutableState.setValue(Boolean.valueOf(!list.isEmpty()));
                    return unit;
            }
        }
    }
}
