package androidx.compose.animation.core;

/* loaded from: classes.dex */
public final class CubicBezierEasing implements Easing {
    public final float c;

    public CubicBezierEasing(float f) {
        this.c = f;
        if (Float.isNaN(0.4f) || Float.isNaN(0.0f) || Float.isNaN(f) || Float.isNaN(1.0f)) {
            throw new IllegalArgumentException(("Parameters to CubicBezierEasing cannot be NaN. Actual parameters are: 0.4, 0.0, " + f + ", 1.0.").toString());
        }
    }

    public static float evaluateCubic(float f, float f2, float f3) {
        float f4 = 3;
        float f5 = 1 - f3;
        return (f3 * f3 * f3) + (f4 * f2 * f5 * f3 * f3) + (f * f4 * f5 * f5 * f3);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof CubicBezierEasing) {
            CubicBezierEasing cubicBezierEasing = (CubicBezierEasing) obj;
            cubicBezierEasing.getClass();
            if (this.c == cubicBezierEasing.c) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Float.hashCode(1.0f) + AnimationEndReason$EnumUnboxingLocalUtility.m(this.c, AnimationEndReason$EnumUnboxingLocalUtility.m(0.0f, Float.hashCode(0.4f) * 31, 31), 31);
    }

    @Override // androidx.compose.animation.core.Easing
    public final float transform(float f) {
        if (f <= 0.0f || f >= 1.0f) {
            return f;
        }
        float f2 = 0.0f;
        float f3 = 1.0f;
        while (true) {
            float f4 = (f2 + f3) / 2;
            float evaluateCubic = evaluateCubic(0.4f, this.c, f4);
            if (Math.abs(f - evaluateCubic) < 0.001f) {
                return evaluateCubic(0.0f, 1.0f, f4);
            }
            if (evaluateCubic < f) {
                f2 = f4;
            } else {
                f3 = f4;
            }
        }
    }
}
