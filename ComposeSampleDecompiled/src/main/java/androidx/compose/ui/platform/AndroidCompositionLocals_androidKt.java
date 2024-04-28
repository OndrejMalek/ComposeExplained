package androidx.compose.ui.platform;

import _COROUTINE.ArtificialStackFrames;
import _COROUTINE._BOUNDARY;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.activity.ComponentActivity;
import androidx.compose.material3.TextKt$ProvideTextStyle$1;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.CompositionLocal;
import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.StructuralEqualityPolicy;
import androidx.compose.runtime.internal.ComposableLambdaImpl$invoke$2;
import androidx.compose.runtime.saveable.SaveableStateRegistryImpl;
import androidx.compose.runtime.saveable.SaveableStateRegistryKt;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.res.ImageVectorCache;
import androidx.savedstate.Recreator;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import eu.malek.composesample.R;
import eu.malek.composesample.ui.theme.ThemeKt$ComposeSampleTheme$1;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.AbstractMap$toString$1;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public abstract class AndroidCompositionLocals_androidKt {
    public static final DynamicProvidableCompositionLocal LocalConfiguration = _BOUNDARY.compositionLocalOf$default(CompositionLocalsKt$LocalDensity$1.INSTANCE$1);
    public static final StaticProvidableCompositionLocal LocalContext = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$2);
    public static final StaticProvidableCompositionLocal LocalImageVectorCache = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$3);
    public static final StaticProvidableCompositionLocal LocalLifecycleOwner = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$4);
    public static final StaticProvidableCompositionLocal LocalSavedStateRegistryOwner = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$5);
    public static final StaticProvidableCompositionLocal LocalView = new CompositionLocal(CompositionLocalsKt$LocalDensity$1.INSTANCE$6);

    public static final void ProvideAndroidCompositionLocals(AndroidComposeView androidComposeView, Function2 function2, Composer composer, int i) {
        boolean z;
        boolean z2;
        ResultKt.checkNotNullParameter(androidComposeView, "owner");
        ResultKt.checkNotNullParameter(function2, "content");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1396852028);
        Context context = androidComposeView.getContext();
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot = composerImpl.nextSlot();
        ArtificialStackFrames artificialStackFrames = Composer.Companion.Empty;
        if (nextSlot == artificialStackFrames) {
            nextSlot = ResultKt.mutableStateOf(new Configuration(context.getResources().getConfiguration()), StructuralEqualityPolicy.INSTANCE);
            composerImpl.updateValue(nextSlot);
        }
        composerImpl.end(false);
        MutableState mutableState = (MutableState) nextSlot;
        composerImpl.startReplaceableGroup(1157296644);
        boolean changed = composerImpl.changed(mutableState);
        Object nextSlot2 = composerImpl.nextSlot();
        if (changed || nextSlot2 == artificialStackFrames) {
            nextSlot2 = new AbstractMap$toString$1(15, mutableState);
            composerImpl.updateValue(nextSlot2);
        }
        composerImpl.end(false);
        androidComposeView.setConfigurationChangeObserver((Function1) nextSlot2);
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot3 = composerImpl.nextSlot();
        if (nextSlot3 == artificialStackFrames) {
            ResultKt.checkNotNullExpressionValue(context, "context");
            nextSlot3 = new Object();
            composerImpl.updateValue(nextSlot3);
        }
        composerImpl.end(false);
        AndroidUriHandler androidUriHandler = (AndroidUriHandler) nextSlot3;
        AndroidComposeView.ViewTreeOwners viewTreeOwners = androidComposeView.getViewTreeOwners();
        if (viewTreeOwners == null) {
            throw new IllegalStateException("Called when the ViewTreeOwnersAvailability is not yet in Available state");
        }
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot4 = composerImpl.nextSlot();
        SavedStateRegistryOwner savedStateRegistryOwner = viewTreeOwners.savedStateRegistryOwner;
        if (nextSlot4 == artificialStackFrames) {
            ResultKt.checkNotNullParameter(savedStateRegistryOwner, "owner");
            Object parent = androidComposeView.getParent();
            ResultKt.checkNotNull(parent, "null cannot be cast to non-null type android.view.View");
            View view = (View) parent;
            Object tag = view.getTag(R.id.compose_view_saveable_id_tag);
            LinkedHashMap linkedHashMap = null;
            String str = tag instanceof String ? (String) tag : null;
            if (str == null) {
                str = String.valueOf(view.getId());
            }
            ResultKt.checkNotNullParameter(str, "id");
            String concat = "SaveableStateRegistry:".concat(str);
            SavedStateRegistry savedStateRegistry = ((ComponentActivity) savedStateRegistryOwner).savedStateRegistryController.savedStateRegistry;
            Bundle consumeRestoredStateForKey = savedStateRegistry.consumeRestoredStateForKey(concat);
            if (consumeRestoredStateForKey != null) {
                linkedHashMap = new LinkedHashMap();
                Set<String> keySet = consumeRestoredStateForKey.keySet();
                ResultKt.checkNotNullExpressionValue(keySet, "this.keySet()");
                Iterator it = keySet.iterator();
                while (it.hasNext()) {
                    String str2 = (String) it.next();
                    Iterator it2 = it;
                    ArrayList parcelableArrayList = consumeRestoredStateForKey.getParcelableArrayList(str2);
                    ResultKt.checkNotNull(parcelableArrayList, "null cannot be cast to non-null type java.util.ArrayList<kotlin.Any?>{ kotlin.collections.TypeAliasesKt.ArrayList<kotlin.Any?> }");
                    ResultKt.checkNotNullExpressionValue(str2, "key");
                    linkedHashMap.put(str2, parcelableArrayList);
                    it = it2;
                    consumeRestoredStateForKey = consumeRestoredStateForKey;
                }
            }
            StaticProvidableCompositionLocal staticProvidableCompositionLocal = SaveableStateRegistryKt.LocalSaveableStateRegistry;
            SaveableStateRegistryImpl saveableStateRegistryImpl = new SaveableStateRegistryImpl(linkedHashMap);
            try {
                savedStateRegistry.registerSavedStateProvider(concat, new Recreator.SavedStateProvider(saveableStateRegistryImpl));
                z2 = true;
            } catch (IllegalArgumentException unused) {
                z2 = false;
            }
            DisposableSaveableStateRegistry disposableSaveableStateRegistry = new DisposableSaveableStateRegistry(saveableStateRegistryImpl, new ThemeKt$ComposeSampleTheme$1(z2, savedStateRegistry, concat));
            composerImpl.updateValue(disposableSaveableStateRegistry);
            nextSlot4 = disposableSaveableStateRegistry;
            z = false;
        } else {
            z = false;
        }
        composerImpl.end(z);
        DisposableSaveableStateRegistry disposableSaveableStateRegistry2 = (DisposableSaveableStateRegistry) nextSlot4;
        EffectsKt.DisposableEffect(Unit.INSTANCE, new AbstractMap$toString$1(16, disposableSaveableStateRegistry2), composerImpl);
        ResultKt.checkNotNullExpressionValue(context, "context");
        Configuration configuration = (Configuration) mutableState.getValue();
        composerImpl.startReplaceableGroup(-485908294);
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot5 = composerImpl.nextSlot();
        if (nextSlot5 == artificialStackFrames) {
            nextSlot5 = new ImageVectorCache();
            composerImpl.updateValue(nextSlot5);
        }
        composerImpl.end(false);
        final ImageVectorCache imageVectorCache = (ImageVectorCache) nextSlot5;
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot6 = composerImpl.nextSlot();
        Object obj = nextSlot6;
        if (nextSlot6 == artificialStackFrames) {
            Configuration configuration2 = new Configuration();
            if (configuration != null) {
                configuration2.setTo(configuration);
            }
            composerImpl.updateValue(configuration2);
            obj = configuration2;
        }
        composerImpl.end(false);
        final Configuration configuration3 = (Configuration) obj;
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot7 = composerImpl.nextSlot();
        if (nextSlot7 == artificialStackFrames) {
            nextSlot7 = new ComponentCallbacks2() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1
                @Override // android.content.ComponentCallbacks
                public final void onConfigurationChanged(Configuration configuration4) {
                    ResultKt.checkNotNullParameter(configuration4, "configuration");
                    Configuration configuration5 = configuration3;
                    configuration5.updateFrom(configuration4);
                    Iterator it3 = imageVectorCache.map.entrySet().iterator();
                    while (it3.hasNext()) {
                        Object next = it3.next();
                        ResultKt.checkNotNullExpressionValue(next, "it.next()");
                        DurationKt$$ExternalSyntheticCheckNotZero0.m(((WeakReference) ((Map.Entry) next).getValue()).get());
                        it3.remove();
                    }
                    configuration5.setTo(configuration4);
                }

                @Override // android.content.ComponentCallbacks
                public final void onLowMemory() {
                    imageVectorCache.map.clear();
                }

                @Override // android.content.ComponentCallbacks2
                public final void onTrimMemory(int i2) {
                    imageVectorCache.map.clear();
                }
            };
            composerImpl.updateValue(nextSlot7);
        }
        composerImpl.end(false);
        EffectsKt.DisposableEffect(imageVectorCache, new WrappedComposition$setContent$1(context, 7, (AndroidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1) nextSlot7), composerImpl);
        composerImpl.end(false);
        _BOUNDARY.CompositionLocalProvider(new ProvidedValue[]{LocalConfiguration.provides((Configuration) mutableState.getValue()), LocalContext.provides(context), LocalLifecycleOwner.provides(viewTreeOwners.lifecycleOwner), LocalSavedStateRegistryOwner.provides(savedStateRegistryOwner), SaveableStateRegistryKt.LocalSaveableStateRegistry.provides(disposableSaveableStateRegistry2), LocalView.provides(androidComposeView.getView()), LocalImageVectorCache.provides(imageVectorCache)}, ResultKt.composableLambda(composerImpl, 1471621628, new ComposableLambdaImpl$invoke$2(androidComposeView, androidUriHandler, function2, i, 1)), composerImpl, 56);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.block = new TextKt$ProvideTextStyle$1(i, 4, androidComposeView, function2);
    }

    public static final void access$noLocalProvidedFor(String str) {
        throw new IllegalStateException(("CompositionLocal " + str + " not present").toString());
    }
}
