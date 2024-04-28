package androidx.compose.ui.semantics;

import kotlin.Function;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AccessibilityAction {
    public final Function action;
    public final String label;

    public AccessibilityAction(String str, Function function) {
        this.label = str;
        this.action = function;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccessibilityAction)) {
            return false;
        }
        AccessibilityAction accessibilityAction = (AccessibilityAction) obj;
        return ResultKt.areEqual(this.label, accessibilityAction.label) && ResultKt.areEqual(this.action, accessibilityAction.action);
    }

    public final int hashCode() {
        String str = this.label;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Function function = this.action;
        return hashCode + (function != null ? function.hashCode() : 0);
    }

    public final String toString() {
        return "AccessibilityAction(label=" + this.label + ", action=" + this.action + ')';
    }
}
