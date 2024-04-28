package androidx.compose.ui.focus;

import _COROUTINE._BOUNDARY;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class FocusProperties$exit$1 extends Lambda implements Function1 {
    public static final FocusProperties$exit$1 INSTANCE$2 = new FocusProperties$exit$1(2);
    public static final FocusProperties$exit$1 INSTANCE$3 = new FocusProperties$exit$1(3);
    public static final FocusProperties$exit$1 INSTANCE$4 = new FocusProperties$exit$1(4);
    public static final FocusProperties$exit$1 INSTANCE$5 = new FocusProperties$exit$1(5);
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FocusProperties$exit$1(int i) {
        super(1);
        this.$r8$classId = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        int i = this.$r8$classId;
        switch (i) {
            case 0:
                int i2 = ((FocusDirection) obj).value;
                return m70invoke3ESFkO8();
            case 1:
                int i3 = ((FocusDirection) obj).value;
                return m70invoke3ESFkO8();
            case 2:
                int i4 = ((FocusDirection) obj).value;
                return m70invoke3ESFkO8();
            case 3:
                int i5 = ((FocusDirection) obj).value;
                return m70invoke3ESFkO8();
            case 4:
                FocusTargetNode focusTargetNode = (FocusTargetNode) obj;
                switch (i) {
                    case 4:
                        ResultKt.checkNotNullParameter(focusTargetNode, "it");
                        return Boolean.valueOf(_BOUNDARY.requestFocus(focusTargetNode));
                    default:
                        ResultKt.checkNotNullParameter(focusTargetNode, "it");
                        return Boolean.valueOf(_BOUNDARY.requestFocus(focusTargetNode));
                }
            default:
                FocusTargetNode focusTargetNode2 = (FocusTargetNode) obj;
                switch (i) {
                    case 4:
                        ResultKt.checkNotNullParameter(focusTargetNode2, "it");
                        return Boolean.valueOf(_BOUNDARY.requestFocus(focusTargetNode2));
                    default:
                        ResultKt.checkNotNullParameter(focusTargetNode2, "it");
                        return Boolean.valueOf(_BOUNDARY.requestFocus(focusTargetNode2));
                }
        }
    }

    /* renamed from: invoke-3ESFkO8, reason: not valid java name */
    public final FocusRequester m70invoke3ESFkO8() {
        switch (this.$r8$classId) {
            case 0:
                return FocusRequester.Default;
            case 1:
                return FocusRequester.Default;
            case 2:
                return FocusRequester.Default;
            default:
                return FocusRequester.Default;
        }
    }
}
