package androidx.compose.foundation;

import _COROUTINE._BOUNDARY;
import androidx.compose.foundation.interaction.MutableInteractionSourceImpl;
import androidx.compose.material.ripple.PlatformRipple;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectableValueKt$NoInspectorInfo$1;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public abstract class BorderKt {
    /* renamed from: background-bw27NRU */
    public static final Modifier m26backgroundbw27NRU(Modifier modifier, long j, Shape shape) {
        ResultKt.checkNotNullParameter(modifier, "$this$background");
        ResultKt.checkNotNullParameter(shape, "shape");
        return modifier.then(new BackgroundElement(j, shape));
    }

    /* renamed from: clickable-O2vRcR0$default */
    public static Modifier m27clickableO2vRcR0$default(Modifier modifier, MutableInteractionSourceImpl mutableInteractionSourceImpl, PlatformRipple platformRipple, boolean z, Function0 function0) {
        ResultKt.checkNotNullParameter(modifier, "$this$clickable");
        ResultKt.checkNotNullParameter(mutableInteractionSourceImpl, "interactionSource");
        ResultKt.checkNotNullParameter(function0, "onClick");
        InspectableValueKt$NoInspectorInfo$1 inspectableValueKt$NoInspectorInfo$1 = InspectableValueKt$NoInspectorInfo$1.INSTANCE;
        Modifier modifier2 = Modifier.Companion.$$INSTANCE;
        StaticProvidableCompositionLocal staticProvidableCompositionLocal = IndicationKt.LocalIndication;
        Modifier then = _BOUNDARY.composed(modifier2, new IndicationKt$indication$2(platformRipple, 0, mutableInteractionSourceImpl)).then(z ? new HoverableElement(mutableInteractionSourceImpl) : modifier2);
        Function1 function1 = new Function1(z, mutableInteractionSourceImpl) { // from class: androidx.compose.foundation.FocusableKt$focusableInNonTouchMode$1
            public final /* synthetic */ Object $interactionSource;

            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                DurationKt$$ExternalSyntheticCheckNotZero0.m(obj);
                ResultKt.checkNotNullParameter(null, "$this$inspectable");
                throw null;
            }
        };
        FocusableKt$FocusableInNonTouchModeElement$1 focusableKt$FocusableInNonTouchModeElement$1 = FocusableKt.FocusableInNonTouchModeElement;
        ResultKt.checkNotNullParameter(focusableKt$FocusableInNonTouchModeElement$1, "other");
        if (z) {
            modifier2 = new FocusableElement(mutableInteractionSourceImpl).then(FocusTargetNode.FocusTargetElement.INSTANCE);
        }
        return InspectableValueKt.inspectableWrapper(modifier, inspectableValueKt$NoInspectorInfo$1, InspectableValueKt.inspectableWrapper(then, function1, focusableKt$FocusableInNonTouchModeElement$1.then(modifier2)).then(new ClickableElement(mutableInteractionSourceImpl, z, null, null, function0)));
    }

    /* renamed from: shrink-Kibmq7A */
    public static final long m28shrinkKibmq7A(float f, long j) {
        return _BOUNDARY.CornerRadius(Math.max(0.0f, CornerRadius.m72getXimpl(j) - f), Math.max(0.0f, CornerRadius.m73getYimpl(j) - f));
    }
}
