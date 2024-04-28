package androidx.compose.material3;

import androidx.compose.material.ripple.RippleAlpha;
import androidx.compose.ui.text.PlatformParagraphStyle;
import androidx.compose.ui.text.PlatformTextStyle;

/* loaded from: classes.dex */
public abstract class MaterialThemeKt {
    public static final PlatformTextStyle DefaultPlatformTextStyle = new PlatformTextStyle(new PlatformParagraphStyle());
    public static final RippleAlpha DefaultRippleAlpha = new RippleAlpha(0.16f, 0.12f, 0.08f, 0.12f);

    /* JADX WARN: Code restructure failed: missing block: B:51:0x00b2, code lost:
    
        if ((r79 & 4) != 0) goto L65;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void MaterialTheme(androidx.compose.material3.ColorScheme r73, androidx.compose.material3.Shapes r74, androidx.compose.material3.Typography r75, final kotlin.jvm.functions.Function2 r76, androidx.compose.runtime.Composer r77, final int r78, final int r79) {
        /*
            r4 = r76
            r5 = r78
            java.lang.String r0 = "content"
            kotlin.ResultKt.checkNotNullParameter(r4, r0)
            r0 = r77
            androidx.compose.runtime.ComposerImpl r0 = (androidx.compose.runtime.ComposerImpl) r0
            r1 = -2127166334(0xffffffff81360482, float:-3.3431355E-38)
            r0.startRestartGroup(r1)
            r1 = r5 & 14
            if (r1 != 0) goto L2a
            r1 = r79 & 1
            if (r1 != 0) goto L25
            r1 = r73
            boolean r2 = r0.changed(r1)
            if (r2 == 0) goto L27
            r2 = 4
            goto L28
        L25:
            r1 = r73
        L27:
            r2 = 2
        L28:
            r2 = r2 | r5
            goto L2d
        L2a:
            r1 = r73
            r2 = r5
        L2d:
            r3 = r5 & 112(0x70, float:1.57E-43)
            if (r3 != 0) goto L46
            r3 = r79 & 2
            if (r3 != 0) goto L40
            r3 = r74
            boolean r6 = r0.changed(r3)
            if (r6 == 0) goto L42
            r6 = 32
            goto L44
        L40:
            r3 = r74
        L42:
            r6 = 16
        L44:
            r2 = r2 | r6
            goto L48
        L46:
            r3 = r74
        L48:
            r6 = r5 & 896(0x380, float:1.256E-42)
            if (r6 != 0) goto L61
            r6 = r79 & 4
            if (r6 != 0) goto L5b
            r6 = r75
            boolean r7 = r0.changed(r6)
            if (r7 == 0) goto L5d
            r7 = 256(0x100, float:3.59E-43)
            goto L5f
        L5b:
            r6 = r75
        L5d:
            r7 = 128(0x80, float:1.794E-43)
        L5f:
            r2 = r2 | r7
            goto L63
        L61:
            r6 = r75
        L63:
            r7 = r79 & 8
            if (r7 == 0) goto L6a
            r2 = r2 | 3072(0xc00, float:4.305E-42)
            goto L7a
        L6a:
            r7 = r5 & 7168(0x1c00, float:1.0045E-41)
            if (r7 != 0) goto L7a
            boolean r7 = r0.changedInstance(r4)
            if (r7 == 0) goto L77
            r7 = 2048(0x800, float:2.87E-42)
            goto L79
        L77:
            r7 = 1024(0x400, float:1.435E-42)
        L79:
            r2 = r2 | r7
        L7a:
            r7 = r2 & 5851(0x16db, float:8.199E-42)
            r8 = 1170(0x492, float:1.64E-42)
            if (r7 != r8) goto L8f
            boolean r7 = r0.getSkipping()
            if (r7 != 0) goto L87
            goto L8f
        L87:
            r0.skipToGroupEnd()
            r2 = r3
            r7 = r4
        L8c:
            r3 = r6
            goto L52d
        L8f:
            r7 = -127(0xffffffffffffff81, float:NaN)
            r8 = 0
            r9 = 0
            r0.m59startBaiHCIY(r7, r9, r8, r8)
            r7 = r5 & 1
            if (r7 == 0) goto Lb7
            boolean r7 = r0.getDefaultsInvalid()
            if (r7 == 0) goto La1
            goto Lb7
        La1:
            r0.skipToGroupEnd()
            r7 = r79 & 1
            if (r7 == 0) goto Laa
            r2 = r2 & (-15)
        Laa:
            r7 = r79 & 2
            if (r7 == 0) goto Lb0
            r2 = r2 & (-113(0xffffffffffffff8f, float:NaN))
        Lb0:
            r7 = r79 & 4
            if (r7 == 0) goto Le0
        Lb4:
            r2 = r2 & (-897(0xfffffffffffffc7f, float:NaN))
            goto Le0
        Lb7:
            r7 = r79 & 1
            if (r7 == 0) goto Lc5
            androidx.compose.runtime.StaticProvidableCompositionLocal r1 = androidx.compose.material3.ColorSchemeKt.LocalColorScheme
            java.lang.Object r1 = r0.consume(r1)
            androidx.compose.material3.ColorScheme r1 = (androidx.compose.material3.ColorScheme) r1
            r2 = r2 & (-15)
        Lc5:
            r7 = r79 & 2
            if (r7 == 0) goto Ld3
            androidx.compose.runtime.StaticProvidableCompositionLocal r3 = androidx.compose.material3.ShapesKt.LocalShapes
            java.lang.Object r3 = r0.consume(r3)
            androidx.compose.material3.Shapes r3 = (androidx.compose.material3.Shapes) r3
            r2 = r2 & (-113(0xffffffffffffff8f, float:NaN))
        Ld3:
            r7 = r79 & 4
            if (r7 == 0) goto Le0
            androidx.compose.runtime.StaticProvidableCompositionLocal r6 = androidx.compose.material3.TypographyKt.LocalTypography
            java.lang.Object r6 = r0.consume(r6)
            androidx.compose.material3.Typography r6 = (androidx.compose.material3.Typography) r6
            goto Lb4
        Le0:
            r0.endDefaults()
            r7 = -492369756(0xffffffffe2a708a4, float:-1.5406144E21)
            r0.startReplaceableGroup(r7)
            java.lang.Object r7 = r0.nextSlot()
            _COROUTINE.ArtificialStackFrames r8 = androidx.compose.runtime.Composer.Companion.Empty
            if (r7 != r8) goto L253
            long r11 = r1.m52getPrimary0d7_KjU()
            androidx.compose.runtime.ParcelableSnapshotMutableState r7 = r1.onPrimary$delegate
            java.lang.Object r7 = r7.getValue()
            androidx.compose.ui.graphics.Color r7 = (androidx.compose.ui.graphics.Color) r7
            long r13 = r7.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r7 = r1.primaryContainer$delegate
            java.lang.Object r7 = r7.getValue()
            androidx.compose.ui.graphics.Color r7 = (androidx.compose.ui.graphics.Color) r7
            long r9 = r7.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r7 = r1.onPrimaryContainer$delegate
            java.lang.Object r7 = r7.getValue()
            androidx.compose.ui.graphics.Color r7 = (androidx.compose.ui.graphics.Color) r7
            long r4 = r7.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r7 = r1.inversePrimary$delegate
            java.lang.Object r7 = r7.getValue()
            androidx.compose.ui.graphics.Color r7 = (androidx.compose.ui.graphics.Color) r7
            r73 = r6
            long r6 = r7.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r15 = r1.secondary$delegate
            java.lang.Object r15 = r15.getValue()
            androidx.compose.ui.graphics.Color r15 = (androidx.compose.ui.graphics.Color) r15
            r75 = r2
            r74 = r3
            long r2 = r15.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r15 = r1.onSecondary$delegate
            java.lang.Object r15 = r15.getValue()
            androidx.compose.ui.graphics.Color r15 = (androidx.compose.ui.graphics.Color) r15
            r69 = r8
            r16 = r9
            long r8 = r15.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.secondaryContainer$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r23 = r8
            long r8 = r10.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.onSecondaryContainer$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r25 = r8
            long r8 = r10.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.tertiary$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r27 = r8
            long r8 = r10.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.onTertiary$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r29 = r8
            long r8 = r10.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.tertiaryContainer$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r31 = r8
            long r8 = r10.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.onTertiaryContainer$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r33 = r8
            long r8 = r10.value
            long r37 = r1.m51getBackground0d7_KjU()
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.onBackground$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r35 = r8
            long r8 = r10.value
            long r41 = r1.m53getSurface0d7_KjU()
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.onSurface$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r39 = r8
            long r8 = r10.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.surfaceVariant$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r43 = r8
            long r8 = r10.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.onSurfaceVariant$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r45 = r8
            long r8 = r10.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.surfaceTint$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r47 = r8
            long r8 = r10.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.inverseSurface$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r49 = r8
            long r8 = r10.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.inverseOnSurface$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r51 = r8
            long r8 = r10.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.error$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r53 = r8
            long r8 = r10.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.onError$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r55 = r8
            long r8 = r10.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.errorContainer$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r57 = r8
            long r8 = r10.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.onErrorContainer$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r59 = r8
            long r8 = r10.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.outline$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r61 = r8
            long r8 = r10.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.outlineVariant$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r63 = r8
            long r8 = r10.value
            androidx.compose.runtime.ParcelableSnapshotMutableState r10 = r1.scrim$delegate
            java.lang.Object r10 = r10.getValue()
            androidx.compose.ui.graphics.Color r10 = (androidx.compose.ui.graphics.Color) r10
            r70 = r0
            r71 = r1
            long r0 = r10.value
            androidx.compose.material3.ColorScheme r15 = new androidx.compose.material3.ColorScheme
            r10 = r15
            r72 = r15
            r15 = r16
            r17 = r4
            r19 = r6
            r21 = r2
            r65 = r8
            r67 = r0
            r10.<init>(r11, r13, r15, r17, r19, r21, r23, r25, r27, r29, r31, r33, r35, r37, r39, r41, r43, r45, r47, r49, r51, r53, r55, r57, r59, r61, r63, r65, r67)
            r0 = r70
            r1 = r72
            r0.updateValue(r1)
            r7 = r1
            r1 = 0
            goto L25e
        L253:
            r71 = r1
            r75 = r2
            r74 = r3
            r73 = r6
            r69 = r8
            r1 = r9
        L25e:
            r0.end(r1)
            androidx.compose.material3.ColorScheme r7 = (androidx.compose.material3.ColorScheme) r7
            androidx.compose.runtime.StaticProvidableCompositionLocal r1 = androidx.compose.material3.ColorSchemeKt.LocalColorScheme
            java.lang.String r1 = "<this>"
            kotlin.ResultKt.checkNotNullParameter(r7, r1)
            java.lang.String r1 = "other"
            r2 = r71
            kotlin.ResultKt.checkNotNullParameter(r2, r1)
            long r3 = r2.m52getPrimary0d7_KjU()
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.primary$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.onPrimary$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.onPrimary$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.primaryContainer$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.primaryContainer$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.onPrimaryContainer$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.onPrimaryContainer$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.inversePrimary$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.inversePrimary$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.secondary$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.secondary$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.onSecondary$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.onSecondary$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.secondaryContainer$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.secondaryContainer$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.onSecondaryContainer$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.onSecondaryContainer$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.tertiary$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.tertiary$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.onTertiary$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.onTertiary$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.tertiaryContainer$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.tertiaryContainer$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.onTertiaryContainer$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.onTertiaryContainer$delegate
            r3.setValue(r1)
            long r3 = r2.m51getBackground0d7_KjU()
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.background$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.onBackground$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.onBackground$delegate
            r3.setValue(r1)
            long r3 = r2.m53getSurface0d7_KjU()
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.surface$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.onSurface$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.onSurface$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.surfaceVariant$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.surfaceVariant$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.onSurfaceVariant$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.onSurfaceVariant$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.surfaceTint$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.surfaceTint$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.inverseSurface$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.inverseSurface$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.inverseOnSurface$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.inverseOnSurface$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.error$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.error$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.onError$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.onError$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.errorContainer$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.errorContainer$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.onErrorContainer$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.onErrorContainer$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.outline$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.outline$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.outlineVariant$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.outlineVariant$delegate
            r3.setValue(r1)
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r2.scrim$delegate
            java.lang.Object r1 = r1.getValue()
            androidx.compose.ui.graphics.Color r1 = (androidx.compose.ui.graphics.Color) r1
            long r3 = r1.value
            androidx.compose.ui.graphics.Color r1 = new androidx.compose.ui.graphics.Color
            r1.<init>(r3)
            androidx.compose.runtime.ParcelableSnapshotMutableState r3 = r7.scrim$delegate
            r3.setValue(r1)
            androidx.compose.material.ripple.PlatformRipple r1 = androidx.compose.material.ripple.RippleKt.m49rememberRipple9IZ8Weo(r0)
            r3 = 1866455512(0x6f3fd9d8, float:5.9374994E28)
            r0.startReplaceableGroup(r3)
            long r3 = r7.m52getPrimary0d7_KjU()
            androidx.compose.ui.graphics.Color r5 = new androidx.compose.ui.graphics.Color
            r5.<init>(r3)
            r6 = 1157296644(0x44faf204, float:2007.563)
            r0.startReplaceableGroup(r6)
            boolean r5 = r0.changed(r5)
            java.lang.Object r6 = r0.nextSlot()
            if (r5 != 0) goto L4cd
            r5 = r69
            if (r6 != r5) goto L4cb
            goto L4cd
        L4cb:
            r3 = 0
            goto L4dd
        L4cd:
            androidx.compose.foundation.text.selection.TextSelectionColors r6 = new androidx.compose.foundation.text.selection.TextSelectionColors
            r5 = 1053609165(0x3ecccccd, float:0.4)
            long r8 = androidx.compose.ui.graphics.Color.m120copywmQWz5c$default(r3, r5)
            r6.<init>(r3, r8)
            r0.updateValue(r6)
            goto L4cb
        L4dd:
            r0.end(r3)
            androidx.compose.foundation.text.selection.TextSelectionColors r6 = (androidx.compose.foundation.text.selection.TextSelectionColors) r6
            r0.end(r3)
            androidx.compose.runtime.StaticProvidableCompositionLocal r3 = androidx.compose.material3.ColorSchemeKt.LocalColorScheme
            androidx.compose.runtime.ProvidedValue r8 = r3.provides(r7)
            androidx.compose.runtime.StaticProvidableCompositionLocal r3 = androidx.compose.foundation.IndicationKt.LocalIndication
            androidx.compose.runtime.ProvidedValue r9 = r3.provides(r1)
            androidx.compose.runtime.StaticProvidableCompositionLocal r1 = androidx.compose.material.ripple.RippleThemeKt.LocalRippleTheme
            androidx.compose.material3.MaterialRippleTheme r3 = androidx.compose.material3.MaterialRippleTheme.INSTANCE
            androidx.compose.runtime.ProvidedValue r10 = r1.provides(r3)
            androidx.compose.runtime.StaticProvidableCompositionLocal r1 = androidx.compose.material3.ShapesKt.LocalShapes
            r3 = r74
            androidx.compose.runtime.ProvidedValue r11 = r1.provides(r3)
            androidx.compose.runtime.DynamicProvidableCompositionLocal r1 = androidx.compose.foundation.text.selection.TextSelectionColorsKt.LocalTextSelectionColors
            androidx.compose.runtime.ProvidedValue r12 = r1.provides(r6)
            androidx.compose.runtime.StaticProvidableCompositionLocal r1 = androidx.compose.material3.TypographyKt.LocalTypography
            r6 = r73
            androidx.compose.runtime.ProvidedValue r13 = r1.provides(r6)
            androidx.compose.runtime.ProvidedValue[] r1 = new androidx.compose.runtime.ProvidedValue[]{r8, r9, r10, r11, r12, r13}
            androidx.compose.material3.TextKt$ProvideTextStyle$1 r4 = new androidx.compose.material3.TextKt$ProvideTextStyle$1
            r5 = 1
            r8 = r75
            r7 = r76
            r4.<init>(r8, r5, r6, r7)
            r5 = -1066563262(0xffffffffc06d8942, float:-3.7115026)
            androidx.compose.runtime.internal.ComposableLambdaImpl r4 = kotlin.ResultKt.composableLambda(r0, r5, r4)
            r5 = 56
            _COROUTINE._BOUNDARY.CompositionLocalProvider(r1, r4, r0, r5)
            r1 = r2
            r2 = r3
            goto L8c
        L52d:
            androidx.compose.runtime.RecomposeScopeImpl r8 = r0.endRestartGroup()
            if (r8 != 0) goto L534
            goto L542
        L534:
            androidx.compose.material3.MaterialThemeKt$MaterialTheme$2 r9 = new androidx.compose.material3.MaterialThemeKt$MaterialTheme$2
            r0 = r9
            r4 = r76
            r5 = r78
            r6 = r79
            r0.<init>()
            r8.block = r9
        L542:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.MaterialThemeKt.MaterialTheme(androidx.compose.material3.ColorScheme, androidx.compose.material3.Shapes, androidx.compose.material3.Typography, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }
}
