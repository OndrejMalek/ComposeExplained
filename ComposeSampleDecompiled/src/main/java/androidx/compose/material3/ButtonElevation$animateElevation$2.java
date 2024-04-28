package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.Animatable$snapTo$2;
import androidx.compose.animation.core.MutatorMutex;
import androidx.compose.animation.core.MutatorMutex$mutate$2;
import androidx.compose.ui.unit.Dp;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes.dex */
public final class ButtonElevation$animateElevation$2 extends SuspendLambda implements Function2 {
    public final /* synthetic */ Animatable $animatable;
    public final /* synthetic */ float $target;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ButtonElevation$animateElevation$2(Animatable animatable, float f, Continuation continuation) {
        super(2, continuation);
        this.$animatable = animatable;
        this.$target = f;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ButtonElevation$animateElevation$2(this.$animatable, this.$target, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ButtonElevation$animateElevation$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Dp dp = new Dp(this.$target);
            this.label = 1;
            Animatable animatable = this.$animatable;
            animatable.getClass();
            Animatable$snapTo$2 animatable$snapTo$2 = new Animatable$snapTo$2(animatable, dp, null);
            MutatorMutex mutatorMutex = animatable.mutatorMutex;
            mutatorMutex.getClass();
            Object coroutineScope = ResultKt.coroutineScope(new MutatorMutex$mutate$2(1, mutatorMutex, animatable$snapTo$2, null), this);
            if (coroutineScope != coroutineSingletons) {
                coroutineScope = unit;
            }
            if (coroutineScope == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
