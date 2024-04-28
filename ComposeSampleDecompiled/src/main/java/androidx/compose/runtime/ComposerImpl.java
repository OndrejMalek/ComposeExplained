package androidx.compose.runtime;

import _COROUTINE.ArtificialStackFrames;
import _COROUTINE._BOUNDARY;
import android.os.Trace;
import android.util.SparseArray;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.CompositionImpl;
import androidx.compose.runtime.collection.IdentityArrayIntMap;
import androidx.compose.runtime.collection.IdentityArraySet;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.PersistentCompositionLocalHashMap;
import androidx.compose.runtime.snapshots.SnapshotWeakSet;
import androidx.compose.runtime.tooling.InspectionTablesKt;
import androidx.compose.ui.node.ComposeUiNode$Companion$SetDensity$1;
import androidx.compose.ui.node.UiApplier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class ComposerImpl implements Composer {
    public final Set abandonSet;
    public final Applier applier;
    public List changes;
    public int childrenComposing;
    public final CompositionImpl composition;
    public int compositionToken;
    public int compoundKeyHash;
    public final ComposerImpl$derivedStateObserver$1 derivedStateObserver;
    public final Stack downNodes;
    public final IntStack entersStack;
    public boolean forceRecomposeScopes;
    public int groupNodeCount;
    public final IntStack groupNodeCountStack;
    public final boolean implicitRootStart;
    public Anchor insertAnchor;
    public final ArrayList insertFixups;
    public SlotTable insertTable;
    public final Stack insertUpFixups;
    public boolean inserting;
    public final Stack invalidateStack;
    public final ArrayList invalidations;
    public boolean isComposing;
    public final List lateChanges;
    public int[] nodeCountOverrides;
    public HashMap nodeCountVirtualOverrides;
    public boolean nodeExpected;
    public int nodeIndex;
    public final IntStack nodeIndexStack;
    public final CompositionContext parentContext;
    public PersistentCompositionLocalHashMap parentProvider;
    public Pending pending;
    public final Stack pendingStack;
    public int pendingUps;
    public int previousCount;
    public int previousMoveFrom;
    public int previousMoveTo;
    public int previousRemove;
    public PersistentCompositionLocalMap providerCache;
    public final Stack providerUpdates;
    public boolean providersInvalid;
    public final IntStack providersInvalidStack;
    public SlotReader reader;
    public boolean reusing;
    public int reusingGroup;
    public final SlotTable slotTable;
    public boolean startedGroup;
    public final IntStack startedGroups;
    public SlotWriter writer;
    public boolean writerHasAProvider;
    public int writersReaderDelta;

    public ComposerImpl(UiApplier uiApplier, CompositionContext compositionContext, SlotTable slotTable, HashSet hashSet, ArrayList arrayList, ArrayList arrayList2, CompositionImpl compositionImpl) {
        ResultKt.checkNotNullParameter(compositionImpl, "composition");
        this.applier = uiApplier;
        this.parentContext = compositionContext;
        this.slotTable = slotTable;
        this.abandonSet = hashSet;
        this.changes = arrayList;
        this.lateChanges = arrayList2;
        this.composition = compositionImpl;
        this.pendingStack = new Stack(0);
        this.nodeIndexStack = new IntStack();
        this.groupNodeCountStack = new IntStack();
        this.invalidations = new ArrayList();
        this.entersStack = new IntStack();
        this.parentProvider = PersistentCompositionLocalHashMap.Empty;
        this.providerUpdates = new Stack((Object) null);
        this.providersInvalidStack = new IntStack();
        this.reusingGroup = -1;
        this.derivedStateObserver = new ComposerImpl$derivedStateObserver$1(0, this);
        this.invalidateStack = new Stack(0);
        SlotReader openReader = slotTable.openReader();
        openReader.close();
        this.reader = openReader;
        SlotTable slotTable2 = new SlotTable();
        this.insertTable = slotTable2;
        SlotWriter openWriter = slotTable2.openWriter();
        openWriter.close();
        this.writer = openWriter;
        SlotReader openReader2 = this.insertTable.openReader();
        try {
            Anchor anchor = openReader2.anchor(0);
            openReader2.close();
            this.insertAnchor = anchor;
            this.insertFixups = new ArrayList();
            this.downNodes = new Stack(0);
            this.implicitRootStart = true;
            this.startedGroups = new IntStack();
            this.insertUpFixups = new Stack(0);
            this.previousRemove = -1;
            this.previousMoveFrom = -1;
            this.previousMoveTo = -1;
        } catch (Throwable th) {
            openReader2.close();
            throw th;
        }
    }

    public static final void insertMovableContentGuarded$positionToParentOf(SlotWriter slotWriter, Applier applier, int i) {
        while (true) {
            int i2 = slotWriter.parent;
            if (i > i2 && i < slotWriter.currentGroupEnd) {
                return;
            }
            if (i2 == 0 && i == 0) {
                return;
            }
            slotWriter.skipToGroupEnd();
            if (ResultKt.access$isNode(slotWriter.groups, slotWriter.groupIndexToAddress(slotWriter.parent))) {
                ((AbstractApplier) applier).up();
            }
            slotWriter.endGroup();
        }
    }

    public static final int reportFreeMovableContent$reportGroup(ComposerImpl composerImpl, int i, boolean z, int i2) {
        SlotReader slotReader = composerImpl.reader;
        int[] iArr = slotReader.groups;
        int i3 = i * 5;
        if ((iArr[i3 + 1] & 134217728) != 0) {
            int i4 = iArr[i3];
            Object objectKey = slotReader.objectKey(iArr, i);
            if (i4 != 206 || !ResultKt.areEqual(objectKey, EffectsKt.reference)) {
                return ResultKt.access$nodeCount(composerImpl.reader.groups, i);
            }
            composerImpl.reader.groupGet(i, 0);
            return ResultKt.access$nodeCount(composerImpl.reader.groups, i);
        }
        if (!ResultKt.access$containsMark(iArr, i)) {
            return ResultKt.access$nodeCount(composerImpl.reader.groups, i);
        }
        int access$groupSize = ResultKt.access$groupSize(composerImpl.reader.groups, i) + i;
        int i5 = i + 1;
        int i6 = 0;
        while (i5 < access$groupSize) {
            boolean access$isNode = ResultKt.access$isNode(composerImpl.reader.groups, i5);
            if (access$isNode) {
                composerImpl.realizeMovement();
                composerImpl.downNodes.push(composerImpl.reader.node(i5));
            }
            i6 += reportFreeMovableContent$reportGroup(composerImpl, i5, access$isNode || z, access$isNode ? 0 : i2 + i6);
            if (access$isNode) {
                composerImpl.realizeMovement();
                composerImpl.recordUp();
            }
            i5 += ResultKt.access$groupSize(composerImpl.reader.groups, i5);
        }
        return i6;
    }

    public final void abortRoot() {
        cleanUpCompose();
        this.pendingStack.clear();
        this.nodeIndexStack.tos = 0;
        this.groupNodeCountStack.tos = 0;
        this.entersStack.tos = 0;
        this.providersInvalidStack.tos = 0;
        this.providerUpdates.clear();
        SlotReader slotReader = this.reader;
        if (!slotReader.closed) {
            slotReader.close();
        }
        SlotWriter slotWriter = this.writer;
        if (!slotWriter.closed) {
            slotWriter.close();
        }
        this.insertFixups.clear();
        EffectsKt.runtimeCheck(this.writer.closed);
        SlotTable slotTable = new SlotTable();
        this.insertTable = slotTable;
        SlotWriter openWriter = slotTable.openWriter();
        openWriter.close();
        this.writer = openWriter;
        this.compoundKeyHash = 0;
        this.childrenComposing = 0;
        this.nodeExpected = false;
        this.inserting = false;
        this.reusing = false;
        this.isComposing = false;
        this.reusingGroup = -1;
    }

    public final void apply(Object obj, ComposeUiNode$Companion$SetDensity$1 composeUiNode$Companion$SetDensity$1) {
        ResultKt.checkNotNullParameter(composeUiNode$Companion$SetDensity$1, "block");
        ComposerImpl$recordInsert$1 composerImpl$recordInsert$1 = new ComposerImpl$recordInsert$1(composeUiNode$Companion$SetDensity$1, 1, obj);
        if (this.inserting) {
            this.insertFixups.add(composerImpl$recordInsert$1);
            return;
        }
        realizeUps();
        realizeDowns$1();
        record(composerImpl$recordInsert$1);
    }

    public final boolean changed(Object obj) {
        if (ResultKt.areEqual(nextSlot(), obj)) {
            return false;
        }
        updateValue(obj);
        return true;
    }

    public final boolean changedInstance(Object obj) {
        if (nextSlot() == obj) {
            return false;
        }
        updateValue(obj);
        return true;
    }

    public final void cleanUpCompose() {
        this.pending = null;
        this.nodeIndex = 0;
        this.groupNodeCount = 0;
        this.writersReaderDelta = 0;
        this.compoundKeyHash = 0;
        this.nodeExpected = false;
        this.startedGroup = false;
        this.startedGroups.tos = 0;
        this.invalidateStack.clear();
        this.nodeCountOverrides = null;
        this.nodeCountVirtualOverrides = null;
    }

    public final void composeContent$runtime_release(SnapshotWeakSet snapshotWeakSet, ComposableLambdaImpl composableLambdaImpl) {
        ResultKt.checkNotNullParameter(snapshotWeakSet, "invalidationsRequested");
        if (this.changes.isEmpty()) {
            doCompose(snapshotWeakSet, composableLambdaImpl);
        } else {
            EffectsKt.composeRuntimeError("Expected applyChanges() to have been called".toString());
            throw null;
        }
    }

    public final int compoundKeyOf(int i, int i2, int i3) {
        int i4;
        Object aux;
        if (i == i2) {
            return i3;
        }
        SlotReader slotReader = this.reader;
        boolean access$hasObjectKey = ResultKt.access$hasObjectKey(slotReader.groups, i);
        int[] iArr = slotReader.groups;
        if (access$hasObjectKey) {
            Object objectKey = slotReader.objectKey(iArr, i);
            i4 = objectKey != null ? objectKey instanceof Enum ? ((Enum) objectKey).ordinal() : objectKey.hashCode() : 0;
        } else {
            int i5 = iArr[i * 5];
            if (i5 == 207 && (aux = slotReader.aux(iArr, i)) != null && !ResultKt.areEqual(aux, Composer.Companion.Empty)) {
                i5 = aux.hashCode();
            }
            i4 = i5;
        }
        return i4 == 126665345 ? i4 : Integer.rotateLeft(compoundKeyOf(ResultKt.access$parentAnchor(this.reader.groups, i), i2, i3), 3) ^ i4;
    }

    public final Object consume(ProvidableCompositionLocal providableCompositionLocal) {
        ResultKt.checkNotNullParameter(providableCompositionLocal, "key");
        return _BOUNDARY.read(currentCompositionLocalScope(), providableCompositionLocal);
    }

    public final void createNode(final Function0 function0) {
        ResultKt.checkNotNullParameter(function0, "factory");
        if (!this.nodeExpected) {
            EffectsKt.composeRuntimeError("A call to createNode(), emitNode() or useNode() expected was not expected".toString());
            throw null;
        }
        int i = 0;
        this.nodeExpected = false;
        if (!this.inserting) {
            EffectsKt.composeRuntimeError("createNode() can only be called when inserting".toString());
            throw null;
        }
        final int i2 = this.nodeIndexStack.slots[r1.tos - 1];
        SlotWriter slotWriter = this.writer;
        final Anchor anchor = slotWriter.anchor(slotWriter.parent);
        this.groupNodeCount++;
        this.insertFixups.add(new Function3() { // from class: androidx.compose.runtime.ComposerImpl$createNode$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                Applier applier = (Applier) obj;
                SlotWriter slotWriter2 = (SlotWriter) obj2;
                ResultKt.checkNotNullParameter(applier, "applier");
                ResultKt.checkNotNullParameter(slotWriter2, "slots");
                ResultKt.checkNotNullParameter((CompositionImpl.RememberEventDispatcher) obj3, "<anonymous parameter 2>");
                Object invoke = Function0.this.invoke();
                Anchor anchor2 = anchor;
                ResultKt.checkNotNullParameter(anchor2, "anchor");
                slotWriter2.updateNodeOfGroup(slotWriter2.anchorIndex(anchor2), invoke);
                applier.insertTopDown(i2, invoke);
                applier.down(invoke);
                return Unit.INSTANCE;
            }
        });
        this.insertUpFixups.push(new ComposerImpl$createNode$3(i2, i, anchor));
    }

    public final PersistentCompositionLocalMap currentCompositionLocalScope() {
        PersistentCompositionLocalMap persistentCompositionLocalMap;
        Object obj;
        PersistentCompositionLocalMap persistentCompositionLocalMap2 = this.providerCache;
        if (persistentCompositionLocalMap2 != null) {
            return persistentCompositionLocalMap2;
        }
        int i = this.reader.parent;
        boolean z = this.inserting;
        OpaqueKey opaqueKey = EffectsKt.compositionLocalMap;
        if (z && this.writerHasAProvider) {
            int i2 = this.writer.parent;
            while (i2 > 0) {
                SlotWriter slotWriter = this.writer;
                if (slotWriter.groups[slotWriter.groupIndexToAddress(i2) * 5] == 202) {
                    SlotWriter slotWriter2 = this.writer;
                    int groupIndexToAddress = slotWriter2.groupIndexToAddress(i2);
                    if (ResultKt.access$hasObjectKey(slotWriter2.groups, groupIndexToAddress)) {
                        Object[] objArr = slotWriter2.slots;
                        int[] iArr = slotWriter2.groups;
                        int i3 = groupIndexToAddress * 5;
                        obj = objArr[ResultKt.countOneBits(iArr[i3 + 1] >> 30) + iArr[i3 + 4]];
                    } else {
                        obj = null;
                    }
                    if (ResultKt.areEqual(obj, opaqueKey)) {
                        SlotWriter slotWriter3 = this.writer;
                        int groupIndexToAddress2 = slotWriter3.groupIndexToAddress(i2);
                        Object obj2 = ResultKt.access$hasAux(slotWriter3.groups, groupIndexToAddress2) ? slotWriter3.slots[slotWriter3.auxIndex(slotWriter3.groups, groupIndexToAddress2)] : Composer.Companion.Empty;
                        ResultKt.checkNotNull(obj2, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
                        PersistentCompositionLocalMap persistentCompositionLocalMap3 = (PersistentCompositionLocalMap) obj2;
                        this.providerCache = persistentCompositionLocalMap3;
                        return persistentCompositionLocalMap3;
                    }
                }
                SlotWriter slotWriter4 = this.writer;
                i2 = slotWriter4.parent(slotWriter4.groups, i2);
            }
        }
        if (this.reader.groupsSize > 0) {
            while (i > 0) {
                SlotReader slotReader = this.reader;
                int[] iArr2 = slotReader.groups;
                if (iArr2[i * 5] == 202 && ResultKt.areEqual(slotReader.objectKey(iArr2, i), opaqueKey)) {
                    PersistentCompositionLocalMap persistentCompositionLocalMap4 = (PersistentCompositionLocalMap) ((SparseArray) this.providerUpdates.backing).get(i);
                    if (persistentCompositionLocalMap4 == null) {
                        SlotReader slotReader2 = this.reader;
                        Object aux = slotReader2.aux(slotReader2.groups, i);
                        ResultKt.checkNotNull(aux, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
                        persistentCompositionLocalMap = (PersistentCompositionLocalMap) aux;
                    } else {
                        persistentCompositionLocalMap = persistentCompositionLocalMap4;
                    }
                    this.providerCache = persistentCompositionLocalMap;
                    return persistentCompositionLocalMap;
                }
                i = ResultKt.access$parentAnchor(this.reader.groups, i);
            }
        }
        PersistentCompositionLocalHashMap persistentCompositionLocalHashMap = this.parentProvider;
        this.providerCache = persistentCompositionLocalHashMap;
        return persistentCompositionLocalHashMap;
    }

    public final void dispose$runtime_release() {
        Trace.beginSection("Compose:Composer.dispose");
        try {
            this.parentContext.getClass();
            this.invalidateStack.clear();
            this.invalidations.clear();
            this.changes.clear();
            this.providerUpdates.clear();
            ((AbstractApplier) this.applier).clear();
        } finally {
            Trace.endSection();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0052, code lost:
    
        if (r5.size() <= 1) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0054, code lost:
    
        kotlin.collections.CollectionsKt__MutableCollectionsJVMKt.sortWith(r5, new androidx.compose.ui.node.DepthSortedSet$DepthComparator$1(r1));
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x005c, code lost:
    
        r10.nodeIndex = 0;
        r10.isComposing = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0060, code lost:
    
        startRoot();
        r11 = nextSlot();
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0067, code lost:
    
        if (r11 == r12) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0069, code lost:
    
        if (r12 == null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x006b, code lost:
    
        updateValue(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0071, code lost:
    
        r0 = r10.derivedStateObserver;
        r4 = kotlin.ResultKt.derivedStateObservers();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0077, code lost:
    
        r4.add(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x007a, code lost:
    
        r0 = androidx.compose.runtime.EffectsKt.invocation;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007e, code lost:
    
        if (r12 == null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0080, code lost:
    
        m59startBaiHCIY(200, 0, r0, null);
        _COROUTINE._BOUNDARY.invokeComposable(r10, r12);
        end(false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00ad, code lost:
    
        r4.removeAt(r4.size - 1);
        endRoot();
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00b6, code lost:
    
        r10.isComposing = false;
        r5.clear();
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00be, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x008e, code lost:
    
        if (r10.providersInvalid == false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0090, code lost:
    
        if (r11 == null) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0098, code lost:
    
        if (kotlin.ResultKt.areEqual(r11, androidx.compose.runtime.Composer.Companion.Empty) != false) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x009a, code lost:
    
        m59startBaiHCIY(200, 0, r0, null);
        kotlin.ResultKt.beforeCheckcastToFunctionOfArity(2, r11);
        _COROUTINE._BOUNDARY.invokeComposable(r10, (kotlin.jvm.functions.Function2) r11);
        end(false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00aa, code lost:
    
        skipCurrentGroup();
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x008a, code lost:
    
        r11 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00bf, code lost:
    
        r4.removeAt(r4.size - 1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00c5, code lost:
    
        throw r11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x006f, code lost:
    
        r11 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00c6, code lost:
    
        r10.isComposing = false;
        r5.clear();
        abortRoot();
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00ce, code lost:
    
        throw r11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void doCompose(androidx.compose.runtime.snapshots.SnapshotWeakSet r11, androidx.compose.runtime.internal.ComposableLambdaImpl r12) {
        /*
            r10 = this;
            boolean r0 = r10.isComposing
            r1 = 1
            r0 = r0 ^ r1
            r2 = 0
            if (r0 == 0) goto Ld3
            java.lang.String r0 = "Compose:recompose"
            android.os.Trace.beginSection(r0)
            androidx.compose.runtime.snapshots.Snapshot r0 = androidx.compose.runtime.snapshots.SnapshotKt.currentSnapshot()     // Catch: java.lang.Throwable -> L47
            int r0 = r0.getId()     // Catch: java.lang.Throwable -> L47
            r10.compositionToken = r0     // Catch: java.lang.Throwable -> L47
            androidx.compose.runtime.Stack r0 = r10.providerUpdates     // Catch: java.lang.Throwable -> L47
            r0.clear()     // Catch: java.lang.Throwable -> L47
            int r0 = r11.size     // Catch: java.lang.Throwable -> L47
            r3 = 0
            r4 = r3
        L1f:
            java.util.ArrayList r5 = r10.invalidations
            if (r4 >= r0) goto L4e
            java.lang.Object r6 = r11.hashes     // Catch: java.lang.Throwable -> L47
            java.lang.Object[] r6 = (java.lang.Object[]) r6     // Catch: java.lang.Throwable -> L47
            r6 = r6[r4]     // Catch: java.lang.Throwable -> L47
            java.lang.String r7 = "null cannot be cast to non-null type Key of androidx.compose.runtime.collection.IdentityArrayMap"
            kotlin.ResultKt.checkNotNull(r6, r7)     // Catch: java.lang.Throwable -> L47
            java.lang.Object[] r7 = r11.values     // Catch: java.lang.Throwable -> L47
            r7 = r7[r4]     // Catch: java.lang.Throwable -> L47
            androidx.compose.runtime.collection.IdentityArraySet r7 = (androidx.compose.runtime.collection.IdentityArraySet) r7     // Catch: java.lang.Throwable -> L47
            androidx.compose.runtime.RecomposeScopeImpl r6 = (androidx.compose.runtime.RecomposeScopeImpl) r6     // Catch: java.lang.Throwable -> L47
            androidx.compose.runtime.Anchor r8 = r6.anchor     // Catch: java.lang.Throwable -> L47
            if (r8 == 0) goto L4a
            int r8 = r8.location     // Catch: java.lang.Throwable -> L47
            androidx.compose.runtime.Invalidation r9 = new androidx.compose.runtime.Invalidation     // Catch: java.lang.Throwable -> L47
            r9.<init>(r6, r8, r7)     // Catch: java.lang.Throwable -> L47
            r5.add(r9)     // Catch: java.lang.Throwable -> L47
            int r4 = r4 + 1
            goto L1f
        L47:
            r11 = move-exception
            goto Lcf
        L4a:
            android.os.Trace.endSection()
            return
        L4e:
            int r11 = r5.size()     // Catch: java.lang.Throwable -> L47
            if (r11 <= r1) goto L5c
            androidx.compose.ui.node.DepthSortedSet$DepthComparator$1 r11 = new androidx.compose.ui.node.DepthSortedSet$DepthComparator$1     // Catch: java.lang.Throwable -> L47
            r11.<init>(r1)     // Catch: java.lang.Throwable -> L47
            kotlin.collections.CollectionsKt__MutableCollectionsJVMKt.sortWith(r5, r11)     // Catch: java.lang.Throwable -> L47
        L5c:
            r10.nodeIndex = r3     // Catch: java.lang.Throwable -> L47
            r10.isComposing = r1     // Catch: java.lang.Throwable -> L47
            r10.startRoot()     // Catch: java.lang.Throwable -> L6f
            java.lang.Object r11 = r10.nextSlot()     // Catch: java.lang.Throwable -> L6f
            if (r11 == r12) goto L71
            if (r12 == 0) goto L71
            r10.updateValue(r12)     // Catch: java.lang.Throwable -> L6f
            goto L71
        L6f:
            r11 = move-exception
            goto Lc6
        L71:
            androidx.compose.runtime.ComposerImpl$derivedStateObserver$1 r0 = r10.derivedStateObserver     // Catch: java.lang.Throwable -> L6f
            androidx.compose.runtime.collection.MutableVector r4 = kotlin.ResultKt.derivedStateObservers()     // Catch: java.lang.Throwable -> L6f
            r4.add(r0)     // Catch: java.lang.Throwable -> L8a
            androidx.compose.runtime.OpaqueKey r0 = androidx.compose.runtime.EffectsKt.invocation
            r6 = 200(0xc8, float:2.8E-43)
            if (r12 == 0) goto L8c
            r10.m59startBaiHCIY(r6, r3, r0, r2)     // Catch: java.lang.Throwable -> L8a
            _COROUTINE._BOUNDARY.invokeComposable(r10, r12)     // Catch: java.lang.Throwable -> L8a
            r10.end(r3)     // Catch: java.lang.Throwable -> L8a
            goto Lad
        L8a:
            r11 = move-exception
            goto Lbf
        L8c:
            boolean r12 = r10.providersInvalid     // Catch: java.lang.Throwable -> L8a
            if (r12 == 0) goto Laa
            if (r11 == 0) goto Laa
            _COROUTINE.ArtificialStackFrames r12 = androidx.compose.runtime.Composer.Companion.Empty     // Catch: java.lang.Throwable -> L8a
            boolean r12 = kotlin.ResultKt.areEqual(r11, r12)     // Catch: java.lang.Throwable -> L8a
            if (r12 != 0) goto Laa
            r10.m59startBaiHCIY(r6, r3, r0, r2)     // Catch: java.lang.Throwable -> L8a
            r12 = 2
            kotlin.ResultKt.beforeCheckcastToFunctionOfArity(r12, r11)     // Catch: java.lang.Throwable -> L8a
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11     // Catch: java.lang.Throwable -> L8a
            _COROUTINE._BOUNDARY.invokeComposable(r10, r11)     // Catch: java.lang.Throwable -> L8a
            r10.end(r3)     // Catch: java.lang.Throwable -> L8a
            goto Lad
        Laa:
            r10.skipCurrentGroup()     // Catch: java.lang.Throwable -> L8a
        Lad:
            int r11 = r4.size     // Catch: java.lang.Throwable -> L6f
            int r11 = r11 - r1
            r4.removeAt(r11)     // Catch: java.lang.Throwable -> L6f
            r10.endRoot()     // Catch: java.lang.Throwable -> L6f
            r10.isComposing = r3     // Catch: java.lang.Throwable -> L47
            r5.clear()     // Catch: java.lang.Throwable -> L47
            android.os.Trace.endSection()
            return
        Lbf:
            int r12 = r4.size     // Catch: java.lang.Throwable -> L6f
            int r12 = r12 - r1
            r4.removeAt(r12)     // Catch: java.lang.Throwable -> L6f
            throw r11     // Catch: java.lang.Throwable -> L6f
        Lc6:
            r10.isComposing = r3     // Catch: java.lang.Throwable -> L47
            r5.clear()     // Catch: java.lang.Throwable -> L47
            r10.abortRoot()     // Catch: java.lang.Throwable -> L47
            throw r11     // Catch: java.lang.Throwable -> L47
        Lcf:
            android.os.Trace.endSection()
            throw r11
        Ld3:
            java.lang.String r11 = "Reentrant composition is not supported"
            java.lang.String r11 = r11.toString()
            androidx.compose.runtime.EffectsKt.composeRuntimeError(r11)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.doCompose(androidx.compose.runtime.snapshots.SnapshotWeakSet, androidx.compose.runtime.internal.ComposableLambdaImpl):void");
    }

    public final void doRecordDownsFor(int i, int i2) {
        if (i <= 0 || i == i2) {
            return;
        }
        doRecordDownsFor(ResultKt.access$parentAnchor(this.reader.groups, i), i2);
        if (ResultKt.access$isNode(this.reader.groups, i)) {
            this.downNodes.push(this.reader.node(i));
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r23v0, resolved type: androidx.compose.runtime.ComposerImpl */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v24, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v58 */
    public final void end(boolean z) {
        boolean z2;
        ?? r4;
        int i;
        HashSet hashSet;
        Pending pending;
        ArrayList arrayList;
        LinkedHashSet linkedHashSet;
        int i2;
        int i3;
        Object obj;
        if (this.inserting) {
            SlotWriter slotWriter = this.writer;
            int i4 = slotWriter.parent;
            int i5 = slotWriter.groups[slotWriter.groupIndexToAddress(i4) * 5];
            SlotWriter slotWriter2 = this.writer;
            int groupIndexToAddress = slotWriter2.groupIndexToAddress(i4);
            if (ResultKt.access$hasObjectKey(slotWriter2.groups, groupIndexToAddress)) {
                Object[] objArr = slotWriter2.slots;
                int[] iArr = slotWriter2.groups;
                int i6 = groupIndexToAddress * 5;
                obj = objArr[ResultKt.countOneBits(iArr[i6 + 1] >> 30) + iArr[i6 + 4]];
            } else {
                obj = null;
            }
            SlotWriter slotWriter3 = this.writer;
            int groupIndexToAddress2 = slotWriter3.groupIndexToAddress(i4);
            updateCompoundKeyWhenWeExitGroup(obj, i5, ResultKt.access$hasAux(slotWriter3.groups, groupIndexToAddress2) ? slotWriter3.slots[slotWriter3.auxIndex(slotWriter3.groups, groupIndexToAddress2)] : Composer.Companion.Empty);
        } else {
            SlotReader slotReader = this.reader;
            int i7 = slotReader.parent;
            int[] iArr2 = slotReader.groups;
            int i8 = iArr2[i7 * 5];
            Object objectKey = slotReader.objectKey(iArr2, i7);
            SlotReader slotReader2 = this.reader;
            updateCompoundKeyWhenWeExitGroup(objectKey, i8, slotReader2.aux(slotReader2.groups, i7));
        }
        int i9 = this.groupNodeCount;
        Pending pending2 = this.pending;
        ArrayList arrayList2 = this.invalidations;
        if (pending2 != null) {
            List list = pending2.keyInfos;
            if (list.size() > 0) {
                ArrayList arrayList3 = pending2.usedKeys;
                ResultKt.checkNotNullParameter(arrayList3, "<this>");
                HashSet hashSet2 = new HashSet(arrayList3.size());
                int size = arrayList3.size();
                for (int i10 = 0; i10 < size; i10++) {
                    hashSet2.add(arrayList3.get(i10));
                }
                LinkedHashSet linkedHashSet2 = new LinkedHashSet();
                int size2 = arrayList3.size();
                int size3 = list.size();
                int i11 = 0;
                int i12 = 0;
                int i13 = 0;
                while (i11 < size3) {
                    KeyInfo keyInfo = (KeyInfo) list.get(i11);
                    boolean contains = hashSet2.contains(keyInfo);
                    int i14 = pending2.startIndex;
                    if (contains) {
                        hashSet = hashSet2;
                        if (!linkedHashSet2.contains(keyInfo)) {
                            if (i12 < size2) {
                                KeyInfo keyInfo2 = (KeyInfo) arrayList3.get(i12);
                                HashMap hashMap = pending2.groupInfos;
                                if (keyInfo2 != keyInfo) {
                                    int nodePositionOf = pending2.nodePositionOf(keyInfo2);
                                    linkedHashSet2.add(keyInfo2);
                                    pending = pending2;
                                    if (nodePositionOf != i13) {
                                        GroupInfo groupInfo = (GroupInfo) hashMap.get(Integer.valueOf(keyInfo2.location));
                                        int i15 = groupInfo != null ? groupInfo.nodeCount : keyInfo2.nodes;
                                        arrayList = arrayList3;
                                        int i16 = nodePositionOf + i14;
                                        int i17 = i14 + i13;
                                        linkedHashSet = linkedHashSet2;
                                        if (i15 > 0) {
                                            int i18 = this.previousCount;
                                            i2 = size2;
                                            if (i18 > 0) {
                                                i3 = size3;
                                                if (this.previousMoveFrom == i16 - i18 && this.previousMoveTo == i17 - i18) {
                                                    this.previousCount = i18 + i15;
                                                }
                                            } else {
                                                i3 = size3;
                                            }
                                            realizeMovement();
                                            this.previousMoveFrom = i16;
                                            this.previousMoveTo = i17;
                                            this.previousCount = i15;
                                        } else {
                                            i2 = size2;
                                            i3 = size3;
                                        }
                                        if (nodePositionOf > i13) {
                                            Collection<GroupInfo> values = hashMap.values();
                                            ResultKt.checkNotNullExpressionValue(values, "groupInfos.values");
                                            for (GroupInfo groupInfo2 : values) {
                                                int i19 = groupInfo2.nodeIndex;
                                                if (nodePositionOf <= i19 && i19 < nodePositionOf + i15) {
                                                    groupInfo2.nodeIndex = (i19 - nodePositionOf) + i13;
                                                } else if (i13 <= i19 && i19 < nodePositionOf) {
                                                    groupInfo2.nodeIndex = i19 + i15;
                                                }
                                            }
                                        } else if (i13 > nodePositionOf) {
                                            Collection<GroupInfo> values2 = hashMap.values();
                                            ResultKt.checkNotNullExpressionValue(values2, "groupInfos.values");
                                            for (GroupInfo groupInfo3 : values2) {
                                                int i20 = groupInfo3.nodeIndex;
                                                if (nodePositionOf <= i20 && i20 < nodePositionOf + i15) {
                                                    groupInfo3.nodeIndex = (i20 - nodePositionOf) + i13;
                                                } else if (nodePositionOf + 1 <= i20 && i20 < i13) {
                                                    groupInfo3.nodeIndex = i20 - i15;
                                                }
                                            }
                                        }
                                    } else {
                                        arrayList = arrayList3;
                                        linkedHashSet = linkedHashSet2;
                                        i2 = size2;
                                        i3 = size3;
                                    }
                                } else {
                                    pending = pending2;
                                    arrayList = arrayList3;
                                    linkedHashSet = linkedHashSet2;
                                    i2 = size2;
                                    i3 = size3;
                                    i11++;
                                }
                                i12++;
                                ResultKt.checkNotNullParameter(keyInfo2, "keyInfo");
                                GroupInfo groupInfo4 = (GroupInfo) hashMap.get(Integer.valueOf(keyInfo2.location));
                                i13 += groupInfo4 != null ? groupInfo4.nodeCount : keyInfo2.nodes;
                                hashSet2 = hashSet;
                                pending2 = pending;
                                arrayList3 = arrayList;
                                linkedHashSet2 = linkedHashSet;
                                size2 = i2;
                                size3 = i3;
                            }
                            hashSet2 = hashSet;
                        }
                    } else {
                        recordRemoveNode(pending2.nodePositionOf(keyInfo) + i14, keyInfo.nodes);
                        int i21 = keyInfo.location;
                        pending2.updateNodeCount(i21, 0);
                        SlotReader slotReader3 = this.reader;
                        hashSet = hashSet2;
                        this.writersReaderDelta = i21 - (slotReader3.currentGroup - this.writersReaderDelta);
                        slotReader3.reposition(i21);
                        recordDelete();
                        this.reader.skipGroup();
                        int access$groupSize = ResultKt.access$groupSize(this.reader.groups, i21) + i21;
                        int findLocation = EffectsKt.findLocation(i21, arrayList2);
                        if (findLocation < 0) {
                            findLocation = -(findLocation + 1);
                        }
                        while (findLocation < arrayList2.size() && ((Invalidation) arrayList2.get(findLocation)).location < access$groupSize) {
                            arrayList2.remove(findLocation);
                        }
                    }
                    i11++;
                    hashSet2 = hashSet;
                }
                realizeMovement();
                if (list.size() > 0) {
                    SlotReader slotReader4 = this.reader;
                    this.writersReaderDelta = slotReader4.currentEnd - (slotReader4.currentGroup - this.writersReaderDelta);
                    slotReader4.skipToGroupEnd();
                }
            }
        }
        int i22 = this.nodeIndex;
        while (true) {
            SlotReader slotReader5 = this.reader;
            if (slotReader5.emptyCount <= 0 && (i = slotReader5.currentGroup) != slotReader5.currentEnd) {
                recordDelete();
                recordRemoveNode(i22, this.reader.skipGroup());
                int i23 = this.reader.currentGroup;
                int findLocation2 = EffectsKt.findLocation(i, arrayList2);
                if (findLocation2 < 0) {
                    findLocation2 = -(findLocation2 + 1);
                }
                while (findLocation2 < arrayList2.size() && ((Invalidation) arrayList2.get(findLocation2)).location < i23) {
                    arrayList2.remove(findLocation2);
                }
            }
        }
        boolean z3 = this.inserting;
        IntStack intStack = this.startedGroups;
        if (z3) {
            ArrayList arrayList4 = this.insertFixups;
            if (z) {
                arrayList4.add(this.insertUpFixups.pop());
                i9 = 1;
            }
            SlotReader slotReader6 = this.reader;
            int i24 = slotReader6.emptyCount;
            if (i24 <= 0) {
                throw new IllegalArgumentException("Unbalanced begin/end empty".toString());
            }
            slotReader6.emptyCount = i24 - 1;
            SlotWriter slotWriter4 = this.writer;
            int i25 = slotWriter4.parent;
            slotWriter4.endGroup();
            if (this.reader.emptyCount <= 0) {
                int i26 = (-2) - i25;
                this.writer.endInsert();
                this.writer.close();
                final Anchor anchor = this.insertAnchor;
                boolean isEmpty = arrayList4.isEmpty();
                int i27 = 2;
                ComposerImpl$useNode$2 composerImpl$useNode$2 = ComposerImpl$useNode$2.INSTANCE$6;
                if (isEmpty) {
                    ComposerImpl$recordInsert$1 composerImpl$recordInsert$1 = new ComposerImpl$recordInsert$1(this.insertTable, 0, anchor);
                    realizeOperationLocation(false);
                    SlotReader slotReader7 = this.reader;
                    if (slotReader7.groupsSize > 0) {
                        int i28 = slotReader7.parent;
                        int i29 = intStack.tos;
                        if ((i29 > 0 ? intStack.slots[i29 - 1] : -2) != i28) {
                            if (!this.startedGroup && this.implicitRootStart) {
                                realizeOperationLocation(false);
                                record(composerImpl$useNode$2);
                                this.startedGroup = true;
                            }
                            if (i28 > 0) {
                                Anchor anchor2 = slotReader7.anchor(i28);
                                intStack.push(i28);
                                ComposerImpl$realizeDowns$1 composerImpl$realizeDowns$1 = new ComposerImpl$realizeDowns$1(i27, anchor2);
                                realizeOperationLocation(false);
                                record(composerImpl$realizeDowns$1);
                            }
                        }
                    }
                    record(composerImpl$recordInsert$1);
                    r4 = 0;
                } else {
                    final ArrayList mutableList = CollectionsKt___CollectionsKt.toMutableList(arrayList4);
                    arrayList4.clear();
                    realizeUps();
                    realizeDowns$1();
                    final SlotTable slotTable = this.insertTable;
                    Function3 function3 = new Function3() { // from class: androidx.compose.runtime.ComposerImpl$recordInsert$2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj2, Object obj3, Object obj4) {
                            Applier applier = (Applier) obj2;
                            SlotWriter slotWriter5 = (SlotWriter) obj3;
                            CompositionImpl.RememberEventDispatcher rememberEventDispatcher = (CompositionImpl.RememberEventDispatcher) obj4;
                            ResultKt.checkNotNullParameter(applier, "applier");
                            ResultKt.checkNotNullParameter(slotWriter5, "slots");
                            ResultKt.checkNotNullParameter(rememberEventDispatcher, "rememberManager");
                            List list2 = mutableList;
                            SlotTable slotTable2 = SlotTable.this;
                            SlotWriter openWriter = slotTable2.openWriter();
                            try {
                                int size4 = list2.size();
                                for (int i30 = 0; i30 < size4; i30++) {
                                    ((Function3) list2.get(i30)).invoke(applier, openWriter, rememberEventDispatcher);
                                }
                                openWriter.close();
                                slotWriter5.beginInsert();
                                Anchor anchor3 = anchor;
                                anchor3.getClass();
                                slotWriter5.moveFrom(slotTable2, slotTable2.anchorIndex(anchor3));
                                slotWriter5.endInsert();
                                return Unit.INSTANCE;
                            } catch (Throwable th) {
                                openWriter.close();
                                throw th;
                            }
                        }
                    };
                    realizeOperationLocation(false);
                    SlotReader slotReader8 = this.reader;
                    if (slotReader8.groupsSize > 0) {
                        int i30 = slotReader8.parent;
                        int i31 = intStack.tos;
                        if ((i31 > 0 ? intStack.slots[i31 - 1] : -2) != i30) {
                            if (!this.startedGroup && this.implicitRootStart) {
                                realizeOperationLocation(false);
                                record(composerImpl$useNode$2);
                                this.startedGroup = true;
                            }
                            if (i30 > 0) {
                                Anchor anchor3 = slotReader8.anchor(i30);
                                intStack.push(i30);
                                ComposerImpl$realizeDowns$1 composerImpl$realizeDowns$12 = new ComposerImpl$realizeDowns$1(i27, anchor3);
                                z2 = false;
                                realizeOperationLocation(false);
                                record(composerImpl$realizeDowns$12);
                                record(function3);
                                r4 = z2;
                            }
                        }
                    }
                    z2 = false;
                    record(function3);
                    r4 = z2;
                }
                this.inserting = r4;
                if (this.slotTable.groupsSize != 0) {
                    updateNodeCount(i26, r4);
                    updateNodeCountOverrides(i26, i9);
                }
            }
        } else {
            if (z) {
                recordUp();
            }
            int i32 = this.reader.parent;
            int i33 = intStack.tos;
            if (!((i33 > 0 ? intStack.slots[i33 + (-1)] : -1) <= i32)) {
                EffectsKt.composeRuntimeError("Missed recording an endGroup".toString());
                throw null;
            }
            if ((i33 > 0 ? intStack.slots[i33 - 1] : -1) == i32) {
                intStack.pop();
                ComposerImpl$useNode$2 composerImpl$useNode$22 = ComposerImpl$useNode$2.INSTANCE$2;
                realizeOperationLocation(false);
                record(composerImpl$useNode$22);
            }
            int i34 = this.reader.parent;
            if (i9 != updatedNodeCount(i34)) {
                updateNodeCountOverrides(i34, i9);
            }
            if (z) {
                i9 = 1;
            }
            this.reader.endGroup();
            realizeMovement();
        }
        Pending pending3 = (Pending) this.pendingStack.pop();
        if (pending3 != null && !z3) {
            pending3.groupIndex++;
        }
        this.pending = pending3;
        this.nodeIndex = this.nodeIndexStack.pop() + i9;
        this.groupNodeCount = this.groupNodeCountStack.pop() + i9;
    }

    public final void endDefaults() {
        end(false);
        RecomposeScopeImpl currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release();
        if (currentRecomposeScope$runtime_release != null) {
            int i = currentRecomposeScope$runtime_release.flags;
            if ((i & 1) != 0) {
                currentRecomposeScope$runtime_release.flags = i | 2;
            }
        }
    }

    public final RecomposeScopeImpl endRestartGroup() {
        Anchor anchor;
        RecomposeScopeImpl$end$1$2 recomposeScopeImpl$end$1$2;
        Stack stack = this.invalidateStack;
        RecomposeScopeImpl recomposeScopeImpl = null;
        RecomposeScopeImpl recomposeScopeImpl2 = stack.isNotEmpty() ? (RecomposeScopeImpl) stack.pop() : null;
        if (recomposeScopeImpl2 != null) {
            recomposeScopeImpl2.flags &= -9;
        }
        int i = 0;
        if (recomposeScopeImpl2 != null) {
            int i2 = this.compositionToken;
            IdentityArrayIntMap identityArrayIntMap = recomposeScopeImpl2.trackedInstances;
            if (identityArrayIntMap != null && (recomposeScopeImpl2.flags & 16) == 0) {
                Object[] objArr = identityArrayIntMap.keys;
                int[] iArr = identityArrayIntMap.values;
                int i3 = identityArrayIntMap.size;
                for (int i4 = 0; i4 < i3; i4++) {
                    ResultKt.checkNotNull(objArr[i4], "null cannot be cast to non-null type kotlin.Any");
                    if (iArr[i4] != i2) {
                        recomposeScopeImpl$end$1$2 = new RecomposeScopeImpl$end$1$2(i2, i, recomposeScopeImpl2, identityArrayIntMap);
                        break;
                    }
                }
            }
            recomposeScopeImpl$end$1$2 = null;
            if (recomposeScopeImpl$end$1$2 != null) {
                record(new ComposerImpl$recordInsert$1(recomposeScopeImpl$end$1$2, 2, this));
            }
        }
        if (recomposeScopeImpl2 != null) {
            int i5 = recomposeScopeImpl2.flags;
            if ((i5 & 16) == 0 && ((i5 & 1) != 0 || this.forceRecomposeScopes)) {
                if (recomposeScopeImpl2.anchor == null) {
                    if (this.inserting) {
                        SlotWriter slotWriter = this.writer;
                        anchor = slotWriter.anchor(slotWriter.parent);
                    } else {
                        SlotReader slotReader = this.reader;
                        anchor = slotReader.anchor(slotReader.parent);
                    }
                    recomposeScopeImpl2.anchor = anchor;
                }
                recomposeScopeImpl2.flags &= -5;
                recomposeScopeImpl = recomposeScopeImpl2;
            }
        }
        end(false);
        return recomposeScopeImpl;
    }

    public final void endRoot() {
        end(false);
        this.parentContext.getClass();
        end(false);
        if (this.startedGroup) {
            ComposerImpl$useNode$2 composerImpl$useNode$2 = ComposerImpl$useNode$2.INSTANCE$2;
            realizeOperationLocation(false);
            record(composerImpl$useNode$2);
            this.startedGroup = false;
        }
        realizeUps();
        if (!((ArrayList) this.pendingStack.backing).isEmpty()) {
            EffectsKt.composeRuntimeError("Start/end imbalance".toString());
            throw null;
        }
        if (this.startedGroups.tos != 0) {
            EffectsKt.composeRuntimeError("Missed recording an endGroup()".toString());
            throw null;
        }
        cleanUpCompose();
        this.reader.close();
    }

    public final void enterGroup(boolean z, Pending pending) {
        this.pendingStack.push(this.pending);
        this.pending = pending;
        this.nodeIndexStack.push(this.nodeIndex);
        if (z) {
            this.nodeIndex = 0;
        }
        this.groupNodeCountStack.push(this.groupNodeCount);
        this.groupNodeCount = 0;
    }

    public final RecomposeScopeImpl getCurrentRecomposeScope$runtime_release() {
        Stack stack = this.invalidateStack;
        if (this.childrenComposing != 0 || !stack.isNotEmpty()) {
            return null;
        }
        return (RecomposeScopeImpl) ((ArrayList) stack.backing).get(((ArrayList) r0).size() - 1);
    }

    public final boolean getDefaultsInvalid() {
        RecomposeScopeImpl currentRecomposeScope$runtime_release;
        return this.providersInvalid || !((currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release()) == null || (currentRecomposeScope$runtime_release.flags & 4) == 0);
    }

    public final boolean getSkipping() {
        RecomposeScopeImpl currentRecomposeScope$runtime_release;
        return (this.inserting || this.reusing || this.providersInvalid || (currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release()) == null || (currentRecomposeScope$runtime_release.flags & 8) != 0) ? false : true;
    }

    public final void insertMovableContentGuarded(ArrayList arrayList) {
        List list = this.lateChanges;
        List list2 = this.changes;
        try {
            this.changes = list;
            record(ComposerImpl$useNode$2.INSTANCE$4);
            if (arrayList.size() <= 0) {
                record(ComposerImpl$useNode$2.INSTANCE$1);
                this.writersReaderDelta = 0;
            } else {
                Pair pair = (Pair) arrayList.get(0);
                DurationKt$$ExternalSyntheticCheckNotZero0.m(pair.first);
                DurationKt$$ExternalSyntheticCheckNotZero0.m(pair.second);
                throw null;
            }
        } finally {
            this.changes = list2;
        }
    }

    public final Object nextSlot() {
        Object obj;
        int i;
        boolean z = this.inserting;
        ArtificialStackFrames artificialStackFrames = Composer.Companion.Empty;
        if (z) {
            if (!this.nodeExpected) {
                return artificialStackFrames;
            }
            EffectsKt.composeRuntimeError("A call to createNode(), emitNode() or useNode() expected".toString());
            throw null;
        }
        SlotReader slotReader = this.reader;
        if (slotReader.emptyCount > 0 || (i = slotReader.currentSlot) >= slotReader.currentSlotEnd) {
            obj = artificialStackFrames;
        } else {
            slotReader.currentSlot = i + 1;
            obj = slotReader.slots[i];
        }
        return this.reusing ? artificialStackFrames : obj;
    }

    public final void realizeDowns$1() {
        Stack stack = this.downNodes;
        if (stack.isNotEmpty()) {
            int size = ((ArrayList) stack.backing).size();
            Object[] objArr = new Object[size];
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                objArr[i2] = ((ArrayList) stack.backing).get(i2);
            }
            record(new ComposerImpl$realizeDowns$1(i, objArr));
            stack.clear();
        }
    }

    public final void realizeMovement() {
        final int i = this.previousCount;
        this.previousCount = 0;
        if (i > 0) {
            final int i2 = this.previousRemove;
            if (i2 >= 0) {
                this.previousRemove = -1;
                Function3 function3 = new Function3() { // from class: androidx.compose.runtime.ComposerImpl$realizeMovement$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                        Applier applier = (Applier) obj;
                        ResultKt.checkNotNullParameter(applier, "applier");
                        ResultKt.checkNotNullParameter((SlotWriter) obj2, "<anonymous parameter 1>");
                        ResultKt.checkNotNullParameter((CompositionImpl.RememberEventDispatcher) obj3, "<anonymous parameter 2>");
                        applier.remove(i2, i);
                        return Unit.INSTANCE;
                    }
                };
                realizeUps();
                realizeDowns$1();
                record(function3);
                return;
            }
            final int i3 = this.previousMoveFrom;
            this.previousMoveFrom = -1;
            final int i4 = this.previousMoveTo;
            this.previousMoveTo = -1;
            Function3 function32 = new Function3() { // from class: androidx.compose.runtime.ComposerImpl$realizeMovement$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    Applier applier = (Applier) obj;
                    ResultKt.checkNotNullParameter(applier, "applier");
                    ResultKt.checkNotNullParameter((SlotWriter) obj2, "<anonymous parameter 1>");
                    ResultKt.checkNotNullParameter((CompositionImpl.RememberEventDispatcher) obj3, "<anonymous parameter 2>");
                    applier.move(i3, i4, i);
                    return Unit.INSTANCE;
                }
            };
            realizeUps();
            realizeDowns$1();
            record(function32);
        }
    }

    public final void realizeOperationLocation(boolean z) {
        int i = z ? this.reader.parent : this.reader.currentGroup;
        int i2 = i - this.writersReaderDelta;
        if (i2 < 0) {
            EffectsKt.composeRuntimeError("Tried to seek backward".toString());
            throw null;
        }
        if (i2 > 0) {
            record(new ComposerImpl$start$2(i2, 1));
            this.writersReaderDelta = i;
        }
    }

    public final void realizeUps() {
        int i = this.pendingUps;
        if (i > 0) {
            this.pendingUps = 0;
            record(new ComposerImpl$start$2(i, 2));
        }
    }

    public final boolean recompose$runtime_release(SnapshotWeakSet snapshotWeakSet) {
        ResultKt.checkNotNullParameter(snapshotWeakSet, "invalidationsRequested");
        if (!this.changes.isEmpty()) {
            EffectsKt.composeRuntimeError("Expected applyChanges() to have been called".toString());
            throw null;
        }
        if (snapshotWeakSet.size <= 0 && !(!this.invalidations.isEmpty())) {
            return false;
        }
        doCompose(snapshotWeakSet, null);
        return !this.changes.isEmpty();
    }

    /* JADX DEBUG: Another duplicated slice has different insns count: {[CONST, IPUT]}, finally: {[CONST] complete} */
    /* JADX DEBUG: Incorrect finally slice size: {[CONST, IPUT] complete}, expected: {[CONST] complete} */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0035, code lost:
    
        if (r9.location < r3) goto L11;
     */
    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x019f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void recomposeToGroupEnd() {
        /*
            r21 = this;
            r1 = r21
            boolean r0 = r1.isComposing
            r2 = 1
            r1.isComposing = r2
            androidx.compose.runtime.SlotReader r3 = r1.reader
            int r4 = r3.parent
            int[] r3 = r3.groups
            int r3 = kotlin.ResultKt.access$groupSize(r3, r4)
            int r3 = r3 + r4
            int r5 = r1.nodeIndex
            int r6 = r1.compoundKeyHash
            int r7 = r1.groupNodeCount
            java.util.ArrayList r8 = r1.invalidations
            androidx.compose.runtime.SlotReader r9 = r1.reader
            int r9 = r9.currentGroup
            int r9 = androidx.compose.runtime.EffectsKt.findLocation(r9, r8)
            if (r9 >= 0) goto L27
            int r9 = r9 + 1
            int r9 = -r9
        L27:
            int r10 = r8.size()
            if (r9 >= r10) goto L38
            java.lang.Object r9 = r8.get(r9)
            androidx.compose.runtime.Invalidation r9 = (androidx.compose.runtime.Invalidation) r9
            int r10 = r9.location
            if (r10 >= r3) goto L38
            goto L39
        L38:
            r9 = 0
        L39:
            r13 = r4
            r12 = 0
        L3b:
            if (r9 == 0) goto L1dc
            int r14 = r9.location
            int r15 = androidx.compose.runtime.EffectsKt.findLocation(r14, r8)
            if (r15 < 0) goto L4b
            java.lang.Object r15 = r8.remove(r15)
            androidx.compose.runtime.Invalidation r15 = (androidx.compose.runtime.Invalidation) r15
        L4b:
            androidx.compose.runtime.collection.IdentityArraySet r15 = r9.instances
            androidx.compose.runtime.RecomposeScopeImpl r9 = r9.scope
            if (r15 != 0) goto L58
            r9.getClass()
        L54:
            r18 = r0
            goto Le6
        L58:
            androidx.compose.runtime.snapshots.SnapshotWeakSet r11 = r9.trackedDependencies
            if (r11 != 0) goto L5d
            goto L54
        L5d:
            boolean r16 = r15.isNotEmpty()
            if (r16 == 0) goto L54
            boolean r16 = r15.isEmpty()
            if (r16 == 0) goto L6a
            goto L9e
        L6a:
            java.util.Iterator r15 = r15.iterator()
        L6e:
            r16 = r15
            kotlin.jvm.internal.ArrayIterator r16 = (kotlin.jvm.internal.ArrayIterator) r16
            boolean r17 = r16.hasNext()
            if (r17 == 0) goto L9e
            java.lang.Object r10 = r16.next()
            boolean r2 = r10 instanceof androidx.compose.runtime.DerivedSnapshotState
            if (r2 == 0) goto L54
            androidx.compose.runtime.DerivedSnapshotState r10 = (androidx.compose.runtime.DerivedSnapshotState) r10
            androidx.compose.runtime.SnapshotMutationPolicy r2 = r10.policy
            if (r2 != 0) goto L88
            androidx.compose.runtime.StructuralEqualityPolicy r2 = androidx.compose.runtime.StructuralEqualityPolicy.INSTANCE
        L88:
            r18 = r15
            androidx.compose.runtime.DerivedSnapshotState$ResultRecord r15 = r10.getCurrentRecord()
            java.lang.Object r15 = r15.result
            java.lang.Object r10 = r11.get(r10)
            boolean r2 = r2.equivalent(r15, r10)
            if (r2 == 0) goto L54
            r15 = r18
            r2 = 1
            goto L6e
        L9e:
            androidx.compose.runtime.Stack r2 = r1.invalidateStack
            r2.push(r9)
            androidx.compose.runtime.RecomposeScopeOwner r10 = r9.owner
            if (r10 == 0) goto Ldd
            androidx.compose.runtime.collection.IdentityArrayIntMap r11 = r9.trackedInstances
            if (r11 == 0) goto Ldd
            r14 = 1
            r9.setRereading(r14)
            java.lang.Object[] r14 = r11.keys     // Catch: java.lang.Throwable -> Ld1
            int[] r15 = r11.values     // Catch: java.lang.Throwable -> Ld1
            int r11 = r11.size     // Catch: java.lang.Throwable -> Ld1
            r18 = r0
            r0 = 0
        Lb8:
            if (r0 >= r11) goto Ld4
            r19 = r11
            r11 = r14[r0]     // Catch: java.lang.Throwable -> Ld1
            r20 = r14
            java.lang.String r14 = "null cannot be cast to non-null type kotlin.Any"
            kotlin.ResultKt.checkNotNull(r11, r14)     // Catch: java.lang.Throwable -> Ld1
            r14 = r15[r0]     // Catch: java.lang.Throwable -> Ld1
            r10.recordReadOf(r11)     // Catch: java.lang.Throwable -> Ld1
            int r0 = r0 + 1
            r11 = r19
            r14 = r20
            goto Lb8
        Ld1:
            r0 = move-exception
            r2 = 0
            goto Ld9
        Ld4:
            r10 = 0
            r9.setRereading(r10)
            goto Ldf
        Ld9:
            r9.setRereading(r2)
            throw r0
        Ldd:
            r18 = r0
        Ldf:
            r2.pop()
            r9 = 1
            r10 = 0
            goto L18c
        Le6:
            androidx.compose.runtime.SlotReader r0 = r1.reader
            r0.reposition(r14)
            androidx.compose.runtime.SlotReader r0 = r1.reader
            int r0 = r0.currentGroup
            r1.recordUpsAndDowns(r13, r0, r4)
            androidx.compose.runtime.SlotReader r2 = r1.reader
            int[] r2 = r2.groups
            int r2 = kotlin.ResultKt.access$parentAnchor(r2, r0)
        Lfa:
            if (r2 == r4) goto L10f
            androidx.compose.runtime.SlotReader r10 = r1.reader
            int[] r10 = r10.groups
            boolean r10 = kotlin.ResultKt.access$isNode(r10, r2)
            if (r10 != 0) goto L10f
            androidx.compose.runtime.SlotReader r10 = r1.reader
            int[] r10 = r10.groups
            int r2 = kotlin.ResultKt.access$parentAnchor(r10, r2)
            goto Lfa
        L10f:
            androidx.compose.runtime.SlotReader r10 = r1.reader
            int[] r10 = r10.groups
            boolean r10 = kotlin.ResultKt.access$isNode(r10, r2)
            if (r10 == 0) goto L11b
            r10 = 0
            goto L11c
        L11b:
            r10 = r5
        L11c:
            if (r2 != r0) goto L11f
            goto L147
        L11f:
            int r11 = r1.updatedNodeCount(r2)
            androidx.compose.runtime.SlotReader r12 = r1.reader
            int[] r12 = r12.groups
            int r12 = kotlin.ResultKt.access$nodeCount(r12, r0)
            int r11 = r11 - r12
            int r11 = r11 + r10
        L12d:
            if (r10 >= r11) goto L147
            if (r2 == r14) goto L147
            int r2 = r2 + 1
        L133:
            if (r2 >= r14) goto L147
            androidx.compose.runtime.SlotReader r12 = r1.reader
            int[] r12 = r12.groups
            int r12 = kotlin.ResultKt.access$groupSize(r12, r2)
            int r12 = r12 + r2
            if (r14 < r12) goto L12d
            int r2 = r1.updatedNodeCount(r2)
            int r10 = r10 + r2
            r2 = r12
            goto L133
        L147:
            r1.nodeIndex = r10
            androidx.compose.runtime.SlotReader r2 = r1.reader
            int[] r2 = r2.groups
            int r2 = kotlin.ResultKt.access$parentAnchor(r2, r0)
            int r2 = r1.compoundKeyOf(r2, r4, r6)
            r1.compoundKeyHash = r2
            r2 = 0
            r1.providerCache = r2
            r9.getClass()
            kotlin.jvm.functions.Function2 r2 = r9.block
            r9 = 1
            if (r2 == 0) goto L16c
            java.lang.Integer r10 = java.lang.Integer.valueOf(r9)
            r2.invoke(r1, r10)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            goto L16d
        L16c:
            r2 = 0
        L16d:
            if (r2 == 0) goto L1d0
            r2 = 0
            r1.providerCache = r2
            androidx.compose.runtime.SlotReader r2 = r1.reader
            int[] r10 = r2.groups
            int r10 = kotlin.ResultKt.access$groupSize(r10, r4)
            int r10 = r10 + r4
            int r11 = r2.currentGroup
            if (r11 < r4) goto L1b1
            if (r11 > r10) goto L1b1
            r2.parent = r4
            r2.currentEnd = r10
            r10 = 0
            r2.currentSlot = r10
            r2.currentSlotEnd = r10
            r13 = r0
            r12 = r9
        L18c:
            androidx.compose.runtime.SlotReader r0 = r1.reader
            int r0 = r0.currentGroup
            int r0 = androidx.compose.runtime.EffectsKt.findLocation(r0, r8)
            if (r0 >= 0) goto L199
            int r0 = r0 + 1
            int r0 = -r0
        L199:
            int r2 = r8.size()
            if (r0 >= r2) goto L1aa
            java.lang.Object r0 = r8.get(r0)
            androidx.compose.runtime.Invalidation r0 = (androidx.compose.runtime.Invalidation) r0
            int r2 = r0.location
            if (r2 >= r3) goto L1aa
            goto L1ab
        L1aa:
            r0 = 0
        L1ab:
            r2 = r9
            r9 = r0
            r0 = r18
            goto L3b
        L1b1:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Index "
            r0.<init>(r2)
            r0.append(r4)
            java.lang.String r2 = " is not a parent of "
            r0.append(r2)
            r0.append(r11)
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = r0.toString()
            androidx.compose.runtime.EffectsKt.composeRuntimeError(r0)
            r0 = 0
            throw r0
        L1d0:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "Invalid restart scope"
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            throw r0
        L1dc:
            r18 = r0
            r10 = 0
            if (r12 == 0) goto L1f4
            r1.recordUpsAndDowns(r13, r4, r4)
            androidx.compose.runtime.SlotReader r0 = r1.reader
            r0.skipToGroupEnd()
            int r0 = r1.updatedNodeCount(r4)
            int r5 = r5 + r0
            r1.nodeIndex = r5
            int r7 = r7 + r0
            r1.groupNodeCount = r7
            goto L207
        L1f4:
            androidx.compose.runtime.SlotReader r0 = r1.reader
            int r2 = r0.parent
            if (r2 < 0) goto L200
            int[] r0 = r0.groups
            int r10 = kotlin.ResultKt.access$nodeCount(r0, r2)
        L200:
            r1.groupNodeCount = r10
            androidx.compose.runtime.SlotReader r0 = r1.reader
            r0.skipToGroupEnd()
        L207:
            r1.compoundKeyHash = r6
            r0 = r18
            r1.isComposing = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.recomposeToGroupEnd():void");
    }

    public final void record(Function3 function3) {
        this.changes.add(function3);
    }

    public final void recordDelete() {
        reportFreeMovableContent(this.reader.currentGroup);
        ComposerImpl$useNode$2 composerImpl$useNode$2 = ComposerImpl$useNode$2.INSTANCE$3;
        realizeOperationLocation(false);
        SlotReader slotReader = this.reader;
        if (slotReader.groupsSize > 0) {
            int i = slotReader.parent;
            IntStack intStack = this.startedGroups;
            int i2 = intStack.tos;
            if ((i2 > 0 ? intStack.slots[i2 - 1] : -2) != i) {
                if (!this.startedGroup && this.implicitRootStart) {
                    ComposerImpl$useNode$2 composerImpl$useNode$22 = ComposerImpl$useNode$2.INSTANCE$6;
                    realizeOperationLocation(false);
                    record(composerImpl$useNode$22);
                    this.startedGroup = true;
                }
                if (i > 0) {
                    Anchor anchor = slotReader.anchor(i);
                    intStack.push(i);
                    ComposerImpl$realizeDowns$1 composerImpl$realizeDowns$1 = new ComposerImpl$realizeDowns$1(2, anchor);
                    realizeOperationLocation(false);
                    record(composerImpl$realizeDowns$1);
                }
            }
        }
        record(composerImpl$useNode$2);
        int i3 = this.writersReaderDelta;
        SlotReader slotReader2 = this.reader;
        this.writersReaderDelta = ResultKt.access$groupSize(slotReader2.groups, slotReader2.currentGroup) + i3;
    }

    public final void recordRemoveNode(int i, int i2) {
        if (i2 > 0) {
            if (i < 0) {
                EffectsKt.composeRuntimeError(("Invalid remove index " + i).toString());
                throw null;
            }
            if (this.previousRemove == i) {
                this.previousCount += i2;
                return;
            }
            realizeMovement();
            this.previousRemove = i;
            this.previousCount = i2;
        }
    }

    public final void recordUp() {
        Stack stack = this.downNodes;
        if (stack.isNotEmpty()) {
            stack.pop();
        } else {
            this.pendingUps++;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0088 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void recordUpsAndDowns(int r8, int r9, int r10) {
        /*
            r7 = this;
            androidx.compose.runtime.SlotReader r0 = r7.reader
            if (r8 != r9) goto L7
        L4:
            r10 = r8
            goto L79
        L7:
            if (r8 == r10) goto L79
            if (r9 != r10) goto Ld
            goto L79
        Ld:
            int[] r1 = r0.groups
            int r1 = kotlin.ResultKt.access$parentAnchor(r1, r8)
            if (r1 != r9) goto L18
            r10 = r9
            goto L79
        L18:
            int[] r1 = r0.groups
            int r2 = kotlin.ResultKt.access$parentAnchor(r1, r9)
            if (r2 != r8) goto L21
            goto L4
        L21:
            int r2 = r8 * 5
            int r2 = r2 + 2
            r2 = r1[r2]
            int r3 = r9 * 5
            int r3 = r3 + 2
            r3 = r1[r3]
            if (r2 != r3) goto L31
            r10 = r2
            goto L79
        L31:
            r2 = 0
            r3 = r8
            r4 = r2
        L34:
            if (r3 <= 0) goto L3f
            if (r3 == r10) goto L3f
            int r3 = kotlin.ResultKt.access$parentAnchor(r1, r3)
            int r4 = r4 + 1
            goto L34
        L3f:
            r3 = r9
            r5 = r2
        L41:
            if (r3 <= 0) goto L4c
            if (r3 == r10) goto L4c
            int r3 = kotlin.ResultKt.access$parentAnchor(r1, r3)
            int r5 = r5 + 1
            goto L41
        L4c:
            int r10 = r4 - r5
            r6 = r8
            r3 = r2
        L50:
            if (r3 >= r10) goto L5b
            int r6 = r6 * 5
            int r6 = r6 + 2
            r6 = r1[r6]
            int r3 = r3 + 1
            goto L50
        L5b:
            int r5 = r5 - r4
            r10 = r9
        L5d:
            if (r2 >= r5) goto L68
            int r10 = r10 * 5
            int r10 = r10 + 2
            r10 = r1[r10]
            int r2 = r2 + 1
            goto L5d
        L68:
            r2 = r10
            r10 = r6
        L6a:
            if (r10 == r2) goto L79
            int r10 = r10 * 5
            int r10 = r10 + 2
            r10 = r1[r10]
            int r2 = r2 * 5
            int r2 = r2 + 2
            r2 = r1[r2]
            goto L6a
        L79:
            if (r8 <= 0) goto L8f
            if (r8 == r10) goto L8f
            int[] r1 = r0.groups
            boolean r1 = kotlin.ResultKt.access$isNode(r1, r8)
            if (r1 == 0) goto L88
            r7.recordUp()
        L88:
            int[] r1 = r0.groups
            int r8 = kotlin.ResultKt.access$parentAnchor(r1, r8)
            goto L79
        L8f:
            r7.doRecordDownsFor(r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.recordUpsAndDowns(int, int, int):void");
    }

    public final void reportFreeMovableContent(int i) {
        reportFreeMovableContent$reportGroup(this, i, false, 0);
        realizeMovement();
    }

    public final void skipCurrentGroup() {
        if (this.invalidations.isEmpty()) {
            this.groupNodeCount = this.reader.skipGroup() + this.groupNodeCount;
            return;
        }
        SlotReader slotReader = this.reader;
        int groupKey = slotReader.getGroupKey();
        int i = slotReader.currentGroup;
        int i2 = slotReader.currentEnd;
        int[] iArr = slotReader.groups;
        Object objectKey = i < i2 ? slotReader.objectKey(iArr, i) : null;
        Object groupAux = slotReader.getGroupAux();
        updateCompoundKeyWhenWeEnterGroup(objectKey, groupKey, groupAux);
        startReaderGroup(null, ResultKt.access$isNode(iArr, slotReader.currentGroup));
        recomposeToGroupEnd();
        slotReader.endGroup();
        updateCompoundKeyWhenWeExitGroup(objectKey, groupKey, groupAux);
    }

    public final void skipToGroupEnd() {
        if (this.groupNodeCount != 0) {
            EffectsKt.composeRuntimeError("No nodes can be emitted before calling skipAndEndGroup".toString());
            throw null;
        }
        RecomposeScopeImpl currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release();
        if (currentRecomposeScope$runtime_release != null) {
            currentRecomposeScope$runtime_release.flags |= 16;
        }
        if (!this.invalidations.isEmpty()) {
            recomposeToGroupEnd();
            return;
        }
        SlotReader slotReader = this.reader;
        int i = slotReader.parent;
        this.groupNodeCount = i >= 0 ? ResultKt.access$nodeCount(slotReader.groups, i) : 0;
        this.reader.skipToGroupEnd();
    }

    /* JADX WARN: Removed duplicated region for block: B:140:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x012f  */
    /* renamed from: start-BaiHCIY, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m59startBaiHCIY(int r19, int r20, androidx.compose.runtime.OpaqueKey r21, java.lang.Object r22) {
        /*
            r18 = this;
            r0 = r18
            r1 = r19
            r2 = r20
            r3 = r21
            r4 = r22
            boolean r5 = r0.nodeExpected
            r6 = 1
            r5 = r5 ^ r6
            r7 = 0
            if (r5 == 0) goto L2b1
            r0.updateCompoundKeyWhenWeEnterGroup(r3, r1, r4)
            r5 = 0
            if (r2 == 0) goto L19
            r8 = r6
            goto L1a
        L19:
            r8 = r5
        L1a:
            boolean r9 = r0.inserting
            _COROUTINE.ArtificialStackFrames r10 = androidx.compose.runtime.Composer.Companion.Empty
            r11 = -2
            r12 = -1
            if (r9 == 0) goto L6c
            androidx.compose.runtime.SlotReader r2 = r0.reader
            int r9 = r2.emptyCount
            int r9 = r9 + r6
            r2.emptyCount = r9
            androidx.compose.runtime.SlotWriter r2 = r0.writer
            int r9 = r2.currentGroup
            if (r8 == 0) goto L33
            r2.startGroup(r1, r10, r6, r10)
            goto L42
        L33:
            if (r4 == 0) goto L3c
            if (r3 != 0) goto L38
            r3 = r10
        L38:
            r2.startGroup(r1, r3, r5, r4)
            goto L42
        L3c:
            if (r3 != 0) goto L3f
            r3 = r10
        L3f:
            r2.startGroup(r1, r3, r5, r10)
        L42:
            androidx.compose.runtime.Pending r2 = r0.pending
            if (r2 == 0) goto L68
            androidx.compose.runtime.KeyInfo r3 = new androidx.compose.runtime.KeyInfo
            java.lang.Integer r4 = java.lang.Integer.valueOf(r12)
            int r11 = r11 - r9
            r3.<init>(r1, r4, r11, r12)
            int r1 = r0.nodeIndex
            int r4 = r2.startIndex
            int r1 = r1 - r4
            java.util.HashMap r4 = r2.groupInfos
            java.lang.Integer r6 = java.lang.Integer.valueOf(r11)
            androidx.compose.runtime.GroupInfo r9 = new androidx.compose.runtime.GroupInfo
            r9.<init>(r12, r1, r5)
            r4.put(r6, r9)
            java.util.ArrayList r1 = r2.usedKeys
            r1.add(r3)
        L68:
            r0.enterGroup(r8, r7)
            return
        L6c:
            if (r2 == r6) goto L6f
            goto L75
        L6f:
            boolean r2 = r0.reusing
            if (r2 == 0) goto L75
            r2 = r6
            goto L76
        L75:
            r2 = r5
        L76:
            androidx.compose.runtime.Pending r9 = r0.pending
            if (r9 != 0) goto Le6
            androidx.compose.runtime.SlotReader r9 = r0.reader
            int r9 = r9.getGroupKey()
            if (r2 != 0) goto L9e
            if (r9 != r1) goto L9e
            androidx.compose.runtime.SlotReader r9 = r0.reader
            int r13 = r9.currentGroup
            int r14 = r9.currentEnd
            if (r13 >= r14) goto L93
            int[] r14 = r9.groups
            java.lang.Object r9 = r9.objectKey(r14, r13)
            goto L94
        L93:
            r9 = r7
        L94:
            boolean r9 = kotlin.ResultKt.areEqual(r3, r9)
            if (r9 == 0) goto L9e
            r0.startReaderGroup(r4, r8)
            goto Le6
        L9e:
            androidx.compose.runtime.Pending r9 = new androidx.compose.runtime.Pending
            androidx.compose.runtime.SlotReader r13 = r0.reader
            r13.getClass()
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            int r15 = r13.emptyCount
            if (r15 <= 0) goto Laf
            goto Ldf
        Laf:
            int r15 = r13.currentGroup
        Lb1:
            int r11 = r13.currentEnd
            if (r15 >= r11) goto Ldf
            androidx.compose.runtime.KeyInfo r11 = new androidx.compose.runtime.KeyInfo
            int r16 = r15 * 5
            int[] r12 = r13.groups
            r7 = r12[r16]
            java.lang.Object r6 = r13.objectKey(r12, r15)
            boolean r17 = kotlin.ResultKt.access$isNode(r12, r15)
            if (r17 == 0) goto Lc9
            r5 = 1
            goto Lcf
        Lc9:
            int r17 = kotlin.ResultKt.access$nodeCount(r12, r15)
            r5 = r17
        Lcf:
            r11.<init>(r7, r6, r15, r5)
            r14.add(r11)
            int r16 = r16 + 3
            r5 = r12[r16]
            int r15 = r15 + r5
            r5 = 0
            r6 = 1
            r7 = 0
            r12 = -1
            goto Lb1
        Ldf:
            int r5 = r0.nodeIndex
            r9.<init>(r5, r14)
            r0.pending = r9
        Le6:
            androidx.compose.runtime.Pending r5 = r0.pending
            if (r5 == 0) goto L2ac
            if (r3 == 0) goto Lf6
            androidx.compose.runtime.JoinedKey r6 = new androidx.compose.runtime.JoinedKey
            java.lang.Integer r7 = java.lang.Integer.valueOf(r19)
            r6.<init>(r7, r3)
            goto Lfa
        Lf6:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r19)
        Lfa:
            kotlin.SynchronizedLazyImpl r7 = r5.keyMap$delegate
            java.lang.Object r7 = r7.getValue()
            java.util.HashMap r7 = (java.util.HashMap) r7
            java.lang.Object r9 = r7.get(r6)
            java.util.LinkedHashSet r9 = (java.util.LinkedHashSet) r9
            if (r9 == 0) goto L144
            boolean r11 = r9 instanceof java.util.List
            if (r11 == 0) goto L11e
            java.util.List r9 = (java.util.List) r9
            boolean r11 = r9.isEmpty()
            if (r11 == 0) goto L118
        L116:
            r9 = 0
            goto L12d
        L118:
            r11 = 0
            java.lang.Object r9 = r9.get(r11)
            goto L12d
        L11e:
            java.util.Iterator r9 = r9.iterator()
            boolean r11 = r9.hasNext()
            if (r11 != 0) goto L129
            goto L116
        L129:
            java.lang.Object r9 = r9.next()
        L12d:
            if (r9 == 0) goto L144
            java.lang.Object r11 = r7.get(r6)
            java.util.LinkedHashSet r11 = (java.util.LinkedHashSet) r11
            if (r11 == 0) goto L145
            r11.remove(r9)
            boolean r11 = r11.isEmpty()
            if (r11 == 0) goto L145
            r7.remove(r6)
            goto L145
        L144:
            r9 = 0
        L145:
            androidx.compose.runtime.KeyInfo r9 = (androidx.compose.runtime.KeyInfo) r9
            java.util.HashMap r6 = r5.groupInfos
            java.util.ArrayList r7 = r5.usedKeys
            int r11 = r5.startIndex
            if (r2 != 0) goto L22e
            if (r9 == 0) goto L22e
            r7.add(r9)
            int r1 = r5.nodePositionOf(r9)
            int r1 = r1 + r11
            r0.nodeIndex = r1
            int r1 = r9.location
            java.lang.Integer r2 = java.lang.Integer.valueOf(r1)
            java.lang.Object r2 = r6.get(r2)
            androidx.compose.runtime.GroupInfo r2 = (androidx.compose.runtime.GroupInfo) r2
            if (r2 == 0) goto L16c
            int r12 = r2.slotIndex
            goto L16d
        L16c:
            r12 = -1
        L16d:
            int r2 = r5.groupIndex
            int r3 = r12 - r2
            java.lang.String r5 = "groupInfos.values"
            if (r12 <= r2) goto L19e
            java.util.Collection r6 = r6.values()
            kotlin.ResultKt.checkNotNullExpressionValue(r6, r5)
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Iterator r5 = r6.iterator()
        L182:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L1cb
            java.lang.Object r6 = r5.next()
            androidx.compose.runtime.GroupInfo r6 = (androidx.compose.runtime.GroupInfo) r6
            int r7 = r6.slotIndex
            if (r7 != r12) goto L195
            r6.slotIndex = r2
            goto L182
        L195:
            if (r2 > r7) goto L182
            if (r7 >= r12) goto L182
            int r7 = r7 + 1
            r6.slotIndex = r7
            goto L182
        L19e:
            if (r2 <= r12) goto L1cb
            java.util.Collection r6 = r6.values()
            kotlin.ResultKt.checkNotNullExpressionValue(r6, r5)
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Iterator r5 = r6.iterator()
        L1ad:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L1cb
            java.lang.Object r6 = r5.next()
            androidx.compose.runtime.GroupInfo r6 = (androidx.compose.runtime.GroupInfo) r6
            int r7 = r6.slotIndex
            if (r7 != r12) goto L1c0
            r6.slotIndex = r2
            goto L1ad
        L1c0:
            int r9 = r12 + 1
            if (r9 > r7) goto L1ad
            if (r7 >= r2) goto L1ad
            int r7 = r7 + (-1)
            r6.slotIndex = r7
            goto L1ad
        L1cb:
            androidx.compose.runtime.SlotReader r2 = r0.reader
            int r5 = r2.currentGroup
            int r6 = r0.writersReaderDelta
            int r5 = r5 - r6
            int r5 = r1 - r5
            r0.writersReaderDelta = r5
            r2.reposition(r1)
            if (r3 <= 0) goto L229
            androidx.compose.runtime.ComposerImpl$start$2 r1 = new androidx.compose.runtime.ComposerImpl$start$2
            r2 = 0
            r1.<init>(r3, r2)
            r0.realizeOperationLocation(r2)
            androidx.compose.runtime.SlotReader r2 = r0.reader
            int r3 = r2.groupsSize
            if (r3 <= 0) goto L226
            int r3 = r2.parent
            androidx.compose.runtime.IntStack r5 = r0.startedGroups
            int r6 = r5.tos
            if (r6 <= 0) goto L1f9
            int[] r7 = r5.slots
            r9 = 1
            int r6 = r6 - r9
            r11 = r7[r6]
            goto L1fa
        L1f9:
            r11 = -2
        L1fa:
            if (r11 == r3) goto L226
            boolean r6 = r0.startedGroup
            if (r6 != 0) goto L210
            boolean r6 = r0.implicitRootStart
            if (r6 == 0) goto L210
            androidx.compose.runtime.ComposerImpl$useNode$2 r6 = androidx.compose.runtime.ComposerImpl$useNode$2.INSTANCE$6
            r7 = 0
            r0.realizeOperationLocation(r7)
            r0.record(r6)
            r6 = 1
            r0.startedGroup = r6
        L210:
            if (r3 <= 0) goto L226
            androidx.compose.runtime.Anchor r2 = r2.anchor(r3)
            r5.push(r3)
            androidx.compose.runtime.ComposerImpl$realizeDowns$1 r3 = new androidx.compose.runtime.ComposerImpl$realizeDowns$1
            r5 = 2
            r3.<init>(r5, r2)
            r2 = 0
            r0.realizeOperationLocation(r2)
            r0.record(r3)
        L226:
            r0.record(r1)
        L229:
            r0.startReaderGroup(r4, r8)
            goto L2ac
        L22e:
            androidx.compose.runtime.SlotReader r2 = r0.reader
            int r5 = r2.emptyCount
            r9 = 1
            int r5 = r5 + r9
            r2.emptyCount = r5
            r0.inserting = r9
            r2 = 0
            r0.providerCache = r2
            androidx.compose.runtime.SlotWriter r2 = r0.writer
            boolean r2 = r2.closed
            if (r2 == 0) goto L252
            androidx.compose.runtime.SlotTable r2 = r0.insertTable
            androidx.compose.runtime.SlotWriter r2 = r2.openWriter()
            r0.writer = r2
            r2.skipToGroupEnd()
            r2 = 0
            r0.writerHasAProvider = r2
            r2 = 0
            r0.providerCache = r2
        L252:
            androidx.compose.runtime.SlotWriter r2 = r0.writer
            r2.beginInsert()
            androidx.compose.runtime.SlotWriter r2 = r0.writer
            int r5 = r2.currentGroup
            if (r8 == 0) goto L262
            r9 = 1
            r2.startGroup(r1, r10, r9, r10)
            goto L273
        L262:
            if (r4 == 0) goto L26c
            if (r3 != 0) goto L267
            r3 = r10
        L267:
            r9 = 0
            r2.startGroup(r1, r3, r9, r4)
            goto L273
        L26c:
            r9 = 0
            if (r3 != 0) goto L270
            r3 = r10
        L270:
            r2.startGroup(r1, r3, r9, r10)
        L273:
            androidx.compose.runtime.SlotWriter r2 = r0.writer
            androidx.compose.runtime.Anchor r2 = r2.anchor(r5)
            r0.insertAnchor = r2
            androidx.compose.runtime.KeyInfo r2 = new androidx.compose.runtime.KeyInfo
            r3 = -1
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)
            r9 = -2
            int r5 = (-2) - r5
            r2.<init>(r1, r4, r5, r3)
            int r1 = r0.nodeIndex
            int r1 = r1 - r11
            java.lang.Integer r4 = java.lang.Integer.valueOf(r5)
            androidx.compose.runtime.GroupInfo r5 = new androidx.compose.runtime.GroupInfo
            r9 = 0
            r5.<init>(r3, r1, r9)
            r6.put(r4, r5)
            r7.add(r2)
            androidx.compose.runtime.Pending r7 = new androidx.compose.runtime.Pending
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            if (r8 == 0) goto L2a6
            r5 = r9
            goto L2a8
        L2a6:
            int r5 = r0.nodeIndex
        L2a8:
            r7.<init>(r5, r1)
            goto L2ad
        L2ac:
            r7 = 0
        L2ad:
            r0.enterGroup(r8, r7)
            return
        L2b1:
            java.lang.String r1 = "A call to createNode(), emitNode() or useNode() expected"
            java.lang.String r1 = r1.toString()
            androidx.compose.runtime.EffectsKt.composeRuntimeError(r1)
            r1 = 0
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.m59startBaiHCIY(int, int, androidx.compose.runtime.OpaqueKey, java.lang.Object):void");
    }

    public final void startReaderGroup(Object obj, boolean z) {
        if (z) {
            SlotReader slotReader = this.reader;
            if (slotReader.emptyCount <= 0) {
                if (!ResultKt.access$isNode(slotReader.groups, slotReader.currentGroup)) {
                    throw new IllegalArgumentException("Expected a node group".toString());
                }
                slotReader.startGroup();
                return;
            }
            return;
        }
        if (obj != null && this.reader.getGroupAux() != obj) {
            ComposerImpl$updateValue$1 composerImpl$updateValue$1 = new ComposerImpl$updateValue$1(1, obj);
            realizeOperationLocation(false);
            record(composerImpl$updateValue$1);
        }
        this.reader.startGroup();
    }

    public final void startReplaceableGroup(int i) {
        m59startBaiHCIY(i, 0, null, null);
    }

    public final ComposerImpl startRestartGroup(int i) {
        Object obj;
        RecomposeScopeImpl recomposeScopeImpl;
        int i2;
        m59startBaiHCIY(i, 0, null, null);
        boolean z = this.inserting;
        Stack stack = this.invalidateStack;
        CompositionImpl compositionImpl = this.composition;
        if (z) {
            ResultKt.checkNotNull(compositionImpl, "null cannot be cast to non-null type androidx.compose.runtime.CompositionImpl");
            RecomposeScopeImpl recomposeScopeImpl2 = new RecomposeScopeImpl(compositionImpl);
            stack.push(recomposeScopeImpl2);
            updateValue(recomposeScopeImpl2);
            recomposeScopeImpl2.currentToken = this.compositionToken;
            recomposeScopeImpl2.flags &= -17;
        } else {
            ArrayList arrayList = this.invalidations;
            int findLocation = EffectsKt.findLocation(this.reader.parent, arrayList);
            Invalidation invalidation = findLocation >= 0 ? (Invalidation) arrayList.remove(findLocation) : null;
            SlotReader slotReader = this.reader;
            int i3 = slotReader.emptyCount;
            ArtificialStackFrames artificialStackFrames = Composer.Companion.Empty;
            if (i3 > 0 || (i2 = slotReader.currentSlot) >= slotReader.currentSlotEnd) {
                obj = artificialStackFrames;
            } else {
                slotReader.currentSlot = i2 + 1;
                obj = slotReader.slots[i2];
            }
            if (ResultKt.areEqual(obj, artificialStackFrames)) {
                ResultKt.checkNotNull(compositionImpl, "null cannot be cast to non-null type androidx.compose.runtime.CompositionImpl");
                recomposeScopeImpl = new RecomposeScopeImpl(compositionImpl);
                updateValue(recomposeScopeImpl);
            } else {
                ResultKt.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.RecomposeScopeImpl");
                recomposeScopeImpl = (RecomposeScopeImpl) obj;
            }
            if (invalidation != null) {
                recomposeScopeImpl.flags |= 8;
            } else {
                recomposeScopeImpl.flags &= -9;
            }
            stack.push(recomposeScopeImpl);
            recomposeScopeImpl.currentToken = this.compositionToken;
            recomposeScopeImpl.flags &= -17;
        }
        return this;
    }

    public final void startReusableNode() {
        m59startBaiHCIY(125, 2, null, null);
        this.nodeExpected = true;
    }

    public final void startRoot() {
        SlotTable slotTable = this.slotTable;
        this.reader = slotTable.openReader();
        m59startBaiHCIY(100, 0, null, null);
        this.parentContext.getClass();
        this.parentProvider = CompositionContextKt.EmptyPersistentCompositionLocalMap;
        this.providersInvalidStack.push(this.providersInvalid ? 1 : 0);
        this.providersInvalid = changed(this.parentProvider);
        this.providerCache = null;
        if (!this.forceRecomposeScopes) {
            this.forceRecomposeScopes = false;
        }
        Set set = (Set) _BOUNDARY.read(this.parentProvider, InspectionTablesKt.LocalInspectionTables);
        if (set != null) {
            set.add(slotTable);
        }
        m59startBaiHCIY(1000, 0, null, null);
    }

    public final boolean tryImminentInvalidation$runtime_release(RecomposeScopeImpl recomposeScopeImpl, Object obj) {
        ResultKt.checkNotNullParameter(recomposeScopeImpl, "scope");
        Anchor anchor = recomposeScopeImpl.anchor;
        if (anchor == null) {
            return false;
        }
        SlotTable slotTable = this.reader.table;
        ResultKt.checkNotNullParameter(slotTable, "slots");
        int anchorIndex = slotTable.anchorIndex(anchor);
        if (!this.isComposing || anchorIndex < this.reader.currentGroup) {
            return false;
        }
        ArrayList arrayList = this.invalidations;
        int findLocation = EffectsKt.findLocation(anchorIndex, arrayList);
        IdentityArraySet identityArraySet = null;
        if (findLocation < 0) {
            int i = -(findLocation + 1);
            if (obj != null) {
                identityArraySet = new IdentityArraySet();
                identityArraySet.add(obj);
            }
            arrayList.add(i, new Invalidation(recomposeScopeImpl, anchorIndex, identityArraySet));
        } else if (obj == null) {
            ((Invalidation) arrayList.get(findLocation)).instances = null;
        } else {
            IdentityArraySet identityArraySet2 = ((Invalidation) arrayList.get(findLocation)).instances;
            if (identityArraySet2 != null) {
                identityArraySet2.add(obj);
            }
        }
        return true;
    }

    public final void updateCompoundKeyWhenWeEnterGroup(Object obj, int i, Object obj2) {
        if (obj != null) {
            if (obj instanceof Enum) {
                this.compoundKeyHash = ((Enum) obj).ordinal() ^ Integer.rotateLeft(this.compoundKeyHash, 3);
                return;
            } else {
                this.compoundKeyHash = obj.hashCode() ^ Integer.rotateLeft(this.compoundKeyHash, 3);
                return;
            }
        }
        if (obj2 == null || i != 207 || ResultKt.areEqual(obj2, Composer.Companion.Empty)) {
            this.compoundKeyHash = Integer.rotateLeft(this.compoundKeyHash, 3) ^ i;
        } else {
            this.compoundKeyHash = obj2.hashCode() ^ Integer.rotateLeft(this.compoundKeyHash, 3);
        }
    }

    public final void updateCompoundKeyWhenWeExitGroup(Object obj, int i, Object obj2) {
        if (obj != null) {
            if (obj instanceof Enum) {
                updateCompoundKeyWhenWeExitGroupKeyHash(((Enum) obj).ordinal());
                return;
            } else {
                updateCompoundKeyWhenWeExitGroupKeyHash(obj.hashCode());
                return;
            }
        }
        if (obj2 == null || i != 207 || ResultKt.areEqual(obj2, Composer.Companion.Empty)) {
            updateCompoundKeyWhenWeExitGroupKeyHash(i);
        } else {
            updateCompoundKeyWhenWeExitGroupKeyHash(obj2.hashCode());
        }
    }

    public final void updateCompoundKeyWhenWeExitGroupKeyHash(int i) {
        this.compoundKeyHash = Integer.rotateRight(Integer.hashCode(i) ^ this.compoundKeyHash, 3);
    }

    public final void updateNodeCount(int i, int i2) {
        if (updatedNodeCount(i) != i2) {
            if (i < 0) {
                HashMap hashMap = this.nodeCountVirtualOverrides;
                if (hashMap == null) {
                    hashMap = new HashMap();
                    this.nodeCountVirtualOverrides = hashMap;
                }
                hashMap.put(Integer.valueOf(i), Integer.valueOf(i2));
                return;
            }
            int[] iArr = this.nodeCountOverrides;
            if (iArr == null) {
                int i3 = this.reader.groupsSize;
                int[] iArr2 = new int[i3];
                Arrays.fill(iArr2, 0, i3, -1);
                this.nodeCountOverrides = iArr2;
                iArr = iArr2;
            }
            iArr[i] = i2;
        }
    }

    public final void updateNodeCountOverrides(int i, int i2) {
        int updatedNodeCount = updatedNodeCount(i);
        if (updatedNodeCount != i2) {
            int i3 = i2 - updatedNodeCount;
            Stack stack = this.pendingStack;
            int size = ((ArrayList) stack.backing).size() - 1;
            while (i != -1) {
                int updatedNodeCount2 = updatedNodeCount(i) + i3;
                updateNodeCount(i, updatedNodeCount2);
                int i4 = size;
                while (true) {
                    if (-1 < i4) {
                        Pending pending = (Pending) ((ArrayList) stack.backing).get(i4);
                        if (pending != null && pending.updateNodeCount(i, updatedNodeCount2)) {
                            size = i4 - 1;
                            break;
                        }
                        i4--;
                    } else {
                        break;
                    }
                }
                if (i < 0) {
                    i = this.reader.parent;
                } else if (ResultKt.access$isNode(this.reader.groups, i)) {
                    return;
                } else {
                    i = ResultKt.access$parentAnchor(this.reader.groups, i);
                }
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBuilder, androidx.compose.runtime.internal.PersistentCompositionLocalHashMap$Builder] */
    public final PersistentCompositionLocalHashMap updateProviderMapGroup(PersistentCompositionLocalMap persistentCompositionLocalMap, PersistentCompositionLocalMap persistentCompositionLocalMap2) {
        PersistentCompositionLocalHashMap persistentCompositionLocalHashMap = (PersistentCompositionLocalHashMap) persistentCompositionLocalMap;
        persistentCompositionLocalHashMap.getClass();
        ?? persistentHashMapBuilder = new PersistentHashMapBuilder(persistentCompositionLocalHashMap);
        persistentHashMapBuilder.map = persistentCompositionLocalHashMap;
        persistentHashMapBuilder.putAll(persistentCompositionLocalMap2);
        PersistentCompositionLocalHashMap build = persistentHashMapBuilder.build();
        m59startBaiHCIY(204, 0, EffectsKt.providerMaps, null);
        changed(build);
        changed(persistentCompositionLocalMap2);
        end(false);
        return build;
    }

    public final void updateValue(Object obj) {
        boolean z = this.inserting;
        Set set = this.abandonSet;
        if (z) {
            this.writer.update(obj);
            if (obj instanceof RememberObserver) {
                record(new ComposerImpl$updateValue$1(0, obj));
                set.add(obj);
                return;
            }
            return;
        }
        SlotReader slotReader = this.reader;
        int access$slotAnchor = slotReader.currentSlot - ResultKt.access$slotAnchor(slotReader.groups, slotReader.parent);
        int i = 1;
        int i2 = access$slotAnchor - 1;
        if (obj instanceof RememberObserver) {
            set.add(obj);
        }
        ComposerImpl$createNode$3 composerImpl$createNode$3 = new ComposerImpl$createNode$3(i2, i, obj);
        realizeOperationLocation(true);
        record(composerImpl$createNode$3);
    }

    public final int updatedNodeCount(int i) {
        int i2;
        Integer num;
        if (i >= 0) {
            int[] iArr = this.nodeCountOverrides;
            return (iArr == null || (i2 = iArr[i]) < 0) ? ResultKt.access$nodeCount(this.reader.groups, i) : i2;
        }
        HashMap hashMap = this.nodeCountVirtualOverrides;
        if (hashMap == null || (num = (Integer) hashMap.get(Integer.valueOf(i))) == null) {
            return 0;
        }
        return num.intValue();
    }

    public final void useNode() {
        if (!this.nodeExpected) {
            EffectsKt.composeRuntimeError("A call to createNode(), emitNode() or useNode() expected was not expected".toString());
            throw null;
        }
        this.nodeExpected = false;
        if (!(!this.inserting)) {
            EffectsKt.composeRuntimeError("useNode() called while inserting".toString());
            throw null;
        }
        SlotReader slotReader = this.reader;
        Object node = slotReader.node(slotReader.parent);
        this.downNodes.push(node);
        if (this.reusing && (node instanceof ComposeNodeLifecycleCallback)) {
            ComposerImpl$useNode$2 composerImpl$useNode$2 = ComposerImpl$useNode$2.INSTANCE;
            realizeUps();
            realizeDowns$1();
            record(composerImpl$useNode$2);
        }
    }

    public final boolean changed(boolean z) {
        Object nextSlot = nextSlot();
        if ((nextSlot instanceof Boolean) && z == ((Boolean) nextSlot).booleanValue()) {
            return false;
        }
        updateValue(Boolean.valueOf(z));
        return true;
    }

    public final boolean changed(long j) {
        Object nextSlot = nextSlot();
        if ((nextSlot instanceof Long) && j == ((Number) nextSlot).longValue()) {
            return false;
        }
        updateValue(Long.valueOf(j));
        return true;
    }

    public final boolean changed(int i) {
        Object nextSlot = nextSlot();
        if ((nextSlot instanceof Integer) && i == ((Number) nextSlot).intValue()) {
            return false;
        }
        updateValue(Integer.valueOf(i));
        return true;
    }
}
