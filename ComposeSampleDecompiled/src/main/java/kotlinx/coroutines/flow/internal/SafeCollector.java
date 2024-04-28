package kotlinx.coroutines.flow.internal;

import androidx.compose.ui.ComposedModifierKt$materialize$result$1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.AbstractMap$toString$1;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt___MapsJvmKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function3;
import kotlin.sequences.GeneratorSequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.DelimitedRangesSequence;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$2;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.flow.FlowCollector;

/* loaded from: classes.dex */
public final class SafeCollector extends ContinuationImpl implements FlowCollector {
    public final CoroutineContext collectContext;
    public final int collectContextSize;
    public final FlowCollector collector;
    public Continuation completion;
    public CoroutineContext lastEmissionContext;

    public SafeCollector(FlowCollector flowCollector, CoroutineContext coroutineContext) {
        super(NoOpContinuation.INSTANCE, EmptyCoroutineContext.INSTANCE);
        this.collector = flowCollector;
        this.collectContext = coroutineContext;
        this.collectContextSize = ((Number) coroutineContext.fold(0, SafeCollector$collectContextSize$1.INSTANCE)).intValue();
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        try {
            Object emit = emit(continuation, obj);
            return emit == CoroutineSingletons.COROUTINE_SUSPENDED ? emit : Unit.INSTANCE;
        } catch (Throwable th) {
            this.lastEmissionContext = new DownstreamExceptionContext(continuation.getContext(), th);
            throw th;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl, kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final CoroutineStackFrame getCallerFrame() {
        Continuation continuation = this.completion;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.ContinuationImpl, kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        CoroutineContext coroutineContext = this.lastEmissionContext;
        return coroutineContext == null ? EmptyCoroutineContext.INSTANCE : coroutineContext;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Throwable m292exceptionOrNullimpl = Result.m292exceptionOrNullimpl(obj);
        if (m292exceptionOrNullimpl != null) {
            this.lastEmissionContext = new DownstreamExceptionContext(getContext(), m292exceptionOrNullimpl);
        }
        Continuation continuation = this.completion;
        if (continuation != null) {
            continuation.resumeWith(obj);
        }
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlin.coroutines.jvm.internal.ContinuationImpl, kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final void releaseIntercepted() {
        super.releaseIntercepted();
    }

    public final Object emit(Continuation continuation, Object obj) {
        Comparable comparable;
        String str;
        CoroutineContext context = continuation.getContext();
        JobKt.ensureActive(context);
        CoroutineContext coroutineContext = this.lastEmissionContext;
        if (coroutineContext != context) {
            int i = 0;
            if (coroutineContext instanceof DownstreamExceptionContext) {
                String str2 = "\n            Flow exception transparency is violated:\n                Previous 'emit' call has thrown exception " + ((DownstreamExceptionContext) coroutineContext).e + ", but then emission attempt of value '" + obj + "' has been detected.\n                Emissions from 'catch' blocks are prohibited in order to avoid unspecified behaviour, 'Flow.catch' operator can be used instead.\n                For a more detailed explanation, please refer to Flow documentation.\n            ";
                ResultKt.checkNotNullParameter(str2, "<this>");
                List asList = Arrays.asList("\r\n", "\n", "\r");
                ResultKt.checkNotNullExpressionValue(asList, "asList(this)");
                List list = SequencesKt.toList(new GeneratorSequence(new DelimitedRangesSequence(str2, 0, 0, new StringsKt__StringsKt$rangesDelimitedBy$2(0, asList, false)), new AbstractMap$toString$1(22, str2)));
                ArrayList arrayList = new ArrayList();
                for (Object obj2 : list) {
                    if (true ^ StringsKt__StringsKt.isBlank((String) obj2)) {
                        arrayList.add(obj2);
                    }
                }
                ArrayList arrayList2 = new ArrayList(MapsKt___MapsJvmKt.collectionSizeOrDefault(arrayList));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    String str3 = (String) it.next();
                    int length = str3.length();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            i2 = -1;
                            break;
                        }
                        char charAt = str3.charAt(i2);
                        if (!(Character.isWhitespace(charAt) || Character.isSpaceChar(charAt))) {
                            break;
                        }
                        i2++;
                    }
                    if (i2 == -1) {
                        i2 = str3.length();
                    }
                    arrayList2.add(Integer.valueOf(i2));
                }
                Iterator it2 = arrayList2.iterator();
                if (it2.hasNext()) {
                    comparable = (Comparable) it2.next();
                    while (it2.hasNext()) {
                        Comparable comparable2 = (Comparable) it2.next();
                        if (comparable.compareTo(comparable2) > 0) {
                            comparable = comparable2;
                        }
                    }
                } else {
                    comparable = null;
                }
                Integer num = (Integer) comparable;
                int intValue = num != null ? num.intValue() : 0;
                int length2 = str2.length();
                list.size();
                int lastIndex = ResultKt.getLastIndex(list);
                ArrayList arrayList3 = new ArrayList();
                for (Object obj3 : list) {
                    int i3 = i + 1;
                    if (i >= 0) {
                        String str4 = (String) obj3;
                        if ((i == 0 || i == lastIndex) && StringsKt__StringsKt.isBlank(str4)) {
                            str = null;
                        } else {
                            ResultKt.checkNotNullParameter(str4, "<this>");
                            if (intValue < 0) {
                                throw new IllegalArgumentException(("Requested character count " + intValue + " is less than zero.").toString());
                            }
                            int length3 = str4.length();
                            if (intValue <= length3) {
                                length3 = intValue;
                            }
                            str = str4.substring(length3);
                            ResultKt.checkNotNullExpressionValue(str, "this as java.lang.String).substring(startIndex)");
                        }
                        if (str != null) {
                            arrayList3.add(str);
                        }
                        i = i3;
                    } else {
                        throw new ArithmeticException("Index overflow has happened.");
                    }
                }
                StringBuilder sb = new StringBuilder(length2);
                CollectionsKt___CollectionsKt.joinTo(arrayList3, sb, "\n", "", "", -1, "...", null);
                String sb2 = sb.toString();
                ResultKt.checkNotNullExpressionValue(sb2, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
                throw new IllegalStateException(sb2.toString());
            }
            if (((Number) context.fold(0, new ComposedModifierKt$materialize$result$1(8, this))).intValue() == this.collectContextSize) {
                this.lastEmissionContext = context;
            } else {
                throw new IllegalStateException(("Flow invariant is violated:\n\t\tFlow was collected in " + this.collectContext + ",\n\t\tbut emission happened in " + context + ".\n\t\tPlease refer to 'flow' documentation or use 'flowOn' instead").toString());
            }
        }
        this.completion = continuation;
        Function3 function3 = SafeCollectorKt.emitFun;
        FlowCollector flowCollector = this.collector;
        ResultKt.checkNotNull(flowCollector, "null cannot be cast to non-null type kotlinx.coroutines.flow.FlowCollector<kotlin.Any?>");
        Object invoke = function3.invoke(flowCollector, obj, this);
        if (!ResultKt.areEqual(invoke, CoroutineSingletons.COROUTINE_SUSPENDED)) {
            this.completion = null;
        }
        return invoke;
    }
}
