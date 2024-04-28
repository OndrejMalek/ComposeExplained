package androidx.compose.ui.text;

import androidx.compose.ui.text.AnnotatedString;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.collections.EmptyList;

/* loaded from: classes.dex */
public abstract class AnnotatedStringKt {
    public static final /* synthetic */ int $r8$clinit = 0;

    static {
        int i = 6 & 2;
        EmptyList emptyList = EmptyList.INSTANCE;
        EmptyList emptyList2 = i != 0 ? emptyList : null;
        if ((6 & 4) == 0) {
            emptyList = null;
        }
        ResultKt.checkNotNullParameter(emptyList2, "spanStyles");
        ResultKt.checkNotNullParameter(emptyList, "paragraphStyles");
    }

    public static final ArrayList access$filterRanges(List list, int i, int i2) {
        if (i > i2) {
            throw new IllegalArgumentException(("start (" + i + ") should be less than or equal to end (" + i2 + ')').toString());
        }
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i3 = 0; i3 < size; i3++) {
            Object obj = list.get(i3);
            AnnotatedString.Range range = (AnnotatedString.Range) obj;
            if (intersect(i, i2, range.start, range.end)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        for (int i4 = 0; i4 < size2; i4++) {
            AnnotatedString.Range range2 = (AnnotatedString.Range) arrayList.get(i4);
            arrayList2.add(new AnnotatedString.Range(range2.item, Math.max(i, range2.start) - i, Math.min(i2, range2.end) - i, range2.tag));
        }
        if (arrayList2.isEmpty()) {
            return null;
        }
        return arrayList2;
    }

    public static final boolean intersect(int i, int i2, int i3, int i4) {
        if (Math.max(i, i3) < Math.min(i2, i4)) {
            return true;
        }
        if (i <= i3 && i4 <= i2) {
            if (i2 != i4) {
                return true;
            }
            if ((i3 == i4) == (i == i2)) {
                return true;
            }
        }
        if (i3 <= i && i2 <= i4) {
            if (i4 != i2) {
                return true;
            }
            if ((i == i2) == (i3 == i4)) {
                return true;
            }
        }
        return false;
    }
}
