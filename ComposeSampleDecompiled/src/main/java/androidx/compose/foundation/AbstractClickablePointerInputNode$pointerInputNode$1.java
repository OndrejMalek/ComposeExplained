package androidx.compose.foundation;

import _COROUTINE._BOUNDARY;
import androidx.compose.foundation.gestures.PressGestureScopeImpl;
import androidx.compose.foundation.gestures.ScrollableKt$NoOpOnDragStarted$1;
import androidx.compose.foundation.gestures.TapGestureDetectorKt;
import androidx.compose.foundation.gestures.TapGestureDetectorKt$detectTapAndPress$2;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl;
import androidx.compose.ui.unit.IntOffset;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.AbstractMap$toString$1;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public final class AbstractClickablePointerInputNode$pointerInputNode$1 extends SuspendLambda implements Function2 {
    public /* synthetic */ Object L$0;
    public int label;
    public final /* synthetic */ AbstractClickablePointerInputNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractClickablePointerInputNode$pointerInputNode$1(AbstractClickablePointerInputNode abstractClickablePointerInputNode, Continuation continuation) {
        super(2, continuation);
        this.this$0 = abstractClickablePointerInputNode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        AbstractClickablePointerInputNode$pointerInputNode$1 abstractClickablePointerInputNode$pointerInputNode$1 = new AbstractClickablePointerInputNode$pointerInputNode$1(this.this$0, continuation);
        abstractClickablePointerInputNode$pointerInputNode$1.L$0 = obj;
        return abstractClickablePointerInputNode$pointerInputNode$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((AbstractClickablePointerInputNode$pointerInputNode$1) create((PointerInputScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
            this.label = 1;
            ClickablePointerInputNode clickablePointerInputNode = (ClickablePointerInputNode) this.this$0;
            clickablePointerInputNode.getClass();
            long j = ((SuspendingPointerInputModifierNodeImpl) pointerInputScope).boundsSize;
            long IntOffset = ResultKt.IntOffset(((int) (j >> 32)) / 2, ((int) (j & 4294967295L)) / 2);
            int i2 = IntOffset.$r8$clinit;
            clickablePointerInputNode.interactionData.centreOffset = _BOUNDARY.Offset((int) (IntOffset >> 32), (int) (IntOffset & 4294967295L));
            ClickablePointerInputNode$pointerInput$2 clickablePointerInputNode$pointerInput$2 = new ClickablePointerInputNode$pointerInput$2(clickablePointerInputNode, null);
            AbstractMap$toString$1 abstractMap$toString$1 = new AbstractMap$toString$1(2, clickablePointerInputNode);
            ScrollableKt$NoOpOnDragStarted$1 scrollableKt$NoOpOnDragStarted$1 = TapGestureDetectorKt.NoPressGesture;
            Object coroutineScope = ResultKt.coroutineScope(new TapGestureDetectorKt$detectTapAndPress$2(pointerInputScope, clickablePointerInputNode$pointerInput$2, abstractMap$toString$1, new PressGestureScopeImpl(pointerInputScope), null), this);
            if (coroutineScope != coroutineSingletons) {
                coroutineScope = unit;
            }
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
