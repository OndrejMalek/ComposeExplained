package androidx.compose.ui.unit;

import _COROUTINE._BOUNDARY;
import androidx.compose.ui.geometry.Size;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public interface Density {
    float getDensity();

    float getFontScale();

    /* renamed from: roundToPx-0680j_4 */
    default int mo180roundToPx0680j_4(float f) {
        float mo32toPx0680j_4 = mo32toPx0680j_4(f);
        if (Float.isInfinite(mo32toPx0680j_4)) {
            return Integer.MAX_VALUE;
        }
        return ResultKt.roundToInt(mo32toPx0680j_4);
    }

    /* renamed from: toPx--R2X_6o */
    default float mo31toPxR2X_6o(long j) {
        if (!TextUnitType.m287equalsimpl0(TextUnit.m284getTypeUIouoOA(j), 4294967296L)) {
            throw new IllegalStateException("Only Sp can convert to Px".toString());
        }
        return getDensity() * getFontScale() * TextUnit.m285getValueimpl(j);
    }

    /* renamed from: toPx-0680j_4 */
    default float mo32toPx0680j_4(float f) {
        return getDensity() * f;
    }

    /* renamed from: toSize-XkaWNTQ */
    default long mo33toSizeXkaWNTQ(long j) {
        long j2 = DpSize.Unspecified;
        if (j == j2) {
            return Size.Unspecified;
        }
        if (j == j2) {
            throw new IllegalStateException("DpSize is unspecified".toString());
        }
        float mo32toPx0680j_4 = mo32toPx0680j_4(Float.intBitsToFloat((int) (j >> 32)));
        if (j != j2) {
            return _BOUNDARY.Size(mo32toPx0680j_4, mo32toPx0680j_4(Float.intBitsToFloat((int) (j & 4294967295L))));
        }
        throw new IllegalStateException("DpSize is unspecified".toString());
    }
}
