package androidx.compose.ui.platform;

import _COROUTINE._BOUNDARY;
import android.graphics.Region;
import android.os.Binder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.compose.runtime.NeverEqualPolicy;
import androidx.compose.runtime.ReferentialEqualityPolicy;
import androidx.compose.runtime.StructuralEqualityPolicy;
import androidx.compose.runtime.snapshots.SnapshotMutableState;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.Snake;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Function;
import kotlin.ResultKt;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public abstract class InvertMatrixKt {
    public static final Class[] AcceptableClasses = {Serializable.class, Parcelable.class, String.class, SparseArray.class, Binder.class, Size.class, SizeF.class};

    public static final boolean access$enabled(SemanticsNode semanticsNode) {
        return _BOUNDARY.getOrNull(semanticsNode.getConfig(), SemanticsProperties.Disabled) == null;
    }

    /* renamed from: access$preTransform-JiSxe2E, reason: not valid java name */
    public static final void m229access$preTransformJiSxe2E(float[] fArr, float[] fArr2) {
        float m231dotp89u6pk = m231dotp89u6pk(fArr2, 0, fArr, 0);
        float m231dotp89u6pk2 = m231dotp89u6pk(fArr2, 0, fArr, 1);
        float m231dotp89u6pk3 = m231dotp89u6pk(fArr2, 0, fArr, 2);
        float m231dotp89u6pk4 = m231dotp89u6pk(fArr2, 0, fArr, 3);
        float m231dotp89u6pk5 = m231dotp89u6pk(fArr2, 1, fArr, 0);
        float m231dotp89u6pk6 = m231dotp89u6pk(fArr2, 1, fArr, 1);
        float m231dotp89u6pk7 = m231dotp89u6pk(fArr2, 1, fArr, 2);
        float m231dotp89u6pk8 = m231dotp89u6pk(fArr2, 1, fArr, 3);
        float m231dotp89u6pk9 = m231dotp89u6pk(fArr2, 2, fArr, 0);
        float m231dotp89u6pk10 = m231dotp89u6pk(fArr2, 2, fArr, 1);
        float m231dotp89u6pk11 = m231dotp89u6pk(fArr2, 2, fArr, 2);
        float m231dotp89u6pk12 = m231dotp89u6pk(fArr2, 2, fArr, 3);
        float m231dotp89u6pk13 = m231dotp89u6pk(fArr2, 3, fArr, 0);
        float m231dotp89u6pk14 = m231dotp89u6pk(fArr2, 3, fArr, 1);
        float m231dotp89u6pk15 = m231dotp89u6pk(fArr2, 3, fArr, 2);
        float m231dotp89u6pk16 = m231dotp89u6pk(fArr2, 3, fArr, 3);
        fArr[0] = m231dotp89u6pk;
        fArr[1] = m231dotp89u6pk2;
        fArr[2] = m231dotp89u6pk3;
        fArr[3] = m231dotp89u6pk4;
        fArr[4] = m231dotp89u6pk5;
        fArr[5] = m231dotp89u6pk6;
        fArr[6] = m231dotp89u6pk7;
        fArr[7] = m231dotp89u6pk8;
        fArr[8] = m231dotp89u6pk9;
        fArr[9] = m231dotp89u6pk10;
        fArr[10] = m231dotp89u6pk11;
        fArr[11] = m231dotp89u6pk12;
        fArr[12] = m231dotp89u6pk13;
        fArr[13] = m231dotp89u6pk14;
        fArr[14] = m231dotp89u6pk15;
        fArr[15] = m231dotp89u6pk16;
    }

    /* renamed from: access$toLegacyClassName-V4PA4sw, reason: not valid java name */
    public static final String m230access$toLegacyClassNameV4PA4sw(int i) {
        if (Role.m238equalsimpl0(i, 0)) {
            return "android.widget.Button";
        }
        if (Role.m238equalsimpl0(i, 1)) {
            return "android.widget.CheckBox";
        }
        if (Role.m238equalsimpl0(i, 3)) {
            return "android.widget.RadioButton";
        }
        if (Role.m238equalsimpl0(i, 5)) {
            return "android.widget.ImageView";
        }
        if (Role.m238equalsimpl0(i, 6)) {
            return "android.widget.Spinner";
        }
        return null;
    }

    public static final boolean canBeSavedToBundle(Object obj) {
        if (obj instanceof SnapshotMutableState) {
            SnapshotMutableState snapshotMutableState = (SnapshotMutableState) obj;
            if (snapshotMutableState.getPolicy() != NeverEqualPolicy.INSTANCE && snapshotMutableState.getPolicy() != StructuralEqualityPolicy.INSTANCE && snapshotMutableState.getPolicy() != ReferentialEqualityPolicy.INSTANCE) {
                return false;
            }
            Object value = snapshotMutableState.getValue();
            if (value == null) {
                return true;
            }
            return canBeSavedToBundle(value);
        }
        if ((obj instanceof Function) && (obj instanceof Serializable)) {
            return false;
        }
        Class[] clsArr = AcceptableClasses;
        for (int i = 0; i < 7; i++) {
            if (clsArr[i].isInstance(obj)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: dot-p89u6pk, reason: not valid java name */
    public static final float m231dotp89u6pk(float[] fArr, int i, float[] fArr2, int i2) {
        int i3 = i * 4;
        return (fArr[i3 + 3] * fArr2[12 + i2]) + (fArr[i3 + 2] * fArr2[8 + i2]) + (fArr[i3 + 1] * fArr2[4 + i2]) + (fArr[i3] * fArr2[i2]);
    }

    public static final ScrollObservationScope findById(int i, ArrayList arrayList) {
        ResultKt.checkNotNullParameter(arrayList, "<this>");
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (((ScrollObservationScope) arrayList.get(i2)).semanticsNodeId == i) {
                return (ScrollObservationScope) arrayList.get(i2);
            }
        }
        return null;
    }

    public static final LayoutNode findClosestParentNode(LayoutNode layoutNode, InspectableValueKt$NoInspectorInfo$1 inspectableValueKt$NoInspectorInfo$1) {
        for (LayoutNode parent$ui_release = layoutNode.getParent$ui_release(); parent$ui_release != null; parent$ui_release = parent$ui_release.getParent$ui_release()) {
            if (((Boolean) inspectableValueKt$NoInspectorInfo$1.invoke((Object) parent$ui_release)).booleanValue()) {
                return parent$ui_release;
            }
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r8v14, types: [androidx.compose.ui.geometry.MutableRect, java.lang.Object] */
    public static final void getAllUncoveredSemanticsNodesToMap$findAllSemanticNodesRecursive(Region region, SemanticsNode semanticsNode, LinkedHashMap linkedHashMap, SemanticsNode semanticsNode2) {
        LayoutNode layoutNode;
        Object outerMergingSemantics;
        boolean isPlaced = semanticsNode2.layoutNode.isPlaced();
        LayoutNode layoutNode2 = semanticsNode2.layoutNode;
        boolean z = (isPlaced && layoutNode2.isAttached()) ? false : true;
        boolean isEmpty = region.isEmpty();
        int i = semanticsNode.id;
        int i2 = semanticsNode2.id;
        if (!isEmpty || i2 == i) {
            if (!z || semanticsNode2.isFake) {
                SemanticsConfiguration semanticsConfiguration = semanticsNode2.unmergedConfig;
                boolean z2 = semanticsConfiguration.isMergingSemanticsOfDescendants;
                Object obj = semanticsNode2.outerSemanticsNode;
                if (z2 && (outerMergingSemantics = _BOUNDARY.getOuterMergingSemantics(layoutNode2)) != null) {
                    obj = outerMergingSemantics;
                }
                Modifier.Node node = ((Modifier.Node) obj).node;
                boolean z3 = _BOUNDARY.getOrNull(semanticsConfiguration, SemanticsActions.OnClick) != null;
                ResultKt.checkNotNullParameter(node, "<this>");
                boolean z4 = node.node.isAttached;
                Rect rect = Rect.Zero;
                if (z4) {
                    if (z3) {
                        NodeCoordinator m215requireCoordinator64DMado = Snake.m215requireCoordinator64DMado(node, 8);
                        if (m215requireCoordinator64DMado.isAttached()) {
                            LayoutCoordinates findRootCoordinates = _BOUNDARY.findRootCoordinates(m215requireCoordinator64DMado);
                            MutableRect mutableRect = m215requireCoordinator64DMado._rectCache;
                            MutableRect mutableRect2 = mutableRect;
                            if (mutableRect == null) {
                                ?? obj2 = new Object();
                                obj2.left = 0.0f;
                                obj2.top = 0.0f;
                                obj2.right = 0.0f;
                                obj2.bottom = 0.0f;
                                m215requireCoordinator64DMado._rectCache = obj2;
                                mutableRect2 = obj2;
                            }
                            long m192calculateMinimumTouchTargetPaddingE7KxVPU = m215requireCoordinator64DMado.m192calculateMinimumTouchTargetPaddingE7KxVPU(m215requireCoordinator64DMado.m195getMinimumTouchTargetSizeNHjbRc());
                            mutableRect2.left = -androidx.compose.ui.geometry.Size.m85getWidthimpl(m192calculateMinimumTouchTargetPaddingE7KxVPU);
                            mutableRect2.top = -androidx.compose.ui.geometry.Size.m83getHeightimpl(m192calculateMinimumTouchTargetPaddingE7KxVPU);
                            mutableRect2.right = androidx.compose.ui.geometry.Size.m85getWidthimpl(m192calculateMinimumTouchTargetPaddingE7KxVPU) + m215requireCoordinator64DMado.getMeasuredWidth();
                            mutableRect2.bottom = androidx.compose.ui.geometry.Size.m83getHeightimpl(m192calculateMinimumTouchTargetPaddingE7KxVPU) + ((int) (m215requireCoordinator64DMado.measuredSize & 4294967295L));
                            NodeCoordinator nodeCoordinator = m215requireCoordinator64DMado;
                            while (true) {
                                if (nodeCoordinator == findRootCoordinates) {
                                    rect = new Rect(mutableRect2.left, mutableRect2.top, mutableRect2.right, mutableRect2.bottom);
                                    break;
                                }
                                nodeCoordinator.rectInParent$ui_release(mutableRect2, false, true);
                                if (mutableRect2.isEmpty()) {
                                    break;
                                }
                                NodeCoordinator nodeCoordinator2 = nodeCoordinator.wrappedBy;
                                ResultKt.checkNotNull(nodeCoordinator2);
                                nodeCoordinator = nodeCoordinator2;
                            }
                        }
                    } else {
                        NodeCoordinator m215requireCoordinator64DMado2 = Snake.m215requireCoordinator64DMado(node, 8);
                        rect = _BOUNDARY.findRootCoordinates(m215requireCoordinator64DMado2).localBoundingBoxOf(m215requireCoordinator64DMado2, true);
                    }
                }
                android.graphics.Rect rect2 = new android.graphics.Rect(ResultKt.roundToInt(rect.left), ResultKt.roundToInt(rect.top), ResultKt.roundToInt(rect.right), ResultKt.roundToInt(rect.bottom));
                Region region2 = new Region();
                region2.set(rect2);
                if (i2 == i) {
                    i2 = -1;
                }
                if (region2.op(region, region2, Region.Op.INTERSECT)) {
                    Integer valueOf = Integer.valueOf(i2);
                    android.graphics.Rect bounds = region2.getBounds();
                    ResultKt.checkNotNullExpressionValue(bounds, "region.bounds");
                    linkedHashMap.put(valueOf, new SemanticsNodeWithAdjustedBounds(semanticsNode2, bounds));
                    List children = semanticsNode2.getChildren(false, true);
                    for (int size = children.size() - 1; -1 < size; size--) {
                        getAllUncoveredSemanticsNodesToMap$findAllSemanticNodesRecursive(region, semanticsNode, linkedHashMap, (SemanticsNode) children.get(size));
                    }
                    region.op(rect2, region, Region.Op.REVERSE_DIFFERENCE);
                    return;
                }
                if (semanticsNode2.isFake) {
                    SemanticsNode parent = semanticsNode2.getParent();
                    Rect rect3 = (parent == null || (layoutNode = parent.layoutNode) == null || !layoutNode.isPlaced()) ? new Rect(0.0f, 0.0f, 10.0f, 10.0f) : parent.getBoundsInRoot();
                    linkedHashMap.put(Integer.valueOf(i2), new SemanticsNodeWithAdjustedBounds(semanticsNode2, new android.graphics.Rect(ResultKt.roundToInt(rect3.left), ResultKt.roundToInt(rect3.top), ResultKt.roundToInt(rect3.right), ResultKt.roundToInt(rect3.bottom))));
                } else if (i2 == -1) {
                    Integer valueOf2 = Integer.valueOf(i2);
                    android.graphics.Rect bounds2 = region2.getBounds();
                    ResultKt.checkNotNullExpressionValue(bounds2, "region.bounds");
                    linkedHashMap.put(valueOf2, new SemanticsNodeWithAdjustedBounds(semanticsNode2, bounds2));
                }
            }
        }
    }

    /* renamed from: invertTo-JiSxe2E, reason: not valid java name */
    public static final boolean m232invertToJiSxe2E(float[] fArr, float[] fArr2) {
        ResultKt.checkNotNullParameter(fArr, "$this$invertTo");
        ResultKt.checkNotNullParameter(fArr2, "other");
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[2];
        float f4 = fArr[3];
        float f5 = fArr[4];
        float f6 = fArr[5];
        float f7 = fArr[6];
        float f8 = fArr[7];
        float f9 = fArr[8];
        float f10 = fArr[9];
        float f11 = fArr[10];
        float f12 = fArr[11];
        float f13 = fArr[12];
        float f14 = fArr[13];
        float f15 = fArr[14];
        float f16 = fArr[15];
        float f17 = (f * f6) - (f2 * f5);
        float f18 = (f * f7) - (f3 * f5);
        float f19 = (f * f8) - (f4 * f5);
        float f20 = (f2 * f7) - (f3 * f6);
        float f21 = (f2 * f8) - (f4 * f6);
        float f22 = (f3 * f8) - (f4 * f7);
        float f23 = (f9 * f14) - (f10 * f13);
        float f24 = (f9 * f15) - (f11 * f13);
        float f25 = (f9 * f16) - (f12 * f13);
        float f26 = (f10 * f15) - (f11 * f14);
        float f27 = (f10 * f16) - (f12 * f14);
        float f28 = (f11 * f16) - (f12 * f15);
        float f29 = (f22 * f23) + (((f20 * f25) + ((f19 * f26) + ((f17 * f28) - (f18 * f27)))) - (f21 * f24));
        if (f29 == 0.0f) {
            return false;
        }
        float f30 = 1.0f / f29;
        fArr2[0] = ((f8 * f26) + ((f6 * f28) - (f7 * f27))) * f30;
        fArr2[1] = (((f3 * f27) + ((-f2) * f28)) - (f4 * f26)) * f30;
        fArr2[2] = ((f16 * f20) + ((f14 * f22) - (f15 * f21))) * f30;
        fArr2[3] = (((f11 * f21) + ((-f10) * f22)) - (f12 * f20)) * f30;
        float f31 = -f5;
        fArr2[4] = (((f7 * f25) + (f31 * f28)) - (f8 * f24)) * f30;
        fArr2[5] = ((f4 * f24) + ((f28 * f) - (f3 * f25))) * f30;
        float f32 = -f13;
        fArr2[6] = (((f15 * f19) + (f32 * f22)) - (f16 * f18)) * f30;
        fArr2[7] = ((f12 * f18) + ((f22 * f9) - (f11 * f19))) * f30;
        fArr2[8] = ((f8 * f23) + ((f5 * f27) - (f6 * f25))) * f30;
        fArr2[9] = (((f25 * f2) + ((-f) * f27)) - (f4 * f23)) * f30;
        fArr2[10] = ((f16 * f17) + ((f13 * f21) - (f14 * f19))) * f30;
        fArr2[11] = (((f19 * f10) + ((-f9) * f21)) - (f12 * f17)) * f30;
        fArr2[12] = (((f6 * f24) + (f31 * f26)) - (f7 * f23)) * f30;
        fArr2[13] = ((f3 * f23) + ((f * f26) - (f2 * f24))) * f30;
        fArr2[14] = (((f14 * f18) + (f32 * f20)) - (f15 * f17)) * f30;
        fArr2[15] = ((f11 * f17) + ((f9 * f20) - (f10 * f18))) * f30;
        return true;
    }

    public static final boolean isTextField(SemanticsNode semanticsNode) {
        SemanticsConfiguration semanticsConfiguration = semanticsNode.unmergedConfig;
        SemanticsPropertyKey semanticsPropertyKey = SemanticsActions.GetTextLayoutResult;
        return semanticsConfiguration.contains(SemanticsActions.SetText);
    }

    /* renamed from: isWithinEllipse-VE1yxkc, reason: not valid java name */
    public static final boolean m233isWithinEllipseVE1yxkc(float f, float f2, float f3, float f4, long j) {
        float f5 = f - f3;
        float f6 = f2 - f4;
        float m72getXimpl = CornerRadius.m72getXimpl(j);
        float m73getYimpl = CornerRadius.m73getYimpl(j);
        return ((f6 * f6) / (m73getYimpl * m73getYimpl)) + ((f5 * f5) / (m72getXimpl * m72getXimpl)) <= 1.0f;
    }

    public static final void semanticsIdToView(AndroidViewsHandler androidViewsHandler, int i) {
        Object obj;
        ResultKt.checkNotNullParameter(androidViewsHandler, "<this>");
        Set<Map.Entry<LayoutNode, Object>> entrySet = androidViewsHandler.getLayoutNodeToHolder().entrySet();
        ResultKt.checkNotNullExpressionValue(entrySet, "layoutNodeToHolder.entries");
        Iterator<T> it = entrySet.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            } else {
                obj = it.next();
                if (((LayoutNode) ((Map.Entry) obj).getKey()).semanticsId == i) {
                    break;
                }
            }
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry != null) {
            DurationKt$$ExternalSyntheticCheckNotZero0.m(entry.getValue());
        }
    }

    public static final String simpleIdentityToString(Object obj) {
        ResultKt.checkNotNullParameter(obj, "obj");
        return (obj.getClass().isAnonymousClass() ? obj.getClass().getName() : obj.getClass().getSimpleName()) + '@' + String.format("%07x", Arrays.copyOf(new Object[]{Integer.valueOf(System.identityHashCode(obj))}, 1));
    }
}
