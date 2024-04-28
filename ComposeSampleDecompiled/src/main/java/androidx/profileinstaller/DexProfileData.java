package androidx.profileinstaller;

import java.util.TreeMap;

/* loaded from: classes.dex */
public final class DexProfileData {
    public final String apkName;
    public int classSetSize;
    public int[] classes;
    public final long dexChecksum;
    public final String dexName;
    public final int hotMethodRegionSize;
    public long mTypeIdCount = 0;
    public final TreeMap methods;
    public final int numMethodIds;

    public DexProfileData(String str, String str2, long j, int i, int i2, int i3, int[] iArr, TreeMap treeMap) {
        this.apkName = str;
        this.dexName = str2;
        this.dexChecksum = j;
        this.classSetSize = i;
        this.hotMethodRegionSize = i2;
        this.numMethodIds = i3;
        this.classes = iArr;
        this.methods = treeMap;
    }
}
