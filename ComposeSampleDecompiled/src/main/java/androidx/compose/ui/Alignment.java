package androidx.compose.ui;

import androidx.compose.ui.BiasAlignment;
import kotlin.coroutines.CoroutineContext;

/* loaded from: classes.dex */
public interface Alignment {

    /* loaded from: classes.dex */
    public final class Companion implements CoroutineContext.Key {
        public static final BiasAlignment TopStart = new BiasAlignment(-1.0f, -1.0f);
        public static final BiasAlignment Center = new BiasAlignment(0.0f, 0.0f);
        public static final BiasAlignment.Vertical Top = new BiasAlignment.Vertical(-1.0f);
        public static final BiasAlignment.Vertical CenterVertically = new BiasAlignment.Vertical(0.0f);
        public static final BiasAlignment.Horizontal Start = new BiasAlignment.Horizontal(-1.0f);
        public static final BiasAlignment.Horizontal CenterHorizontally = new BiasAlignment.Horizontal(0.0f);
        public static final /* synthetic */ Companion $$INSTANCE = new Object();
    }

    /* loaded from: classes.dex */
    public interface Horizontal {
    }

    /* loaded from: classes.dex */
    public interface Vertical {
    }
}
