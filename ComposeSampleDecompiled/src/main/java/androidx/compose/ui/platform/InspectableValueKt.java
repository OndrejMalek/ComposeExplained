package androidx.compose.ui.platform;

import androidx.compose.ui.Modifier;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public abstract class InspectableValueKt {
    public static boolean isDebugInspectorInfoEnabled;

    public static final Modifier inspectableWrapper(Modifier modifier, Function1 function1, Modifier modifier2) {
        ResultKt.checkNotNullParameter(modifier, "<this>");
        ResultKt.checkNotNullParameter(modifier2, "wrapped");
        InspectableModifier inspectableModifier = new InspectableModifier(function1);
        return modifier.then(inspectableModifier).then(modifier2).then(inspectableModifier.end);
    }
}
