package androidx.compose.ui.unit;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class LayoutDirection {
    public static final /* synthetic */ LayoutDirection[] $VALUES;
    public static final LayoutDirection Ltr;
    public static final LayoutDirection Rtl;

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Enum, androidx.compose.ui.unit.LayoutDirection] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Enum, androidx.compose.ui.unit.LayoutDirection] */
    static {
        ?? r0 = new Enum("Ltr", 0);
        Ltr = r0;
        ?? r1 = new Enum("Rtl", 1);
        Rtl = r1;
        $VALUES = new LayoutDirection[]{r0, r1};
    }

    public static LayoutDirection valueOf(String str) {
        return (LayoutDirection) Enum.valueOf(LayoutDirection.class, str);
    }

    public static LayoutDirection[] values() {
        return (LayoutDirection[]) $VALUES.clone();
    }
}
