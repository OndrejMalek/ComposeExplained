package androidx.compose.ui.graphics;

/* loaded from: classes.dex */
public interface Canvas {
    /* renamed from: clipPath-mtrdD-E */
    void mo87clipPathmtrdDE(Path path, int i);

    /* renamed from: clipRect-N_I0leg */
    void mo88clipRectN_I0leg(float f, float f2, float f3, float f4, int i);

    /* renamed from: concat-58bKbWc */
    void mo89concat58bKbWc(float[] fArr);

    void disableZ();

    /* renamed from: drawCircle-9KIMszo */
    void mo90drawCircle9KIMszo(float f, long j, AndroidPaint androidPaint);

    void drawPath(Path path, AndroidPaint androidPaint);

    void drawRect(float f, float f2, float f3, float f4, AndroidPaint androidPaint);

    void drawRoundRect(float f, float f2, float f3, float f4, float f5, float f6, AndroidPaint androidPaint);

    void enableZ();

    void restore();

    void save();

    void translate(float f, float f2);
}
