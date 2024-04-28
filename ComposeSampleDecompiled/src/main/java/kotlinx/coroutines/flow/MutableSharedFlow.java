package kotlinx.coroutines.flow;

/* loaded from: classes.dex */
public interface MutableSharedFlow extends Flow, FlowCollector {
    void resetReplayCache();

    boolean tryEmit(Object obj);
}
