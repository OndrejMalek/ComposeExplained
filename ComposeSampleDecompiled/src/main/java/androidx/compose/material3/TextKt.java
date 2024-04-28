package androidx.compose.material3;

import _COROUTINE._BOUNDARY;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.StructuralEqualityPolicy;
import androidx.compose.ui.text.TextStyle;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public abstract class TextKt {
    public static final DynamicProvidableCompositionLocal LocalTextStyle = new DynamicProvidableCompositionLocal(StructuralEqualityPolicy.INSTANCE, ShapesKt$LocalShapes$1.INSTANCE$5);

    public static final void ProvideTextStyle(TextStyle textStyle, Function2 function2, Composer composer, int i) {
        int i2;
        ResultKt.checkNotNullParameter(textStyle, "value");
        ResultKt.checkNotNullParameter(function2, "content");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-460300127);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changed(textStyle) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerImpl.changedInstance(function2) ? 32 : 16;
        }
        if ((i2 & 91) == 18 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            DynamicProvidableCompositionLocal dynamicProvidableCompositionLocal = LocalTextStyle;
            _BOUNDARY.CompositionLocalProvider(new ProvidedValue[]{dynamicProvidableCompositionLocal.provides(((TextStyle) composerImpl.consume(dynamicProvidableCompositionLocal)).merge(textStyle))}, function2, composerImpl, (i2 & 112) | 8);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.block = new TextKt$ProvideTextStyle$1(i, 0, textStyle, function2);
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02d1  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02d9  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x02fb  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x02cd  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x02b3  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01e9  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x014f  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x016e  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01a9  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01c3  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x03e9  */
    /* JADX WARN: Removed duplicated region for block: B:74:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0260  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x031a  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02ab  */
    /* renamed from: Text--4IGK_g, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m58Text4IGK_g(final java.lang.String r37, androidx.compose.ui.Modifier r38, long r39, long r41, androidx.compose.ui.text.font.FontStyle r43, androidx.compose.ui.text.font.FontWeight r44, androidx.compose.ui.text.font.FontFamily r45, long r46, androidx.compose.ui.text.style.TextDecoration r48, androidx.compose.ui.text.style.TextAlign r49, long r50, int r52, boolean r53, int r54, int r55, kotlin.jvm.functions.Function1 r56, androidx.compose.ui.text.TextStyle r57, androidx.compose.runtime.Composer r58, final int r59, final int r60, final int r61) {
        /*
            r1 = r37
            r14 = r59
            r15 = r60
            r13 = r61
            java.lang.String r0 = "text"
            kotlin.ResultKt.checkNotNullParameter(r1, r0)
            r0 = r58
            androidx.compose.runtime.ComposerImpl r0 = (androidx.compose.runtime.ComposerImpl) r0
            r2 = -2055108902(0xffffffff858186da, float:-1.2180638E-35)
            r0.startRestartGroup(r2)
            r2 = r13 & 1
            if (r2 == 0) goto L1e
            r2 = r14 | 6
            goto L2e
        L1e:
            r2 = r14 & 14
            if (r2 != 0) goto L2d
            boolean r2 = r0.changed(r1)
            if (r2 == 0) goto L2a
            r2 = 4
            goto L2b
        L2a:
            r2 = 2
        L2b:
            r2 = r2 | r14
            goto L2e
        L2d:
            r2 = r14
        L2e:
            r5 = r13 & 2
            if (r5 == 0) goto L37
            r2 = r2 | 48
        L34:
            r8 = r38
            goto L49
        L37:
            r8 = r14 & 112(0x70, float:1.57E-43)
            if (r8 != 0) goto L34
            r8 = r38
            boolean r9 = r0.changed(r8)
            if (r9 == 0) goto L46
            r9 = 32
            goto L48
        L46:
            r9 = 16
        L48:
            r2 = r2 | r9
        L49:
            r9 = r13 & 4
            if (r9 == 0) goto L52
            r2 = r2 | 384(0x180, float:5.38E-43)
            r3 = r39
            goto L65
        L52:
            r12 = r14 & 896(0x380, float:1.256E-42)
            r3 = r39
            if (r12 != 0) goto L65
            boolean r16 = r0.changed(r3)
            if (r16 == 0) goto L61
            r16 = 256(0x100, float:3.59E-43)
            goto L63
        L61:
            r16 = 128(0x80, float:1.794E-43)
        L63:
            r2 = r2 | r16
        L65:
            r16 = r13 & 8
            r17 = 2048(0x800, float:2.87E-42)
            r18 = 1024(0x400, float:1.435E-42)
            if (r16 == 0) goto L72
            r2 = r2 | 3072(0xc00, float:4.305E-42)
            r7 = r41
            goto L85
        L72:
            r6 = r14 & 7168(0x1c00, float:1.0045E-41)
            r7 = r41
            if (r6 != 0) goto L85
            boolean r20 = r0.changed(r7)
            if (r20 == 0) goto L81
            r20 = r17
            goto L83
        L81:
            r20 = r18
        L83:
            r2 = r2 | r20
        L85:
            r20 = r13 & 16
            r21 = 16384(0x4000, float:2.2959E-41)
            r22 = 8192(0x2000, float:1.14794E-41)
            r23 = 57344(0xe000, float:8.0356E-41)
            if (r20 == 0) goto L95
            r2 = r2 | 24576(0x6000, float:3.4438E-41)
            r6 = r43
            goto La8
        L95:
            r24 = r14 & r23
            r6 = r43
            if (r24 != 0) goto La8
            boolean r25 = r0.changed(r6)
            if (r25 == 0) goto La4
            r25 = r21
            goto La6
        La4:
            r25 = r22
        La6:
            r2 = r2 | r25
        La8:
            r25 = r13 & 32
            r26 = 131072(0x20000, float:1.83671E-40)
            r27 = 196608(0x30000, float:2.75506E-40)
            r28 = 458752(0x70000, float:6.42848E-40)
            r29 = 65536(0x10000, float:9.18355E-41)
            if (r25 == 0) goto Lb9
            r2 = r2 | r27
            r10 = r44
            goto Lcc
        Lb9:
            r30 = r14 & r28
            r10 = r44
            if (r30 != 0) goto Lcc
            boolean r31 = r0.changed(r10)
            if (r31 == 0) goto Lc8
            r31 = r26
            goto Lca
        Lc8:
            r31 = r29
        Lca:
            r2 = r2 | r31
        Lcc:
            r31 = r13 & 64
            r32 = 3670016(0x380000, float:5.142788E-39)
            if (r31 == 0) goto Ld9
            r33 = 1572864(0x180000, float:2.204052E-39)
            r2 = r2 | r33
            r11 = r45
            goto Lec
        Ld9:
            r33 = r14 & r32
            r11 = r45
            if (r33 != 0) goto Lec
            boolean r34 = r0.changed(r11)
            if (r34 == 0) goto Le8
            r34 = 1048576(0x100000, float:1.469368E-39)
            goto Lea
        Le8:
            r34 = 524288(0x80000, float:7.34684E-40)
        Lea:
            r2 = r2 | r34
        Lec:
            r12 = r13 & 128(0x80, float:1.794E-43)
            if (r12 == 0) goto Lf7
            r35 = 12582912(0xc00000, float:1.7632415E-38)
            r2 = r2 | r35
            r3 = r46
            goto L10c
        Lf7:
            r35 = 29360128(0x1c00000, float:7.052966E-38)
            r35 = r14 & r35
            r3 = r46
            if (r35 != 0) goto L10c
            boolean r35 = r0.changed(r3)
            if (r35 == 0) goto L108
            r35 = 8388608(0x800000, float:1.17549435E-38)
            goto L10a
        L108:
            r35 = 4194304(0x400000, float:5.877472E-39)
        L10a:
            r2 = r2 | r35
        L10c:
            r1 = r13 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L117
            r35 = 100663296(0x6000000, float:2.4074124E-35)
            r2 = r2 | r35
            r3 = r48
            goto L12b
        L117:
            r35 = 234881024(0xe000000, float:1.5777218E-30)
            r35 = r14 & r35
            r3 = r48
            if (r35 != 0) goto L12b
            boolean r4 = r0.changed(r3)
            if (r4 == 0) goto L128
            r4 = 67108864(0x4000000, float:1.5046328E-36)
            goto L12a
        L128:
            r4 = 33554432(0x2000000, float:9.403955E-38)
        L12a:
            r2 = r2 | r4
        L12b:
            r4 = r13 & 512(0x200, float:7.175E-43)
            if (r4 == 0) goto L136
            r35 = 805306368(0x30000000, float:4.656613E-10)
            r2 = r2 | r35
            r3 = r49
            goto L14b
        L136:
            r35 = 1879048192(0x70000000, float:1.58456325E29)
            r35 = r14 & r35
            r3 = r49
            if (r35 != 0) goto L14b
            boolean r35 = r0.changed(r3)
            if (r35 == 0) goto L147
            r35 = 536870912(0x20000000, float:1.0842022E-19)
            goto L149
        L147:
            r35 = 268435456(0x10000000, float:2.5243549E-29)
        L149:
            r2 = r2 | r35
        L14b:
            r3 = r13 & 1024(0x400, float:1.435E-42)
            if (r3 == 0) goto L154
            r34 = r15 | 6
            r6 = r50
            goto L16a
        L154:
            r35 = r15 & 14
            r6 = r50
            if (r35 != 0) goto L168
            boolean r8 = r0.changed(r6)
            if (r8 == 0) goto L163
            r34 = 4
            goto L165
        L163:
            r34 = 2
        L165:
            r34 = r15 | r34
            goto L16a
        L168:
            r34 = r15
        L16a:
            r8 = r13 & 2048(0x800, float:2.87E-42)
            if (r8 == 0) goto L175
            r34 = r34 | 48
            r6 = r52
        L172:
            r7 = r34
            goto L189
        L175:
            r35 = r15 & 112(0x70, float:1.57E-43)
            r6 = r52
            if (r35 != 0) goto L172
            boolean r7 = r0.changed(r6)
            if (r7 == 0) goto L184
            r24 = 32
            goto L186
        L184:
            r24 = 16
        L186:
            r34 = r34 | r24
            goto L172
        L189:
            r6 = r13 & 4096(0x1000, float:5.74E-42)
            if (r6 == 0) goto L192
            r7 = r7 | 384(0x180, float:5.38E-43)
        L18f:
            r10 = r53
            goto L1a5
        L192:
            r10 = r15 & 896(0x380, float:1.256E-42)
            if (r10 != 0) goto L18f
            r10 = r53
            boolean r19 = r0.changed(r10)
            if (r19 == 0) goto L1a1
            r30 = 256(0x100, float:3.59E-43)
            goto L1a3
        L1a1:
            r30 = 128(0x80, float:1.794E-43)
        L1a3:
            r7 = r7 | r30
        L1a5:
            r10 = r13 & 8192(0x2000, float:1.14794E-41)
            if (r10 == 0) goto L1ae
            r7 = r7 | 3072(0xc00, float:4.305E-42)
        L1ab:
            r11 = r54
            goto L1bf
        L1ae:
            r11 = r15 & 7168(0x1c00, float:1.0045E-41)
            if (r11 != 0) goto L1ab
            r11 = r54
            boolean r19 = r0.changed(r11)
            if (r19 == 0) goto L1bb
            goto L1bd
        L1bb:
            r17 = r18
        L1bd:
            r7 = r7 | r17
        L1bf:
            r11 = r13 & 16384(0x4000, float:2.2959E-41)
            if (r11 == 0) goto L1ca
            r7 = r7 | 24576(0x6000, float:3.4438E-41)
        L1c5:
            r17 = r11
            r11 = r55
            goto L1dd
        L1ca:
            r17 = r15 & r23
            if (r17 != 0) goto L1c5
            r17 = r11
            r11 = r55
            boolean r18 = r0.changed(r11)
            if (r18 == 0) goto L1d9
            goto L1db
        L1d9:
            r21 = r22
        L1db:
            r7 = r7 | r21
        L1dd:
            r18 = 32768(0x8000, float:4.5918E-41)
            r18 = r13 & r18
            if (r18 == 0) goto L1e9
            r7 = r7 | r27
            r11 = r56
            goto L1fa
        L1e9:
            r19 = r15 & r28
            r11 = r56
            if (r19 != 0) goto L1fa
            boolean r19 = r0.changedInstance(r11)
            if (r19 == 0) goto L1f6
            goto L1f8
        L1f6:
            r26 = r29
        L1f8:
            r7 = r7 | r26
        L1fa:
            r19 = r15 & r32
            if (r19 != 0) goto L212
            r19 = r13 & r29
            r11 = r57
            if (r19 != 0) goto L20d
            boolean r19 = r0.changed(r11)
            if (r19 == 0) goto L20d
            r19 = 1048576(0x100000, float:1.469368E-39)
            goto L20f
        L20d:
            r19 = 524288(0x80000, float:7.34684E-40)
        L20f:
            r7 = r7 | r19
            goto L214
        L212:
            r11 = r57
        L214:
            r19 = 1533916891(0x5b6db6db, float:6.6910621E16)
            r11 = r2 & r19
            r15 = 306783378(0x12492492, float:6.3469493E-28)
            if (r11 != r15) goto L253
            r11 = 2995931(0x2db6db, float:4.198194E-39)
            r11 = r11 & r7
            r15 = 599186(0x92492, float:8.39638E-40)
            if (r11 != r15) goto L253
            boolean r11 = r0.getSkipping()
            if (r11 != 0) goto L22e
            goto L253
        L22e:
            r0.skipToGroupEnd()
            r2 = r38
            r3 = r39
            r5 = r41
            r7 = r43
            r8 = r44
            r9 = r45
            r10 = r46
            r12 = r48
            r13 = r49
            r14 = r50
            r16 = r52
            r17 = r53
            r18 = r54
            r19 = r55
            r20 = r56
            r21 = r57
            goto L3e2
        L253:
            r11 = 0
            r15 = 0
            r58 = r2
            r2 = -127(0xffffffffffffff81, float:NaN)
            r0.m59startBaiHCIY(r2, r15, r11, r11)
            r2 = r14 & 1
            if (r2 == 0) goto L294
            boolean r2 = r0.getDefaultsInvalid()
            if (r2 == 0) goto L267
            goto L294
        L267:
            r0.skipToGroupEnd()
            r1 = r13 & r29
            if (r1 == 0) goto L272
            r1 = -3670017(0xffffffffffc7ffff, float:NaN)
            r7 = r7 & r1
        L272:
            r2 = r38
            r21 = r39
            r15 = r41
            r5 = r43
            r9 = r44
            r19 = r45
            r24 = r46
            r1 = r48
            r4 = r49
            r26 = r50
            r8 = r52
            r6 = r53
            r10 = r54
            r3 = r55
            r12 = r56
        L290:
            r11 = r57
            goto L308
        L294:
            if (r5 == 0) goto L299
            androidx.compose.ui.Modifier$Companion r2 = androidx.compose.ui.Modifier.Companion.$$INSTANCE
            goto L29b
        L299:
            r2 = r38
        L29b:
            if (r9 == 0) goto L2a0
            long r21 = androidx.compose.ui.graphics.Color.Unspecified
            goto L2a2
        L2a0:
            r21 = r39
        L2a2:
            if (r16 == 0) goto L2a7
            long r15 = androidx.compose.ui.unit.TextUnit.Unspecified
            goto L2a9
        L2a7:
            r15 = r41
        L2a9:
            if (r20 == 0) goto L2ad
            r5 = 0
            goto L2af
        L2ad:
            r5 = r43
        L2af:
            if (r25 == 0) goto L2b3
            r9 = 0
            goto L2b5
        L2b3:
            r9 = r44
        L2b5:
            if (r31 == 0) goto L2ba
            r19 = 0
            goto L2bc
        L2ba:
            r19 = r45
        L2bc:
            if (r12 == 0) goto L2c1
            long r24 = androidx.compose.ui.unit.TextUnit.Unspecified
            goto L2c3
        L2c1:
            r24 = r46
        L2c3:
            if (r1 == 0) goto L2c7
            r1 = 0
            goto L2c9
        L2c7:
            r1 = r48
        L2c9:
            if (r4 == 0) goto L2cd
            r4 = 0
            goto L2cf
        L2cd:
            r4 = r49
        L2cf:
            if (r3 == 0) goto L2d4
            long r26 = androidx.compose.ui.unit.TextUnit.Unspecified
            goto L2d6
        L2d4:
            r26 = r50
        L2d6:
            r3 = 1
            if (r8 == 0) goto L2db
            r8 = r3
            goto L2dd
        L2db:
            r8 = r52
        L2dd:
            if (r6 == 0) goto L2e1
            r6 = r3
            goto L2e3
        L2e1:
            r6 = r53
        L2e3:
            if (r10 == 0) goto L2e9
            r10 = 2147483647(0x7fffffff, float:NaN)
            goto L2eb
        L2e9:
            r10 = r54
        L2eb:
            if (r17 == 0) goto L2ee
            goto L2f0
        L2ee:
            r3 = r55
        L2f0:
            if (r18 == 0) goto L2f5
            androidx.compose.material3.TextKt$Text$1 r12 = androidx.compose.material3.TextKt$Text$1.INSTANCE
            goto L2f7
        L2f5:
            r12 = r56
        L2f7:
            r17 = r13 & r29
            if (r17 == 0) goto L290
            androidx.compose.runtime.DynamicProvidableCompositionLocal r11 = androidx.compose.material3.TextKt.LocalTextStyle
            java.lang.Object r11 = r0.consume(r11)
            androidx.compose.ui.text.TextStyle r11 = (androidx.compose.ui.text.TextStyle) r11
            r18 = -3670017(0xffffffffffc7ffff, float:NaN)
            r7 = r7 & r18
        L308:
            r0.endDefaults()
            r13 = 79582822(0x4be5666, float:4.474813E-36)
            r0.startReplaceableGroup(r13)
            long r29 = androidx.compose.ui.graphics.Color.Unspecified
            int r13 = (r21 > r29 ? 1 : (r21 == r29 ? 0 : -1))
            if (r13 == 0) goto L31a
            r33 = r21
            goto L32f
        L31a:
            long r33 = r11.m250getColor0d7_KjU()
            int r13 = (r33 > r29 ? 1 : (r33 == r29 ? 0 : -1))
            if (r13 == 0) goto L323
            goto L32f
        L323:
            androidx.compose.runtime.DynamicProvidableCompositionLocal r13 = androidx.compose.material3.ContentColorKt.LocalContentColor
            java.lang.Object r13 = r0.consume(r13)
            androidx.compose.ui.graphics.Color r13 = (androidx.compose.ui.graphics.Color) r13
            long r13 = r13.value
            r33 = r13
        L32f:
            r13 = 0
            r0.end(r13)
            androidx.compose.ui.text.TextStyle r13 = new androidx.compose.ui.text.TextStyle
            androidx.compose.ui.text.SpanStyle r14 = new androidx.compose.ui.text.SpanStyle
            r18 = 0
            r56 = r18
            r18 = 32768(0x8000, float:4.5918E-41)
            r57 = r18
            r18 = 0
            r45 = r18
            r47 = r18
            r50 = r18
            r51 = r18
            r52 = r18
            r38 = r14
            r39 = r33
            r41 = r15
            r43 = r9
            r44 = r5
            r46 = r19
            r48 = r24
            r53 = r29
            r55 = r1
            r38.<init>(r39, r41, r43, r44, r45, r46, r47, r48, r50, r51, r52, r53, r55, r56, r57)
            r49 = r1
            androidx.compose.ui.text.ParagraphStyle r1 = new androidx.compose.ui.text.ParagraphStyle
            r18 = 0
            r20 = 0
            r29 = 0
            r30 = 0
            r31 = 0
            r33 = 0
            r34 = 256(0x100, float:3.59E-43)
            r38 = r1
            r39 = r4
            r40 = r29
            r41 = r26
            r43 = r30
            r44 = r31
            r45 = r33
            r46 = r18
            r47 = r20
            r48 = r34
            r38.<init>(r39, r40, r41, r43, r44, r45, r46, r47, r48)
            r50 = r4
            r4 = 0
            r13.<init>(r14, r1, r4)
            androidx.compose.ui.text.TextStyle r1 = r11.merge(r13)
            r4 = r58 & 14
            r13 = r58 & 112(0x70, float:1.57E-43)
            r4 = r4 | r13
            int r13 = r7 >> 6
            r13 = r13 & 7168(0x1c00, float:1.0045E-41)
            r4 = r4 | r13
            int r7 = r7 << 9
            r13 = r7 & r23
            r4 = r4 | r13
            r13 = r7 & r28
            r4 = r4 | r13
            r13 = r7 & r32
            r4 = r4 | r13
            r13 = 29360128(0x1c00000, float:7.052966E-38)
            r7 = r7 & r13
            r4 = r4 | r7
            r7 = 0
            r38 = r37
            r39 = r2
            r40 = r1
            r41 = r12
            r42 = r8
            r43 = r6
            r44 = r10
            r45 = r3
            r46 = r0
            r47 = r4
            r48 = r7
            _COROUTINE._BOUNDARY.m0BasicText4YKlhWE(r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48)
            r13 = r50
            r7 = r5
            r17 = r6
            r18 = r10
            r20 = r12
            r5 = r15
            r14 = r26
            r12 = r49
            r16 = r8
            r8 = r9
            r9 = r19
            r19 = r3
            r3 = r21
            r21 = r11
            r10 = r24
        L3e2:
            androidx.compose.runtime.RecomposeScopeImpl r1 = r0.endRestartGroup()
            if (r1 != 0) goto L3e9
            goto L400
        L3e9:
            androidx.compose.material3.TextKt$Text$2 r0 = new androidx.compose.material3.TextKt$Text$2
            r38 = r0
            r36 = r1
            r1 = r37
            r22 = r59
            r23 = r60
            r24 = r61
            r0.<init>()
            r1 = r38
            r0 = r36
            r0.block = r1
        L400:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextKt.m58Text4IGK_g(java.lang.String, androidx.compose.ui.Modifier, long, long, androidx.compose.ui.text.font.FontStyle, androidx.compose.ui.text.font.FontWeight, androidx.compose.ui.text.font.FontFamily, long, androidx.compose.ui.text.style.TextDecoration, androidx.compose.ui.text.style.TextAlign, long, int, boolean, int, int, kotlin.jvm.functions.Function1, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int, int):void");
    }
}
