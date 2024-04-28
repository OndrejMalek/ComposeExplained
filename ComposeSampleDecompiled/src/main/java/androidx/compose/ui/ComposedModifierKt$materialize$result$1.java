package androidx.compose.ui;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class ComposedModifierKt$materialize$result$1 extends Lambda implements Function2 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object $this_materialize;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ComposedModifierKt$materialize$result$1(int i, Object obj) {
        super(2);
        this.$r8$classId = i;
        this.$this_materialize = obj;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r7v5, resolved type: java.util.Set[] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x004d  */
    /* JADX WARN: Type inference failed for: r0v15, types: [androidx.compose.ui.text.input.TextInputService, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invoke(java.lang.Object r10, java.lang.Object r11) {
        /*
            r9 = this;
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            int r1 = r9.$r8$classId
            r2 = 0
            r3 = 2
            r4 = 1
            r5 = 0
            java.lang.Object r6 = r9.$this_materialize
            switch(r1) {
                case 0: goto L172;
                case 1: goto L162;
                case 2: goto L152;
                case 3: goto L142;
                case 4: goto L131;
                case 5: goto Ld2;
                case 6: goto Lb0;
                case 7: goto L86;
                default: goto Ld;
            }
        Ld:
            java.lang.Number r10 = (java.lang.Number) r10
            int r0 = r10.intValue()
            kotlin.coroutines.CoroutineContext$Element r11 = (kotlin.coroutines.CoroutineContext.Element) r11
            kotlin.coroutines.CoroutineContext$Key r10 = r11.getKey()
            kotlinx.coroutines.flow.internal.SafeCollector r6 = (kotlinx.coroutines.flow.internal.SafeCollector) r6
            kotlin.coroutines.CoroutineContext r1 = r6.collectContext
            kotlin.coroutines.CoroutineContext$Element r1 = r1.get(r10)
            kotlinx.coroutines.Job$Key r2 = kotlinx.coroutines.Job.Key.$$INSTANCE
            if (r10 == r2) goto L31
            if (r11 == r1) goto L2a
            r10 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L2c
        L2a:
            int r10 = r0 + 1
        L2c:
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            goto L4c
        L31:
            kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
            kotlinx.coroutines.Job r11 = (kotlinx.coroutines.Job) r11
        L35:
            if (r11 != 0) goto L38
            goto L41
        L38:
            if (r11 != r1) goto L3c
        L3a:
            r5 = r11
            goto L41
        L3c:
            boolean r10 = r11 instanceof kotlinx.coroutines.internal.ScopeCoroutine
            if (r10 != 0) goto L72
            goto L3a
        L41:
            if (r5 != r1) goto L4d
            if (r1 != 0) goto L46
            goto L48
        L46:
            int r0 = r0 + 1
        L48:
            java.lang.Integer r10 = java.lang.Integer.valueOf(r0)
        L4c:
            return r10
        L4d:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r0 = "Flow invariant is violated:\n\t\tEmission from another coroutine is detected.\n\t\tChild of "
            r11.<init>(r0)
            r11.append(r5)
            java.lang.String r0 = ", expected child of "
            r11.append(r0)
            r11.append(r1)
            java.lang.String r0 = ".\n\t\tFlowCollector is not thread-safe and concurrent emissions are prohibited.\n\t\tTo mitigate this restriction please use 'channelFlow' builder instead of 'flow'"
            r11.append(r0)
            java.lang.String r11 = r11.toString()
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            throw r10
        L72:
            kotlinx.coroutines.JobSupport r11 = (kotlinx.coroutines.JobSupport) r11
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r10 = kotlinx.coroutines.JobSupport._parentHandle$FU
            java.lang.Object r10 = r10.get(r11)
            kotlinx.coroutines.ChildHandle r10 = (kotlinx.coroutines.ChildHandle) r10
            if (r10 == 0) goto L84
            kotlinx.coroutines.Job r10 = r10.getParent()
            r11 = r10
            goto L35
        L84:
            r11 = r5
            goto L35
        L86:
            androidx.compose.ui.text.input.PlatformTextInputPlugin r10 = (androidx.compose.ui.text.input.PlatformTextInputPlugin) r10
            androidx.compose.ui.text.input.PlatformTextInputPluginRegistryImpl$AdapterInput r11 = (androidx.compose.ui.text.input.PlatformTextInputPluginRegistryImpl.AdapterInput) r11
            java.lang.String r0 = "factory"
            kotlin.ResultKt.checkNotNullParameter(r10, r0)
            java.lang.String r10 = "platformTextInput"
            kotlin.ResultKt.checkNotNullParameter(r11, r10)
            androidx.compose.ui.platform.AndroidComposeView r6 = (androidx.compose.ui.platform.AndroidComposeView) r6
            java.lang.String r10 = "view"
            kotlin.ResultKt.checkNotNullParameter(r6, r10)
            androidx.compose.ui.text.input.TextInputServiceAndroid r10 = new androidx.compose.ui.text.input.TextInputServiceAndroid
            r10.<init>(r6)
            androidx.compose.ui.text.input.AndroidTextInputServicePlugin$Adapter r11 = new androidx.compose.ui.text.input.AndroidTextInputServicePlugin$Adapter
            androidx.compose.ui.text.input.TextInputService r0 = new androidx.compose.ui.text.input.TextInputService
            r0.<init>()
            java.util.concurrent.atomic.AtomicReference r1 = new java.util.concurrent.atomic.AtomicReference
            r1.<init>(r5)
            r11.<init>(r0, r10)
            return r11
        Lb0:
            androidx.compose.runtime.Composer r10 = (androidx.compose.runtime.Composer) r10
            java.lang.Number r11 = (java.lang.Number) r11
            int r11 = r11.intValue()
            r11 = r11 & 11
            if (r11 != r3) goto Lca
            r11 = r10
            androidx.compose.runtime.ComposerImpl r11 = (androidx.compose.runtime.ComposerImpl) r11
            boolean r1 = r11.getSkipping()
            if (r1 != 0) goto Lc6
            goto Lca
        Lc6:
            r11.skipToGroupEnd()
            goto Ld1
        Lca:
            androidx.compose.ui.platform.AbstractComposeView r6 = (androidx.compose.ui.platform.AbstractComposeView) r6
            r11 = 8
            r6.Content(r10, r11)
        Ld1:
            return r0
        Ld2:
            java.util.Set r10 = (java.util.Set) r10
            androidx.compose.runtime.snapshots.Snapshot r11 = (androidx.compose.runtime.snapshots.Snapshot) r11
            java.lang.String r1 = "applied"
            kotlin.ResultKt.checkNotNullParameter(r10, r1)
            java.lang.String r1 = "<anonymous parameter 1>"
            kotlin.ResultKt.checkNotNullParameter(r11, r1)
            androidx.compose.runtime.snapshots.SnapshotStateObserver r6 = (androidx.compose.runtime.snapshots.SnapshotStateObserver) r6
        Le2:
            java.util.concurrent.atomic.AtomicReference r11 = r6.pendingChanges
            java.lang.Object r1 = r11.get()
            if (r1 != 0) goto Lee
            r7 = r10
            java.util.Collection r7 = (java.util.Collection) r7
            goto L10c
        Lee:
            boolean r7 = r1 instanceof java.util.Set
            if (r7 == 0) goto Lfd
            java.util.Set[] r7 = new java.util.Set[r3]
            r7[r2] = r1
            r7[r4] = r10
            java.util.List r7 = kotlin.ResultKt.listOf(r7)
            goto L10c
        Lfd:
            boolean r7 = r1 instanceof java.util.List
            if (r7 == 0) goto L12b
            r7 = r1
            java.util.Collection r7 = (java.util.Collection) r7
            java.util.List r8 = kotlin.ResultKt.listOf(r10)
            java.util.ArrayList r7 = kotlin.collections.CollectionsKt___CollectionsKt.plus(r8, r7)
        L10c:
            boolean r8 = r11.compareAndSet(r1, r7)
            if (r8 == 0) goto L124
            boolean r10 = androidx.compose.runtime.snapshots.SnapshotStateObserver.access$drainChanges(r6)
            if (r10 == 0) goto L123
            androidx.compose.ui.node.LayoutNode$_foldedChildren$1 r10 = new androidx.compose.ui.node.LayoutNode$_foldedChildren$1
            r11 = 5
            r10.<init>(r11, r6)
            kotlin.jvm.functions.Function1 r11 = r6.onChangedExecutor
            r11.invoke(r10)
        L123:
            return r0
        L124:
            java.lang.Object r8 = r11.get()
            if (r8 == r1) goto L10c
            goto Le2
        L12b:
            java.lang.String r10 = "Unexpected notification"
            androidx.compose.runtime.EffectsKt.composeRuntimeError(r10)
            throw r5
        L131:
            androidx.compose.ui.input.pointer.PointerInputChange r10 = (androidx.compose.ui.input.pointer.PointerInputChange) r10
            androidx.compose.ui.geometry.Offset r11 = (androidx.compose.ui.geometry.Offset) r11
            long r0 = r11.packedValue
            java.lang.String r11 = "<anonymous parameter 0>"
            kotlin.ResultKt.checkNotNullParameter(r10, r11)
            _COROUTINE._BOUNDARY r6 = (_COROUTINE._BOUNDARY) r6
            r6.getClass()
            throw r5
        L142:
            androidx.compose.ui.unit.IntSize r10 = (androidx.compose.ui.unit.IntSize) r10
            long r0 = r10.packedValue
            androidx.compose.ui.unit.LayoutDirection r11 = (androidx.compose.ui.unit.LayoutDirection) r11
            long r10 = r9.m62invoke5SAbXVA(r0, r11)
            androidx.compose.ui.unit.IntOffset r0 = new androidx.compose.ui.unit.IntOffset
            r0.<init>(r10)
            return r0
        L152:
            androidx.compose.ui.unit.IntSize r10 = (androidx.compose.ui.unit.IntSize) r10
            long r0 = r10.packedValue
            androidx.compose.ui.unit.LayoutDirection r11 = (androidx.compose.ui.unit.LayoutDirection) r11
            long r10 = r9.m62invoke5SAbXVA(r0, r11)
            androidx.compose.ui.unit.IntOffset r0 = new androidx.compose.ui.unit.IntOffset
            r0.<init>(r10)
            return r0
        L162:
            androidx.compose.ui.unit.IntSize r10 = (androidx.compose.ui.unit.IntSize) r10
            long r0 = r10.packedValue
            androidx.compose.ui.unit.LayoutDirection r11 = (androidx.compose.ui.unit.LayoutDirection) r11
            long r10 = r9.m62invoke5SAbXVA(r0, r11)
            androidx.compose.ui.unit.IntOffset r0 = new androidx.compose.ui.unit.IntOffset
            r0.<init>(r10)
            return r0
        L172:
            androidx.compose.ui.Modifier r10 = (androidx.compose.ui.Modifier) r10
            androidx.compose.ui.Modifier$Element r11 = (androidx.compose.ui.Modifier.Element) r11
            java.lang.String r0 = "acc"
            kotlin.ResultKt.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "element"
            kotlin.ResultKt.checkNotNullParameter(r11, r0)
            boolean r0 = r11 instanceof androidx.compose.ui.ComposedModifier
            if (r0 == 0) goto L1a3
            androidx.compose.ui.ComposedModifier r11 = (androidx.compose.ui.ComposedModifier) r11
            kotlin.jvm.functions.Function3 r11 = r11.factory
            java.lang.String r0 = "null cannot be cast to non-null type @[ExtensionFunctionType] kotlin.Function3<androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, kotlin.Int, androidx.compose.ui.Modifier>"
            kotlin.ResultKt.checkNotNull(r11, r0)
            r0 = 3
            kotlin.ResultKt.beforeCheckcastToFunctionOfArity(r0, r11)
            androidx.compose.ui.Modifier$Companion r0 = androidx.compose.ui.Modifier.Companion.$$INSTANCE
            androidx.compose.runtime.Composer r6 = (androidx.compose.runtime.Composer) r6
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            java.lang.Object r11 = r11.invoke(r0, r6, r1)
            androidx.compose.ui.Modifier r11 = (androidx.compose.ui.Modifier) r11
            androidx.compose.ui.Modifier r11 = _COROUTINE._BOUNDARY.materializeModifier(r6, r11)
        L1a3:
            androidx.compose.ui.Modifier r10 = r10.then(r11)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.ComposedModifierKt$materialize$result$1.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    /* renamed from: invoke-5SAbXVA, reason: not valid java name */
    public final long m62invoke5SAbXVA(long j, LayoutDirection layoutDirection) {
        int i = this.$r8$classId;
        Object obj = this.$this_materialize;
        switch (i) {
            case 1:
                ResultKt.checkNotNullParameter(layoutDirection, "<anonymous parameter 1>");
                return ResultKt.IntOffset(0, ResultKt.roundToInt((1 + ((BiasAlignment.Vertical) ((Alignment.Vertical) obj)).bias) * (((int) (j & 4294967295L)) / 2.0f)));
            case 2:
                ResultKt.checkNotNullParameter(layoutDirection, "layoutDirection");
                return ((BiasAlignment) ((Alignment) obj)).m61alignKFBX0sM(0L, j, layoutDirection);
            default:
                ResultKt.checkNotNullParameter(layoutDirection, "layoutDirection");
                return ResultKt.IntOffset(((BiasAlignment.Horizontal) ((Alignment.Horizontal) obj)).align((int) (j >> 32), layoutDirection), 0);
        }
    }
}
