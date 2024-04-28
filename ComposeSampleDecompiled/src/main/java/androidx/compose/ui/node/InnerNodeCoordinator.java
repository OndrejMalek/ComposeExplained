package androidx.compose.ui.node;

import android.graphics.Paint;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.platform.AndroidComposeView;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class InnerNodeCoordinator extends NodeCoordinator {
    public static final AndroidPaint innerBoundsPaint;
    public LookaheadDelegate lookaheadDelegate;
    public final TailModifierNode tail;

    /* loaded from: classes.dex */
    public final class LookaheadDelegateImpl extends LookaheadDelegate {
        public final /* synthetic */ int $r8$classId = 0;
        public final /* synthetic */ NodeCoordinator this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LookaheadDelegateImpl(LayoutModifierNodeCoordinator layoutModifierNodeCoordinator) {
            super(layoutModifierNodeCoordinator);
            this.this$0 = layoutModifierNodeCoordinator;
        }

        @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
        public final int calculateAlignmentLine(AlignmentLine alignmentLine) {
            LinkedHashMap linkedHashMap = this.cachedAlignmentLinesMap;
            switch (this.$r8$classId) {
                case 0:
                    ResultKt.checkNotNullParameter(alignmentLine, "alignmentLine");
                    LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = this.coordinator.layoutNode.layoutDelegate.lookaheadPassDelegate;
                    ResultKt.checkNotNull(lookaheadPassDelegate);
                    LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
                    int i = layoutNodeLayoutDelegate.layoutState;
                    LookaheadAlignmentLines lookaheadAlignmentLines = lookaheadPassDelegate.alignmentLines;
                    if (i == 2) {
                        lookaheadAlignmentLines.usedByModifierMeasurement = true;
                        if (lookaheadAlignmentLines.dirty) {
                            layoutNodeLayoutDelegate.lookaheadLayoutPending = true;
                            layoutNodeLayoutDelegate.lookaheadLayoutPendingForAlignment = true;
                        }
                    } else {
                        lookaheadAlignmentLines.usedByModifierLayout = true;
                    }
                    LookaheadDelegate lookaheadDelegate = lookaheadPassDelegate.getInnerCoordinator().lookaheadDelegate;
                    if (lookaheadDelegate != null) {
                        lookaheadDelegate.isPlacingForAlignment = true;
                    }
                    lookaheadPassDelegate.layoutChildren();
                    LookaheadDelegate lookaheadDelegate2 = lookaheadPassDelegate.getInnerCoordinator().lookaheadDelegate;
                    if (lookaheadDelegate2 != null) {
                        lookaheadDelegate2.isPlacingForAlignment = false;
                    }
                    Integer num = (Integer) lookaheadAlignmentLines.alignmentLineMap.get(alignmentLine);
                    int intValue = num != null ? num.intValue() : Integer.MIN_VALUE;
                    linkedHashMap.put(alignmentLine, Integer.valueOf(intValue));
                    return intValue;
                default:
                    ResultKt.checkNotNullParameter(alignmentLine, "alignmentLine");
                    int access$calculateAlignmentAndPlaceChildAsNeeded = Snake.access$calculateAlignmentAndPlaceChildAsNeeded(this, alignmentLine);
                    linkedHashMap.put(alignmentLine, Integer.valueOf(access$calculateAlignmentAndPlaceChildAsNeeded));
                    return access$calculateAlignmentAndPlaceChildAsNeeded;
            }
        }

        @Override // androidx.compose.ui.layout.Measurable
        /* renamed from: measure-BRTryo0 */
        public final Placeable mo164measureBRTryo0(long j) {
            List asMutableList;
            switch (this.$r8$classId) {
                case 0:
                    m167setMeasurementConstraintsBRTryo0(j);
                    NodeCoordinator nodeCoordinator = this.coordinator;
                    MutableVector mutableVector = nodeCoordinator.layoutNode.get_children$ui_release();
                    int i = mutableVector.size;
                    if (i > 0) {
                        Object[] objArr = mutableVector.content;
                        int i2 = 0;
                        do {
                            LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = ((LayoutNode) objArr[i2]).layoutDelegate.lookaheadPassDelegate;
                            ResultKt.checkNotNull(lookaheadPassDelegate);
                            lookaheadPassDelegate.measuredByParent = 3;
                            i2++;
                        } while (i2 < i);
                    }
                    LayoutNode layoutNode = nodeCoordinator.layoutNode;
                    MeasurePolicy measurePolicy = layoutNode.measurePolicy;
                    LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate2 = layoutNode.layoutDelegate.lookaheadPassDelegate;
                    ResultKt.checkNotNull(lookaheadPassDelegate2);
                    LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
                    layoutNodeLayoutDelegate.layoutNode.getChildren$ui_release();
                    boolean z = lookaheadPassDelegate2.childDelegatesDirty;
                    MutableVector mutableVector2 = lookaheadPassDelegate2._childDelegates;
                    if (z) {
                        LayoutNode layoutNode2 = layoutNodeLayoutDelegate.layoutNode;
                        MutableVector mutableVector3 = layoutNode2.get_children$ui_release();
                        int i3 = mutableVector3.size;
                        if (i3 > 0) {
                            Object[] objArr2 = mutableVector3.content;
                            int i4 = 0;
                            do {
                                LayoutNode layoutNode3 = (LayoutNode) objArr2[i4];
                                if (mutableVector2.size <= i4) {
                                    LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate3 = layoutNode3.layoutDelegate.lookaheadPassDelegate;
                                    ResultKt.checkNotNull(lookaheadPassDelegate3);
                                    mutableVector2.add(lookaheadPassDelegate3);
                                } else {
                                    LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate4 = layoutNode3.layoutDelegate.lookaheadPassDelegate;
                                    ResultKt.checkNotNull(lookaheadPassDelegate4);
                                    Object[] objArr3 = mutableVector2.content;
                                    Object obj = objArr3[i4];
                                    objArr3[i4] = lookaheadPassDelegate4;
                                }
                                i4++;
                            } while (i4 < i3);
                        }
                        mutableVector2.removeRange(layoutNode2.getChildren$ui_release().size(), mutableVector2.size);
                        lookaheadPassDelegate2.childDelegatesDirty = false;
                        asMutableList = mutableVector2.asMutableList();
                    } else {
                        asMutableList = mutableVector2.asMutableList();
                    }
                    LookaheadDelegate.access$set_measureResult(this, measurePolicy.mo34measure3p2s80s(this, asMutableList, j));
                    return this;
                default:
                    LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = (LayoutModifierNodeCoordinator) this.this$0;
                    m167setMeasurementConstraintsBRTryo0(j);
                    layoutModifierNodeCoordinator.getClass();
                    LayoutModifierNode layoutModifierNode = layoutModifierNodeCoordinator.layoutModifierNode;
                    NodeCoordinator nodeCoordinator2 = layoutModifierNodeCoordinator.wrapped;
                    ResultKt.checkNotNull(nodeCoordinator2);
                    LookaheadDelegate lookaheadDelegate = nodeCoordinator2.getLookaheadDelegate();
                    ResultKt.checkNotNull(lookaheadDelegate);
                    LookaheadDelegate.access$set_measureResult(this, layoutModifierNode.mo35measure3p2s80s(this, lookaheadDelegate, j));
                    return this;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LookaheadDelegateImpl(InnerNodeCoordinator innerNodeCoordinator) {
            super(innerNodeCoordinator);
            this.this$0 = innerNodeCoordinator;
        }
    }

    static {
        AndroidPaint Paint = Brush.Paint();
        Paint.m92setColor8_81llA(Color.Red);
        Paint paint = Paint.internalPaint;
        ResultKt.checkNotNullParameter(paint, "<this>");
        paint.setStrokeWidth(1.0f);
        Paint.m95setStylek9PVt8s(1);
        innerBoundsPaint = Paint;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.ui.node.TailModifierNode, androidx.compose.ui.Modifier$Node] */
    public InnerNodeCoordinator(LayoutNode layoutNode) {
        super(layoutNode);
        ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
        ?? node = new Modifier.Node();
        node.aggregateChildKindSet = 0;
        this.tail = node;
        node.coordinator = this;
        this.lookaheadDelegate = layoutNode.lookaheadRoot != null ? new LookaheadDelegateImpl(this) : null;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final int calculateAlignmentLine(AlignmentLine alignmentLine) {
        ResultKt.checkNotNullParameter(alignmentLine, "alignmentLine");
        LookaheadDelegate lookaheadDelegate = this.lookaheadDelegate;
        if (lookaheadDelegate != null) {
            return lookaheadDelegate.calculateAlignmentLine(alignmentLine);
        }
        LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = this.layoutNode.layoutDelegate.measurePassDelegate;
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
        int i = layoutNodeLayoutDelegate.layoutState;
        LookaheadAlignmentLines lookaheadAlignmentLines = measurePassDelegate.alignmentLines;
        if (i == 1) {
            lookaheadAlignmentLines.usedByModifierMeasurement = true;
            if (lookaheadAlignmentLines.dirty) {
                layoutNodeLayoutDelegate.layoutPending = true;
                layoutNodeLayoutDelegate.layoutPendingForAlignment = true;
            }
        } else {
            lookaheadAlignmentLines.usedByModifierLayout = true;
        }
        measurePassDelegate.getInnerCoordinator().isPlacingForAlignment = true;
        measurePassDelegate.layoutChildren();
        measurePassDelegate.getInnerCoordinator().isPlacingForAlignment = false;
        Integer num = (Integer) lookaheadAlignmentLines.alignmentLineMap.get(alignmentLine);
        if (num != null) {
            return num.intValue();
        }
        return Integer.MIN_VALUE;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final void ensureLookaheadDelegateCreated() {
        if (this.lookaheadDelegate == null) {
            this.lookaheadDelegate = new LookaheadDelegateImpl(this);
        }
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final LookaheadDelegate getLookaheadDelegate() {
        return this.lookaheadDelegate;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final Modifier.Node getTail() {
        return this.tail;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:47:0x012a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:54:0x0133 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:58:0x013a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:60:0x00e7 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:61:0x00e7 */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0033, code lost:
    
        if ((!(r5 != null && r5.isClearingSemantics)) != false) goto L13;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x001b. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v2, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v6, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v8, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v9 */
    @Override // androidx.compose.ui.node.NodeCoordinator
    /* renamed from: hitTestChild-YqVAtuI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void mo171hitTestChildYqVAtuI(androidx.compose.ui.node.NodeCoordinator.HitTestSource r19, long r20, androidx.compose.ui.node.HitTestResult r22, boolean r23, boolean r24) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r11 = r22
            java.lang.String r4 = "hitTestSource"
            kotlin.ResultKt.checkNotNullParameter(r1, r4)
            java.lang.String r4 = "hitTestResult"
            kotlin.ResultKt.checkNotNullParameter(r11, r4)
            kotlin.ULong$Companion r1 = (kotlin.ULong.Companion) r1
            r13 = 1
            androidx.compose.ui.node.LayoutNode r4 = r0.layoutNode
            int r1 = r1.$r8$classId
            java.lang.String r5 = "parentLayoutNode"
            switch(r1) {
                case 0: goto L22;
                default: goto L1e;
            }
        L1e:
            kotlin.ResultKt.checkNotNullParameter(r4, r5)
            goto L35
        L22:
            kotlin.ResultKt.checkNotNullParameter(r4, r5)
            androidx.compose.ui.semantics.SemanticsConfiguration r5 = r4.getCollapsedSemantics$ui_release()
            if (r5 == 0) goto L31
            boolean r5 = r5.isClearingSemantics
            if (r5 != r13) goto L31
            r5 = r13
            goto L32
        L31:
            r5 = 0
        L32:
            r5 = r5 ^ r13
            if (r5 == 0) goto L58
        L35:
            boolean r5 = r0.m203withinLayerBoundsk4lQ0M(r2)
            if (r5 == 0) goto L3f
            r14 = r24
            r5 = r13
            goto L5b
        L3f:
            if (r23 == 0) goto L58
            long r5 = r18.m195getMinimumTouchTargetSizeNHjbRc()
            float r5 = r0.m193distanceInMinimumTouchTargettz77jQw(r2, r5)
            boolean r6 = java.lang.Float.isInfinite(r5)
            if (r6 != 0) goto L58
            boolean r5 = java.lang.Float.isNaN(r5)
            if (r5 != 0) goto L58
            r5 = r13
            r14 = 0
            goto L5b
        L58:
            r14 = r24
            r5 = 0
        L5b:
            if (r5 == 0) goto L15d
            int r15 = r11.hitDepth
            androidx.compose.runtime.collection.MutableVector r4 = r4.getZSortedChildren()
            int r5 = r4.size
            if (r5 <= 0) goto L15b
            int r5 = r5 - r13
            java.lang.Object[] r10 = r4.content
            r16 = r5
        L6c:
            r4 = r10[r16]
            androidx.compose.ui.node.LayoutNode r4 = (androidx.compose.ui.node.LayoutNode) r4
            boolean r5 = r4.isPlaced()
            if (r5 == 0) goto L14f
            androidx.compose.ui.node.NodeChain r9 = r4.nodes
            switch(r1) {
                case 0: goto L91;
                default: goto L7b;
            }
        L7b:
            androidx.compose.ui.node.NodeCoordinator r4 = r9.outerCoordinator
            long r6 = r4.m194fromParentPositionMKHz9U(r2)
            androidx.compose.ui.node.NodeCoordinator r4 = r9.outerCoordinator
            kotlin.ULong$Companion r5 = androidx.compose.ui.node.NodeCoordinator.PointerInputSource
            r8 = r22
            r12 = r9
            r9 = r23
            r17 = r10
            r10 = r14
            r4.m199hitTestYqVAtuI(r5, r6, r8, r9, r10)
            goto La5
        L91:
            r12 = r9
            r17 = r10
            androidx.compose.ui.node.NodeCoordinator r4 = r12.outerCoordinator
            long r6 = r4.m194fromParentPositionMKHz9U(r2)
            androidx.compose.ui.node.NodeCoordinator r4 = r12.outerCoordinator
            r9 = 1
            kotlin.ULong$Companion r5 = androidx.compose.ui.node.NodeCoordinator.SemanticsSource
            r8 = r22
            r10 = r14
            r4.m199hitTestYqVAtuI(r5, r6, r8, r9, r10)
        La5:
            long r4 = r22.m170findBestHitDistanceptXAw2c()
            r6 = 32
            long r6 = r4 >> r6
            int r6 = (int) r6
            float r6 = java.lang.Float.intBitsToFloat(r6)
            r7 = 0
            int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r6 >= 0) goto L151
            r6 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r4 = r4 & r6
            int r4 = (int) r4
            if (r4 == 0) goto L151
            androidx.compose.ui.node.NodeCoordinator r4 = r12.outerCoordinator
            r5 = 16
            boolean r6 = androidx.compose.ui.node.Snake.m214getIncludeSelfInTraversalH91voCI(r5)
            androidx.compose.ui.Modifier$Node r4 = r4.headNode(r6)
            if (r4 != 0) goto Ld0
            goto L15b
        Ld0:
            androidx.compose.ui.Modifier$Node r4 = r4.node
            boolean r6 = r4.isAttached
            if (r6 == 0) goto L143
            int r6 = r4.aggregateChildKindSet
            r6 = r6 & r5
            if (r6 == 0) goto L15b
            androidx.compose.ui.Modifier$Node r4 = r4.child
        Ldd:
            if (r4 == 0) goto L15b
            int r6 = r4.kindSet
            r6 = r6 & r5
            if (r6 == 0) goto L13f
            r6 = 0
            r7 = r4
            r8 = r6
        Le7:
            if (r7 == 0) goto L13f
            boolean r9 = r7 instanceof androidx.compose.ui.node.PointerInputModifierNode
            if (r9 == 0) goto Lfd
            androidx.compose.ui.node.PointerInputModifierNode r7 = (androidx.compose.ui.node.PointerInputModifierNode) r7
            boolean r7 = r7.sharePointerInputWithSiblings()
            if (r7 == 0) goto Lfb
            int r4 = r11.size
            int r4 = r4 - r13
            r11.hitDepth = r4
            goto L151
        Lfb:
            r12 = 0
            goto L13a
        Lfd:
            int r9 = r7.kindSet
            r9 = r9 & r5
            if (r9 == 0) goto Lfb
            boolean r9 = r7 instanceof androidx.compose.ui.node.DelegatingNode
            if (r9 == 0) goto Lfb
            r9 = r7
            androidx.compose.ui.node.DelegatingNode r9 = (androidx.compose.ui.node.DelegatingNode) r9
            androidx.compose.ui.Modifier$Node r9 = r9.delegate
            r10 = 0
        L10c:
            if (r9 == 0) goto L136
            int r12 = r9.kindSet
            r12 = r12 & r5
            if (r12 == 0) goto L118
            int r10 = r10 + 1
            if (r10 != r13) goto L11a
            r7 = r9
        L118:
            r12 = 0
            goto L133
        L11a:
            if (r8 != 0) goto L129
            androidx.compose.runtime.collection.MutableVector r8 = new androidx.compose.runtime.collection.MutableVector
            androidx.compose.ui.Modifier$Node[] r12 = new androidx.compose.ui.Modifier.Node[r5]
            r8.<init>()
            r8.content = r12
            r12 = 0
            r8.size = r12
            goto L12a
        L129:
            r12 = 0
        L12a:
            if (r7 == 0) goto L130
            r8.add(r7)
            r7 = r6
        L130:
            r8.add(r9)
        L133:
            androidx.compose.ui.Modifier$Node r9 = r9.child
            goto L10c
        L136:
            r12 = 0
            if (r10 != r13) goto L13a
            goto Le7
        L13a:
            androidx.compose.ui.Modifier$Node r7 = androidx.compose.ui.node.Snake.access$pop(r8)
            goto Le7
        L13f:
            r12 = 0
            androidx.compose.ui.Modifier$Node r4 = r4.child
            goto Ldd
        L143:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "visitLocalDescendants called on an unattached node"
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L14f:
            r17 = r10
        L151:
            r12 = 0
            int r16 = r16 + (-1)
            if (r16 >= 0) goto L157
            goto L15b
        L157:
            r10 = r17
            goto L6c
        L15b:
            r11.hitDepth = r15
        L15d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.InnerNodeCoordinator.mo171hitTestChildYqVAtuI(androidx.compose.ui.node.NodeCoordinator$HitTestSource, long, androidx.compose.ui.node.HitTestResult, boolean, boolean):void");
    }

    @Override // androidx.compose.ui.layout.Measurable
    /* renamed from: measure-BRTryo0 */
    public final Placeable mo164measureBRTryo0(long j) {
        List asMutableList;
        m167setMeasurementConstraintsBRTryo0(j);
        LayoutNode layoutNode = this.layoutNode;
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = ((LayoutNode) objArr[i2]).layoutDelegate.measurePassDelegate;
                measurePassDelegate.getClass();
                measurePassDelegate.measuredByParent = 3;
                i2++;
            } while (i2 < i);
        }
        MeasurePolicy measurePolicy = layoutNode.measurePolicy;
        LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate2 = layoutNode.layoutDelegate.measurePassDelegate;
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
        layoutNodeLayoutDelegate.layoutNode.updateChildrenIfDirty$ui_release();
        boolean z = measurePassDelegate2.childDelegatesDirty;
        MutableVector mutableVector2 = measurePassDelegate2._childDelegates;
        if (z) {
            LayoutNode layoutNode2 = layoutNodeLayoutDelegate.layoutNode;
            MutableVector mutableVector3 = layoutNode2.get_children$ui_release();
            int i3 = mutableVector3.size;
            if (i3 > 0) {
                Object[] objArr2 = mutableVector3.content;
                int i4 = 0;
                do {
                    LayoutNode layoutNode3 = (LayoutNode) objArr2[i4];
                    if (mutableVector2.size <= i4) {
                        mutableVector2.add(layoutNode3.layoutDelegate.measurePassDelegate);
                    } else {
                        LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate3 = layoutNode3.layoutDelegate.measurePassDelegate;
                        Object[] objArr3 = mutableVector2.content;
                        Object obj = objArr3[i4];
                        objArr3[i4] = measurePassDelegate3;
                    }
                    i4++;
                } while (i4 < i3);
            }
            mutableVector2.removeRange(layoutNode2.getChildren$ui_release().size(), mutableVector2.size);
            measurePassDelegate2.childDelegatesDirty = false;
            asMutableList = mutableVector2.asMutableList();
        } else {
            asMutableList = mutableVector2.asMutableList();
        }
        setMeasureResult$ui_release(measurePolicy.mo34measure3p2s80s(this, asMutableList, j));
        onMeasured();
        return this;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final void performDraw(Canvas canvas) {
        ResultKt.checkNotNullParameter(canvas, "canvas");
        LayoutNode layoutNode = this.layoutNode;
        Owner requireOwner = Snake.requireOwner(layoutNode);
        MutableVector zSortedChildren = layoutNode.getZSortedChildren();
        int i = zSortedChildren.size;
        if (i > 0) {
            Object[] objArr = zSortedChildren.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode2 = (LayoutNode) objArr[i2];
                if (layoutNode2.isPlaced()) {
                    layoutNode2.nodes.outerCoordinator.draw(canvas);
                }
                i2++;
            } while (i2 < i);
        }
        if (((AndroidComposeView) requireOwner).getShowLayoutBounds()) {
            drawBorder(canvas, innerBoundsPaint);
        }
    }

    @Override // androidx.compose.ui.layout.Placeable
    /* renamed from: placeAt-f8xVGno */
    public final void mo165placeAtf8xVGno(long j, float f, Function1 function1) {
        m200placeSelff8xVGno(j, f, function1);
        if (this.isShallowPlacing) {
            return;
        }
        onPlaced();
        this.layoutNode.layoutDelegate.measurePassDelegate.onNodePlaced$ui_release();
    }
}
