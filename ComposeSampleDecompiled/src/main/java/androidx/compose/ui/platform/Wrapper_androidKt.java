package androidx.compose.ui.platform;

import android.view.ViewGroup;

/* loaded from: classes.dex */
public abstract class Wrapper_androidKt {
    public static final ViewGroup.LayoutParams DefaultLayoutParams = new ViewGroup.LayoutParams(-2, -2);

    /* JADX WARN: Removed duplicated region for block: B:19:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00da  */
    /* JADX WARN: Type inference failed for: r7v2, types: [androidx.compose.runtime.AbstractApplier, androidx.compose.ui.node.UiApplier] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.platform.WrappedComposition setContent(androidx.compose.ui.platform.AbstractComposeView r7, androidx.compose.runtime.CompositionContext r8, androidx.compose.runtime.internal.ComposableLambdaImpl r9) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.ResultKt.checkNotNullParameter(r7, r0)
            java.util.concurrent.atomic.AtomicBoolean r0 = androidx.compose.ui.platform.GlobalSnapshotManager.started
            r1 = 0
            r2 = 1
            boolean r0 = r0.compareAndSet(r1, r2)
            r3 = 0
            if (r0 == 0) goto L44
            r0 = -1
            r4 = 6
            kotlinx.coroutines.channels.BufferedChannel r0 = kotlinx.coroutines.JobKt.Channel$default(r0, r3, r4)
            kotlin.SynchronizedLazyImpl r4 = androidx.compose.ui.platform.AndroidUiDispatcher.Main$delegate
            java.lang.Object r4 = r4.getValue()
            kotlin.coroutines.CoroutineContext r4 = (kotlin.coroutines.CoroutineContext) r4
            kotlinx.coroutines.internal.ContextScope r4 = kotlin.ResultKt.CoroutineScope(r4)
            androidx.compose.ui.platform.GlobalSnapshotManager$ensureStarted$1 r5 = new androidx.compose.ui.platform.GlobalSnapshotManager$ensureStarted$1
            r5.<init>(r0, r3)
            r6 = 3
            kotlin.ResultKt.launch$default(r4, r3, r1, r5, r6)
            kotlin.collections.AbstractMap$toString$1 r4 = new kotlin.collections.AbstractMap$toString$1
            r5 = 17
            r4.<init>(r5, r0)
            java.lang.Object r0 = androidx.compose.runtime.snapshots.SnapshotKt.lock
            monitor-enter(r0)
            java.util.ArrayList r5 = androidx.compose.runtime.snapshots.SnapshotKt.globalWriteObservers     // Catch: java.lang.Throwable -> L41
            r5.add(r4)     // Catch: java.lang.Throwable -> L41
            monitor-exit(r0)
            androidx.compose.runtime.snapshots.SnapshotKt$emptyLambda$1 r0 = androidx.compose.runtime.snapshots.SnapshotKt$emptyLambda$1.INSTANCE$1
            androidx.compose.runtime.snapshots.SnapshotKt.advanceGlobalSnapshot(r0)
            goto L44
        L41:
            r7 = move-exception
            monitor-exit(r0)
            throw r7
        L44:
            int r0 = r7.getChildCount()
            if (r0 <= 0) goto L57
            android.view.View r0 = r7.getChildAt(r1)
            boolean r1 = r0 instanceof androidx.compose.ui.platform.AndroidComposeView
            if (r1 == 0) goto L55
            androidx.compose.ui.platform.AndroidComposeView r0 = (androidx.compose.ui.platform.AndroidComposeView) r0
            goto L5b
        L55:
            r0 = r3
            goto L5b
        L57:
            r7.removeAllViews()
            goto L55
        L5b:
            if (r0 != 0) goto L79
            androidx.compose.ui.platform.AndroidComposeView r0 = new androidx.compose.ui.platform.AndroidComposeView
            android.content.Context r1 = r7.getContext()
            java.lang.String r4 = "context"
            kotlin.ResultKt.checkNotNullExpressionValue(r1, r4)
            r4 = r8
            androidx.compose.runtime.Recomposer r4 = (androidx.compose.runtime.Recomposer) r4
            kotlin.coroutines.CoroutineContext r4 = r4.effectCoroutineContext
            r0.<init>(r1, r4)
            android.view.View r1 = r0.getView()
            android.view.ViewGroup$LayoutParams r4 = androidx.compose.ui.platform.Wrapper_androidKt.DefaultLayoutParams
            r7.addView(r1, r4)
        L79:
            int r7 = android.os.Build.VERSION.SDK_INT
            r1 = 29
            if (r7 < r1) goto Lb1
            androidx.compose.ui.platform.WrapperVerificationHelperMethods r7 = androidx.compose.ui.platform.WrapperVerificationHelperMethods.INSTANCE
            java.util.Map r7 = r7.attributeSourceResourceMap(r0)
            boolean r7 = r7.isEmpty()
            r7 = r7 ^ r2
            if (r7 == 0) goto Lb1
            java.util.WeakHashMap r7 = new java.util.WeakHashMap
            r7.<init>()
            java.util.Set r7 = java.util.Collections.newSetFromMap(r7)
            r1 = 2131034164(0x7f050034, float:1.7678838E38)
            r0.setTag(r1, r7)
            java.lang.Class<androidx.compose.ui.platform.InspectableValueKt> r7 = androidx.compose.ui.platform.InspectableValueKt.class
            java.lang.String r1 = "isDebugInspectorInfoEnabled"
            java.lang.reflect.Field r7 = r7.getDeclaredField(r1)     // Catch: java.lang.Exception -> Laa
            r7.setAccessible(r2)     // Catch: java.lang.Exception -> Laa
            r7.setBoolean(r3, r2)     // Catch: java.lang.Exception -> Laa
            goto Lb1
        Laa:
            java.lang.String r7 = "Wrapper"
            java.lang.String r1 = "Could not access isDebugInspectorInfoEnabled. Please set explicitly."
            android.util.Log.w(r7, r1)
        Lb1:
            androidx.compose.ui.node.UiApplier r7 = new androidx.compose.ui.node.UiApplier
            androidx.compose.ui.node.LayoutNode r1 = r0.getRoot()
            java.lang.String r2 = "root"
            kotlin.ResultKt.checkNotNullParameter(r1, r2)
            r7.<init>(r1)
            java.lang.Object r1 = androidx.compose.runtime.CompositionKt.PendingApplyNoModifications
            androidx.compose.runtime.CompositionImpl r1 = new androidx.compose.runtime.CompositionImpl
            r1.<init>(r8, r7)
            android.view.View r7 = r0.getView()
            r8 = 2131034198(0x7f050056, float:1.7678907E38)
            java.lang.Object r7 = r7.getTag(r8)
            boolean r2 = r7 instanceof androidx.compose.ui.platform.WrappedComposition
            if (r2 == 0) goto Ld8
            r3 = r7
            androidx.compose.ui.platform.WrappedComposition r3 = (androidx.compose.ui.platform.WrappedComposition) r3
        Ld8:
            if (r3 != 0) goto Le6
            androidx.compose.ui.platform.WrappedComposition r3 = new androidx.compose.ui.platform.WrappedComposition
            r3.<init>(r0, r1)
            android.view.View r7 = r0.getView()
            r7.setTag(r8, r3)
        Le6:
            r3.setContent(r9)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.Wrapper_androidKt.setContent(androidx.compose.ui.platform.AbstractComposeView, androidx.compose.runtime.CompositionContext, androidx.compose.runtime.internal.ComposableLambdaImpl):androidx.compose.ui.platform.WrappedComposition");
    }
}
