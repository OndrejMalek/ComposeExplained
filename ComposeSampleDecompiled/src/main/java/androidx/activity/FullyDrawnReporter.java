package androidx.activity;

import androidx.activity.ComponentActivity;
import java.util.ArrayList;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class FullyDrawnReporter {
    public final Object lock;
    public final ArrayList onReportCallbacks;
    public boolean reportedFullyDrawn;

    public FullyDrawnReporter(ComponentActivity.ReportFullyDrawnExecutorImpl reportFullyDrawnExecutorImpl, ComponentActivity$fullyDrawnReporter$2 componentActivity$fullyDrawnReporter$2) {
        ResultKt.checkNotNullParameter(reportFullyDrawnExecutorImpl, "executor");
        this.lock = new Object();
        this.onReportCallbacks = new ArrayList();
    }
}
