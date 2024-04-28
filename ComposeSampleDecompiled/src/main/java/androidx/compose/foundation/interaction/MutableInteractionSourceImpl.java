package androidx.compose.foundation.interaction;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.SharedFlowImpl;

/* loaded from: classes.dex */
public final class MutableInteractionSourceImpl implements InteractionSource {
    public final SharedFlowImpl interactions = new SharedFlowImpl(0, 16, BufferOverflow.DROP_OLDEST);

    public final Object emit(Interaction interaction, Continuation continuation) {
        Object emit = this.interactions.emit(interaction, continuation);
        return emit == CoroutineSingletons.COROUTINE_SUSPENDED ? emit : Unit.INSTANCE;
    }
}
