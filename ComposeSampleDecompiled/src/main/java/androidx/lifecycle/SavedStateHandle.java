package androidx.lifecycle;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import androidx.core.os.BundleApi21ImplKt;
import androidx.savedstate.SavedStateRegistry;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.collections.EmptyMap;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* loaded from: classes.dex */
public final class SavedStateHandle {
    public static final Class[] ACCEPTABLE_CLASSES = {Boolean.TYPE, boolean[].class, Double.TYPE, double[].class, Integer.TYPE, int[].class, Long.TYPE, long[].class, String.class, String[].class, Binder.class, Bundle.class, Byte.TYPE, byte[].class, Character.TYPE, char[].class, CharSequence.class, CharSequence[].class, ArrayList.class, Float.TYPE, float[].class, Parcelable.class, Parcelable[].class, Serializable.class, Short.TYPE, short[].class, SparseArray.class, Size.class, SizeF.class};
    public final LinkedHashMap flows;
    public final LinkedHashMap liveDatas;
    public final LinkedHashMap regular;
    public final SavedStateHandle$$ExternalSyntheticLambda0 savedStateProvider;
    public final LinkedHashMap savedStateProviders;

    public static Bundle $r8$lambda$eeLDsk5Qp_lgSAYrhUViF2PFB0k(SavedStateHandle savedStateHandle) {
        Map map;
        ResultKt.checkNotNullParameter(savedStateHandle, "this$0");
        LinkedHashMap linkedHashMap = savedStateHandle.savedStateProviders;
        ResultKt.checkNotNullParameter(linkedHashMap, "<this>");
        int size = linkedHashMap.size();
        if (size == 0) {
            map = EmptyMap.INSTANCE;
        } else if (size != 1) {
            map = new LinkedHashMap(linkedHashMap);
        } else {
            Map.Entry entry = (Map.Entry) linkedHashMap.entrySet().iterator().next();
            map = Collections.singletonMap(entry.getKey(), entry.getValue());
            ResultKt.checkNotNullExpressionValue(map, "with(entries.iterator().…ingletonMap(key, value) }");
        }
        Iterator it = map.entrySet().iterator();
        while (true) {
            boolean hasNext = it.hasNext();
            LinkedHashMap linkedHashMap2 = savedStateHandle.regular;
            int i = 0;
            if (hasNext) {
                Map.Entry entry2 = (Map.Entry) it.next();
                String str = (String) entry2.getKey();
                Bundle saveState = ((SavedStateRegistry.SavedStateProvider) entry2.getValue()).saveState();
                ResultKt.checkNotNullParameter(str, "key");
                Class[] clsArr = ACCEPTABLE_CLASSES;
                while (i < 29) {
                    Class cls = clsArr[i];
                    ResultKt.checkNotNull(cls);
                    if (cls.isInstance(saveState)) {
                        savedStateHandle.liveDatas.get(str);
                        linkedHashMap2.put(str, saveState);
                        MutableStateFlow mutableStateFlow = (MutableStateFlow) savedStateHandle.flows.get(str);
                        if (mutableStateFlow != null) {
                            ((StateFlowImpl) mutableStateFlow).setValue(saveState);
                        }
                    } else {
                        i++;
                    }
                }
                throw new IllegalArgumentException("Can't put value with type " + saveState.getClass() + " into saved state");
            }
            Set<String> keySet = linkedHashMap2.keySet();
            ArrayList arrayList = new ArrayList(keySet.size());
            ArrayList arrayList2 = new ArrayList(arrayList.size());
            for (String str2 : keySet) {
                arrayList.add(str2);
                arrayList2.add(linkedHashMap2.get(str2));
            }
            Pair[] pairArr = {new Pair("keys", arrayList), new Pair("values", arrayList2)};
            Bundle bundle = new Bundle(2);
            while (i < 2) {
                Pair pair = pairArr[i];
                String str3 = (String) pair.first;
                Object obj = pair.second;
                if (obj == null) {
                    bundle.putString(str3, null);
                } else if (obj instanceof Boolean) {
                    bundle.putBoolean(str3, ((Boolean) obj).booleanValue());
                } else if (obj instanceof Byte) {
                    bundle.putByte(str3, ((Number) obj).byteValue());
                } else if (obj instanceof Character) {
                    bundle.putChar(str3, ((Character) obj).charValue());
                } else if (obj instanceof Double) {
                    bundle.putDouble(str3, ((Number) obj).doubleValue());
                } else if (obj instanceof Float) {
                    bundle.putFloat(str3, ((Number) obj).floatValue());
                } else if (obj instanceof Integer) {
                    bundle.putInt(str3, ((Number) obj).intValue());
                } else if (obj instanceof Long) {
                    bundle.putLong(str3, ((Number) obj).longValue());
                } else if (obj instanceof Short) {
                    bundle.putShort(str3, ((Number) obj).shortValue());
                } else if (obj instanceof Bundle) {
                    bundle.putBundle(str3, (Bundle) obj);
                } else if (obj instanceof CharSequence) {
                    bundle.putCharSequence(str3, (CharSequence) obj);
                } else if (obj instanceof Parcelable) {
                    bundle.putParcelable(str3, (Parcelable) obj);
                } else if (obj instanceof boolean[]) {
                    bundle.putBooleanArray(str3, (boolean[]) obj);
                } else if (obj instanceof byte[]) {
                    bundle.putByteArray(str3, (byte[]) obj);
                } else if (obj instanceof char[]) {
                    bundle.putCharArray(str3, (char[]) obj);
                } else if (obj instanceof double[]) {
                    bundle.putDoubleArray(str3, (double[]) obj);
                } else if (obj instanceof float[]) {
                    bundle.putFloatArray(str3, (float[]) obj);
                } else if (obj instanceof int[]) {
                    bundle.putIntArray(str3, (int[]) obj);
                } else if (obj instanceof long[]) {
                    bundle.putLongArray(str3, (long[]) obj);
                } else if (obj instanceof short[]) {
                    bundle.putShortArray(str3, (short[]) obj);
                } else if (obj instanceof Object[]) {
                    Class<?> componentType = obj.getClass().getComponentType();
                    ResultKt.checkNotNull(componentType);
                    if (Parcelable.class.isAssignableFrom(componentType)) {
                        bundle.putParcelableArray(str3, (Parcelable[]) obj);
                    } else if (String.class.isAssignableFrom(componentType)) {
                        bundle.putStringArray(str3, (String[]) obj);
                    } else if (CharSequence.class.isAssignableFrom(componentType)) {
                        bundle.putCharSequenceArray(str3, (CharSequence[]) obj);
                    } else {
                        if (!Serializable.class.isAssignableFrom(componentType)) {
                            throw new IllegalArgumentException("Illegal value array type " + componentType.getCanonicalName() + " for key \"" + str3 + '\"');
                        }
                        bundle.putSerializable(str3, (Serializable) obj);
                    }
                } else if (obj instanceof Serializable) {
                    bundle.putSerializable(str3, (Serializable) obj);
                } else if (obj instanceof IBinder) {
                    bundle.putBinder(str3, (IBinder) obj);
                } else if (obj instanceof Size) {
                    BundleApi21ImplKt.putSize(bundle, str3, (Size) obj);
                } else {
                    if (!(obj instanceof SizeF)) {
                        throw new IllegalArgumentException("Illegal value type " + obj.getClass().getCanonicalName() + " for key \"" + str3 + '\"');
                    }
                    BundleApi21ImplKt.putSizeF(bundle, str3, (SizeF) obj);
                }
                i++;
            }
            return bundle;
        }
    }

    public SavedStateHandle(HashMap hashMap) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.regular = linkedHashMap;
        this.savedStateProviders = new LinkedHashMap();
        this.liveDatas = new LinkedHashMap();
        this.flows = new LinkedHashMap();
        this.savedStateProvider = new SavedStateHandle$$ExternalSyntheticLambda0(1, this);
        linkedHashMap.putAll(hashMap);
    }

    public SavedStateHandle() {
        this.regular = new LinkedHashMap();
        this.savedStateProviders = new LinkedHashMap();
        this.liveDatas = new LinkedHashMap();
        this.flows = new LinkedHashMap();
        this.savedStateProvider = new SavedStateHandle$$ExternalSyntheticLambda0(0, this);
    }
}
