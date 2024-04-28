package androidx.compose.ui.text.android;

import android.os.Build;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.TextUtils;
import androidx.compose.ui.text.platform.AndroidTextPaint;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class StaticLayoutFactory {
    public static final StaticLayoutFactory23 delegate = new Object();

    public static BoringLayout create(CharSequence charSequence, AndroidTextPaint androidTextPaint, int i, BoringLayout.Metrics metrics, Layout.Alignment alignment, boolean z, boolean z2, TextUtils.TruncateAt truncateAt, int i2) {
        ResultKt.checkNotNullParameter(charSequence, "text");
        ResultKt.checkNotNullParameter(androidTextPaint, "paint");
        ResultKt.checkNotNullParameter(alignment, "alignment");
        if (i < 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (i2 >= 0) {
            return Build.VERSION.SDK_INT >= 33 ? BoringLayoutFactory33.create(charSequence, androidTextPaint, i, alignment, 1.0f, 0.0f, metrics, z, z2, truncateAt, i2) : BoringLayoutFactoryDefault.create(charSequence, androidTextPaint, i, alignment, 1.0f, 0.0f, metrics, z, truncateAt, i2);
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }
}
