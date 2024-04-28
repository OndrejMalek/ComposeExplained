package androidx.compose.ui.semantics;

import androidx.compose.ui.platform.InvertMatrixKt;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Function;
import kotlin.ResultKt;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public final class SemanticsConfiguration implements SemanticsPropertyReceiver, Iterable, KMappedMarker {
    public boolean isClearingSemantics;
    public boolean isMergingSemanticsOfDescendants;
    public final LinkedHashMap props = new LinkedHashMap();

    public final boolean contains(SemanticsPropertyKey semanticsPropertyKey) {
        ResultKt.checkNotNullParameter(semanticsPropertyKey, "key");
        return this.props.containsKey(semanticsPropertyKey);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SemanticsConfiguration)) {
            return false;
        }
        SemanticsConfiguration semanticsConfiguration = (SemanticsConfiguration) obj;
        return ResultKt.areEqual(this.props, semanticsConfiguration.props) && this.isMergingSemanticsOfDescendants == semanticsConfiguration.isMergingSemanticsOfDescendants && this.isClearingSemantics == semanticsConfiguration.isClearingSemantics;
    }

    public final Object get(SemanticsPropertyKey semanticsPropertyKey) {
        ResultKt.checkNotNullParameter(semanticsPropertyKey, "key");
        Object obj = this.props.get(semanticsPropertyKey);
        if (obj != null) {
            return obj;
        }
        throw new IllegalStateException("Key not present: " + semanticsPropertyKey + " - consider getOrElse or getOrNull");
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isClearingSemantics) + ((Boolean.hashCode(this.isMergingSemanticsOfDescendants) + (this.props.hashCode() * 31)) * 31);
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return this.props.entrySet().iterator();
    }

    public final void set(SemanticsPropertyKey semanticsPropertyKey, Object obj) {
        ResultKt.checkNotNullParameter(semanticsPropertyKey, "key");
        boolean z = obj instanceof AccessibilityAction;
        LinkedHashMap linkedHashMap = this.props;
        if (!z || !contains(semanticsPropertyKey)) {
            linkedHashMap.put(semanticsPropertyKey, obj);
            return;
        }
        Object obj2 = linkedHashMap.get(semanticsPropertyKey);
        ResultKt.checkNotNull(obj2, "null cannot be cast to non-null type androidx.compose.ui.semantics.AccessibilityAction<*>");
        AccessibilityAction accessibilityAction = (AccessibilityAction) obj2;
        AccessibilityAction accessibilityAction2 = (AccessibilityAction) obj;
        String str = accessibilityAction2.label;
        if (str == null) {
            str = accessibilityAction.label;
        }
        Function function = accessibilityAction2.action;
        if (function == null) {
            function = accessibilityAction.action;
        }
        linkedHashMap.put(semanticsPropertyKey, new AccessibilityAction(str, function));
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        if (this.isMergingSemanticsOfDescendants) {
            sb.append("mergeDescendants=true");
            str = ", ";
        } else {
            str = "";
        }
        if (this.isClearingSemantics) {
            sb.append(str);
            sb.append("isClearingSemantics=true");
            str = ", ";
        }
        for (Map.Entry entry : this.props.entrySet()) {
            SemanticsPropertyKey semanticsPropertyKey = (SemanticsPropertyKey) entry.getKey();
            Object value = entry.getValue();
            sb.append(str);
            sb.append(semanticsPropertyKey.name);
            sb.append(" : ");
            sb.append(value);
            str = ", ";
        }
        return InvertMatrixKt.simpleIdentityToString(this) + "{ " + ((Object) sb) + " }";
    }
}
