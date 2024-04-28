package androidx.compose.ui.focus;

import androidx.compose.runtime.collection.MutableVector;

/* loaded from: classes.dex */
public final class FocusRequester {
    public final MutableVector focusRequesterNodes;
    public static final FocusRequester Default = new FocusRequester();
    public static final FocusRequester Cancel = new FocusRequester();

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public FocusRequester() {
        ?? obj = new Object();
        obj.content = new FocusRequesterModifierNode[16];
        obj.size = 0;
        this.focusRequesterNodes = obj;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:49:0x00b7 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:54:0x0063 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:55:0x0063 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:63:0x007f */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x003f, code lost:
    
        continue;
     */
    /* JADX WARN: Type inference failed for: r10v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v1, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean focus$ui_release() {
        /*
            r14 = this;
            androidx.compose.ui.focus.FocusRequester r0 = androidx.compose.ui.focus.FocusRequester.Default
            java.lang.String r1 = "\n    Please check whether the focusRequester is FocusRequester.Cancel or FocusRequester.Default\n    before invoking any functions on the focusRequester.\n"
            if (r14 == r0) goto Lee
            androidx.compose.ui.focus.FocusRequester r0 = androidx.compose.ui.focus.FocusRequester.Cancel
            if (r14 == r0) goto Le4
            androidx.compose.runtime.collection.MutableVector r0 = r14.focusRequesterNodes
            boolean r1 = r0.isNotEmpty()
            if (r1 == 0) goto Ld8
            int r1 = r0.size
            r2 = 0
            if (r1 <= 0) goto Ld7
            java.lang.Object[] r0 = r0.content
            r3 = r2
            r4 = r3
        L1b:
            r5 = r0[r3]
            androidx.compose.ui.focus.FocusRequesterModifierNode r5 = (androidx.compose.ui.focus.FocusRequesterModifierNode) r5
            androidx.compose.ui.Modifier$Node r5 = (androidx.compose.ui.Modifier.Node) r5
            androidx.compose.ui.Modifier$Node r5 = r5.node
            boolean r6 = r5.isAttached
            if (r6 == 0) goto Lcb
            androidx.compose.runtime.collection.MutableVector r6 = new androidx.compose.runtime.collection.MutableVector
            r7 = 16
            androidx.compose.ui.Modifier$Node[] r8 = new androidx.compose.ui.Modifier.Node[r7]
            r6.<init>()
            r6.content = r8
            r6.size = r2
            androidx.compose.ui.Modifier$Node r8 = r5.child
            if (r8 != 0) goto L3c
            androidx.compose.ui.node.Snake.access$addLayoutNodeChildren(r6, r5)
            goto L3f
        L3c:
            r6.add(r8)
        L3f:
            boolean r5 = r6.isNotEmpty()
            if (r5 == 0) goto Lc5
            int r5 = r6.size
            r8 = 1
            int r5 = r5 - r8
            java.lang.Object r5 = r6.removeAt(r5)
            androidx.compose.ui.Modifier$Node r5 = (androidx.compose.ui.Modifier.Node) r5
            int r9 = r5.aggregateChildKindSet
            r9 = r9 & 1024(0x400, float:1.435E-42)
            if (r9 != 0) goto L59
            androidx.compose.ui.node.Snake.access$addLayoutNodeChildren(r6, r5)
            goto L3f
        L59:
            if (r5 == 0) goto L3f
            int r9 = r5.kindSet
            r9 = r9 & 1024(0x400, float:1.435E-42)
            if (r9 == 0) goto Lc2
            r9 = 0
            r10 = r9
        L63:
            if (r5 == 0) goto L3f
            boolean r11 = r5 instanceof androidx.compose.ui.focus.FocusTargetNode
            if (r11 == 0) goto L83
            androidx.compose.ui.focus.FocusTargetNode r5 = (androidx.compose.ui.focus.FocusTargetNode) r5
            androidx.compose.ui.focus.FocusPropertiesImpl r11 = r5.fetchFocusProperties$ui_release()
            boolean r11 = r11.canFocus
            if (r11 == 0) goto L78
            boolean r5 = _COROUTINE._BOUNDARY.requestFocus(r5)
            goto L7f
        L78:
            androidx.compose.ui.focus.FocusProperties$exit$1 r11 = androidx.compose.ui.focus.FocusProperties$exit$1.INSTANCE$4
            r12 = 7
            boolean r5 = _COROUTINE._BOUNDARY.m10findChildCorrespondingToFocusEnterOMvw8(r5, r12, r11)
        L7f:
            if (r5 == 0) goto Lbd
            r4 = r8
            goto Lc5
        L83:
            int r11 = r5.kindSet
            r11 = r11 & 1024(0x400, float:1.435E-42)
            if (r11 == 0) goto Lbd
            boolean r11 = r5 instanceof androidx.compose.ui.node.DelegatingNode
            if (r11 == 0) goto Lbd
            r11 = r5
            androidx.compose.ui.node.DelegatingNode r11 = (androidx.compose.ui.node.DelegatingNode) r11
            androidx.compose.ui.Modifier$Node r11 = r11.delegate
            r12 = r2
        L93:
            if (r11 == 0) goto Lba
            int r13 = r11.kindSet
            r13 = r13 & 1024(0x400, float:1.435E-42)
            if (r13 == 0) goto Lb7
            int r12 = r12 + 1
            if (r12 != r8) goto La1
            r5 = r11
            goto Lb7
        La1:
            if (r10 != 0) goto Lae
            androidx.compose.runtime.collection.MutableVector r10 = new androidx.compose.runtime.collection.MutableVector
            androidx.compose.ui.Modifier$Node[] r13 = new androidx.compose.ui.Modifier.Node[r7]
            r10.<init>()
            r10.content = r13
            r10.size = r2
        Lae:
            if (r5 == 0) goto Lb4
            r10.add(r5)
            r5 = r9
        Lb4:
            r10.add(r11)
        Lb7:
            androidx.compose.ui.Modifier$Node r11 = r11.child
            goto L93
        Lba:
            if (r12 != r8) goto Lbd
            goto L63
        Lbd:
            androidx.compose.ui.Modifier$Node r5 = androidx.compose.ui.node.Snake.access$pop(r10)
            goto L63
        Lc2:
            androidx.compose.ui.Modifier$Node r5 = r5.child
            goto L59
        Lc5:
            int r3 = r3 + 1
            if (r3 < r1) goto L1b
            r2 = r4
            goto Ld7
        Lcb:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "visitChildren called on an unattached node"
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        Ld7:
            return r2
        Ld8:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "\n   FocusRequester is not initialized. Here are some possible fixes:\n\n   1. Remember the FocusRequester: val focusRequester = remember { FocusRequester() }\n   2. Did you forget to add a Modifier.focusRequester() ?\n   3. Are you attempting to request focus during composition? Focus requests should be made in\n   response to some event. Eg Modifier.clickable { focusRequester.requestFocus() }\n"
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        Le4:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        Lee:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusRequester.focus$ui_release():boolean");
    }
}
