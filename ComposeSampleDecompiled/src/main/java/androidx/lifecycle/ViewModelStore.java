package androidx.lifecycle;

import androidx.compose.ui.input.pointer.InternalPointerEvent;
import androidx.compose.ui.input.pointer.PointerId;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputChangeEventProducer$PointerInputData;
import androidx.compose.ui.input.pointer.PointerInputEvent;
import androidx.compose.ui.input.pointer.PointerInputEventData;
import androidx.compose.ui.input.pointer.PositionCalculator;
import androidx.compose.ui.platform.AndroidComposeView;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ViewModelStore {
    public final /* synthetic */ int $r8$classId;
    public final HashMap map;

    public ViewModelStore(int i) {
        this.$r8$classId = i;
        if (i == 1) {
            this.map = new LinkedHashMap();
        } else if (i != 2) {
            this.map = new LinkedHashMap();
        } else {
            this.map = new HashMap();
        }
    }

    public final void clear() {
        switch (this.$r8$classId) {
            case 0:
                for (ViewModel viewModel : this.map.values()) {
                    viewModel.mCleared = true;
                    HashMap hashMap = viewModel.mBagOfTags;
                    if (hashMap != null) {
                        synchronized (hashMap) {
                            try {
                                Iterator it = viewModel.mBagOfTags.values().iterator();
                                while (it.hasNext()) {
                                    ViewModel.closeWithRuntimeException(it.next());
                                }
                            } finally {
                            }
                        }
                    }
                    LinkedHashSet linkedHashSet = viewModel.mCloseables;
                    if (linkedHashSet != null) {
                        synchronized (linkedHashSet) {
                            try {
                                Iterator it2 = viewModel.mCloseables.iterator();
                                while (it2.hasNext()) {
                                    ViewModel.closeWithRuntimeException((Closeable) it2.next());
                                }
                            } finally {
                            }
                        }
                        viewModel.mCloseables.clear();
                    }
                }
                this.map.clear();
                return;
            default:
                this.map.clear();
                return;
        }
    }

    public final InternalPointerEvent produce(PointerInputEvent pointerInputEvent, PositionCalculator positionCalculator) {
        boolean z;
        long j;
        long j2;
        int i;
        ResultKt.checkNotNullParameter(positionCalculator, "positionCalculator");
        List list = pointerInputEvent.pointers;
        LinkedHashMap linkedHashMap = new LinkedHashMap(list.size());
        int size = list.size();
        int i2 = 0;
        while (i2 < size) {
            PointerInputEventData pointerInputEventData = (PointerInputEventData) list.get(i2);
            HashMap hashMap = this.map;
            PointerInputChangeEventProducer$PointerInputData pointerInputChangeEventProducer$PointerInputData = (PointerInputChangeEventProducer$PointerInputData) hashMap.get(new PointerId(pointerInputEventData.id));
            if (pointerInputChangeEventProducer$PointerInputData == null) {
                j2 = pointerInputEventData.uptime;
                j = pointerInputEventData.position;
                z = false;
            } else {
                long m219screenToLocalMKHz9U = ((AndroidComposeView) positionCalculator).m219screenToLocalMKHz9U(pointerInputChangeEventProducer$PointerInputData.positionOnScreen);
                long j3 = pointerInputChangeEventProducer$PointerInputData.uptime;
                z = pointerInputChangeEventProducer$PointerInputData.down;
                j = m219screenToLocalMKHz9U;
                j2 = j3;
            }
            long j4 = pointerInputEventData.id;
            linkedHashMap.put(new PointerId(j4), new PointerInputChange(j4, pointerInputEventData.uptime, pointerInputEventData.position, pointerInputEventData.down, pointerInputEventData.pressure, j2, j, z, pointerInputEventData.type, pointerInputEventData.historical, pointerInputEventData.scrollDelta));
            long j5 = pointerInputEventData.id;
            boolean z2 = pointerInputEventData.down;
            if (z2) {
                i = i2;
                hashMap.put(new PointerId(j5), new PointerInputChangeEventProducer$PointerInputData(pointerInputEventData.uptime, pointerInputEventData.positionOnScreen, z2));
            } else {
                i = i2;
                hashMap.remove(new PointerId(j5));
            }
            i2 = i + 1;
        }
        return new InternalPointerEvent(linkedHashMap, pointerInputEvent);
    }
}
