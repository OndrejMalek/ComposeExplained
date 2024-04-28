package androidx.compose.ui.platform;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$installFor$1 extends Lambda implements Function0 {
    public final /* synthetic */ AndroidComposeViewAccessibilityDelegateCompat.AnonymousClass1 $listener;
    public final /* synthetic */ AndroidTextToolbar $poolingContainerListener;
    public final /* synthetic */ AbstractComposeView $view;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewCompositionStrategy$DisposeOnDetachedFromWindowOrReleasedFromPool$installFor$1(AbstractComposeView abstractComposeView, AndroidComposeViewAccessibilityDelegateCompat.AnonymousClass1 anonymousClass1, AndroidTextToolbar androidTextToolbar) {
        super(0);
        this.$view = abstractComposeView;
        this.$listener = anonymousClass1;
        this.$poolingContainerListener = androidTextToolbar;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        AbstractComposeView abstractComposeView = this.$view;
        abstractComposeView.removeOnAttachStateChangeListener(this.$listener);
        AndroidTextToolbar androidTextToolbar = this.$poolingContainerListener;
        ResultKt.checkNotNullParameter(androidTextToolbar, "listener");
        _BOUNDARY.getPoolingContainerListenerHolder(abstractComposeView).listeners.remove(androidTextToolbar);
        return Unit.INSTANCE;
    }
}
