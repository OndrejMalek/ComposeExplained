package kotlin.ranges;

/* loaded from: classes.dex */
public final class ClosedFloatRange {
    public final float _start = 0.0f;
    public final float _endInclusive = 0.0f;

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0015, code lost:
    
        if (r2._start > r2._endInclusive) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean equals(java.lang.Object r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof kotlin.ranges.ClosedFloatRange
            if (r0 == 0) goto L28
            float r0 = r4._start
            float r1 = r4._endInclusive
            int r2 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r2 <= 0) goto L18
            r2 = r5
            kotlin.ranges.ClosedFloatRange r2 = (kotlin.ranges.ClosedFloatRange) r2
            float r3 = r2._start
            float r2 = r2._endInclusive
            int r2 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r2 <= 0) goto L18
            goto L26
        L18:
            kotlin.ranges.ClosedFloatRange r5 = (kotlin.ranges.ClosedFloatRange) r5
            float r2 = r5._start
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 != 0) goto L28
            float r5 = r5._endInclusive
            int r5 = (r1 > r5 ? 1 : (r1 == r5 ? 0 : -1))
            if (r5 != 0) goto L28
        L26:
            r5 = 1
            goto L29
        L28:
            r5 = 0
        L29:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ranges.ClosedFloatRange.equals(java.lang.Object):boolean");
    }

    public final int hashCode() {
        float f = this._start;
        float f2 = this._endInclusive;
        if (f > f2) {
            return -1;
        }
        return (Float.hashCode(f) * 31) + Float.hashCode(f2);
    }

    public final String toString() {
        return this._start + ".." + this._endInclusive;
    }
}
