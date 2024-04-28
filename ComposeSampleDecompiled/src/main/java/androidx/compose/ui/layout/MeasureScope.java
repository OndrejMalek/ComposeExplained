package androidx.compose.ui.layout;

import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.collections.EmptyMap;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public interface MeasureScope extends Density {
    static MeasureScope$layout$1 layout$default(MeasureScope measureScope, int i, int i2, Function1 function1) {
        EmptyMap emptyMap = EmptyMap.INSTANCE;
        measureScope.getClass();
        return new MeasureScope$layout$1(i, i2, emptyMap, measureScope, function1);
    }

    LayoutDirection getLayoutDirection();
}
