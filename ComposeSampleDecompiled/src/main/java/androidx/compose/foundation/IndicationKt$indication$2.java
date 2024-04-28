package androidx.compose.foundation;

import android.graphics.Typeface;
import android.text.Spannable;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.android.style.TypefaceSpan;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class IndicationKt$indication$2 extends Lambda implements Function3 {
    public final /* synthetic */ Object $indication;
    public final /* synthetic */ Object $interactionSource;
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ IndicationKt$indication$2(Object obj, int i, Object obj2) {
        super(3);
        this.$r8$classId = i;
        this.$indication = obj;
        this.$interactionSource = obj2;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int i = this.$r8$classId;
        Object obj4 = this.$interactionSource;
        Object obj5 = this.$indication;
        switch (i) {
            case 0:
                ((Number) obj3).intValue();
                ResultKt.checkNotNullParameter((Modifier) obj, "$this$composed");
                ComposerImpl composerImpl = (ComposerImpl) ((Composer) obj2);
                composerImpl.startReplaceableGroup(-353972293);
                Indication indication = (Indication) obj5;
                if (indication == null) {
                    indication = NoIndication.INSTANCE;
                }
                IndicationInstance rememberUpdatedInstance = indication.rememberUpdatedInstance((InteractionSource) obj4, composerImpl);
                composerImpl.startReplaceableGroup(1157296644);
                boolean changed = composerImpl.changed(rememberUpdatedInstance);
                Object nextSlot = composerImpl.nextSlot();
                if (changed || nextSlot == Composer.Companion.Empty) {
                    nextSlot = new IndicationModifier(rememberUpdatedInstance);
                    composerImpl.updateValue(nextSlot);
                }
                composerImpl.end(false);
                IndicationModifier indicationModifier = (IndicationModifier) nextSlot;
                composerImpl.end(false);
                return indicationModifier;
            default:
                SpanStyle spanStyle = (SpanStyle) obj;
                int intValue = ((Number) obj2).intValue();
                int intValue2 = ((Number) obj3).intValue();
                ResultKt.checkNotNullParameter(spanStyle, "spanStyle");
                Spannable spannable = (Spannable) obj5;
                Function4 function4 = (Function4) obj4;
                FontWeight fontWeight = spanStyle.fontWeight;
                if (fontWeight == null) {
                    fontWeight = FontWeight.Normal;
                }
                FontStyle fontStyle = spanStyle.fontStyle;
                FontStyle fontStyle2 = new FontStyle(fontStyle != null ? fontStyle.value : 0);
                FontSynthesis fontSynthesis = spanStyle.fontSynthesis;
                spannable.setSpan(new TypefaceSpan((Typeface) function4.invoke(spanStyle.fontFamily, fontWeight, fontStyle2, new FontSynthesis(fontSynthesis != null ? fontSynthesis.value : 1))), intValue, intValue2, 33);
                return Unit.INSTANCE;
        }
    }
}
