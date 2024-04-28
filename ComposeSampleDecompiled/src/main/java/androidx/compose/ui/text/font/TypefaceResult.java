package androidx.compose.ui.text.font;

import androidx.compose.runtime.State;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public interface TypefaceResult extends State {

    /* loaded from: classes.dex */
    public final class Immutable implements TypefaceResult {
        public final boolean cacheable;
        public final Object value;

        public Immutable(Object obj, boolean z) {
            ResultKt.checkNotNullParameter(obj, "value");
            this.value = obj;
            this.cacheable = z;
        }

        @Override // androidx.compose.runtime.State
        public final Object getValue() {
            return this.value;
        }
    }
}
