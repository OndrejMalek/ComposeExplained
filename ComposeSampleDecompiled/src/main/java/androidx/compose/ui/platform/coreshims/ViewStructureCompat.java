package androidx.compose.ui.platform.coreshims;

import android.view.ViewStructure;

/* loaded from: classes.dex */
public final class ViewStructureCompat {
    public final ViewStructure mWrappedObj;

    /* loaded from: classes.dex */
    public abstract class Api23Impl {
        public static void setClassName(ViewStructure viewStructure, String str) {
            viewStructure.setClassName(str);
        }

        public static void setContentDescription(ViewStructure viewStructure, CharSequence charSequence) {
            viewStructure.setContentDescription(charSequence);
        }

        public static void setDimens(ViewStructure viewStructure, int i, int i2, int i3, int i4, int i5, int i6) {
            viewStructure.setDimens(i, i2, i3, i4, i5, i6);
        }

        public static void setText(ViewStructure viewStructure, CharSequence charSequence) {
            viewStructure.setText(charSequence);
        }
    }

    public ViewStructureCompat(ViewStructure viewStructure) {
        this.mWrappedObj = viewStructure;
    }
}
