package androidx.core.view;

import android.view.View;
import androidx.collection.SimpleArrayMap;
import androidx.core.view.autofill.AutofillIdCompat;
import eu.malek.composesample.R;
import java.util.Objects;
import java.util.WeakHashMap;

/* loaded from: classes.dex */
public abstract class ViewCompat {
    public static final /* synthetic */ int $r8$clinit = 0;

    /* loaded from: classes.dex */
    public abstract class Api28Impl {
        /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: androidx.collection.SimpleArrayMap */
        /* JADX DEBUG: Multi-variable search result rejected for r1v4, resolved type: androidx.collection.SimpleArrayMap */
        /* JADX DEBUG: Multi-variable search result rejected for r1v5, resolved type: androidx.collection.SimpleArrayMap */
        /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: android.view.View */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [android.view.View$OnUnhandledKeyEventListener, java.lang.Object] */
        public static void addOnUnhandledKeyEventListener(View view, OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) view.getTag(R.id.tag_unhandled_key_listeners);
            SimpleArrayMap simpleArrayMap2 = simpleArrayMap;
            if (simpleArrayMap == null) {
                SimpleArrayMap simpleArrayMap3 = new SimpleArrayMap();
                view.setTag(R.id.tag_unhandled_key_listeners, simpleArrayMap3);
                simpleArrayMap2 = simpleArrayMap3;
            }
            Objects.requireNonNull(onUnhandledKeyEventListenerCompat);
            ?? obj = new Object();
            simpleArrayMap2.put(onUnhandledKeyEventListenerCompat, obj);
            view.addOnUnhandledKeyEventListener(obj);
        }

        public static CharSequence getAccessibilityPaneTitle(View view) {
            return view.getAccessibilityPaneTitle();
        }

        public static boolean isAccessibilityHeading(View view) {
            return view.isAccessibilityHeading();
        }

        public static boolean isScreenReaderFocusable(View view) {
            return view.isScreenReaderFocusable();
        }

        public static void removeOnUnhandledKeyEventListener(View view, OnUnhandledKeyEventListenerCompat onUnhandledKeyEventListenerCompat) {
            View.OnUnhandledKeyEventListener onUnhandledKeyEventListener;
            SimpleArrayMap simpleArrayMap = (SimpleArrayMap) view.getTag(R.id.tag_unhandled_key_listeners);
            if (simpleArrayMap == null || (onUnhandledKeyEventListener = (View.OnUnhandledKeyEventListener) simpleArrayMap.getOrDefault(onUnhandledKeyEventListenerCompat, null)) == null) {
                return;
            }
            view.removeOnUnhandledKeyEventListener(onUnhandledKeyEventListener);
        }

        public static <T> T requireViewById(View view, int i) {
            return (T) view.requireViewById(i);
        }

        public static void setAccessibilityHeading(View view, boolean z) {
            view.setAccessibilityHeading(z);
        }

        public static void setAccessibilityPaneTitle(View view, CharSequence charSequence) {
            view.setAccessibilityPaneTitle(charSequence);
        }

        public static void setAutofillId(View view, AutofillIdCompat autofillIdCompat) {
            view.setAutofillId(null);
        }

        public static void setScreenReaderFocusable(View view, boolean z) {
            view.setScreenReaderFocusable(z);
        }
    }

    /* loaded from: classes.dex */
    public abstract class Api30Impl {
        public static int getImportantForContentCapture(View view) {
            return view.getImportantForContentCapture();
        }

        public static CharSequence getStateDescription(View view) {
            return view.getStateDescription();
        }

        public static boolean isImportantForContentCapture(View view) {
            return view.isImportantForContentCapture();
        }

        public static void setImportantForContentCapture(View view, int i) {
            view.setImportantForContentCapture(i);
        }

        public static void setStateDescription(View view, CharSequence charSequence) {
            view.setStateDescription(charSequence);
        }
    }

    /* loaded from: classes.dex */
    public interface OnUnhandledKeyEventListenerCompat {
    }

    static {
        new WeakHashMap();
    }
}
