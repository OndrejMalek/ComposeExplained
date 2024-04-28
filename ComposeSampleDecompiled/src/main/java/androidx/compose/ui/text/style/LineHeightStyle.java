package androidx.compose.ui.text.style;

/* loaded from: classes.dex */
public final class LineHeightStyle {
    public static final LineHeightStyle Default = new LineHeightStyle(Alignment.Proportional);
    public final float alignment;

    /* loaded from: classes.dex */
    public abstract class Alignment {
        public static final float Bottom;
        public static final float Center;
        public static final float Proportional;

        static {
            m269constructorimpl(0.0f);
            m269constructorimpl(0.5f);
            Center = 0.5f;
            m269constructorimpl(-1.0f);
            Proportional = -1.0f;
            m269constructorimpl(1.0f);
            Bottom = 1.0f;
        }

        /* renamed from: constructor-impl, reason: not valid java name */
        public static void m269constructorimpl(float f) {
            if ((0.0f > f || f > 1.0f) && f != -1.0f) {
                throw new IllegalStateException("topRatio should be in [0..1] range or -1".toString());
            }
        }
    }

    public LineHeightStyle(float f) {
        this.alignment = f;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LineHeightStyle)) {
            return false;
        }
        float f = ((LineHeightStyle) obj).alignment;
        float f2 = Alignment.Center;
        return Float.compare(this.alignment, f) == 0;
    }

    public final int hashCode() {
        float f = Alignment.Center;
        return Integer.hashCode(17) + (Float.hashCode(this.alignment) * 31);
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder("LineHeightStyle(alignment=");
        float f = this.alignment;
        if (f == 0.0f) {
            float f2 = Alignment.Center;
            str = "LineHeightStyle.Alignment.Top";
        } else if (f == Alignment.Center) {
            str = "LineHeightStyle.Alignment.Center";
        } else if (f == Alignment.Proportional) {
            str = "LineHeightStyle.Alignment.Proportional";
        } else if (f == Alignment.Bottom) {
            str = "LineHeightStyle.Alignment.Bottom";
        } else {
            str = "LineHeightStyle.Alignment(topPercentage = " + f + ')';
        }
        sb.append((Object) str);
        sb.append(", trim=");
        sb.append((Object) "LineHeightStyle.Trim.Both");
        sb.append(')');
        return sb.toString();
    }
}
