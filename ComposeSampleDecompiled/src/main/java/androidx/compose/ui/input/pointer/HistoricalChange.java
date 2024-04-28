package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Offset;

/* loaded from: classes.dex */
public final class HistoricalChange {
    public final long position;
    public final long uptimeMillis;

    public HistoricalChange(long j, long j2) {
        this.uptimeMillis = j;
        this.position = j2;
    }

    public final String toString() {
        return "HistoricalChange(uptimeMillis=" + this.uptimeMillis + ", position=" + ((Object) Offset.m80toStringimpl(this.position)) + ')';
    }
}
