package androidx.compose.ui.text.input;

import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.Reflection;

/* loaded from: classes.dex */
public final class FinishComposingTextCommand implements EditCommand {
    public final boolean equals(Object obj) {
        return obj instanceof FinishComposingTextCommand;
    }

    public final int hashCode() {
        Reflection.factory.getClass();
        return new ClassReference(FinishComposingTextCommand.class).hashCode();
    }

    public final String toString() {
        return "FinishComposingTextCommand()";
    }
}
