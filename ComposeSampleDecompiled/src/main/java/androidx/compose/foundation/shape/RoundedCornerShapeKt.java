package androidx.compose.foundation.shape;

/* loaded from: classes.dex */
public abstract class RoundedCornerShapeKt {
    public static final RoundedCornerShape CircleShape;

    /* JADX WARN: Type inference failed for: r1v2, types: [androidx.compose.foundation.shape.RoundedCornerShape, androidx.compose.foundation.shape.CornerBasedShape] */
    static {
        PercentCornerSize percentCornerSize = new PercentCornerSize(50);
        CircleShape = new CornerBasedShape(percentCornerSize, percentCornerSize, percentCornerSize, percentCornerSize);
    }
}
