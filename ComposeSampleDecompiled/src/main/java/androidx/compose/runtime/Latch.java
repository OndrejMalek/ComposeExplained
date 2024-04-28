package androidx.compose.runtime;

import androidx.compose.ui.node.DepthSortedSet$DepthComparator$1;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNode$Companion$Constructor$1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeSet;
import kotlin.Lazy;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class Latch {
    public final /* synthetic */ int $r8$classId;
    public boolean _isOpen;
    public Object awaiters;
    public final Object lock;
    public Collection spareList;

    public Latch(int i) {
        this.$r8$classId = 1;
        this._isOpen = false;
        this.lock = ResultKt.lazy(LayoutNode$Companion$Constructor$1.INSTANCE$2);
        this.awaiters = new DepthSortedSet$DepthComparator$1(0);
        Comparator comparator = (Comparator) this.awaiters;
        ResultKt.checkNotNullParameter(comparator, "comparator");
        this.spareList = new TreeSet(comparator);
    }

    public final void add(LayoutNode layoutNode) {
        ResultKt.checkNotNullParameter(layoutNode, "node");
        if (!layoutNode.isAttached()) {
            throw new IllegalStateException("Check failed.".toString());
        }
        if (this._isOpen) {
            Object obj = this.lock;
            Integer num = (Integer) ((Map) ((Lazy) obj).getValue()).get(layoutNode);
            if (num == null) {
                ((Map) ((Lazy) obj).getValue()).put(layoutNode, Integer.valueOf(layoutNode.depth));
            } else {
                if (num.intValue() != layoutNode.depth) {
                    throw new IllegalStateException("Check failed.".toString());
                }
            }
        }
        ((androidx.compose.ui.node.TreeSet) this.spareList).add(layoutNode);
    }

    public final boolean contains(LayoutNode layoutNode) {
        ResultKt.checkNotNullParameter(layoutNode, "node");
        boolean contains = ((androidx.compose.ui.node.TreeSet) this.spareList).contains(layoutNode);
        if (!this._isOpen || contains == ((Map) ((Lazy) this.lock).getValue()).containsKey(layoutNode)) {
            return contains;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public final boolean remove(LayoutNode layoutNode) {
        ResultKt.checkNotNullParameter(layoutNode, "node");
        if (!layoutNode.isAttached()) {
            throw new IllegalStateException("Check failed.".toString());
        }
        boolean remove = ((androidx.compose.ui.node.TreeSet) this.spareList).remove(layoutNode);
        if (this._isOpen) {
            Integer num = (Integer) ((Map) ((Lazy) this.lock).getValue()).remove(layoutNode);
            if (remove) {
                int i = layoutNode.depth;
                if (num == null || num.intValue() != i) {
                    throw new IllegalStateException("Check failed.".toString());
                }
            } else if (num != null) {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
        return remove;
    }

    public final String toString() {
        switch (this.$r8$classId) {
            case 1:
                String obj = ((androidx.compose.ui.node.TreeSet) this.spareList).toString();
                ResultKt.checkNotNullExpressionValue(obj, "set.toString()");
                return obj;
            default:
                return super.toString();
        }
    }

    public Latch() {
        this.$r8$classId = 0;
        this.lock = new Object();
        this.awaiters = new ArrayList();
        this.spareList = new ArrayList();
        this._isOpen = true;
    }
}
