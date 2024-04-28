package androidx.compose.animation.core;

/* loaded from: classes.dex */
public final class ComplexDouble {
    public double _imaginary;
    public double _real;

    public ComplexDouble(double d, double d2) {
        this._real = d;
        this._imaginary = d2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ComplexDouble)) {
            return false;
        }
        ComplexDouble complexDouble = (ComplexDouble) obj;
        return Double.compare(this._real, complexDouble._real) == 0 && Double.compare(this._imaginary, complexDouble._imaginary) == 0;
    }

    public final int hashCode() {
        return Double.hashCode(this._imaginary) + (Double.hashCode(this._real) * 31);
    }

    public final String toString() {
        return "ComplexDouble(_real=" + this._real + ", _imaginary=" + this._imaginary + ')';
    }
}
