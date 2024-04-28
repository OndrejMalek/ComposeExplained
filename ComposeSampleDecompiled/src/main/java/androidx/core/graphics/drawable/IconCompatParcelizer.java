package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.versionedparcelable.VersionedParcel;
import androidx.versionedparcelable.VersionedParcelParcel;
import java.nio.charset.Charset;

/* loaded from: classes.dex */
public class IconCompatParcelizer {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:23:0x00ac. Please report as an issue. */
    public static IconCompat read(VersionedParcel versionedParcel) {
        IconCompat iconCompat = new IconCompat();
        int i = iconCompat.mType;
        if (versionedParcel.readField(1)) {
            i = ((VersionedParcelParcel) versionedParcel).mParcel.readInt();
        }
        iconCompat.mType = i;
        byte[] bArr = iconCompat.mData;
        if (versionedParcel.readField(2)) {
            Parcel parcel = ((VersionedParcelParcel) versionedParcel).mParcel;
            int readInt = parcel.readInt();
            if (readInt < 0) {
                bArr = null;
            } else {
                byte[] bArr2 = new byte[readInt];
                parcel.readByteArray(bArr2);
                bArr = bArr2;
            }
        }
        iconCompat.mData = bArr;
        iconCompat.mParcelable = versionedParcel.readParcelable(iconCompat.mParcelable, 3);
        int i2 = iconCompat.mInt1;
        if (versionedParcel.readField(4)) {
            i2 = ((VersionedParcelParcel) versionedParcel).mParcel.readInt();
        }
        iconCompat.mInt1 = i2;
        int i3 = iconCompat.mInt2;
        if (versionedParcel.readField(5)) {
            i3 = ((VersionedParcelParcel) versionedParcel).mParcel.readInt();
        }
        iconCompat.mInt2 = i3;
        iconCompat.mTintList = (ColorStateList) versionedParcel.readParcelable(iconCompat.mTintList, 6);
        String str = iconCompat.mTintModeStr;
        if (versionedParcel.readField(7)) {
            str = ((VersionedParcelParcel) versionedParcel).mParcel.readString();
        }
        iconCompat.mTintModeStr = str;
        String str2 = iconCompat.mString1;
        if (versionedParcel.readField(8)) {
            str2 = ((VersionedParcelParcel) versionedParcel).mParcel.readString();
        }
        iconCompat.mString1 = str2;
        iconCompat.mTintMode = PorterDuff.Mode.valueOf(iconCompat.mTintModeStr);
        switch (iconCompat.mType) {
            case -1:
                Parcelable parcelable = iconCompat.mParcelable;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                iconCompat.mObj1 = parcelable;
                return iconCompat;
            case 0:
            default:
                return iconCompat;
            case 1:
            case 5:
                Parcelable parcelable2 = iconCompat.mParcelable;
                if (parcelable2 != null) {
                    iconCompat.mObj1 = parcelable2;
                } else {
                    byte[] bArr3 = iconCompat.mData;
                    iconCompat.mObj1 = bArr3;
                    iconCompat.mType = 3;
                    iconCompat.mInt1 = 0;
                    iconCompat.mInt2 = bArr3.length;
                }
                return iconCompat;
            case 2:
            case 4:
            case 6:
                String str3 = new String(iconCompat.mData, Charset.forName("UTF-16"));
                iconCompat.mObj1 = str3;
                if (iconCompat.mType == 2 && iconCompat.mString1 == null) {
                    iconCompat.mString1 = str3.split(":", -1)[0];
                }
                return iconCompat;
            case 3:
                iconCompat.mObj1 = iconCompat.mData;
                return iconCompat;
        }
    }

    public static void write(IconCompat iconCompat, VersionedParcel versionedParcel) {
        versionedParcel.getClass();
        iconCompat.mTintModeStr = iconCompat.mTintMode.name();
        switch (iconCompat.mType) {
            case -1:
                iconCompat.mParcelable = (Parcelable) iconCompat.mObj1;
                break;
            case 1:
            case 5:
                iconCompat.mParcelable = (Parcelable) iconCompat.mObj1;
                break;
            case 2:
                iconCompat.mData = ((String) iconCompat.mObj1).getBytes(Charset.forName("UTF-16"));
                break;
            case 3:
                iconCompat.mData = (byte[]) iconCompat.mObj1;
                break;
            case 4:
            case 6:
                iconCompat.mData = iconCompat.mObj1.toString().getBytes(Charset.forName("UTF-16"));
                break;
        }
        int i = iconCompat.mType;
        if (-1 != i) {
            versionedParcel.setOutputField(1);
            ((VersionedParcelParcel) versionedParcel).mParcel.writeInt(i);
        }
        byte[] bArr = iconCompat.mData;
        if (bArr != null) {
            versionedParcel.setOutputField(2);
            int length = bArr.length;
            Parcel parcel = ((VersionedParcelParcel) versionedParcel).mParcel;
            parcel.writeInt(length);
            parcel.writeByteArray(bArr);
        }
        Parcelable parcelable = iconCompat.mParcelable;
        if (parcelable != null) {
            versionedParcel.setOutputField(3);
            ((VersionedParcelParcel) versionedParcel).mParcel.writeParcelable(parcelable, 0);
        }
        int i2 = iconCompat.mInt1;
        if (i2 != 0) {
            versionedParcel.setOutputField(4);
            ((VersionedParcelParcel) versionedParcel).mParcel.writeInt(i2);
        }
        int i3 = iconCompat.mInt2;
        if (i3 != 0) {
            versionedParcel.setOutputField(5);
            ((VersionedParcelParcel) versionedParcel).mParcel.writeInt(i3);
        }
        ColorStateList colorStateList = iconCompat.mTintList;
        if (colorStateList != null) {
            versionedParcel.setOutputField(6);
            ((VersionedParcelParcel) versionedParcel).mParcel.writeParcelable(colorStateList, 0);
        }
        String str = iconCompat.mTintModeStr;
        if (str != null) {
            versionedParcel.setOutputField(7);
            ((VersionedParcelParcel) versionedParcel).mParcel.writeString(str);
        }
        String str2 = iconCompat.mString1;
        if (str2 != null) {
            versionedParcel.setOutputField(8);
            ((VersionedParcelParcel) versionedParcel).mParcel.writeString(str2);
        }
    }
}
