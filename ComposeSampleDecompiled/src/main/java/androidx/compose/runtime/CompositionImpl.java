package androidx.compose.runtime;

import _COROUTINE.ArtificialStackFrames;
import android.os.Trace;
import androidx.compose.runtime.Recomposer;
import androidx.compose.runtime.collection.IdentityArrayIntMap;
import androidx.compose.runtime.collection.IdentityArraySet;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.snapshots.MutableSnapshot;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.snapshots.SnapshotWeakSet;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.UiApplier;
import androidx.compose.ui.platform.AndroidComposeView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.flow.SharingConfig;

/* loaded from: classes.dex */
public final class CompositionImpl implements RecomposeScopeOwner, Composition {
    public final HashSet abandonSet;
    public final Applier applier;
    public final ArrayList changes;
    public final ComposerImpl composer;
    public final HashSet conditionallyInvalidatedScopes;
    public final SharingConfig derivedStates;
    public boolean disposed;
    public SnapshotWeakSet invalidations;
    public final ArrayList lateChanges;
    public final SharingConfig observations;
    public final SharingConfig observationsProcessed;
    public final CompositionContext parent;
    public boolean pendingInvalidScopes;
    public final SlotTable slotTable;
    public final AtomicReference pendingModifications = new AtomicReference(null);
    public final Object lock = new Object();

    /* loaded from: classes.dex */
    public final class RememberEventDispatcher {
        public final Set abandoning;
        public final ArrayList forgetting;
        public ArrayList releasing;
        public final ArrayList remembering;
        public final ArrayList sideEffects;

        public RememberEventDispatcher(HashSet hashSet) {
            ResultKt.checkNotNullParameter(hashSet, "abandoning");
            this.abandoning = hashSet;
            this.remembering = new ArrayList();
            this.forgetting = new ArrayList();
            this.sideEffects = new ArrayList();
        }

        public final void dispatchAbandons() {
            Set set = this.abandoning;
            if (!set.isEmpty()) {
                Trace.beginSection("Compose:abandons");
                try {
                    Iterator it = set.iterator();
                    while (it.hasNext()) {
                        RememberObserver rememberObserver = (RememberObserver) it.next();
                        it.remove();
                        rememberObserver.onAbandoned();
                    }
                } finally {
                    Trace.endSection();
                }
            }
        }

        public final void dispatchRememberObservers() {
            ArrayList arrayList = this.forgetting;
            boolean z = !arrayList.isEmpty();
            Set set = this.abandoning;
            if (z) {
                Trace.beginSection("Compose:onForgotten");
                try {
                    for (int size = arrayList.size() - 1; -1 < size; size--) {
                        RememberObserver rememberObserver = (RememberObserver) arrayList.get(size);
                        if (!set.contains(rememberObserver)) {
                            rememberObserver.onForgotten();
                        }
                    }
                } finally {
                }
            }
            ArrayList arrayList2 = this.remembering;
            if (!arrayList2.isEmpty()) {
                Trace.beginSection("Compose:onRemembered");
                try {
                    int size2 = arrayList2.size();
                    for (int i = 0; i < size2; i++) {
                        RememberObserver rememberObserver2 = (RememberObserver) arrayList2.get(i);
                        set.remove(rememberObserver2);
                        rememberObserver2.onRemembered();
                    }
                } finally {
                }
            }
            ArrayList arrayList3 = this.releasing;
            if (arrayList3 == null || arrayList3.isEmpty()) {
                return;
            }
            Trace.beginSection("Compose:releases");
            try {
                for (int size3 = arrayList3.size() - 1; -1 < size3; size3--) {
                    NodeChain nodeChain = ((LayoutNode) ((ComposeNodeLifecycleCallback) arrayList3.get(size3))).nodes;
                    NodeCoordinator nodeCoordinator = nodeChain.innerCoordinator.wrapped;
                    for (NodeCoordinator nodeCoordinator2 = nodeChain.outerCoordinator; !ResultKt.areEqual(nodeCoordinator2, nodeCoordinator) && nodeCoordinator2 != null; nodeCoordinator2 = nodeCoordinator2.wrapped) {
                        nodeCoordinator2.released = true;
                        if (nodeCoordinator2.layer != null) {
                            nodeCoordinator2.updateLayerBlock(null, false);
                        }
                    }
                }
                Trace.endSection();
                arrayList3.clear();
            } finally {
            }
        }

        public final void forgetting(RememberObserver rememberObserver) {
            ResultKt.checkNotNullParameter(rememberObserver, "instance");
            ArrayList arrayList = this.remembering;
            int lastIndexOf = arrayList.lastIndexOf(rememberObserver);
            if (lastIndexOf < 0) {
                this.forgetting.add(rememberObserver);
            } else {
                arrayList.remove(lastIndexOf);
                this.abandoning.remove(rememberObserver);
            }
        }

        public final void remembering(RememberObserver rememberObserver) {
            ResultKt.checkNotNullParameter(rememberObserver, "instance");
            ArrayList arrayList = this.forgetting;
            int lastIndexOf = arrayList.lastIndexOf(rememberObserver);
            if (lastIndexOf < 0) {
                this.remembering.add(rememberObserver);
            } else {
                arrayList.remove(lastIndexOf);
                this.abandoning.remove(rememberObserver);
            }
        }
    }

    public CompositionImpl(CompositionContext compositionContext, UiApplier uiApplier) {
        this.parent = compositionContext;
        this.applier = uiApplier;
        HashSet hashSet = new HashSet();
        this.abandonSet = hashSet;
        SlotTable slotTable = new SlotTable();
        this.slotTable = slotTable;
        this.observations = new SharingConfig();
        this.conditionallyInvalidatedScopes = new HashSet();
        this.derivedStates = new SharingConfig();
        ArrayList arrayList = new ArrayList();
        this.changes = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.lateChanges = arrayList2;
        this.observationsProcessed = new SharingConfig();
        this.invalidations = new SnapshotWeakSet((Object) null);
        this.composer = new ComposerImpl(uiApplier, compositionContext, slotTable, hashSet, arrayList, arrayList2, this);
        boolean z = compositionContext instanceof Recomposer;
        int i = ComposableSingletons$CompositionKt.$r8$clinit;
    }

    public final void abandonChanges() {
        this.pendingModifications.set(null);
        this.changes.clear();
        this.lateChanges.clear();
        this.abandonSet.clear();
    }

    public final HashSet addPendingInvalidationsLocked(HashSet hashSet, Object obj, boolean z) {
        SharingConfig sharingConfig = this.observations;
        int find = sharingConfig.find(obj);
        if (find >= 0) {
            IdentityArraySet scopeSetAt = sharingConfig.scopeSetAt(find);
            Object[] objArr = scopeSetAt.values;
            int i = scopeSetAt.size;
            for (int i2 = 0; i2 < i; i2++) {
                Object obj2 = objArr[i2];
                ResultKt.checkNotNull(obj2, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) obj2;
                if (!this.observationsProcessed.remove(obj, recomposeScopeImpl) && recomposeScopeImpl.invalidateForResult(obj) != 1) {
                    if (recomposeScopeImpl.trackedDependencies != null && !z) {
                        this.conditionallyInvalidatedScopes.add(recomposeScopeImpl);
                    } else {
                        if (hashSet == null) {
                            hashSet = new HashSet();
                        }
                        hashSet.add(recomposeScopeImpl);
                    }
                }
            }
        }
        return hashSet;
    }

    /* JADX DEBUG: Finally have unexpected throw blocks count: 4, expect 1 */
    public final void applyChanges() {
        synchronized (this.lock) {
            try {
                applyChangesInLocked(this.changes);
                drainPendingModificationsLocked();
            } catch (Throwable th) {
                try {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            HashSet hashSet = this.abandonSet;
                            ResultKt.checkNotNullParameter(hashSet, "abandoning");
                            new ArrayList();
                            new ArrayList();
                            new ArrayList();
                            if (!hashSet.isEmpty()) {
                                Trace.beginSection("Compose:abandons");
                                try {
                                    Iterator it = hashSet.iterator();
                                    while (it.hasNext()) {
                                        RememberObserver rememberObserver = (RememberObserver) it.next();
                                        it.remove();
                                        rememberObserver.onAbandoned();
                                    }
                                    Trace.endSection();
                                } catch (Throwable th2) {
                                    Trace.endSection();
                                    throw th2;
                                }
                            }
                        }
                        throw th;
                    } catch (Exception e) {
                        abandonChanges();
                        throw e;
                    }
                } catch (Throwable th3) {
                    throw th3;
                }
            }
        }
    }

    /* JADX DEBUG: Another duplicated slice has different insns count: {[GOTO]}, finally: {[GOTO, INVOKE] complete} */
    /* JADX DEBUG: Another duplicated slice has different insns count: {[INVOKE]}, finally: {[INVOKE, INVOKE, IF] complete} */
    public final void applyChangesInLocked(ArrayList arrayList) {
        boolean z;
        boolean z2;
        Anchor anchor;
        boolean isEmpty;
        Applier applier = this.applier;
        ArrayList arrayList2 = this.lateChanges;
        RememberEventDispatcher rememberEventDispatcher = new RememberEventDispatcher(this.abandonSet);
        try {
            if (arrayList.isEmpty()) {
                if (isEmpty) {
                    return;
                } else {
                    return;
                }
            }
            Trace.beginSection("Compose:applyChanges");
            try {
                applier.getClass();
                SlotWriter openWriter = this.slotTable.openWriter();
                try {
                    int size = arrayList.size();
                    for (int i = 0; i < size; i++) {
                        ((Function3) arrayList.get(i)).invoke(applier, openWriter, rememberEventDispatcher);
                    }
                    arrayList.clear();
                    openWriter.close();
                    Owner owner = ((LayoutNode) ((UiApplier) applier).root).owner;
                    if (owner != null) {
                        ((AndroidComposeView) owner).onEndApplyChanges();
                    }
                    Trace.endSection();
                    rememberEventDispatcher.dispatchRememberObservers();
                    ArrayList arrayList3 = rememberEventDispatcher.sideEffects;
                    boolean z3 = true;
                    if (!arrayList3.isEmpty()) {
                        Trace.beginSection("Compose:sideeffects");
                        try {
                            int size2 = arrayList3.size();
                            for (int i2 = 0; i2 < size2; i2++) {
                                ((Function0) arrayList3.get(i2)).invoke();
                            }
                            arrayList3.clear();
                            Trace.endSection();
                        } finally {
                        }
                    }
                    if (this.pendingInvalidScopes) {
                        Trace.beginSection("Compose:unobserve");
                        try {
                            this.pendingInvalidScopes = false;
                            SharingConfig sharingConfig = this.observations;
                            int[] iArr = (int[]) sharingConfig.upstream;
                            IdentityArraySet[] identityArraySetArr = (IdentityArraySet[]) sharingConfig.context;
                            Object[] objArr = (Object[]) sharingConfig.onBufferOverflow;
                            int i3 = sharingConfig.extraBufferCapacity;
                            int i4 = 0;
                            int i5 = 0;
                            while (i4 < i3) {
                                int i6 = iArr[i4];
                                IdentityArraySet identityArraySet = identityArraySetArr[i6];
                                ResultKt.checkNotNull(identityArraySet);
                                Object[] objArr2 = identityArraySet.values;
                                int i7 = identityArraySet.size;
                                int i8 = 0;
                                int i9 = 0;
                                while (i8 < i7) {
                                    Object obj = objArr2[i8];
                                    ResultKt.checkNotNull(obj, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                                    RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) obj;
                                    IdentityArraySet[] identityArraySetArr2 = identityArraySetArr;
                                    if (recomposeScopeImpl.owner == null || (anchor = recomposeScopeImpl.anchor) == null || !anchor.getValid()) {
                                        z = true;
                                        z2 = false;
                                    } else {
                                        z = true;
                                        z2 = true;
                                    }
                                    if (!(!z2)) {
                                        int i10 = i9;
                                        if (i10 != i8) {
                                            objArr2[i10] = obj;
                                        }
                                        i9 = i10 + 1;
                                    }
                                    i8++;
                                    z3 = z;
                                    identityArraySetArr = identityArraySetArr2;
                                }
                                boolean z4 = z3;
                                IdentityArraySet[] identityArraySetArr3 = identityArraySetArr;
                                int i11 = i9;
                                for (int i12 = i11; i12 < i7; i12++) {
                                    objArr2[i12] = null;
                                }
                                identityArraySet.size = i11;
                                if (i11 > 0) {
                                    if (i5 != i4) {
                                        int i13 = iArr[i5];
                                        iArr[i5] = i6;
                                        iArr[i4] = i13;
                                    }
                                    i5++;
                                }
                                i4++;
                                z3 = z4;
                                identityArraySetArr = identityArraySetArr3;
                            }
                            int i14 = sharingConfig.extraBufferCapacity;
                            for (int i15 = i5; i15 < i14; i15++) {
                                objArr[iArr[i15]] = null;
                            }
                            sharingConfig.extraBufferCapacity = i5;
                            cleanUpDerivedStateObservations();
                            Trace.endSection();
                        } finally {
                        }
                    }
                    if (arrayList2.isEmpty()) {
                        rememberEventDispatcher.dispatchAbandons();
                    }
                } finally {
                    openWriter.close();
                }
            } finally {
                Trace.endSection();
            }
        } finally {
            if (arrayList2.isEmpty()) {
                rememberEventDispatcher.dispatchAbandons();
            }
        }
    }

    /* JADX DEBUG: Finally have unexpected throw blocks count: 4, expect 1 */
    public final void applyLateChanges() {
        synchronized (this.lock) {
            try {
                if (!this.lateChanges.isEmpty()) {
                    applyChangesInLocked(this.lateChanges);
                }
            } catch (Throwable th) {
                try {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            HashSet hashSet = this.abandonSet;
                            ResultKt.checkNotNullParameter(hashSet, "abandoning");
                            new ArrayList();
                            new ArrayList();
                            new ArrayList();
                            if (!hashSet.isEmpty()) {
                                Trace.beginSection("Compose:abandons");
                                try {
                                    Iterator it = hashSet.iterator();
                                    while (it.hasNext()) {
                                        RememberObserver rememberObserver = (RememberObserver) it.next();
                                        it.remove();
                                        rememberObserver.onAbandoned();
                                    }
                                    Trace.endSection();
                                } catch (Throwable th2) {
                                    Trace.endSection();
                                    throw th2;
                                }
                            }
                        }
                        throw th;
                    } catch (Exception e) {
                        abandonChanges();
                        throw e;
                    }
                } catch (Throwable th3) {
                    throw th3;
                }
            }
        }
    }

    /* JADX DEBUG: Finally have unexpected throw blocks count: 4, expect 1 */
    public final void changesApplied() {
        synchronized (this.lock) {
            try {
                ComposerImpl composerImpl = this.composer;
                EffectsKt.runtimeCheck(composerImpl.writer.closed);
                SlotTable slotTable = new SlotTable();
                composerImpl.insertTable = slotTable;
                SlotWriter openWriter = slotTable.openWriter();
                openWriter.close();
                composerImpl.writer = openWriter;
                composerImpl.providerUpdates.clear();
                if (!this.abandonSet.isEmpty()) {
                    HashSet hashSet = this.abandonSet;
                    ResultKt.checkNotNullParameter(hashSet, "abandoning");
                    new ArrayList();
                    new ArrayList();
                    new ArrayList();
                    if (!hashSet.isEmpty()) {
                        Trace.beginSection("Compose:abandons");
                        try {
                            Iterator it = hashSet.iterator();
                            while (it.hasNext()) {
                                RememberObserver rememberObserver = (RememberObserver) it.next();
                                it.remove();
                                rememberObserver.onAbandoned();
                            }
                            Trace.endSection();
                        } finally {
                        }
                    }
                }
            } catch (Throwable th) {
                try {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            HashSet hashSet2 = this.abandonSet;
                            ResultKt.checkNotNullParameter(hashSet2, "abandoning");
                            new ArrayList();
                            new ArrayList();
                            new ArrayList();
                            if (!hashSet2.isEmpty()) {
                                Trace.beginSection("Compose:abandons");
                                try {
                                    Iterator it2 = hashSet2.iterator();
                                    while (it2.hasNext()) {
                                        RememberObserver rememberObserver2 = (RememberObserver) it2.next();
                                        it2.remove();
                                        rememberObserver2.onAbandoned();
                                    }
                                    Trace.endSection();
                                } finally {
                                }
                            }
                        }
                        throw th;
                    } catch (Throwable th2) {
                        throw th2;
                    }
                } catch (Exception e) {
                    abandonChanges();
                    throw e;
                }
            }
        }
    }

    public final void cleanUpDerivedStateObservations() {
        SharingConfig sharingConfig = this.derivedStates;
        int[] iArr = (int[]) sharingConfig.upstream;
        IdentityArraySet[] identityArraySetArr = (IdentityArraySet[]) sharingConfig.context;
        Object[] objArr = (Object[]) sharingConfig.onBufferOverflow;
        int i = sharingConfig.extraBufferCapacity;
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            int i4 = iArr[i2];
            IdentityArraySet identityArraySet = identityArraySetArr[i4];
            ResultKt.checkNotNull(identityArraySet);
            Object[] objArr2 = identityArraySet.values;
            int i5 = identityArraySet.size;
            int i6 = 0;
            int i7 = 0;
            while (i7 < i5) {
                Object obj = objArr2[i7];
                ResultKt.checkNotNull(obj, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                IdentityArraySet[] identityArraySetArr2 = identityArraySetArr;
                if (!(!this.observations.contains((DerivedSnapshotState) obj))) {
                    if (i6 != i7) {
                        objArr2[i6] = obj;
                    }
                    i6++;
                }
                i7++;
                identityArraySetArr = identityArraySetArr2;
            }
            IdentityArraySet[] identityArraySetArr3 = identityArraySetArr;
            for (int i8 = i6; i8 < i5; i8++) {
                objArr2[i8] = null;
            }
            identityArraySet.size = i6;
            if (i6 > 0) {
                if (i3 != i2) {
                    int i9 = iArr[i3];
                    iArr[i3] = i4;
                    iArr[i2] = i9;
                }
                i3++;
            }
            i2++;
            identityArraySetArr = identityArraySetArr3;
        }
        int i10 = sharingConfig.extraBufferCapacity;
        for (int i11 = i3; i11 < i10; i11++) {
            objArr[iArr[i11]] = null;
        }
        sharingConfig.extraBufferCapacity = i3;
        HashSet hashSet = this.conditionallyInvalidatedScopes;
        if (!hashSet.isEmpty()) {
            Iterator it = hashSet.iterator();
            ResultKt.checkNotNullExpressionValue(it, "iterator()");
            while (it.hasNext()) {
                if (!(((RecomposeScopeImpl) it.next()).trackedDependencies != null)) {
                    it.remove();
                }
            }
        }
    }

    /* JADX DEBUG: Finally have unexpected throw blocks count: 3, expect 1 */
    public final void composeContent(ComposableLambdaImpl composableLambdaImpl) {
        try {
            synchronized (this.lock) {
                drainPendingModificationsForCompositionLocked();
                SnapshotWeakSet snapshotWeakSet = this.invalidations;
                this.invalidations = new SnapshotWeakSet((Object) null);
                try {
                    this.composer.composeContent$runtime_release(snapshotWeakSet, composableLambdaImpl);
                } catch (Exception e) {
                    this.invalidations = snapshotWeakSet;
                    throw e;
                }
            }
        } catch (Throwable th) {
            try {
                if (!this.abandonSet.isEmpty()) {
                    HashSet hashSet = this.abandonSet;
                    ResultKt.checkNotNullParameter(hashSet, "abandoning");
                    new ArrayList();
                    new ArrayList();
                    new ArrayList();
                    if (!hashSet.isEmpty()) {
                        Trace.beginSection("Compose:abandons");
                        try {
                            Iterator it = hashSet.iterator();
                            while (it.hasNext()) {
                                RememberObserver rememberObserver = (RememberObserver) it.next();
                                it.remove();
                                rememberObserver.onAbandoned();
                            }
                            Trace.endSection();
                        } catch (Throwable th2) {
                            Trace.endSection();
                            throw th2;
                        }
                    }
                }
                throw th;
            } catch (Exception e2) {
                abandonChanges();
                throw e2;
            }
        }
    }

    @Override // androidx.compose.runtime.Composition
    public final void dispose() {
        synchronized (this.lock) {
            try {
                if (!this.disposed) {
                    this.disposed = true;
                    int i = ComposableSingletons$CompositionKt.$r8$clinit;
                    this.composer.getClass();
                    boolean z = this.slotTable.groupsSize > 0;
                    if (!z) {
                        if (true ^ this.abandonSet.isEmpty()) {
                        }
                        this.composer.dispose$runtime_release();
                    }
                    RememberEventDispatcher rememberEventDispatcher = new RememberEventDispatcher(this.abandonSet);
                    if (z) {
                        this.applier.getClass();
                        SlotWriter openWriter = this.slotTable.openWriter();
                        try {
                            EffectsKt.removeCurrentGroup(openWriter, rememberEventDispatcher);
                            openWriter.close();
                            ((AbstractApplier) this.applier).clear();
                            Owner owner = ((LayoutNode) ((UiApplier) this.applier).root).owner;
                            if (owner != null) {
                                ((AndroidComposeView) owner).onEndApplyChanges();
                            }
                            rememberEventDispatcher.dispatchRememberObservers();
                        } catch (Throwable th) {
                            openWriter.close();
                            throw th;
                        }
                    }
                    rememberEventDispatcher.dispatchAbandons();
                    this.composer.dispose$runtime_release();
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
        Recomposer recomposer = (Recomposer) this.parent;
        recomposer.getClass();
        synchronized (recomposer.stateLock) {
            recomposer.knownCompositions.remove(this);
            recomposer.compositionInvalidations.remove(this);
            recomposer.compositionsAwaitingApply.remove(this);
        }
    }

    public final void drainPendingModificationsForCompositionLocked() {
        AtomicReference atomicReference = this.pendingModifications;
        Object obj = CompositionKt.PendingApplyNoModifications;
        Object andSet = atomicReference.getAndSet(obj);
        if (andSet != null) {
            if (ResultKt.areEqual(andSet, obj)) {
                EffectsKt.composeRuntimeError("pending composition has not been applied");
                throw null;
            }
            if (andSet instanceof Set) {
                addPendingInvalidationsLocked((Set) andSet, true);
                return;
            }
            if (!(andSet instanceof Object[])) {
                EffectsKt.composeRuntimeError("corrupt pendingModifications drain: " + atomicReference);
                throw null;
            }
            for (Set set : (Set[]) andSet) {
                addPendingInvalidationsLocked(set, true);
            }
        }
    }

    public final void drainPendingModificationsLocked() {
        AtomicReference atomicReference = this.pendingModifications;
        Object andSet = atomicReference.getAndSet(null);
        if (ResultKt.areEqual(andSet, CompositionKt.PendingApplyNoModifications)) {
            return;
        }
        if (andSet instanceof Set) {
            addPendingInvalidationsLocked((Set) andSet, false);
            return;
        }
        if (andSet instanceof Object[]) {
            for (Set set : (Set[]) andSet) {
                addPendingInvalidationsLocked(set, false);
            }
            return;
        }
        if (andSet == null) {
            EffectsKt.composeRuntimeError("calling recordModificationsOf and applyChanges concurrently is not supported");
            throw null;
        }
        EffectsKt.composeRuntimeError("corrupt pendingModifications drain: " + atomicReference);
        throw null;
    }

    /* JADX DEBUG: Finally have unexpected throw blocks count: 3, expect 1 */
    public final void insertMovableContent(ArrayList arrayList) {
        if (arrayList.size() > 0) {
            DurationKt$$ExternalSyntheticCheckNotZero0.m(((Pair) arrayList.get(0)).first);
            throw null;
        }
        try {
            ComposerImpl composerImpl = this.composer;
            composerImpl.getClass();
            try {
                composerImpl.insertMovableContentGuarded(arrayList);
                composerImpl.cleanUpCompose();
            } catch (Throwable th) {
                composerImpl.abortRoot();
                throw th;
            }
        } catch (Throwable th2) {
            HashSet hashSet = this.abandonSet;
            try {
                if (!hashSet.isEmpty()) {
                    new ArrayList();
                    new ArrayList();
                    new ArrayList();
                    if (!hashSet.isEmpty()) {
                        Trace.beginSection("Compose:abandons");
                        try {
                            Iterator it = hashSet.iterator();
                            while (it.hasNext()) {
                                RememberObserver rememberObserver = (RememberObserver) it.next();
                                it.remove();
                                rememberObserver.onAbandoned();
                            }
                            Trace.endSection();
                        } catch (Throwable th3) {
                            Trace.endSection();
                            throw th3;
                        }
                    }
                }
                throw th2;
            } catch (Exception e) {
                abandonChanges();
                throw e;
            }
        }
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public final int invalidate(RecomposeScopeImpl recomposeScopeImpl, Object obj) {
        ResultKt.checkNotNullParameter(recomposeScopeImpl, "scope");
        int i = recomposeScopeImpl.flags;
        if ((i & 2) != 0) {
            recomposeScopeImpl.flags = i | 4;
        }
        Anchor anchor = recomposeScopeImpl.anchor;
        if (anchor != null && anchor.getValid()) {
            if (!this.slotTable.ownsAnchor(anchor)) {
                synchronized (this.lock) {
                }
                return 1;
            }
            if (recomposeScopeImpl.block != null) {
                return invalidateChecked(recomposeScopeImpl, anchor, obj);
            }
        }
        return 1;
    }

    public final void invalidateAll() {
        RecomposeScopeOwner recomposeScopeOwner;
        synchronized (this.lock) {
            try {
                for (Object obj : this.slotTable.slots) {
                    RecomposeScopeImpl recomposeScopeImpl = obj instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) obj : null;
                    if (recomposeScopeImpl != null && (recomposeScopeOwner = recomposeScopeImpl.owner) != null) {
                        recomposeScopeOwner.invalidate(recomposeScopeImpl, null);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final int invalidateChecked(RecomposeScopeImpl recomposeScopeImpl, Anchor anchor, Object obj) {
        synchronized (this.lock) {
            try {
                ComposerImpl composerImpl = this.composer;
                if (composerImpl.isComposing && composerImpl.tryImminentInvalidation$runtime_release(recomposeScopeImpl, obj)) {
                    return 4;
                }
                CancellableContinuation cancellableContinuation = null;
                if (obj == null) {
                    this.invalidations.set(recomposeScopeImpl, null);
                } else {
                    SnapshotWeakSet snapshotWeakSet = this.invalidations;
                    snapshotWeakSet.getClass();
                    ResultKt.checkNotNullParameter(recomposeScopeImpl, "key");
                    if (snapshotWeakSet.find(recomposeScopeImpl) >= 0) {
                        IdentityArraySet identityArraySet = (IdentityArraySet) snapshotWeakSet.get(recomposeScopeImpl);
                        if (identityArraySet != null) {
                            identityArraySet.add(obj);
                        }
                    } else {
                        IdentityArraySet identityArraySet2 = new IdentityArraySet();
                        identityArraySet2.add(obj);
                        snapshotWeakSet.set(recomposeScopeImpl, identityArraySet2);
                    }
                }
                Recomposer recomposer = (Recomposer) this.parent;
                recomposer.getClass();
                synchronized (recomposer.stateLock) {
                    if (!recomposer.compositionInvalidations.contains(this)) {
                        recomposer.compositionInvalidations.add(this);
                        cancellableContinuation = recomposer.deriveStateLocked();
                    }
                }
                if (cancellableContinuation != null) {
                    cancellableContinuation.resumeWith(Unit.INSTANCE);
                }
                return this.composer.isComposing ? 3 : 2;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void invalidateScopeOfLocked(Object obj) {
        SharingConfig sharingConfig = this.observations;
        int find = sharingConfig.find(obj);
        if (find >= 0) {
            IdentityArraySet scopeSetAt = sharingConfig.scopeSetAt(find);
            Object[] objArr = scopeSetAt.values;
            int i = scopeSetAt.size;
            for (int i2 = 0; i2 < i; i2++) {
                Object obj2 = objArr[i2];
                ResultKt.checkNotNull(obj2, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) obj2;
                if (recomposeScopeImpl.invalidateForResult(obj) == 4) {
                    this.observationsProcessed.add(obj, recomposeScopeImpl);
                }
            }
        }
    }

    /* JADX DEBUG: Finally have unexpected throw blocks count: 3, expect 1 */
    public final boolean recompose() {
        boolean recompose$runtime_release;
        synchronized (this.lock) {
            try {
                drainPendingModificationsForCompositionLocked();
                try {
                    SnapshotWeakSet snapshotWeakSet = this.invalidations;
                    this.invalidations = new SnapshotWeakSet((Object) null);
                    try {
                        recompose$runtime_release = this.composer.recompose$runtime_release(snapshotWeakSet);
                        if (!recompose$runtime_release) {
                            drainPendingModificationsLocked();
                        }
                    } catch (Exception e) {
                        this.invalidations = snapshotWeakSet;
                        throw e;
                    }
                } catch (Throwable th) {
                    try {
                        if (!this.abandonSet.isEmpty()) {
                            HashSet hashSet = this.abandonSet;
                            ResultKt.checkNotNullParameter(hashSet, "abandoning");
                            new ArrayList();
                            new ArrayList();
                            new ArrayList();
                            if (!hashSet.isEmpty()) {
                                Trace.beginSection("Compose:abandons");
                                try {
                                    Iterator it = hashSet.iterator();
                                    while (it.hasNext()) {
                                        RememberObserver rememberObserver = (RememberObserver) it.next();
                                        it.remove();
                                        rememberObserver.onAbandoned();
                                    }
                                    Trace.endSection();
                                } catch (Throwable th2) {
                                    Trace.endSection();
                                    throw th2;
                                }
                            }
                        }
                        throw th;
                    } catch (Exception e2) {
                        abandonChanges();
                        throw e2;
                    }
                }
            } catch (Throwable th3) {
                throw th3;
            }
        }
        return recompose$runtime_release;
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public final void recomposeScopeReleased(RecomposeScopeImpl recomposeScopeImpl) {
        ResultKt.checkNotNullParameter(recomposeScopeImpl, "scope");
        this.pendingInvalidScopes = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r1v12, types: [java.util.Set[]] */
    public final void recordModificationsOf(IdentityArraySet identityArraySet) {
        IdentityArraySet identityArraySet2;
        ResultKt.checkNotNullParameter(identityArraySet, "values");
        while (true) {
            Object obj = this.pendingModifications.get();
            if (obj == null || ResultKt.areEqual(obj, CompositionKt.PendingApplyNoModifications)) {
                identityArraySet2 = identityArraySet;
            } else if (obj instanceof Set) {
                identityArraySet2 = new Set[]{obj, identityArraySet};
            } else {
                if (!(obj instanceof Object[])) {
                    throw new IllegalStateException(("corrupt pendingModifications: " + this.pendingModifications).toString());
                }
                Set[] setArr = (Set[]) obj;
                int length = setArr.length;
                ?? copyOf = Arrays.copyOf(setArr, length + 1);
                copyOf[length] = identityArraySet;
                identityArraySet2 = copyOf;
            }
            AtomicReference atomicReference = this.pendingModifications;
            while (!atomicReference.compareAndSet(obj, identityArraySet2)) {
                if (atomicReference.get() != obj) {
                    break;
                }
            }
            if (obj == null) {
                synchronized (this.lock) {
                    drainPendingModificationsLocked();
                }
                return;
            }
            return;
        }
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public final void recordReadOf(Object obj) {
        RecomposeScopeImpl currentRecomposeScope$runtime_release;
        Object[] objArr;
        ResultKt.checkNotNullParameter(obj, "value");
        ComposerImpl composerImpl = this.composer;
        if (composerImpl.childrenComposing <= 0 && (currentRecomposeScope$runtime_release = composerImpl.getCurrentRecomposeScope$runtime_release()) != null) {
            int i = currentRecomposeScope$runtime_release.flags | 1;
            currentRecomposeScope$runtime_release.flags = i;
            if ((i & 32) == 0) {
                IdentityArrayIntMap identityArrayIntMap = currentRecomposeScope$runtime_release.trackedInstances;
                if (identityArrayIntMap == null) {
                    identityArrayIntMap = new IdentityArrayIntMap();
                    currentRecomposeScope$runtime_release.trackedInstances = identityArrayIntMap;
                }
                if (identityArrayIntMap.add(currentRecomposeScope$runtime_release.currentToken, obj) == currentRecomposeScope$runtime_release.currentToken) {
                    return;
                }
                if (obj instanceof DerivedSnapshotState) {
                    SnapshotWeakSet snapshotWeakSet = currentRecomposeScope$runtime_release.trackedDependencies;
                    if (snapshotWeakSet == null) {
                        snapshotWeakSet = new SnapshotWeakSet((Object) null);
                        currentRecomposeScope$runtime_release.trackedDependencies = snapshotWeakSet;
                    }
                    snapshotWeakSet.set(obj, ((DerivedSnapshotState) obj).getCurrentRecord().result);
                }
            }
            this.observations.add(obj, currentRecomposeScope$runtime_release);
            if (obj instanceof DerivedSnapshotState) {
                SharingConfig sharingConfig = this.derivedStates;
                sharingConfig.removeScope(obj);
                SnapshotWeakSet snapshotWeakSet2 = ((DerivedSnapshotState) obj).getCurrentRecord()._dependencies;
                if (snapshotWeakSet2 == null || (objArr = (Object[]) snapshotWeakSet2.hashes) == null) {
                    objArr = new Object[0];
                }
                for (Object obj2 : objArr) {
                    if (obj2 == null) {
                        return;
                    }
                    sharingConfig.add(obj2, obj);
                }
            }
        }
    }

    public final void recordWriteOf(Object obj) {
        ResultKt.checkNotNullParameter(obj, "value");
        synchronized (this.lock) {
            invalidateScopeOfLocked(obj);
            SharingConfig sharingConfig = this.derivedStates;
            int find = sharingConfig.find(obj);
            if (find >= 0) {
                IdentityArraySet scopeSetAt = sharingConfig.scopeSetAt(find);
                Object[] objArr = scopeSetAt.values;
                int i = scopeSetAt.size;
                for (int i2 = 0; i2 < i; i2++) {
                    Object obj2 = objArr[i2];
                    ResultKt.checkNotNull(obj2, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                    invalidateScopeOfLocked((DerivedSnapshotState) obj2);
                }
            }
        }
    }

    @Override // androidx.compose.runtime.Composition
    public final void setContent(Function2 function2) {
        int i = 1;
        if (!(!this.disposed)) {
            throw new IllegalStateException("The composition is disposed".toString());
        }
        Recomposer recomposer = (Recomposer) this.parent;
        recomposer.getClass();
        boolean z = this.composer.isComposing;
        try {
            MutableSnapshot takeMutableSnapshot = ArtificialStackFrames.takeMutableSnapshot(new Recomposer$effectJob$1$1(i, this), new Latch$await$2$2(this, i, null));
            try {
                Snapshot makeCurrent = takeMutableSnapshot.makeCurrent();
                try {
                    composeContent((ComposableLambdaImpl) function2);
                    if (!z) {
                        SnapshotKt.currentSnapshot().notifyObjectsInitialized$runtime_release();
                    }
                    synchronized (recomposer.stateLock) {
                        if (((Recomposer.State) recomposer._state.getValue()).compareTo(Recomposer.State.ShuttingDown) > 0 && !recomposer.knownCompositions.contains(this)) {
                            recomposer.knownCompositions.add(this);
                        }
                    }
                    try {
                        synchronized (recomposer.stateLock) {
                            ArrayList arrayList = recomposer.compositionValuesAwaitingInsert;
                            if (arrayList.size() > 0) {
                                DurationKt$$ExternalSyntheticCheckNotZero0.m(arrayList.get(0));
                                throw null;
                            }
                        }
                        try {
                            applyChanges();
                            applyLateChanges();
                            if (z) {
                                return;
                            }
                            SnapshotKt.currentSnapshot().notifyObjectsInitialized$runtime_release();
                        } catch (Exception e) {
                            recomposer.processCompositionError(e, null);
                        }
                    } catch (Exception e2) {
                        recomposer.processCompositionError(e2, this);
                    }
                } finally {
                    Snapshot.restoreCurrent(makeCurrent);
                }
            } finally {
                Recomposer.applyAndCheck(takeMutableSnapshot);
            }
        } catch (Exception e3) {
            recomposer.processCompositionError(e3, this);
        }
    }

    public final void addPendingInvalidationsLocked(Set set, boolean z) {
        HashSet hashSet;
        String str;
        boolean z2 = set instanceof IdentityArraySet;
        SharingConfig sharingConfig = this.derivedStates;
        String str2 = "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet";
        if (z2) {
            IdentityArraySet identityArraySet = (IdentityArraySet) set;
            Object[] objArr = identityArraySet.values;
            int i = identityArraySet.size;
            hashSet = null;
            for (int i2 = 0; i2 < i; i2++) {
                Object obj = objArr[i2];
                ResultKt.checkNotNull(obj, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                if (obj instanceof RecomposeScopeImpl) {
                    ((RecomposeScopeImpl) obj).invalidateForResult(null);
                } else {
                    hashSet = addPendingInvalidationsLocked(hashSet, obj, z);
                    int find = sharingConfig.find(obj);
                    if (find >= 0) {
                        IdentityArraySet scopeSetAt = sharingConfig.scopeSetAt(find);
                        Object[] objArr2 = scopeSetAt.values;
                        int i3 = scopeSetAt.size;
                        for (int i4 = 0; i4 < i3; i4++) {
                            Object obj2 = objArr2[i4];
                            ResultKt.checkNotNull(obj2, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                            hashSet = addPendingInvalidationsLocked(hashSet, (DerivedSnapshotState) obj2, z);
                        }
                    }
                }
            }
        } else {
            hashSet = null;
            for (Object obj3 : set) {
                if (obj3 instanceof RecomposeScopeImpl) {
                    ((RecomposeScopeImpl) obj3).invalidateForResult(null);
                } else {
                    HashSet addPendingInvalidationsLocked = addPendingInvalidationsLocked(hashSet, obj3, z);
                    int find2 = sharingConfig.find(obj3);
                    if (find2 >= 0) {
                        IdentityArraySet scopeSetAt2 = sharingConfig.scopeSetAt(find2);
                        Object[] objArr3 = scopeSetAt2.values;
                        int i5 = scopeSetAt2.size;
                        for (int i6 = 0; i6 < i5; i6++) {
                            Object obj4 = objArr3[i6];
                            ResultKt.checkNotNull(obj4, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                            addPendingInvalidationsLocked = addPendingInvalidationsLocked(addPendingInvalidationsLocked, (DerivedSnapshotState) obj4, z);
                        }
                    }
                    hashSet = addPendingInvalidationsLocked;
                }
            }
        }
        SharingConfig sharingConfig2 = this.observations;
        if (z) {
            HashSet hashSet2 = this.conditionallyInvalidatedScopes;
            if (!hashSet2.isEmpty()) {
                int[] iArr = (int[]) sharingConfig2.upstream;
                IdentityArraySet[] identityArraySetArr = (IdentityArraySet[]) sharingConfig2.context;
                Object[] objArr4 = (Object[]) sharingConfig2.onBufferOverflow;
                int i7 = sharingConfig2.extraBufferCapacity;
                int i8 = 0;
                int i9 = 0;
                while (i8 < i7) {
                    int i10 = iArr[i8];
                    IdentityArraySet identityArraySet2 = identityArraySetArr[i10];
                    ResultKt.checkNotNull(identityArraySet2);
                    Object[] objArr5 = identityArraySet2.values;
                    int i11 = identityArraySet2.size;
                    int i12 = 0;
                    int i13 = 0;
                    while (i13 < i11) {
                        IdentityArraySet[] identityArraySetArr2 = identityArraySetArr;
                        Object obj5 = objArr5[i13];
                        ResultKt.checkNotNull(obj5, str2);
                        int i14 = i7;
                        RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) obj5;
                        if (hashSet2.contains(recomposeScopeImpl)) {
                            str = str2;
                        } else {
                            if (hashSet != null) {
                                str = str2;
                                if (hashSet.contains(recomposeScopeImpl)) {
                                }
                            } else {
                                str = str2;
                            }
                            if (i12 != i13) {
                                objArr5[i12] = obj5;
                            }
                            i12++;
                        }
                        i13++;
                        identityArraySetArr = identityArraySetArr2;
                        i7 = i14;
                        str2 = str;
                    }
                    String str3 = str2;
                    IdentityArraySet[] identityArraySetArr3 = identityArraySetArr;
                    int i15 = i7;
                    for (int i16 = i12; i16 < i11; i16++) {
                        objArr5[i16] = null;
                    }
                    identityArraySet2.size = i12;
                    if (i12 > 0) {
                        if (i9 != i8) {
                            int i17 = iArr[i9];
                            iArr[i9] = i10;
                            iArr[i8] = i17;
                        }
                        i9++;
                    }
                    i8++;
                    identityArraySetArr = identityArraySetArr3;
                    i7 = i15;
                    str2 = str3;
                }
                int i18 = sharingConfig2.extraBufferCapacity;
                for (int i19 = i9; i19 < i18; i19++) {
                    objArr4[iArr[i19]] = null;
                }
                sharingConfig2.extraBufferCapacity = i9;
                hashSet2.clear();
                cleanUpDerivedStateObservations();
                return;
            }
        }
        String str4 = "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet";
        if (hashSet != null) {
            int[] iArr2 = (int[]) sharingConfig2.upstream;
            IdentityArraySet[] identityArraySetArr4 = (IdentityArraySet[]) sharingConfig2.context;
            Object[] objArr6 = (Object[]) sharingConfig2.onBufferOverflow;
            int i20 = sharingConfig2.extraBufferCapacity;
            int i21 = 0;
            int i22 = 0;
            while (i21 < i20) {
                int i23 = iArr2[i21];
                IdentityArraySet identityArraySet3 = identityArraySetArr4[i23];
                ResultKt.checkNotNull(identityArraySet3);
                Object[] objArr7 = identityArraySet3.values;
                int i24 = identityArraySet3.size;
                int i25 = 0;
                int i26 = 0;
                while (i25 < i24) {
                    Object obj6 = objArr7[i25];
                    String str5 = str4;
                    ResultKt.checkNotNull(obj6, str5);
                    IdentityArraySet[] identityArraySetArr5 = identityArraySetArr4;
                    if (!hashSet.contains((RecomposeScopeImpl) obj6)) {
                        if (i26 != i25) {
                            objArr7[i26] = obj6;
                        }
                        i26++;
                    }
                    i25++;
                    identityArraySetArr4 = identityArraySetArr5;
                    str4 = str5;
                }
                IdentityArraySet[] identityArraySetArr6 = identityArraySetArr4;
                String str6 = str4;
                for (int i27 = i26; i27 < i24; i27++) {
                    objArr7[i27] = null;
                }
                identityArraySet3.size = i26;
                if (i26 > 0) {
                    if (i22 != i21) {
                        int i28 = iArr2[i22];
                        iArr2[i22] = i23;
                        iArr2[i21] = i28;
                    }
                    i22++;
                }
                i21++;
                identityArraySetArr4 = identityArraySetArr6;
                str4 = str6;
            }
            int i29 = sharingConfig2.extraBufferCapacity;
            for (int i30 = i22; i30 < i29; i30++) {
                objArr6[iArr2[i30]] = null;
            }
            sharingConfig2.extraBufferCapacity = i22;
            cleanUpDerivedStateObservations();
        }
    }
}
