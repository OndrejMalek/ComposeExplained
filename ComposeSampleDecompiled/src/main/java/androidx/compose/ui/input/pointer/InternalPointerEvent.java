package androidx.compose.ui.input.pointer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public final class InternalPointerEvent {
    public final Map changes;
    public final PointerInputEvent pointerInputEvent;

    public InternalPointerEvent(LinkedHashMap linkedHashMap, PointerInputEvent pointerInputEvent) {
        this.changes = linkedHashMap;
        this.pointerInputEvent = pointerInputEvent;
    }

    /* renamed from: issuesEnterExitEvent-0FcD4WY, reason: not valid java name */
    public final boolean m153issuesEnterExitEvent0FcD4WY(long j) {
        Object obj;
        List list = this.pointerInputEvent.pointers;
        int size = list.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                obj = null;
                break;
            }
            obj = list.get(i);
            if (PointerId.m156equalsimpl0(((PointerInputEventData) obj).id, j)) {
                break;
            }
            i++;
        }
        PointerInputEventData pointerInputEventData = (PointerInputEventData) obj;
        if (pointerInputEventData != null) {
            return pointerInputEventData.issuesEnterExit;
        }
        return false;
    }
}
