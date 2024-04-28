package androidx.compose.animation.core;

import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class TwoWayConverterImpl {
    public final Function1 convertFromVector;
    public final Function1 convertToVector;

    public TwoWayConverterImpl(VectorConvertersKt$DpToVector$1 vectorConvertersKt$DpToVector$1, VectorConvertersKt$DpToVector$1 vectorConvertersKt$DpToVector$12) {
        this.convertToVector = vectorConvertersKt$DpToVector$1;
        this.convertFromVector = vectorConvertersKt$DpToVector$12;
    }
}
