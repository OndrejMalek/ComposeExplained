package androidx.core.app;

import android.app.PendingIntent;
import android.os.Parcel;
import android.text.TextUtils;
import androidx.core.graphics.drawable.IconCompat;
import androidx.versionedparcelable.VersionedParcel;
import androidx.versionedparcelable.VersionedParcelParcel;
import androidx.versionedparcelable.VersionedParcelable;

/* loaded from: classes.dex */
public class RemoteActionCompatParcelizer {
    public static RemoteActionCompat read(VersionedParcel versionedParcel) {
        RemoteActionCompat remoteActionCompat = new RemoteActionCompat();
        VersionedParcelable versionedParcelable = remoteActionCompat.mIcon;
        if (versionedParcel.readField(1)) {
            versionedParcelable = versionedParcel.readVersionedParcelable$1();
        }
        remoteActionCompat.mIcon = (IconCompat) versionedParcelable;
        CharSequence charSequence = remoteActionCompat.mTitle;
        if (versionedParcel.readField(2)) {
            charSequence = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((VersionedParcelParcel) versionedParcel).mParcel);
        }
        remoteActionCompat.mTitle = charSequence;
        CharSequence charSequence2 = remoteActionCompat.mContentDescription;
        if (versionedParcel.readField(3)) {
            charSequence2 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(((VersionedParcelParcel) versionedParcel).mParcel);
        }
        remoteActionCompat.mContentDescription = charSequence2;
        remoteActionCompat.mActionIntent = (PendingIntent) versionedParcel.readParcelable(remoteActionCompat.mActionIntent, 4);
        boolean z = remoteActionCompat.mEnabled;
        if (versionedParcel.readField(5)) {
            z = ((VersionedParcelParcel) versionedParcel).mParcel.readInt() != 0;
        }
        remoteActionCompat.mEnabled = z;
        boolean z2 = remoteActionCompat.mShouldShowIcon;
        if (versionedParcel.readField(6)) {
            z2 = ((VersionedParcelParcel) versionedParcel).mParcel.readInt() != 0;
        }
        remoteActionCompat.mShouldShowIcon = z2;
        return remoteActionCompat;
    }

    public static void write(RemoteActionCompat remoteActionCompat, VersionedParcel versionedParcel) {
        versionedParcel.getClass();
        IconCompat iconCompat = remoteActionCompat.mIcon;
        versionedParcel.setOutputField(1);
        versionedParcel.writeVersionedParcelable(iconCompat);
        CharSequence charSequence = remoteActionCompat.mTitle;
        versionedParcel.setOutputField(2);
        Parcel parcel = ((VersionedParcelParcel) versionedParcel).mParcel;
        TextUtils.writeToParcel(charSequence, parcel, 0);
        CharSequence charSequence2 = remoteActionCompat.mContentDescription;
        versionedParcel.setOutputField(3);
        TextUtils.writeToParcel(charSequence2, parcel, 0);
        PendingIntent pendingIntent = remoteActionCompat.mActionIntent;
        versionedParcel.setOutputField(4);
        parcel.writeParcelable(pendingIntent, 0);
        boolean z = remoteActionCompat.mEnabled;
        versionedParcel.setOutputField(5);
        parcel.writeInt(z ? 1 : 0);
        boolean z2 = remoteActionCompat.mShouldShowIcon;
        versionedParcel.setOutputField(6);
        parcel.writeInt(z2 ? 1 : 0);
    }
}
