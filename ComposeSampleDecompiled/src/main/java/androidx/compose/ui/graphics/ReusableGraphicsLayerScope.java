package androidx.compose.ui.graphics;

import androidx.compose.ui.unit.Density;

/* loaded from: classes.dex */
public final class ReusableGraphicsLayerScope implements Density {
    public float alpha;
    public long ambientShadowColor;
    public float cameraDistance;
    public boolean clip;
    public int compositingStrategy;
    public Density graphicsDensity;
    public float rotationX;
    public float rotationY;
    public float rotationZ;
    public float scaleX;
    public float scaleY;
    public float shadowElevation;
    public Shape shape;
    public long spotShadowColor;
    public long transformOrigin;
    public float translationX;
    public float translationY;

    @Override // androidx.compose.ui.unit.Density
    public final float getDensity() {
        return this.graphicsDensity.getDensity();
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getFontScale() {
        return this.graphicsDensity.getFontScale();
    }
}
