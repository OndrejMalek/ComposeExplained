package androidx.compose.runtime;

import _COROUTINE.ArtificialStackFrames;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.snapshots.SnapshotWeakSet;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.compose.runtime.snapshots.StateRecord;
import androidx.compose.ui.platform.AndroidComposeView$viewTreeOwners$2;
import androidx.compose.ui.platform.WeakCache;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class DerivedSnapshotState implements StateObject, State {
    public final Function0 calculation;
    public final SnapshotMutationPolicy policy = null;
    public ResultRecord first = new ResultRecord();

    /* loaded from: classes.dex */
    public final class ResultRecord extends StateRecord {
        public static final Object Unset = new Object();
        public SnapshotWeakSet _dependencies;
        public Object result = Unset;
        public int resultHash;
        public int validSnapshotId;
        public int validSnapshotWriteCount;

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final void assign(StateRecord stateRecord) {
            ResultKt.checkNotNullParameter(stateRecord, "value");
            ResultRecord resultRecord = (ResultRecord) stateRecord;
            this._dependencies = resultRecord._dependencies;
            this.result = resultRecord.result;
            this.resultHash = resultRecord.resultHash;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final StateRecord create() {
            return new ResultRecord();
        }

        public final boolean isValid(DerivedSnapshotState derivedSnapshotState, Snapshot snapshot) {
            boolean z;
            boolean z2;
            ResultKt.checkNotNullParameter(derivedSnapshotState, "derivedState");
            Object obj = SnapshotKt.lock;
            synchronized (obj) {
                z = true;
                if (this.validSnapshotId == snapshot.getId()) {
                    if (this.validSnapshotWriteCount == snapshot.getWriteCount$runtime_release()) {
                        z2 = false;
                    }
                }
                z2 = true;
            }
            if (this.result == Unset || (z2 && this.resultHash != readableHash(derivedSnapshotState, snapshot))) {
                z = false;
            }
            if (z && z2) {
                synchronized (obj) {
                    this.validSnapshotId = snapshot.getId();
                    this.validSnapshotWriteCount = snapshot.getWriteCount$runtime_release();
                }
            }
            return z;
        }

        public final int readableHash(DerivedSnapshotState derivedSnapshotState, Snapshot snapshot) {
            SnapshotWeakSet snapshotWeakSet;
            StateRecord readable;
            ResultKt.checkNotNullParameter(derivedSnapshotState, "derivedState");
            synchronized (SnapshotKt.lock) {
                snapshotWeakSet = this._dependencies;
            }
            int i = 7;
            if (snapshotWeakSet != null) {
                MutableVector derivedStateObservers = ResultKt.derivedStateObservers();
                int i2 = derivedStateObservers.size;
                int i3 = 0;
                if (i2 > 0) {
                    Object[] objArr = derivedStateObservers.content;
                    int i4 = 0;
                    do {
                        ((ComposerImpl$derivedStateObserver$1) ((DerivedStateObserver) objArr[i4])).start(derivedSnapshotState);
                        i4++;
                    } while (i4 < i2);
                }
                try {
                    int i5 = snapshotWeakSet.size;
                    for (int i6 = 0; i6 < i5; i6++) {
                        Object obj = ((Object[]) snapshotWeakSet.hashes)[i6];
                        ResultKt.checkNotNull(obj, "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.IdentityArrayMap");
                        StateObject stateObject = (StateObject) obj;
                        if (((Number) snapshotWeakSet.values[i6]).intValue() == 1) {
                            if (stateObject instanceof DerivedSnapshotState) {
                                DerivedSnapshotState derivedSnapshotState2 = (DerivedSnapshotState) stateObject;
                                ResultRecord resultRecord = derivedSnapshotState2.first;
                                ResultKt.checkNotNullParameter(resultRecord, "r");
                                StateRecord readable2 = SnapshotKt.readable(resultRecord, snapshot.getId(), snapshot.getInvalid$runtime_release());
                                if (readable2 == null) {
                                    SnapshotKt.readError();
                                    throw null;
                                }
                                readable = derivedSnapshotState2.currentRecord((ResultRecord) readable2, snapshot, false, derivedSnapshotState2.calculation);
                            } else {
                                StateRecord firstStateRecord = stateObject.getFirstStateRecord();
                                ResultKt.checkNotNullParameter(firstStateRecord, "r");
                                readable = SnapshotKt.readable(firstStateRecord, snapshot.getId(), snapshot.getInvalid$runtime_release());
                                if (readable == null) {
                                    SnapshotKt.readError();
                                    throw null;
                                }
                            }
                            i = (((i * 31) + System.identityHashCode(readable)) * 31) + readable.snapshotId;
                        }
                    }
                    int i7 = derivedStateObservers.size;
                    if (i7 > 0) {
                        Object[] objArr2 = derivedStateObservers.content;
                        do {
                            ((ComposerImpl$derivedStateObserver$1) ((DerivedStateObserver) objArr2[i3])).done(derivedSnapshotState);
                            i3++;
                        } while (i3 < i7);
                    }
                } catch (Throwable th) {
                    int i8 = derivedStateObservers.size;
                    if (i8 > 0) {
                        Object[] objArr3 = derivedStateObservers.content;
                        do {
                            ((ComposerImpl$derivedStateObserver$1) ((DerivedStateObserver) objArr3[i3])).done(derivedSnapshotState);
                            i3++;
                        } while (i3 < i8);
                    }
                    throw th;
                }
            }
            return i;
        }
    }

    public DerivedSnapshotState(AndroidComposeView$viewTreeOwners$2 androidComposeView$viewTreeOwners$2) {
        this.calculation = androidComposeView$viewTreeOwners$2;
    }

    /* JADX DEBUG: Another duplicated slice has different insns count: {[IGET]}, finally: {[IGET, IF, AGET, CHECK_CAST, CHECK_CAST, INVOKE, ARITH, IGET, IF] complete} */
    public final ResultRecord currentRecord(ResultRecord resultRecord, Snapshot snapshot, boolean z, Function0 function0) {
        MutableVector derivedStateObservers;
        SnapshotMutationPolicy snapshotMutationPolicy;
        int i = 0;
        if (resultRecord.isValid(this, snapshot)) {
            if (z) {
                derivedStateObservers = ResultKt.derivedStateObservers();
                int i2 = derivedStateObservers.size;
                if (i2 > 0) {
                    Object[] objArr = derivedStateObservers.content;
                    int i3 = 0;
                    do {
                        ((ComposerImpl$derivedStateObserver$1) ((DerivedStateObserver) objArr[i3])).start(this);
                        i3++;
                    } while (i3 < i2);
                }
                try {
                    SnapshotWeakSet snapshotWeakSet = resultRecord._dependencies;
                    Integer num = (Integer) SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel.get();
                    int intValue = num != null ? num.intValue() : 0;
                    if (snapshotWeakSet != null) {
                        int i4 = snapshotWeakSet.size;
                        for (int i5 = 0; i5 < i4; i5++) {
                            Object obj = ((Object[]) snapshotWeakSet.hashes)[i5];
                            ResultKt.checkNotNull(obj, "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.IdentityArrayMap");
                            StateObject stateObject = (StateObject) obj;
                            SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel.set(Integer.valueOf(((Number) snapshotWeakSet.values[i5]).intValue() + intValue));
                            Function1 readObserver$runtime_release = snapshot.getReadObserver$runtime_release();
                            if (readObserver$runtime_release != null) {
                                readObserver$runtime_release.invoke(stateObject);
                            }
                        }
                    }
                    SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel.set(Integer.valueOf(intValue));
                    int i6 = derivedStateObservers.size;
                    if (i6 > 0) {
                        Object[] objArr2 = derivedStateObservers.content;
                        do {
                            ((ComposerImpl$derivedStateObserver$1) ((DerivedStateObserver) objArr2[i])).done(this);
                            i++;
                        } while (i < i6);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return resultRecord;
        }
        Integer num2 = (Integer) SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel.get();
        int intValue2 = num2 != null ? num2.intValue() : 0;
        SnapshotWeakSet snapshotWeakSet2 = new SnapshotWeakSet((Object) null);
        derivedStateObservers = ResultKt.derivedStateObservers();
        int i7 = derivedStateObservers.size;
        if (i7 > 0) {
            Object[] objArr3 = derivedStateObservers.content;
            int i8 = 0;
            do {
                ((ComposerImpl$derivedStateObserver$1) ((DerivedStateObserver) objArr3[i8])).start(this);
                i8++;
            } while (i8 < i7);
        }
        try {
            WeakCache weakCache = SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel;
            weakCache.set(Integer.valueOf(intValue2 + 1));
            Object observe = ArtificialStackFrames.observe(new RecomposeScopeImpl$end$1$2(this, snapshotWeakSet2, intValue2), function0);
            weakCache.set(Integer.valueOf(intValue2));
            int i9 = derivedStateObservers.size;
            if (i9 > 0) {
                Object[] objArr4 = derivedStateObservers.content;
                do {
                    ((ComposerImpl$derivedStateObserver$1) ((DerivedStateObserver) objArr4[i])).done(this);
                    i++;
                } while (i < i9);
            }
            synchronized (SnapshotKt.lock) {
                try {
                    Snapshot currentSnapshot = SnapshotKt.currentSnapshot();
                    Object obj2 = resultRecord.result;
                    if (obj2 == ResultRecord.Unset || (snapshotMutationPolicy = this.policy) == null || !snapshotMutationPolicy.equivalent(observe, obj2)) {
                        resultRecord = (ResultRecord) SnapshotKt.newWritableRecord(this.first, this, currentSnapshot);
                        resultRecord._dependencies = snapshotWeakSet2;
                        resultRecord.resultHash = resultRecord.readableHash(this, currentSnapshot);
                        resultRecord.validSnapshotId = snapshot.getId();
                        resultRecord.validSnapshotWriteCount = snapshot.getWriteCount$runtime_release();
                        resultRecord.result = observe;
                    } else {
                        resultRecord._dependencies = snapshotWeakSet2;
                        resultRecord.resultHash = resultRecord.readableHash(this, currentSnapshot);
                        resultRecord.validSnapshotId = snapshot.getId();
                        resultRecord.validSnapshotWriteCount = snapshot.getWriteCount$runtime_release();
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
            if (intValue2 == 0) {
                SnapshotKt.currentSnapshot().notifyObjectsInitialized$runtime_release();
            }
            return resultRecord;
        } finally {
            int i10 = derivedStateObservers.size;
            if (i10 > 0) {
                Object[] objArr5 = derivedStateObservers.content;
                do {
                    ((ComposerImpl$derivedStateObserver$1) ((DerivedStateObserver) objArr5[i])).done(this);
                    i++;
                } while (i < i10);
            }
        }
    }

    public final ResultRecord getCurrentRecord() {
        return currentRecord((ResultRecord) SnapshotKt.current(this.first), SnapshotKt.currentSnapshot(), false, this.calculation);
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final StateRecord getFirstStateRecord() {
        return this.first;
    }

    @Override // androidx.compose.runtime.State
    public final Object getValue() {
        Function1 readObserver$runtime_release = SnapshotKt.currentSnapshot().getReadObserver$runtime_release();
        if (readObserver$runtime_release != null) {
            readObserver$runtime_release.invoke(this);
        }
        return currentRecord((ResultRecord) SnapshotKt.current(this.first), SnapshotKt.currentSnapshot(), true, this.calculation).result;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final void prependStateRecord(StateRecord stateRecord) {
        this.first = (ResultRecord) stateRecord;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DerivedState(value=");
        ResultRecord resultRecord = (ResultRecord) SnapshotKt.current(this.first);
        sb.append(resultRecord.isValid(this, SnapshotKt.currentSnapshot()) ? String.valueOf(resultRecord.result) : "<Not calculated>");
        sb.append(")@");
        sb.append(hashCode());
        return sb.toString();
    }
}
