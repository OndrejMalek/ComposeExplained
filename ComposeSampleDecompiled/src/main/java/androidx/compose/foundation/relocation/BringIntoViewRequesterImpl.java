package androidx.compose.foundation.relocation;

import androidx.compose.runtime.collection.MutableVector;

/* loaded from: classes.dex */
public final class BringIntoViewRequesterImpl {
    public final MutableVector modifiers;

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public BringIntoViewRequesterImpl() {
        ?? obj = new Object();
        obj.content = new BringIntoViewRequesterNode[16];
        obj.size = 0;
        this.modifiers = obj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x00c0, code lost:
    
        if (r4 < r8) goto L17;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x00bd -> B:10:0x00bf). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object bringIntoView(androidx.compose.ui.geometry.Rect r17, kotlin.coroutines.Continuation r18) {
        /*
            r16 = this;
            r0 = r16
            r1 = r18
            boolean r2 = r1 instanceof androidx.compose.foundation.relocation.BringIntoViewRequesterImpl$bringIntoView$1
            if (r2 == 0) goto L17
            r2 = r1
            androidx.compose.foundation.relocation.BringIntoViewRequesterImpl$bringIntoView$1 r2 = (androidx.compose.foundation.relocation.BringIntoViewRequesterImpl$bringIntoView$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L17
            int r3 = r3 - r4
            r2.label = r3
            goto L1c
        L17:
            androidx.compose.foundation.relocation.BringIntoViewRequesterImpl$bringIntoView$1 r2 = new androidx.compose.foundation.relocation.BringIntoViewRequesterImpl$bringIntoView$1
            r2.<init>(r0, r1)
        L1c:
            java.lang.Object r1 = r2.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r3 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r4 = r2.label
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            r6 = 0
            r7 = 1
            if (r4 == 0) goto L40
            if (r4 != r7) goto L38
            int r4 = r2.I$1
            int r8 = r2.I$0
            java.lang.Object[] r9 = r2.L$1
            androidx.compose.ui.geometry.Rect r10 = r2.L$0
            kotlin.ResultKt.throwOnFailure(r1)
            r1 = r10
            goto Lbf
        L38:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L40:
            kotlin.ResultKt.throwOnFailure(r1)
            androidx.compose.runtime.collection.MutableVector r1 = r0.modifiers
            int r4 = r1.size
            if (r4 <= 0) goto Lc2
            java.lang.Object[] r1 = r1.content
            r9 = r1
            r8 = r4
            r4 = r6
            r1 = r17
        L50:
            r10 = r9[r4]
            androidx.compose.foundation.relocation.BringIntoViewRequesterNode r10 = (androidx.compose.foundation.relocation.BringIntoViewRequesterNode) r10
            r2.L$0 = r1
            r2.L$1 = r9
            r2.I$0 = r8
            r2.I$1 = r4
            r2.label = r7
            r10.getClass()
            androidx.compose.ui.modifier.ProvidableModifierLocal r11 = androidx.compose.foundation.relocation.BringIntoViewKt.ModifierLocalBringIntoViewParent
            java.lang.Object r11 = r10.getCurrent(r11)
            androidx.compose.foundation.relocation.BringIntoViewResponder_androidKt$defaultBringIntoViewParent$1 r11 = (androidx.compose.foundation.relocation.BringIntoViewResponder_androidKt$defaultBringIntoViewParent$1) r11
            if (r11 != 0) goto L6d
            androidx.compose.foundation.relocation.BringIntoViewResponder_androidKt$defaultBringIntoViewParent$1 r11 = r10.defaultParent
        L6d:
            androidx.compose.ui.layout.LayoutCoordinates r12 = r10.layoutCoordinates
            r13 = 0
            if (r12 == 0) goto L79
            boolean r14 = r12.isAttached()
            if (r14 == 0) goto L79
            goto L7a
        L79:
            r12 = r13
        L7a:
            if (r12 != 0) goto L7e
            r11 = r8
            goto Lba
        L7e:
            androidx.compose.ui.node.NodeCoordinator$invoke$1 r14 = new androidx.compose.ui.node.NodeCoordinator$invoke$1
            r15 = 2
            r14.<init>(r1, r15, r10)
            r11.getClass()
            androidx.compose.runtime.StaticProvidableCompositionLocal r10 = androidx.compose.ui.platform.AndroidCompositionLocals_androidKt.LocalView
            androidx.compose.ui.node.CompositionLocalConsumerModifierNode r11 = r11.$this_defaultBringIntoViewParent
            java.lang.Object r10 = androidx.compose.ui.node.Snake.currentValueOf(r11, r10)
            android.view.View r10 = (android.view.View) r10
            r11 = r8
            long r7 = androidx.compose.ui.geometry.Offset.Zero
            long r7 = r12.mo161localToRootMKHz9U(r7)
            java.lang.Object r12 = r14.invoke()
            androidx.compose.ui.geometry.Rect r12 = (androidx.compose.ui.geometry.Rect) r12
            if (r12 == 0) goto La4
            androidx.compose.ui.geometry.Rect r13 = r12.m82translatek4lQ0M(r7)
        La4:
            if (r13 == 0) goto Lba
            android.graphics.Rect r7 = new android.graphics.Rect
            float r8 = r13.left
            int r8 = (int) r8
            float r12 = r13.top
            int r12 = (int) r12
            float r14 = r13.right
            int r14 = (int) r14
            float r13 = r13.bottom
            int r13 = (int) r13
            r7.<init>(r8, r12, r14, r13)
            r10.requestRectangleOnScreen(r7, r6)
        Lba:
            if (r5 != r3) goto Lbd
            return r3
        Lbd:
            r8 = r11
            r7 = 1
        Lbf:
            int r4 = r4 + r7
            if (r4 < r8) goto L50
        Lc2:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.relocation.BringIntoViewRequesterImpl.bringIntoView(androidx.compose.ui.geometry.Rect, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
