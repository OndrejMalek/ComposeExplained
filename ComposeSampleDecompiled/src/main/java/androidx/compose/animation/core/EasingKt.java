package androidx.compose.animation.core;

/* loaded from: classes.dex */
public abstract class EasingKt {
    public static final CubicBezierEasing FastOutSlowInEasing = new CubicBezierEasing(0.2f);
    public static final EasingKt$LinearEasing$1 LinearEasing;

    static {
        if (Float.isNaN(0.0f) || Float.isNaN(0.0f) || Float.isNaN(0.2f) || Float.isNaN(1.0f)) {
            throw new IllegalArgumentException("Parameters to CubicBezierEasing cannot be NaN. Actual parameters are: 0.0, 0.0, 0.2, 1.0.".toString());
        }
        if (Float.isNaN(0.4f) || Float.isNaN(0.0f) || Float.isNaN(1.0f) || Float.isNaN(1.0f)) {
            throw new IllegalArgumentException("Parameters to CubicBezierEasing cannot be NaN. Actual parameters are: 0.4, 0.0, 1.0, 1.0.".toString());
        }
        LinearEasing = EasingKt$LinearEasing$1.INSTANCE;
    }
}
