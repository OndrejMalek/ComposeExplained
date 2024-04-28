package androidx.compose.ui.unit;

/* loaded from: classes.dex */
public final class Dp implements Comparable {
    public final float value;

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m280equalsimpl0(float f, float f2) {
        return Float.compare(f, f2) == 0;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m281toStringimpl(float f) {
        if (Float.isNaN(f)) {
            return "Dp.Unspecified";
        }
        return f + ".dp";
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        return Float.compare(this.value, ((Dp) obj).value);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Dp) {
            return Float.compare(this.value, ((Dp) obj).value) == 0;
        }
        return false;
    }

    public final int hashCode() {
        return Float.hashCode(this.value);
    }

    public final String toString() {
        return m281toStringimpl(this.value);
    }
}
