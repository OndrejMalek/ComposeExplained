package androidx.lifecycle;

/* loaded from: classes.dex */
public interface ViewModelProvider$Factory {
    default ViewModel create() {
        throw new UnsupportedOperationException("Factory.create(String) is unsupported.  This Factory requires `CreationExtras` to be passed into `create` method.");
    }
}
