package kotlin.text;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.ResultKt;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntProgressionIterator;

/* loaded from: classes.dex */
public abstract class StringsKt__StringsKt extends StringsKt__StringNumberConversionsKt {
    public static final int getLastIndex(CharSequence charSequence) {
        ResultKt.checkNotNullParameter(charSequence, "<this>");
        return charSequence.length() - 1;
    }

    public static final int indexOf(CharSequence charSequence, String str, int i, boolean z) {
        ResultKt.checkNotNullParameter(charSequence, "<this>");
        ResultKt.checkNotNullParameter(str, "string");
        return (z || !(charSequence instanceof String)) ? indexOf$StringsKt__StringsKt(charSequence, str, i, charSequence.length(), z, false) : ((String) charSequence).indexOf(str, i);
    }

    public static final int indexOf$StringsKt__StringsKt(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z, boolean z2) {
        IntProgression intProgression;
        if (z2) {
            int lastIndex = getLastIndex(charSequence);
            if (i > lastIndex) {
                i = lastIndex;
            }
            if (i2 < 0) {
                i2 = 0;
            }
            intProgression = new IntProgression(i, i2, -1);
        } else {
            if (i < 0) {
                i = 0;
            }
            int length = charSequence.length();
            if (i2 > length) {
                i2 = length;
            }
            intProgression = new IntProgression(i, i2, 1);
        }
        boolean z3 = charSequence instanceof String;
        int i3 = intProgression.step;
        int i4 = intProgression.last;
        int i5 = intProgression.first;
        if (z3 && (charSequence2 instanceof String)) {
            if ((i3 > 0 && i5 <= i4) || (i3 < 0 && i4 <= i5)) {
                while (!regionMatches(i5, charSequence2.length(), (String) charSequence2, (String) charSequence, z)) {
                    if (i5 != i4) {
                        i5 += i3;
                    }
                }
                return i5;
            }
        } else if ((i3 > 0 && i5 <= i4) || (i3 < 0 && i4 <= i5)) {
            while (!regionMatchesImpl(charSequence2, charSequence, i5, charSequence2.length(), z)) {
                if (i5 != i4) {
                    i5 += i3;
                }
            }
            return i5;
        }
        return -1;
    }

    public static int indexOf$default(CharSequence charSequence, char c, int i, boolean z, int i2) {
        int i3;
        char upperCase;
        char upperCase2;
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(c, i);
        }
        char[] cArr = {c};
        if (!z && (charSequence instanceof String)) {
            return ((String) charSequence).indexOf(cArr[0], i);
        }
        if (i < 0) {
            i = 0;
        }
        int i4 = new IntProgression(i, getLastIndex(charSequence), 1).last;
        boolean z2 = i <= i4;
        if (!z2) {
            i = i4;
        }
        while (z2) {
            if (i != i4) {
                i3 = i + 1;
            } else {
                if (!z2) {
                    throw new NoSuchElementException();
                }
                i3 = i;
                z2 = false;
            }
            char charAt = charSequence.charAt(i);
            char c2 = cArr[0];
            if (c2 == charAt || (z && ((upperCase = Character.toUpperCase(c2)) == (upperCase2 = Character.toUpperCase(charAt)) || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2)))) {
                return i;
            }
            i = i3;
        }
        return -1;
    }

    public static final boolean isBlank(String str) {
        ResultKt.checkNotNullParameter(str, "<this>");
        if (str.length() == 0) {
            return true;
        }
        Iterable intProgression = new IntProgression(0, str.length() - 1, 1);
        if ((intProgression instanceof Collection) && ((Collection) intProgression).isEmpty()) {
            return true;
        }
        Iterator it = intProgression.iterator();
        while (((IntProgressionIterator) it).hasNext) {
            char charAt = str.charAt(((IntProgressionIterator) it).nextInt());
            if (!Character.isWhitespace(charAt) && !Character.isSpaceChar(charAt)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean regionMatches(int i, int i2, String str, String str2, boolean z) {
        ResultKt.checkNotNullParameter(str, "<this>");
        ResultKt.checkNotNullParameter(str2, "other");
        return !z ? str.regionMatches(0, str2, i, i2) : str.regionMatches(z, 0, str2, i, i2);
    }

    public static final boolean regionMatchesImpl(CharSequence charSequence, CharSequence charSequence2, int i, int i2, boolean z) {
        char upperCase;
        char upperCase2;
        ResultKt.checkNotNullParameter(charSequence, "<this>");
        ResultKt.checkNotNullParameter(charSequence2, "other");
        if (i < 0 || charSequence.length() - i2 < 0 || i > charSequence2.length() - i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            char charAt = charSequence.charAt(i3);
            char charAt2 = charSequence2.charAt(i + i3);
            if (charAt != charAt2 && (!z || ((upperCase = Character.toUpperCase(charAt)) != (upperCase2 = Character.toUpperCase(charAt2)) && Character.toLowerCase(upperCase) != Character.toLowerCase(upperCase2)))) {
                return false;
            }
        }
        return true;
    }

    public static String substringAfter$default(String str, String str2) {
        ResultKt.checkNotNullParameter(str2, "delimiter");
        int indexOf$default = indexOf$default(str, str2, 0, 6);
        if (indexOf$default == -1) {
            return str;
        }
        String substring = str.substring(str2.length() + indexOf$default, str.length());
        ResultKt.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }

    public static String substringAfterLast$default(String str) {
        ResultKt.checkNotNullParameter(str, "<this>");
        ResultKt.checkNotNullParameter(str, "missingDelimiterValue");
        int lastIndexOf = str.lastIndexOf(46, getLastIndex(str));
        if (lastIndexOf == -1) {
            return str;
        }
        String substring = str.substring(lastIndexOf + 1, str.length());
        ResultKt.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        return substring;
    }

    public static /* synthetic */ int indexOf$default(CharSequence charSequence, String str, int i, int i2) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return indexOf(charSequence, str, i, false);
    }
}
