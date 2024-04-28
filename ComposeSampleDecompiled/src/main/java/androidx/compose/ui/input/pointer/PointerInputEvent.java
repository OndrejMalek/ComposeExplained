package androidx.compose.ui.input.pointer;

import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class PointerInputEvent {
    public final MotionEvent motionEvent;
    public final List pointers;

    public PointerInputEvent(ArrayList arrayList, MotionEvent motionEvent) {
        ResultKt.checkNotNullParameter(arrayList, "pointers");
        ResultKt.checkNotNullParameter(motionEvent, "motionEvent");
        this.pointers = arrayList;
        this.motionEvent = motionEvent;
    }
}
