package eu.malek.composesample2.ui.theme;

import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.ColorSchemeKt;

/* loaded from: classes.dex */
public abstract class ThemeKt {
    public static final ColorScheme DarkColorScheme = ColorSchemeKt.m54darkColorSchemeG1PFcw$default(ColorKt.Purple80, 0, 0, 0, 0, ColorKt.PurpleGrey80, 0, 0, 0, ColorKt.Pink80, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 536870366);
    public static final ColorScheme LightColorScheme = ColorSchemeKt.m55lightColorSchemeG1PFcw$default(ColorKt.Purple40, 0, 0, 0, 0, ColorKt.PurpleGrey40, 0, 0, 0, ColorKt.Pink40, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 536870366);

    /* JADX WARN: Removed duplicated region for block: B:15:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x018a  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ComposeSample2Theme(boolean r58, boolean r59, final kotlin.jvm.functions.Function2 r60, androidx.compose.runtime.Composer r61, final int r62, final int r63) {
        /*
            r7 = r60
            r8 = r62
            java.lang.String r0 = "content"
            kotlin.ResultKt.checkNotNullParameter(r7, r0)
            r9 = r61
            androidx.compose.runtime.ComposerImpl r9 = (androidx.compose.runtime.ComposerImpl) r9
            r0 = -868416647(0xffffffffcc3d0379, float:-4.9548772E7)
            r9.startRestartGroup(r0)
            r0 = r8 & 14
            if (r0 != 0) goto L2a
            r0 = r63 & 1
            if (r0 != 0) goto L25
            r0 = r58
            boolean r1 = r9.changed(r0)
            if (r1 == 0) goto L27
            r1 = 4
            goto L28
        L25:
            r0 = r58
        L27:
            r1 = 2
        L28:
            r1 = r1 | r8
            goto L2d
        L2a:
            r0 = r58
            r1 = r8
        L2d:
            r2 = r63 & 2
            r3 = 32
            if (r2 == 0) goto L38
            r1 = r1 | 48
        L35:
            r4 = r59
            goto L49
        L38:
            r4 = r8 & 112(0x70, float:1.57E-43)
            if (r4 != 0) goto L35
            r4 = r59
            boolean r5 = r9.changed(r4)
            if (r5 == 0) goto L46
            r5 = r3
            goto L48
        L46:
            r5 = 16
        L48:
            r1 = r1 | r5
        L49:
            r5 = r63 & 4
            if (r5 == 0) goto L50
            r1 = r1 | 384(0x180, float:5.38E-43)
            goto L60
        L50:
            r5 = r8 & 896(0x380, float:1.256E-42)
            if (r5 != 0) goto L60
            boolean r5 = r9.changedInstance(r7)
            if (r5 == 0) goto L5d
            r5 = 256(0x100, float:3.59E-43)
            goto L5f
        L5d:
            r5 = 128(0x80, float:1.794E-43)
        L5f:
            r1 = r1 | r5
        L60:
            r5 = r1 & 731(0x2db, float:1.024E-42)
            r6 = 146(0x92, float:2.05E-43)
            if (r5 != r6) goto L74
            boolean r5 = r9.getSkipping()
            if (r5 != 0) goto L6d
            goto L74
        L6d:
            r9.skipToGroupEnd()
            r1 = r0
            r2 = r4
            goto L1d1
        L74:
            r5 = -127(0xffffffffffffff81, float:NaN)
            r6 = 0
            r10 = 0
            r9.m59startBaiHCIY(r5, r10, r6, r6)
            r5 = r8 & 1
            r6 = 1
            if (r5 == 0) goto L93
            boolean r5 = r9.getDefaultsInvalid()
            if (r5 == 0) goto L87
            goto L93
        L87:
            r9.skipToGroupEnd()
            r2 = r63 & 1
            if (r2 == 0) goto L90
            r1 = r1 & (-15)
        L90:
            r11 = r0
            r12 = r4
            goto Lae
        L93:
            r5 = r63 & 1
            if (r5 == 0) goto Laa
            androidx.compose.runtime.DynamicProvidableCompositionLocal r0 = androidx.compose.ui.platform.AndroidCompositionLocals_androidKt.LocalConfiguration
            java.lang.Object r0 = r9.consume(r0)
            android.content.res.Configuration r0 = (android.content.res.Configuration) r0
            int r0 = r0.uiMode
            r0 = r0 & 48
            if (r0 != r3) goto La7
            r0 = r6
            goto La8
        La7:
            r0 = r10
        La8:
            r1 = r1 & (-15)
        Laa:
            if (r2 == 0) goto L90
            r11 = r0
            r12 = r6
        Lae:
            r9.endDefaults()
            r0 = -886036189(0xffffffffcb302923, float:-1.1544867E7)
            r9.startReplaceableGroup(r0)
            if (r12 == 0) goto L188
            int r0 = android.os.Build.VERSION.SDK_INT
            r2 = 31
            if (r0 < r2) goto L188
            androidx.compose.runtime.StaticProvidableCompositionLocal r0 = androidx.compose.ui.platform.AndroidCompositionLocals_androidKt.LocalContext
            java.lang.Object r0 = r9.consume(r0)
            android.content.Context r0 = (android.content.Context) r0
            java.lang.String r2 = "context"
            if (r11 == 0) goto L129
            kotlin.ResultKt.checkNotNullParameter(r0, r2)
            androidx.compose.material3.TonalPalette r0 = _COROUTINE._BOUNDARY.dynamicTonalPalette(r0)
            long r2 = r0.neutralVariant60
            r55 = r2
            r57 = 466092032(0x1bc80000, float:3.3087225E-22)
            long r13 = r0.primary80
            long r2 = r0.primary20
            r15 = r2
            long r2 = r0.primary30
            r17 = r2
            long r2 = r0.primary90
            r19 = r2
            long r2 = r0.primary40
            r21 = r2
            long r2 = r0.secondary80
            r23 = r2
            long r2 = r0.secondary20
            r25 = r2
            long r2 = r0.secondary30
            r27 = r2
            long r2 = r0.secondary90
            r29 = r2
            long r2 = r0.tertiary80
            r31 = r2
            long r2 = r0.tertiary20
            r33 = r2
            long r2 = r0.tertiary30
            r35 = r2
            long r2 = r0.tertiary90
            r37 = r2
            long r2 = r0.neutral10
            r43 = r2
            r39 = r2
            long r2 = r0.neutral90
            r51 = r2
            r41 = r2
            r45 = r2
            long r2 = r0.neutralVariant30
            r47 = r2
            long r2 = r0.neutralVariant80
            r49 = r2
            long r2 = r0.neutral20
            r53 = r2
            androidx.compose.material3.ColorScheme r0 = androidx.compose.material3.ColorSchemeKt.m54darkColorSchemeG1PFcw$default(r13, r15, r17, r19, r21, r23, r25, r27, r29, r31, r33, r35, r37, r39, r41, r43, r45, r47, r49, r51, r53, r55, r57)
            goto L18f
        L129:
            kotlin.ResultKt.checkNotNullParameter(r0, r2)
            androidx.compose.material3.TonalPalette r0 = _COROUTINE._BOUNDARY.dynamicTonalPalette(r0)
            long r2 = r0.neutralVariant50
            r55 = r2
            r57 = 466092032(0x1bc80000, float:3.3087225E-22)
            long r13 = r0.primary40
            long r2 = r0.primary100
            r15 = r2
            long r2 = r0.primary90
            r17 = r2
            long r2 = r0.primary10
            r19 = r2
            long r2 = r0.primary80
            r21 = r2
            long r2 = r0.secondary40
            r23 = r2
            long r2 = r0.secondary100
            r25 = r2
            long r2 = r0.secondary90
            r27 = r2
            long r2 = r0.secondary10
            r29 = r2
            long r2 = r0.tertiary40
            r31 = r2
            long r2 = r0.tertiary100
            r33 = r2
            long r2 = r0.tertiary90
            r35 = r2
            long r2 = r0.tertiary10
            r37 = r2
            long r2 = r0.neutral99
            r43 = r2
            r39 = r2
            long r2 = r0.neutral10
            r41 = r2
            r45 = r2
            long r2 = r0.neutralVariant90
            r47 = r2
            long r2 = r0.neutralVariant30
            r49 = r2
            long r2 = r0.neutral20
            r51 = r2
            long r2 = r0.neutral95
            r53 = r2
            androidx.compose.material3.ColorScheme r0 = androidx.compose.material3.ColorSchemeKt.m55lightColorSchemeG1PFcw$default(r13, r15, r17, r19, r21, r23, r25, r27, r29, r31, r33, r35, r37, r39, r41, r43, r45, r47, r49, r51, r53, r55, r57)
            goto L18f
        L188:
            if (r11 == 0) goto L18d
            androidx.compose.material3.ColorScheme r0 = eu.malek.composesample2.ui.theme.ThemeKt.DarkColorScheme
            goto L18f
        L18d:
            androidx.compose.material3.ColorScheme r0 = eu.malek.composesample2.ui.theme.ThemeKt.LightColorScheme
        L18f:
            r9.end(r10)
            androidx.compose.runtime.StaticProvidableCompositionLocal r2 = androidx.compose.ui.platform.AndroidCompositionLocals_androidKt.LocalView
            java.lang.Object r2 = r9.consume(r2)
            android.view.View r2 = (android.view.View) r2
            r3 = -886035838(0xffffffffcb302a82, float:-1.1545218E7)
            r9.startReplaceableGroup(r3)
            boolean r3 = r2.isInEditMode()
            if (r3 != 0) goto L1bc
            eu.malek.composesample2.ui.theme.ThemeKt$ComposeSample2Theme$1 r3 = new eu.malek.composesample2.ui.theme.ThemeKt$ComposeSample2Theme$1
            r3.<init>(r2, r0, r11)
            r2 = -1288466761(0xffffffffb3338eb7, float:-4.1806484E-8)
            r9.startReplaceableGroup(r2)
            androidx.compose.runtime.ComposerImpl$realizeDowns$1 r2 = new androidx.compose.runtime.ComposerImpl$realizeDowns$1
            r2.<init>(r6, r3)
            r9.record(r2)
            r9.end(r10)
        L1bc:
            r9.end(r10)
            androidx.compose.material3.Typography r2 = eu.malek.composesample2.ui.theme.TypeKt.Typography
            int r1 = r1 << 3
            r1 = r1 & 7168(0x1c00, float:1.0045E-41)
            r5 = r1 | 384(0x180, float:5.38E-43)
            r6 = 2
            r1 = 0
            r3 = r60
            r4 = r9
            androidx.compose.material3.MaterialThemeKt.MaterialTheme(r0, r1, r2, r3, r4, r5, r6)
            r1 = r11
            r2 = r12
        L1d1:
            androidx.compose.runtime.RecomposeScopeImpl r6 = r9.endRestartGroup()
            if (r6 != 0) goto L1d8
            goto L1e6
        L1d8:
            eu.malek.composesample2.ui.theme.ThemeKt$ComposeSample2Theme$2 r9 = new eu.malek.composesample2.ui.theme.ThemeKt$ComposeSample2Theme$2
            r0 = r9
            r3 = r60
            r4 = r62
            r5 = r63
            r0.<init>()
            r6.block = r9
        L1e6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: eu.malek.composesample2.ui.theme.ThemeKt.ComposeSample2Theme(boolean, boolean, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }
}
