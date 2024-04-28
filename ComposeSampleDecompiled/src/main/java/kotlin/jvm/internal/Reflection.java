package kotlin.jvm.internal;

/* loaded from: classes.dex */
public abstract class Reflection {
    public static final ReflectionFactory factory;

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:11:0x000e */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:5:0x000e */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.jvm.internal.ReflectionFactory] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v4 */
    static {
        ?? r0 = 0;
        try {
            r0 = (ReflectionFactory) Class.forName("kotlin.reflect.jvm.internal.ReflectionFactoryImpl").newInstance();
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
        }
        if (r0 == 0) {
            r0 = new Object();
        }
        factory = r0;
    }
}
