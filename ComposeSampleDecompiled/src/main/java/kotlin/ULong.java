package kotlin;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ReportFragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.profileinstaller.ProfileInstaller$DiagnosticsCallback;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public final class ULong implements Comparable {
    public final long data;

    /* loaded from: classes.dex */
    public class Companion implements NodeCoordinator.HitTestSource, ProfileInstaller$DiagnosticsCallback {
        public static Companion DEFAULT;
        public final int $r8$classId;

        /* JADX DEBUG: Marked for inline */
        /* JADX DEBUG: Method not inlined, still used in: [androidx.compose.runtime.Stack.findBestInfo(int, androidx.core.provider.FontsContractCompat$FontInfo[]):androidx.core.provider.FontsContractCompat$FontInfo, androidx.core.view.SoftwareKeyboardControllerCompat$Impl20.<init>():void, androidx.core.view.WindowInsetsControllerCompat$Impl20.<init>(android.view.Window):void] */
        public /* synthetic */ Companion(int i) {
            this.$r8$classId = i;
        }

        public static void checkElementIndex$kotlin_stdlib(int i, int i2) {
            if (i < 0 || i >= i2) {
                throw new IndexOutOfBoundsException("index: " + i + ", size: " + i2);
            }
        }

        public static void checkPositionIndex$kotlin_stdlib(int i, int i2) {
            if (i < 0 || i > i2) {
                throw new IndexOutOfBoundsException("index: " + i + ", size: " + i2);
            }
        }

        public static void checkRangeIndexes$kotlin_stdlib(int i, int i2, int i3) {
            if (i < 0 || i2 > i3) {
                throw new IndexOutOfBoundsException("fromIndex: " + i + ", toIndex: " + i2 + ", size: " + i3);
            }
            if (i <= i2) {
                return;
            }
            throw new IllegalArgumentException("fromIndex: " + i + " > toIndex: " + i2);
        }

        public static SavedStateHandle createHandle(Bundle bundle, Bundle bundle2) {
            if (bundle == null) {
                if (bundle2 == null) {
                    return new SavedStateHandle();
                }
                HashMap hashMap = new HashMap();
                for (String str : bundle2.keySet()) {
                    ResultKt.checkNotNullExpressionValue(str, "key");
                    hashMap.put(str, bundle2.get(str));
                }
                return new SavedStateHandle(hashMap);
            }
            ClassLoader classLoader = SavedStateHandle.class.getClassLoader();
            ResultKt.checkNotNull(classLoader);
            bundle.setClassLoader(classLoader);
            ArrayList parcelableArrayList = bundle.getParcelableArrayList("keys");
            ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("values");
            if (parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) {
                throw new IllegalStateException("Invalid bundle passed as restored state".toString());
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int size = parcelableArrayList.size();
            for (int i = 0; i < size; i++) {
                Object obj = parcelableArrayList.get(i);
                ResultKt.checkNotNull(obj, "null cannot be cast to non-null type kotlin.String");
                linkedHashMap.put((String) obj, parcelableArrayList2.get(i));
            }
            return new SavedStateHandle(linkedHashMap);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: android.app.Activity */
        /* JADX WARN: Multi-variable type inference failed */
        public static void dispatch$lifecycle_runtime_release(Activity activity, Lifecycle.Event event) {
            ResultKt.checkNotNullParameter(activity, "activity");
            ResultKt.checkNotNullParameter(event, "event");
            if (activity instanceof LifecycleOwner) {
                LifecycleRegistry lifecycle = ((LifecycleOwner) activity).getLifecycle();
                if (lifecycle instanceof LifecycleRegistry) {
                    lifecycle.handleLifecycleEvent(event);
                }
            }
        }

        public static void injectIfNeededIn(Activity activity) {
            ResultKt.checkNotNullParameter(activity, "activity");
            if (Build.VERSION.SDK_INT >= 29) {
                ReportFragment.LifecycleCallbacks.Companion.getClass();
                activity.registerActivityLifecycleCallbacks(new ReportFragment.LifecycleCallbacks());
            }
            FragmentManager fragmentManager = activity.getFragmentManager();
            if (fragmentManager.findFragmentByTag("androidx.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
                fragmentManager.beginTransaction().add(new Fragment(), "androidx.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
                fragmentManager.executePendingTransactions();
            }
        }

        @Override // androidx.profileinstaller.ProfileInstaller$DiagnosticsCallback
        public void onDiagnosticReceived() {
            switch (this.$r8$classId) {
                case 19:
                    return;
                default:
                    Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
                    return;
            }
        }

        @Override // androidx.profileinstaller.ProfileInstaller$DiagnosticsCallback
        public void onResultReceived(int i, Object obj) {
            String str;
            switch (this.$r8$classId) {
                case 19:
                    return;
                default:
                    switch (i) {
                        case 1:
                            str = "RESULT_INSTALL_SUCCESS";
                            break;
                        case 2:
                            str = "RESULT_ALREADY_INSTALLED";
                            break;
                        case 3:
                            str = "RESULT_UNSUPPORTED_ART_VERSION";
                            break;
                        case 4:
                            str = "RESULT_NOT_WRITABLE";
                            break;
                        case 5:
                            str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                            break;
                        case 6:
                            str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                            break;
                        case 7:
                            str = "RESULT_IO_EXCEPTION";
                            break;
                        case 8:
                            str = "RESULT_PARSE_EXCEPTION";
                            break;
                        case 9:
                        default:
                            str = "";
                            break;
                        case 10:
                            str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                            break;
                        case 11:
                            str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                            break;
                    }
                    if (i == 6 || i == 7 || i == 8) {
                        Log.e("ProfileInstaller", str, (Throwable) obj);
                        return;
                    } else {
                        Log.d("ProfileInstaller", str);
                        return;
                    }
            }
        }

        public void setAppearanceLightStatusBars(boolean z) {
        }

        public Companion() {
            this.$r8$classId = 12;
        }
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        long j = ((ULong) obj).data;
        long j2 = this.data ^ Long.MIN_VALUE;
        long j3 = j ^ Long.MIN_VALUE;
        if (j2 < j3) {
            return -1;
        }
        return j2 == j3 ? 0 : 1;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof ULong) {
            return this.data == ((ULong) obj).data;
        }
        return false;
    }

    public final int hashCode() {
        return Long.hashCode(this.data);
    }

    public final String toString() {
        long j = this.data;
        if (j >= 0) {
            ResultKt.checkRadix();
            String l = Long.toString(j, 10);
            ResultKt.checkNotNullExpressionValue(l, "toString(this, checkRadix(radix))");
            return l;
        }
        long j2 = 10;
        long j3 = ((j >>> 1) / j2) << 1;
        long j4 = j - (j3 * j2);
        if (j4 >= j2) {
            j4 -= j2;
            j3++;
        }
        ResultKt.checkRadix();
        String l2 = Long.toString(j3, 10);
        ResultKt.checkNotNullExpressionValue(l2, "toString(this, checkRadix(radix))");
        ResultKt.checkRadix();
        String l3 = Long.toString(j4, 10);
        ResultKt.checkNotNullExpressionValue(l3, "toString(this, checkRadix(radix))");
        return l2.concat(l3);
    }
}
