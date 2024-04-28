package androidx.compose.animation.core;

import kotlin.ResultKt;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class AnimationResult {
    public final int endReason;
    public final AnimationState endState;

    public AnimationResult(AnimationState animationState, int i) {
        ResultKt.checkNotNullParameter(animationState, "endState");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(i, "endReason");
        this.endState = animationState;
        this.endReason = i;
    }

    public final String toString() {
        return "AnimationResult(endReason=" + AnimationEndReason$EnumUnboxingLocalUtility.stringValueOf(this.endReason) + ", endState=" + this.endState + ')';
    }
}
