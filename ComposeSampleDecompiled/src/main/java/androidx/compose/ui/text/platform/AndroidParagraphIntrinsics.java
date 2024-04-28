package androidx.compose.ui.text.platform;

import _COROUTINE._BOUNDARY;
import android.text.Layout;
import android.text.TextPaint;
import androidx.compose.runtime.State;
import androidx.compose.ui.node.LayoutNode$$ExternalSyntheticLambda0;
import androidx.compose.ui.text.ParagraphIntrinsics;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.android.CharSequenceCharacterIterator;
import androidx.compose.ui.text.android.LayoutIntrinsics;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Density;
import androidx.core.view.MenuHostHelper;
import androidx.emoji2.text.EmojiCompat;
import java.text.BreakIterator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import kotlin.Pair;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AndroidParagraphIntrinsics implements ParagraphIntrinsics {
    public final CharSequence charSequence;
    public final Density density;
    public final boolean emojiCompatProcessed;
    public final FontFamily.Resolver fontFamilyResolver;
    public final LayoutIntrinsics layoutIntrinsics;
    public final List placeholders;
    public MenuHostHelper resolvedTypefaces;
    public final List spanStyles;
    public final TextStyle style;
    public final String text;
    public final int textDirectionHeuristic;
    public final AndroidTextPaint textPaint;

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:94:0x030d */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x03c6, code lost:
    
        if (kotlin.ResultKt.m297isUnspecifiedR2X_6o(r8.lineHeight) == false) goto L203;
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0495, code lost:
    
        if (androidx.compose.ui.unit.TextUnit.m283equalsimpl0(r13, kotlin.ResultKt.getSp(0)) == false) goto L248;
     */
    /* JADX WARN: Code restructure failed: missing block: B:413:0x00bd, code lost:
    
        if (r11 == null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:416:0x00c9, code lost:
    
        if (r11 == 1) goto L22;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:122:0x03b0  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x03ce  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x03f1  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x047a  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0526  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x054b  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x058e  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0682  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x07b3  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:267:0x0822  */
    /* JADX WARN: Removed duplicated region for block: B:273:0x084f  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x05c4  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x0512  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x0409  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x03d1  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x030b  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:378:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x02ab  */
    /* JADX WARN: Removed duplicated region for block: B:389:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:393:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:399:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x02a0 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x032f  */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r5v14, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r5v7, types: [android.text.Spannable] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AndroidParagraphIntrinsics(androidx.compose.ui.text.TextStyle r43, androidx.compose.ui.text.font.FontFamily.Resolver r44, androidx.compose.ui.unit.Density r45, java.lang.String r46, java.util.List r47, java.util.List r48) {
        /*
            r42 = this;
            r0 = r42
            r1 = r43
            r2 = r44
            r3 = r45
            r4 = r46
            r5 = r47
            r6 = r48
            java.lang.String r7 = "text"
            kotlin.ResultKt.checkNotNullParameter(r4, r7)
            java.lang.String r8 = "style"
            kotlin.ResultKt.checkNotNullParameter(r1, r8)
            java.lang.String r8 = "spanStyles"
            kotlin.ResultKt.checkNotNullParameter(r5, r8)
            java.lang.String r8 = "placeholders"
            kotlin.ResultKt.checkNotNullParameter(r6, r8)
            java.lang.String r9 = "fontFamilyResolver"
            kotlin.ResultKt.checkNotNullParameter(r2, r9)
            java.lang.String r9 = "density"
            kotlin.ResultKt.checkNotNullParameter(r3, r9)
            r42.<init>()
            r0.text = r4
            r0.style = r1
            r0.spanStyles = r5
            r0.placeholders = r6
            r0.fontFamilyResolver = r2
            r0.density = r3
            androidx.compose.ui.text.platform.AndroidTextPaint r4 = new androidx.compose.ui.text.platform.AndroidTextPaint
            float r6 = r45.getDensity()
            r4.<init>(r6)
            r0.textPaint = r4
            boolean r6 = _COROUTINE._BOUNDARY.access$getHasEmojiCompat(r43)
            if (r6 != 0) goto L4e
            r6 = 0
            goto L6e
        L4e:
            androidx.compose.ui.text.platform.DefaultImpl r6 = androidx.compose.ui.text.platform.EmojiCompatStatus.delegate
            androidx.compose.ui.text.platform.DefaultImpl r6 = androidx.compose.ui.text.platform.EmojiCompatStatus.delegate
            androidx.compose.runtime.State r11 = r6.loadState
            if (r11 == 0) goto L57
            goto L64
        L57:
            androidx.emoji2.text.EmojiCompat r11 = androidx.emoji2.text.EmojiCompat.sInstance
            if (r11 == 0) goto L62
            androidx.compose.runtime.State r11 = r6.getFontLoadState()
            r6.loadState = r11
            goto L64
        L62:
            androidx.compose.ui.text.platform.ImmutableBool r11 = androidx.compose.ui.text.platform.EmojiCompatStatusKt.Falsey
        L64:
            java.lang.Object r6 = r11.getValue()
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
        L6e:
            r0.emojiCompatProcessed = r6
            androidx.compose.ui.text.ParagraphStyle r6 = r1.paragraphStyle
            androidx.compose.ui.text.style.TextDirection r11 = r6.textDirection
            androidx.compose.ui.text.SpanStyle r1 = r1.spanStyle
            androidx.compose.ui.text.intl.LocaleList r12 = r1.localeList
            r13 = 3
            if (r11 == 0) goto L7e
            int r11 = r11.value
            goto L7f
        L7e:
            r11 = r13
        L7f:
            r14 = 4
            boolean r14 = androidx.compose.ui.text.style.TextDirection.m271equalsimpl0(r11, r14)
            r15 = 2
            r10 = 1
            if (r14 == 0) goto L8a
        L88:
            r11 = r15
            goto Lcc
        L8a:
            r14 = 5
            boolean r14 = androidx.compose.ui.text.style.TextDirection.m271equalsimpl0(r11, r14)
            if (r14 == 0) goto L93
        L91:
            r11 = r13
            goto Lcc
        L93:
            boolean r14 = androidx.compose.ui.text.style.TextDirection.m271equalsimpl0(r11, r10)
            if (r14 == 0) goto L9b
            r11 = 0
            goto Lcc
        L9b:
            boolean r14 = androidx.compose.ui.text.style.TextDirection.m271equalsimpl0(r11, r15)
            if (r14 == 0) goto La3
            r11 = r10
            goto Lcc
        La3:
            boolean r11 = androidx.compose.ui.text.style.TextDirection.m271equalsimpl0(r11, r13)
            if (r11 == 0) goto L869
            if (r12 == 0) goto Lbf
            java.util.List r11 = r12.localeList
            r12 = 0
            java.lang.Object r11 = r11.get(r12)
            androidx.compose.ui.text.intl.Locale r11 = (androidx.compose.ui.text.intl.Locale) r11
            androidx.compose.ui.text.intl.AndroidLocale r11 = r11.platformLocale
            java.lang.String r12 = "null cannot be cast to non-null type androidx.compose.ui.text.intl.AndroidLocale"
            kotlin.ResultKt.checkNotNull(r11, r12)
            java.util.Locale r11 = r11.javaLocale
            if (r11 != 0) goto Lc3
        Lbf:
            java.util.Locale r11 = java.util.Locale.getDefault()
        Lc3:
            int r11 = android.text.TextUtils.getLayoutDirectionFromLocale(r11)
            if (r11 == 0) goto L88
            if (r11 == r10) goto L91
            goto L88
        Lcc:
            r0.textDirectionHeuristic = r11
            androidx.compose.ui.text.platform.AndroidParagraphIntrinsics$resolveTypeface$1 r11 = new androidx.compose.ui.text.platform.AndroidParagraphIntrinsics$resolveTypeface$1
            r11.<init>()
            androidx.compose.ui.text.style.TextMotion r6 = r6.textMotion
            if (r6 != 0) goto Ld9
            androidx.compose.ui.text.style.TextMotion r6 = androidx.compose.ui.text.style.TextMotion.Static
        Ld9:
            boolean r12 = r6.subpixelTextPositioning
            if (r12 == 0) goto Le4
            int r12 = r4.getFlags()
            r12 = r12 | 128(0x80, float:1.794E-43)
            goto Lea
        Le4:
            int r12 = r4.getFlags()
            r12 = r12 & (-129(0xffffffffffffff7f, float:NaN))
        Lea:
            r4.setFlags(r12)
            int r6 = r6.linearity
            if (r6 != r10) goto Lff
            int r6 = r4.getFlags()
            r6 = r6 | 64
            r4.setFlags(r6)
            r12 = 0
            r4.setHinting(r12)
            goto L115
        Lff:
            r12 = 0
            if (r6 != r15) goto L109
            r4.getFlags()
            r4.setHinting(r10)
            goto L115
        L109:
            if (r6 != r13) goto L112
            r4.getFlags()
            r4.setHinting(r12)
            goto L115
        L112:
            r4.getFlags()
        L115:
            boolean r6 = r47.isEmpty()
            r6 = r6 ^ r10
            long r12 = r1.fontSize
            long r14 = androidx.compose.ui.unit.TextUnit.m284getTypeUIouoOA(r12)
            r48 = r11
            r10 = 4294967296(0x100000000, double:2.121995791E-314)
            boolean r16 = androidx.compose.ui.unit.TextUnitType.m287equalsimpl0(r14, r10)
            r10 = 8589934592(0x200000000, double:4.243991582E-314)
            if (r16 == 0) goto L13a
            float r12 = r3.mo31toPxR2X_6o(r12)
            r4.setTextSize(r12)
            goto L14c
        L13a:
            boolean r14 = androidx.compose.ui.unit.TextUnitType.m287equalsimpl0(r14, r10)
            if (r14 == 0) goto L14c
            float r14 = r4.getTextSize()
            float r12 = androidx.compose.ui.unit.TextUnit.m285getValueimpl(r12)
            float r12 = r12 * r14
            r4.setTextSize(r12)
        L14c:
            boolean r12 = _COROUTINE._BOUNDARY.hasFontAttributes(r1)
            if (r12 == 0) goto L198
            androidx.compose.ui.text.font.FontWeight r12 = r1.fontWeight
            if (r12 != 0) goto L158
            androidx.compose.ui.text.font.FontWeight r12 = androidx.compose.ui.text.font.FontWeight.Normal
        L158:
            androidx.compose.ui.text.font.FontStyle r13 = r1.fontStyle
            if (r13 == 0) goto L15f
            int r13 = r13.value
            goto L160
        L15f:
            r13 = 0
        L160:
            androidx.compose.ui.text.font.FontSynthesis r14 = r1.fontSynthesis
            if (r14 == 0) goto L167
            int r14 = r14.value
            goto L168
        L167:
            r14 = 1
        L168:
            java.lang.String r15 = "fontWeight"
            kotlin.ResultKt.checkNotNullParameter(r12, r15)
            androidx.compose.ui.text.font.FontFamilyResolverImpl r2 = (androidx.compose.ui.text.font.FontFamilyResolverImpl) r2
            androidx.compose.ui.text.font.FontFamily r15 = r1.fontFamily
            androidx.compose.ui.text.font.TypefaceResult r2 = r2.m257resolveDPcqOEQ(r15, r12, r13, r14)
            boolean r12 = r2 instanceof androidx.compose.ui.text.font.TypefaceResult.Immutable
            java.lang.String r13 = "null cannot be cast to non-null type android.graphics.Typeface"
            if (r12 != 0) goto L18c
            androidx.core.view.MenuHostHelper r12 = new androidx.core.view.MenuHostHelper
            androidx.core.view.MenuHostHelper r14 = r0.resolvedTypefaces
            r12.<init>(r2, r14)
            r0.resolvedTypefaces = r12
            java.lang.Object r2 = r12.mProviderToLifecycleContainers
            kotlin.ResultKt.checkNotNull(r2, r13)
            android.graphics.Typeface r2 = (android.graphics.Typeface) r2
            goto L195
        L18c:
            androidx.compose.ui.text.font.TypefaceResult$Immutable r2 = (androidx.compose.ui.text.font.TypefaceResult.Immutable) r2
            java.lang.Object r2 = r2.value
            kotlin.ResultKt.checkNotNull(r2, r13)
            android.graphics.Typeface r2 = (android.graphics.Typeface) r2
        L195:
            r4.setTypeface(r2)
        L198:
            androidx.compose.ui.text.intl.LocaleList r2 = r1.localeList
            androidx.compose.ui.text.platform.extensions.LocaleListHelperMethods r12 = androidx.compose.ui.text.platform.extensions.LocaleListHelperMethods.INSTANCE
            if (r2 == 0) goto L1ad
            androidx.compose.ui.text.intl.AndroidLocaleDelegateAPI24 r13 = androidx.compose.ui.text.intl.PlatformLocaleKt.platformLocaleDelegate
            androidx.compose.ui.text.intl.LocaleList r13 = r13.getCurrent()
            boolean r13 = kotlin.ResultKt.areEqual(r2, r13)
            if (r13 != 0) goto L1ad
            r12.setTextLocales(r4, r2)
        L1ad:
            java.lang.String r2 = r1.fontFeatureSettings
            if (r2 == 0) goto L1bc
            java.lang.String r13 = ""
            boolean r13 = kotlin.ResultKt.areEqual(r2, r13)
            if (r13 != 0) goto L1bc
            r4.setFontFeatureSettings(r2)
        L1bc:
            androidx.compose.ui.text.style.TextGeometricTransform r2 = r1.textGeometricTransform
            if (r2 == 0) goto L1dc
            androidx.compose.ui.text.style.TextGeometricTransform r13 = androidx.compose.ui.text.style.TextGeometricTransform.None
            boolean r13 = kotlin.ResultKt.areEqual(r2, r13)
            if (r13 != 0) goto L1dc
            float r13 = r4.getTextScaleX()
            float r14 = r2.scaleX
            float r13 = r13 * r14
            r4.setTextScaleX(r13)
            float r13 = r4.getTextSkewX()
            float r2 = r2.skewX
            float r13 = r13 + r2
            r4.setTextSkewX(r13)
        L1dc:
            androidx.compose.ui.text.style.TextForegroundStyle r2 = r1.textForegroundStyle
            long r13 = r2.mo264getColor0d7_KjU()
            long r10 = androidx.compose.ui.graphics.Color.Unspecified
            int r17 = (r13 > r10 ? 1 : (r13 == r10 ? 0 : -1))
            r15 = 0
            if (r17 == 0) goto L1f1
            androidx.compose.ui.graphics.AndroidPaint r5 = r4.composePaint
            r5.m92setColor8_81llA(r13)
            r5.setShader(r15)
        L1f1:
            androidx.compose.ui.graphics.Brush r5 = r2.getBrush()
            long r13 = androidx.compose.ui.geometry.Size.Unspecified
            float r2 = r2.getAlpha()
            r4.m263setBrush12SF9DM(r5, r13, r2)
            androidx.compose.ui.graphics.Shadow r2 = r1.shadow
            r4.setShadow(r2)
            androidx.compose.ui.text.style.TextDecoration r2 = r1.textDecoration
            r4.setTextDecoration(r2)
            androidx.compose.ui.graphics.drawscope.DrawStyle r2 = r1.drawStyle
            r4.setDrawStyle(r2)
            long r13 = r1.letterSpacing
            r2 = r8
            r5 = r9
            long r8 = androidx.compose.ui.unit.TextUnit.m284getTypeUIouoOA(r13)
            r20 = r10
            r10 = 4294967296(0x100000000, double:2.121995791E-314)
            boolean r8 = androidx.compose.ui.unit.TextUnitType.m287equalsimpl0(r8, r10)
            r9 = 0
            if (r8 == 0) goto L244
            float r8 = androidx.compose.ui.unit.TextUnit.m285getValueimpl(r13)
            int r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
            if (r8 != 0) goto L22c
            goto L244
        L22c:
            float r8 = r4.getTextSize()
            float r10 = r4.getTextScaleX()
            float r10 = r10 * r8
            float r3 = r3.mo31toPxR2X_6o(r13)
            int r8 = (r10 > r9 ? 1 : (r10 == r9 ? 0 : -1))
            if (r8 != 0) goto L23f
        L23d:
            r8 = r2
            goto L25b
        L23f:
            float r3 = r3 / r10
            r4.setLetterSpacing(r3)
            goto L23d
        L244:
            long r10 = androidx.compose.ui.unit.TextUnit.m284getTypeUIouoOA(r13)
            r8 = r2
            r2 = 8589934592(0x200000000, double:4.243991582E-314)
            boolean r10 = androidx.compose.ui.unit.TextUnitType.m287equalsimpl0(r10, r2)
            if (r10 == 0) goto L25b
            float r10 = androidx.compose.ui.unit.TextUnit.m285getValueimpl(r13)
            r4.setLetterSpacing(r10)
        L25b:
            if (r6 == 0) goto L277
            long r10 = androidx.compose.ui.unit.TextUnit.m284getTypeUIouoOA(r13)
            r2 = 4294967296(0x100000000, double:2.121995791E-314)
            boolean r4 = androidx.compose.ui.unit.TextUnitType.m287equalsimpl0(r10, r2)
            if (r4 == 0) goto L277
            float r2 = androidx.compose.ui.unit.TextUnit.m285getValueimpl(r13)
            int r2 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r2 != 0) goto L275
            goto L277
        L275:
            r2 = 1
            goto L278
        L277:
            r2 = 0
        L278:
            long r3 = r1.background
            r10 = r20
            boolean r6 = androidx.compose.ui.graphics.Color.m121equalsimpl0(r3, r10)
            if (r6 != 0) goto L28c
            long r9 = androidx.compose.ui.graphics.Color.Transparent
            boolean r6 = androidx.compose.ui.graphics.Color.m121equalsimpl0(r3, r9)
            if (r6 != 0) goto L28c
            r6 = 1
            goto L28d
        L28c:
            r6 = 0
        L28d:
            androidx.compose.ui.text.style.BaselineShift r1 = r1.baselineShift
            if (r1 == 0) goto L29d
            float r9 = r1.multiplier
            r10 = 0
            int r9 = java.lang.Float.compare(r9, r10)
            if (r9 != 0) goto L29b
            goto L29d
        L29b:
            r9 = 1
            goto L29e
        L29d:
            r9 = 0
        L29e:
            if (r2 != 0) goto L2a6
            if (r6 != 0) goto L2a6
            if (r9 != 0) goto L2a6
            r1 = r15
            goto L2dc
        L2a6:
            if (r2 == 0) goto L2ab
        L2a8:
            r32 = r13
            goto L2ae
        L2ab:
            long r13 = androidx.compose.ui.unit.TextUnit.Unspecified
            goto L2a8
        L2ae:
            if (r6 == 0) goto L2b3
            r37 = r3
            goto L2b5
        L2b3:
            r37 = r20
        L2b5:
            if (r9 == 0) goto L2ba
            r34 = r1
            goto L2bc
        L2ba:
            r34 = r15
        L2bc:
            androidx.compose.ui.text.SpanStyle r1 = new androidx.compose.ui.text.SpanStyle
            r22 = r1
            r39 = 0
            r40 = 0
            r23 = 0
            r25 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r30 = 0
            r31 = 0
            r35 = 0
            r36 = 0
            r41 = 63103(0xf67f, float:8.8426E-41)
            r22.<init>(r23, r25, r27, r28, r29, r30, r31, r32, r34, r35, r36, r37, r39, r40, r41)
        L2dc:
            if (r1 == 0) goto L30b
            int r2 = r47.size()
            r3 = 1
            int r2 = r2 + r3
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>(r2)
            r4 = 0
        L2ea:
            if (r4 >= r2) goto L30d
            if (r4 != 0) goto L2fb
            androidx.compose.ui.text.AnnotatedString$Range r6 = new androidx.compose.ui.text.AnnotatedString$Range
            java.lang.String r9 = r0.text
            int r9 = r9.length()
            r10 = 0
            r6.<init>(r10, r9, r1)
            goto L305
        L2fb:
            java.util.List r6 = r0.spanStyles
            int r9 = r4 + (-1)
            java.lang.Object r6 = r6.get(r9)
            androidx.compose.ui.text.AnnotatedString$Range r6 = (androidx.compose.ui.text.AnnotatedString.Range) r6
        L305:
            r3.add(r6)
            int r4 = r4 + 1
            goto L2ea
        L30b:
            r3 = r47
        L30d:
            java.lang.String r1 = r0.text
            androidx.compose.ui.text.platform.AndroidTextPaint r2 = r0.textPaint
            float r2 = r2.getTextSize()
            androidx.compose.ui.text.TextStyle r4 = r0.style
            java.util.List r6 = r0.placeholders
            androidx.compose.ui.unit.Density r9 = r0.density
            boolean r10 = r0.emojiCompatProcessed
            androidx.compose.ui.text.platform.AndroidParagraphHelper_androidKt$NoopSpan$1 r11 = androidx.compose.ui.text.platform.AndroidParagraphHelper_androidKt.NoopSpan
            kotlin.ResultKt.checkNotNullParameter(r1, r7)
            java.lang.String r7 = "contextTextStyle"
            kotlin.ResultKt.checkNotNullParameter(r4, r7)
            kotlin.ResultKt.checkNotNullParameter(r6, r8)
            kotlin.ResultKt.checkNotNullParameter(r9, r5)
            if (r10 == 0) goto L3a7
            androidx.emoji2.text.EmojiCompat r5 = androidx.emoji2.text.EmojiCompat.sInstance
            if (r5 == 0) goto L3a7
            androidx.emoji2.text.EmojiCompat r5 = androidx.emoji2.text.EmojiCompat.get()
            r5.getClass()
            int r7 = r1.length()
            int r8 = r5.getLoadState()
            r10 = 1
            if (r8 != r10) goto L347
            r8 = 1
            goto L348
        L347:
            r8 = 0
        L348:
            if (r8 == 0) goto L39f
            if (r7 < 0) goto L397
            if (r7 < 0) goto L350
            r8 = 1
            goto L351
        L350:
            r8 = 0
        L351:
            if (r8 == 0) goto L38f
            int r8 = r1.length()
            if (r8 < 0) goto L35b
            r8 = 1
            goto L35c
        L35b:
            r8 = 0
        L35c:
            if (r8 == 0) goto L387
            int r8 = r1.length()
            if (r7 > r8) goto L366
            r8 = 1
            goto L367
        L366:
            r8 = 0
        L367:
            if (r8 == 0) goto L37f
            int r8 = r1.length()
            if (r8 == 0) goto L37a
            if (r7 != 0) goto L372
            goto L37a
        L372:
            androidx.emoji2.text.EmojiCompat$CompatInternal19 r5 = r5.mHelper
            r8 = 0
            java.lang.CharSequence r5 = r5.process(r1, r7, r8)
            goto L37b
        L37a:
            r5 = r1
        L37b:
            kotlin.ResultKt.checkNotNull(r5)
            goto L3a8
        L37f:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "end should be < than charSequence length"
            r1.<init>(r2)
            throw r1
        L387:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "start should be < than charSequence length"
            r1.<init>(r2)
            throw r1
        L38f:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "start should be <= than end"
            r1.<init>(r2)
            throw r1
        L397:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r2 = "end cannot be negative"
            r1.<init>(r2)
            throw r1
        L39f:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Not initialized yet"
            r1.<init>(r2)
            throw r1
        L3a7:
            r5 = r1
        L3a8:
            boolean r7 = r3.isEmpty()
            androidx.compose.ui.text.ParagraphStyle r8 = r4.paragraphStyle
            if (r7 == 0) goto L3ca
            boolean r7 = r6.isEmpty()
            if (r7 == 0) goto L3ca
            androidx.compose.ui.text.style.TextIndent r7 = r8.textIndent
            androidx.compose.ui.text.style.TextIndent r10 = androidx.compose.ui.text.style.TextIndent.None
            boolean r7 = kotlin.ResultKt.areEqual(r7, r10)
            if (r7 == 0) goto L3ca
            long r10 = r8.lineHeight
            boolean r7 = kotlin.ResultKt.m297isUnspecifiedR2X_6o(r10)
            if (r7 == 0) goto L3ca
            goto L851
        L3ca:
            boolean r7 = r5 instanceof android.text.Spannable
            if (r7 == 0) goto L3d1
            android.text.Spannable r5 = (android.text.Spannable) r5
            goto L3d7
        L3d1:
            android.text.SpannableString r7 = new android.text.SpannableString
            r7.<init>(r5)
            r5 = r7
        L3d7:
            androidx.compose.ui.text.SpanStyle r7 = r4.spanStyle
            androidx.compose.ui.text.style.TextDecoration r10 = r7.textDecoration
            androidx.compose.ui.text.style.TextDecoration r11 = androidx.compose.ui.text.style.TextDecoration.Underline
            boolean r10 = kotlin.ResultKt.areEqual(r10, r11)
            if (r10 == 0) goto L3ed
            androidx.compose.ui.text.platform.AndroidParagraphHelper_androidKt$NoopSpan$1 r10 = androidx.compose.ui.text.platform.AndroidParagraphHelper_androidKt.NoopSpan
            int r1 = r1.length()
            r13 = 0
            _COROUTINE._BOUNDARY.setSpan(r5, r10, r13, r1)
        L3ed:
            androidx.compose.ui.text.PlatformTextStyle r1 = r4.platformStyle
            if (r1 == 0) goto L3f9
            androidx.compose.ui.text.PlatformParagraphStyle r1 = r1.paragraphStyle
            if (r1 == 0) goto L3f9
            boolean r1 = r1.includeFontPadding
            if (r1 == 0) goto L418
        L3f9:
            androidx.compose.ui.text.style.LineHeightStyle r1 = r8.lineHeightStyle
            if (r1 != 0) goto L418
            long r13 = r8.lineHeight
            float r1 = _COROUTINE._BOUNDARY.m18resolveLineHeightInPxo2QH7mI(r13, r2, r9)
            boolean r4 = java.lang.Float.isNaN(r1)
            if (r4 != 0) goto L416
            androidx.compose.ui.text.android.style.LineHeightSpan r4 = new androidx.compose.ui.text.android.style.LineHeightSpan
            r4.<init>(r1)
            int r1 = r5.length()
            r10 = 0
            _COROUTINE._BOUNDARY.setSpan(r5, r4, r10, r1)
        L416:
            r10 = 0
            goto L476
        L418:
            androidx.compose.ui.text.style.LineHeightStyle r1 = r8.lineHeightStyle
            if (r1 != 0) goto L41e
            androidx.compose.ui.text.style.LineHeightStyle r1 = androidx.compose.ui.text.style.LineHeightStyle.Default
        L41e:
            java.lang.String r4 = "lineHeightStyle"
            kotlin.ResultKt.checkNotNullParameter(r1, r4)
            long r13 = r8.lineHeight
            float r21 = _COROUTINE._BOUNDARY.m18resolveLineHeightInPxo2QH7mI(r13, r2, r9)
            boolean r4 = java.lang.Float.isNaN(r21)
            if (r4 != 0) goto L416
            int r4 = r5.length()
            if (r4 != 0) goto L436
            goto L448
        L436:
            int r4 = r5.length()
            if (r4 == 0) goto L46e
            int r4 = kotlin.text.StringsKt__StringsKt.getLastIndex(r5)
            char r4 = r5.charAt(r4)
            r10 = 10
            if (r4 != r10) goto L451
        L448:
            int r4 = r5.length()
            r10 = 1
            int r4 = r4 + r10
        L44e:
            r22 = r4
            goto L456
        L451:
            int r4 = r5.length()
            goto L44e
        L456:
            androidx.compose.ui.text.android.style.LineHeightStyleSpan r4 = new androidx.compose.ui.text.android.style.LineHeightStyleSpan
            r24 = 1
            float r1 = r1.alignment
            r23 = 1
            r20 = r4
            r25 = r1
            r20.<init>(r21, r22, r23, r24, r25)
            int r1 = r5.length()
            r10 = 0
            _COROUTINE._BOUNDARY.setSpan(r5, r4, r10, r1)
            goto L476
        L46e:
            java.util.NoSuchElementException r1 = new java.util.NoSuchElementException
            java.lang.String r2 = "Char sequence is empty."
            r1.<init>(r2)
            throw r1
        L476:
            androidx.compose.ui.text.style.TextIndent r1 = r8.textIndent
            if (r1 == 0) goto L512
            long r13 = kotlin.ResultKt.getSp(r10)
            r45 = r11
            long r10 = r1.firstLine
            boolean r4 = androidx.compose.ui.unit.TextUnit.m283equalsimpl0(r10, r13)
            long r13 = r1.restLine
            if (r4 == 0) goto L498
            r4 = r6
            r8 = r7
            r1 = 0
            long r6 = kotlin.ResultKt.getSp(r1)
            boolean r1 = androidx.compose.ui.unit.TextUnit.m283equalsimpl0(r13, r6)
            if (r1 != 0) goto L516
            goto L49a
        L498:
            r4 = r6
            r8 = r7
        L49a:
            boolean r1 = kotlin.ResultKt.m297isUnspecifiedR2X_6o(r10)
            if (r1 != 0) goto L516
            boolean r1 = kotlin.ResultKt.m297isUnspecifiedR2X_6o(r13)
            if (r1 == 0) goto L4a8
            goto L516
        L4a8:
            long r6 = androidx.compose.ui.unit.TextUnit.m284getTypeUIouoOA(r10)
            r0 = 4294967296(0x100000000, double:2.121995791E-314)
            boolean r16 = androidx.compose.ui.unit.TextUnitType.m287equalsimpl0(r6, r0)
            if (r16 == 0) goto L4bc
            float r10 = r9.mo31toPxR2X_6o(r10)
            goto L4cf
        L4bc:
            r0 = 8589934592(0x200000000, double:4.243991582E-314)
            boolean r6 = androidx.compose.ui.unit.TextUnitType.m287equalsimpl0(r6, r0)
            if (r6 == 0) goto L4ce
            float r6 = androidx.compose.ui.unit.TextUnit.m285getValueimpl(r10)
            float r10 = r6 * r2
            goto L4cf
        L4ce:
            r10 = 0
        L4cf:
            long r6 = androidx.compose.ui.unit.TextUnit.m284getTypeUIouoOA(r13)
            r0 = 4294967296(0x100000000, double:2.121995791E-314)
            boolean r11 = androidx.compose.ui.unit.TextUnitType.m287equalsimpl0(r6, r0)
            if (r11 == 0) goto L4e4
            float r0 = r9.mo31toPxR2X_6o(r13)
            r2 = r0
            goto L4f6
        L4e4:
            r0 = 8589934592(0x200000000, double:4.243991582E-314)
            boolean r6 = androidx.compose.ui.unit.TextUnitType.m287equalsimpl0(r6, r0)
            if (r6 == 0) goto L4f5
            float r6 = androidx.compose.ui.unit.TextUnit.m285getValueimpl(r13)
            float r2 = r2 * r6
            goto L4f6
        L4f5:
            r2 = 0
        L4f6:
            android.text.style.LeadingMarginSpan$Standard r6 = new android.text.style.LeadingMarginSpan$Standard
            double r10 = (double) r10
            double r10 = java.lang.Math.ceil(r10)
            float r7 = (float) r10
            int r7 = (int) r7
            double r10 = (double) r2
            double r10 = java.lang.Math.ceil(r10)
            float r2 = (float) r10
            int r2 = (int) r2
            r6.<init>(r7, r2)
            int r2 = r5.length()
            r7 = 0
            _COROUTINE._BOUNDARY.setSpan(r5, r6, r7, r2)
            goto L516
        L512:
            r4 = r6
            r8 = r7
            r45 = r11
        L516:
            java.util.ArrayList r2 = new java.util.ArrayList
            int r6 = r3.size()
            r2.<init>(r6)
            int r6 = r3.size()
            r7 = 0
        L524:
            if (r7 >= r6) goto L545
            java.lang.Object r10 = r3.get(r7)
            r11 = r10
            androidx.compose.ui.text.AnnotatedString$Range r11 = (androidx.compose.ui.text.AnnotatedString.Range) r11
            java.lang.Object r13 = r11.item
            androidx.compose.ui.text.SpanStyle r13 = (androidx.compose.ui.text.SpanStyle) r13
            boolean r13 = _COROUTINE._BOUNDARY.hasFontAttributes(r13)
            if (r13 != 0) goto L53f
            java.lang.Object r11 = r11.item
            androidx.compose.ui.text.SpanStyle r11 = (androidx.compose.ui.text.SpanStyle) r11
            androidx.compose.ui.text.font.FontSynthesis r11 = r11.fontSynthesis
            if (r11 == 0) goto L542
        L53f:
            r2.add(r10)
        L542:
            int r7 = r7 + 1
            goto L524
        L545:
            boolean r6 = _COROUTINE._BOUNDARY.hasFontAttributes(r8)
            if (r6 != 0) goto L552
            androidx.compose.ui.text.font.FontSynthesis r6 = r8.fontSynthesis
            if (r6 == 0) goto L550
            goto L552
        L550:
            r6 = r15
            goto L580
        L552:
            androidx.compose.ui.text.SpanStyle r6 = new androidx.compose.ui.text.SpanStyle
            r17 = r6
            androidx.compose.ui.text.font.FontWeight r7 = r8.fontWeight
            r22 = r7
            androidx.compose.ui.text.font.FontStyle r7 = r8.fontStyle
            r23 = r7
            androidx.compose.ui.text.font.FontSynthesis r7 = r8.fontSynthesis
            r24 = r7
            androidx.compose.ui.text.font.FontFamily r7 = r8.fontFamily
            r25 = r7
            r35 = 0
            r36 = 65475(0xffc3, float:9.175E-41)
            r18 = 0
            r20 = 0
            r26 = 0
            r27 = 0
            r29 = 0
            r30 = 0
            r31 = 0
            r32 = 0
            r34 = 0
            r17.<init>(r18, r20, r22, r23, r24, r25, r26, r27, r29, r30, r31, r32, r34, r35, r36)
        L580:
            androidx.compose.foundation.IndicationKt$indication$2 r7 = new androidx.compose.foundation.IndicationKt$indication$2
            r10 = r48
            r8 = 1
            r7.<init>(r5, r8, r10)
            int r10 = r2.size()
            if (r10 > r8) goto L5c4
            boolean r10 = r2.isEmpty()
            r10 = r10 ^ r8
            if (r10 == 0) goto L67a
            r8 = 0
            java.lang.Object r10 = r2.get(r8)
            androidx.compose.ui.text.AnnotatedString$Range r10 = (androidx.compose.ui.text.AnnotatedString.Range) r10
            java.lang.Object r10 = r10.item
            androidx.compose.ui.text.SpanStyle r10 = (androidx.compose.ui.text.SpanStyle) r10
            if (r6 != 0) goto L5a3
            goto L5a7
        L5a3:
            androidx.compose.ui.text.SpanStyle r10 = r6.merge(r10)
        L5a7:
            java.lang.Object r6 = r2.get(r8)
            androidx.compose.ui.text.AnnotatedString$Range r6 = (androidx.compose.ui.text.AnnotatedString.Range) r6
            int r6 = r6.start
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r2 = r2.get(r8)
            androidx.compose.ui.text.AnnotatedString$Range r2 = (androidx.compose.ui.text.AnnotatedString.Range) r2
            int r2 = r2.end
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r7.invoke(r10, r6, r2)
            goto L67a
        L5c4:
            int r8 = r2.size()
            int r10 = r8 * 2
            java.lang.Integer[] r11 = new java.lang.Integer[r10]
            r13 = 0
        L5cd:
            if (r13 >= r10) goto L5d9
            r14 = 0
            java.lang.Integer r16 = java.lang.Integer.valueOf(r14)
            r11[r13] = r16
            int r13 = r13 + 1
            goto L5cd
        L5d9:
            int r13 = r2.size()
            r14 = 0
        L5de:
            if (r14 >= r13) goto L5fd
            java.lang.Object r16 = r2.get(r14)
            r0 = r16
            androidx.compose.ui.text.AnnotatedString$Range r0 = (androidx.compose.ui.text.AnnotatedString.Range) r0
            int r1 = r0.start
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r11[r14] = r1
            int r1 = r14 + r8
            int r0 = r0.end
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r11[r1] = r0
            int r14 = r14 + 1
            goto L5de
        L5fd:
            r0 = r11
            java.lang.Comparable[] r0 = (java.lang.Comparable[]) r0
            int r1 = r0.length
            r8 = 1
            if (r1 <= r8) goto L607
            java.util.Arrays.sort(r0)
        L607:
            if (r10 == 0) goto L85f
            r0 = 0
            r1 = r11[r0]
            int r0 = r1.intValue()
            r1 = r0
            r0 = 0
        L612:
            if (r0 >= r10) goto L67a
            r8 = r11[r0]
            int r13 = r8.intValue()
            if (r13 != r1) goto L625
            r17 = r2
            r47 = r6
            r16 = r10
            r20 = r11
            goto L66e
        L625:
            int r14 = r2.size()
            r47 = r6
            r15 = 0
        L62c:
            if (r15 >= r14) goto L65e
            java.lang.Object r16 = r2.get(r15)
            r17 = r2
            r2 = r16
            androidx.compose.ui.text.AnnotatedString$Range r2 = (androidx.compose.ui.text.AnnotatedString.Range) r2
            r16 = r10
            int r10 = r2.start
            r20 = r11
            int r11 = r2.end
            if (r10 == r11) goto L655
            boolean r10 = androidx.compose.ui.text.AnnotatedStringKt.intersect(r1, r13, r10, r11)
            if (r10 == 0) goto L655
            java.lang.Object r2 = r2.item
            androidx.compose.ui.text.SpanStyle r2 = (androidx.compose.ui.text.SpanStyle) r2
            if (r6 != 0) goto L650
        L64e:
            r6 = r2
            goto L655
        L650:
            androidx.compose.ui.text.SpanStyle r2 = r6.merge(r2)
            goto L64e
        L655:
            int r15 = r15 + 1
            r10 = r16
            r2 = r17
            r11 = r20
            goto L62c
        L65e:
            r17 = r2
            r16 = r10
            r20 = r11
            if (r6 == 0) goto L66d
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r7.invoke(r6, r1, r8)
        L66d:
            r1 = r13
        L66e:
            int r0 = r0 + 1
            r6 = r47
            r10 = r16
            r2 = r17
            r11 = r20
            r15 = 0
            goto L612
        L67a:
            int r0 = r3.size()
            r1 = 0
            r2 = 0
        L680:
            if (r1 >= r0) goto L7b1
            java.lang.Object r6 = r3.get(r1)
            androidx.compose.ui.text.AnnotatedString$Range r6 = (androidx.compose.ui.text.AnnotatedString.Range) r6
            int r7 = r6.start
            if (r7 < 0) goto L69c
            int r8 = r5.length()
            if (r7 >= r8) goto L69c
            int r8 = r6.end
            if (r8 <= r7) goto L69c
            int r7 = r5.length()
            if (r8 <= r7) goto L6a1
        L69c:
            r47 = r0
            r15 = 0
            goto L7ab
        L6a1:
            java.lang.Object r7 = r6.item
            androidx.compose.ui.text.SpanStyle r7 = (androidx.compose.ui.text.SpanStyle) r7
            androidx.compose.ui.text.style.BaselineShift r8 = r7.baselineShift
            int r10 = r6.start
            int r6 = r6.end
            if (r8 == 0) goto L6b9
            androidx.compose.ui.text.android.style.SkewXSpan r11 = new androidx.compose.ui.text.android.style.SkewXSpan
            float r8 = r8.multiplier
            r13 = 1
            r11.<init>(r8, r13)
            _COROUTINE._BOUNDARY.setSpan(r5, r11, r10, r6)
            goto L6ba
        L6b9:
            r13 = 1
        L6ba:
            androidx.compose.ui.text.style.TextForegroundStyle r8 = r7.textForegroundStyle
            long r14 = r8.mo264getColor0d7_KjU()
            long r26 = androidx.compose.ui.graphics.Color.Unspecified
            int r11 = (r14 > r26 ? 1 : (r14 == r26 ? 0 : -1))
            if (r11 == 0) goto L6d2
            android.text.style.ForegroundColorSpan r11 = new android.text.style.ForegroundColorSpan
            int r14 = androidx.compose.ui.graphics.Brush.m109toArgb8_81llA(r14)
            r11.<init>(r14)
            _COROUTINE._BOUNDARY.setSpan(r5, r11, r10, r6)
        L6d2:
            androidx.compose.ui.graphics.Brush r11 = r8.getBrush()
            float r8 = r8.getAlpha()
            if (r11 == 0) goto L6e6
            androidx.compose.ui.text.platform.style.ShaderBrushSpan r14 = new androidx.compose.ui.text.platform.style.ShaderBrushSpan
            androidx.compose.ui.graphics.BrushKt$ShaderBrush$1 r11 = (androidx.compose.ui.graphics.BrushKt$ShaderBrush$1) r11
            r14.<init>(r11, r8)
            _COROUTINE._BOUNDARY.setSpan(r5, r14, r10, r6)
        L6e6:
            androidx.compose.ui.text.style.TextDecoration r8 = r7.textDecoration
            if (r8 == 0) goto L703
            androidx.compose.ui.text.android.style.TextDecorationSpan r11 = new androidx.compose.ui.text.android.style.TextDecorationSpan
            r14 = r45
            boolean r15 = r8.contains(r14)
            int r8 = r8.mask
            r13 = r8 | 2
            if (r13 != r8) goto L6fa
            r8 = 1
            goto L6fb
        L6fa:
            r8 = 0
        L6fb:
            r11.<init>(r15, r8)
            _COROUTINE._BOUNDARY.setSpan(r5, r11, r10, r6)
            r45 = r14
        L703:
            long r13 = r7.fontSize
            r20 = r5
            r21 = r13
            r23 = r9
            r24 = r10
            r25 = r6
            _COROUTINE._BOUNDARY.m21setFontSizeKmRG4DE(r20, r21, r23, r24, r25)
            java.lang.String r8 = r7.fontFeatureSettings
            if (r8 == 0) goto L71e
            androidx.compose.ui.text.android.style.TypefaceSpan r11 = new androidx.compose.ui.text.android.style.TypefaceSpan
            r11.<init>(r8)
            _COROUTINE._BOUNDARY.setSpan(r5, r11, r10, r6)
        L71e:
            androidx.compose.ui.text.style.TextGeometricTransform r8 = r7.textGeometricTransform
            if (r8 == 0) goto L737
            android.text.style.ScaleXSpan r11 = new android.text.style.ScaleXSpan
            float r13 = r8.scaleX
            r11.<init>(r13)
            _COROUTINE._BOUNDARY.setSpan(r5, r11, r10, r6)
            androidx.compose.ui.text.android.style.SkewXSpan r11 = new androidx.compose.ui.text.android.style.SkewXSpan
            float r8 = r8.skewX
            r13 = 0
            r11.<init>(r8, r13)
            _COROUTINE._BOUNDARY.setSpan(r5, r11, r10, r6)
        L737:
            androidx.compose.ui.text.intl.LocaleList r8 = r7.localeList
            if (r8 == 0) goto L742
            java.lang.Object r8 = r12.localeSpan(r8)
            _COROUTINE._BOUNDARY.setSpan(r5, r8, r10, r6)
        L742:
            long r13 = r7.background
            int r8 = (r13 > r26 ? 1 : (r13 == r26 ? 0 : -1))
            if (r8 == 0) goto L754
            android.text.style.BackgroundColorSpan r8 = new android.text.style.BackgroundColorSpan
            int r11 = androidx.compose.ui.graphics.Brush.m109toArgb8_81llA(r13)
            r8.<init>(r11)
            _COROUTINE._BOUNDARY.setSpan(r5, r8, r10, r6)
        L754:
            androidx.compose.ui.graphics.Shadow r8 = r7.shadow
            if (r8 == 0) goto L77b
            androidx.compose.ui.text.android.style.ShadowSpan r11 = new androidx.compose.ui.text.android.style.ShadowSpan
            long r13 = r8.color
            int r13 = androidx.compose.ui.graphics.Brush.m109toArgb8_81llA(r13)
            long r14 = r8.offset
            r47 = r0
            float r0 = androidx.compose.ui.geometry.Offset.m76getXimpl(r14)
            float r14 = androidx.compose.ui.geometry.Offset.m77getYimpl(r14)
            float r8 = r8.blurRadius
            r15 = 0
            int r16 = (r8 > r15 ? 1 : (r8 == r15 ? 0 : -1))
            if (r16 != 0) goto L774
            r8 = 1
        L774:
            r11.<init>(r0, r14, r8, r13)
            _COROUTINE._BOUNDARY.setSpan(r5, r11, r10, r6)
            goto L77e
        L77b:
            r47 = r0
            r15 = 0
        L77e:
            androidx.compose.ui.graphics.drawscope.DrawStyle r0 = r7.drawStyle
            if (r0 == 0) goto L78a
            androidx.compose.ui.text.platform.style.DrawStyleSpan r8 = new androidx.compose.ui.text.platform.style.DrawStyleSpan
            r8.<init>(r0)
            _COROUTINE._BOUNDARY.setSpan(r5, r8, r10, r6)
        L78a:
            long r6 = r7.letterSpacing
            long r10 = androidx.compose.ui.unit.TextUnit.m284getTypeUIouoOA(r6)
            r13 = 4294967296(0x100000000, double:2.121995791E-314)
            boolean r0 = androidx.compose.ui.unit.TextUnitType.m287equalsimpl0(r10, r13)
            if (r0 != 0) goto L7aa
            long r6 = androidx.compose.ui.unit.TextUnit.m284getTypeUIouoOA(r6)
            r10 = 8589934592(0x200000000, double:4.243991582E-314)
            boolean r0 = androidx.compose.ui.unit.TextUnitType.m287equalsimpl0(r6, r10)
            if (r0 == 0) goto L7ab
        L7aa:
            r2 = 1
        L7ab:
            int r1 = r1 + 1
            r0 = r47
            goto L680
        L7b1:
            if (r2 == 0) goto L81c
            int r0 = r3.size()
            r12 = 0
        L7b8:
            if (r12 >= r0) goto L81c
            java.lang.Object r1 = r3.get(r12)
            androidx.compose.ui.text.AnnotatedString$Range r1 = (androidx.compose.ui.text.AnnotatedString.Range) r1
            int r2 = r1.start
            java.lang.Object r8 = r1.item
            androidx.compose.ui.text.SpanStyle r8 = (androidx.compose.ui.text.SpanStyle) r8
            if (r2 < 0) goto L7d8
            int r10 = r5.length()
            if (r2 >= r10) goto L7d8
            int r1 = r1.end
            if (r1 <= r2) goto L7d8
            int r10 = r5.length()
            if (r1 <= r10) goto L7de
        L7d8:
            r6 = 8589934592(0x200000000, double:4.243991582E-314)
            goto L819
        L7de:
            long r10 = r8.letterSpacing
            long r13 = androidx.compose.ui.unit.TextUnit.m284getTypeUIouoOA(r10)
            r6 = 4294967296(0x100000000, double:2.121995791E-314)
            boolean r8 = androidx.compose.ui.unit.TextUnitType.m287equalsimpl0(r13, r6)
            if (r8 == 0) goto L7fe
            androidx.compose.ui.text.android.style.LetterSpacingSpanPx r8 = new androidx.compose.ui.text.android.style.LetterSpacingSpanPx
            float r10 = r9.mo31toPxR2X_6o(r10)
            r8.<init>(r10)
            r6 = 8589934592(0x200000000, double:4.243991582E-314)
            goto L814
        L7fe:
            r6 = 8589934592(0x200000000, double:4.243991582E-314)
            boolean r8 = androidx.compose.ui.unit.TextUnitType.m287equalsimpl0(r13, r6)
            if (r8 == 0) goto L813
            androidx.compose.ui.text.android.style.LetterSpacingSpanEm r8 = new androidx.compose.ui.text.android.style.LetterSpacingSpanEm
            float r10 = androidx.compose.ui.unit.TextUnit.m285getValueimpl(r10)
            r8.<init>(r10)
            goto L814
        L813:
            r8 = 0
        L814:
            if (r8 == 0) goto L819
            _COROUTINE._BOUNDARY.setSpan(r5, r8, r2, r1)
        L819:
            int r12 = r12 + 1
            goto L7b8
        L81c:
            int r0 = r4.size()
            if (r0 <= 0) goto L84f
            r0 = 0
            java.lang.Object r1 = r4.get(r0)
            androidx.compose.ui.text.AnnotatedString$Range r1 = (androidx.compose.ui.text.AnnotatedString.Range) r1
            java.lang.Object r2 = r1.item
            kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0.m(r2)
            int r2 = r1.end
            java.lang.Class<androidx.emoji2.text.TypefaceEmojiSpan> r3 = androidx.emoji2.text.TypefaceEmojiSpan.class
            int r1 = r1.start
            java.lang.Object[] r1 = r5.getSpans(r1, r2, r3)
            java.lang.String r2 = "getSpans(start, end, EmojiSpan::class.java)"
            kotlin.ResultKt.checkNotNullExpressionValue(r1, r2)
            int r2 = r1.length
            r10 = r0
        L83f:
            if (r10 >= r2) goto L84b
            r0 = r1[r10]
            androidx.emoji2.text.TypefaceEmojiSpan r0 = (androidx.emoji2.text.TypefaceEmojiSpan) r0
            r5.removeSpan(r0)
            int r10 = r10 + 1
            goto L83f
        L84b:
            androidx.compose.ui.text.android.style.PlaceholderSpan r0 = new androidx.compose.ui.text.android.style.PlaceholderSpan
            r0 = 0
            throw r0
        L84f:
            r0 = r42
        L851:
            r0.charSequence = r5
            androidx.compose.ui.text.android.LayoutIntrinsics r1 = new androidx.compose.ui.text.android.LayoutIntrinsics
            androidx.compose.ui.text.platform.AndroidTextPaint r2 = r0.textPaint
            int r3 = r0.textDirectionHeuristic
            r1.<init>(r5, r2, r3)
            r0.layoutIntrinsics = r1
            return
        L85f:
            r0 = r42
            java.util.NoSuchElementException r1 = new java.util.NoSuchElementException
            java.lang.String r2 = "Array is empty."
            r1.<init>(r2)
            throw r1
        L869:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Invalid TextDirection."
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.platform.AndroidParagraphIntrinsics.<init>(androidx.compose.ui.text.TextStyle, androidx.compose.ui.text.font.FontFamily$Resolver, androidx.compose.ui.unit.Density, java.lang.String, java.util.List, java.util.List):void");
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public final boolean getHasStaleResolvedFonts() {
        MenuHostHelper menuHostHelper = this.resolvedTypefaces;
        if (menuHostHelper == null || !menuHostHelper.isStaleResolvedFont()) {
            if (!this.emojiCompatProcessed && _BOUNDARY.access$getHasEmojiCompat(this.style)) {
                DefaultImpl defaultImpl = EmojiCompatStatus.delegate;
                DefaultImpl defaultImpl2 = EmojiCompatStatus.delegate;
                State state = defaultImpl2.loadState;
                if (state == null) {
                    if (EmojiCompat.sInstance != null) {
                        state = defaultImpl2.getFontLoadState();
                        defaultImpl2.loadState = state;
                    } else {
                        state = EmojiCompatStatusKt.Falsey;
                    }
                }
                if (((Boolean) state.getValue()).booleanValue()) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public final float getMaxIntrinsicWidth() {
        return this.layoutIntrinsics.getMaxIntrinsicWidth();
    }

    @Override // androidx.compose.ui.text.ParagraphIntrinsics
    public final float getMinIntrinsicWidth() {
        LayoutIntrinsics layoutIntrinsics = this.layoutIntrinsics;
        if (!Float.isNaN(layoutIntrinsics._minIntrinsicWidth)) {
            return layoutIntrinsics._minIntrinsicWidth;
        }
        CharSequence charSequence = layoutIntrinsics.charSequence;
        ResultKt.checkNotNullParameter(charSequence, "text");
        TextPaint textPaint = layoutIntrinsics.textPaint;
        ResultKt.checkNotNullParameter(textPaint, "paint");
        BreakIterator lineInstance = BreakIterator.getLineInstance(textPaint.getTextLocale());
        lineInstance.setText(new CharSequenceCharacterIterator(charSequence, charSequence.length()));
        PriorityQueue priorityQueue = new PriorityQueue(10, new LayoutNode$$ExternalSyntheticLambda0(1));
        int i = 0;
        for (int next = lineInstance.next(); next != -1; next = lineInstance.next()) {
            if (priorityQueue.size() < 10) {
                priorityQueue.add(new Pair(Integer.valueOf(i), Integer.valueOf(next)));
            } else {
                Pair pair = (Pair) priorityQueue.peek();
                if (pair != null && ((Number) pair.second).intValue() - ((Number) pair.first).intValue() < next - i) {
                    priorityQueue.poll();
                    priorityQueue.add(new Pair(Integer.valueOf(i), Integer.valueOf(next)));
                }
            }
            i = next;
        }
        Iterator it = priorityQueue.iterator();
        float f = 0.0f;
        while (it.hasNext()) {
            Pair pair2 = (Pair) it.next();
            f = Math.max(f, Layout.getDesiredWidth(charSequence, ((Number) pair2.first).intValue(), ((Number) pair2.second).intValue(), textPaint));
        }
        layoutIntrinsics._minIntrinsicWidth = f;
        return f;
    }
}
