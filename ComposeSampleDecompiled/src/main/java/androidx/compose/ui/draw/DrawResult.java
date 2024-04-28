package androidx.compose.ui.draw;

import android.content.res.Configuration;
import android.graphics.Typeface;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.text.android.CharSequenceCharacterIterator;
import androidx.compose.ui.text.caches.ContainerHelpersKt;
import androidx.compose.ui.text.caches.LruCache;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.PlatformTypefaces;
import androidx.compose.ui.unit.Constraints;
import java.text.BreakIterator;
import java.util.Locale;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class DrawResult implements HapticFeedback, PlatformTypefaces {
    public DrawResult(Configuration configuration, int i) {
        if (i != 1) {
            ResultKt.checkNotNullParameter(configuration, "newConfig");
        } else {
            ResultKt.checkNotNullParameter(configuration, "newConfig");
        }
    }

    public static float area(float[] fArr) {
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[2];
        float f4 = fArr[3];
        float f5 = fArr[4];
        float f6 = fArr[5];
        float f7 = (((((f3 * f6) + ((f2 * f5) + (f * f4))) - (f4 * f5)) - (f2 * f3)) - (f * f6)) * 0.5f;
        return f7 < 0.0f ? -f7 : f7;
    }

    public static int bitsNeedForSize(int i) {
        if (i < 8191) {
            return 13;
        }
        if (i < 32767) {
            return 15;
        }
        if (i < 65535) {
            return 16;
        }
        if (i < 262143) {
            return 18;
        }
        throw new IllegalArgumentException("Can't represent a size of " + i + " in Constraints");
    }

    /* renamed from: createAndroidTypefaceApi28-RetOiIg, reason: not valid java name */
    public static Typeface m64createAndroidTypefaceApi28RetOiIg(String str, FontWeight fontWeight, int i) {
        if (FontStyle.m258equalsimpl0(i, 0) && ResultKt.areEqual(fontWeight, FontWeight.Normal) && (str == null || str.length() == 0)) {
            Typeface typeface = Typeface.DEFAULT;
            ResultKt.checkNotNullExpressionValue(typeface, "DEFAULT");
            return typeface;
        }
        Typeface create = Typeface.create(str == null ? Typeface.DEFAULT : Typeface.create(str, 0), fontWeight.weight, FontStyle.m258equalsimpl0(i, 1));
        ResultKt.checkNotNullExpressionValue(create, "create(\n            fami…ontStyle.Italic\n        )");
        return create;
    }

    /* renamed from: createConstraints-Zbe2FdA$ui_unit_release, reason: not valid java name */
    public static long m65createConstraintsZbe2FdA$ui_unit_release(int i, int i2, int i3, int i4) {
        long j;
        int i5 = i4 == Integer.MAX_VALUE ? i3 : i4;
        int bitsNeedForSize = bitsNeedForSize(i5);
        int i6 = i2 == Integer.MAX_VALUE ? i : i2;
        int bitsNeedForSize2 = bitsNeedForSize(i6);
        if (bitsNeedForSize + bitsNeedForSize2 > 31) {
            throw new IllegalArgumentException("Can't represent a width of " + i6 + " and height of " + i5 + " in Constraints");
        }
        if (bitsNeedForSize2 == 13) {
            j = 3;
        } else if (bitsNeedForSize2 == 18) {
            j = 1;
        } else if (bitsNeedForSize2 == 15) {
            j = 2;
        } else {
            if (bitsNeedForSize2 != 16) {
                throw new IllegalStateException("Should only have the provided constants.");
            }
            j = 0;
        }
        int i7 = i2 == Integer.MAX_VALUE ? 0 : i2 + 1;
        int i8 = i4 != Integer.MAX_VALUE ? i4 + 1 : 0;
        int i9 = Constraints.MinHeightOffsets[(int) j];
        return (i7 << 33) | j | (i << 2) | (i3 << i9) | (i8 << (i9 + 31));
    }

    public static float cross(float f, float f2, float f3, float f4) {
        return (f * f4) - (f2 * f3);
    }

    /* renamed from: fixed-JhjzzOo, reason: not valid java name */
    public static long m66fixedJhjzzOo(int i, int i2) {
        if (i >= 0 && i2 >= 0) {
            return m65createConstraintsZbe2FdA$ui_unit_release(i, i, i2, i2);
        }
        throw new IllegalArgumentException(("width(" + i + ") and height(" + i2 + ") must be >= 0").toString());
    }

    public DrawResult(CharSequence charSequence, int i, Locale locale) {
        if (charSequence.length() >= 0) {
            if (i >= 0 && i <= charSequence.length()) {
                BreakIterator wordInstance = BreakIterator.getWordInstance(locale);
                ResultKt.checkNotNullExpressionValue(wordInstance, "getWordInstance(locale)");
                Math.max(0, -50);
                Math.min(charSequence.length(), i + 50);
                wordInstance.setText(new CharSequenceCharacterIterator(charSequence, i));
                return;
            }
            throw new IllegalArgumentException("input end index is outside the CharSequence".toString());
        }
        throw new IllegalArgumentException("input start index is outside the CharSequence".toString());
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.ui.text.caches.SimpleArrayMap, java.lang.Object] */
    public DrawResult() {
        new LruCache();
        ?? obj = new Object();
        obj.hashes = ContainerHelpersKt.EMPTY_INTS;
        obj.keyValues = ContainerHelpersKt.EMPTY_OBJECTS;
        obj._size = 0;
    }
}
