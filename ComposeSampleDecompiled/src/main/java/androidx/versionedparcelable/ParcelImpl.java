package androidx.versionedparcelable;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.activity.result.ActivityResult;
import androidx.compose.runtime.ParcelableSnapshotMutableFloatState;
import androidx.compose.runtime.ParcelableSnapshotMutableIntState;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public class ParcelImpl implements Parcelable {
    public static final Parcelable.Creator<ParcelImpl> CREATOR = new AnonymousClass1(0);
    public final VersionedParcelable mParcel;

    /* renamed from: androidx.versionedparcelable.ParcelImpl$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements Parcelable.Creator {
        public final /* synthetic */ int $r8$classId;

        /* JADX DEBUG: Marked for inline */
        /* JADX DEBUG: Method not inlined, still used in: [androidx.activity.result.ActivityResult.<clinit>():void, androidx.compose.runtime.ParcelableSnapshotMutableIntState.<clinit>():void, androidx.versionedparcelable.ParcelImpl.<clinit>():void] */
        public /* synthetic */ AnonymousClass1(int i) {
            this.$r8$classId = i;
        }

        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel parcel) {
            switch (this.$r8$classId) {
                case 0:
                    return new ParcelImpl(parcel);
                case 1:
                    ResultKt.checkNotNullParameter(parcel, "parcel");
                    return new ActivityResult(parcel.readInt() == 0 ? null : (Intent) Intent.CREATOR.createFromParcel(parcel), parcel.readInt());
                case 2:
                    ResultKt.checkNotNullParameter(parcel, "parcel");
                    return new ParcelableSnapshotMutableFloatState(parcel.readFloat());
                default:
                    ResultKt.checkNotNullParameter(parcel, "parcel");
                    return new ParcelableSnapshotMutableIntState(parcel.readInt());
            }
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            switch (this.$r8$classId) {
                case 0:
                    return new ParcelImpl[i];
                case 1:
                    return new ActivityResult[i];
                case 2:
                    return new ParcelableSnapshotMutableFloatState[i];
                default:
                    return new ParcelableSnapshotMutableIntState[i];
            }
        }
    }

    public ParcelImpl(Parcel parcel) {
        this.mParcel = new VersionedParcelParcel(parcel).readVersionedParcelable$1();
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        new VersionedParcelParcel(parcel).writeVersionedParcelable(this.mParcel);
    }
}
