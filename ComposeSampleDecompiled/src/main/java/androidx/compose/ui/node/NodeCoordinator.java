package androidx.compose.ui.node;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.view.View;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.GraphicsLayerScopeKt;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LookaheadLayoutCoordinatesImpl;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.AndroidTextToolbar;
import androidx.compose.ui.platform.DrawChildContainer;
import androidx.compose.ui.platform.RenderNodeLayer;
import androidx.compose.ui.platform.ViewLayer;
import androidx.compose.ui.platform.WeakCache;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityImpl;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import java.lang.ref.Reference;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.ULong;
import kotlin.Unit;
import kotlin.collections.MapsKt___MapsJvmKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public abstract class NodeCoordinator extends LookaheadCapablePlaceable implements Measurable, LayoutCoordinates, OwnerScope, Function1 {
    public static final ULong.Companion PointerInputSource;
    public static final ULong.Companion SemanticsSource;
    public static final ReusableGraphicsLayerScope graphicsLayerScope;
    public static final LayerPositionalProperties tmpLayerPositionalProperties;
    public MeasureScope$layout$1 _measureResult;
    public MutableRect _rectCache;
    public final LayoutNode$_foldedChildren$1 invalidateParentLayer;
    public boolean isClipping;
    public float lastLayerAlpha;
    public boolean lastLayerDrawingWasSkipped;
    public OwnedLayer layer;
    public Function1 layerBlock;
    public Density layerDensity;
    public LayoutDirection layerLayoutDirection;
    public LayerPositionalProperties layerPositionalProperties;
    public final LayoutNode layoutNode;
    public LinkedHashMap oldAlignmentLines;
    public long position;
    public boolean released;
    public NodeCoordinator wrapped;
    public NodeCoordinator wrappedBy;
    public float zIndex;

    /* loaded from: classes.dex */
    public interface HitTestSource {
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, androidx.compose.ui.graphics.ReusableGraphicsLayerScope] */
    static {
        ?? obj = new Object();
        obj.scaleX = 1.0f;
        obj.scaleY = 1.0f;
        obj.alpha = 1.0f;
        long j = GraphicsLayerScopeKt.DefaultShadowColor;
        obj.ambientShadowColor = j;
        obj.spotShadowColor = j;
        obj.cameraDistance = 8.0f;
        obj.transformOrigin = TransformOrigin.Center;
        obj.shape = Brush.RectangleShape;
        obj.compositingStrategy = 0;
        int i = Size.$r8$clinit;
        obj.graphicsDensity = new DensityImpl(1.0f, 1.0f);
        graphicsLayerScope = obj;
        tmpLayerPositionalProperties = new LayerPositionalProperties();
        PointerInputSource = new ULong.Companion(1);
        SemanticsSource = new ULong.Companion(0);
    }

    public NodeCoordinator(LayoutNode layoutNode) {
        ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
        this.layoutNode = layoutNode;
        this.layerDensity = layoutNode.density;
        this.layerLayoutDirection = layoutNode.layoutDirection;
        this.lastLayerAlpha = 0.8f;
        this.position = IntOffset.Zero;
        this.invalidateParentLayer = new LayoutNode$_foldedChildren$1(9, this);
    }

    public final void ancestorToLocal(NodeCoordinator nodeCoordinator, MutableRect mutableRect, boolean z) {
        if (nodeCoordinator == this) {
            return;
        }
        NodeCoordinator nodeCoordinator2 = this.wrappedBy;
        if (nodeCoordinator2 != null) {
            nodeCoordinator2.ancestorToLocal(nodeCoordinator, mutableRect, z);
        }
        long j = this.position;
        int i = IntOffset.$r8$clinit;
        float f = (int) (j >> 32);
        mutableRect.left -= f;
        mutableRect.right -= f;
        float f2 = (int) (j & 4294967295L);
        mutableRect.top -= f2;
        mutableRect.bottom -= f2;
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.mapBounds(mutableRect, true);
            if (this.isClipping && z) {
                long j2 = this.measuredSize;
                mutableRect.intersect(0.0f, 0.0f, (int) (j2 >> 32), (int) (j2 & 4294967295L));
            }
        }
    }

    /* renamed from: ancestorToLocal-R5De75A, reason: not valid java name */
    public final long m191ancestorToLocalR5De75A(NodeCoordinator nodeCoordinator, long j) {
        if (nodeCoordinator == this) {
            return j;
        }
        NodeCoordinator nodeCoordinator2 = this.wrappedBy;
        return (nodeCoordinator2 == null || ResultKt.areEqual(nodeCoordinator, nodeCoordinator2)) ? m194fromParentPositionMKHz9U(j) : m194fromParentPositionMKHz9U(nodeCoordinator2.m191ancestorToLocalR5De75A(nodeCoordinator, j));
    }

    /* renamed from: calculateMinimumTouchTargetPadding-E7KxVPU, reason: not valid java name */
    public final long m192calculateMinimumTouchTargetPaddingE7KxVPU(long j) {
        return _BOUNDARY.Size(Math.max(0.0f, (Size.m85getWidthimpl(j) - getMeasuredWidth()) / 2.0f), Math.max(0.0f, (Size.m83getHeightimpl(j) - ((int) (this.measuredSize & 4294967295L))) / 2.0f));
    }

    /* renamed from: distanceInMinimumTouchTarget-tz77jQw, reason: not valid java name */
    public final float m193distanceInMinimumTouchTargettz77jQw(long j, long j2) {
        if (getMeasuredWidth() >= Size.m85getWidthimpl(j2) && ((int) (this.measuredSize & 4294967295L)) >= Size.m83getHeightimpl(j2)) {
            return Float.POSITIVE_INFINITY;
        }
        long m192calculateMinimumTouchTargetPaddingE7KxVPU = m192calculateMinimumTouchTargetPaddingE7KxVPU(j2);
        float m85getWidthimpl = Size.m85getWidthimpl(m192calculateMinimumTouchTargetPaddingE7KxVPU);
        float m83getHeightimpl = Size.m83getHeightimpl(m192calculateMinimumTouchTargetPaddingE7KxVPU);
        float m76getXimpl = Offset.m76getXimpl(j);
        float max = Math.max(0.0f, m76getXimpl < 0.0f ? -m76getXimpl : m76getXimpl - getMeasuredWidth());
        float m77getYimpl = Offset.m77getYimpl(j);
        long Offset = _BOUNDARY.Offset(max, Math.max(0.0f, m77getYimpl < 0.0f ? -m77getYimpl : m77getYimpl - ((int) (4294967295L & this.measuredSize))));
        if ((m85getWidthimpl > 0.0f || m83getHeightimpl > 0.0f) && Offset.m76getXimpl(Offset) <= m85getWidthimpl && Offset.m77getYimpl(Offset) <= m83getHeightimpl) {
            return (Offset.m77getYimpl(Offset) * Offset.m77getYimpl(Offset)) + (Offset.m76getXimpl(Offset) * Offset.m76getXimpl(Offset));
        }
        return Float.POSITIVE_INFINITY;
    }

    public final void draw(Canvas canvas) {
        ResultKt.checkNotNullParameter(canvas, "canvas");
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.drawLayer(canvas);
            return;
        }
        long j = this.position;
        int i = IntOffset.$r8$clinit;
        float f = (int) (j >> 32);
        float f2 = (int) (j & 4294967295L);
        canvas.translate(f, f2);
        drawContainedDrawModifiers(canvas);
        canvas.translate(-f, -f2);
    }

    public final void drawBorder(Canvas canvas, AndroidPaint androidPaint) {
        ResultKt.checkNotNullParameter(canvas, "canvas");
        ResultKt.checkNotNullParameter(androidPaint, "paint");
        long j = this.measuredSize;
        canvas.drawRect(0.5f, 0.5f, ((int) (j >> 32)) - 0.5f, ((int) (j & 4294967295L)) - 0.5f, androidPaint);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:30:0x0072 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:35:0x002b */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:36:0x002b */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:39:0x0078 */
    /* JADX WARN: Type inference failed for: r12v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public final void drawContainedDrawModifiers(Canvas canvas) {
        Modifier.Node m196headH91voCI = m196headH91voCI(4);
        if (m196headH91voCI == null) {
            performDraw(canvas);
            return;
        }
        LayoutNode layoutNode = this.layoutNode;
        layoutNode.getClass();
        LayoutNodeDrawScope sharedDrawScope = ((AndroidComposeView) Snake.requireOwner(layoutNode)).getSharedDrawScope();
        long m300toSizeozmzZPI = ResultKt.m300toSizeozmzZPI(this.measuredSize);
        sharedDrawScope.getClass();
        ResultKt.checkNotNullParameter(canvas, "canvas");
        MutableVector mutableVector = null;
        while (m196headH91voCI != null) {
            if (m196headH91voCI instanceof DrawModifierNode) {
                sharedDrawScope.m176drawDirectx_KDEd0$ui_release(canvas, m300toSizeozmzZPI, this, (DrawModifierNode) m196headH91voCI);
            } else if ((m196headH91voCI.kindSet & 4) != 0 && (m196headH91voCI instanceof DelegatingNode)) {
                Modifier.Node node = ((DelegatingNode) m196headH91voCI).delegate;
                int i = 0;
                mutableVector = mutableVector;
                while (node != null) {
                    if ((node.kindSet & 4) != 0) {
                        i++;
                        mutableVector = mutableVector;
                        if (i == 1) {
                            m196headH91voCI = node;
                        } else {
                            if (mutableVector == null) {
                                ?? obj = new Object();
                                obj.content = new Modifier.Node[16];
                                obj.size = 0;
                                mutableVector = obj;
                            }
                            if (m196headH91voCI != null) {
                                mutableVector.add(m196headH91voCI);
                                m196headH91voCI = null;
                            }
                            mutableVector.add(node);
                        }
                    }
                    node = node.child;
                    mutableVector = mutableVector;
                }
                if (i == 1) {
                }
            }
            m196headH91voCI = Snake.access$pop(mutableVector);
        }
    }

    public abstract void ensureLookaheadDelegateCreated();

    public final NodeCoordinator findCommonAncestor$ui_release(NodeCoordinator nodeCoordinator) {
        ResultKt.checkNotNullParameter(nodeCoordinator, "other");
        LayoutNode layoutNode = this.layoutNode;
        LayoutNode layoutNode2 = nodeCoordinator.layoutNode;
        if (layoutNode2 == layoutNode) {
            Modifier.Node tail = nodeCoordinator.getTail();
            Modifier.Node node = getTail().node;
            if (!node.isAttached) {
                throw new IllegalStateException("visitLocalAncestors called on an unattached node".toString());
            }
            for (Modifier.Node node2 = node.parent; node2 != null; node2 = node2.parent) {
                if ((node2.kindSet & 2) != 0 && node2 == tail) {
                    return nodeCoordinator;
                }
            }
            return this;
        }
        LayoutNode layoutNode3 = layoutNode2;
        while (layoutNode3.depth > layoutNode.depth) {
            layoutNode3 = layoutNode3.getParent$ui_release();
            ResultKt.checkNotNull(layoutNode3);
        }
        LayoutNode layoutNode4 = layoutNode;
        while (layoutNode4.depth > layoutNode3.depth) {
            layoutNode4 = layoutNode4.getParent$ui_release();
            ResultKt.checkNotNull(layoutNode4);
        }
        while (layoutNode3 != layoutNode4) {
            layoutNode3 = layoutNode3.getParent$ui_release();
            layoutNode4 = layoutNode4.getParent$ui_release();
            if (layoutNode3 == null || layoutNode4 == null) {
                throw new IllegalArgumentException("layouts are not part of the same hierarchy");
            }
        }
        return layoutNode4 == layoutNode ? this : layoutNode3 == layoutNode2 ? nodeCoordinator : layoutNode3.nodes.innerCoordinator;
    }

    /* renamed from: fromParentPosition-MK-Hz9U, reason: not valid java name */
    public final long m194fromParentPositionMKHz9U(long j) {
        long j2 = this.position;
        float m76getXimpl = Offset.m76getXimpl(j);
        int i = IntOffset.$r8$clinit;
        long Offset = _BOUNDARY.Offset(m76getXimpl - ((int) (j2 >> 32)), Offset.m77getYimpl(j) - ((int) (j2 & 4294967295L)));
        OwnedLayer ownedLayer = this.layer;
        return ownedLayer != null ? ownedLayer.mo207mapOffset8S9VItk(Offset, true) : Offset;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final LookaheadCapablePlaceable getChild() {
        return this.wrapped;
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getDensity() {
        return this.layoutNode.density.getDensity();
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getFontScale() {
        return this.layoutNode.density.getFontScale();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final boolean getHasMeasureResult() {
        return this._measureResult != null;
    }

    @Override // androidx.compose.ui.layout.MeasureScope
    public final LayoutDirection getLayoutDirection() {
        return this.layoutNode.layoutDirection;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final LayoutNode getLayoutNode() {
        return this.layoutNode;
    }

    public abstract LookaheadDelegate getLookaheadDelegate();

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final MeasureScope$layout$1 getMeasureResult$ui_release() {
        MeasureScope$layout$1 measureScope$layout$1 = this._measureResult;
        if (measureScope$layout$1 != null) {
            return measureScope$layout$1;
        }
        throw new IllegalStateException("Asking for measurement result of unmeasured layout modifier".toString());
    }

    /* renamed from: getMinimumTouchTargetSize-NH-jbRc, reason: not valid java name */
    public final long m195getMinimumTouchTargetSizeNHjbRc() {
        return this.layerDensity.mo33toSizeXkaWNTQ(this.layoutNode.viewConfiguration.mo174getMinimumTouchTargetSizeMYxV2XQ());
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final LookaheadCapablePlaceable getParent() {
        return this.wrappedBy;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:27:0x0050 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:31:0x0059 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:36:0x001d */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:37:0x001d */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:40:0x005f */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r5v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    @Override // androidx.compose.ui.layout.Measurable
    public final Object getParentData() {
        LayoutNode layoutNode = this.layoutNode;
        if (layoutNode.nodes.m190hasH91voCI$ui_release(64)) {
            getTail();
            for (Modifier.Node node = layoutNode.nodes.tail; node != null; node = node.parent) {
                if ((node.kindSet & 64) != 0) {
                    DelegatingNode delegatingNode = node;
                    ?? r5 = 0;
                    while (delegatingNode != 0) {
                        if (delegatingNode instanceof ParentDataModifierNode) {
                            ResultKt.checkNotNullParameter(layoutNode.density, "<this>");
                            Modifier.Element element = ((BackwardsCompatNode) ((ParentDataModifierNode) delegatingNode)).element;
                            ResultKt.checkNotNull(element, "null cannot be cast to non-null type androidx.compose.ui.layout.ParentDataModifier");
                            DurationKt$$ExternalSyntheticCheckNotZero0.m(element);
                            throw null;
                        }
                        if ((delegatingNode.kindSet & 64) != 0 && (delegatingNode instanceof DelegatingNode)) {
                            Modifier.Node node2 = delegatingNode.delegate;
                            int i = 0;
                            delegatingNode = delegatingNode;
                            r5 = r5;
                            while (node2 != null) {
                                if ((node2.kindSet & 64) != 0) {
                                    i++;
                                    r5 = r5;
                                    if (i == 1) {
                                        delegatingNode = node2;
                                    } else {
                                        if (r5 == 0) {
                                            ?? obj = new Object();
                                            obj.content = new Modifier.Node[16];
                                            obj.size = 0;
                                            r5 = obj;
                                        }
                                        if (delegatingNode != 0) {
                                            r5.add(delegatingNode);
                                            delegatingNode = 0;
                                        }
                                        r5.add(node2);
                                    }
                                }
                                node2 = node2.child;
                                delegatingNode = delegatingNode;
                                r5 = r5;
                            }
                            if (i == 1) {
                            }
                        }
                        delegatingNode = Snake.access$pop(r5);
                    }
                }
            }
        }
        return null;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final LayoutCoordinates getParentLayoutCoordinates() {
        if (!isAttached()) {
            throw new IllegalStateException("LayoutCoordinate operations are only valid when isAttached is true".toString());
        }
        onCoordinatesUsed$ui_release();
        return this.layoutNode.nodes.outerCoordinator.wrappedBy;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    /* renamed from: getPosition-nOcc-ac */
    public final long mo185getPositionnOccac() {
        return this.position;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: getSize-YbymL2g */
    public final long mo159getSizeYbymL2g() {
        return this.measuredSize;
    }

    public abstract Modifier.Node getTail();

    /* renamed from: head-H91voCI, reason: not valid java name */
    public final Modifier.Node m196headH91voCI(int i) {
        boolean m214getIncludeSelfInTraversalH91voCI = Snake.m214getIncludeSelfInTraversalH91voCI(i);
        Modifier.Node tail = getTail();
        if (!m214getIncludeSelfInTraversalH91voCI && (tail = tail.parent) == null) {
            return null;
        }
        for (Modifier.Node headNode = headNode(m214getIncludeSelfInTraversalH91voCI); headNode != null && (headNode.aggregateChildKindSet & i) != 0; headNode = headNode.child) {
            if ((headNode.kindSet & i) != 0) {
                return headNode;
            }
            if (headNode == tail) {
                return null;
            }
        }
        return null;
    }

    public final Modifier.Node headNode(boolean z) {
        Modifier.Node tail;
        NodeChain nodeChain = this.layoutNode.nodes;
        if (nodeChain.outerCoordinator == this) {
            return nodeChain.head;
        }
        if (z) {
            NodeCoordinator nodeCoordinator = this.wrappedBy;
            if (nodeCoordinator != null && (tail = nodeCoordinator.getTail()) != null) {
                return tail.child;
            }
        } else {
            NodeCoordinator nodeCoordinator2 = this.wrappedBy;
            if (nodeCoordinator2 != null) {
                return nodeCoordinator2.getTail();
            }
        }
        return null;
    }

    /* renamed from: hit-1hIXUjU, reason: not valid java name */
    public final void m197hit1hIXUjU(final Modifier.Node node, final HitTestSource hitTestSource, final long j, final HitTestResult hitTestResult, final boolean z, final boolean z2) {
        if (node == null) {
            mo171hitTestChildYqVAtuI(hitTestSource, j, hitTestResult, z, z2);
            return;
        }
        Function0 function0 = new Function0() { // from class: androidx.compose.ui.node.NodeCoordinator$hit$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                int i;
                switch (((ULong.Companion) hitTestSource).$r8$classId) {
                    case 0:
                        i = 8;
                        break;
                    default:
                        i = 16;
                        break;
                }
                NodeCoordinator.this.m197hit1hIXUjU(Snake.m211access$nextUntilhw7D004(node, i), hitTestSource, j, hitTestResult, z, z2);
                return Unit.INSTANCE;
            }
        };
        hitTestResult.getClass();
        hitTestResult.hitInMinimumTouchTarget(node, -1.0f, z2, function0);
    }

    /* renamed from: hitNear-JHbHoSQ, reason: not valid java name */
    public final void m198hitNearJHbHoSQ(Modifier.Node node, HitTestSource hitTestSource, long j, HitTestResult hitTestResult, boolean z, boolean z2, float f) {
        if (node == null) {
            mo171hitTestChildYqVAtuI(hitTestSource, j, hitTestResult, z, z2);
        } else {
            hitTestResult.hitInMinimumTouchTarget(node, f, z2, new NodeCoordinator$hitNear$1(this, node, hitTestSource, j, hitTestResult, z, z2, f, 0));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x00d4, code lost:
    
        if (androidx.compose.ui.node.Snake.m212compareToS_HNhKs(r18.m170findBestHitDistanceptXAw2c(), androidx.compose.ui.node.Snake.access$DistanceAndInLayer(r8, r20)) > 0) goto L43;
     */
    /* renamed from: hitTest-YqVAtuI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m199hitTestYqVAtuI(androidx.compose.ui.node.NodeCoordinator.HitTestSource r15, long r16, androidx.compose.ui.node.HitTestResult r18, boolean r19, boolean r20) {
        /*
            r14 = this;
            r9 = r14
            r2 = r15
            r3 = r16
            r5 = r18
            java.lang.String r0 = "hitTestSource"
            kotlin.ResultKt.checkNotNullParameter(r15, r0)
            java.lang.String r0 = "hitTestResult"
            kotlin.ResultKt.checkNotNullParameter(r5, r0)
            r0 = r2
            kotlin.ULong$Companion r0 = (kotlin.ULong.Companion) r0
            int r0 = r0.$r8$classId
            switch(r0) {
                case 0: goto L1b;
                default: goto L18;
            }
        L18:
            r0 = 16
            goto L1d
        L1b:
            r0 = 8
        L1d:
            androidx.compose.ui.Modifier$Node r1 = r14.m196headH91voCI(r0)
            boolean r0 = r14.m203withinLayerBoundsk4lQ0M(r3)
            if (r0 != 0) goto L63
            if (r19 == 0) goto Lf3
            long r6 = r14.m195getMinimumTouchTargetSizeNHjbRc()
            float r8 = r14.m193distanceInMinimumTouchTargettz77jQw(r3, r6)
            boolean r0 = java.lang.Float.isInfinite(r8)
            if (r0 != 0) goto Lf3
            boolean r0 = java.lang.Float.isNaN(r8)
            if (r0 != 0) goto Lf3
            int r0 = r5.hitDepth
            int r6 = kotlin.ResultKt.getLastIndex(r18)
            if (r0 != r6) goto L46
            goto L55
        L46:
            r0 = 0
            long r6 = androidx.compose.ui.node.Snake.access$DistanceAndInLayer(r8, r0)
            long r10 = r18.m170findBestHitDistanceptXAw2c()
            int r0 = androidx.compose.ui.node.Snake.m212compareToS_HNhKs(r10, r6)
            if (r0 <= 0) goto Lf3
        L55:
            r7 = 0
            r0 = r14
            r2 = r15
            r3 = r16
            r5 = r18
            r6 = r19
            r0.m198hitNearJHbHoSQ(r1, r2, r3, r5, r6, r7, r8)
            goto Lf3
        L63:
            if (r1 != 0) goto L6a
            r14.mo171hitTestChildYqVAtuI(r15, r16, r18, r19, r20)
            goto Lf3
        L6a:
            float r0 = androidx.compose.ui.geometry.Offset.m76getXimpl(r16)
            float r6 = androidx.compose.ui.geometry.Offset.m77getYimpl(r16)
            r7 = 0
            int r8 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r8 < 0) goto La0
            int r7 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
            if (r7 < 0) goto La0
            int r7 = r14.getMeasuredWidth()
            float r7 = (float) r7
            int r0 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r0 >= 0) goto La0
            long r7 = r9.measuredSize
            r10 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r7 = r7 & r10
            int r0 = (int) r7
            float r0 = (float) r0
            int r0 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r0 >= 0) goto La0
            r0 = r14
            r2 = r15
            r3 = r16
            r5 = r18
            r6 = r19
            r7 = r20
            r0.m197hit1hIXUjU(r1, r2, r3, r5, r6, r7)
            goto Lf3
        La0:
            if (r19 != 0) goto La6
            r0 = 2139095040(0x7f800000, float:Infinity)
        La4:
            r8 = r0
            goto Laf
        La6:
            long r6 = r14.m195getMinimumTouchTargetSizeNHjbRc()
            float r0 = r14.m193distanceInMinimumTouchTargettz77jQw(r3, r6)
            goto La4
        Laf:
            boolean r0 = java.lang.Float.isInfinite(r8)
            if (r0 != 0) goto Le4
            boolean r0 = java.lang.Float.isNaN(r8)
            if (r0 != 0) goto Le4
            int r0 = r5.hitDepth
            int r6 = kotlin.ResultKt.getLastIndex(r18)
            if (r0 != r6) goto Lc6
            r7 = r20
            goto Ld6
        Lc6:
            r7 = r20
            long r10 = androidx.compose.ui.node.Snake.access$DistanceAndInLayer(r8, r7)
            long r12 = r18.m170findBestHitDistanceptXAw2c()
            int r0 = androidx.compose.ui.node.Snake.m212compareToS_HNhKs(r12, r10)
            if (r0 <= 0) goto Le6
        Ld6:
            r0 = r14
            r2 = r15
            r3 = r16
            r5 = r18
            r6 = r19
            r7 = r20
            r0.m198hitNearJHbHoSQ(r1, r2, r3, r5, r6, r7, r8)
            goto Lf3
        Le4:
            r7 = r20
        Le6:
            r0 = r14
            r2 = r15
            r3 = r16
            r5 = r18
            r6 = r19
            r7 = r20
            r0.m201speculativeHitJHbHoSQ(r1, r2, r3, r5, r6, r7, r8)
        Lf3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.NodeCoordinator.m199hitTestYqVAtuI(androidx.compose.ui.node.NodeCoordinator$HitTestSource, long, androidx.compose.ui.node.HitTestResult, boolean, boolean):void");
    }

    /* renamed from: hitTestChild-YqVAtuI */
    public void mo171hitTestChildYqVAtuI(HitTestSource hitTestSource, long j, HitTestResult hitTestResult, boolean z, boolean z2) {
        ResultKt.checkNotNullParameter(hitTestSource, "hitTestSource");
        ResultKt.checkNotNullParameter(hitTestResult, "hitTestResult");
        NodeCoordinator nodeCoordinator = this.wrapped;
        if (nodeCoordinator != null) {
            nodeCoordinator.m199hitTestYqVAtuI(hitTestSource, nodeCoordinator.m194fromParentPositionMKHz9U(j), hitTestResult, z, z2);
        }
    }

    public final void invalidateLayer() {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.invalidate();
            return;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (nodeCoordinator != null) {
            nodeCoordinator.invalidateLayer();
        }
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Canvas canvas = (Canvas) obj;
        ResultKt.checkNotNullParameter(canvas, "canvas");
        LayoutNode layoutNode = this.layoutNode;
        if (layoutNode.isPlaced()) {
            ((AndroidComposeView) Snake.requireOwner(layoutNode)).getSnapshotObserver().observeReads$ui_release(this, BackwardsCompatNodeKt$onDrawCacheReadsChanged$1.INSTANCE$8, new NodeCoordinator$invoke$1(this, 0, canvas));
            this.lastLayerDrawingWasSkipped = false;
        } else {
            this.lastLayerDrawingWasSkipped = true;
        }
        return Unit.INSTANCE;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final boolean isAttached() {
        return !this.released && this.layoutNode.isAttached();
    }

    public final boolean isTransparent() {
        if (this.layer != null && this.lastLayerAlpha <= 0.0f) {
            return true;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (nodeCoordinator != null) {
            return nodeCoordinator.isTransparent();
        }
        return false;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public final boolean isValidOwnerScope() {
        return this.layer != null && isAttached();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r8v0, resolved type: androidx.compose.ui.node.NodeCoordinator */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0020, code lost:
    
        if (r0 == null) goto L13;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v3, types: [androidx.compose.ui.geometry.MutableRect, java.lang.Object] */
    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.ui.geometry.Rect localBoundingBoxOf(androidx.compose.ui.node.NodeCoordinator r8, boolean r9) {
        /*
            r7 = this;
            java.lang.String r0 = "sourceCoordinates"
            kotlin.ResultKt.checkNotNullParameter(r8, r0)
            boolean r0 = r7.isAttached()
            if (r0 == 0) goto L99
            boolean r0 = r8.isAttached()
            if (r0 == 0) goto L7c
            boolean r0 = r8 instanceof androidx.compose.ui.layout.LookaheadLayoutCoordinatesImpl
            if (r0 == 0) goto L19
            r0 = r8
            androidx.compose.ui.layout.LookaheadLayoutCoordinatesImpl r0 = (androidx.compose.ui.layout.LookaheadLayoutCoordinatesImpl) r0
            goto L1a
        L19:
            r0 = 0
        L1a:
            if (r0 == 0) goto L22
            androidx.compose.ui.node.LookaheadDelegate r0 = r0.lookaheadDelegate
            androidx.compose.ui.node.NodeCoordinator r0 = r0.coordinator
            if (r0 != 0) goto L23
        L22:
            r0 = r8
        L23:
            r0.onCoordinatesUsed$ui_release()
            androidx.compose.ui.node.NodeCoordinator r1 = r7.findCommonAncestor$ui_release(r0)
            androidx.compose.ui.geometry.MutableRect r2 = r7._rectCache
            r3 = 0
            if (r2 != 0) goto L3e
            androidx.compose.ui.geometry.MutableRect r2 = new androidx.compose.ui.geometry.MutableRect
            r2.<init>()
            r2.left = r3
            r2.top = r3
            r2.right = r3
            r2.bottom = r3
            r7._rectCache = r2
        L3e:
            r2.left = r3
            r2.top = r3
            long r3 = r8.measuredSize
            r8 = 32
            long r5 = r3 >> r8
            int r8 = (int) r5
            float r8 = (float) r8
            r2.right = r8
            r5 = 4294967295(0xffffffff, double:2.1219957905E-314)
            long r3 = r3 & r5
            int r8 = (int) r3
            float r8 = (float) r8
            r2.bottom = r8
        L56:
            if (r0 == r1) goto L6b
            r8 = 0
            r0.rectInParent$ui_release(r2, r9, r8)
            boolean r8 = r2.isEmpty()
            if (r8 == 0) goto L65
            androidx.compose.ui.geometry.Rect r8 = androidx.compose.ui.geometry.Rect.Zero
            return r8
        L65:
            androidx.compose.ui.node.NodeCoordinator r0 = r0.wrappedBy
            kotlin.ResultKt.checkNotNull(r0)
            goto L56
        L6b:
            r7.ancestorToLocal(r1, r2, r9)
            androidx.compose.ui.geometry.Rect r8 = new androidx.compose.ui.geometry.Rect
            float r9 = r2.left
            float r0 = r2.top
            float r1 = r2.right
            float r2 = r2.bottom
            r8.<init>(r9, r0, r1, r2)
            return r8
        L7c:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r0 = "LayoutCoordinates "
            r9.<init>(r0)
            r9.append(r8)
            java.lang.String r8 = " is not attached!"
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r8 = r8.toString()
            r9.<init>(r8)
            throw r9
        L99:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "LayoutCoordinate operations are only valid when isAttached is true"
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.NodeCoordinator.localBoundingBoxOf(androidx.compose.ui.node.NodeCoordinator, boolean):androidx.compose.ui.geometry.Rect");
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localPositionOf-R5De75A */
    public final long mo160localPositionOfR5De75A(LayoutCoordinates layoutCoordinates, long j) {
        NodeCoordinator nodeCoordinator;
        ResultKt.checkNotNullParameter(layoutCoordinates, "sourceCoordinates");
        boolean z = layoutCoordinates instanceof LookaheadLayoutCoordinatesImpl;
        if (z) {
            long mo160localPositionOfR5De75A = layoutCoordinates.mo160localPositionOfR5De75A(this, _BOUNDARY.Offset(-Offset.m76getXimpl(j), -Offset.m77getYimpl(j)));
            return _BOUNDARY.Offset(-Offset.m76getXimpl(mo160localPositionOfR5De75A), -Offset.m77getYimpl(mo160localPositionOfR5De75A));
        }
        LookaheadLayoutCoordinatesImpl lookaheadLayoutCoordinatesImpl = z ? (LookaheadLayoutCoordinatesImpl) layoutCoordinates : null;
        if (lookaheadLayoutCoordinatesImpl == null || (nodeCoordinator = lookaheadLayoutCoordinatesImpl.lookaheadDelegate.coordinator) == null) {
            nodeCoordinator = (NodeCoordinator) layoutCoordinates;
        }
        nodeCoordinator.onCoordinatesUsed$ui_release();
        NodeCoordinator findCommonAncestor$ui_release = findCommonAncestor$ui_release(nodeCoordinator);
        while (nodeCoordinator != findCommonAncestor$ui_release) {
            j = nodeCoordinator.m202toParentPositionMKHz9U(j);
            nodeCoordinator = nodeCoordinator.wrappedBy;
            ResultKt.checkNotNull(nodeCoordinator);
        }
        return m191ancestorToLocalR5De75A(findCommonAncestor$ui_release, j);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localToRoot-MK-Hz9U */
    public final long mo161localToRootMKHz9U(long j) {
        if (!isAttached()) {
            throw new IllegalStateException("LayoutCoordinate operations are only valid when isAttached is true".toString());
        }
        onCoordinatesUsed$ui_release();
        for (NodeCoordinator nodeCoordinator = this; nodeCoordinator != null; nodeCoordinator = nodeCoordinator.wrappedBy) {
            j = nodeCoordinator.m202toParentPositionMKHz9U(j);
        }
        return j;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localToWindow-MK-Hz9U */
    public final long mo162localToWindowMKHz9U(long j) {
        long mo161localToRootMKHz9U = mo161localToRootMKHz9U(j);
        AndroidComposeView androidComposeView = (AndroidComposeView) Snake.requireOwner(this.layoutNode);
        androidComposeView.recalculateWindowPosition();
        return Brush.m104mapMKHz9U(androidComposeView.viewToWindowMatrix, mo161localToRootMKHz9U);
    }

    public final void onCoordinatesUsed$ui_release() {
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = this.layoutNode.layoutDelegate;
        int i = layoutNodeLayoutDelegate.layoutNode.layoutDelegate.layoutState;
        if (i == 3 || i == 4) {
            if (layoutNodeLayoutDelegate.measurePassDelegate.layingOutChildren) {
                layoutNodeLayoutDelegate.setCoordinatesAccessedDuringPlacement(true);
            } else {
                layoutNodeLayoutDelegate.setCoordinatesAccessedDuringModifierPlacement(true);
            }
        }
        if (i == 4) {
            LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate;
            if (lookaheadPassDelegate == null || !lookaheadPassDelegate.layingOutChildren) {
                layoutNodeLayoutDelegate.setCoordinatesAccessedDuringModifierPlacement(true);
            } else {
                layoutNodeLayoutDelegate.setCoordinatesAccessedDuringPlacement(true);
            }
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:38:0x0084 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:42:0x008d */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:48:0x004f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:51:0x004f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:53:0x0093 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v2, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    public final void onMeasured() {
        Modifier.Node node;
        Modifier.Node headNode = headNode(Snake.m214getIncludeSelfInTraversalH91voCI(128));
        if (headNode == null || (headNode.node.aggregateChildKindSet & 128) == 0) {
            return;
        }
        Snapshot createTransparentSnapshotWithNoParentReadObserver = SnapshotKt.createTransparentSnapshotWithNoParentReadObserver((Snapshot) SnapshotKt.threadSnapshot.get(), null, false);
        try {
            Snapshot makeCurrent = createTransparentSnapshotWithNoParentReadObserver.makeCurrent();
            try {
                boolean m214getIncludeSelfInTraversalH91voCI = Snake.m214getIncludeSelfInTraversalH91voCI(128);
                if (m214getIncludeSelfInTraversalH91voCI) {
                    node = getTail();
                } else {
                    node = getTail().parent;
                    if (node == null) {
                    }
                }
                for (Modifier.Node headNode2 = headNode(m214getIncludeSelfInTraversalH91voCI); headNode2 != null; headNode2 = headNode2.child) {
                    if ((headNode2.aggregateChildKindSet & 128) == 0) {
                        break;
                    }
                    if ((headNode2.kindSet & 128) != 0) {
                        ?? r8 = 0;
                        DelegatingNode delegatingNode = headNode2;
                        while (delegatingNode != 0) {
                            if (delegatingNode instanceof LayoutAwareModifierNode) {
                            } else if ((delegatingNode.kindSet & 128) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                Modifier.Node node2 = delegatingNode.delegate;
                                int i = 0;
                                delegatingNode = delegatingNode;
                                r8 = r8;
                                while (node2 != null) {
                                    if ((node2.kindSet & 128) != 0) {
                                        i++;
                                        r8 = r8;
                                        if (i == 1) {
                                            delegatingNode = node2;
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
                                            r8.add(node2);
                                        }
                                    }
                                    node2 = node2.child;
                                    delegatingNode = delegatingNode;
                                    r8 = r8;
                                }
                                if (i == 1) {
                                }
                            }
                            delegatingNode = Snake.access$pop(r8);
                        }
                    }
                    if (headNode2 == node) {
                        break;
                    }
                }
            } finally {
                Snapshot.restoreCurrent(makeCurrent);
            }
        } finally {
            createTransparentSnapshotWithNoParentReadObserver.dispose();
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:33:0x005f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:37:0x0068 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:42:0x0026 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:43:0x0026 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:46:0x006e */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r5v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    public final void onPlaced() {
        boolean m214getIncludeSelfInTraversalH91voCI = Snake.m214getIncludeSelfInTraversalH91voCI(128);
        Modifier.Node tail = getTail();
        if (!m214getIncludeSelfInTraversalH91voCI && (tail = tail.parent) == null) {
            return;
        }
        for (Modifier.Node headNode = headNode(m214getIncludeSelfInTraversalH91voCI); headNode != null && (headNode.aggregateChildKindSet & 128) != 0; headNode = headNode.child) {
            if ((headNode.kindSet & 128) != 0) {
                DelegatingNode delegatingNode = headNode;
                ?? r5 = 0;
                while (delegatingNode != 0) {
                    if (delegatingNode instanceof LayoutAwareModifierNode) {
                        ((LayoutAwareModifierNode) delegatingNode).onPlaced(this);
                    } else if ((delegatingNode.kindSet & 128) != 0 && (delegatingNode instanceof DelegatingNode)) {
                        Modifier.Node node = delegatingNode.delegate;
                        int i = 0;
                        delegatingNode = delegatingNode;
                        r5 = r5;
                        while (node != null) {
                            if ((node.kindSet & 128) != 0) {
                                i++;
                                r5 = r5;
                                if (i == 1) {
                                    delegatingNode = node;
                                } else {
                                    if (r5 == 0) {
                                        ?? obj = new Object();
                                        obj.content = new Modifier.Node[16];
                                        obj.size = 0;
                                        r5 = obj;
                                    }
                                    if (delegatingNode != 0) {
                                        r5.add(delegatingNode);
                                        delegatingNode = 0;
                                    }
                                    r5.add(node);
                                }
                            }
                            node = node.child;
                            delegatingNode = delegatingNode;
                            r5 = r5;
                        }
                        if (i == 1) {
                        }
                    }
                    delegatingNode = Snake.access$pop(r5);
                }
            }
            if (headNode == tail) {
                return;
            }
        }
    }

    public abstract void performDraw(Canvas canvas);

    /* renamed from: placeSelf-f8xVGno, reason: not valid java name */
    public final void m200placeSelff8xVGno(long j, float f, Function1 function1) {
        updateLayerBlock(function1, false);
        long j2 = this.position;
        int i = IntOffset.$r8$clinit;
        if (j2 != j) {
            this.position = j;
            LayoutNode layoutNode = this.layoutNode;
            layoutNode.layoutDelegate.measurePassDelegate.notifyChildrenUsingCoordinatesWhilePlacing();
            OwnedLayer ownedLayer = this.layer;
            if (ownedLayer != null) {
                ownedLayer.mo208movegyyYBs(j);
            } else {
                NodeCoordinator nodeCoordinator = this.wrappedBy;
                if (nodeCoordinator != null) {
                    nodeCoordinator.invalidateLayer();
                }
            }
            LookaheadCapablePlaceable.invalidateAlignmentLinesFromPositionChange(this);
            Owner owner = layoutNode.owner;
            if (owner != null) {
                ((AndroidComposeView) owner).onLayoutChange(layoutNode);
            }
        }
        this.zIndex = f;
    }

    public final void rectInParent$ui_release(MutableRect mutableRect, boolean z, boolean z2) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            if (this.isClipping) {
                if (z2) {
                    long m195getMinimumTouchTargetSizeNHjbRc = m195getMinimumTouchTargetSizeNHjbRc();
                    float m85getWidthimpl = Size.m85getWidthimpl(m195getMinimumTouchTargetSizeNHjbRc) / 2.0f;
                    float m83getHeightimpl = Size.m83getHeightimpl(m195getMinimumTouchTargetSizeNHjbRc) / 2.0f;
                    long j = this.measuredSize;
                    mutableRect.intersect(-m85getWidthimpl, -m83getHeightimpl, ((int) (j >> 32)) + m85getWidthimpl, ((int) (j & 4294967295L)) + m83getHeightimpl);
                } else if (z) {
                    long j2 = this.measuredSize;
                    mutableRect.intersect(0.0f, 0.0f, (int) (j2 >> 32), (int) (j2 & 4294967295L));
                }
                if (mutableRect.isEmpty()) {
                    return;
                }
            }
            ownedLayer.mapBounds(mutableRect, false);
        }
        long j3 = this.position;
        int i = IntOffset.$r8$clinit;
        float f = (int) (j3 >> 32);
        mutableRect.left += f;
        mutableRect.right += f;
        float f2 = (int) (j3 & 4294967295L);
        mutableRect.top += f2;
        mutableRect.bottom += f2;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final void replace$ui_release() {
        mo165placeAtf8xVGno(this.position, this.zIndex, this.layerBlock);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:60:0x0096 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:64:0x009f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:69:0x005f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:70:0x005f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:73:0x00a5 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r7v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    public final void setMeasureResult$ui_release(MeasureScope$layout$1 measureScope$layout$1) {
        ResultKt.checkNotNullParameter(measureScope$layout$1, "value");
        MeasureScope$layout$1 measureScope$layout$12 = this._measureResult;
        if (measureScope$layout$1 != measureScope$layout$12) {
            this._measureResult = measureScope$layout$1;
            LayoutNode layoutNode = this.layoutNode;
            int i = measureScope$layout$1.height;
            int i2 = measureScope$layout$1.width;
            if (measureScope$layout$12 == null || i2 != measureScope$layout$12.width || i != measureScope$layout$12.height) {
                OwnedLayer ownedLayer = this.layer;
                if (ownedLayer != null) {
                    ownedLayer.mo209resizeozmzZPI(ResultKt.IntSize(i2, i));
                } else {
                    NodeCoordinator nodeCoordinator = this.wrappedBy;
                    if (nodeCoordinator != null) {
                        nodeCoordinator.invalidateLayer();
                    }
                }
                m166setMeasuredSizeozmzZPI(ResultKt.IntSize(i2, i));
                updateLayerParameters(false);
                boolean m214getIncludeSelfInTraversalH91voCI = Snake.m214getIncludeSelfInTraversalH91voCI(4);
                Modifier.Node tail = getTail();
                if (m214getIncludeSelfInTraversalH91voCI || (tail = tail.parent) != null) {
                    for (Modifier.Node headNode = headNode(m214getIncludeSelfInTraversalH91voCI); headNode != null && (headNode.aggregateChildKindSet & 4) != 0; headNode = headNode.child) {
                        if ((headNode.kindSet & 4) != 0) {
                            DelegatingNode delegatingNode = headNode;
                            ?? r8 = 0;
                            while (delegatingNode != 0) {
                                if (delegatingNode instanceof DrawModifierNode) {
                                    ((DrawModifierNode) delegatingNode).onMeasureResultChanged();
                                } else if ((delegatingNode.kindSet & 4) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                    Modifier.Node node = delegatingNode.delegate;
                                    int i3 = 0;
                                    delegatingNode = delegatingNode;
                                    r8 = r8;
                                    while (node != null) {
                                        if ((node.kindSet & 4) != 0) {
                                            i3++;
                                            r8 = r8;
                                            if (i3 == 1) {
                                                delegatingNode = node;
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
                                                r8.add(node);
                                            }
                                        }
                                        node = node.child;
                                        delegatingNode = delegatingNode;
                                        r8 = r8;
                                    }
                                    if (i3 == 1) {
                                    }
                                }
                                delegatingNode = Snake.access$pop(r8);
                            }
                        }
                        if (headNode == tail) {
                            break;
                        }
                    }
                }
                Owner owner = layoutNode.owner;
                if (owner != null) {
                    ((AndroidComposeView) owner).onLayoutChange(layoutNode);
                }
            }
            LinkedHashMap linkedHashMap = this.oldAlignmentLines;
            Map map = measureScope$layout$1.alignmentLines;
            if (((linkedHashMap == null || linkedHashMap.isEmpty()) && !(!map.isEmpty())) || ResultKt.areEqual(map, this.oldAlignmentLines)) {
                return;
            }
            layoutNode.layoutDelegate.measurePassDelegate.alignmentLines.onAlignmentsChanged();
            LinkedHashMap linkedHashMap2 = this.oldAlignmentLines;
            if (linkedHashMap2 == null) {
                linkedHashMap2 = new LinkedHashMap();
                this.oldAlignmentLines = linkedHashMap2;
            }
            linkedHashMap2.clear();
            linkedHashMap2.putAll(map);
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:27:0x00e6 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:31:0x00ef */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:37:0x0029 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:38:0x0029 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:41:0x00f7 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r3v13, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r4v12, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v16 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* renamed from: speculativeHit-JHbHoSQ, reason: not valid java name */
    public final void m201speculativeHitJHbHoSQ(Modifier.Node node, HitTestSource hitTestSource, long j, HitTestResult hitTestResult, boolean z, boolean z2, float f) {
        if (node == null) {
            mo171hitTestChildYqVAtuI(hitTestSource, j, hitTestResult, z, z2);
            return;
        }
        ULong.Companion companion = (ULong.Companion) hitTestSource;
        int i = 16;
        switch (companion.$r8$classId) {
            case 0:
                break;
            default:
                ?? r4 = 0;
                DelegatingNode delegatingNode = node;
                while (delegatingNode != 0) {
                    if (delegatingNode instanceof PointerInputModifierNode) {
                        if (((PointerInputModifierNode) delegatingNode).interceptOutOfBoundsChildEvents()) {
                            NodeCoordinator$hitNear$1 nodeCoordinator$hitNear$1 = new NodeCoordinator$hitNear$1(this, node, hitTestSource, j, hitTestResult, z, z2, f, 1);
                            hitTestResult.getClass();
                            if (hitTestResult.hitDepth == ResultKt.getLastIndex(hitTestResult)) {
                                hitTestResult.hitInMinimumTouchTarget(node, f, z2, nodeCoordinator$hitNear$1);
                                if (hitTestResult.hitDepth + 1 == ResultKt.getLastIndex(hitTestResult)) {
                                    hitTestResult.resizeToHitDepth();
                                    return;
                                }
                                return;
                            }
                            long m170findBestHitDistanceptXAw2c = hitTestResult.m170findBestHitDistanceptXAw2c();
                            int i2 = hitTestResult.hitDepth;
                            hitTestResult.hitDepth = ResultKt.getLastIndex(hitTestResult);
                            hitTestResult.hitInMinimumTouchTarget(node, f, z2, nodeCoordinator$hitNear$1);
                            if (hitTestResult.hitDepth + 1 < ResultKt.getLastIndex(hitTestResult) && Snake.m212compareToS_HNhKs(m170findBestHitDistanceptXAw2c, hitTestResult.m170findBestHitDistanceptXAw2c()) > 0) {
                                int i3 = hitTestResult.hitDepth + 1;
                                int i4 = i2 + 1;
                                Object[] objArr = hitTestResult.values;
                                MapsKt___MapsJvmKt.copyInto(objArr, objArr, i4, i3, hitTestResult.size);
                                long[] jArr = hitTestResult.distanceFromEdgeAndInLayer;
                                int i5 = hitTestResult.size;
                                ResultKt.checkNotNullParameter(jArr, "<this>");
                                System.arraycopy(jArr, i3, jArr, i4, i5 - i3);
                                hitTestResult.hitDepth = ((hitTestResult.size + i2) - hitTestResult.hitDepth) - 1;
                            }
                            hitTestResult.resizeToHitDepth();
                            hitTestResult.hitDepth = i2;
                            return;
                        }
                    } else if ((delegatingNode.kindSet & 16) != 0 && (delegatingNode instanceof DelegatingNode)) {
                        Modifier.Node node2 = delegatingNode.delegate;
                        int i6 = 0;
                        delegatingNode = delegatingNode;
                        r4 = r4;
                        while (node2 != null) {
                            if ((node2.kindSet & 16) != 0) {
                                i6++;
                                r4 = r4;
                                if (i6 == 1) {
                                    delegatingNode = node2;
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
                                    r4.add(node2);
                                }
                            }
                            node2 = node2.child;
                            delegatingNode = delegatingNode;
                            r4 = r4;
                        }
                        if (i6 == 1) {
                        }
                    }
                    delegatingNode = Snake.access$pop(r4);
                }
                break;
        }
        switch (companion.$r8$classId) {
            case 0:
                i = 8;
                break;
        }
        m201speculativeHitJHbHoSQ(Snake.m211access$nextUntilhw7D004(node, i), hitTestSource, j, hitTestResult, z, z2, f);
    }

    /* renamed from: toParentPosition-MK-Hz9U, reason: not valid java name */
    public final long m202toParentPositionMKHz9U(long j) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            j = ownedLayer.mo207mapOffset8S9VItk(j, false);
        }
        long j2 = this.position;
        float m76getXimpl = Offset.m76getXimpl(j);
        int i = IntOffset.$r8$clinit;
        return _BOUNDARY.Offset(m76getXimpl + ((int) (j2 >> 32)), Offset.m77getYimpl(j) + ((int) (j2 & 4294967295L)));
    }

    public final void updateLayerBlock(Function1 function1, boolean z) {
        Owner owner;
        DrawChildContainer drawChildContainer;
        LayoutNode layoutNode = this.layoutNode;
        boolean z2 = (!z && this.layerBlock == function1 && ResultKt.areEqual(this.layerDensity, layoutNode.density) && this.layerLayoutDirection == layoutNode.layoutDirection) ? false : true;
        this.layerBlock = function1;
        this.layerDensity = layoutNode.density;
        this.layerLayoutDirection = layoutNode.layoutDirection;
        boolean isAttached = isAttached();
        LayoutNode$_foldedChildren$1 layoutNode$_foldedChildren$1 = this.invalidateParentLayer;
        Object obj = null;
        if (!isAttached || function1 == null) {
            OwnedLayer ownedLayer = this.layer;
            if (ownedLayer != null) {
                ownedLayer.destroy();
                layoutNode.innerLayerCoordinatorIsDirty = true;
                layoutNode$_foldedChildren$1.invoke();
                if (isAttached() && (owner = layoutNode.owner) != null) {
                    ((AndroidComposeView) owner).onLayoutChange(layoutNode);
                }
            }
            this.layer = null;
            this.lastLayerDrawingWasSkipped = false;
            return;
        }
        if (this.layer != null) {
            if (z2) {
                updateLayerParameters(true);
                return;
            }
            return;
        }
        AndroidComposeView androidComposeView = (AndroidComposeView) Snake.requireOwner(layoutNode);
        ResultKt.checkNotNullParameter(layoutNode$_foldedChildren$1, "invalidateParentLayer");
        WeakCache weakCache = androidComposeView.layerCache;
        weakCache.clearWeakReferences();
        while (true) {
            MutableVector mutableVector = (MutableVector) weakCache.values;
            if (!mutableVector.isNotEmpty()) {
                break;
            }
            Object obj2 = ((Reference) mutableVector.removeAt(mutableVector.size - 1)).get();
            if (obj2 != null) {
                obj = obj2;
                break;
            }
        }
        OwnedLayer ownedLayer2 = (OwnedLayer) obj;
        if (ownedLayer2 != null) {
            ownedLayer2.reuseLayer(layoutNode$_foldedChildren$1, this);
        } else {
            if (androidComposeView.isHardwareAccelerated() && androidComposeView.isRenderNodeCompatible) {
                try {
                    ownedLayer2 = new RenderNodeLayer(androidComposeView, this, layoutNode$_foldedChildren$1);
                } catch (Throwable unused) {
                    androidComposeView.isRenderNodeCompatible = false;
                }
            }
            if (androidComposeView.viewLayersContainer == null) {
                if (!ViewLayer.hasRetrievedMethod) {
                    AndroidTextToolbar.updateDisplayList(new View(androidComposeView.getContext()));
                }
                if (ViewLayer.shouldUseDispatchDraw) {
                    Context context = androidComposeView.getContext();
                    ResultKt.checkNotNullExpressionValue(context, "context");
                    drawChildContainer = new DrawChildContainer(context);
                } else {
                    Context context2 = androidComposeView.getContext();
                    ResultKt.checkNotNullExpressionValue(context2, "context");
                    drawChildContainer = new DrawChildContainer(context2);
                }
                androidComposeView.viewLayersContainer = drawChildContainer;
                androidComposeView.addView(drawChildContainer);
            }
            DrawChildContainer drawChildContainer2 = androidComposeView.viewLayersContainer;
            ResultKt.checkNotNull(drawChildContainer2);
            ownedLayer2 = new ViewLayer(androidComposeView, drawChildContainer2, this, layoutNode$_foldedChildren$1);
        }
        ownedLayer2.mo209resizeozmzZPI(this.measuredSize);
        ownedLayer2.mo208movegyyYBs(this.position);
        this.layer = ownedLayer2;
        updateLayerParameters(true);
        layoutNode.innerLayerCoordinatorIsDirty = true;
        layoutNode$_foldedChildren$1.invoke();
    }

    public final void updateLayerParameters(boolean z) {
        Owner owner;
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer == null) {
            if (this.layerBlock != null) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
            return;
        }
        Function1 function1 = this.layerBlock;
        if (function1 == null) {
            throw new IllegalArgumentException("Required value was null.".toString());
        }
        ReusableGraphicsLayerScope reusableGraphicsLayerScope = graphicsLayerScope;
        reusableGraphicsLayerScope.scaleX = 1.0f;
        reusableGraphicsLayerScope.scaleY = 1.0f;
        reusableGraphicsLayerScope.alpha = 1.0f;
        reusableGraphicsLayerScope.translationX = 0.0f;
        reusableGraphicsLayerScope.translationY = 0.0f;
        reusableGraphicsLayerScope.shadowElevation = 0.0f;
        long j = GraphicsLayerScopeKt.DefaultShadowColor;
        reusableGraphicsLayerScope.ambientShadowColor = j;
        reusableGraphicsLayerScope.spotShadowColor = j;
        reusableGraphicsLayerScope.rotationX = 0.0f;
        reusableGraphicsLayerScope.rotationY = 0.0f;
        reusableGraphicsLayerScope.rotationZ = 0.0f;
        reusableGraphicsLayerScope.cameraDistance = 8.0f;
        reusableGraphicsLayerScope.transformOrigin = TransformOrigin.Center;
        reusableGraphicsLayerScope.shape = Brush.RectangleShape;
        reusableGraphicsLayerScope.clip = false;
        reusableGraphicsLayerScope.compositingStrategy = 0;
        int i = Size.$r8$clinit;
        LayoutNode layoutNode = this.layoutNode;
        Density density = layoutNode.density;
        ResultKt.checkNotNullParameter(density, "<set-?>");
        reusableGraphicsLayerScope.graphicsDensity = density;
        ResultKt.m300toSizeozmzZPI(this.measuredSize);
        ((AndroidComposeView) Snake.requireOwner(layoutNode)).getSnapshotObserver().observeReads$ui_release(this, BackwardsCompatNodeKt$onDrawCacheReadsChanged$1.INSTANCE$9, new LayoutNode$_foldedChildren$1(10, function1));
        LayerPositionalProperties layerPositionalProperties = this.layerPositionalProperties;
        if (layerPositionalProperties == null) {
            layerPositionalProperties = new LayerPositionalProperties();
            this.layerPositionalProperties = layerPositionalProperties;
        }
        float f = reusableGraphicsLayerScope.scaleX;
        layerPositionalProperties.scaleX = f;
        float f2 = reusableGraphicsLayerScope.scaleY;
        layerPositionalProperties.scaleY = f2;
        float f3 = reusableGraphicsLayerScope.translationX;
        layerPositionalProperties.translationX = f3;
        float f4 = reusableGraphicsLayerScope.translationY;
        layerPositionalProperties.translationY = f4;
        float f5 = reusableGraphicsLayerScope.rotationX;
        layerPositionalProperties.rotationX = f5;
        float f6 = reusableGraphicsLayerScope.rotationY;
        layerPositionalProperties.rotationY = f6;
        float f7 = reusableGraphicsLayerScope.rotationZ;
        layerPositionalProperties.rotationZ = f7;
        float f8 = reusableGraphicsLayerScope.cameraDistance;
        layerPositionalProperties.cameraDistance = f8;
        long j2 = reusableGraphicsLayerScope.transformOrigin;
        layerPositionalProperties.transformOrigin = j2;
        ownedLayer.mo210updateLayerPropertiesdDxrwY(f, f2, reusableGraphicsLayerScope.alpha, f3, f4, reusableGraphicsLayerScope.shadowElevation, f5, f6, f7, f8, j2, reusableGraphicsLayerScope.shape, reusableGraphicsLayerScope.clip, reusableGraphicsLayerScope.ambientShadowColor, reusableGraphicsLayerScope.spotShadowColor, reusableGraphicsLayerScope.compositingStrategy, layoutNode.layoutDirection, layoutNode.density);
        this.isClipping = reusableGraphicsLayerScope.clip;
        this.lastLayerAlpha = reusableGraphicsLayerScope.alpha;
        if (!z || (owner = layoutNode.owner) == null) {
            return;
        }
        ((AndroidComposeView) owner).onLayoutChange(layoutNode);
    }

    /* renamed from: withinLayerBounds-k-4lQ0M, reason: not valid java name */
    public final boolean m203withinLayerBoundsk4lQ0M(long j) {
        float m76getXimpl = Offset.m76getXimpl(j);
        if (Float.isInfinite(m76getXimpl) || Float.isNaN(m76getXimpl)) {
            return false;
        }
        float m77getYimpl = Offset.m77getYimpl(j);
        if (Float.isInfinite(m77getYimpl) || Float.isNaN(m77getYimpl)) {
            return false;
        }
        OwnedLayer ownedLayer = this.layer;
        return ownedLayer == null || !this.isClipping || ownedLayer.mo206isInLayerk4lQ0M(j);
    }
}
