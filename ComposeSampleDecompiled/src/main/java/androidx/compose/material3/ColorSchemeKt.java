package androidx.compose.material3;

import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;
import androidx.compose.material3.tokens.ColorDarkTokens;
import androidx.compose.material3.tokens.ColorLightTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import kotlin.ResultKt;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public abstract class ColorSchemeKt {
    public static final StaticProvidableCompositionLocal LocalColorScheme = new CompositionLocal(ShapesKt$LocalShapes$1.INSTANCE$1);

    /* renamed from: darkColorScheme-G1PFc-w$default, reason: not valid java name */
    public static ColorScheme m54darkColorSchemeG1PFcw$default(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, int i) {
        return new ColorScheme(j, (i & 2) != 0 ? ColorDarkTokens.OnPrimary : j2, (i & 4) != 0 ? ColorDarkTokens.PrimaryContainer : j3, (i & 8) != 0 ? ColorDarkTokens.OnPrimaryContainer : j4, (i & 16) != 0 ? ColorDarkTokens.InversePrimary : j5, j6, (i & 64) != 0 ? ColorDarkTokens.OnSecondary : j7, (i & 128) != 0 ? ColorDarkTokens.SecondaryContainer : j8, (i & 256) != 0 ? ColorDarkTokens.OnSecondaryContainer : j9, j10, (i & 1024) != 0 ? ColorDarkTokens.OnTertiary : j11, (i & 2048) != 0 ? ColorDarkTokens.TertiaryContainer : j12, (i & 4096) != 0 ? ColorDarkTokens.OnTertiaryContainer : j13, (i & 8192) != 0 ? ColorDarkTokens.Background : j14, (i & 16384) != 0 ? ColorDarkTokens.OnBackground : j15, (32768 & i) != 0 ? ColorDarkTokens.Surface : j16, (65536 & i) != 0 ? ColorDarkTokens.OnSurface : j17, (131072 & i) != 0 ? ColorDarkTokens.SurfaceVariant : j18, (262144 & i) != 0 ? ColorDarkTokens.OnSurfaceVariant : j19, j, (1048576 & i) != 0 ? ColorDarkTokens.InverseSurface : j20, (2097152 & i) != 0 ? ColorDarkTokens.InverseOnSurface : j21, ColorDarkTokens.Error, ColorDarkTokens.OnError, ColorDarkTokens.ErrorContainer, ColorDarkTokens.OnErrorContainer, (i & 67108864) != 0 ? ColorDarkTokens.Outline : j22, ColorDarkTokens.OutlineVariant, ColorDarkTokens.Scrim);
    }

    /* renamed from: lightColorScheme-G1PFc-w$default, reason: not valid java name */
    public static ColorScheme m55lightColorSchemeG1PFcw$default(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, int i) {
        long j23 = (i & 1) != 0 ? ColorLightTokens.Primary : j;
        return new ColorScheme(j23, (i & 2) != 0 ? ColorLightTokens.OnPrimary : j2, (i & 4) != 0 ? ColorLightTokens.PrimaryContainer : j3, (i & 8) != 0 ? ColorLightTokens.OnPrimaryContainer : j4, (i & 16) != 0 ? ColorLightTokens.InversePrimary : j5, (i & 32) != 0 ? ColorLightTokens.Secondary : j6, (i & 64) != 0 ? ColorLightTokens.OnSecondary : j7, (i & 128) != 0 ? ColorLightTokens.SecondaryContainer : j8, (i & 256) != 0 ? ColorLightTokens.OnSecondaryContainer : j9, (i & 512) != 0 ? ColorLightTokens.Tertiary : j10, (i & 1024) != 0 ? ColorLightTokens.OnTertiary : j11, (i & 2048) != 0 ? ColorLightTokens.TertiaryContainer : j12, (i & 4096) != 0 ? ColorLightTokens.OnTertiaryContainer : j13, (i & 8192) != 0 ? ColorLightTokens.Background : j14, (i & 16384) != 0 ? ColorLightTokens.OnBackground : j15, (32768 & i) != 0 ? ColorLightTokens.Surface : j16, (65536 & i) != 0 ? ColorLightTokens.OnSurface : j17, (131072 & i) != 0 ? ColorLightTokens.SurfaceVariant : j18, (262144 & i) != 0 ? ColorLightTokens.OnSurfaceVariant : j19, j23, (1048576 & i) != 0 ? ColorLightTokens.InverseSurface : j20, (2097152 & i) != 0 ? ColorLightTokens.InverseOnSurface : j21, ColorLightTokens.Error, ColorLightTokens.OnError, ColorLightTokens.ErrorContainer, ColorLightTokens.OnErrorContainer, (i & 67108864) != 0 ? ColorLightTokens.Outline : j22, ColorLightTokens.OutlineVariant, ColorLightTokens.Scrim);
    }

    public static final long toColor(int i, Composer composer) {
        DurationKt$$ExternalSyntheticCheckNotZero0.m(i, "<this>");
        ColorScheme colorScheme = (ColorScheme) ((ComposerImpl) composer).consume(LocalColorScheme);
        ResultKt.checkNotNullParameter(colorScheme, "<this>");
        switch (AnimationEndReason$EnumUnboxingLocalUtility.ordinal(i)) {
            case 0:
                return colorScheme.m51getBackground0d7_KjU();
            case 1:
                return ((Color) colorScheme.error$delegate.getValue()).value;
            case 2:
                return ((Color) colorScheme.errorContainer$delegate.getValue()).value;
            case 3:
                return ((Color) colorScheme.inverseOnSurface$delegate.getValue()).value;
            case 4:
                return ((Color) colorScheme.inversePrimary$delegate.getValue()).value;
            case 5:
                return ((Color) colorScheme.inverseSurface$delegate.getValue()).value;
            case 6:
                return ((Color) colorScheme.onBackground$delegate.getValue()).value;
            case 7:
                return ((Color) colorScheme.onError$delegate.getValue()).value;
            case 8:
                return ((Color) colorScheme.onErrorContainer$delegate.getValue()).value;
            case 9:
                return ((Color) colorScheme.onPrimary$delegate.getValue()).value;
            case 10:
                return ((Color) colorScheme.onPrimaryContainer$delegate.getValue()).value;
            case 11:
                return ((Color) colorScheme.onSecondary$delegate.getValue()).value;
            case 12:
                return ((Color) colorScheme.onSecondaryContainer$delegate.getValue()).value;
            case 13:
                return ((Color) colorScheme.onSurface$delegate.getValue()).value;
            case 14:
                return ((Color) colorScheme.onSurfaceVariant$delegate.getValue()).value;
            case 15:
                return ((Color) colorScheme.onTertiary$delegate.getValue()).value;
            case 16:
                return ((Color) colorScheme.onTertiaryContainer$delegate.getValue()).value;
            case 17:
                return ((Color) colorScheme.outline$delegate.getValue()).value;
            case 18:
                return ((Color) colorScheme.outlineVariant$delegate.getValue()).value;
            case 19:
                return colorScheme.m52getPrimary0d7_KjU();
            case 20:
                return ((Color) colorScheme.primaryContainer$delegate.getValue()).value;
            case 21:
                return ((Color) colorScheme.scrim$delegate.getValue()).value;
            case 22:
                return ((Color) colorScheme.secondary$delegate.getValue()).value;
            case 23:
                return ((Color) colorScheme.secondaryContainer$delegate.getValue()).value;
            case 24:
                return colorScheme.m53getSurface0d7_KjU();
            case 25:
                return ((Color) colorScheme.surfaceTint$delegate.getValue()).value;
            case 26:
                return ((Color) colorScheme.surfaceVariant$delegate.getValue()).value;
            case 27:
                return ((Color) colorScheme.tertiary$delegate.getValue()).value;
            case 28:
                return ((Color) colorScheme.tertiaryContainer$delegate.getValue()).value;
            default:
                throw new RuntimeException();
        }
    }
}
