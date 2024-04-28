package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.ResultKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;

/* loaded from: classes.dex */
public final class DelimitedRangesSequence$iterator$1 implements Iterator, KMappedMarker {
    public int counter;
    public int currentStartIndex;
    public IntRange nextItem;
    public int nextSearchIndex;
    public int nextState = -1;
    public final /* synthetic */ DelimitedRangesSequence this$0;

    public DelimitedRangesSequence$iterator$1(DelimitedRangesSequence delimitedRangesSequence) {
        this.this$0 = delimitedRangesSequence;
        int coerceIn = ResultKt.coerceIn(delimitedRangesSequence.startIndex, 0, delimitedRangesSequence.input.length());
        this.currentStartIndex = coerceIn;
        this.nextSearchIndex = coerceIn;
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0018, code lost:
    
        if (r6 < r3) goto L9;
     */
    /* JADX WARN: Type inference failed for: r0v7, types: [kotlin.ranges.IntProgression, kotlin.ranges.IntRange] */
    /* JADX WARN: Type inference failed for: r0v8, types: [kotlin.ranges.IntProgression, kotlin.ranges.IntRange] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void calcNext$2() {
        /*
            r7 = this;
            int r0 = r7.nextSearchIndex
            r1 = 0
            if (r0 >= 0) goto Lb
            r7.nextState = r1
            r0 = 0
            r7.nextItem = r0
            goto L7b
        Lb:
            kotlin.text.DelimitedRangesSequence r2 = r7.this$0
            int r3 = r2.limit
            r4 = -1
            r5 = 1
            if (r3 <= 0) goto L1a
            int r6 = r7.counter
            int r6 = r6 + r5
            r7.counter = r6
            if (r6 >= r3) goto L22
        L1a:
            java.lang.CharSequence r3 = r2.input
            int r3 = r3.length()
            if (r0 <= r3) goto L34
        L22:
            kotlin.ranges.IntRange r0 = new kotlin.ranges.IntRange
            int r1 = r7.currentStartIndex
            java.lang.CharSequence r2 = r2.input
            int r2 = kotlin.text.StringsKt__StringsKt.getLastIndex(r2)
            r0.<init>(r1, r2, r5)
            r7.nextItem = r0
            r7.nextSearchIndex = r4
            goto L79
        L34:
            kotlin.jvm.functions.Function2 r0 = r2.getNextMatch
            java.lang.CharSequence r3 = r2.input
            int r6 = r7.nextSearchIndex
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r0 = r0.invoke(r3, r6)
            kotlin.Pair r0 = (kotlin.Pair) r0
            if (r0 != 0) goto L58
            kotlin.ranges.IntRange r0 = new kotlin.ranges.IntRange
            int r1 = r7.currentStartIndex
            java.lang.CharSequence r2 = r2.input
            int r2 = kotlin.text.StringsKt__StringsKt.getLastIndex(r2)
            r0.<init>(r1, r2, r5)
            r7.nextItem = r0
            r7.nextSearchIndex = r4
            goto L79
        L58:
            java.lang.Object r2 = r0.first
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            java.lang.Object r0 = r0.second
            java.lang.Number r0 = (java.lang.Number) r0
            int r0 = r0.intValue()
            int r3 = r7.currentStartIndex
            kotlin.ranges.IntRange r3 = kotlin.ResultKt.until(r3, r2)
            r7.nextItem = r3
            int r2 = r2 + r0
            r7.currentStartIndex = r2
            if (r0 != 0) goto L76
            r1 = r5
        L76:
            int r2 = r2 + r1
            r7.nextSearchIndex = r2
        L79:
            r7.nextState = r5
        L7b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.DelimitedRangesSequence$iterator$1.calcNext$2():void");
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.nextState == -1) {
            calcNext$2();
        }
        return this.nextState == 1;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.nextState == -1) {
            calcNext$2();
        }
        if (this.nextState == 0) {
            throw new NoSuchElementException();
        }
        IntRange intRange = this.nextItem;
        ResultKt.checkNotNull(intRange, "null cannot be cast to non-null type kotlin.ranges.IntRange");
        this.nextItem = null;
        this.nextState = -1;
        return intRange;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
