package androidx.compose.material3;

import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class InteractiveComponentSizeKt {
    public static final StaticProvidableCompositionLocal LocalMinimumInteractiveComponentEnforcement = new CompositionLocal(ShapesKt$LocalShapes$1.INSTANCE$3);
    public static final long minimumInteractiveComponentSize;

    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.compose.runtime.StaticProvidableCompositionLocal, androidx.compose.runtime.CompositionLocal] */
    static {
        float f = 48;
        minimumInteractiveComponentSize = ResultKt.m294DpSizeYgX7TsA(f, f);
    }
}
