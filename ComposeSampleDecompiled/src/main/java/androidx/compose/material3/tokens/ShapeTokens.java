package androidx.compose.material3.tokens;

import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.DpCornerSize;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;

/* loaded from: classes.dex */
public abstract class ShapeTokens {
    public static final RoundedCornerShape CornerExtraLarge;
    public static final RoundedCornerShape CornerExtraSmall;
    public static final RoundedCornerShape CornerLarge;
    public static final RoundedCornerShape CornerMedium;
    public static final RoundedCornerShape CornerSmall;

    /* JADX WARN: Type inference failed for: r0v12, types: [androidx.compose.foundation.shape.RoundedCornerShape, androidx.compose.foundation.shape.CornerBasedShape] */
    /* JADX WARN: Type inference failed for: r0v15, types: [androidx.compose.foundation.shape.RoundedCornerShape, androidx.compose.foundation.shape.CornerBasedShape] */
    /* JADX WARN: Type inference failed for: r0v2, types: [androidx.compose.foundation.shape.RoundedCornerShape, androidx.compose.foundation.shape.CornerBasedShape] */
    /* JADX WARN: Type inference failed for: r0v6, types: [androidx.compose.foundation.shape.RoundedCornerShape, androidx.compose.foundation.shape.CornerBasedShape] */
    /* JADX WARN: Type inference failed for: r0v9, types: [androidx.compose.foundation.shape.RoundedCornerShape, androidx.compose.foundation.shape.CornerBasedShape] */
    static {
        DpCornerSize dpCornerSize = new DpCornerSize((float) 28.0d);
        CornerExtraLarge = new CornerBasedShape(dpCornerSize, dpCornerSize, dpCornerSize, dpCornerSize);
        RoundedCornerShape roundedCornerShape = RoundedCornerShapeKt.CircleShape;
        DpCornerSize dpCornerSize2 = new DpCornerSize((float) 4.0d);
        CornerExtraSmall = new CornerBasedShape(dpCornerSize2, dpCornerSize2, dpCornerSize2, dpCornerSize2);
        DpCornerSize dpCornerSize3 = new DpCornerSize((float) 16.0d);
        CornerLarge = new CornerBasedShape(dpCornerSize3, dpCornerSize3, dpCornerSize3, dpCornerSize3);
        DpCornerSize dpCornerSize4 = new DpCornerSize((float) 12.0d);
        CornerMedium = new CornerBasedShape(dpCornerSize4, dpCornerSize4, dpCornerSize4, dpCornerSize4);
        DpCornerSize dpCornerSize5 = new DpCornerSize((float) 8.0d);
        CornerSmall = new CornerBasedShape(dpCornerSize5, dpCornerSize5, dpCornerSize5, dpCornerSize5);
    }
}
