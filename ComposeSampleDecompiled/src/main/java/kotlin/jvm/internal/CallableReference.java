package kotlin.jvm.internal;

import java.io.Serializable;
import kotlin.reflect.KCallable;

/* loaded from: classes.dex */
public abstract class CallableReference implements KCallable, Serializable {
    public final boolean isTopLevel;
    public final String name;
    public final Class owner;
    public final Object receiver;
    public transient KCallable reflected;
    public final String signature;

    /* loaded from: classes.dex */
    public final class NoReceiver implements Serializable {
        public static final NoReceiver INSTANCE = new Object();
    }

    public CallableReference(Object obj, Class cls, String str, String str2, boolean z) {
        this.receiver = obj;
        this.owner = cls;
        this.name = str;
        this.signature = str2;
        this.isTopLevel = z;
    }

    public abstract KCallable computeReflected();

    public final ClassBasedDeclarationContainer getOwner() {
        ClassBasedDeclarationContainer classReference;
        Class cls = this.owner;
        if (cls == null) {
            return null;
        }
        if (this.isTopLevel) {
            Reflection.factory.getClass();
            classReference = new PackageReference(cls);
        } else {
            Reflection.factory.getClass();
            classReference = new ClassReference(cls);
        }
        return classReference;
    }
}
