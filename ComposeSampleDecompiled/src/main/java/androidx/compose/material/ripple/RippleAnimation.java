package androidx.compose.material.ripple;

import androidx.compose.animation.core.Animatable;
import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.StructuralEqualityPolicy;
import androidx.compose.ui.geometry.Offset;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlinx.coroutines.CompletableDeferredImpl;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.JobSupport;

/* loaded from: classes.dex */
public final class RippleAnimation {
    public final boolean bounded;
    public final ParcelableSnapshotMutableState finishRequested$delegate;
    public final CompletableDeferredImpl finishSignalDeferred;
    public final ParcelableSnapshotMutableState finishedFadingIn$delegate;
    public Offset origin;
    public final float radius;
    public Float startRadius;
    public Offset targetCenter;
    public Float targetRadius;
    public final Animatable animatedAlpha = ResultKt.Animatable$default();
    public final Animatable animatedRadiusPercent = ResultKt.Animatable$default();
    public final Animatable animatedCenterPercent = ResultKt.Animatable$default();

    /* JADX WARN: Type inference failed for: r1v4, types: [kotlinx.coroutines.JobSupport, kotlinx.coroutines.CompletableDeferredImpl] */
    public RippleAnimation(Offset offset, float f, boolean z) {
        this.origin = offset;
        this.radius = f;
        this.bounded = z;
        ?? jobSupport = new JobSupport(true);
        jobSupport.initParentJob(null);
        this.finishSignalDeferred = jobSupport;
        Boolean bool = Boolean.FALSE;
        StructuralEqualityPolicy structuralEqualityPolicy = StructuralEqualityPolicy.INSTANCE;
        this.finishedFadingIn$delegate = ResultKt.mutableStateOf(bool, structuralEqualityPolicy);
        this.finishRequested$delegate = ResultKt.mutableStateOf(bool, structuralEqualityPolicy);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x00c2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00c1 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00aa A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0072 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object animate(kotlin.coroutines.Continuation r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof androidx.compose.material.ripple.RippleAnimation$animate$1
            if (r0 == 0) goto L13
            r0 = r11
            androidx.compose.material.ripple.RippleAnimation$animate$1 r0 = (androidx.compose.material.ripple.RippleAnimation$animate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.compose.material.ripple.RippleAnimation$animate$1 r0 = new androidx.compose.material.ripple.RippleAnimation$animate$1
            r0.<init>(r10, r11)
        L18:
            java.lang.Object r11 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            r4 = 1
            r5 = 3
            r6 = 0
            r7 = 2
            if (r2 == 0) goto L45
            if (r2 == r4) goto L3f
            if (r2 == r7) goto L39
            if (r2 != r5) goto L31
            kotlin.ResultKt.throwOnFailure(r11)
            goto Lc2
        L31:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L39:
            androidx.compose.material.ripple.RippleAnimation r2 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r11)
            goto Lab
        L3f:
            androidx.compose.material.ripple.RippleAnimation r2 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r11)
            goto L5d
        L45:
            kotlin.ResultKt.throwOnFailure(r11)
            r0.L$0 = r10
            r0.label = r4
            androidx.compose.material.ripple.RippleAnimation$fadeIn$2 r11 = new androidx.compose.material.ripple.RippleAnimation$fadeIn$2
            r11.<init>(r10, r6)
            java.lang.Object r11 = kotlin.ResultKt.coroutineScope(r11, r0)
            if (r11 != r1) goto L58
            goto L59
        L58:
            r11 = r3
        L59:
            if (r11 != r1) goto L5c
            return r1
        L5c:
            r2 = r10
        L5d:
            androidx.compose.runtime.ParcelableSnapshotMutableState r11 = r2.finishedFadingIn$delegate
            java.lang.Boolean r8 = java.lang.Boolean.TRUE
            r11.setValue(r8)
            r0.L$0 = r2
            r0.label = r7
        L68:
            kotlinx.coroutines.CompletableDeferredImpl r11 = r2.finishSignalDeferred
            java.lang.Object r8 = r11.getState$kotlinx_coroutines_core()
            boolean r9 = r8 instanceof kotlinx.coroutines.Incomplete
            if (r9 != 0) goto L80
            boolean r11 = r8 instanceof kotlinx.coroutines.CompletedExceptionally
            if (r11 != 0) goto L7b
            java.lang.Object r11 = kotlinx.coroutines.JobKt.unboxState(r8)
            goto La8
        L7b:
            kotlinx.coroutines.CompletedExceptionally r8 = (kotlinx.coroutines.CompletedExceptionally) r8
            java.lang.Throwable r11 = r8.cause
            throw r11
        L80:
            int r8 = r11.startInternal(r8)
            if (r8 < 0) goto L68
            kotlinx.coroutines.JobSupport$AwaitContinuation r8 = new kotlinx.coroutines.JobSupport$AwaitContinuation
            kotlin.coroutines.Continuation r9 = kotlin.ResultKt.intercepted(r0)
            r8.<init>(r9, r11)
            r8.initCancellability()
            kotlinx.coroutines.InvokeOnCompletion r9 = new kotlinx.coroutines.InvokeOnCompletion
            r9.<init>(r7, r8)
            r7 = 0
            kotlinx.coroutines.DisposableHandle r11 = r11.invokeOnCompletion(r7, r4, r9)
            kotlinx.coroutines.InvokeOnCancel r7 = new kotlinx.coroutines.InvokeOnCancel
            r7.<init>(r4, r11)
            r8.invokeOnCancellation(r7)
            java.lang.Object r11 = r8.getResult()
        La8:
            if (r11 != r1) goto Lab
            return r1
        Lab:
            r0.L$0 = r6
            r0.label = r5
            r2.getClass()
            androidx.compose.material.ripple.RippleAnimation$fadeOut$2 r11 = new androidx.compose.material.ripple.RippleAnimation$fadeOut$2
            r11.<init>(r2, r6)
            java.lang.Object r11 = kotlin.ResultKt.coroutineScope(r11, r0)
            if (r11 != r1) goto Lbe
            goto Lbf
        Lbe:
            r11 = r3
        Lbf:
            if (r11 != r1) goto Lc2
            return r1
        Lc2:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ripple.RippleAnimation.animate(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void finish() {
        Object tryMakeCompleting;
        this.finishRequested$delegate.setValue(Boolean.TRUE);
        Unit unit = Unit.INSTANCE;
        do {
            CompletableDeferredImpl completableDeferredImpl = this.finishSignalDeferred;
            tryMakeCompleting = completableDeferredImpl.tryMakeCompleting(completableDeferredImpl.getState$kotlinx_coroutines_core(), unit);
            if (tryMakeCompleting == JobKt.COMPLETING_ALREADY || tryMakeCompleting == JobKt.COMPLETING_WAITING_CHILDREN) {
                return;
            }
        } while (tryMakeCompleting == JobKt.COMPLETING_RETRY);
    }
}
