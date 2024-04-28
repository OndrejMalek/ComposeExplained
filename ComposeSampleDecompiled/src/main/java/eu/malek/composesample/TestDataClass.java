package eu.malek.composesample;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class TestDataClass {
    public final TestDataClass dp54jh45;
    public final String redundant1;
    public final String redundant2;
    public final String redundant3;
    public final String redundant4;
    public final String text8798SDF;

    public TestDataClass(String str, String str2, String str3, String str4, String str5, TestDataClass testDataClass) {
        this.text8798SDF = str;
        this.redundant1 = str2;
        this.redundant2 = str3;
        this.redundant3 = str4;
        this.redundant4 = str5;
        this.dp54jh45 = testDataClass;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TestDataClass)) {
            return false;
        }
        TestDataClass testDataClass = (TestDataClass) obj;
        return ResultKt.areEqual(this.text8798SDF, testDataClass.text8798SDF) && ResultKt.areEqual(this.redundant1, testDataClass.redundant1) && ResultKt.areEqual(this.redundant2, testDataClass.redundant2) && ResultKt.areEqual(this.redundant3, testDataClass.redundant3) && ResultKt.areEqual(this.redundant4, testDataClass.redundant4) && ResultKt.areEqual(this.dp54jh45, testDataClass.dp54jh45);
    }

    public final int hashCode() {
        int hashCode = (this.redundant4.hashCode() + ((this.redundant3.hashCode() + ((this.redundant2.hashCode() + ((this.redundant1.hashCode() + (this.text8798SDF.hashCode() * 31)) * 31)) * 31)) * 31)) * 31;
        TestDataClass testDataClass = this.dp54jh45;
        return hashCode + (testDataClass == null ? 0 : testDataClass.hashCode());
    }

    public final String toString() {
        return "TestDataClass(text8798SDF=" + this.text8798SDF + ", redundant1=" + this.redundant1 + ", redundant2=" + this.redundant2 + ", redundant3=" + this.redundant3 + ", redundant4=" + this.redundant4 + ", dp54jh45=" + this.dp54jh45 + ')';
    }
}
