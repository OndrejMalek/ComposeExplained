package kotlin.random;

import java.io.Serializable;
import kotlin.internal.jdk8.JDK8PlatformImplementations$ReflectSdkVersion;

/* loaded from: classes.dex */
public abstract class Random {
    public static final Default Default = new Random();
    public static final AbstractPlatformRandom defaultRandom;

    /* loaded from: classes.dex */
    public final class Default extends Random implements Serializable {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [kotlin.random.Random$Default, kotlin.random.Random] */
    /* JADX WARN: Type inference failed for: r0v3, types: [kotlin.random.AbstractPlatformRandom] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    static {
        Integer num = JDK8PlatformImplementations$ReflectSdkVersion.sdkVersion;
        defaultRandom = (num == null || num.intValue() >= 34) ? new Random() : new FallbackThreadLocalRandom();
    }
}
