package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Offset;
import java.util.List;
import kotlin.ResultKt;
import kotlin.collections.EmptyList;

/* loaded from: classes.dex */
public final class PointerInputChange {
    public final List _historical;
    public ConsumedData consumed;
    public final long id;
    public final long position;
    public final boolean pressed;
    public final float pressure;
    public final long previousPosition;
    public final boolean previousPressed;
    public final long previousUptimeMillis;
    public final long scrollDelta;
    public final int type;
    public final long uptimeMillis;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PointerInputChange(long j, long j2, long j3, boolean z, float f, long j4, long j5, boolean z2, int i, List list, long j6) {
        this(j, j2, j3, z, f, j4, j5, z2, false, i, j6);
        ResultKt.checkNotNullParameter(list, "historical");
        this._historical = list;
    }

    public final boolean isConsumed() {
        ConsumedData consumedData = this.consumed;
        return consumedData.downChange || consumedData.positionChange;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PointerInputChange(id=");
        sb.append((Object) PointerId.m157toStringimpl(this.id));
        sb.append(", uptimeMillis=");
        sb.append(this.uptimeMillis);
        sb.append(", position=");
        sb.append((Object) Offset.m80toStringimpl(this.position));
        sb.append(", pressed=");
        sb.append(this.pressed);
        sb.append(", pressure=");
        sb.append(this.pressure);
        sb.append(", previousUptimeMillis=");
        sb.append(this.previousUptimeMillis);
        sb.append(", previousPosition=");
        sb.append((Object) Offset.m80toStringimpl(this.previousPosition));
        sb.append(", previousPressed=");
        sb.append(this.previousPressed);
        sb.append(", isConsumed=");
        sb.append(isConsumed());
        sb.append(", type=");
        int i = this.type;
        sb.append((Object) (i != 1 ? i != 2 ? i != 3 ? i != 4 ? "Unknown" : "Eraser" : "Stylus" : "Mouse" : "Touch"));
        sb.append(", historical=");
        Object obj = this._historical;
        if (obj == null) {
            obj = EmptyList.INSTANCE;
        }
        sb.append(obj);
        sb.append(",scrollDelta=");
        sb.append((Object) Offset.m80toStringimpl(this.scrollDelta));
        sb.append(')');
        return sb.toString();
    }

    /* JADX WARN: Type inference failed for: r2v10, types: [java.lang.Object, androidx.compose.ui.input.pointer.ConsumedData] */
    public PointerInputChange(long j, long j2, long j3, boolean z, float f, long j4, long j5, boolean z2, boolean z3, int i, long j6) {
        this.id = j;
        this.uptimeMillis = j2;
        this.position = j3;
        this.pressed = z;
        this.pressure = f;
        this.previousUptimeMillis = j4;
        this.previousPosition = j5;
        this.previousPressed = z2;
        this.type = i;
        this.scrollDelta = j6;
        ?? obj = new Object();
        obj.positionChange = z3;
        obj.downChange = z3;
        this.consumed = obj;
    }
}
