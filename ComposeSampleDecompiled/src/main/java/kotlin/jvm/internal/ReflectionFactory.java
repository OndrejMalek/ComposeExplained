package kotlin.jvm.internal;

/* loaded from: classes.dex */
public final class ReflectionFactory {
    public static String renderLambdaToString(FunctionBase functionBase) {
        String obj = functionBase.getClass().getGenericInterfaces()[0].toString();
        return obj.startsWith("kotlin.jvm.functions.") ? obj.substring(21) : obj;
    }
}
