package androidx.compose.runtime;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.UiApplier;
import androidx.compose.ui.platform.WeakCache;
import java.util.ArrayList;
import kotlin.jvm.functions.Function0;

/* loaded from: classes.dex */
public abstract class AbstractApplier implements Applier {
    public Object current;
    public final Object root;
    public final ArrayList stack = new ArrayList();

    public AbstractApplier(LayoutNode layoutNode) {
        this.root = layoutNode;
        this.current = layoutNode;
    }

    public final void clear() {
        int i;
        this.stack.clear();
        this.current = this.root;
        LayoutNode layoutNode = (LayoutNode) ((UiApplier) this).root;
        WeakCache weakCache = layoutNode._foldedChildren;
        int i2 = weakCache.$r8$classId;
        Object obj = weakCache.values;
        switch (i2) {
            case 0:
                weakCache.clearWeakReferences();
                i = ((MutableVector) obj).size;
                break;
            default:
                i = ((MutableVector) obj).size;
                break;
        }
        while (true) {
            i--;
            if (-1 >= i) {
                ((MutableVector) obj).clear();
                ((Function0) weakCache.referenceQueue).invoke();
                return;
            }
            layoutNode.onChildRemoved((LayoutNode) ((MutableVector) obj).content[i]);
        }
    }

    @Override // androidx.compose.runtime.Applier
    public final void down(Object obj) {
        this.stack.add(this.current);
        this.current = obj;
    }

    public final void up() {
        ArrayList arrayList = this.stack;
        if (!(!arrayList.isEmpty())) {
            throw new IllegalStateException("Check failed.".toString());
        }
        this.current = arrayList.remove(arrayList.size() - 1);
    }
}
