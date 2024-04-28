package androidx.compose.runtime;

/* loaded from: classes.dex */
public interface Applier {
    void down(Object obj);

    void insertTopDown(int i, Object obj);

    void move(int i, int i2, int i3);

    void remove(int i, int i2);
}
