package androidx.compose.animation.core;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class TweenSpec implements AnimationSpec {
    public final int delay;
    public final int durationMillis;
    public final Easing easing;

    public TweenSpec(int i, int i2, Easing easing) {
        ResultKt.checkNotNullParameter(easing, "easing");
        this.durationMillis = i;
        this.delay = i2;
        this.easing = easing;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof TweenSpec)) {
            return false;
        }
        TweenSpec tweenSpec = (TweenSpec) obj;
        return tweenSpec.durationMillis == this.durationMillis && tweenSpec.delay == this.delay && ResultKt.areEqual(tweenSpec.easing, this.easing);
    }

    public final int hashCode() {
        return ((this.easing.hashCode() + (this.durationMillis * 31)) * 31) + this.delay;
    }

    @Override // androidx.compose.animation.core.AnimationSpec
    public final VectorizedFiniteAnimationSpec vectorize(TwoWayConverterImpl twoWayConverterImpl) {
        return new VectorizedTweenSpec(this.durationMillis, this.delay, this.easing);
    }
}
