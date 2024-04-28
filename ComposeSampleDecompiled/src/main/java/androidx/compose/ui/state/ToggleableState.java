package androidx.compose.ui.state;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class ToggleableState {
    public static final /* synthetic */ ToggleableState[] $VALUES;
    public static final ToggleableState Off;
    public static final ToggleableState On;

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.ui.state.ToggleableState, java.lang.Enum] */
    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.compose.ui.state.ToggleableState, java.lang.Enum] */
    /* JADX WARN: Type inference failed for: r2v2, types: [androidx.compose.ui.state.ToggleableState, java.lang.Enum] */
    static {
        ?? r0 = new Enum("On", 0);
        On = r0;
        ?? r1 = new Enum("Off", 1);
        Off = r1;
        $VALUES = new ToggleableState[]{r0, r1, new Enum("Indeterminate", 2)};
    }

    public static ToggleableState valueOf(String str) {
        return (ToggleableState) Enum.valueOf(ToggleableState.class, str);
    }

    public static ToggleableState[] values() {
        return (ToggleableState[]) $VALUES.clone();
    }
}
