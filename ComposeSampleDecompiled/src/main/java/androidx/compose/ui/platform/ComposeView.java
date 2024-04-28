package androidx.compose.ui.platform;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.StructuralEqualityPolicy;
import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat;
import eu.malek.composesample2.MainActivityKt$Greetings$1;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public final class ComposeView extends AbstractComposeView {
    public final ParcelableSnapshotMutableState content;
    public boolean shouldCreateCompositionOnAttachedToWindow;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Type inference failed for: r1v2, types: [androidx.compose.ui.platform.AndroidTextToolbar, java.lang.Object] */
    public ComposeView(Context context) {
        super(context, null, 0);
        ResultKt.checkNotNullParameter(context, "context");
        setClipChildren(false);
        setClipToPadding(false);
        AndroidComposeViewAccessibilityDelegateCompat.AnonymousClass1 anonymousClass1 = new AndroidComposeViewAccessibilityDelegateCompat.AnonymousClass1(1, this);
        addOnAttachStateChangeListener(anonymousClass1);
        ?? obj = new Object();
        _BOUNDARY.getPoolingContainerListenerHolder(this).listeners.add(obj);
        this.disposeViewCompositionStrategy = new ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$installFor$1(this, anonymousClass1, obj);
        this.content = ResultKt.mutableStateOf(null, StructuralEqualityPolicy.INSTANCE);
    }

    public static /* synthetic */ void getShouldCreateCompositionOnAttachedToWindow$annotations() {
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public final void Content(Composer composer, int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(420213850);
        Function2 function2 = (Function2) this.content.getValue();
        if (function2 != null) {
            function2.invoke(composerImpl, 0);
        }
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.block = new MainActivityKt$Greetings$1(i, 2, this);
    }

    @Override // android.view.ViewGroup, android.view.View
    public CharSequence getAccessibilityClassName() {
        return "androidx.compose.ui.platform.ComposeView";
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public boolean getShouldCreateCompositionOnAttachedToWindow() {
        return this.shouldCreateCompositionOnAttachedToWindow;
    }

    public final void setContent(Function2 function2) {
        ResultKt.checkNotNullParameter(function2, "content");
        this.shouldCreateCompositionOnAttachedToWindow = true;
        this.content.setValue(function2);
        if (isAttachedToWindow()) {
            if (this.parentContext == null && !isAttachedToWindow()) {
                throw new IllegalStateException("createComposition requires either a parent reference or the View to be attachedto a window. Attach the View or call setParentCompositionReference.".toString());
            }
            ensureCompositionCreated();
        }
    }
}
