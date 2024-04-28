package androidx.compose.material3;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.DpSize;

/* loaded from: classes.dex */
public final class MinimumInteractiveComponentSizeModifier implements Modifier.Element {
    public final long size;

    public MinimumInteractiveComponentSizeModifier(long j) {
        this.size = j;
    }

    public final boolean equals(Object obj) {
        MinimumInteractiveComponentSizeModifier minimumInteractiveComponentSizeModifier = obj instanceof MinimumInteractiveComponentSizeModifier ? (MinimumInteractiveComponentSizeModifier) obj : null;
        if (minimumInteractiveComponentSizeModifier == null) {
            return false;
        }
        int i = DpSize.$r8$clinit;
        return this.size == minimumInteractiveComponentSizeModifier.size;
    }

    public final int hashCode() {
        int i = DpSize.$r8$clinit;
        return Long.hashCode(this.size);
    }
}
