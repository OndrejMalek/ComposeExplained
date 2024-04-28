package kotlin.coroutines.jvm.internal;

import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.ReflectionFactory;

/* loaded from: classes.dex */
public abstract class SuspendLambda extends ContinuationImpl implements FunctionBase {
    public final int arity;

    public SuspendLambda(int i, Continuation continuation) {
        super(continuation);
        this.arity = i;
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public final int getArity() {
        return this.arity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final String toString() {
        if (this.completion != null) {
            return super.toString();
        }
        Reflection.factory.getClass();
        String renderLambdaToString = ReflectionFactory.renderLambdaToString(this);
        ResultKt.checkNotNullExpressionValue(renderLambdaToString, "renderLambdaToString(this)");
        return renderLambdaToString;
    }
}
