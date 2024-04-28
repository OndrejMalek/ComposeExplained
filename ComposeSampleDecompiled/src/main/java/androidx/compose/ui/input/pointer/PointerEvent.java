package androidx.compose.ui.input.pointer;

import _COROUTINE._BOUNDARY;
import android.view.MotionEvent;
import java.util.List;

/* loaded from: classes.dex */
public final class PointerEvent {
    public final List changes;
    public int type;

    public PointerEvent(List list, InternalPointerEvent internalPointerEvent) {
        this.changes = list;
        MotionEvent motionEvent = internalPointerEvent != null ? internalPointerEvent.pointerInputEvent.motionEvent : null;
        int i = 0;
        if (motionEvent != null) {
            motionEvent.getButtonState();
        }
        MotionEvent motionEvent2 = internalPointerEvent != null ? internalPointerEvent.pointerInputEvent.motionEvent : null;
        if (motionEvent2 != null) {
            motionEvent2.getMetaState();
        }
        MotionEvent motionEvent3 = internalPointerEvent != null ? internalPointerEvent.pointerInputEvent.motionEvent : null;
        int i2 = 1;
        if (motionEvent3 != null) {
            int actionMasked = motionEvent3.getActionMasked();
            if (actionMasked != 0) {
                if (actionMasked != 1) {
                    if (actionMasked != 2) {
                        switch (actionMasked) {
                            case 8:
                                i = 6;
                                break;
                            case 9:
                                i = 4;
                                break;
                            case 10:
                                i = 5;
                                break;
                        }
                        i2 = i;
                    }
                    i = 3;
                    i2 = i;
                }
                i = 2;
                i2 = i;
            }
            i = 1;
            i2 = i;
        } else {
            int size = list.size();
            while (true) {
                if (i >= size) {
                    i2 = 3;
                    break;
                }
                PointerInputChange pointerInputChange = (PointerInputChange) list.get(i);
                if (_BOUNDARY.changedToUpIgnoreConsumed(pointerInputChange)) {
                    i2 = 2;
                    break;
                } else if (_BOUNDARY.changedToDownIgnoreConsumed(pointerInputChange)) {
                    break;
                } else {
                    i++;
                }
            }
        }
        this.type = i2;
    }

    public PointerEvent(List list) {
        this(list, null);
    }
}
