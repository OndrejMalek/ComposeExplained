package androidx.compose.ui.text.platform.style;

import android.graphics.Shader;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.BrushKt$ShaderBrush$1;
import kotlin.Pair;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ShaderBrushSpan extends CharacterStyle implements UpdateAppearance {
    public final float alpha;
    public Pair cachedShader;
    public final BrushKt$ShaderBrush$1 shaderBrush;
    public long size = Size.Unspecified;

    public ShaderBrushSpan(BrushKt$ShaderBrush$1 brushKt$ShaderBrush$1, float f) {
        this.shaderBrush = brushKt$ShaderBrush$1;
        this.alpha = f;
    }

    @Override // android.text.style.CharacterStyle
    public final void updateDrawState(TextPaint textPaint) {
        ResultKt.checkNotNullParameter(textPaint, "textPaint");
        float f = this.alpha;
        if (!Float.isNaN(f)) {
            textPaint.setAlpha(ResultKt.roundToInt(ResultKt.coerceIn(f, 0.0f, 1.0f) * 255));
        }
        long j = this.size;
        if (j == Size.Unspecified) {
            return;
        }
        Pair pair = this.cachedShader;
        Shader shader = (pair == null || ((Size) pair.first).packedValue != j) ? this.shaderBrush.$shader : (Shader) pair.second;
        textPaint.setShader(shader);
        this.cachedShader = new Pair(new Size(this.size), shader);
    }
}
