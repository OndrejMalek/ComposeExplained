package androidx.compose.ui;

import androidx.compose.ui.Alignment;
import kotlin.coroutines.CoroutineContext;

/* loaded from: classes.dex */
public interface MotionDurationScale extends CoroutineContext.Element {
    @Override // kotlin.coroutines.CoroutineContext.Element
    default CoroutineContext.Key getKey() {
        return Alignment.Companion.$$INSTANCE;
    }

    float getScaleFactor();
}
