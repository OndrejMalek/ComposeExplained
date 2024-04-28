package androidx.compose.runtime;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ParcelableSnapshotMutableState$Companion$CREATOR$1 implements Parcelable.ClassLoaderCreator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        ResultKt.checkNotNullParameter(parcel, "parcel");
        return createFromParcel(parcel, (ClassLoader) null);
    }

    @Override // android.os.Parcelable.Creator
    public final Object[] newArray(int i) {
        return new ParcelableSnapshotMutableState[i];
    }

    @Override // android.os.Parcelable.ClassLoaderCreator
    public final /* bridge */ /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return createFromParcel(parcel, classLoader);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.runtime.SnapshotMutableStateImpl, androidx.compose.runtime.ParcelableSnapshotMutableState] */
    public static ParcelableSnapshotMutableState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        SnapshotMutationPolicy snapshotMutationPolicy;
        ResultKt.checkNotNullParameter(parcel, "parcel");
        if (classLoader == null) {
            classLoader = ParcelableSnapshotMutableState$Companion$CREATOR$1.class.getClassLoader();
        }
        Object readValue = parcel.readValue(classLoader);
        int readInt = parcel.readInt();
        if (readInt == 0) {
            snapshotMutationPolicy = NeverEqualPolicy.INSTANCE;
        } else if (readInt == 1) {
            snapshotMutationPolicy = StructuralEqualityPolicy.INSTANCE;
        } else if (readInt == 2) {
            snapshotMutationPolicy = ReferentialEqualityPolicy.INSTANCE;
        } else {
            throw new IllegalStateException("Unsupported MutableState policy " + readInt + " was restored");
        }
        return new SnapshotMutableStateImpl(readValue, snapshotMutationPolicy);
    }
}
