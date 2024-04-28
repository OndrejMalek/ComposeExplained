package androidx.compose.foundation;

import _COROUTINE.ArtificialStackFrames;
import androidx.compose.foundation.interaction.FocusInteractionKt$collectIsFocusedAsState$1$1;
import androidx.compose.foundation.interaction.HoverInteractionKt$collectIsHoveredAsState$1$1;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.PressInteractionKt$collectIsPressedAsState$1$1;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.runtime.StructuralEqualityPolicy;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function2;

/* loaded from: classes.dex */
public final class DefaultDebugIndication implements Indication, IndicationInstance {
    public static final DefaultDebugIndication INSTANCE = new Object();
    public static final DefaultDebugIndication INSTANCE$1 = new Object();

    /* loaded from: classes.dex */
    public final class DefaultDebugIndicationInstance implements IndicationInstance {
        public final State isFocused;
        public final State isHovered;
        public final State isPressed;

        public DefaultDebugIndicationInstance(MutableState mutableState, MutableState mutableState2, MutableState mutableState3) {
            ResultKt.checkNotNullParameter(mutableState, "isPressed");
            ResultKt.checkNotNullParameter(mutableState2, "isHovered");
            ResultKt.checkNotNullParameter(mutableState3, "isFocused");
            this.isPressed = mutableState;
            this.isHovered = mutableState2;
            this.isFocused = mutableState3;
        }

        @Override // androidx.compose.foundation.IndicationInstance
        public final void drawIndication(ContentDrawScope contentDrawScope) {
            long Color;
            long Color2;
            ResultKt.checkNotNullParameter(contentDrawScope, "<this>");
            LayoutNodeDrawScope layoutNodeDrawScope = (LayoutNodeDrawScope) contentDrawScope;
            layoutNodeDrawScope.drawContent();
            boolean booleanValue = ((Boolean) this.isPressed.getValue()).booleanValue();
            CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
            if (booleanValue) {
                Color2 = Brush.Color(Color.m126getRedimpl(r1), Color.m125getGreenimpl(r1), Color.m123getBlueimpl(r1), 0.3f, Color.m124getColorSpaceimpl(Color.Black));
                DrawScope.m145drawRectnJ9OG0$default(contentDrawScope, Color2, canvasDrawScope.mo149getSizeNHjbRc(), 122);
            } else if (((Boolean) this.isHovered.getValue()).booleanValue() || ((Boolean) this.isFocused.getValue()).booleanValue()) {
                Color = Brush.Color(Color.m126getRedimpl(r1), Color.m125getGreenimpl(r1), Color.m123getBlueimpl(r1), 0.1f, Color.m124getColorSpaceimpl(Color.Black));
                DrawScope.m145drawRectnJ9OG0$default(contentDrawScope, Color, canvasDrawScope.mo149getSizeNHjbRc(), 122);
            }
        }
    }

    @Override // androidx.compose.foundation.IndicationInstance
    public void drawIndication(ContentDrawScope contentDrawScope) {
        ResultKt.checkNotNullParameter(contentDrawScope, "<this>");
        ((LayoutNodeDrawScope) contentDrawScope).drawContent();
    }

    @Override // androidx.compose.foundation.Indication
    public IndicationInstance rememberUpdatedInstance(InteractionSource interactionSource, Composer composer) {
        ResultKt.checkNotNullParameter(interactionSource, "interactionSource");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(1683566979);
        composerImpl.startReplaceableGroup(-1692965168);
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot = composerImpl.nextSlot();
        ArtificialStackFrames artificialStackFrames = Composer.Companion.Empty;
        StructuralEqualityPolicy structuralEqualityPolicy = StructuralEqualityPolicy.INSTANCE;
        if (nextSlot == artificialStackFrames) {
            nextSlot = ResultKt.mutableStateOf(Boolean.FALSE, structuralEqualityPolicy);
            composerImpl.updateValue(nextSlot);
        }
        composerImpl.end(false);
        MutableState mutableState = (MutableState) nextSlot;
        composerImpl.startReplaceableGroup(511388516);
        boolean changed = composerImpl.changed(interactionSource) | composerImpl.changed(mutableState);
        Object nextSlot2 = composerImpl.nextSlot();
        if (changed || nextSlot2 == artificialStackFrames) {
            nextSlot2 = new PressInteractionKt$collectIsPressedAsState$1$1(interactionSource, mutableState, null);
            composerImpl.updateValue(nextSlot2);
        }
        composerImpl.end(false);
        EffectsKt.LaunchedEffect(interactionSource, (Function2) nextSlot2, composerImpl);
        composerImpl.end(false);
        composerImpl.startReplaceableGroup(1206586544);
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot3 = composerImpl.nextSlot();
        if (nextSlot3 == artificialStackFrames) {
            nextSlot3 = ResultKt.mutableStateOf(Boolean.FALSE, structuralEqualityPolicy);
            composerImpl.updateValue(nextSlot3);
        }
        composerImpl.end(false);
        MutableState mutableState2 = (MutableState) nextSlot3;
        composerImpl.startReplaceableGroup(511388516);
        boolean changed2 = composerImpl.changed(interactionSource) | composerImpl.changed(mutableState2);
        Object nextSlot4 = composerImpl.nextSlot();
        if (changed2 || nextSlot4 == artificialStackFrames) {
            nextSlot4 = new HoverInteractionKt$collectIsHoveredAsState$1$1(interactionSource, mutableState2, null);
            composerImpl.updateValue(nextSlot4);
        }
        composerImpl.end(false);
        EffectsKt.LaunchedEffect(interactionSource, (Function2) nextSlot4, composerImpl);
        composerImpl.end(false);
        composerImpl.startReplaceableGroup(-1805515472);
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot5 = composerImpl.nextSlot();
        if (nextSlot5 == artificialStackFrames) {
            nextSlot5 = ResultKt.mutableStateOf(Boolean.FALSE, structuralEqualityPolicy);
            composerImpl.updateValue(nextSlot5);
        }
        composerImpl.end(false);
        MutableState mutableState3 = (MutableState) nextSlot5;
        composerImpl.startReplaceableGroup(511388516);
        boolean changed3 = composerImpl.changed(interactionSource) | composerImpl.changed(mutableState3);
        Object nextSlot6 = composerImpl.nextSlot();
        if (changed3 || nextSlot6 == artificialStackFrames) {
            nextSlot6 = new FocusInteractionKt$collectIsFocusedAsState$1$1(interactionSource, mutableState3, null);
            composerImpl.updateValue(nextSlot6);
        }
        composerImpl.end(false);
        EffectsKt.LaunchedEffect(interactionSource, (Function2) nextSlot6, composerImpl);
        composerImpl.end(false);
        composerImpl.startReplaceableGroup(1157296644);
        boolean changed4 = composerImpl.changed(interactionSource);
        Object nextSlot7 = composerImpl.nextSlot();
        if (changed4 || nextSlot7 == artificialStackFrames) {
            nextSlot7 = new DefaultDebugIndicationInstance(mutableState, mutableState2, mutableState3);
            composerImpl.updateValue(nextSlot7);
        }
        composerImpl.end(false);
        DefaultDebugIndicationInstance defaultDebugIndicationInstance = (DefaultDebugIndicationInstance) nextSlot7;
        composerImpl.end(false);
        return defaultDebugIndicationInstance;
    }
}
