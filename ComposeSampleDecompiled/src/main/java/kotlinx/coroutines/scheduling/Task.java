package kotlinx.coroutines.scheduling;

import kotlin.ULong;

/* loaded from: classes.dex */
public abstract class Task implements Runnable {
    public long submissionTime;
    public ULong.Companion taskContext;

    public Task(long j, ULong.Companion companion) {
        this.submissionTime = j;
        this.taskContext = companion;
    }
}
