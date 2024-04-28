package kotlin.collections;

import _COROUTINE._BOUNDARY;
import android.content.res.Configuration;
import androidx.compose.foundation.BorderModifierNode;
import androidx.compose.foundation.ClickablePointerInputNode;
import androidx.compose.foundation.text.modifiers.ParagraphLayoutCache;
import androidx.compose.foundation.text.modifiers.TextAnnotatedStringNode;
import androidx.compose.foundation.text.modifiers.TextStringSimpleNode;
import androidx.compose.foundation.text.selection.MouseSelectionObserver;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.collection.IdentityArrayIntMap;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.ConsumedData;
import androidx.compose.ui.input.pointer.PointerEventType;
import androidx.compose.ui.input.pointer.PointerIcon;
import androidx.compose.ui.input.pointer.PointerIconService;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.node.AlignmentLines;
import androidx.compose.ui.node.AlignmentLinesOwner;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.NodeCoordinator$invoke$1;
import androidx.compose.ui.platform.AndroidComposeView$pointerIconService$1;
import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat;
import androidx.compose.ui.platform.AndroidComposeViewVerificationHelperMethodsN;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.DisposableSaveableStateRegistry;
import androidx.compose.ui.platform.ScrollObservationScope;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraph;
import androidx.compose.ui.text.MultiParagraphIntrinsics;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamilyResolverImpl;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.font.TypefaceRequest;
import androidx.compose.ui.text.font.TypefaceResult;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.IntRange;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.sync.SemaphoreImpl;

/* loaded from: classes.dex */
public final class AbstractMap$toString$1 extends Lambda implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ AbstractMap$toString$1(int i, Object obj) {
        super(1);
        this.$r8$classId = i;
        this.this$0 = obj;
    }

    public final Boolean invoke(List list) {
        Density density;
        int i = this.$r8$classId;
        TextLayoutResult textLayoutResult = null;
        Object obj = this.this$0;
        switch (i) {
            case 6:
                ResultKt.checkNotNullParameter(list, "textLayoutResult");
                TextLayoutResult textLayoutResult2 = ((TextAnnotatedStringNode) obj).getLayoutCache().layoutCache;
                if (textLayoutResult2 != null) {
                    list.add(textLayoutResult2);
                    textLayoutResult = textLayoutResult2;
                }
                return Boolean.valueOf(textLayoutResult != null);
            default:
                ResultKt.checkNotNullParameter(list, "textLayoutResult");
                ParagraphLayoutCache layoutCache = ((TextStringSimpleNode) obj).getLayoutCache();
                LayoutDirection layoutDirection = layoutCache.intrinsicsLayoutDirection;
                if (layoutDirection != null && (density = layoutCache.density) != null) {
                    AnnotatedString annotatedString = new AnnotatedString(layoutCache.text);
                    if (layoutCache.paragraph != null && layoutCache.paragraphIntrinsics != null) {
                        long m272copyZbe2FdA$default = Constraints.m272copyZbe2FdA$default(layoutCache.prevConstraints);
                        TextStyle textStyle = layoutCache.style;
                        EmptyList emptyList = EmptyList.INSTANCE;
                        textLayoutResult = new TextLayoutResult(new TextLayoutInput(annotatedString, textStyle, emptyList, layoutCache.maxLines, layoutCache.softWrap, layoutCache.overflow, density, layoutDirection, layoutCache.fontFamilyResolver, m272copyZbe2FdA$default), new MultiParagraph(new MultiParagraphIntrinsics(annotatedString, layoutCache.style, emptyList, density, layoutCache.fontFamilyResolver), m272copyZbe2FdA$default, layoutCache.maxLines, _BOUNDARY.m7equalsimpl0$1(layoutCache.overflow, 2)), layoutCache.layoutSize);
                    }
                }
                if (textLayoutResult != null) {
                    list.add(textLayoutResult);
                }
                return Boolean.FALSE;
        }
    }

    /* renamed from: invoke-k-4lQ0M, reason: not valid java name */
    public final void m301invokek4lQ0M(long j) {
        int i = this.$r8$classId;
        Object obj = this.this$0;
        switch (i) {
            case 2:
                ClickablePointerInputNode clickablePointerInputNode = (ClickablePointerInputNode) obj;
                if (clickablePointerInputNode.enabled) {
                    clickablePointerInputNode.onClick.invoke();
                    return;
                }
                return;
            default:
                ((_BOUNDARY) obj).getClass();
                throw null;
        }
    }

    /* JADX DEBUG: Possible override for method kotlin.jvm.functions.Function1.invoke(Ljava/lang/Object;)Ljava/lang/Object; */
    /* renamed from: invoke, reason: collision with other method in class */
    public final void m302invoke(Object obj) {
        switch (this.$r8$classId) {
            case 9:
                ResultKt.checkNotNullParameter(obj, "state");
                SnapshotStateObserver snapshotStateObserver = (SnapshotStateObserver) this.this$0;
                if (snapshotStateObserver.isPaused) {
                    return;
                }
                synchronized (snapshotStateObserver.observedScopeMaps) {
                    SnapshotStateObserver.ObservedScopeMap observedScopeMap = snapshotStateObserver.currentMap;
                    ResultKt.checkNotNull(observedScopeMap);
                    Object obj2 = observedScopeMap.currentScope;
                    ResultKt.checkNotNull(obj2);
                    int i = observedScopeMap.currentToken;
                    IdentityArrayIntMap identityArrayIntMap = observedScopeMap.currentScopeReads;
                    if (identityArrayIntMap == null) {
                        identityArrayIntMap = new IdentityArrayIntMap();
                        observedScopeMap.currentScopeReads = identityArrayIntMap;
                        observedScopeMap.scopeToValues.set(obj2, identityArrayIntMap);
                    }
                    observedScopeMap.recordRead(obj, i, obj2, identityArrayIntMap);
                }
                return;
            default:
                ResultKt.checkNotNullParameter(obj, "it");
                ((Channel) this.this$0).mo306trySendJP2dKIU(Unit.INSTANCE);
                return;
        }
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Unit unit = Unit.INSTANCE;
        int i = this.$r8$classId;
        Object obj2 = this.this$0;
        switch (i) {
            case 0:
                Map.Entry entry = (Map.Entry) obj;
                ResultKt.checkNotNullParameter(entry, "it");
                AbstractMap abstractMap = (AbstractMap) obj2;
                abstractMap.getClass();
                StringBuilder sb = new StringBuilder();
                Object key = entry.getKey();
                String str = "(this Map)";
                sb.append(key == abstractMap ? "(this Map)" : String.valueOf(key));
                sb.append('=');
                Object value = entry.getValue();
                if (value != abstractMap) {
                    str = String.valueOf(value);
                }
                sb.append(str);
                return sb.toString();
            case 1:
                CacheDrawScope cacheDrawScope = (CacheDrawScope) obj;
                ResultKt.checkNotNullParameter(cacheDrawScope, "$this$CacheDrawModifierNode");
                ((BorderModifierNode) obj2).getClass();
                if (cacheDrawScope.getDensity() * 0.0f < 0.0f) {
                    Object obj3 = new Object();
                    cacheDrawScope.getClass();
                    return obj3;
                }
                cacheDrawScope.getClass();
                throw null;
            case 2:
                m301invokek4lQ0M(((Offset) obj).packedValue);
                return unit;
            case 3:
                PointerInputChange pointerInputChange = (PointerInputChange) obj;
                switch (i) {
                    case 3:
                        ResultKt.checkNotNullParameter(pointerInputChange, "it");
                        Function2 function2 = (Function2) obj2;
                        long m78minusMKHz9U = Offset.m78minusMKHz9U(pointerInputChange.position, pointerInputChange.previousPosition);
                        if (pointerInputChange.isConsumed()) {
                            m78minusMKHz9U = Offset.Zero;
                        }
                        function2.invoke(pointerInputChange, new Offset(m78minusMKHz9U));
                        ConsumedData consumedData = pointerInputChange.consumed;
                        consumedData.downChange = true;
                        consumedData.positionChange = true;
                        return unit;
                    default:
                        ResultKt.checkNotNullParameter(pointerInputChange, "it");
                        ((ResultKt) ((MouseSelectionObserver) obj2)).getClass();
                        throw null;
                }
            case 4:
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                ResultKt.checkNotNullParameter(null, "$this$$receiver");
                throw null;
            case 5:
                m301invokek4lQ0M(((Offset) obj).packedValue);
                return unit;
            case 6:
                return invoke((List) obj);
            case 7:
                return invoke((List) obj);
            case 8:
            case 20:
            default:
                Throwable th = (Throwable) obj;
                switch (i) {
                    case 11:
                        SuspendingPointerInputModifierNodeImpl.PointerEventHandlerCoroutine pointerEventHandlerCoroutine = (SuspendingPointerInputModifierNodeImpl.PointerEventHandlerCoroutine) obj2;
                        CancellableContinuation cancellableContinuation = pointerEventHandlerCoroutine.pointerAwaiter;
                        if (cancellableContinuation != null) {
                            cancellableContinuation.cancel(th);
                        }
                        pointerEventHandlerCoroutine.pointerAwaiter = null;
                        return unit;
                    default:
                        ((SemaphoreImpl) obj2).release();
                        return unit;
                }
            case 9:
                m302invoke(obj);
                return unit;
            case 10:
                PointerIcon pointerIcon = (PointerIcon) obj;
                AndroidComposeView$pointerIconService$1 androidComposeView$pointerIconService$1 = (AndroidComposeView$pointerIconService$1) ((PointerIconService) obj2);
                if (pointerIcon == null) {
                    androidComposeView$pointerIconService$1.getClass();
                    PointerIcon.Companion.getClass();
                    pointerIcon = PointerEventType.pointerIconDefault;
                }
                androidComposeView$pointerIconService$1.getClass();
                AndroidComposeViewVerificationHelperMethodsN.INSTANCE.setPointerIcon(androidComposeView$pointerIconService$1.this$0, pointerIcon);
                return unit;
            case 11:
                Throwable th2 = (Throwable) obj;
                switch (i) {
                    case 11:
                        SuspendingPointerInputModifierNodeImpl.PointerEventHandlerCoroutine pointerEventHandlerCoroutine2 = (SuspendingPointerInputModifierNodeImpl.PointerEventHandlerCoroutine) obj2;
                        CancellableContinuation cancellableContinuation2 = pointerEventHandlerCoroutine2.pointerAwaiter;
                        if (cancellableContinuation2 != null) {
                            cancellableContinuation2.cancel(th2);
                        }
                        pointerEventHandlerCoroutine2.pointerAwaiter = null;
                        return unit;
                    default:
                        ((SemaphoreImpl) obj2).release();
                        return unit;
                }
            case 12:
                AlignmentLinesOwner alignmentLinesOwner = (AlignmentLinesOwner) obj;
                ResultKt.checkNotNullParameter(alignmentLinesOwner, "childOwner");
                if (alignmentLinesOwner.isPlaced()) {
                    if (alignmentLinesOwner.getAlignmentLines().dirty) {
                        alignmentLinesOwner.layoutChildren();
                    }
                    AlignmentLines alignmentLines = (AlignmentLines) obj2;
                    for (Map.Entry entry2 : alignmentLinesOwner.getAlignmentLines().alignmentLineMap.entrySet()) {
                        AlignmentLines.access$addAlignmentLine(alignmentLines, (AlignmentLine) entry2.getKey(), ((Number) entry2.getValue()).intValue(), alignmentLinesOwner.getInnerCoordinator());
                    }
                    NodeCoordinator nodeCoordinator = alignmentLinesOwner.getInnerCoordinator().wrappedBy;
                    ResultKt.checkNotNull(nodeCoordinator);
                    while (!ResultKt.areEqual(nodeCoordinator, alignmentLines.alignmentLinesOwner.getInnerCoordinator())) {
                        for (AlignmentLine alignmentLine : alignmentLines.getAlignmentLinesMap(nodeCoordinator).keySet()) {
                            AlignmentLines.access$addAlignmentLine(alignmentLines, alignmentLine, alignmentLines.getPositionFor(nodeCoordinator, alignmentLine), nodeCoordinator);
                        }
                        nodeCoordinator = nodeCoordinator.wrappedBy;
                        ResultKt.checkNotNull(nodeCoordinator);
                    }
                }
                return unit;
            case 13:
                Modifier.Element element = (Modifier.Element) obj;
                ResultKt.checkNotNullParameter(element, "it");
                ((MutableVector) obj2).add(element);
                return Boolean.TRUE;
            case 14:
                ScrollObservationScope scrollObservationScope = (ScrollObservationScope) obj;
                ResultKt.checkNotNullParameter(scrollObservationScope, "it");
                AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = (AndroidComposeViewAccessibilityDelegateCompat) obj2;
                int[] iArr = AndroidComposeViewAccessibilityDelegateCompat.AccessibilityActionsResourceIds;
                androidComposeViewAccessibilityDelegateCompat.getClass();
                if (scrollObservationScope.allScopes.contains(scrollObservationScope)) {
                    androidComposeViewAccessibilityDelegateCompat.view.getSnapshotObserver().observeReads$ui_release(scrollObservationScope, androidComposeViewAccessibilityDelegateCompat.sendScrollEventIfNeededLambda, new NodeCoordinator$invoke$1(scrollObservationScope, 9, androidComposeViewAccessibilityDelegateCompat));
                }
                return unit;
            case 15:
                Configuration configuration = (Configuration) obj;
                ResultKt.checkNotNullParameter(configuration, "it");
                Configuration configuration2 = new Configuration(configuration);
                DynamicProvidableCompositionLocal dynamicProvidableCompositionLocal = AndroidCompositionLocals_androidKt.LocalConfiguration;
                ((MutableState) obj2).setValue(configuration2);
                return unit;
            case 16:
                ResultKt.checkNotNullParameter((DisposableEffectScope) obj, "$this$DisposableEffect");
                final DisposableSaveableStateRegistry disposableSaveableStateRegistry = (DisposableSaveableStateRegistry) obj2;
                return new DisposableEffectResult() { // from class: androidx.compose.ui.platform.AndroidCompositionLocals_androidKt$ProvideAndroidCompositionLocals$2$invoke$$inlined$onDispose$1
                    @Override // androidx.compose.runtime.DisposableEffectResult
                    public final void dispose() {
                        DisposableSaveableStateRegistry.this.onDispose.invoke();
                    }
                };
            case 17:
                m302invoke(obj);
                return unit;
            case 18:
                SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj;
                ResultKt.checkNotNullParameter(semanticsPropertyReceiver, "$this$fakeSemanticsNode");
                SemanticsPropertiesKt.m240setRolekuIjeqM(semanticsPropertyReceiver, ((Role) obj2).value);
                return unit;
            case 19:
                TypefaceRequest typefaceRequest = (TypefaceRequest) obj;
                ResultKt.checkNotNullParameter(typefaceRequest, "it");
                FontWeight fontWeight = typefaceRequest.fontWeight;
                ResultKt.checkNotNullParameter(fontWeight, "fontWeight");
                return ((TypefaceResult.Immutable) ((FontFamilyResolverImpl) obj2).resolve(new TypefaceRequest(null, fontWeight, typefaceRequest.fontStyle, typefaceRequest.fontSynthesis, typefaceRequest.resourceLoaderCacheKey))).value;
            case 21:
                return obj == ((AbstractCollection) obj2) ? "(this Collection)" : String.valueOf(obj);
            case 22:
                IntRange intRange = (IntRange) obj;
                ResultKt.checkNotNullParameter(intRange, "it");
                CharSequence charSequence = (CharSequence) obj2;
                ResultKt.checkNotNullParameter(charSequence, "<this>");
                return charSequence.subSequence(intRange.first, intRange.last + 1).toString();
        }
    }
}
