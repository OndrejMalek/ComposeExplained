package androidx.compose.ui.text.font;

import androidx.compose.runtime.Stack;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.platform.WeakCache;
import androidx.compose.ui.platform.WrappedComposition$setContent$1;
import androidx.compose.ui.text.caches.LruCache;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.TypefaceResult;
import kotlin.ResultKt;
import kotlin.collections.AbstractMap$toString$1;

/* loaded from: classes.dex */
public final class FontFamilyResolverImpl implements FontFamily.Resolver {
    public final AbstractMap$toString$1 createDefaultTypeface;
    public final FontListFontFamilyTypefaceAdapter fontListFontFamilyTypefaceAdapter;
    public final Stack platformFamilyTypefaceAdapter;
    public final DrawResult platformFontLoader;
    public final PlatformResolveInterceptor platformResolveInterceptor;
    public final WeakCache typefaceRequestCache;

    public FontFamilyResolverImpl(DrawResult drawResult, AndroidFontResolveInterceptor androidFontResolveInterceptor) {
        WeakCache weakCache = FontFamilyResolverKt.GlobalTypefaceRequestCache;
        FontListFontFamilyTypefaceAdapter fontListFontFamilyTypefaceAdapter = new FontListFontFamilyTypefaceAdapter(FontFamilyResolverKt.GlobalAsyncTypefaceCache);
        Stack stack = new Stack(9);
        ResultKt.checkNotNullParameter(weakCache, "typefaceRequestCache");
        this.platformFontLoader = drawResult;
        this.platformResolveInterceptor = androidFontResolveInterceptor;
        this.typefaceRequestCache = weakCache;
        this.fontListFontFamilyTypefaceAdapter = fontListFontFamilyTypefaceAdapter;
        this.platformFamilyTypefaceAdapter = stack;
        this.createDefaultTypeface = new AbstractMap$toString$1(19, this);
    }

    public final TypefaceResult resolve(TypefaceRequest typefaceRequest) {
        TypefaceResult typefaceResult;
        WeakCache weakCache = this.typefaceRequestCache;
        WrappedComposition$setContent$1 wrappedComposition$setContent$1 = new WrappedComposition$setContent$1(this, 10, typefaceRequest);
        weakCache.getClass();
        synchronized (((DrawResult) weakCache.values)) {
            typefaceResult = (TypefaceResult) ((LruCache) weakCache.referenceQueue).get(typefaceRequest);
            if (typefaceResult != null) {
                if (!((TypefaceResult.Immutable) typefaceResult).cacheable) {
                }
            }
            try {
                typefaceResult = (TypefaceResult) wrappedComposition$setContent$1.invoke(new WrappedComposition$setContent$1(weakCache, 11, typefaceRequest));
                synchronized (((DrawResult) weakCache.values)) {
                    if (((LruCache) weakCache.referenceQueue).get(typefaceRequest) == null && ((TypefaceResult.Immutable) typefaceResult).cacheable) {
                        ((LruCache) weakCache.referenceQueue).put(typefaceRequest, typefaceResult);
                    }
                }
            } catch (Exception e) {
                throw new IllegalStateException("Could not load font", e);
            }
        }
        return typefaceResult;
    }

    /* renamed from: resolve-DPcqOEQ, reason: not valid java name */
    public final TypefaceResult m257resolveDPcqOEQ(FontFamily fontFamily, FontWeight fontWeight, int i, int i2) {
        ResultKt.checkNotNullParameter(fontWeight, "fontWeight");
        PlatformResolveInterceptor platformResolveInterceptor = this.platformResolveInterceptor;
        platformResolveInterceptor.getClass();
        FontWeight interceptFontWeight = platformResolveInterceptor.interceptFontWeight(fontWeight);
        this.platformFontLoader.getClass();
        return resolve(new TypefaceRequest(fontFamily, interceptFontWeight, i, i2, null));
    }
}
