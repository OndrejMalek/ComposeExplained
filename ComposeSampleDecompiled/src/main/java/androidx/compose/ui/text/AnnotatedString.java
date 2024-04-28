package androidx.compose.ui.text;

import androidx.compose.ui.node.DepthSortedSet$DepthComparator$1;
import java.util.Arrays;
import java.util.List;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt___CollectionsKt;

/* loaded from: classes.dex */
public final class AnnotatedString implements CharSequence {
    public final List annotations;
    public final List paragraphStylesOrNull;
    public final List spanStylesOrNull;
    public final String text;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AnnotatedString(String str) {
        this(str, null, null, null);
        ResultKt.checkNotNullParameter(str, "text");
    }

    @Override // java.lang.CharSequence
    public final char charAt(int i) {
        return this.text.charAt(i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnnotatedString)) {
            return false;
        }
        AnnotatedString annotatedString = (AnnotatedString) obj;
        return ResultKt.areEqual(this.text, annotatedString.text) && ResultKt.areEqual(this.spanStylesOrNull, annotatedString.spanStylesOrNull) && ResultKt.areEqual(this.paragraphStylesOrNull, annotatedString.paragraphStylesOrNull) && ResultKt.areEqual(this.annotations, annotatedString.annotations);
    }

    public final int hashCode() {
        int hashCode = this.text.hashCode() * 31;
        List list = this.spanStylesOrNull;
        int hashCode2 = (hashCode + (list != null ? list.hashCode() : 0)) * 31;
        List list2 = this.paragraphStylesOrNull;
        int hashCode3 = (hashCode2 + (list2 != null ? list2.hashCode() : 0)) * 31;
        List list3 = this.annotations;
        return hashCode3 + (list3 != null ? list3.hashCode() : 0);
    }

    @Override // java.lang.CharSequence
    public final int length() {
        return this.text.length();
    }

    @Override // java.lang.CharSequence
    public final String toString() {
        return this.text;
    }

    /* JADX DEBUG: Method merged with bridge method: subSequence(II)Ljava/lang/CharSequence; */
    @Override // java.lang.CharSequence
    public final AnnotatedString subSequence(int i, int i2) {
        if (i <= i2) {
            String str = this.text;
            if (i == 0 && i2 == str.length()) {
                return this;
            }
            String substring = str.substring(i, i2);
            ResultKt.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            return new AnnotatedString(substring, AnnotatedStringKt.access$filterRanges(this.spanStylesOrNull, i, i2), AnnotatedStringKt.access$filterRanges(this.paragraphStylesOrNull, i, i2), AnnotatedStringKt.access$filterRanges(this.annotations, i, i2));
        }
        throw new IllegalArgumentException(("start (" + i + ") should be less or equal to end (" + i2 + ')').toString());
    }

    /* loaded from: classes.dex */
    public final class Range {
        public final int end;
        public final Object item;
        public final int start;
        public final String tag;

        public Range(Object obj, int i, int i2, String str) {
            ResultKt.checkNotNullParameter(str, "tag");
            this.item = obj;
            this.start = i;
            this.end = i2;
            this.tag = str;
            if (i > i2) {
                throw new IllegalArgumentException("Reversed range is not supported".toString());
            }
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Range)) {
                return false;
            }
            Range range = (Range) obj;
            return ResultKt.areEqual(this.item, range.item) && this.start == range.start && this.end == range.end && ResultKt.areEqual(this.tag, range.tag);
        }

        public final int hashCode() {
            Object obj = this.item;
            return this.tag.hashCode() + ((Integer.hashCode(this.end) + ((Integer.hashCode(this.start) + ((obj == null ? 0 : obj.hashCode()) * 31)) * 31)) * 31);
        }

        public final String toString() {
            return "Range(item=" + this.item + ", start=" + this.start + ", end=" + this.end + ", tag=" + this.tag + ')';
        }

        public Range(int i, int i2, Object obj) {
            this(obj, i, i2, "");
        }
    }

    public AnnotatedString(String str, List list, List list2, List list3) {
        List asList;
        ResultKt.checkNotNullParameter(str, "text");
        this.text = str;
        this.spanStylesOrNull = list;
        this.paragraphStylesOrNull = list2;
        this.annotations = list3;
        if (list2 != null) {
            DepthSortedSet$DepthComparator$1 depthSortedSet$DepthComparator$1 = new DepthSortedSet$DepthComparator$1(2);
            int i = 0;
            if (list2.size() <= 1) {
                asList = CollectionsKt___CollectionsKt.toList(list2);
            } else {
                Object[] array = list2.toArray(new Object[0]);
                ResultKt.checkNotNullParameter(array, "<this>");
                if (array.length > 1) {
                    Arrays.sort(array, depthSortedSet$DepthComparator$1);
                }
                asList = Arrays.asList(array);
                ResultKt.checkNotNullExpressionValue(asList, "asList(this)");
            }
            int size = asList.size();
            int i2 = -1;
            while (i < size) {
                Range range = (Range) asList.get(i);
                if (range.start >= i2) {
                    int length = this.text.length();
                    int i3 = range.end;
                    if (i3 > length) {
                        throw new IllegalArgumentException(("ParagraphStyle range [" + range.start + ", " + i3 + ") is out of boundary").toString());
                    }
                    i++;
                    i2 = i3;
                } else {
                    throw new IllegalArgumentException("ParagraphStyle should not overlap".toString());
                }
            }
        }
    }
}
