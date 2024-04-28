package kotlinx.coroutines.flow;

import kotlinx.coroutines.flow.internal.SubscriptionCountStateFlow;

/* loaded from: classes.dex */
public interface SharingStarted {

    /* loaded from: classes.dex */
    public final class Companion {
        public static final StartedLazily Eagerly = new StartedLazily(1);
        public static final StartedLazily Lazily = new StartedLazily(0);
    }

    Flow command(SubscriptionCountStateFlow subscriptionCountStateFlow);
}
