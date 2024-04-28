package kotlin.text;

import java.util.Iterator;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;

/* loaded from: classes.dex */
public final class DelimitedRangesSequence implements Sequence {
    public final Function2 getNextMatch;
    public final CharSequence input;
    public final int limit;
    public final int startIndex;

    public DelimitedRangesSequence(String str, int i, int i2, StringsKt__StringsKt$rangesDelimitedBy$2 stringsKt__StringsKt$rangesDelimitedBy$2) {
        this.input = str;
        this.startIndex = i;
        this.limit = i2;
        this.getNextMatch = stringsKt__StringsKt$rangesDelimitedBy$2;
    }

    @Override // kotlin.sequences.Sequence
    public final Iterator iterator() {
        return new DelimitedRangesSequence$iterator$1(this);
    }
}
