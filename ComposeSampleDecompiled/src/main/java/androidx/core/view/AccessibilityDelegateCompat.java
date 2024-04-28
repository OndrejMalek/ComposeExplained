package androidx.core.view;

import android.os.Build;
import android.os.Bundle;
import android.text.Spanned;
import android.text.style.ClickableSpan;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import eu.malek.composesample.R;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
public abstract class AccessibilityDelegateCompat {
    public static final View.AccessibilityDelegate DEFAULT_DELEGATE = new View.AccessibilityDelegate();
    public final View.AccessibilityDelegate mOriginalDelegate = DEFAULT_DELEGATE;
    public final AccessibilityDelegateAdapter mBridge = new AccessibilityDelegateAdapter(this);

    /* loaded from: classes.dex */
    public final class AccessibilityDelegateAdapter extends View.AccessibilityDelegate {
        public final AccessibilityDelegateCompat mCompat;

        public AccessibilityDelegateAdapter(AccessibilityDelegateCompat accessibilityDelegateCompat) {
            this.mCompat = accessibilityDelegateCompat;
        }

        @Override // android.view.View.AccessibilityDelegate
        public final boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            return this.mCompat.mOriginalDelegate.dispatchPopulateAccessibilityEvent(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public final AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
            AccessibilityNodeProviderCompat accessibilityNodeProvider = this.mCompat.getAccessibilityNodeProvider(view);
            if (accessibilityNodeProvider != null) {
                return (AccessibilityNodeProvider) accessibilityNodeProvider.mProvider;
            }
            return null;
        }

        @Override // android.view.View.AccessibilityDelegate
        public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.mCompat.mOriginalDelegate.onInitializeAccessibilityEvent(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
            Object tag;
            int i = ViewCompat.$r8$clinit;
            accessibilityNodeInfo.setScreenReaderFocusable(Boolean.valueOf(ViewCompat.Api28Impl.isScreenReaderFocusable(view)).booleanValue());
            accessibilityNodeInfo.setHeading(Boolean.valueOf(ViewCompat.Api28Impl.isAccessibilityHeading(view)).booleanValue());
            accessibilityNodeInfo.setPaneTitle(ViewCompat.Api28Impl.getAccessibilityPaneTitle(view));
            if (Build.VERSION.SDK_INT >= 30) {
                tag = ViewCompat.Api30Impl.getStateDescription(view);
            } else {
                tag = view.getTag(R.id.tag_state_description);
                if (!CharSequence.class.isInstance(tag)) {
                    tag = null;
                }
            }
            CharSequence charSequence = (CharSequence) tag;
            if (Build.VERSION.SDK_INT >= 30) {
                AccessibilityNodeInfoCompat.Api30Impl.setStateDescription(accessibilityNodeInfo, charSequence);
            } else {
                accessibilityNodeInfo.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY", charSequence);
            }
            AccessibilityDelegateCompat accessibilityDelegateCompat = this.mCompat;
            accessibilityDelegateCompat.getClass();
            accessibilityDelegateCompat.mOriginalDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
            accessibilityNodeInfo.getText();
            List list = (List) view.getTag(R.id.tag_accessibility_actions);
            if (list == null) {
                list = Collections.emptyList();
            }
            for (int i2 = 0; i2 < list.size(); i2++) {
                accessibilityNodeInfo.addAction((AccessibilityNodeInfo.AccessibilityAction) ((AccessibilityNodeInfoCompat.AccessibilityActionCompat) list.get(i2)).mAction);
            }
        }

        @Override // android.view.View.AccessibilityDelegate
        public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            this.mCompat.mOriginalDelegate.onPopulateAccessibilityEvent(view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public final boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
            return this.mCompat.mOriginalDelegate.onRequestSendAccessibilityEvent(viewGroup, view, accessibilityEvent);
        }

        @Override // android.view.View.AccessibilityDelegate
        public final boolean performAccessibilityAction(View view, int i, Bundle bundle) {
            WeakReference weakReference;
            ClickableSpan clickableSpan;
            AccessibilityDelegateCompat accessibilityDelegateCompat = this.mCompat;
            accessibilityDelegateCompat.getClass();
            List list = (List) view.getTag(R.id.tag_accessibility_actions);
            if (list == null) {
                list = Collections.emptyList();
            }
            boolean z = false;
            for (int i2 = 0; i2 < list.size() && ((AccessibilityNodeInfo.AccessibilityAction) ((AccessibilityNodeInfoCompat.AccessibilityActionCompat) list.get(i2)).mAction).getId() != i; i2++) {
            }
            boolean performAccessibilityAction = accessibilityDelegateCompat.mOriginalDelegate.performAccessibilityAction(view, i, bundle);
            if (performAccessibilityAction || i != R.id.accessibility_action_clickable_span || bundle == null) {
                return performAccessibilityAction;
            }
            int i3 = bundle.getInt("ACCESSIBILITY_CLICKABLE_SPAN_ID", -1);
            SparseArray sparseArray = (SparseArray) view.getTag(R.id.tag_accessibility_clickable_spans);
            if (sparseArray != null && (weakReference = (WeakReference) sparseArray.get(i3)) != null && (clickableSpan = (ClickableSpan) weakReference.get()) != null) {
                CharSequence text = view.createAccessibilityNodeInfo().getText();
                ClickableSpan[] clickableSpanArr = text instanceof Spanned ? (ClickableSpan[]) ((Spanned) text).getSpans(0, text.length(), ClickableSpan.class) : null;
                int i4 = 0;
                while (true) {
                    if (clickableSpanArr == null || i4 >= clickableSpanArr.length) {
                        break;
                    }
                    if (clickableSpan.equals(clickableSpanArr[i4])) {
                        clickableSpan.onClick(view);
                        z = true;
                        break;
                    }
                    i4++;
                }
            }
            return z;
        }

        @Override // android.view.View.AccessibilityDelegate
        public final void sendAccessibilityEvent(View view, int i) {
            this.mCompat.mOriginalDelegate.sendAccessibilityEvent(view, i);
        }

        @Override // android.view.View.AccessibilityDelegate
        public final void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
            this.mCompat.mOriginalDelegate.sendAccessibilityEventUnchecked(view, accessibilityEvent);
        }
    }

    public abstract AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view);
}
