package androidx.lifecycle;

import android.view.View;
import eu.malek.composesample2.R;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1 extends Lambda implements Function1 {
    public static final ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1 INSTANCE = new ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1(0);
    public static final ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1 INSTANCE$1 = new ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1(1);
    public static final ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1 INSTANCE$2 = new ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1(2);
    public static final ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1 INSTANCE$3 = new ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1(3);
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1(int i) {
        super(1);
        this.$r8$classId = i;
    }

    public final View invoke(View view) {
        switch (this.$r8$classId) {
            case 0:
                ResultKt.checkNotNullParameter(view, "currentView");
                Object parent = view.getParent();
                if (parent instanceof View) {
                    return (View) parent;
                }
                return null;
            default:
                ResultKt.checkNotNullParameter(view, "view");
                Object parent2 = view.getParent();
                if (parent2 instanceof View) {
                    return (View) parent2;
                }
                return null;
        }
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return invoke((View) obj);
            case 1:
                View view = (View) obj;
                ResultKt.checkNotNullParameter(view, "viewParent");
                Object tag = view.getTag(R.id.view_tree_lifecycle_owner);
                if (tag instanceof LifecycleOwner) {
                    return (LifecycleOwner) tag;
                }
                return null;
            case 2:
                return invoke((View) obj);
            default:
                View view2 = (View) obj;
                ResultKt.checkNotNullParameter(view2, "view");
                Object tag2 = view2.getTag(R.id.view_tree_view_model_store_owner);
                if (tag2 instanceof ViewModelStoreOwner) {
                    return (ViewModelStoreOwner) tag2;
                }
                return null;
        }
    }
}
