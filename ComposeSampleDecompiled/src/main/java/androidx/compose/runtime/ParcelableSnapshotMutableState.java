package androidx.compose.runtime;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ParcelableSnapshotMutableState extends SnapshotMutableStateImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelableSnapshotMutableState> CREATOR = new Object();

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int i2;
        ResultKt.checkNotNullParameter(parcel, "parcel");
        parcel.writeValue(getValue());
        NeverEqualPolicy neverEqualPolicy = NeverEqualPolicy.INSTANCE;
        SnapshotMutationPolicy snapshotMutationPolicy = this.policy;
        if (ResultKt.areEqual(snapshotMutationPolicy, neverEqualPolicy)) {
            i2 = 0;
        } else if (ResultKt.areEqual(snapshotMutationPolicy, StructuralEqualityPolicy.INSTANCE)) {
            i2 = 1;
        } else {
            if (!ResultKt.areEqual(snapshotMutationPolicy, ReferentialEqualityPolicy.INSTANCE)) {
                throw new IllegalStateException("Only known types of MutableState's SnapshotMutationPolicy are supported");
            }
            i2 = 2;
        }
        parcel.writeInt(i2);
    }
}
