package androidx.compose.runtime.snapshots;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.ResultKt;
import kotlin.collections.MapsKt___MapsJvmKt;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public final class SnapshotIdSet implements Iterable, KMappedMarker {
    public static final SnapshotIdSet EMPTY = new SnapshotIdSet(0, 0, 0, null);
    public final int[] belowBound;
    public final int lowerBound;
    public final long lowerSet;
    public final long upperSet;

    public SnapshotIdSet(long j, long j2, int i, int[] iArr) {
        this.upperSet = j;
        this.lowerSet = j2;
        this.lowerBound = i;
        this.belowBound = iArr;
    }

    public final SnapshotIdSet andNot(SnapshotIdSet snapshotIdSet) {
        ResultKt.checkNotNullParameter(snapshotIdSet, "bits");
        SnapshotIdSet snapshotIdSet2 = EMPTY;
        if (snapshotIdSet == snapshotIdSet2) {
            return this;
        }
        if (this == snapshotIdSet2) {
            return snapshotIdSet2;
        }
        int i = snapshotIdSet.lowerBound;
        int i2 = this.lowerBound;
        if (i == i2) {
            int[] iArr = snapshotIdSet.belowBound;
            int[] iArr2 = this.belowBound;
            if (iArr == iArr2) {
                return new SnapshotIdSet(this.upperSet & (~snapshotIdSet.upperSet), (~snapshotIdSet.lowerSet) & this.lowerSet, i2, iArr2);
            }
        }
        Iterator it = snapshotIdSet.iterator();
        SnapshotIdSet snapshotIdSet3 = this;
        while (it.hasNext()) {
            snapshotIdSet3 = snapshotIdSet3.clear(((Number) it.next()).intValue());
        }
        return snapshotIdSet3;
    }

    public final SnapshotIdSet clear(int i) {
        int[] iArr;
        int binarySearch;
        int i2 = this.lowerBound;
        int i3 = i - i2;
        if (i3 >= 0 && i3 < 64) {
            long j = 1 << i3;
            long j2 = this.lowerSet;
            if ((j2 & j) != 0) {
                return new SnapshotIdSet(this.upperSet, j2 & (~j), i2, this.belowBound);
            }
        } else if (i3 >= 64 && i3 < 128) {
            long j3 = 1 << (i3 - 64);
            long j4 = this.upperSet;
            if ((j4 & j3) != 0) {
                return new SnapshotIdSet(j4 & (~j3), this.lowerSet, i2, this.belowBound);
            }
        } else if (i3 < 0 && (iArr = this.belowBound) != null && (binarySearch = ResultKt.binarySearch(iArr, i)) >= 0) {
            int length = iArr.length;
            int i4 = length - 1;
            if (i4 == 0) {
                return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, null);
            }
            int[] iArr2 = new int[i4];
            if (binarySearch > 0) {
                MapsKt___MapsJvmKt.copyInto(iArr, iArr2, 0, 0, binarySearch);
            }
            if (binarySearch < i4) {
                MapsKt___MapsJvmKt.copyInto(iArr, iArr2, binarySearch, binarySearch + 1, length);
            }
            return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, iArr2);
        }
        return this;
    }

    public final boolean get(int i) {
        int[] iArr;
        int i2 = i - this.lowerBound;
        if (i2 >= 0 && i2 < 64) {
            return ((1 << i2) & this.lowerSet) != 0;
        }
        if (i2 >= 64 && i2 < 128) {
            return ((1 << (i2 - 64)) & this.upperSet) != 0;
        }
        if (i2 <= 0 && (iArr = this.belowBound) != null) {
            return ResultKt.binarySearch(iArr, i) >= 0;
        }
        return false;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [kotlin.coroutines.Continuation, kotlin.sequences.SequenceBuilderIterator, java.util.Iterator, java.lang.Object] */
    @Override // java.lang.Iterable
    public final Iterator iterator() {
        SnapshotIdSet$iterator$1 snapshotIdSet$iterator$1 = new SnapshotIdSet$iterator$1(this, null);
        ?? obj = new Object();
        obj.nextStep = ResultKt.createCoroutineUnintercepted(obj, obj, snapshotIdSet$iterator$1);
        return obj;
    }

    public final SnapshotIdSet or(SnapshotIdSet snapshotIdSet) {
        ResultKt.checkNotNullParameter(snapshotIdSet, "bits");
        SnapshotIdSet snapshotIdSet2 = EMPTY;
        if (snapshotIdSet == snapshotIdSet2) {
            return this;
        }
        if (this == snapshotIdSet2) {
            return snapshotIdSet;
        }
        int i = snapshotIdSet.lowerBound;
        int i2 = this.lowerBound;
        if (i == i2) {
            int[] iArr = snapshotIdSet.belowBound;
            int[] iArr2 = this.belowBound;
            if (iArr == iArr2) {
                return new SnapshotIdSet(this.upperSet | snapshotIdSet.upperSet, this.lowerSet | snapshotIdSet.lowerSet, i2, iArr2);
            }
        }
        if (this.belowBound == null) {
            Iterator it = iterator();
            while (it.hasNext()) {
                snapshotIdSet = snapshotIdSet.set(((Number) it.next()).intValue());
            }
            return snapshotIdSet;
        }
        Iterator it2 = snapshotIdSet.iterator();
        SnapshotIdSet snapshotIdSet3 = this;
        while (it2.hasNext()) {
            snapshotIdSet3 = snapshotIdSet3.set(((Number) it2.next()).intValue());
        }
        return snapshotIdSet3;
    }

    public final SnapshotIdSet set(int i) {
        int i2;
        int i3 = this.lowerBound;
        int i4 = i - i3;
        long j = this.lowerSet;
        if (i4 < 0 || i4 >= 64) {
            long j2 = this.upperSet;
            if (i4 < 64 || i4 >= 128) {
                int[] iArr = this.belowBound;
                if (i4 < 128) {
                    if (iArr == null) {
                        return new SnapshotIdSet(j2, j, i3, new int[]{i});
                    }
                    int binarySearch = ResultKt.binarySearch(iArr, i);
                    if (binarySearch < 0) {
                        int i5 = -(binarySearch + 1);
                        int length = iArr.length;
                        int[] iArr2 = new int[length + 1];
                        MapsKt___MapsJvmKt.copyInto(iArr, iArr2, 0, 0, i5);
                        MapsKt___MapsJvmKt.copyInto(iArr, iArr2, i5 + 1, i5, length);
                        iArr2[i5] = i;
                        return new SnapshotIdSet(this.upperSet, this.lowerSet, this.lowerBound, iArr2);
                    }
                } else if (!get(i)) {
                    int i6 = ((i + 1) / 64) * 64;
                    int i7 = this.lowerBound;
                    ArrayList arrayList = null;
                    long j3 = j2;
                    while (true) {
                        if (i7 >= i6) {
                            i2 = i7;
                            break;
                        }
                        if (j != 0) {
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                                if (iArr != null) {
                                    for (int i8 : iArr) {
                                        arrayList.add(Integer.valueOf(i8));
                                    }
                                }
                            }
                            for (int i9 = 0; i9 < 64; i9++) {
                                if (((1 << i9) & j) != 0) {
                                    arrayList.add(Integer.valueOf(i9 + i7));
                                }
                            }
                        }
                        if (j3 == 0) {
                            i2 = i6;
                            j = 0;
                            break;
                        }
                        i7 += 64;
                        j = j3;
                        j3 = 0;
                    }
                    if (arrayList != null) {
                        iArr = new int[arrayList.size()];
                        Iterator it = arrayList.iterator();
                        int i10 = 0;
                        while (it.hasNext()) {
                            iArr[i10] = ((Number) it.next()).intValue();
                            i10++;
                        }
                    }
                    return new SnapshotIdSet(j3, j, i2, iArr).set(i);
                }
            } else {
                long j4 = 1 << (i4 - 64);
                if ((j2 & j4) == 0) {
                    return new SnapshotIdSet(j2 | j4, j, i3, this.belowBound);
                }
            }
        } else {
            long j5 = 1 << i4;
            if ((j & j5) == 0) {
                return new SnapshotIdSet(this.upperSet, j | j5, i3, this.belowBound);
            }
        }
        return this;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [");
        ArrayList arrayList = new ArrayList(MapsKt___MapsJvmKt.collectionSizeOrDefault(this));
        Iterator it = iterator();
        while (it.hasNext()) {
            arrayList.add(String.valueOf(((Number) it.next()).intValue()));
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append((CharSequence) "");
        int size = arrayList.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Object obj = arrayList.get(i2);
            i++;
            if (i > 1) {
                sb2.append((CharSequence) ", ");
            }
            if (obj == null || (obj instanceof CharSequence)) {
                sb2.append((CharSequence) obj);
            } else if (obj instanceof Character) {
                sb2.append(((Character) obj).charValue());
            } else {
                sb2.append((CharSequence) String.valueOf(obj));
            }
        }
        sb2.append((CharSequence) "");
        String sb3 = sb2.toString();
        ResultKt.checkNotNullExpressionValue(sb3, "fastJoinTo(StringBuilder…form)\n        .toString()");
        sb.append(sb3);
        sb.append(']');
        return sb.toString();
    }
}
