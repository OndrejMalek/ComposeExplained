package kotlin.sequences;

import androidx.compose.ui.node.LayoutNode$_foldedChildren$1;
import java.util.Iterator;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final class GeneratorSequence implements Sequence {
    public final /* synthetic */ int $r8$classId = 0;
    public final Object getInitialValue;
    public final Function1 getNextValue;

    public GeneratorSequence(Sequence sequence, Function1 function1) {
        this.getInitialValue = sequence;
        this.getNextValue = function1;
    }

    @Override // kotlin.sequences.Sequence
    public final Iterator iterator() {
        switch (this.$r8$classId) {
            case 0:
                return new GeneratorSequence$iterator$1(this);
            default:
                return new TransformingSequence$iterator$1(this);
        }
    }

    public GeneratorSequence(LayoutNode$_foldedChildren$1 layoutNode$_foldedChildren$1, Function1 function1) {
        this.getInitialValue = layoutNode$_foldedChildren$1;
        this.getNextValue = function1;
    }
}
