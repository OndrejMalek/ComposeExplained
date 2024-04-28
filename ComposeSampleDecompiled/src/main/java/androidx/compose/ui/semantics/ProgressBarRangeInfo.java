package androidx.compose.ui.semantics;

import kotlin.ResultKt;
import kotlin.ranges.ClosedFloatRange;

/* loaded from: classes.dex */
public final class ProgressBarRangeInfo {
    public static final ProgressBarRangeInfo Indeterminate = new ProgressBarRangeInfo(new ClosedFloatRange());
    public final ClosedFloatRange range;
    public final float current = 0.0f;
    public final int steps = 0;

    public ProgressBarRangeInfo(ClosedFloatRange closedFloatRange) {
        this.range = closedFloatRange;
        if (!(!Float.isNaN(0.0f))) {
            throw new IllegalArgumentException("current must not be NaN".toString());
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ProgressBarRangeInfo)) {
            return false;
        }
        ProgressBarRangeInfo progressBarRangeInfo = (ProgressBarRangeInfo) obj;
        return this.current == progressBarRangeInfo.current && ResultKt.areEqual(this.range, progressBarRangeInfo.range) && this.steps == progressBarRangeInfo.steps;
    }

    public final int hashCode() {
        return ((this.range.hashCode() + (Float.hashCode(this.current) * 31)) * 31) + this.steps;
    }

    public final String toString() {
        return "ProgressBarRangeInfo(current=" + this.current + ", range=" + this.range + ", steps=" + this.steps + ')';
    }
}
