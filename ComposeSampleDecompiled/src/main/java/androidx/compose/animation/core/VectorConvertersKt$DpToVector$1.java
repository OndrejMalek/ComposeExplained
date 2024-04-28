package androidx.compose.animation.core;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class VectorConvertersKt$DpToVector$1 extends Lambda implements Function1 {
    public static final VectorConvertersKt$DpToVector$1 INSTANCE = new VectorConvertersKt$DpToVector$1(0);
    public static final VectorConvertersKt$DpToVector$1 INSTANCE$3 = new VectorConvertersKt$DpToVector$1(3);
    public static final VectorConvertersKt$DpToVector$1 INSTANCE$4 = new VectorConvertersKt$DpToVector$1(4);
    public static final VectorConvertersKt$DpToVector$1 INSTANCE$5 = new VectorConvertersKt$DpToVector$1(5);
    public final /* synthetic */ int $r8$classId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ VectorConvertersKt$DpToVector$1(int i) {
        super(1);
        this.$r8$classId = i;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                return new AnimationVector1D(((Dp) obj).value);
            case 1:
                long j = ((DpOffset) obj).packedValue;
                long j2 = DpOffset.Unspecified;
                if (j == j2) {
                    throw new IllegalStateException("DpOffset is unspecified".toString());
                }
                float intBitsToFloat = Float.intBitsToFloat((int) (j >> 32));
                if (j != j2) {
                    return new AnimationVector2D(intBitsToFloat, Float.intBitsToFloat((int) (4294967295L & j)));
                }
                throw new IllegalStateException("DpOffset is unspecified".toString());
            case 2:
                AnimationVector2D animationVector2D = (AnimationVector2D) obj;
                ResultKt.checkNotNullParameter(animationVector2D, "it");
                return new DpOffset(ResultKt.m293DpOffsetYgX7TsA(animationVector2D.v1, animationVector2D.v2));
            case 3:
                AnimationVector1D animationVector1D = (AnimationVector1D) obj;
                ResultKt.checkNotNullParameter(animationVector1D, "it");
                return new Dp(animationVector1D.value);
            case 4:
                return new AnimationVector1D(((Number) obj).floatValue());
            case 5:
                AnimationVector1D animationVector1D2 = (AnimationVector1D) obj;
                ResultKt.checkNotNullParameter(animationVector1D2, "it");
                return Float.valueOf(animationVector1D2.value);
            case 6:
                long j3 = ((IntOffset) obj).packedValue;
                int i = IntOffset.$r8$clinit;
                return new AnimationVector2D((int) (j3 >> 32), (int) (4294967295L & j3));
            case 7:
                AnimationVector2D animationVector2D2 = (AnimationVector2D) obj;
                ResultKt.checkNotNullParameter(animationVector2D2, "it");
                return new IntOffset(ResultKt.IntOffset(ResultKt.roundToInt(animationVector2D2.v1), ResultKt.roundToInt(animationVector2D2.v2)));
            case 8:
                long j4 = ((IntSize) obj).packedValue;
                return new AnimationVector2D((int) (j4 >> 32), (int) (4294967295L & j4));
            case 9:
                AnimationVector2D animationVector2D3 = (AnimationVector2D) obj;
                ResultKt.checkNotNullParameter(animationVector2D3, "it");
                return new IntSize(ResultKt.IntSize(ResultKt.roundToInt(animationVector2D3.v1), ResultKt.roundToInt(animationVector2D3.v2)));
            case 10:
                return new AnimationVector1D(((Number) obj).intValue());
            case 11:
                AnimationVector1D animationVector1D3 = (AnimationVector1D) obj;
                ResultKt.checkNotNullParameter(animationVector1D3, "it");
                return Integer.valueOf((int) animationVector1D3.value);
            case 12:
                long j5 = ((Offset) obj).packedValue;
                return new AnimationVector2D(Offset.m76getXimpl(j5), Offset.m77getYimpl(j5));
            case 13:
                AnimationVector2D animationVector2D4 = (AnimationVector2D) obj;
                ResultKt.checkNotNullParameter(animationVector2D4, "it");
                return new Offset(_BOUNDARY.Offset(animationVector2D4.v1, animationVector2D4.v2));
            case 14:
                Rect rect = (Rect) obj;
                ResultKt.checkNotNullParameter(rect, "it");
                return new AnimationVector4D(rect.left, rect.top, rect.right, rect.bottom);
            case 15:
                AnimationVector4D animationVector4D = (AnimationVector4D) obj;
                ResultKt.checkNotNullParameter(animationVector4D, "it");
                return new Rect(animationVector4D.v1, animationVector4D.v2, animationVector4D.v3, animationVector4D.v4);
            case 16:
                long j6 = ((Size) obj).packedValue;
                return new AnimationVector2D(Size.m85getWidthimpl(j6), Size.m83getHeightimpl(j6));
            default:
                AnimationVector2D animationVector2D5 = (AnimationVector2D) obj;
                ResultKt.checkNotNullParameter(animationVector2D5, "it");
                return new Size(_BOUNDARY.Size(animationVector2D5.v1, animationVector2D5.v2));
        }
    }
}
