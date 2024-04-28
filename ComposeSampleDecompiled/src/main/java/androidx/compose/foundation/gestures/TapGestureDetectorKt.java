package androidx.compose.foundation.gestures;

/* loaded from: classes.dex */
public abstract class TapGestureDetectorKt {
    public static final ScrollableKt$NoOpOnDragStarted$1 NoPressGesture = new ScrollableKt$NoOpOnDragStarted$1(1, null);

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:31:0x003c */
    /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2 */
    /* JADX DEBUG: Multi-variable search result rejected for r0v5, resolved type: androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2 */
    /* JADX DEBUG: Multi-variable search result rejected for r0v6, resolved type: androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0048 -> B:10:0x004b). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitFirstDown(androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl.PointerEventHandlerCoroutine r10, boolean r11, androidx.compose.ui.input.pointer.PointerEventPass r12, kotlin.coroutines.Continuation r13) {
        /*
            boolean r0 = r13 instanceof androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2
            if (r0 == 0) goto L13
            r0 = r13
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2 r0 = (androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2 r0 = new androidx.compose.foundation.gestures.TapGestureDetectorKt$awaitFirstDown$2
            r0.<init>(r13)
        L18:
            java.lang.Object r13 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            boolean r10 = r0.Z$0
            androidx.compose.ui.input.pointer.PointerEventPass r11 = r0.L$1
            androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine r12 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r13)
            r9 = r11
            r11 = r10
            r10 = r12
            r12 = r9
            goto L4b
        L31:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L39:
            kotlin.ResultKt.throwOnFailure(r13)
        L3c:
            r0.L$0 = r10
            r0.L$1 = r12
            r0.Z$0 = r11
            r0.label = r3
            java.lang.Object r13 = r10.awaitPointerEvent(r12, r0)
            if (r13 != r1) goto L4b
            return r1
        L4b:
            androidx.compose.ui.input.pointer.PointerEvent r13 = (androidx.compose.ui.input.pointer.PointerEvent) r13
            java.util.List r2 = r13.changes
            int r4 = r2.size()
            r5 = 0
            r6 = r5
        L55:
            if (r6 >= r4) goto L7d
            java.lang.Object r7 = r2.get(r6)
            androidx.compose.ui.input.pointer.PointerInputChange r7 = (androidx.compose.ui.input.pointer.PointerInputChange) r7
            if (r11 == 0) goto L73
            java.lang.String r8 = "<this>"
            kotlin.ResultKt.checkNotNullParameter(r7, r8)
            boolean r8 = r7.isConsumed()
            if (r8 != 0) goto L3c
            boolean r8 = r7.previousPressed
            if (r8 != 0) goto L3c
            boolean r7 = r7.pressed
            if (r7 == 0) goto L3c
            goto L7a
        L73:
            boolean r7 = _COROUTINE._BOUNDARY.changedToDownIgnoreConsumed(r7)
            if (r7 != 0) goto L7a
            goto L3c
        L7a:
            int r6 = r6 + 1
            goto L55
        L7d:
            java.util.List r10 = r13.changes
            java.lang.Object r10 = r10.get(r5)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown(androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine, boolean, androidx.compose.ui.input.pointer.PointerEventPass, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v12, resolved type: androidx.compose.foundation.gestures.TapGestureDetectorKt$waitForUpOrCancellation$2 */
    /* JADX DEBUG: Multi-variable search result rejected for r1v13, resolved type: androidx.compose.foundation.gestures.TapGestureDetectorKt$waitForUpOrCancellation$2 */
    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: androidx.compose.foundation.gestures.TapGestureDetectorKt$waitForUpOrCancellation$2 */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00b1, code lost:
    
        return null;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x00cd  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0059 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00c1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00e0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x00bf -> B:11:0x0030). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object waitForUpOrCancellation(androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl.PointerEventHandlerCoroutine r17, androidx.compose.ui.input.pointer.PointerEventPass r18, kotlin.coroutines.Continuation r19) {
        /*
            r0 = r19
            boolean r1 = r0 instanceof androidx.compose.foundation.gestures.TapGestureDetectorKt$waitForUpOrCancellation$2
            if (r1 == 0) goto L15
            r1 = r0
            androidx.compose.foundation.gestures.TapGestureDetectorKt$waitForUpOrCancellation$2 r1 = (androidx.compose.foundation.gestures.TapGestureDetectorKt$waitForUpOrCancellation$2) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.label = r2
            goto L1a
        L15:
            androidx.compose.foundation.gestures.TapGestureDetectorKt$waitForUpOrCancellation$2 r1 = new androidx.compose.foundation.gestures.TapGestureDetectorKt$waitForUpOrCancellation$2
            r1.<init>(r0)
        L1a:
            java.lang.Object r0 = r1.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r2 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r3 = r1.label
            r4 = 1
            r5 = 0
            r6 = 2
            if (r3 == 0) goto L45
            if (r3 == r4) goto L3d
            if (r3 != r6) goto L35
            androidx.compose.ui.input.pointer.PointerEventPass r3 = r1.L$1
            androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine r8 = r1.L$0
            kotlin.ResultKt.throwOnFailure(r0)
        L30:
            r15 = r3
            r3 = r1
            r1 = r15
            goto Lc2
        L35:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L3d:
            androidx.compose.ui.input.pointer.PointerEventPass r3 = r1.L$1
            androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine r8 = r1.L$0
            kotlin.ResultKt.throwOnFailure(r0)
            goto L62
        L45:
            kotlin.ResultKt.throwOnFailure(r0)
            r0 = r17
            r3 = r1
            r1 = r18
        L4d:
            r3.L$0 = r0
            r3.L$1 = r1
            r3.label = r4
            java.lang.Object r8 = r0.awaitPointerEvent(r1, r3)
            if (r8 != r2) goto L5a
            return r2
        L5a:
            r15 = r8
            r8 = r0
            r0 = r15
            r16 = r3
            r3 = r1
            r1 = r16
        L62:
            androidx.compose.ui.input.pointer.PointerEvent r0 = (androidx.compose.ui.input.pointer.PointerEvent) r0
            java.util.List r9 = r0.changes
            int r10 = r9.size()
            r11 = 0
        L6b:
            java.util.List r12 = r0.changes
            if (r11 >= r10) goto Le0
            java.lang.Object r13 = r9.get(r11)
            androidx.compose.ui.input.pointer.PointerInputChange r13 = (androidx.compose.ui.input.pointer.PointerInputChange) r13
            java.lang.String r14 = "<this>"
            kotlin.ResultKt.checkNotNullParameter(r13, r14)
            boolean r14 = r13.isConsumed()
            if (r14 != 0) goto L8b
            boolean r14 = r13.previousPressed
            if (r14 == 0) goto L8b
            boolean r13 = r13.pressed
            if (r13 != 0) goto L8b
            int r11 = r11 + 1
            goto L6b
        L8b:
            int r0 = r12.size()
            r9 = 0
        L90:
            if (r9 >= r0) goto Lb2
            java.lang.Object r10 = r12.get(r9)
            androidx.compose.ui.input.pointer.PointerInputChange r10 = (androidx.compose.ui.input.pointer.PointerInputChange) r10
            boolean r11 = r10.isConsumed()
            if (r11 != 0) goto Lb1
            androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl r11 = androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl.this
            long r13 = r11.boundsSize
            long r6 = r8.m158getExtendedTouchPaddingNHjbRc()
            boolean r6 = _COROUTINE._BOUNDARY.m14isOutOfBoundsjwHxaWs(r10, r13, r6)
            if (r6 == 0) goto Lad
            goto Lb1
        Lad:
            int r9 = r9 + 1
            r6 = 2
            goto L90
        Lb1:
            return r5
        Lb2:
            androidx.compose.ui.input.pointer.PointerEventPass r0 = androidx.compose.ui.input.pointer.PointerEventPass.Final
            r1.L$0 = r8
            r1.L$1 = r3
            r6 = 2
            r1.label = r6
            java.lang.Object r0 = r8.awaitPointerEvent(r0, r1)
            if (r0 != r2) goto L30
            return r2
        Lc2:
            androidx.compose.ui.input.pointer.PointerEvent r0 = (androidx.compose.ui.input.pointer.PointerEvent) r0
            java.util.List r0 = r0.changes
            int r7 = r0.size()
            r9 = 0
        Lcb:
            if (r9 >= r7) goto Ldd
            java.lang.Object r10 = r0.get(r9)
            androidx.compose.ui.input.pointer.PointerInputChange r10 = (androidx.compose.ui.input.pointer.PointerInputChange) r10
            boolean r10 = r10.isConsumed()
            if (r10 == 0) goto Lda
            return r5
        Lda:
            int r9 = r9 + 1
            goto Lcb
        Ldd:
            r0 = r8
            goto L4d
        Le0:
            r0 = 0
            java.lang.Object r0 = r12.get(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.TapGestureDetectorKt.waitForUpOrCancellation(androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl$PointerEventHandlerCoroutine, androidx.compose.ui.input.pointer.PointerEventPass, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
