package kotlinx.coroutines.flow.internal;

import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;

/* loaded from: classes.dex */
public interface FusibleFlow extends Flow {
    Flow fuse(CoroutineContext coroutineContext, int i, BufferOverflow bufferOverflow);
}
