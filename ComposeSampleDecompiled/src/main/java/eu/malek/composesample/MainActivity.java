package eu.malek.composesample;

import android.R;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.ComponentActivity;
import androidx.activity.compose.ComponentActivityKt;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.platform.ComposeView;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner$findViewTreeSavedStateRegistryOwner$1;
import kotlin.ResultKt;
import kotlin.math.MathKt;
import kotlin.sequences.SequencesKt;

/* loaded from: classes.dex */
public final class MainActivity extends ComponentActivity {
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ComposableLambdaImpl composableLambdaImpl = ComposableSingletons$MainActivityKt.f3lambda3;
        ViewGroup.LayoutParams layoutParams = ComponentActivityKt.DefaultActivityContentLayoutParams;
        View childAt = ((ViewGroup) getWindow().getDecorView().findViewById(R.id.content)).getChildAt(0);
        ComposeView composeView = childAt instanceof ComposeView ? (ComposeView) childAt : null;
        if (composeView != null) {
            composeView.setParentCompositionContext(null);
            composeView.setContent(composableLambdaImpl);
            return;
        }
        ComposeView composeView2 = new ComposeView(this);
        composeView2.setParentCompositionContext(null);
        composeView2.setContent(composableLambdaImpl);
        View decorView = getWindow().getDecorView();
        if (ResultKt.get(decorView) == null) {
            decorView.setTag(R.id.view_tree_lifecycle_owner, this);
        }
        if (((ViewModelStoreOwner) SequencesKt.firstOrNull(SequencesKt.mapNotNull(MathKt.generateSequence(decorView, ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1.INSTANCE$2), ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1.INSTANCE$3))) == null) {
            decorView.setTag(R.id.view_tree_view_model_store_owner, this);
        }
        if (((SavedStateRegistryOwner) SequencesKt.firstOrNull(SequencesKt.mapNotNull(MathKt.generateSequence(decorView, ViewTreeSavedStateRegistryOwner$findViewTreeSavedStateRegistryOwner$1.INSTANCE), ViewTreeSavedStateRegistryOwner$findViewTreeSavedStateRegistryOwner$1.INSTANCE$1))) == null) {
            decorView.setTag(R.id.view_tree_saved_state_registry_owner, this);
        }
        setContentView(composeView2, ComponentActivityKt.DefaultActivityContentLayoutParams);
    }
}
