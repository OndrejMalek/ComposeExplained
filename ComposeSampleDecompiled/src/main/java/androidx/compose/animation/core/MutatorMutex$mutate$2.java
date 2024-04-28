package androidx.compose.animation.core;

import androidx.compose.animation.core.MutatorMutex;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.internal.ChildCancelledException;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexImpl;

/* loaded from: classes.dex */
public final class MutatorMutex$mutate$2 extends SuspendLambda implements Function2 {
    public final /* synthetic */ Function1 $block;
    public final /* synthetic */ int $priority;
    public /* synthetic */ Object L$0;
    public Mutex L$1;
    public Object L$2;
    public MutatorMutex L$3;
    public int label;
    public final /* synthetic */ MutatorMutex this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MutatorMutex$mutate$2(int i, MutatorMutex mutatorMutex, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.$priority = i;
        this.this$0 = mutatorMutex;
        this.$block = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        MutatorMutex$mutate$2 mutatorMutex$mutate$2 = new MutatorMutex$mutate$2(this.$priority, this.this$0, this.$block, continuation);
        mutatorMutex$mutate$2.L$0 = obj;
        return mutatorMutex$mutate$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((MutatorMutex$mutate$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r5v6, types: [kotlinx.coroutines.sync.Mutex] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MutatorMutex mutatorMutex;
        Function1 function1;
        MutatorMutex.Mutator mutator;
        MutexImpl mutexImpl;
        Mutex mutex;
        MutatorMutex.Mutator mutator2;
        MutatorMutex mutatorMutex2;
        Throwable th;
        AtomicReference atomicReference;
        AtomicReference atomicReference2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        ?? r1 = this.label;
        try {
            try {
                if (r1 == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineContext.Element element = ((CoroutineScope) this.L$0).getCoroutineContext().get(Job.Key.$$INSTANCE);
                    ResultKt.checkNotNull(element);
                    MutatorMutex.Mutator mutator3 = new MutatorMutex.Mutator(this.$priority, (Job) element);
                    while (true) {
                        mutatorMutex = this.this$0;
                        AtomicReference atomicReference3 = mutatorMutex.currentMutator;
                        MutatorMutex.Mutator mutator4 = (MutatorMutex.Mutator) atomicReference3.get();
                        if (mutator4 != null && AnimationEndReason$EnumUnboxingLocalUtility.compareTo(mutator3.priority, mutator4.priority) < 0) {
                            throw new CancellationException("Current mutation had a higher priority");
                        }
                        while (!atomicReference3.compareAndSet(mutator4, mutator3)) {
                            if (atomicReference3.get() != mutator4) {
                                break;
                            }
                        }
                        if (mutator4 != null) {
                            mutator4.job.cancel(new ChildCancelledException(1));
                        }
                        this.L$0 = mutator3;
                        MutexImpl mutexImpl2 = mutatorMutex.mutex;
                        this.L$1 = mutexImpl2;
                        Function1 function12 = this.$block;
                        this.L$2 = function12;
                        this.L$3 = mutatorMutex;
                        this.label = 1;
                        if (mutexImpl2.lock(null, this) == coroutineSingletons) {
                            return coroutineSingletons;
                        }
                        function1 = function12;
                        mutator = mutator3;
                        mutexImpl = mutexImpl2;
                    }
                } else {
                    if (r1 != 1) {
                        if (r1 != 2) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        mutatorMutex2 = (MutatorMutex) this.L$2;
                        mutex = this.L$1;
                        mutator2 = (MutatorMutex.Mutator) this.L$0;
                        try {
                            ResultKt.throwOnFailure(obj);
                            atomicReference2 = mutatorMutex2.currentMutator;
                            while (!atomicReference2.compareAndSet(mutator2, null) && atomicReference2.get() == mutator2) {
                            }
                            ((MutexImpl) mutex).unlock(null);
                            return obj;
                        } catch (Throwable th2) {
                            th = th2;
                            atomicReference = mutatorMutex2.currentMutator;
                            while (!atomicReference.compareAndSet(mutator2, null)) {
                            }
                            throw th;
                        }
                    }
                    MutatorMutex mutatorMutex3 = this.L$3;
                    function1 = (Function1) this.L$2;
                    ?? r5 = this.L$1;
                    mutator = (MutatorMutex.Mutator) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    mutatorMutex = mutatorMutex3;
                    mutexImpl = r5;
                }
                this.L$0 = mutator;
                this.L$1 = mutex;
                this.L$2 = mutatorMutex;
                this.L$3 = null;
                this.label = 2;
                Object invoke = function1.invoke(this);
                if (invoke == coroutineSingletons) {
                    return coroutineSingletons;
                }
                mutatorMutex2 = mutatorMutex;
                obj = invoke;
                mutator2 = mutator;
                atomicReference2 = mutatorMutex2.currentMutator;
                while (!atomicReference2.compareAndSet(mutator2, null)) {
                }
                ((MutexImpl) mutex).unlock(null);
                return obj;
            } catch (Throwable th3) {
                mutator2 = mutator;
                mutatorMutex2 = mutatorMutex;
                th = th3;
                atomicReference = mutatorMutex2.currentMutator;
                while (!atomicReference.compareAndSet(mutator2, null) && atomicReference.get() == mutator2) {
                }
                throw th;
            }
            mutex = mutexImpl;
        } catch (Throwable th4) {
            ((MutexImpl) r1).unlock(null);
            throw th4;
        }
    }
}
