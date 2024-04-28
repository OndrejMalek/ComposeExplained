package androidx.compose.ui.platform;

import _COROUTINE._BOUNDARY;
import android.view.Choreographer;
import android.view.View;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.tooling.InspectionTablesKt;
import androidx.compose.ui.graphics.BlockGraphicsLayerModifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.SimpleGraphicsLayerModifier;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import eu.malek.composesample.R;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableSet;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.android.HandlerContext;
import kotlinx.coroutines.sync.MutexImpl;

/* loaded from: classes.dex */
public final class WrappedComposition$setContent$1 extends Lambda implements Function1 {
    public final /* synthetic */ Object $content;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ WrappedComposition$setContent$1(Object obj, int i, Object obj2) {
        super(1);
        this.$r8$classId = i;
        this.this$0 = obj;
        this.$content = obj2;
    }

    public final void invoke(ContentDrawScope contentDrawScope) {
        int i = this.$r8$classId;
        Object obj = this.this$0;
        switch (i) {
            case 1:
                ResultKt.checkNotNullParameter(contentDrawScope, "$this$onDrawWithContent");
                ((LayoutNodeDrawScope) contentDrawScope).drawContent();
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                throw null;
            default:
                ResultKt.checkNotNullParameter(contentDrawScope, "$this$onDrawWithContent");
                LayoutNodeDrawScope layoutNodeDrawScope = (LayoutNodeDrawScope) contentDrawScope;
                layoutNodeDrawScope.drawContent();
                layoutNodeDrawScope.m177drawPathGBMwjPU((Path) obj, (Brush) this.$content, 1.0f, Fill.INSTANCE, null, 3);
                return;
        }
    }

    /* renamed from: androidx.compose.ui.platform.WrappedComposition$setContent$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends Lambda implements Function2 {
        public final /* synthetic */ Function2 $content;
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ WrappedComposition this$0;

        /* renamed from: androidx.compose.ui.platform.WrappedComposition$setContent$1$1$1, reason: invalid class name and collision with other inner class name */
        /* loaded from: classes.dex */
        public final class C00011 extends SuspendLambda implements Function2 {
            public int label;
            public final /* synthetic */ WrappedComposition this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C00011(WrappedComposition wrappedComposition, Continuation continuation) {
                super(2, continuation);
                this.this$0 = wrappedComposition;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C00011(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C00011) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                Unit unit = Unit.INSTANCE;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    AndroidComposeView androidComposeView = this.this$0.owner;
                    this.label = 1;
                    Object boundsUpdatesEventLoop = androidComposeView.accessibilityDelegate.boundsUpdatesEventLoop(this);
                    if (boundsUpdatesEventLoop != coroutineSingletons) {
                        boundsUpdatesEventLoop = unit;
                    }
                    if (boundsUpdatesEventLoop == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return unit;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AnonymousClass1(WrappedComposition wrappedComposition, Function2 function2, int i) {
            super(2);
            this.$r8$classId = i;
            this.this$0 = wrappedComposition;
            this.$content = function2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            Unit unit = Unit.INSTANCE;
            switch (this.$r8$classId) {
                case 0:
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return unit;
                default:
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return unit;
            }
        }

        public final void invoke(Composer composer, int i) {
            int i2 = this.$r8$classId;
            Function2 function2 = this.$content;
            WrappedComposition wrappedComposition = this.this$0;
            switch (i2) {
                case 0:
                    if ((i & 11) == 2) {
                        ComposerImpl composerImpl = (ComposerImpl) composer;
                        if (composerImpl.getSkipping()) {
                            composerImpl.skipToGroupEnd();
                            return;
                        }
                    }
                    Object tag = wrappedComposition.owner.getTag(R.id.inspection_slot_table_set);
                    int i3 = 1;
                    Set set = (tag instanceof Set) && (!(tag instanceof KMappedMarker) || (tag instanceof KMutableSet)) ? (Set) tag : null;
                    AndroidComposeView androidComposeView = wrappedComposition.owner;
                    if (set == null) {
                        Object parent = androidComposeView.getParent();
                        View view = parent instanceof View ? (View) parent : null;
                        Object tag2 = view != null ? view.getTag(R.id.inspection_slot_table_set) : null;
                        set = (!(tag2 instanceof Set) || ((tag2 instanceof KMappedMarker) && !(tag2 instanceof KMutableSet))) ? null : (Set) tag2;
                    }
                    if (set != null) {
                        ComposerImpl composerImpl2 = (ComposerImpl) composer;
                        set.add(composerImpl2.slotTable);
                        composerImpl2.forceRecomposeScopes = true;
                    }
                    EffectsKt.LaunchedEffect(androidComposeView, new C00011(wrappedComposition, null), composer);
                    _BOUNDARY.CompositionLocalProvider(new ProvidedValue[]{InspectionTablesKt.LocalInspectionTables.provides(set)}, ResultKt.composableLambda(composer, -1193460702, new AnonymousClass1(wrappedComposition, function2, i3)), composer, 56);
                    return;
                default:
                    if ((i & 11) == 2) {
                        ComposerImpl composerImpl3 = (ComposerImpl) composer;
                        if (composerImpl3.getSkipping()) {
                            composerImpl3.skipToGroupEnd();
                            return;
                        }
                    }
                    AndroidCompositionLocals_androidKt.ProvideAndroidCompositionLocals(wrappedComposition.owner, function2, composer, 8);
                    return;
            }
        }
    }

    public final void invoke(Placeable.PlacementScope placementScope) {
        int i = this.$r8$classId;
        Object obj = this.$content;
        Object obj2 = this.this$0;
        switch (i) {
            case 5:
                ResultKt.checkNotNullParameter(placementScope, "$this$layout");
                Placeable.PlacementScope.placeWithLayer$default(placementScope, (Placeable) obj2, ((BlockGraphicsLayerModifier) obj).layerBlock);
                return;
            default:
                ResultKt.checkNotNullParameter(placementScope, "$this$layout");
                Placeable.PlacementScope.placeWithLayer$default(placementScope, (Placeable) obj2, ((SimpleGraphicsLayerModifier) obj).layerBlock);
                return;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00cf A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00d0  */
    @Override // kotlin.jvm.functions.Function1
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invoke(java.lang.Object r8) {
        /*
            r7 = this;
            int r0 = r7.$r8$classId
            r1 = 1
            switch(r0) {
                case 0: goto L124;
                case 1: goto L11c;
                case 2: goto L114;
                case 3: goto L6;
                case 4: goto L6;
                case 5: goto L10c;
                case 6: goto L104;
                case 7: goto Le8;
                case 8: goto Le0;
                case 9: goto Ld8;
                case 10: goto L48;
                case 11: goto L16;
                case 12: goto Le;
                default: goto L6;
            }
        L6:
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            r7.invoke(r8)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        Le:
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            r7.invoke(r8)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L16:
            androidx.compose.ui.text.font.TypefaceResult r8 = (androidx.compose.ui.text.font.TypefaceResult) r8
            java.lang.String r0 = "finalResult"
            kotlin.ResultKt.checkNotNullParameter(r8, r0)
            java.lang.Object r0 = r7.this$0
            androidx.compose.ui.platform.WeakCache r0 = (androidx.compose.ui.platform.WeakCache) r0
            java.lang.Object r1 = r0.values
            androidx.compose.ui.draw.DrawResult r1 = (androidx.compose.ui.draw.DrawResult) r1
            java.lang.Object r2 = r7.$content
            androidx.compose.ui.text.font.TypefaceRequest r2 = (androidx.compose.ui.text.font.TypefaceRequest) r2
            monitor-enter(r1)
            r3 = r8
            androidx.compose.ui.text.font.TypefaceResult$Immutable r3 = (androidx.compose.ui.text.font.TypefaceResult.Immutable) r3     // Catch: java.lang.Throwable -> L39
            boolean r3 = r3.cacheable     // Catch: java.lang.Throwable -> L39
            if (r3 == 0) goto L3b
            java.lang.Object r0 = r0.referenceQueue     // Catch: java.lang.Throwable -> L39
            androidx.compose.ui.text.caches.LruCache r0 = (androidx.compose.ui.text.caches.LruCache) r0     // Catch: java.lang.Throwable -> L39
            r0.put(r2, r8)     // Catch: java.lang.Throwable -> L39
            goto L42
        L39:
            r8 = move-exception
            goto L46
        L3b:
            java.lang.Object r8 = r0.referenceQueue     // Catch: java.lang.Throwable -> L39
            androidx.compose.ui.text.caches.LruCache r8 = (androidx.compose.ui.text.caches.LruCache) r8     // Catch: java.lang.Throwable -> L39
            r8.remove(r2)     // Catch: java.lang.Throwable -> L39
        L42:
            monitor-exit(r1)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L46:
            monitor-exit(r1)
            throw r8
        L48:
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.String r0 = "onAsyncCompletion"
            kotlin.ResultKt.checkNotNullParameter(r8, r0)
            java.lang.Object r8 = r7.this$0
            androidx.compose.ui.text.font.FontFamilyResolverImpl r8 = (androidx.compose.ui.text.font.FontFamilyResolverImpl) r8
            androidx.compose.ui.text.font.FontListFontFamilyTypefaceAdapter r0 = r8.fontListFontFamilyTypefaceAdapter
            java.lang.Object r2 = r7.$content
            androidx.compose.ui.text.font.TypefaceRequest r2 = (androidx.compose.ui.text.font.TypefaceRequest) r2
            androidx.compose.ui.draw.DrawResult r3 = r8.platformFontLoader
            kotlin.collections.AbstractMap$toString$1 r8 = r8.createDefaultTypeface
            r0.getClass()
            java.lang.String r0 = "typefaceRequest"
            kotlin.ResultKt.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "platformFontLoader"
            kotlin.ResultKt.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "createDefaultTypeface"
            kotlin.ResultKt.checkNotNullParameter(r8, r0)
            java.lang.Object r8 = r7.this$0
            androidx.compose.ui.text.font.FontFamilyResolverImpl r8 = (androidx.compose.ui.text.font.FontFamilyResolverImpl) r8
            androidx.compose.runtime.Stack r0 = r8.platformFamilyTypefaceAdapter
            java.lang.Object r2 = r7.$content
            androidx.compose.ui.text.font.TypefaceRequest r2 = (androidx.compose.ui.text.font.TypefaceRequest) r2
            androidx.compose.ui.draw.DrawResult r3 = r8.platformFontLoader
            kotlin.collections.AbstractMap$toString$1 r8 = r8.createDefaultTypeface
            r0.getClass()
            java.lang.String r4 = "typefaceRequest"
            kotlin.ResultKt.checkNotNullParameter(r2, r4)
            java.lang.String r4 = "platformFontLoader"
            kotlin.ResultKt.checkNotNullParameter(r3, r4)
            java.lang.String r3 = "createDefaultTypeface"
            kotlin.ResultKt.checkNotNullParameter(r8, r3)
            java.lang.String r8 = "fontWeight"
            int r3 = r2.fontStyle
            androidx.compose.ui.text.font.FontWeight r4 = r2.fontWeight
            androidx.compose.ui.text.font.FontFamily r2 = r2.fontFamily
            r5 = 0
            if (r2 != 0) goto L9b
            goto L9f
        L9b:
            boolean r6 = r2 instanceof androidx.compose.ui.text.font.DefaultFontFamily
            if (r6 == 0) goto Lb0
        L9f:
            java.lang.Object r0 = r0.backing
            androidx.compose.ui.text.font.PlatformTypefaces r0 = (androidx.compose.ui.text.font.PlatformTypefaces) r0
            androidx.compose.ui.draw.DrawResult r0 = (androidx.compose.ui.draw.DrawResult) r0
            r0.getClass()
            kotlin.ResultKt.checkNotNullParameter(r4, r8)
            android.graphics.Typeface r8 = androidx.compose.ui.draw.DrawResult.m64createAndroidTypefaceApi28RetOiIg(r5, r4, r3)
            goto Lc8
        Lb0:
            boolean r6 = r2 instanceof androidx.compose.ui.text.font.GenericFontFamily
            if (r6 == 0) goto Lcd
            java.lang.Object r0 = r0.backing
            androidx.compose.ui.text.font.PlatformTypefaces r0 = (androidx.compose.ui.text.font.PlatformTypefaces) r0
            androidx.compose.ui.text.font.GenericFontFamily r2 = (androidx.compose.ui.text.font.GenericFontFamily) r2
            androidx.compose.ui.draw.DrawResult r0 = (androidx.compose.ui.draw.DrawResult) r0
            r0.getClass()
            kotlin.ResultKt.checkNotNullParameter(r4, r8)
            java.lang.String r8 = r2.name
            android.graphics.Typeface r8 = androidx.compose.ui.draw.DrawResult.m64createAndroidTypefaceApi28RetOiIg(r8, r4, r3)
        Lc8:
            androidx.compose.ui.text.font.TypefaceResult$Immutable r5 = new androidx.compose.ui.text.font.TypefaceResult$Immutable
            r5.<init>(r8, r1)
        Lcd:
            if (r5 == 0) goto Ld0
            return r5
        Ld0:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "Could not load font"
            r8.<init>(r0)
            throw r8
        Ld8:
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            r7.invoke(r8)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        Le0:
            java.lang.Throwable r8 = (java.lang.Throwable) r8
            r7.invoke(r8)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        Le8:
            androidx.compose.runtime.DisposableEffectScope r8 = (androidx.compose.runtime.DisposableEffectScope) r8
            java.lang.String r0 = "$this$DisposableEffect"
            kotlin.ResultKt.checkNotNullParameter(r8, r0)
            java.lang.Object r8 = r7.this$0
            android.content.Context r8 = (android.content.Context) r8
            android.content.Context r0 = r8.getApplicationContext()
            java.lang.Object r1 = r7.$content
            androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1 r1 = (androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainImageVectorCache$callbacks$1$1) r1
            r0.registerComponentCallbacks(r1)
            androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainImageVectorCache$1$invoke$$inlined$onDispose$1 r0 = new androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$obtainImageVectorCache$1$invoke$$inlined$onDispose$1
            r0.<init>()
            return r0
        L104:
            androidx.compose.ui.layout.Placeable$PlacementScope r8 = (androidx.compose.ui.layout.Placeable.PlacementScope) r8
            r7.invoke(r8)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L10c:
            androidx.compose.ui.layout.Placeable$PlacementScope r8 = (androidx.compose.ui.layout.Placeable.PlacementScope) r8
            r7.invoke(r8)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L114:
            androidx.compose.ui.graphics.drawscope.ContentDrawScope r8 = (androidx.compose.ui.graphics.drawscope.ContentDrawScope) r8
            r7.invoke(r8)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L11c:
            androidx.compose.ui.graphics.drawscope.ContentDrawScope r8 = (androidx.compose.ui.graphics.drawscope.ContentDrawScope) r8
            r7.invoke(r8)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L124:
            androidx.compose.ui.platform.AndroidComposeView$ViewTreeOwners r8 = (androidx.compose.ui.platform.AndroidComposeView.ViewTreeOwners) r8
            java.lang.String r0 = "it"
            kotlin.ResultKt.checkNotNullParameter(r8, r0)
            java.lang.Object r0 = r7.this$0
            androidx.compose.ui.platform.WrappedComposition r0 = (androidx.compose.ui.platform.WrappedComposition) r0
            boolean r2 = r0.disposed
            if (r2 != 0) goto L165
            androidx.lifecycle.LifecycleOwner r8 = r8.lifecycleOwner
            androidx.lifecycle.LifecycleRegistry r8 = r8.getLifecycle()
            java.lang.Object r2 = r7.$content
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.lastContent = r2
            androidx.lifecycle.Lifecycle r3 = r0.addedToLifecycle
            if (r3 != 0) goto L149
            r0.addedToLifecycle = r8
            r8.addObserver(r0)
            goto L165
        L149:
            androidx.lifecycle.Lifecycle$State r8 = r8.state
            androidx.lifecycle.Lifecycle$State r3 = androidx.lifecycle.Lifecycle.State.CREATED
            int r8 = r8.compareTo(r3)
            if (r8 < 0) goto L165
            androidx.compose.runtime.Composition r8 = r0.original
            androidx.compose.ui.platform.WrappedComposition$setContent$1$1 r3 = new androidx.compose.ui.platform.WrappedComposition$setContent$1$1
            r4 = 0
            r3.<init>(r0, r2, r4)
            r0 = -2000640158(0xffffffff88c0a762, float:-1.1594931E-33)
            androidx.compose.runtime.internal.ComposableLambdaImpl r0 = kotlin.ResultKt.composableLambdaInstance(r0, r3, r1)
            r8.setContent(r0)
        L165:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.WrappedComposition$setContent$1.invoke(java.lang.Object):java.lang.Object");
    }

    public final void invoke(Throwable th) {
        switch (this.$r8$classId) {
            case 8:
                AndroidUiDispatcher androidUiDispatcher = (AndroidUiDispatcher) this.this$0;
                Choreographer.FrameCallback frameCallback = (Choreographer.FrameCallback) this.$content;
                androidUiDispatcher.getClass();
                ResultKt.checkNotNullParameter(frameCallback, "callback");
                synchronized (androidUiDispatcher.lock) {
                    androidUiDispatcher.toRunOnFrame.remove(frameCallback);
                }
                return;
            case 9:
                ((AndroidUiFrameClock) this.this$0).choreographer.removeFrameCallback((Choreographer.FrameCallback) this.$content);
                return;
            case 10:
            case 11:
            default:
                ((MutexImpl) this.this$0).unlock(this.$content);
                return;
            case 12:
                ((HandlerContext) this.this$0).handler.removeCallbacks((Runnable) this.$content);
                return;
        }
    }
}
