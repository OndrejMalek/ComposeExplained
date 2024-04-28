package androidx.activity;

/* loaded from: classes.dex */
public final /* synthetic */ class FullyDrawnReporter$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ FullyDrawnReporter$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    /* JADX WARN: Code restructure failed: missing block: B:220:0x05c3, code lost:
    
        if (r3 != null) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:222:0x05c8, code lost:
    
        if (r3 == null) goto L223;
     */
    /* JADX WARN: Code restructure failed: missing block: B:232:0x00ef, code lost:
    
        if (r10 == false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:249:0x060e, code lost:
    
        if (r18 != false) goto L246;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0399 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:150:0x03eb A[ADDED_TO_REGION] */
    /* JADX WARN: Type inference failed for: r0v25, types: [androidx.compose.ui.text.AnnotatedString] */
    /* JADX WARN: Type inference failed for: r2v36, types: [java.util.Collection, java.util.Set, java.util.LinkedHashSet] */
    /* JADX WARN: Type inference failed for: r4v49, types: [java.util.Collection, java.util.Set, java.util.LinkedHashSet] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r30 = this;
            r0 = r30
            int r1 = r0.$r8$classId
            r3 = 0
            r4 = 1
            switch(r1) {
                case 1: goto L75a;
                case 2: goto L752;
                case 3: goto L727;
                case 4: goto L71a;
                case 5: goto L33;
                default: goto L9;
            }
        L9:
            java.lang.Object r1 = r0.f$0
            androidx.lifecycle.ProcessLifecycleOwner r1 = (androidx.lifecycle.ProcessLifecycleOwner) r1
            androidx.lifecycle.ProcessLifecycleOwner r2 = androidx.lifecycle.ProcessLifecycleOwner.newInstance
            java.lang.String r2 = "this$0"
            kotlin.ResultKt.checkNotNullParameter(r1, r2)
            int r2 = r1.resumedCounter
            if (r2 != 0) goto L21
            r1.pauseSent = r4
            androidx.lifecycle.LifecycleRegistry r2 = r1.registry
            androidx.lifecycle.Lifecycle$Event r3 = androidx.lifecycle.Lifecycle.Event.ON_PAUSE
            r2.handleLifecycleEvent(r3)
        L21:
            int r2 = r1.startedCounter
            if (r2 != 0) goto L32
            boolean r2 = r1.pauseSent
            if (r2 == 0) goto L32
            androidx.lifecycle.LifecycleRegistry r2 = r1.registry
            androidx.lifecycle.Lifecycle$Event r3 = androidx.lifecycle.Lifecycle.Event.ON_STOP
            r2.handleLifecycleEvent(r3)
            r1.stopSent = r4
        L32:
            return
        L33:
            java.lang.Object r1 = r0.f$0
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r1 = (androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat) r1
            int[] r5 = androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.AccessibilityActionsResourceIds
            java.lang.String r5 = "this$0"
            kotlin.ResultKt.checkNotNullParameter(r1, r5)
            java.lang.Integer r11 = java.lang.Integer.valueOf(r3)
            androidx.compose.ui.platform.AndroidComposeView r12 = r1.view
            r12.measureAndLayout(r4)
            androidx.compose.ui.semantics.SemanticsOwner r5 = r12.getSemanticsOwner()
            androidx.compose.ui.semantics.SemanticsNode r5 = r5.getUnmergedRootSemanticsNode()
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$SemanticsNodeCopy r6 = r1.previousSemanticsRoot
            r1.sendAccessibilitySemanticsStructureChangeEvents(r5, r6)
            androidx.compose.ui.semantics.SemanticsOwner r5 = r12.getSemanticsOwner()
            androidx.compose.ui.semantics.SemanticsNode r5 = r5.getUnmergedRootSemanticsNode()
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$SemanticsNodeCopy r6 = r1.previousSemanticsRoot
            r1.sendContentCaptureSemanticsStructureChangeEvents$ui_release(r5, r6)
            java.util.Map r13 = r1.getCurrentSemanticsNodes$ui_release()
            java.lang.String r5 = "newSemanticsNodes"
            kotlin.ResultKt.checkNotNullParameter(r13, r5)
            java.util.ArrayList r14 = new java.util.ArrayList
            java.util.ArrayList r15 = r1.scrollObservationScopes
            r14.<init>(r15)
            r15.clear()
            java.util.Set r5 = r13.keySet()
            java.util.Iterator r16 = r5.iterator()
        L7c:
            boolean r5 = r16.hasNext()
            java.util.LinkedHashMap r6 = r1.previousSemanticsNodes
            if (r5 == 0) goto L626
            java.lang.Object r5 = r16.next()
            java.lang.Number r5 = (java.lang.Number) r5
            int r8 = r5.intValue()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r8)
            java.lang.Object r5 = r6.get(r5)
            r7 = r5
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$SemanticsNodeCopy r7 = (androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.SemanticsNodeCopy) r7
            if (r7 != 0) goto L9c
            goto L7c
        L9c:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r8)
            java.lang.Object r5 = r13.get(r5)
            androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds r5 = (androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds) r5
            if (r5 == 0) goto Lac
            androidx.compose.ui.semantics.SemanticsNode r5 = r5.semanticsNode
            r6 = r5
            goto Lad
        Lac:
            r6 = 0
        Lad:
            kotlin.ResultKt.checkNotNull(r6)
            androidx.compose.ui.semantics.SemanticsConfiguration r5 = r6.unmergedConfig
            java.util.Iterator r17 = r5.iterator()
            r18 = r3
        Lb8:
            boolean r19 = r17.hasNext()
            androidx.compose.ui.semantics.SemanticsConfiguration r9 = r7.unmergedConfig
            if (r19 == 0) goto L5e2
            java.lang.Object r19 = r17.next()
            java.util.Map$Entry r19 = (java.util.Map.Entry) r19
            java.lang.Object r10 = r19.getKey()
            androidx.compose.ui.semantics.SemanticsPropertyKey r4 = androidx.compose.ui.semantics.SemanticsProperties.HorizontalScrollAxisRange
            boolean r10 = kotlin.ResultKt.areEqual(r10, r4)
            if (r10 != 0) goto Lde
            java.lang.Object r10 = r19.getKey()
            androidx.compose.ui.semantics.SemanticsPropertyKey r2 = androidx.compose.ui.semantics.SemanticsProperties.VerticalScrollAxisRange
            boolean r2 = kotlin.ResultKt.areEqual(r10, r2)
            if (r2 == 0) goto Lf1
        Lde:
            androidx.compose.ui.platform.ScrollObservationScope r2 = androidx.compose.ui.platform.InvertMatrixKt.findById(r8, r14)
            if (r2 == 0) goto Le6
            r10 = 0
            goto Lec
        Le6:
            androidx.compose.ui.platform.ScrollObservationScope r2 = new androidx.compose.ui.platform.ScrollObservationScope
            r2.<init>(r8, r15)
            r10 = 1
        Lec:
            r15.add(r2)
            if (r10 != 0) goto L116
        Lf1:
            java.lang.Object r2 = r19.getValue()
            java.lang.Object r10 = r19.getKey()
            androidx.compose.ui.semantics.SemanticsPropertyKey r10 = (androidx.compose.ui.semantics.SemanticsPropertyKey) r10
            java.lang.Object r10 = _COROUTINE._BOUNDARY.getOrNull(r9, r10)
            boolean r2 = kotlin.ResultKt.areEqual(r2, r10)
            if (r2 == 0) goto L116
            r21 = r6
            r28 = r7
            r6 = r8
            r0 = r11
            r24 = r13
            r25 = r14
            r7 = 32
            r13 = r5
            r5 = 16
            goto L548
        L116:
            java.lang.Object r2 = r19.getKey()
            androidx.compose.ui.semantics.SemanticsPropertyKey r2 = (androidx.compose.ui.semantics.SemanticsPropertyKey) r2
            androidx.compose.ui.semantics.SemanticsPropertyKey r10 = androidx.compose.ui.semantics.SemanticsProperties.Text
            boolean r24 = kotlin.ResultKt.areEqual(r2, r10)
            int r3 = r6.id
            if (r24 == 0) goto L1a4
            java.lang.Object r2 = _COROUTINE._BOUNDARY.getOrNull(r9, r10)
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L135
            java.lang.Object r2 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r2)
            androidx.compose.ui.text.AnnotatedString r2 = (androidx.compose.ui.text.AnnotatedString) r2
            goto L136
        L135:
            r2 = 0
        L136:
            java.lang.Object r4 = _COROUTINE._BOUNDARY.getOrNull(r5, r10)
            java.util.List r4 = (java.util.List) r4
            if (r4 == 0) goto L145
            java.lang.Object r4 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r4)
            androidx.compose.ui.text.AnnotatedString r4 = (androidx.compose.ui.text.AnnotatedString) r4
            goto L146
        L145:
            r4 = 0
        L146:
            boolean r2 = kotlin.ResultKt.areEqual(r2, r4)
            if (r2 != 0) goto L19f
            java.lang.String r2 = java.lang.String.valueOf(r4)
            androidx.compose.ui.platform.WeakCache r4 = r1.contentCaptureSession
            if (r4 != 0) goto L155
        L154:
            goto L19f
        L155:
            int r9 = android.os.Build.VERSION.SDK_INT
            r10 = 29
            if (r9 >= r10) goto L15c
            goto L154
        L15c:
            r24 = r13
            r25 = r14
            long r13 = (long) r3
            if (r9 < r10) goto L176
            java.lang.Object r3 = r4.values
            android.view.contentcapture.ContentCaptureSession r3 = androidx.compose.ui.text.android.Paint29$$ExternalSyntheticApiModelOutline0.m(r3)
            java.lang.Object r10 = r4.referenceQueue
            android.view.View r10 = (android.view.View) r10
            android.view.autofill.AutofillId r10 = androidx.compose.ui.platform.coreshims.ViewCompatShims$Api26Impl.getAutofillId(r10)
            android.view.autofill.AutofillId r3 = androidx.compose.ui.platform.coreshims.ContentCaptureSessionCompat$Api29Impl.newAutofillId(r3, r10, r13)
            goto L177
        L176:
            r3 = 0
        L177:
            if (r3 == 0) goto L193
            r10 = 29
            if (r9 < r10) goto L186
            java.lang.Object r4 = r4.values
            android.view.contentcapture.ContentCaptureSession r4 = androidx.compose.ui.text.android.Paint29$$ExternalSyntheticApiModelOutline0.m(r4)
            androidx.compose.ui.platform.coreshims.ContentCaptureSessionCompat$Api29Impl.notifyViewTextChanged(r4, r3, r2)
        L186:
            r13 = r5
            r21 = r6
            r28 = r7
            r6 = r8
            r0 = r11
            r5 = 16
            r7 = 32
            goto L548
        L193:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Invalid content capture ID"
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L19f:
            r24 = r13
            r25 = r14
            goto L186
        L1a4:
            r24 = r13
            r25 = r14
            androidx.compose.ui.semantics.SemanticsPropertyKey r13 = androidx.compose.ui.semantics.SemanticsProperties.PaneTitle
            boolean r14 = kotlin.ResultKt.areEqual(r2, r13)
            if (r14 == 0) goto L1c7
            java.lang.Object r2 = r19.getValue()
            java.lang.String r3 = "null cannot be cast to non-null type kotlin.String"
            kotlin.ResultKt.checkNotNull(r2, r3)
            java.lang.String r2 = (java.lang.String) r2
            boolean r3 = r9.contains(r13)
            if (r3 == 0) goto L186
            r3 = 8
            r1.sendPaneChangeEvents(r8, r3, r2)
            goto L186
        L1c7:
            androidx.compose.ui.semantics.SemanticsPropertyKey r13 = androidx.compose.ui.semantics.SemanticsProperties.StateDescription
            boolean r13 = kotlin.ResultKt.areEqual(r2, r13)
            r14 = 64
            if (r13 == 0) goto L1d2
            goto L1da
        L1d2:
            androidx.compose.ui.semantics.SemanticsPropertyKey r13 = androidx.compose.ui.semantics.SemanticsProperties.ToggleableState
            boolean r13 = kotlin.ResultKt.areEqual(r2, r13)
            if (r13 == 0) goto L1f1
        L1da:
            int r2 = r1.semanticsNodeIdToAccessibilityVirtualNodeId(r8)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r14)
            r4 = 8
            r13 = 2048(0x800, float:2.87E-42)
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(r1, r2, r13, r3, r4)
            int r2 = r1.semanticsNodeIdToAccessibilityVirtualNodeId(r8)
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(r1, r2, r13, r11, r4)
            goto L186
        L1f1:
            androidx.compose.ui.semantics.SemanticsPropertyKey r13 = androidx.compose.ui.semantics.SemanticsProperties.ProgressBarRangeInfo
            boolean r13 = kotlin.ResultKt.areEqual(r2, r13)
            if (r13 == 0) goto L211
            int r2 = r1.semanticsNodeIdToAccessibilityVirtualNodeId(r8)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r14)
            r4 = 2048(0x800, float:2.87E-42)
            r9 = 8
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(r1, r2, r4, r3, r9)
            int r2 = r1.semanticsNodeIdToAccessibilityVirtualNodeId(r8)
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(r1, r2, r4, r11, r9)
            goto L186
        L211:
            androidx.compose.ui.semantics.SemanticsPropertyKey r13 = androidx.compose.ui.semantics.SemanticsProperties.Selected
            boolean r26 = kotlin.ResultKt.areEqual(r2, r13)
            androidx.compose.ui.node.LayoutNode r14 = r6.layoutNode
            r0 = 4
            if (r26 == 0) goto L2b8
            androidx.compose.ui.semantics.SemanticsConfiguration r2 = r6.getConfig()
            androidx.compose.ui.semantics.SemanticsPropertyKey r3 = androidx.compose.ui.semantics.SemanticsProperties.Role
            java.lang.Object r2 = _COROUTINE._BOUNDARY.getOrNull(r2, r3)
            androidx.compose.ui.semantics.Role r2 = (androidx.compose.ui.semantics.Role) r2
            if (r2 != 0) goto L230
        L22a:
            r2 = 2048(0x800, float:2.87E-42)
            r3 = 8
            goto L2a2
        L230:
            int r2 = r2.value
            boolean r2 = androidx.compose.ui.semantics.Role.m238equalsimpl0(r2, r0)
            if (r2 == 0) goto L22a
            androidx.compose.ui.semantics.SemanticsConfiguration r2 = r6.getConfig()
            java.lang.Object r2 = _COROUTINE._BOUNDARY.getOrNull(r2, r13)
            java.lang.Boolean r3 = java.lang.Boolean.TRUE
            boolean r2 = kotlin.ResultKt.areEqual(r2, r3)
            if (r2 == 0) goto L295
            int r2 = r1.semanticsNodeIdToAccessibilityVirtualNodeId(r8)
            android.view.accessibility.AccessibilityEvent r0 = r1.createEvent$ui_release(r2, r0)
            androidx.compose.ui.semantics.SemanticsNode r2 = new androidx.compose.ui.semantics.SemanticsNode
            androidx.compose.ui.Modifier$Node r3 = r6.outerSemanticsNode
            r4 = 1
            r2.<init>(r3, r4, r14, r5)
            androidx.compose.ui.semantics.SemanticsConfiguration r3 = r2.getConfig()
            androidx.compose.ui.semantics.SemanticsPropertyKey r4 = androidx.compose.ui.semantics.SemanticsProperties.ContentDescription
            java.lang.Object r3 = _COROUTINE._BOUNDARY.getOrNull(r3, r4)
            java.util.List r3 = (java.util.List) r3
            if (r3 == 0) goto L26d
            java.lang.String r4 = ","
            java.lang.String r3 = _COROUTINE._BOUNDARY.fastJoinToString$default(r3, r4)
            goto L26e
        L26d:
            r3 = 0
        L26e:
            androidx.compose.ui.semantics.SemanticsConfiguration r2 = r2.getConfig()
            java.lang.Object r2 = _COROUTINE._BOUNDARY.getOrNull(r2, r10)
            java.util.List r2 = (java.util.List) r2
            if (r2 == 0) goto L281
            java.lang.String r4 = ","
            java.lang.String r2 = _COROUTINE._BOUNDARY.fastJoinToString$default(r2, r4)
            goto L282
        L281:
            r2 = 0
        L282:
            if (r3 == 0) goto L287
            r0.setContentDescription(r3)
        L287:
            if (r2 == 0) goto L290
            java.util.List r3 = r0.getText()
            r3.add(r2)
        L290:
            r1.sendEvent(r0)
            goto L186
        L295:
            int r0 = r1.semanticsNodeIdToAccessibilityVirtualNodeId(r8)
            r2 = 2048(0x800, float:2.87E-42)
            r3 = 8
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(r1, r0, r2, r11, r3)
            goto L186
        L2a2:
            int r0 = r1.semanticsNodeIdToAccessibilityVirtualNodeId(r8)
            r4 = 64
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(r1, r0, r2, r4, r3)
            int r0 = r1.semanticsNodeIdToAccessibilityVirtualNodeId(r8)
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(r1, r0, r2, r11, r3)
            goto L186
        L2b8:
            androidx.compose.ui.semantics.SemanticsPropertyKey r10 = androidx.compose.ui.semantics.SemanticsProperties.ContentDescription
            boolean r10 = kotlin.ResultKt.areEqual(r2, r10)
            if (r10 == 0) goto L2da
            int r2 = r1.semanticsNodeIdToAccessibilityVirtualNodeId(r8)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Object r3 = r19.getValue()
            java.lang.String r4 = "null cannot be cast to non-null type kotlin.collections.List<kotlin.String>"
            kotlin.ResultKt.checkNotNull(r3, r4)
            java.util.List r3 = (java.util.List) r3
            r4 = 2048(0x800, float:2.87E-42)
            r1.sendEventForVirtualView(r2, r4, r0, r3)
            goto L186
        L2da:
            androidx.compose.ui.semantics.SemanticsPropertyKey r0 = androidx.compose.ui.semantics.SemanticsProperties.EditableText
            boolean r10 = kotlin.ResultKt.areEqual(r2, r0)
            java.lang.String r13 = ""
            r26 = 4294967295(0xffffffff, double:2.1219957905E-314)
            if (r10 == 0) goto L42a
            androidx.compose.ui.semantics.SemanticsConfiguration r2 = r6.unmergedConfig
            androidx.compose.ui.semantics.SemanticsPropertyKey r3 = androidx.compose.ui.semantics.SemanticsActions.SetText
            boolean r2 = r2.contains(r3)
            if (r2 == 0) goto L40f
            java.lang.Object r2 = _COROUTINE._BOUNDARY.getOrNull(r9, r0)
            androidx.compose.ui.text.AnnotatedString r2 = (androidx.compose.ui.text.AnnotatedString) r2
            if (r2 == 0) goto L2fc
            goto L2fd
        L2fc:
            r2 = r13
        L2fd:
            java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r5, r0)
            androidx.compose.ui.text.AnnotatedString r0 = (androidx.compose.ui.text.AnnotatedString) r0
            if (r0 == 0) goto L306
            r13 = r0
        L306:
            java.lang.CharSequence r10 = androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.trimToSize(r13)
            int r0 = r2.length()
            int r3 = r13.length()
            if (r0 <= r3) goto L316
            r4 = r3
            goto L317
        L316:
            r4 = r0
        L317:
            r9 = 0
        L318:
            if (r9 >= r4) goto L32c
            char r14 = r2.charAt(r9)
            r28 = r5
            char r5 = r13.charAt(r9)
            if (r14 == r5) goto L327
            goto L32e
        L327:
            int r9 = r9 + 1
            r5 = r28
            goto L318
        L32c:
            r28 = r5
        L32e:
            r5 = 0
        L32f:
            int r14 = r4 - r9
            if (r5 >= r14) goto L34c
            int r14 = r0 + (-1)
            int r14 = r14 - r5
            char r14 = r2.charAt(r14)
            int r19 = r3 + (-1)
            r23 = r4
            int r4 = r19 - r5
            char r4 = r13.charAt(r4)
            if (r14 == r4) goto L347
            goto L34c
        L347:
            int r5 = r5 + 1
            r4 = r23
            goto L32f
        L34c:
            int r0 = r0 - r5
            int r0 = r0 - r9
            int r4 = r3 - r5
            int r4 = r4 - r9
            androidx.compose.ui.semantics.SemanticsNode r5 = r7.semanticsNode
            boolean r13 = androidx.compose.ui.platform.InvertMatrixKt.isTextField(r5)
            if (r13 == 0) goto L371
            androidx.compose.ui.semantics.SemanticsConfiguration r13 = r5.getConfig()
            androidx.compose.ui.semantics.SemanticsPropertyKey r14 = androidx.compose.ui.semantics.SemanticsProperties.Password
            boolean r13 = r13.contains(r14)
            if (r13 != 0) goto L371
            androidx.compose.ui.semantics.SemanticsConfiguration r13 = r6.getConfig()
            boolean r13 = r13.contains(r14)
            if (r13 == 0) goto L371
            r13 = 1
            goto L372
        L371:
            r13 = 0
        L372:
            androidx.compose.ui.semantics.SemanticsConfiguration r14 = r5.unmergedConfig
            r29 = r7
            androidx.compose.ui.semantics.SemanticsPropertyKey r7 = androidx.compose.ui.semantics.SemanticsActions.SetText
            boolean r7 = r14.contains(r7)
            if (r7 == 0) goto L396
            androidx.compose.ui.semantics.SemanticsConfiguration r5 = r5.getConfig()
            androidx.compose.ui.semantics.SemanticsPropertyKey r7 = androidx.compose.ui.semantics.SemanticsProperties.Password
            boolean r5 = r5.contains(r7)
            if (r5 == 0) goto L396
            androidx.compose.ui.semantics.SemanticsConfiguration r5 = r6.getConfig()
            boolean r5 = r5.contains(r7)
            if (r5 != 0) goto L396
            r14 = 1
            goto L397
        L396:
            r14 = 0
        L397:
            if (r13 != 0) goto L39b
            if (r14 == 0) goto L39e
        L39b:
            r7 = 16
            goto L3c7
        L39e:
            int r3 = r1.semanticsNodeIdToAccessibilityVirtualNodeId(r8)
            r7 = 16
            android.view.accessibility.AccessibilityEvent r3 = r1.createEvent$ui_release(r3, r7)
            r3.setFromIndex(r9)
            r3.setRemovedCount(r0)
            r3.setAddedCount(r4)
            r3.setBeforeText(r2)
            java.util.List r0 = r3.getText()
            r0.add(r10)
            r5 = r3
            r21 = r6
            r0 = r7
            r3 = r8
            r2 = r28
            r28 = r29
            r4 = 32
            goto L3e1
        L3c7:
            int r0 = r1.semanticsNodeIdToAccessibilityVirtualNodeId(r8)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r3)
            r2 = r28
            r5 = r1
            r21 = r6
            r6 = r0
            r0 = r7
            r28 = r29
            r7 = r11
            r3 = r8
            r8 = r11
            r4 = 32
            android.view.accessibility.AccessibilityEvent r5 = r5.createTextSelectionChangedEvent(r6, r7, r8, r9, r10)
        L3e1:
            java.lang.String r6 = "android.widget.EditText"
            r5.setClassName(r6)
            r1.sendEvent(r5)
            if (r13 != 0) goto L3f5
            if (r14 == 0) goto L3ee
            goto L3f5
        L3ee:
            r5 = r0
            r13 = r2
            r6 = r3
            r7 = r4
            r0 = r11
            goto L548
        L3f5:
            androidx.compose.ui.semantics.SemanticsPropertyKey r6 = androidx.compose.ui.semantics.SemanticsProperties.TextSelectionRange
            java.lang.Object r6 = r2.get(r6)
            androidx.compose.ui.text.TextRange r6 = (androidx.compose.ui.text.TextRange) r6
            long r6 = r6.packedValue
            long r8 = r6 >> r4
            int r8 = (int) r8
            r5.setFromIndex(r8)
            long r6 = r6 & r26
            int r6 = (int) r6
            r5.setToIndex(r6)
            r1.sendEvent(r5)
            goto L3ee
        L40f:
            r2 = r5
            r21 = r6
            r28 = r7
            r3 = r8
            r0 = 16
            r4 = 32
            int r5 = r1.semanticsNodeIdToAccessibilityVirtualNodeId(r3)
            r6 = 2
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r7 = 2048(0x800, float:2.87E-42)
            r8 = 8
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(r1, r5, r7, r6, r8)
            goto L3ee
        L42a:
            r21 = r6
            r28 = r7
            r7 = r8
            r8 = 32
            r10 = 16
            r6 = r5
            androidx.compose.ui.semantics.SemanticsPropertyKey r5 = androidx.compose.ui.semantics.SemanticsProperties.TextSelectionRange
            boolean r20 = kotlin.ResultKt.areEqual(r2, r5)
            if (r20 == 0) goto L48b
            java.lang.Object r0 = _COROUTINE._BOUNDARY.getOrNull(r6, r0)
            androidx.compose.ui.text.AnnotatedString r0 = (androidx.compose.ui.text.AnnotatedString) r0
            if (r0 == 0) goto L44a
            java.lang.String r0 = r0.text
            if (r0 != 0) goto L449
            goto L44a
        L449:
            r13 = r0
        L44a:
            java.lang.Object r0 = r6.get(r5)
            androidx.compose.ui.text.TextRange r0 = (androidx.compose.ui.text.TextRange) r0
            int r2 = r1.semanticsNodeIdToAccessibilityVirtualNodeId(r7)
            long r4 = r0.packedValue
            r0 = r11
            long r10 = r4 >> r8
            int r9 = (int) r10
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
            long r4 = r4 & r26
            int r4 = (int) r4
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            int r5 = r13.length()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r5)
            java.lang.CharSequence r11 = androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.trimToSize(r13)
            r5 = r1
            r13 = r6
            r6 = r2
            r2 = r7
            r7 = r9
            r14 = r8
            r8 = r4
            r9 = r10
            r4 = 16
            r10 = r11
            android.view.accessibility.AccessibilityEvent r5 = r5.createTextSelectionChangedEvent(r6, r7, r8, r9, r10)
            r1.sendEvent(r5)
            r1.sendPendingTextTraversedAtGranularityEvent(r3)
            r6 = r2
            r5 = r4
            r7 = r14
            goto L548
        L48b:
            r13 = r6
            r6 = r7
            r7 = r8
            r5 = r10
            r0 = r11
            boolean r8 = kotlin.ResultKt.areEqual(r2, r4)
            if (r8 == 0) goto L497
            goto L49f
        L497:
            androidx.compose.ui.semantics.SemanticsPropertyKey r8 = androidx.compose.ui.semantics.SemanticsProperties.VerticalScrollAxisRange
            boolean r8 = kotlin.ResultKt.areEqual(r2, r8)
            if (r8 == 0) goto L4d7
        L49f:
            r1.notifySubtreeAccessibilityStateChangedIfNeeded(r14)
            androidx.compose.ui.platform.ScrollObservationScope r2 = androidx.compose.ui.platform.InvertMatrixKt.findById(r6, r15)
            kotlin.ResultKt.checkNotNull(r2)
            java.lang.Object r3 = _COROUTINE._BOUNDARY.getOrNull(r13, r4)
            kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0.m(r3)
            androidx.compose.ui.semantics.SemanticsPropertyKey r3 = androidx.compose.ui.semantics.SemanticsProperties.VerticalScrollAxisRange
            java.lang.Object r3 = _COROUTINE._BOUNDARY.getOrNull(r13, r3)
            kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0.m(r3)
            java.util.List r3 = r2.allScopes
            boolean r3 = r3.contains(r2)
            if (r3 != 0) goto L4c3
            goto L548
        L4c3:
            androidx.compose.ui.platform.AndroidComposeView r3 = r1.view
            androidx.compose.ui.node.OwnerSnapshotObserver r3 = r3.getSnapshotObserver()
            androidx.compose.ui.node.NodeCoordinator$invoke$1 r4 = new androidx.compose.ui.node.NodeCoordinator$invoke$1
            r8 = 9
            r4.<init>(r2, r8, r1)
            kotlin.collections.AbstractMap$toString$1 r8 = r1.sendScrollEventIfNeededLambda
            r3.observeReads$ui_release(r2, r8, r4)
            goto L548
        L4d7:
            androidx.compose.ui.semantics.SemanticsPropertyKey r4 = androidx.compose.ui.semantics.SemanticsProperties.Focused
            boolean r4 = kotlin.ResultKt.areEqual(r2, r4)
            if (r4 == 0) goto L50a
            java.lang.Object r2 = r19.getValue()
            java.lang.String r4 = "null cannot be cast to non-null type kotlin.Boolean"
            kotlin.ResultKt.checkNotNull(r2, r4)
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r2 = r2.booleanValue()
            if (r2 == 0) goto L4fe
            int r2 = r1.semanticsNodeIdToAccessibilityVirtualNodeId(r3)
            r4 = 8
            android.view.accessibility.AccessibilityEvent r2 = r1.createEvent$ui_release(r2, r4)
            r1.sendEvent(r2)
            goto L500
        L4fe:
            r4 = 8
        L500:
            int r2 = r1.semanticsNodeIdToAccessibilityVirtualNodeId(r3)
            r3 = 2048(0x800, float:2.87E-42)
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(r1, r2, r3, r0, r4)
            goto L548
        L50a:
            androidx.compose.ui.semantics.SemanticsPropertyKey r3 = androidx.compose.ui.semantics.SemanticsActions.CustomActions
            boolean r2 = kotlin.ResultKt.areEqual(r2, r3)
            if (r2 == 0) goto L585
            java.lang.Object r2 = r13.get(r3)
            java.util.List r2 = (java.util.List) r2
            java.lang.Object r3 = _COROUTINE._BOUNDARY.getOrNull(r9, r3)
            java.util.List r3 = (java.util.List) r3
            if (r3 == 0) goto L56d
            java.util.LinkedHashSet r4 = new java.util.LinkedHashSet
            r4.<init>()
            int r8 = r2.size()
            if (r8 > 0) goto L563
            java.util.LinkedHashSet r2 = new java.util.LinkedHashSet
            r2.<init>()
            int r8 = r3.size()
            if (r8 > 0) goto L559
            boolean r3 = r4.containsAll(r2)
            if (r3 == 0) goto L546
            boolean r2 = r2.containsAll(r4)
            if (r2 != 0) goto L543
            goto L546
        L543:
            r18 = 0
            goto L548
        L546:
            r18 = 1
        L548:
            r11 = r0
            r8 = r6
            r5 = r13
            r6 = r21
            r13 = r24
            r14 = r25
            r7 = r28
            r3 = 0
            r4 = 1
        L555:
            r0 = r30
            goto Lb8
        L559:
            r4 = 0
            java.lang.Object r0 = r3.get(r4)
            kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0.m(r0)
            r0 = 0
            throw r0
        L563:
            r0 = 0
            r4 = 0
            java.lang.Object r1 = r2.get(r4)
            kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0.m(r1)
            throw r0
        L56d:
            boolean r2 = r2.isEmpty()
            r3 = 1
            r2 = r2 ^ r3
            if (r2 == 0) goto L548
            r11 = r0
            r8 = r6
            r5 = r13
            r6 = r21
            r13 = r24
            r14 = r25
            r7 = r28
            r3 = 0
            r4 = 1
            r18 = 1
            goto L555
        L585:
            java.lang.Object r2 = r19.getValue()
            boolean r2 = r2 instanceof androidx.compose.ui.semantics.AccessibilityAction
            if (r2 == 0) goto L5dc
            java.lang.Object r2 = r19.getValue()
            java.lang.String r3 = "null cannot be cast to non-null type androidx.compose.ui.semantics.AccessibilityAction<*>"
            kotlin.ResultKt.checkNotNull(r2, r3)
            androidx.compose.ui.semantics.AccessibilityAction r2 = (androidx.compose.ui.semantics.AccessibilityAction) r2
            java.lang.Object r3 = r19.getKey()
            androidx.compose.ui.semantics.SemanticsPropertyKey r3 = (androidx.compose.ui.semantics.SemanticsPropertyKey) r3
            java.lang.Object r3 = _COROUTINE._BOUNDARY.getOrNull(r9, r3)
            if (r2 != r3) goto L5a8
        L5a4:
            r2 = 1
            r22 = 1
            goto L5cb
        L5a8:
            boolean r4 = r3 instanceof androidx.compose.ui.semantics.AccessibilityAction
            if (r4 != 0) goto L5b0
        L5ac:
            r2 = 1
            r22 = 0
            goto L5cb
        L5b0:
            androidx.compose.ui.semantics.AccessibilityAction r3 = (androidx.compose.ui.semantics.AccessibilityAction) r3
            java.lang.String r4 = r3.label
            java.lang.String r8 = r2.label
            boolean r4 = kotlin.ResultKt.areEqual(r8, r4)
            if (r4 != 0) goto L5bd
            goto L5ac
        L5bd:
            kotlin.Function r3 = r3.action
            kotlin.Function r2 = r2.action
            if (r2 != 0) goto L5c6
            if (r3 == 0) goto L5c6
            goto L5ac
        L5c6:
            if (r2 == 0) goto L5a4
            if (r3 != 0) goto L5a4
            goto L5ac
        L5cb:
            r18 = r22 ^ 1
            r11 = r0
            r4 = r2
        L5cf:
            r8 = r6
            r5 = r13
            r6 = r21
            r13 = r24
            r14 = r25
            r7 = r28
            r3 = 0
            goto L555
        L5dc:
            r2 = 1
            r11 = r0
            r4 = r2
            r18 = r4
            goto L5cf
        L5e2:
            r2 = r4
            r21 = r6
            r6 = r8
            r0 = r11
            r24 = r13
            r25 = r14
            if (r18 != 0) goto L60e
            java.util.Iterator r3 = r9.iterator()
        L5f1:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L61b
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            androidx.compose.ui.semantics.SemanticsConfiguration r5 = r21.getConfig()
            java.lang.Object r4 = r4.getKey()
            androidx.compose.ui.semantics.SemanticsPropertyKey r4 = (androidx.compose.ui.semantics.SemanticsPropertyKey) r4
            boolean r4 = r5.contains(r4)
            if (r4 != 0) goto L5f1
            goto L610
        L60e:
            if (r18 == 0) goto L61b
        L610:
            int r3 = r1.semanticsNodeIdToAccessibilityVirtualNodeId(r6)
            r4 = 2048(0x800, float:2.87E-42)
            r5 = 8
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView$default(r1, r3, r4, r0, r5)
        L61b:
            r11 = r0
            r4 = r2
            r13 = r24
            r14 = r25
            r3 = 0
            r0 = r30
            goto L7c
        L626:
            r5 = 16
            r7 = 32
            androidx.collection.ArraySet r0 = new androidx.collection.ArraySet
            r0.<init>()
            androidx.collection.ArraySet r2 = r1.paneDisplayed
            java.util.Iterator r3 = r2.iterator()
        L635:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L685
            java.lang.Object r4 = r3.next()
            java.lang.Integer r4 = (java.lang.Integer) r4
            java.util.Map r8 = r1.getCurrentSemanticsNodes$ui_release()
            java.lang.Object r8 = r8.get(r4)
            androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds r8 = (androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds) r8
            if (r8 == 0) goto L650
            androidx.compose.ui.semantics.SemanticsNode r8 = r8.semanticsNode
            goto L651
        L650:
            r8 = 0
        L651:
            if (r8 == 0) goto L65f
            androidx.compose.ui.semantics.SemanticsConfiguration r8 = r8.getConfig()
            androidx.compose.ui.semantics.SemanticsPropertyKey r9 = androidx.compose.ui.semantics.SemanticsProperties.PaneTitle
            boolean r8 = r8.contains(r9)
            if (r8 != 0) goto L635
        L65f:
            r0.add(r4)
            java.lang.String r8 = "id"
            kotlin.ResultKt.checkNotNullExpressionValue(r4, r8)
            int r8 = r4.intValue()
            java.lang.Object r4 = r6.get(r4)
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$SemanticsNodeCopy r4 = (androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.SemanticsNodeCopy) r4
            if (r4 == 0) goto L680
            androidx.compose.ui.semantics.SemanticsConfiguration r4 = r4.unmergedConfig
            if (r4 == 0) goto L680
            androidx.compose.ui.semantics.SemanticsPropertyKey r9 = androidx.compose.ui.semantics.SemanticsProperties.PaneTitle
            java.lang.Object r4 = _COROUTINE._BOUNDARY.getOrNull(r4, r9)
            java.lang.String r4 = (java.lang.String) r4
            goto L681
        L680:
            r4 = 0
        L681:
            r1.sendPaneChangeEvents(r8, r7, r4)
            goto L635
        L685:
            int r3 = r0.mSize
            r4 = 0
        L688:
            if (r4 >= r3) goto L694
            java.lang.Object[] r7 = r0.mArray
            r7 = r7[r4]
            r2.remove(r7)
            int r4 = r4 + 1
            goto L688
        L694:
            r6.clear()
            java.util.Map r0 = r1.getCurrentSemanticsNodes$ui_release()
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L6a3:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L703
            java.lang.Object r3 = r0.next()
            java.util.Map$Entry r3 = (java.util.Map.Entry) r3
            java.lang.Object r4 = r3.getValue()
            androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds r4 = (androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds) r4
            androidx.compose.ui.semantics.SemanticsNode r4 = r4.semanticsNode
            androidx.compose.ui.semantics.SemanticsConfiguration r4 = r4.getConfig()
            androidx.compose.ui.semantics.SemanticsPropertyKey r7 = androidx.compose.ui.semantics.SemanticsProperties.PaneTitle
            boolean r4 = r4.contains(r7)
            if (r4 == 0) goto L6ea
            java.lang.Object r4 = r3.getKey()
            boolean r4 = r2.add(r4)
            if (r4 == 0) goto L6ea
            java.lang.Object r4 = r3.getKey()
            java.lang.Number r4 = (java.lang.Number) r4
            int r4 = r4.intValue()
            java.lang.Object r8 = r3.getValue()
            androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds r8 = (androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds) r8
            androidx.compose.ui.semantics.SemanticsNode r8 = r8.semanticsNode
            androidx.compose.ui.semantics.SemanticsConfiguration r8 = r8.unmergedConfig
            java.lang.Object r7 = r8.get(r7)
            java.lang.String r7 = (java.lang.String) r7
            r1.sendPaneChangeEvents(r4, r5, r7)
        L6ea:
            java.lang.Object r4 = r3.getKey()
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$SemanticsNodeCopy r7 = new androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$SemanticsNodeCopy
            java.lang.Object r3 = r3.getValue()
            androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds r3 = (androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds) r3
            androidx.compose.ui.semantics.SemanticsNode r3 = r3.semanticsNode
            java.util.Map r8 = r1.getCurrentSemanticsNodes$ui_release()
            r7.<init>(r3, r8)
            r6.put(r4, r7)
            goto L6a3
        L703:
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$SemanticsNodeCopy r0 = new androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$SemanticsNodeCopy
            androidx.compose.ui.semantics.SemanticsOwner r2 = r12.getSemanticsOwner()
            androidx.compose.ui.semantics.SemanticsNode r2 = r2.getUnmergedRootSemanticsNode()
            java.util.Map r3 = r1.getCurrentSemanticsNodes$ui_release()
            r0.<init>(r2, r3)
            r1.previousSemanticsRoot = r0
            r0 = 0
            r1.checkingForSemanticsChanges = r0
            return
        L71a:
            java.lang.Object r1 = r0.f$0
            kotlin.jvm.functions.Function0 r1 = (kotlin.jvm.functions.Function0) r1
            java.lang.String r2 = "$tmp0"
            kotlin.ResultKt.checkNotNullParameter(r1, r2)
            r1.invoke()
            return
        L727:
            java.lang.Object r1 = r0.f$0
            androidx.compose.ui.platform.AndroidComposeView r1 = (androidx.compose.ui.platform.AndroidComposeView) r1
            java.lang.Class r2 = androidx.compose.ui.platform.AndroidComposeView.systemPropertiesClass
            java.lang.String r2 = "this$0"
            kotlin.ResultKt.checkNotNullParameter(r1, r2)
            r2 = 0
            r1.hoverExitReceived = r2
            android.view.MotionEvent r2 = r1.previousMotionEvent
            kotlin.ResultKt.checkNotNull(r2)
            int r3 = r2.getActionMasked()
            r4 = 10
            if (r3 != r4) goto L746
            r1.m220sendMotionEvent8iAsVTc(r2)
            return
        L746:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "The ACTION_HOVER_EXIT event was not cleared."
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L752:
            java.lang.Object r1 = r0.f$0
            androidx.compose.material.ripple.RippleHostView r1 = (androidx.compose.material.ripple.RippleHostView) r1
            androidx.compose.material.ripple.RippleHostView.m45$r8$lambda$kwnYusj11673lL3lZ3fgj8B_w(r1)
            return
        L75a:
            java.lang.Object r1 = r0.f$0
            androidx.activity.ComponentActivity$ReportFullyDrawnExecutorImpl r1 = (androidx.activity.ComponentActivity.ReportFullyDrawnExecutorImpl) r1
            java.lang.String r2 = "this$0"
            kotlin.ResultKt.checkNotNullParameter(r1, r2)
            java.lang.Runnable r2 = r1.currentRunnable
            if (r2 == 0) goto L76d
            r2.run()
            r2 = 0
            r1.currentRunnable = r2
        L76d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.activity.FullyDrawnReporter$$ExternalSyntheticLambda0.run():void");
    }
}
