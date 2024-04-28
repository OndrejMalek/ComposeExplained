package kotlin.coroutines.intrinsics;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class CoroutineSingletons {
    public static final /* synthetic */ CoroutineSingletons[] $VALUES;
    public static final CoroutineSingletons COROUTINE_SUSPENDED;
    public static final CoroutineSingletons RESUMED;
    public static final CoroutineSingletons UNDECIDED;

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Enum, kotlin.coroutines.intrinsics.CoroutineSingletons] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Enum, kotlin.coroutines.intrinsics.CoroutineSingletons] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Enum, kotlin.coroutines.intrinsics.CoroutineSingletons] */
    static {
        ?? r0 = new Enum("COROUTINE_SUSPENDED", 0);
        COROUTINE_SUSPENDED = r0;
        ?? r1 = new Enum("UNDECIDED", 1);
        UNDECIDED = r1;
        ?? r2 = new Enum("RESUMED", 2);
        RESUMED = r2;
        $VALUES = new CoroutineSingletons[]{r0, r1, r2};
    }

    public static CoroutineSingletons valueOf(String str) {
        return (CoroutineSingletons) Enum.valueOf(CoroutineSingletons.class, str);
    }

    public static CoroutineSingletons[] values() {
        return (CoroutineSingletons[]) $VALUES.clone();
    }
}
