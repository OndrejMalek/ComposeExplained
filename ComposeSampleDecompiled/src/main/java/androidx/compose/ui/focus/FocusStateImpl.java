package androidx.compose.ui.focus;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class FocusStateImpl implements FocusState {
    public static final /* synthetic */ FocusStateImpl[] $VALUES;
    public static final FocusStateImpl Active;
    public static final FocusStateImpl ActiveParent;
    public static final FocusStateImpl Inactive;

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Enum, androidx.compose.ui.focus.FocusStateImpl] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Enum, androidx.compose.ui.focus.FocusStateImpl] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Enum, androidx.compose.ui.focus.FocusStateImpl] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Enum, androidx.compose.ui.focus.FocusStateImpl] */
    static {
        ?? r0 = new Enum("Active", 0);
        Active = r0;
        ?? r1 = new Enum("ActiveParent", 1);
        ActiveParent = r1;
        ?? r2 = new Enum("Captured", 2);
        ?? r3 = new Enum("Inactive", 3);
        Inactive = r3;
        $VALUES = new FocusStateImpl[]{r0, r1, r2, r3};
    }

    public static FocusStateImpl valueOf(String str) {
        return (FocusStateImpl) Enum.valueOf(FocusStateImpl.class, str);
    }

    public static FocusStateImpl[] values() {
        return (FocusStateImpl[]) $VALUES.clone();
    }

    public final boolean isFocused() {
        int ordinal = ordinal();
        if (ordinal == 0) {
            return true;
        }
        if (ordinal != 1) {
            if (ordinal == 2) {
                return true;
            }
            if (ordinal != 3) {
                throw new RuntimeException();
            }
        }
        return false;
    }
}
