package androidx.compose.ui.node;

import _COROUTINE._BOUNDARY;
import androidx.compose.foundation.FocusablePinnableContainerNode;
import androidx.compose.foundation.relocation.BringIntoViewRequesterNode;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawModifierNodeImpl;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.PinnableContainerKt;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.platform.ScrollObservationScope;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref$ObjectRef;

/* loaded from: classes.dex */
public final class NodeCoordinator$invoke$1 extends Lambda implements Function0 {
    public final /* synthetic */ Object $canvas;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ NodeCoordinator$invoke$1(Object obj, int i, Object obj2) {
        super(0);
        this.$r8$classId = i;
        this.this$0 = obj;
        this.$canvas = obj2;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                m205invoke();
                return unit;
            case 1:
                m205invoke();
                return unit;
            case 2:
                Rect rect = (Rect) this.this$0;
                if (rect != null) {
                    return rect;
                }
                LayoutCoordinates layoutCoordinates = ((BringIntoViewRequesterNode) this.$canvas).layoutCoordinates;
                if (layoutCoordinates == null || !layoutCoordinates.isAttached()) {
                    layoutCoordinates = null;
                }
                if (layoutCoordinates == null) {
                    return null;
                }
                return _BOUNDARY.m4Recttz77jQw(Offset.Zero, ResultKt.m300toSizeozmzZPI(layoutCoordinates.mo159getSizeYbymL2g()));
            case 3:
                m205invoke();
                return unit;
            case 4:
                m205invoke();
                return unit;
            case 5:
            default:
                m205invoke();
                return unit;
            case 6:
                m205invoke();
                return unit;
            case 7:
                m205invoke();
                return unit;
            case 8:
                m205invoke();
                return unit;
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:78:0x016b */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:82:0x0174 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:87:0x0112 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:88:0x0112 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:91:0x017a */
    /* JADX DEBUG: Possible override for method kotlin.jvm.functions.Function0.invoke()Ljava/lang/Object; */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v2, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r3v7, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r3v8, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* renamed from: invoke, reason: collision with other method in class */
    public final void m205invoke() {
        int i = this.$r8$classId;
        int i2 = 0;
        Object obj = this.$canvas;
        Object obj2 = this.this$0;
        switch (i) {
            case 0:
                ((NodeCoordinator) obj2).drawContainedDrawModifiers((Canvas) obj);
                return;
            case 1:
                ((Ref$ObjectRef) obj2).element = Snake.currentValueOf((FocusablePinnableContainerNode) obj, PinnableContainerKt.LocalPinnableContainer);
                return;
            case 2:
            case 5:
            default:
                ((ScrollObservationScope) obj2).getClass();
                return;
            case 3:
                ((CacheDrawModifierNodeImpl) obj2).getClass();
                throw null;
            case 4:
                ((Ref$ObjectRef) obj2).element = ((FocusTargetNode) obj).fetchFocusProperties$ui_release();
                return;
            case 6:
                NodeChain nodeChain = ((LayoutNode) obj2).nodes;
                Ref$ObjectRef ref$ObjectRef = (Ref$ObjectRef) obj;
                if ((nodeChain.head.aggregateChildKindSet & 8) != 0) {
                    for (Modifier.Node node = nodeChain.tail; node != null; node = node.parent) {
                        if ((node.kindSet & 8) != 0) {
                            DelegatingNode delegatingNode = node;
                            ?? r3 = 0;
                            while (delegatingNode != 0) {
                                if (delegatingNode instanceof SemanticsModifierNode) {
                                    SemanticsModifierNode semanticsModifierNode = (SemanticsModifierNode) delegatingNode;
                                    if (semanticsModifierNode.getShouldClearDescendantSemantics()) {
                                        SemanticsConfiguration semanticsConfiguration = new SemanticsConfiguration();
                                        ref$ObjectRef.element = semanticsConfiguration;
                                        semanticsConfiguration.isClearingSemantics = true;
                                    }
                                    if (semanticsModifierNode.getShouldMergeDescendantSemantics()) {
                                        ((SemanticsConfiguration) ref$ObjectRef.element).isMergingSemanticsOfDescendants = true;
                                    }
                                    semanticsModifierNode.applySemantics((SemanticsConfiguration) ref$ObjectRef.element);
                                } else if ((delegatingNode.kindSet & 8) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                    Modifier.Node node2 = delegatingNode.delegate;
                                    int i3 = 0;
                                    delegatingNode = delegatingNode;
                                    r3 = r3;
                                    while (node2 != null) {
                                        if ((node2.kindSet & 8) != 0) {
                                            i3++;
                                            r3 = r3;
                                            if (i3 == 1) {
                                                delegatingNode = node2;
                                            } else {
                                                if (r3 == 0) {
                                                    ?? obj3 = new Object();
                                                    obj3.content = new Modifier.Node[16];
                                                    obj3.size = 0;
                                                    r3 = obj3;
                                                }
                                                if (delegatingNode != 0) {
                                                    r3.add(delegatingNode);
                                                    delegatingNode = 0;
                                                }
                                                r3.add(node2);
                                            }
                                        }
                                        node2 = node2.child;
                                        delegatingNode = delegatingNode;
                                        r3 = r3;
                                    }
                                    if (i3 == 1) {
                                    }
                                }
                                delegatingNode = Snake.access$pop(r3);
                            }
                        }
                    }
                    return;
                }
                return;
            case 7:
                LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = (LayoutNodeLayoutDelegate.LookaheadPassDelegate) obj2;
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
                layoutNodeLayoutDelegate.nextChildLookaheadPlaceOrder = 0;
                MutableVector mutableVector = layoutNodeLayoutDelegate.layoutNode.get_children$ui_release();
                int i4 = mutableVector.size;
                if (i4 > 0) {
                    Object[] objArr = mutableVector.content;
                    int i5 = 0;
                    do {
                        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate2 = ((LayoutNode) objArr[i5]).layoutDelegate.lookaheadPassDelegate;
                        ResultKt.checkNotNull(lookaheadPassDelegate2);
                        lookaheadPassDelegate2.previousPlaceOrder = lookaheadPassDelegate2.placeOrder;
                        lookaheadPassDelegate2.placeOrder = Integer.MAX_VALUE;
                        if (lookaheadPassDelegate2.measuredByParent == 2) {
                            lookaheadPassDelegate2.measuredByParent = 3;
                        }
                        i5++;
                    } while (i5 < i4);
                }
                lookaheadPassDelegate.forEachChildAlignmentLinesOwner(BackwardsCompatNodeKt$onDrawCacheReadsChanged$1.INSTANCE$2);
                ((LookaheadDelegate) obj).getMeasureResult$ui_release().placeChildren();
                MutableVector mutableVector2 = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release();
                int i6 = mutableVector2.size;
                if (i6 > 0) {
                    Object[] objArr2 = mutableVector2.content;
                    do {
                        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate3 = ((LayoutNode) objArr2[i2]).layoutDelegate.lookaheadPassDelegate;
                        ResultKt.checkNotNull(lookaheadPassDelegate3);
                        int i7 = lookaheadPassDelegate3.previousPlaceOrder;
                        int i8 = lookaheadPassDelegate3.placeOrder;
                        if (i7 != i8 && i8 == Integer.MAX_VALUE) {
                            lookaheadPassDelegate3.markSubtreeAsNotPlaced();
                        }
                        i2++;
                    } while (i2 < i6);
                }
                lookaheadPassDelegate.forEachChildAlignmentLinesOwner(BackwardsCompatNodeKt$onDrawCacheReadsChanged$1.INSTANCE$3);
                return;
            case 8:
                LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = (LayoutNodeLayoutDelegate.MeasurePassDelegate) obj2;
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate2 = LayoutNodeLayoutDelegate.this;
                layoutNodeLayoutDelegate2.nextChildPlaceOrder = 0;
                MutableVector mutableVector3 = layoutNodeLayoutDelegate2.layoutNode.get_children$ui_release();
                int i9 = mutableVector3.size;
                if (i9 > 0) {
                    Object[] objArr3 = mutableVector3.content;
                    int i10 = 0;
                    do {
                        LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate2 = ((LayoutNode) objArr3[i10]).layoutDelegate.measurePassDelegate;
                        measurePassDelegate2.previousPlaceOrder = measurePassDelegate2.placeOrder;
                        measurePassDelegate2.placeOrder = Integer.MAX_VALUE;
                        if (measurePassDelegate2.measuredByParent == 2) {
                            measurePassDelegate2.measuredByParent = 3;
                        }
                        i10++;
                    } while (i10 < i9);
                }
                measurePassDelegate.forEachChildAlignmentLinesOwner(BackwardsCompatNodeKt$onDrawCacheReadsChanged$1.INSTANCE$5);
                ((LayoutNode) obj).nodes.innerCoordinator.getMeasureResult$ui_release().placeChildren();
                LayoutNode layoutNode = LayoutNodeLayoutDelegate.this.layoutNode;
                MutableVector mutableVector4 = layoutNode.get_children$ui_release();
                int i11 = mutableVector4.size;
                if (i11 > 0) {
                    Object[] objArr4 = mutableVector4.content;
                    do {
                        LayoutNode layoutNode2 = (LayoutNode) objArr4[i2];
                        if (layoutNode2.layoutDelegate.measurePassDelegate.previousPlaceOrder != layoutNode2.getPlaceOrder$ui_release()) {
                            layoutNode.onZSortedChildrenInvalidated$ui_release();
                            layoutNode.invalidateLayer$ui_release();
                            if (layoutNode2.getPlaceOrder$ui_release() == Integer.MAX_VALUE) {
                                layoutNode2.layoutDelegate.measurePassDelegate.markSubtreeAsNotPlaced$1();
                            }
                        }
                        i2++;
                    } while (i2 < i11);
                }
                measurePassDelegate.forEachChildAlignmentLinesOwner(BackwardsCompatNodeKt$onDrawCacheReadsChanged$1.INSTANCE$6);
                return;
        }
    }
}
