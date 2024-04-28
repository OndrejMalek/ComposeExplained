package _COROUTINE;

import android.R;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.MetricAffectingSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.TtsSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.SparseArray;
import android.view.View;
import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;
import androidx.compose.foundation.gestures.ForEachGestureKt$awaitEachGesture$2;
import androidx.compose.material3.ColorResourceHelper;
import androidx.compose.material3.TextKt$ProvideTextStyle$1;
import androidx.compose.material3.TonalPalette;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.IntStack;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeImpl$end$1$2;
import androidx.compose.runtime.SlotReader;
import androidx.compose.runtime.Stack;
import androidx.compose.runtime.State;
import androidx.compose.runtime.StructuralEqualityPolicy;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.PersistentCompositionLocalHashMap;
import androidx.compose.ui.ComposedModifier;
import androidx.compose.ui.ComposedModifierKt$materialize$1;
import androidx.compose.ui.ComposedModifierKt$materialize$result$1;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusStateImpl;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.focus.FocusableChildrenComparator;
import androidx.compose.ui.focus.OneDimensionalFocusSearchKt$generateAndSearchChildren$1;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.colorspace.Adaptation;
import androidx.compose.ui.graphics.colorspace.Adaptation$Companion$Bradford$1;
import androidx.compose.ui.graphics.colorspace.ColorModel;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.colorspace.Illuminant;
import androidx.compose.ui.graphics.colorspace.Rgb;
import androidx.compose.ui.graphics.colorspace.WhitePoint;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutKt$materializerOf$1;
import androidx.compose.ui.modifier.ModifierLocal;
import androidx.compose.ui.modifier.ProvidableModifierLocal;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.InnerNodeCoordinator;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNode$_foldedChildren$1;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.Snake;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsNode$parent$1;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.text.AndroidParagraph;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.AnnotatedStringKt;
import androidx.compose.ui.text.EmojiSupportMatch;
import androidx.compose.ui.text.ParagraphInfo;
import androidx.compose.ui.text.ParagraphIntrinsics;
import androidx.compose.ui.text.ParagraphStyle;
import androidx.compose.ui.text.ParagraphStyleKt;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.SpanStyleKt;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TtsAnnotation;
import androidx.compose.ui.text.UrlAnnotation;
import androidx.compose.ui.text.VerbatimTtsAnnotation;
import androidx.compose.ui.text.android.Paint29;
import androidx.compose.ui.text.android.TextAndroidCanvas;
import androidx.compose.ui.text.android.TextLayoutKt;
import androidx.compose.ui.text.android.style.IndentationFixSpanKt$WhenMappings;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.intl.PlatformLocaleKt;
import androidx.compose.ui.text.platform.AndroidParagraphIntrinsics;
import androidx.compose.ui.text.platform.URLSpanCache;
import androidx.compose.ui.text.platform.extensions.LocaleListHelperMethods;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.ColorStyle;
import androidx.compose.ui.text.style.Hyphens;
import androidx.compose.ui.text.style.LineBreak;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextDirection;
import androidx.compose.ui.text.style.TextForegroundStyle;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;
import androidx.concurrent.futures.AbstractResolvableFuture;
import androidx.customview.poolingcontainer.PoolingContainerListenerHolder;
import androidx.emoji2.text.flatbuffer.MetadataList;
import androidx.emoji2.text.flatbuffer.Table;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.IntProgression;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;
import kotlinx.coroutines.internal.DispatchedContinuation;

/* loaded from: classes.dex */
public abstract class _BOUNDARY {
    /* JADX WARN: Removed duplicated region for block: B:10:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0134  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0050  */
    /* renamed from: BasicText-4YKlhWE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m0BasicText4YKlhWE(final java.lang.String r25, androidx.compose.ui.Modifier r26, androidx.compose.ui.text.TextStyle r27, kotlin.jvm.functions.Function1 r28, int r29, boolean r30, int r31, int r32, androidx.compose.runtime.Composer r33, final int r34, final int r35) {
        /*
            r11 = r25
            r12 = r34
            r13 = r35
            java.lang.String r0 = "text"
            kotlin.ResultKt.checkNotNullParameter(r11, r0)
            r14 = r33
            androidx.compose.runtime.ComposerImpl r14 = (androidx.compose.runtime.ComposerImpl) r14
            r0 = 1542716361(0x5bf3fbc9, float:1.3735052E17)
            r14.startRestartGroup(r0)
            r0 = r13 & 1
            if (r0 == 0) goto L1c
            r0 = r12 | 6
            goto L2c
        L1c:
            r0 = r12 & 14
            if (r0 != 0) goto L2b
            boolean r0 = r14.changed(r11)
            if (r0 == 0) goto L28
            r0 = 4
            goto L29
        L28:
            r0 = 2
        L29:
            r0 = r0 | r12
            goto L2c
        L2b:
            r0 = r12
        L2c:
            r1 = r13 & 2
            if (r1 == 0) goto L35
            r0 = r0 | 48
        L32:
            r2 = r26
            goto L47
        L35:
            r2 = r12 & 112(0x70, float:1.57E-43)
            if (r2 != 0) goto L32
            r2 = r26
            boolean r3 = r14.changed(r2)
            if (r3 == 0) goto L44
            r3 = 32
            goto L46
        L44:
            r3 = 16
        L46:
            r0 = r0 | r3
        L47:
            r3 = r13 & 4
            if (r3 == 0) goto L50
            r0 = r0 | 384(0x180, float:5.38E-43)
        L4d:
            r4 = r27
            goto L62
        L50:
            r4 = r12 & 896(0x380, float:1.256E-42)
            if (r4 != 0) goto L4d
            r4 = r27
            boolean r5 = r14.changed(r4)
            if (r5 == 0) goto L5f
            r5 = 256(0x100, float:3.59E-43)
            goto L61
        L5f:
            r5 = 128(0x80, float:1.794E-43)
        L61:
            r0 = r0 | r5
        L62:
            r5 = r13 & 8
            if (r5 == 0) goto L6b
            r0 = r0 | 3072(0xc00, float:4.305E-42)
        L68:
            r6 = r28
            goto L7d
        L6b:
            r6 = r12 & 7168(0x1c00, float:1.0045E-41)
            if (r6 != 0) goto L68
            r6 = r28
            boolean r7 = r14.changedInstance(r6)
            if (r7 == 0) goto L7a
            r7 = 2048(0x800, float:2.87E-42)
            goto L7c
        L7a:
            r7 = 1024(0x400, float:1.435E-42)
        L7c:
            r0 = r0 | r7
        L7d:
            r7 = r13 & 16
            r8 = 57344(0xe000, float:8.0356E-41)
            if (r7 == 0) goto L89
            r0 = r0 | 24576(0x6000, float:3.4438E-41)
        L86:
            r9 = r29
            goto L9b
        L89:
            r9 = r12 & r8
            if (r9 != 0) goto L86
            r9 = r29
            boolean r10 = r14.changed(r9)
            if (r10 == 0) goto L98
            r10 = 16384(0x4000, float:2.2959E-41)
            goto L9a
        L98:
            r10 = 8192(0x2000, float:1.14794E-41)
        L9a:
            r0 = r0 | r10
        L9b:
            r10 = r13 & 32
            r15 = 458752(0x70000, float:6.42848E-40)
            if (r10 == 0) goto La8
            r16 = 196608(0x30000, float:2.75506E-40)
            r0 = r0 | r16
            r15 = r30
            goto Lbb
        La8:
            r16 = r12 & r15
            r15 = r30
            if (r16 != 0) goto Lbb
            boolean r16 = r14.changed(r15)
            if (r16 == 0) goto Lb7
            r16 = 131072(0x20000, float:1.83671E-40)
            goto Lb9
        Lb7:
            r16 = 65536(0x10000, float:9.18355E-41)
        Lb9:
            r0 = r0 | r16
        Lbb:
            r16 = r13 & 64
            r17 = 3670016(0x380000, float:5.142788E-39)
            if (r16 == 0) goto Lc8
            r18 = 1572864(0x180000, float:2.204052E-39)
            r0 = r0 | r18
            r8 = r31
            goto Ldb
        Lc8:
            r18 = r12 & r17
            r8 = r31
            if (r18 != 0) goto Ldb
            boolean r19 = r14.changed(r8)
            if (r19 == 0) goto Ld7
            r19 = 1048576(0x100000, float:1.469368E-39)
            goto Ld9
        Ld7:
            r19 = 524288(0x80000, float:7.34684E-40)
        Ld9:
            r0 = r0 | r19
        Ldb:
            r2 = r13 & 128(0x80, float:1.794E-43)
            r19 = 29360128(0x1c00000, float:7.052966E-38)
            if (r2 == 0) goto Le8
            r20 = 12582912(0xc00000, float:1.7632415E-38)
            r0 = r0 | r20
            r4 = r32
            goto Lfb
        Le8:
            r20 = r12 & r19
            r4 = r32
            if (r20 != 0) goto Lfb
            boolean r20 = r14.changed(r4)
            if (r20 == 0) goto Lf7
            r20 = 8388608(0x800000, float:1.17549435E-38)
            goto Lf9
        Lf7:
            r20 = 4194304(0x400000, float:5.877472E-39)
        Lf9:
            r0 = r0 | r20
        Lfb:
            r20 = 23967451(0x16db6db, float:4.3661218E-38)
            r4 = r0 & r20
            r6 = 4793490(0x492492, float:6.71711E-39)
            if (r4 != r6) goto L11c
            boolean r4 = r14.getSkipping()
            if (r4 != 0) goto L10c
            goto L11c
        L10c:
            r14.skipToGroupEnd()
            r2 = r26
            r3 = r27
            r4 = r28
            r7 = r8
            r5 = r9
            r6 = r15
            r8 = r32
            goto L18f
        L11c:
            if (r1 == 0) goto L123
            androidx.compose.ui.Modifier$Companion r1 = androidx.compose.ui.Modifier.Companion.$$INSTANCE
            r20 = r1
            goto L125
        L123:
            r20 = r26
        L125:
            if (r3 == 0) goto L12c
            androidx.compose.ui.text.TextStyle r1 = androidx.compose.ui.text.TextStyle.Default
            r21 = r1
            goto L12e
        L12c:
            r21 = r27
        L12e:
            if (r5 == 0) goto L134
            r1 = 0
            r22 = r1
            goto L136
        L134:
            r22 = r28
        L136:
            r1 = 1
            if (r7 == 0) goto L13c
            r23 = r1
            goto L13e
        L13c:
            r23 = r9
        L13e:
            if (r10 == 0) goto L141
            r15 = r1
        L141:
            if (r16 == 0) goto L149
            r3 = 2147483647(0x7fffffff, float:NaN)
            r16 = r3
            goto L14b
        L149:
            r16 = r8
        L14b:
            if (r2 == 0) goto L150
            r24 = r1
            goto L152
        L150:
            r24 = r32
        L152:
            r1 = r0 & 14
            r2 = r0 & 112(0x70, float:1.57E-43)
            r1 = r1 | r2
            r2 = r0 & 896(0x380, float:1.256E-42)
            r1 = r1 | r2
            r2 = r0 & 7168(0x1c00, float:1.0045E-41)
            r1 = r1 | r2
            r2 = 57344(0xe000, float:8.0356E-41)
            r2 = r2 & r0
            r1 = r1 | r2
            r2 = 458752(0x70000, float:6.42848E-40)
            r2 = r2 & r0
            r1 = r1 | r2
            r2 = r0 & r17
            r1 = r1 | r2
            r0 = r0 & r19
            r9 = r1 | r0
            r10 = 256(0x100, float:3.59E-43)
            r0 = r25
            r1 = r20
            r2 = r21
            r3 = r22
            r4 = r23
            r5 = r15
            r6 = r16
            r7 = r24
            r8 = r14
            m1BasicTextVhcvRP8(r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)
            r6 = r15
            r7 = r16
            r2 = r20
            r3 = r21
            r4 = r22
            r5 = r23
            r8 = r24
        L18f:
            androidx.compose.runtime.RecomposeScopeImpl r14 = r14.endRestartGroup()
            if (r14 != 0) goto L196
            goto L1a4
        L196:
            androidx.compose.foundation.text.BasicTextKt$BasicText$8 r15 = new androidx.compose.foundation.text.BasicTextKt$BasicText$8
            r0 = r15
            r1 = r25
            r9 = r34
            r10 = r35
            r0.<init>()
            r14.block = r15
        L1a4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: _COROUTINE._BOUNDARY.m0BasicText4YKlhWE(java.lang.String, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function1, int, boolean, int, int, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0104  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x013f  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x014d A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x00c3  */
    /* renamed from: BasicText-VhcvRP8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1BasicTextVhcvRP8(final java.lang.String r26, androidx.compose.ui.Modifier r27, androidx.compose.ui.text.TextStyle r28, kotlin.jvm.functions.Function1 r29, int r30, boolean r31, int r32, int r33, androidx.compose.runtime.Composer r34, final int r35, final int r36) {
        /*
            r1 = r26
            r9 = r35
            r10 = r36
            java.lang.String r0 = "text"
            kotlin.ResultKt.checkNotNullParameter(r1, r0)
            r0 = r34
            androidx.compose.runtime.ComposerImpl r0 = (androidx.compose.runtime.ComposerImpl) r0
            r2 = -1186827822(0xffffffffb94271d2, float:-1.8543683E-4)
            r0.startRestartGroup(r2)
            r2 = r10 & 1
            if (r2 == 0) goto L1c
            r2 = r9 | 6
            goto L2c
        L1c:
            r2 = r9 & 14
            if (r2 != 0) goto L2b
            boolean r2 = r0.changed(r1)
            if (r2 == 0) goto L28
            r2 = 4
            goto L29
        L28:
            r2 = 2
        L29:
            r2 = r2 | r9
            goto L2c
        L2b:
            r2 = r9
        L2c:
            r3 = r10 & 2
            if (r3 == 0) goto L35
            r2 = r2 | 48
        L32:
            r4 = r27
            goto L47
        L35:
            r4 = r9 & 112(0x70, float:1.57E-43)
            if (r4 != 0) goto L32
            r4 = r27
            boolean r5 = r0.changed(r4)
            if (r5 == 0) goto L44
            r5 = 32
            goto L46
        L44:
            r5 = 16
        L46:
            r2 = r2 | r5
        L47:
            r5 = r10 & 4
            if (r5 == 0) goto L50
            r2 = r2 | 384(0x180, float:5.38E-43)
        L4d:
            r7 = r28
            goto L62
        L50:
            r7 = r9 & 896(0x380, float:1.256E-42)
            if (r7 != 0) goto L4d
            r7 = r28
            boolean r8 = r0.changed(r7)
            if (r8 == 0) goto L5f
            r8 = 256(0x100, float:3.59E-43)
            goto L61
        L5f:
            r8 = 128(0x80, float:1.794E-43)
        L61:
            r2 = r2 | r8
        L62:
            r8 = r10 & 8
            if (r8 == 0) goto L6b
            r2 = r2 | 3072(0xc00, float:4.305E-42)
        L68:
            r11 = r29
            goto L7d
        L6b:
            r11 = r9 & 7168(0x1c00, float:1.0045E-41)
            if (r11 != 0) goto L68
            r11 = r29
            boolean r12 = r0.changedInstance(r11)
            if (r12 == 0) goto L7a
            r12 = 2048(0x800, float:2.87E-42)
            goto L7c
        L7a:
            r12 = 1024(0x400, float:1.435E-42)
        L7c:
            r2 = r2 | r12
        L7d:
            r12 = r10 & 16
            if (r12 == 0) goto L86
            r2 = r2 | 24576(0x6000, float:3.4438E-41)
        L83:
            r13 = r30
            goto L9a
        L86:
            r13 = 57344(0xe000, float:8.0356E-41)
            r13 = r13 & r9
            if (r13 != 0) goto L83
            r13 = r30
            boolean r14 = r0.changed(r13)
            if (r14 == 0) goto L97
            r14 = 16384(0x4000, float:2.2959E-41)
            goto L99
        L97:
            r14 = 8192(0x2000, float:1.14794E-41)
        L99:
            r2 = r2 | r14
        L9a:
            r14 = r10 & 32
            if (r14 == 0) goto La4
            r15 = 196608(0x30000, float:2.75506E-40)
            r2 = r2 | r15
        La1:
            r15 = r31
            goto Lb8
        La4:
            r15 = 458752(0x70000, float:6.42848E-40)
            r15 = r15 & r9
            if (r15 != 0) goto La1
            r15 = r31
            boolean r16 = r0.changed(r15)
            if (r16 == 0) goto Lb4
            r16 = 131072(0x20000, float:1.83671E-40)
            goto Lb6
        Lb4:
            r16 = 65536(0x10000, float:9.18355E-41)
        Lb6:
            r2 = r2 | r16
        Lb8:
            r16 = r10 & 64
            if (r16 == 0) goto Lc3
            r17 = 1572864(0x180000, float:2.204052E-39)
            r2 = r2 | r17
            r6 = r32
            goto Ld8
        Lc3:
            r17 = 3670016(0x380000, float:5.142788E-39)
            r17 = r9 & r17
            r6 = r32
            if (r17 != 0) goto Ld8
            boolean r17 = r0.changed(r6)
            if (r17 == 0) goto Ld4
            r17 = 1048576(0x100000, float:1.469368E-39)
            goto Ld6
        Ld4:
            r17 = 524288(0x80000, float:7.34684E-40)
        Ld6:
            r2 = r2 | r17
        Ld8:
            r4 = r10 & 128(0x80, float:1.794E-43)
            if (r4 == 0) goto Le3
            r17 = 12582912(0xc00000, float:1.7632415E-38)
            r2 = r2 | r17
            r6 = r33
            goto Lf8
        Le3:
            r17 = 29360128(0x1c00000, float:7.052966E-38)
            r17 = r9 & r17
            r6 = r33
            if (r17 != 0) goto Lf8
            boolean r17 = r0.changed(r6)
            if (r17 == 0) goto Lf4
            r17 = 8388608(0x800000, float:1.17549435E-38)
            goto Lf6
        Lf4:
            r17 = 4194304(0x400000, float:5.877472E-39)
        Lf6:
            r2 = r2 | r17
        Lf8:
            r6 = r10 & 256(0x100, float:3.59E-43)
            if (r6 == 0) goto L100
            r17 = 33554432(0x2000000, float:9.403955E-38)
            r2 = r2 | r17
        L100:
            r7 = 256(0x100, float:3.59E-43)
            if (r6 != r7) goto L124
            r6 = 191739611(0xb6db6db, float:4.5782105E-32)
            r2 = r2 & r6
            r6 = 38347922(0x2492492, float:1.4777643E-37)
            if (r2 != r6) goto L124
            boolean r2 = r0.getSkipping()
            if (r2 != 0) goto L114
            goto L124
        L114:
            r0.skipToGroupEnd()
            r2 = r27
            r3 = r28
            r7 = r32
            r8 = r33
            r4 = r11
            r5 = r13
            r6 = r15
            goto L256
        L124:
            androidx.compose.ui.Modifier$Companion r2 = androidx.compose.ui.Modifier.Companion.$$INSTANCE
            if (r3 == 0) goto L12a
            r3 = r2
            goto L12c
        L12a:
            r3 = r27
        L12c:
            if (r5 == 0) goto L131
            androidx.compose.ui.text.TextStyle r5 = androidx.compose.ui.text.TextStyle.Default
            goto L133
        L131:
            r5 = r28
        L133:
            r6 = 0
            if (r8 == 0) goto L137
            r11 = r6
        L137:
            if (r12 == 0) goto L13a
            r13 = 1
        L13a:
            if (r14 == 0) goto L13d
            r15 = 1
        L13d:
            if (r16 == 0) goto L143
            r8 = 2147483647(0x7fffffff, float:NaN)
            goto L145
        L143:
            r8 = r32
        L145:
            if (r4 == 0) goto L149
            r4 = 1
            goto L14b
        L149:
            r4 = r33
        L14b:
            if (r4 <= 0) goto L291
            if (r8 <= 0) goto L291
            if (r4 > r8) goto L271
            androidx.compose.runtime.DynamicProvidableCompositionLocal r12 = androidx.compose.foundation.text.selection.SelectionRegistrarKt.LocalSelectionRegistrar
            java.lang.Object r12 = r0.consume(r12)
            kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0.m(r12)
            r12 = 959238473(0x392cd149, float:1.6481163E-4)
            r0.startReplaceableGroup(r12)
            r12 = 0
            r0.end(r12)
            r14 = 131071(0x1ffff, float:1.8367E-40)
            if (r11 == 0) goto L1a3
            r7 = 959238907(0x392cd2fb, float:1.6481795E-4)
            r0.startReplaceableGroup(r7)
            androidx.compose.ui.Modifier r7 = androidx.compose.ui.graphics.Brush.m102graphicsLayerAp8cVGQ$default(r3, r6, r12, r14)
            androidx.compose.ui.text.AnnotatedString r14 = new androidx.compose.ui.text.AnnotatedString
            r14.<init>(r1)
            androidx.compose.runtime.StaticProvidableCompositionLocal r6 = androidx.compose.ui.platform.CompositionLocalsKt.LocalFontFamilyResolver
            java.lang.Object r6 = r0.consume(r6)
            r20 = r6
            androidx.compose.ui.text.font.FontFamily$Resolver r20 = (androidx.compose.ui.text.font.FontFamily.Resolver) r20
            androidx.compose.foundation.text.modifiers.TextAnnotatedStringElement r6 = new androidx.compose.foundation.text.modifiers.TextAnnotatedStringElement
            r17 = r6
            r18 = r14
            r19 = r5
            r21 = r11
            r22 = r13
            r23 = r15
            r24 = r8
            r25 = r4
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25)
            androidx.compose.ui.Modifier r2 = r7.then(r2)
            androidx.compose.ui.Modifier r2 = r2.then(r6)
            r0.end(r12)
            goto L1d2
        L1a3:
            r2 = 959239577(0x392cd599, float:1.648277E-4)
            r0.startReplaceableGroup(r2)
            r2 = 0
            androidx.compose.ui.Modifier r6 = androidx.compose.ui.graphics.Brush.m102graphicsLayerAp8cVGQ$default(r3, r2, r12, r14)
            androidx.compose.foundation.text.modifiers.TextStringSimpleElement r2 = new androidx.compose.foundation.text.modifiers.TextStringSimpleElement
            androidx.compose.runtime.StaticProvidableCompositionLocal r7 = androidx.compose.ui.platform.CompositionLocalsKt.LocalFontFamilyResolver
            java.lang.Object r7 = r0.consume(r7)
            androidx.compose.ui.text.font.FontFamily$Resolver r7 = (androidx.compose.ui.text.font.FontFamily.Resolver) r7
            r27 = r2
            r28 = r26
            r29 = r5
            r30 = r7
            r31 = r13
            r32 = r15
            r33 = r8
            r34 = r4
            r27.<init>(r28, r29, r30, r31, r32, r33, r34)
            androidx.compose.ui.Modifier r2 = r6.then(r2)
            r0.end(r12)
        L1d2:
            androidx.compose.foundation.text.EmptyMeasurePolicy r6 = androidx.compose.foundation.text.EmptyMeasurePolicy.INSTANCE
            r7 = 544976794(0x207baf9a, float:2.1318629E-19)
            r0.startReplaceableGroup(r7)
            int r7 = r0.compoundKeyHash
            androidx.compose.ui.Modifier r2 = materializeModifier(r0, r2)
            androidx.compose.runtime.PersistentCompositionLocalMap r14 = r0.currentCompositionLocalScope()
            androidx.compose.ui.node.ComposeUiNode$Companion r17 = androidx.compose.ui.node.ComposeUiNode.Companion
            r17.getClass()
            androidx.compose.ui.node.LayoutNode$Companion$Constructor$1 r12 = androidx.compose.ui.node.ComposeUiNode.Companion.Constructor
            r1 = 1405779621(0x53ca7ea5, float:1.73941627E12)
            r0.startReplaceableGroup(r1)
            androidx.compose.runtime.Applier r1 = r0.applier
            boolean r1 = r1 instanceof androidx.compose.runtime.Applier
            if (r1 == 0) goto L26c
            r0.startReusableNode()
            boolean r1 = r0.inserting
            if (r1 == 0) goto L20a
            androidx.compose.ui.node.LayoutNode$_foldedChildren$1 r1 = new androidx.compose.ui.node.LayoutNode$_foldedChildren$1
            r17 = r3
            r3 = 3
            r1.<init>(r3, r12)
            r0.createNode(r1)
            goto L20f
        L20a:
            r17 = r3
            r0.useNode()
        L20f:
            androidx.compose.ui.node.ComposeUiNode$Companion$SetDensity$1 r1 = androidx.compose.ui.node.ComposeUiNode.Companion.SetMeasurePolicy
            kotlin.ResultKt.m299setimpl(r0, r6, r1)
            androidx.compose.ui.node.ComposeUiNode$Companion$SetDensity$1 r1 = androidx.compose.ui.node.ComposeUiNode.Companion.SetResolvedCompositionLocals
            kotlin.ResultKt.m299setimpl(r0, r14, r1)
            androidx.compose.ui.node.ComposeUiNode$Companion$SetDensity$1 r1 = androidx.compose.ui.node.ComposeUiNode.Companion.SetModifier
            kotlin.ResultKt.m299setimpl(r0, r2, r1)
            androidx.compose.ui.node.ComposeUiNode$Companion$SetDensity$1 r1 = androidx.compose.ui.node.ComposeUiNode.Companion.SetCompositeKeyHash
            boolean r2 = r0.inserting
            if (r2 != 0) goto L235
            java.lang.Object r2 = r0.nextSlot()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r7)
            boolean r2 = kotlin.ResultKt.areEqual(r2, r3)
            if (r2 != 0) goto L233
            goto L235
        L233:
            r1 = 1
            goto L244
        L235:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r7)
            r0.updateValue(r2)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r7)
            r0.apply(r2, r1)
            goto L233
        L244:
            r0.end(r1)
            r1 = 0
            r0.end(r1)
            r0.end(r1)
            r3 = r5
            r7 = r8
            r5 = r13
            r6 = r15
            r2 = r17
            r8 = r4
            r4 = r11
        L256:
            androidx.compose.runtime.RecomposeScopeImpl r11 = r0.endRestartGroup()
            if (r11 != 0) goto L25d
            goto L26b
        L25d:
            androidx.compose.foundation.text.BasicTextKt$BasicText$1 r12 = new androidx.compose.foundation.text.BasicTextKt$BasicText$1
            r0 = r12
            r1 = r26
            r9 = r35
            r10 = r36
            r0.<init>()
            r11.block = r12
        L26b:
            return
        L26c:
            invalidApplier()
            r0 = 0
            throw r0
        L271:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "minLines "
            r0.<init>(r1)
            r0.append(r4)
            java.lang.String r1 = " must be less than or equal to maxLines "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L291:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "both minLines "
            r0.<init>(r1)
            r0.append(r4)
            java.lang.String r1 = " and maxLines "
            r0.append(r1)
            r0.append(r8)
            java.lang.String r1 = " must be greater than zero"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: _COROUTINE._BOUNDARY.m1BasicTextVhcvRP8(java.lang.String, androidx.compose.ui.Modifier, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function1, int, boolean, int, int, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r11v1, resolved type: androidx.compose.runtime.ComposerImpl */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x029f  */
    /* JADX WARN: Removed duplicated region for block: B:101:0x027d  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x019e  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x01c6  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x022d  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x011f  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x00e1  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x00c6  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0138  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x02c5  */
    /* JADX WARN: Type inference failed for: r13v1, types: [androidx.compose.material3.SurfaceKt$Surface$3, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r14v12, types: [androidx.compose.material3.ButtonKt$Button$3, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r14v14 */
    /* JADX WARN: Type inference failed for: r14v4 */
    /* JADX WARN: Type inference failed for: r14v5, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v26, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r5v37 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Button(final kotlin.jvm.functions.Function0 r30, androidx.compose.ui.Modifier r31, boolean r32, androidx.compose.ui.graphics.Shape r33, androidx.compose.material3.ButtonColors r34, androidx.compose.material3.ButtonElevation r35, androidx.compose.foundation.layout.PaddingValuesImpl r36, androidx.compose.foundation.interaction.MutableInteractionSourceImpl r37, final kotlin.jvm.functions.Function3 r38, androidx.compose.runtime.Composer r39, final int r40, final int r41) {
        /*
            r12 = r30
            r13 = r38
            r14 = r40
            r15 = r41
            java.lang.String r0 = "onClick"
            kotlin.ResultKt.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "content"
            kotlin.ResultKt.checkNotNullParameter(r13, r0)
            r11 = r39
            androidx.compose.runtime.ComposerImpl r11 = (androidx.compose.runtime.ComposerImpl) r11
            r0 = 650121315(0x26c01063, float:1.3327118E-15)
            r11.startRestartGroup(r0)
            r0 = r15 & 1
            if (r0 == 0) goto L23
            r0 = r14 | 6
            goto L33
        L23:
            r0 = r14 & 14
            if (r0 != 0) goto L32
            boolean r0 = r11.changedInstance(r12)
            if (r0 == 0) goto L2f
            r0 = 4
            goto L30
        L2f:
            r0 = 2
        L30:
            r0 = r0 | r14
            goto L33
        L32:
            r0 = r14
        L33:
            r1 = r15 & 2
            if (r1 == 0) goto L3c
            r0 = r0 | 48
        L39:
            r2 = r31
            goto L4e
        L3c:
            r2 = r14 & 112(0x70, float:1.57E-43)
            if (r2 != 0) goto L39
            r2 = r31
            boolean r3 = r11.changed(r2)
            if (r3 == 0) goto L4b
            r3 = 32
            goto L4d
        L4b:
            r3 = 16
        L4d:
            r0 = r0 | r3
        L4e:
            r3 = r15 & 4
            if (r3 == 0) goto L57
            r0 = r0 | 384(0x180, float:5.38E-43)
        L54:
            r4 = r32
            goto L69
        L57:
            r4 = r14 & 896(0x380, float:1.256E-42)
            if (r4 != 0) goto L54
            r4 = r32
            boolean r5 = r11.changed(r4)
            if (r5 == 0) goto L66
            r5 = 256(0x100, float:3.59E-43)
            goto L68
        L66:
            r5 = 128(0x80, float:1.794E-43)
        L68:
            r0 = r0 | r5
        L69:
            r5 = r14 & 7168(0x1c00, float:1.0045E-41)
            if (r5 != 0) goto L82
            r5 = r15 & 8
            if (r5 != 0) goto L7c
            r5 = r33
            boolean r6 = r11.changed(r5)
            if (r6 == 0) goto L7e
            r6 = 2048(0x800, float:2.87E-42)
            goto L80
        L7c:
            r5 = r33
        L7e:
            r6 = 1024(0x400, float:1.435E-42)
        L80:
            r0 = r0 | r6
            goto L84
        L82:
            r5 = r33
        L84:
            r6 = 57344(0xe000, float:8.0356E-41)
            r6 = r6 & r14
            if (r6 != 0) goto L9f
            r6 = r15 & 16
            if (r6 != 0) goto L99
            r6 = r34
            boolean r7 = r11.changed(r6)
            if (r7 == 0) goto L9b
            r7 = 16384(0x4000, float:2.2959E-41)
            goto L9d
        L99:
            r6 = r34
        L9b:
            r7 = 8192(0x2000, float:1.14794E-41)
        L9d:
            r0 = r0 | r7
            goto La1
        L9f:
            r6 = r34
        La1:
            r7 = 458752(0x70000, float:6.42848E-40)
            r7 = r7 & r14
            if (r7 != 0) goto Lbb
            r7 = r15 & 32
            if (r7 != 0) goto Lb5
            r7 = r35
            boolean r8 = r11.changed(r7)
            if (r8 == 0) goto Lb7
            r8 = 131072(0x20000, float:1.83671E-40)
            goto Lb9
        Lb5:
            r7 = r35
        Lb7:
            r8 = 65536(0x10000, float:9.18355E-41)
        Lb9:
            r0 = r0 | r8
            goto Lbd
        Lbb:
            r7 = r35
        Lbd:
            r8 = r15 & 64
            r9 = 0
            if (r8 == 0) goto Lc6
            r8 = 1572864(0x180000, float:2.204052E-39)
        Lc4:
            r0 = r0 | r8
            goto Ld7
        Lc6:
            r8 = 3670016(0x380000, float:5.142788E-39)
            r8 = r8 & r14
            if (r8 != 0) goto Ld7
            boolean r8 = r11.changed(r9)
            if (r8 == 0) goto Ld4
            r8 = 1048576(0x100000, float:1.469368E-39)
            goto Lc4
        Ld4:
            r8 = 524288(0x80000, float:7.34684E-40)
            goto Lc4
        Ld7:
            r8 = r15 & 128(0x80, float:1.794E-43)
            if (r8 == 0) goto Le1
            r10 = 12582912(0xc00000, float:1.7632415E-38)
            r0 = r0 | r10
        Lde:
            r10 = r36
            goto Lf5
        Le1:
            r10 = 29360128(0x1c00000, float:7.052966E-38)
            r10 = r10 & r14
            if (r10 != 0) goto Lde
            r10 = r36
            boolean r16 = r11.changed(r10)
            if (r16 == 0) goto Lf1
            r16 = 8388608(0x800000, float:1.17549435E-38)
            goto Lf3
        Lf1:
            r16 = 4194304(0x400000, float:5.877472E-39)
        Lf3:
            r0 = r0 | r16
        Lf5:
            r9 = r15 & 256(0x100, float:3.59E-43)
            r16 = 234881024(0xe000000, float:1.5777218E-30)
            if (r9 == 0) goto L102
            r17 = 100663296(0x6000000, float:2.4074124E-35)
            r0 = r0 | r17
            r2 = r37
            goto L115
        L102:
            r17 = r14 & r16
            r2 = r37
            if (r17 != 0) goto L115
            boolean r17 = r11.changed(r2)
            if (r17 == 0) goto L111
            r17 = 67108864(0x4000000, float:1.5046328E-36)
            goto L113
        L111:
            r17 = 33554432(0x2000000, float:9.403955E-38)
        L113:
            r0 = r0 | r17
        L115:
            r2 = r15 & 512(0x200, float:7.175E-43)
            r17 = 1879048192(0x70000000, float:1.58456325E29)
            if (r2 == 0) goto L11f
            r2 = 805306368(0x30000000, float:4.656613E-10)
        L11d:
            r0 = r0 | r2
            goto L12f
        L11f:
            r2 = r14 & r17
            if (r2 != 0) goto L12f
            boolean r2 = r11.changedInstance(r13)
            if (r2 == 0) goto L12c
            r2 = 536870912(0x20000000, float:1.0842022E-19)
            goto L11d
        L12c:
            r2 = 268435456(0x10000000, float:2.5243549E-29)
            goto L11d
        L12f:
            r2 = 1533916891(0x5b6db6db, float:6.6910621E16)
            r2 = r2 & r0
            r4 = 306783378(0x12492492, float:6.3469493E-28)
            if (r2 != r4) goto L14f
            boolean r2 = r11.getSkipping()
            if (r2 != 0) goto L13f
            goto L14f
        L13f:
            r11.skipToGroupEnd()
            r2 = r31
            r3 = r32
            r8 = r37
            r4 = r5
            r5 = r6
            r6 = r7
            r7 = r10
            r15 = r11
            goto L3dd
        L14f:
            r2 = -127(0xffffffffffffff81, float:NaN)
            r4 = 0
            r5 = 0
            r11.m59startBaiHCIY(r2, r4, r5, r5)
            r2 = r14 & 1
            java.lang.String r5 = "<this>"
            r18 = -458753(0xfffffffffff8ffff, float:NaN)
            r19 = -57345(0xffffffffffff1fff, float:NaN)
            if (r2 == 0) goto L18d
            boolean r2 = r11.getDefaultsInvalid()
            if (r2 == 0) goto L169
            goto L18d
        L169:
            r11.skipToGroupEnd()
            r1 = r15 & 8
            if (r1 == 0) goto L172
            r0 = r0 & (-7169(0xffffffffffffe3ff, float:NaN))
        L172:
            r1 = r15 & 16
            if (r1 == 0) goto L178
            r0 = r0 & r19
        L178:
            r1 = r15 & 32
            if (r1 == 0) goto L17e
            r0 = r0 & r18
        L17e:
            r9 = r32
            r18 = r33
            r29 = r5
            r8 = r6
            r19 = r10
            r10 = r31
        L189:
            r6 = r37
            goto L266
        L18d:
            if (r1 == 0) goto L192
            androidx.compose.ui.Modifier$Companion r1 = androidx.compose.ui.Modifier.Companion.$$INSTANCE
            goto L194
        L192:
            r1 = r31
        L194:
            if (r3 == 0) goto L198
            r2 = 1
            goto L19a
        L198:
            r2 = r32
        L19a:
            r3 = r15 & 8
            if (r3 == 0) goto L1c0
            androidx.compose.foundation.layout.PaddingValuesImpl r3 = androidx.compose.material3.ButtonDefaults.ContentPadding
            r3 = -1234923021(0xffffffffb66491f3, float:-3.4059601E-6)
            r11.startReplaceableGroup(r3)
            int r3 = androidx.compose.material3.tokens.FilledButtonTokens.ContainerShape
            androidx.compose.runtime.StaticProvidableCompositionLocal r20 = androidx.compose.material3.ShapesKt.LocalShapes
            kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0.m(r3, r5)
            androidx.compose.runtime.StaticProvidableCompositionLocal r3 = androidx.compose.material3.ShapesKt.LocalShapes
            java.lang.Object r3 = r11.consume(r3)
            androidx.compose.material3.Shapes r3 = (androidx.compose.material3.Shapes) r3
            kotlin.ResultKt.checkNotNullParameter(r3, r5)
            androidx.compose.foundation.shape.RoundedCornerShape r3 = androidx.compose.foundation.shape.RoundedCornerShapeKt.CircleShape
            r11.end(r4)
            r0 = r0 & (-7169(0xffffffffffffe3ff, float:NaN))
            goto L1c2
        L1c0:
            r3 = r33
        L1c2:
            r20 = r15 & 16
            if (r20 == 0) goto L206
            androidx.compose.foundation.layout.PaddingValuesImpl r6 = androidx.compose.material3.ButtonDefaults.ContentPadding
            r6 = -339300779(0xffffffffebc6ae55, float:-4.8038114E26)
            r11.startReplaceableGroup(r6)
            float r6 = androidx.compose.material3.tokens.FilledButtonTokens.ContainerElevation
            r6 = 20
            long r21 = androidx.compose.material3.ColorSchemeKt.toColor(r6, r11)
            int r6 = androidx.compose.material3.tokens.FilledButtonTokens.LabelTextColor
            long r23 = androidx.compose.material3.ColorSchemeKt.toColor(r6, r11)
            int r6 = androidx.compose.material3.tokens.FilledButtonTokens.DisabledContainerColor
            r29 = r5
            long r4 = androidx.compose.material3.ColorSchemeKt.toColor(r6, r11)
            r6 = 1039516303(0x3df5c28f, float:0.12)
            long r25 = androidx.compose.ui.graphics.Color.m120copywmQWz5c$default(r4, r6)
            int r4 = androidx.compose.material3.tokens.FilledButtonTokens.DisabledLabelTextColor
            long r4 = androidx.compose.material3.ColorSchemeKt.toColor(r4, r11)
            r6 = 1052938076(0x3ec28f5c, float:0.38)
            long r27 = androidx.compose.ui.graphics.Color.m120copywmQWz5c$default(r4, r6)
            androidx.compose.material3.ButtonColors r4 = new androidx.compose.material3.ButtonColors
            r20 = r4
            r20.<init>(r21, r23, r25, r27)
            r5 = 0
            r11.end(r5)
            r0 = r0 & r19
            goto L209
        L206:
            r29 = r5
            r4 = r6
        L209:
            r5 = r15 & 32
            if (r5 == 0) goto L22d
            androidx.compose.foundation.layout.PaddingValuesImpl r5 = androidx.compose.material3.ButtonDefaults.ContentPadding
            r5 = 1827791191(0x6cf1e157, float:2.3393221E27)
            r11.startReplaceableGroup(r5)
            float r20 = androidx.compose.material3.tokens.FilledButtonTokens.ContainerElevation
            float r21 = androidx.compose.material3.tokens.FilledButtonTokens.PressedContainerElevation
            float r22 = androidx.compose.material3.tokens.FilledButtonTokens.FocusContainerElevation
            float r23 = androidx.compose.material3.tokens.FilledButtonTokens.HoverContainerElevation
            float r24 = androidx.compose.material3.tokens.FilledButtonTokens.DisabledContainerElevation
            androidx.compose.material3.ButtonElevation r5 = new androidx.compose.material3.ButtonElevation
            r19 = r5
            r19.<init>(r20, r21, r22, r23, r24)
            r6 = 0
            r11.end(r6)
            r0 = r0 & r18
            goto L22e
        L22d:
            r5 = r7
        L22e:
            if (r8 == 0) goto L233
            androidx.compose.foundation.layout.PaddingValuesImpl r6 = androidx.compose.material3.ButtonDefaults.ContentPadding
            goto L234
        L233:
            r6 = r10
        L234:
            if (r9 == 0) goto L25c
            r7 = -492369756(0xffffffffe2a708a4, float:-1.5406144E21)
            r11.startReplaceableGroup(r7)
            java.lang.Object r7 = r11.nextSlot()
            _COROUTINE.ArtificialStackFrames r8 = androidx.compose.runtime.Composer.Companion.Empty
            if (r7 != r8) goto L24c
            androidx.compose.foundation.interaction.MutableInteractionSourceImpl r7 = new androidx.compose.foundation.interaction.MutableInteractionSourceImpl
            r7.<init>()
            r11.updateValue(r7)
        L24c:
            r8 = 0
            r11.end(r8)
            androidx.compose.foundation.interaction.MutableInteractionSourceImpl r7 = (androidx.compose.foundation.interaction.MutableInteractionSourceImpl) r7
            r10 = r1
            r9 = r2
            r18 = r3
            r8 = r4
            r19 = r6
            r6 = r7
            r7 = r5
            goto L266
        L25c:
            r10 = r1
            r9 = r2
            r18 = r3
            r8 = r4
            r7 = r5
            r19 = r6
            goto L189
        L266:
            r11.endDefaults()
            int r1 = r0 >> 6
            r1 = r1 & 14
            int r2 = r0 >> 9
            r8.getClass()
            r3 = -754887434(0xffffffffd30154f6, float:-5.5547619E11)
            r11.startReplaceableGroup(r3)
            if (r9 == 0) goto L27d
            long r3 = r8.containerColor
            goto L27f
        L27d:
            long r3 = r8.disabledContainerColor
        L27f:
            androidx.compose.ui.graphics.Color r5 = new androidx.compose.ui.graphics.Color
            r5.<init>(r3)
            androidx.compose.runtime.MutableState r3 = kotlin.ResultKt.rememberUpdatedState(r5, r11)
            r4 = 0
            r11.end(r4)
            java.lang.Object r3 = r3.getValue()
            androidx.compose.ui.graphics.Color r3 = (androidx.compose.ui.graphics.Color) r3
            long r3 = r3.value
            r5 = -360303250(0xffffffffea86356e, float:-8.112419E25)
            r11.startReplaceableGroup(r5)
            if (r9 == 0) goto L29f
            long r12 = r8.contentColor
            goto L2a1
        L29f:
            long r12 = r8.disabledContentColor
        L2a1:
            androidx.compose.ui.graphics.Color r5 = new androidx.compose.ui.graphics.Color
            r5.<init>(r12)
            androidx.compose.runtime.MutableState r5 = kotlin.ResultKt.rememberUpdatedState(r5, r11)
            r12 = 0
            r11.end(r12)
            java.lang.Object r5 = r5.getValue()
            androidx.compose.ui.graphics.Color r5 = (androidx.compose.ui.graphics.Color) r5
            long r12 = r5.value
            r5 = 823569249(0x3116ab61, float:2.192529E-9)
            r11.startReplaceableGroup(r5)
            java.lang.String r5 = "interactionSource"
            if (r7 != 0) goto L2c5
            r37 = r8
            r8 = 0
            r14 = 0
            goto L2eb
        L2c5:
            int r20 = r0 >> 21
            r20 = r20 & 112(0x70, float:1.57E-43)
            r20 = r1 | r20
            r37 = r8
            r8 = r2 & 896(0x380, float:1.256E-42)
            r8 = r20 | r8
            kotlin.ResultKt.checkNotNullParameter(r6, r5)
            r14 = -2045116089(0xffffffff861a0147, float:-2.896512E-35)
            r11.startReplaceableGroup(r14)
            r14 = r8 & 14
            r20 = r8 & 112(0x70, float:1.57E-43)
            r14 = r14 | r20
            r8 = r8 & 896(0x380, float:1.256E-42)
            r8 = r8 | r14
            androidx.compose.animation.core.AnimationState r8 = r7.animateElevation(r9, r6, r11, r8)
            r14 = 0
            r11.end(r14)
        L2eb:
            r11.end(r14)
            if (r8 == 0) goto L2fb
            androidx.compose.runtime.ParcelableSnapshotMutableState r8 = r8.value$delegate
            java.lang.Object r8 = r8.getValue()
            androidx.compose.ui.unit.Dp r8 = (androidx.compose.ui.unit.Dp) r8
            float r8 = r8.value
            goto L2fc
        L2fb:
            float r8 = (float) r14
        L2fc:
            r14 = 823569344(0x3116abc0, float:2.1925501E-9)
            r11.startReplaceableGroup(r14)
            if (r7 != 0) goto L307
            r1 = 0
            r5 = 0
            goto L328
        L307:
            int r14 = r0 >> 21
            r14 = r14 & 112(0x70, float:1.57E-43)
            r1 = r1 | r14
            r2 = r2 & 896(0x380, float:1.256E-42)
            r1 = r1 | r2
            kotlin.ResultKt.checkNotNullParameter(r6, r5)
            r2 = -423890235(0xffffffffe6bbf2c5, float:-4.4378042E23)
            r11.startReplaceableGroup(r2)
            r2 = r1 & 14
            r5 = r1 & 112(0x70, float:1.57E-43)
            r2 = r2 | r5
            r1 = r1 & 896(0x380, float:1.256E-42)
            r1 = r1 | r2
            androidx.compose.animation.core.AnimationState r1 = r7.animateElevation(r9, r6, r11, r1)
            r5 = 0
            r11.end(r5)
        L328:
            r11.end(r5)
            if (r1 == 0) goto L338
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r1.value$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.unit.Dp r1 = (androidx.compose.ui.unit.Dp) r1
            float r1 = r1.value
            goto L339
        L338:
            float r1 = (float) r5
        L339:
            androidx.compose.material3.ButtonKt$Button$2 r2 = androidx.compose.material3.ButtonKt$Button$2.INSTANCE
            java.util.concurrent.atomic.AtomicInteger r14 = androidx.compose.ui.semantics.SemanticsModifierKt.lastIdentifier
            r14 = r29
            kotlin.ResultKt.checkNotNullParameter(r10, r14)
            androidx.compose.ui.semantics.AppendedSemanticsElement r14 = new androidx.compose.ui.semantics.AppendedSemanticsElement
            r14.<init>(r2, r5)
            androidx.compose.ui.Modifier r2 = r10.then(r14)
            androidx.compose.material3.ButtonKt$Button$3 r14 = new androidx.compose.material3.ButtonKt$Button$3
            r31 = r14
            r32 = r12
            r34 = r19
            r35 = r38
            r36 = r0
            r31.<init>()
            r5 = 956488494(0x3902db2e, float:1.2479417E-4)
            androidx.compose.runtime.internal.ComposableLambdaImpl r14 = kotlin.ResultKt.composableLambda(r11, r5, r14)
            r5 = r0 & 14
            r20 = r6
            r6 = r0 & 896(0x380, float:1.256E-42)
            r5 = r5 | r6
            r6 = r0 & 7168(0x1c00, float:1.0045E-41)
            r5 = r5 | r6
            int r6 = r0 << 6
            r6 = r6 & r16
            r5 = r5 | r6
            int r0 = r0 << 3
            r0 = r0 & r17
            r6 = r5 | r0
            androidx.compose.runtime.DynamicProvidableCompositionLocal r0 = androidx.compose.material3.SurfaceKt.LocalAbsoluteTonalElevation
            r0 = -789752804(0xffffffffd0ed541c, float:-3.18536991E10)
            r11.startReplaceableGroup(r0)
            androidx.compose.runtime.DynamicProvidableCompositionLocal r0 = androidx.compose.material3.SurfaceKt.LocalAbsoluteTonalElevation
            java.lang.Object r5 = r11.consume(r0)
            androidx.compose.ui.unit.Dp r5 = (androidx.compose.ui.unit.Dp) r5
            float r5 = r5.value
            float r5 = r5 + r1
            androidx.compose.runtime.DynamicProvidableCompositionLocal r1 = androidx.compose.material3.ContentColorKt.LocalContentColor
            r31 = r7
            androidx.compose.ui.graphics.Color r7 = new androidx.compose.ui.graphics.Color
            r7.<init>(r12)
            androidx.compose.runtime.ProvidedValue r1 = r1.provides(r7)
            androidx.compose.ui.unit.Dp r7 = new androidx.compose.ui.unit.Dp
            r7.<init>(r5)
            androidx.compose.runtime.ProvidedValue r0 = r0.provides(r7)
            androidx.compose.runtime.ProvidedValue[] r12 = new androidx.compose.runtime.ProvidedValue[]{r1, r0}
            androidx.compose.material3.SurfaceKt$Surface$3 r13 = new androidx.compose.material3.SurfaceKt$Surface$3
            r0 = r13
            r1 = r2
            r2 = r18
            r7 = 0
            r16 = r20
            r17 = r31
            r15 = r7
            r7 = r8
            r20 = r37
            r8 = r16
            r21 = r9
            r22 = r10
            r10 = r30
            r15 = r11
            r11 = r14
            r0.<init>(r2, r3, r5, r6, r7, r8, r9, r10, r11)
            r0 = 1279702876(0x4c46b75c, float:5.2092272E7)
            androidx.compose.runtime.internal.ComposableLambdaImpl r0 = kotlin.ResultKt.composableLambda(r15, r0, r13)
            r1 = 56
            CompositionLocalProvider(r12, r0, r15, r1)
            r0 = 0
            r15.end(r0)
            r8 = r16
            r6 = r17
            r4 = r18
            r7 = r19
            r5 = r20
            r3 = r21
            r2 = r22
        L3dd:
            androidx.compose.runtime.RecomposeScopeImpl r12 = r15.endRestartGroup()
            if (r12 != 0) goto L3e4
            goto L3f4
        L3e4:
            androidx.compose.material3.ButtonKt$Button$4 r13 = new androidx.compose.material3.ButtonKt$Button$4
            r0 = r13
            r1 = r30
            r9 = r38
            r10 = r40
            r11 = r41
            r0.<init>()
            r12.block = r13
        L3f4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: _COROUTINE._BOUNDARY.Button(kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, boolean, androidx.compose.ui.graphics.Shape, androidx.compose.material3.ButtonColors, androidx.compose.material3.ButtonElevation, androidx.compose.foundation.layout.PaddingValuesImpl, androidx.compose.foundation.interaction.MutableInteractionSourceImpl, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.Object, kotlin.jvm.functions.Function2] */
    /* JADX WARN: Type inference failed for: r6v4, types: [androidx.compose.runtime.PersistentCompositionLocalMap, java.lang.Object] */
    public static final void CompositionLocalProvider(final ProvidedValue[] providedValueArr, Function2 function2, Composer composer, int i) {
        PersistentCompositionLocalHashMap updateProviderMapGroup;
        boolean z;
        ResultKt.checkNotNullParameter(providedValueArr, "values");
        ResultKt.checkNotNullParameter(function2, "content");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1390796515);
        final PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl.currentCompositionLocalScope();
        composerImpl.m59startBaiHCIY(201, 0, EffectsKt.provider, null);
        composerImpl.m59startBaiHCIY(203, 0, EffectsKt.providerValues, null);
        final int i2 = 1;
        Function2 function22 = new Function2() { // from class: androidx.compose.runtime.ComposerImpl$invokeMovableContentLambda$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX WARN: Type inference failed for: r2v0, types: [androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder, androidx.compose.runtime.internal.PersistentCompositionLocalHashMap$Builder] */
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                int i3 = i2;
                Object obj3 = providedValueArr;
                switch (i3) {
                    case 0:
                        Composer composer2 = (Composer) obj;
                        if ((((Number) obj2).intValue() & 11) == 2) {
                            ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                            if (composerImpl2.getSkipping()) {
                                composerImpl2.skipToGroupEnd();
                                return Unit.INSTANCE;
                            }
                        }
                        DurationKt$$ExternalSyntheticCheckNotZero0.m(obj3);
                        throw null;
                    default:
                        ((Number) obj2).intValue();
                        ComposerImpl composerImpl3 = (ComposerImpl) ((Composer) obj);
                        composerImpl3.startReplaceableGroup(-948105361);
                        ProvidedValue[] providedValueArr2 = (ProvidedValue[]) obj3;
                        PersistentCompositionLocalMap persistentCompositionLocalMap = (PersistentCompositionLocalMap) currentCompositionLocalScope;
                        ResultKt.checkNotNullParameter(providedValueArr2, "values");
                        ResultKt.checkNotNullParameter(persistentCompositionLocalMap, "parentScope");
                        composerImpl3.startReplaceableGroup(-300354947);
                        PersistentCompositionLocalHashMap persistentCompositionLocalHashMap = PersistentCompositionLocalHashMap.Empty;
                        persistentCompositionLocalHashMap.getClass();
                        ?? persistentHashMapBuilder = new PersistentHashMapBuilder(persistentCompositionLocalHashMap);
                        persistentHashMapBuilder.map = persistentCompositionLocalHashMap;
                        for (ProvidedValue providedValue : providedValueArr2) {
                            composerImpl3.startReplaceableGroup(680845765);
                            boolean z2 = providedValue.canOverride;
                            CompositionLocal compositionLocal = providedValue.compositionLocal;
                            if (!z2) {
                                ResultKt.checkNotNullParameter(compositionLocal, "key");
                                if (((PersistentCompositionLocalHashMap) persistentCompositionLocalMap).containsKey(compositionLocal)) {
                                    composerImpl3.end(false);
                                }
                            }
                            ResultKt.checkNotNull(compositionLocal, "null cannot be cast to non-null type androidx.compose.runtime.CompositionLocal<kotlin.Any?>");
                            persistentHashMapBuilder.put(compositionLocal, compositionLocal.provided$runtime_release(providedValue.value, composerImpl3));
                            composerImpl3.end(false);
                        }
                        PersistentCompositionLocalHashMap build = persistentHashMapBuilder.build();
                        composerImpl3.end(false);
                        composerImpl3.end(false);
                        return build;
                }
            }
        };
        int i3 = 2;
        ResultKt.beforeCheckcastToFunctionOfArity(2, function22);
        PersistentCompositionLocalMap persistentCompositionLocalMap = (PersistentCompositionLocalMap) function22.invoke(composerImpl, 1);
        composerImpl.end(false);
        if (composerImpl.inserting) {
            updateProviderMapGroup = composerImpl.updateProviderMapGroup(currentCompositionLocalScope, persistentCompositionLocalMap);
            composerImpl.writerHasAProvider = true;
            z = false;
        } else {
            SlotReader slotReader = composerImpl.reader;
            Object groupGet = slotReader.groupGet(slotReader.currentGroup, 0);
            ResultKt.checkNotNull(groupGet, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
            ?? r6 = (PersistentCompositionLocalMap) groupGet;
            SlotReader slotReader2 = composerImpl.reader;
            Object groupGet2 = slotReader2.groupGet(slotReader2.currentGroup, 1);
            ResultKt.checkNotNull(groupGet2, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
            PersistentCompositionLocalMap persistentCompositionLocalMap2 = (PersistentCompositionLocalMap) groupGet2;
            if (composerImpl.getSkipping() && ResultKt.areEqual(persistentCompositionLocalMap2, persistentCompositionLocalMap)) {
                composerImpl.groupNodeCount = composerImpl.reader.skipGroup() + composerImpl.groupNodeCount;
                z = false;
                updateProviderMapGroup = r6;
            } else {
                updateProviderMapGroup = composerImpl.updateProviderMapGroup(currentCompositionLocalScope, persistentCompositionLocalMap);
                z = !ResultKt.areEqual(updateProviderMapGroup, r6);
            }
        }
        if (z && !composerImpl.inserting) {
            ((SparseArray) composerImpl.providerUpdates.backing).put(composerImpl.reader.currentGroup, updateProviderMapGroup);
        }
        boolean z2 = composerImpl.providersInvalid;
        IntStack intStack = composerImpl.providersInvalidStack;
        intStack.push(z2 ? 1 : 0);
        composerImpl.providersInvalid = z;
        composerImpl.providerCache = updateProviderMapGroup;
        composerImpl.m59startBaiHCIY(202, 0, EffectsKt.compositionLocalMap, updateProviderMapGroup);
        function2.invoke(composerImpl, Integer.valueOf((i >> 3) & 14));
        composerImpl.end(false);
        composerImpl.end(false);
        composerImpl.providersInvalid = intStack.pop() != 0;
        composerImpl.providerCache = null;
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.block = new TextKt$ProvideTextStyle$1(i, i3, providedValueArr, function2);
    }

    public static final long CornerRadius(float f, float f2) {
        long floatToIntBits = (Float.floatToIntBits(f2) & 4294967295L) | (Float.floatToIntBits(f) << 32);
        int i = CornerRadius.$r8$clinit;
        return floatToIntBits;
    }

    public static final long Offset(float f, float f2) {
        long floatToIntBits = (Float.floatToIntBits(f2) & 4294967295L) | (Float.floatToIntBits(f) << 32);
        int i = Offset.$r8$clinit;
        return floatToIntBits;
    }

    /* renamed from: Paragraph-UdtVg6A$default, reason: not valid java name */
    public static AndroidParagraph m2ParagraphUdtVg6A$default(String str, TextStyle textStyle, long j, Density density, FontFamily.Resolver resolver, int i) {
        EmptyList emptyList = EmptyList.INSTANCE;
        ResultKt.checkNotNullParameter(str, "text");
        ResultKt.checkNotNullParameter(textStyle, "style");
        ResultKt.checkNotNullParameter(density, "density");
        ResultKt.checkNotNullParameter(resolver, "fontFamilyResolver");
        return new AndroidParagraph(new AndroidParagraphIntrinsics(textStyle, resolver, density, str, emptyList, emptyList), i, false, j);
    }

    /* renamed from: Paragraph-_EkL_-Y, reason: not valid java name */
    public static final AndroidParagraph m3Paragraph_EkL_Y(int i, long j, ParagraphIntrinsics paragraphIntrinsics, boolean z) {
        ResultKt.checkNotNullParameter(paragraphIntrinsics, "paragraphIntrinsics");
        return new AndroidParagraph((AndroidParagraphIntrinsics) paragraphIntrinsics, i, z, j);
    }

    public static final AndroidParagraphIntrinsics ParagraphIntrinsics(TextStyle textStyle, FontFamily.Resolver resolver, Density density, String str, List list, List list2) {
        ResultKt.checkNotNullParameter(str, "text");
        ResultKt.checkNotNullParameter(list, "spanStyles");
        ResultKt.checkNotNullParameter(list2, "placeholders");
        ResultKt.checkNotNullParameter(resolver, "fontFamilyResolver");
        return new AndroidParagraphIntrinsics(textStyle, resolver, density, str, list, list2);
    }

    /* renamed from: Rect-tz77jQw, reason: not valid java name */
    public static final Rect m4Recttz77jQw(long j, long j2) {
        return new Rect(Offset.m76getXimpl(j), Offset.m77getYimpl(j), Size.m85getWidthimpl(j2) + Offset.m76getXimpl(j), Size.m83getHeightimpl(j2) + Offset.m77getYimpl(j));
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:30:0x005a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:35:0x001a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:36:0x001a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:39:0x0060 */
    /* JADX WARN: Type inference failed for: r3v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public static final SemanticsNode SemanticsNode(LayoutNode layoutNode, boolean z) {
        ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
        Modifier.Node node = layoutNode.nodes.head;
        Object obj = null;
        if ((node.aggregateChildKindSet & 8) != 0) {
            loop0: while (true) {
                if (node != null) {
                    if ((node.kindSet & 8) != 0) {
                        Modifier.Node node2 = node;
                        MutableVector mutableVector = null;
                        while (node2 != null) {
                            if (node2 instanceof SemanticsModifierNode) {
                                obj = node2;
                                break loop0;
                            }
                            if ((node2.kindSet & 8) != 0 && (node2 instanceof DelegatingNode)) {
                                Modifier.Node node3 = ((DelegatingNode) node2).delegate;
                                int i = 0;
                                mutableVector = mutableVector;
                                while (node3 != null) {
                                    if ((node3.kindSet & 8) != 0) {
                                        i++;
                                        mutableVector = mutableVector;
                                        if (i == 1) {
                                            node2 = node3;
                                        } else {
                                            if (mutableVector == null) {
                                                ?? obj2 = new Object();
                                                obj2.content = new Modifier.Node[16];
                                                obj2.size = 0;
                                                mutableVector = obj2;
                                            }
                                            if (node2 != null) {
                                                mutableVector.add(node2);
                                                node2 = null;
                                            }
                                            mutableVector.add(node3);
                                        }
                                    }
                                    node3 = node3.child;
                                    mutableVector = mutableVector;
                                }
                                if (i == 1) {
                                }
                            }
                            node2 = Snake.access$pop(mutableVector);
                        }
                    }
                    if ((node.aggregateChildKindSet & 8) == 0) {
                        break;
                    }
                    node = node.child;
                } else {
                    break;
                }
            }
        }
        ResultKt.checkNotNull(obj);
        Modifier.Node node4 = ((Modifier.Node) ((SemanticsModifierNode) obj)).node;
        SemanticsConfiguration collapsedSemantics$ui_release = layoutNode.getCollapsedSemantics$ui_release();
        ResultKt.checkNotNull(collapsedSemantics$ui_release);
        return new SemanticsNode(node4, z, layoutNode, collapsedSemantics$ui_release);
    }

    public static final long Size(float f, float f2) {
        long floatToIntBits = (Float.floatToIntBits(f2) & 4294967295L) | (Float.floatToIntBits(f) << 32);
        int i = Size.$r8$clinit;
        return floatToIntBits;
    }

    public static final long TextRange(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException(("start cannot be negative. [start: " + i + ", end: " + i2 + ']').toString());
        }
        if (i2 >= 0) {
            long j = (i2 & 4294967295L) | (i << 32);
            int i3 = TextRange.$r8$clinit;
            return j;
        }
        throw new IllegalArgumentException(("end cannot be negative. [start: " + i + ", end: " + i2 + ']').toString());
    }

    public static final boolean access$getHasEmojiCompat(TextStyle textStyle) {
        PlatformTextStyle platformTextStyle = textStyle.platformStyle;
        boolean z = false;
        EmojiSupportMatch emojiSupportMatch = (platformTextStyle == null || platformTextStyle.paragraphStyle == null) ? null : new EmojiSupportMatch(0);
        if (emojiSupportMatch != null && emojiSupportMatch.value == 1) {
            z = true;
        }
        return !z;
    }

    public static ColorSpace adapt$default(ColorSpace colorSpace) {
        WhitePoint whitePoint = Illuminant.D50;
        Adaptation$Companion$Bradford$1 adaptation$Companion$Bradford$1 = Adaptation.Bradford;
        ResultKt.checkNotNullParameter(colorSpace, "<this>");
        if (!ColorModel.m131equalsimpl0(colorSpace.model, ColorModel.Rgb)) {
            return colorSpace;
        }
        Rgb rgb = (Rgb) colorSpace;
        WhitePoint whitePoint2 = rgb.whitePoint;
        if (compare(whitePoint2, whitePoint)) {
            return colorSpace;
        }
        float[] xyz$ui_graphics_release = whitePoint.toXyz$ui_graphics_release();
        return new Rgb(rgb.name, rgb.primaries, whitePoint, mul3x3(chromaticAdaptation(adaptation$Companion$Bradford$1.transform, whitePoint2.toXyz$ui_graphics_release(), xyz$ui_graphics_release), rgb.transform), rgb.oetfOrig, rgb.eotfOrig, rgb.min, rgb.max, rgb.transferParameters, -1);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:15:0x005a */
    /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3 */
    /* JADX DEBUG: Multi-variable search result rejected for r0v5, resolved type: androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3 */
    /* JADX DEBUG: Multi-variable search result rejected for r0v6, resolved type: androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3 */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0058, code lost:
    
        if ((!r8) == false) goto L23;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0064 -> B:10:0x0067). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitAllPointersUp(androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl.PointerEventHandlerCoroutine r7, kotlin.coroutines.Continuation r8) {
        /*
            boolean r0 = r8 instanceof androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3
            if (r0 == 0) goto L13
            r0 = r8
            androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3 r0 = (androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3 r0 = new androidx.compose.foundation.gestures.ForEachGestureKt$awaitAllPointersUp$3
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine r7 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L67
        L2a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L32:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.String r8 = "<this>"
            kotlin.ResultKt.checkNotNullParameter(r7, r8)
            androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl r8 = androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl.this
            androidx.compose.ui.input.pointer.PointerEvent r8 = r8.currentEvent
            java.util.List r8 = r8.changes
            int r2 = r8.size()
            r5 = r4
        L45:
            if (r5 >= r2) goto L56
            java.lang.Object r6 = r8.get(r5)
            androidx.compose.ui.input.pointer.PointerInputChange r6 = (androidx.compose.ui.input.pointer.PointerInputChange) r6
            boolean r6 = r6.pressed
            if (r6 == 0) goto L53
            r8 = r3
            goto L57
        L53:
            int r5 = r5 + 1
            goto L45
        L56:
            r8 = r4
        L57:
            r8 = r8 ^ r3
            if (r8 != 0) goto L80
        L5a:
            androidx.compose.ui.input.pointer.PointerEventPass r8 = androidx.compose.ui.input.pointer.PointerEventPass.Final
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r8 = r7.awaitPointerEvent(r8, r0)
            if (r8 != r1) goto L67
            return r1
        L67:
            androidx.compose.ui.input.pointer.PointerEvent r8 = (androidx.compose.ui.input.pointer.PointerEvent) r8
            java.util.List r8 = r8.changes
            int r2 = r8.size()
            r5 = r4
        L70:
            if (r5 >= r2) goto L80
            java.lang.Object r6 = r8.get(r5)
            androidx.compose.ui.input.pointer.PointerInputChange r6 = (androidx.compose.ui.input.pointer.PointerInputChange) r6
            boolean r6 = r6.pressed
            if (r6 == 0) goto L7d
            goto L5a
        L7d:
            int r5 = r5 + 1
            goto L70
        L80:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: _COROUTINE._BOUNDARY.awaitAllPointersUp(androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Object awaitEachGesture(PointerInputScope pointerInputScope, Function2 function2, Continuation continuation) {
        Object awaitPointerEventScope = ((SuspendingPointerInputModifierNodeImpl) pointerInputScope).awaitPointerEventScope(new ForEachGestureKt$awaitEachGesture$2(continuation.getContext(), function2, null), continuation);
        return awaitPointerEventScope == CoroutineSingletons.COROUTINE_SUSPENDED ? awaitPointerEventScope : Unit.INSTANCE;
    }

    public static final boolean backwardFocusSearch(FocusTargetNode focusTargetNode, Function1 function1) {
        int ordinal = focusTargetNode.focusState.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                FocusTargetNode activeChild = getActiveChild(focusTargetNode);
                if (activeChild == null) {
                    throw new IllegalStateException("ActiveParent must have a focusedChild".toString());
                }
                int ordinal2 = activeChild.focusState.ordinal();
                if (ordinal2 != 0) {
                    if (ordinal2 != 1) {
                        if (ordinal2 != 2) {
                            if (ordinal2 != 3) {
                                throw new RuntimeException();
                            }
                            throw new IllegalStateException("ActiveParent must have a focusedChild".toString());
                        }
                    } else if (!backwardFocusSearch(activeChild, function1) && !m11generateAndSearchChildren4C6V_qg(focusTargetNode, activeChild, 2, function1) && (!activeChild.fetchFocusProperties$ui_release().canFocus || !((Boolean) function1.invoke(activeChild)).booleanValue())) {
                        return false;
                    }
                }
                return m11generateAndSearchChildren4C6V_qg(focusTargetNode, activeChild, 2, function1);
            }
            if (ordinal != 2) {
                if (ordinal != 3) {
                    throw new RuntimeException();
                }
                if (!pickChildForBackwardSearch(focusTargetNode, function1) && (!focusTargetNode.fetchFocusProperties$ui_release().canFocus || !((Boolean) function1.invoke(focusTargetNode)).booleanValue())) {
                    return false;
                }
            }
            return true;
        }
        return pickChildForBackwardSearch(focusTargetNode, function1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x005b, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r19, 3) != false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0061, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r19, 4) == false) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0068, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r19, 3) == false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x006a, code lost:
    
        r1 = r0 - r17.right;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x008d, code lost:
    
        r1 = java.lang.Math.max(0.0f, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0096, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r19, 3) == false) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0098, code lost:
    
        r0 = r0 - r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x00bc, code lost:
    
        if (r1 >= java.lang.Math.max(1.0f, r0)) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x009e, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r19, 4) == false) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00a0, code lost:
    
        r0 = r2 - r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a7, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r19, 5) == false) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a9, code lost:
    
        r0 = r5 - r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00b0, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r19, 6) == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00b2, code lost:
    
        r0 = r13 - r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00cb, code lost:
    
        throw new java.lang.IllegalStateException("This function should only be used for 2-D focus search".toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0073, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r19, 4) == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0075, code lost:
    
        r1 = r17.left - r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x007d, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r19, 5) == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x007f, code lost:
    
        r1 = r5 - r17.bottom;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0088, code lost:
    
        if (androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r19, 6) == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x008a, code lost:
    
        r1 = r17.top - r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00d5, code lost:
    
        throw new java.lang.IllegalStateException("This function should only be used for 2-D focus search".toString());
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x003f, code lost:
    
        if (r7 <= r14) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x004a, code lost:
    
        if (r5 >= r13) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0055, code lost:
    
        if (r15 <= r12) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0034, code lost:
    
        if (r0 >= r2) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x00be, code lost:
    
        return true;
     */
    /* renamed from: beamBeats-I7lrPNg, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean m5beamBeatsI7lrPNg(androidx.compose.ui.geometry.Rect r16, androidx.compose.ui.geometry.Rect r17, androidx.compose.ui.geometry.Rect r18, int r19) {
        /*
            r0 = r16
            r1 = r17
            r2 = r18
            r3 = r19
            boolean r4 = beamBeats_I7lrPNg$inSourceBeam(r3, r2, r0)
            if (r4 != 0) goto Lc0
            boolean r4 = beamBeats_I7lrPNg$inSourceBeam(r3, r1, r0)
            if (r4 != 0) goto L16
            goto Lc0
        L16:
            r4 = 3
            boolean r6 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r3, r4)
            java.lang.String r8 = "This function should only be used for 2-D focus search"
            r9 = 6
            r10 = 5
            r11 = 4
            float r12 = r2.top
            float r13 = r2.bottom
            float r14 = r2.left
            float r2 = r2.right
            float r15 = r0.bottom
            float r5 = r0.top
            float r7 = r0.right
            float r0 = r0.left
            if (r6 == 0) goto L37
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 < 0) goto Lbe
            goto L57
        L37:
            boolean r6 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r3, r11)
            if (r6 == 0) goto L42
            int r6 = (r7 > r14 ? 1 : (r7 == r14 ? 0 : -1))
            if (r6 > 0) goto Lbe
            goto L57
        L42:
            boolean r6 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r3, r10)
            if (r6 == 0) goto L4d
            int r6 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
            if (r6 < 0) goto Lbe
            goto L57
        L4d:
            boolean r6 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r3, r9)
            if (r6 == 0) goto Ld6
            int r6 = (r15 > r12 ? 1 : (r15 == r12 ? 0 : -1))
            if (r6 > 0) goto Lbe
        L57:
            boolean r6 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r3, r4)
            if (r6 != 0) goto Lbe
            boolean r6 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r3, r11)
            if (r6 == 0) goto L64
            goto Lbe
        L64:
            boolean r6 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r3, r4)
            if (r6 == 0) goto L6f
            float r1 = r1.right
            float r1 = r0 - r1
            goto L8d
        L6f:
            boolean r6 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r3, r11)
            if (r6 == 0) goto L79
            float r1 = r1.left
            float r1 = r1 - r7
            goto L8d
        L79:
            boolean r6 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r3, r10)
            if (r6 == 0) goto L84
            float r1 = r1.bottom
            float r1 = r5 - r1
            goto L8d
        L84:
            boolean r6 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r3, r9)
            if (r6 == 0) goto Lcc
            float r1 = r1.top
            float r1 = r1 - r15
        L8d:
            r6 = 0
            float r1 = java.lang.Math.max(r6, r1)
            boolean r4 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r3, r4)
            if (r4 == 0) goto L9a
            float r0 = r0 - r14
            goto Lb4
        L9a:
            boolean r0 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r3, r11)
            if (r0 == 0) goto La3
            float r0 = r2 - r7
            goto Lb4
        La3:
            boolean r0 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r3, r10)
            if (r0 == 0) goto Lac
            float r0 = r5 - r12
            goto Lb4
        Lac:
            boolean r0 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r3, r9)
            if (r0 == 0) goto Lc2
            float r0 = r13 - r15
        Lb4:
            r2 = 1065353216(0x3f800000, float:1.0)
            float r0 = java.lang.Math.max(r2, r0)
            int r0 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r0 >= 0) goto Lc0
        Lbe:
            r5 = 1
            goto Le0
        Lc0:
            r5 = 0
            goto Le0
        Lc2:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = r8.toString()
            r0.<init>(r1)
            throw r0
        Lcc:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = r8.toString()
            r0.<init>(r1)
            throw r0
        Ld6:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = r8.toString()
            r0.<init>(r1)
            throw r0
        Le0:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: _COROUTINE._BOUNDARY.m5beamBeatsI7lrPNg(androidx.compose.ui.geometry.Rect, androidx.compose.ui.geometry.Rect, androidx.compose.ui.geometry.Rect, int):boolean");
    }

    public static final boolean beamBeats_I7lrPNg$inSourceBeam(int i, Rect rect, Rect rect2) {
        if (FocusDirection.m67equalsimpl0(i, 3) || FocusDirection.m67equalsimpl0(i, 4)) {
            if (rect.bottom <= rect2.top || rect.top >= rect2.bottom) {
                return false;
            }
        } else {
            if (!FocusDirection.m67equalsimpl0(i, 5) && !FocusDirection.m67equalsimpl0(i, 6)) {
                throw new IllegalStateException("This function should only be used for 2-D focus search".toString());
            }
            if (rect.right <= rect2.left || rect.left >= rect2.right) {
                return false;
            }
        }
        return true;
    }

    public static final Rect boundsInParent(InnerNodeCoordinator innerNodeCoordinator) {
        ResultKt.checkNotNullParameter(innerNodeCoordinator, "<this>");
        LayoutCoordinates parentLayoutCoordinates = innerNodeCoordinator.getParentLayoutCoordinates();
        if (parentLayoutCoordinates != null) {
            return ((NodeCoordinator) parentLayoutCoordinates).localBoundingBoxOf(innerNodeCoordinator, true);
        }
        long j = innerNodeCoordinator.measuredSize;
        return new Rect(0.0f, 0.0f, (int) (j >> 32), (int) (j & 4294967295L));
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:12:0x007c */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.collections.EmptyList] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.ArrayList] */
    public static final boolean calculateIfHorizontallyStacked(ArrayList arrayList) {
        ?? r0;
        long j;
        if (arrayList.size() < 2) {
            return true;
        }
        if (arrayList.size() == 0 || arrayList.size() == 1) {
            r0 = EmptyList.INSTANCE;
        } else {
            r0 = new ArrayList();
            Object obj = arrayList.get(0);
            int lastIndex = ResultKt.getLastIndex(arrayList);
            int i = 0;
            while (i < lastIndex) {
                i++;
                Object obj2 = arrayList.get(i);
                SemanticsNode semanticsNode = (SemanticsNode) obj2;
                SemanticsNode semanticsNode2 = (SemanticsNode) obj;
                r0.add(new Offset(Offset(Math.abs(Offset.m76getXimpl(semanticsNode2.getBoundsInRoot().m81getCenterF1C5BW0()) - Offset.m76getXimpl(semanticsNode.getBoundsInRoot().m81getCenterF1C5BW0())), Math.abs(Offset.m77getYimpl(semanticsNode2.getBoundsInRoot().m81getCenterF1C5BW0()) - Offset.m77getYimpl(semanticsNode.getBoundsInRoot().m81getCenterF1C5BW0())))));
                obj = obj2;
            }
        }
        if (r0.size() == 1) {
            j = ((Offset) CollectionsKt___CollectionsKt.first(r0)).packedValue;
        } else {
            if (r0.isEmpty()) {
                throw new UnsupportedOperationException("Empty collection can't be reduced.");
            }
            Object first = CollectionsKt___CollectionsKt.first(r0);
            int lastIndex2 = ResultKt.getLastIndex(r0);
            if (1 <= lastIndex2) {
                int i2 = 1;
                while (true) {
                    first = new Offset(Offset.m79plusMKHz9U(((Offset) first).packedValue, ((Offset) r0.get(i2)).packedValue));
                    if (i2 == lastIndex2) {
                        break;
                    }
                    i2++;
                }
            }
            j = ((Offset) first).packedValue;
        }
        return Offset.m77getYimpl(j) < Offset.m76getXimpl(j);
    }

    public static final int ceilToIntPx(float f) {
        return ResultKt.roundToInt((float) Math.ceil(f));
    }

    public static final boolean changedToDownIgnoreConsumed(PointerInputChange pointerInputChange) {
        ResultKt.checkNotNullParameter(pointerInputChange, "<this>");
        return !pointerInputChange.previousPressed && pointerInputChange.pressed;
    }

    public static final boolean changedToUpIgnoreConsumed(PointerInputChange pointerInputChange) {
        ResultKt.checkNotNullParameter(pointerInputChange, "<this>");
        return pointerInputChange.previousPressed && !pointerInputChange.pressed;
    }

    public static final void checkElementIndex$runtime_release(int i, int i2) {
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException("index: " + i + ", size: " + i2);
        }
    }

    public static final void checkPositionIndex$runtime_release(int i, int i2) {
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException("index: " + i + ", size: " + i2);
        }
    }

    public static final void checkRangeIndexes$runtime_release(int i, int i2, int i3) {
        if (i < 0 || i2 > i3) {
            throw new IndexOutOfBoundsException("fromIndex: " + i + ", toIndex: " + i2 + ", size: " + i3);
        }
        if (i <= i2) {
            return;
        }
        throw new IllegalArgumentException("fromIndex: " + i + " > toIndex: " + i2);
    }

    public static final float[] chromaticAdaptation(float[] fArr, float[] fArr2, float[] fArr3) {
        ResultKt.checkNotNullParameter(fArr, "matrix");
        mul3x3Float3(fArr, fArr2);
        mul3x3Float3(fArr, fArr3);
        return mul3x3(inverse3x3(fArr), mul3x3Diag(new float[]{fArr3[0] / fArr2[0], fArr3[1] / fArr2[1], fArr3[2] / fArr2[2]}, fArr));
    }

    public static final boolean clearFocus(FocusTargetNode focusTargetNode, boolean z, boolean z2) {
        ResultKt.checkNotNullParameter(focusTargetNode, "<this>");
        int ordinal = focusTargetNode.focusState.ordinal();
        FocusStateImpl focusStateImpl = FocusStateImpl.Inactive;
        if (ordinal == 0) {
            focusTargetNode.focusState = focusStateImpl;
            if (z2) {
                ResultKt.refreshFocusEventNodes(focusTargetNode);
            }
        } else if (ordinal == 1) {
            FocusTargetNode activeChild = getActiveChild(focusTargetNode);
            if (activeChild != null && !clearFocus(activeChild, z, z2)) {
                return false;
            }
            focusTargetNode.focusState = focusStateImpl;
            if (z2) {
                ResultKt.refreshFocusEventNodes(focusTargetNode);
            }
        } else {
            if (ordinal == 2) {
                if (!z) {
                    return z;
                }
                focusTargetNode.focusState = focusStateImpl;
                if (!z2) {
                    return z;
                }
                ResultKt.refreshFocusEventNodes(focusTargetNode);
                return z;
            }
            if (ordinal != 3) {
                throw new RuntimeException();
            }
        }
        return true;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:40:0x0095 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:45:0x0045 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:46:0x0045 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:49:0x009b */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public static final void collectAccessibleChildren(DelegatableNode delegatableNode, MutableVector mutableVector) {
        Modifier.Node node = ((Modifier.Node) delegatableNode).node;
        if (!node.isAttached) {
            throw new IllegalStateException("visitChildren called on an unattached node".toString());
        }
        ?? obj = new Object();
        obj.content = new Modifier.Node[16];
        obj.size = 0;
        Modifier.Node node2 = node.child;
        if (node2 == null) {
            Snake.access$addLayoutNodeChildren(obj, node);
        } else {
            obj.add(node2);
        }
        while (obj.isNotEmpty()) {
            Modifier.Node node3 = (Modifier.Node) obj.removeAt(obj.size - 1);
            if ((node3.aggregateChildKindSet & 1024) == 0) {
                Snake.access$addLayoutNodeChildren(obj, node3);
            } else {
                while (true) {
                    if (node3 == null) {
                        break;
                    }
                    if ((node3.kindSet & 1024) != 0) {
                        MutableVector mutableVector2 = null;
                        while (node3 != null) {
                            if (node3 instanceof FocusTargetNode) {
                                FocusTargetNode focusTargetNode = (FocusTargetNode) node3;
                                if (focusTargetNode.isAttached) {
                                    if (focusTargetNode.fetchFocusProperties$ui_release().canFocus) {
                                        mutableVector.add(focusTargetNode);
                                    } else {
                                        collectAccessibleChildren(focusTargetNode, mutableVector);
                                    }
                                }
                            } else if ((node3.kindSet & 1024) != 0 && (node3 instanceof DelegatingNode)) {
                                Modifier.Node node4 = ((DelegatingNode) node3).delegate;
                                int i = 0;
                                mutableVector2 = mutableVector2;
                                while (node4 != null) {
                                    if ((node4.kindSet & 1024) != 0) {
                                        i++;
                                        mutableVector2 = mutableVector2;
                                        if (i == 1) {
                                            node3 = node4;
                                        } else {
                                            if (mutableVector2 == null) {
                                                ?? obj2 = new Object();
                                                obj2.content = new Modifier.Node[16];
                                                obj2.size = 0;
                                                mutableVector2 = obj2;
                                            }
                                            if (node3 != null) {
                                                mutableVector2.add(node3);
                                                node3 = null;
                                            }
                                            mutableVector2.add(node4);
                                        }
                                    }
                                    node4 = node4.child;
                                    mutableVector2 = mutableVector2;
                                }
                                if (i == 1) {
                                }
                            }
                            node3 = Snake.access$pop(mutableVector2);
                        }
                    } else {
                        node3 = node3.child;
                    }
                }
            }
        }
    }

    public static final boolean compare(WhitePoint whitePoint, WhitePoint whitePoint2) {
        ResultKt.checkNotNullParameter(whitePoint, "a");
        ResultKt.checkNotNullParameter(whitePoint2, "b");
        if (whitePoint == whitePoint2) {
            return true;
        }
        return Math.abs(whitePoint.x - whitePoint2.x) < 0.001f && Math.abs(whitePoint.y - whitePoint2.y) < 0.001f;
    }

    public static final Modifier composed(Modifier modifier, Function3 function3) {
        ResultKt.checkNotNullParameter(modifier, "<this>");
        return modifier.then(new ComposedModifier(function3));
    }

    public static DynamicProvidableCompositionLocal compositionLocalOf$default(Function0 function0) {
        return new DynamicProvidableCompositionLocal(StructuralEqualityPolicy.INSTANCE, function0);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r4v1, types: [androidx.emoji2.text.EmojiCompat$Config, androidx.emoji2.text.FontRequestEmojiCompatConfig] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static androidx.emoji2.text.FontRequestEmojiCompatConfig create(android.content.Context r8) {
        /*
            android.content.pm.PackageManager r0 = r8.getPackageManager()
            java.lang.String r1 = "Package manager required to locate emoji font provider"
            kotlin.ResultKt.checkNotNull$1(r0, r1)
            android.content.Intent r1 = new android.content.Intent
            java.lang.String r2 = "androidx.content.action.LOAD_EMOJI_FONT"
            r1.<init>(r2)
            r2 = 0
            java.util.List r1 = r0.queryIntentContentProviders(r1, r2)
            java.util.Iterator r1 = r1.iterator()
        L19:
            boolean r3 = r1.hasNext()
            r4 = 0
            if (r3 == 0) goto L35
            java.lang.Object r3 = r1.next()
            android.content.pm.ResolveInfo r3 = (android.content.pm.ResolveInfo) r3
            android.content.pm.ProviderInfo r3 = r3.providerInfo
            if (r3 == 0) goto L19
            android.content.pm.ApplicationInfo r5 = r3.applicationInfo
            if (r5 == 0) goto L19
            int r5 = r5.flags
            r6 = 1
            r5 = r5 & r6
            if (r5 != r6) goto L19
            goto L36
        L35:
            r3 = r4
        L36:
            if (r3 != 0) goto L3a
        L38:
            r2 = r4
            goto L6d
        L3a:
            java.lang.String r1 = r3.authority     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L66
            java.lang.String r3 = r3.packageName     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L66
            r5 = 64
            android.content.pm.PackageInfo r0 = r0.getPackageInfo(r3, r5)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L66
            android.content.pm.Signature[] r0 = r0.signatures     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L66
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L66
            r5.<init>()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L66
            int r6 = r0.length     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L66
        L4c:
            if (r2 >= r6) goto L5a
            r7 = r0[r2]     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L66
            byte[] r7 = r7.toByteArray()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L66
            r5.add(r7)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L66
            int r2 = r2 + 1
            goto L4c
        L5a:
            java.util.List r0 = java.util.Collections.singletonList(r5)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L66
            androidx.core.provider.FontRequest r2 = new androidx.core.provider.FontRequest     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L66
            java.lang.String r5 = "emojicompat-emoji-font"
            r2.<init>(r1, r3, r5, r0)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L66
            goto L6d
        L66:
            r0 = move-exception
            java.lang.String r1 = "emoji2.text.DefaultEmojiConfig"
            android.util.Log.wtf(r1, r0)
            goto L38
        L6d:
            if (r2 != 0) goto L70
            goto L7a
        L70:
            androidx.emoji2.text.FontRequestEmojiCompatConfig r4 = new androidx.emoji2.text.FontRequestEmojiCompatConfig
            androidx.emoji2.text.FontRequestEmojiCompatConfig$FontRequestMetadataLoader r0 = new androidx.emoji2.text.FontRequestEmojiCompatConfig$FontRequestMetadataLoader
            r0.<init>(r8, r2)
            r4.<init>(r0)
        L7a:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: _COROUTINE._BOUNDARY.create(android.content.Context):androidx.emoji2.text.FontRequestEmojiCompatConfig");
    }

    public static final TonalPalette dynamicTonalPalette(Context context) {
        ColorResourceHelper colorResourceHelper = ColorResourceHelper.INSTANCE;
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.Blue_700);
        long m50getColorWaAFU9c = colorResourceHelper.m50getColorWaAFU9c(context, R.color.Blue_800);
        long m50getColorWaAFU9c2 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.GM2_grey_800);
        long m50getColorWaAFU9c3 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.Indigo_700);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.Indigo_800);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.Pink_700);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.Pink_800);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.Purple_700);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.Purple_800);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.Red_700);
        long m50getColorWaAFU9c4 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.Red_800);
        long m50getColorWaAFU9c5 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.Teal_700);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.Teal_800);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.accent_device_default);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.accent_device_default_50);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.accent_device_default_700);
        long m50getColorWaAFU9c6 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.accent_device_default_dark);
        long m50getColorWaAFU9c7 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.accent_device_default_dark_60_percent_opacity);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.accent_device_default_light);
        long m50getColorWaAFU9c8 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.accent_material_dark);
        long m50getColorWaAFU9c9 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.accent_material_light);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.accessibility_focus_highlight);
        long m50getColorWaAFU9c10 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.autofill_background_material_dark);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.autofill_background_material_light);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.autofilled_highlight);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.background_cache_hint_selector_device_default);
        long m50getColorWaAFU9c11 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.background_cache_hint_selector_holo_dark);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.background_cache_hint_selector_holo_light);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.background_cache_hint_selector_material_dark);
        long m50getColorWaAFU9c12 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.background_cache_hint_selector_material_light);
        long m50getColorWaAFU9c13 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.background_device_default_dark);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.background_device_default_light);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.background_floating_device_default_dark);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.background_floating_device_default_light);
        long m50getColorWaAFU9c14 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.background_floating_material_dark);
        long m50getColorWaAFU9c15 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.background_floating_material_light);
        long m50getColorWaAFU9c16 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.background_holo_dark);
        long m50getColorWaAFU9c17 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.background_holo_light);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.background_leanback_dark);
        long m50getColorWaAFU9c18 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.background_leanback_light);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.background_material_dark);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.background_material_light);
        long m50getColorWaAFU9c19 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.bright_foreground_dark);
        long m50getColorWaAFU9c20 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.bright_foreground_dark_disabled);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.bright_foreground_dark_inverse);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.bright_foreground_disabled_holo_dark);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.bright_foreground_disabled_holo_light);
        long m50getColorWaAFU9c21 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.bright_foreground_holo_dark);
        long m50getColorWaAFU9c22 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.bright_foreground_holo_light);
        long m50getColorWaAFU9c23 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.bright_foreground_inverse_holo_dark);
        long m50getColorWaAFU9c24 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.bright_foreground_inverse_holo_light);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.bright_foreground_light);
        long m50getColorWaAFU9c25 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.bright_foreground_light_disabled);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.bright_foreground_light_inverse);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.btn_colored_background_material);
        long m50getColorWaAFU9c26 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.btn_colored_borderless_text_material);
        long m50getColorWaAFU9c27 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.btn_colored_text_material);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.btn_default_material_dark);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.btn_default_material_light);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.btn_watch_default_dark);
        long m50getColorWaAFU9c28 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.button_material_dark);
        long m50getColorWaAFU9c29 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.button_material_light);
        long m50getColorWaAFU9c30 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.button_normal_device_default_dark);
        long m50getColorWaAFU9c31 = colorResourceHelper.m50getColorWaAFU9c(context, R.color.car_accent);
        colorResourceHelper.m50getColorWaAFU9c(context, R.color.car_accent_dark);
        return new TonalPalette(m50getColorWaAFU9c, m50getColorWaAFU9c2, m50getColorWaAFU9c3, m50getColorWaAFU9c4, m50getColorWaAFU9c5, m50getColorWaAFU9c6, m50getColorWaAFU9c7, m50getColorWaAFU9c8, m50getColorWaAFU9c9, m50getColorWaAFU9c10, m50getColorWaAFU9c11, m50getColorWaAFU9c12, m50getColorWaAFU9c13, m50getColorWaAFU9c14, m50getColorWaAFU9c15, m50getColorWaAFU9c16, m50getColorWaAFU9c17, m50getColorWaAFU9c18, m50getColorWaAFU9c19, m50getColorWaAFU9c20, m50getColorWaAFU9c21, m50getColorWaAFU9c22, m50getColorWaAFU9c23, m50getColorWaAFU9c24, m50getColorWaAFU9c25, m50getColorWaAFU9c26, m50getColorWaAFU9c27, m50getColorWaAFU9c28, m50getColorWaAFU9c29, m50getColorWaAFU9c30, m50getColorWaAFU9c31);
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m6equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: equals-impl0$1, reason: not valid java name */
    public static final boolean m7equalsimpl0$1(int i, int i2) {
        return i == i2;
    }

    public static String fastJoinToString$default(List list, String str) {
        ResultKt.checkNotNullParameter(list, "<this>");
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "");
        int size = list.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = list.get(i2);
            i++;
            if (i > 1) {
                sb.append((CharSequence) str);
            }
            if (obj == null || (obj instanceof CharSequence)) {
                sb.append((CharSequence) obj);
            } else if (obj instanceof Character) {
                sb.append(((Character) obj).charValue());
            } else {
                sb.append((CharSequence) String.valueOf(obj));
            }
        }
        sb.append((CharSequence) "");
        String sb2 = sb.toString();
        ResultKt.checkNotNullExpressionValue(sb2, "fastJoinTo(StringBuilder…form)\n        .toString()");
        return sb2;
    }

    /* renamed from: finalConstraints-tfFHcEY, reason: not valid java name */
    public static final long m8finalConstraintstfFHcEY(long j, boolean z, int i, float f) {
        int m276getMaxWidthimpl = ((z || m7equalsimpl0$1(i, 2)) && (Constraints.WidthMask[(int) (3 & j)] & ((int) (j >> 33))) != 0) ? Constraints.m276getMaxWidthimpl(j) : Integer.MAX_VALUE;
        if (Constraints.m278getMinWidthimpl(j) != m276getMaxWidthimpl) {
            m276getMaxWidthimpl = ResultKt.coerceIn(ceilToIntPx(f), Constraints.m278getMinWidthimpl(j), m276getMaxWidthimpl);
        }
        return ResultKt.Constraints$default(m276getMaxWidthimpl, Constraints.m275getMaxHeightimpl(j), 5);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:52:0x00a2 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:57:0x005f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:58:0x005f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:61:0x00a8 */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x003d, code lost:
    
        continue;
     */
    /* JADX WARN: Type inference failed for: r0v6, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v12, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.focus.FocusTargetNode findActiveFocusNode(androidx.compose.ui.focus.FocusTargetNode r9) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.ResultKt.checkNotNullParameter(r9, r0)
            androidx.compose.ui.focus.FocusStateImpl r0 = r9.focusState
            int r0 = r0.ordinal()
            if (r0 == 0) goto Lbd
            r1 = 1
            r2 = 0
            if (r0 == r1) goto L1e
            r1 = 2
            if (r0 == r1) goto Lbd
            r9 = 3
            if (r0 != r9) goto L18
            return r2
        L18:
            androidx.startup.StartupException r9 = new androidx.startup.StartupException
            r9.<init>()
            throw r9
        L1e:
            androidx.compose.ui.Modifier$Node r9 = r9.node
            boolean r0 = r9.isAttached
            if (r0 == 0) goto Lb1
            androidx.compose.runtime.collection.MutableVector r0 = new androidx.compose.runtime.collection.MutableVector
            r3 = 16
            androidx.compose.ui.Modifier$Node[] r4 = new androidx.compose.ui.Modifier.Node[r3]
            r0.<init>()
            r0.content = r4
            r4 = 0
            r0.size = r4
            androidx.compose.ui.Modifier$Node r5 = r9.child
            if (r5 != 0) goto L3a
            androidx.compose.ui.node.Snake.access$addLayoutNodeChildren(r0, r9)
            goto L3d
        L3a:
            r0.add(r5)
        L3d:
            boolean r9 = r0.isNotEmpty()
            if (r9 == 0) goto Lb0
            int r9 = r0.size
            int r9 = r9 - r1
            java.lang.Object r9 = r0.removeAt(r9)
            androidx.compose.ui.Modifier$Node r9 = (androidx.compose.ui.Modifier.Node) r9
            int r5 = r9.aggregateChildKindSet
            r5 = r5 & 1024(0x400, float:1.435E-42)
            if (r5 != 0) goto L56
            androidx.compose.ui.node.Snake.access$addLayoutNodeChildren(r0, r9)
            goto L3d
        L56:
            if (r9 == 0) goto L3d
            int r5 = r9.kindSet
            r5 = r5 & 1024(0x400, float:1.435E-42)
            if (r5 == 0) goto Lad
            r5 = r2
        L5f:
            if (r9 == 0) goto L3d
            boolean r6 = r9 instanceof androidx.compose.ui.focus.FocusTargetNode
            if (r6 == 0) goto L6e
            androidx.compose.ui.focus.FocusTargetNode r9 = (androidx.compose.ui.focus.FocusTargetNode) r9
            androidx.compose.ui.focus.FocusTargetNode r9 = findActiveFocusNode(r9)
            if (r9 == 0) goto La8
            return r9
        L6e:
            int r6 = r9.kindSet
            r6 = r6 & 1024(0x400, float:1.435E-42)
            if (r6 == 0) goto La8
            boolean r6 = r9 instanceof androidx.compose.ui.node.DelegatingNode
            if (r6 == 0) goto La8
            r6 = r9
            androidx.compose.ui.node.DelegatingNode r6 = (androidx.compose.ui.node.DelegatingNode) r6
            androidx.compose.ui.Modifier$Node r6 = r6.delegate
            r7 = r4
        L7e:
            if (r6 == 0) goto La5
            int r8 = r6.kindSet
            r8 = r8 & 1024(0x400, float:1.435E-42)
            if (r8 == 0) goto La2
            int r7 = r7 + 1
            if (r7 != r1) goto L8c
            r9 = r6
            goto La2
        L8c:
            if (r5 != 0) goto L99
            androidx.compose.runtime.collection.MutableVector r5 = new androidx.compose.runtime.collection.MutableVector
            androidx.compose.ui.Modifier$Node[] r8 = new androidx.compose.ui.Modifier.Node[r3]
            r5.<init>()
            r5.content = r8
            r5.size = r4
        L99:
            if (r9 == 0) goto L9f
            r5.add(r9)
            r9 = r2
        L9f:
            r5.add(r6)
        La2:
            androidx.compose.ui.Modifier$Node r6 = r6.child
            goto L7e
        La5:
            if (r7 != r1) goto La8
            goto L5f
        La8:
            androidx.compose.ui.Modifier$Node r9 = androidx.compose.ui.node.Snake.access$pop(r5)
            goto L5f
        Lad:
            androidx.compose.ui.Modifier$Node r9 = r9.child
            goto L56
        Lb0:
            return r2
        Lb1:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "visitChildren called on an unattached node"
            java.lang.String r0 = r0.toString()
            r9.<init>(r0)
            throw r9
        Lbd:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: _COROUTINE._BOUNDARY.findActiveFocusNode(androidx.compose.ui.focus.FocusTargetNode):androidx.compose.ui.focus.FocusTargetNode");
    }

    /* renamed from: findBestCandidate-4WY_MpI, reason: not valid java name */
    public static final FocusTargetNode m9findBestCandidate4WY_MpI(MutableVector mutableVector, Rect rect, int i) {
        Rect translate;
        if (FocusDirection.m67equalsimpl0(i, 3)) {
            translate = rect.translate(rect.getWidth() + 1, 0.0f);
        } else if (FocusDirection.m67equalsimpl0(i, 4)) {
            translate = rect.translate(-(rect.getWidth() + 1), 0.0f);
        } else if (FocusDirection.m67equalsimpl0(i, 5)) {
            translate = rect.translate(0.0f, rect.getHeight() + 1);
        } else {
            if (!FocusDirection.m67equalsimpl0(i, 6)) {
                throw new IllegalStateException("This function should only be used for 2-D focus search".toString());
            }
            translate = rect.translate(0.0f, -(rect.getHeight() + 1));
        }
        int i2 = mutableVector.size;
        FocusTargetNode focusTargetNode = null;
        if (i2 > 0) {
            Object[] objArr = mutableVector.content;
            int i3 = 0;
            do {
                FocusTargetNode focusTargetNode2 = (FocusTargetNode) objArr[i3];
                if (isEligibleForFocusSearch(focusTargetNode2)) {
                    Rect focusRect = focusRect(focusTargetNode2);
                    if (isBetterCandidate_I7lrPNg$isCandidate(i, focusRect, rect) && (!isBetterCandidate_I7lrPNg$isCandidate(i, translate, rect) || m5beamBeatsI7lrPNg(rect, focusRect, translate, i) || (!m5beamBeatsI7lrPNg(rect, translate, focusRect, i) && isBetterCandidate_I7lrPNg$weightedDistance(i, rect, focusRect) < isBetterCandidate_I7lrPNg$weightedDistance(i, rect, translate)))) {
                        focusTargetNode = focusTargetNode2;
                        translate = focusRect;
                    }
                }
                i3++;
            } while (i3 < i2);
        }
        return focusTargetNode;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* renamed from: findChildCorrespondingToFocusEnter--OM-vw8, reason: not valid java name */
    public static final boolean m10findChildCorrespondingToFocusEnterOMvw8(FocusTargetNode focusTargetNode, int i, Function1 function1) {
        Rect rect;
        ?? obj = new Object();
        obj.content = new FocusTargetNode[16];
        obj.size = 0;
        collectAccessibleChildren(focusTargetNode, obj);
        int i2 = obj.size;
        if (i2 <= 1) {
            FocusTargetNode focusTargetNode2 = (FocusTargetNode) (i2 == 0 ? null : obj.content[0]);
            if (focusTargetNode2 != null) {
                return ((Boolean) function1.invoke(focusTargetNode2)).booleanValue();
            }
            return false;
        }
        if (FocusDirection.m67equalsimpl0(i, 7)) {
            i = 4;
        }
        if (FocusDirection.m67equalsimpl0(i, 4) || FocusDirection.m67equalsimpl0(i, 6)) {
            Rect focusRect = focusRect(focusTargetNode);
            float f = focusRect.top;
            float f2 = focusRect.left;
            rect = new Rect(f2, f, f2, f);
        } else {
            if (!FocusDirection.m67equalsimpl0(i, 3) && !FocusDirection.m67equalsimpl0(i, 5)) {
                throw new IllegalStateException("This function should only be used for 2-D focus search".toString());
            }
            Rect focusRect2 = focusRect(focusTargetNode);
            float f3 = focusRect2.bottom;
            float f4 = focusRect2.right;
            rect = new Rect(f4, f3, f4, f3);
        }
        FocusTargetNode m9findBestCandidate4WY_MpI = m9findBestCandidate4WY_MpI(obj, rect, i);
        if (m9findBestCandidate4WY_MpI != null) {
            return ((Boolean) function1.invoke(m9findBestCandidate4WY_MpI)).booleanValue();
        }
        return false;
    }

    public static final LayoutNode findClosestParentNode(LayoutNode layoutNode, SemanticsNode$parent$1 semanticsNode$parent$1) {
        ResultKt.checkNotNullParameter(layoutNode, "<this>");
        for (LayoutNode parent$ui_release = layoutNode.getParent$ui_release(); parent$ui_release != null; parent$ui_release = parent$ui_release.getParent$ui_release()) {
            if (((Boolean) semanticsNode$parent$1.invoke((Object) parent$ui_release)).booleanValue()) {
                return parent$ui_release;
            }
        }
        return null;
    }

    public static final int findParagraphByIndex(int i, List list) {
        ResultKt.checkNotNullParameter(list, "paragraphInfoList");
        int size = list.size() - 1;
        int i2 = 0;
        while (i2 <= size) {
            int i3 = (i2 + size) >>> 1;
            ParagraphInfo paragraphInfo = (ParagraphInfo) list.get(i3);
            char c = paragraphInfo.startIndex > i ? (char) 1 : paragraphInfo.endIndex <= i ? (char) 65535 : (char) 0;
            if (c < 0) {
                i2 = i3 + 1;
            } else {
                if (c <= 0) {
                    return i3;
                }
                size = i3 - 1;
            }
        }
        return -(i2 + 1);
    }

    public static final int findParagraphByLineIndex(int i, List list) {
        ResultKt.checkNotNullParameter(list, "paragraphInfoList");
        int size = list.size() - 1;
        int i2 = 0;
        while (i2 <= size) {
            int i3 = (i2 + size) >>> 1;
            ParagraphInfo paragraphInfo = (ParagraphInfo) list.get(i3);
            char c = paragraphInfo.startLineIndex > i ? (char) 1 : paragraphInfo.endLineIndex <= i ? (char) 65535 : (char) 0;
            if (c < 0) {
                i2 = i3 + 1;
            } else {
                if (c <= 0) {
                    return i3;
                }
                size = i3 - 1;
            }
        }
        return -(i2 + 1);
    }

    public static final int findParagraphByY(List list, float f) {
        ResultKt.checkNotNullParameter(list, "paragraphInfoList");
        int size = list.size() - 1;
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) >>> 1;
            ParagraphInfo paragraphInfo = (ParagraphInfo) list.get(i2);
            char c = paragraphInfo.top > f ? (char) 1 : paragraphInfo.bottom <= f ? (char) 65535 : (char) 0;
            if (c < 0) {
                i = i2 + 1;
            } else {
                if (c <= 0) {
                    return i2;
                }
                size = i2 - 1;
            }
        }
        return -(i + 1);
    }

    public static final LayoutCoordinates findRootCoordinates(NodeCoordinator nodeCoordinator) {
        NodeCoordinator nodeCoordinator2;
        LayoutCoordinates parentLayoutCoordinates = nodeCoordinator.getParentLayoutCoordinates();
        while (true) {
            LayoutCoordinates layoutCoordinates = parentLayoutCoordinates;
            nodeCoordinator2 = nodeCoordinator;
            nodeCoordinator = layoutCoordinates;
            if (nodeCoordinator == null) {
                break;
            }
            parentLayoutCoordinates = nodeCoordinator.getParentLayoutCoordinates();
        }
        NodeCoordinator nodeCoordinator3 = nodeCoordinator2 instanceof NodeCoordinator ? nodeCoordinator2 : null;
        if (nodeCoordinator3 == null) {
            return nodeCoordinator2;
        }
        NodeCoordinator nodeCoordinator4 = nodeCoordinator3.wrappedBy;
        while (true) {
            NodeCoordinator nodeCoordinator5 = nodeCoordinator4;
            NodeCoordinator nodeCoordinator6 = nodeCoordinator3;
            nodeCoordinator3 = nodeCoordinator5;
            if (nodeCoordinator3 == null) {
                return nodeCoordinator6;
            }
            nodeCoordinator4 = nodeCoordinator3.wrappedBy;
        }
    }

    public static final Rect focusRect(FocusTargetNode focusTargetNode) {
        ResultKt.checkNotNullParameter(focusTargetNode, "<this>");
        NodeCoordinator nodeCoordinator = focusTargetNode.coordinator;
        return nodeCoordinator != null ? findRootCoordinates(nodeCoordinator).localBoundingBoxOf(nodeCoordinator, false) : Rect.Zero;
    }

    public static final boolean forwardFocusSearch(FocusTargetNode focusTargetNode, Function1 function1) {
        int ordinal = focusTargetNode.focusState.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                FocusTargetNode activeChild = getActiveChild(focusTargetNode);
                if (activeChild != null) {
                    return forwardFocusSearch(activeChild, function1) || m11generateAndSearchChildren4C6V_qg(focusTargetNode, activeChild, 1, function1);
                }
                throw new IllegalStateException("ActiveParent must have a focusedChild".toString());
            }
            if (ordinal != 2) {
                if (ordinal == 3) {
                    return focusTargetNode.fetchFocusProperties$ui_release().canFocus ? ((Boolean) function1.invoke(focusTargetNode)).booleanValue() : pickChildForForwardSearch(focusTargetNode, function1);
                }
                throw new RuntimeException();
            }
        }
        return pickChildForForwardSearch(focusTargetNode, function1);
    }

    /* renamed from: generateAndSearchChildren-4C6V_qg, reason: not valid java name */
    public static final boolean m11generateAndSearchChildren4C6V_qg(FocusTargetNode focusTargetNode, FocusTargetNode focusTargetNode2, int i, Function1 function1) {
        if (m19searchChildren4C6V_qg(focusTargetNode, focusTargetNode2, i, function1)) {
            return true;
        }
        ResultKt.m298searchBeyondBoundsOMvw8(focusTargetNode, new OneDimensionalFocusSearchKt$generateAndSearchChildren$1(focusTargetNode, focusTargetNode2, i, function1, 0));
        return false;
    }

    /* renamed from: generateAndSearchChildren-4C6V_qg$1, reason: not valid java name */
    public static final boolean m12generateAndSearchChildren4C6V_qg$1(FocusTargetNode focusTargetNode, FocusTargetNode focusTargetNode2, int i, Function1 function1) {
        if (m20searchChildren4C6V_qg$1(focusTargetNode, focusTargetNode2, i, function1)) {
            return true;
        }
        ResultKt.m298searchBeyondBoundsOMvw8(focusTargetNode, new OneDimensionalFocusSearchKt$generateAndSearchChildren$1(focusTargetNode, focusTargetNode2, i, function1, 1));
        return false;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:43:0x0096 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:48:0x004b */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:49:0x004b */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:52:0x009c */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0028, code lost:
    
        continue;
     */
    /* JADX WARN: Type inference failed for: r0v4, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v11, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.focus.FocusTargetNode getActiveChild(androidx.compose.ui.focus.FocusTargetNode r9) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.ResultKt.checkNotNullParameter(r9, r0)
            androidx.compose.ui.Modifier$Node r9 = r9.node
            boolean r0 = r9.isAttached
            r1 = 0
            if (r0 != 0) goto Ld
            return r1
        Ld:
            if (r0 == 0) goto La5
            androidx.compose.runtime.collection.MutableVector r0 = new androidx.compose.runtime.collection.MutableVector
            r2 = 16
            androidx.compose.ui.Modifier$Node[] r3 = new androidx.compose.ui.Modifier.Node[r2]
            r0.<init>()
            r0.content = r3
            r3 = 0
            r0.size = r3
            androidx.compose.ui.Modifier$Node r4 = r9.child
            if (r4 != 0) goto L25
            androidx.compose.ui.node.Snake.access$addLayoutNodeChildren(r0, r9)
            goto L28
        L25:
            r0.add(r4)
        L28:
            boolean r9 = r0.isNotEmpty()
            if (r9 == 0) goto La4
            int r9 = r0.size
            r4 = 1
            int r9 = r9 - r4
            java.lang.Object r9 = r0.removeAt(r9)
            androidx.compose.ui.Modifier$Node r9 = (androidx.compose.ui.Modifier.Node) r9
            int r5 = r9.aggregateChildKindSet
            r5 = r5 & 1024(0x400, float:1.435E-42)
            if (r5 != 0) goto L42
            androidx.compose.ui.node.Snake.access$addLayoutNodeChildren(r0, r9)
            goto L28
        L42:
            if (r9 == 0) goto L28
            int r5 = r9.kindSet
            r5 = r5 & 1024(0x400, float:1.435E-42)
            if (r5 == 0) goto La1
            r5 = r1
        L4b:
            if (r9 == 0) goto L28
            boolean r6 = r9 instanceof androidx.compose.ui.focus.FocusTargetNode
            if (r6 == 0) goto L62
            androidx.compose.ui.focus.FocusTargetNode r9 = (androidx.compose.ui.focus.FocusTargetNode) r9
            androidx.compose.ui.focus.FocusStateImpl r6 = r9.focusState
            int r6 = r6.ordinal()
            if (r6 == 0) goto L61
            if (r6 == r4) goto L61
            r7 = 2
            if (r6 == r7) goto L61
            goto L9c
        L61:
            return r9
        L62:
            int r6 = r9.kindSet
            r6 = r6 & 1024(0x400, float:1.435E-42)
            if (r6 == 0) goto L9c
            boolean r6 = r9 instanceof androidx.compose.ui.node.DelegatingNode
            if (r6 == 0) goto L9c
            r6 = r9
            androidx.compose.ui.node.DelegatingNode r6 = (androidx.compose.ui.node.DelegatingNode) r6
            androidx.compose.ui.Modifier$Node r6 = r6.delegate
            r7 = r3
        L72:
            if (r6 == 0) goto L99
            int r8 = r6.kindSet
            r8 = r8 & 1024(0x400, float:1.435E-42)
            if (r8 == 0) goto L96
            int r7 = r7 + 1
            if (r7 != r4) goto L80
            r9 = r6
            goto L96
        L80:
            if (r5 != 0) goto L8d
            androidx.compose.runtime.collection.MutableVector r5 = new androidx.compose.runtime.collection.MutableVector
            androidx.compose.ui.Modifier$Node[] r8 = new androidx.compose.ui.Modifier.Node[r2]
            r5.<init>()
            r5.content = r8
            r5.size = r3
        L8d:
            if (r9 == 0) goto L93
            r5.add(r9)
            r9 = r1
        L93:
            r5.add(r6)
        L96:
            androidx.compose.ui.Modifier$Node r6 = r6.child
            goto L72
        L99:
            if (r7 != r4) goto L9c
            goto L4b
        L9c:
            androidx.compose.ui.Modifier$Node r9 = androidx.compose.ui.node.Snake.access$pop(r5)
            goto L4b
        La1:
            androidx.compose.ui.Modifier$Node r9 = r9.child
            goto L42
        La4:
            return r1
        La5:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "visitChildren called on an unattached node"
            java.lang.String r0 = r0.toString()
            r9.<init>(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: _COROUTINE._BOUNDARY.getActiveChild(androidx.compose.ui.focus.FocusTargetNode):androidx.compose.ui.focus.FocusTargetNode");
    }

    public static final android.graphics.Rect getCharSequenceBounds(TextPaint textPaint, CharSequence charSequence, int i, int i2) {
        int i3 = i;
        if (charSequence instanceof Spanned) {
            Spanned spanned = (Spanned) charSequence;
            if (spanned.nextSpanTransition(i3 - 1, i2, MetricAffectingSpan.class) != i2) {
                android.graphics.Rect rect = new android.graphics.Rect();
                android.graphics.Rect rect2 = new android.graphics.Rect();
                TextPaint textPaint2 = new TextPaint();
                while (i3 < i2) {
                    int nextSpanTransition = spanned.nextSpanTransition(i3, i2, MetricAffectingSpan.class);
                    MetricAffectingSpan[] metricAffectingSpanArr = (MetricAffectingSpan[]) spanned.getSpans(i3, nextSpanTransition, MetricAffectingSpan.class);
                    textPaint2.set(textPaint);
                    ResultKt.checkNotNullExpressionValue(metricAffectingSpanArr, "spans");
                    for (MetricAffectingSpan metricAffectingSpan : metricAffectingSpanArr) {
                        if (spanned.getSpanStart(metricAffectingSpan) != spanned.getSpanEnd(metricAffectingSpan)) {
                            metricAffectingSpan.updateMeasureState(textPaint2);
                        }
                    }
                    if (Build.VERSION.SDK_INT >= 29) {
                        Paint29.getTextBounds(textPaint2, charSequence, i3, nextSpanTransition, rect2);
                    } else {
                        textPaint2.getTextBounds(charSequence.toString(), i3, nextSpanTransition, rect2);
                    }
                    rect.right = rect2.width() + rect.right;
                    rect.top = Math.min(rect.top, rect2.top);
                    rect.bottom = Math.max(rect.bottom, rect2.bottom);
                    i3 = nextSpanTransition;
                }
                return rect;
            }
        }
        android.graphics.Rect rect3 = new android.graphics.Rect();
        if (Build.VERSION.SDK_INT >= 29) {
            Paint29.getTextBounds(textPaint, charSequence, i3, i2, rect3);
        } else {
            textPaint.getTextBounds(charSequence.toString(), i3, i2, rect3);
        }
        return rect3;
    }

    public static final float getEllipsizedLeftPadding(Layout layout, int i, Paint paint) {
        float abs;
        float width;
        ResultKt.checkNotNullParameter(layout, "<this>");
        ResultKt.checkNotNullParameter(paint, "paint");
        float lineLeft = layout.getLineLeft(i);
        TextAndroidCanvas textAndroidCanvas = TextLayoutKt.SharedTextAndroidCanvas;
        if (!(layout.getEllipsisCount(i) > 0) || layout.getParagraphDirection(i) != 1 || lineLeft >= 0.0f) {
            return 0.0f;
        }
        float measureText = paint.measureText("…") + (layout.getPrimaryHorizontal(layout.getEllipsisStart(i) + layout.getLineStart(i)) - lineLeft);
        Layout.Alignment paragraphAlignment = layout.getParagraphAlignment(i);
        if (paragraphAlignment != null && IndentationFixSpanKt$WhenMappings.$EnumSwitchMapping$0[paragraphAlignment.ordinal()] == 1) {
            abs = Math.abs(lineLeft);
            width = (layout.getWidth() - measureText) / 2.0f;
        } else {
            abs = Math.abs(lineLeft);
            width = layout.getWidth() - measureText;
        }
        return width + abs;
    }

    public static final float getEllipsizedRightPadding(Layout layout, int i, Paint paint) {
        float width;
        float width2;
        ResultKt.checkNotNullParameter(layout, "<this>");
        ResultKt.checkNotNullParameter(paint, "paint");
        TextAndroidCanvas textAndroidCanvas = TextLayoutKt.SharedTextAndroidCanvas;
        if (layout.getEllipsisCount(i) <= 0 || layout.getParagraphDirection(i) != -1 || layout.getWidth() >= layout.getLineRight(i)) {
            return 0.0f;
        }
        float measureText = paint.measureText("…") + (layout.getLineRight(i) - layout.getPrimaryHorizontal(layout.getEllipsisStart(i) + layout.getLineStart(i)));
        Layout.Alignment paragraphAlignment = layout.getParagraphAlignment(i);
        if (paragraphAlignment != null && IndentationFixSpanKt$WhenMappings.$EnumSwitchMapping$0[paragraphAlignment.ordinal()] == 1) {
            width = layout.getWidth() - layout.getLineRight(i);
            width2 = (layout.getWidth() - measureText) / 2.0f;
        } else {
            width = layout.getWidth() - layout.getLineRight(i);
            width2 = layout.getWidth() - measureText;
        }
        return width - width2;
    }

    public static Set getExclusions() {
        try {
            Object invoke = Class.forName("android.text.EmojiConsistency").getMethod("getEmojiConsistencySet", new Class[0]).invoke(null, new Object[0]);
            if (invoke == null) {
                return Collections.emptySet();
            }
            Set set = (Set) invoke;
            Iterator it = set.iterator();
            while (it.hasNext()) {
                if (!(it.next() instanceof int[])) {
                    return Collections.emptySet();
                }
            }
            return set;
        } catch (Throwable unused) {
            return Collections.emptySet();
        }
    }

    public static final String getHexAddress(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static final Object getOrNull(SemanticsConfiguration semanticsConfiguration, SemanticsPropertyKey semanticsPropertyKey) {
        ResultKt.checkNotNullParameter(semanticsConfiguration, "<this>");
        ResultKt.checkNotNullParameter(semanticsPropertyKey, "key");
        Object obj = semanticsConfiguration.props.get(semanticsPropertyKey);
        if (obj == null) {
            return null;
        }
        return obj;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:30:0x0063 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:35:0x001a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:36:0x001a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:39:0x0069 */
    /* JADX WARN: Type inference failed for: r2v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public static final SemanticsModifierNode getOuterMergingSemantics(LayoutNode layoutNode) {
        ResultKt.checkNotNullParameter(layoutNode, "<this>");
        Modifier.Node node = layoutNode.nodes.head;
        Object obj = null;
        if ((node.aggregateChildKindSet & 8) != 0) {
            loop0: while (true) {
                if (node != null) {
                    if ((node.kindSet & 8) != 0) {
                        Modifier.Node node2 = node;
                        MutableVector mutableVector = null;
                        while (node2 != null) {
                            if (node2 instanceof SemanticsModifierNode) {
                                if (((SemanticsModifierNode) node2).getShouldMergeDescendantSemantics()) {
                                    obj = node2;
                                    break loop0;
                                }
                            } else if ((node2.kindSet & 8) != 0 && (node2 instanceof DelegatingNode)) {
                                Modifier.Node node3 = ((DelegatingNode) node2).delegate;
                                int i = 0;
                                mutableVector = mutableVector;
                                while (node3 != null) {
                                    if ((node3.kindSet & 8) != 0) {
                                        i++;
                                        mutableVector = mutableVector;
                                        if (i == 1) {
                                            node2 = node3;
                                        } else {
                                            if (mutableVector == null) {
                                                ?? obj2 = new Object();
                                                obj2.content = new Modifier.Node[16];
                                                obj2.size = 0;
                                                mutableVector = obj2;
                                            }
                                            if (node2 != null) {
                                                mutableVector.add(node2);
                                                node2 = null;
                                            }
                                            mutableVector.add(node3);
                                        }
                                    }
                                    node3 = node3.child;
                                    mutableVector = mutableVector;
                                }
                                if (i == 1) {
                                }
                            }
                            node2 = Snake.access$pop(mutableVector);
                        }
                    }
                    if ((node.aggregateChildKindSet & 8) == 0) {
                        break;
                    }
                    node = node.child;
                } else {
                    break;
                }
            }
        }
        return (SemanticsModifierNode) obj;
    }

    public static final PoolingContainerListenerHolder getPoolingContainerListenerHolder(View view) {
        PoolingContainerListenerHolder poolingContainerListenerHolder = (PoolingContainerListenerHolder) view.getTag(eu.malek.composesample.R.id.pooling_container_listener_holder_tag);
        if (poolingContainerListenerHolder != null) {
            return poolingContainerListenerHolder;
        }
        PoolingContainerListenerHolder poolingContainerListenerHolder2 = new PoolingContainerListenerHolder();
        view.setTag(eu.malek.composesample.R.id.pooling_container_listener_holder_tag, poolingContainerListenerHolder2);
        return poolingContainerListenerHolder2;
    }

    public static final LookaheadDelegate getRootLookaheadDelegate(LookaheadDelegate lookaheadDelegate) {
        ResultKt.checkNotNullParameter(lookaheadDelegate, "<this>");
        LayoutNode layoutNode = lookaheadDelegate.coordinator.layoutNode;
        while (true) {
            LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
            LayoutNode layoutNode2 = null;
            if ((parent$ui_release != null ? parent$ui_release.lookaheadRoot : null) == null) {
                LookaheadDelegate lookaheadDelegate2 = layoutNode.nodes.outerCoordinator.getLookaheadDelegate();
                ResultKt.checkNotNull(lookaheadDelegate2);
                return lookaheadDelegate2;
            }
            LayoutNode parent$ui_release2 = layoutNode.getParent$ui_release();
            if (parent$ui_release2 != null) {
                layoutNode2 = parent$ui_release2.lookaheadRoot;
            }
            ResultKt.checkNotNull(layoutNode2);
            LayoutNode parent$ui_release3 = layoutNode.getParent$ui_release();
            ResultKt.checkNotNull(parent$ui_release3);
            layoutNode = parent$ui_release3.lookaheadRoot;
            ResultKt.checkNotNull(layoutNode);
        }
    }

    public static final void grantFocus(FocusTargetNode focusTargetNode) {
        Snake.observeReads(focusTargetNode, new LayoutNode$_foldedChildren$1(7, focusTargetNode));
        int ordinal = focusTargetNode.focusState.ordinal();
        if (ordinal == 1 || ordinal == 3) {
            focusTargetNode.focusState = FocusStateImpl.Active;
        }
    }

    public static final boolean hasFontAttributes(SpanStyle spanStyle) {
        ResultKt.checkNotNullParameter(spanStyle, "<this>");
        return (spanStyle.fontFamily == null && spanStyle.fontStyle == null && spanStyle.fontWeight == null) ? false : true;
    }

    public static final void invalidApplier() {
        throw new IllegalStateException("Invalid applier".toString());
    }

    public static final float[] inverse3x3(float[] fArr) {
        ResultKt.checkNotNullParameter(fArr, "m");
        float f = fArr[0];
        float f2 = fArr[3];
        float f3 = fArr[6];
        float f4 = fArr[1];
        float f5 = fArr[4];
        float f6 = fArr[7];
        float f7 = fArr[2];
        float f8 = fArr[5];
        float f9 = fArr[8];
        float f10 = (f5 * f9) - (f6 * f8);
        float f11 = (f6 * f7) - (f4 * f9);
        float f12 = (f4 * f8) - (f5 * f7);
        float f13 = (f3 * f12) + (f2 * f11) + (f * f10);
        float[] fArr2 = new float[fArr.length];
        fArr2[0] = f10 / f13;
        fArr2[1] = f11 / f13;
        fArr2[2] = f12 / f13;
        fArr2[3] = ((f3 * f8) - (f2 * f9)) / f13;
        fArr2[4] = ((f9 * f) - (f3 * f7)) / f13;
        fArr2[5] = ((f7 * f2) - (f8 * f)) / f13;
        fArr2[6] = ((f2 * f6) - (f3 * f5)) / f13;
        fArr2[7] = ((f3 * f4) - (f6 * f)) / f13;
        fArr2[8] = ((f * f5) - (f2 * f4)) / f13;
        return fArr2;
    }

    public static final void invokeComposable(Composer composer, Function2 function2) {
        ResultKt.checkNotNullParameter(composer, "composer");
        ResultKt.checkNotNullParameter(function2, "composable");
        ResultKt.beforeCheckcastToFunctionOfArity(2, function2);
        function2.invoke(composer, 1);
    }

    public static final boolean isBetterCandidate_I7lrPNg$isCandidate(int i, Rect rect, Rect rect2) {
        boolean m67equalsimpl0 = FocusDirection.m67equalsimpl0(i, 3);
        float f = rect.left;
        float f2 = rect.right;
        float f3 = rect2.left;
        float f4 = rect2.right;
        if (m67equalsimpl0) {
            if ((f4 <= f2 && f3 < f2) || f3 <= f) {
                return false;
            }
        } else if (!FocusDirection.m67equalsimpl0(i, 4)) {
            boolean m67equalsimpl02 = FocusDirection.m67equalsimpl0(i, 5);
            float f5 = rect.top;
            float f6 = rect.bottom;
            float f7 = rect2.top;
            float f8 = rect2.bottom;
            if (m67equalsimpl02) {
                if ((f8 <= f6 && f7 < f6) || f7 <= f5) {
                    return false;
                }
            } else {
                if (!FocusDirection.m67equalsimpl0(i, 6)) {
                    throw new IllegalStateException("This function should only be used for 2-D focus search".toString());
                }
                if ((f7 >= f5 && f8 > f5) || f8 >= f6) {
                    return false;
                }
            }
        } else if ((f3 >= f && f4 > f) || f4 >= f2) {
            return false;
        }
        return true;
    }

    public static final long isBetterCandidate_I7lrPNg$weightedDistance(int i, Rect rect, Rect rect2) {
        float f;
        float f2;
        float height;
        boolean m67equalsimpl0 = FocusDirection.m67equalsimpl0(i, 3);
        float f3 = rect.top;
        float f4 = rect.left;
        float f5 = rect2.top;
        float f6 = rect2.left;
        if (m67equalsimpl0) {
            f = f4 - rect2.right;
        } else if (FocusDirection.m67equalsimpl0(i, 4)) {
            f = f6 - rect.right;
        } else if (FocusDirection.m67equalsimpl0(i, 5)) {
            f = f3 - rect2.bottom;
        } else {
            if (!FocusDirection.m67equalsimpl0(i, 6)) {
                throw new IllegalStateException("This function should only be used for 2-D focus search".toString());
            }
            f = f5 - rect.bottom;
        }
        long abs = Math.abs(Math.max(0.0f, f));
        if (FocusDirection.m67equalsimpl0(i, 3) || FocusDirection.m67equalsimpl0(i, 4)) {
            float height2 = rect.getHeight();
            float f7 = 2;
            f2 = (height2 / f7) + f3;
            height = (rect2.getHeight() / f7) + f5;
        } else {
            if (!FocusDirection.m67equalsimpl0(i, 5) && !FocusDirection.m67equalsimpl0(i, 6)) {
                throw new IllegalStateException("This function should only be used for 2-D focus search".toString());
            }
            float width = rect.getWidth();
            float f8 = 2;
            f2 = (width / f8) + f4;
            height = (rect2.getWidth() / f8) + f6;
        }
        long abs2 = Math.abs(f2 - height);
        return (abs2 * abs2) + (13 * abs * abs);
    }

    public static final boolean isEligibleForFocusSearch(FocusTargetNode focusTargetNode) {
        LayoutNode layoutNode;
        NodeCoordinator nodeCoordinator;
        LayoutNode layoutNode2;
        ResultKt.checkNotNullParameter(focusTargetNode, "<this>");
        NodeCoordinator nodeCoordinator2 = focusTargetNode.coordinator;
        return (nodeCoordinator2 == null || (layoutNode = nodeCoordinator2.layoutNode) == null || !layoutNode.isPlaced() || (nodeCoordinator = focusTargetNode.coordinator) == null || (layoutNode2 = nodeCoordinator.layoutNode) == null || !layoutNode2.isAttached()) ? false : true;
    }

    /* renamed from: isOutOfBounds-O0kMr_c, reason: not valid java name */
    public static final boolean m13isOutOfBoundsO0kMr_c(PointerInputChange pointerInputChange, long j) {
        long j2 = pointerInputChange.position;
        float m76getXimpl = Offset.m76getXimpl(j2);
        float m77getYimpl = Offset.m77getYimpl(j2);
        return m76getXimpl < 0.0f || m76getXimpl > ((float) ((int) (j >> 32))) || m77getYimpl < 0.0f || m77getYimpl > ((float) ((int) (j & 4294967295L)));
    }

    /* renamed from: isOutOfBounds-jwHxaWs, reason: not valid java name */
    public static final boolean m14isOutOfBoundsjwHxaWs(PointerInputChange pointerInputChange, long j, long j2) {
        if (pointerInputChange.type != 1) {
            return m13isOutOfBoundsO0kMr_c(pointerInputChange, j);
        }
        long j3 = pointerInputChange.position;
        float m76getXimpl = Offset.m76getXimpl(j3);
        float m77getYimpl = Offset.m77getYimpl(j3);
        return m76getXimpl < (-Size.m85getWidthimpl(j2)) || m76getXimpl > Size.m85getWidthimpl(j2) + ((float) ((int) (j >> 32))) || m77getYimpl < (-Size.m83getHeightimpl(j2)) || m77getYimpl > Size.m83getHeightimpl(j2) + ((float) ((int) (j & 4294967295L)));
    }

    public static final boolean isSimple(RoundRect roundRect) {
        ResultKt.checkNotNullParameter(roundRect, "<this>");
        long j = roundRect.topLeftCornerRadius;
        if (CornerRadius.m72getXimpl(j) == CornerRadius.m73getYimpl(j)) {
            float m72getXimpl = CornerRadius.m72getXimpl(j);
            long j2 = roundRect.topRightCornerRadius;
            if (m72getXimpl == CornerRadius.m72getXimpl(j2) && CornerRadius.m72getXimpl(j) == CornerRadius.m73getYimpl(j2)) {
                float m72getXimpl2 = CornerRadius.m72getXimpl(j);
                long j3 = roundRect.bottomRightCornerRadius;
                if (m72getXimpl2 == CornerRadius.m72getXimpl(j3) && CornerRadius.m72getXimpl(j) == CornerRadius.m73getYimpl(j3)) {
                    float m72getXimpl3 = CornerRadius.m72getXimpl(j);
                    long j4 = roundRect.bottomLeftCornerRadius;
                    if (m72getXimpl3 == CornerRadius.m72getXimpl(j4) && CornerRadius.m72getXimpl(j) == CornerRadius.m73getYimpl(j4)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static final Modifier materializeModifier(Composer composer, Modifier modifier) {
        ResultKt.checkNotNullParameter(composer, "<this>");
        ResultKt.checkNotNullParameter(modifier, "modifier");
        if (modifier.all(ComposedModifierKt$materialize$1.INSTANCE)) {
            return modifier;
        }
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(1219399079);
        Modifier modifier2 = (Modifier) modifier.foldIn(Modifier.Companion.$$INSTANCE, new ComposedModifierKt$materialize$result$1(0, composer));
        composerImpl.end(false);
        return modifier2;
    }

    public static final ComposableLambdaImpl materializerOf(Modifier modifier) {
        ResultKt.checkNotNullParameter(modifier, "modifier");
        return ResultKt.composableLambdaInstance(-55743822, new LayoutKt$materializerOf$1(modifier, 1), true);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.ui.modifier.ProvidableModifierLocal, androidx.compose.ui.modifier.ModifierLocal] */
    public static final ProvidableModifierLocal modifierLocalOf(Function0 function0) {
        return new ModifierLocal(function0);
    }

    public static final float[] mul3x3(float[] fArr, float[] fArr2) {
        ResultKt.checkNotNullParameter(fArr, "lhs");
        ResultKt.checkNotNullParameter(fArr2, "rhs");
        float f = fArr[0];
        float f2 = fArr2[0];
        float f3 = fArr[3];
        float f4 = fArr2[1];
        float f5 = fArr[6];
        float f6 = fArr2[2];
        float f7 = (f5 * f6) + (f3 * f4) + (f * f2);
        float f8 = fArr[1];
        float f9 = fArr[4];
        float f10 = fArr[7];
        float f11 = (f10 * f6) + (f9 * f4) + (f8 * f2);
        float f12 = fArr[2];
        float f13 = fArr[5];
        float f14 = fArr[8];
        float f15 = (f6 * f14) + (f4 * f13) + (f2 * f12);
        float f16 = fArr2[3];
        float f17 = fArr2[4];
        float f18 = fArr2[5];
        float f19 = (f5 * f18) + (f3 * f17) + (f * f16);
        float f20 = (f10 * f18) + (f9 * f17) + (f8 * f16);
        float f21 = (f18 * f14) + (f17 * f13) + (f16 * f12);
        float f22 = fArr2[6];
        float f23 = fArr2[7];
        float f24 = fArr2[8];
        return new float[]{f7, f11, f15, f19, f20, f21, (f5 * f24) + (f3 * f23) + (f * f22), (f10 * f24) + (f9 * f23) + (f8 * f22), (f14 * f24) + (f13 * f23) + (f12 * f22)};
    }

    public static final float[] mul3x3Diag(float[] fArr, float[] fArr2) {
        ResultKt.checkNotNullParameter(fArr2, "rhs");
        float f = fArr[0];
        float f2 = fArr2[0] * f;
        float f3 = fArr[1];
        float f4 = fArr2[1] * f3;
        float f5 = fArr[2];
        return new float[]{f2, f4, fArr2[2] * f5, fArr2[3] * f, fArr2[4] * f3, fArr2[5] * f5, f * fArr2[6], f3 * fArr2[7], f5 * fArr2[8]};
    }

    public static final void mul3x3Float3(float[] fArr, float[] fArr2) {
        ResultKt.checkNotNullParameter(fArr, "lhs");
        float f = fArr2[0];
        float f2 = fArr2[1];
        float f3 = fArr2[2];
        fArr2[0] = (fArr[6] * f3) + (fArr[3] * f2) + (fArr[0] * f);
        fArr2[1] = (fArr[7] * f3) + (fArr[4] * f2) + (fArr[1] * f);
        fArr2[2] = (fArr[8] * f3) + (fArr[5] * f2) + (fArr[2] * f);
    }

    public static final float mul3x3Float3_0(float[] fArr, float f, float f2, float f3) {
        ResultKt.checkNotNullParameter(fArr, "lhs");
        return (fArr[6] * f3) + (fArr[3] * f2) + (fArr[0] * f);
    }

    public static final float mul3x3Float3_1(float[] fArr, float f, float f2, float f3) {
        ResultKt.checkNotNullParameter(fArr, "lhs");
        return (fArr[7] * f3) + (fArr[4] * f2) + (fArr[1] * f);
    }

    public static final float mul3x3Float3_2(float[] fArr, float f, float f2, float f3) {
        ResultKt.checkNotNullParameter(fArr, "lhs");
        return (fArr[8] * f3) + (fArr[5] * f2) + (fArr[2] * f);
    }

    /* renamed from: performCustomClearFocus-Mxy_nc0, reason: not valid java name */
    public static final int m15performCustomClearFocusMxy_nc0(FocusTargetNode focusTargetNode, int i) {
        ResultKt.checkNotNullParameter(focusTargetNode, "$this$performCustomClearFocus");
        int ordinal = focusTargetNode.focusState.ordinal();
        int i2 = 1;
        if (ordinal == 0) {
            return 1;
        }
        if (ordinal == 1) {
            FocusTargetNode activeChild = getActiveChild(focusTargetNode);
            if (activeChild == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            int m15performCustomClearFocusMxy_nc0 = m15performCustomClearFocusMxy_nc0(activeChild, i);
            if (m15performCustomClearFocusMxy_nc0 == 1) {
                m15performCustomClearFocusMxy_nc0 = 0;
            }
            if (m15performCustomClearFocusMxy_nc0 != 0) {
                return m15performCustomClearFocusMxy_nc0;
            }
            if (focusTargetNode.isProcessingCustomExit) {
                return 1;
            }
            focusTargetNode.isProcessingCustomExit = true;
            try {
                FocusRequester focusRequester = (FocusRequester) focusTargetNode.fetchFocusProperties$ui_release().exit.invoke(new FocusDirection(i));
                if (focusRequester != FocusRequester.Default) {
                    if (focusRequester != FocusRequester.Cancel) {
                        i2 = focusRequester.focus$ui_release() ? 3 : 4;
                    }
                }
                return i2;
            } finally {
                focusTargetNode.isProcessingCustomExit = false;
            }
        }
        if (ordinal != 2) {
            if (ordinal == 3) {
                return 1;
            }
            throw new RuntimeException();
        }
        return 2;
    }

    /* renamed from: performCustomEnter-Mxy_nc0, reason: not valid java name */
    public static final int m16performCustomEnterMxy_nc0(FocusTargetNode focusTargetNode, int i) {
        if (!focusTargetNode.isProcessingCustomEnter) {
            focusTargetNode.isProcessingCustomEnter = true;
            try {
                FocusRequester focusRequester = (FocusRequester) focusTargetNode.fetchFocusProperties$ui_release().enter.invoke(new FocusDirection(i));
                if (focusRequester != FocusRequester.Default) {
                    if (focusRequester != FocusRequester.Cancel) {
                        return focusRequester.focus$ui_release() ? 3 : 4;
                    }
                    focusTargetNode.isProcessingCustomEnter = false;
                    return 2;
                }
            } finally {
                focusTargetNode.isProcessingCustomEnter = false;
            }
        }
        return 1;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:41:0x0078 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:46:0x003a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:47:0x003a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:50:0x007e */
    /* JADX WARN: Type inference failed for: r7v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* renamed from: performCustomRequestFocus-Mxy_nc0, reason: not valid java name */
    public static final int m17performCustomRequestFocusMxy_nc0(FocusTargetNode focusTargetNode, int i) {
        Modifier.Node node;
        NodeChain nodeChain;
        ResultKt.checkNotNullParameter(focusTargetNode, "$this$performCustomRequestFocus");
        int ordinal = focusTargetNode.focusState.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                FocusTargetNode activeChild = getActiveChild(focusTargetNode);
                if (activeChild != null) {
                    return m15performCustomClearFocusMxy_nc0(activeChild, i);
                }
                throw new IllegalStateException("Required value was null.".toString());
            }
            if (ordinal != 2) {
                if (ordinal != 3) {
                    throw new RuntimeException();
                }
                Modifier.Node node2 = focusTargetNode.node;
                if (!node2.isAttached) {
                    throw new IllegalStateException("visitAncestors called on an unattached node".toString());
                }
                Modifier.Node node3 = node2.parent;
                LayoutNode requireLayoutNode = Snake.requireLayoutNode(focusTargetNode);
                loop0: while (true) {
                    node = null;
                    if (requireLayoutNode == null) {
                        break;
                    }
                    if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 1024) != 0) {
                        while (node3 != null) {
                            if ((node3.kindSet & 1024) != 0) {
                                Modifier.Node node4 = node3;
                                MutableVector mutableVector = null;
                                while (node4 != null) {
                                    if (node4 instanceof FocusTargetNode) {
                                        node = node4;
                                        break loop0;
                                    }
                                    if ((node4.kindSet & 1024) != 0 && (node4 instanceof DelegatingNode)) {
                                        Modifier.Node node5 = ((DelegatingNode) node4).delegate;
                                        int i2 = 0;
                                        mutableVector = mutableVector;
                                        while (node5 != null) {
                                            if ((node5.kindSet & 1024) != 0) {
                                                i2++;
                                                mutableVector = mutableVector;
                                                if (i2 == 1) {
                                                    node4 = node5;
                                                } else {
                                                    if (mutableVector == null) {
                                                        ?? obj = new Object();
                                                        obj.content = new Modifier.Node[16];
                                                        obj.size = 0;
                                                        mutableVector = obj;
                                                    }
                                                    if (node4 != null) {
                                                        mutableVector.add(node4);
                                                        node4 = null;
                                                    }
                                                    mutableVector.add(node5);
                                                }
                                            }
                                            node5 = node5.child;
                                            mutableVector = mutableVector;
                                        }
                                        if (i2 == 1) {
                                        }
                                    }
                                    node4 = Snake.access$pop(mutableVector);
                                }
                            }
                            node3 = node3.parent;
                        }
                    }
                    requireLayoutNode = requireLayoutNode.getParent$ui_release();
                    node3 = (requireLayoutNode == null || (nodeChain = requireLayoutNode.nodes) == null) ? null : nodeChain.tail;
                }
                FocusTargetNode focusTargetNode2 = (FocusTargetNode) node;
                if (focusTargetNode2 == null) {
                    return 1;
                }
                int ordinal2 = focusTargetNode2.focusState.ordinal();
                if (ordinal2 == 0) {
                    return m16performCustomEnterMxy_nc0(focusTargetNode2, i);
                }
                if (ordinal2 == 1) {
                    return m17performCustomRequestFocusMxy_nc0(focusTargetNode2, i);
                }
                if (ordinal2 == 2) {
                    return 2;
                }
                if (ordinal2 != 3) {
                    throw new RuntimeException();
                }
                int m17performCustomRequestFocusMxy_nc0 = m17performCustomRequestFocusMxy_nc0(focusTargetNode2, i);
                int i3 = m17performCustomRequestFocusMxy_nc0 == 1 ? 0 : m17performCustomRequestFocusMxy_nc0;
                return i3 == 0 ? m16performCustomEnterMxy_nc0(focusTargetNode2, i) : i3;
            }
        }
        return 1;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:42:0x0078 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:47:0x003a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:48:0x003a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:51:0x007e */
    /* JADX WARN: Type inference failed for: r6v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public static final boolean performRequestFocus(FocusTargetNode focusTargetNode) {
        Modifier.Node node;
        LayoutNode layoutNode;
        Owner owner;
        NodeChain nodeChain;
        ResultKt.checkNotNullParameter(focusTargetNode, "<this>");
        int ordinal = focusTargetNode.focusState.ordinal();
        boolean z = true;
        if (ordinal != 0) {
            if (ordinal == 1) {
                FocusTargetNode activeChild = getActiveChild(focusTargetNode);
                if (activeChild == null || clearFocus(activeChild, false, true)) {
                    grantFocus(focusTargetNode);
                } else {
                    z = false;
                }
                if (!z) {
                    return z;
                }
                ResultKt.refreshFocusEventNodes(focusTargetNode);
                return z;
            }
            if (ordinal != 2) {
                if (ordinal != 3) {
                    throw new RuntimeException();
                }
                Modifier.Node node2 = focusTargetNode.node;
                if (!node2.isAttached) {
                    throw new IllegalStateException("visitAncestors called on an unattached node".toString());
                }
                Modifier.Node node3 = node2.parent;
                LayoutNode requireLayoutNode = Snake.requireLayoutNode(focusTargetNode);
                loop0: while (true) {
                    node = null;
                    if (requireLayoutNode == null) {
                        break;
                    }
                    if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 1024) != 0) {
                        while (node3 != null) {
                            if ((node3.kindSet & 1024) != 0) {
                                Modifier.Node node4 = node3;
                                MutableVector mutableVector = null;
                                while (node4 != null) {
                                    if (node4 instanceof FocusTargetNode) {
                                        node = node4;
                                        break loop0;
                                    }
                                    if ((node4.kindSet & 1024) != 0 && (node4 instanceof DelegatingNode)) {
                                        Modifier.Node node5 = ((DelegatingNode) node4).delegate;
                                        int i = 0;
                                        mutableVector = mutableVector;
                                        while (node5 != null) {
                                            if ((node5.kindSet & 1024) != 0) {
                                                i++;
                                                mutableVector = mutableVector;
                                                if (i == 1) {
                                                    node4 = node5;
                                                } else {
                                                    if (mutableVector == null) {
                                                        ?? obj = new Object();
                                                        obj.content = new Modifier.Node[16];
                                                        obj.size = 0;
                                                        mutableVector = obj;
                                                    }
                                                    if (node4 != null) {
                                                        mutableVector.add(node4);
                                                        node4 = null;
                                                    }
                                                    mutableVector.add(node5);
                                                }
                                            }
                                            node5 = node5.child;
                                            mutableVector = mutableVector;
                                        }
                                        if (i == 1) {
                                        }
                                    }
                                    node4 = Snake.access$pop(mutableVector);
                                }
                            }
                            node3 = node3.parent;
                        }
                    }
                    requireLayoutNode = requireLayoutNode.getParent$ui_release();
                    node3 = (requireLayoutNode == null || (nodeChain = requireLayoutNode.nodes) == null) ? null : nodeChain.tail;
                }
                FocusTargetNode focusTargetNode2 = (FocusTargetNode) node;
                if (focusTargetNode2 != null) {
                    return requestFocusForChild(focusTargetNode2, focusTargetNode);
                }
                NodeCoordinator nodeCoordinator = focusTargetNode.coordinator;
                if (nodeCoordinator == null || (layoutNode = nodeCoordinator.layoutNode) == null || (owner = layoutNode.owner) == null) {
                    throw new IllegalStateException("Owner not initialized.".toString());
                }
                if (owner.requestFocus()) {
                    grantFocus(focusTargetNode);
                } else {
                    z = false;
                }
                if (!z) {
                    return z;
                }
                ResultKt.refreshFocusEventNodes(focusTargetNode);
                return z;
            }
        }
        ResultKt.refreshFocusEventNodes(focusTargetNode);
        return true;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:40:0x009c */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:45:0x0047 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:46:0x0047 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:49:0x00a2 */
    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public static final boolean pickChildForBackwardSearch(FocusTargetNode focusTargetNode, Function1 function1) {
        Object[] objArr = new FocusTargetNode[16];
        Modifier.Node node = focusTargetNode.node;
        if (!node.isAttached) {
            throw new IllegalStateException("visitChildren called on an unattached node".toString());
        }
        ?? obj = new Object();
        obj.content = new Modifier.Node[16];
        obj.size = 0;
        Modifier.Node node2 = node.child;
        if (node2 == null) {
            Snake.access$addLayoutNodeChildren(obj, node);
        } else {
            obj.add(node2);
        }
        int i = 0;
        while (obj.isNotEmpty()) {
            Modifier.Node node3 = (Modifier.Node) obj.removeAt(obj.size - 1);
            if ((node3.aggregateChildKindSet & 1024) == 0) {
                Snake.access$addLayoutNodeChildren(obj, node3);
            } else {
                while (true) {
                    if (node3 == null) {
                        break;
                    }
                    if ((node3.kindSet & 1024) != 0) {
                        MutableVector mutableVector = null;
                        while (node3 != null) {
                            if (node3 instanceof FocusTargetNode) {
                                FocusTargetNode focusTargetNode2 = (FocusTargetNode) node3;
                                int i2 = i + 1;
                                if (objArr.length < i2) {
                                    objArr = Arrays.copyOf(objArr, Math.max(i2, objArr.length * 2));
                                    ResultKt.checkNotNullExpressionValue(objArr, "copyOf(this, newSize)");
                                }
                                objArr[i] = focusTargetNode2;
                                i = i2;
                            } else if ((node3.kindSet & 1024) != 0 && (node3 instanceof DelegatingNode)) {
                                Modifier.Node node4 = ((DelegatingNode) node3).delegate;
                                int i3 = 0;
                                mutableVector = mutableVector;
                                while (node4 != null) {
                                    if ((node4.kindSet & 1024) != 0) {
                                        i3++;
                                        mutableVector = mutableVector;
                                        if (i3 == 1) {
                                            node3 = node4;
                                        } else {
                                            if (mutableVector == null) {
                                                ?? obj2 = new Object();
                                                obj2.content = new Modifier.Node[16];
                                                obj2.size = 0;
                                                mutableVector = obj2;
                                            }
                                            if (node3 != null) {
                                                mutableVector.add(node3);
                                                node3 = null;
                                            }
                                            mutableVector.add(node4);
                                        }
                                    }
                                    node4 = node4.child;
                                    mutableVector = mutableVector;
                                }
                                if (i3 == 1) {
                                }
                            }
                            node3 = Snake.access$pop(mutableVector);
                        }
                    } else {
                        node3 = node3.child;
                    }
                }
            }
        }
        FocusableChildrenComparator focusableChildrenComparator = FocusableChildrenComparator.INSTANCE;
        ResultKt.checkNotNullParameter(objArr, "<this>");
        Arrays.sort(objArr, 0, i, focusableChildrenComparator);
        if (i > 0) {
            int i4 = i - 1;
            do {
                FocusTargetNode focusTargetNode3 = (FocusTargetNode) objArr[i4];
                if (isEligibleForFocusSearch(focusTargetNode3) && backwardFocusSearch(focusTargetNode3, function1)) {
                    return true;
                }
                i4--;
            } while (i4 >= 0);
        }
        return false;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:40:0x009c */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:45:0x0047 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:46:0x0047 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:49:0x00a2 */
    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public static final boolean pickChildForForwardSearch(FocusTargetNode focusTargetNode, Function1 function1) {
        Object[] objArr = new FocusTargetNode[16];
        Modifier.Node node = focusTargetNode.node;
        if (!node.isAttached) {
            throw new IllegalStateException("visitChildren called on an unattached node".toString());
        }
        ?? obj = new Object();
        obj.content = new Modifier.Node[16];
        obj.size = 0;
        Modifier.Node node2 = node.child;
        if (node2 == null) {
            Snake.access$addLayoutNodeChildren(obj, node);
        } else {
            obj.add(node2);
        }
        int i = 0;
        while (obj.isNotEmpty()) {
            Modifier.Node node3 = (Modifier.Node) obj.removeAt(obj.size - 1);
            if ((node3.aggregateChildKindSet & 1024) == 0) {
                Snake.access$addLayoutNodeChildren(obj, node3);
            } else {
                while (true) {
                    if (node3 == null) {
                        break;
                    }
                    if ((node3.kindSet & 1024) != 0) {
                        MutableVector mutableVector = null;
                        while (node3 != null) {
                            if (node3 instanceof FocusTargetNode) {
                                FocusTargetNode focusTargetNode2 = (FocusTargetNode) node3;
                                int i2 = i + 1;
                                if (objArr.length < i2) {
                                    objArr = Arrays.copyOf(objArr, Math.max(i2, objArr.length * 2));
                                    ResultKt.checkNotNullExpressionValue(objArr, "copyOf(this, newSize)");
                                }
                                objArr[i] = focusTargetNode2;
                                i = i2;
                            } else if ((node3.kindSet & 1024) != 0 && (node3 instanceof DelegatingNode)) {
                                Modifier.Node node4 = ((DelegatingNode) node3).delegate;
                                int i3 = 0;
                                mutableVector = mutableVector;
                                while (node4 != null) {
                                    if ((node4.kindSet & 1024) != 0) {
                                        i3++;
                                        mutableVector = mutableVector;
                                        if (i3 == 1) {
                                            node3 = node4;
                                        } else {
                                            if (mutableVector == null) {
                                                ?? obj2 = new Object();
                                                obj2.content = new Modifier.Node[16];
                                                obj2.size = 0;
                                                mutableVector = obj2;
                                            }
                                            if (node3 != null) {
                                                mutableVector.add(node3);
                                                node3 = null;
                                            }
                                            mutableVector.add(node4);
                                        }
                                    }
                                    node4 = node4.child;
                                    mutableVector = mutableVector;
                                }
                                if (i3 == 1) {
                                }
                            }
                            node3 = Snake.access$pop(mutableVector);
                        }
                    } else {
                        node3 = node3.child;
                    }
                }
            }
        }
        FocusableChildrenComparator focusableChildrenComparator = FocusableChildrenComparator.INSTANCE;
        ResultKt.checkNotNullParameter(objArr, "<this>");
        Arrays.sort(objArr, 0, i, focusableChildrenComparator);
        if (i <= 0) {
            return false;
        }
        int i4 = 0;
        do {
            FocusTargetNode focusTargetNode3 = (FocusTargetNode) objArr[i4];
            if (isEligibleForFocusSearch(focusTargetNode3) && forwardFocusSearch(focusTargetNode3, function1)) {
                return true;
            }
            i4++;
        } while (i4 < i);
        return false;
    }

    public static final Object read(PersistentCompositionLocalMap persistentCompositionLocalMap, ProvidableCompositionLocal providableCompositionLocal) {
        ResultKt.checkNotNullParameter(persistentCompositionLocalMap, "<this>");
        ResultKt.checkNotNullParameter(providableCompositionLocal, "key");
        PersistentCompositionLocalHashMap persistentCompositionLocalHashMap = (PersistentCompositionLocalHashMap) persistentCompositionLocalMap;
        if (persistentCompositionLocalHashMap.containsKey(providableCompositionLocal)) {
            State state = (State) persistentCompositionLocalHashMap.get(providableCompositionLocal);
            if (state != null) {
                return state.getValue();
            }
            return null;
        }
        return providableCompositionLocal.defaultValueHolder.current$delegate.getValue();
    }

    public static final boolean requestFocus(FocusTargetNode focusTargetNode) {
        ResultKt.checkNotNullParameter(focusTargetNode, "<this>");
        int ordinal = AnimationEndReason$EnumUnboxingLocalUtility.ordinal(m17performCustomRequestFocusMxy_nc0(focusTargetNode, 7));
        if (ordinal == 0) {
            return performRequestFocus(focusTargetNode);
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

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:34:0x0064 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:39:0x0029 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:40:0x0029 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:43:0x006a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:86:0x00f8 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:91:0x00bc */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:92:0x00bc */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:95:0x00fe */
    /* JADX WARN: Type inference failed for: r8v15, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public static final boolean requestFocusForChild(FocusTargetNode focusTargetNode, FocusTargetNode focusTargetNode2) {
        boolean z;
        Modifier.Node node;
        Modifier.Node node2;
        LayoutNode layoutNode;
        Owner owner;
        NodeChain nodeChain;
        NodeChain nodeChain2;
        Modifier.Node node3 = focusTargetNode2.node;
        if (!node3.isAttached) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node4 = node3.parent;
        LayoutNode requireLayoutNode = Snake.requireLayoutNode(focusTargetNode2);
        loop0: while (true) {
            z = false;
            node = null;
            if (requireLayoutNode == null) {
                node2 = null;
                break;
            }
            if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 1024) != 0) {
                while (node4 != null) {
                    if ((node4.kindSet & 1024) != 0) {
                        node2 = node4;
                        MutableVector mutableVector = null;
                        while (node2 != null) {
                            if (node2 instanceof FocusTargetNode) {
                                break loop0;
                            }
                            if ((node2.kindSet & 1024) != 0 && (node2 instanceof DelegatingNode)) {
                                Modifier.Node node5 = ((DelegatingNode) node2).delegate;
                                int i = 0;
                                mutableVector = mutableVector;
                                while (node5 != null) {
                                    if ((node5.kindSet & 1024) != 0) {
                                        i++;
                                        mutableVector = mutableVector;
                                        if (i == 1) {
                                            node2 = node5;
                                        } else {
                                            if (mutableVector == null) {
                                                ?? obj = new Object();
                                                obj.content = new Modifier.Node[16];
                                                obj.size = 0;
                                                mutableVector = obj;
                                            }
                                            if (node2 != null) {
                                                mutableVector.add(node2);
                                                node2 = null;
                                            }
                                            mutableVector.add(node5);
                                        }
                                    }
                                    node5 = node5.child;
                                    mutableVector = mutableVector;
                                }
                                if (i == 1) {
                                }
                            }
                            node2 = Snake.access$pop(mutableVector);
                        }
                    }
                    node4 = node4.parent;
                }
            }
            requireLayoutNode = requireLayoutNode.getParent$ui_release();
            node4 = (requireLayoutNode == null || (nodeChain2 = requireLayoutNode.nodes) == null) ? null : nodeChain2.tail;
        }
        if (!ResultKt.areEqual(node2, focusTargetNode)) {
            throw new IllegalStateException("Non child node cannot request focus.".toString());
        }
        int ordinal = focusTargetNode.focusState.ordinal();
        FocusStateImpl focusStateImpl = FocusStateImpl.ActiveParent;
        if (ordinal == 0) {
            grantFocus(focusTargetNode2);
            focusTargetNode.focusState = focusStateImpl;
            ResultKt.refreshFocusEventNodes(focusTargetNode2);
            ResultKt.refreshFocusEventNodes(focusTargetNode);
            return true;
        }
        if (ordinal == 1) {
            if (getActiveChild(focusTargetNode) == null) {
                throw new IllegalStateException("Required value was null.".toString());
            }
            FocusTargetNode activeChild = getActiveChild(focusTargetNode);
            if (activeChild == null || clearFocus(activeChild, false, true)) {
                grantFocus(focusTargetNode2);
                z = true;
            }
            if (!z) {
                return z;
            }
            ResultKt.refreshFocusEventNodes(focusTargetNode2);
            return z;
        }
        if (ordinal == 2) {
            return false;
        }
        if (ordinal != 3) {
            throw new RuntimeException();
        }
        Modifier.Node node6 = focusTargetNode.node;
        if (!node6.isAttached) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node7 = node6.parent;
        LayoutNode requireLayoutNode2 = Snake.requireLayoutNode(focusTargetNode);
        loop4: while (true) {
            if (requireLayoutNode2 == null) {
                break;
            }
            if ((requireLayoutNode2.nodes.head.aggregateChildKindSet & 1024) != 0) {
                while (node7 != null) {
                    if ((node7.kindSet & 1024) != 0) {
                        Modifier.Node node8 = node7;
                        MutableVector mutableVector2 = null;
                        while (node8 != null) {
                            if (node8 instanceof FocusTargetNode) {
                                node = node8;
                                break loop4;
                            }
                            if ((node8.kindSet & 1024) != 0 && (node8 instanceof DelegatingNode)) {
                                Modifier.Node node9 = ((DelegatingNode) node8).delegate;
                                int i2 = 0;
                                mutableVector2 = mutableVector2;
                                while (node9 != null) {
                                    if ((node9.kindSet & 1024) != 0) {
                                        i2++;
                                        mutableVector2 = mutableVector2;
                                        if (i2 == 1) {
                                            node8 = node9;
                                        } else {
                                            if (mutableVector2 == null) {
                                                ?? obj2 = new Object();
                                                obj2.content = new Modifier.Node[16];
                                                obj2.size = 0;
                                                mutableVector2 = obj2;
                                            }
                                            if (node8 != null) {
                                                mutableVector2.add(node8);
                                                node8 = null;
                                            }
                                            mutableVector2.add(node9);
                                        }
                                    }
                                    node9 = node9.child;
                                    mutableVector2 = mutableVector2;
                                }
                                if (i2 == 1) {
                                }
                            }
                            node8 = Snake.access$pop(mutableVector2);
                        }
                    }
                    node7 = node7.parent;
                }
            }
            requireLayoutNode2 = requireLayoutNode2.getParent$ui_release();
            node7 = (requireLayoutNode2 == null || (nodeChain = requireLayoutNode2.nodes) == null) ? null : nodeChain.tail;
        }
        FocusTargetNode focusTargetNode3 = (FocusTargetNode) node;
        if (focusTargetNode3 == null) {
            NodeCoordinator nodeCoordinator = focusTargetNode.coordinator;
            if (nodeCoordinator == null || (layoutNode = nodeCoordinator.layoutNode) == null || (owner = layoutNode.owner) == null) {
                throw new IllegalStateException("Owner not initialized.".toString());
            }
            if (owner.requestFocus()) {
                focusTargetNode.focusState = FocusStateImpl.Active;
                ResultKt.refreshFocusEventNodes(focusTargetNode);
                return requestFocusForChild(focusTargetNode, focusTargetNode2);
            }
        }
        if (focusTargetNode3 == null || !requestFocusForChild(focusTargetNode3, focusTargetNode)) {
            return false;
        }
        boolean requestFocusForChild = requestFocusForChild(focusTargetNode, focusTargetNode2);
        if (focusTargetNode.focusState == focusStateImpl) {
            return requestFocusForChild;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public static final TextStyle resolveDefaults(TextStyle textStyle, LayoutDirection layoutDirection) {
        int i;
        ResultKt.checkNotNullParameter(textStyle, "style");
        int i2 = SpanStyleKt.$r8$clinit;
        SpanStyle spanStyle = textStyle.spanStyle;
        ResultKt.checkNotNullParameter(spanStyle, "style");
        TextForegroundStyle textForegroundStyle = spanStyle.textForegroundStyle;
        textForegroundStyle.getClass();
        TextForegroundStyle.Unspecified unspecified = TextForegroundStyle.Unspecified.INSTANCE;
        if (ResultKt.areEqual(textForegroundStyle, unspecified)) {
            long j = SpanStyleKt.DefaultColor;
            textForegroundStyle = j != Color.Unspecified ? new ColorStyle(j) : unspecified;
        }
        TextForegroundStyle textForegroundStyle2 = textForegroundStyle;
        long j2 = spanStyle.fontSize;
        if (ResultKt.m297isUnspecifiedR2X_6o(j2)) {
            j2 = SpanStyleKt.DefaultFontSize;
        }
        long j3 = j2;
        FontWeight fontWeight = spanStyle.fontWeight;
        if (fontWeight == null) {
            fontWeight = FontWeight.Normal;
        }
        FontWeight fontWeight2 = fontWeight;
        FontStyle fontStyle = spanStyle.fontStyle;
        FontStyle fontStyle2 = new FontStyle(fontStyle != null ? fontStyle.value : 0);
        FontSynthesis fontSynthesis = spanStyle.fontSynthesis;
        FontSynthesis fontSynthesis2 = new FontSynthesis(fontSynthesis != null ? fontSynthesis.value : 1);
        FontFamily fontFamily = spanStyle.fontFamily;
        if (fontFamily == null) {
            fontFamily = FontFamily.Default;
        }
        FontFamily fontFamily2 = fontFamily;
        String str = spanStyle.fontFeatureSettings;
        if (str == null) {
            str = "";
        }
        String str2 = str;
        long j4 = spanStyle.letterSpacing;
        if (ResultKt.m297isUnspecifiedR2X_6o(j4)) {
            j4 = SpanStyleKt.DefaultLetterSpacing;
        }
        BaselineShift baselineShift = spanStyle.baselineShift;
        BaselineShift baselineShift2 = new BaselineShift(baselineShift != null ? baselineShift.multiplier : 0.0f);
        TextGeometricTransform textGeometricTransform = spanStyle.textGeometricTransform;
        if (textGeometricTransform == null) {
            textGeometricTransform = TextGeometricTransform.None;
        }
        TextGeometricTransform textGeometricTransform2 = textGeometricTransform;
        LocaleList localeList = spanStyle.localeList;
        if (localeList == null) {
            localeList = PlatformLocaleKt.platformLocaleDelegate.getCurrent();
        }
        LocaleList localeList2 = localeList;
        long j5 = Color.Unspecified;
        long j6 = spanStyle.background;
        if (j6 == j5) {
            j6 = SpanStyleKt.DefaultBackgroundColor;
        }
        long j7 = j6;
        TextDecoration textDecoration = spanStyle.textDecoration;
        if (textDecoration == null) {
            textDecoration = TextDecoration.None;
        }
        TextDecoration textDecoration2 = textDecoration;
        Shadow shadow = spanStyle.shadow;
        if (shadow == null) {
            shadow = Shadow.None;
        }
        Shadow shadow2 = shadow;
        DrawStyle drawStyle = spanStyle.drawStyle;
        if (drawStyle == null) {
            drawStyle = Fill.INSTANCE;
        }
        SpanStyle spanStyle2 = new SpanStyle(textForegroundStyle2, j3, fontWeight2, fontStyle2, fontSynthesis2, fontFamily2, str2, j4, baselineShift2, textGeometricTransform2, localeList2, j7, textDecoration2, shadow2, drawStyle);
        int i3 = ParagraphStyleKt.$r8$clinit;
        ParagraphStyle paragraphStyle = textStyle.paragraphStyle;
        ResultKt.checkNotNullParameter(paragraphStyle, "style");
        TextAlign textAlign = new TextAlign(paragraphStyle.textAlignOrDefault);
        TextDirection textDirection = paragraphStyle.textDirection;
        if (textDirection != null && TextDirection.m271equalsimpl0(textDirection.value, 3)) {
            int ordinal = layoutDirection.ordinal();
            if (ordinal == 0) {
                i = 4;
            } else {
                if (ordinal != 1) {
                    throw new RuntimeException();
                }
                i = 5;
            }
        } else if (textDirection == null) {
            int ordinal2 = layoutDirection.ordinal();
            if (ordinal2 == 0) {
                i = 1;
            } else {
                if (ordinal2 != 1) {
                    throw new RuntimeException();
                }
                i = 2;
            }
        } else {
            i = textDirection.value;
        }
        TextDirection textDirection2 = new TextDirection(i);
        long j8 = paragraphStyle.lineHeight;
        if (ResultKt.m297isUnspecifiedR2X_6o(j8)) {
            j8 = ParagraphStyleKt.DefaultLineHeight;
        }
        TextIndent textIndent = paragraphStyle.textIndent;
        if (textIndent == null) {
            textIndent = TextIndent.None;
        }
        TextIndent textIndent2 = textIndent;
        LineBreak lineBreak = new LineBreak(paragraphStyle.lineBreakOrDefault);
        Hyphens hyphens = new Hyphens(paragraphStyle.hyphensOrDefault);
        TextMotion textMotion = paragraphStyle.textMotion;
        if (textMotion == null) {
            textMotion = TextMotion.Static;
        }
        return new TextStyle(spanStyle2, new ParagraphStyle(textAlign, textDirection2, j8, textIndent2, paragraphStyle.platformStyle, paragraphStyle.lineHeightStyle, lineBreak, hyphens, textMotion), textStyle.platformStyle);
    }

    /* renamed from: resolveLineHeightInPx-o2QH7mI, reason: not valid java name */
    public static final float m18resolveLineHeightInPxo2QH7mI(long j, float f, Density density) {
        long m284getTypeUIouoOA = TextUnit.m284getTypeUIouoOA(j);
        if (TextUnitType.m287equalsimpl0(m284getTypeUIouoOA, 4294967296L)) {
            return density.mo31toPxR2X_6o(j);
        }
        if (TextUnitType.m287equalsimpl0(m284getTypeUIouoOA, 8589934592L)) {
            return TextUnit.m285getValueimpl(j) * f;
        }
        return Float.NaN;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:119:0x0192 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:124:0x0156 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:125:0x0156 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:128:0x0198 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:42:0x00a9 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:47:0x0055 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:48:0x0055 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:51:0x00af */
    /* JADX WARN: Type inference failed for: r13v11, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v14, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v1, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* renamed from: searchChildren-4C6V_qg, reason: not valid java name */
    public static final boolean m19searchChildren4C6V_qg(FocusTargetNode focusTargetNode, FocusTargetNode focusTargetNode2, int i, Function1 function1) {
        Modifier.Node node;
        NodeChain nodeChain;
        if (focusTargetNode.focusState != FocusStateImpl.ActiveParent) {
            throw new IllegalStateException("This function should only be used within a parent that has focus.".toString());
        }
        Object[] objArr = new FocusTargetNode[16];
        Modifier.Node node2 = focusTargetNode.node;
        if (!node2.isAttached) {
            throw new IllegalStateException("visitChildren called on an unattached node".toString());
        }
        ?? obj = new Object();
        obj.content = new Modifier.Node[16];
        obj.size = 0;
        Modifier.Node node3 = node2.child;
        if (node3 == null) {
            Snake.access$addLayoutNodeChildren(obj, node2);
        } else {
            obj.add(node3);
        }
        int i2 = 0;
        while (obj.isNotEmpty()) {
            Modifier.Node node4 = (Modifier.Node) obj.removeAt(obj.size - 1);
            if ((node4.aggregateChildKindSet & 1024) == 0) {
                Snake.access$addLayoutNodeChildren(obj, node4);
            } else {
                while (true) {
                    if (node4 == null) {
                        break;
                    }
                    if ((node4.kindSet & 1024) != 0) {
                        MutableVector mutableVector = null;
                        while (node4 != null) {
                            if (node4 instanceof FocusTargetNode) {
                                FocusTargetNode focusTargetNode3 = (FocusTargetNode) node4;
                                int i3 = i2 + 1;
                                if (objArr.length < i3) {
                                    objArr = Arrays.copyOf(objArr, Math.max(i3, objArr.length * 2));
                                    ResultKt.checkNotNullExpressionValue(objArr, "copyOf(this, newSize)");
                                }
                                objArr[i2] = focusTargetNode3;
                                i2 = i3;
                            } else if ((node4.kindSet & 1024) != 0 && (node4 instanceof DelegatingNode)) {
                                Modifier.Node node5 = ((DelegatingNode) node4).delegate;
                                int i4 = 0;
                                mutableVector = mutableVector;
                                while (node5 != null) {
                                    if ((node5.kindSet & 1024) != 0) {
                                        i4++;
                                        mutableVector = mutableVector;
                                        if (i4 == 1) {
                                            node4 = node5;
                                        } else {
                                            if (mutableVector == null) {
                                                ?? obj2 = new Object();
                                                obj2.content = new Modifier.Node[16];
                                                obj2.size = 0;
                                                mutableVector = obj2;
                                            }
                                            if (node4 != null) {
                                                mutableVector.add(node4);
                                                node4 = null;
                                            }
                                            mutableVector.add(node5);
                                        }
                                    }
                                    node5 = node5.child;
                                    mutableVector = mutableVector;
                                }
                                if (i4 == 1) {
                                }
                            }
                            node4 = Snake.access$pop(mutableVector);
                        }
                    } else {
                        node4 = node4.child;
                    }
                }
            }
        }
        FocusableChildrenComparator focusableChildrenComparator = FocusableChildrenComparator.INSTANCE;
        ResultKt.checkNotNullParameter(objArr, "<this>");
        Arrays.sort(objArr, 0, i2, focusableChildrenComparator);
        if (FocusDirection.m67equalsimpl0(i, 1)) {
            int i5 = new IntProgression(0, i2 - 1, 1).last;
            if (i5 >= 0) {
                boolean z = false;
                int i6 = 0;
                while (true) {
                    if (z) {
                        FocusTargetNode focusTargetNode4 = (FocusTargetNode) objArr[i6];
                        if (isEligibleForFocusSearch(focusTargetNode4) && forwardFocusSearch(focusTargetNode4, function1)) {
                            return true;
                        }
                    }
                    if (ResultKt.areEqual(objArr[i6], focusTargetNode2)) {
                        z = true;
                    }
                    if (i6 == i5) {
                        break;
                    }
                    i6++;
                }
            }
        } else {
            if (!FocusDirection.m67equalsimpl0(i, 2)) {
                throw new IllegalStateException("This function should only be used for 1-D focus search".toString());
            }
            int i7 = new IntProgression(0, i2 - 1, 1).last;
            if (i7 >= 0) {
                boolean z2 = false;
                while (true) {
                    if (z2) {
                        FocusTargetNode focusTargetNode5 = (FocusTargetNode) objArr[i7];
                        if (isEligibleForFocusSearch(focusTargetNode5) && backwardFocusSearch(focusTargetNode5, function1)) {
                            return true;
                        }
                    }
                    if (ResultKt.areEqual(objArr[i7], focusTargetNode2)) {
                        z2 = true;
                    }
                    if (i7 == 0) {
                        break;
                    }
                    i7--;
                }
            }
        }
        if (!FocusDirection.m67equalsimpl0(i, 1) && focusTargetNode.fetchFocusProperties$ui_release().canFocus) {
            Modifier.Node node6 = focusTargetNode.node;
            if (!node6.isAttached) {
                throw new IllegalStateException("visitAncestors called on an unattached node".toString());
            }
            Modifier.Node node7 = node6.parent;
            LayoutNode requireLayoutNode = Snake.requireLayoutNode(focusTargetNode);
            loop5: while (true) {
                if (requireLayoutNode == null) {
                    node = null;
                    break;
                }
                if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 1024) != 0) {
                    while (node7 != null) {
                        if ((node7.kindSet & 1024) != 0) {
                            Modifier.Node node8 = node7;
                            MutableVector mutableVector2 = null;
                            while (node8 != null) {
                                if (node8 instanceof FocusTargetNode) {
                                    node = node8;
                                    break loop5;
                                }
                                if ((node8.kindSet & 1024) != 0 && (node8 instanceof DelegatingNode)) {
                                    Modifier.Node node9 = ((DelegatingNode) node8).delegate;
                                    int i8 = 0;
                                    mutableVector2 = mutableVector2;
                                    while (node9 != null) {
                                        if ((node9.kindSet & 1024) != 0) {
                                            i8++;
                                            mutableVector2 = mutableVector2;
                                            if (i8 == 1) {
                                                node8 = node9;
                                            } else {
                                                if (mutableVector2 == null) {
                                                    ?? obj3 = new Object();
                                                    obj3.content = new Modifier.Node[16];
                                                    obj3.size = 0;
                                                    mutableVector2 = obj3;
                                                }
                                                if (node8 != null) {
                                                    mutableVector2.add(node8);
                                                    node8 = null;
                                                }
                                                mutableVector2.add(node9);
                                            }
                                        }
                                        node9 = node9.child;
                                        mutableVector2 = mutableVector2;
                                    }
                                    if (i8 == 1) {
                                    }
                                }
                                node8 = Snake.access$pop(mutableVector2);
                            }
                        }
                        node7 = node7.parent;
                    }
                }
                requireLayoutNode = requireLayoutNode.getParent$ui_release();
                node7 = (requireLayoutNode == null || (nodeChain = requireLayoutNode.nodes) == null) ? null : nodeChain.tail;
            }
            if (node != null) {
                return ((Boolean) function1.invoke(focusTargetNode)).booleanValue();
            }
        }
        return false;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:40:0x008e */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:45:0x004e */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:46:0x004e */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:49:0x0094 */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v1, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* renamed from: searchChildren-4C6V_qg$1, reason: not valid java name */
    public static final boolean m20searchChildren4C6V_qg$1(FocusTargetNode focusTargetNode, FocusTargetNode focusTargetNode2, int i, Function1 function1) {
        FocusTargetNode m9findBestCandidate4WY_MpI;
        ?? obj = new Object();
        obj.content = new FocusTargetNode[16];
        obj.size = 0;
        Modifier.Node node = focusTargetNode.node;
        if (!node.isAttached) {
            throw new IllegalStateException("visitChildren called on an unattached node".toString());
        }
        ?? obj2 = new Object();
        obj2.content = new Modifier.Node[16];
        obj2.size = 0;
        Modifier.Node node2 = node.child;
        if (node2 == null) {
            Snake.access$addLayoutNodeChildren(obj2, node);
        } else {
            obj2.add(node2);
        }
        while (obj2.isNotEmpty()) {
            Modifier.Node node3 = (Modifier.Node) obj2.removeAt(obj2.size - 1);
            if ((node3.aggregateChildKindSet & 1024) == 0) {
                Snake.access$addLayoutNodeChildren(obj2, node3);
            } else {
                while (true) {
                    if (node3 == null) {
                        break;
                    }
                    if ((node3.kindSet & 1024) != 0) {
                        MutableVector mutableVector = null;
                        while (node3 != null) {
                            if (node3 instanceof FocusTargetNode) {
                                obj.add((FocusTargetNode) node3);
                            } else if ((node3.kindSet & 1024) != 0 && (node3 instanceof DelegatingNode)) {
                                Modifier.Node node4 = ((DelegatingNode) node3).delegate;
                                int i2 = 0;
                                mutableVector = mutableVector;
                                while (node4 != null) {
                                    if ((node4.kindSet & 1024) != 0) {
                                        i2++;
                                        mutableVector = mutableVector;
                                        if (i2 == 1) {
                                            node3 = node4;
                                        } else {
                                            if (mutableVector == null) {
                                                ?? obj3 = new Object();
                                                obj3.content = new Modifier.Node[16];
                                                obj3.size = 0;
                                                mutableVector = obj3;
                                            }
                                            if (node3 != null) {
                                                mutableVector.add(node3);
                                                node3 = null;
                                            }
                                            mutableVector.add(node4);
                                        }
                                    }
                                    node4 = node4.child;
                                    mutableVector = mutableVector;
                                }
                                if (i2 == 1) {
                                }
                            }
                            node3 = Snake.access$pop(mutableVector);
                        }
                    } else {
                        node3 = node3.child;
                    }
                }
            }
        }
        while (obj.isNotEmpty() && (m9findBestCandidate4WY_MpI = m9findBestCandidate4WY_MpI(obj, focusRect(focusTargetNode2), i)) != null) {
            if (m9findBestCandidate4WY_MpI.fetchFocusProperties$ui_release().canFocus) {
                return ((Boolean) function1.invoke(m9findBestCandidate4WY_MpI)).booleanValue();
            }
            if (m12generateAndSearchChildren4C6V_qg$1(m9findBestCandidate4WY_MpI, focusTargetNode2, i, function1)) {
                return true;
            }
            obj.remove(m9findBestCandidate4WY_MpI);
        }
        return false;
    }

    /* renamed from: setFontSize-KmRG4DE, reason: not valid java name */
    public static final void m21setFontSizeKmRG4DE(Spannable spannable, long j, Density density, int i, int i2) {
        ResultKt.checkNotNullParameter(density, "density");
        long m284getTypeUIouoOA = TextUnit.m284getTypeUIouoOA(j);
        if (TextUnitType.m287equalsimpl0(m284getTypeUIouoOA, 4294967296L)) {
            setSpan(spannable, new AbsoluteSizeSpan(ResultKt.roundToInt(density.mo31toPxR2X_6o(j)), false), i, i2);
        } else if (TextUnitType.m287equalsimpl0(m284getTypeUIouoOA, 8589934592L)) {
            setSpan(spannable, new RelativeSizeSpan(TextUnit.m285getValueimpl(j)), i, i2);
        }
    }

    public static final void setSpan(Spannable spannable, Object obj, int i, int i2) {
        ResultKt.checkNotNullParameter(spannable, "<this>");
        ResultKt.checkNotNullParameter(obj, "span");
        spannable.setSpan(obj, i, i2, 33);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:103:0x01d9 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [kotlin.collections.EmptyList] */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.util.ArrayList] */
    public static final SpannableString toAccessibilitySpannableString(AnnotatedString annotatedString, Density density, FontFamily.Resolver resolver, URLSpanCache uRLSpanCache) {
        ArrayList arrayList;
        int i;
        int i2;
        int i3;
        ResultKt.checkNotNullParameter(density, "density");
        ResultKt.checkNotNullParameter(resolver, "fontFamilyResolver");
        ResultKt.checkNotNullParameter(uRLSpanCache, "urlSpanCache");
        String str = annotatedString.text;
        SpannableString spannableString = new SpannableString(str);
        List list = annotatedString.spanStylesOrNull;
        if (list != null) {
            int size = list.size();
            for (int i4 = 0; i4 < size; i4++) {
                AnnotatedString.Range range = (AnnotatedString.Range) list.get(i4);
                SpanStyle spanStyle = (SpanStyle) range.item;
                long mo264getColor0d7_KjU = spanStyle.textForegroundStyle.mo264getColor0d7_KjU();
                TextForegroundStyle textForegroundStyle = spanStyle.textForegroundStyle;
                if (!Color.m121equalsimpl0(mo264getColor0d7_KjU, textForegroundStyle.mo264getColor0d7_KjU())) {
                    textForegroundStyle = mo264getColor0d7_KjU != Color.Unspecified ? new ColorStyle(mo264getColor0d7_KjU) : TextForegroundStyle.Unspecified.INSTANCE;
                }
                long mo264getColor0d7_KjU2 = textForegroundStyle.mo264getColor0d7_KjU();
                long j = Color.Unspecified;
                int i5 = range.start;
                int i6 = range.end;
                if (mo264getColor0d7_KjU2 != j) {
                    setSpan(spannableString, new ForegroundColorSpan(Brush.m109toArgb8_81llA(mo264getColor0d7_KjU2)), i5, i6);
                }
                m21setFontSizeKmRG4DE(spannableString, spanStyle.fontSize, density, i5, i6);
                FontWeight fontWeight = spanStyle.fontWeight;
                FontStyle fontStyle = spanStyle.fontStyle;
                if (fontWeight == null && fontStyle == null) {
                    i = i6;
                    i2 = i5;
                } else {
                    if (fontWeight == null) {
                        fontWeight = FontWeight.Normal;
                    }
                    int i7 = fontStyle != null ? fontStyle.value : 0;
                    ResultKt.checkNotNullParameter(fontWeight, "fontWeight");
                    boolean z = fontWeight.compareTo(FontWeight.W600) >= 0;
                    boolean m258equalsimpl0 = FontStyle.m258equalsimpl0(i7, 1);
                    int i8 = (m258equalsimpl0 && z) ? 3 : z ? 1 : m258equalsimpl0 ? 2 : 0;
                    i = i6;
                    i2 = i5;
                    spannableString.setSpan(new StyleSpan(i8), i2, i, 33);
                }
                TextDecoration textDecoration = spanStyle.textDecoration;
                if (textDecoration != null) {
                    int i9 = textDecoration.mask;
                    if ((i9 | 1) == i9) {
                        i3 = 33;
                        spannableString.setSpan(new UnderlineSpan(), i2, i, 33);
                    } else {
                        i3 = 33;
                    }
                    if ((i9 | 2) == i9) {
                        spannableString.setSpan(new StrikethroughSpan(), i2, i, i3);
                    }
                } else {
                    i3 = 33;
                }
                TextGeometricTransform textGeometricTransform = spanStyle.textGeometricTransform;
                if (textGeometricTransform != null) {
                    spannableString.setSpan(new ScaleXSpan(textGeometricTransform.scaleX), i2, i, i3);
                }
                LocaleList localeList = spanStyle.localeList;
                if (localeList != null) {
                    setSpan(spannableString, LocaleListHelperMethods.INSTANCE.localeSpan(localeList), i2, i);
                }
                long j2 = spanStyle.background;
                if (j2 != j) {
                    setSpan(spannableString, new BackgroundColorSpan(Brush.m109toArgb8_81llA(j2)), i2, i);
                }
            }
        }
        int length = str.length();
        ?? r4 = EmptyList.INSTANCE;
        List list2 = annotatedString.annotations;
        if (list2 != null) {
            arrayList = new ArrayList(list2.size());
            int size2 = list2.size();
            for (int i10 = 0; i10 < size2; i10++) {
                Object obj = list2.get(i10);
                AnnotatedString.Range range2 = (AnnotatedString.Range) obj;
                if ((range2.item instanceof TtsAnnotation) && AnnotatedStringKt.intersect(0, length, range2.start, range2.end)) {
                    arrayList.add(obj);
                }
            }
        } else {
            arrayList = r4;
        }
        int size3 = arrayList.size();
        for (int i11 = 0; i11 < size3; i11++) {
            AnnotatedString.Range range3 = (AnnotatedString.Range) arrayList.get(i11);
            TtsAnnotation ttsAnnotation = (TtsAnnotation) range3.item;
            ResultKt.checkNotNullParameter(ttsAnnotation, "<this>");
            if (!(ttsAnnotation instanceof VerbatimTtsAnnotation)) {
                throw new RuntimeException();
            }
            TtsSpan build = new TtsSpan.VerbatimBuilder(((VerbatimTtsAnnotation) ttsAnnotation).verbatim).build();
            ResultKt.checkNotNullExpressionValue(build, "builder.build()");
            spannableString.setSpan(build, range3.start, range3.end, 33);
        }
        int length2 = str.length();
        if (list2 != null) {
            r4 = new ArrayList(list2.size());
            int size4 = list2.size();
            for (int i12 = 0; i12 < size4; i12++) {
                Object obj2 = list2.get(i12);
                AnnotatedString.Range range4 = (AnnotatedString.Range) obj2;
                if ((range4.item instanceof UrlAnnotation) && AnnotatedStringKt.intersect(0, length2, range4.start, range4.end)) {
                    r4.add(obj2);
                }
            }
        }
        int size5 = r4.size();
        for (int i13 = 0; i13 < size5; i13++) {
            AnnotatedString.Range range5 = (AnnotatedString.Range) r4.get(i13);
            UrlAnnotation urlAnnotation = (UrlAnnotation) range5.item;
            ResultKt.checkNotNullParameter(urlAnnotation, "urlAnnotation");
            WeakHashMap weakHashMap = uRLSpanCache.spansByAnnotation;
            Object obj3 = weakHashMap.get(urlAnnotation);
            if (obj3 == null) {
                obj3 = new URLSpan(urlAnnotation.url);
                weakHashMap.put(urlAnnotation, obj3);
            }
            spannableString.setSpan((URLSpan) obj3, range5.start, range5.end, 33);
        }
        return spannableString;
    }

    public static final String toDebugString(Continuation continuation) {
        Object createFailure;
        if (continuation instanceof DispatchedContinuation) {
            return continuation.toString();
        }
        try {
            createFailure = continuation + '@' + getHexAddress(continuation);
        } catch (Throwable th) {
            createFailure = ResultKt.createFailure(th);
        }
        if (Result.m292exceptionOrNullimpl(createFailure) != null) {
            createFailure = continuation.getClass().getName() + '@' + getHexAddress(continuation);
        }
        return (String) createFailure;
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m22toStringimpl(int i) {
        return m7equalsimpl0$1(i, 1) ? "Clip" : m7equalsimpl0$1(i, 2) ? "Ellipsis" : m7equalsimpl0$1(i, 3) ? "Visible" : "Invalid";
    }

    public static final String toStringAsFixed(float f) {
        int max = Math.max(1, 0);
        float pow = (float) Math.pow(10.0f, max);
        float f2 = f * pow;
        int i = (int) f2;
        if (f2 - i >= 0.5f) {
            i++;
        }
        float f3 = i / pow;
        return max > 0 ? String.valueOf(f3) : String.valueOf((int) f3);
    }

    /* renamed from: twoDimensionalFocusSearch--OM-vw8, reason: not valid java name */
    public static final Boolean m23twoDimensionalFocusSearchOMvw8(FocusTargetNode focusTargetNode, int i, RecomposeScopeImpl$end$1$2 recomposeScopeImpl$end$1$2) {
        int ordinal = focusTargetNode.focusState.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                FocusTargetNode activeChild = getActiveChild(focusTargetNode);
                if (activeChild == null) {
                    throw new IllegalStateException("ActiveParent must have a focusedChild".toString());
                }
                int ordinal2 = activeChild.focusState.ordinal();
                if (ordinal2 != 0) {
                    if (ordinal2 == 1) {
                        Boolean m23twoDimensionalFocusSearchOMvw8 = m23twoDimensionalFocusSearchOMvw8(activeChild, i, recomposeScopeImpl$end$1$2);
                        if (!ResultKt.areEqual(m23twoDimensionalFocusSearchOMvw8, Boolean.FALSE)) {
                            return m23twoDimensionalFocusSearchOMvw8;
                        }
                        if (activeChild.focusState != FocusStateImpl.ActiveParent) {
                            throw new IllegalStateException("Check failed.".toString());
                        }
                        FocusTargetNode findActiveFocusNode = findActiveFocusNode(activeChild);
                        if (findActiveFocusNode != null) {
                            return Boolean.valueOf(m12generateAndSearchChildren4C6V_qg$1(focusTargetNode, findActiveFocusNode, i, recomposeScopeImpl$end$1$2));
                        }
                        throw new IllegalStateException("ActiveParent must have a focusedChild".toString());
                    }
                    if (ordinal2 != 2) {
                        if (ordinal2 != 3) {
                            throw new RuntimeException();
                        }
                        throw new IllegalStateException("ActiveParent must have a focusedChild".toString());
                    }
                }
                return Boolean.valueOf(m12generateAndSearchChildren4C6V_qg$1(focusTargetNode, activeChild, i, recomposeScopeImpl$end$1$2));
            }
            if (ordinal != 2) {
                if (ordinal == 3) {
                    return focusTargetNode.fetchFocusProperties$ui_release().canFocus ? (Boolean) recomposeScopeImpl$end$1$2.invoke(focusTargetNode) : Boolean.FALSE;
                }
                throw new RuntimeException();
            }
        }
        return Boolean.valueOf(m10findChildCorrespondingToFocusEnterOMvw8(focusTargetNode, i, recomposeScopeImpl$end$1$2));
    }

    public abstract boolean casListeners(AbstractResolvableFuture abstractResolvableFuture, AbstractResolvableFuture.Listener listener);

    public abstract boolean casValue(AbstractResolvableFuture abstractResolvableFuture, Object obj, Object obj2);

    public abstract boolean casWaiters(AbstractResolvableFuture abstractResolvableFuture, AbstractResolvableFuture.Waiter waiter, AbstractResolvableFuture.Waiter waiter2);

    public abstract void putNext(AbstractResolvableFuture.Waiter waiter, AbstractResolvableFuture.Waiter waiter2);

    public abstract void putThread(AbstractResolvableFuture.Waiter waiter, Thread thread);

    /* JADX WARN: Type inference failed for: r0v2, types: [androidx.emoji2.text.flatbuffer.MetadataList, androidx.emoji2.text.flatbuffer.Table] */
    public static MetadataList read(MappedByteBuffer mappedByteBuffer) {
        long j;
        ByteBuffer duplicate = mappedByteBuffer.duplicate();
        Stack stack = new Stack(duplicate);
        stack.skip(4);
        int i = ((ByteBuffer) stack.backing).getShort() & 65535;
        if (i <= 100) {
            stack.skip(6);
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    j = -1;
                    break;
                }
                int i3 = ((ByteBuffer) stack.backing).getInt();
                stack.skip(4);
                j = stack.readUnsignedInt();
                stack.skip(4);
                if (1835365473 == i3) {
                    break;
                }
                i2++;
            }
            if (j != -1) {
                stack.skip((int) (j - ((ByteBuffer) stack.backing).position()));
                stack.skip(12);
                long readUnsignedInt = stack.readUnsignedInt();
                for (int i4 = 0; i4 < readUnsignedInt; i4++) {
                    int i5 = ((ByteBuffer) stack.backing).getInt();
                    long readUnsignedInt2 = stack.readUnsignedInt();
                    stack.readUnsignedInt();
                    if (1164798569 == i5 || 1701669481 == i5) {
                        duplicate.position((int) (readUnsignedInt2 + j));
                        ?? table = new Table();
                        duplicate.order(ByteOrder.LITTLE_ENDIAN);
                        int position = duplicate.position() + duplicate.getInt(duplicate.position());
                        table.bb = duplicate;
                        table.bb_pos = position;
                        int i6 = position - duplicate.getInt(position);
                        table.vtable_start = i6;
                        table.vtable_size = table.bb.getShort(i6);
                        return table;
                    }
                }
            }
            throw new IOException("Cannot read metadata.");
        }
        throw new IOException("Cannot read metadata.");
    }
}
