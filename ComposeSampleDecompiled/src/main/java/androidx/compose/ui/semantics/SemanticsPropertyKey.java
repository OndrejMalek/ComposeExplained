package androidx.compose.ui.semantics;

import java.util.ArrayList;
import java.util.List;
import kotlin.Function;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class SemanticsPropertyKey {
    public final Function2 mergePolicy;
    public final String name;

    public SemanticsPropertyKey(String str, Function2 function2) {
        ResultKt.checkNotNullParameter(function2, "mergePolicy");
        this.name = str;
        this.mergePolicy = function2;
    }

    public final String toString() {
        return "SemanticsPropertyKey: " + this.name;
    }

    /* renamed from: androidx.compose.ui.semantics.SemanticsPropertyKey$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends Lambda implements Function2 {
        public final /* synthetic */ int $r8$classId;
        public static final AnonymousClass1 INSTANCE$1 = new AnonymousClass1(1);
        public static final AnonymousClass1 INSTANCE$2 = new AnonymousClass1(2);
        public static final AnonymousClass1 INSTANCE$5 = new AnonymousClass1(5);
        public static final AnonymousClass1 INSTANCE$6 = new AnonymousClass1(6);
        public static final AnonymousClass1 INSTANCE$7 = new AnonymousClass1(7);
        public static final AnonymousClass1 INSTANCE$8 = new AnonymousClass1(8);
        public static final AnonymousClass1 INSTANCE$9 = new AnonymousClass1(9);
        public static final AnonymousClass1 INSTANCE$10 = new AnonymousClass1(10);
        public static final AnonymousClass1 INSTANCE$11 = new AnonymousClass1(11);
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1(0);

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public /* synthetic */ AnonymousClass1(int i) {
            super(2);
            this.$r8$classId = i;
        }

        public final List invoke(List list, List list2) {
            switch (this.$r8$classId) {
                case 1:
                    ResultKt.checkNotNullParameter(list2, "childValue");
                    if (list == null) {
                        return list2;
                    }
                    ArrayList mutableList = CollectionsKt___CollectionsKt.toMutableList(list);
                    mutableList.addAll(list2);
                    return mutableList;
                default:
                    ResultKt.checkNotNullParameter(list2, "childValue");
                    if (list == null) {
                        return list2;
                    }
                    ArrayList mutableList2 = CollectionsKt___CollectionsKt.toMutableList(list);
                    mutableList2.addAll(list2);
                    return mutableList2;
            }
        }

        public final void invoke(Unit unit, Unit unit2) {
            switch (this.$r8$classId) {
                case 2:
                    ResultKt.checkNotNullParameter(unit2, "<anonymous parameter 1>");
                    return;
                case 3:
                    ResultKt.checkNotNullParameter(unit2, "<anonymous parameter 1>");
                    throw new IllegalStateException("merge function called on unmergeable property IsDialog. A dialog should not be a child of a clickable/focusable node.");
                default:
                    ResultKt.checkNotNullParameter(unit2, "<anonymous parameter 1>");
                    throw new IllegalStateException("merge function called on unmergeable property IsPopup. A popup should not be a child of a clickable/focusable node.");
            }
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            String str;
            Function function;
            int i = this.$r8$classId;
            switch (i) {
                case 0:
                    return obj == null ? obj2 : obj;
                case 1:
                    return invoke((List) obj, (List) obj2);
                case 2:
                    Unit unit = (Unit) obj;
                    invoke(unit, (Unit) obj2);
                    return unit;
                case 3:
                    Unit unit2 = (Unit) obj;
                    invoke(unit2, (Unit) obj2);
                    return unit2;
                case 4:
                    Unit unit3 = (Unit) obj;
                    invoke(unit3, (Unit) obj2);
                    return unit3;
                case 5:
                    String str2 = (String) obj;
                    String str3 = (String) obj2;
                    switch (i) {
                        case 5:
                            ResultKt.checkNotNullParameter(str3, "<anonymous parameter 1>");
                            throw new IllegalStateException("merge function called on unmergeable property PaneTitle.");
                        default:
                            ResultKt.checkNotNullParameter(str3, "<anonymous parameter 1>");
                            return str2;
                    }
                case 6:
                    Role role = (Role) obj;
                    int i2 = ((Role) obj2).value;
                    return role;
                case 7:
                    String str4 = (String) obj;
                    String str5 = (String) obj2;
                    switch (i) {
                        case 5:
                            ResultKt.checkNotNullParameter(str5, "<anonymous parameter 1>");
                            throw new IllegalStateException("merge function called on unmergeable property PaneTitle.");
                        default:
                            ResultKt.checkNotNullParameter(str5, "<anonymous parameter 1>");
                            return str4;
                    }
                case 8:
                    return invoke((List) obj, (List) obj2);
                case 9:
                    Float f = (Float) obj;
                    ((Number) obj2).floatValue();
                    return f;
                case 10:
                    Boolean bool = (Boolean) obj;
                    ((Boolean) obj2).getClass();
                    return bool;
                default:
                    AccessibilityAction accessibilityAction = (AccessibilityAction) obj;
                    AccessibilityAction accessibilityAction2 = (AccessibilityAction) obj2;
                    ResultKt.checkNotNullParameter(accessibilityAction2, "childValue");
                    if (accessibilityAction == null || (str = accessibilityAction.label) == null) {
                        str = accessibilityAction2.label;
                    }
                    if (accessibilityAction == null || (function = accessibilityAction.action) == null) {
                        function = accessibilityAction2.action;
                    }
                    return new AccessibilityAction(str, function);
            }
        }
    }
}
