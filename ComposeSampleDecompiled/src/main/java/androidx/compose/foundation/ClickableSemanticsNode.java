package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.reflect.KProperty;

/* loaded from: classes.dex */
public final class ClickableSemanticsNode extends Modifier.Node implements SemanticsModifierNode {
    public boolean enabled;
    public Function0 onClick;
    public String onClickLabel;
    public Function0 onLongClick;
    public String onLongClickLabel;
    public Role role;

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final void applySemantics(SemanticsConfiguration semanticsConfiguration) {
        ResultKt.checkNotNullParameter(semanticsConfiguration, "<this>");
        Role role = this.role;
        if (role != null) {
            SemanticsPropertiesKt.m240setRolekuIjeqM(semanticsConfiguration, role.value);
        }
        String str = this.onClickLabel;
        final int i = 0;
        Function0 function0 = new Function0(this) { // from class: androidx.compose.foundation.ClickableSemanticsNode$applySemantics$1
            public final /* synthetic */ ClickableSemanticsNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Boolean bool = Boolean.TRUE;
                ClickableSemanticsNode clickableSemanticsNode = this.this$0;
                int i2 = i;
                switch (i2) {
                    case 0:
                        switch (i2) {
                            case 0:
                                clickableSemanticsNode.onClick.invoke();
                                return bool;
                            default:
                                Function0 function02 = clickableSemanticsNode.onLongClick;
                                if (function02 != null) {
                                    function02.invoke();
                                }
                                return bool;
                        }
                    default:
                        switch (i2) {
                            case 0:
                                clickableSemanticsNode.onClick.invoke();
                                return bool;
                            default:
                                Function0 function03 = clickableSemanticsNode.onLongClick;
                                if (function03 != null) {
                                    function03.invoke();
                                }
                                return bool;
                        }
                }
            }
        };
        KProperty[] kPropertyArr = SemanticsPropertiesKt.$$delegatedProperties;
        semanticsConfiguration.set(SemanticsActions.OnClick, new AccessibilityAction(str, function0));
        if (this.onLongClick != null) {
            final int i2 = 1;
            semanticsConfiguration.set(SemanticsActions.OnLongClick, new AccessibilityAction(this.onLongClickLabel, new Function0(this) { // from class: androidx.compose.foundation.ClickableSemanticsNode$applySemantics$1
                public final /* synthetic */ ClickableSemanticsNode this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                    this.this$0 = this;
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Boolean bool = Boolean.TRUE;
                    ClickableSemanticsNode clickableSemanticsNode = this.this$0;
                    int i22 = i2;
                    switch (i22) {
                        case 0:
                            switch (i22) {
                                case 0:
                                    clickableSemanticsNode.onClick.invoke();
                                    return bool;
                                default:
                                    Function0 function02 = clickableSemanticsNode.onLongClick;
                                    if (function02 != null) {
                                        function02.invoke();
                                    }
                                    return bool;
                            }
                        default:
                            switch (i22) {
                                case 0:
                                    clickableSemanticsNode.onClick.invoke();
                                    return bool;
                                default:
                                    Function0 function03 = clickableSemanticsNode.onLongClick;
                                    if (function03 != null) {
                                        function03.invoke();
                                    }
                                    return bool;
                            }
                    }
                }
            }));
        }
        if (this.enabled) {
            return;
        }
        semanticsConfiguration.set(SemanticsProperties.Disabled, Unit.INSTANCE);
    }

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final boolean getShouldMergeDescendantSemantics() {
        return true;
    }
}
