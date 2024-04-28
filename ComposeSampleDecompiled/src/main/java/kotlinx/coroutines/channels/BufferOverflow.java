package kotlinx.coroutines.channels;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class BufferOverflow {
    public static final /* synthetic */ BufferOverflow[] $VALUES;
    public static final BufferOverflow DROP_LATEST;
    public static final BufferOverflow DROP_OLDEST;
    public static final BufferOverflow SUSPEND;

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Enum, kotlinx.coroutines.channels.BufferOverflow] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Enum, kotlinx.coroutines.channels.BufferOverflow] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Enum, kotlinx.coroutines.channels.BufferOverflow] */
    static {
        ?? r0 = new Enum("SUSPEND", 0);
        SUSPEND = r0;
        ?? r1 = new Enum("DROP_OLDEST", 1);
        DROP_OLDEST = r1;
        ?? r2 = new Enum("DROP_LATEST", 2);
        DROP_LATEST = r2;
        $VALUES = new BufferOverflow[]{r0, r1, r2};
    }

    public static BufferOverflow valueOf(String str) {
        return (BufferOverflow) Enum.valueOf(BufferOverflow.class, str);
    }

    public static BufferOverflow[] values() {
        return (BufferOverflow[]) $VALUES.clone();
    }
}
