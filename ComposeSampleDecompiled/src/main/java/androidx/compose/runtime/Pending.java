package androidx.compose.runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.ResultKt;
import kotlin.SynchronizedLazyImpl;

/* loaded from: classes.dex */
public final class Pending {
    public int groupIndex;
    public final HashMap groupInfos;
    public final List keyInfos;
    public final SynchronizedLazyImpl keyMap$delegate;
    public final int startIndex;
    public final ArrayList usedKeys;

    public Pending(int i, ArrayList arrayList) {
        this.keyInfos = arrayList;
        this.startIndex = i;
        if (i < 0) {
            throw new IllegalArgumentException("Invalid start index".toString());
        }
        this.usedKeys = new ArrayList();
        HashMap hashMap = new HashMap();
        int size = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            KeyInfo keyInfo = (KeyInfo) this.keyInfos.get(i4);
            Integer valueOf = Integer.valueOf(keyInfo.location);
            int i5 = keyInfo.nodes;
            hashMap.put(valueOf, new GroupInfo(i4, i3, i5));
            i3 += i5;
        }
        this.groupInfos = hashMap;
        this.keyMap$delegate = new SynchronizedLazyImpl(new Pending$keyMap$2(i2, this));
    }

    public final int nodePositionOf(KeyInfo keyInfo) {
        ResultKt.checkNotNullParameter(keyInfo, "keyInfo");
        GroupInfo groupInfo = (GroupInfo) this.groupInfos.get(Integer.valueOf(keyInfo.location));
        if (groupInfo != null) {
            return groupInfo.nodeIndex;
        }
        return -1;
    }

    public final boolean updateNodeCount(int i, int i2) {
        int i3;
        HashMap hashMap = this.groupInfos;
        GroupInfo groupInfo = (GroupInfo) hashMap.get(Integer.valueOf(i));
        if (groupInfo == null) {
            return false;
        }
        int i4 = groupInfo.nodeIndex;
        int i5 = i2 - groupInfo.nodeCount;
        groupInfo.nodeCount = i2;
        if (i5 == 0) {
            return true;
        }
        Collection<GroupInfo> values = hashMap.values();
        ResultKt.checkNotNullExpressionValue(values, "groupInfos.values");
        for (GroupInfo groupInfo2 : values) {
            if (groupInfo2.nodeIndex >= i4 && !ResultKt.areEqual(groupInfo2, groupInfo) && (i3 = groupInfo2.nodeIndex + i5) >= 0) {
                groupInfo2.nodeIndex = i3;
            }
        }
        return true;
    }
}
