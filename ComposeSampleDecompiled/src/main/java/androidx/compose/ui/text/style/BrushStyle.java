package androidx.compose.ui.text.style;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.BrushKt$ShaderBrush$1;
import androidx.compose.ui.graphics.Color;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class BrushStyle implements TextForegroundStyle {
    public final float alpha;
    public final BrushKt$ShaderBrush$1 value;

    public BrushStyle(BrushKt$ShaderBrush$1 brushKt$ShaderBrush$1, float f) {
        ResultKt.checkNotNullParameter(brushKt$ShaderBrush$1, "value");
        this.value = brushKt$ShaderBrush$1;
        this.alpha = f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BrushStyle)) {
            return false;
        }
        BrushStyle brushStyle = (BrushStyle) obj;
        return ResultKt.areEqual(this.value, brushStyle.value) && Float.compare(this.alpha, brushStyle.alpha) == 0;
    }

    @Override // androidx.compose.ui.text.style.TextForegroundStyle
    public final float getAlpha() {
        return this.alpha;
    }

    @Override // androidx.compose.ui.text.style.TextForegroundStyle
    public final Brush getBrush() {
        return this.value;
    }

    @Override // androidx.compose.ui.text.style.TextForegroundStyle
    /* renamed from: getColor-0d7_KjU, reason: not valid java name */
    public final long mo264getColor0d7_KjU() {
        int i = Color.$r8$clinit;
        return Color.Unspecified;
    }

    public final int hashCode() {
        return Float.hashCode(this.alpha) + (this.value.hashCode() * 31);
    }

    public final String toString() {
        return "BrushStyle(value=" + this.value + ", alpha=" + this.alpha + ')';
    }
}
