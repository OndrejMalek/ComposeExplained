package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;

/* loaded from: classes.dex */
public abstract class ModifierNodeElement implements Modifier.Element {
    public abstract Modifier.Node create();

    public abstract void update(Modifier.Node node);
}
