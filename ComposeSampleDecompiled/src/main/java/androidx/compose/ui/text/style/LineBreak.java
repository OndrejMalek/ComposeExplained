package androidx.compose.ui.text.style;

/* loaded from: classes.dex */
public final class LineBreak {
    public static final int Simple = 66305;
    public final int mask;

    /* loaded from: classes.dex */
    public final class Strategy {
        public final int value;

        /* renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m265equalsimpl0(int i, int i2) {
            return i == i2;
        }

        /* renamed from: toString-impl, reason: not valid java name */
        public static String m266toStringimpl(int i) {
            return m265equalsimpl0(i, 1) ? "Strategy.Simple" : m265equalsimpl0(i, 2) ? "Strategy.HighQuality" : m265equalsimpl0(i, 3) ? "Strategy.Balanced" : "Invalid";
        }

        public final boolean equals(Object obj) {
            if (obj instanceof Strategy) {
                return this.value == ((Strategy) obj).value;
            }
            return false;
        }

        public final int hashCode() {
            return Integer.hashCode(this.value);
        }

        public final String toString() {
            return m266toStringimpl(this.value);
        }
    }

    /* loaded from: classes.dex */
    public final class Strictness {
        public final int value;

        /* renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m267equalsimpl0(int i, int i2) {
            return i == i2;
        }

        /* renamed from: toString-impl, reason: not valid java name */
        public static String m268toStringimpl(int i) {
            return m267equalsimpl0(i, 1) ? "Strictness.None" : m267equalsimpl0(i, 2) ? "Strictness.Loose" : m267equalsimpl0(i, 3) ? "Strictness.Normal" : m267equalsimpl0(i, 4) ? "Strictness.Strict" : "Invalid";
        }

        public final boolean equals(Object obj) {
            if (obj instanceof Strictness) {
                return this.value == ((Strictness) obj).value;
            }
            return false;
        }

        public final int hashCode() {
            return Integer.hashCode(this.value);
        }

        public final String toString() {
            return m268toStringimpl(this.value);
        }
    }

    /* loaded from: classes.dex */
    public final class WordBreak {
        public final int value;

        public final boolean equals(Object obj) {
            if (obj instanceof WordBreak) {
                return this.value == ((WordBreak) obj).value;
            }
            return false;
        }

        public final int hashCode() {
            return Integer.hashCode(this.value);
        }

        public final String toString() {
            int i = this.value;
            return i == 1 ? "WordBreak.None" : i == 2 ? "WordBreak.Phrase" : "Invalid";
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof LineBreak) {
            return this.mask == ((LineBreak) obj).mask;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.mask);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("LineBreak(strategy=");
        int i = this.mask;
        sb.append((Object) Strategy.m266toStringimpl(i & 255));
        sb.append(", strictness=");
        sb.append((Object) Strictness.m268toStringimpl((i >> 8) & 255));
        sb.append(", wordBreak=");
        int i2 = (i >> 16) & 255;
        sb.append((Object) (i2 == 1 ? "WordBreak.None" : i2 == 2 ? "WordBreak.Phrase" : "Invalid"));
        sb.append(')');
        return sb.toString();
    }
}
