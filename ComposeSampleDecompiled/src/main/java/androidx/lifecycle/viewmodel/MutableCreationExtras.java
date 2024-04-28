package androidx.lifecycle.viewmodel;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class MutableCreationExtras extends CreationExtras {
    public MutableCreationExtras(CreationExtras creationExtras) {
        ResultKt.checkNotNullParameter(creationExtras, "initialExtras");
        this.map.putAll(creationExtras.map);
    }
}
