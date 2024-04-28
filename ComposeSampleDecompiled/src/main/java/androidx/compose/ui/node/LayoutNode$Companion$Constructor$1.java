package androidx.compose.ui.node;

import java.util.LinkedHashMap;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class LayoutNode$Companion$Constructor$1 extends Lambda implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public static final LayoutNode$Companion$Constructor$1 INSTANCE$2 = new LayoutNode$Companion$Constructor$1(2);
    public static final LayoutNode$Companion$Constructor$1 INSTANCE = new LayoutNode$Companion$Constructor$1(0);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LayoutNode$Companion$Constructor$1(int i) {
        super(0);
        this.$r8$classId = i;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        int i = this.$r8$classId;
        switch (i) {
            case 0:
                switch (i) {
                    case 0:
                        return new LayoutNode(false, 3);
                    default:
                        return new LayoutNode(true, 2);
                }
            case 1:
                switch (i) {
                    case 0:
                        return new LayoutNode(false, 3);
                    default:
                        return new LayoutNode(true, 2);
                }
            default:
                return new LinkedHashMap();
        }
    }
}
