package kotlin.jvm.internal;

import java.io.Serializable;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public abstract class Lambda implements FunctionBase, Serializable {
    public final int arity;

    public Lambda(int i) {
        this.arity = i;
    }

    @Override // kotlin.jvm.internal.FunctionBase
    public final int getArity() {
        return this.arity;
    }

    public final String toString() {
        Reflection.factory.getClass();
        String renderLambdaToString = ReflectionFactory.renderLambdaToString(this);
        ResultKt.checkNotNullExpressionValue(renderLambdaToString, "renderLambdaToString(this)");
        return renderLambdaToString;
    }
}
