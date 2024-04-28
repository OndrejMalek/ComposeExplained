package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectableModifier;
import androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class FocusableKt {
    public static final FocusableKt$FocusableInNonTouchModeElement$1 FocusableInNonTouchModeElement;

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.foundation.FocusableKt$FocusableInNonTouchModeElement$1] */
    static {
        new InspectableModifier(InspectableValueKt$NoInspectorInfo$1.INSTANCE);
        FocusableInNonTouchModeElement = new ModifierNodeElement() { // from class: androidx.compose.foundation.FocusableKt$FocusableInNonTouchModeElement$1
            @Override // androidx.compose.ui.node.ModifierNodeElement
            public final Modifier.Node create() {
                return new Modifier.Node();
            }

            public final boolean equals(Object obj) {
                return this == obj;
            }

            public final int hashCode() {
                return System.identityHashCode(this);
            }

            @Override // androidx.compose.ui.node.ModifierNodeElement
            public final void update(Modifier.Node node) {
                ResultKt.checkNotNullParameter((FocusableInNonTouchMode) node, "node");
            }
        };
    }
}
