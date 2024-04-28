package androidx.compose.ui.focus;

import _COROUTINE._BOUNDARY;
import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.AndroidComposeView$focusOwner$1;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class FocusOwnerImpl implements FocusOwner {
    public final FocusInvalidationManager focusInvalidationManager;
    public LayoutDirection layoutDirection;
    public final FocusTargetNode rootFocusNode = new FocusTargetNode();
    public final FocusOwnerImpl$modifier$1 modifier = new ModifierNodeElement() { // from class: androidx.compose.ui.focus.FocusOwnerImpl$modifier$1
        @Override // androidx.compose.ui.node.ModifierNodeElement
        public final Modifier.Node create() {
            return FocusOwnerImpl.this.rootFocusNode;
        }

        public final boolean equals(Object obj) {
            return obj == this;
        }

        public final int hashCode() {
            return FocusOwnerImpl.this.rootFocusNode.hashCode();
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public final void update(Modifier.Node node) {
            ResultKt.checkNotNullParameter((FocusTargetNode) node, "node");
        }
    };

    /* JADX WARN: Type inference failed for: r2v1, types: [androidx.compose.ui.focus.FocusOwnerImpl$modifier$1] */
    public FocusOwnerImpl(AndroidComposeView$focusOwner$1 androidComposeView$focusOwner$1) {
        this.focusInvalidationManager = new FocusInvalidationManager(androidComposeView$focusOwner$1);
    }

    public final void clearFocus(boolean z, boolean z2) {
        FocusStateImpl focusStateImpl;
        int ordinal;
        FocusTargetNode focusTargetNode = this.rootFocusNode;
        if (z || !((ordinal = AnimationEndReason$EnumUnboxingLocalUtility.ordinal(_BOUNDARY.m15performCustomClearFocusMxy_nc0(focusTargetNode, 8))) == 1 || ordinal == 2 || ordinal == 3)) {
            FocusStateImpl focusStateImpl2 = focusTargetNode.focusState;
            if (_BOUNDARY.clearFocus(focusTargetNode, z, z2)) {
                int ordinal2 = focusStateImpl2.ordinal();
                if (ordinal2 == 0 || ordinal2 == 1 || ordinal2 == 2) {
                    focusStateImpl = FocusStateImpl.Active;
                } else {
                    if (ordinal2 != 3) {
                        throw new RuntimeException();
                    }
                    focusStateImpl = FocusStateImpl.Inactive;
                }
                focusTargetNode.focusState = focusStateImpl;
            }
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:109:0x01d1 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:114:0x0187 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:115:0x0187 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:118:0x01d7 */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0257, code lost:
    
        if (m69moveFocus3ESFkO8(r18) == false) goto L189;
     */
    /* JADX WARN: Type inference failed for: r11v8, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v27, types: [kotlin.jvm.internal.Ref$BooleanRef, java.lang.Object] */
    /* renamed from: moveFocus-3ESFkO8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean m69moveFocus3ESFkO8(int r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            androidx.compose.ui.focus.FocusTargetNode r2 = r0.rootFocusNode
            androidx.compose.ui.focus.FocusTargetNode r3 = _COROUTINE._BOUNDARY.findActiveFocusNode(r2)
            r4 = 0
            if (r3 != 0) goto Le
            return r4
        Le:
            androidx.compose.ui.unit.LayoutDirection r5 = r0.layoutDirection
            java.lang.String r6 = "layoutDirection"
            if (r5 == 0) goto L28b
            androidx.compose.ui.focus.FocusPropertiesImpl r8 = r3.fetchFocusProperties$ui_release()
            r9 = 1
            boolean r10 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r9)
            r11 = 8
            r12 = 7
            r13 = 4
            r14 = 6
            r15 = 5
            r7 = 3
            r4 = 2
            if (r10 == 0) goto L2b
            androidx.compose.ui.focus.FocusRequester r5 = r8.next
            goto Lb6
        L2b:
            boolean r10 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r4)
            if (r10 == 0) goto L35
            androidx.compose.ui.focus.FocusRequester r5 = r8.previous
            goto Lb6
        L35:
            boolean r10 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r15)
            if (r10 == 0) goto L3f
            androidx.compose.ui.focus.FocusRequester r5 = r8.up
            goto Lb6
        L3f:
            boolean r10 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r14)
            if (r10 == 0) goto L49
            androidx.compose.ui.focus.FocusRequester r5 = r8.down
            goto Lb6
        L49:
            boolean r10 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r7)
            if (r10 == 0) goto L6c
            int r5 = r5.ordinal()
            if (r5 == 0) goto L60
            if (r5 != r9) goto L5a
            androidx.compose.ui.focus.FocusRequester r5 = r8.end
            goto L62
        L5a:
            androidx.startup.StartupException r1 = new androidx.startup.StartupException
            r1.<init>()
            throw r1
        L60:
            androidx.compose.ui.focus.FocusRequester r5 = r8.start
        L62:
            androidx.compose.ui.focus.FocusRequester r10 = androidx.compose.ui.focus.FocusRequester.Default
            if (r5 != r10) goto L67
            r5 = 0
        L67:
            if (r5 != 0) goto Lb6
            androidx.compose.ui.focus.FocusRequester r5 = r8.left
            goto Lb6
        L6c:
            boolean r10 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r13)
            if (r10 == 0) goto L8f
            int r5 = r5.ordinal()
            if (r5 == 0) goto L83
            if (r5 != r9) goto L7d
            androidx.compose.ui.focus.FocusRequester r5 = r8.start
            goto L85
        L7d:
            androidx.startup.StartupException r1 = new androidx.startup.StartupException
            r1.<init>()
            throw r1
        L83:
            androidx.compose.ui.focus.FocusRequester r5 = r8.end
        L85:
            androidx.compose.ui.focus.FocusRequester r10 = androidx.compose.ui.focus.FocusRequester.Default
            if (r5 != r10) goto L8a
            r5 = 0
        L8a:
            if (r5 != 0) goto Lb6
            androidx.compose.ui.focus.FocusRequester r5 = r8.right
            goto Lb6
        L8f:
            boolean r5 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r12)
            if (r5 == 0) goto La3
            androidx.compose.ui.focus.FocusProperties$exit$1 r5 = r8.enter
            androidx.compose.ui.focus.FocusDirection r8 = new androidx.compose.ui.focus.FocusDirection
            r8.<init>(r1)
            java.lang.Object r5 = r5.invoke(r8)
            androidx.compose.ui.focus.FocusRequester r5 = (androidx.compose.ui.focus.FocusRequester) r5
            goto Lb6
        La3:
            boolean r5 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r11)
            if (r5 == 0) goto L27f
            androidx.compose.ui.focus.FocusProperties$exit$1 r5 = r8.exit
            androidx.compose.ui.focus.FocusDirection r8 = new androidx.compose.ui.focus.FocusDirection
            r8.<init>(r1)
            java.lang.Object r5 = r5.invoke(r8)
            androidx.compose.ui.focus.FocusRequester r5 = (androidx.compose.ui.focus.FocusRequester) r5
        Lb6:
            androidx.compose.ui.focus.FocusRequester r8 = androidx.compose.ui.focus.FocusRequester.Default
            if (r5 == r8) goto Lc8
            androidx.compose.ui.focus.FocusRequester r1 = androidx.compose.ui.focus.FocusRequester.Cancel
            if (r5 == r1) goto Lc6
            boolean r1 = r5.focus$ui_release()
            if (r1 == 0) goto Lc6
            r4 = r9
            goto Lc7
        Lc6:
            r4 = 0
        Lc7:
            return r4
        Lc8:
            kotlin.jvm.internal.Ref$BooleanRef r5 = new kotlin.jvm.internal.Ref$BooleanRef
            r5.<init>()
            androidx.compose.ui.unit.LayoutDirection r8 = r0.layoutDirection
            if (r8 == 0) goto L27a
            androidx.compose.runtime.RecomposeScopeImpl$end$1$2 r6 = new androidx.compose.runtime.RecomposeScopeImpl$end$1$2
            r6.<init>(r1, r4, r3, r5)
            boolean r3 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r9)
            if (r3 == 0) goto Ldd
            goto Le3
        Ldd:
            boolean r3 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r4)
            if (r3 == 0) goto L107
        Le3:
            boolean r3 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r9)
            if (r3 == 0) goto Lef
            boolean r3 = _COROUTINE._BOUNDARY.forwardFocusSearch(r2, r6)
            goto L214
        Lef:
            boolean r3 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r4)
            if (r3 == 0) goto Lfb
            boolean r3 = _COROUTINE._BOUNDARY.backwardFocusSearch(r2, r6)
            goto L214
        Lfb:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "This function should only be used for 1-D focus search"
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L107:
            boolean r3 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r7)
            if (r3 == 0) goto L10e
            goto L122
        L10e:
            boolean r3 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r13)
            if (r3 == 0) goto L115
            goto L122
        L115:
            boolean r3 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r15)
            if (r3 == 0) goto L11c
            goto L122
        L11c:
            boolean r3 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r14)
            if (r3 == 0) goto L131
        L122:
            java.lang.Boolean r3 = _COROUTINE._BOUNDARY.m23twoDimensionalFocusSearchOMvw8(r2, r1, r6)
            if (r3 == 0) goto L12e
            boolean r3 = r3.booleanValue()
            goto L214
        L12e:
            r3 = 0
            goto L214
        L131:
            boolean r3 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r12)
            if (r3 == 0) goto L159
            int r3 = r8.ordinal()
            if (r3 == 0) goto L147
            if (r3 != r9) goto L141
            r13 = r7
            goto L147
        L141:
            androidx.startup.StartupException r1 = new androidx.startup.StartupException
            r1.<init>()
            throw r1
        L147:
            androidx.compose.ui.focus.FocusTargetNode r3 = _COROUTINE._BOUNDARY.findActiveFocusNode(r2)
            if (r3 == 0) goto L12e
            java.lang.Boolean r3 = _COROUTINE._BOUNDARY.m23twoDimensionalFocusSearchOMvw8(r3, r13, r6)
            if (r3 == 0) goto L12e
            boolean r3 = r3.booleanValue()
            goto L214
        L159:
            boolean r3 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r11)
            if (r3 == 0) goto L25e
            androidx.compose.ui.focus.FocusTargetNode r3 = _COROUTINE._BOUNDARY.findActiveFocusNode(r2)
            if (r3 == 0) goto L1ff
            androidx.compose.ui.Modifier$Node r8 = r3.node
            boolean r10 = r8.isAttached
            if (r10 == 0) goto L1f3
            androidx.compose.ui.Modifier$Node r8 = r8.parent
            androidx.compose.ui.node.LayoutNode r3 = androidx.compose.ui.node.Snake.requireLayoutNode(r3)
        L171:
            if (r3 == 0) goto L1ee
            androidx.compose.ui.node.NodeChain r10 = r3.nodes
            androidx.compose.ui.Modifier$Node r10 = r10.head
            int r10 = r10.aggregateChildKindSet
            r10 = r10 & 1024(0x400, float:1.435E-42)
            if (r10 == 0) goto L1df
        L17d:
            if (r8 == 0) goto L1df
            int r10 = r8.kindSet
            r10 = r10 & 1024(0x400, float:1.435E-42)
            if (r10 == 0) goto L1dc
            r10 = r8
            r11 = 0
        L187:
            if (r10 == 0) goto L1dc
            boolean r12 = r10 instanceof androidx.compose.ui.focus.FocusTargetNode
            if (r12 == 0) goto L19a
            androidx.compose.ui.focus.FocusTargetNode r10 = (androidx.compose.ui.focus.FocusTargetNode) r10
            androidx.compose.ui.focus.FocusPropertiesImpl r12 = r10.fetchFocusProperties$ui_release()
            boolean r12 = r12.canFocus
            if (r12 == 0) goto L1d7
            r16 = r10
            goto L1f0
        L19a:
            int r12 = r10.kindSet
            r12 = r12 & 1024(0x400, float:1.435E-42)
            if (r12 == 0) goto L1d7
            boolean r12 = r10 instanceof androidx.compose.ui.node.DelegatingNode
            if (r12 == 0) goto L1d7
            r12 = r10
            androidx.compose.ui.node.DelegatingNode r12 = (androidx.compose.ui.node.DelegatingNode) r12
            androidx.compose.ui.Modifier$Node r12 = r12.delegate
            r13 = 0
        L1aa:
            if (r12 == 0) goto L1d4
            int r14 = r12.kindSet
            r14 = r14 & 1024(0x400, float:1.435E-42)
            if (r14 == 0) goto L1d1
            int r13 = r13 + 1
            if (r13 != r9) goto L1b8
            r10 = r12
            goto L1d1
        L1b8:
            if (r11 != 0) goto L1c8
            androidx.compose.runtime.collection.MutableVector r11 = new androidx.compose.runtime.collection.MutableVector
            r14 = 16
            androidx.compose.ui.Modifier$Node[] r14 = new androidx.compose.ui.Modifier.Node[r14]
            r11.<init>()
            r11.content = r14
            r14 = 0
            r11.size = r14
        L1c8:
            if (r10 == 0) goto L1ce
            r11.add(r10)
            r10 = 0
        L1ce:
            r11.add(r12)
        L1d1:
            androidx.compose.ui.Modifier$Node r12 = r12.child
            goto L1aa
        L1d4:
            if (r13 != r9) goto L1d7
            goto L187
        L1d7:
            androidx.compose.ui.Modifier$Node r10 = androidx.compose.ui.node.Snake.access$pop(r11)
            goto L187
        L1dc:
            androidx.compose.ui.Modifier$Node r8 = r8.parent
            goto L17d
        L1df:
            androidx.compose.ui.node.LayoutNode r3 = r3.getParent$ui_release()
            if (r3 == 0) goto L1ec
            androidx.compose.ui.node.NodeChain r8 = r3.nodes
            if (r8 == 0) goto L1ec
            androidx.compose.ui.node.TailModifierNode r8 = r8.tail
            goto L171
        L1ec:
            r8 = 0
            goto L171
        L1ee:
            r16 = 0
        L1f0:
            r3 = r16
            goto L200
        L1f3:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "visitAncestors called on an unattached node"
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L1ff:
            r3 = 0
        L200:
            if (r3 == 0) goto L12e
            boolean r8 = kotlin.ResultKt.areEqual(r3, r2)
            if (r8 == 0) goto L20a
            goto L12e
        L20a:
            java.lang.Object r3 = r6.invoke(r3)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            boolean r3 = r3.booleanValue()
        L214:
            boolean r5 = r5.element
            if (r5 != 0) goto L25b
            if (r3 != 0) goto L259
            androidx.compose.ui.focus.FocusStateImpl r3 = r2.focusState
            int r3 = r3.ordinal()
            if (r3 == 0) goto L22f
            if (r3 == r9) goto L22f
            if (r3 == r4) goto L22f
            if (r3 != r7) goto L229
            goto L237
        L229:
            androidx.startup.StartupException r1 = new androidx.startup.StartupException
            r1.<init>()
            throw r1
        L22f:
            androidx.compose.ui.focus.FocusStateImpl r3 = r2.focusState
            boolean r3 = r3.isFocused()
            if (r3 == 0) goto L238
        L237:
            goto L25b
        L238:
            boolean r3 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r9)
            if (r3 == 0) goto L240
        L23e:
            r3 = 0
            goto L247
        L240:
            boolean r3 = androidx.compose.ui.focus.FocusDirection.m67equalsimpl0(r1, r4)
            if (r3 == 0) goto L25b
            goto L23e
        L247:
            r0.clearFocus(r3, r9)
            androidx.compose.ui.focus.FocusStateImpl r2 = r2.focusState
            boolean r2 = r2.isFocused()
            if (r2 != 0) goto L253
            goto L25c
        L253:
            boolean r1 = r17.m69moveFocus3ESFkO8(r18)
            if (r1 == 0) goto L25c
        L259:
            r4 = r9
            goto L25d
        L25b:
            r3 = 0
        L25c:
            r4 = r3
        L25d:
            return r4
        L25e:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Focus search invoked with invalid FocusDirection "
            r3.<init>(r4)
            java.lang.String r1 = androidx.compose.ui.focus.FocusDirection.m68toStringimpl(r18)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            throw r2
        L27a:
            kotlin.ResultKt.throwUninitializedPropertyAccessException(r6)
            r1 = 0
            throw r1
        L27f:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "invalid FocusDirection"
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L28b:
            r1 = 0
            kotlin.ResultKt.throwUninitializedPropertyAccessException(r6)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.focus.FocusOwnerImpl.m69moveFocus3ESFkO8(int):boolean");
    }
}
