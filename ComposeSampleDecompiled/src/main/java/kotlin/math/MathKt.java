package kotlin.math;

import androidx.compose.ui.node.LayoutNode$_foldedChildren$1;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.EmptySequence;
import kotlin.sequences.GeneratorSequence;
import kotlin.sequences.Sequence;

/* loaded from: classes.dex */
public abstract class MathKt extends ResultKt {
    public static Sequence generateSequence(Object obj, Function1 function1) {
        return obj == null ? EmptySequence.INSTANCE : new GeneratorSequence(new LayoutNode$_foldedChildren$1(19, obj), function1);
    }
}
