package androidx.compose.animation.core;

/* loaded from: classes.dex */
public final class SpringSimulation {
    public double dampedFreq;
    public float dampingRatio;
    public float finalPosition;
    public double gammaMinus;
    public double gammaPlus;
    public boolean initialized;
    public double naturalFreq;

    /* renamed from: updateValues-IJZedt4$animation_core_release, reason: not valid java name */
    public final long m24updateValuesIJZedt4$animation_core_release(float f, float f2, long j) {
        double cos;
        double d;
        if (!this.initialized) {
            if (this.finalPosition == Float.MAX_VALUE) {
                throw new IllegalStateException("Error: Final position of the spring must be set before the animation starts");
            }
            float f3 = this.dampingRatio;
            double d2 = f3;
            double d3 = d2 * d2;
            if (f3 > 1.0f) {
                double d4 = this.naturalFreq;
                double d5 = d3 - 1;
                this.gammaPlus = (Math.sqrt(d5) * d4) + ((-f3) * d4);
                double d6 = -this.dampingRatio;
                double d7 = this.naturalFreq;
                this.gammaMinus = (d6 * d7) - (Math.sqrt(d5) * d7);
            } else if (f3 >= 0.0f && f3 < 1.0f) {
                this.dampedFreq = Math.sqrt(1 - d3) * this.naturalFreq;
            }
            this.initialized = true;
        }
        float f4 = f - this.finalPosition;
        double d8 = j / 1000.0d;
        float f5 = this.dampingRatio;
        if (f5 > 1.0f) {
            double d9 = f4;
            double d10 = this.gammaMinus;
            double d11 = f2;
            double d12 = this.gammaPlus;
            double d13 = d9 - (((d10 * d9) - d11) / (d10 - d12));
            double d14 = ((d9 * d10) - d11) / (d10 - d12);
            d = (Math.exp(this.gammaPlus * d8) * d14) + (Math.exp(d10 * d8) * d13);
            double d15 = this.gammaMinus;
            double exp = Math.exp(d15 * d8) * d13 * d15;
            double d16 = this.gammaPlus;
            cos = (Math.exp(d16 * d8) * d14 * d16) + exp;
        } else if (f5 == 1.0f) {
            double d17 = this.naturalFreq;
            double d18 = f4;
            double d19 = (d17 * d18) + f2;
            double d20 = (d19 * d8) + d18;
            d = Math.exp((-d17) * d8) * d20;
            double exp2 = Math.exp((-this.naturalFreq) * d8) * d20;
            double d21 = this.naturalFreq;
            cos = (Math.exp((-d21) * d8) * d19) + (exp2 * (-d21));
        } else {
            double d22 = 1 / this.dampedFreq;
            double d23 = this.naturalFreq;
            double d24 = f4;
            double d25 = ((f5 * d23 * d24) + f2) * d22;
            double exp3 = Math.exp((-f5) * d23 * d8) * ((Math.sin(this.dampedFreq * d8) * d25) + (Math.cos(this.dampedFreq * d8) * d24));
            double d26 = this.naturalFreq;
            double d27 = (-d26) * exp3 * this.dampingRatio;
            double exp4 = Math.exp((-r7) * d26 * d8);
            double d28 = this.dampedFreq;
            double sin = Math.sin(d28 * d8) * (-d28) * d24;
            double d29 = this.dampedFreq;
            cos = (((Math.cos(d29 * d8) * d25 * d29) + sin) * exp4) + d27;
            d = exp3;
        }
        return (Float.floatToIntBits((float) cos) & 4294967295L) | (Float.floatToIntBits((float) (d + this.finalPosition)) << 32);
    }
}
