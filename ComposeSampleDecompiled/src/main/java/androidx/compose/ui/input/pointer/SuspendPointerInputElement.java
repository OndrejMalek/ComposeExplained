package androidx.compose.ui.input.pointer;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public final class SuspendPointerInputElement extends ModifierNodeElement {
    public final Object key1;
    public final Object key2;
    public final Object[] keys;
    public final Function2 pointerInputHandler;

    public SuspendPointerInputElement(Object obj, Function2 function2) {
        ResultKt.checkNotNullParameter(function2, "pointerInputHandler");
        this.key1 = obj;
        this.key2 = null;
        this.keys = null;
        this.pointerInputHandler = function2;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        return new SuspendingPointerInputModifierNodeImpl(this.pointerInputHandler);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SuspendPointerInputElement)) {
            return false;
        }
        SuspendPointerInputElement suspendPointerInputElement = (SuspendPointerInputElement) obj;
        if (!ResultKt.areEqual(this.key1, suspendPointerInputElement.key1) || !ResultKt.areEqual(this.key2, suspendPointerInputElement.key2)) {
            return false;
        }
        Object[] objArr = this.keys;
        if (objArr != null) {
            Object[] objArr2 = suspendPointerInputElement.keys;
            if (objArr2 == null || !Arrays.equals(objArr, objArr2)) {
                return false;
            }
        } else if (suspendPointerInputElement.keys != null) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        Object obj = this.key1;
        int hashCode = (obj != null ? obj.hashCode() : 0) * 31;
        Object obj2 = this.key2;
        int hashCode2 = (hashCode + (obj2 != null ? obj2.hashCode() : 0)) * 31;
        Object[] objArr = this.keys;
        return hashCode2 + (objArr != null ? Arrays.hashCode(objArr) : 0);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        SuspendingPointerInputModifierNodeImpl suspendingPointerInputModifierNodeImpl = (SuspendingPointerInputModifierNodeImpl) node;
        ResultKt.checkNotNullParameter(suspendingPointerInputModifierNodeImpl, "node");
        Function2 function2 = this.pointerInputHandler;
        ResultKt.checkNotNullParameter(function2, "value");
        suspendingPointerInputModifierNodeImpl.resetPointerInputHandler();
        suspendingPointerInputModifierNodeImpl.pointerInputHandler = function2;
    }
}
