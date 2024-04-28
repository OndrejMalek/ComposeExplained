package kotlin.jvm.internal;

import kotlin.ResultKt;
import kotlin.jvm.internal.CallableReference;
import kotlin.reflect.KCallable;
import kotlin.reflect.KFunction;

/* loaded from: classes.dex */
public abstract class FunctionReferenceImpl extends CallableReference implements FunctionBase, KFunction {
    public final int arity;
    public final int flags;

    public FunctionReferenceImpl(int i, Class cls, String str, String str2, int i2) {
        super(CallableReference.NoReceiver.INSTANCE, cls, str, str2, (i2 & 1) == 1);
        this.arity = i;
        this.flags = 0;
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KCallable computeReflected() {
        Reflection.factory.getClass();
        return this;
    }

    /* JADX DEBUG: Method merged with bridge method: equals(Ljava/lang/Object;)Z */
    /* renamed from: equals$kotlin$jvm$internal$FunctionReference, reason: merged with bridge method [inline-methods] */
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FunctionReferenceImpl) {
            FunctionReferenceImpl functionReferenceImpl = (FunctionReferenceImpl) obj;
            return this.name.equals(functionReferenceImpl.name) && this.signature.equals(functionReferenceImpl.signature) && this.flags == functionReferenceImpl.flags && this.arity == functionReferenceImpl.arity && ResultKt.areEqual(this.receiver, functionReferenceImpl.receiver) && ResultKt.areEqual(getOwner(), functionReferenceImpl.getOwner());
        }
        if (!(obj instanceof KFunction)) {
            return false;
        }
        KCallable kCallable = this.reflected;
        if (kCallable == null) {
            computeReflected();
            this.reflected = this;
            kCallable = this;
        }
        return obj.equals(kCallable);
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public final int getArity() {
        return this.arity;
    }

    /* JADX DEBUG: Method merged with bridge method: hashCode()I */
    /* renamed from: hashCode$kotlin$jvm$internal$FunctionReference, reason: merged with bridge method [inline-methods] */
    public final int hashCode() {
        return this.signature.hashCode() + ((this.name.hashCode() + (getOwner() == null ? 0 : getOwner().hashCode() * 31)) * 31);
    }

    /* JADX DEBUG: Method merged with bridge method: toString()Ljava/lang/String; */
    /* renamed from: toString$kotlin$jvm$internal$FunctionReference, reason: merged with bridge method [inline-methods] */
    public final String toString() {
        KCallable kCallable = this.reflected;
        if (kCallable == null) {
            computeReflected();
            this.reflected = this;
            kCallable = this;
        }
        if (kCallable != this) {
            return kCallable.toString();
        }
        String str = this.name;
        if ("<init>".equals(str)) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + str + " (Kotlin reflection is not available)";
    }
}
