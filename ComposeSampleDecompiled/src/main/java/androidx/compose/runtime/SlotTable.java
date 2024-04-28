package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public final class SlotTable implements Iterable, KMappedMarker {
    public int groupsSize;
    public int readers;
    public int slotsSize;
    public int version;
    public boolean writer;
    public int[] groups = new int[0];
    public Object[] slots = new Object[0];
    public ArrayList anchors = new ArrayList();

    public final int anchorIndex(Anchor anchor) {
        ResultKt.checkNotNullParameter(anchor, "anchor");
        if (!(!this.writer)) {
            EffectsKt.composeRuntimeError("Use active SlotWriter to determine anchor location instead".toString());
            throw null;
        }
        if (anchor.getValid()) {
            return anchor.location;
        }
        throw new IllegalArgumentException("Anchor refers to a group that was removed".toString());
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new GroupIterator(0, this.groupsSize, this);
    }

    public final SlotReader openReader() {
        if (this.writer) {
            throw new IllegalStateException("Cannot read while a writer is pending".toString());
        }
        this.readers++;
        return new SlotReader(this);
    }

    public final SlotWriter openWriter() {
        if (!(!this.writer)) {
            EffectsKt.composeRuntimeError("Cannot start a writer when another writer is pending".toString());
            throw null;
        }
        if (this.readers > 0) {
            EffectsKt.composeRuntimeError("Cannot start a writer when a reader is pending".toString());
            throw null;
        }
        this.writer = true;
        this.version++;
        return new SlotWriter(this);
    }

    public final boolean ownsAnchor(Anchor anchor) {
        int search;
        ResultKt.checkNotNullParameter(anchor, "anchor");
        return anchor.getValid() && (search = ResultKt.search(this.anchors, anchor.location, this.groupsSize)) >= 0 && ResultKt.areEqual(this.anchors.get(search), anchor);
    }
}
