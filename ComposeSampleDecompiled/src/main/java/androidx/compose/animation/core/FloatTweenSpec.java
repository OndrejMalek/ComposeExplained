package androidx.compose.animation.core;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class FloatTweenSpec implements FloatAnimationSpec {
    public final int delay;
    public final int duration;
    public final Easing easing;

    public FloatTweenSpec(int i, int i2, Easing easing) {
        ResultKt.checkNotNullParameter(easing, "easing");
        this.duration = i;
        this.delay = i2;
        this.easing = easing;
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final long getDurationNanos(float f, float f2, float f3) {
        return (this.delay + this.duration) * 1000000;
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final float getValueFromNanos(long j, float f, float f2, float f3) {
        long j2 = (j / 1000000) - this.delay;
        int i = this.duration;
        long j3 = i;
        if (0 > j3) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + j3 + " is less than minimum 0.");
        }
        if (j2 < 0) {
            j2 = 0;
        } else if (j2 > j3) {
            j2 = j3;
        }
        float transform = this.easing.transform(ResultKt.coerceIn(i == 0 ? 1.0f : ((float) j2) / i, 0.0f, 1.0f));
        TwoWayConverterImpl twoWayConverterImpl = VectorConvertersKt.FloatToVector;
        return (f2 * transform) + ((1 - transform) * f);
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final float getVelocityFromNanos(long j, float f, float f2, float f3) {
        long j2 = (j / 1000000) - this.delay;
        long j3 = this.duration;
        if (0 > j3) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + j3 + " is less than minimum 0.");
        }
        if (j2 < 0) {
            j2 = 0;
        } else if (j2 > j3) {
            j2 = j3;
        }
        if (j2 < 0) {
            return 0.0f;
        }
        if (j2 == 0) {
            return f3;
        }
        return (getValueFromNanos(j2 * 1000000, f, f2, f3) - getValueFromNanos((j2 - 1) * 1000000, f, f2, f3)) * 1000.0f;
    }
}
