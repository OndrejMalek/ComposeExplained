package androidx.compose.foundation;

import android.view.KeyEvent;
import androidx.compose.foundation.interaction.MutableInteractionSourceImpl;
import androidx.compose.foundation.interaction.PressInteraction$Cancel;
import androidx.compose.foundation.interaction.PressInteraction$Press;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyInputModifierNode;
import androidx.compose.ui.input.key.Key_androidKt;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerEventPass;
import androidx.compose.ui.input.pointer.SuspendingPointerInputModifierNodeImpl;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.PointerInputModifierNode;
import androidx.compose.ui.semantics.Role;
import java.util.LinkedHashMap;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;

/* loaded from: classes.dex */
public final class ClickableNode extends DelegatingNode implements PointerInputModifierNode, KeyInputModifierNode {
    public final ClickablePointerInputNode clickablePointerInputNode;
    public final ClickableSemanticsNode clickableSemanticsNode;
    public boolean enabled;
    public final AbstractClickableNode$InteractionData interactionData;
    public MutableInteractionSourceImpl interactionSource;
    public Function0 onClick;

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: androidx.compose.foundation.ClickableNode */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [androidx.compose.ui.Modifier$Node, androidx.compose.foundation.ClickableSemanticsNode] */
    /* JADX WARN: Type inference failed for: r5v2, types: [androidx.compose.foundation.ClickablePointerInputNode, androidx.compose.foundation.AbstractClickablePointerInputNode, androidx.compose.ui.Modifier$Node] */
    public ClickableNode(MutableInteractionSourceImpl mutableInteractionSourceImpl, boolean z, String str, Role role, Function0 function0) {
        ResultKt.checkNotNullParameter(mutableInteractionSourceImpl, "interactionSource");
        ResultKt.checkNotNullParameter(function0, "onClick");
        this.interactionSource = mutableInteractionSourceImpl;
        this.enabled = z;
        this.onClick = function0;
        AbstractClickableNode$InteractionData abstractClickableNode$InteractionData = new AbstractClickableNode$InteractionData();
        this.interactionData = abstractClickableNode$InteractionData;
        ?? node = new Modifier.Node();
        node.enabled = z;
        node.onClickLabel = str;
        node.role = role;
        node.onClick = function0;
        node.onLongClickLabel = null;
        node.onLongClick = null;
        delegate(node);
        this.clickableSemanticsNode = node;
        ?? abstractClickablePointerInputNode = new AbstractClickablePointerInputNode(z, mutableInteractionSourceImpl, function0, abstractClickableNode$InteractionData);
        delegate(abstractClickablePointerInputNode);
        this.clickablePointerInputNode = abstractClickablePointerInputNode;
    }

    public final void disposeInteractionSource$1() {
        AbstractClickableNode$InteractionData abstractClickableNode$InteractionData = this.interactionData;
        PressInteraction$Press pressInteraction$Press = abstractClickableNode$InteractionData.pressInteraction;
        if (pressInteraction$Press != null) {
            this.interactionSource.interactions.tryEmit(new PressInteraction$Cancel(pressInteraction$Press));
        }
        LinkedHashMap linkedHashMap = abstractClickableNode$InteractionData.currentKeyPressInteractions;
        for (PressInteraction$Press pressInteraction$Press2 : linkedHashMap.values()) {
            MutableInteractionSourceImpl mutableInteractionSourceImpl = this.interactionSource;
            mutableInteractionSourceImpl.interactions.tryEmit(new PressInteraction$Cancel(pressInteraction$Press2));
        }
        abstractClickableNode$InteractionData.pressInteraction = null;
        linkedHashMap.clear();
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    public final void onCancelPointerInput() {
        this.clickablePointerInputNode.onCancelPointerInput();
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        disposeInteractionSource$1();
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* renamed from: onKeyEvent-ZmokQxo, reason: not valid java name */
    public final boolean mo29onKeyEventZmokQxo(KeyEvent keyEvent) {
        int m151getKeyZmokQxo;
        ResultKt.checkNotNullParameter(keyEvent, "event");
        boolean z = this.enabled;
        AbstractClickableNode$InteractionData abstractClickableNode$InteractionData = this.interactionData;
        if (z) {
            int i = Clickable_androidKt.$r8$clinit;
            if (Key_androidKt.m152getTypeZmokQxo(keyEvent) == 2 && ((m151getKeyZmokQxo = (int) (Key_androidKt.m151getKeyZmokQxo(keyEvent) >> 32)) == 23 || m151getKeyZmokQxo == 66 || m151getKeyZmokQxo == 160)) {
                if (abstractClickableNode$InteractionData.currentKeyPressInteractions.containsKey(new Key(Key_androidKt.m151getKeyZmokQxo(keyEvent)))) {
                    return false;
                }
                PressInteraction$Press pressInteraction$Press = new PressInteraction$Press(abstractClickableNode$InteractionData.centreOffset);
                abstractClickableNode$InteractionData.currentKeyPressInteractions.put(new Key(Key_androidKt.m151getKeyZmokQxo(keyEvent)), pressInteraction$Press);
                ResultKt.launch$default(getCoroutineScope(), null, 0, new AbstractClickableNode$onKeyEvent$1(this, pressInteraction$Press, null), 3);
                return true;
            }
        }
        if (!this.enabled) {
            return false;
        }
        int i2 = Clickable_androidKt.$r8$clinit;
        if (Key_androidKt.m152getTypeZmokQxo(keyEvent) != 1) {
            return false;
        }
        int m151getKeyZmokQxo2 = (int) (Key_androidKt.m151getKeyZmokQxo(keyEvent) >> 32);
        if (m151getKeyZmokQxo2 != 23 && m151getKeyZmokQxo2 != 66 && m151getKeyZmokQxo2 != 160) {
            return false;
        }
        PressInteraction$Press pressInteraction$Press2 = (PressInteraction$Press) abstractClickableNode$InteractionData.currentKeyPressInteractions.remove(new Key(Key_androidKt.m151getKeyZmokQxo(keyEvent)));
        if (pressInteraction$Press2 != null) {
            ResultKt.launch$default(getCoroutineScope(), null, 0, new AbstractClickableNode$onKeyEvent$2$1(this, pressInteraction$Press2, null), 3);
        }
        this.onClick.invoke();
        return true;
    }

    @Override // androidx.compose.ui.node.PointerInputModifierNode
    /* renamed from: onPointerEvent-H0pRuoY */
    public final void mo25onPointerEventH0pRuoY(PointerEvent pointerEvent, PointerEventPass pointerEventPass, long j) {
        ((SuspendingPointerInputModifierNodeImpl) this.clickablePointerInputNode.pointerInputNode).mo25onPointerEventH0pRuoY(pointerEvent, pointerEventPass, j);
    }

    @Override // androidx.compose.ui.input.key.KeyInputModifierNode
    /* renamed from: onPreKeyEvent-ZmokQxo, reason: not valid java name */
    public final boolean mo30onPreKeyEventZmokQxo(KeyEvent keyEvent) {
        ResultKt.checkNotNullParameter(keyEvent, "event");
        return false;
    }
}
