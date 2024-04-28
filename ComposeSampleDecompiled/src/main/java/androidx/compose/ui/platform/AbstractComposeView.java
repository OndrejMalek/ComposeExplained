package androidx.compose.ui.platform;

import _COROUTINE._BOUNDARY;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.ui.ComposedModifierKt$materialize$result$1;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat;
import java.lang.ref.WeakReference;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class AbstractComposeView extends ViewGroup {
    public WeakReference cachedViewTreeCompositionContext;
    public WrappedComposition composition;
    public boolean creatingComposition;
    public ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$installFor$1 disposeViewCompositionStrategy;
    public boolean isTransitionGroupSet;
    public CompositionContext parentContext;
    public IBinder previousAttachedWindowToken;
    public boolean showLayoutBounds;

    private static /* synthetic */ void getDisposeViewCompositionStrategy$annotations() {
    }

    public static /* synthetic */ void getShowLayoutBounds$annotations() {
    }

    private final void setParentContext(CompositionContext compositionContext) {
        if (this.parentContext != compositionContext) {
            this.parentContext = compositionContext;
            if (compositionContext != null) {
                this.cachedViewTreeCompositionContext = null;
            }
            WrappedComposition wrappedComposition = this.composition;
            if (wrappedComposition != null) {
                wrappedComposition.dispose();
                this.composition = null;
                if (isAttachedToWindow()) {
                    ensureCompositionCreated();
                }
            }
        }
    }

    private final void setPreviousAttachedWindowToken(IBinder iBinder) {
        if (this.previousAttachedWindowToken != iBinder) {
            this.previousAttachedWindowToken = iBinder;
            this.cachedViewTreeCompositionContext = null;
        }
    }

    public abstract void Content(Composer composer, int i);

    @Override // android.view.ViewGroup
    public final void addView(View view) {
        checkAddView();
        super.addView(view);
    }

    @Override // android.view.ViewGroup
    public final boolean addViewInLayout(View view, int i, ViewGroup.LayoutParams layoutParams) {
        checkAddView();
        return super.addViewInLayout(view, i, layoutParams);
    }

    public final void checkAddView() {
        if (this.creatingComposition) {
            return;
        }
        throw new UnsupportedOperationException("Cannot add views to " + getClass().getSimpleName() + "; only Compose content is supported");
    }

    public final void ensureCompositionCreated() {
        if (this.composition == null) {
            try {
                this.creatingComposition = true;
                this.composition = Wrapper_androidKt.setContent(this, resolveParentCompositionContext(), ResultKt.composableLambdaInstance(-656146368, new ComposedModifierKt$materialize$result$1(6, this), true));
            } finally {
                this.creatingComposition = false;
            }
        }
    }

    public final boolean getHasComposition() {
        return this.composition != null;
    }

    public boolean getShouldCreateCompositionOnAttachedToWindow() {
        return true;
    }

    public final boolean getShowLayoutBounds() {
        return this.showLayoutBounds;
    }

    @Override // android.view.ViewGroup
    public final boolean isTransitionGroup() {
        return !this.isTransitionGroupSet || super.isTransitionGroup();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        setPreviousAttachedWindowToken(getWindowToken());
        if (getShouldCreateCompositionOnAttachedToWindow()) {
            ensureCompositionCreated();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View childAt = getChildAt(0);
        if (childAt != null) {
            childAt.layout(getPaddingLeft(), getPaddingTop(), (i3 - i) - getPaddingRight(), (i4 - i2) - getPaddingBottom());
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        ensureCompositionCreated();
        View childAt = getChildAt(0);
        if (childAt == null) {
            super.onMeasure(i, i2);
            return;
        }
        childAt.measure(View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight()), View.MeasureSpec.getMode(i)), View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i2) - getPaddingTop()) - getPaddingBottom()), View.MeasureSpec.getMode(i2)));
        setMeasuredDimension(getPaddingRight() + getPaddingLeft() + childAt.getMeasuredWidth(), getPaddingBottom() + getPaddingTop() + childAt.getMeasuredHeight());
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i) {
        View childAt = getChildAt(0);
        if (childAt == null) {
            return;
        }
        childAt.setLayoutDirection(i);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:11:0x0021 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:19:0x003f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:21:0x004a */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x006d, code lost:
    
        if (r2 > 0) goto L35;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0073  */
    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.runtime.CompositionContext] */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.runtime.CompositionContext] */
    /* JADX WARN: Type inference failed for: r0v18, types: [androidx.compose.runtime.Recomposer] */
    /* JADX WARN: Type inference failed for: r0v2, types: [androidx.compose.runtime.CompositionContext] */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v42 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v52 */
    /* JADX WARN: Type inference failed for: r0v56 */
    /* JADX WARN: Type inference failed for: r0v57 */
    /* JADX WARN: Type inference failed for: r0v58 */
    /* JADX WARN: Type inference failed for: r0v59 */
    /* JADX WARN: Type inference failed for: r0v60 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [androidx.compose.runtime.PausableMonotonicFrameClock] */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r6v0, types: [java.lang.Object, kotlin.jvm.internal.Ref$ObjectRef] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.runtime.CompositionContext resolveParentCompositionContext() {
        /*
            r13 = this;
            androidx.compose.runtime.CompositionContext r0 = r13.parentContext
            if (r0 != 0) goto L20b
            androidx.compose.runtime.CompositionContext r0 = androidx.compose.ui.platform.WindowRecomposer_androidKt.getCompositionContext(r13)
            if (r0 == 0) goto Lb
            goto L21
        Lb:
            android.view.ViewParent r1 = r13.getParent()
        Lf:
            if (r0 != 0) goto L21
            boolean r2 = r1 instanceof android.view.View
            if (r2 == 0) goto L21
            r0 = r1
            android.view.View r0 = (android.view.View) r0
            androidx.compose.runtime.CompositionContext r0 = androidx.compose.ui.platform.WindowRecomposer_androidKt.getCompositionContext(r0)
            android.view.ViewParent r1 = r1.getParent()
            goto Lf
        L21:
            r1 = 0
            if (r0 == 0) goto L49
            boolean r2 = r0 instanceof androidx.compose.runtime.Recomposer
            if (r2 == 0) goto L3e
            r2 = r0
            androidx.compose.runtime.Recomposer r2 = (androidx.compose.runtime.Recomposer) r2
            kotlinx.coroutines.flow.StateFlowImpl r2 = r2._state
            java.lang.Object r2 = r2.getValue()
            androidx.compose.runtime.Recomposer$State r2 = (androidx.compose.runtime.Recomposer.State) r2
            androidx.compose.runtime.Recomposer$State r3 = androidx.compose.runtime.Recomposer.State.ShuttingDown
            int r2 = r2.compareTo(r3)
            if (r2 <= 0) goto L3c
            goto L3e
        L3c:
            r2 = r1
            goto L3f
        L3e:
            r2 = r0
        L3f:
            if (r2 == 0) goto L4a
            java.lang.ref.WeakReference r3 = new java.lang.ref.WeakReference
            r3.<init>(r2)
            r13.cachedViewTreeCompositionContext = r3
            goto L4a
        L49:
            r0 = r1
        L4a:
            if (r0 != 0) goto L20b
            java.lang.ref.WeakReference r0 = r13.cachedViewTreeCompositionContext
            if (r0 == 0) goto L70
            java.lang.Object r0 = r0.get()
            androidx.compose.runtime.CompositionContext r0 = (androidx.compose.runtime.CompositionContext) r0
            if (r0 == 0) goto L70
            boolean r2 = r0 instanceof androidx.compose.runtime.Recomposer
            if (r2 == 0) goto L71
            r2 = r0
            androidx.compose.runtime.Recomposer r2 = (androidx.compose.runtime.Recomposer) r2
            kotlinx.coroutines.flow.StateFlowImpl r2 = r2._state
            java.lang.Object r2 = r2.getValue()
            androidx.compose.runtime.Recomposer$State r2 = (androidx.compose.runtime.Recomposer.State) r2
            androidx.compose.runtime.Recomposer$State r3 = androidx.compose.runtime.Recomposer.State.ShuttingDown
            int r2 = r2.compareTo(r3)
            if (r2 <= 0) goto L70
            goto L71
        L70:
            r0 = r1
        L71:
            if (r0 != 0) goto L20b
            boolean r0 = r13.isAttachedToWindow()
            if (r0 == 0) goto L1ee
            android.view.ViewParent r0 = r13.getParent()
            r8 = r13
        L7e:
            boolean r2 = r0 instanceof android.view.View
            if (r2 == 0) goto L95
            android.view.View r0 = (android.view.View) r0
            int r2 = r0.getId()
            r3 = 16908290(0x1020002, float:2.3877235E-38)
            if (r2 != r3) goto L8e
            goto L95
        L8e:
            android.view.ViewParent r2 = r0.getParent()
            r8 = r0
            r0 = r2
            goto L7e
        L95:
            androidx.compose.runtime.CompositionContext r0 = androidx.compose.ui.platform.WindowRecomposer_androidKt.getCompositionContext(r8)
            if (r0 != 0) goto L1c1
            java.util.concurrent.atomic.AtomicReference r0 = androidx.compose.ui.platform.WindowRecomposerPolicy.factory
            java.lang.Object r0 = r0.get()
            androidx.compose.ui.platform.WindowRecomposerFactory r0 = (androidx.compose.ui.platform.WindowRecomposerFactory) r0
            androidx.compose.ui.platform.WindowRecomposerFactory$Companion$LifecycleAware$1 r0 = (androidx.compose.ui.platform.WindowRecomposerFactory$Companion$LifecycleAware$1) r0
            r0.getClass()
            kotlin.coroutines.EmptyCoroutineContext r0 = kotlin.coroutines.EmptyCoroutineContext.INSTANCE
            kotlin.SynchronizedLazyImpl r2 = androidx.compose.ui.platform.AndroidUiDispatcher.Main$delegate
            android.os.Looper r2 = android.os.Looper.myLooper()
            android.os.Looper r3 = android.os.Looper.getMainLooper()
            if (r2 != r3) goto Lbf
            kotlin.SynchronizedLazyImpl r2 = androidx.compose.ui.platform.AndroidUiDispatcher.Main$delegate
            java.lang.Object r2 = r2.getValue()
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            goto Lc9
        Lbf:
            kotlin.random.FallbackThreadLocalRandom$implStorage$1 r2 = androidx.compose.ui.platform.AndroidUiDispatcher.currentThread
            java.lang.Object r2 = r2.get()
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            if (r2 == 0) goto L1b5
        Lc9:
            kotlin.coroutines.CoroutineContext r2 = r2.plus(r0)
            androidx.compose.runtime.Composer$Companion r3 = androidx.compose.runtime.Composer.Companion.$$INSTANCE
            kotlin.coroutines.CoroutineContext$Element r3 = r2.get(r3)
            androidx.compose.runtime.MonotonicFrameClock r3 = (androidx.compose.runtime.MonotonicFrameClock) r3
            r9 = 0
            if (r3 == 0) goto Le9
            androidx.compose.runtime.PausableMonotonicFrameClock r4 = new androidx.compose.runtime.PausableMonotonicFrameClock
            r4.<init>(r3)
            androidx.compose.runtime.Latch r3 = r4.latch
            java.lang.Object r5 = r3.lock
            monitor-enter(r5)
            r3._isOpen = r9     // Catch: java.lang.Throwable -> Le6
            monitor-exit(r5)
            goto Lea
        Le6:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        Le9:
            r4 = r1
        Lea:
            kotlin.jvm.internal.Ref$ObjectRef r6 = new kotlin.jvm.internal.Ref$ObjectRef
            r6.<init>()
            androidx.compose.ui.Alignment$Companion r3 = androidx.compose.ui.Alignment.Companion.$$INSTANCE
            kotlin.coroutines.CoroutineContext$Element r3 = r2.get(r3)
            androidx.compose.ui.MotionDurationScale r3 = (androidx.compose.ui.MotionDurationScale) r3
            if (r3 != 0) goto L100
            androidx.compose.ui.platform.MotionDurationScaleImpl r3 = new androidx.compose.ui.platform.MotionDurationScaleImpl
            r3.<init>()
            r6.element = r3
        L100:
            if (r4 == 0) goto L103
            r0 = r4
        L103:
            kotlin.coroutines.CoroutineContext r0 = r2.plus(r0)
            kotlin.coroutines.CoroutineContext r0 = r0.plus(r3)
            androidx.compose.runtime.Recomposer r10 = new androidx.compose.runtime.Recomposer
            r10.<init>(r0)
            java.lang.Object r2 = r10.stateLock
            monitor-enter(r2)
            r11 = 1
            r10.frameClockPaused = r11     // Catch: java.lang.Throwable -> L1b2
            monitor-exit(r2)
            kotlinx.coroutines.internal.ContextScope r3 = kotlin.ResultKt.CoroutineScope(r0)
            androidx.lifecycle.LifecycleOwner r0 = kotlin.ResultKt.get(r8)
            if (r0 == 0) goto L126
            androidx.lifecycle.LifecycleRegistry r0 = r0.getLifecycle()
            goto L127
        L126:
            r0 = r1
        L127:
            if (r0 == 0) goto L19a
            androidx.compose.ui.platform.WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$1 r2 = new androidx.compose.ui.platform.WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$1
            r2.<init>()
            r8.addOnAttachStateChangeListener(r2)
            androidx.compose.ui.platform.WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2 r12 = new androidx.compose.ui.platform.WindowRecomposer_androidKt$createLifecycleAwareWindowRecomposer$2
            r2 = r12
            r5 = r10
            r7 = r8
            r2.<init>()
            r0.addObserver(r12)
            r0 = 2131034150(0x7f050026, float:1.767881E38)
            r8.setTag(r0, r10)
            android.os.Handler r0 = r8.getHandler()
            java.lang.String r2 = "rootView.handler"
            kotlin.ResultKt.checkNotNullExpressionValue(r0, r2)
            java.lang.String r2 = "windowRecomposer cleanup"
            int r3 = kotlinx.coroutines.android.HandlerDispatcherKt.$r8$clinit
            kotlinx.coroutines.android.HandlerContext r3 = new kotlinx.coroutines.android.HandlerContext
            r3.<init>(r0, r2, r9)
            kotlinx.coroutines.android.HandlerContext r0 = r3.immediate
            androidx.compose.ui.platform.WindowRecomposerPolicy$createAndInstallWindowRecomposer$unsetJob$1 r2 = new androidx.compose.ui.platform.WindowRecomposerPolicy$createAndInstallWindowRecomposer$unsetJob$1
            r2.<init>(r10, r8, r1)
            r3 = 2
            r3 = r3 & r11
            if (r3 == 0) goto L161
            kotlin.coroutines.EmptyCoroutineContext r0 = kotlin.coroutines.EmptyCoroutineContext.INSTANCE
        L161:
            r3 = 2
            r4 = r3 & r3
            if (r4 == 0) goto L167
            r9 = r11
        L167:
            kotlin.coroutines.EmptyCoroutineContext r4 = kotlin.coroutines.EmptyCoroutineContext.INSTANCE
            kotlin.coroutines.CoroutineContext r0 = kotlin.ResultKt.foldCopies(r4, r0, r11)
            kotlinx.coroutines.scheduling.DefaultScheduler r4 = kotlinx.coroutines.Dispatchers.Default
            if (r0 == r4) goto L17d
            kotlin.coroutines.ContinuationInterceptor$Key r5 = kotlin.coroutines.ContinuationInterceptor.Key.$$INSTANCE
            kotlin.coroutines.CoroutineContext$Element r5 = r0.get(r5)
            if (r5 != 0) goto L17d
            kotlin.coroutines.CoroutineContext r0 = r0.plus(r4)
        L17d:
            if (r9 == 0) goto L199
            if (r9 != r3) goto L187
            kotlinx.coroutines.LazyStandaloneCoroutine r4 = new kotlinx.coroutines.LazyStandaloneCoroutine
            r4.<init>(r0, r2)
            goto L18c
        L187:
            kotlinx.coroutines.StandaloneCoroutine r4 = new kotlinx.coroutines.StandaloneCoroutine
            r4.<init>(r0, r11)
        L18c:
            r4.start(r9, r4, r2)
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$1 r0 = new androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$1
            r0.<init>(r3, r4)
            r8.addOnAttachStateChangeListener(r0)
            r0 = r10
            goto L1c7
        L199:
            throw r1
        L19a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "ViewTreeLifecycleOwner not found from "
            r0.<init>(r1)
            r0.append(r8)
            java.lang.String r0 = r0.toString()
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L1b2:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        L1b5:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "no AndroidUiDispatcher for this thread"
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L1c1:
            boolean r2 = r0 instanceof androidx.compose.runtime.Recomposer
            if (r2 == 0) goto L1e2
            androidx.compose.runtime.Recomposer r0 = (androidx.compose.runtime.Recomposer) r0
        L1c7:
            kotlinx.coroutines.flow.StateFlowImpl r2 = r0._state
            java.lang.Object r2 = r2.getValue()
            androidx.compose.runtime.Recomposer$State r2 = (androidx.compose.runtime.Recomposer.State) r2
            androidx.compose.runtime.Recomposer$State r3 = androidx.compose.runtime.Recomposer.State.ShuttingDown
            int r2 = r2.compareTo(r3)
            if (r2 <= 0) goto L1d8
            r1 = r0
        L1d8:
            if (r1 == 0) goto L20b
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference
            r2.<init>(r1)
            r13.cachedViewTreeCompositionContext = r2
            goto L20b
        L1e2:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "root viewTreeParentCompositionContext is not a Recomposer"
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L1ee:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Cannot locate windowRecomposer; View "
            r0.<init>(r1)
            r0.append(r13)
            java.lang.String r1 = " is not attached to a window"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L20b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AbstractComposeView.resolveParentCompositionContext():androidx.compose.runtime.CompositionContext");
    }

    public final void setParentCompositionContext(CompositionContext compositionContext) {
        setParentContext(compositionContext);
    }

    public final void setShowLayoutBounds(boolean z) {
        this.showLayoutBounds = z;
        KeyEvent.Callback childAt = getChildAt(0);
        if (childAt != null) {
            ((AndroidComposeView) ((Owner) childAt)).setShowLayoutBounds(z);
        }
    }

    @Override // android.view.ViewGroup
    public void setTransitionGroup(boolean z) {
        super.setTransitionGroup(z);
        this.isTransitionGroupSet = true;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [androidx.compose.ui.platform.AndroidTextToolbar, java.lang.Object] */
    public final void setViewCompositionStrategy(ViewCompositionStrategy viewCompositionStrategy) {
        ResultKt.checkNotNullParameter(viewCompositionStrategy, "strategy");
        ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$installFor$1 viewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$installFor$1 = this.disposeViewCompositionStrategy;
        if (viewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$installFor$1 != null) {
            viewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$installFor$1.invoke();
        }
        AndroidComposeViewAccessibilityDelegateCompat.AnonymousClass1 anonymousClass1 = new AndroidComposeViewAccessibilityDelegateCompat.AnonymousClass1(1, this);
        addOnAttachStateChangeListener(anonymousClass1);
        ?? obj = new Object();
        _BOUNDARY.getPoolingContainerListenerHolder(this).listeners.add(obj);
        this.disposeViewCompositionStrategy = new ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$installFor$1(this, anonymousClass1, obj);
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i) {
        checkAddView();
        super.addView(view, i);
    }

    @Override // android.view.ViewGroup
    public final boolean addViewInLayout(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        checkAddView();
        return super.addViewInLayout(view, i, layoutParams, z);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, int i2) {
        checkAddView();
        super.addView(view, i, i2);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        checkAddView();
        super.addView(view, layoutParams);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        checkAddView();
        super.addView(view, i, layoutParams);
    }
}
