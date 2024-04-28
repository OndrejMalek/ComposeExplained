package androidx.compose.foundation;

import androidx.compose.ui.draw.DrawModifier;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class IndicationModifier implements DrawModifier {
    public final IndicationInstance indicationInstance;

    public IndicationModifier(IndicationInstance indicationInstance) {
        ResultKt.checkNotNullParameter(indicationInstance, "indicationInstance");
        this.indicationInstance = indicationInstance;
    }
}
