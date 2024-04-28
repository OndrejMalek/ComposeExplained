package androidx.compose.runtime;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.CompositionImpl;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public abstract class EffectsKt {
    public static final OpaqueKey invocation = new OpaqueKey("provider");
    public static final OpaqueKey provider = new OpaqueKey("provider");
    public static final OpaqueKey compositionLocalMap = new OpaqueKey("compositionLocalMap");
    public static final OpaqueKey providerValues = new OpaqueKey("providerValues");
    public static final OpaqueKey providerMaps = new OpaqueKey("providers");
    public static final OpaqueKey reference = new OpaqueKey("reference");
    public static final DisposableEffectScope InternalDisposableEffectScope = new Object();

    public static final void DisposableEffect(Object obj, Function1 function1, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(-1371986847);
        composerImpl.startReplaceableGroup(1157296644);
        boolean changed = composerImpl.changed(obj);
        Object nextSlot = composerImpl.nextSlot();
        if (changed || nextSlot == Composer.Companion.Empty) {
            composerImpl.updateValue(new DisposableEffectImpl(function1));
        }
        composerImpl.end(false);
        composerImpl.end(false);
    }

    public static final void LaunchedEffect(Object obj, Function2 function2, Composer composer) {
        ResultKt.checkNotNullParameter(function2, "block");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(1179185413);
        CoroutineContext coroutineContext = ((Recomposer) composerImpl.parentContext).effectCoroutineContext;
        composerImpl.startReplaceableGroup(1157296644);
        boolean changed = composerImpl.changed(obj);
        Object nextSlot = composerImpl.nextSlot();
        if (changed || nextSlot == Composer.Companion.Empty) {
            composerImpl.updateValue(new LaunchedEffectImpl(coroutineContext, function2));
        }
        composerImpl.end(false);
        composerImpl.end(false);
    }

    public static final void composeRuntimeError(String str) {
        ResultKt.checkNotNullParameter(str, "message");
        throw new ComposeRuntimeError("Compose Runtime internal error. Unexpected or incorrect use of the Compose internal runtime API (" + str + "). Please report to Google or use https://goo.gle/compose-feedback");
    }

    public static final int findLocation(int i, ArrayList arrayList) {
        int size = arrayList.size() - 1;
        int i2 = 0;
        while (i2 <= size) {
            int i3 = (i2 + size) >>> 1;
            int compare = ResultKt.compare(((Invalidation) arrayList.get(i3)).location, i);
            if (compare < 0) {
                i2 = i3 + 1;
            } else {
                if (compare <= 0) {
                    return i3;
                }
                size = i3 - 1;
            }
        }
        return -(i2 + 1);
    }

    public static final void removeCurrentGroup(SlotWriter slotWriter, CompositionImpl.RememberEventDispatcher rememberEventDispatcher) {
        ResultKt.checkNotNullParameter(slotWriter, "<this>");
        ResultKt.checkNotNullParameter(rememberEventDispatcher, "rememberManager");
        int dataIndex = slotWriter.dataIndex(slotWriter.groups, slotWriter.groupIndexToAddress(slotWriter.currentGroup));
        int[] iArr = slotWriter.groups;
        int i = slotWriter.currentGroup;
        SlotWriter$groupSlots$1 slotWriter$groupSlots$1 = new SlotWriter$groupSlots$1(dataIndex, slotWriter.dataIndex(iArr, slotWriter.groupIndexToAddress(slotWriter.groupSize(i) + i)), slotWriter);
        while (slotWriter$groupSlots$1.hasNext()) {
            Object next = slotWriter$groupSlots$1.next();
            if (next instanceof ComposeNodeLifecycleCallback) {
                ComposeNodeLifecycleCallback composeNodeLifecycleCallback = (ComposeNodeLifecycleCallback) next;
                ResultKt.checkNotNullParameter(composeNodeLifecycleCallback, "instance");
                ArrayList arrayList = rememberEventDispatcher.releasing;
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    rememberEventDispatcher.releasing = arrayList;
                }
                arrayList.add(composeNodeLifecycleCallback);
            }
            if (next instanceof RememberObserver) {
                rememberEventDispatcher.forgetting((RememberObserver) next);
            }
            if (next instanceof RecomposeScopeImpl) {
                RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) next;
                RecomposeScopeOwner recomposeScopeOwner = recomposeScopeImpl.owner;
                if (recomposeScopeOwner != null) {
                    recomposeScopeOwner.recomposeScopeReleased(recomposeScopeImpl);
                }
                recomposeScopeImpl.owner = null;
                recomposeScopeImpl.trackedInstances = null;
                recomposeScopeImpl.trackedDependencies = null;
            }
        }
        slotWriter.removeGroup();
    }

    public static final void runtimeCheck(boolean z) {
        if (z) {
            return;
        }
        composeRuntimeError("Check failed".toString());
        throw null;
    }
}
