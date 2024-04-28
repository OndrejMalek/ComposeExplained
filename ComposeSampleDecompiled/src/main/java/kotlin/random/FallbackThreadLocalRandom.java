package kotlin.random;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class FallbackThreadLocalRandom extends AbstractPlatformRandom {
    public final FallbackThreadLocalRandom$implStorage$1 implStorage = new FallbackThreadLocalRandom$implStorage$1(0);

    @Override // kotlin.random.AbstractPlatformRandom
    public final java.util.Random getImpl() {
        Object obj = this.implStorage.get();
        ResultKt.checkNotNullExpressionValue(obj, "implStorage.get()");
        return (java.util.Random) obj;
    }
}
