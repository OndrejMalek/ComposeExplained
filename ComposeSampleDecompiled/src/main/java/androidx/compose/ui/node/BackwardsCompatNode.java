package androidx.compose.ui.node;

import androidx.compose.foundation.IndicationModifier;
import androidx.compose.material3.MinimumInteractiveComponentSizeModifier;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.BuildDrawCacheParams;
import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusProperties;
import androidx.compose.ui.focus.FocusPropertiesModifierNode;
import androidx.compose.ui.focus.FocusRequesterModifierNode;
import androidx.compose.ui.focus.FocusStateImpl;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.PointerIconModifierLocal;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.modifier.BackwardsCompatLocalMap;
import androidx.compose.ui.modifier.EmptyMap;
import androidx.compose.ui.modifier.ModifierLocalConsumer;
import androidx.compose.ui.modifier.ModifierLocalManager;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.modifier.ModifierLocalProvider;
import androidx.compose.ui.modifier.ModifierLocalReadScope;
import androidx.compose.ui.modifier.ProvidableModifierLocal;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.AppendedSemanticsElement;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsModifier;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Function;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class BackwardsCompatNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode, SemanticsModifierNode, PointerInputModifierNode, ModifierLocalModifierNode, ModifierLocalReadScope, ParentDataModifierNode, LayoutAwareModifierNode, GlobalPositionAwareModifierNode, FocusEventModifierNode, FocusPropertiesModifierNode, FocusRequesterModifierNode, OwnerScope, BuildDrawCacheParams {
    public BackwardsCompatLocalMap _providedValues;
    public Modifier.Element element;
    public HashSet readValues;

    @Override // androidx.compose.ui.focus.FocusPropertiesModifierNode
    public final void applyFocusProperties(FocusProperties focusProperties) {
        throw new IllegalStateException("Check failed.".toString());
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final void applySemantics(SemanticsConfiguration semanticsConfiguration) {
        ResultKt.checkNotNullParameter(semanticsConfiguration, "<this>");
        Modifier.Element element = this.element;
        ResultKt.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.semantics.SemanticsModifier");
        AppendedSemanticsElement appendedSemanticsElement = (AppendedSemanticsElement) ((SemanticsModifier) element);
        SemanticsConfiguration semanticsConfiguration2 = new SemanticsConfiguration();
        semanticsConfiguration2.isMergingSemanticsOfDescendants = appendedSemanticsElement.mergeDescendants;
        appendedSemanticsElement.properties.invoke(semanticsConfiguration2);
        if (semanticsConfiguration2.isMergingSemanticsOfDescendants) {
            semanticsConfiguration.isMergingSemanticsOfDescendants = true;
        }
        if (semanticsConfiguration2.isClearingSemantics) {
            semanticsConfiguration.isClearingSemantics = true;
        }
        for (Map.Entry entry : semanticsConfiguration2.props.entrySet()) {
            SemanticsPropertyKey semanticsPropertyKey = (SemanticsPropertyKey) entry.getKey();
            Object value = entry.getValue();
            LinkedHashMap linkedHashMap = semanticsConfiguration.props;
            if (!linkedHashMap.containsKey(semanticsPropertyKey)) {
                linkedHashMap.put(semanticsPropertyKey, value);
            } else if (value instanceof AccessibilityAction) {
                Object obj = linkedHashMap.get(semanticsPropertyKey);
                ResultKt.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.ui.semantics.AccessibilityAction<*>");
                AccessibilityAction accessibilityAction = (AccessibilityAction) obj;
                String str = accessibilityAction.label;
                if (str == null) {
                    str = ((AccessibilityAction) value).label;
                }
                Function function = accessibilityAction.action;
                if (function == null) {
                    function = ((AccessibilityAction) value).action;
                }
                linkedHashMap.put(semanticsPropertyKey, new AccessibilityAction(str, function));
            }
        }
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void draw(ContentDrawScope contentDrawScope) {
        ResultKt.checkNotNullParameter(contentDrawScope, "<this>");
        Modifier.Element element = this.element;
        ResultKt.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.draw.DrawModifier");
        ((IndicationModifier) ((DrawModifier) element)).indicationInstance.drawIndication(contentDrawScope);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:30:0x0077 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:34:0x0080 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:39:0x002d */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:40:0x002d */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:43:0x0086 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r4v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    @Override // androidx.compose.ui.modifier.ModifierLocalModifierNode
    public final Object getCurrent(ProvidableModifierLocal providableModifierLocal) {
        NodeChain nodeChain;
        ResultKt.checkNotNullParameter(providableModifierLocal, "<this>");
        this.readValues.add(providableModifierLocal);
        Modifier.Node node = this.node;
        if (!node.isAttached) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node2 = node.parent;
        LayoutNode requireLayoutNode = Snake.requireLayoutNode(this);
        while (requireLayoutNode != null) {
            if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 32) != 0) {
                while (node2 != null) {
                    if ((node2.kindSet & 32) != 0) {
                        DelegatingNode delegatingNode = node2;
                        ?? r4 = 0;
                        while (delegatingNode != 0) {
                            if (delegatingNode instanceof ModifierLocalModifierNode) {
                                ModifierLocalModifierNode modifierLocalModifierNode = (ModifierLocalModifierNode) delegatingNode;
                                if (modifierLocalModifierNode.getProvidedValues().contains$ui_release(providableModifierLocal)) {
                                    return modifierLocalModifierNode.getProvidedValues().get$ui_release(providableModifierLocal);
                                }
                            } else if ((delegatingNode.kindSet & 32) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                Modifier.Node node3 = delegatingNode.delegate;
                                int i = 0;
                                delegatingNode = delegatingNode;
                                r4 = r4;
                                while (node3 != null) {
                                    if ((node3.kindSet & 32) != 0) {
                                        i++;
                                        r4 = r4;
                                        if (i == 1) {
                                            delegatingNode = node3;
                                        } else {
                                            if (r4 == 0) {
                                                ?? obj = new Object();
                                                obj.content = new Modifier.Node[16];
                                                obj.size = 0;
                                                r4 = obj;
                                            }
                                            if (delegatingNode != 0) {
                                                r4.add(delegatingNode);
                                                delegatingNode = 0;
                                            }
                                            r4.add(node3);
                                        }
                                    }
                                    node3 = node3.child;
                                    delegatingNode = delegatingNode;
                                    r4 = r4;
                                }
                                if (i == 1) {
                                }
                            }
                            delegatingNode = Snake.access$pop(r4);
                        }
                    }
                    node2 = node2.parent;
                }
            }
            requireLayoutNode = requireLayoutNode.getParent$ui_release();
            node2 = (requireLayoutNode == null || (nodeChain = requireLayoutNode.nodes) == null) ? null : nodeChain.tail;
        }
        return providableModifierLocal.defaultFactory.invoke();
    }

    @Override // androidx.compose.ui.draw.BuildDrawCacheParams
    public final LayoutDirection getLayoutDirection() {
        return Snake.requireLayoutNode(this).layoutDirection;
    }

    @Override // androidx.compose.ui.modifier.ModifierLocalModifierNode
    public final ResultKt getProvidedValues() {
        BackwardsCompatLocalMap backwardsCompatLocalMap = this._providedValues;
        return backwardsCompatLocalMap != null ? backwardsCompatLocalMap : EmptyMap.INSTANCE;
    }

    @Override // androidx.compose.ui.draw.BuildDrawCacheParams
    /* renamed from: getSize-NH-jbRc */
    public final long mo63getSizeNHjbRc() {
        return ResultKt.m300toSizeozmzZPI(Snake.m215requireCoordinator64DMado(this, 128).measuredSize);
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [java.lang.Object, androidx.compose.ui.modifier.BackwardsCompatLocalMap] */
    public final void initializeModifier(boolean z) {
        if (!this.isAttached) {
            throw new IllegalStateException("Check failed.".toString());
        }
        Modifier.Element element = this.element;
        if ((this.kindSet & 32) != 0) {
            if (element instanceof ModifierLocalConsumer) {
                ((AndroidComposeView) Snake.requireOwner(this)).registerOnEndApplyChangesListener(new BackwardsCompatNode$initializeModifier$1(this, 0));
            }
            if (element instanceof ModifierLocalProvider) {
                ModifierLocalProvider modifierLocalProvider = (ModifierLocalProvider) element;
                BackwardsCompatLocalMap backwardsCompatLocalMap = this._providedValues;
                if (backwardsCompatLocalMap != null) {
                    PointerIconModifierLocal pointerIconModifierLocal = (PointerIconModifierLocal) modifierLocalProvider;
                    pointerIconModifierLocal.getClass();
                    if (backwardsCompatLocalMap.contains$ui_release(null)) {
                        backwardsCompatLocalMap.getClass();
                        ModifierLocalManager modifierLocalManager = ((AndroidComposeView) Snake.requireOwner(this)).getModifierLocalManager();
                        modifierLocalManager.getClass();
                        pointerIconModifierLocal.getClass();
                        ResultKt.checkNotNullParameter(null, "key");
                        modifierLocalManager.inserted.add(this);
                        modifierLocalManager.insertedLocal.add(null);
                        modifierLocalManager.invalidate();
                    }
                }
                ResultKt.checkNotNullParameter(modifierLocalProvider, "element");
                this._providedValues = new Object();
                TailModifierNode tailModifierNode = Snake.requireLayoutNode(this).nodes.tail;
                ResultKt.checkNotNull(tailModifierNode, "null cannot be cast to non-null type androidx.compose.ui.node.TailModifierNode");
                if (tailModifierNode.attachHasBeenRun) {
                    ModifierLocalManager modifierLocalManager2 = ((AndroidComposeView) Snake.requireOwner(this)).getModifierLocalManager();
                    modifierLocalManager2.getClass();
                    ((PointerIconModifierLocal) modifierLocalProvider).getClass();
                    ResultKt.checkNotNullParameter(null, "key");
                    modifierLocalManager2.inserted.add(this);
                    modifierLocalManager2.insertedLocal.add(null);
                    modifierLocalManager2.invalidate();
                }
            }
        }
        if ((this.kindSet & 4) != 0 && !z) {
            Snake.invalidateLayer(this);
        }
        if ((this.kindSet & 2) != 0) {
            TailModifierNode tailModifierNode2 = Snake.requireLayoutNode(this).nodes.tail;
            ResultKt.checkNotNull(tailModifierNode2, "null cannot be cast to non-null type androidx.compose.ui.node.TailModifierNode");
            if (tailModifierNode2.attachHasBeenRun) {
                NodeCoordinator nodeCoordinator = this.coordinator;
                ResultKt.checkNotNull(nodeCoordinator);
                ((LayoutModifierNodeCoordinator) nodeCoordinator).layoutModifierNode = this;
                OwnedLayer ownedLayer = nodeCoordinator.layer;
                if (ownedLayer != null) {
                    ownedLayer.invalidate();
                }
            }
            if (!z) {
                Snake.invalidateLayer(this);
                Snake.requireLayoutNode(this).invalidateMeasurements$ui_release();
            }
        }
        if ((this.kindSet & 8) != 0) {
            ((AndroidComposeView) Snake.requireOwner(this)).onSemanticsChange();
        }
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final boolean interceptOutOfBoundsChildEvents() {
        Modifier.Element element = this.element;
        ResultKt.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(element);
        throw null;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public final boolean isValidOwnerScope() {
        return this.isAttached;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureScope$layout$1 mo35measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        ResultKt.checkNotNullParameter(measureScope, "$this$measure");
        Modifier.Element element = this.element;
        ResultKt.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.layout.LayoutModifier");
        final Placeable mo164measureBRTryo0 = measurable.mo164measureBRTryo0(j);
        int i = mo164measureBRTryo0.width;
        long j2 = DpSize.Unspecified;
        long j3 = ((MinimumInteractiveComponentSizeModifier) element).size;
        if (j3 == j2) {
            throw new IllegalStateException("DpSize is unspecified".toString());
        }
        final int max = Math.max(i, measureScope.mo180roundToPx0680j_4(Float.intBitsToFloat((int) (j3 >> 32))));
        int i2 = mo164measureBRTryo0.height;
        if (j3 == j2) {
            throw new IllegalStateException("DpSize is unspecified".toString());
        }
        final int max2 = Math.max(i2, measureScope.mo180roundToPx0680j_4(Float.intBitsToFloat((int) (4294967295L & j3))));
        return MeasureScope.layout$default(measureScope, max, max2, new Function1() { // from class: androidx.compose.material3.MinimumInteractiveComponentSizeModifier$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
                ResultKt.checkNotNullParameter(placementScope, "$this$layout");
                Placeable.PlacementScope.place$default(placementScope, mo164measureBRTryo0, ResultKt.roundToInt((max - r0.width) / 2.0f), ResultKt.roundToInt((max2 - r0.height) / 2.0f));
                return Unit.INSTANCE;
            }
        });
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        initializeModifier(true);
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final void onCancelPointerInput() {
        Modifier.Element element = this.element;
        ResultKt.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(element);
        throw null;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        unInitializeModifier();
    }

    @Override // androidx.compose.ui.focus.FocusEventModifierNode
    public final void onFocusEvent(FocusStateImpl focusStateImpl) {
        throw new IllegalStateException("Check failed.".toString());
    }

    @Override // androidx.compose.ui.node.GlobalPositionAwareModifierNode
    public final void onGloballyPositioned(NodeCoordinator nodeCoordinator) {
        Modifier.Element element = this.element;
        ResultKt.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.layout.OnGloballyPositionedModifier");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(element);
        throw null;
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void onMeasureResultChanged() {
        Snake.invalidateDraw(this);
    }

    @Override // androidx.compose.ui.node.LayoutAwareModifierNode
    public final void onPlaced(NodeCoordinator nodeCoordinator) {
        ResultKt.checkNotNullParameter(nodeCoordinator, "coordinates");
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* renamed from: onPointerEvent-H0pRuoY */
    public final void mo25onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j) {
        Modifier.Element element = this.element;
        ResultKt.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(element);
        throw null;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final boolean sharePointerInputWithSiblings() {
        Modifier.Element element = this.element;
        ResultKt.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.input.pointer.PointerInputModifier");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(element);
        throw null;
    }

    public final String toString() {
        return this.element.toString();
    }

    public final void unInitializeModifier() {
        if (!this.isAttached) {
            throw new IllegalStateException("Check failed.".toString());
        }
        Modifier.Element element = this.element;
        if ((this.kindSet & 32) != 0) {
            if (element instanceof ModifierLocalProvider) {
                ModifierLocalManager modifierLocalManager = ((AndroidComposeView) Snake.requireOwner(this)).getModifierLocalManager();
                ((PointerIconModifierLocal) ((ModifierLocalProvider) element)).getClass();
                modifierLocalManager.getClass();
                ResultKt.checkNotNullParameter(null, "key");
                modifierLocalManager.removed.add(Snake.requireLayoutNode(this));
                modifierLocalManager.removedLocal.add(null);
                modifierLocalManager.invalidate();
            }
            if (element instanceof ModifierLocalConsumer) {
                ((PointerIconModifierLocal) ((ModifierLocalConsumer) element)).onModifierLocalsUpdated(Snake.DetachedModifierLocalReadScope);
            }
        }
        if ((this.kindSet & 8) != 0) {
            ((AndroidComposeView) Snake.requireOwner(this)).onSemanticsChange();
        }
    }

    public final void updateModifierLocalConsumer() {
        if (this.isAttached) {
            this.readValues.clear();
            ((AndroidComposeView) Snake.requireOwner(this)).getSnapshotObserver().observeReads$ui_release(this, BackwardsCompatNodeKt$onDrawCacheReadsChanged$1.INSTANCE$1, new BackwardsCompatNode$initializeModifier$1(this, 1));
        }
    }
}
