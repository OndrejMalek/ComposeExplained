package androidx.compose.runtime;

/* loaded from: classes.dex */
public final class Anchor {
    public int location;

    public Anchor(int i) {
        this.location = i;
    }

    public final boolean getValid() {
        return this.location != Integer.MIN_VALUE;
    }
}
