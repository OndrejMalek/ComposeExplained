package androidx.compose.runtime;

import _COROUTINE.ArtificialStackFrames;
import android.util.Log;
import androidx.compose.runtime.collection.IdentityArraySet;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.PersistentOrderedSet;
import androidx.compose.runtime.snapshots.MutableSnapshot;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotApplyResult$Failure;
import androidx.compose.ui.draw.DrawResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobImpl;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

/* loaded from: classes.dex */
public final class Recomposer extends CompositionContext {
    public static final AtomicReference _hotReloadEnabled;
    public static final StateFlowImpl _runningRecomposers;
    public final StateFlowImpl _state;
    public final BroadcastFrameClock broadcastFrameClock;
    public Throwable closeCause;
    public final ArrayList compositionInvalidations;
    public final LinkedHashMap compositionValueStatesAvailable;
    public final ArrayList compositionValuesAwaitingInsert;
    public final LinkedHashMap compositionValuesRemoved;
    public final ArrayList compositionsAwaitingApply;
    public Set compositionsRemoved;
    public final CoroutineContext effectCoroutineContext;
    public final JobImpl effectJob;
    public DrawResult errorState;
    public ArrayList failedCompositions;
    public boolean frameClockPaused;
    public final ArrayList knownCompositions;
    public final Stack recomposerInfo;
    public Job runnerJob;
    public IdentityArraySet snapshotInvalidations;
    public final Object stateLock;
    public CancellableContinuation workContinuation;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes.dex */
    public final class State {
        public static final /* synthetic */ State[] $VALUES;
        public static final State Idle;
        public static final State Inactive;
        public static final State InactivePendingWork;
        public static final State PendingWork;
        public static final State ShutDown;
        public static final State ShuttingDown;

        /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Enum, androidx.compose.runtime.Recomposer$State] */
        /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Enum, androidx.compose.runtime.Recomposer$State] */
        /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Enum, androidx.compose.runtime.Recomposer$State] */
        /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Enum, androidx.compose.runtime.Recomposer$State] */
        /* JADX WARN: Type inference failed for: r4v2, types: [java.lang.Enum, androidx.compose.runtime.Recomposer$State] */
        /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.Enum, androidx.compose.runtime.Recomposer$State] */
        static {
            ?? r0 = new Enum("ShutDown", 0);
            ShutDown = r0;
            ?? r1 = new Enum("ShuttingDown", 1);
            ShuttingDown = r1;
            ?? r2 = new Enum("Inactive", 2);
            Inactive = r2;
            ?? r3 = new Enum("InactivePendingWork", 3);
            InactivePendingWork = r3;
            ?? r4 = new Enum("Idle", 4);
            Idle = r4;
            ?? r5 = new Enum("PendingWork", 5);
            PendingWork = r5;
            $VALUES = new State[]{r0, r1, r2, r3, r4, r5};
        }

        public static State valueOf(String str) {
            return (State) Enum.valueOf(State.class, str);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    static {
        Object obj = PersistentOrderedSet.EMPTY;
        if (obj == null) {
            obj = NullSurrogateKt.NULL;
        }
        _runningRecomposers = new StateFlowImpl(obj);
        _hotReloadEnabled = new AtomicReference(Boolean.FALSE);
    }

    public Recomposer(CoroutineContext coroutineContext) {
        ResultKt.checkNotNullParameter(coroutineContext, "effectCoroutineContext");
        BroadcastFrameClock broadcastFrameClock = new BroadcastFrameClock(new Pending$keyMap$2(1, this));
        this.broadcastFrameClock = broadcastFrameClock;
        this.stateLock = new Object();
        this.knownCompositions = new ArrayList();
        this.snapshotInvalidations = new IdentityArraySet();
        this.compositionInvalidations = new ArrayList();
        this.compositionsAwaitingApply = new ArrayList();
        this.compositionValuesAwaitingInsert = new ArrayList();
        this.compositionValuesRemoved = new LinkedHashMap();
        this.compositionValueStatesAvailable = new LinkedHashMap();
        this._state = new StateFlowImpl(State.Inactive);
        JobImpl jobImpl = new JobImpl((Job) coroutineContext.get(Job.Key.$$INSTANCE));
        jobImpl.invokeOnCompletion(false, true, new Recomposer$effectJob$1$1(0, this));
        this.effectJob = jobImpl;
        this.effectCoroutineContext = coroutineContext.plus(broadcastFrameClock).plus(jobImpl);
        this.recomposerInfo = new Stack(3, this);
    }

    public static final CompositionImpl access$performRecompose(Recomposer recomposer, CompositionImpl compositionImpl, IdentityArraySet identityArraySet) {
        ComposerImpl composerImpl = compositionImpl.composer;
        if (composerImpl.isComposing || compositionImpl.disposed) {
            return null;
        }
        Set set = recomposer.compositionsRemoved;
        int i = 1;
        if (set != null && set.contains(compositionImpl)) {
            return null;
        }
        MutableSnapshot takeMutableSnapshot = ArtificialStackFrames.takeMutableSnapshot(new Recomposer$effectJob$1$1(i, compositionImpl), new Latch$await$2$2(compositionImpl, i, identityArraySet));
        try {
            Snapshot makeCurrent = takeMutableSnapshot.makeCurrent();
            try {
                if (identityArraySet.isNotEmpty()) {
                    composerImpl.getClass();
                    if (!(!composerImpl.isComposing)) {
                        EffectsKt.composeRuntimeError("Preparing a composition while composing is not supported".toString());
                        throw null;
                    }
                    composerImpl.isComposing = true;
                    try {
                        Object[] objArr = identityArraySet.values;
                        int i2 = identityArraySet.size;
                        for (int i3 = 0; i3 < i2; i3++) {
                            Object obj = objArr[i3];
                            ResultKt.checkNotNull(obj, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                            compositionImpl.recordWriteOf(obj);
                        }
                        composerImpl.isComposing = false;
                    } catch (Throwable th) {
                        composerImpl.isComposing = false;
                        throw th;
                    }
                }
                if (!compositionImpl.recompose()) {
                    compositionImpl = null;
                }
                return compositionImpl;
            } finally {
                Snapshot.restoreCurrent(makeCurrent);
            }
        } finally {
            applyAndCheck(takeMutableSnapshot);
        }
    }

    /* JADX DEBUG: Finally have unexpected throw blocks count: 2, expect 1 */
    public static final boolean access$recordComposerModifications(Recomposer recomposer) {
        ArrayList mutableList;
        boolean z;
        synchronized (recomposer.stateLock) {
            if (recomposer.snapshotInvalidations.isEmpty()) {
                z = (recomposer.compositionInvalidations.isEmpty() ^ true) || recomposer.getHasBroadcastFrameClockAwaitersLocked();
            } else {
                IdentityArraySet identityArraySet = recomposer.snapshotInvalidations;
                recomposer.snapshotInvalidations = new IdentityArraySet();
                synchronized (recomposer.stateLock) {
                    mutableList = CollectionsKt___CollectionsKt.toMutableList(recomposer.knownCompositions);
                }
                try {
                    int size = mutableList.size();
                    for (int i = 0; i < size; i++) {
                        ((CompositionImpl) mutableList.get(i)).recordModificationsOf(identityArraySet);
                        if (((State) recomposer._state.getValue()).compareTo(State.ShuttingDown) <= 0) {
                            break;
                        }
                    }
                    recomposer.snapshotInvalidations = new IdentityArraySet();
                    synchronized (recomposer.stateLock) {
                        if (recomposer.deriveStateLocked() != null) {
                            throw new IllegalStateException("called outside of runRecomposeAndApplyChanges".toString());
                        }
                        z = (recomposer.compositionInvalidations.isEmpty() ^ true) || recomposer.getHasBroadcastFrameClockAwaitersLocked();
                    }
                } catch (Throwable th) {
                    synchronized (recomposer.stateLock) {
                        recomposer.snapshotInvalidations.addAll((Collection) identityArraySet);
                        throw th;
                    }
                }
            }
        }
        return z;
    }

    public static void applyAndCheck(MutableSnapshot mutableSnapshot) {
        try {
            if (mutableSnapshot.apply() instanceof SnapshotApplyResult$Failure) {
                throw new IllegalStateException("Unsupported concurrent change during composition. A state object was modified by composition as well as being modified outside composition.".toString());
            }
        } finally {
            mutableSnapshot.dispose();
        }
    }

    public final void cancel() {
        synchronized (this.stateLock) {
            if (((State) this._state.getValue()).compareTo(State.Idle) >= 0) {
                this._state.setValue(State.ShuttingDown);
            }
        }
        this.effectJob.cancel(null);
    }

    public final CancellableContinuation deriveStateLocked() {
        StateFlowImpl stateFlowImpl = this._state;
        int compareTo = ((State) stateFlowImpl.getValue()).compareTo(State.ShuttingDown);
        ArrayList arrayList = this.compositionValuesAwaitingInsert;
        ArrayList arrayList2 = this.compositionsAwaitingApply;
        ArrayList arrayList3 = this.compositionInvalidations;
        if (compareTo <= 0) {
            this.knownCompositions.clear();
            this.snapshotInvalidations = new IdentityArraySet();
            arrayList3.clear();
            arrayList2.clear();
            arrayList.clear();
            this.failedCompositions = null;
            CancellableContinuation cancellableContinuation = this.workContinuation;
            if (cancellableContinuation != null) {
                cancellableContinuation.cancel(null);
            }
            this.workContinuation = null;
            this.errorState = null;
            return null;
        }
        DrawResult drawResult = this.errorState;
        State state = State.PendingWork;
        State state2 = State.Inactive;
        if (drawResult == null) {
            if (this.runnerJob == null) {
                this.snapshotInvalidations = new IdentityArraySet();
                arrayList3.clear();
                if (getHasBroadcastFrameClockAwaitersLocked()) {
                    state2 = State.InactivePendingWork;
                }
            } else {
                state2 = ((arrayList3.isEmpty() ^ true) || this.snapshotInvalidations.isNotEmpty() || (arrayList2.isEmpty() ^ true) || (arrayList.isEmpty() ^ true) || getHasBroadcastFrameClockAwaitersLocked()) ? state : State.Idle;
            }
        }
        stateFlowImpl.setValue(state2);
        if (state2 != state) {
            return null;
        }
        CancellableContinuation cancellableContinuation2 = this.workContinuation;
        this.workContinuation = null;
        return cancellableContinuation2;
    }

    public final boolean getHasBroadcastFrameClockAwaitersLocked() {
        boolean z;
        if (!this.frameClockPaused) {
            BroadcastFrameClock broadcastFrameClock = this.broadcastFrameClock;
            synchronized (broadcastFrameClock.lock) {
                z = !broadcastFrameClock.awaiters.isEmpty();
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final boolean getHasSchedulingWork() {
        boolean z;
        synchronized (this.stateLock) {
            z = true;
            if (!this.snapshotInvalidations.isNotEmpty() && !(!this.compositionInvalidations.isEmpty())) {
                if (!getHasBroadcastFrameClockAwaitersLocked()) {
                    z = false;
                }
            }
        }
        return z;
    }

    /* JADX WARN: Type inference failed for: r4v8, types: [java.lang.Object, androidx.compose.ui.draw.DrawResult] */
    public final void processCompositionError(Exception exc, CompositionImpl compositionImpl) {
        Object obj = _hotReloadEnabled.get();
        ResultKt.checkNotNullExpressionValue(obj, "_hotReloadEnabled.get()");
        if (!((Boolean) obj).booleanValue()) {
            throw exc;
        }
        if (exc instanceof ComposeRuntimeError) {
            throw exc;
        }
        synchronized (this.stateLock) {
            try {
                Log.e("ComposeInternal", "Error was captured in composition while live edit was enabled.", exc);
                this.compositionsAwaitingApply.clear();
                this.compositionInvalidations.clear();
                this.snapshotInvalidations = new IdentityArraySet();
                this.compositionValuesAwaitingInsert.clear();
                this.compositionValuesRemoved.clear();
                this.compositionValueStatesAvailable.clear();
                this.errorState = new Object();
                if (compositionImpl != null) {
                    ArrayList arrayList = this.failedCompositions;
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        this.failedCompositions = arrayList;
                    }
                    if (!arrayList.contains(compositionImpl)) {
                        arrayList.add(compositionImpl);
                    }
                    this.knownCompositions.remove(compositionImpl);
                }
                deriveStateLocked();
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
