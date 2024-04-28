package androidx.compose.foundation.text.modifiers;

/* loaded from: classes.dex */
public abstract class MinLinesConstrainerKt {
    public static final String EmptyTextReplacement;
    public static final String TwoLineTextReplacement;

    static {
        char charAt = "H".charAt(0);
        char[] cArr = new char[10];
        for (int i = 0; i < 10; i++) {
            cArr[i] = charAt;
        }
        String str = new String(cArr);
        EmptyTextReplacement = str;
        TwoLineTextReplacement = str + '\n' + str;
    }
}
