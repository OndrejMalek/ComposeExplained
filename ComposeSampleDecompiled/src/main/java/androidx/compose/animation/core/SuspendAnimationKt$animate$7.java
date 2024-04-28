package androidx.compose.animation.core;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class SuspendAnimationKt$animate$7 extends Lambda implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ AnimationState $this_animate;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SuspendAnimationKt$animate$7(AnimationState animationState, int i) {
        super(0);
        this.$r8$classId = i;
        this.$this_animate = animationState;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Unit unit = Unit.INSTANCE;
        AnimationState animationState = this.$this_animate;
        int i = this.$r8$classId;
        switch (i) {
            case 0:
                switch (i) {
                    case 0:
                        animationState.isRunning = false;
                        return unit;
                    default:
                        animationState.isRunning = false;
                        return unit;
                }
            default:
                switch (i) {
                    case 0:
                        animationState.isRunning = false;
                        return unit;
                    default:
                        animationState.isRunning = false;
                        return unit;
                }
        }
    }
}
