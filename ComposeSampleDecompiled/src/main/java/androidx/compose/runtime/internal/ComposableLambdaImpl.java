package androidx.compose.runtime.internal;

import androidx.compose.material3.TextKt$ProvideTextStyle$1;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.RecomposeScope;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.RecomposeScopeOwner;
import java.io.Serializable;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class ComposableLambdaImpl implements ComposableLambda {
    public Object _block;
    public final int key;
    public RecomposeScopeImpl scope;
    public ArrayList scopes;
    public final boolean tracked;

    public ComposableLambdaImpl(int i, boolean z) {
        this.key = i;
        this.tracked = z;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        int bitsForSlot;
        Composer composer = (Composer) obj;
        int intValue = ((Number) obj2).intValue();
        ResultKt.checkNotNullParameter(composer, "c");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(this.key);
        trackRead(composerImpl);
        if (composerImpl.changed(this)) {
            bitsForSlot = ResultKt.bitsForSlot(2, 0);
        } else {
            bitsForSlot = ResultKt.bitsForSlot(1, 0);
        }
        int i = intValue | bitsForSlot;
        Object obj3 = this._block;
        ResultKt.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Function2<@[ParameterName(name = 'c')] androidx.compose.runtime.Composer, @[ParameterName(name = 'changed')] kotlin.Int, kotlin.Any?>");
        ResultKt.beforeCheckcastToFunctionOfArity(2, obj3);
        Object invoke = ((Function2) obj3).invoke(composerImpl, Integer.valueOf(i));
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            ResultKt.beforeCheckcastToFunctionOfArity(2, this);
            endRestartGroup.block = this;
        }
        return invoke;
    }

    public final void trackRead(Composer composer) {
        RecomposeScopeImpl currentRecomposeScope$runtime_release;
        if (!this.tracked || (currentRecomposeScope$runtime_release = ((ComposerImpl) composer).getCurrentRecomposeScope$runtime_release()) == null) {
            return;
        }
        currentRecomposeScope$runtime_release.flags |= 1;
        if (ResultKt.replacableWith(this.scope, currentRecomposeScope$runtime_release)) {
            this.scope = currentRecomposeScope$runtime_release;
            return;
        }
        ArrayList arrayList = this.scopes;
        if (arrayList == null) {
            ArrayList arrayList2 = new ArrayList();
            this.scopes = arrayList2;
            arrayList2.add(currentRecomposeScope$runtime_release);
            return;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (ResultKt.replacableWith((RecomposeScope) arrayList.get(i), currentRecomposeScope$runtime_release)) {
                arrayList.set(i, currentRecomposeScope$runtime_release);
                return;
            }
        }
        arrayList.add(currentRecomposeScope$runtime_release);
    }

    public final void update(Lambda lambda) {
        if (ResultKt.areEqual(this._block, lambda)) {
            return;
        }
        boolean z = this._block == null;
        this._block = lambda;
        if (z || !this.tracked) {
            return;
        }
        RecomposeScopeImpl recomposeScopeImpl = this.scope;
        if (recomposeScopeImpl != null) {
            RecomposeScopeOwner recomposeScopeOwner = recomposeScopeImpl.owner;
            if (recomposeScopeOwner != null) {
                recomposeScopeOwner.invalidate(recomposeScopeImpl, null);
            }
            this.scope = null;
        }
        ArrayList arrayList = this.scopes;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) ((RecomposeScope) arrayList.get(i));
                RecomposeScopeOwner recomposeScopeOwner2 = recomposeScopeImpl2.owner;
                if (recomposeScopeOwner2 != null) {
                    recomposeScopeOwner2.invalidate(recomposeScopeImpl2, null);
                }
            }
            arrayList.clear();
        }
    }

    @Override // kotlin.jvm.functions.Function3
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return invoke(obj, (Composer) obj2, ((Number) obj3).intValue());
    }

    @Override // kotlin.jvm.functions.Function4
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        return invoke(obj, obj2, (Composer) obj3, ((Number) obj4).intValue());
    }

    @Override // kotlin.jvm.functions.Function5
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Serializable serializable) {
        return invoke(obj, obj2, obj3, (Composer) obj4, ((Number) serializable).intValue());
    }

    public final Object invoke(Object obj, Composer composer, int i) {
        int bitsForSlot;
        ResultKt.checkNotNullParameter(composer, "c");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(this.key);
        trackRead(composerImpl);
        if (composerImpl.changed(this)) {
            bitsForSlot = ResultKt.bitsForSlot(2, 1);
        } else {
            bitsForSlot = ResultKt.bitsForSlot(1, 1);
        }
        Object obj2 = this._block;
        ResultKt.checkNotNull(obj2, "null cannot be cast to non-null type kotlin.Function3<@[ParameterName(name = 'p1')] kotlin.Any?, @[ParameterName(name = 'c')] androidx.compose.runtime.Composer, @[ParameterName(name = 'changed')] kotlin.Int, kotlin.Any?>");
        int i2 = 3;
        ResultKt.beforeCheckcastToFunctionOfArity(3, obj2);
        Object invoke = ((Function3) obj2).invoke(obj, composerImpl, Integer.valueOf(bitsForSlot | i));
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new TextKt$ProvideTextStyle$1(i, i2, this, obj);
        }
        return invoke;
    }

    public final Object invoke(Object obj, Object obj2, Composer composer, int i) {
        int bitsForSlot;
        ResultKt.checkNotNullParameter(composer, "c");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(this.key);
        trackRead(composerImpl);
        if (composerImpl.changed(this)) {
            bitsForSlot = ResultKt.bitsForSlot(2, 2);
        } else {
            bitsForSlot = ResultKt.bitsForSlot(1, 2);
        }
        Object obj3 = this._block;
        ResultKt.checkNotNull(obj3, "null cannot be cast to non-null type kotlin.Function4<@[ParameterName(name = 'p1')] kotlin.Any?, @[ParameterName(name = 'p2')] kotlin.Any?, @[ParameterName(name = 'c')] androidx.compose.runtime.Composer, @[ParameterName(name = 'changed')] kotlin.Int, kotlin.Any?>");
        ResultKt.beforeCheckcastToFunctionOfArity(4, obj3);
        Object invoke = ((Function4) obj3).invoke(obj, obj2, composerImpl, Integer.valueOf(bitsForSlot | i));
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new ComposableLambdaImpl$invoke$2(this, obj, obj2, i, 0);
        }
        return invoke;
    }

    public final Object invoke(final Object obj, final Object obj2, final Object obj3, Composer composer, final int i) {
        int bitsForSlot;
        ResultKt.checkNotNullParameter(composer, "c");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(this.key);
        trackRead(composerImpl);
        if (composerImpl.changed(this)) {
            bitsForSlot = ResultKt.bitsForSlot(2, 3);
        } else {
            bitsForSlot = ResultKt.bitsForSlot(1, 3);
        }
        Object obj4 = this._block;
        ResultKt.checkNotNull(obj4, "null cannot be cast to non-null type kotlin.Function5<@[ParameterName(name = 'p1')] kotlin.Any?, @[ParameterName(name = 'p2')] kotlin.Any?, @[ParameterName(name = 'p3')] kotlin.Any?, @[ParameterName(name = 'c')] androidx.compose.runtime.Composer, @[ParameterName(name = 'changed')] kotlin.Int, kotlin.Any?>");
        ResultKt.beforeCheckcastToFunctionOfArity(5, obj4);
        Object invoke = ((Function5) obj4).invoke(obj, obj2, obj3, composerImpl, Integer.valueOf(bitsForSlot | i));
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.block = new Function2() { // from class: androidx.compose.runtime.internal.ComposableLambdaImpl$invoke$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj5, Object obj6) {
                    Composer composer2 = (Composer) obj5;
                    ((Number) obj6).intValue();
                    ResultKt.checkNotNullParameter(composer2, "nc");
                    int updateChangedFlags = ResultKt.updateChangedFlags(i) | 1;
                    ComposableLambdaImpl.this.invoke(obj, obj2, obj3, composer2, updateChangedFlags);
                    return Unit.INSTANCE;
                }
            };
        }
        return invoke;
    }
}
