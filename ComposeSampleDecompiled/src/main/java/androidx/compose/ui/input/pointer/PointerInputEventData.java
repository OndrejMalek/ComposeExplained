package androidx.compose.ui.input.pointer;

import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;
import androidx.compose.ui.geometry.Offset;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class PointerInputEventData {
    public final boolean down;
    public final List historical;
    public final long id;
    public final boolean issuesEnterExit;
    public final long position;
    public final long positionOnScreen;
    public final float pressure;
    public final long scrollDelta;
    public final int type;
    public final long uptime;

    public PointerInputEventData(long j, long j2, long j3, long j4, boolean z, float f, int i, boolean z2, ArrayList arrayList, long j5) {
        this.id = j;
        this.uptime = j2;
        this.positionOnScreen = j3;
        this.position = j4;
        this.down = z;
        this.pressure = f;
        this.type = i;
        this.issuesEnterExit = z2;
        this.historical = arrayList;
        this.scrollDelta = j5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PointerInputEventData)) {
            return false;
        }
        PointerInputEventData pointerInputEventData = (PointerInputEventData) obj;
        return PointerId.m156equalsimpl0(this.id, pointerInputEventData.id) && this.uptime == pointerInputEventData.uptime && Offset.m75equalsimpl0(this.positionOnScreen, pointerInputEventData.positionOnScreen) && Offset.m75equalsimpl0(this.position, pointerInputEventData.position) && this.down == pointerInputEventData.down && Float.compare(this.pressure, pointerInputEventData.pressure) == 0 && this.type == pointerInputEventData.type && this.issuesEnterExit == pointerInputEventData.issuesEnterExit && ResultKt.areEqual(this.historical, pointerInputEventData.historical) && Offset.m75equalsimpl0(this.scrollDelta, pointerInputEventData.scrollDelta);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    public final int hashCode() {
        int hashCode = (Long.hashCode(this.uptime) + (Long.hashCode(this.id) * 31)) * 31;
        int i = Offset.$r8$clinit;
        int hashCode2 = (Long.hashCode(this.position) + ((Long.hashCode(this.positionOnScreen) + hashCode) * 31)) * 31;
        boolean z = this.down;
        int i2 = z;
        if (z != 0) {
            i2 = 1;
        }
        int hashCode3 = (Integer.hashCode(this.type) + AnimationEndReason$EnumUnboxingLocalUtility.m(this.pressure, (hashCode2 + i2) * 31, 31)) * 31;
        boolean z2 = this.issuesEnterExit;
        return Long.hashCode(this.scrollDelta) + ((this.historical.hashCode() + ((hashCode3 + (z2 ? 1 : z2 ? 1 : 0)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("PointerInputEventData(id=");
        sb.append((Object) PointerId.m157toStringimpl(this.id));
        sb.append(", uptime=");
        sb.append(this.uptime);
        sb.append(", positionOnScreen=");
        sb.append((Object) Offset.m80toStringimpl(this.positionOnScreen));
        sb.append(", position=");
        sb.append((Object) Offset.m80toStringimpl(this.position));
        sb.append(", down=");
        sb.append(this.down);
        sb.append(", pressure=");
        sb.append(this.pressure);
        sb.append(", type=");
        int i = this.type;
        sb.append((Object) (i != 1 ? i != 2 ? i != 3 ? i != 4 ? "Unknown" : "Eraser" : "Stylus" : "Mouse" : "Touch"));
        sb.append(", issuesEnterExit=");
        sb.append(this.issuesEnterExit);
        sb.append(", historical=");
        sb.append(this.historical);
        sb.append(", scrollDelta=");
        sb.append((Object) Offset.m80toStringimpl(this.scrollDelta));
        sb.append(')');
        return sb.toString();
    }
}
