package androidx.compose.ui.input.pointer;

import _COROUTINE._BOUNDARY;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.node.Snake;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.EmptyList;

/* loaded from: classes.dex */
public final class Node extends NodeParent {
    public NodeCoordinator coordinates;
    public boolean hasExited;
    public boolean isIn;
    public final Modifier.Node modifierNode;
    public PointerEvent pointerEvent;
    public final MutableVector pointerIds;
    public final LinkedHashMap relevantChanges;
    public boolean wasIn;

    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public Node(Modifier.Node node) {
        ResultKt.checkNotNullParameter(node, "modifierNode");
        this.modifierNode = node;
        ?? obj = new Object();
        obj.content = new PointerId[16];
        obj.size = 0;
        this.pointerIds = obj;
        this.relevantChanges = new LinkedHashMap();
        this.isIn = true;
        this.hasExited = true;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:25:0x005a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:29:0x0063 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:34:0x001f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:35:0x001f */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:38:0x0069 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v25 */
    /* JADX WARN: Type inference failed for: r5v26, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v27, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v28 */
    /* JADX WARN: Type inference failed for: r5v29 */
    /* JADX WARN: Type inference failed for: r5v30 */
    /* JADX WARN: Type inference failed for: r5v31 */
    /* JADX WARN: Type inference failed for: r5v32 */
    /* JADX WARN: Type inference failed for: r5v33 */
    /* JADX WARN: Type inference failed for: r7v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v24, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v25 */
    /* JADX WARN: Type inference failed for: r8v26 */
    /* JADX WARN: Type inference failed for: r8v27, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v28, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v29 */
    /* JADX WARN: Type inference failed for: r8v30 */
    /* JADX WARN: Type inference failed for: r8v31 */
    /* JADX WARN: Type inference failed for: r8v32 */
    @Override // androidx.compose.ui.input.pointer.NodeParent
    public final boolean buildCache(Map map, LayoutCoordinates layoutCoordinates, InternalPointerEvent internalPointerEvent, boolean z) {
        int i;
        LinkedHashMap linkedHashMap;
        MutableVector mutableVector;
        PointerInputChange pointerInputChange;
        boolean z2;
        boolean z3;
        PointerEvent pointerEvent;
        Iterator it;
        ResultKt.checkNotNullParameter(map, "changes");
        ResultKt.checkNotNullParameter(layoutCoordinates, "parentCoordinates");
        boolean buildCache = super.buildCache(map, layoutCoordinates, internalPointerEvent, z);
        DelegatingNode delegatingNode = this.modifierNode;
        int i2 = 1;
        if (!delegatingNode.isAttached) {
            return true;
        }
        ?? r8 = 0;
        while (true) {
            i = 0;
            if (delegatingNode == 0) {
                break;
            }
            if (delegatingNode instanceof PointerInputModifierNode) {
                this.coordinates = Snake.m215requireCoordinator64DMado((PointerInputModifierNode) delegatingNode, 16);
            } else if ((delegatingNode.kindSet & 16) != 0 && (delegatingNode instanceof DelegatingNode)) {
                Modifier.Node node = delegatingNode.delegate;
                int i3 = 0;
                delegatingNode = delegatingNode;
                r8 = r8;
                while (node != null) {
                    if ((node.kindSet & 16) != 0) {
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
        Iterator it2 = map.entrySet().iterator();
        while (true) {
            boolean hasNext = it2.hasNext();
            linkedHashMap = this.relevantChanges;
            mutableVector = this.pointerIds;
            if (!hasNext) {
                break;
            }
            Map.Entry entry = (Map.Entry) it2.next();
            long j = ((PointerId) entry.getKey()).value;
            PointerInputChange pointerInputChange2 = (PointerInputChange) entry.getValue();
            int i4 = mutableVector.size - i2;
            if (i4 >= 0) {
                int i5 = i;
                while (true) {
                    LinkedHashMap linkedHashMap2 = linkedHashMap;
                    if (((PointerId) mutableVector.content[i5]).value == j) {
                        List list = pointerInputChange2._historical;
                        List list2 = EmptyList.INSTANCE;
                        if (list == null) {
                            list = list2;
                        }
                        ArrayList arrayList = new ArrayList(list.size());
                        List list3 = pointerInputChange2._historical;
                        if (list3 != null) {
                            list2 = list3;
                        }
                        int size = list2.size();
                        int i6 = 0;
                        while (i6 < size) {
                            HistoricalChange historicalChange = (HistoricalChange) list2.get(i6);
                            long j2 = historicalChange.uptimeMillis;
                            Iterator it3 = it2;
                            NodeCoordinator nodeCoordinator = this.coordinates;
                            ResultKt.checkNotNull(nodeCoordinator);
                            arrayList.add(new HistoricalChange(j2, nodeCoordinator.mo160localPositionOfR5De75A(layoutCoordinates, historicalChange.position)));
                            i6++;
                            list2 = list2;
                            size = size;
                            pointerInputChange2 = pointerInputChange2;
                            it2 = it3;
                        }
                        it = it2;
                        PointerInputChange pointerInputChange3 = pointerInputChange2;
                        PointerId pointerId = new PointerId(j);
                        NodeCoordinator nodeCoordinator2 = this.coordinates;
                        ResultKt.checkNotNull(nodeCoordinator2);
                        long mo160localPositionOfR5De75A = nodeCoordinator2.mo160localPositionOfR5De75A(layoutCoordinates, pointerInputChange3.previousPosition);
                        NodeCoordinator nodeCoordinator3 = this.coordinates;
                        ResultKt.checkNotNull(nodeCoordinator3);
                        PointerInputChange pointerInputChange4 = new PointerInputChange(pointerInputChange3.id, pointerInputChange3.uptimeMillis, nodeCoordinator3.mo160localPositionOfR5De75A(layoutCoordinates, pointerInputChange3.position), pointerInputChange3.pressed, pointerInputChange3.pressure, pointerInputChange3.previousUptimeMillis, mo160localPositionOfR5De75A, pointerInputChange3.previousPressed, pointerInputChange3.type, arrayList, pointerInputChange3.scrollDelta);
                        pointerInputChange4.consumed = pointerInputChange3.consumed;
                        linkedHashMap2.put(pointerId, pointerInputChange4);
                    } else {
                        it = it2;
                        if (i5 != i4) {
                            i5++;
                            linkedHashMap = linkedHashMap2;
                            it2 = it;
                        }
                    }
                }
            } else {
                it = it2;
            }
            it2 = it;
            i2 = 1;
            i = 0;
        }
        if (linkedHashMap.isEmpty()) {
            mutableVector.clear();
            this.children.clear();
            return true;
        }
        for (int i7 = mutableVector.size - 1; -1 < i7; i7--) {
            if (!map.containsKey(new PointerId(((PointerId) mutableVector.content[i7]).value))) {
                mutableVector.removeAt(i7);
            }
        }
        List list4 = CollectionsKt___CollectionsKt.toList(linkedHashMap.values());
        PointerEvent pointerEvent2 = new PointerEvent(list4, internalPointerEvent);
        int size2 = list4.size();
        int i8 = 0;
        while (true) {
            if (i8 >= size2) {
                pointerInputChange = null;
                break;
            }
            ?? r7 = list4.get(i8);
            if (internalPointerEvent.m153issuesEnterExitEvent0FcD4WY(((PointerInputChange) r7).id)) {
                pointerInputChange = r7;
                break;
            }
            i8++;
        }
        PointerInputChange pointerInputChange5 = pointerInputChange;
        if (pointerInputChange5 != null) {
            boolean z4 = pointerInputChange5.pressed;
            if (z) {
                z2 = false;
                z2 = false;
                z2 = false;
                if (!this.isIn && (z4 || pointerInputChange5.previousPressed)) {
                    NodeCoordinator nodeCoordinator4 = this.coordinates;
                    ResultKt.checkNotNull(nodeCoordinator4);
                    boolean m13isOutOfBoundsO0kMr_c = _BOUNDARY.m13isOutOfBoundsO0kMr_c(pointerInputChange5, nodeCoordinator4.measuredSize);
                    z3 = true;
                    this.isIn = !m13isOutOfBoundsO0kMr_c;
                    if (this.isIn == this.wasIn && (PointerEventType.m155equalsimpl0(pointerEvent2.type, 3) || PointerEventType.m155equalsimpl0(pointerEvent2.type, 4) || PointerEventType.m155equalsimpl0(pointerEvent2.type, 5))) {
                        pointerEvent2.type = this.isIn ? 4 : 5;
                    } else if (!PointerEventType.m155equalsimpl0(pointerEvent2.type, 4) && this.wasIn && !this.hasExited) {
                        pointerEvent2.type = 3;
                    } else if (PointerEventType.m155equalsimpl0(pointerEvent2.type, 5) && this.isIn && z4) {
                        pointerEvent2.type = 3;
                    }
                }
            } else {
                z2 = false;
                this.isIn = false;
            }
            z3 = true;
            if (this.isIn == this.wasIn) {
            }
            if (!PointerEventType.m155equalsimpl0(pointerEvent2.type, 4)) {
            }
            if (PointerEventType.m155equalsimpl0(pointerEvent2.type, 5)) {
                pointerEvent2.type = 3;
            }
        } else {
            z2 = false;
            z3 = true;
        }
        if (!buildCache && PointerEventType.m155equalsimpl0(pointerEvent2.type, 3) && (pointerEvent = this.pointerEvent) != null) {
            List list5 = pointerEvent.changes;
            if (list5.size() == list4.size()) {
                int size3 = list4.size();
                for (int i9 = z2 ? 1 : 0; i9 < size3; i9++) {
                    if (Offset.m75equalsimpl0(((PointerInputChange) list5.get(i9)).position, ((PointerInputChange) list4.get(i9)).position)) {
                    }
                }
                this.pointerEvent = pointerEvent2;
                return z2;
            }
        }
        z2 = z3;
        this.pointerEvent = pointerEvent2;
        return z2;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public final void cleanUpHits(InternalPointerEvent internalPointerEvent) {
        super.cleanUpHits(internalPointerEvent);
        PointerEvent pointerEvent = this.pointerEvent;
        if (pointerEvent == null) {
            return;
        }
        this.wasIn = this.isIn;
        List list = pointerEvent.changes;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            PointerInputChange pointerInputChange = (PointerInputChange) list.get(i);
            if (!pointerInputChange.pressed) {
                long j = pointerInputChange.id;
                if (!internalPointerEvent.m153issuesEnterExitEvent0FcD4WY(j) || !this.isIn) {
                    this.pointerIds.remove(new PointerId(j));
                }
            }
        }
        this.isIn = false;
        this.hasExited = PointerEventType.m155equalsimpl0(pointerEvent.type, 5);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:27:0x0050 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:31:0x0059 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:36:0x0019 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:37:0x0019 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:40:0x005f */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r4v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    @Override // androidx.compose.ui.input.pointer.NodeParent
    public final void dispatchCancel() {
        MutableVector mutableVector = this.children;
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                ((Node) objArr[i2]).dispatchCancel();
                i2++;
            } while (i2 < i);
        }
        DelegatingNode delegatingNode = this.modifierNode;
        ?? r4 = 0;
        while (delegatingNode != 0) {
            if (delegatingNode instanceof PointerInputModifierNode) {
                ((PointerInputModifierNode) delegatingNode).onCancelPointerInput();
            } else if ((delegatingNode.kindSet & 16) != 0 && (delegatingNode instanceof DelegatingNode)) {
                Modifier.Node node = delegatingNode.delegate;
                int i3 = 0;
                delegatingNode = delegatingNode;
                r4 = r4;
                while (node != null) {
                    if ((node.kindSet & 16) != 0) {
                        i3++;
                        r4 = r4;
                        if (i3 == 1) {
                            delegatingNode = node;
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
                            r4.add(node);
                        }
                    }
                    node = node.child;
                    delegatingNode = delegatingNode;
                    r4 = r4;
                }
                if (i3 == 1) {
                }
            }
            delegatingNode = Snake.access$pop(r4);
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:25:0x005c */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:29:0x0065 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:34:0x0022 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:35:0x0022 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:38:0x006b */
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
    @Override // androidx.compose.ui.input.pointer.NodeParent
    public final boolean dispatchFinalEventPass(InternalPointerEvent internalPointerEvent) {
        MutableVector mutableVector;
        int i;
        LinkedHashMap linkedHashMap = this.relevantChanges;
        boolean z = false;
        int i2 = 0;
        z = false;
        if (!linkedHashMap.isEmpty()) {
            Modifier.Node node = this.modifierNode;
            if (node.isAttached) {
                PointerEvent pointerEvent = this.pointerEvent;
                ResultKt.checkNotNull(pointerEvent);
                NodeCoordinator nodeCoordinator = this.coordinates;
                ResultKt.checkNotNull(nodeCoordinator);
                long j = nodeCoordinator.measuredSize;
                DelegatingNode delegatingNode = node;
                ?? r8 = 0;
                while (delegatingNode != 0) {
                    if (delegatingNode instanceof PointerInputModifierNode) {
                        ((PointerInputModifierNode) delegatingNode).mo25onPointerEventH0pRuoY(pointerEvent, PointerEventPass.Final, j);
                    } else if ((delegatingNode.kindSet & 16) != 0 && (delegatingNode instanceof DelegatingNode)) {
                        Modifier.Node node2 = delegatingNode.delegate;
                        int i3 = 0;
                        delegatingNode = delegatingNode;
                        r8 = r8;
                        while (node2 != null) {
                            if ((node2.kindSet & 16) != 0) {
                                i3++;
                                r8 = r8;
                                if (i3 == 1) {
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
                        if (i3 == 1) {
                        }
                    }
                    delegatingNode = Snake.access$pop(r8);
                }
                if (node.isAttached && (i = (mutableVector = this.children).size) > 0) {
                    Object[] objArr = mutableVector.content;
                    do {
                        ((Node) objArr[i2]).dispatchFinalEventPass(internalPointerEvent);
                        i2++;
                    } while (i2 < i);
                }
                z = true;
            }
        }
        cleanUpHits(internalPointerEvent);
        linkedHashMap.clear();
        this.coordinates = null;
        return z;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:25:0x006b */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:29:0x0074 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:34:0x0031 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:35:0x0031 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:38:0x007a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:72:0x00db */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:76:0x00e4 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:81:0x00a4 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:82:0x00a4 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:85:0x00ea */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r1v11, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v15 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v3, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v12, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v13 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18 */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r9v11, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    @Override // androidx.compose.ui.input.pointer.NodeParent
    public final boolean dispatchMainEventPass(Map map, LayoutCoordinates layoutCoordinates, InternalPointerEvent internalPointerEvent, boolean z) {
        MutableVector mutableVector;
        int i;
        ResultKt.checkNotNullParameter(map, "changes");
        ResultKt.checkNotNullParameter(layoutCoordinates, "parentCoordinates");
        LinkedHashMap linkedHashMap = this.relevantChanges;
        if (linkedHashMap.isEmpty()) {
            return false;
        }
        DelegatingNode delegatingNode = this.modifierNode;
        if (!delegatingNode.isAttached) {
            return false;
        }
        PointerEvent pointerEvent = this.pointerEvent;
        ResultKt.checkNotNull(pointerEvent);
        NodeCoordinator nodeCoordinator = this.coordinates;
        ResultKt.checkNotNull(nodeCoordinator);
        long j = nodeCoordinator.measuredSize;
        DelegatingNode delegatingNode2 = delegatingNode;
        ?? r9 = 0;
        while (delegatingNode2 != 0) {
            if (delegatingNode2 instanceof PointerInputModifierNode) {
                ((PointerInputModifierNode) delegatingNode2).mo25onPointerEventH0pRuoY(pointerEvent, PointerEventPass.Initial, j);
            } else if ((delegatingNode2.kindSet & 16) != 0 && (delegatingNode2 instanceof DelegatingNode)) {
                Modifier.Node node = delegatingNode2.delegate;
                int i2 = 0;
                delegatingNode2 = delegatingNode2;
                r9 = r9;
                while (node != null) {
                    if ((node.kindSet & 16) != 0) {
                        i2++;
                        r9 = r9;
                        if (i2 == 1) {
                            delegatingNode2 = node;
                        } else {
                            if (r9 == 0) {
                                ?? obj = new Object();
                                obj.content = new Modifier.Node[16];
                                obj.size = 0;
                                r9 = obj;
                            }
                            if (delegatingNode2 != 0) {
                                r9.add(delegatingNode2);
                                delegatingNode2 = 0;
                            }
                            r9.add(node);
                        }
                    }
                    node = node.child;
                    delegatingNode2 = delegatingNode2;
                    r9 = r9;
                }
                if (i2 == 1) {
                }
            }
            delegatingNode2 = Snake.access$pop(r9);
        }
        if (delegatingNode.isAttached && (i = (mutableVector = this.children).size) > 0) {
            Object[] objArr = mutableVector.content;
            int i3 = 0;
            do {
                Node node2 = (Node) objArr[i3];
                NodeCoordinator nodeCoordinator2 = this.coordinates;
                ResultKt.checkNotNull(nodeCoordinator2);
                node2.dispatchMainEventPass(linkedHashMap, nodeCoordinator2, internalPointerEvent, z);
                i3++;
            } while (i3 < i);
        }
        if (delegatingNode.isAttached) {
            ?? r1 = 0;
            while (delegatingNode != 0) {
                if (delegatingNode instanceof PointerInputModifierNode) {
                    ((PointerInputModifierNode) delegatingNode).mo25onPointerEventH0pRuoY(pointerEvent, PointerEventPass.Main, j);
                } else if ((delegatingNode.kindSet & 16) != 0 && (delegatingNode instanceof DelegatingNode)) {
                    Modifier.Node node3 = delegatingNode.delegate;
                    int i4 = 0;
                    r1 = r1;
                    delegatingNode = delegatingNode;
                    while (node3 != null) {
                        if ((node3.kindSet & 16) != 0) {
                            i4++;
                            r1 = r1;
                            if (i4 == 1) {
                                delegatingNode = node3;
                            } else {
                                if (r1 == 0) {
                                    ?? obj2 = new Object();
                                    obj2.content = new Modifier.Node[16];
                                    obj2.size = 0;
                                    r1 = obj2;
                                }
                                if (delegatingNode != 0) {
                                    r1.add(delegatingNode);
                                    delegatingNode = 0;
                                }
                                r1.add(node3);
                            }
                        }
                        node3 = node3.child;
                        r1 = r1;
                        delegatingNode = delegatingNode;
                    }
                    if (i4 == 1) {
                    }
                }
                delegatingNode = Snake.access$pop(r1);
            }
        }
        return true;
    }

    public final String toString() {
        return "Node(pointerInputFilter=" + this.modifierNode + ", children=" + this.children + ", pointerIds=" + this.pointerIds + ')';
    }
}
