package androidx.compose.animation.core;

/* loaded from: classes.dex */
public final class FloatSpringSpec implements FloatAnimationSpec {
    public final SpringSimulation spring;
    public final float visibilityThreshold;

    /* JADX WARN: Type inference failed for: r7v1, types: [androidx.compose.animation.core.SpringSimulation, java.lang.Object] */
    public FloatSpringSpec(float f, float f2, float f3) {
        this.visibilityThreshold = f3;
        ?? obj = new Object();
        obj.finalPosition = 1.0f;
        double sqrt = Math.sqrt(50.0d);
        obj.naturalFreq = sqrt;
        obj.dampingRatio = 1.0f;
        if (f < 0.0f) {
            throw new IllegalArgumentException("Damping ratio must be non-negative");
        }
        obj.dampingRatio = f;
        obj.initialized = false;
        if (((float) (sqrt * sqrt)) <= 0.0f) {
            throw new IllegalArgumentException("Spring stiffness constant must be positive.");
        }
        obj.naturalFreq = Math.sqrt(f2);
        obj.initialized = false;
        this.spring = obj;
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final long getDurationNanos(float f, float f2, float f3) {
        double d;
        ComplexDouble complexDouble;
        ComplexDouble complexDouble2;
        ComplexDouble complexDouble3;
        boolean z;
        boolean z2;
        double d2;
        double d3;
        long j;
        double d4;
        double d5;
        double d6;
        SpringSimulation springSimulation = this.spring;
        double d7 = springSimulation.naturalFreq;
        float f4 = springSimulation.dampingRatio;
        float f5 = this.visibilityThreshold;
        double d8 = (float) (d7 * d7);
        double d9 = f4;
        double d10 = f3 / f5;
        double d11 = (f - f2) / f5;
        double d12 = 1.0f;
        double sqrt = d9 * 2.0d * Math.sqrt(d8);
        double d13 = (sqrt * sqrt) - (d8 * 4.0d);
        double d14 = -sqrt;
        if (d13 < 0.0d) {
            d = d12;
            complexDouble = new ComplexDouble(0.0d, Math.sqrt(Math.abs(d13)));
        } else {
            d = d12;
            complexDouble = new ComplexDouble(Math.sqrt(d13), 0.0d);
        }
        complexDouble._real = (complexDouble._real + d14) * 0.5d;
        complexDouble._imaginary *= 0.5d;
        if (d13 < 0.0d) {
            complexDouble2 = complexDouble;
            complexDouble3 = new ComplexDouble(0.0d, Math.sqrt(Math.abs(d13)));
        } else {
            complexDouble2 = complexDouble;
            complexDouble3 = new ComplexDouble(Math.sqrt(d13), 0.0d);
        }
        double d15 = -1;
        double d16 = complexDouble3._real * d15;
        double d17 = complexDouble3._imaginary * d15;
        complexDouble3._real = (d16 + d14) * 0.5d;
        complexDouble3._imaginary = d17 * 0.5d;
        if (d11 == 0.0d && d10 == 0.0d) {
            j = 0;
        } else {
            if (d11 < 0.0d) {
                d10 = -d10;
            }
            double abs = Math.abs(d11);
            int i = 0;
            if (d9 > 1.0d) {
                double d18 = complexDouble2._real;
                double d19 = complexDouble3._real;
                double d20 = (d18 * abs) - d10;
                double d21 = d18 - d19;
                double d22 = d20 / d21;
                double d23 = abs - d22;
                d3 = Math.log(Math.abs(d / d23)) / d18;
                double log = Math.log(Math.abs(d / d22)) / d19;
                if (!((Double.isInfinite(d3) || Double.isNaN(d3)) ? false : true)) {
                    d3 = log;
                } else if (!(!((Double.isInfinite(log) || Double.isNaN(log)) ? false : true))) {
                    d3 = Math.max(d3, log);
                }
                double d24 = d23 * d18;
                double log2 = Math.log(d24 / ((-d22) * d19)) / (d19 - d18);
                if (Double.isNaN(log2) || log2 <= 0.0d) {
                    d4 = -d;
                } else {
                    if (log2 > 0.0d) {
                        if ((-((Math.exp(log2 * d19) * d22) + (Math.exp(d18 * log2) * d23))) < d) {
                            if (d22 <= 0.0d || d23 >= 0.0d) {
                                d5 = d3;
                                d6 = d;
                            } else {
                                d6 = d;
                                d5 = 0.0d;
                            }
                            d4 = -d6;
                            d3 = d5;
                        }
                    }
                    d4 = d;
                    d3 = Math.log((-((d22 * d19) * d19)) / (d24 * d18)) / d21;
                }
                double d25 = d22 * d19;
                if (Math.abs((Math.exp(d19 * d3) * d25) + (Math.exp(d18 * d3) * d24)) >= 1.0E-4d) {
                    double d26 = Double.MAX_VALUE;
                    for (double d27 = 0.001d; d26 > d27 && i < 100; d27 = 0.001d) {
                        i++;
                        double d28 = d18 * d3;
                        double d29 = d19 * d3;
                        double exp = d3 - ((((Math.exp(d29) * d22) + (Math.exp(d28) * d23)) + d4) / ((Math.exp(d29) * d25) + (Math.exp(d28) * d24)));
                        d26 = Math.abs(d3 - exp);
                        d3 = exp;
                    }
                }
            } else {
                ComplexDouble complexDouble4 = complexDouble2;
                double d30 = d;
                if (d9 < 1.0d) {
                    double d31 = complexDouble4._real;
                    double d32 = (d10 - (d31 * abs)) / complexDouble4._imaginary;
                    d3 = Math.log(d30 / Math.sqrt((d32 * d32) + (abs * abs))) / d31;
                } else {
                    double d33 = complexDouble4._real;
                    double d34 = d33 * abs;
                    double d35 = d10 - d34;
                    double log3 = Math.log(Math.abs(d30 / abs)) / d33;
                    double log4 = Math.log(Math.abs(d30 / d35));
                    double d36 = log4;
                    for (int i2 = 0; i2 < 6; i2++) {
                        d36 = log4 - Math.log(Math.abs(d36 / d33));
                    }
                    double d37 = d36 / d33;
                    if (Double.isInfinite(log3) || Double.isNaN(log3)) {
                        z = false;
                        z2 = true;
                    } else {
                        z2 = true;
                        z = true;
                    }
                    if (!z) {
                        log3 = d37;
                    } else if (!(!((Double.isInfinite(d37) || Double.isNaN(d37)) ? false : z2))) {
                        log3 = Math.max(log3, d37);
                    }
                    double d38 = (-(d34 + d35)) / (d33 * d35);
                    double d39 = d33 * d38;
                    double d40 = log3;
                    double exp2 = (Math.exp(d39) * d35 * d38) + (Math.exp(d39) * abs);
                    if (Double.isNaN(d38) || d38 <= 0.0d) {
                        d30 = -d30;
                        d2 = d40;
                    } else if (d38 <= 0.0d || (-exp2) >= d30) {
                        d2 = (-(2.0d / d33)) - (abs / d35);
                    } else {
                        d2 = (d35 >= 0.0d || abs <= 0.0d) ? d40 : 0.0d;
                        d30 = -d30;
                    }
                    double d41 = Double.MAX_VALUE;
                    for (double d42 = 0.001d; d41 > d42 && i < 100; d42 = 0.001d) {
                        i++;
                        double d43 = d33 * d2;
                        double exp3 = d2 - (((Math.exp(d43) * ((d35 * d2) + abs)) + d30) / (Math.exp(d43) * (((d43 + 1) * d35) + d34)));
                        d41 = Math.abs(d2 - exp3);
                        d2 = exp3;
                    }
                    d3 = d2;
                }
            }
            j = (long) (d3 * 1000.0d);
        }
        return j * 1000000;
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final float getEndVelocity(float f, float f2, float f3) {
        return 0.0f;
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final float getValueFromNanos(long j, float f, float f2, float f3) {
        SpringSimulation springSimulation = this.spring;
        springSimulation.finalPosition = f2;
        return Float.intBitsToFloat((int) (springSimulation.m24updateValuesIJZedt4$animation_core_release(f, f3, j / 1000000) >> 32));
    }

    @Override // androidx.compose.animation.core.FloatAnimationSpec
    public final float getVelocityFromNanos(long j, float f, float f2, float f3) {
        SpringSimulation springSimulation = this.spring;
        springSimulation.finalPosition = f2;
        return Float.intBitsToFloat((int) (springSimulation.m24updateValuesIJZedt4$animation_core_release(f, f3, j / 1000000) & 4294967295L));
    }
}
