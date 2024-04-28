package androidx.compose.animation.core;

/* loaded from: classes.dex */
public abstract /* synthetic */ class AnimationEndReason$EnumUnboxingLocalUtility {
    public static final /* synthetic */ int[] $VALUES = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29};

    public static /* synthetic */ int compareTo(int i, int i2) {
        if (i == 0 || i2 == 0) {
            throw null;
        }
        return i - i2;
    }

    public static int m(float f, int i, int i2) {
        return (Float.hashCode(f) + i) * i2;
    }

    public static /* synthetic */ int ordinal(int i) {
        if (i != 0) {
            return i - 1;
        }
        throw null;
    }

    public static /* synthetic */ String stringValueOf(int i) {
        return i != 1 ? i != 2 ? "null" : "Finished" : "BoundReached";
    }

    public static /* synthetic */ int[] values(int i) {
        int[] iArr = new int[i];
        System.arraycopy($VALUES, 0, iArr, 0, i);
        return iArr;
    }
}
