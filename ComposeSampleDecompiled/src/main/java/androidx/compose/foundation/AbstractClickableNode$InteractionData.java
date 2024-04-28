package androidx.compose.foundation;

import androidx.compose.foundation.interaction.PressInteraction$Press;
import androidx.compose.ui.geometry.Offset;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public final class AbstractClickableNode$InteractionData {
    public PressInteraction$Press pressInteraction;
    public final LinkedHashMap currentKeyPressInteractions = new LinkedHashMap();
    public long centreOffset = Offset.Zero;
}
