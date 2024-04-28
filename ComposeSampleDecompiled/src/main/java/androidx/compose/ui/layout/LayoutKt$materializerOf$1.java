package androidx.compose.ui.layout;

import _COROUTINE._BOUNDARY;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.ui.CompositionLocalMapInjectionElement;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.ComposeUiNode$Companion$SetDensity$1;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class LayoutKt$materializerOf$1 extends Lambda implements Function3 {
    public final /* synthetic */ Modifier $modifier;
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LayoutKt$materializerOf$1(Modifier modifier, int i) {
        super(3);
        this.$r8$classId = i;
        this.$modifier = modifier;
    }

    @Override // kotlin.jvm.functions.Function3
    public final /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                ((Number) obj3).intValue();
                m163invokeDeg8D_g(((SkippableUpdater) obj).composer, (Composer) obj2);
                return unit;
            default:
                ((Number) obj3).intValue();
                m163invokeDeg8D_g(((SkippableUpdater) obj).composer, (Composer) obj2);
                return unit;
        }
    }

    /* renamed from: invoke-Deg8D_g, reason: not valid java name */
    public final void m163invokeDeg8D_g(Composer composer, Composer composer2) {
        int i = this.$r8$classId;
        Modifier modifier = this.$modifier;
        switch (i) {
            case 0:
                ResultKt.checkNotNullParameter(composer, "$this$null");
                int i2 = ((ComposerImpl) composer2).compoundKeyHash;
                Modifier materializeModifier = _BOUNDARY.materializeModifier(composer2, modifier);
                ComposerImpl composerImpl = (ComposerImpl) composer;
                composerImpl.startReplaceableGroup(509942095);
                ComposeUiNode.Companion.getClass();
                ResultKt.m299setimpl(composer, materializeModifier, ComposeUiNode.Companion.SetModifier);
                ComposeUiNode$Companion$SetDensity$1 composeUiNode$Companion$SetDensity$1 = ComposeUiNode.Companion.SetCompositeKeyHash;
                if (composerImpl.inserting || !ResultKt.areEqual(composerImpl.nextSlot(), Integer.valueOf(i2))) {
                    composerImpl.updateValue(Integer.valueOf(i2));
                    composerImpl.apply(Integer.valueOf(i2), composeUiNode$Companion$SetDensity$1);
                }
                composerImpl.end(false);
                return;
            default:
                ResultKt.checkNotNullParameter(composer, "$this$null");
                ComposerImpl composerImpl2 = (ComposerImpl) composer2;
                int i3 = composerImpl2.compoundKeyHash;
                ResultKt.checkNotNullParameter(modifier, "modifier");
                if (modifier != Modifier.Companion.$$INSTANCE) {
                    modifier = _BOUNDARY.materializeModifier(composer2, new CompositionLocalMapInjectionElement(composerImpl2.currentCompositionLocalScope()).then(modifier));
                }
                ComposerImpl composerImpl3 = (ComposerImpl) composer;
                composerImpl3.startReplaceableGroup(509942095);
                ComposeUiNode.Companion.getClass();
                ResultKt.m299setimpl(composer, modifier, ComposeUiNode.Companion.SetModifier);
                ComposeUiNode$Companion$SetDensity$1 composeUiNode$Companion$SetDensity$12 = ComposeUiNode.Companion.SetCompositeKeyHash;
                if (composerImpl3.inserting || !ResultKt.areEqual(composerImpl3.nextSlot(), Integer.valueOf(i3))) {
                    composerImpl3.updateValue(Integer.valueOf(i3));
                    composerImpl3.apply(Integer.valueOf(i3), composeUiNode$Companion$SetDensity$12);
                }
                composerImpl3.end(false);
                return;
        }
    }
}
