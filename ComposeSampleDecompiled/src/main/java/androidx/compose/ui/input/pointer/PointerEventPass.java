package androidx.compose.ui.input.pointer;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class PointerEventPass {
    public static final /* synthetic */ PointerEventPass[] $VALUES;
    public static final PointerEventPass Final;
    public static final PointerEventPass Initial;
    public static final PointerEventPass Main;

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Enum, androidx.compose.ui.input.pointer.PointerEventPass] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Enum, androidx.compose.ui.input.pointer.PointerEventPass] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Enum, androidx.compose.ui.input.pointer.PointerEventPass] */
    static {
        ?? r0 = new Enum("Initial", 0);
        Initial = r0;
        ?? r1 = new Enum("Main", 1);
        Main = r1;
        ?? r2 = new Enum("Final", 2);
        Final = r2;
        $VALUES = new PointerEventPass[]{r0, r1, r2};
    }

    public static PointerEventPass valueOf(String str) {
        return (PointerEventPass) Enum.valueOf(PointerEventPass.class, str);
    }

    public static PointerEventPass[] values() {
        return (PointerEventPass[]) $VALUES.clone();
    }
}
