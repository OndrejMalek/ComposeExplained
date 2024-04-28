package kotlinx.coroutines;

import androidx.startup.StartupException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ConflatedBufferedChannel;
import kotlinx.coroutines.channels.ReceiveChannel;
import kotlinx.coroutines.flow.DistinctFlowImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__DistinctKt$defaultAreEquivalent$1;
import kotlinx.coroutines.flow.FlowKt__DistinctKt$defaultKeySelector$1;
import kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharing$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharingConfig;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedWhileSubscribed;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.internal.ChannelFlow;
import kotlinx.coroutines.flow.internal.StackFrameContinuation;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.ContextScope;
import kotlinx.coroutines.internal.CoroutineExceptionHandlerImplKt;
import kotlinx.coroutines.internal.DiagnosticCoroutineContextException;
import kotlinx.coroutines.internal.Segment;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* loaded from: classes.dex */
public abstract class JobKt {
    public static final Symbol RESUME_TOKEN = new Symbol(0, "RESUME_TOKEN");
    public static final Symbol DISPOSED_TASK = new Symbol(0, "REMOVED_TASK");
    public static final Symbol CLOSED_EMPTY = new Symbol(0, "CLOSED_EMPTY");
    public static final Symbol COMPLETING_ALREADY = new Symbol(0, "COMPLETING_ALREADY");
    public static final Symbol COMPLETING_WAITING_CHILDREN = new Symbol(0, "COMPLETING_WAITING_CHILDREN");
    public static final Symbol COMPLETING_RETRY = new Symbol(0, "COMPLETING_RETRY");
    public static final Symbol TOO_LATE_TO_CANCEL = new Symbol(0, "TOO_LATE_TO_CANCEL");
    public static final Symbol SEALED = new Symbol(0, "SEALED");
    public static final Empty EMPTY_NEW = new Empty(false);
    public static final Empty EMPTY_ACTIVE = new Empty(true);

    public static BufferedChannel Channel$default(int i, BufferOverflow bufferOverflow, int i2) {
        BufferedChannel conflatedBufferedChannel;
        int i3 = i2 & 2;
        BufferOverflow bufferOverflow2 = BufferOverflow.SUSPEND;
        if (i3 != 0) {
            bufferOverflow = bufferOverflow2;
        }
        if (i != -2) {
            if (i == -1) {
                if (bufferOverflow == bufferOverflow2) {
                    return new ConflatedBufferedChannel(1, BufferOverflow.DROP_OLDEST, null);
                }
                throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow".toString());
            }
            if (i != 0) {
                return i != Integer.MAX_VALUE ? bufferOverflow == bufferOverflow2 ? new BufferedChannel(i, null) : new ConflatedBufferedChannel(i, bufferOverflow, null) : new BufferedChannel(Integer.MAX_VALUE, null);
            }
            conflatedBufferedChannel = bufferOverflow == bufferOverflow2 ? new BufferedChannel(0, null) : new ConflatedBufferedChannel(1, bufferOverflow, null);
        } else if (bufferOverflow == bufferOverflow2) {
            Channel.Factory.getClass();
            conflatedBufferedChannel = new BufferedChannel(Channel.Factory.CHANNEL_DEFAULT_CAPACITY, null);
        } else {
            conflatedBufferedChannel = new ConflatedBufferedChannel(1, bufferOverflow, null);
        }
        return conflatedBufferedChannel;
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [java.lang.RuntimeException, androidx.startup.StartupException] */
    public static final StartupException callUndeliveredElementCatchingException(Function1 function1, Object obj, StartupException startupException) {
        try {
            function1.invoke(obj);
        } catch (Throwable th) {
            if (startupException == null || startupException.getCause() == th) {
                return new RuntimeException("Exception in undelivered element handler for " + obj, th);
            }
            ResultKt.addSuppressed(startupException, th);
        }
        return startupException;
    }

    public static final void cancelConsumed(ReceiveChannel receiveChannel, Throwable th) {
        if (th != null) {
            r0 = th instanceof CancellationException ? (CancellationException) th : null;
            if (r0 == null) {
                r0 = new CancellationException("Channel was consumed, consumer had failed");
                r0.initCause(th);
            }
        }
        receiveChannel.cancel(r0);
    }

    public static final Flow distinctUntilChanged(Flow flow) {
        if (flow instanceof StateFlow) {
            return flow;
        }
        FlowKt__DistinctKt$defaultKeySelector$1 flowKt__DistinctKt$defaultKeySelector$1 = FlowKt__DistinctKt$defaultKeySelector$1.INSTANCE;
        FlowKt__DistinctKt$defaultAreEquivalent$1 flowKt__DistinctKt$defaultAreEquivalent$1 = FlowKt__DistinctKt$defaultAreEquivalent$1.INSTANCE;
        if (flow instanceof DistinctFlowImpl) {
            DistinctFlowImpl distinctFlowImpl = (DistinctFlowImpl) flow;
            if (distinctFlowImpl.keySelector == flowKt__DistinctKt$defaultKeySelector$1 && distinctFlowImpl.areEquivalent == flowKt__DistinctKt$defaultAreEquivalent$1) {
                return flow;
            }
        }
        return new DistinctFlowImpl(flow);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:7:0x0020 */
    /* JADX DEBUG: Finally have unexpected throw blocks count: 2, expect 1 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0063 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0070 A[Catch: all -> 0x0034, TRY_LEAVE, TryCatch #0 {all -> 0x0034, blocks: (B:12:0x002e, B:14:0x0053, B:19:0x0068, B:21:0x0070, B:32:0x0046, B:34:0x004d), top: B:7:0x0020 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0022  */
    /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.coroutines.Continuation, kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r2v1, types: [kotlinx.coroutines.flow.FlowCollector] */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r7v5, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r7v7, types: [kotlinx.coroutines.channels.ReceiveChannel] */
    /* JADX WARN: Type inference failed for: r9v10, types: [kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator] */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:22:0x0082 -> B:13:0x0031). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object emitAllImpl$FlowKt__ChannelsKt(kotlinx.coroutines.flow.FlowCollector r6, kotlinx.coroutines.channels.ProducerCoroutine r7, boolean r8, kotlin.coroutines.Continuation r9) {
        /*
            boolean r0 = r9 instanceof kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1
            if (r0 == 0) goto L13
            r0 = r9
            kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1 r0 = (kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1 r0 = new kotlinx.coroutines.flow.FlowKt__ChannelsKt$emitAllImpl$1
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L4a
            if (r2 == r4) goto L3e
            if (r2 != r3) goto L36
            boolean r8 = r0.Z$0
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r2 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L34
        L31:
            r9 = r6
            r6 = r2
            goto L53
        L34:
            r6 = move-exception
            goto L8e
        L36:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3e:
            boolean r8 = r0.Z$0
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r6 = r0.L$2
            kotlinx.coroutines.channels.ReceiveChannel r7 = r0.L$1
            kotlinx.coroutines.flow.FlowCollector r2 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L34
            goto L68
        L4a:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.channels.Channel r9 = r7._channel     // Catch: java.lang.Throwable -> L34
            kotlinx.coroutines.channels.BufferedChannel$BufferedChannelIterator r9 = r9.iterator()     // Catch: java.lang.Throwable -> L34
        L53:
            r0.L$0 = r6     // Catch: java.lang.Throwable -> L34
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L34
            r0.L$2 = r9     // Catch: java.lang.Throwable -> L34
            r0.Z$0 = r8     // Catch: java.lang.Throwable -> L34
            r0.label = r4     // Catch: java.lang.Throwable -> L34
            java.lang.Object r2 = r9.hasNext(r0)     // Catch: java.lang.Throwable -> L34
            if (r2 != r1) goto L64
            return r1
        L64:
            r5 = r2
            r2 = r6
            r6 = r9
            r9 = r5
        L68:
            java.lang.Boolean r9 = (java.lang.Boolean) r9     // Catch: java.lang.Throwable -> L34
            boolean r9 = r9.booleanValue()     // Catch: java.lang.Throwable -> L34
            if (r9 == 0) goto L85
            java.lang.Object r9 = r6.next()     // Catch: java.lang.Throwable -> L34
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L34
            r0.L$1 = r7     // Catch: java.lang.Throwable -> L34
            r0.L$2 = r6     // Catch: java.lang.Throwable -> L34
            r0.Z$0 = r8     // Catch: java.lang.Throwable -> L34
            r0.label = r3     // Catch: java.lang.Throwable -> L34
            java.lang.Object r9 = r2.emit(r9, r0)     // Catch: java.lang.Throwable -> L34
            if (r9 != r1) goto L31
            return r1
        L85:
            if (r8 == 0) goto L8b
            r6 = 0
            cancelConsumed(r7, r6)
        L8b:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L8e:
            throw r6     // Catch: java.lang.Throwable -> L8f
        L8f:
            r9 = move-exception
            if (r8 == 0) goto L95
            cancelConsumed(r7, r6)
        L95:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobKt.emitAllImpl$FlowKt__ChannelsKt(kotlinx.coroutines.flow.FlowCollector, kotlinx.coroutines.channels.ProducerCoroutine, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final void ensureActive(CoroutineContext coroutineContext) {
        Job job = (Job) coroutineContext.get(Job.Key.$$INSTANCE);
        if (job != null && !job.isActive()) {
            throw ((JobSupport) job).getCancellationException();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v10, resolved type: kotlinx.coroutines.flow.FlowKt__ReduceKt$first$3 */
    /* JADX DEBUG: Multi-variable search result rejected for r0v11, resolved type: kotlinx.coroutines.flow.FlowKt__ReduceKt$first$3 */
    /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: kotlinx.coroutines.flow.FlowKt__ReduceKt$first$3 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.lang.Object, kotlin.jvm.internal.Ref$ObjectRef] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object first(kotlinx.coroutines.flow.Flow r5, kotlin.jvm.functions.Function2 r6, kotlin.coroutines.Continuation r7) {
        /*
            boolean r0 = r7 instanceof kotlinx.coroutines.flow.FlowKt__ReduceKt$first$3
            if (r0 == 0) goto L13
            r0 = r7
            kotlinx.coroutines.flow.FlowKt__ReduceKt$first$3 r0 = (kotlinx.coroutines.flow.FlowKt__ReduceKt$first$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.flow.FlowKt__ReduceKt$first$3 r0 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$first$3
            r0.<init>(r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.flow.internal.NullSurrogateKt.NULL
            r4 = 1
            if (r2 == 0) goto L39
            if (r2 != r4) goto L31
            kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$2 r5 = r0.L$2
            kotlin.jvm.internal.Ref$ObjectRef r6 = r0.L$1
            kotlin.jvm.functions.Function2 r0 = r0.L$0
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L2f
            goto L63
        L2f:
            r7 = move-exception
            goto L5f
        L31:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L39:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlin.jvm.internal.Ref$ObjectRef r7 = new kotlin.jvm.internal.Ref$ObjectRef
            r7.<init>()
            r7.element = r3
            kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$2 r2 = new kotlinx.coroutines.flow.FlowKt__ReduceKt$first$$inlined$collectWhile$2
            r2.<init>(r6, r7)
            r0.L$0 = r6     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L5a
            r0.L$1 = r7     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L5a
            r0.L$2 = r2     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L5a
            r0.label = r4     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L5a
            java.lang.Object r5 = r5.collect(r2, r0)     // Catch: kotlinx.coroutines.flow.internal.AbortFlowException -> L5a
            if (r5 != r1) goto L57
            goto L67
        L57:
            r0 = r6
            r6 = r7
            goto L63
        L5a:
            r5 = move-exception
            r0 = r6
            r6 = r7
            r7 = r5
            r5 = r2
        L5f:
            kotlinx.coroutines.flow.FlowCollector r1 = r7.owner
            if (r1 != r5) goto L7c
        L63:
            java.lang.Object r1 = r6.element
            if (r1 == r3) goto L68
        L67:
            return r1
        L68:
            java.util.NoSuchElementException r5 = new java.util.NoSuchElementException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Expected at least one element matching the predicate "
            r6.<init>(r7)
            r6.append(r0)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L7c:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobKt.first(kotlinx.coroutines.flow.Flow, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* renamed from: getSegment-impl, reason: not valid java name */
    public static final Segment m303getSegmentimpl(Object obj) {
        if (obj != AtomicKt.CLOSED) {
            return (Segment) obj;
        }
        throw new IllegalStateException("Does not contain segment".toString());
    }

    public static final void handleUncaughtCoroutineException(CoroutineContext coroutineContext, Throwable th) {
        Throwable runtimeException;
        Iterator it = CoroutineExceptionHandlerImplKt.platformExceptionHandlers.iterator();
        while (it.hasNext()) {
            try {
                ((CoroutineExceptionHandler) it.next()).handleException(coroutineContext, th);
            } catch (Throwable th2) {
                if (th == th2) {
                    runtimeException = th;
                } else {
                    runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                    ResultKt.addSuppressed(runtimeException, th);
                }
                Thread currentThread = Thread.currentThread();
                currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, runtimeException);
            }
        }
        try {
            ResultKt.addSuppressed(th, new DiagnosticCoroutineContextException(coroutineContext));
        } catch (Throwable unused) {
        }
        Thread currentThread2 = Thread.currentThread();
        currentThread2.getUncaughtExceptionHandler().uncaughtException(currentThread2, th);
    }

    public static final boolean isActive(CoroutineContext coroutineContext) {
        Job job = (Job) coroutineContext.get(Job.Key.$$INSTANCE);
        if (job != null) {
            return job.isActive();
        }
        return true;
    }

    /* renamed from: isClosed-impl, reason: not valid java name */
    public static final boolean m304isClosedimpl(Object obj) {
        return obj == AtomicKt.CLOSED;
    }

    /* renamed from: plus-FjFbRPM, reason: not valid java name */
    public static final Object m305plusFjFbRPM(Object obj, Object obj2) {
        if (obj == null) {
            return obj2;
        }
        if (obj instanceof ArrayList) {
            ((ArrayList) obj).add(obj2);
            return obj;
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(obj);
        arrayList.add(obj2);
        return arrayList;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r10v0, resolved type: kotlinx.coroutines.flow.SafeFlow */
    /* JADX WARN: Multi-variable type inference failed */
    public static final ReadonlyStateFlow stateIn(SafeFlow safeFlow, ContextScope contextScope, StartedWhileSubscribed startedWhileSubscribed, Float f) {
        SharingConfig sharingConfig;
        ChannelFlow channelFlow;
        Flow dropChannelOperators;
        Channel.Factory.getClass();
        int i = Channel.Factory.CHANNEL_DEFAULT_CAPACITY;
        if (1 >= i) {
            i = 1;
        }
        int i2 = i - 1;
        boolean z = safeFlow instanceof ChannelFlow;
        BufferOverflow bufferOverflow = BufferOverflow.SUSPEND;
        if (!z || (dropChannelOperators = (channelFlow = (ChannelFlow) safeFlow).dropChannelOperators()) == null) {
            sharingConfig = new SharingConfig(i2, EmptyCoroutineContext.INSTANCE, bufferOverflow, safeFlow);
        } else {
            BufferOverflow bufferOverflow2 = channelFlow.onBufferOverflow;
            int i3 = channelFlow.capacity;
            if (i3 != -3 && i3 != -2 && i3 != 0) {
                i2 = i3;
            } else if (bufferOverflow2 != bufferOverflow || i3 == 0) {
                i2 = 0;
            }
            sharingConfig = new SharingConfig(i2, channelFlow.context, bufferOverflow2, dropChannelOperators);
        }
        StateFlowImpl stateFlowImpl = new StateFlowImpl(f);
        CoroutineContext coroutineContext = (CoroutineContext) sharingConfig.context;
        Flow flow = (Flow) sharingConfig.upstream;
        int i4 = ResultKt.areEqual(startedWhileSubscribed, SharingStarted.Companion.Eagerly) ? 1 : 4;
        FlowKt__ShareKt$launchSharing$1 flowKt__ShareKt$launchSharing$1 = new FlowKt__ShareKt$launchSharing$1(startedWhileSubscribed, flow, stateFlowImpl, f, null);
        CoroutineContext foldCopies = ResultKt.foldCopies(contextScope.getCoroutineContext(), coroutineContext, true);
        DefaultScheduler defaultScheduler = Dispatchers.Default;
        if (foldCopies != defaultScheduler && foldCopies.get(ContinuationInterceptor.Key.$$INSTANCE) == null) {
            foldCopies = foldCopies.plus(defaultScheduler);
        }
        if (i4 == 0) {
            throw null;
        }
        AbstractCoroutine lazyStandaloneCoroutine = i4 == 2 ? new LazyStandaloneCoroutine(foldCopies, flowKt__ShareKt$launchSharing$1) : new AbstractCoroutine(foldCopies, true);
        lazyStandaloneCoroutine.start(i4, lazyStandaloneCoroutine, flowKt__ShareKt$launchSharing$1);
        return new ReadonlyStateFlow(stateFlowImpl);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00e8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final long systemProp(java.lang.String r23, long r24, long r26, long r28) {
        /*
            r0 = r23
            r1 = r26
            r3 = r28
            int r5 = kotlinx.coroutines.internal.SystemPropsKt__SystemPropsKt.AVAILABLE_PROCESSORS
            java.lang.String r6 = java.lang.System.getProperty(r23)     // Catch: java.lang.SecurityException -> Ld
            goto Le
        Ld:
            r6 = 0
        Le:
            if (r6 != 0) goto L14
            r8 = r24
            goto Lb6
        L14:
            kotlin.ResultKt.checkRadix()
            int r7 = r6.length()
            if (r7 != 0) goto L22
        L1d:
            r19 = r6
        L1f:
            r5 = 0
            goto La4
        L22:
            r8 = 0
            char r9 = r6.charAt(r8)
            r10 = 48
            int r10 = kotlin.ResultKt.compare(r9, r10)
            r11 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r10 >= 0) goto L4a
            r10 = 1
            if (r7 != r10) goto L38
            goto L1d
        L38:
            r13 = 45
            if (r9 != r13) goto L40
            r11 = -9223372036854775808
            r8 = r10
            goto L4b
        L40:
            r13 = 43
            if (r9 != r13) goto L1d
            r22 = r10
            r10 = r8
            r8 = r22
            goto L4b
        L4a:
            r10 = r8
        L4b:
            r15 = 0
            r13 = r15
            r15 = -256204778801521550(0xfc71c71c71c71c72, double:-2.772000429909333E291)
        L53:
            if (r8 >= r7) goto L96
            char r9 = r6.charAt(r8)
            r5 = 10
            int r9 = java.lang.Character.digit(r9, r5)
            if (r9 >= 0) goto L62
            goto L1d
        L62:
            int r17 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r17 >= 0) goto L7b
            r17 = -256204778801521550(0xfc71c71c71c71c72, double:-2.772000429909333E291)
            int r15 = (r15 > r17 ? 1 : (r15 == r17 ? 0 : -1))
            if (r15 != 0) goto L1d
            r19 = r6
            r24 = r7
            long r6 = (long) r5
            long r15 = r11 / r6
            int r6 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r6 >= 0) goto L84
        L7a:
            goto L1f
        L7b:
            r19 = r6
            r24 = r7
            r17 = -256204778801521550(0xfc71c71c71c71c72, double:-2.772000429909333E291)
        L84:
            long r5 = (long) r5
            long r13 = r13 * r5
            long r5 = (long) r9
            long r20 = r11 + r5
            int r7 = (r13 > r20 ? 1 : (r13 == r20 ? 0 : -1))
            if (r7 >= 0) goto L8e
            goto L7a
        L8e:
            long r13 = r13 - r5
            int r8 = r8 + 1
            r7 = r24
            r6 = r19
            goto L53
        L96:
            r19 = r6
            if (r10 == 0) goto L9f
            java.lang.Long r5 = java.lang.Long.valueOf(r13)
            goto La4
        L9f:
            long r5 = -r13
            java.lang.Long r5 = java.lang.Long.valueOf(r5)
        La4:
            r6 = 39
            java.lang.String r7 = "System property '"
            if (r5 == 0) goto Le8
            long r8 = r5.longValue()
            int r5 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r5 > 0) goto Lb7
            int r5 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r5 > 0) goto Lb7
        Lb6:
            return r8
        Lb7:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r7)
            r10.append(r0)
            java.lang.String r0 = "' should be in range "
            r10.append(r0)
            r10.append(r1)
            java.lang.String r0 = ".."
            r10.append(r0)
            r10.append(r3)
            java.lang.String r0 = ", but is '"
            r10.append(r0)
            r10.append(r8)
            r10.append(r6)
            java.lang.String r0 = r10.toString()
            java.lang.String r0 = r0.toString()
            r5.<init>(r0)
            throw r5
        Le8:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>(r7)
            r2.append(r0)
            java.lang.String r0 = "' has unrecognized value '"
            r2.append(r0)
            r5 = r19
            r2.append(r5)
            r2.append(r6)
            java.lang.String r0 = r2.toString()
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.JobKt.systemProp(java.lang.String, long, long, long):long");
    }

    public static int systemProp$default(String str, int i, int i2, int i3, int i4) {
        if ((i4 & 4) != 0) {
            i2 = 1;
        }
        if ((i4 & 8) != 0) {
            i3 = Integer.MAX_VALUE;
        }
        return (int) systemProp(str, i, i2, i3);
    }

    public static final Object unboxState(Object obj) {
        Incomplete incomplete;
        IncompleteStateBox incompleteStateBox = obj instanceof IncompleteStateBox ? (IncompleteStateBox) obj : null;
        return (incompleteStateBox == null || (incomplete = incompleteStateBox.state) == null) ? obj : incomplete;
    }

    public static final Object withContextUndispatched(CoroutineContext coroutineContext, Object obj, Object obj2, Function2 function2, Continuation continuation) {
        Object updateThreadContext = AtomicKt.updateThreadContext(coroutineContext, obj2);
        try {
            StackFrameContinuation stackFrameContinuation = new StackFrameContinuation(continuation, coroutineContext);
            ResultKt.beforeCheckcastToFunctionOfArity(2, function2);
            Object invoke = function2.invoke(obj, stackFrameContinuation);
            AtomicKt.restoreThreadContext(coroutineContext, updateThreadContext);
            if (invoke == CoroutineSingletons.COROUTINE_SUSPENDED) {
                ResultKt.checkNotNullParameter(continuation, "frame");
            }
            return invoke;
        } catch (Throwable th) {
            AtomicKt.restoreThreadContext(coroutineContext, updateThreadContext);
            throw th;
        }
    }
}
