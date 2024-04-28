package androidx.compose.animation.core;

import kotlin.ResultKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref$ObjectRef;

/* loaded from: classes.dex */
public final class SuspendAnimationKt$animate$4 extends ContinuationImpl {
    public AnimationState L$0;
    public Animation L$1;
    public Function1 L$2;
    public Ref$ObjectRef L$3;
    public int label;
    public /* synthetic */ Object result;

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ResultKt.animate(null, null, 0L, null, this);
    }
}
