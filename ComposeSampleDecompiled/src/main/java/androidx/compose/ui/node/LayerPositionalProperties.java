package androidx.compose.ui.node;

import androidx.compose.ui.graphics.TransformOrigin;

/* loaded from: classes.dex */
public final class LayerPositionalProperties {
    public float rotationX;
    public float rotationY;
    public float rotationZ;
    public long transformOrigin;
    public float translationX;
    public float translationY;
    public float scaleX = 1.0f;
    public float scaleY = 1.0f;
    public float cameraDistance = 8.0f;

    public LayerPositionalProperties() {
        int i = TransformOrigin.$r8$clinit;
        this.transformOrigin = TransformOrigin.Center;
    }
}
