package androidx.compose.foundation.text.selection;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class SelectionRegistrarKt$LocalSelectionRegistrar$1 extends Lambda implements Function0 {
    public static final SelectionRegistrarKt$LocalSelectionRegistrar$1 INSTANCE = new SelectionRegistrarKt$LocalSelectionRegistrar$1(0);
    public static final SelectionRegistrarKt$LocalSelectionRegistrar$1 INSTANCE$1 = new SelectionRegistrarKt$LocalSelectionRegistrar$1(1);
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SelectionRegistrarKt$LocalSelectionRegistrar$1(int i) {
        super(0);
        this.$r8$classId = i;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        switch (this.$r8$classId) {
            case 0:
                return null;
            default:
                return TextSelectionColorsKt.DefaultTextSelectionColors;
        }
    }
}
