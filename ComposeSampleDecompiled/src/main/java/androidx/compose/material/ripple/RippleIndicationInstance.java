package androidx.compose.material.ripple;

import androidx.compose.animation.core.Animatable;
import androidx.compose.foundation.IndicationInstance;
import androidx.compose.foundation.interaction.PressInteraction$Press;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope$drawContext$1;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.emoji2.text.EmojiProcessor;
import kotlin.ResultKt;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes.dex */
public abstract class RippleIndicationInstance implements IndicationInstance {
    public final EmojiProcessor stateLayer;

    public RippleIndicationInstance(boolean z, MutableState mutableState) {
        this.stateLayer = new EmojiProcessor(z, mutableState);
    }

    public abstract void addRipple(PressInteraction$Press pressInteraction$Press, CoroutineScope coroutineScope);

    /* renamed from: drawStateLayer-H2RKhps, reason: not valid java name */
    public final void m48drawStateLayerH2RKhps(DrawScope drawScope, float f, long j) {
        long Color;
        ResultKt.checkNotNullParameter(drawScope, "$this$drawStateLayer");
        EmojiProcessor emojiProcessor = this.stateLayer;
        emojiProcessor.getClass();
        float m44getRippleEndRadiuscSwnlzA = Float.isNaN(f) ? RippleAnimationKt.m44getRippleEndRadiuscSwnlzA(drawScope, emojiProcessor.mUseEmojiAsDefaultStyle, drawScope.mo149getSizeNHjbRc()) : drawScope.mo32toPx0680j_4(f);
        float floatValue = ((Number) ((Animatable) emojiProcessor.mMetadataRepo).getValue()).floatValue();
        if (floatValue > 0.0f) {
            Color = Brush.Color(Color.m126getRedimpl(j), Color.m125getGreenimpl(j), Color.m123getBlueimpl(j), floatValue, Color.m124getColorSpaceimpl(j));
            if (!emojiProcessor.mUseEmojiAsDefaultStyle) {
                drawScope.mo137drawCircleVaOC9Bg(Color, m44getRippleEndRadiuscSwnlzA, (r18 & 4) != 0 ? drawScope.mo148getCenterF1C5BW0() : 0L, 1.0f, Fill.INSTANCE, null, 3);
                return;
            }
            float m85getWidthimpl = Size.m85getWidthimpl(drawScope.mo149getSizeNHjbRc());
            float m83getHeightimpl = Size.m83getHeightimpl(drawScope.mo149getSizeNHjbRc());
            CanvasDrawScope$drawContext$1 drawContext = drawScope.getDrawContext();
            long j2 = drawContext.this$0.drawParams.size;
            drawContext.getCanvas().save();
            drawContext.transform.$this_asDrawTransform.getCanvas().mo88clipRectN_I0leg(0.0f, 0.0f, m85getWidthimpl, m83getHeightimpl, 1);
            drawScope.mo137drawCircleVaOC9Bg(Color, m44getRippleEndRadiuscSwnlzA, (r18 & 4) != 0 ? drawScope.mo148getCenterF1C5BW0() : 0L, 1.0f, Fill.INSTANCE, null, 3);
            drawContext.getCanvas().restore();
            drawContext.this$0.drawParams.size = j2;
        }
    }

    public abstract void removeRipple(PressInteraction$Press pressInteraction$Press);
}
