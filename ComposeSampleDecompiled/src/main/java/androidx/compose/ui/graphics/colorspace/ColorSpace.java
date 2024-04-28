package androidx.compose.ui.graphics.colorspace;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class ColorSpace {
    public final int id;
    public final long model;
    public final String name;

    public ColorSpace(String str, long j, int i) {
        ResultKt.checkNotNullParameter(str, "name");
        this.name = str;
        this.model = j;
        this.id = i;
        if (str.length() == 0) {
            throw new IllegalArgumentException("The name of a color space cannot be null and must contain at least 1 character");
        }
        if (i < -1 || i > 63) {
            throw new IllegalArgumentException("The id must be between -1 and 63");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ColorSpace colorSpace = (ColorSpace) obj;
        if (this.id == colorSpace.id && ResultKt.areEqual(this.name, colorSpace.name)) {
            return ColorModel.m131equalsimpl0(this.model, colorSpace.model);
        }
        return false;
    }

    public abstract float getMaxValue(int i);

    public abstract float getMinValue(int i);

    public int hashCode() {
        int hashCode = this.name.hashCode() * 31;
        int i = ColorModel.$r8$clinit;
        return ((Long.hashCode(this.model) + hashCode) * 31) + this.id;
    }

    public boolean isSrgb() {
        return false;
    }

    public final String toString() {
        return this.name + " (id=" + this.id + ", model=" + ((Object) ColorModel.m132toStringimpl(this.model)) + ')';
    }

    public abstract long toXy$ui_graphics_release(float f, float f2, float f3);

    public abstract float toZ$ui_graphics_release(float f, float f2, float f3);

    /* renamed from: xyzaToColor-JlNiLsg$ui_graphics_release, reason: not valid java name */
    public abstract long mo133xyzaToColorJlNiLsg$ui_graphics_release(float f, float f2, float f3, float f4, ColorSpace colorSpace);
}
