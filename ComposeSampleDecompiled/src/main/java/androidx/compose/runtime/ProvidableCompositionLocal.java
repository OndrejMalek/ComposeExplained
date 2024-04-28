package androidx.compose.runtime;

/* loaded from: classes.dex */
public abstract class ProvidableCompositionLocal extends CompositionLocal {
    public final ProvidedValue provides(Object obj) {
        return new ProvidedValue(this, obj, true);
    }
}
