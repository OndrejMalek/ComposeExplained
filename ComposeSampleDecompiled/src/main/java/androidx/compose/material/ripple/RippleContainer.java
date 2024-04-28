package androidx.compose.material.ripple;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.ui.platform.WeakCache;
import eu.malek.composesample.R;
import java.util.ArrayList;

/* loaded from: classes.dex */
public final class RippleContainer extends ViewGroup {
    public final int MaxRippleHosts;
    public int nextHostIndex;
    public final WeakCache rippleHostMap;
    public final ArrayList rippleHosts;
    public final ArrayList unusedRippleHosts;

    public RippleContainer(Context context) {
        super(context);
        this.MaxRippleHosts = 5;
        ArrayList arrayList = new ArrayList();
        this.rippleHosts = arrayList;
        ArrayList arrayList2 = new ArrayList();
        this.unusedRippleHosts = arrayList2;
        this.rippleHostMap = new WeakCache(1);
        setClipChildren(false);
        View view = new View(context);
        addView(view);
        arrayList.add(view);
        arrayList2.add(view);
        this.nextHostIndex = 1;
        setTag(R.id.hide_in_inspector_tag, Boolean.TRUE);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        setMeasuredDimension(0, 0);
    }
}
