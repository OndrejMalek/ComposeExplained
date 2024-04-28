package kotlinx.coroutines.flow;

import kotlinx.coroutines.flow.internal.SubscriptionCountStateFlow;

/* loaded from: classes.dex */
public final class StartedLazily implements SharingStarted {
    public final /* synthetic */ int $r8$classId;

    @Override // kotlinx.coroutines.flow.SharingStarted
    public final Flow command(SubscriptionCountStateFlow subscriptionCountStateFlow) {
        switch (this.$r8$classId) {
            case 0:
                return new SafeFlow(new StartedLazily$command$1(subscriptionCountStateFlow, null));
            default:
                return new SafeFlow();
        }
    }

    public final String toString() {
        switch (this.$r8$classId) {
            case 0:
                return "SharingStarted.Lazily";
            default:
                return "SharingStarted.Eagerly";
        }
    }
}
