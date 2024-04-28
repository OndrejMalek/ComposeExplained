package androidx.compose.ui.semantics;

import _COROUTINE._BOUNDARY;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.Snake;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.AbstractMap$toString$1;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.reflect.KProperty;

/* loaded from: classes.dex */
public final class SemanticsNode {
    public SemanticsNode fakeNodeParent;
    public final int id;
    public boolean isFake;
    public final LayoutNode layoutNode;
    public final boolean mergingEnabled;
    public final Modifier.Node outerSemanticsNode;
    public final SemanticsConfiguration unmergedConfig;

    public SemanticsNode(Modifier.Node node, boolean z, LayoutNode layoutNode, SemanticsConfiguration semanticsConfiguration) {
        ResultKt.checkNotNullParameter(node, "outerSemanticsNode");
        ResultKt.checkNotNullParameter(layoutNode, "layoutNode");
        ResultKt.checkNotNullParameter(semanticsConfiguration, "unmergedConfig");
        this.outerSemanticsNode = node;
        this.mergingEnabled = z;
        this.layoutNode = layoutNode;
        this.unmergedConfig = semanticsConfiguration;
        this.id = layoutNode.semanticsId;
    }

    /* renamed from: fakeSemanticsNode-ypyhhiA, reason: not valid java name */
    public final SemanticsNode m239fakeSemanticsNodeypyhhiA(Role role, Function1 function1) {
        SemanticsConfiguration semanticsConfiguration = new SemanticsConfiguration();
        semanticsConfiguration.isMergingSemanticsOfDescendants = false;
        semanticsConfiguration.isClearingSemantics = false;
        function1.invoke(semanticsConfiguration);
        SemanticsNode semanticsNode = new SemanticsNode(new SemanticsNode$fakeSemanticsNode$fakeNode$1(function1), false, new LayoutNode(this.id + (role != null ? 1000000000 : 2000000000), true), semanticsConfiguration);
        semanticsNode.isFake = true;
        semanticsNode.fakeNodeParent = this;
        return semanticsNode;
    }

    public final void fillOneLayerOfSemanticsWrappers(LayoutNode layoutNode, ArrayList arrayList) {
        MutableVector zSortedChildren = layoutNode.getZSortedChildren();
        int i = zSortedChildren.size;
        if (i > 0) {
            Object[] objArr = zSortedChildren.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode2 = (LayoutNode) objArr[i2];
                if (layoutNode2.nodes.m190hasH91voCI$ui_release(8)) {
                    arrayList.add(_BOUNDARY.SemanticsNode(layoutNode2, this.mergingEnabled));
                } else {
                    fillOneLayerOfSemanticsWrappers(layoutNode2, arrayList);
                }
                i2++;
            } while (i2 < i);
        }
    }

    public final NodeCoordinator findCoordinatorToGetBounds$ui_release() {
        if (this.isFake) {
            SemanticsNode parent = getParent();
            if (parent != null) {
                return parent.findCoordinatorToGetBounds$ui_release();
            }
            return null;
        }
        DelegatableNode outerMergingSemantics = _BOUNDARY.getOuterMergingSemantics(this.layoutNode);
        if (outerMergingSemantics == null) {
            outerMergingSemantics = this.outerSemanticsNode;
        }
        return Snake.m215requireCoordinator64DMado(outerMergingSemantics, 8);
    }

    public final void findOneLayerOfMergingSemanticsNodes(List list) {
        List unmergedChildren$ui_release = unmergedChildren$ui_release(false);
        int size = unmergedChildren$ui_release.size();
        for (int i = 0; i < size; i++) {
            SemanticsNode semanticsNode = (SemanticsNode) unmergedChildren$ui_release.get(i);
            if (semanticsNode.isMergingSemanticsOfDescendants()) {
                list.add(semanticsNode);
            } else if (!semanticsNode.unmergedConfig.isClearingSemantics) {
                semanticsNode.findOneLayerOfMergingSemanticsNodes(list);
            }
        }
    }

    public final Rect getBoundsInRoot() {
        NodeCoordinator findCoordinatorToGetBounds$ui_release = findCoordinatorToGetBounds$ui_release();
        if (findCoordinatorToGetBounds$ui_release != null) {
            if (!findCoordinatorToGetBounds$ui_release.isAttached()) {
                findCoordinatorToGetBounds$ui_release = null;
            }
            if (findCoordinatorToGetBounds$ui_release != null) {
                return _BOUNDARY.findRootCoordinates(findCoordinatorToGetBounds$ui_release).localBoundingBoxOf(findCoordinatorToGetBounds$ui_release, true);
            }
        }
        return Rect.Zero;
    }

    public final Rect getBoundsInWindow() {
        NodeCoordinator findCoordinatorToGetBounds$ui_release = findCoordinatorToGetBounds$ui_release();
        Rect rect = Rect.Zero;
        if (findCoordinatorToGetBounds$ui_release == null) {
            return rect;
        }
        if (!findCoordinatorToGetBounds$ui_release.isAttached()) {
            findCoordinatorToGetBounds$ui_release = null;
        }
        if (findCoordinatorToGetBounds$ui_release == null) {
            return rect;
        }
        LayoutCoordinates findRootCoordinates = _BOUNDARY.findRootCoordinates(findCoordinatorToGetBounds$ui_release);
        Rect localBoundingBoxOf = _BOUNDARY.findRootCoordinates(findCoordinatorToGetBounds$ui_release).localBoundingBoxOf(findCoordinatorToGetBounds$ui_release, true);
        float mo159getSizeYbymL2g = (int) (findRootCoordinates.mo159getSizeYbymL2g() >> 32);
        float mo159getSizeYbymL2g2 = (int) (findRootCoordinates.mo159getSizeYbymL2g() & 4294967295L);
        float coerceIn = ResultKt.coerceIn(localBoundingBoxOf.left, 0.0f, mo159getSizeYbymL2g);
        float coerceIn2 = ResultKt.coerceIn(localBoundingBoxOf.top, 0.0f, mo159getSizeYbymL2g2);
        float coerceIn3 = ResultKt.coerceIn(localBoundingBoxOf.right, 0.0f, mo159getSizeYbymL2g);
        float coerceIn4 = ResultKt.coerceIn(localBoundingBoxOf.bottom, 0.0f, mo159getSizeYbymL2g2);
        if (coerceIn == coerceIn3 || coerceIn2 == coerceIn4) {
            return rect;
        }
        long mo162localToWindowMKHz9U = findRootCoordinates.mo162localToWindowMKHz9U(_BOUNDARY.Offset(coerceIn, coerceIn2));
        long mo162localToWindowMKHz9U2 = findRootCoordinates.mo162localToWindowMKHz9U(_BOUNDARY.Offset(coerceIn3, coerceIn2));
        long mo162localToWindowMKHz9U3 = findRootCoordinates.mo162localToWindowMKHz9U(_BOUNDARY.Offset(coerceIn3, coerceIn4));
        long mo162localToWindowMKHz9U4 = findRootCoordinates.mo162localToWindowMKHz9U(_BOUNDARY.Offset(coerceIn, coerceIn4));
        float m76getXimpl = Offset.m76getXimpl(mo162localToWindowMKHz9U);
        float[] fArr = {Offset.m76getXimpl(mo162localToWindowMKHz9U2), Offset.m76getXimpl(mo162localToWindowMKHz9U4), Offset.m76getXimpl(mo162localToWindowMKHz9U3)};
        for (int i = 0; i < 3; i++) {
            m76getXimpl = Math.min(m76getXimpl, fArr[i]);
        }
        float m77getYimpl = Offset.m77getYimpl(mo162localToWindowMKHz9U);
        float[] fArr2 = {Offset.m77getYimpl(mo162localToWindowMKHz9U2), Offset.m77getYimpl(mo162localToWindowMKHz9U4), Offset.m77getYimpl(mo162localToWindowMKHz9U3)};
        float f = m77getYimpl;
        for (int i2 = 0; i2 < 3; i2++) {
            f = Math.min(f, fArr2[i2]);
        }
        float m76getXimpl2 = Offset.m76getXimpl(mo162localToWindowMKHz9U);
        float[] fArr3 = {Offset.m76getXimpl(mo162localToWindowMKHz9U2), Offset.m76getXimpl(mo162localToWindowMKHz9U4), Offset.m76getXimpl(mo162localToWindowMKHz9U3)};
        float f2 = m76getXimpl2;
        for (int i3 = 0; i3 < 3; i3++) {
            f2 = Math.max(f2, fArr3[i3]);
        }
        float m77getYimpl2 = Offset.m77getYimpl(mo162localToWindowMKHz9U);
        float[] fArr4 = {Offset.m77getYimpl(mo162localToWindowMKHz9U2), Offset.m77getYimpl(mo162localToWindowMKHz9U4), Offset.m77getYimpl(mo162localToWindowMKHz9U3)};
        for (int i4 = 0; i4 < 3; i4++) {
            m77getYimpl2 = Math.max(m77getYimpl2, fArr4[i4]);
        }
        return new Rect(m76getXimpl, f, f2, m77getYimpl2);
    }

    public final List getChildren(boolean z, boolean z2) {
        if (!z && this.unmergedConfig.isClearingSemantics) {
            return EmptyList.INSTANCE;
        }
        if (!isMergingSemanticsOfDescendants()) {
            return unmergedChildren$ui_release(z2);
        }
        ArrayList arrayList = new ArrayList();
        findOneLayerOfMergingSemanticsNodes(arrayList);
        return arrayList;
    }

    public final SemanticsConfiguration getConfig() {
        boolean isMergingSemanticsOfDescendants = isMergingSemanticsOfDescendants();
        SemanticsConfiguration semanticsConfiguration = this.unmergedConfig;
        if (!isMergingSemanticsOfDescendants) {
            return semanticsConfiguration;
        }
        semanticsConfiguration.getClass();
        SemanticsConfiguration semanticsConfiguration2 = new SemanticsConfiguration();
        semanticsConfiguration2.isMergingSemanticsOfDescendants = semanticsConfiguration.isMergingSemanticsOfDescendants;
        semanticsConfiguration2.isClearingSemantics = semanticsConfiguration.isClearingSemantics;
        semanticsConfiguration2.props.putAll(semanticsConfiguration.props);
        mergeConfig(semanticsConfiguration2);
        return semanticsConfiguration2;
    }

    public final SemanticsNode getParent() {
        SemanticsNode semanticsNode = this.fakeNodeParent;
        if (semanticsNode != null) {
            return semanticsNode;
        }
        LayoutNode layoutNode = this.layoutNode;
        boolean z = this.mergingEnabled;
        LayoutNode findClosestParentNode = z ? _BOUNDARY.findClosestParentNode(layoutNode, SemanticsNode$parent$1.INSTANCE) : null;
        if (findClosestParentNode == null) {
            findClosestParentNode = _BOUNDARY.findClosestParentNode(layoutNode, SemanticsNode$parent$1.INSTANCE$2);
        }
        if (findClosestParentNode == null) {
            return null;
        }
        return _BOUNDARY.SemanticsNode(findClosestParentNode, z);
    }

    public final boolean isMergingSemanticsOfDescendants() {
        return this.mergingEnabled && this.unmergedConfig.isMergingSemanticsOfDescendants;
    }

    public final void mergeConfig(SemanticsConfiguration semanticsConfiguration) {
        if (this.unmergedConfig.isClearingSemantics) {
            return;
        }
        List unmergedChildren$ui_release = unmergedChildren$ui_release(false);
        int size = unmergedChildren$ui_release.size();
        for (int i = 0; i < size; i++) {
            SemanticsNode semanticsNode = (SemanticsNode) unmergedChildren$ui_release.get(i);
            if (!semanticsNode.isMergingSemanticsOfDescendants()) {
                SemanticsConfiguration semanticsConfiguration2 = semanticsNode.unmergedConfig;
                ResultKt.checkNotNullParameter(semanticsConfiguration2, "child");
                for (Map.Entry entry : semanticsConfiguration2.props.entrySet()) {
                    SemanticsPropertyKey semanticsPropertyKey = (SemanticsPropertyKey) entry.getKey();
                    Object value = entry.getValue();
                    LinkedHashMap linkedHashMap = semanticsConfiguration.props;
                    Object obj = linkedHashMap.get(semanticsPropertyKey);
                    ResultKt.checkNotNull(semanticsPropertyKey, "null cannot be cast to non-null type androidx.compose.ui.semantics.SemanticsPropertyKey<kotlin.Any?>");
                    Object invoke = semanticsPropertyKey.mergePolicy.invoke(obj, value);
                    if (invoke != null) {
                        linkedHashMap.put(semanticsPropertyKey, invoke);
                    }
                }
                semanticsNode.mergeConfig(semanticsConfiguration);
            }
        }
    }

    public final List unmergedChildren$ui_release(boolean z) {
        if (this.isFake) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList();
        fillOneLayerOfSemanticsWrappers(this.layoutNode, arrayList);
        if (z) {
            SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.Role;
            SemanticsConfiguration semanticsConfiguration = this.unmergedConfig;
            Role role = (Role) _BOUNDARY.getOrNull(semanticsConfiguration, semanticsPropertyKey);
            final int i = 1;
            if (role != null && semanticsConfiguration.isMergingSemanticsOfDescendants && (!arrayList.isEmpty())) {
                arrayList.add(m239fakeSemanticsNodeypyhhiA(role, new AbstractMap$toString$1(18, role)));
            }
            SemanticsPropertyKey semanticsPropertyKey2 = SemanticsProperties.ContentDescription;
            if (semanticsConfiguration.contains(semanticsPropertyKey2) && (!arrayList.isEmpty()) && semanticsConfiguration.isMergingSemanticsOfDescendants) {
                List list = (List) _BOUNDARY.getOrNull(semanticsConfiguration, semanticsPropertyKey2);
                final String str = list != null ? (String) CollectionsKt___CollectionsKt.firstOrNull(list) : null;
                if (str != null) {
                    arrayList.add(0, m239fakeSemanticsNodeypyhhiA(null, new Function1() { // from class: kotlin.text.StringsKt__IndentKt$getIndentFunction$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            int i2 = i;
                            String str2 = str;
                            switch (i2) {
                                case 0:
                                    String str3 = (String) obj;
                                    ResultKt.checkNotNullParameter(str3, "line");
                                    return str2 + str3;
                                default:
                                    SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj;
                                    ResultKt.checkNotNullParameter(semanticsPropertyReceiver, "$this$fakeSemanticsNode");
                                    KProperty[] kPropertyArr = SemanticsPropertiesKt.$$delegatedProperties;
                                    ResultKt.checkNotNullParameter(str2, "value");
                                    ((SemanticsConfiguration) semanticsPropertyReceiver).set(SemanticsProperties.ContentDescription, ResultKt.listOf(str2));
                                    return Unit.INSTANCE;
                            }
                        }
                    }));
                }
            }
        }
        return arrayList;
    }
}
