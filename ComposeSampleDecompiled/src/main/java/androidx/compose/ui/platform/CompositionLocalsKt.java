package androidx.compose.ui.platform;

import _COROUTINE._BOUNDARY;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.internal.ComposableLambdaImpl$invoke$2;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.text.font.Font$ResourceLoader;
import androidx.compose.ui.text.font.FontFamily;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public abstract class CompositionLocalsKt {
    public static final StaticProvidableCompositionLocal LocalAccessibilityManager = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$8);
    public static final StaticProvidableCompositionLocal LocalAutofill = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$9);
    public static final StaticProvidableCompositionLocal LocalAutofillTree = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$10);
    public static final StaticProvidableCompositionLocal LocalClipboardManager = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$11);
    public static final StaticProvidableCompositionLocal LocalDensity = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE);
    public static final StaticProvidableCompositionLocal LocalFocusManager = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$12);
    public static final StaticProvidableCompositionLocal LocalFontLoader = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$14);
    public static final StaticProvidableCompositionLocal LocalFontFamilyResolver = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$13);
    public static final StaticProvidableCompositionLocal LocalHapticFeedback = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$15);
    public static final StaticProvidableCompositionLocal LocalInputModeManager = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$16);
    public static final StaticProvidableCompositionLocal LocalLayoutDirection = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$17);
    public static final StaticProvidableCompositionLocal LocalTextInputService = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$20);
    public static final StaticProvidableCompositionLocal LocalPlatformTextInputPluginRegistry = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$18);
    public static final StaticProvidableCompositionLocal LocalTextToolbar = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$21);
    public static final StaticProvidableCompositionLocal LocalUriHandler = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$22);
    public static final StaticProvidableCompositionLocal LocalViewConfiguration = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$23);
    public static final StaticProvidableCompositionLocal LocalWindowInfo = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$24);
    public static final StaticProvidableCompositionLocal LocalPointerIconService = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$19);

    public static final void ProvideCommonCompositionLocals(Owner owner, UriHandler uriHandler, Function2 function2, Composer composer, int i) {
        int i2;
        ResultKt.checkNotNullParameter(owner, "owner");
        ResultKt.checkNotNullParameter(uriHandler, "uriHandler");
        ResultKt.checkNotNullParameter(function2, "content");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(874662829);
        if ((i & 14) == 0) {
            i2 = (composerImpl.changed(owner) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 112) == 0) {
            i2 |= composerImpl.changed(uriHandler) ? 32 : 16;
        }
        if ((i & 896) == 0) {
            i2 |= composerImpl.changedInstance(function2) ? 256 : 128;
        }
        if ((i2 & 731) == 146 && composerImpl.getSkipping()) {
            composerImpl.skipToGroupEnd();
        } else {
            AndroidComposeView androidComposeView = (AndroidComposeView) owner;
            ProvidedValue provides = LocalAccessibilityManager.provides(androidComposeView.getAccessibilityManager());
            ProvidedValue provides2 = LocalAutofill.provides(androidComposeView.getAutofill());
            ProvidedValue provides3 = LocalAutofillTree.provides(androidComposeView.getAutofillTree());
            ProvidedValue provides4 = LocalClipboardManager.provides(androidComposeView.m222getClipboardManager());
            ProvidedValue provides5 = LocalDensity.provides(androidComposeView.getDensity());
            ProvidedValue provides6 = LocalFocusManager.provides(androidComposeView.getFocusOwner());
            Font$ResourceLoader fontLoader = androidComposeView.getFontLoader();
            StaticProvidableCompositionLocal staticProvidableCompositionLocal = LocalFontLoader;
            staticProvidableCompositionLocal.getClass();
            ProvidedValue providedValue = new ProvidedValue(staticProvidableCompositionLocal, fontLoader, false);
            FontFamily.Resolver fontFamilyResolver = androidComposeView.getFontFamilyResolver();
            StaticProvidableCompositionLocal staticProvidableCompositionLocal2 = LocalFontFamilyResolver;
            staticProvidableCompositionLocal2.getClass();
            _BOUNDARY.CompositionLocalProvider(new ProvidedValue[]{provides, provides2, provides3, provides4, provides5, provides6, providedValue, new ProvidedValue(staticProvidableCompositionLocal2, fontFamilyResolver, false), LocalHapticFeedback.provides(androidComposeView.getHapticFeedBack()), LocalInputModeManager.provides(androidComposeView.getInputModeManager()), LocalLayoutDirection.provides(androidComposeView.getLayoutDirection()), LocalTextInputService.provides(androidComposeView.getTextInputService()), LocalPlatformTextInputPluginRegistry.provides(androidComposeView.getPlatformTextInputPluginRegistry()), LocalTextToolbar.provides(androidComposeView.getTextToolbar()), LocalUriHandler.provides(uriHandler), LocalViewConfiguration.provides(androidComposeView.getViewConfiguration()), LocalWindowInfo.provides(androidComposeView.getWindowInfo()), LocalPointerIconService.provides(androidComposeView.getPointerIconService())}, function2, composerImpl, ((i2 >> 3) & 112) | 8);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.block = new ComposableLambdaImpl$invoke$2(owner, uriHandler, function2, i, 2);
    }

    public static final void access$noLocalProvidedFor(String str) {
        throw new IllegalStateException(("CompositionLocal " + str + " not present").toString());
    }
}
