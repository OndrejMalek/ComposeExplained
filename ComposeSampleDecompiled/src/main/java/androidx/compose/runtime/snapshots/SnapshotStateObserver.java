package androidx.compose.runtime.snapshots;

import _COROUTINE.ArtificialStackFrames;
import androidx.compose.runtime.ComposerImpl$derivedStateObserver$1;
import androidx.compose.runtime.DerivedSnapshotState;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.StructuralEqualityPolicy;
import androidx.compose.runtime.collection.IdentityArrayIntMap;
import androidx.compose.runtime.collection.IdentityArraySet;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.ComposedModifierKt$materialize$result$1;
import androidx.compose.ui.node.BackwardsCompatNodeKt$onDrawCacheReadsChanged$1;
import androidx.compose.ui.platform.AndroidComposeView$focusOwner$1;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.ResultKt;
import kotlin.collections.AbstractMap$toString$1;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.SharingConfig;

/* loaded from: classes.dex */
public final class SnapshotStateObserver {
    public Snapshot$Companion$registerApplyObserver$2 applyUnsubscribe;
    public ObservedScopeMap currentMap;
    public boolean isPaused;
    public final MutableVector observedScopeMaps;
    public final Function1 onChangedExecutor;
    public boolean sendingNotifications;
    public final AtomicReference pendingChanges = new AtomicReference(null);
    public final ComposedModifierKt$materialize$result$1 applyObserver = new ComposedModifierKt$materialize$result$1(5, this);
    public final AbstractMap$toString$1 readObserver = new AbstractMap$toString$1(9, this);

    /* loaded from: classes.dex */
    public final class ObservedScopeMap {
        public Object currentScope;
        public IdentityArrayIntMap currentScopeReads;
        public int currentToken;
        public final SharingConfig dependencyToDerivedStates;
        public int deriveStateScopeCount;
        public final ComposerImpl$derivedStateObserver$1 derivedStateObserver;
        public final IdentityArraySet invalidated;
        public final Function1 onChanged;
        public final HashMap recordedDerivedStateValues;
        public final SnapshotWeakSet scopeToValues;
        public final MutableVector statesToReread;
        public final SharingConfig valueToScopes;

        /* JADX WARN: Type inference failed for: r2v5, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
        public ObservedScopeMap(Function1 function1) {
            ResultKt.checkNotNullParameter(function1, "onChanged");
            this.onChanged = function1;
            this.currentToken = -1;
            this.valueToScopes = new SharingConfig();
            this.scopeToValues = new SnapshotWeakSet((Object) null);
            this.invalidated = new IdentityArraySet();
            ?? obj = new Object();
            obj.content = new DerivedSnapshotState[16];
            obj.size = 0;
            this.statesToReread = obj;
            this.derivedStateObserver = new ComposerImpl$derivedStateObserver$1(1, this);
            this.dependencyToDerivedStates = new SharingConfig();
            this.recordedDerivedStateValues = new HashMap();
        }

        public final void observe(Object obj, AbstractMap$toString$1 abstractMap$toString$1, Function0 function0) {
            ResultKt.checkNotNullParameter(obj, "scope");
            ResultKt.checkNotNullParameter(abstractMap$toString$1, "readObserver");
            Object obj2 = this.currentScope;
            IdentityArrayIntMap identityArrayIntMap = this.currentScopeReads;
            int i = this.currentToken;
            this.currentScope = obj;
            this.currentScopeReads = (IdentityArrayIntMap) this.scopeToValues.get(obj);
            if (this.currentToken == -1) {
                this.currentToken = SnapshotKt.currentSnapshot().getId();
            }
            ComposerImpl$derivedStateObserver$1 composerImpl$derivedStateObserver$1 = this.derivedStateObserver;
            MutableVector derivedStateObservers = ResultKt.derivedStateObservers();
            boolean z = true;
            try {
                derivedStateObservers.add(composerImpl$derivedStateObserver$1);
                ArtificialStackFrames.observe(abstractMap$toString$1, function0);
                derivedStateObservers.removeAt(derivedStateObservers.size - 1);
                Object obj3 = this.currentScope;
                ResultKt.checkNotNull(obj3);
                int i2 = this.currentToken;
                IdentityArrayIntMap identityArrayIntMap2 = this.currentScopeReads;
                if (identityArrayIntMap2 != null) {
                    Object[] objArr = identityArrayIntMap2.keys;
                    int[] iArr = identityArrayIntMap2.values;
                    int i3 = identityArrayIntMap2.size;
                    int i4 = 0;
                    int i5 = 0;
                    while (i4 < i3) {
                        Object obj4 = objArr[i4];
                        ResultKt.checkNotNull(obj4, "null cannot be cast to non-null type kotlin.Any");
                        int i6 = iArr[i4];
                        boolean z2 = i6 != i2 ? z : false;
                        if (z2) {
                            SharingConfig sharingConfig = this.valueToScopes;
                            sharingConfig.remove(obj4, obj3);
                            if ((obj4 instanceof DerivedSnapshotState) && !sharingConfig.contains(obj4)) {
                                this.dependencyToDerivedStates.removeScope(obj4);
                                this.recordedDerivedStateValues.remove(obj4);
                            }
                        }
                        if (!z2) {
                            if (i5 != i4) {
                                objArr[i5] = obj4;
                                iArr[i5] = i6;
                            }
                            i5++;
                        }
                        i4++;
                        z = true;
                    }
                    for (int i7 = i5; i7 < i3; i7++) {
                        objArr[i7] = null;
                    }
                    identityArrayIntMap2.size = i5;
                }
                this.currentScope = obj2;
                this.currentScopeReads = identityArrayIntMap;
                this.currentToken = i;
            } catch (Throwable th) {
                derivedStateObservers.removeAt(derivedStateObservers.size - 1);
                throw th;
            }
        }

        public final boolean recordInvalidation(Set set) {
            boolean z;
            int find;
            int find2;
            HashMap hashMap = this.recordedDerivedStateValues;
            boolean z2 = set instanceof IdentityArraySet;
            StructuralEqualityPolicy structuralEqualityPolicy = StructuralEqualityPolicy.INSTANCE;
            MutableVector mutableVector = this.statesToReread;
            SharingConfig sharingConfig = this.dependencyToDerivedStates;
            SharingConfig sharingConfig2 = this.valueToScopes;
            IdentityArraySet identityArraySet = this.invalidated;
            if (z2) {
                IdentityArraySet identityArraySet2 = (IdentityArraySet) set;
                Object[] objArr = identityArraySet2.values;
                int i = identityArraySet2.size;
                int i2 = 0;
                z = false;
                while (i2 < i) {
                    Object obj = objArr[i2];
                    ResultKt.checkNotNull(obj, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                    if (sharingConfig.contains(obj) && (find2 = sharingConfig.find(obj)) >= 0) {
                        IdentityArraySet scopeSetAt = sharingConfig.scopeSetAt(find2);
                        Object[] objArr2 = scopeSetAt.values;
                        int i3 = scopeSetAt.size;
                        int i4 = 0;
                        while (i4 < i3) {
                            int i5 = i;
                            Object obj2 = objArr2[i4];
                            ResultKt.checkNotNull(obj2, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                            DerivedSnapshotState derivedSnapshotState = (DerivedSnapshotState) obj2;
                            Object[] objArr3 = objArr;
                            Object obj3 = hashMap.get(derivedSnapshotState);
                            StructuralEqualityPolicy structuralEqualityPolicy2 = structuralEqualityPolicy;
                            SnapshotMutationPolicy snapshotMutationPolicy = derivedSnapshotState.policy;
                            Object[] objArr4 = objArr2;
                            if (snapshotMutationPolicy == null) {
                                snapshotMutationPolicy = structuralEqualityPolicy2;
                            }
                            if (snapshotMutationPolicy.equivalent(derivedSnapshotState.getCurrentRecord().result, obj3)) {
                                mutableVector.add(derivedSnapshotState);
                            } else {
                                int find3 = sharingConfig2.find(derivedSnapshotState);
                                if (find3 >= 0) {
                                    IdentityArraySet scopeSetAt2 = sharingConfig2.scopeSetAt(find3);
                                    Object[] objArr5 = scopeSetAt2.values;
                                    int i6 = scopeSetAt2.size;
                                    int i7 = 0;
                                    while (i7 < i6) {
                                        Object obj4 = objArr5[i7];
                                        ResultKt.checkNotNull(obj4, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                                        identityArraySet.add(obj4);
                                        i7++;
                                        z = true;
                                    }
                                }
                            }
                            i4++;
                            i = i5;
                            objArr = objArr3;
                            structuralEqualityPolicy = structuralEqualityPolicy2;
                            objArr2 = objArr4;
                        }
                    }
                    int i8 = i;
                    Object[] objArr6 = objArr;
                    StructuralEqualityPolicy structuralEqualityPolicy3 = structuralEqualityPolicy;
                    int find4 = sharingConfig2.find(obj);
                    if (find4 >= 0) {
                        IdentityArraySet scopeSetAt3 = sharingConfig2.scopeSetAt(find4);
                        Object[] objArr7 = scopeSetAt3.values;
                        int i9 = scopeSetAt3.size;
                        int i10 = 0;
                        while (i10 < i9) {
                            Object obj5 = objArr7[i10];
                            ResultKt.checkNotNull(obj5, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                            identityArraySet.add(obj5);
                            i10++;
                            z = true;
                        }
                    }
                    i2++;
                    i = i8;
                    objArr = objArr6;
                    structuralEqualityPolicy = structuralEqualityPolicy3;
                }
            } else {
                Iterator it = set.iterator();
                z = false;
                while (it.hasNext()) {
                    Object next = it.next();
                    if (sharingConfig.contains(next) && (find = sharingConfig.find(next)) >= 0) {
                        IdentityArraySet scopeSetAt4 = sharingConfig.scopeSetAt(find);
                        Object[] objArr8 = scopeSetAt4.values;
                        int i11 = scopeSetAt4.size;
                        int i12 = 0;
                        while (i12 < i11) {
                            Object obj6 = objArr8[i12];
                            ResultKt.checkNotNull(obj6, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                            DerivedSnapshotState derivedSnapshotState2 = (DerivedSnapshotState) obj6;
                            Object obj7 = hashMap.get(derivedSnapshotState2);
                            SnapshotMutationPolicy snapshotMutationPolicy2 = derivedSnapshotState2.policy;
                            Iterator it2 = it;
                            if (snapshotMutationPolicy2 == null) {
                                snapshotMutationPolicy2 = structuralEqualityPolicy;
                            }
                            if (snapshotMutationPolicy2.equivalent(derivedSnapshotState2.getCurrentRecord().result, obj7)) {
                                mutableVector.add(derivedSnapshotState2);
                            } else {
                                int find5 = sharingConfig2.find(derivedSnapshotState2);
                                if (find5 >= 0) {
                                    IdentityArraySet scopeSetAt5 = sharingConfig2.scopeSetAt(find5);
                                    Object[] objArr9 = scopeSetAt5.values;
                                    int i13 = scopeSetAt5.size;
                                    int i14 = 0;
                                    while (i14 < i13) {
                                        Object obj8 = objArr9[i14];
                                        ResultKt.checkNotNull(obj8, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                                        identityArraySet.add(obj8);
                                        i14++;
                                        z = true;
                                    }
                                }
                            }
                            i12++;
                            it = it2;
                        }
                    }
                    Iterator it3 = it;
                    int find6 = sharingConfig2.find(next);
                    if (find6 >= 0) {
                        IdentityArraySet scopeSetAt6 = sharingConfig2.scopeSetAt(find6);
                        Object[] objArr10 = scopeSetAt6.values;
                        int i15 = scopeSetAt6.size;
                        int i16 = 0;
                        while (i16 < i15) {
                            Object obj9 = objArr10[i16];
                            ResultKt.checkNotNull(obj9, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                            identityArraySet.add(obj9);
                            i16++;
                            z = true;
                        }
                    }
                    it = it3;
                }
            }
            if (mutableVector.isNotEmpty()) {
                int i17 = mutableVector.size;
                if (i17 > 0) {
                    Object[] objArr11 = mutableVector.content;
                    int i18 = 0;
                    do {
                        DerivedSnapshotState derivedSnapshotState3 = (DerivedSnapshotState) objArr11[i18];
                        ResultKt.checkNotNullParameter(derivedSnapshotState3, "derivedState");
                        int id = SnapshotKt.currentSnapshot().getId();
                        int find7 = sharingConfig2.find(derivedSnapshotState3);
                        if (find7 >= 0) {
                            IdentityArraySet scopeSetAt7 = sharingConfig2.scopeSetAt(find7);
                            Object[] objArr12 = scopeSetAt7.values;
                            int i19 = scopeSetAt7.size;
                            for (int i20 = 0; i20 < i19; i20++) {
                                Object obj10 = objArr12[i20];
                                ResultKt.checkNotNull(obj10, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                                SnapshotWeakSet snapshotWeakSet = this.scopeToValues;
                                IdentityArrayIntMap identityArrayIntMap = (IdentityArrayIntMap) snapshotWeakSet.get(obj10);
                                if (identityArrayIntMap == null) {
                                    identityArrayIntMap = new IdentityArrayIntMap();
                                    snapshotWeakSet.set(obj10, identityArrayIntMap);
                                }
                                recordRead(derivedSnapshotState3, id, obj10, identityArrayIntMap);
                            }
                        }
                        i18++;
                    } while (i18 < i17);
                }
                mutableVector.clear();
            }
            return z;
        }

        public final void recordRead(Object obj, int i, Object obj2, IdentityArrayIntMap identityArrayIntMap) {
            Object[] objArr;
            if (this.deriveStateScopeCount > 0) {
                return;
            }
            int add = identityArrayIntMap.add(i, obj);
            if ((obj instanceof DerivedSnapshotState) && add != i) {
                DerivedSnapshotState.ResultRecord currentRecord = ((DerivedSnapshotState) obj).getCurrentRecord();
                this.recordedDerivedStateValues.put(obj, currentRecord.result);
                SnapshotWeakSet snapshotWeakSet = currentRecord._dependencies;
                if (snapshotWeakSet == null || (objArr = (Object[]) snapshotWeakSet.hashes) == null) {
                    objArr = new Object[0];
                }
                SharingConfig sharingConfig = this.dependencyToDerivedStates;
                sharingConfig.removeScope(obj);
                for (Object obj3 : objArr) {
                    if (obj3 == null) {
                        break;
                    }
                    sharingConfig.add(obj3, obj);
                }
            }
            if (add == -1) {
                this.valueToScopes.add(obj, obj2);
            }
        }

        public final void removeScopeIf() {
            BackwardsCompatNodeKt$onDrawCacheReadsChanged$1 backwardsCompatNodeKt$onDrawCacheReadsChanged$1 = BackwardsCompatNodeKt$onDrawCacheReadsChanged$1.INSTANCE$11;
            SnapshotWeakSet snapshotWeakSet = this.scopeToValues;
            int i = snapshotWeakSet.size;
            int i2 = 0;
            for (int i3 = 0; i3 < i; i3++) {
                Object obj = ((Object[]) snapshotWeakSet.hashes)[i3];
                ResultKt.checkNotNull(obj, "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.IdentityArrayMap");
                IdentityArrayIntMap identityArrayIntMap = (IdentityArrayIntMap) snapshotWeakSet.values[i3];
                Boolean bool = (Boolean) backwardsCompatNodeKt$onDrawCacheReadsChanged$1.invoke(obj);
                if (bool.booleanValue()) {
                    Object[] objArr = identityArrayIntMap.keys;
                    int[] iArr = identityArrayIntMap.values;
                    int i4 = identityArrayIntMap.size;
                    for (int i5 = 0; i5 < i4; i5++) {
                        Object obj2 = objArr[i5];
                        ResultKt.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Any");
                        int i6 = iArr[i5];
                        SharingConfig sharingConfig = this.valueToScopes;
                        sharingConfig.remove(obj2, obj);
                        if ((obj2 instanceof DerivedSnapshotState) && !sharingConfig.contains(obj2)) {
                            this.dependencyToDerivedStates.removeScope(obj2);
                            this.recordedDerivedStateValues.remove(obj2);
                        }
                    }
                }
                if (!bool.booleanValue()) {
                    if (i2 != i3) {
                        ((Object[]) snapshotWeakSet.hashes)[i2] = obj;
                        Object[] objArr2 = snapshotWeakSet.values;
                        objArr2[i2] = objArr2[i3];
                    }
                    i2++;
                }
            }
            int i7 = snapshotWeakSet.size;
            if (i7 > i2) {
                for (int i8 = i2; i8 < i7; i8++) {
                    ((Object[]) snapshotWeakSet.hashes)[i8] = null;
                    snapshotWeakSet.values[i8] = null;
                }
                snapshotWeakSet.size = i2;
            }
        }
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public SnapshotStateObserver(AndroidComposeView$focusOwner$1 androidComposeView$focusOwner$1) {
        this.onChangedExecutor = androidComposeView$focusOwner$1;
        ?? obj = new Object();
        obj.content = new ObservedScopeMap[16];
        obj.size = 0;
        this.observedScopeMaps = obj;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v4, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean access$drainChanges(SnapshotStateObserver snapshotStateObserver) {
        boolean z;
        Set set;
        synchronized (snapshotStateObserver.observedScopeMaps) {
            z = snapshotStateObserver.sendingNotifications;
        }
        if (z) {
            return false;
        }
        boolean z2 = false;
        while (true) {
            AtomicReference atomicReference = snapshotStateObserver.pendingChanges;
            Object obj = atomicReference.get();
            Set set2 = null;
            r4 = null;
            List list = null;
            if (obj != null) {
                if (obj instanceof Set) {
                    set = (Set) obj;
                } else {
                    if (!(obj instanceof List)) {
                        EffectsKt.composeRuntimeError("Unexpected notification");
                        throw null;
                    }
                    List list2 = (List) obj;
                    set = (Set) list2.get(0);
                    if (list2.size() == 2) {
                        list = list2.get(1);
                    } else if (list2.size() > 2) {
                        list = list2.subList(1, list2.size());
                    }
                }
                List list3 = list;
                while (!atomicReference.compareAndSet(obj, list3)) {
                    if (atomicReference.get() != obj) {
                        break;
                    }
                }
                set2 = set;
            }
            if (set2 == null) {
                return z2;
            }
            synchronized (snapshotStateObserver.observedScopeMaps) {
                MutableVector mutableVector = snapshotStateObserver.observedScopeMaps;
                int i = mutableVector.size;
                if (i > 0) {
                    Object[] objArr = mutableVector.content;
                    int i2 = 0;
                    do {
                        z2 = ((ObservedScopeMap) objArr[i2]).recordInvalidation(set2) || z2;
                        i2++;
                    } while (i2 < i);
                }
            }
        }
    }
}
