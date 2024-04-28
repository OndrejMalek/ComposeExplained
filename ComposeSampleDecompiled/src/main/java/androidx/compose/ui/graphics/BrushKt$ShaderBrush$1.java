package androidx.compose.ui.graphics;

import android.graphics.Shader;
import androidx.compose.ui.geometry.Size;

/* loaded from: classes.dex */
public final class BrushKt$ShaderBrush$1 extends Brush {
    public final /* synthetic */ Shader $shader;
    public long createdSize = Size.Unspecified;
    public Shader internalShader;

    public BrushKt$ShaderBrush$1(Shader shader) {
        this.$shader = shader;
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x000f, code lost:
    
        if (r1 == r7) goto L14;
     */
    @Override // androidx.compose.ui.graphics.Brush
    /* renamed from: applyTo-Pq9zytI */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void mo110applyToPq9zytI(float r6, long r7, androidx.compose.ui.graphics.AndroidPaint r9) {
        /*
            r5 = this;
            java.lang.String r0 = "p"
            kotlin.ResultKt.checkNotNullParameter(r9, r0)
            android.graphics.Shader r0 = r5.internalShader
            if (r0 == 0) goto L12
            long r1 = r5.createdSize
            int r3 = androidx.compose.ui.geometry.Size.$r8$clinit
            int r1 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r1 != 0) goto L12
            goto L32
        L12:
            float r0 = androidx.compose.ui.geometry.Size.m85getWidthimpl(r7)
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L2b
            float r0 = androidx.compose.ui.geometry.Size.m83getHeightimpl(r7)
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 > 0) goto L24
            goto L2b
        L24:
            android.graphics.Shader r0 = r5.$shader
            r5.internalShader = r0
            r5.createdSize = r7
            goto L32
        L2b:
            r0 = 0
            r5.internalShader = r0
            long r7 = androidx.compose.ui.geometry.Size.Unspecified
            r5.createdSize = r7
        L32:
            android.graphics.Paint r7 = r9.internalPaint
            java.lang.String r8 = "<this>"
            kotlin.ResultKt.checkNotNullParameter(r7, r8)
            int r7 = r7.getColor()
            long r1 = (long) r7
            r7 = 32
            long r1 = r1 << r7
            long r3 = androidx.compose.ui.graphics.Color.Black
            boolean r7 = androidx.compose.ui.graphics.Color.m121equalsimpl0(r1, r3)
            if (r7 != 0) goto L4c
            r9.m92setColor8_81llA(r3)
        L4c:
            android.graphics.Shader r7 = r9.internalShader
            boolean r7 = kotlin.ResultKt.areEqual(r7, r0)
            if (r7 != 0) goto L57
            r9.setShader(r0)
        L57:
            android.graphics.Paint r7 = r9.internalPaint
            kotlin.ResultKt.checkNotNullParameter(r7, r8)
            int r7 = r7.getAlpha()
            float r7 = (float) r7
            r8 = 1132396544(0x437f0000, float:255.0)
            float r7 = r7 / r8
            int r7 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r7 != 0) goto L69
            goto L6c
        L69:
            r9.setAlpha(r6)
        L6c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.graphics.BrushKt$ShaderBrush$1.mo110applyToPq9zytI(float, long, androidx.compose.ui.graphics.AndroidPaint):void");
    }
}
