package androidx.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.SystemClock;
import android.os.Trace;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.contextaware.ContextAwareHelper;
import androidx.activity.result.ActivityResultRegistry;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.compose.ui.draw.DrawResult;
import androidx.core.provider.FontRequestWorker$2;
import androidx.core.util.Consumer;
import androidx.core.view.MenuHostHelper;
import androidx.lifecycle.GeneratedAdapter;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.SavedStateHandle$$ExternalSyntheticLambda0;
import androidx.lifecycle.SavedStateHandlesProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import eu.malek.composesample.R;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.ResultKt;
import kotlin.SynchronizedLazyImpl;
import kotlin.ULong;
import kotlin.jvm.functions.Function0;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public abstract class ComponentActivity extends androidx.core.app.ComponentActivity implements ViewModelStoreOwner, HasDefaultViewModelProviderFactory, SavedStateRegistryOwner {
    public static final /* synthetic */ int $r8$clinit = 0;
    public ViewModelStore _viewModelStore;
    public final ComponentActivity$activityResultRegistry$1 activityResultRegistry;
    public final ContextAwareHelper contextAwareHelper;
    public boolean dispatchingOnMultiWindowModeChanged;
    public boolean dispatchingOnPictureInPictureModeChanged;
    public final SynchronizedLazyImpl fullyDrawnReporter$delegate;
    public final MenuHostHelper menuHostHelper;
    public final SynchronizedLazyImpl onBackPressedDispatcher$delegate;
    public final CopyOnWriteArrayList onConfigurationChangedListeners;
    public final CopyOnWriteArrayList onMultiWindowModeChangedListeners;
    public final CopyOnWriteArrayList onNewIntentListeners;
    public final CopyOnWriteArrayList onPictureInPictureModeChangedListeners;
    public final CopyOnWriteArrayList onTrimMemoryListeners;
    public final CopyOnWriteArrayList onUserLeaveHintListeners;
    public final ReportFullyDrawnExecutorImpl reportFullyDrawnExecutor;
    public final SavedStateRegistryController savedStateRegistryController;

    /* loaded from: classes.dex */
    public final class Api33Impl {
        public static final Api33Impl INSTANCE = new Object();

        public final OnBackInvokedDispatcher getOnBackInvokedDispatcher(Activity activity) {
            ResultKt.checkNotNullParameter(activity, "activity");
            OnBackInvokedDispatcher onBackInvokedDispatcher = activity.getOnBackInvokedDispatcher();
            ResultKt.checkNotNullExpressionValue(onBackInvokedDispatcher, "activity.getOnBackInvokedDispatcher()");
            return onBackInvokedDispatcher;
        }
    }

    /* loaded from: classes.dex */
    public final class NonConfigurationInstances {
        public ViewModelStore viewModelStore;
    }

    /* loaded from: classes.dex */
    public final class ReportFullyDrawnExecutorImpl implements ViewTreeObserver.OnDrawListener, Runnable, Executor {
        public Runnable currentRunnable;
        public final long endWatchTimeMillis = SystemClock.uptimeMillis() + 10000;
        public boolean onDrawScheduled;

        public ReportFullyDrawnExecutorImpl() {
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            ResultKt.checkNotNullParameter(runnable, "runnable");
            this.currentRunnable = runnable;
            View decorView = ComponentActivity.this.getWindow().getDecorView();
            ResultKt.checkNotNullExpressionValue(decorView, "window.decorView");
            if (!this.onDrawScheduled) {
                decorView.postOnAnimation(new FullyDrawnReporter$$ExternalSyntheticLambda0(1, this));
            } else if (ResultKt.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
                decorView.invalidate();
            } else {
                decorView.postInvalidate();
            }
        }

        @Override // android.view.ViewTreeObserver.OnDrawListener
        public final void onDraw() {
            boolean z;
            Runnable runnable = this.currentRunnable;
            if (runnable == null) {
                if (SystemClock.uptimeMillis() > this.endWatchTimeMillis) {
                    this.onDrawScheduled = false;
                    ComponentActivity.this.getWindow().getDecorView().post(this);
                    return;
                }
                return;
            }
            runnable.run();
            this.currentRunnable = null;
            FullyDrawnReporter fullyDrawnReporter = (FullyDrawnReporter) ComponentActivity.this.fullyDrawnReporter$delegate.getValue();
            synchronized (fullyDrawnReporter.lock) {
                z = fullyDrawnReporter.reportedFullyDrawn;
            }
            if (z) {
                this.onDrawScheduled = false;
                ComponentActivity.this.getWindow().getDecorView().post(this);
            }
        }

        @Override // java.lang.Runnable
        public final void run() {
            ComponentActivity.this.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
        }

        public final void viewCreated(View view) {
            if (this.onDrawScheduled) {
                return;
            }
            this.onDrawScheduled = true;
            view.getViewTreeObserver().addOnDrawListener(this);
        }
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [androidx.activity.ComponentActivity$activityResultRegistry$1, androidx.activity.result.ActivityResultRegistry] */
    public ComponentActivity() {
        ContextAwareHelper contextAwareHelper = new ContextAwareHelper();
        this.contextAwareHelper = contextAwareHelper;
        int i = 0;
        this.menuHostHelper = new MenuHostHelper(new ComponentActivity$$ExternalSyntheticLambda0(this, i));
        SavedStateRegistryController savedStateRegistryController = new SavedStateRegistryController(this);
        this.savedStateRegistryController = savedStateRegistryController;
        this.reportFullyDrawnExecutor = new ReportFullyDrawnExecutorImpl();
        this.fullyDrawnReporter$delegate = new SynchronizedLazyImpl(new ComponentActivity$fullyDrawnReporter$2(this, i));
        new AtomicInteger();
        this.activityResultRegistry = new ActivityResultRegistry();
        this.onConfigurationChangedListeners = new CopyOnWriteArrayList();
        this.onTrimMemoryListeners = new CopyOnWriteArrayList();
        this.onNewIntentListeners = new CopyOnWriteArrayList();
        this.onMultiWindowModeChangedListeners = new CopyOnWriteArrayList();
        this.onPictureInPictureModeChangedListeners = new CopyOnWriteArrayList();
        this.onUserLeaveHintListeners = new CopyOnWriteArrayList();
        LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
        if (lifecycleRegistry == null) {
            throw new IllegalStateException("getLifecycle() returned null in ComponentActivity's constructor. Please make sure you are lazily constructing your Lifecycle in the first call to getLifecycle() rather than relying on field initialization.".toString());
        }
        lifecycleRegistry.addObserver(new ComponentActivity$$ExternalSyntheticLambda1(0, this));
        this.lifecycleRegistry.addObserver(new ComponentActivity$$ExternalSyntheticLambda1(1, this));
        this.lifecycleRegistry.addObserver(new AnonymousClass4(this));
        savedStateRegistryController.performAttach();
        LifecycleRegistry lifecycleRegistry2 = this.lifecycleRegistry;
        Lifecycle.State state = lifecycleRegistry2.state;
        if (state != Lifecycle.State.INITIALIZED && state != Lifecycle.State.CREATED) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (savedStateRegistryController.savedStateRegistry.getSavedStateProvider() == null) {
            SavedStateHandlesProvider savedStateHandlesProvider = new SavedStateHandlesProvider(savedStateRegistryController.savedStateRegistry, this);
            savedStateRegistryController.savedStateRegistry.registerSavedStateProvider("androidx.lifecycle.internal.SavedStateHandlesProvider", savedStateHandlesProvider);
            lifecycleRegistry2.addObserver(new AnonymousClass4(savedStateHandlesProvider));
        }
        savedStateRegistryController.savedStateRegistry.registerSavedStateProvider("android:support:activity-result", new SavedStateHandle$$ExternalSyntheticLambda0(2, this));
        ComponentActivity$$ExternalSyntheticLambda2 componentActivity$$ExternalSyntheticLambda2 = new ComponentActivity$$ExternalSyntheticLambda2(this);
        Context context = contextAwareHelper.context;
        if (context != null) {
            componentActivity$$ExternalSyntheticLambda2.onContextAvailable(context);
        }
        contextAwareHelper.listeners.add(componentActivity$$ExternalSyntheticLambda2);
        this.onBackPressedDispatcher$delegate = new SynchronizedLazyImpl(new ComponentActivity$fullyDrawnReporter$2(this, 3));
    }

    /* JADX DEBUG: Method not inlined, still used in: [androidx.activity.ComponentActivity$$ExternalSyntheticLambda0.run():void] */
    public static final /* synthetic */ void access$onBackPressed$s1027565324(ComponentActivity componentActivity) {
        super.onBackPressed();
    }

    @Override // android.app.Activity
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        initializeViewTreeOwners();
        View decorView = getWindow().getDecorView();
        ResultKt.checkNotNullExpressionValue(decorView, "window.decorView");
        this.reportFullyDrawnExecutor.viewCreated(decorView);
        super.addContentView(view, layoutParams);
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public final LifecycleRegistry getLifecycle() {
        return this.lifecycleRegistry;
    }

    public final ViewModelStore getViewModelStore() {
        if (getApplication() == null) {
            throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.".toString());
        }
        if (this._viewModelStore == null) {
            NonConfigurationInstances nonConfigurationInstances = (NonConfigurationInstances) getLastNonConfigurationInstance();
            if (nonConfigurationInstances != null) {
                this._viewModelStore = nonConfigurationInstances.viewModelStore;
            }
            if (this._viewModelStore == null) {
                this._viewModelStore = new ViewModelStore(0);
            }
        }
        ViewModelStore viewModelStore = this._viewModelStore;
        ResultKt.checkNotNull(viewModelStore);
        return viewModelStore;
    }

    public final void initializeViewTreeOwners() {
        View decorView = getWindow().getDecorView();
        ResultKt.checkNotNullExpressionValue(decorView, "window.decorView");
        decorView.setTag(R.id.view_tree_lifecycle_owner, this);
        View decorView2 = getWindow().getDecorView();
        ResultKt.checkNotNullExpressionValue(decorView2, "window.decorView");
        decorView2.setTag(R.id.view_tree_view_model_store_owner, this);
        View decorView3 = getWindow().getDecorView();
        ResultKt.checkNotNullExpressionValue(decorView3, "window.decorView");
        decorView3.setTag(R.id.view_tree_saved_state_registry_owner, this);
        View decorView4 = getWindow().getDecorView();
        ResultKt.checkNotNullExpressionValue(decorView4, "window.decorView");
        decorView4.setTag(R.id.view_tree_on_back_pressed_dispatcher_owner, this);
        View decorView5 = getWindow().getDecorView();
        ResultKt.checkNotNullExpressionValue(decorView5, "window.decorView");
        decorView5.setTag(R.id.report_drawn, this);
    }

    @Override // android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        if (this.activityResultRegistry.dispatchResult(i, i2, intent)) {
            return;
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity
    public final void onBackPressed() {
        ((OnBackPressedDispatcher) this.onBackPressedDispatcher$delegate.getValue()).onBackPressed();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
        ResultKt.checkNotNullParameter(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
        Iterator it = this.onConfigurationChangedListeners.iterator();
        while (it.hasNext()) {
            ((FontRequestWorker$2) ((Consumer) it.next())).accept(configuration);
        }
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        SavedStateRegistryController savedStateRegistryController = this.savedStateRegistryController;
        if (!savedStateRegistryController.attached) {
            savedStateRegistryController.performAttach();
        }
        LifecycleRegistry lifecycleRegistry = ((ComponentActivity) savedStateRegistryController.owner).lifecycleRegistry;
        if (!(!(lifecycleRegistry.state.compareTo(Lifecycle.State.STARTED) >= 0))) {
            throw new IllegalStateException(("performRestore cannot be called when owner is " + lifecycleRegistry.state).toString());
        }
        SavedStateRegistry savedStateRegistry = savedStateRegistryController.savedStateRegistry;
        if (!savedStateRegistry.attached) {
            throw new IllegalStateException("You must call performAttach() before calling performRestore(Bundle).".toString());
        }
        if (!(!savedStateRegistry.isRestored)) {
            throw new IllegalStateException("SavedStateRegistry was already restored.".toString());
        }
        savedStateRegistry.restoredState = bundle != null ? bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key") : null;
        savedStateRegistry.isRestored = true;
        ContextAwareHelper contextAwareHelper = this.contextAwareHelper;
        contextAwareHelper.getClass();
        contextAwareHelper.context = this;
        Iterator it = contextAwareHelper.listeners.iterator();
        while (it.hasNext()) {
            ((ComponentActivity$$ExternalSyntheticLambda2) it.next()).onContextAvailable(this);
        }
        super.onCreate(bundle);
        int i = ReportFragment.$r8$clinit;
        ULong.Companion.injectIfNeededIn(this);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean onCreatePanelMenu(int i, Menu menu) {
        ResultKt.checkNotNullParameter(menu, "menu");
        if (i != 0) {
            return true;
        }
        super.onCreatePanelMenu(i, menu);
        getMenuInflater();
        Iterator it = ((CopyOnWriteArrayList) this.menuHostHelper.mMenuProviders).iterator();
        if (!it.hasNext()) {
            return true;
        }
        DurationKt$$ExternalSyntheticCheckNotZero0.m(it.next());
        throw null;
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        ResultKt.checkNotNullParameter(menuItem, "item");
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i != 0) {
            return false;
        }
        Iterator it = ((CopyOnWriteArrayList) this.menuHostHelper.mMenuProviders).iterator();
        if (!it.hasNext()) {
            return false;
        }
        DurationKt$$ExternalSyntheticCheckNotZero0.m(it.next());
        throw null;
    }

    @Override // android.app.Activity
    public final void onMultiWindowModeChanged(boolean z) {
        if (this.dispatchingOnMultiWindowModeChanged) {
            return;
        }
        Iterator it = this.onMultiWindowModeChangedListeners.iterator();
        while (it.hasNext()) {
            ((FontRequestWorker$2) ((Consumer) it.next())).accept(new Object());
        }
    }

    @Override // android.app.Activity
    public final void onNewIntent(Intent intent) {
        ResultKt.checkNotNullParameter(intent, "intent");
        super.onNewIntent(intent);
        Iterator it = this.onNewIntentListeners.iterator();
        while (it.hasNext()) {
            ((FontRequestWorker$2) ((Consumer) it.next())).accept(intent);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final void onPanelClosed(int i, Menu menu) {
        ResultKt.checkNotNullParameter(menu, "menu");
        Iterator it = ((CopyOnWriteArrayList) this.menuHostHelper.mMenuProviders).iterator();
        if (it.hasNext()) {
            DurationKt$$ExternalSyntheticCheckNotZero0.m(it.next());
            throw null;
        }
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    public final void onPictureInPictureModeChanged(boolean z) {
        if (this.dispatchingOnPictureInPictureModeChanged) {
            return;
        }
        Iterator it = this.onPictureInPictureModeChangedListeners.iterator();
        while (it.hasNext()) {
            ((FontRequestWorker$2) ((Consumer) it.next())).accept(new Object());
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public final boolean onPreparePanel(int i, View view, Menu menu) {
        ResultKt.checkNotNullParameter(menu, "menu");
        if (i != 0) {
            return true;
        }
        super.onPreparePanel(i, view, menu);
        Iterator it = ((CopyOnWriteArrayList) this.menuHostHelper.mMenuProviders).iterator();
        if (!it.hasNext()) {
            return true;
        }
        DurationKt$$ExternalSyntheticCheckNotZero0.m(it.next());
        throw null;
    }

    @Override // android.app.Activity
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        ResultKt.checkNotNullParameter(strArr, "permissions");
        ResultKt.checkNotNullParameter(iArr, "grantResults");
        if (this.activityResultRegistry.dispatchResult(i, -1, new Intent().putExtra("androidx.activity.result.contract.extra.PERMISSIONS", strArr).putExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS", iArr))) {
            return;
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.activity.ComponentActivity$NonConfigurationInstances, java.lang.Object] */
    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        NonConfigurationInstances nonConfigurationInstances;
        ViewModelStore viewModelStore = this._viewModelStore;
        if (viewModelStore == null && (nonConfigurationInstances = (NonConfigurationInstances) getLastNonConfigurationInstance()) != null) {
            viewModelStore = nonConfigurationInstances.viewModelStore;
        }
        if (viewModelStore == null) {
            return null;
        }
        ?? obj = new Object();
        obj.viewModelStore = viewModelStore;
        return obj;
    }

    @Override // androidx.core.app.ComponentActivity, android.app.Activity
    public final void onSaveInstanceState(Bundle bundle) {
        ResultKt.checkNotNullParameter(bundle, "outState");
        LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
        if (lifecycleRegistry instanceof LifecycleRegistry) {
            ResultKt.checkNotNull(lifecycleRegistry, "null cannot be cast to non-null type androidx.lifecycle.LifecycleRegistry");
            Lifecycle.State state = Lifecycle.State.CREATED;
            lifecycleRegistry.enforceMainThreadIfNeeded("setCurrentState");
            lifecycleRegistry.moveToState(state);
        }
        super.onSaveInstanceState(bundle);
        SavedStateRegistryController savedStateRegistryController = this.savedStateRegistryController;
        savedStateRegistryController.getClass();
        SavedStateRegistry savedStateRegistry = savedStateRegistryController.savedStateRegistry;
        savedStateRegistry.getClass();
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = savedStateRegistry.restoredState;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        SafeIterableMap safeIterableMap = savedStateRegistry.components;
        safeIterableMap.getClass();
        SafeIterableMap.IteratorWithAdditions iteratorWithAdditions = new SafeIterableMap.IteratorWithAdditions();
        safeIterableMap.mIterators.put(iteratorWithAdditions, Boolean.FALSE);
        while (iteratorWithAdditions.hasNext()) {
            Map.Entry entry = (Map.Entry) iteratorWithAdditions.next();
            bundle2.putBundle((String) entry.getKey(), ((SavedStateRegistry.SavedStateProvider) entry.getValue()).saveState());
        }
        if (bundle2.isEmpty()) {
            return;
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
        super.onTrimMemory(i);
        Iterator it = this.onTrimMemoryListeners.iterator();
        while (it.hasNext()) {
            ((FontRequestWorker$2) ((Consumer) it.next())).accept(Integer.valueOf(i));
        }
    }

    @Override // android.app.Activity
    public final void onUserLeaveHint() {
        super.onUserLeaveHint();
        Iterator it = this.onUserLeaveHintListeners.iterator();
        while (it.hasNext()) {
            ((Runnable) it.next()).run();
        }
    }

    @Override // android.app.Activity
    public final void reportFullyDrawn() {
        try {
            if (ResultKt.isEnabled()) {
                Trace.beginSection("reportFullyDrawn() for ComponentActivity");
            }
            super.reportFullyDrawn();
            FullyDrawnReporter fullyDrawnReporter = (FullyDrawnReporter) this.fullyDrawnReporter$delegate.getValue();
            synchronized (fullyDrawnReporter.lock) {
                try {
                    fullyDrawnReporter.reportedFullyDrawn = true;
                    Iterator it = fullyDrawnReporter.onReportCallbacks.iterator();
                    while (it.hasNext()) {
                        ((Function0) it.next()).invoke();
                    }
                    fullyDrawnReporter.onReportCallbacks.clear();
                } finally {
                }
            }
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    @Override // android.app.Activity
    public final void setContentView(int i) {
        initializeViewTreeOwners();
        View decorView = getWindow().getDecorView();
        ResultKt.checkNotNullExpressionValue(decorView, "window.decorView");
        this.reportFullyDrawnExecutor.viewCreated(decorView);
        super.setContentView(i);
    }

    @Override // android.app.Activity
    public final void startActivityForResult(Intent intent, int i) {
        ResultKt.checkNotNullParameter(intent, "intent");
        super.startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    public final void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) {
        ResultKt.checkNotNullParameter(intentSender, "intent");
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    @Override // android.app.Activity
    public final void startActivityForResult(Intent intent, int i, Bundle bundle) {
        ResultKt.checkNotNullParameter(intent, "intent");
        super.startActivityForResult(intent, i, bundle);
    }

    @Override // android.app.Activity
    public final void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        ResultKt.checkNotNullParameter(intentSender, "intent");
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    /* renamed from: androidx.activity.ComponentActivity$4 */
    /* loaded from: classes.dex */
    public final class AnonymousClass4 implements LifecycleEventObserver {
        public final /* synthetic */ int $r8$classId = 0;
        public final Object this$0;

        public AnonymousClass4(GeneratedAdapter[] generatedAdapterArr) {
            this.this$0 = generatedAdapterArr;
        }

        @Override // androidx.lifecycle.LifecycleEventObserver
        public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            int i = this.$r8$classId;
            Object obj = this.this$0;
            switch (i) {
                case 0:
                    ComponentActivity componentActivity = (ComponentActivity) obj;
                    if (componentActivity._viewModelStore == null) {
                        NonConfigurationInstances nonConfigurationInstances = (NonConfigurationInstances) componentActivity.getLastNonConfigurationInstance();
                        if (nonConfigurationInstances != null) {
                            componentActivity._viewModelStore = nonConfigurationInstances.viewModelStore;
                        }
                        if (componentActivity._viewModelStore == null) {
                            componentActivity._viewModelStore = new ViewModelStore(0);
                        }
                    }
                    componentActivity.lifecycleRegistry.removeObserver(this);
                    return;
                case 1:
                    new HashMap();
                    GeneratedAdapter[] generatedAdapterArr = (GeneratedAdapter[]) obj;
                    if (generatedAdapterArr.length > 0) {
                        GeneratedAdapter generatedAdapter = generatedAdapterArr[0];
                        throw null;
                    }
                    if (generatedAdapterArr.length <= 0) {
                        return;
                    }
                    GeneratedAdapter generatedAdapter2 = generatedAdapterArr[0];
                    throw null;
                case 2:
                    if (event == Lifecycle.Event.ON_CREATE) {
                        lifecycleOwner.getLifecycle().removeObserver(this);
                        ((SavedStateHandlesProvider) obj).performRestore();
                        return;
                    } else {
                        throw new IllegalStateException(("Next event must be ON_CREATE, it was " + event).toString());
                    }
                default:
                    DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                    throw null;
            }
        }

        public AnonymousClass4(SavedStateHandlesProvider savedStateHandlesProvider) {
            this.this$0 = savedStateHandlesProvider;
        }

        public AnonymousClass4(ComponentActivity componentActivity) {
            this.this$0 = componentActivity;
        }
    }

    @Override // android.app.Activity
    public final void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        ResultKt.checkNotNullParameter(configuration, "newConfig");
        this.dispatchingOnMultiWindowModeChanged = true;
        try {
            super.onMultiWindowModeChanged(z, configuration);
            this.dispatchingOnMultiWindowModeChanged = false;
            Iterator it = this.onMultiWindowModeChangedListeners.iterator();
            while (it.hasNext()) {
                ((FontRequestWorker$2) ((Consumer) it.next())).accept(new DrawResult(configuration, 0));
            }
        } catch (Throwable th) {
            this.dispatchingOnMultiWindowModeChanged = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public final void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        ResultKt.checkNotNullParameter(configuration, "newConfig");
        this.dispatchingOnPictureInPictureModeChanged = true;
        try {
            super.onPictureInPictureModeChanged(z, configuration);
            this.dispatchingOnPictureInPictureModeChanged = false;
            Iterator it = this.onPictureInPictureModeChangedListeners.iterator();
            while (it.hasNext()) {
                ((FontRequestWorker$2) ((Consumer) it.next())).accept(new DrawResult(configuration, 1));
            }
        } catch (Throwable th) {
            this.dispatchingOnPictureInPictureModeChanged = false;
            throw th;
        }
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        initializeViewTreeOwners();
        View decorView = getWindow().getDecorView();
        ResultKt.checkNotNullExpressionValue(decorView, "window.decorView");
        this.reportFullyDrawnExecutor.viewCreated(decorView);
        super.setContentView(view);
    }

    @Override // android.app.Activity
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        initializeViewTreeOwners();
        View decorView = getWindow().getDecorView();
        ResultKt.checkNotNullExpressionValue(decorView, "window.decorView");
        this.reportFullyDrawnExecutor.viewCreated(decorView);
        super.setContentView(view, layoutParams);
    }
}
