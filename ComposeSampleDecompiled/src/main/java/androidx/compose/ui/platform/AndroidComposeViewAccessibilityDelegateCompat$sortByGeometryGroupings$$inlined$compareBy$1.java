package androidx.compose.ui.platform;

import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import java.util.Comparator;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class AndroidComposeViewAccessibilityDelegateCompat$sortByGeometryGroupings$$inlined$compareBy$1 implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        SemanticsNode semanticsNode = (SemanticsNode) obj;
        SemanticsConfiguration config = semanticsNode.getConfig();
        SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.TraversalIndex;
        SemanticsNode semanticsNode2 = (SemanticsNode) obj2;
        return ResultKt.compareValues(Float.valueOf(config.contains(semanticsPropertyKey) ? ((Number) semanticsNode.getConfig().get(semanticsPropertyKey)).floatValue() : 0.0f), Float.valueOf(semanticsNode2.getConfig().contains(semanticsPropertyKey) ? ((Number) semanticsNode2.getConfig().get(semanticsPropertyKey)).floatValue() : 0.0f));
    }
}
