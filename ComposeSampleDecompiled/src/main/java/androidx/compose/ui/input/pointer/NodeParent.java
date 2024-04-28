package androidx.compose.ui.input.pointer;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.layout.LayoutCoordinates;
import java.util.Map;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public class NodeParent {
    public final MutableVector children;

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public NodeParent() {
        ?? obj = new Object();
        obj.content = new Node[16];
        obj.size = 0;
        this.children = obj;
    }

    public boolean buildCache(Map map, LayoutCoordinates layoutCoordinates, InternalPointerEvent internalPointerEvent, boolean z) {
        ResultKt.checkNotNullParameter(map, "changes");
        ResultKt.checkNotNullParameter(layoutCoordinates, "parentCoordinates");
        MutableVector mutableVector = this.children;
        int i = mutableVector.size;
        if (i <= 0) {
            return false;
        }
        Object[] objArr = mutableVector.content;
        int i2 = 0;
        boolean z2 = false;
        do {
            z2 = ((Node) objArr[i2]).buildCache(map, layoutCoordinates, internalPointerEvent, z) || z2;
            i2++;
        } while (i2 < i);
        return z2;
    }

    public void cleanUpHits(InternalPointerEvent internalPointerEvent) {
        MutableVector mutableVector = this.children;
        int i = mutableVector.size;
        while (true) {
            i--;
            if (-1 >= i) {
                return;
            }
            if (((Node) mutableVector.content[i]).pointerIds.size == 0) {
                mutableVector.removeAt(i);
            }
        }
    }

    public void dispatchCancel() {
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
    }

    public boolean dispatchFinalEventPass(InternalPointerEvent internalPointerEvent) {
        MutableVector mutableVector = this.children;
        int i = mutableVector.size;
        boolean z = false;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            boolean z2 = false;
            do {
                z2 = ((Node) objArr[i2]).dispatchFinalEventPass(internalPointerEvent) || z2;
                i2++;
            } while (i2 < i);
            z = z2;
        }
        cleanUpHits(internalPointerEvent);
        return z;
    }

    public boolean dispatchMainEventPass(Map map, LayoutCoordinates layoutCoordinates, InternalPointerEvent internalPointerEvent, boolean z) {
        ResultKt.checkNotNullParameter(map, "changes");
        ResultKt.checkNotNullParameter(layoutCoordinates, "parentCoordinates");
        MutableVector mutableVector = this.children;
        int i = mutableVector.size;
        if (i <= 0) {
            return false;
        }
        Object[] objArr = mutableVector.content;
        int i2 = 0;
        boolean z2 = false;
        do {
            z2 = ((Node) objArr[i2]).dispatchMainEventPass(map, layoutCoordinates, internalPointerEvent, z) || z2;
            i2++;
        } while (i2 < i);
        return z2;
    }

    public final void removeDetachedPointerInputFilters() {
        int i = 0;
        while (true) {
            MutableVector mutableVector = this.children;
            if (i >= mutableVector.size) {
                return;
            }
            Node node = (Node) mutableVector.content[i];
            if (node.modifierNode.isAttached) {
                i++;
                node.removeDetachedPointerInputFilters();
            } else {
                mutableVector.removeAt(i);
                node.dispatchCancel();
            }
        }
    }
}
