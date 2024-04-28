package androidx.compose.ui.platform;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Outline;
import androidx.compose.runtime.Stack;
import androidx.compose.ui.graphics.Path;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public interface DeviceRenderNode {
    void discardDisplayList();

    void drawInto(Canvas canvas);

    float getAlpha();

    int getBottom();

    boolean getClipToBounds();

    boolean getClipToOutline();

    float getElevation();

    boolean getHasDisplayList();

    int getHeight();

    int getLeft();

    void getMatrix(Matrix matrix);

    int getRight();

    int getTop();

    int getWidth();

    void offsetLeftAndRight(int i);

    void offsetTopAndBottom(int i);

    void record(Stack stack, Path path, Function1 function1);

    void setAlpha(float f);

    void setAmbientShadowColor(int i);

    void setCameraDistance(float f);

    void setClipToBounds(boolean z);

    void setClipToOutline(boolean z);

    /* renamed from: setCompositingStrategy-aDBOjCE, reason: not valid java name */
    void mo228setCompositingStrategyaDBOjCE(int i);

    void setElevation(float f);

    boolean setHasOverlappingRendering();

    void setOutline(Outline outline);

    void setPivotX(float f);

    void setPivotY(float f);

    boolean setPosition(int i, int i2, int i3, int i4);

    void setRenderEffect();

    void setRotationX(float f);

    void setRotationY(float f);

    void setRotationZ(float f);

    void setScaleX(float f);

    void setScaleY(float f);

    void setSpotShadowColor(int i);

    void setTranslationX(float f);

    void setTranslationY(float f);
}
