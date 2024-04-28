package androidx.compose.runtime;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.compose.runtime.SnapshotMutableFloatStateImpl;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.versionedparcelable.ParcelImpl;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ParcelableSnapshotMutableFloatState extends SnapshotMutableFloatStateImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelableSnapshotMutableFloatState> CREATOR = new ParcelImpl.AnonymousClass1(2);

    public ParcelableSnapshotMutableFloatState(float f) {
        this.next = new SnapshotMutableFloatStateImpl.FloatStateStateRecord(f);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        ResultKt.checkNotNullParameter(parcel, "parcel");
        parcel.writeFloat(((SnapshotMutableFloatStateImpl.FloatStateStateRecord) SnapshotKt.readable(this.next, this)).value);
    }
}
