package androidx.compose.ui.platform;

import androidx.compose.material.ripple.AndroidRippleIndicationInstance;
import androidx.compose.material.ripple.RippleHostView;
import androidx.compose.runtime.Latch;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.internal.ThreadMap;
import androidx.compose.runtime.internal.ThreadMapKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.Node;
import androidx.compose.ui.input.pointer.NodeParent;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.node.HitTestResult;
import androidx.compose.ui.node.InnerNodeCoordinator;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNode$_foldedChildren$1;
import androidx.compose.ui.text.caches.LruCache;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class WeakCache {
    public final /* synthetic */ int $r8$classId;
    public final Object referenceQueue;
    public final Object values;

    public /* synthetic */ WeakCache(Object obj, int i, Object obj2) {
        this.$r8$classId = i;
        this.values = obj;
        this.referenceQueue = obj2;
    }

    public final void add(LayoutNode layoutNode, boolean z) {
        ResultKt.checkNotNullParameter(layoutNode, "node");
        Object obj = this.values;
        if (z) {
            ((Latch) obj).add(layoutNode);
        } else {
            if (((Latch) obj).contains(layoutNode)) {
                return;
            }
            ((Latch) this.referenceQueue).add(layoutNode);
        }
    }

    /* renamed from: addHitPath-KNwqfcY, reason: not valid java name */
    public final void m237addHitPathKNwqfcY(long j, HitTestResult hitTestResult) {
        Object obj;
        ResultKt.checkNotNullParameter(hitTestResult, "pointerInputNodes");
        NodeParent nodeParent = (NodeParent) this.referenceQueue;
        int i = hitTestResult.size;
        boolean z = true;
        for (int i2 = 0; i2 < i; i2++) {
            Modifier.Node node = (Modifier.Node) hitTestResult.get(i2);
            if (z) {
                MutableVector mutableVector = nodeParent.children;
                int i3 = mutableVector.size;
                if (i3 > 0) {
                    Object[] objArr = mutableVector.content;
                    int i4 = 0;
                    do {
                        obj = objArr[i4];
                        if (ResultKt.areEqual(((Node) obj).modifierNode, node)) {
                            break;
                        } else {
                            i4++;
                        }
                    } while (i4 < i3);
                }
                obj = null;
                Node node2 = (Node) obj;
                if (node2 != null) {
                    node2.isIn = true;
                    PointerId pointerId = new PointerId(j);
                    MutableVector mutableVector2 = node2.pointerIds;
                    if (!mutableVector2.contains(pointerId)) {
                        mutableVector2.add(new PointerId(j));
                    }
                    nodeParent = node2;
                } else {
                    z = false;
                }
            }
            Node node3 = new Node(node);
            node3.pointerIds.add(new PointerId(j));
            nodeParent.children.add(node3);
            nodeParent = node3;
        }
    }

    public final void clearWeakReferences() {
        Reference poll;
        do {
            poll = ((ReferenceQueue) this.referenceQueue).poll();
            if (poll != null) {
                ((MutableVector) this.values).remove(poll);
            }
        } while (poll != null);
    }

    public final Object get() {
        ThreadMap threadMap = (ThreadMap) ((AtomicReference) this.values).get();
        int find = threadMap.find(Thread.currentThread().getId());
        if (find >= 0) {
            return threadMap.values[find];
        }
        return null;
    }

    public final void remove(AndroidRippleIndicationInstance androidRippleIndicationInstance) {
        ResultKt.checkNotNullParameter(androidRippleIndicationInstance, "indicationInstance");
        Object obj = this.values;
        RippleHostView rippleHostView = (RippleHostView) ((Map) obj).get(androidRippleIndicationInstance);
        if (rippleHostView != null) {
        }
        ((Map) obj).remove(androidRippleIndicationInstance);
    }

    public final void set(Object obj) {
        long id = Thread.currentThread().getId();
        synchronized (this.referenceQueue) {
            ThreadMap threadMap = (ThreadMap) ((AtomicReference) this.values).get();
            int find = threadMap.find(id);
            if (find < 0) {
                ((AtomicReference) this.values).set(threadMap.newWith(id, obj));
            } else {
                threadMap.values[find] = obj;
            }
        }
    }

    public WeakCache(MutableVector mutableVector, LayoutNode$_foldedChildren$1 layoutNode$_foldedChildren$1) {
        this.$r8$classId = 5;
        this.values = mutableVector;
        this.referenceQueue = layoutNode$_foldedChildren$1;
    }

    /* JADX WARN: Type inference failed for: r2v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public WeakCache(int i) {
        this.$r8$classId = i;
        if (i == 1) {
            this.values = new LinkedHashMap();
            this.referenceQueue = new LinkedHashMap();
            return;
        }
        if (i == 2) {
            this.values = new AtomicReference(ThreadMapKt.emptyThreadMap);
            this.referenceQueue = new Object();
        } else {
            if (i != 7) {
                ?? obj = new Object();
                obj.content = new Reference[16];
                obj.size = 0;
                this.values = obj;
                this.referenceQueue = new ReferenceQueue();
                return;
            }
            this.values = new Object();
            this.referenceQueue = new LruCache();
        }
    }

    public WeakCache(InnerNodeCoordinator innerNodeCoordinator) {
        this.$r8$classId = 3;
        ResultKt.checkNotNullParameter(innerNodeCoordinator, "rootCoordinates");
        this.values = innerNodeCoordinator;
        this.referenceQueue = new NodeParent();
    }

    public WeakCache() {
        this.$r8$classId = 4;
        this.values = new Latch(0);
        this.referenceQueue = new Latch(0);
    }
}
