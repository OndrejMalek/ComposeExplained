package kotlin.jvm.internal;

import kotlin.ResultKt;
import kotlin.reflect.KCallable;
import kotlin.reflect.KProperty;

/* loaded from: classes.dex */
public abstract class PropertyReference extends CallableReference implements KProperty {
    public final boolean syntheticJavaProperty;

    public PropertyReference(Object obj, Class cls, String str, String str2) {
        super(obj, cls, str, str2, true);
        this.syntheticJavaProperty = false;
    }

    public final KCallable compute() {
        if (this.syntheticJavaProperty) {
            return this;
        }
        KCallable kCallable = this.reflected;
        if (kCallable != null) {
            return kCallable;
        }
        KCallable computeReflected = computeReflected();
        this.reflected = computeReflected;
        return computeReflected;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PropertyReference) {
            PropertyReference propertyReference = (PropertyReference) obj;
            return getOwner().equals(propertyReference.getOwner()) && this.name.equals(propertyReference.name) && this.signature.equals(propertyReference.signature) && ResultKt.areEqual(this.receiver, propertyReference.receiver);
        }
        if (obj instanceof KProperty) {
            return obj.equals(compute());
        }
        return false;
    }

    public final int hashCode() {
        return this.signature.hashCode() + ((this.name.hashCode() + (getOwner().hashCode() * 31)) * 31);
    }

    public final String toString() {
        KCallable compute = compute();
        if (compute != this) {
            return compute.toString();
        }
        return "property " + this.name + " (Kotlin reflection is not available)";
    }
}
