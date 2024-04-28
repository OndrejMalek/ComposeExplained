package androidx.compose.material3;

import androidx.compose.foundation.interaction.FocusInteraction$Focus;
import androidx.compose.foundation.interaction.FocusInteraction$Unfocus;
import androidx.compose.foundation.interaction.HoverInteraction$Enter;
import androidx.compose.foundation.interaction.HoverInteraction$Exit;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.MutableInteractionSourceImpl;
import androidx.compose.foundation.interaction.PressInteraction$Cancel;
import androidx.compose.foundation.interaction.PressInteraction$Press;
import androidx.compose.foundation.interaction.PressInteraction$Release;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.platform.MotionDurationScaleImpl;
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
public final class ButtonElevation$animateElevation$1$1 extends SuspendLambda implements Function2 {
    public final /* synthetic */ InteractionSource $interactionSource;
    public final /* synthetic */ SnapshotStateList $interactions;
    public int label;

    /* renamed from: androidx.compose.material3.ButtonElevation$animateElevation$1$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ Object $interactions;
        public final /* synthetic */ int $r8$classId;

        public /* synthetic */ AnonymousClass1(int i, Object obj) {
            this.$r8$classId = i;
            this.$interactions = obj;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            Unit unit = Unit.INSTANCE;
            int i = this.$r8$classId;
            Object obj2 = this.$interactions;
            switch (i) {
                case 0:
                    Interaction interaction = (Interaction) obj;
                    if (interaction instanceof HoverInteraction$Enter) {
                        ((SnapshotStateList) obj2).add(interaction);
                    } else if (interaction instanceof HoverInteraction$Exit) {
                        ((SnapshotStateList) obj2).remove(((HoverInteraction$Exit) interaction).enter);
                    } else if (interaction instanceof FocusInteraction$Focus) {
                        ((SnapshotStateList) obj2).add(interaction);
                    } else if (interaction instanceof FocusInteraction$Unfocus) {
                        ((SnapshotStateList) obj2).remove(((FocusInteraction$Unfocus) interaction).focus);
                    } else if (interaction instanceof PressInteraction$Press) {
                        ((SnapshotStateList) obj2).add(interaction);
                    } else if (interaction instanceof PressInteraction$Release) {
                        ((SnapshotStateList) obj2).remove(((PressInteraction$Release) interaction).press);
                    } else if (interaction instanceof PressInteraction$Cancel) {
                        ((SnapshotStateList) obj2).remove(((PressInteraction$Cancel) interaction).press);
                    }
                    return unit;
                default:
                    ((MotionDurationScaleImpl) obj2).scaleFactor$delegate.setFloatValue(((Number) obj).floatValue());
                    return unit;
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ButtonElevation$animateElevation$1$1(InteractionSource interactionSource, SnapshotStateList snapshotStateList, Continuation continuation) {
        super(2, continuation);
        this.$interactionSource = interactionSource;
        this.$interactions = snapshotStateList;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ButtonElevation$animateElevation$1$1(this.$interactionSource, this.$interactions, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ButtonElevation$animateElevation$1$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
        SharedFlowImpl sharedFlowImpl = ((MutableInteractionSourceImpl) this.$interactionSource).interactions;
        AnonymousClass1 anonymousClass1 = new AnonymousClass1(0, this.$interactions);
        this.label = 1;
        sharedFlowImpl.getClass();
        SharedFlowImpl.collect$suspendImpl(sharedFlowImpl, anonymousClass1, this);
        return coroutineSingletons;
    }
}
