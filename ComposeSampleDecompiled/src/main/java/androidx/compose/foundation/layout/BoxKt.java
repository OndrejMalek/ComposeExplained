package androidx.compose.foundation.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MeasureScope$layout$1;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public abstract class BoxKt {
    public static final /* synthetic */ int $r8$clinit = 0;

    public static final MeasurePolicy rememberBoxMeasurePolicy(Composer composer) {
        BiasAlignment biasAlignment = Alignment.Companion.TopStart;
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(56522820);
        ResultKt.areEqual(biasAlignment, biasAlignment);
        Boolean bool = Boolean.TRUE;
        composerImpl.startReplaceableGroup(511388516);
        boolean changed = composerImpl.changed(biasAlignment) | composerImpl.changed(bool);
        Object nextSlot = composerImpl.nextSlot();
        if (changed || nextSlot == Composer.Companion.Empty) {
            final boolean z = true;
            nextSlot = new MeasurePolicy() { // from class: androidx.compose.foundation.layout.BoxKt$boxMeasurePolicy$1
                public final /* synthetic */ Alignment $alignment = Alignment.Companion.TopStart;

                /* JADX WARN: Type inference failed for: r5v1, types: [kotlin.jvm.internal.Ref$IntRef, java.lang.Object] */
                /* JADX WARN: Type inference failed for: r7v1, types: [kotlin.jvm.internal.Ref$IntRef, java.lang.Object] */
                @Override // androidx.compose.ui.layout.MeasurePolicy
                /* renamed from: measure-3p2s80s, reason: not valid java name */
                public final MeasureScope$layout$1 mo34measure3p2s80s(final MeasureScope measureScope, final List list, long j) {
                    ResultKt.checkNotNullParameter(measureScope, "$this$MeasurePolicy");
                    if (list.isEmpty()) {
                        return MeasureScope.layout$default(measureScope, Constraints.m278getMinWidthimpl(j), Constraints.m277getMinHeightimpl(j), BoxKt$boxMeasurePolicy$1$measure$1.INSTANCE);
                    }
                    long m272copyZbe2FdA$default = z ? j : Constraints.m272copyZbe2FdA$default(j);
                    if (list.size() == 1) {
                        final Measurable measurable = (Measurable) list.get(0);
                        measurable.getParentData();
                        final Placeable mo164measureBRTryo0 = measurable.mo164measureBRTryo0(m272copyZbe2FdA$default);
                        final int max = Math.max(Constraints.m278getMinWidthimpl(j), mo164measureBRTryo0.width);
                        final int max2 = Math.max(Constraints.m277getMinHeightimpl(j), mo164measureBRTryo0.height);
                        final Alignment alignment = this.$alignment;
                        return MeasureScope.layout$default(measureScope, max, max2, new Function1() { // from class: androidx.compose.foundation.layout.BoxKt$boxMeasurePolicy$1$measure$2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
                                ResultKt.checkNotNullParameter(placementScope, "$this$layout");
                                LayoutDirection layoutDirection = measureScope.getLayoutDirection();
                                int i = BoxKt.$r8$clinit;
                                measurable.getParentData();
                                Placeable placeable = Placeable.this;
                                Placeable.PlacementScope.m169place70tqf50$default(placementScope, placeable, ((BiasAlignment) alignment).m61alignKFBX0sM(ResultKt.IntSize(placeable.width, placeable.height), ResultKt.IntSize(max, max2), layoutDirection));
                                return Unit.INSTANCE;
                            }
                        });
                    }
                    final Placeable[] placeableArr = new Placeable[list.size()];
                    final ?? obj = new Object();
                    obj.element = Constraints.m278getMinWidthimpl(j);
                    final ?? obj2 = new Object();
                    obj2.element = Constraints.m277getMinHeightimpl(j);
                    int size = list.size();
                    for (int i = 0; i < size; i++) {
                        Measurable measurable2 = (Measurable) list.get(i);
                        measurable2.getParentData();
                        Placeable mo164measureBRTryo02 = measurable2.mo164measureBRTryo0(m272copyZbe2FdA$default);
                        placeableArr[i] = mo164measureBRTryo02;
                        obj.element = Math.max(obj.element, mo164measureBRTryo02.width);
                        obj2.element = Math.max(obj2.element, mo164measureBRTryo02.height);
                    }
                    int i2 = obj.element;
                    int i3 = obj2.element;
                    final Alignment alignment2 = this.$alignment;
                    return MeasureScope.layout$default(measureScope, i2, i3, new Function1() { // from class: androidx.compose.foundation.layout.BoxKt$boxMeasurePolicy$1$measure$5
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj3;
                            ResultKt.checkNotNullParameter(placementScope, "$this$layout");
                            Placeable[] placeableArr2 = placeableArr;
                            int length = placeableArr2.length;
                            int i4 = 0;
                            int i5 = 0;
                            while (i4 < length) {
                                Placeable placeable = placeableArr2[i4];
                                int i6 = i5 + 1;
                                ResultKt.checkNotNull(placeable, "null cannot be cast to non-null type androidx.compose.ui.layout.Placeable");
                                Measurable measurable3 = (Measurable) list.get(i5);
                                LayoutDirection layoutDirection = measureScope.getLayoutDirection();
                                int i7 = obj.element;
                                int i8 = obj2.element;
                                int i9 = BoxKt.$r8$clinit;
                                measurable3.getParentData();
                                Placeable.PlacementScope.m169place70tqf50$default(placementScope, placeable, ((BiasAlignment) alignment2).m61alignKFBX0sM(ResultKt.IntSize(placeable.width, placeable.height), ResultKt.IntSize(i7, i8), layoutDirection));
                                i4++;
                                i5 = i6;
                            }
                            return Unit.INSTANCE;
                        }
                    });
                }
            };
            composerImpl.updateValue(nextSlot);
        }
        composerImpl.end(false);
        MeasurePolicy measurePolicy = (MeasurePolicy) nextSlot;
        composerImpl.end(false);
        return measurePolicy;
    }
}
