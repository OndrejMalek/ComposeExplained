package eu.malek.composesample2;

import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class DataPackage {
    public final DataPackage dp;
    public final String redundant1;
    public final String redundant2;
    public final String redundant3;
    public final String redundant4;
    public final String text8798SDF;

    public DataPackage(String str, String str2, String str3, String str4, String str5, DataPackage dataPackage) {
        this.text8798SDF = str;
        this.redundant1 = str2;
        this.redundant2 = str3;
        this.redundant3 = str4;
        this.redundant4 = str5;
        this.dp = dataPackage;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DataPackage)) {
            return false;
        }
        DataPackage dataPackage = (DataPackage) obj;
        return ResultKt.areEqual(this.text8798SDF, dataPackage.text8798SDF) && ResultKt.areEqual(this.redundant1, dataPackage.redundant1) && ResultKt.areEqual(this.redundant2, dataPackage.redundant2) && ResultKt.areEqual(this.redundant3, dataPackage.redundant3) && ResultKt.areEqual(this.redundant4, dataPackage.redundant4) && ResultKt.areEqual(this.dp, dataPackage.dp);
    }

    public final int hashCode() {
        int hashCode = (this.redundant4.hashCode() + ((this.redundant3.hashCode() + ((this.redundant2.hashCode() + ((this.redundant1.hashCode() + (this.text8798SDF.hashCode() * 31)) * 31)) * 31)) * 31)) * 31;
        DataPackage dataPackage = this.dp;
        return hashCode + (dataPackage == null ? 0 : dataPackage.hashCode());
    }

    public final String toString() {
        return "DataPackage(text8798SDF=" + this.text8798SDF + ", redundant1=" + this.redundant1 + ", redundant2=" + this.redundant2 + ", redundant3=" + this.redundant3 + ", redundant4=" + this.redundant4 + ", dp=" + this.dp + ')';
    }
}
