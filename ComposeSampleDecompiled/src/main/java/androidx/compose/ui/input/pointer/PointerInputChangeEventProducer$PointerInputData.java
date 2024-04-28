package androidx.compose.ui.input.pointer;

/* loaded from: classes.dex */
public final class PointerInputChangeEventProducer$PointerInputData {
    public final boolean down;
    public final long positionOnScreen;
    public final long uptime;

    public PointerInputChangeEventProducer$PointerInputData(long j, long j2, boolean z) {
        this.uptime = j;
        this.positionOnScreen = j2;
        this.down = z;
    }
}
