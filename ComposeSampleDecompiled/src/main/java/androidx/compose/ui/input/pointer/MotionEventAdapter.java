package androidx.compose.ui.input.pointer;

import android.util.SparseBooleanArray;
import android.util.SparseLongArray;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class MotionEventAdapter {
    public long nextId;
    public final SparseLongArray motionEventToComposePointerIdMap = new SparseLongArray();
    public final SparseBooleanArray canHover = new SparseBooleanArray();
    public final ArrayList pointers = new ArrayList();
    public int previousToolType = -1;
    public int previousSource = -1;

    /* JADX WARN: Code restructure failed: missing block: B:60:0x015c, code lost:
    
        if (r7 != 4) goto L80;
     */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x016c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.ui.input.pointer.PointerInputEvent convertToPointerInputEvent$ui_release(android.view.MotionEvent r37, androidx.compose.ui.input.pointer.PositionCalculator r38) {
        /*
            r36 = this;
            r0 = r36
            r1 = r37
            r2 = r38
            java.lang.String r3 = "motionEvent"
            kotlin.ResultKt.checkNotNullParameter(r1, r3)
            java.lang.String r3 = "positionCalculator"
            kotlin.ResultKt.checkNotNullParameter(r2, r3)
            int r3 = r37.getActionMasked()
            android.util.SparseLongArray r4 = r0.motionEventToComposePointerIdMap
            android.util.SparseBooleanArray r5 = r0.canHover
            r6 = 3
            if (r3 != r6) goto L23
            r4.clear()
            r5.clear()
            r1 = 0
            return r1
        L23:
            int r7 = r37.getPointerCount()
            r8 = 1
            r9 = 0
            if (r7 == r8) goto L2c
            goto L46
        L2c:
            int r7 = r1.getToolType(r9)
            int r10 = r37.getSource()
            int r11 = r0.previousToolType
            if (r7 != r11) goto L3c
            int r11 = r0.previousSource
            if (r10 == r11) goto L46
        L3c:
            r0.previousToolType = r7
            r0.previousSource = r10
            r5.clear()
            r4.clear()
        L46:
            int r7 = r37.getActionMasked()
            r10 = 1
            r12 = 9
            if (r7 == 0) goto L6a
            r13 = 5
            if (r7 == r13) goto L6a
            if (r7 == r12) goto L56
            goto L90
        L56:
            int r7 = r1.getPointerId(r9)
            int r13 = r4.indexOfKey(r7)
            if (r13 >= 0) goto L90
            long r13 = r0.nextId
            long r8 = r13 + r10
            r0.nextId = r8
            r4.put(r7, r13)
            goto L90
        L6a:
            int r7 = r37.getActionIndex()
            int r8 = r1.getPointerId(r7)
            int r9 = r4.indexOfKey(r8)
            if (r9 >= 0) goto L90
            long r13 = r0.nextId
            r16 = r7
            long r6 = r13 + r10
            r0.nextId = r6
            r4.put(r8, r13)
            r6 = r16
            int r6 = r1.getToolType(r6)
            r7 = 3
            if (r6 != r7) goto L90
            r6 = 1
            r5.put(r8, r6)
        L90:
            r6 = 10
            if (r3 == r6) goto L9c
            r7 = 7
            if (r3 == r7) goto L9c
            if (r3 != r12) goto L9a
            goto L9c
        L9a:
            r7 = 0
            goto L9d
        L9c:
            r7 = 1
        L9d:
            r8 = 8
            if (r3 != r8) goto La3
            r13 = 1
            goto La4
        La3:
            r13 = 0
        La4:
            if (r7 == 0) goto Lb3
            int r14 = r37.getActionIndex()
            int r14 = r1.getPointerId(r14)
            r15 = 1
            r5.put(r14, r15)
            goto Lb4
        Lb3:
            r15 = 1
        Lb4:
            r9 = 6
            if (r3 == r15) goto Lc0
            if (r3 == r9) goto Lbb
            r3 = -1
            goto Lc1
        Lbb:
            int r3 = r37.getActionIndex()
            goto Lc1
        Lc0:
            r3 = 0
        Lc1:
            java.util.ArrayList r15 = r0.pointers
            r15.clear()
            int r14 = r37.getPointerCount()
            r9 = 0
        Lcb:
            if (r9 >= r14) goto L20d
            if (r7 != 0) goto Ldc
            if (r9 == r3) goto Ldc
            if (r13 == 0) goto Ld9
            int r17 = r37.getButtonState()
            if (r17 == 0) goto Ldc
        Ld9:
            r27 = 1
            goto Lde
        Ldc:
            r27 = 0
        Lde:
            int r12 = r1.getPointerId(r9)
            int r6 = r4.indexOfKey(r12)
            if (r6 < 0) goto Lf2
            long r18 = r4.valueAt(r6)
            r35 = r7
            r6 = r9
            r19 = r18
            goto L103
        Lf2:
            r34 = r9
            long r8 = r0.nextId
            r35 = r7
            long r6 = r8 + r10
            r0.nextId = r6
            r4.put(r12, r8)
            r19 = r8
            r6 = r34
        L103:
            float r28 = r1.getPressure(r6)
            float r7 = r1.getX(r6)
            float r8 = r1.getY(r6)
            long r7 = _COROUTINE._BOUNDARY.Offset(r7, r8)
            if (r6 != 0) goto L12d
            float r7 = r37.getRawX()
            float r8 = r37.getRawY()
            long r7 = _COROUTINE._BOUNDARY.Offset(r7, r8)
            r9 = r2
            androidx.compose.ui.platform.AndroidComposeView r9 = (androidx.compose.ui.platform.AndroidComposeView) r9
            long r21 = r9.m219screenToLocalMKHz9U(r7)
        L128:
            r23 = r7
            r25 = r21
            goto L14c
        L12d:
            int r9 = android.os.Build.VERSION.SDK_INT
            r12 = 29
            if (r9 < r12) goto L141
            androidx.compose.ui.input.pointer.MotionEventHelper r7 = androidx.compose.ui.input.pointer.MotionEventHelper.INSTANCE
            long r7 = r7.m154toRawOffsetdBAh8RU(r1, r6)
            r9 = r2
            androidx.compose.ui.platform.AndroidComposeView r9 = (androidx.compose.ui.platform.AndroidComposeView) r9
            long r21 = r9.m219screenToLocalMKHz9U(r7)
            goto L128
        L141:
            r9 = r2
            androidx.compose.ui.platform.AndroidComposeView r9 = (androidx.compose.ui.platform.AndroidComposeView) r9
            long r21 = r9.m218localToScreenMKHz9U(r7)
            r25 = r7
            r23 = r21
        L14c:
            int r7 = r1.getToolType(r6)
            if (r7 == 0) goto L16c
            r8 = 1
            if (r7 == r8) goto L168
            r9 = 2
            if (r7 == r9) goto L164
            r12 = 3
            if (r7 == r12) goto L161
            r9 = 4
            if (r7 == r9) goto L161
        L15e:
            r29 = 0
            goto L16e
        L161:
            r29 = r9
            goto L16e
        L164:
            r12 = 3
            r29 = r12
            goto L16e
        L168:
            r12 = 3
            r29 = 1
            goto L16e
        L16c:
            r12 = 3
            goto L15e
        L16e:
            java.util.ArrayList r7 = new java.util.ArrayList
            int r9 = r37.getHistorySize()
            r7.<init>(r9)
            int r9 = r37.getHistorySize()
            r8 = 0
        L17c:
            if (r8 >= r9) goto L1bd
            float r10 = r1.getHistoricalX(r6, r8)
            float r11 = r1.getHistoricalY(r6, r8)
            boolean r16 = java.lang.Float.isInfinite(r10)
            if (r16 != 0) goto L1b1
            boolean r16 = java.lang.Float.isNaN(r10)
            if (r16 != 0) goto L1b1
            boolean r16 = java.lang.Float.isInfinite(r11)
            if (r16 != 0) goto L1b1
            boolean r16 = java.lang.Float.isNaN(r11)
            if (r16 != 0) goto L1b1
            androidx.compose.ui.input.pointer.HistoricalChange r12 = new androidx.compose.ui.input.pointer.HistoricalChange
            r34 = r3
            long r2 = r1.getHistoricalEventTime(r8)
            long r10 = _COROUTINE._BOUNDARY.Offset(r10, r11)
            r12.<init>(r2, r10)
            r7.add(r12)
            goto L1b3
        L1b1:
            r34 = r3
        L1b3:
            int r8 = r8 + 1
            r2 = r38
            r3 = r34
            r10 = 1
            r12 = 3
            goto L17c
        L1bd:
            r34 = r3
            int r2 = r37.getActionMasked()
            r3 = 8
            if (r2 != r3) goto L1dd
            r2 = 10
            float r8 = r1.getAxisValue(r2)
            r9 = 9
            float r10 = r1.getAxisValue(r9)
            float r10 = -r10
            r11 = 0
            float r10 = r10 + r11
            long r10 = _COROUTINE._BOUNDARY.Offset(r8, r10)
        L1da:
            r32 = r10
            goto L1e4
        L1dd:
            r2 = 10
            r9 = 9
            long r10 = androidx.compose.ui.geometry.Offset.Zero
            goto L1da
        L1e4:
            int r8 = r1.getPointerId(r6)
            r10 = 0
            boolean r30 = r5.get(r8, r10)
            androidx.compose.ui.input.pointer.PointerInputEventData r8 = new androidx.compose.ui.input.pointer.PointerInputEventData
            long r21 = r37.getEventTime()
            r18 = r8
            r31 = r7
            r18.<init>(r19, r21, r23, r25, r27, r28, r29, r30, r31, r32)
            r15.add(r8)
            int r6 = r6 + 1
            r8 = r3
            r12 = r9
            r3 = r34
            r7 = r35
            r10 = 1
            r9 = r6
            r6 = r2
            r2 = r38
            goto Lcb
        L20d:
            int r2 = r37.getActionMasked()
            r3 = 1
            if (r2 == r3) goto L219
            r6 = 6
            if (r2 == r6) goto L219
            r10 = 0
            goto L22e
        L219:
            int r2 = r37.getActionIndex()
            int r2 = r1.getPointerId(r2)
            r10 = 0
            boolean r6 = r5.get(r2, r10)
            if (r6 != 0) goto L22e
            r4.delete(r2)
            r5.delete(r2)
        L22e:
            int r2 = r4.size()
            int r6 = r37.getPointerCount()
            if (r2 <= r6) goto L25f
            int r2 = r4.size()
            r3 = 1
            int r2 = r2 - r3
            r3 = -1
        L23f:
            if (r3 >= r2) goto L25f
            int r6 = r4.keyAt(r2)
            int r7 = r37.getPointerCount()
            r8 = r10
        L24a:
            if (r8 >= r7) goto L256
            int r9 = r1.getPointerId(r8)
            if (r9 != r6) goto L253
            goto L25c
        L253:
            int r8 = r8 + 1
            goto L24a
        L256:
            r4.removeAt(r2)
            r5.delete(r6)
        L25c:
            int r2 = r2 + (-1)
            goto L23f
        L25f:
            androidx.compose.ui.input.pointer.PointerInputEvent r2 = new androidx.compose.ui.input.pointer.PointerInputEvent
            r37.getEventTime()
            r2.<init>(r15, r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.input.pointer.MotionEventAdapter.convertToPointerInputEvent$ui_release(android.view.MotionEvent, androidx.compose.ui.input.pointer.PositionCalculator):androidx.compose.ui.input.pointer.PointerInputEvent");
    }
}
