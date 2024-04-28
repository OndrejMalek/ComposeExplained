package androidx.compose.foundation;

import androidx.compose.foundation.interaction.MutableInteractionSourceImpl;
import androidx.compose.foundation.interaction.PressInteraction$Press;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes.dex */
public final class ClickableKt$handlePressInteraction$2$delayJob$1 extends SuspendLambda implements Function2 {
    public final /* synthetic */ Function0 $delayPressInteraction;
    public final /* synthetic */ AbstractClickableNode$InteractionData $interactionData;
    public final /* synthetic */ MutableInteractionSourceImpl $interactionSource;
    public final /* synthetic */ long $pressPoint;
    public PressInteraction$Press L$0;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ClickableKt$handlePressInteraction$2$delayJob$1(Function0 function0, long j, MutableInteractionSourceImpl mutableInteractionSourceImpl, AbstractClickableNode$InteractionData abstractClickableNode$InteractionData, Continuation continuation) {
        super(2, continuation);
        this.$delayPressInteraction = function0;
        this.$pressPoint = j;
        this.$interactionSource = mutableInteractionSourceImpl;
        this.$interactionData = abstractClickableNode$InteractionData;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ClickableKt$handlePressInteraction$2$delayJob$1(this.$delayPressInteraction, this.$pressPoint, this.$interactionSource, this.$interactionData, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ClickableKt$handlePressInteraction$2$delayJob$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        PressInteraction$Press pressInteraction$Press;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (((Boolean) this.$delayPressInteraction.invoke()).booleanValue()) {
                long j = Clickable_androidKt.TapIndicationDelay;
                this.label = 1;
                if (ResultKt.delay(j, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                pressInteraction$Press = this.L$0;
                ResultKt.throwOnFailure(obj);
                this.$interactionData.pressInteraction = pressInteraction$Press;
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
        }
        PressInteraction$Press pressInteraction$Press2 = new PressInteraction$Press(this.$pressPoint);
        this.L$0 = pressInteraction$Press2;
        this.label = 2;
        if (this.$interactionSource.emit(pressInteraction$Press2, this) == coroutineSingletons) {
            return coroutineSingletons;
        }
        pressInteraction$Press = pressInteraction$Press2;
        this.$interactionData.pressInteraction = pressInteraction$Press;
        return Unit.INSTANCE;
    }
}
