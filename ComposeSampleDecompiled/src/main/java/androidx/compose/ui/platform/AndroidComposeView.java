package androidx.compose.ui.platform;

import _COROUTINE.ArtificialStackFrames;
import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.os.Trace;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.view.animation.AnimationUtils;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import androidx.activity.FullyDrawnReporter$$ExternalSyntheticLambda0;
import androidx.compose.runtime.DerivedSnapshotState;
import androidx.compose.runtime.Latch;
import androidx.compose.runtime.ParcelableSnapshotMutableIntState;
import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.ReferentialEqualityPolicy;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import androidx.compose.runtime.SnapshotStateKt__DerivedStateKt;
import androidx.compose.runtime.Stack;
import androidx.compose.runtime.StructuralEqualityPolicy;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.Snapshot$Companion$registerApplyObserver$2;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import androidx.compose.runtime.snapshots.SnapshotWeakSet;
import androidx.compose.ui.ComposedModifierKt$materialize$result$1;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.autofill.AndroidAutofill;
import androidx.compose.ui.autofill.Autofill;
import androidx.compose.ui.autofill.AutofillApi23Helper;
import androidx.compose.ui.autofill.AutofillApi26Helper;
import androidx.compose.ui.autofill.AutofillCallback;
import androidx.compose.ui.autofill.AutofillTree;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusOwner;
import androidx.compose.ui.focus.FocusOwnerImpl;
import androidx.compose.ui.focus.FocusStateImpl;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.AndroidCanvas;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.hapticfeedback.HapticFeedback;
import androidx.compose.ui.input.InputMode;
import androidx.compose.ui.input.InputModeManager;
import androidx.compose.ui.input.InputModeManagerImpl;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.key.Key_androidKt;
import androidx.compose.ui.input.pointer.MotionEventAdapter;
import androidx.compose.ui.input.pointer.PointerIconService;
import androidx.compose.ui.input.pointer.PointerInputEvent;
import androidx.compose.ui.input.pointer.PointerInputEventData;
import androidx.compose.ui.input.pointer.PointerKeyboardModifiers;
import androidx.compose.ui.input.pointer.PositionCalculator;
import androidx.compose.ui.input.rotary.RotaryInputModifierKt;
import androidx.compose.ui.input.rotary.RotaryInputModifierNode;
import androidx.compose.ui.input.rotary.RotaryInputNode;
import androidx.compose.ui.input.rotary.RotaryScrollEvent;
import androidx.compose.ui.layout.RootMeasurePolicy;
import androidx.compose.ui.modifier.ModifierLocalManager;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.node.MeasureAndLayoutDelegate;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.OwnedLayer;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.OwnerSnapshotObserver;
import androidx.compose.ui.node.RootForTest;
import androidx.compose.ui.node.Snake;
import androidx.compose.ui.node.TreeSet;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.semantics.EmptySemanticsElement;
import androidx.compose.ui.semantics.SemanticsOwner;
import androidx.compose.ui.text.font.Font$ResourceLoader;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.input.AndroidTextInputServicePlugin;
import androidx.compose.ui.text.input.PlatformTextInputAdapter;
import androidx.compose.ui.text.input.PlatformTextInputPluginRegistryImpl;
import androidx.compose.ui.text.input.TextInputService;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityImpl;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewConfigurationCompat$Api26Impl;
import androidx.emoji2.text.EmojiProcessor;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner$findViewTreeSavedStateRegistryOwner$1;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt___MapsJvmKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;
import kotlin.sequences.SequencesKt;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class AndroidComposeView extends ViewGroup implements Owner, RootForTest, PositionCalculator, DefaultLifecycleObserver {
    public static Method getBooleanMethod;
    public static Class systemPropertiesClass;
    public AndroidViewsHandler _androidViewsHandler;
    public final AndroidAutofill _autofill;
    public final InputModeManagerImpl _inputModeManager;
    public final ParcelableSnapshotMutableState _viewTreeOwners$delegate;
    public final WindowInfoImpl _windowInfo;
    public final AndroidComposeViewAccessibilityDelegateCompat accessibilityDelegate;
    public final AndroidAccessibilityManager accessibilityManager;
    public final AutofillTree autofillTree;
    public final Stack canvasHolder;
    public final AndroidClipboardManager clipboardManager;
    public Function1 configurationChangeObserver;
    public final CoroutineContext coroutineContext;
    public int currentFontWeightAdjustment;
    public DensityImpl density;
    public final ArrayList dirtyLayers;
    public final MutableVector endApplyChangesListeners;
    public final FocusOwnerImpl focusOwner;
    public final ParcelableSnapshotMutableState fontFamilyResolver$delegate;
    public final AndroidTextToolbar fontLoader;
    public boolean forceUseMatrixCache;
    public final AndroidComposeView$$ExternalSyntheticLambda1 globalLayoutListener;
    public long globalPosition;
    public final DrawResult hapticFeedBack;
    public boolean hoverExitReceived;
    public boolean isDrawingContent;
    public boolean isRenderNodeCompatible;
    public boolean keyboardModifiersRequireUpdate;
    public long lastDownPointerPosition;
    public long lastMatrixRecalculationAnimationTime;
    public final WeakCache layerCache;
    public final ParcelableSnapshotMutableState layoutDirection$delegate;
    public final CalculateMatrixToWindow matrixToWindow;
    public final MeasureAndLayoutDelegate measureAndLayoutDelegate;
    public final ModifierLocalManager modifierLocalManager;
    public final MotionEventAdapter motionEventAdapter;
    public boolean observationClearRequested;
    public Constraints onMeasureConstraints;
    public Function1 onViewTreeOwnersAvailable;
    public final PlatformTextInputPluginRegistryImpl platformTextInputPluginRegistry;
    public final AndroidComposeView$pointerIconService$1 pointerIconService;
    public final EmojiProcessor pointerInputEventProcessor;
    public ArrayList postponedDirtyLayers;
    public MotionEvent previousMotionEvent;
    public long relayoutTime;
    public final AndroidComposeView$viewTreeOwners$2 resendMotionEventOnLayout;
    public final AndroidComposeView$resendMotionEventRunnable$1 resendMotionEventRunnable;
    public final LayoutNode root;
    public final AndroidComposeView rootForTest;
    public final AndroidComposeView$$ExternalSyntheticLambda2 scrollChangedListener;
    public final SemanticsOwner semanticsOwner;
    public final FullyDrawnReporter$$ExternalSyntheticLambda0 sendHoverExitEvent;
    public final LayoutNodeDrawScope sharedDrawScope;
    public boolean showLayoutBounds;
    public final OwnerSnapshotObserver snapshotObserver;
    public final boolean superclassInitComplete;
    public final TextInputService textInputService;
    public final AndroidTextToolbar textToolbar;
    public final int[] tmpPositionArray;
    public final AndroidComposeView$$ExternalSyntheticLambda3 touchModeChangeListener;
    public final AndroidViewConfiguration viewConfiguration;
    public DrawChildContainer viewLayersContainer;
    public final float[] viewToWindowMatrix;
    public final DerivedSnapshotState viewTreeOwners$delegate;
    public boolean wasMeasuredWithMultipleConstraints;
    public long windowPosition;
    public final float[] windowToViewMatrix;

    /* loaded from: classes.dex */
    public final class ViewTreeOwners {
        public final LifecycleOwner lifecycleOwner;
        public final SavedStateRegistryOwner savedStateRegistryOwner;

        public ViewTreeOwners(LifecycleOwner lifecycleOwner, SavedStateRegistryOwner savedStateRegistryOwner) {
            this.lifecycleOwner = lifecycleOwner;
            this.savedStateRegistryOwner = savedStateRegistryOwner;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r12v10, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r12v11, types: [androidx.compose.ui.platform.AndroidComposeView$resendMotionEventRunnable$1] */
    /* JADX WARN: Type inference failed for: r12v13, types: [androidx.compose.ui.platform.AndroidComposeView$viewTreeOwners$2] */
    /* JADX WARN: Type inference failed for: r12v5, types: [java.lang.Object, androidx.compose.ui.draw.DrawResult] */
    /* JADX WARN: Type inference failed for: r12v8, types: [androidx.compose.ui.platform.AndroidTextToolbar, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v15, types: [java.lang.Object, androidx.compose.ui.platform.AndroidAccessibilityManager] */
    /* JADX WARN: Type inference failed for: r2v18, types: [java.lang.Object, androidx.compose.ui.platform.AndroidViewConfiguration] */
    /* JADX WARN: Type inference failed for: r2v25, types: [androidx.compose.ui.platform.AndroidComposeView$focusOwner$1] */
    /* JADX WARN: Type inference failed for: r2v5, types: [androidx.compose.ui.platform.AndroidComposeView$focusOwner$1] */
    /* JADX WARN: Type inference failed for: r3v1, types: [androidx.compose.ui.platform.AndroidComposeView$focusOwner$1] */
    /* JADX WARN: Type inference failed for: r5v17, types: [androidx.compose.ui.platform.AndroidComposeView$viewTreeOwners$2] */
    /* JADX WARN: Type inference failed for: r5v18, types: [androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda1] */
    /* JADX WARN: Type inference failed for: r5v19, types: [androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda2] */
    /* JADX WARN: Type inference failed for: r5v20, types: [androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda3] */
    /* JADX WARN: Type inference failed for: r5v27, types: [androidx.compose.ui.platform.AndroidTextToolbar, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v9, types: [androidx.compose.ui.platform.AndroidComposeView$focusOwner$1] */
    public AndroidComposeView(Context context, CoroutineContext coroutineContext) {
        super(context);
        ResultKt.checkNotNullParameter(coroutineContext, "coroutineContext");
        this.lastDownPointerPosition = Offset.Unspecified;
        final int i = 1;
        this.superclassInitComplete = true;
        this.sharedDrawScope = new LayoutNodeDrawScope();
        this.density = new DensityImpl(context.getResources().getDisplayMetrics().density, context.getResources().getConfiguration().fontScale);
        EmptySemanticsElement emptySemanticsElement = EmptySemanticsElement.INSTANCE;
        final int i2 = 0;
        this.focusOwner = new FocusOwnerImpl(new Function1(this) { // from class: androidx.compose.ui.platform.AndroidComposeView$focusOwner$1
            public final /* synthetic */ AndroidComposeView this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            public final void invoke(Function0 function0) {
                int i3 = i2;
                AndroidComposeView androidComposeView = this.this$0;
                switch (i3) {
                    case 0:
                        ResultKt.checkNotNullParameter(function0, "it");
                        androidComposeView.registerOnEndApplyChangesListener(function0);
                        return;
                    default:
                        ResultKt.checkNotNullParameter(function0, "command");
                        Handler handler = androidComposeView.getHandler();
                        if ((handler != null ? handler.getLooper() : null) == Looper.myLooper()) {
                            function0.invoke();
                            return;
                        }
                        Handler handler2 = androidComposeView.getHandler();
                        if (handler2 != null) {
                            handler2.post(new FullyDrawnReporter$$ExternalSyntheticLambda0(4, function0));
                            return;
                        }
                        return;
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                FocusDirection focusDirection;
                Unit unit = Unit.INSTANCE;
                int i3 = i2;
                boolean z = true;
                AndroidComposeView androidComposeView = this.this$0;
                switch (i3) {
                    case 0:
                        invoke((Function0) obj);
                        return unit;
                    case 1:
                        int i4 = ((InputMode) obj).value;
                        if (i4 == 1) {
                            z = androidComposeView.isInTouchMode();
                        } else if (i4 != 2) {
                            z = false;
                        } else if (androidComposeView.isInTouchMode()) {
                            z = androidComposeView.requestFocusFromTouch();
                        }
                        return Boolean.valueOf(z);
                    case 2:
                        KeyEvent keyEvent = ((androidx.compose.ui.input.key.KeyEvent) obj).nativeKeyEvent;
                        ResultKt.checkNotNullParameter(keyEvent, "it");
                        androidComposeView.getClass();
                        long m151getKeyZmokQxo = Key_androidKt.m151getKeyZmokQxo(keyEvent);
                        if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.Tab)) {
                            focusDirection = new FocusDirection(keyEvent.isShiftPressed() ? 2 : 1);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionRight)) {
                            focusDirection = new FocusDirection(4);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionLeft)) {
                            focusDirection = new FocusDirection(3);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionUp)) {
                            focusDirection = new FocusDirection(5);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionDown)) {
                            focusDirection = new FocusDirection(6);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionCenter) || Key.m150equalsimpl0(m151getKeyZmokQxo, Key.Enter) || Key.m150equalsimpl0(m151getKeyZmokQxo, Key.NumPadEnter)) {
                            focusDirection = new FocusDirection(7);
                        } else {
                            focusDirection = (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.Back) || Key.m150equalsimpl0(m151getKeyZmokQxo, Key.Escape)) ? new FocusDirection(8) : null;
                        }
                        if (focusDirection != null && Key_androidKt.m152getTypeZmokQxo(keyEvent) == 2) {
                            return Boolean.valueOf(((FocusOwnerImpl) androidComposeView.getFocusOwner()).m69moveFocus3ESFkO8(focusDirection.value));
                        }
                        return Boolean.FALSE;
                    default:
                        invoke((Function0) obj);
                        return unit;
                }
            }
        });
        this._windowInfo = new WindowInfoImpl();
        Modifier onKeyEvent = Key_androidKt.onKeyEvent(new Function1(this) { // from class: androidx.compose.ui.platform.AndroidComposeView$focusOwner$1
            public final /* synthetic */ AndroidComposeView this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            public final void invoke(Function0 function0) {
                int i3 = r2;
                AndroidComposeView androidComposeView = this.this$0;
                switch (i3) {
                    case 0:
                        ResultKt.checkNotNullParameter(function0, "it");
                        androidComposeView.registerOnEndApplyChangesListener(function0);
                        return;
                    default:
                        ResultKt.checkNotNullParameter(function0, "command");
                        Handler handler = androidComposeView.getHandler();
                        if ((handler != null ? handler.getLooper() : null) == Looper.myLooper()) {
                            function0.invoke();
                            return;
                        }
                        Handler handler2 = androidComposeView.getHandler();
                        if (handler2 != null) {
                            handler2.post(new FullyDrawnReporter$$ExternalSyntheticLambda0(4, function0));
                            return;
                        }
                        return;
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                FocusDirection focusDirection;
                Unit unit = Unit.INSTANCE;
                int i3 = r2;
                boolean z = true;
                AndroidComposeView androidComposeView = this.this$0;
                switch (i3) {
                    case 0:
                        invoke((Function0) obj);
                        return unit;
                    case 1:
                        int i4 = ((InputMode) obj).value;
                        if (i4 == 1) {
                            z = androidComposeView.isInTouchMode();
                        } else if (i4 != 2) {
                            z = false;
                        } else if (androidComposeView.isInTouchMode()) {
                            z = androidComposeView.requestFocusFromTouch();
                        }
                        return Boolean.valueOf(z);
                    case 2:
                        KeyEvent keyEvent = ((androidx.compose.ui.input.key.KeyEvent) obj).nativeKeyEvent;
                        ResultKt.checkNotNullParameter(keyEvent, "it");
                        androidComposeView.getClass();
                        long m151getKeyZmokQxo = Key_androidKt.m151getKeyZmokQxo(keyEvent);
                        if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.Tab)) {
                            focusDirection = new FocusDirection(keyEvent.isShiftPressed() ? 2 : 1);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionRight)) {
                            focusDirection = new FocusDirection(4);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionLeft)) {
                            focusDirection = new FocusDirection(3);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionUp)) {
                            focusDirection = new FocusDirection(5);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionDown)) {
                            focusDirection = new FocusDirection(6);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionCenter) || Key.m150equalsimpl0(m151getKeyZmokQxo, Key.Enter) || Key.m150equalsimpl0(m151getKeyZmokQxo, Key.NumPadEnter)) {
                            focusDirection = new FocusDirection(7);
                        } else {
                            focusDirection = (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.Back) || Key.m150equalsimpl0(m151getKeyZmokQxo, Key.Escape)) ? new FocusDirection(8) : null;
                        }
                        if (focusDirection != null && Key_androidKt.m152getTypeZmokQxo(keyEvent) == 2) {
                            return Boolean.valueOf(((FocusOwnerImpl) androidComposeView.getFocusOwner()).m69moveFocus3ESFkO8(focusDirection.value));
                        }
                        return Boolean.FALSE;
                    default:
                        invoke((Function0) obj);
                        return unit;
                }
            }
        });
        Modifier onRotaryScrollEvent = RotaryInputModifierKt.onRotaryScrollEvent();
        this.canvasHolder = new Stack(5);
        final int i3 = 3;
        LayoutNode layoutNode = new LayoutNode(false, 3);
        layoutNode.setMeasurePolicy(RootMeasurePolicy.INSTANCE);
        layoutNode.setDensity(getDensity());
        ResultKt.checkNotNullParameter(emptySemanticsElement, "other");
        layoutNode.setModifier(emptySemanticsElement.then(onRotaryScrollEvent).then(((FocusOwnerImpl) getFocusOwner()).modifier).then(onKeyEvent));
        this.root = layoutNode;
        this.rootForTest = this;
        this.semanticsOwner = new SemanticsOwner(getRoot());
        AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = new AndroidComposeViewAccessibilityDelegateCompat(this);
        this.accessibilityDelegate = androidComposeViewAccessibilityDelegateCompat;
        this.autofillTree = new AutofillTree();
        this.dirtyLayers = new ArrayList();
        this.motionEventAdapter = new MotionEventAdapter();
        this.pointerInputEventProcessor = new EmojiProcessor(getRoot());
        this.configurationChangeObserver = InspectableValueKt$NoInspectorInfo$1.INSTANCE$1;
        this._autofill = new AndroidAutofill(this, getAutofillTree());
        this.clipboardManager = new AndroidClipboardManager(context);
        ?? obj = new Object();
        Object systemService = context.getSystemService("accessibility");
        ResultKt.checkNotNull(systemService, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        this.accessibilityManager = obj;
        this.snapshotObserver = new OwnerSnapshotObserver(new Function1(this) { // from class: androidx.compose.ui.platform.AndroidComposeView$focusOwner$1
            public final /* synthetic */ AndroidComposeView this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            public final void invoke(Function0 function0) {
                int i32 = i3;
                AndroidComposeView androidComposeView = this.this$0;
                switch (i32) {
                    case 0:
                        ResultKt.checkNotNullParameter(function0, "it");
                        androidComposeView.registerOnEndApplyChangesListener(function0);
                        return;
                    default:
                        ResultKt.checkNotNullParameter(function0, "command");
                        Handler handler = androidComposeView.getHandler();
                        if ((handler != null ? handler.getLooper() : null) == Looper.myLooper()) {
                            function0.invoke();
                            return;
                        }
                        Handler handler2 = androidComposeView.getHandler();
                        if (handler2 != null) {
                            handler2.post(new FullyDrawnReporter$$ExternalSyntheticLambda0(4, function0));
                            return;
                        }
                        return;
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                FocusDirection focusDirection;
                Unit unit = Unit.INSTANCE;
                int i32 = i3;
                boolean z = true;
                AndroidComposeView androidComposeView = this.this$0;
                switch (i32) {
                    case 0:
                        invoke((Function0) obj2);
                        return unit;
                    case 1:
                        int i4 = ((InputMode) obj2).value;
                        if (i4 == 1) {
                            z = androidComposeView.isInTouchMode();
                        } else if (i4 != 2) {
                            z = false;
                        } else if (androidComposeView.isInTouchMode()) {
                            z = androidComposeView.requestFocusFromTouch();
                        }
                        return Boolean.valueOf(z);
                    case 2:
                        KeyEvent keyEvent = ((androidx.compose.ui.input.key.KeyEvent) obj2).nativeKeyEvent;
                        ResultKt.checkNotNullParameter(keyEvent, "it");
                        androidComposeView.getClass();
                        long m151getKeyZmokQxo = Key_androidKt.m151getKeyZmokQxo(keyEvent);
                        if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.Tab)) {
                            focusDirection = new FocusDirection(keyEvent.isShiftPressed() ? 2 : 1);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionRight)) {
                            focusDirection = new FocusDirection(4);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionLeft)) {
                            focusDirection = new FocusDirection(3);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionUp)) {
                            focusDirection = new FocusDirection(5);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionDown)) {
                            focusDirection = new FocusDirection(6);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionCenter) || Key.m150equalsimpl0(m151getKeyZmokQxo, Key.Enter) || Key.m150equalsimpl0(m151getKeyZmokQxo, Key.NumPadEnter)) {
                            focusDirection = new FocusDirection(7);
                        } else {
                            focusDirection = (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.Back) || Key.m150equalsimpl0(m151getKeyZmokQxo, Key.Escape)) ? new FocusDirection(8) : null;
                        }
                        if (focusDirection != null && Key_androidKt.m152getTypeZmokQxo(keyEvent) == 2) {
                            return Boolean.valueOf(((FocusOwnerImpl) androidComposeView.getFocusOwner()).m69moveFocus3ESFkO8(focusDirection.value));
                        }
                        return Boolean.FALSE;
                    default:
                        invoke((Function0) obj2);
                        return unit;
                }
            }
        });
        this.measureAndLayoutDelegate = new MeasureAndLayoutDelegate(getRoot());
        ResultKt.checkNotNullExpressionValue(android.view.ViewConfiguration.get(context), "get(context)");
        this.viewConfiguration = new Object();
        this.globalPosition = ResultKt.IntOffset(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.tmpPositionArray = new int[]{0, 0};
        this.viewToWindowMatrix = Brush.m97constructorimpl$default();
        this.windowToViewMatrix = Brush.m97constructorimpl$default();
        this.lastMatrixRecalculationAnimationTime = -1L;
        this.windowPosition = Offset.Infinite;
        this.isRenderNodeCompatible = true;
        StructuralEqualityPolicy structuralEqualityPolicy = StructuralEqualityPolicy.INSTANCE;
        this._viewTreeOwners$delegate = ResultKt.mutableStateOf(null, structuralEqualityPolicy);
        ?? r5 = new Function0(this) { // from class: androidx.compose.ui.platform.AndroidComposeView$viewTreeOwners$2
            public final /* synthetic */ AndroidComposeView this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                AndroidComposeView.ViewTreeOwners viewTreeOwners;
                int actionMasked;
                int i4 = i2;
                AndroidComposeView androidComposeView = this.this$0;
                switch (i4) {
                    case 0:
                        viewTreeOwners = androidComposeView.get_viewTreeOwners();
                        return viewTreeOwners;
                    default:
                        MotionEvent motionEvent = androidComposeView.previousMotionEvent;
                        if (motionEvent != null && ((actionMasked = motionEvent.getActionMasked()) == 7 || actionMasked == 9)) {
                            androidComposeView.relayoutTime = SystemClock.uptimeMillis();
                            androidComposeView.post(androidComposeView.resendMotionEventRunnable);
                        }
                        return Unit.INSTANCE;
                }
            }
        };
        WeakCache weakCache = SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel;
        this.viewTreeOwners$delegate = new DerivedSnapshotState(r5);
        this.globalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                AndroidComposeView androidComposeView = AndroidComposeView.this;
                ResultKt.checkNotNullParameter(androidComposeView, "this$0");
                androidComposeView.updatePositionCacheAndDispatch();
            }
        };
        this.scrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() { // from class: androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda2
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public final void onScrollChanged() {
                AndroidComposeView androidComposeView = AndroidComposeView.this;
                ResultKt.checkNotNullParameter(androidComposeView, "this$0");
                androidComposeView.updatePositionCacheAndDispatch();
            }
        };
        this.touchModeChangeListener = new ViewTreeObserver.OnTouchModeChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeView$$ExternalSyntheticLambda3
            @Override // android.view.ViewTreeObserver.OnTouchModeChangeListener
            public final void onTouchModeChanged(boolean z) {
                AndroidComposeView androidComposeView = AndroidComposeView.this;
                ResultKt.checkNotNullParameter(androidComposeView, "this$0");
                int i4 = z ? 1 : 2;
                InputModeManagerImpl inputModeManagerImpl = androidComposeView._inputModeManager;
                inputModeManagerImpl.getClass();
                inputModeManagerImpl.inputMode$delegate.setValue(new InputMode(i4));
            }
        };
        this.platformTextInputPluginRegistry = new PlatformTextInputPluginRegistryImpl(new ComposedModifierKt$materialize$result$1(7, this));
        PlatformTextInputPluginRegistryImpl platformTextInputPluginRegistry = getPlatformTextInputPluginRegistry();
        AndroidTextInputServicePlugin androidTextInputServicePlugin = AndroidTextInputServicePlugin.INSTANCE;
        platformTextInputPluginRegistry.getClass();
        SnapshotStateMap snapshotStateMap = platformTextInputPluginRegistry.adaptersByPlugin;
        PlatformTextInputPluginRegistryImpl.AdapterWithRefCount adapterWithRefCount = (PlatformTextInputPluginRegistryImpl.AdapterWithRefCount) snapshotStateMap.get(androidTextInputServicePlugin);
        if (adapterWithRefCount == null) {
            Object invoke = platformTextInputPluginRegistry.factory.invoke(androidTextInputServicePlugin, new Object());
            ResultKt.checkNotNull(invoke, "null cannot be cast to non-null type T of androidx.compose.ui.text.input.PlatformTextInputPluginRegistryImpl.instantiateAdapter");
            PlatformTextInputPluginRegistryImpl.AdapterWithRefCount adapterWithRefCount2 = new PlatformTextInputPluginRegistryImpl.AdapterWithRefCount((PlatformTextInputAdapter) invoke);
            snapshotStateMap.put(androidTextInputServicePlugin, adapterWithRefCount2);
            adapterWithRefCount = adapterWithRefCount2;
        }
        ParcelableSnapshotMutableIntState parcelableSnapshotMutableIntState = adapterWithRefCount.refCount$delegate;
        parcelableSnapshotMutableIntState.setIntValue(((SnapshotMutableIntStateImpl.IntStateStateRecord) SnapshotKt.readable(parcelableSnapshotMutableIntState.next, parcelableSnapshotMutableIntState)).value + 1);
        PlatformTextInputAdapter platformTextInputAdapter = adapterWithRefCount.adapter;
        ResultKt.checkNotNullParameter(platformTextInputAdapter, "adapter");
        this.textInputService = ((AndroidTextInputServicePlugin.Adapter) platformTextInputAdapter).service;
        this.fontLoader = new Object();
        this.fontFamilyResolver$delegate = ResultKt.mutableStateOf(ResultKt.createFontFamilyResolver(context), ReferentialEqualityPolicy.INSTANCE);
        Configuration configuration = context.getResources().getConfiguration();
        ResultKt.checkNotNullExpressionValue(configuration, "context.resources.configuration");
        int i4 = Build.VERSION.SDK_INT;
        this.currentFontWeightAdjustment = i4 >= 31 ? configuration.fontWeightAdjustment : 0;
        Configuration configuration2 = context.getResources().getConfiguration();
        ResultKt.checkNotNullExpressionValue(configuration2, "context.resources.configuration");
        int layoutDirection = configuration2.getLayoutDirection();
        LayoutDirection layoutDirection2 = LayoutDirection.Ltr;
        if (layoutDirection != 0 && layoutDirection == 1) {
            layoutDirection2 = LayoutDirection.Rtl;
        }
        this.layoutDirection$delegate = ResultKt.mutableStateOf(layoutDirection2, structuralEqualityPolicy);
        this.hapticFeedBack = new Object();
        this._inputModeManager = new InputModeManagerImpl(isInTouchMode() ? 1 : 2, new Function1(this) { // from class: androidx.compose.ui.platform.AndroidComposeView$focusOwner$1
            public final /* synthetic */ AndroidComposeView this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
                this.this$0 = this;
            }

            public final void invoke(Function0 function0) {
                int i32 = i;
                AndroidComposeView androidComposeView = this.this$0;
                switch (i32) {
                    case 0:
                        ResultKt.checkNotNullParameter(function0, "it");
                        androidComposeView.registerOnEndApplyChangesListener(function0);
                        return;
                    default:
                        ResultKt.checkNotNullParameter(function0, "command");
                        Handler handler = androidComposeView.getHandler();
                        if ((handler != null ? handler.getLooper() : null) == Looper.myLooper()) {
                            function0.invoke();
                            return;
                        }
                        Handler handler2 = androidComposeView.getHandler();
                        if (handler2 != null) {
                            handler2.post(new FullyDrawnReporter$$ExternalSyntheticLambda0(4, function0));
                            return;
                        }
                        return;
                }
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                FocusDirection focusDirection;
                Unit unit = Unit.INSTANCE;
                int i32 = i;
                boolean z = true;
                AndroidComposeView androidComposeView = this.this$0;
                switch (i32) {
                    case 0:
                        invoke((Function0) obj2);
                        return unit;
                    case 1:
                        int i42 = ((InputMode) obj2).value;
                        if (i42 == 1) {
                            z = androidComposeView.isInTouchMode();
                        } else if (i42 != 2) {
                            z = false;
                        } else if (androidComposeView.isInTouchMode()) {
                            z = androidComposeView.requestFocusFromTouch();
                        }
                        return Boolean.valueOf(z);
                    case 2:
                        KeyEvent keyEvent = ((androidx.compose.ui.input.key.KeyEvent) obj2).nativeKeyEvent;
                        ResultKt.checkNotNullParameter(keyEvent, "it");
                        androidComposeView.getClass();
                        long m151getKeyZmokQxo = Key_androidKt.m151getKeyZmokQxo(keyEvent);
                        if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.Tab)) {
                            focusDirection = new FocusDirection(keyEvent.isShiftPressed() ? 2 : 1);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionRight)) {
                            focusDirection = new FocusDirection(4);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionLeft)) {
                            focusDirection = new FocusDirection(3);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionUp)) {
                            focusDirection = new FocusDirection(5);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionDown)) {
                            focusDirection = new FocusDirection(6);
                        } else if (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.DirectionCenter) || Key.m150equalsimpl0(m151getKeyZmokQxo, Key.Enter) || Key.m150equalsimpl0(m151getKeyZmokQxo, Key.NumPadEnter)) {
                            focusDirection = new FocusDirection(7);
                        } else {
                            focusDirection = (Key.m150equalsimpl0(m151getKeyZmokQxo, Key.Back) || Key.m150equalsimpl0(m151getKeyZmokQxo, Key.Escape)) ? new FocusDirection(8) : null;
                        }
                        if (focusDirection != null && Key_androidKt.m152getTypeZmokQxo(keyEvent) == 2) {
                            return Boolean.valueOf(((FocusOwnerImpl) androidComposeView.getFocusOwner()).m69moveFocus3ESFkO8(focusDirection.value));
                        }
                        return Boolean.FALSE;
                    default:
                        invoke((Function0) obj2);
                        return unit;
                }
            }
        });
        this.modifierLocalManager = new ModifierLocalManager(this);
        this.textToolbar = new Object();
        this.coroutineContext = coroutineContext;
        this.layerCache = new WeakCache(0);
        ?? obj2 = new Object();
        obj2.content = new Function0[16];
        obj2.size = 0;
        this.endApplyChangesListeners = obj2;
        this.resendMotionEventRunnable = new Runnable() { // from class: androidx.compose.ui.platform.AndroidComposeView$resendMotionEventRunnable$1
            @Override // java.lang.Runnable
            public final void run() {
                AndroidComposeView androidComposeView = AndroidComposeView.this;
                androidComposeView.removeCallbacks(this);
                MotionEvent motionEvent = androidComposeView.previousMotionEvent;
                if (motionEvent != null) {
                    boolean z = motionEvent.getToolType(0) == 3;
                    int actionMasked = motionEvent.getActionMasked();
                    if (z) {
                        if (actionMasked == 10 || actionMasked == 1) {
                            return;
                        }
                    } else if (actionMasked == 1) {
                        return;
                    }
                    int i5 = 7;
                    if (actionMasked != 7 && actionMasked != 9) {
                        i5 = 2;
                    }
                    AndroidComposeView androidComposeView2 = AndroidComposeView.this;
                    androidComposeView2.sendSimulatedEvent(motionEvent, i5, androidComposeView2.relayoutTime, false);
                }
            }
        };
        this.sendHoverExitEvent = new FullyDrawnReporter$$ExternalSyntheticLambda0(i3, this);
        this.resendMotionEventOnLayout = new Function0(this) { // from class: androidx.compose.ui.platform.AndroidComposeView$viewTreeOwners$2
            public final /* synthetic */ AndroidComposeView this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                AndroidComposeView.ViewTreeOwners viewTreeOwners;
                int actionMasked;
                int i42 = i;
                AndroidComposeView androidComposeView = this.this$0;
                switch (i42) {
                    case 0:
                        viewTreeOwners = androidComposeView.get_viewTreeOwners();
                        return viewTreeOwners;
                    default:
                        MotionEvent motionEvent = androidComposeView.previousMotionEvent;
                        if (motionEvent != null && ((actionMasked = motionEvent.getActionMasked()) == 7 || actionMasked == 9)) {
                            androidComposeView.relayoutTime = SystemClock.uptimeMillis();
                            androidComposeView.post(androidComposeView.resendMotionEventRunnable);
                        }
                        return Unit.INSTANCE;
                }
            }
        };
        this.matrixToWindow = i4 >= 29 ? new CalculateMatrixToWindowApi29() : new CalculateMatrixToWindowApi21();
        setWillNotDraw(false);
        setFocusable(true);
        AndroidComposeViewVerificationHelperMethodsO.INSTANCE.focusable(this, 1, false);
        setFocusableInTouchMode(true);
        setClipChildren(false);
        int i5 = ViewCompat.$r8$clinit;
        if (getImportantForAccessibility() == 0) {
            setImportantForAccessibility(1);
        }
        setAccessibilityDelegate(androidComposeViewAccessibilityDelegateCompat.mBridge);
        getRoot().attach$ui_release(this);
        if (i4 >= 29) {
            AndroidComposeViewForceDarkModeQ.INSTANCE.disallowForceDark(this);
        }
        this.pointerIconService = new AndroidComposeView$pointerIconService$1(this);
    }

    public static void clearChildInvalidObservations(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof AndroidComposeView) {
                ((AndroidComposeView) childAt).onEndApplyChanges();
            } else if (childAt instanceof ViewGroup) {
                clearChildInvalidObservations((ViewGroup) childAt);
            }
        }
    }

    /* renamed from: convertMeasureSpec-I7RO_PI, reason: not valid java name */
    public static long m216convertMeasureSpecI7RO_PI(int i) {
        long j;
        long j2;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE) {
            j = 0 << 32;
        } else {
            if (mode != 0) {
                if (mode != 1073741824) {
                    throw new IllegalStateException();
                }
                j2 = size;
                j = j2 << 32;
                return j | j2;
            }
            j = 0 << 32;
            size = Integer.MAX_VALUE;
        }
        j2 = size;
        return j | j2;
    }

    public static View findViewByAccessibilityIdRootedAtCurrentView(View view, int i) {
        if (Build.VERSION.SDK_INT >= 29) {
            return null;
        }
        Method declaredMethod = View.class.getDeclaredMethod("getAccessibilityViewId", new Class[0]);
        declaredMethod.setAccessible(true);
        if (ResultKt.areEqual(declaredMethod.invoke(view, new Object[0]), Integer.valueOf(i))) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            ResultKt.checkNotNullExpressionValue(childAt, "currentView.getChildAt(i)");
            View findViewByAccessibilityIdRootedAtCurrentView = findViewByAccessibilityIdRootedAtCurrentView(childAt, i);
            if (findViewByAccessibilityIdRootedAtCurrentView != null) {
                return findViewByAccessibilityIdRootedAtCurrentView;
            }
        }
        return null;
    }

    public static /* synthetic */ void getFontLoader$annotations() {
    }

    public static /* synthetic */ void getLastMatrixRecalculationAnimationTime$ui_release$annotations() {
    }

    public static /* synthetic */ void getShowLayoutBounds$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ViewTreeOwners get_viewTreeOwners() {
        return (ViewTreeOwners) this._viewTreeOwners$delegate.getValue();
    }

    public static void invalidateLayers(LayoutNode layoutNode) {
        layoutNode.invalidateLayers$ui_release();
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                invalidateLayers((LayoutNode) objArr[i2]);
                i2++;
            } while (i2 < i);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0082 A[LOOP:0: B:20:0x004c->B:35:0x0082, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0085 A[EDGE_INSN: B:36:0x0085->B:39:0x0085 BREAK  A[LOOP:0: B:20:0x004c->B:35:0x0082], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isBadMotionEvent(android.view.MotionEvent r6) {
        /*
            float r0 = r6.getX()
            boolean r1 = java.lang.Float.isInfinite(r0)
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L44
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L44
            float r0 = r6.getY()
            boolean r1 = java.lang.Float.isInfinite(r0)
            if (r1 != 0) goto L44
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L44
            float r0 = r6.getRawX()
            boolean r1 = java.lang.Float.isInfinite(r0)
            if (r1 != 0) goto L44
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L44
            float r0 = r6.getRawY()
            boolean r1 = java.lang.Float.isInfinite(r0)
            if (r1 != 0) goto L44
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L44
            r0 = r2
            goto L45
        L44:
            r0 = r3
        L45:
            if (r0 != 0) goto L85
            int r1 = r6.getPointerCount()
            r4 = r3
        L4c:
            if (r4 >= r1) goto L85
            float r0 = r6.getX(r4)
            boolean r5 = java.lang.Float.isInfinite(r0)
            if (r5 != 0) goto L7f
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L7f
            float r0 = r6.getY(r4)
            boolean r5 = java.lang.Float.isInfinite(r0)
            if (r5 != 0) goto L7f
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L7f
            int r0 = android.os.Build.VERSION.SDK_INT
            r5 = 29
            if (r0 < r5) goto L7d
            androidx.compose.ui.platform.MotionEventVerifierApi29 r0 = androidx.compose.ui.platform.MotionEventVerifierApi29.INSTANCE
            boolean r0 = r0.isValidMotionEvent(r6, r4)
            if (r0 != 0) goto L7d
            goto L7f
        L7d:
            r0 = r2
            goto L80
        L7f:
            r0 = r3
        L80:
            if (r0 != 0) goto L85
            int r4 = r4 + 1
            goto L4c
        L85:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView.isBadMotionEvent(android.view.MotionEvent):boolean");
    }

    private void setFontFamilyResolver(FontFamily.Resolver resolver) {
        this.fontFamilyResolver$delegate.setValue(resolver);
    }

    private void setLayoutDirection(LayoutDirection layoutDirection) {
        this.layoutDirection$delegate.setValue(layoutDirection);
    }

    private final void set_viewTreeOwners(ViewTreeOwners viewTreeOwners) {
        this._viewTreeOwners$delegate.setValue(viewTreeOwners);
    }

    @Override // android.view.View
    public final void autofill(SparseArray sparseArray) {
        ResultKt.checkNotNullParameter(sparseArray, "values");
        AndroidAutofill androidAutofill = this._autofill;
        if (androidAutofill != null) {
            int size = sparseArray.size();
            for (int i = 0; i < size; i++) {
                int keyAt = sparseArray.keyAt(i);
                AutofillValue autofillValue = (AutofillValue) sparseArray.get(keyAt);
                AutofillApi26Helper autofillApi26Helper = AutofillApi26Helper.INSTANCE;
                ResultKt.checkNotNullExpressionValue(autofillValue, "value");
                if (autofillApi26Helper.isText(autofillValue)) {
                    String obj = autofillApi26Helper.textValue(autofillValue).toString();
                    AutofillTree autofillTree = androidAutofill.autofillTree;
                    autofillTree.getClass();
                    ResultKt.checkNotNullParameter(obj, "value");
                    DurationKt$$ExternalSyntheticCheckNotZero0.m(autofillTree.children.get(Integer.valueOf(keyAt)));
                } else {
                    if (autofillApi26Helper.isDate(autofillValue)) {
                        throw new Error("An operation is not implemented: b/138604541: Add onFill() callback for date");
                    }
                    if (autofillApi26Helper.isList(autofillValue)) {
                        throw new Error("An operation is not implemented: b/138604541: Add onFill() callback for list");
                    }
                    if (autofillApi26Helper.isToggle(autofillValue)) {
                        throw new Error("An operation is not implemented: b/138604541:  Add onFill() callback for toggle");
                    }
                }
            }
        }
    }

    @Override // android.view.View
    public final boolean canScrollHorizontally(int i) {
        this.accessibilityDelegate.m224canScroll0AR0LA0$ui_release(false, this.lastDownPointerPosition);
        return false;
    }

    @Override // android.view.View
    public final boolean canScrollVertically(int i) {
        this.accessibilityDelegate.m224canScroll0AR0LA0$ui_release(true, this.lastDownPointerPosition);
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchDraw(Canvas canvas) {
        ResultKt.checkNotNullParameter(canvas, "canvas");
        if (!isAttachedToWindow()) {
            invalidateLayers(getRoot());
        }
        measureAndLayout(true);
        this.isDrawingContent = true;
        Stack stack = this.canvasHolder;
        AndroidCanvas androidCanvas = (AndroidCanvas) stack.backing;
        Canvas canvas2 = androidCanvas.internalCanvas;
        androidCanvas.getClass();
        androidCanvas.internalCanvas = canvas;
        AndroidCanvas androidCanvas2 = (AndroidCanvas) stack.backing;
        LayoutNode root = getRoot();
        root.getClass();
        ResultKt.checkNotNullParameter(androidCanvas2, "canvas");
        root.nodes.outerCoordinator.draw(androidCanvas2);
        ((AndroidCanvas) stack.backing).setInternalCanvas(canvas2);
        ArrayList arrayList = this.dirtyLayers;
        if (true ^ arrayList.isEmpty()) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((OwnedLayer) arrayList.get(i)).updateDisplayList();
            }
        }
        if (ViewLayer.shouldUseDispatchDraw) {
            int save = canvas.save();
            canvas.clipRect(0.0f, 0.0f, 0.0f, 0.0f);
            super.dispatchDraw(canvas);
            canvas.restoreToCount(save);
        }
        arrayList.clear();
        this.isDrawingContent = false;
        ArrayList arrayList2 = this.postponedDirtyLayers;
        if (arrayList2 != null) {
            arrayList.addAll(arrayList2);
            arrayList2.clear();
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:148:0x01dd */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:152:0x01e6 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:157:0x0197 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:158:0x0197 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:161:0x01ec */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:188:0x023b */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:192:0x0244 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:197:0x01f4 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:198:0x01f4 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:201:0x024a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:35:0x00a9 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:39:0x00b2 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:44:0x0077 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:45:0x0077 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:48:0x00b8 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:49:0x00d0 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:84:0x014b */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:89:0x0106 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:90:0x0106 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:93:0x0151 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v15, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r0v26, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v33 */
    /* JADX WARN: Type inference failed for: r0v34, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v35, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v36 */
    /* JADX WARN: Type inference failed for: r0v37 */
    /* JADX WARN: Type inference failed for: r0v38 */
    /* JADX WARN: Type inference failed for: r0v39 */
    /* JADX WARN: Type inference failed for: r0v58 */
    /* JADX WARN: Type inference failed for: r0v59 */
    /* JADX WARN: Type inference failed for: r0v60 */
    /* JADX WARN: Type inference failed for: r0v61 */
    /* JADX WARN: Type inference failed for: r0v62 */
    /* JADX WARN: Type inference failed for: r0v63 */
    /* JADX WARN: Type inference failed for: r14v10, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r14v11, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r14v19 */
    /* JADX WARN: Type inference failed for: r14v20, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r14v21, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v22 */
    /* JADX WARN: Type inference failed for: r14v23 */
    /* JADX WARN: Type inference failed for: r14v24 */
    /* JADX WARN: Type inference failed for: r14v25 */
    /* JADX WARN: Type inference failed for: r14v30 */
    /* JADX WARN: Type inference failed for: r14v31 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v19, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r6v20, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v32 */
    /* JADX WARN: Type inference failed for: r6v33 */
    /* JADX WARN: Type inference failed for: r6v34 */
    /* JADX WARN: Type inference failed for: r6v35 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v22 */
    /* JADX WARN: Type inference failed for: r7v23, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v24 */
    /* JADX WARN: Type inference failed for: r7v25, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v26, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v27 */
    /* JADX WARN: Type inference failed for: r7v28 */
    /* JADX WARN: Type inference failed for: r7v29 */
    /* JADX WARN: Type inference failed for: r7v30 */
    /* JADX WARN: Type inference failed for: r7v31 */
    /* JADX WARN: Type inference failed for: r7v32 */
    /* JADX WARN: Type inference failed for: r8v29 */
    /* JADX WARN: Type inference failed for: r8v30 */
    /* JADX WARN: Type inference failed for: r8v31 */
    /* JADX WARN: Type inference failed for: r8v32, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v33 */
    /* JADX WARN: Type inference failed for: r8v34 */
    /* JADX WARN: Type inference failed for: r8v35, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v36, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v37 */
    /* JADX WARN: Type inference failed for: r8v38 */
    /* JADX WARN: Type inference failed for: r8v39 */
    /* JADX WARN: Type inference failed for: r8v40 */
    /* JADX WARN: Type inference failed for: r9v14, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    @Override // android.view.View
    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        RotaryInputModifierNode rotaryInputModifierNode;
        int size;
        NodeChain nodeChain;
        DelegatingNode delegatingNode;
        NodeChain nodeChain2;
        ResultKt.checkNotNullParameter(motionEvent, "event");
        if (motionEvent.getActionMasked() != 8) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        if (motionEvent.isFromSource(4194304)) {
            android.view.ViewConfiguration viewConfiguration = android.view.ViewConfiguration.get(getContext());
            float f = -motionEvent.getAxisValue(26);
            getContext();
            float scaledVerticalScrollFactor = ViewConfigurationCompat$Api26Impl.getScaledVerticalScrollFactor(viewConfiguration) * f;
            getContext();
            RotaryScrollEvent rotaryScrollEvent = new RotaryScrollEvent(scaledVerticalScrollFactor, ViewConfigurationCompat$Api26Impl.getScaledHorizontalScrollFactor(viewConfiguration) * f, motionEvent.getEventTime());
            FocusOwnerImpl focusOwnerImpl = (FocusOwnerImpl) getFocusOwner();
            focusOwnerImpl.getClass();
            FocusTargetNode findActiveFocusNode = _BOUNDARY.findActiveFocusNode(focusOwnerImpl.rootFocusNode);
            if (findActiveFocusNode != null) {
                Modifier.Node node = findActiveFocusNode.node;
                if (!node.isAttached) {
                    throw new IllegalStateException("visitAncestors called on an unattached node".toString());
                }
                Modifier.Node node2 = node.parent;
                LayoutNode requireLayoutNode = Snake.requireLayoutNode(findActiveFocusNode);
                loop0: while (true) {
                    if (requireLayoutNode == null) {
                        delegatingNode = 0;
                        break;
                    }
                    if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 16384) != 0) {
                        while (node2 != null) {
                            if ((node2.kindSet & 16384) != 0) {
                                ?? r8 = 0;
                                delegatingNode = node2;
                                while (delegatingNode != 0) {
                                    if (delegatingNode instanceof RotaryInputModifierNode) {
                                        break loop0;
                                    }
                                    if ((delegatingNode.kindSet & 16384) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                        Modifier.Node node3 = delegatingNode.delegate;
                                        int i = 0;
                                        delegatingNode = delegatingNode;
                                        r8 = r8;
                                        while (node3 != null) {
                                            if ((node3.kindSet & 16384) != 0) {
                                                i++;
                                                r8 = r8;
                                                if (i == 1) {
                                                    delegatingNode = node3;
                                                } else {
                                                    if (r8 == 0) {
                                                        ?? obj = new Object();
                                                        obj.content = new Modifier.Node[16];
                                                        obj.size = 0;
                                                        r8 = obj;
                                                    }
                                                    if (delegatingNode != 0) {
                                                        r8.add(delegatingNode);
                                                        delegatingNode = 0;
                                                    }
                                                    r8.add(node3);
                                                }
                                            }
                                            node3 = node3.child;
                                            delegatingNode = delegatingNode;
                                            r8 = r8;
                                        }
                                        if (i == 1) {
                                        }
                                    }
                                    delegatingNode = Snake.access$pop(r8);
                                }
                            }
                            node2 = node2.parent;
                        }
                    }
                    requireLayoutNode = requireLayoutNode.getParent$ui_release();
                    node2 = (requireLayoutNode == null || (nodeChain2 = requireLayoutNode.nodes) == null) ? null : nodeChain2.tail;
                }
                rotaryInputModifierNode = (RotaryInputModifierNode) delegatingNode;
            } else {
                rotaryInputModifierNode = null;
            }
            if (rotaryInputModifierNode == null) {
                return false;
            }
            Modifier.Node node4 = (Modifier.Node) rotaryInputModifierNode;
            Modifier.Node node5 = node4.node;
            if (!node5.isAttached) {
                throw new IllegalStateException("visitAncestors called on an unattached node".toString());
            }
            Modifier.Node node6 = node5.parent;
            LayoutNode requireLayoutNode2 = Snake.requireLayoutNode(rotaryInputModifierNode);
            ArrayList arrayList = null;
            while (requireLayoutNode2 != null) {
                if ((requireLayoutNode2.nodes.head.aggregateChildKindSet & 16384) != 0) {
                    while (node6 != null) {
                        if ((node6.kindSet & 16384) != 0) {
                            Modifier.Node node7 = node6;
                            MutableVector mutableVector = null;
                            while (node7 != null) {
                                if (node7 instanceof RotaryInputModifierNode) {
                                    if (arrayList == null) {
                                        arrayList = new ArrayList();
                                    }
                                    arrayList.add(node7);
                                } else if ((node7.kindSet & 16384) != 0 && (node7 instanceof DelegatingNode)) {
                                    Modifier.Node node8 = ((DelegatingNode) node7).delegate;
                                    int i2 = 0;
                                    mutableVector = mutableVector;
                                    while (node8 != null) {
                                        if ((node8.kindSet & 16384) != 0) {
                                            i2++;
                                            mutableVector = mutableVector;
                                            if (i2 == 1) {
                                                node7 = node8;
                                            } else {
                                                if (mutableVector == null) {
                                                    ?? obj2 = new Object();
                                                    obj2.content = new Modifier.Node[16];
                                                    obj2.size = 0;
                                                    mutableVector = obj2;
                                                }
                                                if (node7 != null) {
                                                    mutableVector.add(node7);
                                                    node7 = null;
                                                }
                                                mutableVector.add(node8);
                                            }
                                        }
                                        node8 = node8.child;
                                        mutableVector = mutableVector;
                                    }
                                    if (i2 == 1) {
                                    }
                                }
                                node7 = Snake.access$pop(mutableVector);
                            }
                        }
                        node6 = node6.parent;
                    }
                }
                requireLayoutNode2 = requireLayoutNode2.getParent$ui_release();
                node6 = (requireLayoutNode2 == null || (nodeChain = requireLayoutNode2.nodes) == null) ? null : nodeChain.tail;
            }
            if (arrayList != null && arrayList.size() - 1 >= 0) {
                while (true) {
                    int i3 = size - 1;
                    Function1 function1 = ((RotaryInputNode) ((RotaryInputModifierNode) arrayList.get(size))).onPreEvent;
                    if (function1 != null && ((Boolean) function1.invoke(rotaryScrollEvent)).booleanValue()) {
                        break;
                    }
                    if (i3 < 0) {
                        break;
                    }
                    size = i3;
                }
            }
            DelegatingNode delegatingNode2 = node4.node;
            ?? r6 = 0;
            while (true) {
                if (delegatingNode2 != 0) {
                    if (delegatingNode2 instanceof RotaryInputModifierNode) {
                        Function1 function12 = ((RotaryInputNode) ((RotaryInputModifierNode) delegatingNode2)).onPreEvent;
                        if (function12 != null && ((Boolean) function12.invoke(rotaryScrollEvent)).booleanValue()) {
                            break;
                        }
                    } else if ((delegatingNode2.kindSet & 16384) != 0 && (delegatingNode2 instanceof DelegatingNode)) {
                        Modifier.Node node9 = delegatingNode2.delegate;
                        int i4 = 0;
                        delegatingNode2 = delegatingNode2;
                        r6 = r6;
                        while (node9 != null) {
                            if ((node9.kindSet & 16384) != 0) {
                                i4++;
                                r6 = r6;
                                if (i4 == 1) {
                                    delegatingNode2 = node9;
                                } else {
                                    if (r6 == 0) {
                                        ?? obj3 = new Object();
                                        obj3.content = new Modifier.Node[16];
                                        obj3.size = 0;
                                        r6 = obj3;
                                    }
                                    if (delegatingNode2 != 0) {
                                        r6.add(delegatingNode2);
                                        delegatingNode2 = 0;
                                    }
                                    r6.add(node9);
                                }
                            }
                            node9 = node9.child;
                            delegatingNode2 = delegatingNode2;
                            r6 = r6;
                        }
                        if (i4 == 1) {
                        }
                    }
                    delegatingNode2 = Snake.access$pop(r6);
                } else {
                    DelegatingNode delegatingNode3 = node4.node;
                    ?? r0 = 0;
                    while (true) {
                        if (delegatingNode3 == 0) {
                            if (arrayList == null) {
                                return false;
                            }
                            int size2 = arrayList.size();
                            for (int i5 = 0; i5 < size2; i5++) {
                                Function1 function13 = ((RotaryInputNode) ((RotaryInputModifierNode) arrayList.get(i5))).onEvent;
                                if (function13 == null || !((Boolean) function13.invoke(rotaryScrollEvent)).booleanValue()) {
                                }
                            }
                            return false;
                        }
                        if (delegatingNode3 instanceof RotaryInputModifierNode) {
                            Function1 function14 = ((RotaryInputNode) ((RotaryInputModifierNode) delegatingNode3)).onEvent;
                            if (function14 != null && ((Boolean) function14.invoke(rotaryScrollEvent)).booleanValue()) {
                                break;
                            }
                        } else if ((delegatingNode3.kindSet & 16384) != 0 && (delegatingNode3 instanceof DelegatingNode)) {
                            Modifier.Node node10 = delegatingNode3.delegate;
                            int i6 = 0;
                            r0 = r0;
                            delegatingNode3 = delegatingNode3;
                            while (node10 != null) {
                                if ((node10.kindSet & 16384) != 0) {
                                    i6++;
                                    r0 = r0;
                                    if (i6 == 1) {
                                        delegatingNode3 = node10;
                                    } else {
                                        if (r0 == 0) {
                                            ?? obj4 = new Object();
                                            obj4.content = new Modifier.Node[16];
                                            obj4.size = 0;
                                            r0 = obj4;
                                        }
                                        if (delegatingNode3 != 0) {
                                            r0.add(delegatingNode3);
                                            delegatingNode3 = 0;
                                        }
                                        r0.add(node10);
                                    }
                                }
                                node10 = node10.child;
                                r0 = r0;
                                delegatingNode3 = delegatingNode3;
                            }
                            if (i6 == 1) {
                            }
                        }
                        delegatingNode3 = Snake.access$pop(r0);
                    }
                }
            }
        } else {
            if (isBadMotionEvent(motionEvent) || !isAttachedToWindow()) {
                return super.dispatchGenericMotionEvent(motionEvent);
            }
            if ((m217handleMotionEvent8iAsVTc(motionEvent) & 1) == 0) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x0102  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean dispatchHoverEvent(android.view.MotionEvent r25) {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            java.lang.String r2 = "event"
            kotlin.ResultKt.checkNotNullParameter(r1, r2)
            boolean r2 = r0.hoverExitReceived
            androidx.activity.FullyDrawnReporter$$ExternalSyntheticLambda0 r3 = r0.sendHoverExitEvent
            if (r2 == 0) goto L15
            r0.removeCallbacks(r3)
            r3.run()
        L15:
            boolean r2 = isBadMotionEvent(r25)
            r4 = 0
            if (r2 != 0) goto L14c
            boolean r2 = r24.isAttachedToWindow()
            if (r2 != 0) goto L24
            goto L14c
        L24:
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r2 = r0.accessibilityDelegate
            r2.getClass()
            android.view.accessibility.AccessibilityManager r5 = r2.accessibilityManager
            boolean r6 = r5.isEnabled()
            r7 = 1
            if (r6 == 0) goto L3a
            boolean r5 = r5.isTouchExplorationEnabled()
            if (r5 == 0) goto L3a
            r5 = r7
            goto L3b
        L3a:
            r5 = r4
        L3b:
            r6 = 10
            r8 = 7
            if (r5 != 0) goto L42
            goto L10c
        L42:
            int r5 = r25.getAction()
            r9 = 256(0x100, float:3.59E-43)
            r10 = 12
            r11 = 128(0x80, float:1.794E-43)
            r12 = 0
            androidx.compose.ui.platform.AndroidComposeView r13 = r2.view
            r14 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r5 == r8) goto L76
            r15 = 9
            if (r5 == r15) goto L76
            if (r5 == r6) goto L5b
            goto L10c
        L5b:
            int r5 = r2.hoveredVirtualViewId
            if (r5 == r14) goto L6d
            if (r5 != r14) goto L63
            goto L10c
        L63:
            r2.hoveredVirtualViewId = r14
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(r2, r14, r11, r12, r10)
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(r2, r5, r9, r12, r10)
            goto L10c
        L6d:
            androidx.compose.ui.platform.AndroidViewsHandler r2 = r13.getAndroidViewsHandler$ui_release()
            r2.dispatchGenericMotionEvent(r1)
            goto L10c
        L76:
            float r5 = r25.getX()
            float r15 = r25.getY()
            r13.measureAndLayout(r7)
            androidx.compose.ui.node.HitTestResult r23 = new androidx.compose.ui.node.HitTestResult
            r23.<init>()
            androidx.compose.ui.node.LayoutNode r14 = r13.getRoot()
            long r8 = _COROUTINE._BOUNDARY.Offset(r5, r15)
            r14.getClass()
            androidx.compose.ui.node.NodeChain r5 = r14.nodes
            androidx.compose.ui.node.NodeCoordinator r14 = r5.outerCoordinator
            long r18 = r14.m194fromParentPositionMKHz9U(r8)
            androidx.compose.ui.node.NodeCoordinator r5 = r5.outerCoordinator
            kotlin.ULong$Companion r17 = androidx.compose.ui.node.NodeCoordinator.SemanticsSource
            r21 = 1
            r22 = 1
            r16 = r5
            r20 = r23
            r16.m199hitTestYqVAtuI(r17, r18, r20, r21, r22)
            java.lang.Object r5 = kotlin.collections.CollectionsKt___CollectionsKt.lastOrNull(r23)
            androidx.compose.ui.Modifier$Node r5 = (androidx.compose.ui.Modifier.Node) r5
            if (r5 == 0) goto Lb5
            androidx.compose.ui.node.LayoutNode r5 = androidx.compose.ui.node.Snake.requireLayoutNode(r5)
            goto Lb6
        Lb5:
            r5 = r12
        Lb6:
            if (r5 == 0) goto Lf4
            androidx.compose.ui.node.NodeChain r8 = r5.nodes
            if (r8 == 0) goto Lf4
            r9 = 8
            boolean r8 = r8.m190hasH91voCI$ui_release(r9)
            if (r8 != r7) goto Lf4
            androidx.compose.ui.semantics.SemanticsNode r8 = _COROUTINE._BOUNDARY.SemanticsNode(r5, r4)
            androidx.compose.ui.node.NodeCoordinator r9 = r8.findCoordinatorToGetBounds$ui_release()
            if (r9 == 0) goto Ld4
            boolean r9 = r9.isTransparent()
            if (r9 != 0) goto Lf4
        Ld4:
            androidx.compose.ui.semantics.SemanticsPropertyKey r9 = androidx.compose.ui.semantics.SemanticsProperties.InvisibleToUser
            androidx.compose.ui.semantics.SemanticsConfiguration r8 = r8.unmergedConfig
            boolean r8 = r8.contains(r9)
            if (r8 != 0) goto Lf4
            androidx.compose.ui.platform.AndroidViewsHandler r8 = r13.getAndroidViewsHandler$ui_release()
            java.util.HashMap r8 = r8.getLayoutNodeToHolder()
            java.lang.Object r8 = r8.get(r5)
            kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0.m(r8)
            int r5 = r5.semanticsId
            int r14 = r2.semanticsNodeIdToAccessibilityVirtualNodeId(r5)
            goto Lf6
        Lf4:
            r14 = -2147483648(0xffffffff80000000, float:-0.0)
        Lf6:
            androidx.compose.ui.platform.AndroidViewsHandler r5 = r13.getAndroidViewsHandler$ui_release()
            r5.dispatchGenericMotionEvent(r1)
            int r5 = r2.hoveredVirtualViewId
            if (r5 != r14) goto L102
            goto L10c
        L102:
            r2.hoveredVirtualViewId = r14
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(r2, r14, r11, r12, r10)
            r8 = 256(0x100, float:3.59E-43)
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(r2, r5, r8, r12, r10)
        L10c:
            int r2 = r25.getActionMasked()
            r5 = 7
            if (r2 == r5) goto L13d
            if (r2 == r6) goto L116
            goto L144
        L116:
            boolean r2 = r24.isInBounds(r25)
            if (r2 == 0) goto L144
            int r2 = r1.getToolType(r4)
            r5 = 3
            if (r2 == r5) goto L136
            android.view.MotionEvent r2 = r0.previousMotionEvent
            if (r2 == 0) goto L12a
            r2.recycle()
        L12a:
            android.view.MotionEvent r1 = android.view.MotionEvent.obtainNoHistory(r25)
            r0.previousMotionEvent = r1
            r0.hoverExitReceived = r7
            r0.post(r3)
            return r4
        L136:
            int r2 = r25.getButtonState()
            if (r2 == 0) goto L144
            return r4
        L13d:
            boolean r2 = r24.isPositionChanged(r25)
            if (r2 != 0) goto L144
            return r4
        L144:
            int r1 = r24.m217handleMotionEvent8iAsVTc(r25)
            r1 = r1 & r7
            if (r1 == 0) goto L14c
            r4 = r7
        L14c:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView.dispatchHoverEvent(android.view.MotionEvent):boolean");
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:122:0x0153 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:127:0x010e */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:128:0x010e */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:131:0x0159 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:183:0x01cd */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:187:0x01d6 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:192:0x0192 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:193:0x0192 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:196:0x01dc */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:221:0x021f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:225:0x0228 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:230:0x01e4 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:231:0x01e4 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:234:0x022e */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:49:0x00ad */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:53:0x00b6 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:58:0x007b */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:59:0x007b */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:62:0x00bc */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:63:0x00d4 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v16, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v17, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v18, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v19, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v24, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v25, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v26 */
    /* JADX WARN: Type inference failed for: r0v27 */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r0v32 */
    /* JADX WARN: Type inference failed for: r0v33, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v34, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v35 */
    /* JADX WARN: Type inference failed for: r0v36 */
    /* JADX WARN: Type inference failed for: r0v37 */
    /* JADX WARN: Type inference failed for: r0v38 */
    /* JADX WARN: Type inference failed for: r0v56 */
    /* JADX WARN: Type inference failed for: r0v57 */
    /* JADX WARN: Type inference failed for: r0v58 */
    /* JADX WARN: Type inference failed for: r0v59 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r2v16, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v33 */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v35 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r5v19, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v23 */
    /* JADX WARN: Type inference failed for: r5v24 */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v26 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v22 */
    /* JADX WARN: Type inference failed for: r7v23, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v24 */
    /* JADX WARN: Type inference failed for: r7v25, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v26, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v27 */
    /* JADX WARN: Type inference failed for: r7v28 */
    /* JADX WARN: Type inference failed for: r7v29 */
    /* JADX WARN: Type inference failed for: r7v30 */
    /* JADX WARN: Type inference failed for: r7v31 */
    /* JADX WARN: Type inference failed for: r7v32 */
    /* JADX WARN: Type inference failed for: r8v28 */
    /* JADX WARN: Type inference failed for: r8v29 */
    /* JADX WARN: Type inference failed for: r8v30 */
    /* JADX WARN: Type inference failed for: r8v31, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v32 */
    /* JADX WARN: Type inference failed for: r8v33 */
    /* JADX WARN: Type inference failed for: r8v34, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v35, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v36 */
    /* JADX WARN: Type inference failed for: r8v37 */
    /* JADX WARN: Type inference failed for: r8v38 */
    /* JADX WARN: Type inference failed for: r8v39 */
    /* JADX WARN: Type inference failed for: r9v14, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Modifier.Node node;
        int size;
        NodeChain nodeChain;
        DelegatingNode delegatingNode;
        NodeChain nodeChain2;
        ResultKt.checkNotNullParameter(keyEvent, "event");
        if (!isFocused()) {
            return super.dispatchKeyEvent(keyEvent);
        }
        int metaState = keyEvent.getMetaState();
        this._windowInfo.getClass();
        WindowInfoImpl.GlobalKeyboardModifiers.setValue(new PointerKeyboardModifiers(metaState));
        FocusOwnerImpl focusOwnerImpl = (FocusOwnerImpl) getFocusOwner();
        focusOwnerImpl.getClass();
        FocusTargetNode findActiveFocusNode = _BOUNDARY.findActiveFocusNode(focusOwnerImpl.rootFocusNode);
        if (findActiveFocusNode == null) {
            throw new IllegalStateException("Event can't be processed because we do not have an active focus target.".toString());
        }
        Modifier.Node node2 = findActiveFocusNode.node;
        if (!node2.isAttached) {
            throw new IllegalStateException("visitLocalDescendants called on an unattached node".toString());
        }
        if ((node2.aggregateChildKindSet & 9216) != 0) {
            node = null;
            for (Modifier.Node node3 = node2.child; node3 != null; node3 = node3.child) {
                int i = node3.kindSet;
                if ((i & 9216) != 0) {
                    if ((i & 1024) != 0) {
                        break;
                    }
                    node = node3;
                }
            }
        } else {
            node = null;
        }
        if (node == null) {
            Modifier.Node node4 = findActiveFocusNode.node;
            if (!node4.isAttached) {
                throw new IllegalStateException("visitAncestors called on an unattached node".toString());
            }
            Modifier.Node node5 = node4.parent;
            LayoutNode requireLayoutNode = Snake.requireLayoutNode(findActiveFocusNode);
            loop1: while (true) {
                if (requireLayoutNode == null) {
                    delegatingNode = 0;
                    break;
                }
                if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 8192) != 0) {
                    while (node5 != null) {
                        if ((node5.kindSet & 8192) != 0) {
                            delegatingNode = node5;
                            ?? r8 = 0;
                            while (delegatingNode != 0) {
                                if (delegatingNode instanceof KeyInputModifierNode) {
                                    break loop1;
                                }
                                if ((delegatingNode.kindSet & 8192) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                    Modifier.Node node6 = delegatingNode.delegate;
                                    int i2 = 0;
                                    delegatingNode = delegatingNode;
                                    r8 = r8;
                                    while (node6 != null) {
                                        if ((node6.kindSet & 8192) != 0) {
                                            i2++;
                                            r8 = r8;
                                            if (i2 == 1) {
                                                delegatingNode = node6;
                                            } else {
                                                if (r8 == 0) {
                                                    ?? obj = new Object();
                                                    obj.content = new Modifier.Node[16];
                                                    obj.size = 0;
                                                    r8 = obj;
                                                }
                                                if (delegatingNode != 0) {
                                                    r8.add(delegatingNode);
                                                    delegatingNode = 0;
                                                }
                                                r8.add(node6);
                                            }
                                        }
                                        node6 = node6.child;
                                        delegatingNode = delegatingNode;
                                        r8 = r8;
                                    }
                                    if (i2 == 1) {
                                    }
                                }
                                delegatingNode = Snake.access$pop(r8);
                            }
                        }
                        node5 = node5.parent;
                    }
                }
                requireLayoutNode = requireLayoutNode.getParent$ui_release();
                node5 = (requireLayoutNode == null || (nodeChain2 = requireLayoutNode.nodes) == null) ? null : nodeChain2.tail;
            }
            Object obj2 = (KeyInputModifierNode) delegatingNode;
            node = obj2 != null ? ((Modifier.Node) obj2).node : null;
        }
        if (node != null) {
            Modifier.Node node7 = node.node;
            if (!node7.isAttached) {
                throw new IllegalStateException("visitAncestors called on an unattached node".toString());
            }
            Modifier.Node node8 = node7.parent;
            LayoutNode requireLayoutNode2 = Snake.requireLayoutNode(node);
            ArrayList arrayList = null;
            while (requireLayoutNode2 != null) {
                if ((requireLayoutNode2.nodes.head.aggregateChildKindSet & 8192) != 0) {
                    while (node8 != null) {
                        if ((node8.kindSet & 8192) != 0) {
                            Modifier.Node node9 = node8;
                            MutableVector mutableVector = null;
                            while (node9 != null) {
                                if (node9 instanceof KeyInputModifierNode) {
                                    if (arrayList == null) {
                                        arrayList = new ArrayList();
                                    }
                                    arrayList.add(node9);
                                } else if ((node9.kindSet & 8192) != 0 && (node9 instanceof DelegatingNode)) {
                                    Modifier.Node node10 = ((DelegatingNode) node9).delegate;
                                    int i3 = 0;
                                    mutableVector = mutableVector;
                                    while (node10 != null) {
                                        if ((node10.kindSet & 8192) != 0) {
                                            i3++;
                                            mutableVector = mutableVector;
                                            if (i3 == 1) {
                                                node9 = node10;
                                            } else {
                                                if (mutableVector == null) {
                                                    ?? obj3 = new Object();
                                                    obj3.content = new Modifier.Node[16];
                                                    obj3.size = 0;
                                                    mutableVector = obj3;
                                                }
                                                if (node9 != null) {
                                                    mutableVector.add(node9);
                                                    node9 = null;
                                                }
                                                mutableVector.add(node10);
                                            }
                                        }
                                        node10 = node10.child;
                                        mutableVector = mutableVector;
                                    }
                                    if (i3 == 1) {
                                    }
                                }
                                node9 = Snake.access$pop(mutableVector);
                            }
                        }
                        node8 = node8.parent;
                    }
                }
                requireLayoutNode2 = requireLayoutNode2.getParent$ui_release();
                node8 = (requireLayoutNode2 == null || (nodeChain = requireLayoutNode2.nodes) == null) ? null : nodeChain.tail;
            }
            if (arrayList != null && arrayList.size() - 1 >= 0) {
                while (true) {
                    int i4 = size - 1;
                    if (((KeyInputModifierNode) arrayList.get(size)).mo30onPreKeyEventZmokQxo(keyEvent)) {
                        break;
                    }
                    if (i4 < 0) {
                        break;
                    }
                    size = i4;
                }
            }
            DelegatingNode delegatingNode2 = node.node;
            ?? r5 = 0;
            while (true) {
                if (delegatingNode2 != 0) {
                    if (delegatingNode2 instanceof KeyInputModifierNode) {
                        if (((KeyInputModifierNode) delegatingNode2).mo30onPreKeyEventZmokQxo(keyEvent)) {
                            break;
                        }
                    } else if ((delegatingNode2.kindSet & 8192) != 0 && (delegatingNode2 instanceof DelegatingNode)) {
                        Modifier.Node node11 = delegatingNode2.delegate;
                        int i5 = 0;
                        delegatingNode2 = delegatingNode2;
                        r5 = r5;
                        while (node11 != null) {
                            if ((node11.kindSet & 8192) != 0) {
                                i5++;
                                r5 = r5;
                                if (i5 == 1) {
                                    delegatingNode2 = node11;
                                } else {
                                    if (r5 == 0) {
                                        ?? obj4 = new Object();
                                        obj4.content = new Modifier.Node[16];
                                        obj4.size = 0;
                                        r5 = obj4;
                                    }
                                    if (delegatingNode2 != 0) {
                                        r5.add(delegatingNode2);
                                        delegatingNode2 = 0;
                                    }
                                    r5.add(node11);
                                }
                            }
                            node11 = node11.child;
                            delegatingNode2 = delegatingNode2;
                            r5 = r5;
                        }
                        if (i5 == 1) {
                        }
                    }
                    delegatingNode2 = Snake.access$pop(r5);
                } else {
                    DelegatingNode delegatingNode3 = node.node;
                    ?? r2 = 0;
                    while (true) {
                        if (delegatingNode3 != 0) {
                            if (delegatingNode3 instanceof KeyInputModifierNode) {
                                if (((KeyInputModifierNode) delegatingNode3).mo29onKeyEventZmokQxo(keyEvent)) {
                                    break;
                                }
                            } else if ((delegatingNode3.kindSet & 8192) != 0 && (delegatingNode3 instanceof DelegatingNode)) {
                                Modifier.Node node12 = delegatingNode3.delegate;
                                int i6 = 0;
                                delegatingNode3 = delegatingNode3;
                                r2 = r2;
                                while (node12 != null) {
                                    if ((node12.kindSet & 8192) != 0) {
                                        i6++;
                                        r2 = r2;
                                        if (i6 == 1) {
                                            delegatingNode3 = node12;
                                        } else {
                                            if (r2 == 0) {
                                                ?? obj5 = new Object();
                                                obj5.content = new Modifier.Node[16];
                                                obj5.size = 0;
                                                r2 = obj5;
                                            }
                                            if (delegatingNode3 != 0) {
                                                r2.add(delegatingNode3);
                                                delegatingNode3 = 0;
                                            }
                                            r2.add(node12);
                                        }
                                    }
                                    node12 = node12.child;
                                    delegatingNode3 = delegatingNode3;
                                    r2 = r2;
                                }
                                if (i6 == 1) {
                                }
                            }
                            delegatingNode3 = Snake.access$pop(r2);
                        } else if (arrayList != null) {
                            int size2 = arrayList.size();
                            for (int i7 = 0; i7 < size2; i7++) {
                                if (((KeyInputModifierNode) arrayList.get(i7)).mo29onKeyEventZmokQxo(keyEvent)) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }
        if (!super.dispatchKeyEvent(keyEvent)) {
            return false;
        }
        return true;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:36:0x0077 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:41:0x003f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:42:0x003f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:45:0x007d */
    /* JADX WARN: Type inference failed for: r5v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEventPreIme(KeyEvent keyEvent) {
        NodeChain nodeChain;
        ResultKt.checkNotNullParameter(keyEvent, "event");
        if (isFocused()) {
            FocusOwnerImpl focusOwnerImpl = (FocusOwnerImpl) getFocusOwner();
            focusOwnerImpl.getClass();
            FocusTargetNode findActiveFocusNode = _BOUNDARY.findActiveFocusNode(focusOwnerImpl.rootFocusNode);
            if (findActiveFocusNode != null) {
                Modifier.Node node = findActiveFocusNode.node;
                if (!node.isAttached) {
                    throw new IllegalStateException("visitAncestors called on an unattached node".toString());
                }
                Modifier.Node node2 = node.parent;
                LayoutNode requireLayoutNode = Snake.requireLayoutNode(findActiveFocusNode);
                while (requireLayoutNode != null) {
                    if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 131072) != 0) {
                        while (node2 != null) {
                            if ((node2.kindSet & 131072) != 0) {
                                Modifier.Node node3 = node2;
                                MutableVector mutableVector = null;
                                while (node3 != null) {
                                    if ((node3.kindSet & 131072) != 0 && (node3 instanceof DelegatingNode)) {
                                        Modifier.Node node4 = ((DelegatingNode) node3).delegate;
                                        int i = 0;
                                        mutableVector = mutableVector;
                                        while (node4 != null) {
                                            if ((node4.kindSet & 131072) != 0) {
                                                i++;
                                                mutableVector = mutableVector;
                                                if (i == 1) {
                                                    node3 = node4;
                                                } else {
                                                    if (mutableVector == null) {
                                                        ?? obj = new Object();
                                                        obj.content = new Modifier.Node[16];
                                                        obj.size = 0;
                                                        mutableVector = obj;
                                                    }
                                                    if (node3 != null) {
                                                        mutableVector.add(node3);
                                                        node3 = null;
                                                    }
                                                    mutableVector.add(node4);
                                                }
                                            }
                                            node4 = node4.child;
                                            mutableVector = mutableVector;
                                        }
                                        if (i == 1) {
                                        }
                                    }
                                    node3 = Snake.access$pop(mutableVector);
                                }
                            }
                            node2 = node2.parent;
                        }
                    }
                    requireLayoutNode = requireLayoutNode.getParent$ui_release();
                    node2 = (requireLayoutNode == null || (nodeChain = requireLayoutNode.nodes) == null) ? null : nodeChain.tail;
                }
            }
        }
        return super.dispatchKeyEventPreIme(keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        ResultKt.checkNotNullParameter(motionEvent, "motionEvent");
        if (this.hoverExitReceived) {
            FullyDrawnReporter$$ExternalSyntheticLambda0 fullyDrawnReporter$$ExternalSyntheticLambda0 = this.sendHoverExitEvent;
            removeCallbacks(fullyDrawnReporter$$ExternalSyntheticLambda0);
            MotionEvent motionEvent2 = this.previousMotionEvent;
            ResultKt.checkNotNull(motionEvent2);
            if (motionEvent.getActionMasked() == 0 && motionEvent2.getSource() == motionEvent.getSource() && motionEvent2.getToolType(0) == motionEvent.getToolType(0)) {
                this.hoverExitReceived = false;
            } else {
                fullyDrawnReporter$$ExternalSyntheticLambda0.run();
            }
        }
        if (isBadMotionEvent(motionEvent) || !isAttachedToWindow()) {
            return false;
        }
        if (motionEvent.getActionMasked() == 2 && !isPositionChanged(motionEvent)) {
            return false;
        }
        int m217handleMotionEvent8iAsVTc = m217handleMotionEvent8iAsVTc(motionEvent);
        if ((m217handleMotionEvent8iAsVTc & 2) != 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return (m217handleMotionEvent8iAsVTc & 1) != 0;
    }

    public final View findViewByAccessibilityIdTraversal(int i) {
        View view = null;
        try {
            if (Build.VERSION.SDK_INT >= 29) {
                Method declaredMethod = View.class.getDeclaredMethod("findViewByAccessibilityIdTraversal", Integer.TYPE);
                declaredMethod.setAccessible(true);
                Object invoke = declaredMethod.invoke(this, Integer.valueOf(i));
                if (invoke instanceof View) {
                    view = (View) invoke;
                }
            } else {
                view = findViewByAccessibilityIdRootedAtCurrentView(this, i);
            }
        } catch (NoSuchMethodException unused) {
        }
        return view;
    }

    public final void forceMeasureTheSubtree(LayoutNode layoutNode, boolean z) {
        ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
        this.measureAndLayoutDelegate.forceMeasureTheSubtree(layoutNode, z);
    }

    public final AndroidViewsHandler getAndroidViewsHandler$ui_release() {
        if (this._androidViewsHandler == null) {
            Context context = getContext();
            ResultKt.checkNotNullExpressionValue(context, "context");
            AndroidViewsHandler androidViewsHandler = new AndroidViewsHandler(context);
            this._androidViewsHandler = androidViewsHandler;
            addView(androidViewsHandler);
        }
        AndroidViewsHandler androidViewsHandler2 = this._androidViewsHandler;
        ResultKt.checkNotNull(androidViewsHandler2);
        return androidViewsHandler2;
    }

    public Autofill getAutofill() {
        return this._autofill;
    }

    public AutofillTree getAutofillTree() {
        return this.autofillTree;
    }

    public final Function1 getConfigurationChangeObserver() {
        return this.configurationChangeObserver;
    }

    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public Density getDensity() {
        return this.density;
    }

    public FocusOwner getFocusOwner() {
        return this.focusOwner;
    }

    @Override // android.view.View
    public final void getFocusedRect(Rect rect) {
        ResultKt.checkNotNullParameter(rect, "rect");
        FocusTargetNode findActiveFocusNode = _BOUNDARY.findActiveFocusNode(((FocusOwnerImpl) getFocusOwner()).rootFocusNode);
        Unit unit = null;
        androidx.compose.ui.geometry.Rect focusRect = findActiveFocusNode != null ? _BOUNDARY.focusRect(findActiveFocusNode) : null;
        if (focusRect != null) {
            rect.left = ResultKt.roundToInt(focusRect.left);
            rect.top = ResultKt.roundToInt(focusRect.top);
            rect.right = ResultKt.roundToInt(focusRect.right);
            rect.bottom = ResultKt.roundToInt(focusRect.bottom);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            super.getFocusedRect(rect);
        }
    }

    public FontFamily.Resolver getFontFamilyResolver() {
        return (FontFamily.Resolver) this.fontFamilyResolver$delegate.getValue();
    }

    public Font$ResourceLoader getFontLoader() {
        return this.fontLoader;
    }

    public HapticFeedback getHapticFeedBack() {
        return this.hapticFeedBack;
    }

    public boolean getHasPendingMeasureOrLayout() {
        WeakCache weakCache = this.measureAndLayoutDelegate.relayoutNodes;
        return !(((TreeSet) ((Latch) weakCache.referenceQueue).spareList).isEmpty() && ((TreeSet) ((Latch) weakCache.values).spareList).isEmpty());
    }

    public InputModeManager getInputModeManager() {
        return this._inputModeManager;
    }

    public final long getLastMatrixRecalculationAnimationTime$ui_release() {
        return this.lastMatrixRecalculationAnimationTime;
    }

    @Override // android.view.View, android.view.ViewParent
    public LayoutDirection getLayoutDirection() {
        return (LayoutDirection) this.layoutDirection$delegate.getValue();
    }

    public long getMeasureIteration() {
        MeasureAndLayoutDelegate measureAndLayoutDelegate = this.measureAndLayoutDelegate;
        if (measureAndLayoutDelegate.duringMeasureLayout) {
            return measureAndLayoutDelegate.measureIteration;
        }
        throw new IllegalArgumentException("measureIteration should be only used during the measure/layout pass".toString());
    }

    public ModifierLocalManager getModifierLocalManager() {
        return this.modifierLocalManager;
    }

    public PointerIconService getPointerIconService() {
        return this.pointerIconService;
    }

    public LayoutNode getRoot() {
        return this.root;
    }

    public RootForTest getRootForTest() {
        return this.rootForTest;
    }

    public SemanticsOwner getSemanticsOwner() {
        return this.semanticsOwner;
    }

    public LayoutNodeDrawScope getSharedDrawScope() {
        return this.sharedDrawScope;
    }

    public boolean getShowLayoutBounds() {
        return this.showLayoutBounds;
    }

    public OwnerSnapshotObserver getSnapshotObserver() {
        return this.snapshotObserver;
    }

    public TextInputService getTextInputService() {
        return this.textInputService;
    }

    public TextToolbar getTextToolbar() {
        return this.textToolbar;
    }

    public View getView() {
        return this;
    }

    public ViewConfiguration getViewConfiguration() {
        return this.viewConfiguration;
    }

    public final ViewTreeOwners getViewTreeOwners() {
        return (ViewTreeOwners) this.viewTreeOwners$delegate.getValue();
    }

    public WindowInfo getWindowInfo() {
        return this._windowInfo;
    }

    /* renamed from: handleMotionEvent-8iAsVTc, reason: not valid java name */
    public final int m217handleMotionEvent8iAsVTc(MotionEvent motionEvent) {
        int actionMasked;
        float[] fArr = this.viewToWindowMatrix;
        removeCallbacks(this.resendMotionEventRunnable);
        try {
            this.lastMatrixRecalculationAnimationTime = AnimationUtils.currentAnimationTimeMillis();
            this.matrixToWindow.mo225calculateMatrixToWindowEL8BTi8(this, fArr);
            InvertMatrixKt.m232invertToJiSxe2E(fArr, this.windowToViewMatrix);
            long m104mapMKHz9U = Brush.m104mapMKHz9U(fArr, _BOUNDARY.Offset(motionEvent.getX(), motionEvent.getY()));
            this.windowPosition = _BOUNDARY.Offset(motionEvent.getRawX() - Offset.m76getXimpl(m104mapMKHz9U), motionEvent.getRawY() - Offset.m77getYimpl(m104mapMKHz9U));
            boolean z = true;
            this.forceUseMatrixCache = true;
            measureAndLayout(false);
            Trace.beginSection("AndroidOwner:onTouch");
            try {
                int actionMasked2 = motionEvent.getActionMasked();
                MotionEvent motionEvent2 = this.previousMotionEvent;
                boolean z2 = motionEvent2 != null && motionEvent2.getToolType(0) == 3;
                if (motionEvent2 != null && (motionEvent2.getSource() != motionEvent.getSource() || motionEvent2.getToolType(0) != motionEvent.getToolType(0))) {
                    if (motionEvent2.getButtonState() == 0 && (actionMasked = motionEvent2.getActionMasked()) != 0 && actionMasked != 2 && actionMasked != 6) {
                        if (motionEvent2.getActionMasked() != 10 && z2) {
                            sendSimulatedEvent(motionEvent2, 10, motionEvent2.getEventTime(), true);
                        }
                    }
                    this.pointerInputEventProcessor.processCancel();
                }
                if (motionEvent.getToolType(0) != 3) {
                    z = false;
                }
                if (!z2 && z && actionMasked2 != 3 && actionMasked2 != 9 && isInBounds(motionEvent)) {
                    sendSimulatedEvent(motionEvent, 9, motionEvent.getEventTime(), true);
                }
                if (motionEvent2 != null) {
                    motionEvent2.recycle();
                }
                this.previousMotionEvent = MotionEvent.obtainNoHistory(motionEvent);
                int m220sendMotionEvent8iAsVTc = m220sendMotionEvent8iAsVTc(motionEvent);
                Trace.endSection();
                return m220sendMotionEvent8iAsVTc;
            } catch (Throwable th) {
                Trace.endSection();
                throw th;
            }
        } finally {
            this.forceUseMatrixCache = false;
        }
    }

    public final void invalidateLayoutNodeMeasurement(LayoutNode layoutNode) {
        int i = 0;
        this.measureAndLayoutDelegate.requestRemeasure(layoutNode, false);
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int i2 = mutableVector.size;
        if (i2 > 0) {
            Object[] objArr = mutableVector.content;
            do {
                invalidateLayoutNodeMeasurement((LayoutNode) objArr[i]);
                i++;
            } while (i < i2);
        }
    }

    public final boolean isInBounds(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        return 0.0f <= x && x <= ((float) getWidth()) && 0.0f <= y && y <= ((float) getHeight());
    }

    public final boolean isPositionChanged(MotionEvent motionEvent) {
        MotionEvent motionEvent2;
        return (motionEvent.getPointerCount() == 1 && (motionEvent2 = this.previousMotionEvent) != null && motionEvent2.getPointerCount() == motionEvent.getPointerCount() && motionEvent.getRawX() == motionEvent2.getRawX() && motionEvent.getRawY() == motionEvent2.getRawY()) ? false : true;
    }

    /* renamed from: localToScreen-MK-Hz9U, reason: not valid java name */
    public final long m218localToScreenMKHz9U(long j) {
        recalculateWindowPosition();
        long m104mapMKHz9U = Brush.m104mapMKHz9U(this.viewToWindowMatrix, j);
        return _BOUNDARY.Offset(Offset.m76getXimpl(this.windowPosition) + Offset.m76getXimpl(m104mapMKHz9U), Offset.m77getYimpl(this.windowPosition) + Offset.m77getYimpl(m104mapMKHz9U));
    }

    public final void measureAndLayout(boolean z) {
        AndroidComposeView$viewTreeOwners$2 androidComposeView$viewTreeOwners$2;
        MeasureAndLayoutDelegate measureAndLayoutDelegate = this.measureAndLayoutDelegate;
        WeakCache weakCache = measureAndLayoutDelegate.relayoutNodes;
        if ((!(((TreeSet) ((Latch) weakCache.referenceQueue).spareList).isEmpty() && ((TreeSet) ((Latch) weakCache.values).spareList).isEmpty())) || measureAndLayoutDelegate.onPositionedDispatcher.layoutNodes.isNotEmpty()) {
            Trace.beginSection("AndroidOwner:measureAndLayout");
            if (z) {
                try {
                    androidComposeView$viewTreeOwners$2 = this.resendMotionEventOnLayout;
                } catch (Throwable th) {
                    Trace.endSection();
                    throw th;
                }
            } else {
                androidComposeView$viewTreeOwners$2 = null;
            }
            if (measureAndLayoutDelegate.measureAndLayout(androidComposeView$viewTreeOwners$2)) {
                requestLayout();
            }
            measureAndLayoutDelegate.dispatchOnPositionedCallbacks(false);
            Trace.endSection();
        }
    }

    public final void notifyLayerIsDirty$ui_release(OwnedLayer ownedLayer, boolean z) {
        ResultKt.checkNotNullParameter(ownedLayer, "layer");
        ArrayList arrayList = this.dirtyLayers;
        if (!z) {
            if (this.isDrawingContent) {
                return;
            }
            arrayList.remove(ownedLayer);
            ArrayList arrayList2 = this.postponedDirtyLayers;
            if (arrayList2 != null) {
                arrayList2.remove(ownedLayer);
                return;
            }
            return;
        }
        if (!this.isDrawingContent) {
            arrayList.add(ownedLayer);
            return;
        }
        ArrayList arrayList3 = this.postponedDirtyLayers;
        if (arrayList3 == null) {
            arrayList3 = new ArrayList();
            this.postponedDirtyLayers = arrayList3;
        }
        arrayList3.add(ownedLayer);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        LifecycleOwner lifecycleOwner;
        LifecycleRegistry lifecycle;
        LifecycleOwner lifecycleOwner2;
        super.onAttachedToWindow();
        invalidateLayoutNodeMeasurement(getRoot());
        invalidateLayers(getRoot());
        SnapshotStateObserver snapshotStateObserver = getSnapshotObserver().observer;
        snapshotStateObserver.getClass();
        snapshotStateObserver.applyUnsubscribe = ArtificialStackFrames.registerApplyObserver(snapshotStateObserver.applyObserver);
        AndroidAutofill androidAutofill = this._autofill;
        if (androidAutofill != null) {
            AutofillCallback.INSTANCE.register(androidAutofill);
        }
        LifecycleOwner lifecycleOwner3 = ResultKt.get(this);
        SavedStateRegistryOwner savedStateRegistryOwner = (SavedStateRegistryOwner) SequencesKt.firstOrNull(SequencesKt.mapNotNull(MathKt.generateSequence(this, ViewTreeSavedStateRegistryOwner$findViewTreeSavedStateRegistryOwner$1.INSTANCE), ViewTreeSavedStateRegistryOwner$findViewTreeSavedStateRegistryOwner$1.INSTANCE$1));
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners == null || (lifecycleOwner3 != null && savedStateRegistryOwner != null && (lifecycleOwner3 != (lifecycleOwner2 = viewTreeOwners.lifecycleOwner) || savedStateRegistryOwner != lifecycleOwner2))) {
            if (lifecycleOwner3 == null) {
                throw new IllegalStateException("Composed into the View which doesn't propagate ViewTreeLifecycleOwner!");
            }
            if (savedStateRegistryOwner == null) {
                throw new IllegalStateException("Composed into the View which doesn't propagateViewTreeSavedStateRegistryOwner!");
            }
            if (viewTreeOwners != null && (lifecycleOwner = viewTreeOwners.lifecycleOwner) != null && (lifecycle = lifecycleOwner.getLifecycle()) != null) {
                lifecycle.removeObserver(this);
            }
            lifecycleOwner3.getLifecycle().addObserver(this);
            ViewTreeOwners viewTreeOwners2 = new ViewTreeOwners(lifecycleOwner3, savedStateRegistryOwner);
            set_viewTreeOwners(viewTreeOwners2);
            Function1 function1 = this.onViewTreeOwnersAvailable;
            if (function1 != null) {
                function1.invoke(viewTreeOwners2);
            }
            this.onViewTreeOwnersAvailable = null;
        }
        int i = isInTouchMode() ? 1 : 2;
        InputModeManagerImpl inputModeManagerImpl = this._inputModeManager;
        inputModeManagerImpl.getClass();
        inputModeManagerImpl.inputMode$delegate.setValue(new InputMode(i));
        ViewTreeOwners viewTreeOwners3 = getViewTreeOwners();
        ResultKt.checkNotNull(viewTreeOwners3);
        viewTreeOwners3.lifecycleOwner.getLifecycle().addObserver(this);
        getViewTreeObserver().addOnGlobalLayoutListener(this.globalLayoutListener);
        getViewTreeObserver().addOnScrollChangedListener(this.scrollChangedListener);
        getViewTreeObserver().addOnTouchModeChangeListener(this.touchModeChangeListener);
    }

    @Override // android.view.View
    public final boolean onCheckIsTextEditor() {
        PlatformTextInputPluginRegistryImpl.AdapterWithRefCount adapterWithRefCount = (PlatformTextInputPluginRegistryImpl.AdapterWithRefCount) getPlatformTextInputPluginRegistry().adaptersByPlugin.get(null);
        return (adapterWithRefCount != null ? adapterWithRefCount.adapter : null) != null;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        ResultKt.checkNotNullParameter(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
        Context context = getContext();
        ResultKt.checkNotNullExpressionValue(context, "context");
        this.density = new DensityImpl(context.getResources().getDisplayMetrics().density, context.getResources().getConfiguration().fontScale);
        int i = Build.VERSION.SDK_INT;
        if ((i >= 31 ? configuration.fontWeightAdjustment : 0) != this.currentFontWeightAdjustment) {
            this.currentFontWeightAdjustment = i >= 31 ? configuration.fontWeightAdjustment : 0;
            Context context2 = getContext();
            ResultKt.checkNotNullExpressionValue(context2, "context");
            setFontFamilyResolver(ResultKt.createFontFamilyResolver(context2));
        }
        this.configurationChangeObserver.invoke(configuration);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00eb  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0091  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final android.view.inputmethod.InputConnection onCreateInputConnection(android.view.inputmethod.EditorInfo r19) {
        /*
            r18 = this;
            r0 = r19
            r2 = 2
            r3 = 0
            r4 = 1
            java.lang.String r5 = "outAttrs"
            kotlin.ResultKt.checkNotNullParameter(r0, r5)
            androidx.compose.ui.text.input.PlatformTextInputPluginRegistryImpl r5 = r18.getPlatformTextInputPluginRegistry()
            androidx.compose.runtime.snapshots.SnapshotStateMap r5 = r5.adaptersByPlugin
            r6 = 0
            java.lang.Object r5 = r5.get(r6)
            androidx.compose.ui.text.input.PlatformTextInputPluginRegistryImpl$AdapterWithRefCount r5 = (androidx.compose.ui.text.input.PlatformTextInputPluginRegistryImpl.AdapterWithRefCount) r5
            if (r5 == 0) goto L1c
            androidx.compose.ui.text.input.PlatformTextInputAdapter r5 = r5.adapter
            goto L1d
        L1c:
            r5 = r6
        L1d:
            if (r5 == 0) goto L265
            androidx.compose.ui.text.input.AndroidTextInputServicePlugin$Adapter r5 = (androidx.compose.ui.text.input.AndroidTextInputServicePlugin.Adapter) r5
            androidx.compose.ui.text.input.TextInputServiceAndroid r5 = r5.androidService
            r5.getClass()
            androidx.compose.ui.text.input.ImeOptions r7 = r5.imeOptions
            java.lang.String r8 = "imeOptions"
            kotlin.ResultKt.checkNotNullParameter(r7, r8)
            androidx.compose.ui.text.input.TextFieldValue r8 = r5.state
            java.lang.String r9 = "textFieldValue"
            kotlin.ResultKt.checkNotNullParameter(r8, r9)
            int r9 = r7.imeAction
            boolean r10 = androidx.compose.ui.text.input.ImeAction.m261equalsimpl0(r9, r4)
            boolean r11 = r7.singleLine
            r12 = 4
            r13 = 7
            r14 = 5
            r15 = 6
            r6 = 3
            if (r10 == 0) goto L49
            if (r11 == 0) goto L47
        L45:
            r10 = r15
            goto L80
        L47:
            r10 = r3
            goto L80
        L49:
            boolean r10 = androidx.compose.ui.text.input.ImeAction.m261equalsimpl0(r9, r3)
            if (r10 == 0) goto L51
            r10 = r4
            goto L80
        L51:
            boolean r10 = androidx.compose.ui.text.input.ImeAction.m261equalsimpl0(r9, r2)
            if (r10 == 0) goto L59
            r10 = r2
            goto L80
        L59:
            boolean r10 = androidx.compose.ui.text.input.ImeAction.m261equalsimpl0(r9, r15)
            if (r10 == 0) goto L61
            r10 = r14
            goto L80
        L61:
            boolean r10 = androidx.compose.ui.text.input.ImeAction.m261equalsimpl0(r9, r14)
            if (r10 == 0) goto L69
            r10 = r13
            goto L80
        L69:
            boolean r10 = androidx.compose.ui.text.input.ImeAction.m261equalsimpl0(r9, r6)
            if (r10 == 0) goto L71
            r10 = r6
            goto L80
        L71:
            boolean r10 = androidx.compose.ui.text.input.ImeAction.m261equalsimpl0(r9, r12)
            if (r10 == 0) goto L79
            r10 = r12
            goto L80
        L79:
            boolean r10 = androidx.compose.ui.text.input.ImeAction.m261equalsimpl0(r9, r13)
            if (r10 == 0) goto L259
            goto L45
        L80:
            r0.imeOptions = r10
            int r10 = r7.keyboardType
            boolean r17 = kotlin.ResultKt.m296equalsimpl0(r10, r4)
            r3 = 18
            r1 = 129(0x81, float:1.81E-43)
            if (r17 == 0) goto L91
            r0.inputType = r4
            goto Le9
        L91:
            boolean r17 = kotlin.ResultKt.m296equalsimpl0(r10, r2)
            if (r17 == 0) goto La1
            r0.inputType = r4
            int r10 = r0.imeOptions
            r12 = -2147483648(0xffffffff80000000, float:-0.0)
            r10 = r10 | r12
            r0.imeOptions = r10
            goto Le9
        La1:
            boolean r17 = kotlin.ResultKt.m296equalsimpl0(r10, r6)
            if (r17 == 0) goto Laa
            r0.inputType = r2
            goto Le9
        Laa:
            boolean r12 = kotlin.ResultKt.m296equalsimpl0(r10, r12)
            if (r12 == 0) goto Lb3
            r0.inputType = r6
            goto Le9
        Lb3:
            boolean r12 = kotlin.ResultKt.m296equalsimpl0(r10, r14)
            if (r12 == 0) goto Lbe
            r10 = 17
            r0.inputType = r10
            goto Le9
        Lbe:
            boolean r12 = kotlin.ResultKt.m296equalsimpl0(r10, r15)
            if (r12 == 0) goto Lc9
            r10 = 33
            r0.inputType = r10
            goto Le9
        Lc9:
            boolean r12 = kotlin.ResultKt.m296equalsimpl0(r10, r13)
            if (r12 == 0) goto Ld2
            r0.inputType = r1
            goto Le9
        Ld2:
            r12 = 8
            boolean r12 = kotlin.ResultKt.m296equalsimpl0(r10, r12)
            if (r12 == 0) goto Ldd
            r0.inputType = r3
            goto Le9
        Ldd:
            r12 = 9
            boolean r10 = kotlin.ResultKt.m296equalsimpl0(r10, r12)
            if (r10 == 0) goto L24d
            r10 = 8194(0x2002, float:1.1482E-41)
            r0.inputType = r10
        Le9:
            if (r11 != 0) goto L103
            int r10 = r0.inputType
            r11 = r10 & 1
            if (r11 != r4) goto L103
            r11 = 131072(0x20000, float:1.83671E-40)
            r10 = r10 | r11
            r0.inputType = r10
            boolean r9 = androidx.compose.ui.text.input.ImeAction.m261equalsimpl0(r9, r4)
            if (r9 == 0) goto L103
            int r9 = r0.imeOptions
            r10 = 1073741824(0x40000000, float:2.0)
            r9 = r9 | r10
            r0.imeOptions = r9
        L103:
            int r9 = r0.inputType
            r9 = r9 & r4
            if (r9 != r4) goto L10a
            r9 = r4
            goto L10b
        L10a:
            r9 = 0
        L10b:
            boolean r10 = r7.autoCorrect
            if (r9 == 0) goto L141
            int r7 = r7.capitalization
            boolean r9 = _COROUTINE._BOUNDARY.m6equalsimpl0(r7, r4)
            if (r9 == 0) goto L11e
            int r6 = r0.inputType
            r6 = r6 | 4096(0x1000, float:5.74E-42)
            r0.inputType = r6
            goto L137
        L11e:
            boolean r9 = _COROUTINE._BOUNDARY.m6equalsimpl0(r7, r2)
            if (r9 == 0) goto L12b
            int r6 = r0.inputType
            r6 = r6 | 8192(0x2000, float:1.14794E-41)
            r0.inputType = r6
            goto L137
        L12b:
            boolean r6 = _COROUTINE._BOUNDARY.m6equalsimpl0(r7, r6)
            if (r6 == 0) goto L137
            int r6 = r0.inputType
            r6 = r6 | 16384(0x4000, float:2.2959E-41)
            r0.inputType = r6
        L137:
            if (r10 == 0) goto L141
            int r6 = r0.inputType
            r7 = 32768(0x8000, float:4.5918E-41)
            r6 = r6 | r7
            r0.inputType = r6
        L141:
            int r6 = androidx.compose.ui.text.TextRange.$r8$clinit
            long r6 = r8.selection
            r9 = 32
            long r11 = r6 >> r9
            int r9 = (int) r11
            r0.initialSelStart = r9
            r11 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r6 = r6 & r11
            int r6 = (int) r6
            r0.initialSelEnd = r6
            androidx.compose.ui.text.AnnotatedString r6 = r8.annotatedString
            java.lang.String r6 = r6.text
            int r7 = android.os.Build.VERSION.SDK_INT
            r9 = 30
            if (r7 < r9) goto L164
            androidx.core.view.inputmethod.EditorInfoCompat$Api30Impl.setInitialSurroundingSubText(r0, r6)
            goto L213
        L164:
            r6.getClass()
            if (r7 < r9) goto L16e
            androidx.core.view.inputmethod.EditorInfoCompat$Api30Impl.setInitialSurroundingSubText(r0, r6)
            goto L213
        L16e:
            int r7 = r0.initialSelStart
            int r9 = r0.initialSelEnd
            if (r7 <= r9) goto L176
            r11 = r9
            goto L177
        L176:
            r11 = r7
        L177:
            if (r7 <= r9) goto L17a
            goto L17b
        L17a:
            r7 = r9
        L17b:
            int r9 = r6.length()
            if (r11 < 0) goto L183
            if (r7 <= r9) goto L187
        L183:
            r1 = 0
            r2 = 0
            goto L210
        L187:
            int r12 = r0.inputType
            r12 = r12 & 4095(0xfff, float:5.738E-42)
            if (r12 == r1) goto L193
            r1 = 225(0xe1, float:3.15E-43)
            if (r12 == r1) goto L193
            if (r12 != r3) goto L197
        L193:
            r1 = 0
            r2 = 0
            goto L20c
        L197:
            r1 = 2048(0x800, float:2.87E-42)
            if (r9 > r1) goto L1a0
            kotlin.ResultKt.setSurroundingText(r0, r6, r11, r7)
            goto L213
        L1a0:
            int r3 = r7 - r11
            r9 = 1024(0x400, float:1.435E-42)
            if (r3 <= r9) goto L1a8
            r9 = 0
            goto L1a9
        L1a8:
            r9 = r3
        L1a9:
            int r12 = r6.length()
            int r12 = r12 - r7
            int r1 = r1 - r9
            r13 = 4605380978949069210(0x3fe999999999999a, double:0.8)
            r16 = r3
            double r2 = (double) r1
            double r2 = r2 * r13
            int r2 = (int) r2
            int r2 = java.lang.Math.min(r11, r2)
            int r2 = r1 - r2
            int r2 = java.lang.Math.min(r12, r2)
            int r1 = r1 - r2
            int r1 = java.lang.Math.min(r11, r1)
            int r11 = r11 - r1
            char r3 = r6.charAt(r11)
            boolean r3 = java.lang.Character.isLowSurrogate(r3)
            if (r3 == 0) goto L1d5
            int r11 = r11 + r4
            int r1 = r1 - r4
        L1d5:
            int r3 = r7 + r2
            int r3 = r3 - r4
            char r3 = r6.charAt(r3)
            boolean r3 = java.lang.Character.isHighSurrogate(r3)
            if (r3 == 0) goto L1e3
            int r2 = r2 - r4
        L1e3:
            int r3 = r1 + r9
            int r12 = r3 + r2
            r13 = r16
            if (r9 == r13) goto L203
            int r9 = r11 + r1
            java.lang.CharSequence r9 = r6.subSequence(r11, r9)
            int r2 = r2 + r7
            java.lang.CharSequence r2 = r6.subSequence(r7, r2)
            r6 = 2
            java.lang.CharSequence[] r6 = new java.lang.CharSequence[r6]
            r7 = 0
            r6[r7] = r9
            r6[r4] = r2
            java.lang.CharSequence r2 = android.text.TextUtils.concat(r6)
            goto L208
        L203:
            int r12 = r12 + r11
            java.lang.CharSequence r2 = r6.subSequence(r11, r12)
        L208:
            kotlin.ResultKt.setSurroundingText(r0, r2, r1, r3)
            goto L213
        L20c:
            kotlin.ResultKt.setSurroundingText(r0, r1, r2, r2)
            goto L213
        L210:
            kotlin.ResultKt.setSurroundingText(r0, r1, r2, r2)
        L213:
            int r1 = r0.imeOptions
            r2 = 33554432(0x2000000, float:9.403955E-38)
            r1 = r1 | r2
            r0.imeOptions = r1
            androidx.emoji2.text.EmojiCompat r1 = androidx.emoji2.text.EmojiCompat.sInstance
            if (r1 == 0) goto L238
            androidx.emoji2.text.EmojiCompat r1 = androidx.emoji2.text.EmojiCompat.get()
            int r2 = r1.getLoadState()
            if (r2 != r4) goto L238
            android.os.Bundle r2 = r0.extras
            if (r2 != 0) goto L233
            android.os.Bundle r2 = new android.os.Bundle
            r2.<init>()
            r0.extras = r2
        L233:
            androidx.emoji2.text.EmojiCompat$CompatInternal19 r1 = r1.mHelper
            r1.updateEditorInfoAttrs(r0)
        L238:
            androidx.compose.ui.text.input.TextInputServiceAndroid$createInputConnection$1 r0 = new androidx.compose.ui.text.input.TextInputServiceAndroid$createInputConnection$1
            r0.<init>(r5)
            androidx.compose.ui.text.input.RecordingInputConnection r6 = new androidx.compose.ui.text.input.RecordingInputConnection
            r6.<init>(r8, r0, r10)
            java.util.ArrayList r0 = r5.ics
            java.lang.ref.WeakReference r1 = new java.lang.ref.WeakReference
            r1.<init>(r6)
            r0.add(r1)
            goto L266
        L24d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Invalid Keyboard Type"
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L259:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "invalid ImeAction"
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L265:
            r1 = r6
        L266:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView.onCreateInputConnection(android.view.inputmethod.EditorInfo):android.view.inputmethod.InputConnection");
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        LifecycleOwner lifecycleOwner;
        LifecycleRegistry lifecycle;
        super.onDetachedFromWindow();
        OwnerSnapshotObserver snapshotObserver = getSnapshotObserver();
        Snapshot$Companion$registerApplyObserver$2 snapshot$Companion$registerApplyObserver$2 = snapshotObserver.observer.applyUnsubscribe;
        if (snapshot$Companion$registerApplyObserver$2 != null) {
            snapshot$Companion$registerApplyObserver$2.dispose();
        }
        SnapshotStateObserver snapshotStateObserver = snapshotObserver.observer;
        synchronized (snapshotStateObserver.observedScopeMaps) {
            MutableVector mutableVector = snapshotStateObserver.observedScopeMaps;
            int i = mutableVector.size;
            if (i > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    SnapshotStateObserver.ObservedScopeMap observedScopeMap = (SnapshotStateObserver.ObservedScopeMap) objArr[i2];
                    observedScopeMap.valueToScopes.clear();
                    SnapshotWeakSet snapshotWeakSet = observedScopeMap.scopeToValues;
                    snapshotWeakSet.size = 0;
                    MapsKt___MapsJvmKt.fill(0, r7.length, (Object[]) snapshotWeakSet.hashes);
                    MapsKt___MapsJvmKt.fill(0, r6.length, snapshotWeakSet.values);
                    observedScopeMap.dependencyToDerivedStates.clear();
                    observedScopeMap.recordedDerivedStateValues.clear();
                    i2++;
                } while (i2 < i);
            }
        }
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners != null && (lifecycleOwner = viewTreeOwners.lifecycleOwner) != null && (lifecycle = lifecycleOwner.getLifecycle()) != null) {
            lifecycle.removeObserver(this);
        }
        AndroidAutofill androidAutofill = this._autofill;
        if (androidAutofill != null) {
            AutofillCallback.INSTANCE.unregister(androidAutofill);
        }
        getViewTreeObserver().removeOnGlobalLayoutListener(this.globalLayoutListener);
        getViewTreeObserver().removeOnScrollChangedListener(this.scrollChangedListener);
        getViewTreeObserver().removeOnTouchModeChangeListener(this.touchModeChangeListener);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        ResultKt.checkNotNullParameter(canvas, "canvas");
    }

    public final void onEndApplyChanges() {
        if (this.observationClearRequested) {
            SnapshotStateObserver snapshotStateObserver = getSnapshotObserver().observer;
            snapshotStateObserver.getClass();
            synchronized (snapshotStateObserver.observedScopeMaps) {
                MutableVector mutableVector = snapshotStateObserver.observedScopeMaps;
                int i = mutableVector.size;
                if (i > 0) {
                    Object[] objArr = mutableVector.content;
                    int i2 = 0;
                    do {
                        ((SnapshotStateObserver.ObservedScopeMap) objArr[i2]).removeScopeIf();
                        i2++;
                    } while (i2 < i);
                }
            }
            this.observationClearRequested = false;
        }
        AndroidViewsHandler androidViewsHandler = this._androidViewsHandler;
        if (androidViewsHandler != null) {
            clearChildInvalidObservations(androidViewsHandler);
        }
        while (this.endApplyChangesListeners.isNotEmpty()) {
            int i3 = this.endApplyChangesListeners.size;
            for (int i4 = 0; i4 < i3; i4++) {
                Object[] objArr2 = this.endApplyChangesListeners.content;
                Function0 function0 = (Function0) objArr2[i4];
                objArr2[i4] = null;
                if (function0 != null) {
                    function0.invoke();
                }
            }
            this.endApplyChangesListeners.removeRange(0, i3);
        }
    }

    @Override // android.view.View
    public final void onFocusChanged(boolean z, int i, Rect rect) {
        super.onFocusChanged(z, i, rect);
        Log.d("Compose Focus", "Owner FocusChanged(" + z + ')');
        if (!z) {
            _BOUNDARY.clearFocus(((FocusOwnerImpl) getFocusOwner()).rootFocusNode, true, true);
            return;
        }
        FocusTargetNode focusTargetNode = ((FocusOwnerImpl) getFocusOwner()).rootFocusNode;
        if (focusTargetNode.focusState == FocusStateImpl.Inactive) {
            focusTargetNode.focusState = FocusStateImpl.Active;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        this.measureAndLayoutDelegate.measureAndLayout(this.resendMotionEventOnLayout);
        this.onMeasureConstraints = null;
        updatePositionCacheAndDispatch();
        if (this._androidViewsHandler != null) {
            getAndroidViewsHandler$ui_release().layout(0, 0, i3 - i, i4 - i2);
        }
    }

    public final void onLayoutChange(LayoutNode layoutNode) {
        ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
        AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = this.accessibilityDelegate;
        androidComposeViewAccessibilityDelegateCompat.getClass();
        androidComposeViewAccessibilityDelegateCompat.currentSemanticsNodesInvalidated = true;
        if (androidComposeViewAccessibilityDelegateCompat.isEnabledForAccessibility()) {
            androidComposeViewAccessibilityDelegateCompat.notifySubtreeAccessibilityStateChangedIfNeeded(layoutNode);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        MeasureAndLayoutDelegate measureAndLayoutDelegate = this.measureAndLayoutDelegate;
        Trace.beginSection("AndroidOwner:onMeasure");
        try {
            if (!isAttachedToWindow()) {
                invalidateLayoutNodeMeasurement(getRoot());
            }
            long m216convertMeasureSpecI7RO_PI = m216convertMeasureSpecI7RO_PI(i);
            long m216convertMeasureSpecI7RO_PI2 = m216convertMeasureSpecI7RO_PI(i2);
            long Constraints = ResultKt.Constraints((int) (m216convertMeasureSpecI7RO_PI >>> 32), (int) (m216convertMeasureSpecI7RO_PI & 4294967295L), (int) (m216convertMeasureSpecI7RO_PI2 >>> 32), (int) (4294967295L & m216convertMeasureSpecI7RO_PI2));
            Constraints constraints = this.onMeasureConstraints;
            if (constraints == null) {
                this.onMeasureConstraints = new Constraints(Constraints);
                this.wasMeasuredWithMultipleConstraints = false;
            } else if (!Constraints.m273equalsimpl0(constraints.value, Constraints)) {
                this.wasMeasuredWithMultipleConstraints = true;
            }
            measureAndLayoutDelegate.m189updateRootConstraintsBRTryo0(Constraints);
            measureAndLayoutDelegate.measureOnly();
            setMeasuredDimension(getRoot().layoutDelegate.measurePassDelegate.width, getRoot().layoutDelegate.measurePassDelegate.height);
            if (this._androidViewsHandler != null) {
                getAndroidViewsHandler$ui_release().measure(View.MeasureSpec.makeMeasureSpec(getRoot().layoutDelegate.measurePassDelegate.width, 1073741824), View.MeasureSpec.makeMeasureSpec(getRoot().layoutDelegate.measurePassDelegate.height, 1073741824));
            }
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    @Override // android.view.View
    public final void onProvideAutofillVirtualStructure(ViewStructure viewStructure, int i) {
        AndroidAutofill androidAutofill;
        if (viewStructure == null || (androidAutofill = this._autofill) == null) {
            return;
        }
        AutofillApi23Helper autofillApi23Helper = AutofillApi23Helper.INSTANCE;
        AutofillTree autofillTree = androidAutofill.autofillTree;
        int addChildCount = autofillApi23Helper.addChildCount(viewStructure, autofillTree.children.size());
        for (Map.Entry entry : autofillTree.children.entrySet()) {
            int intValue = ((Number) entry.getKey()).intValue();
            DurationKt$$ExternalSyntheticCheckNotZero0.m(entry.getValue());
            ViewStructure newChild = autofillApi23Helper.newChild(viewStructure, addChildCount);
            if (newChild != null) {
                AutofillApi26Helper autofillApi26Helper = AutofillApi26Helper.INSTANCE;
                AutofillId autofillId = autofillApi26Helper.getAutofillId(viewStructure);
                ResultKt.checkNotNull(autofillId);
                autofillApi26Helper.setAutofillId(newChild, autofillId, intValue);
                autofillApi23Helper.setId(newChild, intValue, androidAutofill.view.getContext().getPackageName(), null, null);
                autofillApi26Helper.setAutofillType(newChild, 1);
                throw null;
            }
            addChildCount++;
        }
    }

    public final void onRequestMeasure(LayoutNode layoutNode, boolean z, boolean z2, boolean z3) {
        ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
        MeasureAndLayoutDelegate measureAndLayoutDelegate = this.measureAndLayoutDelegate;
        if (z) {
            if (measureAndLayoutDelegate.requestLookaheadRemeasure(layoutNode, z2) && z3) {
                scheduleMeasureAndLayout(layoutNode);
                return;
            }
            return;
        }
        if (measureAndLayoutDelegate.requestRemeasure(layoutNode, z2) && z3) {
            scheduleMeasureAndLayout(layoutNode);
        }
    }

    public final void onRequestRelayout(LayoutNode layoutNode, boolean z, boolean z2) {
        ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
        MeasureAndLayoutDelegate measureAndLayoutDelegate = this.measureAndLayoutDelegate;
        if (z) {
            if (measureAndLayoutDelegate.requestLookaheadRelayout(layoutNode, z2)) {
                scheduleMeasureAndLayout(null);
            }
        } else if (measureAndLayoutDelegate.requestRelayout(layoutNode, z2)) {
            scheduleMeasureAndLayout(null);
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onResume(LifecycleOwner lifecycleOwner) {
        setShowLayoutBounds(AndroidTextToolbar.access$getIsShowingLayoutBounds());
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i) {
        if (this.superclassInitComplete) {
            LayoutDirection layoutDirection = LayoutDirection.Ltr;
            if (i != 0 && i == 1) {
                layoutDirection = LayoutDirection.Rtl;
            }
            setLayoutDirection(layoutDirection);
            FocusOwnerImpl focusOwnerImpl = (FocusOwnerImpl) getFocusOwner();
            focusOwnerImpl.getClass();
            focusOwnerImpl.layoutDirection = layoutDirection;
        }
    }

    public final void onSemanticsChange() {
        AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = this.accessibilityDelegate;
        androidComposeViewAccessibilityDelegateCompat.currentSemanticsNodesInvalidated = true;
        if (!androidComposeViewAccessibilityDelegateCompat.isEnabledForAccessibility() || androidComposeViewAccessibilityDelegateCompat.checkingForSemanticsChanges) {
            return;
        }
        androidComposeViewAccessibilityDelegateCompat.checkingForSemanticsChanges = true;
        androidComposeViewAccessibilityDelegateCompat.handler.post(androidComposeViewAccessibilityDelegateCompat.semanticsChangeChecker);
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        boolean access$getIsShowingLayoutBounds;
        this._windowInfo._isWindowFocused.setValue(Boolean.valueOf(z));
        this.keyboardModifiersRequireUpdate = true;
        super.onWindowFocusChanged(z);
        if (!z || getShowLayoutBounds() == (access$getIsShowingLayoutBounds = AndroidTextToolbar.access$getIsShowingLayoutBounds())) {
            return;
        }
        setShowLayoutBounds(access$getIsShowingLayoutBounds);
        invalidateLayers(getRoot());
    }

    public final void recalculateWindowPosition() {
        if (this.forceUseMatrixCache) {
            return;
        }
        long currentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        if (currentAnimationTimeMillis != this.lastMatrixRecalculationAnimationTime) {
            this.lastMatrixRecalculationAnimationTime = currentAnimationTimeMillis;
            CalculateMatrixToWindow calculateMatrixToWindow = this.matrixToWindow;
            float[] fArr = this.viewToWindowMatrix;
            calculateMatrixToWindow.mo225calculateMatrixToWindowEL8BTi8(this, fArr);
            InvertMatrixKt.m232invertToJiSxe2E(fArr, this.windowToViewMatrix);
            ViewParent parent = getParent();
            View view = this;
            while (parent instanceof ViewGroup) {
                view = (View) parent;
                parent = ((ViewGroup) view).getParent();
            }
            int[] iArr = this.tmpPositionArray;
            view.getLocationOnScreen(iArr);
            float f = iArr[0];
            float f2 = iArr[1];
            view.getLocationInWindow(iArr);
            this.windowPosition = _BOUNDARY.Offset(f - iArr[0], f2 - iArr[1]);
        }
    }

    public final void recycle$ui_release(OwnedLayer ownedLayer) {
        ResultKt.checkNotNullParameter(ownedLayer, "layer");
        if (this.viewLayersContainer != null) {
            ViewLayer$Companion$OutlineProvider$1 viewLayer$Companion$OutlineProvider$1 = ViewLayer.OutlineProvider;
        }
        WeakCache weakCache = this.layerCache;
        weakCache.clearWeakReferences();
        ((MutableVector) weakCache.values).add(new WeakReference(ownedLayer, (ReferenceQueue) weakCache.referenceQueue));
    }

    public final void registerOnEndApplyChangesListener(Function0 function0) {
        ResultKt.checkNotNullParameter(function0, "listener");
        MutableVector mutableVector = this.endApplyChangesListeners;
        if (mutableVector.contains(function0)) {
            return;
        }
        mutableVector.add(function0);
    }

    public final void scheduleMeasureAndLayout(LayoutNode layoutNode) {
        if (isLayoutRequested() || !isAttachedToWindow()) {
            return;
        }
        if (layoutNode != null) {
            while (layoutNode != null && layoutNode.getMeasuredByParent$ui_release() == 1) {
                if (!this.wasMeasuredWithMultipleConstraints) {
                    LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
                    if (parent$ui_release == null) {
                        break;
                    }
                    long j = parent$ui_release.nodes.innerCoordinator.measurementConstraints;
                    if (Constraints.m276getMaxWidthimpl(j) == Constraints.m278getMinWidthimpl(j) && Constraints.m275getMaxHeightimpl(j) == Constraints.m277getMinHeightimpl(j)) {
                        break;
                    }
                }
                layoutNode = layoutNode.getParent$ui_release();
            }
            if (layoutNode == getRoot()) {
                requestLayout();
                return;
            }
        }
        if (getWidth() == 0 || getHeight() == 0) {
            requestLayout();
        } else {
            invalidate();
        }
    }

    /* renamed from: screenToLocal-MK-Hz9U, reason: not valid java name */
    public final long m219screenToLocalMKHz9U(long j) {
        recalculateWindowPosition();
        return Brush.m104mapMKHz9U(this.windowToViewMatrix, _BOUNDARY.Offset(Offset.m76getXimpl(j) - Offset.m76getXimpl(this.windowPosition), Offset.m77getYimpl(j) - Offset.m77getYimpl(this.windowPosition)));
    }

    /* renamed from: sendMotionEvent-8iAsVTc, reason: not valid java name */
    public final int m220sendMotionEvent8iAsVTc(MotionEvent motionEvent) {
        Object obj;
        int i = 0;
        if (this.keyboardModifiersRequireUpdate) {
            this.keyboardModifiersRequireUpdate = false;
            int metaState = motionEvent.getMetaState();
            this._windowInfo.getClass();
            WindowInfoImpl.GlobalKeyboardModifiers.setValue(new PointerKeyboardModifiers(metaState));
        }
        MotionEventAdapter motionEventAdapter = this.motionEventAdapter;
        PointerInputEvent convertToPointerInputEvent$ui_release = motionEventAdapter.convertToPointerInputEvent$ui_release(motionEvent, this);
        EmojiProcessor emojiProcessor = this.pointerInputEventProcessor;
        if (convertToPointerInputEvent$ui_release != null) {
            List list = convertToPointerInputEvent$ui_release.pointers;
            int size = list.size() - 1;
            if (size >= 0) {
                while (true) {
                    int i2 = size - 1;
                    obj = list.get(size);
                    if (((PointerInputEventData) obj).down) {
                        break;
                    }
                    if (i2 < 0) {
                        break;
                    }
                    size = i2;
                }
            }
            obj = null;
            PointerInputEventData pointerInputEventData = (PointerInputEventData) obj;
            if (pointerInputEventData != null) {
                this.lastDownPointerPosition = pointerInputEventData.position;
            }
            i = emojiProcessor.m289processBIzXfog(convertToPointerInputEvent$ui_release, this, isInBounds(motionEvent));
            int actionMasked = motionEvent.getActionMasked();
            if ((actionMasked == 0 || actionMasked == 5) && (i & 1) == 0) {
                int pointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
                motionEventAdapter.canHover.delete(pointerId);
                motionEventAdapter.motionEventToComposePointerIdMap.delete(pointerId);
            }
        } else {
            emojiProcessor.processCancel();
        }
        return i;
    }

    public final void sendSimulatedEvent(MotionEvent motionEvent, int i, long j, boolean z) {
        int actionMasked = motionEvent.getActionMasked();
        int i2 = -1;
        if (actionMasked != 1) {
            if (actionMasked == 6) {
                i2 = motionEvent.getActionIndex();
            }
        } else if (i != 9 && i != 10) {
            i2 = 0;
        }
        int pointerCount = motionEvent.getPointerCount() - (i2 >= 0 ? 1 : 0);
        if (pointerCount == 0) {
            return;
        }
        MotionEvent.PointerProperties[] pointerPropertiesArr = new MotionEvent.PointerProperties[pointerCount];
        for (int i3 = 0; i3 < pointerCount; i3++) {
            pointerPropertiesArr[i3] = new MotionEvent.PointerProperties();
        }
        MotionEvent.PointerCoords[] pointerCoordsArr = new MotionEvent.PointerCoords[pointerCount];
        for (int i4 = 0; i4 < pointerCount; i4++) {
            pointerCoordsArr[i4] = new MotionEvent.PointerCoords();
        }
        int i5 = 0;
        while (i5 < pointerCount) {
            int i6 = ((i2 < 0 || i5 < i2) ? 0 : 1) + i5;
            motionEvent.getPointerProperties(i6, pointerPropertiesArr[i5]);
            MotionEvent.PointerCoords pointerCoords = pointerCoordsArr[i5];
            motionEvent.getPointerCoords(i6, pointerCoords);
            long m218localToScreenMKHz9U = m218localToScreenMKHz9U(_BOUNDARY.Offset(pointerCoords.x, pointerCoords.y));
            pointerCoords.x = Offset.m76getXimpl(m218localToScreenMKHz9U);
            pointerCoords.y = Offset.m77getYimpl(m218localToScreenMKHz9U);
            i5++;
        }
        MotionEvent obtain = MotionEvent.obtain(motionEvent.getDownTime() == motionEvent.getEventTime() ? j : motionEvent.getDownTime(), j, i, pointerCount, pointerPropertiesArr, pointerCoordsArr, motionEvent.getMetaState(), z ? 0 : motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags());
        ResultKt.checkNotNullExpressionValue(obtain, "event");
        PointerInputEvent convertToPointerInputEvent$ui_release = this.motionEventAdapter.convertToPointerInputEvent$ui_release(obtain, this);
        ResultKt.checkNotNull(convertToPointerInputEvent$ui_release);
        this.pointerInputEventProcessor.m289processBIzXfog(convertToPointerInputEvent$ui_release, this, true);
        obtain.recycle();
    }

    public final void setConfigurationChangeObserver(Function1 function1) {
        ResultKt.checkNotNullParameter(function1, "<set-?>");
        this.configurationChangeObserver = function1;
    }

    public final void setLastMatrixRecalculationAnimationTime$ui_release(long j) {
        this.lastMatrixRecalculationAnimationTime = j;
    }

    public final void setOnViewTreeOwnersAvailable(Function1 function1) {
        ResultKt.checkNotNullParameter(function1, "callback");
        ViewTreeOwners viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners != null) {
            function1.invoke(viewTreeOwners);
        }
        if (isAttachedToWindow()) {
            return;
        }
        this.onViewTreeOwnersAvailable = function1;
    }

    public void setShowLayoutBounds(boolean z) {
        this.showLayoutBounds = z;
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    public final void updatePositionCacheAndDispatch() {
        int[] iArr = this.tmpPositionArray;
        getLocationOnScreen(iArr);
        long j = this.globalPosition;
        int i = IntOffset.$r8$clinit;
        int i2 = (int) (j >> 32);
        int i3 = (int) (j & 4294967295L);
        boolean z = false;
        int i4 = iArr[0];
        if (i2 != i4 || i3 != iArr[1]) {
            this.globalPosition = ResultKt.IntOffset(i4, iArr[1]);
            if (i2 != Integer.MAX_VALUE && i3 != Integer.MAX_VALUE) {
                getRoot().layoutDelegate.measurePassDelegate.notifyChildrenUsingCoordinatesWhilePlacing();
                z = true;
            }
        }
        this.measureAndLayoutDelegate.dispatchOnPositionedCallbacks(z);
    }

    /* JADX DEBUG: Method merged with bridge method: getAccessibilityManager()Landroidx/compose/ui/platform/AccessibilityManager; */
    public AndroidAccessibilityManager getAccessibilityManager() {
        return this.accessibilityManager;
    }

    /* JADX DEBUG: Method merged with bridge method: getClipboardManager()Landroidx/compose/ui/platform/ClipboardManager; */
    /* renamed from: getClipboardManager, reason: merged with bridge method [inline-methods] */
    public AndroidClipboardManager m222getClipboardManager() {
        return this.clipboardManager;
    }

    /* JADX DEBUG: Method merged with bridge method: getPlatformTextInputPluginRegistry()Landroidx/compose/ui/text/input/PlatformTextInputPluginRegistry; */
    public PlatformTextInputPluginRegistryImpl getPlatformTextInputPluginRegistry() {
        return this.platformTextInputPluginRegistry;
    }
}
