package androidx.compose.ui.platform;

import _COROUTINE._BOUNDARY;
import android.content.ClipDescription;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.SpannableString;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.contentcapture.ContentCaptureSession;
import androidx.activity.FullyDrawnReporter$$ExternalSyntheticLambda0;
import androidx.collection.ArrayMap;
import androidx.collection.ArraySet;
import androidx.collection.ContainerHelpers;
import androidx.collection.SimpleArrayMap;
import androidx.collection.SparseArrayCompat;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.coreshims.ViewCompatShims$Api29Impl;
import androidx.compose.ui.platform.coreshims.ViewCompatShims$Api30Impl;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsNode$parent$1;
import androidx.compose.ui.semantics.SemanticsOwner;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesAndroid;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.state.ToggleableState;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.platform.URLSpanCache;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewKt$ancestors$1;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import eu.malek.composesample2.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.AbstractMap$toString$1;
import kotlin.collections.ArrayAsCollection;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.collections.EmptyMap;
import kotlin.jvm.functions.Function3;
import kotlin.math.MathKt;
import kotlin.ranges.ClosedFloatRange;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferedChannel;

/* loaded from: classes.dex */
public final class AndroidComposeViewAccessibilityDelegateCompat extends AccessibilityDelegateCompat {
    public static final int[] AccessibilityActionsResourceIds = {R.id.accessibility_custom_action_0, R.id.accessibility_custom_action_1, R.id.accessibility_custom_action_2, R.id.accessibility_custom_action_3, R.id.accessibility_custom_action_4, R.id.accessibility_custom_action_5, R.id.accessibility_custom_action_6, R.id.accessibility_custom_action_7, R.id.accessibility_custom_action_8, R.id.accessibility_custom_action_9, R.id.accessibility_custom_action_10, R.id.accessibility_custom_action_11, R.id.accessibility_custom_action_12, R.id.accessibility_custom_action_13, R.id.accessibility_custom_action_14, R.id.accessibility_custom_action_15, R.id.accessibility_custom_action_16, R.id.accessibility_custom_action_17, R.id.accessibility_custom_action_18, R.id.accessibility_custom_action_19, R.id.accessibility_custom_action_20, R.id.accessibility_custom_action_21, R.id.accessibility_custom_action_22, R.id.accessibility_custom_action_23, R.id.accessibility_custom_action_24, R.id.accessibility_custom_action_25, R.id.accessibility_custom_action_26, R.id.accessibility_custom_action_27, R.id.accessibility_custom_action_28, R.id.accessibility_custom_action_29, R.id.accessibility_custom_action_30, R.id.accessibility_custom_action_31};
    public final String EXTRA_DATA_TEST_TRAVERSALAFTER_VAL;
    public final String EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL;
    public int accessibilityCursorPosition;
    public final android.view.accessibility.AccessibilityManager accessibilityManager;
    public final SparseArrayCompat actionIdToLabel;
    public final BufferedChannel boundsUpdateChannel;
    public final ArrayMap bufferedContentCaptureAppearedNodes;
    public final ArraySet bufferedContentCaptureDisappearedNodes;
    public boolean checkingForSemanticsChanges;
    public WeakCache contentCaptureSession;
    public Map currentSemanticsNodes;
    public boolean currentSemanticsNodesInvalidated;
    public List enabledServices;
    public final AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda0 enabledStateListener;
    public int focusedVirtualViewId;
    public final Handler handler;
    public int hoveredVirtualViewId;
    public final HashMap idToAfterMap;
    public final HashMap idToBeforeMap;
    public final SparseArrayCompat labelToActionId;
    public final AccessibilityNodeProviderCompat nodeProvider;
    public final ArraySet paneDisplayed;
    public PendingTextTraversedEvent pendingTextTraversedEvent;
    public final LinkedHashMap previousSemanticsNodes;
    public SemanticsNodeCopy previousSemanticsRoot;
    public Integer previousTraversedNode;
    public final ArrayList scrollObservationScopes;
    public final FullyDrawnReporter$$ExternalSyntheticLambda0 semanticsChangeChecker;
    public final AbstractMap$toString$1 sendScrollEventIfNeededLambda;
    public final ArraySet subtreeChangedLayoutNodes;
    public final AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda1 touchExplorationStateListener;
    public final URLSpanCache urlSpanCache;
    public final AndroidComposeView view;

    /* renamed from: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$1 */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements View.OnAttachStateChangeListener {
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ Object this$0;

        public /* synthetic */ AnonymousClass1(int i, Object obj) {
            this.$r8$classId = i;
            this.this$0 = obj;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewAttachedToWindow(View view) {
            ContentCaptureSession contentCaptureSession;
            switch (this.$r8$classId) {
                case 0:
                    ResultKt.checkNotNullParameter(view, "view");
                    AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = (AndroidComposeViewAccessibilityDelegateCompat) this.this$0;
                    androidComposeViewAccessibilityDelegateCompat.accessibilityManager.addAccessibilityStateChangeListener(androidComposeViewAccessibilityDelegateCompat.enabledStateListener);
                    androidComposeViewAccessibilityDelegateCompat.accessibilityManager.addTouchExplorationStateChangeListener(androidComposeViewAccessibilityDelegateCompat.touchExplorationStateListener);
                    int i = Build.VERSION.SDK_INT;
                    if (i >= 30) {
                        ViewCompatShims$Api30Impl.setImportantForContentCapture(view, 1);
                    }
                    WeakCache weakCache = null;
                    if (i >= 29 && (contentCaptureSession = ViewCompatShims$Api29Impl.getContentCaptureSession(view)) != null) {
                        weakCache = new WeakCache(contentCaptureSession, 6, view);
                    }
                    androidComposeViewAccessibilityDelegateCompat.contentCaptureSession = weakCache;
                    return;
                case 1:
                    ResultKt.checkNotNullParameter(view, "v");
                    return;
                default:
                    ResultKt.checkNotNullParameter(view, "v");
                    return;
            }
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public final void onViewDetachedFromWindow(View view) {
            int i = this.$r8$classId;
            Object obj = this.this$0;
            switch (i) {
                case 0:
                    ResultKt.checkNotNullParameter(view, "view");
                    AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = (AndroidComposeViewAccessibilityDelegateCompat) obj;
                    androidComposeViewAccessibilityDelegateCompat.handler.removeCallbacks(androidComposeViewAccessibilityDelegateCompat.semanticsChangeChecker);
                    android.view.accessibility.AccessibilityManager accessibilityManager = androidComposeViewAccessibilityDelegateCompat.accessibilityManager;
                    accessibilityManager.removeAccessibilityStateChangeListener(androidComposeViewAccessibilityDelegateCompat.enabledStateListener);
                    accessibilityManager.removeTouchExplorationStateChangeListener(androidComposeViewAccessibilityDelegateCompat.touchExplorationStateListener);
                    androidComposeViewAccessibilityDelegateCompat.contentCaptureSession = null;
                    return;
                case 1:
                    ResultKt.checkNotNullParameter(view, "v");
                    AbstractComposeView abstractComposeView = (AbstractComposeView) obj;
                    ResultKt.checkNotNullParameter(abstractComposeView, "<this>");
                    for (Object obj2 : MathKt.generateSequence(abstractComposeView.getParent(), ViewKt$ancestors$1.INSTANCE)) {
                        if (obj2 instanceof View) {
                            View view2 = (View) obj2;
                            ResultKt.checkNotNullParameter(view2, "<this>");
                            Object tag = view2.getTag(R.id.is_pooling_container_tag);
                            Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
                            if (bool != null && bool.booleanValue()) {
                                return;
                            }
                        }
                    }
                    WrappedComposition wrappedComposition = abstractComposeView.composition;
                    if (wrappedComposition != null) {
                        wrappedComposition.dispose();
                    }
                    abstractComposeView.composition = null;
                    abstractComposeView.requestLayout();
                    return;
                default:
                    ResultKt.checkNotNullParameter(view, "v");
                    view.removeOnAttachStateChangeListener(this);
                    ((Job) obj).cancel(null);
                    return;
            }
        }
    }

    /* loaded from: classes.dex */
    public abstract class Api24Impl {
        public static final void addSetProgressAction(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, SemanticsNode semanticsNode) {
            ResultKt.checkNotNullParameter(accessibilityNodeInfoCompat, "info");
            ResultKt.checkNotNullParameter(semanticsNode, "semanticsNode");
            if (InvertMatrixKt.access$enabled(semanticsNode)) {
                AccessibilityAction accessibilityAction = (AccessibilityAction) _BOUNDARY.getOrNull(semanticsNode.unmergedConfig, SemanticsActions.SetProgress);
                if (accessibilityAction != null) {
                    accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, android.R.id.accessibilityActionSetProgress, accessibilityAction.label, null));
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public abstract class Api29Impl {
        public static final void addPageActions(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat, SemanticsNode semanticsNode) {
            ResultKt.checkNotNullParameter(accessibilityNodeInfoCompat, "info");
            ResultKt.checkNotNullParameter(semanticsNode, "semanticsNode");
            if (InvertMatrixKt.access$enabled(semanticsNode)) {
                SemanticsPropertyKey semanticsPropertyKey = SemanticsActions.PageUp;
                SemanticsConfiguration semanticsConfiguration = semanticsNode.unmergedConfig;
                AccessibilityAction accessibilityAction = (AccessibilityAction) _BOUNDARY.getOrNull(semanticsConfiguration, semanticsPropertyKey);
                if (accessibilityAction != null) {
                    accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, android.R.id.accessibilityActionPageUp, accessibilityAction.label, null));
                }
                AccessibilityAction accessibilityAction2 = (AccessibilityAction) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsActions.PageDown);
                if (accessibilityAction2 != null) {
                    accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, android.R.id.accessibilityActionPageDown, accessibilityAction2.label, null));
                }
                AccessibilityAction accessibilityAction3 = (AccessibilityAction) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsActions.PageLeft);
                if (accessibilityAction3 != null) {
                    accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, android.R.id.accessibilityActionPageLeft, accessibilityAction3.label, null));
                }
                AccessibilityAction accessibilityAction4 = (AccessibilityAction) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsActions.PageRight);
                if (accessibilityAction4 != null) {
                    accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, android.R.id.accessibilityActionPageRight, accessibilityAction4.label, null));
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public final class MyNodeProvider extends AccessibilityNodeProvider {
        public MyNodeProvider() {
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public final void addExtraDataToAccessibilityNodeInfo(int i, AccessibilityNodeInfo accessibilityNodeInfo, String str, Bundle bundle) {
            ResultKt.checkNotNullParameter(accessibilityNodeInfo, "info");
            ResultKt.checkNotNullParameter(str, "extraDataKey");
            AndroidComposeViewAccessibilityDelegateCompat.this.addExtraDataToAccessibilityNodeInfoHelper(i, accessibilityNodeInfo, str, bundle);
        }

        @Override // android.view.accessibility.AccessibilityNodeProvider
        public final AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
            LayoutNode findClosestParentNode;
            SemanticsConfiguration collapsedSemantics$ui_release;
            ClipDescription primaryClipDescription;
            LifecycleOwner lifecycleOwner;
            LifecycleRegistry lifecycle;
            AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = AndroidComposeViewAccessibilityDelegateCompat.this;
            AndroidComposeView androidComposeView = androidComposeViewAccessibilityDelegateCompat.view;
            AndroidComposeView.ViewTreeOwners viewTreeOwners = androidComposeView.getViewTreeOwners();
            if (((viewTreeOwners == null || (lifecycleOwner = viewTreeOwners.lifecycleOwner) == null || (lifecycle = lifecycleOwner.getLifecycle()) == null) ? null : lifecycle.state) != Lifecycle.State.DESTROYED) {
                AccessibilityNodeInfo obtain = AccessibilityNodeInfo.obtain();
                AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = new AccessibilityNodeInfoCompat(obtain);
                SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) androidComposeViewAccessibilityDelegateCompat.getCurrentSemanticsNodes$ui_release().get(Integer.valueOf(i));
                if (semanticsNodeWithAdjustedBounds != null) {
                    SemanticsNode semanticsNode = semanticsNodeWithAdjustedBounds.semanticsNode;
                    if (i == -1) {
                        int i2 = ViewCompat.$r8$clinit;
                        Object parentForAccessibility = androidComposeView.getParentForAccessibility();
                        View view = parentForAccessibility instanceof View ? (View) parentForAccessibility : null;
                        accessibilityNodeInfoCompat.mParentVirtualDescendantId = -1;
                        obtain.setParent(view);
                    } else {
                        if (semanticsNode.getParent() == null) {
                            throw new IllegalStateException("semanticsNode " + i + " has null parent");
                        }
                        SemanticsNode parent = semanticsNode.getParent();
                        ResultKt.checkNotNull(parent);
                        int i3 = androidComposeView.getSemanticsOwner().getUnmergedRootSemanticsNode().id;
                        int i4 = parent.id;
                        int i5 = i4 != i3 ? i4 : -1;
                        accessibilityNodeInfoCompat.mParentVirtualDescendantId = i5;
                        obtain.setParent(androidComposeView, i5);
                    }
                    accessibilityNodeInfoCompat.mVirtualDescendantId = i;
                    obtain.setSource(androidComposeView, i);
                    Rect rect = semanticsNodeWithAdjustedBounds.adjustedBounds;
                    long m218localToScreenMKHz9U = androidComposeView.m218localToScreenMKHz9U(_BOUNDARY.Offset(rect.left, rect.top));
                    long m218localToScreenMKHz9U2 = androidComposeView.m218localToScreenMKHz9U(_BOUNDARY.Offset(rect.right, rect.bottom));
                    obtain.setBoundsInScreen(new Rect((int) Math.floor(Offset.m76getXimpl(m218localToScreenMKHz9U)), (int) Math.floor(Offset.m77getYimpl(m218localToScreenMKHz9U)), (int) Math.ceil(Offset.m76getXimpl(m218localToScreenMKHz9U2)), (int) Math.ceil(Offset.m77getYimpl(m218localToScreenMKHz9U2))));
                    ResultKt.checkNotNullParameter(semanticsNode, "semanticsNode");
                    accessibilityNodeInfoCompat.setClassName("android.view.View");
                    SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.Role;
                    SemanticsConfiguration semanticsConfiguration = semanticsNode.unmergedConfig;
                    Role role = (Role) _BOUNDARY.getOrNull(semanticsConfiguration, semanticsPropertyKey);
                    LayoutNode layoutNode = semanticsNode.layoutNode;
                    if (role != null && (semanticsNode.isFake || semanticsNode.getChildren(false, true).isEmpty())) {
                        int i6 = role.value;
                        if (Role.m238equalsimpl0(i6, 4)) {
                            obtain.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", androidComposeView.getContext().getResources().getString(R.string.tab));
                        } else if (Role.m238equalsimpl0(i6, 2)) {
                            obtain.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", androidComposeView.getContext().getResources().getString(R.string.switch_role));
                        } else {
                            String m230access$toLegacyClassNameV4PA4sw = InvertMatrixKt.m230access$toLegacyClassNameV4PA4sw(i6);
                            if (!Role.m238equalsimpl0(i6, 5) || ((!semanticsNode.isFake && semanticsNode.getChildren(false, true).isEmpty() && _BOUNDARY.findClosestParentNode(layoutNode, SemanticsNode$parent$1.INSTANCE$1) == null) || semanticsConfiguration.isMergingSemanticsOfDescendants)) {
                                accessibilityNodeInfoCompat.setClassName(m230access$toLegacyClassNameV4PA4sw);
                            }
                        }
                    }
                    if (semanticsConfiguration.contains(SemanticsActions.SetText)) {
                        accessibilityNodeInfoCompat.setClassName("android.widget.EditText");
                    }
                    if (semanticsNode.getConfig().contains(SemanticsProperties.Text)) {
                        accessibilityNodeInfoCompat.setClassName("android.widget.TextView");
                    }
                    obtain.setPackageName(androidComposeView.getContext().getPackageName());
                    obtain.setImportantForAccessibility(true);
                    List children = semanticsNode.getChildren(false, true);
                    int size = children.size();
                    for (int i7 = 0; i7 < size; i7++) {
                        SemanticsNode semanticsNode2 = (SemanticsNode) children.get(i7);
                        if (androidComposeViewAccessibilityDelegateCompat.getCurrentSemanticsNodes$ui_release().containsKey(Integer.valueOf(semanticsNode2.id))) {
                            DurationKt$$ExternalSyntheticCheckNotZero0.m(androidComposeView.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().get(semanticsNode2.layoutNode));
                            obtain.addChild(androidComposeView, semanticsNode2.id);
                        }
                    }
                    int i8 = androidComposeViewAccessibilityDelegateCompat.focusedVirtualViewId;
                    AccessibilityNodeInfo accessibilityNodeInfo = accessibilityNodeInfoCompat.mInfo;
                    if (i8 == i) {
                        accessibilityNodeInfo.setAccessibilityFocused(true);
                        accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
                    } else {
                        accessibilityNodeInfo.setAccessibilityFocused(false);
                        accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_ACCESSIBILITY_FOCUS);
                    }
                    obtain.setText(androidComposeViewAccessibilityDelegateCompat.getInfoText(semanticsNode));
                    SemanticsPropertyKey semanticsPropertyKey2 = SemanticsProperties.Error;
                    if (semanticsConfiguration.contains(semanticsPropertyKey2)) {
                        obtain.setContentInvalid(true);
                        obtain.setError((CharSequence) _BOUNDARY.getOrNull(semanticsConfiguration, semanticsPropertyKey2));
                    }
                    String infoStateDescriptionOrNull = androidComposeViewAccessibilityDelegateCompat.getInfoStateDescriptionOrNull(semanticsNode);
                    if (Build.VERSION.SDK_INT >= 30) {
                        AccessibilityNodeInfoCompat.Api30Impl.setStateDescription(accessibilityNodeInfo, infoStateDescriptionOrNull);
                    } else {
                        accessibilityNodeInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY", infoStateDescriptionOrNull);
                    }
                    obtain.setCheckable(AndroidComposeViewAccessibilityDelegateCompat.getInfoIsCheckable(semanticsNode));
                    ToggleableState toggleableState = (ToggleableState) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsProperties.ToggleableState);
                    if (toggleableState != null) {
                        if (toggleableState == ToggleableState.On) {
                            accessibilityNodeInfo.setChecked(true);
                        } else if (toggleableState == ToggleableState.Off) {
                            accessibilityNodeInfo.setChecked(false);
                        }
                    }
                    Boolean bool = (Boolean) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsProperties.Selected);
                    if (bool != null) {
                        boolean booleanValue = bool.booleanValue();
                        if (role != null && Role.m238equalsimpl0(role.value, 4)) {
                            obtain.setSelected(booleanValue);
                        } else {
                            accessibilityNodeInfo.setChecked(booleanValue);
                        }
                    }
                    if (!semanticsConfiguration.isMergingSemanticsOfDescendants || semanticsNode.getChildren(false, true).isEmpty()) {
                        List list = (List) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsProperties.ContentDescription);
                        obtain.setContentDescription(list != null ? (String) CollectionsKt___CollectionsKt.firstOrNull(list) : null);
                    }
                    String str = (String) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsProperties.TestTag);
                    if (str != null) {
                        SemanticsNode semanticsNode3 = semanticsNode;
                        while (true) {
                            if (semanticsNode3 == null) {
                                break;
                            }
                            SemanticsPropertyKey semanticsPropertyKey3 = SemanticsPropertiesAndroid.TestTagsAsResourceId;
                            SemanticsConfiguration semanticsConfiguration2 = semanticsNode3.unmergedConfig;
                            if (!semanticsConfiguration2.contains(semanticsPropertyKey3)) {
                                semanticsNode3 = semanticsNode3.getParent();
                            } else if (((Boolean) semanticsConfiguration2.get(semanticsPropertyKey3)).booleanValue()) {
                                obtain.setViewIdResourceName(str);
                            }
                        }
                    }
                    if (((Unit) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsProperties.Heading)) != null) {
                        accessibilityNodeInfo.setHeading(true);
                    }
                    obtain.setPassword(semanticsNode.getConfig().contains(SemanticsProperties.Password));
                    SemanticsPropertyKey semanticsPropertyKey4 = SemanticsActions.SetText;
                    obtain.setEditable(semanticsConfiguration.contains(semanticsPropertyKey4));
                    obtain.setEnabled(InvertMatrixKt.access$enabled(semanticsNode));
                    SemanticsPropertyKey semanticsPropertyKey5 = SemanticsProperties.Focused;
                    obtain.setFocusable(semanticsConfiguration.contains(semanticsPropertyKey5));
                    if (obtain.isFocusable()) {
                        obtain.setFocused(((Boolean) semanticsConfiguration.get(semanticsPropertyKey5)).booleanValue());
                        if (obtain.isFocused()) {
                            accessibilityNodeInfo.addAction(2);
                        } else {
                            accessibilityNodeInfo.addAction(1);
                        }
                    }
                    NodeCoordinator findCoordinatorToGetBounds$ui_release = semanticsNode.findCoordinatorToGetBounds$ui_release();
                    obtain.setVisibleToUser((findCoordinatorToGetBounds$ui_release == null || !findCoordinatorToGetBounds$ui_release.isTransparent()) && !semanticsConfiguration.contains(SemanticsProperties.InvisibleToUser));
                    DurationKt$$ExternalSyntheticCheckNotZero0.m(_BOUNDARY.getOrNull(semanticsConfiguration, SemanticsProperties.LiveRegion));
                    accessibilityNodeInfo.setClickable(false);
                    AccessibilityAction accessibilityAction = (AccessibilityAction) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsActions.OnClick);
                    if (accessibilityAction != null) {
                        boolean areEqual = ResultKt.areEqual(_BOUNDARY.getOrNull(semanticsConfiguration, SemanticsProperties.Selected), Boolean.TRUE);
                        accessibilityNodeInfo.setClickable(!areEqual);
                        if (InvertMatrixKt.access$enabled(semanticsNode) && !areEqual) {
                            accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, 16, accessibilityAction.label, null));
                        }
                    }
                    accessibilityNodeInfo.setLongClickable(false);
                    AccessibilityAction accessibilityAction2 = (AccessibilityAction) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsActions.OnLongClick);
                    if (accessibilityAction2 != null) {
                        accessibilityNodeInfo.setLongClickable(true);
                        if (InvertMatrixKt.access$enabled(semanticsNode)) {
                            accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, 32, accessibilityAction2.label, null));
                        }
                    }
                    AccessibilityAction accessibilityAction3 = (AccessibilityAction) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsActions.CopyText);
                    if (accessibilityAction3 != null) {
                        accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, 16384, accessibilityAction3.label, null));
                    }
                    if (InvertMatrixKt.access$enabled(semanticsNode)) {
                        AccessibilityAction accessibilityAction4 = (AccessibilityAction) _BOUNDARY.getOrNull(semanticsConfiguration, semanticsPropertyKey4);
                        if (accessibilityAction4 != null) {
                            accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, 2097152, accessibilityAction4.label, null));
                        }
                        AccessibilityAction accessibilityAction5 = (AccessibilityAction) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsActions.PerformImeAction);
                        if (accessibilityAction5 != null) {
                            accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, android.R.id.accessibilityActionImeEnter, accessibilityAction5.label, null));
                        }
                        AccessibilityAction accessibilityAction6 = (AccessibilityAction) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsActions.CutText);
                        if (accessibilityAction6 != null) {
                            accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, 65536, accessibilityAction6.label, null));
                        }
                        AccessibilityAction accessibilityAction7 = (AccessibilityAction) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsActions.PasteText);
                        if (accessibilityAction7 != null && obtain.isFocused() && (primaryClipDescription = androidComposeView.m222getClipboardManager().clipboardManager.getPrimaryClipDescription()) != null && primaryClipDescription.hasMimeType("text/*")) {
                            accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, 32768, accessibilityAction7.label, null));
                        }
                    }
                    String iterableTextForAccessibility = AndroidComposeViewAccessibilityDelegateCompat.getIterableTextForAccessibility(semanticsNode);
                    if (iterableTextForAccessibility != null && iterableTextForAccessibility.length() != 0) {
                        obtain.setTextSelection(androidComposeViewAccessibilityDelegateCompat.getAccessibilitySelectionStart(semanticsNode), androidComposeViewAccessibilityDelegateCompat.getAccessibilitySelectionEnd(semanticsNode));
                        AccessibilityAction accessibilityAction8 = (AccessibilityAction) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsActions.SetSelection);
                        accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, 131072, accessibilityAction8 != null ? accessibilityAction8.label : null, null));
                        accessibilityNodeInfo.addAction(256);
                        accessibilityNodeInfo.addAction(512);
                        accessibilityNodeInfo.setMovementGranularities(11);
                        List list2 = (List) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsProperties.ContentDescription);
                        if ((list2 == null || list2.isEmpty()) && semanticsConfiguration.contains(SemanticsActions.GetTextLayoutResult) && ((!semanticsConfiguration.contains(semanticsPropertyKey4) || ResultKt.areEqual(_BOUNDARY.getOrNull(semanticsConfiguration, semanticsPropertyKey5), Boolean.TRUE)) && ((findClosestParentNode = InvertMatrixKt.findClosestParentNode(layoutNode, InspectableValueKt$NoInspectorInfo$1.INSTANCE$15)) == null || ((collapsedSemantics$ui_release = findClosestParentNode.getCollapsedSemantics$ui_release()) != null && ResultKt.areEqual(_BOUNDARY.getOrNull(collapsedSemantics$ui_release, semanticsPropertyKey5), Boolean.TRUE))))) {
                            accessibilityNodeInfo.setMovementGranularities(obtain.getMovementGranularities() | 20);
                        }
                    }
                    ArrayList arrayList = new ArrayList();
                    arrayList.add("androidx.compose.ui.semantics.id");
                    CharSequence text = accessibilityNodeInfoCompat.getText();
                    if (text != null && text.length() != 0 && semanticsConfiguration.contains(SemanticsActions.GetTextLayoutResult)) {
                        arrayList.add("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY");
                    }
                    if (semanticsConfiguration.contains(SemanticsProperties.TestTag)) {
                        arrayList.add("androidx.compose.ui.semantics.testTag");
                    }
                    AccessibilityNodeInfoVerificationHelperMethods.INSTANCE.setAvailableExtraData(obtain, arrayList);
                    ProgressBarRangeInfo progressBarRangeInfo = (ProgressBarRangeInfo) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsProperties.ProgressBarRangeInfo);
                    if (progressBarRangeInfo != null) {
                        SemanticsPropertyKey semanticsPropertyKey6 = SemanticsActions.SetProgress;
                        if (semanticsConfiguration.contains(semanticsPropertyKey6)) {
                            accessibilityNodeInfoCompat.setClassName("android.widget.SeekBar");
                        } else {
                            accessibilityNodeInfoCompat.setClassName("android.widget.ProgressBar");
                        }
                        ProgressBarRangeInfo progressBarRangeInfo2 = ProgressBarRangeInfo.Indeterminate;
                        float f = progressBarRangeInfo.current;
                        ClosedFloatRange closedFloatRange = progressBarRangeInfo.range;
                        if (progressBarRangeInfo != progressBarRangeInfo2) {
                            obtain.setRangeInfo(AccessibilityNodeInfo.RangeInfo.obtain(1, closedFloatRange._start, closedFloatRange._endInclusive, f));
                        }
                        if (semanticsConfiguration.contains(semanticsPropertyKey6) && InvertMatrixKt.access$enabled(semanticsNode)) {
                            float f2 = closedFloatRange._endInclusive;
                            float f3 = closedFloatRange._start;
                            if (f2 < f3) {
                                f2 = f3;
                            }
                            if (f < f2) {
                                accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                            }
                            float f4 = closedFloatRange._endInclusive;
                            if (f3 > f4) {
                                f3 = f4;
                            }
                            if (f > f3) {
                                accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                            }
                        }
                    }
                    Api24Impl.addSetProgressAction(accessibilityNodeInfoCompat, semanticsNode);
                    DurationKt$$ExternalSyntheticCheckNotZero0.m(_BOUNDARY.getOrNull(semanticsNode.getConfig(), SemanticsProperties.CollectionInfo));
                    ArrayList arrayList2 = new ArrayList();
                    if (_BOUNDARY.getOrNull(semanticsNode.getConfig(), SemanticsProperties.SelectableGroup) != null) {
                        List children2 = semanticsNode.getChildren(false, true);
                        int size2 = children2.size();
                        for (int i9 = 0; i9 < size2; i9++) {
                            SemanticsNode semanticsNode4 = (SemanticsNode) children2.get(i9);
                            if (semanticsNode4.getConfig().contains(SemanticsProperties.Selected)) {
                                arrayList2.add(semanticsNode4);
                            }
                        }
                    }
                    if (!arrayList2.isEmpty()) {
                        boolean calculateIfHorizontallyStacked = _BOUNDARY.calculateIfHorizontallyStacked(arrayList2);
                        accessibilityNodeInfo.setCollectionInfo(AccessibilityNodeInfo.CollectionInfo.obtain(calculateIfHorizontallyStacked ? 1 : arrayList2.size(), calculateIfHorizontallyStacked ? arrayList2.size() : 1, false, 0));
                    }
                    DurationKt$$ExternalSyntheticCheckNotZero0.m(_BOUNDARY.getOrNull(semanticsNode.getConfig(), SemanticsProperties.CollectionItemInfo));
                    SemanticsNode parent2 = semanticsNode.getParent();
                    if (parent2 != null && _BOUNDARY.getOrNull(parent2.getConfig(), SemanticsProperties.SelectableGroup) != null) {
                        DurationKt$$ExternalSyntheticCheckNotZero0.m(_BOUNDARY.getOrNull(parent2.getConfig(), SemanticsProperties.CollectionInfo));
                        if (semanticsNode.getConfig().contains(SemanticsProperties.Selected)) {
                            ArrayList arrayList3 = new ArrayList();
                            List children3 = parent2.getChildren(false, true);
                            int size3 = children3.size();
                            int i10 = 0;
                            int i11 = 0;
                            while (i10 < size3) {
                                SemanticsNode semanticsNode5 = (SemanticsNode) children3.get(i10);
                                List list3 = children3;
                                if (semanticsNode5.getConfig().contains(SemanticsProperties.Selected)) {
                                    arrayList3.add(semanticsNode5);
                                    if (semanticsNode5.layoutNode.getPlaceOrder$ui_release() < layoutNode.getPlaceOrder$ui_release()) {
                                        i11++;
                                    }
                                }
                                i10++;
                                children3 = list3;
                            }
                            if (!arrayList3.isEmpty()) {
                                boolean calculateIfHorizontallyStacked2 = _BOUNDARY.calculateIfHorizontallyStacked(arrayList3);
                                int i12 = calculateIfHorizontallyStacked2 ? 0 : i11;
                                int i13 = calculateIfHorizontallyStacked2 ? i11 : 0;
                                SemanticsConfiguration config = semanticsNode.getConfig();
                                SemanticsPropertyKey semanticsPropertyKey7 = SemanticsProperties.Selected;
                                config.getClass();
                                ResultKt.checkNotNullParameter(semanticsPropertyKey7, "key");
                                Object obj = config.props.get(semanticsPropertyKey7);
                                if (obj == null) {
                                    obj = Boolean.FALSE;
                                }
                                accessibilityNodeInfo.setCollectionItemInfo(AccessibilityNodeInfo.CollectionItemInfo.obtain(i12, 1, i13, 1, false, ((Boolean) obj).booleanValue()));
                            }
                        }
                    }
                    DurationKt$$ExternalSyntheticCheckNotZero0.m(_BOUNDARY.getOrNull(semanticsConfiguration, SemanticsProperties.HorizontalScrollAxisRange));
                    DurationKt$$ExternalSyntheticCheckNotZero0.m(_BOUNDARY.getOrNull(semanticsConfiguration, SemanticsProperties.VerticalScrollAxisRange));
                    if (Build.VERSION.SDK_INT >= 29) {
                        Api29Impl.addPageActions(accessibilityNodeInfoCompat, semanticsNode);
                    }
                    accessibilityNodeInfo.setPaneTitle((CharSequence) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsProperties.PaneTitle));
                    if (InvertMatrixKt.access$enabled(semanticsNode)) {
                        AccessibilityAction accessibilityAction9 = (AccessibilityAction) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsActions.Expand);
                        if (accessibilityAction9 != null) {
                            accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, 262144, accessibilityAction9.label, null));
                        }
                        AccessibilityAction accessibilityAction10 = (AccessibilityAction) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsActions.Collapse);
                        if (accessibilityAction10 != null) {
                            accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, 524288, accessibilityAction10.label, null));
                        }
                        AccessibilityAction accessibilityAction11 = (AccessibilityAction) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsActions.Dismiss);
                        if (accessibilityAction11 != null) {
                            accessibilityNodeInfoCompat.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(null, 1048576, accessibilityAction11.label, null));
                        }
                        SemanticsPropertyKey semanticsPropertyKey8 = SemanticsActions.CustomActions;
                        if (semanticsConfiguration.contains(semanticsPropertyKey8)) {
                            List list4 = (List) semanticsConfiguration.get(semanticsPropertyKey8);
                            if (list4.size() >= 32) {
                                throw new IllegalStateException("Can't have more than 32 custom actions for one widget");
                            }
                            SparseArrayCompat sparseArrayCompat = new SparseArrayCompat();
                            LinkedHashMap linkedHashMap = new LinkedHashMap();
                            SparseArrayCompat sparseArrayCompat2 = androidComposeViewAccessibilityDelegateCompat.labelToActionId;
                            if (ContainerHelpers.binarySearch(sparseArrayCompat2.mKeys, sparseArrayCompat2.mSize, i) >= 0) {
                                Map map = (Map) sparseArrayCompat2.get(i);
                                int[] iArr = AndroidComposeViewAccessibilityDelegateCompat.AccessibilityActionsResourceIds;
                                ArrayList arrayList4 = new ArrayList(32);
                                for (int i14 = 0; i14 < 32; i14++) {
                                    arrayList4.add(Integer.valueOf(iArr[i14]));
                                }
                                ArrayList arrayList5 = new ArrayList();
                                if (list4.size() > 0) {
                                    DurationKt$$ExternalSyntheticCheckNotZero0.m(list4.get(0));
                                    ResultKt.checkNotNull(map);
                                    throw null;
                                }
                                if (arrayList5.size() > 0) {
                                    DurationKt$$ExternalSyntheticCheckNotZero0.m(arrayList5.get(0));
                                    ((Number) arrayList4.get(0)).intValue();
                                    throw null;
                                }
                            } else if (list4.size() > 0) {
                                DurationKt$$ExternalSyntheticCheckNotZero0.m(list4.get(0));
                                throw null;
                            }
                            androidComposeViewAccessibilityDelegateCompat.actionIdToLabel.put(i, sparseArrayCompat);
                            sparseArrayCompat2.put(i, linkedHashMap);
                        }
                    }
                    accessibilityNodeInfo.setScreenReaderFocusable(androidComposeViewAccessibilityDelegateCompat.isScreenReaderFocusable(semanticsNode));
                    Integer num = (Integer) androidComposeViewAccessibilityDelegateCompat.idToBeforeMap.get(Integer.valueOf(i));
                    if (num != null) {
                        InvertMatrixKt.semanticsIdToView(androidComposeView.getAndroidViewsHandler$ui_release(), num.intValue());
                        obtain.setTraversalBefore(androidComposeView, num.intValue());
                        androidComposeViewAccessibilityDelegateCompat.addExtraDataToAccessibilityNodeInfoHelper(i, obtain, androidComposeViewAccessibilityDelegateCompat.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL, null);
                    }
                    Integer num2 = (Integer) androidComposeViewAccessibilityDelegateCompat.idToAfterMap.get(Integer.valueOf(i));
                    if (num2 != null) {
                        InvertMatrixKt.semanticsIdToView(androidComposeView.getAndroidViewsHandler$ui_release(), num2.intValue());
                        obtain.setTraversalAfter(androidComposeView, num2.intValue());
                        androidComposeViewAccessibilityDelegateCompat.addExtraDataToAccessibilityNodeInfoHelper(i, obtain, androidComposeViewAccessibilityDelegateCompat.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL, null);
                    }
                    return accessibilityNodeInfo;
                }
            }
            return null;
        }

        /* JADX WARN: Code restructure failed: missing block: B:333:0x04ca, code lost:
        
            if (r0 != 16) goto L756;
         */
        /* JADX WARN: Removed duplicated region for block: B:109:0x017f  */
        /* JADX WARN: Removed duplicated region for block: B:121:0x01c0  */
        /* JADX WARN: Removed duplicated region for block: B:124:0x01f8  */
        /* JADX WARN: Removed duplicated region for block: B:129:0x020e  */
        /* JADX WARN: Removed duplicated region for block: B:132:0x0225  */
        /* JADX WARN: Removed duplicated region for block: B:143:0x0207  */
        /* JADX WARN: Removed duplicated region for block: B:144:0x01c3  */
        /* JADX WARN: Removed duplicated region for block: B:147:? A[RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:99:0x0163 A[ADDED_TO_REGION] */
        /* JADX WARN: Type inference failed for: r7v26, types: [androidx.compose.ui.platform.AccessibilityIterators$AbstractTextSegmentIterator, androidx.compose.ui.platform.AccessibilityIterators$PageTextSegmentIterator] */
        /* JADX WARN: Type inference failed for: r7v29, types: [androidx.compose.ui.platform.AccessibilityIterators$AbstractTextSegmentIterator, androidx.compose.ui.platform.AccessibilityIterators$LineTextSegmentIterator] */
        /* JADX WARN: Type inference failed for: r7v32, types: [androidx.compose.ui.platform.AccessibilityIterators$AbstractTextSegmentIterator, androidx.compose.ui.platform.AccessibilityIterators$ParagraphTextSegmentIterator] */
        /* JADX WARN: Type inference failed for: r9v11, types: [androidx.compose.ui.platform.AccessibilityIterators$WordTextSegmentIterator, androidx.compose.ui.platform.AccessibilityIterators$AbstractTextSegmentIterator] */
        /* JADX WARN: Type inference failed for: r9v7, types: [androidx.compose.ui.platform.AccessibilityIterators$AbstractTextSegmentIterator, androidx.compose.ui.platform.AccessibilityIterators$CharacterTextSegmentIterator] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:116:0x0160 -> B:73:0x0161). Please report as a decompilation issue!!! */
        @Override // android.view.accessibility.AccessibilityNodeProvider
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final boolean performAction(int r19, int r20, android.os.Bundle r21) {
            /*
                r18 = this;
                r0 = r19
                r1 = r20
                r2 = r21
                r3 = r18
                androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r4 = androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.this
                java.util.Map r5 = r4.getCurrentSemanticsNodes$ui_release()
                java.lang.Integer r6 = java.lang.Integer.valueOf(r19)
                java.lang.Object r5 = r5.get(r6)
                androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds r5 = (androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds) r5
                r6 = 0
                if (r5 == 0) goto L658
                androidx.compose.ui.semantics.SemanticsNode r5 = r5.semanticsNode
                if (r5 != 0) goto L21
                goto L658
            L21:
                r7 = 65536(0x10000, float:9.18355E-41)
                r15 = 1
                r8 = 12
                r9 = 0
                r10 = 64
                r11 = -2147483648(0xffffffff80000000, float:-0.0)
                androidx.compose.ui.platform.AndroidComposeView r12 = r4.view
                if (r1 == r10) goto L633
                r10 = 128(0x80, float:1.794E-43)
                if (r1 == r10) goto L625
                r7 = 2
                r10 = 512(0x200, float:7.175E-43)
                r11 = 256(0x100, float:3.59E-43)
                int r14 = r5.id
                androidx.compose.ui.semantics.SemanticsConfiguration r13 = r5.unmergedConfig
                if (r1 == r11) goto L477
                if (r1 == r10) goto L477
                r10 = 16384(0x4000, float:2.2959E-41)
                if (r1 == r10) goto L45b
                r10 = 131072(0x20000, float:1.83671E-40)
                if (r1 == r10) goto L433
                boolean r10 = androidx.compose.ui.platform.InvertMatrixKt.access$enabled(r5)
                if (r10 != 0) goto L50
                goto L658
            L50:
                if (r1 == r15) goto L417
                if (r1 == r7) goto L3fd
                androidx.compose.ui.node.LayoutNode r7 = r5.layoutNode
                switch(r1) {
                    case 16: goto L3da;
                    case 32: goto L3be;
                    case 4096: goto L309;
                    case 8192: goto L309;
                    case 32768: goto L2ed;
                    case 65536: goto L2d1;
                    case 262144: goto L2b5;
                    case 524288: goto L299;
                    case 1048576: goto L27d;
                    case 2097152: goto L250;
                    case 16908342: goto L14b;
                    case 16908349: goto L11b;
                    case 16908372: goto Lff;
                    default: goto L59;
                }
            L59:
                switch(r1) {
                    case 16908344: goto L309;
                    case 16908345: goto L309;
                    case 16908346: goto L309;
                    case 16908347: goto L309;
                    default: goto L5c;
                }
            L5c:
                switch(r1) {
                    case 16908358: goto Le3;
                    case 16908359: goto Lc7;
                    case 16908360: goto Lab;
                    case 16908361: goto L8f;
                    default: goto L5f;
                }
            L5f:
                androidx.collection.SparseArrayCompat r2 = r4.actionIdToLabel
                java.lang.Object r0 = r2.get(r0)
                androidx.collection.SparseArrayCompat r0 = (androidx.collection.SparseArrayCompat) r0
                if (r0 == 0) goto L658
                java.lang.Object r0 = r0.get(r1)
                java.lang.CharSequence r0 = (java.lang.CharSequence) r0
                if (r0 != 0) goto L73
                goto L658
            L73:
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsActions.CustomActions
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                java.util.List r0 = (java.util.List) r0
                if (r0 != 0) goto L7f
                goto L658
            L7f:
                int r1 = r0.size()
                if (r1 > 0) goto L87
                goto L658
            L87:
                java.lang.Object r0 = r0.get(r6)
                kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0.m(r0)
                throw r9
            L8f:
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsActions.PageRight
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                androidx.compose.ui.semantics.AccessibilityAction r0 = (androidx.compose.ui.semantics.AccessibilityAction) r0
                if (r0 == 0) goto L658
                kotlin.Function r0 = r0.action
                kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
                if (r0 == 0) goto L658
                java.lang.Object r0 = r0.invoke()
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r6 = r0.booleanValue()
                goto L658
            Lab:
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsActions.PageLeft
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                androidx.compose.ui.semantics.AccessibilityAction r0 = (androidx.compose.ui.semantics.AccessibilityAction) r0
                if (r0 == 0) goto L658
                kotlin.Function r0 = r0.action
                kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
                if (r0 == 0) goto L658
                java.lang.Object r0 = r0.invoke()
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r6 = r0.booleanValue()
                goto L658
            Lc7:
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsActions.PageDown
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                androidx.compose.ui.semantics.AccessibilityAction r0 = (androidx.compose.ui.semantics.AccessibilityAction) r0
                if (r0 == 0) goto L658
                kotlin.Function r0 = r0.action
                kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
                if (r0 == 0) goto L658
                java.lang.Object r0 = r0.invoke()
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r6 = r0.booleanValue()
                goto L658
            Le3:
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsActions.PageUp
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                androidx.compose.ui.semantics.AccessibilityAction r0 = (androidx.compose.ui.semantics.AccessibilityAction) r0
                if (r0 == 0) goto L658
                kotlin.Function r0 = r0.action
                kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
                if (r0 == 0) goto L658
                java.lang.Object r0 = r0.invoke()
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r6 = r0.booleanValue()
                goto L658
            Lff:
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsActions.PerformImeAction
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                androidx.compose.ui.semantics.AccessibilityAction r0 = (androidx.compose.ui.semantics.AccessibilityAction) r0
                if (r0 == 0) goto L658
                kotlin.Function r0 = r0.action
                kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
                if (r0 == 0) goto L658
                java.lang.Object r0 = r0.invoke()
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r6 = r0.booleanValue()
                goto L658
            L11b:
                if (r2 == 0) goto L658
                java.lang.String r0 = "android.view.accessibility.action.ARGUMENT_PROGRESS_VALUE"
                boolean r1 = r2.containsKey(r0)
                if (r1 != 0) goto L127
                goto L658
            L127:
                androidx.compose.ui.semantics.SemanticsPropertyKey r1 = androidx.compose.ui.semantics.SemanticsActions.SetProgress
                java.lang.Object r1 = _COROUTINE._BOUNDARY.getOrNull(r13, r1)
                androidx.compose.ui.semantics.AccessibilityAction r1 = (androidx.compose.ui.semantics.AccessibilityAction) r1
                if (r1 == 0) goto L658
                kotlin.Function r1 = r1.action
                kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                if (r1 == 0) goto L658
                float r0 = r2.getFloat(r0)
                java.lang.Float r0 = java.lang.Float.valueOf(r0)
                java.lang.Object r0 = r1.invoke(r0)
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r6 = r0.booleanValue()
                goto L658
            L14b:
                androidx.compose.ui.semantics.SemanticsNode r0 = r5.getParent()
                if (r0 == 0) goto L160
                androidx.compose.ui.semantics.SemanticsConfiguration r1 = r0.getConfig()
                if (r1 == 0) goto L160
                androidx.compose.ui.semantics.SemanticsPropertyKey r2 = androidx.compose.ui.semantics.SemanticsActions.ScrollBy
                java.lang.Object r1 = _COROUTINE._BOUNDARY.getOrNull(r1, r2)
                androidx.compose.ui.semantics.AccessibilityAction r1 = (androidx.compose.ui.semantics.AccessibilityAction) r1
                goto L161
            L160:
                r1 = r9
            L161:
                if (r0 == 0) goto L17b
                if (r1 == 0) goto L166
                goto L17b
            L166:
                androidx.compose.ui.semantics.SemanticsNode r0 = r0.getParent()
                if (r0 == 0) goto L160
                androidx.compose.ui.semantics.SemanticsConfiguration r1 = r0.getConfig()
                if (r1 == 0) goto L160
                androidx.compose.ui.semantics.SemanticsPropertyKey r2 = androidx.compose.ui.semantics.SemanticsActions.ScrollBy
                java.lang.Object r1 = _COROUTINE._BOUNDARY.getOrNull(r1, r2)
                androidx.compose.ui.semantics.AccessibilityAction r1 = (androidx.compose.ui.semantics.AccessibilityAction) r1
                goto L161
            L17b:
                if (r0 != 0) goto L17f
                goto L658
            L17f:
                androidx.compose.ui.node.LayoutNode r2 = r0.layoutNode
                androidx.compose.ui.node.NodeChain r4 = r2.nodes
                androidx.compose.ui.node.InnerNodeCoordinator r4 = r4.innerCoordinator
                androidx.compose.ui.geometry.Rect r4 = _COROUTINE._BOUNDARY.boundsInParent(r4)
                androidx.compose.ui.node.NodeChain r2 = r2.nodes
                androidx.compose.ui.node.InnerNodeCoordinator r2 = r2.innerCoordinator
                androidx.compose.ui.layout.LayoutCoordinates r2 = r2.getParentLayoutCoordinates()
                if (r2 == 0) goto L19c
                long r10 = androidx.compose.ui.geometry.Offset.Zero
                androidx.compose.ui.node.NodeCoordinator r2 = (androidx.compose.ui.node.NodeCoordinator) r2
                long r10 = r2.mo161localToRootMKHz9U(r10)
                goto L19e
            L19c:
                long r10 = androidx.compose.ui.geometry.Offset.Zero
            L19e:
                androidx.compose.ui.geometry.Rect r2 = r4.m82translatek4lQ0M(r10)
                androidx.compose.ui.node.NodeCoordinator r4 = r5.findCoordinatorToGetBounds$ui_release()
                if (r4 == 0) goto L1b8
                boolean r8 = r4.isAttached()
                if (r8 == 0) goto L1af
                r9 = r4
            L1af:
                if (r9 == 0) goto L1b8
                long r10 = androidx.compose.ui.geometry.Offset.Zero
                long r8 = r9.mo161localToRootMKHz9U(r10)
                goto L1ba
            L1b8:
                long r8 = androidx.compose.ui.geometry.Offset.Zero
            L1ba:
                androidx.compose.ui.node.NodeCoordinator r4 = r5.findCoordinatorToGetBounds$ui_release()
                if (r4 == 0) goto L1c3
                long r4 = r4.measuredSize
                goto L1c5
            L1c3:
                r4 = 0
            L1c5:
                long r4 = kotlin.ResultKt.m300toSizeozmzZPI(r4)
                androidx.compose.ui.geometry.Rect r4 = _COROUTINE._BOUNDARY.m4Recttz77jQw(r8, r4)
                androidx.compose.ui.semantics.SemanticsPropertyKey r5 = androidx.compose.ui.semantics.SemanticsProperties.HorizontalScrollAxisRange
                androidx.compose.ui.semantics.SemanticsConfiguration r0 = r0.unmergedConfig
                java.lang.Object r5 = _COROUTINE._BOUNDARY.getOrNull(r0, r5)
                kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0.m(r5)
                androidx.compose.ui.semantics.SemanticsPropertyKey r5 = androidx.compose.ui.semantics.SemanticsProperties.VerticalScrollAxisRange
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r0, r5)
                kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0.m(r0)
                float r0 = r4.left
                float r5 = r2.left
                float r0 = r0 - r5
                float r5 = r4.right
                float r8 = r2.right
                float r5 = r5 - r8
                float r8 = java.lang.Math.signum(r0)
                float r9 = java.lang.Math.signum(r5)
                int r8 = (r8 > r9 ? 1 : (r8 == r9 ? 0 : -1))
                r9 = 0
                if (r8 != 0) goto L207
                float r8 = java.lang.Math.abs(r0)
                float r10 = java.lang.Math.abs(r5)
                int r8 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1))
                if (r8 >= 0) goto L205
                goto L208
            L205:
                r0 = r5
                goto L208
            L207:
                r0 = r9
            L208:
                androidx.compose.ui.unit.LayoutDirection r5 = r7.layoutDirection
                androidx.compose.ui.unit.LayoutDirection r7 = androidx.compose.ui.unit.LayoutDirection.Rtl
                if (r5 != r7) goto L20f
                float r0 = -r0
            L20f:
                float r5 = r4.top
                float r7 = r2.top
                float r5 = r5 - r7
                float r4 = r4.bottom
                float r2 = r2.bottom
                float r4 = r4 - r2
                float r2 = java.lang.Math.signum(r5)
                float r7 = java.lang.Math.signum(r4)
                int r2 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
                if (r2 != 0) goto L234
                float r2 = java.lang.Math.abs(r5)
                float r7 = java.lang.Math.abs(r4)
                int r2 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
                if (r2 >= 0) goto L233
                r9 = r5
                goto L234
            L233:
                r9 = r4
            L234:
                if (r1 == 0) goto L658
                kotlin.Function r1 = r1.action
                kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
                if (r1 == 0) goto L658
                java.lang.Float r0 = java.lang.Float.valueOf(r0)
                java.lang.Float r2 = java.lang.Float.valueOf(r9)
                java.lang.Object r0 = r1.invoke(r0, r2)
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r6 = r0.booleanValue()
                goto L658
            L250:
                if (r2 == 0) goto L258
                java.lang.String r0 = "ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE"
                java.lang.String r9 = r2.getString(r0)
            L258:
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsActions.SetText
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                androidx.compose.ui.semantics.AccessibilityAction r0 = (androidx.compose.ui.semantics.AccessibilityAction) r0
                if (r0 == 0) goto L658
                kotlin.Function r0 = r0.action
                kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0
                if (r0 == 0) goto L658
                androidx.compose.ui.text.AnnotatedString r1 = new androidx.compose.ui.text.AnnotatedString
                if (r9 != 0) goto L26e
                java.lang.String r9 = ""
            L26e:
                r1.<init>(r9)
                java.lang.Object r0 = r0.invoke(r1)
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r6 = r0.booleanValue()
                goto L658
            L27d:
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsActions.Dismiss
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                androidx.compose.ui.semantics.AccessibilityAction r0 = (androidx.compose.ui.semantics.AccessibilityAction) r0
                if (r0 == 0) goto L658
                kotlin.Function r0 = r0.action
                kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
                if (r0 == 0) goto L658
                java.lang.Object r0 = r0.invoke()
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r6 = r0.booleanValue()
                goto L658
            L299:
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsActions.Collapse
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                androidx.compose.ui.semantics.AccessibilityAction r0 = (androidx.compose.ui.semantics.AccessibilityAction) r0
                if (r0 == 0) goto L658
                kotlin.Function r0 = r0.action
                kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
                if (r0 == 0) goto L658
                java.lang.Object r0 = r0.invoke()
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r6 = r0.booleanValue()
                goto L658
            L2b5:
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsActions.Expand
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                androidx.compose.ui.semantics.AccessibilityAction r0 = (androidx.compose.ui.semantics.AccessibilityAction) r0
                if (r0 == 0) goto L658
                kotlin.Function r0 = r0.action
                kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
                if (r0 == 0) goto L658
                java.lang.Object r0 = r0.invoke()
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r6 = r0.booleanValue()
                goto L658
            L2d1:
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsActions.CutText
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                androidx.compose.ui.semantics.AccessibilityAction r0 = (androidx.compose.ui.semantics.AccessibilityAction) r0
                if (r0 == 0) goto L658
                kotlin.Function r0 = r0.action
                kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
                if (r0 == 0) goto L658
                java.lang.Object r0 = r0.invoke()
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r6 = r0.booleanValue()
                goto L658
            L2ed:
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsActions.PasteText
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                androidx.compose.ui.semantics.AccessibilityAction r0 = (androidx.compose.ui.semantics.AccessibilityAction) r0
                if (r0 == 0) goto L658
                kotlin.Function r0 = r0.action
                kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
                if (r0 == 0) goto L658
                java.lang.Object r0 = r0.invoke()
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r6 = r0.booleanValue()
                goto L658
            L309:
                r0 = 4096(0x1000, float:5.74E-42)
                if (r1 != r0) goto L30f
                r0 = r15
                goto L310
            L30f:
                r0 = r6
            L310:
                r2 = 8192(0x2000, float:1.14794E-41)
                if (r1 != r2) goto L316
                r2 = r15
                goto L317
            L316:
                r2 = r6
            L317:
                r4 = 16908345(0x1020039, float:2.387739E-38)
                if (r1 != r4) goto L31e
                r4 = r15
                goto L31f
            L31e:
                r4 = r6
            L31f:
                r5 = 16908347(0x102003b, float:2.3877394E-38)
                if (r1 != r5) goto L326
                r5 = r15
                goto L327
            L326:
                r5 = r6
            L327:
                r8 = 16908344(0x1020038, float:2.3877386E-38)
                if (r1 != r8) goto L32e
                r8 = r15
                goto L32f
            L32e:
                r8 = r6
            L32f:
                r9 = 16908346(0x102003a, float:2.3877392E-38)
                if (r1 != r9) goto L336
                r1 = r15
                goto L337
            L336:
                r1 = r6
            L337:
                if (r0 != 0) goto L33b
                if (r2 == 0) goto L38b
            L33b:
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsProperties.ProgressBarRangeInfo
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                androidx.compose.ui.semantics.ProgressBarRangeInfo r0 = (androidx.compose.ui.semantics.ProgressBarRangeInfo) r0
                androidx.compose.ui.semantics.SemanticsPropertyKey r1 = androidx.compose.ui.semantics.SemanticsActions.SetProgress
                java.lang.Object r1 = _COROUTINE._BOUNDARY.getOrNull(r13, r1)
                androidx.compose.ui.semantics.AccessibilityAction r1 = (androidx.compose.ui.semantics.AccessibilityAction) r1
                if (r0 == 0) goto L38b
                if (r1 == 0) goto L38b
                kotlin.ranges.ClosedFloatRange r4 = r0.range
                float r5 = r4._endInclusive
                float r4 = r4._start
                int r7 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1))
                if (r7 >= 0) goto L35b
                r7 = r4
                goto L35c
            L35b:
                r7 = r5
            L35c:
                int r8 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
                if (r8 <= 0) goto L361
                goto L362
            L361:
                r5 = r4
            L362:
                int r4 = r0.steps
                if (r4 <= 0) goto L36b
                float r7 = r7 - r5
                int r4 = r4 + r15
            L368:
                float r4 = (float) r4
                float r7 = r7 / r4
                goto L36f
            L36b:
                float r7 = r7 - r5
                r4 = 20
                goto L368
            L36f:
                if (r2 == 0) goto L372
                float r7 = -r7
            L372:
                kotlin.Function r1 = r1.action
                kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                if (r1 == 0) goto L658
                float r0 = r0.current
                float r0 = r0 + r7
                java.lang.Float r0 = java.lang.Float.valueOf(r0)
                java.lang.Object r0 = r1.invoke(r0)
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r6 = r0.booleanValue()
                goto L658
            L38b:
                androidx.compose.ui.node.NodeChain r0 = r7.nodes
                androidx.compose.ui.node.InnerNodeCoordinator r0 = r0.innerCoordinator
                androidx.compose.ui.geometry.Rect r0 = _COROUTINE._BOUNDARY.boundsInParent(r0)
                float r1 = r0.getWidth()
                float r0 = r0.getHeight()
                _COROUTINE._BOUNDARY.Size(r1, r0)
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsActions.ScrollBy
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                androidx.compose.ui.semantics.AccessibilityAction r0 = (androidx.compose.ui.semantics.AccessibilityAction) r0
                if (r0 != 0) goto L3aa
                goto L658
            L3aa:
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsProperties.HorizontalScrollAxisRange
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0.m(r0)
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsProperties.VerticalScrollAxisRange
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0.m(r0)
                goto L658
            L3be:
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsActions.OnLongClick
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                androidx.compose.ui.semantics.AccessibilityAction r0 = (androidx.compose.ui.semantics.AccessibilityAction) r0
                if (r0 == 0) goto L658
                kotlin.Function r0 = r0.action
                kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
                if (r0 == 0) goto L658
                java.lang.Object r0 = r0.invoke()
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r6 = r0.booleanValue()
                goto L658
            L3da:
                androidx.compose.ui.semantics.SemanticsPropertyKey r1 = androidx.compose.ui.semantics.SemanticsActions.OnClick
                java.lang.Object r1 = _COROUTINE._BOUNDARY.getOrNull(r13, r1)
                androidx.compose.ui.semantics.AccessibilityAction r1 = (androidx.compose.ui.semantics.AccessibilityAction) r1
                if (r1 == 0) goto L3f1
                kotlin.Function r1 = r1.action
                kotlin.jvm.functions.Function0 r1 = (kotlin.jvm.functions.Function0) r1
                if (r1 == 0) goto L3f1
                java.lang.Object r1 = r1.invoke()
                java.lang.Boolean r1 = (java.lang.Boolean) r1
                goto L3f2
            L3f1:
                r1 = r9
            L3f2:
                androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(r4, r0, r15, r9, r8)
                if (r1 == 0) goto L658
                boolean r6 = r1.booleanValue()
                goto L658
            L3fd:
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsProperties.Focused
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                java.lang.Boolean r1 = java.lang.Boolean.TRUE
                boolean r0 = kotlin.ResultKt.areEqual(r0, r1)
                if (r0 == 0) goto L658
                androidx.compose.ui.focus.FocusOwner r0 = r12.getFocusOwner()
                androidx.compose.ui.focus.FocusOwnerImpl r0 = (androidx.compose.ui.focus.FocusOwnerImpl) r0
                r0.clearFocus(r6, r15)
            L414:
                r6 = r15
                goto L658
            L417:
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsActions.RequestFocus
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                androidx.compose.ui.semantics.AccessibilityAction r0 = (androidx.compose.ui.semantics.AccessibilityAction) r0
                if (r0 == 0) goto L658
                kotlin.Function r0 = r0.action
                kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
                if (r0 == 0) goto L658
                java.lang.Object r0 = r0.invoke()
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r6 = r0.booleanValue()
                goto L658
            L433:
                if (r2 == 0) goto L43f
                java.lang.String r0 = "ACTION_ARGUMENT_SELECTION_START_INT"
                r1 = -1
                int r16 = r2.getInt(r0, r1)
                r0 = r16
                goto L441
            L43f:
                r1 = -1
                r0 = r1
            L441:
                if (r2 == 0) goto L44a
                java.lang.String r7 = "ACTION_ARGUMENT_SELECTION_END_INT"
                int r13 = r2.getInt(r7, r1)
                goto L44b
            L44a:
                r13 = -1
            L44b:
                boolean r0 = r4.setAccessibilitySelection(r5, r0, r13, r6)
                if (r0 == 0) goto L458
                int r1 = r4.semanticsNodeIdToAccessibilityVirtualNodeId(r14)
                androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(r4, r1, r6, r9, r8)
            L458:
                r6 = r0
                goto L658
            L45b:
                androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsActions.CopyText
                java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r13, r0)
                androidx.compose.ui.semantics.AccessibilityAction r0 = (androidx.compose.ui.semantics.AccessibilityAction) r0
                if (r0 == 0) goto L658
                kotlin.Function r0 = r0.action
                kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
                if (r0 == 0) goto L658
                java.lang.Object r0 = r0.invoke()
                java.lang.Boolean r0 = (java.lang.Boolean) r0
                boolean r6 = r0.booleanValue()
                goto L658
            L477:
                if (r2 == 0) goto L658
                java.lang.String r0 = "ACTION_ARGUMENT_MOVEMENT_GRANULARITY_INT"
                int r0 = r2.getInt(r0)
                java.lang.String r8 = "ACTION_ARGUMENT_EXTEND_SELECTION_BOOLEAN"
                boolean r2 = r2.getBoolean(r8)
                if (r1 != r11) goto L489
                r1 = r15
                goto L48a
            L489:
                r1 = r6
            L48a:
                java.lang.Integer r8 = r4.previousTraversedNode
                if (r8 != 0) goto L490
            L48e:
                r8 = -1
                goto L497
            L490:
                int r8 = r8.intValue()
                if (r14 == r8) goto L49f
                goto L48e
            L497:
                r4.accessibilityCursorPosition = r8
                java.lang.Integer r8 = java.lang.Integer.valueOf(r14)
                r4.previousTraversedNode = r8
            L49f:
                java.lang.String r8 = androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.getIterableTextForAccessibility(r5)
                if (r8 == 0) goto L658
                int r14 = r8.length()
                if (r14 != 0) goto L4ad
                goto L658
            L4ad:
                java.lang.String r14 = androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.getIterableTextForAccessibility(r5)
                if (r14 == 0) goto L5bb
                int r17 = r14.length()
                if (r17 != 0) goto L4bb
                goto L5bb
            L4bb:
                java.lang.String r10 = "view.context.resources.configuration.locale"
                if (r0 == r15) goto L588
                if (r0 == r7) goto L555
                r7 = 4
                if (r0 == r7) goto L4e5
                r10 = 8
                if (r0 == r10) goto L4ce
                r10 = 16
                if (r0 == r10) goto L4e5
                goto L5bb
            L4ce:
                androidx.compose.ui.platform.AccessibilityIterators$ParagraphTextSegmentIterator r7 = androidx.compose.ui.platform.AccessibilityIterators$ParagraphTextSegmentIterator.instance
                if (r7 != 0) goto L4d9
                androidx.compose.ui.platform.AccessibilityIterators$ParagraphTextSegmentIterator r7 = new androidx.compose.ui.platform.AccessibilityIterators$ParagraphTextSegmentIterator
                r7.<init>()
                androidx.compose.ui.platform.AccessibilityIterators$ParagraphTextSegmentIterator.instance = r7
            L4d9:
                androidx.compose.ui.platform.AccessibilityIterators$ParagraphTextSegmentIterator r7 = androidx.compose.ui.platform.AccessibilityIterators$ParagraphTextSegmentIterator.instance
                java.lang.String r9 = "null cannot be cast to non-null type androidx.compose.ui.platform.AccessibilityIterators.ParagraphTextSegmentIterator"
                kotlin.ResultKt.checkNotNull(r7, r9)
                r7.text = r14
            L4e2:
                r9 = r7
                goto L5bb
            L4e5:
                androidx.compose.ui.semantics.SemanticsPropertyKey r10 = androidx.compose.ui.semantics.SemanticsActions.GetTextLayoutResult
                boolean r12 = r13.contains(r10)
                if (r12 != 0) goto L4ef
                goto L5bb
            L4ef:
                java.util.ArrayList r12 = new java.util.ArrayList
                r12.<init>()
                java.lang.Object r10 = r13.get(r10)
                androidx.compose.ui.semantics.AccessibilityAction r10 = (androidx.compose.ui.semantics.AccessibilityAction) r10
                kotlin.Function r10 = r10.action
                kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
                if (r10 == 0) goto L507
                java.lang.Object r10 = r10.invoke(r12)
                java.lang.Boolean r10 = (java.lang.Boolean) r10
                goto L508
            L507:
                r10 = r9
            L508:
                java.lang.Boolean r11 = java.lang.Boolean.TRUE
                boolean r10 = kotlin.ResultKt.areEqual(r10, r11)
                if (r10 == 0) goto L5bb
                java.lang.Object r9 = r12.get(r6)
                androidx.compose.ui.text.TextLayoutResult r9 = (androidx.compose.ui.text.TextLayoutResult) r9
                java.lang.String r10 = "layoutResult"
                if (r0 != r7) goto L534
                androidx.compose.ui.platform.AccessibilityIterators$LineTextSegmentIterator r7 = androidx.compose.ui.platform.AccessibilityIterators$LineTextSegmentIterator.lineInstance
                if (r7 != 0) goto L525
                androidx.compose.ui.platform.AccessibilityIterators$LineTextSegmentIterator r7 = new androidx.compose.ui.platform.AccessibilityIterators$LineTextSegmentIterator
                r7.<init>()
                androidx.compose.ui.platform.AccessibilityIterators$LineTextSegmentIterator.lineInstance = r7
            L525:
                androidx.compose.ui.platform.AccessibilityIterators$LineTextSegmentIterator r7 = androidx.compose.ui.platform.AccessibilityIterators$LineTextSegmentIterator.lineInstance
                java.lang.String r11 = "null cannot be cast to non-null type androidx.compose.ui.platform.AccessibilityIterators.LineTextSegmentIterator"
                kotlin.ResultKt.checkNotNull(r7, r11)
                kotlin.ResultKt.checkNotNullParameter(r9, r10)
                r7.text = r14
                r7.layoutResult = r9
                goto L4e2
            L534:
                androidx.compose.ui.platform.AccessibilityIterators$PageTextSegmentIterator r7 = androidx.compose.ui.platform.AccessibilityIterators$PageTextSegmentIterator.pageInstance
                if (r7 != 0) goto L544
                androidx.compose.ui.platform.AccessibilityIterators$PageTextSegmentIterator r7 = new androidx.compose.ui.platform.AccessibilityIterators$PageTextSegmentIterator
                r7.<init>()
                android.graphics.Rect r11 = new android.graphics.Rect
                r11.<init>()
                androidx.compose.ui.platform.AccessibilityIterators$PageTextSegmentIterator.pageInstance = r7
            L544:
                androidx.compose.ui.platform.AccessibilityIterators$PageTextSegmentIterator r7 = androidx.compose.ui.platform.AccessibilityIterators$PageTextSegmentIterator.pageInstance
                java.lang.String r11 = "null cannot be cast to non-null type androidx.compose.ui.platform.AccessibilityIterators.PageTextSegmentIterator"
                kotlin.ResultKt.checkNotNull(r7, r11)
                kotlin.ResultKt.checkNotNullParameter(r9, r10)
                r7.text = r14
                r7.layoutResult = r9
                r7.node = r5
                goto L4e2
            L555:
                android.content.Context r7 = r12.getContext()
                android.content.res.Resources r7 = r7.getResources()
                android.content.res.Configuration r7 = r7.getConfiguration()
                java.util.Locale r7 = r7.locale
                kotlin.ResultKt.checkNotNullExpressionValue(r7, r10)
                androidx.compose.ui.platform.AccessibilityIterators$WordTextSegmentIterator r9 = androidx.compose.ui.platform.AccessibilityIterators$WordTextSegmentIterator.instance
                if (r9 != 0) goto L57c
                androidx.compose.ui.platform.AccessibilityIterators$WordTextSegmentIterator r9 = new androidx.compose.ui.platform.AccessibilityIterators$WordTextSegmentIterator
                r9.<init>()
                java.text.BreakIterator r7 = java.text.BreakIterator.getWordInstance(r7)
                java.lang.String r10 = "getWordInstance(locale)"
                kotlin.ResultKt.checkNotNullExpressionValue(r7, r10)
                r9.impl = r7
                androidx.compose.ui.platform.AccessibilityIterators$WordTextSegmentIterator.instance = r9
            L57c:
                androidx.compose.ui.platform.AccessibilityIterators$WordTextSegmentIterator r7 = androidx.compose.ui.platform.AccessibilityIterators$WordTextSegmentIterator.instance
                java.lang.String r9 = "null cannot be cast to non-null type androidx.compose.ui.platform.AccessibilityIterators.WordTextSegmentIterator"
                kotlin.ResultKt.checkNotNull(r7, r9)
                r7.initialize(r14)
                goto L4e2
            L588:
                android.content.Context r7 = r12.getContext()
                android.content.res.Resources r7 = r7.getResources()
                android.content.res.Configuration r7 = r7.getConfiguration()
                java.util.Locale r7 = r7.locale
                kotlin.ResultKt.checkNotNullExpressionValue(r7, r10)
                androidx.compose.ui.platform.AccessibilityIterators$CharacterTextSegmentIterator r9 = androidx.compose.ui.platform.AccessibilityIterators$CharacterTextSegmentIterator.instance
                if (r9 != 0) goto L5af
                androidx.compose.ui.platform.AccessibilityIterators$CharacterTextSegmentIterator r9 = new androidx.compose.ui.platform.AccessibilityIterators$CharacterTextSegmentIterator
                r9.<init>()
                java.text.BreakIterator r7 = java.text.BreakIterator.getCharacterInstance(r7)
                java.lang.String r10 = "getCharacterInstance(locale)"
                kotlin.ResultKt.checkNotNullExpressionValue(r7, r10)
                r9.impl = r7
                androidx.compose.ui.platform.AccessibilityIterators$CharacterTextSegmentIterator.instance = r9
            L5af:
                androidx.compose.ui.platform.AccessibilityIterators$CharacterTextSegmentIterator r7 = androidx.compose.ui.platform.AccessibilityIterators$CharacterTextSegmentIterator.instance
                java.lang.String r9 = "null cannot be cast to non-null type androidx.compose.ui.platform.AccessibilityIterators.CharacterTextSegmentIterator"
                kotlin.ResultKt.checkNotNull(r7, r9)
                r7.initialize(r14)
                goto L4e2
            L5bb:
                if (r9 != 0) goto L5bf
                goto L658
            L5bf:
                int r7 = r4.getAccessibilitySelectionEnd(r5)
                r10 = -1
                if (r7 != r10) goto L5ce
                if (r1 == 0) goto L5ca
                r7 = r6
                goto L5ce
            L5ca:
                int r7 = r8.length()
            L5ce:
                if (r1 == 0) goto L5d5
                int[] r7 = r9.following(r7)
                goto L5d9
            L5d5:
                int[] r7 = r9.preceding(r7)
            L5d9:
                if (r7 != 0) goto L5dd
                goto L658
            L5dd:
                r11 = r7[r6]
                r12 = r7[r15]
                if (r2 == 0) goto L605
                androidx.compose.ui.semantics.SemanticsPropertyKey r2 = androidx.compose.ui.semantics.SemanticsProperties.ContentDescription
                boolean r2 = r13.contains(r2)
                if (r2 != 0) goto L605
                androidx.compose.ui.semantics.SemanticsPropertyKey r2 = androidx.compose.ui.semantics.SemanticsProperties.EditableText
                boolean r2 = r13.contains(r2)
                if (r2 == 0) goto L605
                int r2 = r4.getAccessibilitySelectionStart(r5)
                r6 = -1
                if (r2 != r6) goto L5ff
                if (r1 == 0) goto L5fe
                r2 = r11
                goto L5ff
            L5fe:
                r2 = r12
            L5ff:
                if (r1 == 0) goto L603
                r6 = r12
                goto L60b
            L603:
                r6 = r11
                goto L60b
            L605:
                if (r1 == 0) goto L609
                r2 = r12
                goto L60a
            L609:
                r2 = r11
            L60a:
                r6 = r2
            L60b:
                if (r1 == 0) goto L610
                r9 = 256(0x100, float:3.59E-43)
                goto L612
            L610:
                r9 = 512(0x200, float:7.175E-43)
            L612:
                androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$PendingTextTraversedEvent r1 = new androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$PendingTextTraversedEvent
                long r13 = android.os.SystemClock.uptimeMillis()
                r7 = r1
                r8 = r5
                r10 = r0
                r7.<init>(r8, r9, r10, r11, r12, r13)
                r4.pendingTextTraversedEvent = r1
                r4.setAccessibilitySelection(r5, r2, r6, r15)
                goto L414
            L625:
                int r1 = r4.focusedVirtualViewId
                if (r1 != r0) goto L658
                r4.focusedVirtualViewId = r11
                r12.invalidate()
                androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(r4, r0, r7, r9, r8)
                goto L414
            L633:
                android.view.accessibility.AccessibilityManager r1 = r4.accessibilityManager
                boolean r2 = r1.isEnabled()
                if (r2 == 0) goto L658
                boolean r1 = r1.isTouchExplorationEnabled()
                if (r1 == 0) goto L658
                int r1 = r4.focusedVirtualViewId
                if (r1 != r0) goto L646
                goto L658
            L646:
                if (r1 == r11) goto L64b
                androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(r4, r1, r7, r9, r8)
            L64b:
                r4.focusedVirtualViewId = r0
                r12.invalidate()
                r1 = 32768(0x8000, float:4.5918E-41)
                androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(r4, r0, r1, r9, r8)
                goto L414
            L658:
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.MyNodeProvider.performAction(int, int, android.os.Bundle):boolean");
        }
    }

    /* loaded from: classes.dex */
    public final class PendingTextTraversedEvent {
        public final int action;
        public final int fromIndex;
        public final int granularity;
        public final SemanticsNode node;
        public final int toIndex;
        public final long traverseTime;

        public PendingTextTraversedEvent(SemanticsNode semanticsNode, int i, int i2, int i3, int i4, long j) {
            this.node = semanticsNode;
            this.action = i;
            this.granularity = i2;
            this.fromIndex = i3;
            this.toIndex = i4;
            this.traverseTime = j;
        }
    }

    /* loaded from: classes.dex */
    public final class SemanticsNodeCopy {
        public final LinkedHashSet children;
        public final SemanticsNode semanticsNode;
        public final SemanticsConfiguration unmergedConfig;

        public SemanticsNodeCopy(SemanticsNode semanticsNode, Map map) {
            ResultKt.checkNotNullParameter(semanticsNode, "semanticsNode");
            ResultKt.checkNotNullParameter(map, "currentSemanticsNodes");
            this.semanticsNode = semanticsNode;
            this.unmergedConfig = semanticsNode.unmergedConfig;
            this.children = new LinkedHashSet();
            List children = semanticsNode.getChildren(false, true);
            int size = children.size();
            for (int i = 0; i < size; i++) {
                SemanticsNode semanticsNode2 = (SemanticsNode) children.get(i);
                if (map.containsKey(Integer.valueOf(semanticsNode2.id))) {
                    this.children.add(Integer.valueOf(semanticsNode2.id));
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [androidx.collection.SimpleArrayMap, androidx.collection.ArrayMap] */
    /* JADX WARN: Type inference failed for: r2v2, types: [androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda0] */
    /* JADX WARN: Type inference failed for: r2v3, types: [androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda1] */
    public AndroidComposeViewAccessibilityDelegateCompat(AndroidComposeView androidComposeView) {
        ResultKt.checkNotNullParameter(androidComposeView, "view");
        this.view = androidComposeView;
        this.hoveredVirtualViewId = Integer.MIN_VALUE;
        Object systemService = androidComposeView.getContext().getSystemService("accessibility");
        ResultKt.checkNotNull(systemService, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        android.view.accessibility.AccessibilityManager accessibilityManager = (android.view.accessibility.AccessibilityManager) systemService;
        this.accessibilityManager = accessibilityManager;
        this.enabledStateListener = new AccessibilityManager.AccessibilityStateChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda0
            @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
            public final void onAccessibilityStateChanged(boolean z) {
                AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = AndroidComposeViewAccessibilityDelegateCompat.this;
                ResultKt.checkNotNullParameter(androidComposeViewAccessibilityDelegateCompat, "this$0");
                androidComposeViewAccessibilityDelegateCompat.enabledServices = z ? androidComposeViewAccessibilityDelegateCompat.accessibilityManager.getEnabledAccessibilityServiceList(-1) : EmptyList.INSTANCE;
            }
        };
        this.touchExplorationStateListener = new AccessibilityManager.TouchExplorationStateChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda1
            @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
            public final void onTouchExplorationStateChanged(boolean z) {
                AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = AndroidComposeViewAccessibilityDelegateCompat.this;
                ResultKt.checkNotNullParameter(androidComposeViewAccessibilityDelegateCompat, "this$0");
                androidComposeViewAccessibilityDelegateCompat.enabledServices = androidComposeViewAccessibilityDelegateCompat.accessibilityManager.getEnabledAccessibilityServiceList(-1);
            }
        };
        this.enabledServices = accessibilityManager.getEnabledAccessibilityServiceList(-1);
        this.handler = new Handler(Looper.getMainLooper());
        this.nodeProvider = new AccessibilityNodeProviderCompat(new MyNodeProvider());
        this.focusedVirtualViewId = Integer.MIN_VALUE;
        this.actionIdToLabel = new SparseArrayCompat();
        this.labelToActionId = new SparseArrayCompat();
        this.accessibilityCursorPosition = -1;
        this.subtreeChangedLayoutNodes = new ArraySet();
        this.boundsUpdateChannel = JobKt.Channel$default(-1, null, 6);
        this.currentSemanticsNodesInvalidated = true;
        this.bufferedContentCaptureAppearedNodes = new SimpleArrayMap();
        this.bufferedContentCaptureDisappearedNodes = new ArraySet();
        EmptyMap emptyMap = EmptyMap.INSTANCE;
        this.currentSemanticsNodes = emptyMap;
        this.paneDisplayed = new ArraySet();
        this.idToBeforeMap = new HashMap();
        this.idToAfterMap = new HashMap();
        this.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL";
        this.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL";
        this.urlSpanCache = new URLSpanCache();
        this.previousSemanticsNodes = new LinkedHashMap();
        this.previousSemanticsRoot = new SemanticsNodeCopy(androidComposeView.getSemanticsOwner().getUnmergedRootSemanticsNode(), emptyMap);
        androidComposeView.addOnAttachStateChangeListener(new AnonymousClass1(0, this));
        this.semanticsChangeChecker = new FullyDrawnReporter$$ExternalSyntheticLambda0(5, this);
        this.scrollObservationScopes = new ArrayList();
        this.sendScrollEventIfNeededLambda = new AbstractMap$toString$1(14, this);
    }

    public static boolean getInfoIsCheckable(SemanticsNode semanticsNode) {
        ToggleableState toggleableState = (ToggleableState) _BOUNDARY.getOrNull(semanticsNode.unmergedConfig, SemanticsProperties.ToggleableState);
        SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.Role;
        SemanticsConfiguration semanticsConfiguration = semanticsNode.unmergedConfig;
        Role role = (Role) _BOUNDARY.getOrNull(semanticsConfiguration, semanticsPropertyKey);
        boolean z = true;
        boolean z2 = toggleableState != null;
        if (((Boolean) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsProperties.Selected)) == null) {
            return z2;
        }
        if (role != null && Role.m238equalsimpl0(role.value, 4)) {
            z = z2;
        }
        return z;
    }

    public static String getIterableTextForAccessibility(SemanticsNode semanticsNode) {
        AnnotatedString annotatedString;
        if (semanticsNode == null) {
            return null;
        }
        SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.ContentDescription;
        SemanticsConfiguration semanticsConfiguration = semanticsNode.unmergedConfig;
        if (semanticsConfiguration.contains(semanticsPropertyKey)) {
            return _BOUNDARY.fastJoinToString$default((List) semanticsConfiguration.get(semanticsPropertyKey), ",");
        }
        if (semanticsConfiguration.contains(SemanticsActions.SetText)) {
            AnnotatedString annotatedString2 = (AnnotatedString) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsProperties.EditableText);
            if (annotatedString2 != null) {
                return annotatedString2.text;
            }
            return null;
        }
        List list = (List) _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsProperties.Text);
        if (list == null || (annotatedString = (AnnotatedString) CollectionsKt___CollectionsKt.firstOrNull(list)) == null) {
            return null;
        }
        return annotatedString.text;
    }

    public static /* synthetic */ void sendEventForVirtualView$default(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, int i, int i2, Integer num, int i3) {
        if ((i3 & 4) != 0) {
            num = null;
        }
        androidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView(i, i2, num, null);
    }

    public static final void subtreeSortedByGeometryGrouping$depthFirstSearch(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, ArrayList arrayList, LinkedHashMap linkedHashMap, boolean z, SemanticsNode semanticsNode) {
        SemanticsConfiguration config = semanticsNode.getConfig();
        SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.IsTraversalGroup;
        Boolean bool = (Boolean) _BOUNDARY.getOrNull(config, semanticsPropertyKey);
        Boolean bool2 = Boolean.TRUE;
        boolean areEqual = ResultKt.areEqual(bool, bool2);
        int i = semanticsNode.id;
        if ((areEqual || androidComposeViewAccessibilityDelegateCompat.isScreenReaderFocusable(semanticsNode)) && androidComposeViewAccessibilityDelegateCompat.getCurrentSemanticsNodes$ui_release().keySet().contains(Integer.valueOf(i))) {
            arrayList.add(semanticsNode);
        }
        boolean areEqual2 = ResultKt.areEqual((Boolean) _BOUNDARY.getOrNull(semanticsNode.getConfig(), semanticsPropertyKey), bool2);
        boolean z2 = semanticsNode.mergingEnabled;
        if (areEqual2) {
            linkedHashMap.put(Integer.valueOf(i), androidComposeViewAccessibilityDelegateCompat.subtreeSortedByGeometryGrouping(z, CollectionsKt___CollectionsKt.toMutableList(semanticsNode.getChildren(!z2, false))));
            return;
        }
        List children = semanticsNode.getChildren(!z2, false);
        int size = children.size();
        for (int i2 = 0; i2 < size; i2++) {
            subtreeSortedByGeometryGrouping$depthFirstSearch(androidComposeViewAccessibilityDelegateCompat, arrayList, linkedHashMap, z, (SemanticsNode) children.get(i2));
        }
    }

    public static CharSequence trimToSize(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() == 0) {
            return charSequence;
        }
        int i = 100000;
        if (charSequence.length() <= 100000) {
            return charSequence;
        }
        if (Character.isHighSurrogate(charSequence.charAt(99999)) && Character.isLowSurrogate(charSequence.charAt(100000))) {
            i = 99999;
        }
        CharSequence subSequence = charSequence.subSequence(0, i);
        ResultKt.checkNotNull(subSequence, "null cannot be cast to non-null type T of androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.trimToSize");
        return subSequence;
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0209  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void addExtraDataToAccessibilityNodeInfoHelper(int r18, android.view.accessibility.AccessibilityNodeInfo r19, java.lang.String r20, android.os.Bundle r21) {
        /*
            r17 = this;
            r0 = r17
            r1 = r20
            r2 = r21
            java.util.Map r3 = r17.getCurrentSemanticsNodes$ui_release()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r18)
            java.lang.Object r3 = r3.get(r4)
            androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds r3 = (androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds) r3
            if (r3 == 0) goto L28d
            androidx.compose.ui.semantics.SemanticsNode r3 = r3.semanticsNode
            if (r3 != 0) goto L1c
            goto L28d
        L1c:
            java.lang.String r4 = getIterableTextForAccessibility(r3)
            java.lang.String r5 = r0.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL
            boolean r5 = kotlin.ResultKt.areEqual(r1, r5)
            if (r5 == 0) goto L43
            java.util.HashMap r2 = r0.idToBeforeMap
            java.lang.Integer r3 = java.lang.Integer.valueOf(r18)
            java.lang.Object r2 = r2.get(r3)
            java.lang.Integer r2 = (java.lang.Integer) r2
            if (r2 == 0) goto L28d
            android.os.Bundle r3 = r19.getExtras()
            int r2 = r2.intValue()
            r3.putInt(r1, r2)
            goto L28d
        L43:
            java.lang.String r5 = r0.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL
            boolean r5 = kotlin.ResultKt.areEqual(r1, r5)
            if (r5 == 0) goto L66
            java.util.HashMap r2 = r0.idToAfterMap
            java.lang.Integer r3 = java.lang.Integer.valueOf(r18)
            java.lang.Object r2 = r2.get(r3)
            java.lang.Integer r2 = (java.lang.Integer) r2
            if (r2 == 0) goto L28d
            android.os.Bundle r3 = r19.getExtras()
            int r2 = r2.intValue()
            r3.putInt(r1, r2)
            goto L28d
        L66:
            androidx.compose.ui.semantics.SemanticsPropertyKey r5 = androidx.compose.ui.semantics.SemanticsActions.GetTextLayoutResult
            androidx.compose.ui.semantics.SemanticsConfiguration r6 = r3.unmergedConfig
            boolean r7 = r6.contains(r5)
            if (r7 == 0) goto L25a
            if (r2 == 0) goto L25a
            java.lang.String r7 = "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY"
            boolean r7 = kotlin.ResultKt.areEqual(r1, r7)
            if (r7 == 0) goto L25a
            java.lang.String r7 = "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX"
            r8 = -1
            int r7 = r2.getInt(r7, r8)
            java.lang.String r9 = "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH"
            int r2 = r2.getInt(r9, r8)
            if (r2 <= 0) goto L252
            if (r7 < 0) goto L252
            if (r4 == 0) goto L92
            int r4 = r4.length()
            goto L95
        L92:
            r4 = 2147483647(0x7fffffff, float:NaN)
        L95:
            if (r7 < r4) goto L99
            goto L252
        L99:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.lang.Object r5 = r6.get(r5)
            androidx.compose.ui.semantics.AccessibilityAction r5 = (androidx.compose.ui.semantics.AccessibilityAction) r5
            kotlin.Function r5 = r5.action
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            r6 = 0
            if (r5 == 0) goto Lb2
            java.lang.Object r5 = r5.invoke(r4)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            goto Lb3
        Lb2:
            r5 = r6
        Lb3:
            java.lang.Boolean r8 = java.lang.Boolean.TRUE
            boolean r5 = kotlin.ResultKt.areEqual(r5, r8)
            if (r5 == 0) goto L251
            r5 = 0
            java.lang.Object r4 = r4.get(r5)
            androidx.compose.ui.text.TextLayoutResult r4 = (androidx.compose.ui.text.TextLayoutResult) r4
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            r9 = r5
        Lc8:
            if (r9 >= r2) goto L240
            int r10 = r7 + r9
            androidx.compose.ui.text.TextLayoutInput r11 = r4.layoutInput
            androidx.compose.ui.text.AnnotatedString r11 = r11.text
            java.lang.String r11 = r11.text
            int r11 = r11.length()
            if (r10 < r11) goto Ldd
            r8.add(r6)
            goto L20d
        Ldd:
            androidx.compose.ui.text.MultiParagraph r11 = r4.multiParagraph
            androidx.compose.ui.text.MultiParagraphIntrinsics r12 = r11.intrinsics
            if (r10 < 0) goto L213
            androidx.compose.ui.text.AnnotatedString r13 = r12.annotatedString
            java.lang.String r13 = r13.text
            int r13 = r13.length()
            if (r10 >= r13) goto L213
            java.util.ArrayList r11 = r11.paragraphInfoList
            int r12 = _COROUTINE._BOUNDARY.findParagraphByIndex(r10, r11)
            java.lang.Object r11 = r11.get(r12)
            androidx.compose.ui.text.ParagraphInfo r11 = (androidx.compose.ui.text.ParagraphInfo) r11
            androidx.compose.ui.text.AndroidParagraph r12 = r11.paragraph
            int r13 = r11.startIndex
            int r14 = r11.endIndex
            int r10 = kotlin.ResultKt.coerceIn(r10, r13, r14)
            int r10 = r10 - r13
            androidx.compose.ui.text.android.TextLayout r12 = r12.layout
            android.text.Layout r13 = r12.layout
            int r14 = r13.getLineForOffset(r10)
            float r15 = r12.getLineTop(r14)
            float r6 = r12.getLineBottom(r14)
            int r14 = r13.getParagraphDirection(r14)
            r5 = 1
            if (r14 != r5) goto L11d
            r14 = r5
            goto L11e
        L11d:
            r14 = 0
        L11e:
            boolean r13 = r13.isRtlCharAt(r10)
            if (r14 == 0) goto L133
            if (r13 != 0) goto L133
            r5 = 0
            float r13 = r12.getPrimaryHorizontal(r10, r5)
            int r10 = r10 + 1
            r14 = 1
            float r10 = r12.getPrimaryHorizontal(r10, r14)
            goto L161
        L133:
            r5 = 0
            if (r14 == 0) goto L149
            if (r13 == 0) goto L149
            float r13 = r12.getSecondaryHorizontal(r10, r5)
            int r10 = r10 + 1
            r14 = 1
            float r10 = r12.getSecondaryHorizontal(r10, r14)
        L143:
            r16 = r13
            r13 = r10
            r10 = r16
            goto L161
        L149:
            r14 = 1
            if (r13 == 0) goto L157
            float r13 = r12.getPrimaryHorizontal(r10, r5)
            int r10 = r10 + 1
            float r10 = r12.getPrimaryHorizontal(r10, r14)
            goto L143
        L157:
            float r13 = r12.getSecondaryHorizontal(r10, r5)
            int r10 = r10 + 1
            float r10 = r12.getSecondaryHorizontal(r10, r14)
        L161:
            android.graphics.RectF r5 = new android.graphics.RectF
            r5.<init>(r13, r15, r10, r6)
            float r6 = r5.left
            float r10 = r5.top
            float r12 = r5.right
            float r5 = r5.bottom
            r13 = 0
            float r11 = r11.top
            long r13 = _COROUTINE._BOUNDARY.Offset(r13, r11)
            androidx.compose.ui.geometry.Rect r11 = new androidx.compose.ui.geometry.Rect
            float r15 = androidx.compose.ui.geometry.Offset.m76getXimpl(r13)
            float r15 = r15 + r6
            float r6 = androidx.compose.ui.geometry.Offset.m77getYimpl(r13)
            float r6 = r6 + r10
            float r10 = androidx.compose.ui.geometry.Offset.m76getXimpl(r13)
            float r10 = r10 + r12
            float r12 = androidx.compose.ui.geometry.Offset.m77getYimpl(r13)
            float r12 = r12 + r5
            r11.<init>(r15, r6, r10, r12)
            androidx.compose.ui.node.NodeCoordinator r5 = r3.findCoordinatorToGetBounds$ui_release()
            if (r5 == 0) goto L1a5
            boolean r6 = r5.isAttached()
            if (r6 == 0) goto L19b
            goto L19c
        L19b:
            r5 = 0
        L19c:
            if (r5 == 0) goto L1a5
            long r12 = androidx.compose.ui.geometry.Offset.Zero
            long r5 = r5.mo161localToRootMKHz9U(r12)
            goto L1a7
        L1a5:
            long r5 = androidx.compose.ui.geometry.Offset.Zero
        L1a7:
            androidx.compose.ui.geometry.Rect r5 = r11.m82translatek4lQ0M(r5)
            androidx.compose.ui.geometry.Rect r6 = r3.getBoundsInRoot()
            float r10 = r5.right
            float r11 = r6.left
            int r10 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r10 <= 0) goto L1d6
            float r10 = r6.right
            float r11 = r5.left
            int r10 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r10 > 0) goto L1c0
            goto L1d6
        L1c0:
            float r10 = r5.bottom
            float r11 = r6.top
            int r10 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r10 <= 0) goto L1d6
            float r10 = r6.bottom
            float r11 = r5.top
            int r10 = (r10 > r11 ? 1 : (r10 == r11 ? 0 : -1))
            if (r10 > 0) goto L1d1
            goto L1d6
        L1d1:
            androidx.compose.ui.geometry.Rect r5 = r5.intersect(r6)
            goto L1d7
        L1d6:
            r5 = 0
        L1d7:
            if (r5 == 0) goto L209
            float r6 = r5.left
            float r10 = r5.top
            long r10 = _COROUTINE._BOUNDARY.Offset(r6, r10)
            androidx.compose.ui.platform.AndroidComposeView r6 = r0.view
            long r10 = r6.m218localToScreenMKHz9U(r10)
            float r12 = r5.right
            float r5 = r5.bottom
            long r12 = _COROUTINE._BOUNDARY.Offset(r12, r5)
            long r5 = r6.m218localToScreenMKHz9U(r12)
            android.graphics.RectF r12 = new android.graphics.RectF
            float r13 = androidx.compose.ui.geometry.Offset.m76getXimpl(r10)
            float r10 = androidx.compose.ui.geometry.Offset.m77getYimpl(r10)
            float r11 = androidx.compose.ui.geometry.Offset.m76getXimpl(r5)
            float r5 = androidx.compose.ui.geometry.Offset.m77getYimpl(r5)
            r12.<init>(r13, r10, r11, r5)
            goto L20a
        L209:
            r12 = 0
        L20a:
            r8.add(r12)
        L20d:
            int r9 = r9 + 1
            r5 = 0
            r6 = 0
            goto Lc8
        L213:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "offset("
            r1.<init>(r2)
            r1.append(r10)
            java.lang.String r2 = ") is out of bounds [0, "
            r1.append(r2)
            androidx.compose.ui.text.AnnotatedString r2 = r12.annotatedString
            java.lang.String r2 = r2.text
            int r2 = r2.length()
            r1.append(r2)
            r2 = 41
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            throw r2
        L240:
            android.os.Bundle r2 = r19.getExtras()
            r3 = 0
            android.graphics.RectF[] r3 = new android.graphics.RectF[r3]
            java.lang.Object[] r3 = r8.toArray(r3)
            android.os.Parcelable[] r3 = (android.os.Parcelable[]) r3
            r2.putParcelableArray(r1, r3)
            goto L28d
        L251:
            return
        L252:
            java.lang.String r1 = "AccessibilityDelegate"
            java.lang.String r2 = "Invalid arguments for accessibility character locations"
            android.util.Log.e(r1, r2)
            return
        L25a:
            androidx.compose.ui.semantics.SemanticsPropertyKey r4 = androidx.compose.ui.semantics.SemanticsProperties.TestTag
            boolean r5 = r6.contains(r4)
            if (r5 == 0) goto L27c
            if (r2 == 0) goto L27c
            java.lang.String r2 = "androidx.compose.ui.semantics.testTag"
            boolean r2 = kotlin.ResultKt.areEqual(r1, r2)
            if (r2 == 0) goto L27c
            java.lang.Object r2 = _COROUTINE._BOUNDARY.getOrNull(r6, r4)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto L28d
            android.os.Bundle r3 = r19.getExtras()
            r3.putCharSequence(r1, r2)
            goto L28d
        L27c:
            java.lang.String r2 = "androidx.compose.ui.semantics.id"
            boolean r2 = kotlin.ResultKt.areEqual(r1, r2)
            if (r2 == 0) goto L28d
            android.os.Bundle r2 = r19.getExtras()
            int r3 = r3.id
            r2.putInt(r1, r3)
        L28d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.addExtraDataToAccessibilityNodeInfoHelper(int, android.view.accessibility.AccessibilityNodeInfo, java.lang.String, android.os.Bundle):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0068 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0075 A[Catch: all -> 0x0032, TRY_LEAVE, TryCatch #0 {all -> 0x0032, blocks: (B:12:0x002c, B:14:0x005a, B:19:0x006d, B:21:0x0075, B:24:0x0083, B:26:0x0088, B:28:0x0097, B:30:0x009e, B:31:0x00a7, B:40:0x0043), top: B:7:0x0020 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0022  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x00b8 -> B:13:0x002f). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object boundsUpdatesEventLoop(kotlin.coroutines.Continuation r12) {
        /*
            r11 = this;
            boolean r0 = r12 instanceof androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$boundsUpdatesEventLoop$1
            if (r0 == 0) goto L13
            r0 = r12
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$boundsUpdatesEventLoop$1 r0 = (androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$boundsUpdatesEventLoop$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$boundsUpdatesEventLoop$1 r0 = new androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$boundsUpdatesEventLoop$1
            r0.<init>(r11, r12)
        L18:
            java.lang.Object r12 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            r4 = 2
            if (r2 == 0) goto L47
            if (r2 == r3) goto L3d
            if (r2 != r4) goto L35
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r2 = r0.L$2
            androidx.collection.ArraySet r5 = r0.L$1
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r6 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L32
        L2f:
            r12 = r5
            r5 = r2
            goto L5a
        L32:
            r12 = move-exception
            goto Lc7
        L35:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L3d:
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r2 = r0.L$2
            androidx.collection.ArraySet r5 = r0.L$1
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r6 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r12)     // Catch: java.lang.Throwable -> L32
            goto L6d
        L47:
            kotlin.ResultKt.throwOnFailure(r12)
            androidx.collection.ArraySet r12 = new androidx.collection.ArraySet     // Catch: java.lang.Throwable -> Lc5
            r12.<init>()     // Catch: java.lang.Throwable -> Lc5
            kotlinx.coroutines.channels.BufferedChannel r2 = r11.boundsUpdateChannel     // Catch: java.lang.Throwable -> Lc5
            r2.getClass()     // Catch: java.lang.Throwable -> Lc5
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r5 = new kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator     // Catch: java.lang.Throwable -> Lc5
            r5.<init>()     // Catch: java.lang.Throwable -> Lc5
            r6 = r11
        L5a:
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L32
            r0.L$1 = r12     // Catch: java.lang.Throwable -> L32
            r0.L$2 = r5     // Catch: java.lang.Throwable -> L32
            r0.label = r3     // Catch: java.lang.Throwable -> L32
            java.lang.Object r2 = r5.hasNext(r0)     // Catch: java.lang.Throwable -> L32
            if (r2 != r1) goto L69
            return r1
        L69:
            r10 = r5
            r5 = r12
            r12 = r2
            r2 = r10
        L6d:
            java.lang.Boolean r12 = (java.lang.Boolean) r12     // Catch: java.lang.Throwable -> L32
            boolean r12 = r12.booleanValue()     // Catch: java.lang.Throwable -> L32
            if (r12 == 0) goto Lbb
            r2.next()     // Catch: java.lang.Throwable -> L32
            r6.getClass()     // Catch: java.lang.Throwable -> L32
            boolean r12 = r6.isEnabledForAccessibility()     // Catch: java.lang.Throwable -> L32
            androidx.collection.ArraySet r7 = r6.subtreeChangedLayoutNodes
            if (r12 == 0) goto La7
            int r12 = r7.mSize     // Catch: java.lang.Throwable -> L32
            r8 = 0
        L86:
            if (r8 >= r12) goto L97
            java.lang.Object[] r9 = r7.mArray     // Catch: java.lang.Throwable -> L32
            r9 = r9[r8]     // Catch: java.lang.Throwable -> L32
            kotlin.ResultKt.checkNotNull(r9)     // Catch: java.lang.Throwable -> L32
            androidx.compose.ui.node.LayoutNode r9 = (androidx.compose.ui.node.LayoutNode) r9     // Catch: java.lang.Throwable -> L32
            r6.sendSubtreeChangeAccessibilityEvents(r9, r5)     // Catch: java.lang.Throwable -> L32
            int r8 = r8 + 1
            goto L86
        L97:
            r5.clear()     // Catch: java.lang.Throwable -> L32
            boolean r12 = r6.checkingForSemanticsChanges     // Catch: java.lang.Throwable -> L32
            if (r12 != 0) goto La7
            r6.checkingForSemanticsChanges = r3     // Catch: java.lang.Throwable -> L32
            android.os.Handler r12 = r6.handler     // Catch: java.lang.Throwable -> L32
            androidx.activity.FullyDrawnReporter$$ExternalSyntheticLambda0 r8 = r6.semanticsChangeChecker     // Catch: java.lang.Throwable -> L32
            r12.post(r8)     // Catch: java.lang.Throwable -> L32
        La7:
            r7.clear()     // Catch: java.lang.Throwable -> L32
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L32
            r0.L$1 = r5     // Catch: java.lang.Throwable -> L32
            r0.L$2 = r2     // Catch: java.lang.Throwable -> L32
            r0.label = r4     // Catch: java.lang.Throwable -> L32
            r7 = 100
            java.lang.Object r12 = kotlin.ResultKt.delay(r7, r0)     // Catch: java.lang.Throwable -> L32
            if (r12 != r1) goto L2f
            return r1
        Lbb:
            androidx.collection.ArraySet r12 = r6.subtreeChangedLayoutNodes
            r12.clear()
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        Lc3:
            r6 = r11
            goto Lc7
        Lc5:
            r12 = move-exception
            goto Lc3
        Lc7:
            androidx.collection.ArraySet r0 = r6.subtreeChangedLayoutNodes
            r0.clear()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.boundsUpdatesEventLoop(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* renamed from: canScroll-0AR0LA0$ui_release */
    public final void m224canScroll0AR0LA0$ui_release(boolean z, long j) {
        SemanticsPropertyKey semanticsPropertyKey;
        Collection values = getCurrentSemanticsNodes$ui_release().values();
        ResultKt.checkNotNullParameter(values, "currentSemanticsNodes");
        if (Offset.m75equalsimpl0(j, Offset.Unspecified)) {
            return;
        }
        if (Float.isNaN(Offset.m76getXimpl(j)) || Float.isNaN(Offset.m77getYimpl(j))) {
            throw new IllegalStateException("Offset argument contained a NaN value.".toString());
        }
        if (z) {
            semanticsPropertyKey = SemanticsProperties.VerticalScrollAxisRange;
        } else {
            if (z) {
                throw new RuntimeException();
            }
            semanticsPropertyKey = SemanticsProperties.HorizontalScrollAxisRange;
        }
        Collection<SemanticsNodeWithAdjustedBounds> collection = values;
        if (collection.isEmpty()) {
            return;
        }
        for (SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds : collection) {
            Rect rect = semanticsNodeWithAdjustedBounds.adjustedBounds;
            ResultKt.checkNotNullParameter(rect, "<this>");
            float f = rect.left;
            float f2 = rect.top;
            float f3 = rect.right;
            float f4 = rect.bottom;
            if (Offset.m76getXimpl(j) >= f && Offset.m76getXimpl(j) < f3 && Offset.m77getYimpl(j) >= f2 && Offset.m77getYimpl(j) < f4) {
                DurationKt$$ExternalSyntheticCheckNotZero0.m(_BOUNDARY.getOrNull(semanticsNodeWithAdjustedBounds.semanticsNode.getConfig(), semanticsPropertyKey));
            }
        }
    }

    public final AccessibilityEvent createEvent$ui_release(int i, int i2) {
        AccessibilityEvent obtain = AccessibilityEvent.obtain(i2);
        ResultKt.checkNotNullExpressionValue(obtain, "obtain(eventType)");
        obtain.setEnabled(true);
        obtain.setClassName("android.view.View");
        AndroidComposeView androidComposeView = this.view;
        obtain.setPackageName(androidComposeView.getContext().getPackageName());
        obtain.setSource(androidComposeView, i);
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) getCurrentSemanticsNodes$ui_release().get(Integer.valueOf(i));
        if (semanticsNodeWithAdjustedBounds != null) {
            obtain.setPassword(semanticsNodeWithAdjustedBounds.semanticsNode.getConfig().contains(SemanticsProperties.Password));
        }
        return obtain;
    }

    public final AccessibilityEvent createTextSelectionChangedEvent(int i, Integer num, Integer num2, Integer num3, CharSequence charSequence) {
        AccessibilityEvent createEvent$ui_release = createEvent$ui_release(i, 8192);
        if (num != null) {
            createEvent$ui_release.setFromIndex(num.intValue());
        }
        if (num2 != null) {
            createEvent$ui_release.setToIndex(num2.intValue());
        }
        if (num3 != null) {
            createEvent$ui_release.setItemCount(num3.intValue());
        }
        if (charSequence != null) {
            createEvent$ui_release.getText().add(charSequence);
        }
        return createEvent$ui_release;
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public final AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view) {
        ResultKt.checkNotNullParameter(view, "host");
        return this.nodeProvider;
    }

    public final int getAccessibilitySelectionEnd(SemanticsNode semanticsNode) {
        SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.ContentDescription;
        SemanticsConfiguration semanticsConfiguration = semanticsNode.unmergedConfig;
        if (!semanticsConfiguration.contains(semanticsPropertyKey)) {
            SemanticsPropertyKey semanticsPropertyKey2 = SemanticsProperties.TextSelectionRange;
            if (semanticsConfiguration.contains(semanticsPropertyKey2)) {
                return (int) (4294967295L & ((TextRange) semanticsConfiguration.get(semanticsPropertyKey2)).packedValue);
            }
        }
        return this.accessibilityCursorPosition;
    }

    public final int getAccessibilitySelectionStart(SemanticsNode semanticsNode) {
        SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.ContentDescription;
        SemanticsConfiguration semanticsConfiguration = semanticsNode.unmergedConfig;
        if (!semanticsConfiguration.contains(semanticsPropertyKey)) {
            SemanticsPropertyKey semanticsPropertyKey2 = SemanticsProperties.TextSelectionRange;
            if (semanticsConfiguration.contains(semanticsPropertyKey2)) {
                return (int) (((TextRange) semanticsConfiguration.get(semanticsPropertyKey2)).packedValue >> 32);
            }
        }
        return this.accessibilityCursorPosition;
    }

    public final Map getCurrentSemanticsNodes$ui_release() {
        if (this.currentSemanticsNodesInvalidated) {
            this.currentSemanticsNodesInvalidated = false;
            SemanticsOwner semanticsOwner = this.view.getSemanticsOwner();
            ResultKt.checkNotNullParameter(semanticsOwner, "<this>");
            SemanticsNode unmergedRootSemanticsNode = semanticsOwner.getUnmergedRootSemanticsNode();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            LayoutNode layoutNode = unmergedRootSemanticsNode.layoutNode;
            if (layoutNode.isPlaced() && layoutNode.isAttached()) {
                Region region = new Region();
                androidx.compose.ui.geometry.Rect boundsInRoot = unmergedRootSemanticsNode.getBoundsInRoot();
                region.set(new Rect(ResultKt.roundToInt(boundsInRoot.left), ResultKt.roundToInt(boundsInRoot.top), ResultKt.roundToInt(boundsInRoot.right), ResultKt.roundToInt(boundsInRoot.bottom)));
                InvertMatrixKt.getAllUncoveredSemanticsNodesToMap$findAllSemanticNodesRecursive(region, unmergedRootSemanticsNode, linkedHashMap, unmergedRootSemanticsNode);
            }
            this.currentSemanticsNodes = linkedHashMap;
            HashMap hashMap = this.idToBeforeMap;
            hashMap.clear();
            HashMap hashMap2 = this.idToAfterMap;
            hashMap2.clear();
            SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = (SemanticsNodeWithAdjustedBounds) getCurrentSemanticsNodes$ui_release().get(-1);
            SemanticsNode semanticsNode = semanticsNodeWithAdjustedBounds != null ? semanticsNodeWithAdjustedBounds.semanticsNode : null;
            ResultKt.checkNotNull(semanticsNode);
            int i = 1;
            ArrayList subtreeSortedByGeometryGrouping = subtreeSortedByGeometryGrouping(semanticsNode.layoutNode.layoutDirection == LayoutDirection.Rtl, new ArrayList(new ArrayAsCollection(new SemanticsNode[]{semanticsNode}, true)));
            int lastIndex = ResultKt.getLastIndex(subtreeSortedByGeometryGrouping);
            if (1 <= lastIndex) {
                while (true) {
                    int i2 = ((SemanticsNode) subtreeSortedByGeometryGrouping.get(i - 1)).id;
                    int i3 = ((SemanticsNode) subtreeSortedByGeometryGrouping.get(i)).id;
                    hashMap.put(Integer.valueOf(i2), Integer.valueOf(i3));
                    hashMap2.put(Integer.valueOf(i3), Integer.valueOf(i2));
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
        }
        return this.currentSemanticsNodes;
    }

    public final String getInfoStateDescriptionOrNull(SemanticsNode semanticsNode) {
        SemanticsConfiguration semanticsConfiguration = semanticsNode.unmergedConfig;
        SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.ContentDescription;
        Object orNull = _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsProperties.StateDescription);
        SemanticsPropertyKey semanticsPropertyKey2 = SemanticsProperties.ToggleableState;
        SemanticsConfiguration semanticsConfiguration2 = semanticsNode.unmergedConfig;
        ToggleableState toggleableState = (ToggleableState) _BOUNDARY.getOrNull(semanticsConfiguration2, semanticsPropertyKey2);
        Role role = (Role) _BOUNDARY.getOrNull(semanticsConfiguration2, SemanticsProperties.Role);
        AndroidComposeView androidComposeView = this.view;
        if (toggleableState != null) {
            int ordinal = toggleableState.ordinal();
            if (ordinal != 0) {
                if (ordinal != 1) {
                    if (ordinal == 2 && orNull == null) {
                        orNull = androidComposeView.getContext().getResources().getString(R.string.indeterminate);
                    }
                } else if (role != null && Role.m238equalsimpl0(role.value, 2) && orNull == null) {
                    orNull = androidComposeView.getContext().getResources().getString(R.string.off);
                }
            } else if (role != null && Role.m238equalsimpl0(role.value, 2) && orNull == null) {
                orNull = androidComposeView.getContext().getResources().getString(R.string.on);
            }
        }
        Boolean bool = (Boolean) _BOUNDARY.getOrNull(semanticsConfiguration2, SemanticsProperties.Selected);
        if (bool != null) {
            boolean booleanValue = bool.booleanValue();
            if ((role == null || !Role.m238equalsimpl0(role.value, 4)) && orNull == null) {
                orNull = booleanValue ? androidComposeView.getContext().getResources().getString(R.string.selected) : androidComposeView.getContext().getResources().getString(R.string.not_selected);
            }
        }
        ProgressBarRangeInfo progressBarRangeInfo = (ProgressBarRangeInfo) _BOUNDARY.getOrNull(semanticsConfiguration2, SemanticsProperties.ProgressBarRangeInfo);
        if (progressBarRangeInfo != null) {
            ProgressBarRangeInfo progressBarRangeInfo2 = ProgressBarRangeInfo.Indeterminate;
            if (progressBarRangeInfo != ProgressBarRangeInfo.Indeterminate) {
                if (orNull == null) {
                    ClosedFloatRange closedFloatRange = progressBarRangeInfo.range;
                    float floatValue = Float.valueOf(closedFloatRange._endInclusive).floatValue();
                    float f = closedFloatRange._start;
                    float coerceIn = ResultKt.coerceIn(floatValue - Float.valueOf(f).floatValue() == 0.0f ? 0.0f : (progressBarRangeInfo.current - Float.valueOf(f).floatValue()) / (Float.valueOf(closedFloatRange._endInclusive).floatValue() - Float.valueOf(f).floatValue()), 0.0f, 1.0f);
                    orNull = androidComposeView.getContext().getResources().getString(R.string.template_percent, Integer.valueOf(coerceIn == 0.0f ? 0 : coerceIn == 1.0f ? 100 : ResultKt.coerceIn(ResultKt.roundToInt(coerceIn * 100), 1, 99)));
                }
            } else if (orNull == null) {
                orNull = androidComposeView.getContext().getResources().getString(R.string.in_progress);
            }
        }
        return (String) orNull;
    }

    public final SpannableString getInfoText(SemanticsNode semanticsNode) {
        AnnotatedString annotatedString;
        AndroidComposeView androidComposeView = this.view;
        FontFamily.Resolver fontFamilyResolver = androidComposeView.getFontFamilyResolver();
        AnnotatedString annotatedString2 = (AnnotatedString) _BOUNDARY.getOrNull(semanticsNode.unmergedConfig, SemanticsProperties.EditableText);
        SpannableString spannableString = null;
        URLSpanCache uRLSpanCache = this.urlSpanCache;
        SpannableString spannableString2 = (SpannableString) trimToSize(annotatedString2 != null ? _BOUNDARY.toAccessibilitySpannableString(annotatedString2, androidComposeView.getDensity(), fontFamilyResolver, uRLSpanCache) : null);
        List list = (List) _BOUNDARY.getOrNull(semanticsNode.unmergedConfig, SemanticsProperties.Text);
        if (list != null && (annotatedString = (AnnotatedString) CollectionsKt___CollectionsKt.firstOrNull(list)) != null) {
            spannableString = _BOUNDARY.toAccessibilitySpannableString(annotatedString, androidComposeView.getDensity(), fontFamilyResolver, uRLSpanCache);
        }
        return spannableString2 == null ? (SpannableString) trimToSize(spannableString) : spannableString2;
    }

    public final boolean isEnabledForAccessibility() {
        if (this.accessibilityManager.isEnabled()) {
            ResultKt.checkNotNullExpressionValue(this.enabledServices, "enabledServices");
            if (!r0.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isScreenReaderFocusable(SemanticsNode semanticsNode) {
        List list = (List) _BOUNDARY.getOrNull(semanticsNode.unmergedConfig, SemanticsProperties.ContentDescription);
        boolean z = ((list != null ? (String) CollectionsKt___CollectionsKt.firstOrNull(list) : null) == null && getInfoText(semanticsNode) == null && getInfoStateDescriptionOrNull(semanticsNode) == null && !getInfoIsCheckable(semanticsNode)) ? false : true;
        if (!semanticsNode.unmergedConfig.isMergingSemanticsOfDescendants) {
            if (semanticsNode.isFake || !semanticsNode.getChildren(false, true).isEmpty()) {
                return false;
            }
            if (_BOUNDARY.findClosestParentNode(semanticsNode.layoutNode, SemanticsNode$parent$1.INSTANCE$1) != null || !z) {
                return false;
            }
        }
        return true;
    }

    public final void notifySubtreeAccessibilityStateChangedIfNeeded(LayoutNode layoutNode) {
        if (this.subtreeChangedLayoutNodes.add(layoutNode)) {
            this.boundsUpdateChannel.mo306trySendJP2dKIU(Unit.INSTANCE);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0037, code lost:
    
        if (r5 == null) goto L91;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void notifySubtreeAppeared(androidx.compose.ui.semantics.SemanticsNode r14) {
        /*
            r13 = this;
            int r0 = r14.id
            androidx.compose.ui.platform.WeakCache r1 = r13.contentCaptureSession
            r2 = 0
            if (r1 != 0) goto L9
            goto Ld1
        L9:
            int r3 = android.os.Build.VERSION.SDK_INT
            r4 = 29
            if (r3 >= r4) goto L11
            goto Ld1
        L11:
            androidx.compose.ui.platform.AndroidComposeView r5 = r13.view
            android.view.autofill.AutofillId r5 = androidx.compose.ui.platform.coreshims.ViewCompatShims$Api26Impl.getAutofillId(r5)
            androidx.compose.ui.semantics.SemanticsNode r6 = r14.getParent()
            java.lang.Object r7 = r1.values
            if (r6 == 0) goto L3b
            int r5 = r6.id
            long r5 = (long) r5
            if (r3 < r4) goto L36
            android.view.contentcapture.ContentCaptureSession r8 = androidx.compose.ui.text.android.Paint29$$ExternalSyntheticApiModelOutline0.m(r7)
            java.lang.Object r1 = r1.referenceQueue
            android.view.View r1 = (android.view.View) r1
            android.view.autofill.AutofillId r1 = androidx.compose.ui.platform.coreshims.ViewCompatShims$Api26Impl.getAutofillId(r1)
            android.view.autofill.AutofillId r1 = androidx.compose.ui.platform.coreshims.ContentCaptureSessionCompat$Api29Impl.newAutofillId(r8, r1, r5)
            r5 = r1
            goto L37
        L36:
            r5 = r2
        L37:
            if (r5 != 0) goto L3b
            goto Ld1
        L3b:
            java.lang.String r1 = "if (parentNode != null) ….toAutofillId()\n        }"
            kotlin.ResultKt.checkNotNullExpressionValue(r5, r1)
            int r1 = r14.id
            long r8 = (long) r1
            if (r3 < r4) goto L53
            android.view.contentcapture.ContentCaptureSession r1 = androidx.compose.ui.text.android.Paint29$$ExternalSyntheticApiModelOutline0.m(r7)
            android.view.ViewStructure r1 = androidx.compose.ui.platform.coreshims.ContentCaptureSessionCompat$Api29Impl.newVirtualViewStructure(r1, r5, r8)
            androidx.compose.ui.platform.coreshims.ViewStructureCompat r3 = new androidx.compose.ui.platform.coreshims.ViewStructureCompat
            r3.<init>(r1)
            goto L54
        L53:
            r3 = r2
        L54:
            if (r3 != 0) goto L58
            goto Ld1
        L58:
            androidx.compose.ui.semantics.SemanticsPropertyKey r1 = androidx.compose.ui.semantics.SemanticsProperties.Password
            androidx.compose.ui.semantics.SemanticsConfiguration r4 = r14.unmergedConfig
            boolean r1 = r4.contains(r1)
            if (r1 == 0) goto L63
            goto Ld1
        L63:
            androidx.compose.ui.semantics.SemanticsPropertyKey r1 = androidx.compose.ui.semantics.SemanticsProperties.Text
            java.lang.Object r1 = _COROUTINE._BOUNDARY.getOrNull(r4, r1)
            java.util.List r1 = (java.util.List) r1
            java.lang.String r2 = "\n"
            android.view.ViewStructure r5 = r3.mWrappedObj
            if (r1 == 0) goto L7d
            java.lang.String r6 = "android.widget.TextView"
            androidx.compose.ui.platform.coreshims.ViewStructureCompat.Api23Impl.setClassName(r5, r6)
            java.lang.String r1 = _COROUTINE._BOUNDARY.fastJoinToString$default(r1, r2)
            androidx.compose.ui.platform.coreshims.ViewStructureCompat.Api23Impl.setText(r5, r1)
        L7d:
            androidx.compose.ui.semantics.SemanticsPropertyKey r1 = androidx.compose.ui.semantics.SemanticsProperties.EditableText
            java.lang.Object r1 = _COROUTINE._BOUNDARY.getOrNull(r4, r1)
            androidx.compose.ui.text.AnnotatedString r1 = (androidx.compose.ui.text.AnnotatedString) r1
            if (r1 == 0) goto L8f
            java.lang.String r6 = "android.widget.EditText"
            androidx.compose.ui.platform.coreshims.ViewStructureCompat.Api23Impl.setClassName(r5, r6)
            androidx.compose.ui.platform.coreshims.ViewStructureCompat.Api23Impl.setText(r5, r1)
        L8f:
            androidx.compose.ui.semantics.SemanticsPropertyKey r1 = androidx.compose.ui.semantics.SemanticsProperties.ContentDescription
            java.lang.Object r1 = _COROUTINE._BOUNDARY.getOrNull(r4, r1)
            java.util.List r1 = (java.util.List) r1
            android.view.ViewStructure r6 = r3.mWrappedObj
            if (r1 == 0) goto La2
            java.lang.String r1 = _COROUTINE._BOUNDARY.fastJoinToString$default(r1, r2)
            androidx.compose.ui.platform.coreshims.ViewStructureCompat.Api23Impl.setContentDescription(r6, r1)
        La2:
            androidx.compose.ui.semantics.SemanticsPropertyKey r1 = androidx.compose.ui.semantics.SemanticsProperties.Role
            java.lang.Object r1 = _COROUTINE._BOUNDARY.getOrNull(r4, r1)
            androidx.compose.ui.semantics.Role r1 = (androidx.compose.ui.semantics.Role) r1
            if (r1 == 0) goto Lb7
            int r1 = r1.value
            java.lang.String r1 = androidx.compose.ui.platform.InvertMatrixKt.m230access$toLegacyClassNameV4PA4sw(r1)
            if (r1 == 0) goto Lb7
            androidx.compose.ui.platform.coreshims.ViewStructureCompat.Api23Impl.setClassName(r5, r1)
        Lb7:
            androidx.compose.ui.geometry.Rect r1 = r14.getBoundsInWindow()
            float r2 = r1.left
            int r7 = (int) r2
            float r2 = r1.top
            int r8 = (int) r2
            float r2 = r1.getWidth()
            int r11 = (int) r2
            float r1 = r1.getHeight()
            int r12 = (int) r1
            r9 = 0
            r10 = 0
            androidx.compose.ui.platform.coreshims.ViewStructureCompat.Api23Impl.setDimens(r6, r7, r8, r9, r10, r11, r12)
            r2 = r3
        Ld1:
            if (r2 != 0) goto Ld4
            goto Lf1
        Ld4:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            androidx.collection.ArraySet r3 = r13.bufferedContentCaptureDisappearedNodes
            boolean r1 = r3.contains(r1)
            if (r1 == 0) goto Le8
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r3.remove(r0)
            goto Lf1
        Le8:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            androidx.collection.ArrayMap r1 = r13.bufferedContentCaptureAppearedNodes
            r1.put(r0, r2)
        Lf1:
            r0 = 0
            r1 = 1
            java.util.List r14 = r14.getChildren(r0, r1)
            int r1 = r14.size()
        Lfb:
            if (r0 >= r1) goto L109
            java.lang.Object r2 = r14.get(r0)
            androidx.compose.ui.semantics.SemanticsNode r2 = (androidx.compose.ui.semantics.SemanticsNode) r2
            r13.notifySubtreeAppeared(r2)
            int r0 = r0 + 1
            goto Lfb
        L109:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.notifySubtreeAppeared(androidx.compose.ui.semantics.SemanticsNode):void");
    }

    public final int semanticsNodeIdToAccessibilityVirtualNodeId(int i) {
        if (i == this.view.getSemanticsOwner().getUnmergedRootSemanticsNode().id) {
            return -1;
        }
        return i;
    }

    public final void sendAccessibilitySemanticsStructureChangeEvents(SemanticsNode semanticsNode, SemanticsNodeCopy semanticsNodeCopy) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        List children = semanticsNode.getChildren(false, true);
        int size = children.size();
        int i = 0;
        while (true) {
            LayoutNode layoutNode = semanticsNode.layoutNode;
            if (i >= size) {
                Iterator it = semanticsNodeCopy.children.iterator();
                while (it.hasNext()) {
                    if (!linkedHashSet.contains(Integer.valueOf(((Number) it.next()).intValue()))) {
                        notifySubtreeAccessibilityStateChangedIfNeeded(layoutNode);
                        return;
                    }
                }
                List children2 = semanticsNode.getChildren(false, true);
                int size2 = children2.size();
                for (int i2 = 0; i2 < size2; i2++) {
                    SemanticsNode semanticsNode2 = (SemanticsNode) children2.get(i2);
                    if (getCurrentSemanticsNodes$ui_release().containsKey(Integer.valueOf(semanticsNode2.id))) {
                        Object obj = this.previousSemanticsNodes.get(Integer.valueOf(semanticsNode2.id));
                        ResultKt.checkNotNull(obj);
                        sendAccessibilitySemanticsStructureChangeEvents(semanticsNode2, (SemanticsNodeCopy) obj);
                    }
                }
                return;
            }
            SemanticsNode semanticsNode3 = (SemanticsNode) children.get(i);
            if (getCurrentSemanticsNodes$ui_release().containsKey(Integer.valueOf(semanticsNode3.id))) {
                LinkedHashSet linkedHashSet2 = semanticsNodeCopy.children;
                int i3 = semanticsNode3.id;
                if (!linkedHashSet2.contains(Integer.valueOf(i3))) {
                    notifySubtreeAccessibilityStateChangedIfNeeded(layoutNode);
                    return;
                }
                linkedHashSet.add(Integer.valueOf(i3));
            }
            i++;
        }
    }

    public final void sendContentCaptureSemanticsStructureChangeEvents$ui_release(SemanticsNode semanticsNode, SemanticsNodeCopy semanticsNodeCopy) {
        ResultKt.checkNotNullParameter(semanticsNodeCopy, "oldNode");
        List children = semanticsNode.getChildren(false, true);
        int size = children.size();
        for (int i = 0; i < size; i++) {
            SemanticsNode semanticsNode2 = (SemanticsNode) children.get(i);
            if (getCurrentSemanticsNodes$ui_release().containsKey(Integer.valueOf(semanticsNode2.id)) && !semanticsNodeCopy.children.contains(Integer.valueOf(semanticsNode2.id))) {
                notifySubtreeAppeared(semanticsNode2);
            }
        }
        LinkedHashMap linkedHashMap = this.previousSemanticsNodes;
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            if (!getCurrentSemanticsNodes$ui_release().containsKey(entry.getKey())) {
                int intValue = ((Number) entry.getKey()).intValue();
                Integer valueOf = Integer.valueOf(intValue);
                ArrayMap arrayMap = this.bufferedContentCaptureAppearedNodes;
                if (arrayMap.containsKey(valueOf)) {
                    arrayMap.remove(Integer.valueOf(intValue));
                } else {
                    this.bufferedContentCaptureDisappearedNodes.add(Integer.valueOf(intValue));
                }
            }
        }
        List children2 = semanticsNode.getChildren(false, true);
        int size2 = children2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            SemanticsNode semanticsNode3 = (SemanticsNode) children2.get(i2);
            if (getCurrentSemanticsNodes$ui_release().containsKey(Integer.valueOf(semanticsNode3.id))) {
                int i3 = semanticsNode3.id;
                if (linkedHashMap.containsKey(Integer.valueOf(i3))) {
                    Object obj = linkedHashMap.get(Integer.valueOf(i3));
                    ResultKt.checkNotNull(obj);
                    sendContentCaptureSemanticsStructureChangeEvents$ui_release(semanticsNode3, (SemanticsNodeCopy) obj);
                }
            }
        }
    }

    public final boolean sendEvent(AccessibilityEvent accessibilityEvent) {
        if (!isEnabledForAccessibility()) {
            return false;
        }
        View view = this.view;
        return view.getParent().requestSendAccessibilityEvent(view, accessibilityEvent);
    }

    public final boolean sendEventForVirtualView(int i, int i2, Integer num, List list) {
        if (i == Integer.MIN_VALUE || !isEnabledForAccessibility()) {
            return false;
        }
        AccessibilityEvent createEvent$ui_release = createEvent$ui_release(i, i2);
        if (num != null) {
            createEvent$ui_release.setContentChangeTypes(num.intValue());
        }
        if (list != null) {
            createEvent$ui_release.setContentDescription(_BOUNDARY.fastJoinToString$default(list, ","));
        }
        return sendEvent(createEvent$ui_release);
    }

    public final void sendPaneChangeEvents(int i, int i2, String str) {
        AccessibilityEvent createEvent$ui_release = createEvent$ui_release(semanticsNodeIdToAccessibilityVirtualNodeId(i), 32);
        createEvent$ui_release.setContentChangeTypes(i2);
        if (str != null) {
            createEvent$ui_release.getText().add(str);
        }
        sendEvent(createEvent$ui_release);
    }

    public final void sendPendingTextTraversedAtGranularityEvent(int i) {
        PendingTextTraversedEvent pendingTextTraversedEvent = this.pendingTextTraversedEvent;
        if (pendingTextTraversedEvent != null) {
            SemanticsNode semanticsNode = pendingTextTraversedEvent.node;
            if (i != semanticsNode.id) {
                return;
            }
            if (SystemClock.uptimeMillis() - pendingTextTraversedEvent.traverseTime <= 1000) {
                AccessibilityEvent createEvent$ui_release = createEvent$ui_release(semanticsNodeIdToAccessibilityVirtualNodeId(semanticsNode.id), 131072);
                createEvent$ui_release.setFromIndex(pendingTextTraversedEvent.fromIndex);
                createEvent$ui_release.setToIndex(pendingTextTraversedEvent.toIndex);
                createEvent$ui_release.setAction(pendingTextTraversedEvent.action);
                createEvent$ui_release.setMovementGranularity(pendingTextTraversedEvent.granularity);
                createEvent$ui_release.getText().add(getIterableTextForAccessibility(semanticsNode));
                sendEvent(createEvent$ui_release);
            }
        }
        this.pendingTextTraversedEvent = null;
    }

    public final void sendSubtreeChangeAccessibilityEvents(LayoutNode layoutNode, ArraySet arraySet) {
        SemanticsConfiguration collapsedSemantics$ui_release;
        LayoutNode findClosestParentNode;
        if (layoutNode.isAttached() && !this.view.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().containsKey(layoutNode)) {
            if (!layoutNode.nodes.m190hasH91voCI$ui_release(8)) {
                layoutNode = InvertMatrixKt.findClosestParentNode(layoutNode, InspectableValueKt$NoInspectorInfo$1.INSTANCE$12);
            }
            if (layoutNode == null || (collapsedSemantics$ui_release = layoutNode.getCollapsedSemantics$ui_release()) == null) {
                return;
            }
            if (!collapsedSemantics$ui_release.isMergingSemanticsOfDescendants && (findClosestParentNode = InvertMatrixKt.findClosestParentNode(layoutNode, InspectableValueKt$NoInspectorInfo$1.INSTANCE$11)) != null) {
                layoutNode = findClosestParentNode;
            }
            int i = layoutNode.semanticsId;
            if (arraySet.add(Integer.valueOf(i))) {
                sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(i), 2048, 1, 8);
            }
        }
    }

    public final boolean setAccessibilitySelection(SemanticsNode semanticsNode, int i, int i2, boolean z) {
        String iterableTextForAccessibility;
        SemanticsPropertyKey semanticsPropertyKey = SemanticsActions.SetSelection;
        SemanticsConfiguration semanticsConfiguration = semanticsNode.unmergedConfig;
        if (semanticsConfiguration.contains(semanticsPropertyKey) && InvertMatrixKt.access$enabled(semanticsNode)) {
            Function3 function3 = (Function3) ((AccessibilityAction) semanticsConfiguration.get(semanticsPropertyKey)).action;
            if (function3 != null) {
                return ((Boolean) function3.invoke(Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(z))).booleanValue();
            }
            return false;
        }
        if ((i == i2 && i2 == this.accessibilityCursorPosition) || (iterableTextForAccessibility = getIterableTextForAccessibility(semanticsNode)) == null) {
            return false;
        }
        if (i < 0 || i != i2 || i2 > iterableTextForAccessibility.length()) {
            i = -1;
        }
        this.accessibilityCursorPosition = i;
        boolean z2 = iterableTextForAccessibility.length() > 0;
        int i3 = semanticsNode.id;
        sendEvent(createTextSelectionChangedEvent(semanticsNodeIdToAccessibilityVirtualNodeId(i3), z2 ? Integer.valueOf(this.accessibilityCursorPosition) : null, z2 ? Integer.valueOf(this.accessibilityCursorPosition) : null, z2 ? Integer.valueOf(iterableTextForAccessibility.length()) : null, iterableTextForAccessibility));
        sendPendingTextTraversedAtGranularityEvent(i3);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00da A[LOOP:1: B:8:0x0031->B:21:0x00da, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00e0 A[EDGE_INSN: B:22:0x00e0->B:23:0x00e0 BREAK  A[LOOP:1: B:8:0x0031->B:21:0x00da], SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.util.Comparator] */
    /* JADX WARN: Type inference failed for: r10v4, types: [androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$$inlined$thenBy$1] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.ArrayList subtreeSortedByGeometryGrouping(boolean r21, java.util.ArrayList r22) {
        /*
            r20 = this;
            r0 = r21
            r4 = 1
            java.util.LinkedHashMap r5 = new java.util.LinkedHashMap
            r5.<init>()
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            int r7 = r22.size()
            r9 = 0
        L12:
            if (r9 >= r7) goto L23
            r10 = r22
            java.lang.Object r11 = r10.get(r9)
            androidx.compose.ui.semantics.SemanticsNode r11 = (androidx.compose.ui.semantics.SemanticsNode) r11
            r12 = r20
            subtreeSortedByGeometryGrouping$depthFirstSearch(r12, r6, r5, r0, r11)
            int r9 = r9 + r4
            goto L12
        L23:
            r12 = r20
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            int r9 = kotlin.ResultKt.getLastIndex(r6)
            if (r9 < 0) goto Le2
            r10 = 0
        L31:
            java.lang.Object r11 = r6.get(r10)
            androidx.compose.ui.semantics.SemanticsNode r11 = (androidx.compose.ui.semantics.SemanticsNode) r11
            if (r10 == 0) goto Lbb
            androidx.compose.ui.geometry.Rect r13 = r11.getBoundsInWindow()
            androidx.compose.ui.geometry.Rect r14 = r11.getBoundsInWindow()
            int r15 = kotlin.ResultKt.getLastIndex(r7)
            if (r15 < 0) goto Lbb
            r1 = 0
        L48:
            java.lang.Object r16 = r7.get(r1)
            r2 = r16
            kotlin.Pair r2 = (kotlin.Pair) r2
            java.lang.Object r2 = r2.first
            androidx.compose.ui.geometry.Rect r2 = (androidx.compose.ui.geometry.Rect) r2
            float r8 = r2.top
            float r3 = r2.bottom
            int r17 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r17 < 0) goto L5f
            r18 = r6
            goto Lb3
        L5f:
            float r4 = r13.top
            r18 = r6
            float r6 = r14.bottom
            int r19 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r19 < 0) goto L6a
            goto Lb3
        L6a:
            float r8 = java.lang.Math.max(r8, r4)
            float r19 = java.lang.Math.min(r3, r6)
            int r8 = (r8 > r19 ? 1 : (r8 == r19 ? 0 : -1))
            if (r8 >= 0) goto Lb3
            androidx.compose.ui.geometry.Rect r8 = new androidx.compose.ui.geometry.Rect
            float r13 = r2.left
            r14 = 0
            float r13 = java.lang.Math.max(r13, r14)
            float r14 = r2.top
            float r4 = java.lang.Math.max(r14, r4)
            float r2 = r2.right
            r14 = 2139095040(0x7f800000, float:Infinity)
            float r2 = java.lang.Math.min(r2, r14)
            float r3 = java.lang.Math.min(r3, r6)
            r8.<init>(r13, r4, r2, r3)
            kotlin.Pair r2 = new kotlin.Pair
            java.lang.Object r3 = r7.get(r1)
            kotlin.Pair r3 = (kotlin.Pair) r3
            java.lang.Object r3 = r3.second
            r2.<init>(r8, r3)
            r7.set(r1, r2)
            java.lang.Object r1 = r7.get(r1)
            kotlin.Pair r1 = (kotlin.Pair) r1
            java.lang.Object r1 = r1.second
            java.util.List r1 = (java.util.List) r1
            r1.add(r11)
            r8 = 1
            goto Ld8
        Lb3:
            if (r1 == r15) goto Lbd
            r2 = 1
            int r1 = r1 + r2
            r4 = r2
            r6 = r18
            goto L48
        Lbb:
            r18 = r6
        Lbd:
            androidx.compose.ui.geometry.Rect r1 = r11.getBoundsInWindow()
            kotlin.Pair r2 = new kotlin.Pair
            androidx.compose.ui.semantics.SemanticsNode[] r3 = new androidx.compose.ui.semantics.SemanticsNode[]{r11}
            java.util.ArrayList r4 = new java.util.ArrayList
            kotlin.collections.ArrayAsCollection r6 = new kotlin.collections.ArrayAsCollection
            r8 = 1
            r6.<init>(r3, r8)
            r4.<init>(r6)
            r2.<init>(r1, r4)
            r7.add(r2)
        Ld8:
            if (r10 == r9) goto Le0
            int r10 = r10 + r8
            r4 = r8
            r6 = r18
            goto L31
        Le0:
            r1 = 2
            goto Le4
        Le2:
            r8 = r4
            goto Le0
        Le4:
            kotlin.jvm.functions.Function1[] r2 = new kotlin.jvm.functions.Function1[r1]
            androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1 r1 = androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1.INSTANCE$13
            r3 = 0
            r2[r3] = r1
            androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1 r1 = androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1.INSTANCE$14
            r2[r8] = r1
            kotlin.comparisons.ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda0 r1 = kotlin.ResultKt.compareBy(r2)
            kotlin.collections.CollectionsKt__MutableCollectionsJVMKt.sortWith(r7, r1)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            int r2 = r7.size()
            r3 = 0
        L100:
            if (r3 >= r2) goto L156
            java.lang.Object r4 = r7.get(r3)
            kotlin.Pair r4 = (kotlin.Pair) r4
            java.lang.Object r6 = r4.second
            java.util.List r6 = (java.util.List) r6
            r8 = 4
            kotlin.jvm.functions.Function1[] r9 = new kotlin.jvm.functions.Function1[r8]
            androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1 r10 = androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1.INSTANCE$7
            r11 = 0
            r9[r11] = r10
            androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1 r10 = androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1.INSTANCE$8
            r13 = 1
            r9[r13] = r10
            androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1 r10 = androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1.INSTANCE$9
            r14 = 2
            r9[r14] = r10
            androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1 r10 = androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1.INSTANCE$10
            r15 = 3
            r9[r15] = r10
            kotlin.comparisons.ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda0 r9 = kotlin.ResultKt.compareBy(r9)
            if (r0 == 0) goto L13f
            kotlin.jvm.functions.Function1[] r9 = new kotlin.jvm.functions.Function1[r8]
            androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1 r10 = androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1.INSTANCE$3
            r9[r11] = r10
            androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1 r10 = androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1.INSTANCE$4
            r9[r13] = r10
            androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1 r10 = androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1.INSTANCE$5
            r9[r14] = r10
            androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1 r10 = androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1.INSTANCE$6
            r9[r15] = r10
            kotlin.comparisons.ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda0 r9 = kotlin.ResultKt.compareBy(r9)
        L13f:
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$$inlined$thenBy$1 r10 = new androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$$inlined$thenBy$1
            r10.<init>()
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$$inlined$thenBy$2 r9 = new androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$$inlined$thenBy$2
            r9.<init>()
            kotlin.collections.CollectionsKt__MutableCollectionsJVMKt.sortWith(r6, r9)
            java.lang.Object r4 = r4.second
            java.util.Collection r4 = (java.util.Collection) r4
            r1.addAll(r4)
            r4 = 1
            int r3 = r3 + r4
            goto L100
        L156:
            r4 = 1
            r11 = 0
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sortByGeometryGroupings$$inlined$compareBy$1 r0 = new androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sortByGeometryGroupings$$inlined$compareBy$1
            r0.<init>()
            kotlin.collections.CollectionsKt__MutableCollectionsJVMKt.sortWith(r1, r0)
            r8 = r11
        L161:
            int r0 = kotlin.ResultKt.getLastIndex(r1)
            if (r8 > r0) goto L195
            java.lang.Object r0 = r1.get(r8)
            androidx.compose.ui.semantics.SemanticsNode r0 = (androidx.compose.ui.semantics.SemanticsNode) r0
            int r0 = r0.id
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            java.lang.Object r2 = r5.get(r2)
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L181
            r1.remove(r8)
            r1.addAll(r8, r2)
        L181:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Object r0 = r5.get(r0)
            java.util.List r0 = (java.util.List) r0
            if (r0 == 0) goto L192
            int r2 = r0.size()
            goto L193
        L192:
            r2 = r4
        L193:
            int r8 = r8 + r2
            goto L161
        L195:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.subtreeSortedByGeometryGrouping(boolean, java.util.ArrayList):java.util.ArrayList");
    }
}
