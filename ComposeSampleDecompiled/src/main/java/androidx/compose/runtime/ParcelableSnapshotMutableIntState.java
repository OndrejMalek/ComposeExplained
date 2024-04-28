package androidx.compose.runtime;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.versionedparcelable.ParcelImpl;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ParcelableSnapshotMutableIntState extends SnapshotMutableIntStateImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelableSnapshotMutableIntState> CREATOR = new ParcelImpl.AnonymousClass1(3);

    public ParcelableSnapshotMutableIntState(int i) {
        this.next = new SnapshotMutableIntStateImpl.IntStateStateRecord(i);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        ResultKt.checkNotNullParameter(parcel, "parcel");
        parcel.writeInt(((SnapshotMutableIntStateImpl.IntStateStateRecord) SnapshotKt.readable(this.next, this)).value);
    }
}
