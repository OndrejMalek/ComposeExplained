package androidx.compose.animation.core;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.MutexImpl;

/* loaded from: classes.dex */
public final class MutatorMutex {
    public final AtomicReference currentMutator = new AtomicReference(null);
    public final MutexImpl mutex = new MutexImpl(false);

    /* loaded from: classes.dex */
    public final class Mutator {
        public final Job job;
        public final int priority;

        public Mutator(int i, Job job) {
            DurationKt$$ExternalSyntheticCheckNotZero0.m(i, "priority");
            this.priority = i;
            this.job = job;
        }
    }
}
