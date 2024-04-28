package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import androidx.compose.ui.platform.AndroidComposeView$focusOwner$1;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class OwnerSnapshotObserver {
    public final SnapshotStateObserver observer;
    public final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 onCommitAffectingLookaheadMeasure = BackwardsCompatNodeKt$onDrawCacheReadsChanged$1.INSTANCE$16;
    public final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 onCommitAffectingMeasure = BackwardsCompatNodeKt$onDrawCacheReadsChanged$1.INSTANCE$17;
    public final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 onCommitAffectingSemantics = BackwardsCompatNodeKt$onDrawCacheReadsChanged$1.INSTANCE$18;
    public final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 onCommitAffectingLayout = BackwardsCompatNodeKt$onDrawCacheReadsChanged$1.INSTANCE$12;
    public final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 onCommitAffectingLayoutModifier = BackwardsCompatNodeKt$onDrawCacheReadsChanged$1.INSTANCE$13;
    public final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 onCommitAffectingLayoutModifierInLookahead = BackwardsCompatNodeKt$onDrawCacheReadsChanged$1.INSTANCE$14;
    public final BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 onCommitAffectingLookaheadLayout = BackwardsCompatNodeKt$onDrawCacheReadsChanged$1.INSTANCE$15;

    public OwnerSnapshotObserver(AndroidComposeView$focusOwner$1 androidComposeView$focusOwner$1) {
        this.observer = new SnapshotStateObserver(androidComposeView$focusOwner$1);
    }

    public final void observeReads$ui_release(OwnerScope ownerScope, Function1 function1, Function0 function0) {
        Object obj;
        SnapshotStateObserver.ObservedScopeMap observedScopeMap;
        ResultKt.checkNotNullParameter(ownerScope, "target");
        ResultKt.checkNotNullParameter(function1, "onChanged");
        SnapshotStateObserver snapshotStateObserver = this.observer;
        snapshotStateObserver.getClass();
        synchronized (snapshotStateObserver.observedScopeMaps) {
            MutableVector mutableVector = snapshotStateObserver.observedScopeMaps;
            int i = mutableVector.size;
            if (i > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    obj = objArr[i2];
                    if (((SnapshotStateObserver.ObservedScopeMap) obj).onChanged == function1) {
                        break;
                    } else {
                        i2++;
                    }
                } while (i2 < i);
            }
            obj = null;
            observedScopeMap = (SnapshotStateObserver.ObservedScopeMap) obj;
            if (observedScopeMap == null) {
                ResultKt.beforeCheckcastToFunctionOfArity(1, function1);
                observedScopeMap = new SnapshotStateObserver.ObservedScopeMap(function1);
                mutableVector.add(observedScopeMap);
            }
        }
        boolean z = snapshotStateObserver.isPaused;
        SnapshotStateObserver.ObservedScopeMap observedScopeMap2 = snapshotStateObserver.currentMap;
        try {
            snapshotStateObserver.isPaused = false;
            snapshotStateObserver.currentMap = observedScopeMap;
            observedScopeMap.observe(ownerScope, snapshotStateObserver.readObserver, function0);
        } finally {
            snapshotStateObserver.currentMap = observedScopeMap2;
            snapshotStateObserver.isPaused = z;
        }
    }
}
