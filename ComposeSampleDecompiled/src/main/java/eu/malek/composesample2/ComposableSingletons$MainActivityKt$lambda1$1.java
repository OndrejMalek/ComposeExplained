package eu.malek.composesample2;

import _COROUTINE._BOUNDARY;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.Arrangement$Top$1;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.CrossAxisAlignment$VerticalCrossAxisAlignment;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowColumnImplKt$rowColumnMeasurePolicy$1;
import androidx.compose.foundation.layout.RowKt$rowMeasurePolicy$1$1;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.ColorSchemeKt;
import androidx.compose.material3.ContentColorKt;
import androidx.compose.material3.SurfaceKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.DynamicProvidableCompositionLocal;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PersistentCompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.StaticProvidableCompositionLocal;
import androidx.compose.runtime.StructuralEqualityPolicy;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.RectangleShapeKt$RectangleShape$1;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendPointerInputElement;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutKt$materializerOf$1;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.ComposeUiNode$Companion$SetDensity$1;
import androidx.compose.ui.node.LayoutNode$Companion$Constructor$1;
import androidx.compose.ui.node.LayoutNode$_foldedChildren$1;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.AppendedSemanticsElement;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import eu.malek.composesample2.DataPackage;
import eu.malek.composesample2.ui.theme.ThemeKt;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KProperty;

/* renamed from: eu.malek.composesample2.ComposableSingletons$MainActivityKt$lambda-1$1, reason: invalid class name */
/* loaded from: classes.dex */
public final class ComposableSingletons$MainActivityKt$lambda1$1 extends Lambda implements Function2 {
    public static final ComposableSingletons$MainActivityKt$lambda1$1 INSTANCE = new ComposableSingletons$MainActivityKt$lambda1$1(0);
    public static final ComposableSingletons$MainActivityKt$lambda1$1 INSTANCE$1 = new ComposableSingletons$MainActivityKt$lambda1$1(1);
    public static final ComposableSingletons$MainActivityKt$lambda1$1 INSTANCE$2 = new ComposableSingletons$MainActivityKt$lambda1$1(2);
    public static final ComposableSingletons$MainActivityKt$lambda1$1 INSTANCE$3 = new ComposableSingletons$MainActivityKt$lambda1$1(3);
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ComposableSingletons$MainActivityKt$lambda1$1(int i) {
        super(2);
        this.$r8$classId = i;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                invoke((Composer) obj, ((Number) obj2).intValue());
                return unit;
            case 1:
                invoke((Composer) obj, ((Number) obj2).intValue());
                return unit;
            case 2:
                invoke((Composer) obj, ((Number) obj2).intValue());
                return unit;
            default:
                invoke((Composer) obj, ((Number) obj2).intValue());
                return unit;
        }
    }

    /* JADX WARN: Type inference failed for: r2v11, types: [kotlinx.coroutines.sync.MutexImpl$onSelectCancellationUnlockConstructor$1, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r3v79, types: [androidx.compose.material3.SurfaceKt$Surface$1, kotlin.jvm.internal.Lambda] */
    public final void invoke(Composer composer, int i) {
        Object obj;
        long j;
        int i2 = 0;
        switch (this.$r8$classId) {
            case 0:
                if ((i & 11) == 2) {
                    ComposerImpl composerImpl = (ComposerImpl) composer;
                    if (composerImpl.getSkipping()) {
                        composerImpl.skipToGroupEnd();
                        return;
                    }
                }
                ComposerImpl composerImpl2 = (ComposerImpl) composer;
                composerImpl2.startReplaceableGroup(-492369756);
                Object nextSlot = composerImpl2.nextSlot();
                Object obj2 = Composer.Companion.Empty;
                if (nextSlot == obj2) {
                    nextSlot = ResultKt.mutableStateOf(ResultKt.access$dataPackageOf(), StructuralEqualityPolicy.INSTANCE);
                    composerImpl2.updateValue(nextSlot);
                }
                composerImpl2.end(false);
                final MutableState mutableState = (MutableState) nextSlot;
                composerImpl2.startReplaceableGroup(-483455358);
                Modifier.Companion companion = Modifier.Companion.$$INSTANCE;
                Arrangement$Top$1 arrangement$Top$1 = Arrangement.Top;
                Object obj3 = Alignment.Companion.Start;
                RowColumnImplKt$rowColumnMeasurePolicy$1 rowColumnImplKt$rowColumnMeasurePolicy$1 = ColumnKt.DefaultColumnMeasurePolicy;
                composerImpl2.startReplaceableGroup(1089876336);
                if (ResultKt.areEqual(arrangement$Top$1, arrangement$Top$1) && ResultKt.areEqual(obj3, obj3)) {
                    obj = ColumnKt.DefaultColumnMeasurePolicy;
                } else {
                    composerImpl2.startReplaceableGroup(511388516);
                    boolean changed = composerImpl2.changed(obj3) | composerImpl2.changed(arrangement$Top$1);
                    Object nextSlot2 = composerImpl2.nextSlot();
                    if (changed || nextSlot2 == obj2) {
                        nextSlot2 = PaddingKt.m36rowColumnMeasurePolicyTDGSqEk(2, new RowKt$rowMeasurePolicy$1$1(1, arrangement$Top$1), 0, new CrossAxisAlignment$VerticalCrossAxisAlignment());
                        composerImpl2.updateValue(nextSlot2);
                    }
                    composerImpl2.end(false);
                    obj = (MeasurePolicy) nextSlot2;
                }
                composerImpl2.end(false);
                composerImpl2.startReplaceableGroup(-1323940314);
                int i3 = composerImpl2.compoundKeyHash;
                PersistentCompositionLocalMap currentCompositionLocalScope = composerImpl2.currentCompositionLocalScope();
                ComposeUiNode.Companion.getClass();
                Function0 function0 = ComposeUiNode.Companion.Constructor;
                ComposableLambdaImpl composableLambdaInstance = ResultKt.composableLambdaInstance(-1586257396, new LayoutKt$materializerOf$1(companion, i2), true);
                if (composerImpl2.applier instanceof Applier) {
                    composerImpl2.startReusableNode();
                    if (composerImpl2.inserting) {
                        composerImpl2.createNode(function0);
                    } else {
                        composerImpl2.useNode();
                    }
                    ResultKt.m299setimpl(composerImpl2, obj, ComposeUiNode.Companion.SetMeasurePolicy);
                    ResultKt.m299setimpl(composerImpl2, currentCompositionLocalScope, ComposeUiNode.Companion.SetResolvedCompositionLocals);
                    ComposeUiNode$Companion$SetDensity$1 composeUiNode$Companion$SetDensity$1 = ComposeUiNode.Companion.SetCompositeKeyHash;
                    if (composerImpl2.inserting || !ResultKt.areEqual(composerImpl2.nextSlot(), Integer.valueOf(i3))) {
                        composerImpl2.updateValue(Integer.valueOf(i3));
                        composerImpl2.apply(Integer.valueOf(i3), composeUiNode$Companion$SetDensity$1);
                    }
                    composableLambdaInstance.invoke(new SkippableUpdater(composerImpl2), (Object) composerImpl2, (Object) 0);
                    composerImpl2.startReplaceableGroup(2058660585);
                    composerImpl2.startReplaceableGroup(1157296644);
                    boolean changed2 = composerImpl2.changed(mutableState);
                    Object nextSlot3 = composerImpl2.nextSlot();
                    if (changed2 || nextSlot3 == obj2) {
                        nextSlot3 = new LayoutNode$_foldedChildren$1(18, mutableState);
                        composerImpl2.updateValue(nextSlot3);
                    }
                    composerImpl2.end(false);
                    _BOUNDARY.Button((Function0) nextSlot3, null, false, null, null, null, null, null, ResultKt.composableLambda(composerImpl2, -950399869, new Function3() { // from class: kotlinx.coroutines.sync.MutexImpl$onSelectCancellationUnlockConstructor$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public final Object invoke(Object obj4, Object obj5, Object obj6) {
                            Composer composer2 = (Composer) obj5;
                            int intValue = ((Number) obj6).intValue();
                            ResultKt.checkNotNullParameter((RowScope) obj4, "$this$Button");
                            if ((intValue & 81) == 16) {
                                ComposerImpl composerImpl3 = (ComposerImpl) composer2;
                                if (composerImpl3.getSkipping()) {
                                    composerImpl3.skipToGroupEnd();
                                    return Unit.INSTANCE;
                                }
                            }
                            TextKt.m58Text4IGK_g(((DataPackage) ((MutableState) mutableState).getValue()).text8798SDF, null, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer2, 0, 0, 131070);
                            return Unit.INSTANCE;
                        }
                    }), composerImpl2, 805306368, 510);
                    ResultKt.Greeting(((DataPackage) mutableState.getValue()).text8798SDF + "_0", null, (DataPackage) mutableState.getValue(), composerImpl2, 512, 2);
                    ResultKt.Greeting(((DataPackage) mutableState.getValue()).text8798SDF + "_1", null, (DataPackage) mutableState.getValue(), composerImpl2, 512, 2);
                    ResultKt.Greeting(((DataPackage) mutableState.getValue()).text8798SDF + "_2", null, (DataPackage) mutableState.getValue(), composerImpl2, 512, 2);
                    ResultKt.Greeting(((DataPackage) mutableState.getValue()).text8798SDF + "_3", null, (DataPackage) mutableState.getValue(), composerImpl2, 512, 2);
                    ResultKt.Greetings((DataPackage) mutableState.getValue(), composerImpl2, 8);
                    ResultKt.access$GreetingsGreetings((DataPackage) mutableState.getValue(), composerImpl2, 8);
                    composerImpl2.end(false);
                    composerImpl2.end(true);
                    composerImpl2.end(false);
                    composerImpl2.end(false);
                    return;
                }
                _BOUNDARY.invalidApplier();
                throw null;
            case 1:
                if ((i & 11) == 2) {
                    ComposerImpl composerImpl3 = (ComposerImpl) composer;
                    if (composerImpl3.getSkipping()) {
                        composerImpl3.skipToGroupEnd();
                        return;
                    }
                }
                final Modifier fillMaxSize$default = SizeKt.fillMaxSize$default();
                StaticProvidableCompositionLocal staticProvidableCompositionLocal = ColorSchemeKt.LocalColorScheme;
                ComposerImpl composerImpl4 = (ComposerImpl) composer;
                final long m51getBackground0d7_KjU = ((ColorScheme) composerImpl4.consume(staticProvidableCompositionLocal)).m51getBackground0d7_KjU();
                final ComposableLambdaImpl composableLambdaImpl = ComposableSingletons$MainActivityKt.f1lambda1;
                DynamicProvidableCompositionLocal dynamicProvidableCompositionLocal = SurfaceKt.LocalAbsoluteTonalElevation;
                ResultKt.checkNotNullParameter(composableLambdaImpl, "content");
                composerImpl4.startReplaceableGroup(-513881741);
                final RectangleShapeKt$RectangleShape$1 rectangleShapeKt$RectangleShape$1 = Brush.RectangleShape;
                ColorScheme colorScheme = (ColorScheme) composerImpl4.consume(staticProvidableCompositionLocal);
                ResultKt.checkNotNullParameter(colorScheme, "$this$contentColorFor");
                if (Color.m121equalsimpl0(m51getBackground0d7_KjU, colorScheme.m52getPrimary0d7_KjU())) {
                    j = ((Color) colorScheme.onPrimary$delegate.getValue()).value;
                } else if (Color.m121equalsimpl0(m51getBackground0d7_KjU, ((Color) colorScheme.secondary$delegate.getValue()).value)) {
                    j = ((Color) colorScheme.onSecondary$delegate.getValue()).value;
                } else if (Color.m121equalsimpl0(m51getBackground0d7_KjU, ((Color) colorScheme.tertiary$delegate.getValue()).value)) {
                    j = ((Color) colorScheme.onTertiary$delegate.getValue()).value;
                } else if (Color.m121equalsimpl0(m51getBackground0d7_KjU, colorScheme.m51getBackground0d7_KjU())) {
                    j = ((Color) colorScheme.onBackground$delegate.getValue()).value;
                } else if (Color.m121equalsimpl0(m51getBackground0d7_KjU, ((Color) colorScheme.error$delegate.getValue()).value)) {
                    j = ((Color) colorScheme.onError$delegate.getValue()).value;
                } else if (Color.m121equalsimpl0(m51getBackground0d7_KjU, colorScheme.m53getSurface0d7_KjU())) {
                    j = ((Color) colorScheme.onSurface$delegate.getValue()).value;
                } else if (Color.m121equalsimpl0(m51getBackground0d7_KjU, ((Color) colorScheme.surfaceVariant$delegate.getValue()).value)) {
                    j = ((Color) colorScheme.onSurfaceVariant$delegate.getValue()).value;
                } else if (Color.m121equalsimpl0(m51getBackground0d7_KjU, ((Color) colorScheme.primaryContainer$delegate.getValue()).value)) {
                    j = ((Color) colorScheme.onPrimaryContainer$delegate.getValue()).value;
                } else if (Color.m121equalsimpl0(m51getBackground0d7_KjU, ((Color) colorScheme.secondaryContainer$delegate.getValue()).value)) {
                    j = ((Color) colorScheme.onSecondaryContainer$delegate.getValue()).value;
                } else if (Color.m121equalsimpl0(m51getBackground0d7_KjU, ((Color) colorScheme.tertiaryContainer$delegate.getValue()).value)) {
                    j = ((Color) colorScheme.onTertiaryContainer$delegate.getValue()).value;
                } else if (Color.m121equalsimpl0(m51getBackground0d7_KjU, ((Color) colorScheme.errorContainer$delegate.getValue()).value)) {
                    j = ((Color) colorScheme.onErrorContainer$delegate.getValue()).value;
                } else if (Color.m121equalsimpl0(m51getBackground0d7_KjU, ((Color) colorScheme.inverseSurface$delegate.getValue()).value)) {
                    j = ((Color) colorScheme.inverseOnSurface$delegate.getValue()).value;
                } else {
                    j = Color.Unspecified;
                }
                if (j == Color.Unspecified) {
                    j = ((Color) composerImpl4.consume(ContentColorKt.LocalContentColor)).value;
                }
                final float f = 0;
                DynamicProvidableCompositionLocal dynamicProvidableCompositionLocal2 = SurfaceKt.LocalAbsoluteTonalElevation;
                final float f2 = ((Dp) composerImpl4.consume(dynamicProvidableCompositionLocal2)).value + f;
                _BOUNDARY.CompositionLocalProvider(new ProvidedValue[]{ContentColorKt.LocalContentColor.provides(new Color(j)), dynamicProvidableCompositionLocal2.provides(new Dp(f2))}, ResultKt.composableLambda(composerImpl4, -70914509, new Function2() { // from class: androidx.compose.material3.SurfaceKt$Surface$1
                    public final /* synthetic */ int $$changed = 12582918;

                    /* renamed from: androidx.compose.material3.SurfaceKt$Surface$1$1, reason: invalid class name */
                    /* loaded from: classes.dex */
                    public final class AnonymousClass1 extends Lambda implements Function1 {
                        public static final AnonymousClass1 INSTANCE = new Lambda(1);

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            SemanticsPropertyReceiver semanticsPropertyReceiver = (SemanticsPropertyReceiver) obj;
                            ResultKt.checkNotNullParameter(semanticsPropertyReceiver, "$this$semantics");
                            KProperty[] kPropertyArr = SemanticsPropertiesKt.$$delegatedProperties;
                            SemanticsPropertyKey semanticsPropertyKey = SemanticsProperties.IsTraversalGroup;
                            KProperty kProperty = SemanticsPropertiesKt.$$delegatedProperties[5];
                            Boolean bool = Boolean.TRUE;
                            semanticsPropertyKey.getClass();
                            ResultKt.checkNotNullParameter(kProperty, "property");
                            ((SemanticsConfiguration) semanticsPropertyReceiver).set(semanticsPropertyKey, bool);
                            return Unit.INSTANCE;
                        }
                    }

                    /* renamed from: androidx.compose.material3.SurfaceKt$Surface$1$2, reason: invalid class name */
                    /* loaded from: classes.dex */
                    public final class AnonymousClass2 extends SuspendLambda implements Function2 {
                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation create(Object obj, Continuation continuation) {
                            return new SuspendLambda(2, continuation);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            AnonymousClass2 anonymousClass2 = (AnonymousClass2) create((PointerInputScope) obj, (Continuation) obj2);
                            Unit unit = Unit.INSTANCE;
                            anonymousClass2.invokeSuspend(unit);
                            return unit;
                        }

                        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object obj) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                    }

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    /* JADX WARN: Type inference failed for: r2v3, types: [kotlin.coroutines.jvm.internal.SuspendLambda, kotlin.jvm.functions.Function2] */
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj4, Object obj5) {
                        Composer composer2 = (Composer) obj4;
                        int intValue = ((Number) obj5).intValue() & 11;
                        Unit unit = Unit.INSTANCE;
                        if (intValue == 2) {
                            ComposerImpl composerImpl5 = (ComposerImpl) composer2;
                            if (composerImpl5.getSkipping()) {
                                composerImpl5.skipToGroupEnd();
                                return unit;
                            }
                        }
                        Modifier m56access$surface8ww4TTg = SurfaceKt.m56access$surface8ww4TTg(Modifier.this, rectangleShapeKt$RectangleShape$1, SurfaceKt.m57access$surfaceColorAtElevationCLU3JFs(m51getBackground0d7_KjU, f2, composer2), f);
                        AnonymousClass1 anonymousClass1 = AnonymousClass1.INSTANCE;
                        AtomicInteger atomicInteger = SemanticsModifierKt.lastIdentifier;
                        Modifier then = m56access$surface8ww4TTg.then(new AppendedSemanticsElement(anonymousClass1, false));
                        ?? suspendLambda = new SuspendLambda(2, null);
                        PointerEvent pointerEvent = SuspendingPointerInputFilterKt.EmptyPointerEvent;
                        Modifier then2 = then.then(new SuspendPointerInputElement(unit, suspendLambda));
                        ComposerImpl composerImpl6 = (ComposerImpl) composer2;
                        composerImpl6.startReplaceableGroup(733328855);
                        MeasurePolicy rememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(composer2);
                        composerImpl6.startReplaceableGroup(-1323940314);
                        Density density = (Density) composerImpl6.consume(CompositionLocalsKt.LocalDensity);
                        LayoutDirection layoutDirection = (LayoutDirection) composerImpl6.consume(CompositionLocalsKt.LocalLayoutDirection);
                        ViewConfiguration viewConfiguration = (ViewConfiguration) composerImpl6.consume(CompositionLocalsKt.LocalViewConfiguration);
                        ComposeUiNode.Companion.getClass();
                        LayoutNode$Companion$Constructor$1 layoutNode$Companion$Constructor$1 = ComposeUiNode.Companion.Constructor;
                        ComposableLambdaImpl materializerOf = _BOUNDARY.materializerOf(then2);
                        if (!(composerImpl6.applier instanceof Applier)) {
                            _BOUNDARY.invalidApplier();
                            throw null;
                        }
                        composerImpl6.startReusableNode();
                        if (composerImpl6.inserting) {
                            composerImpl6.createNode(layoutNode$Companion$Constructor$1);
                        } else {
                            composerImpl6.useNode();
                        }
                        composerImpl6.reusing = false;
                        ResultKt.m299setimpl(composer2, rememberBoxMeasurePolicy, ComposeUiNode.Companion.SetMeasurePolicy);
                        ResultKt.m299setimpl(composer2, density, ComposeUiNode.Companion.SetDensity);
                        ResultKt.m299setimpl(composer2, layoutDirection, ComposeUiNode.Companion.SetLayoutDirection);
                        ResultKt.m299setimpl(composer2, viewConfiguration, ComposeUiNode.Companion.SetViewConfiguration);
                        composerImpl6.reusing = composerImpl6.reusingGroup >= 0;
                        materializerOf.invoke((Object) new SkippableUpdater(composer2), (Object) composer2, (Object) 0);
                        composerImpl6.startReplaceableGroup(2058660585);
                        composableLambdaImpl.invoke(composer2, Integer.valueOf((this.$$changed >> 21) & 14));
                        composerImpl6.end(false);
                        composerImpl6.end(true);
                        composerImpl6.end(false);
                        composerImpl6.end(false);
                        return unit;
                    }
                }), composerImpl4, 56);
                composerImpl4.end(false);
                return;
            case 2:
                if ((i & 11) == 2) {
                    ComposerImpl composerImpl5 = (ComposerImpl) composer;
                    if (composerImpl5.getSkipping()) {
                        composerImpl5.skipToGroupEnd();
                        return;
                    }
                }
                ThemeKt.ComposeSample2Theme(false, false, ComposableSingletons$MainActivityKt.f2lambda2, composer, 384, 3);
                return;
            default:
                if ((i & 11) == 2) {
                    ComposerImpl composerImpl6 = (ComposerImpl) composer;
                    if (composerImpl6.getSkipping()) {
                        composerImpl6.skipToGroupEnd();
                        return;
                    }
                }
                ResultKt.Greeting("Android", null, ResultKt.access$dataPackageOf(), composer, 518, 2);
                return;
        }
    }
}
