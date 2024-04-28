package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.foundation.interaction.Interaction;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes.dex */
public final class ButtonElevation$animateElevation$3 extends SuspendLambda implements Function2 {
    public final /* synthetic */ Animatable $animatable;
    public final /* synthetic */ Interaction $interaction;
    public final /* synthetic */ float $target;
    public int label;
    public final /* synthetic */ ButtonElevation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ButtonElevation$animateElevation$3(Animatable animatable, ButtonElevation buttonElevation, float f, Interaction interaction, Continuation continuation) {
        super(2, continuation);
        this.$animatable = animatable;
        this.this$0 = buttonElevation;
        this.$target = f;
        this.$interaction = interaction;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ButtonElevation$animateElevation$3(this.$animatable, this.this$0, this.$target, this.$interaction, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ButtonElevation$animateElevation$3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x006d, code lost:
    
        if ((r4 instanceof androidx.compose.foundation.interaction.FocusInteraction$Focus) != false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0094, code lost:
    
        if (r8 == r0) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0097, code lost:
    
        r8 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00ba, code lost:
    
        if (r8 != r0) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00bc, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00b8, code lost:
    
        if (r8 == r0) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0083, code lost:
    
        if ((r1 instanceof androidx.compose.foundation.interaction.FocusInteraction$Focus) != false) goto L40;
     */
    /* JADX WARN: Removed duplicated region for block: B:23:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0099  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r7.label
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            r3 = 1
            if (r1 == 0) goto L18
            if (r1 != r3) goto L10
            kotlin.ResultKt.throwOnFailure(r8)
            goto Lbd
        L10:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L18:
            kotlin.ResultKt.throwOnFailure(r8)
            androidx.compose.animation.core.Animatable r8 = r7.$animatable
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r8.targetValue$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.unit.Dp r1 = (androidx.compose.ui.unit.Dp) r1
            float r1 = r1.value
            androidx.compose.material3.ButtonElevation r4 = r7.this$0
            float r5 = r4.pressedElevation
            boolean r5 = androidx.compose.ui.unit.Dp.m280equalsimpl0(r1, r5)
            r6 = 0
            if (r5 == 0) goto L3a
            androidx.compose.foundation.interaction.PressInteraction$Press r1 = new androidx.compose.foundation.interaction.PressInteraction$Press
            long r4 = androidx.compose.ui.geometry.Offset.Zero
            r1.<init>(r4)
            goto L57
        L3a:
            float r5 = r4.hoveredElevation
            boolean r5 = androidx.compose.ui.unit.Dp.m280equalsimpl0(r1, r5)
            if (r5 == 0) goto L48
            androidx.compose.foundation.interaction.HoverInteraction$Enter r1 = new androidx.compose.foundation.interaction.HoverInteraction$Enter
            r1.<init>()
            goto L57
        L48:
            float r4 = r4.focusedElevation
            boolean r1 = androidx.compose.ui.unit.Dp.m280equalsimpl0(r1, r4)
            if (r1 == 0) goto L56
            androidx.compose.foundation.interaction.FocusInteraction$Focus r1 = new androidx.compose.foundation.interaction.FocusInteraction$Focus
            r1.<init>()
            goto L57
        L56:
            r1 = r6
        L57:
            r7.label = r3
            androidx.compose.animation.core.TweenSpec r4 = androidx.compose.material3.ElevationKt.DefaultIncomingSpec
            androidx.compose.foundation.interaction.Interaction r4 = r7.$interaction
            if (r4 == 0) goto L70
            boolean r1 = r4 instanceof androidx.compose.foundation.interaction.PressInteraction$Press
            androidx.compose.animation.core.TweenSpec r5 = androidx.compose.material3.ElevationKt.DefaultIncomingSpec
            if (r1 == 0) goto L66
            goto L87
        L66:
            boolean r1 = r4 instanceof androidx.compose.foundation.interaction.HoverInteraction$Enter
            if (r1 == 0) goto L6b
            goto L87
        L6b:
            boolean r1 = r4 instanceof androidx.compose.foundation.interaction.FocusInteraction$Focus
            if (r1 == 0) goto L86
            goto L87
        L70:
            if (r1 == 0) goto L86
            boolean r4 = r1 instanceof androidx.compose.foundation.interaction.PressInteraction$Press
            androidx.compose.animation.core.TweenSpec r5 = androidx.compose.material3.ElevationKt.DefaultOutgoingSpec
            if (r4 == 0) goto L79
            goto L87
        L79:
            boolean r4 = r1 instanceof androidx.compose.foundation.interaction.HoverInteraction$Enter
            if (r4 == 0) goto L81
            androidx.compose.animation.core.TweenSpec r1 = androidx.compose.material3.ElevationKt.HoveredOutgoingSpec
            r5 = r1
            goto L87
        L81:
            boolean r1 = r1 instanceof androidx.compose.foundation.interaction.FocusInteraction$Focus
            if (r1 == 0) goto L86
            goto L87
        L86:
            r5 = r6
        L87:
            float r1 = r7.$target
            if (r5 == 0) goto L99
            androidx.compose.ui.unit.Dp r3 = new androidx.compose.ui.unit.Dp
            r3.<init>(r1)
            java.lang.Object r8 = androidx.compose.animation.core.Animatable.animateTo$default(r8, r3, r5, r7)
            if (r8 != r0) goto L97
            goto Lba
        L97:
            r8 = r2
            goto Lba
        L99:
            androidx.compose.ui.unit.Dp r4 = new androidx.compose.ui.unit.Dp
            r4.<init>(r1)
            r8.getClass()
            androidx.compose.animation.core.Animatable$snapTo$2 r1 = new androidx.compose.animation.core.Animatable$snapTo$2
            r1.<init>(r8, r4, r6)
            androidx.compose.animation.core.MutatorMutex r8 = r8.mutatorMutex
            r8.getClass()
            androidx.compose.animation.core.MutatorMutex$mutate$2 r4 = new androidx.compose.animation.core.MutatorMutex$mutate$2
            r4.<init>(r3, r8, r1, r6)
            java.lang.Object r8 = kotlin.ResultKt.coroutineScope(r4, r7)
            if (r8 != r0) goto Lb7
            goto Lb8
        Lb7:
            r8 = r2
        Lb8:
            if (r8 != r0) goto L97
        Lba:
            if (r8 != r0) goto Lbd
            return r0
        Lbd:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ButtonElevation$animateElevation$3.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
