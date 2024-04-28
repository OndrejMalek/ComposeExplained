package androidx.activity;

import android.os.Build;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import java.util.ListIterator;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class OnBackPressedDispatcher {
    public boolean backInvokedCallbackRegistered;
    public final Runnable fallbackOnBackPressed;
    public final OnBackInvokedCallback onBackInvokedCallback;
    public final ArrayDeque onBackPressedCallbacks = new ArrayDeque();

    /* loaded from: classes.dex */
    public final class Api33Impl {
        public static final Api33Impl INSTANCE = new Object();

        public final OnBackInvokedCallback createOnBackInvokedCallback(final Function0 function0) {
            ResultKt.checkNotNullParameter(function0, "onBackInvoked");
            return new OnBackInvokedCallback() { // from class: androidx.activity.OnBackPressedDispatcher$Api33Impl$$ExternalSyntheticLambda0
                public final void onBackInvoked() {
                    Function0 function02 = Function0.this;
                    ResultKt.checkNotNullParameter(function02, "$onBackInvoked");
                    function02.invoke();
                }
            };
        }

        public final void registerOnBackInvokedCallback(Object obj, int i, Object obj2) {
            ResultKt.checkNotNullParameter(obj, "dispatcher");
            ResultKt.checkNotNullParameter(obj2, "callback");
            ((OnBackInvokedDispatcher) obj).registerOnBackInvokedCallback(i, (OnBackInvokedCallback) obj2);
        }

        public final void unregisterOnBackInvokedCallback(Object obj, Object obj2) {
            ResultKt.checkNotNullParameter(obj, "dispatcher");
            ResultKt.checkNotNullParameter(obj2, "callback");
            ((OnBackInvokedDispatcher) obj).unregisterOnBackInvokedCallback((OnBackInvokedCallback) obj2);
        }
    }

    /* loaded from: classes.dex */
    public final class Api34Impl {
        public static final Api34Impl INSTANCE = new Object();

        public final OnBackInvokedCallback createOnBackAnimationCallback(final Function1 function1, final Function1 function12, final Function0 function0, final Function0 function02) {
            ResultKt.checkNotNullParameter(function1, "onBackStarted");
            ResultKt.checkNotNullParameter(function12, "onBackProgressed");
            ResultKt.checkNotNullParameter(function0, "onBackInvoked");
            ResultKt.checkNotNullParameter(function02, "onBackCancelled");
            return new OnBackAnimationCallback() { // from class: androidx.activity.OnBackPressedDispatcher$Api34Impl$createOnBackAnimationCallback$1
                public final void onBackCancelled() {
                    function02.invoke();
                }

                public final void onBackInvoked() {
                    function0.invoke();
                }

                public final void onBackProgressed(BackEvent backEvent) {
                    ResultKt.checkNotNullParameter(backEvent, "backEvent");
                    function12.invoke(new BackEventCompat(backEvent));
                }

                public final void onBackStarted(BackEvent backEvent) {
                    ResultKt.checkNotNullParameter(backEvent, "backEvent");
                    Function1.this.invoke(new BackEventCompat(backEvent));
                }
            };
        }
    }

    public OnBackPressedDispatcher(ComponentActivity$$ExternalSyntheticLambda0 componentActivity$$ExternalSyntheticLambda0) {
        OnBackInvokedCallback createOnBackInvokedCallback;
        this.fallbackOnBackPressed = componentActivity$$ExternalSyntheticLambda0;
        int i = Build.VERSION.SDK_INT;
        if (i >= 33) {
            if (i >= 34) {
                final int i2 = 0;
                final int i3 = 1;
                createOnBackInvokedCallback = Api34Impl.INSTANCE.createOnBackAnimationCallback(new Function1(this) { // from class: androidx.activity.OnBackPressedDispatcher.1
                    public final /* synthetic */ OnBackPressedDispatcher this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                        this.this$0 = this;
                    }

                    public final void invoke(BackEventCompat backEventCompat) {
                        int i4 = i2;
                        OnBackPressedDispatcher onBackPressedDispatcher = this.this$0;
                        switch (i4) {
                            case 0:
                                ResultKt.checkNotNullParameter(backEventCompat, "backEvent");
                                ArrayDeque arrayDeque = onBackPressedDispatcher.onBackPressedCallbacks;
                                ListIterator listIterator = arrayDeque.listIterator(arrayDeque.getSize());
                                if (listIterator.hasPrevious()) {
                                    DurationKt$$ExternalSyntheticCheckNotZero0.m(listIterator.previous());
                                    throw null;
                                }
                                return;
                            default:
                                ResultKt.checkNotNullParameter(backEventCompat, "backEvent");
                                ArrayDeque arrayDeque2 = onBackPressedDispatcher.onBackPressedCallbacks;
                                ListIterator listIterator2 = arrayDeque2.listIterator(arrayDeque2.getSize());
                                if (listIterator2.hasPrevious()) {
                                    DurationKt$$ExternalSyntheticCheckNotZero0.m(listIterator2.previous());
                                    throw null;
                                }
                                return;
                        }
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        Unit unit = Unit.INSTANCE;
                        switch (i2) {
                            case 0:
                                invoke((BackEventCompat) obj);
                                return unit;
                            default:
                                invoke((BackEventCompat) obj);
                                return unit;
                        }
                    }
                }, new Function1(this) { // from class: androidx.activity.OnBackPressedDispatcher.1
                    public final /* synthetic */ OnBackPressedDispatcher this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                        this.this$0 = this;
                    }

                    public final void invoke(BackEventCompat backEventCompat) {
                        int i4 = i3;
                        OnBackPressedDispatcher onBackPressedDispatcher = this.this$0;
                        switch (i4) {
                            case 0:
                                ResultKt.checkNotNullParameter(backEventCompat, "backEvent");
                                ArrayDeque arrayDeque = onBackPressedDispatcher.onBackPressedCallbacks;
                                ListIterator listIterator = arrayDeque.listIterator(arrayDeque.getSize());
                                if (listIterator.hasPrevious()) {
                                    DurationKt$$ExternalSyntheticCheckNotZero0.m(listIterator.previous());
                                    throw null;
                                }
                                return;
                            default:
                                ResultKt.checkNotNullParameter(backEventCompat, "backEvent");
                                ArrayDeque arrayDeque2 = onBackPressedDispatcher.onBackPressedCallbacks;
                                ListIterator listIterator2 = arrayDeque2.listIterator(arrayDeque2.getSize());
                                if (listIterator2.hasPrevious()) {
                                    DurationKt$$ExternalSyntheticCheckNotZero0.m(listIterator2.previous());
                                    throw null;
                                }
                                return;
                        }
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        Unit unit = Unit.INSTANCE;
                        switch (i3) {
                            case 0:
                                invoke((BackEventCompat) obj);
                                return unit;
                            default:
                                invoke((BackEventCompat) obj);
                                return unit;
                        }
                    }
                }, new Function0(this) { // from class: androidx.activity.OnBackPressedDispatcher.3
                    public final /* synthetic */ OnBackPressedDispatcher this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                        this.this$0 = this;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit unit = Unit.INSTANCE;
                        OnBackPressedDispatcher onBackPressedDispatcher = this.this$0;
                        int i4 = i2;
                        switch (i4) {
                            case 0:
                                switch (i4) {
                                    case 0:
                                        onBackPressedDispatcher.onBackPressed();
                                        return unit;
                                    case 1:
                                        ArrayDeque arrayDeque = onBackPressedDispatcher.onBackPressedCallbacks;
                                        ListIterator listIterator = arrayDeque.listIterator(arrayDeque.getSize());
                                        if (listIterator.hasPrevious()) {
                                            DurationKt$$ExternalSyntheticCheckNotZero0.m(listIterator.previous());
                                            throw null;
                                        }
                                        return unit;
                                    default:
                                        onBackPressedDispatcher.onBackPressed();
                                        return unit;
                                }
                            case 1:
                                switch (i4) {
                                    case 0:
                                        onBackPressedDispatcher.onBackPressed();
                                        return unit;
                                    case 1:
                                        ArrayDeque arrayDeque2 = onBackPressedDispatcher.onBackPressedCallbacks;
                                        ListIterator listIterator2 = arrayDeque2.listIterator(arrayDeque2.getSize());
                                        if (listIterator2.hasPrevious()) {
                                            DurationKt$$ExternalSyntheticCheckNotZero0.m(listIterator2.previous());
                                            throw null;
                                        }
                                        return unit;
                                    default:
                                        onBackPressedDispatcher.onBackPressed();
                                        return unit;
                                }
                            default:
                                switch (i4) {
                                    case 0:
                                        onBackPressedDispatcher.onBackPressed();
                                        return unit;
                                    case 1:
                                        ArrayDeque arrayDeque3 = onBackPressedDispatcher.onBackPressedCallbacks;
                                        ListIterator listIterator3 = arrayDeque3.listIterator(arrayDeque3.getSize());
                                        if (listIterator3.hasPrevious()) {
                                            DurationKt$$ExternalSyntheticCheckNotZero0.m(listIterator3.previous());
                                            throw null;
                                        }
                                        return unit;
                                    default:
                                        onBackPressedDispatcher.onBackPressed();
                                        return unit;
                                }
                        }
                    }
                }, new Function0(this) { // from class: androidx.activity.OnBackPressedDispatcher.3
                    public final /* synthetic */ OnBackPressedDispatcher this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                        this.this$0 = this;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit unit = Unit.INSTANCE;
                        OnBackPressedDispatcher onBackPressedDispatcher = this.this$0;
                        int i4 = i3;
                        switch (i4) {
                            case 0:
                                switch (i4) {
                                    case 0:
                                        onBackPressedDispatcher.onBackPressed();
                                        return unit;
                                    case 1:
                                        ArrayDeque arrayDeque = onBackPressedDispatcher.onBackPressedCallbacks;
                                        ListIterator listIterator = arrayDeque.listIterator(arrayDeque.getSize());
                                        if (listIterator.hasPrevious()) {
                                            DurationKt$$ExternalSyntheticCheckNotZero0.m(listIterator.previous());
                                            throw null;
                                        }
                                        return unit;
                                    default:
                                        onBackPressedDispatcher.onBackPressed();
                                        return unit;
                                }
                            case 1:
                                switch (i4) {
                                    case 0:
                                        onBackPressedDispatcher.onBackPressed();
                                        return unit;
                                    case 1:
                                        ArrayDeque arrayDeque2 = onBackPressedDispatcher.onBackPressedCallbacks;
                                        ListIterator listIterator2 = arrayDeque2.listIterator(arrayDeque2.getSize());
                                        if (listIterator2.hasPrevious()) {
                                            DurationKt$$ExternalSyntheticCheckNotZero0.m(listIterator2.previous());
                                            throw null;
                                        }
                                        return unit;
                                    default:
                                        onBackPressedDispatcher.onBackPressed();
                                        return unit;
                                }
                            default:
                                switch (i4) {
                                    case 0:
                                        onBackPressedDispatcher.onBackPressed();
                                        return unit;
                                    case 1:
                                        ArrayDeque arrayDeque3 = onBackPressedDispatcher.onBackPressedCallbacks;
                                        ListIterator listIterator3 = arrayDeque3.listIterator(arrayDeque3.getSize());
                                        if (listIterator3.hasPrevious()) {
                                            DurationKt$$ExternalSyntheticCheckNotZero0.m(listIterator3.previous());
                                            throw null;
                                        }
                                        return unit;
                                    default:
                                        onBackPressedDispatcher.onBackPressed();
                                        return unit;
                                }
                        }
                    }
                });
            } else {
                final int i4 = 2;
                createOnBackInvokedCallback = Api33Impl.INSTANCE.createOnBackInvokedCallback(new Function0(this) { // from class: androidx.activity.OnBackPressedDispatcher.3
                    public final /* synthetic */ OnBackPressedDispatcher this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                        this.this$0 = this;
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Unit unit = Unit.INSTANCE;
                        OnBackPressedDispatcher onBackPressedDispatcher = this.this$0;
                        int i42 = i4;
                        switch (i42) {
                            case 0:
                                switch (i42) {
                                    case 0:
                                        onBackPressedDispatcher.onBackPressed();
                                        return unit;
                                    case 1:
                                        ArrayDeque arrayDeque = onBackPressedDispatcher.onBackPressedCallbacks;
                                        ListIterator listIterator = arrayDeque.listIterator(arrayDeque.getSize());
                                        if (listIterator.hasPrevious()) {
                                            DurationKt$$ExternalSyntheticCheckNotZero0.m(listIterator.previous());
                                            throw null;
                                        }
                                        return unit;
                                    default:
                                        onBackPressedDispatcher.onBackPressed();
                                        return unit;
                                }
                            case 1:
                                switch (i42) {
                                    case 0:
                                        onBackPressedDispatcher.onBackPressed();
                                        return unit;
                                    case 1:
                                        ArrayDeque arrayDeque2 = onBackPressedDispatcher.onBackPressedCallbacks;
                                        ListIterator listIterator2 = arrayDeque2.listIterator(arrayDeque2.getSize());
                                        if (listIterator2.hasPrevious()) {
                                            DurationKt$$ExternalSyntheticCheckNotZero0.m(listIterator2.previous());
                                            throw null;
                                        }
                                        return unit;
                                    default:
                                        onBackPressedDispatcher.onBackPressed();
                                        return unit;
                                }
                            default:
                                switch (i42) {
                                    case 0:
                                        onBackPressedDispatcher.onBackPressed();
                                        return unit;
                                    case 1:
                                        ArrayDeque arrayDeque3 = onBackPressedDispatcher.onBackPressedCallbacks;
                                        ListIterator listIterator3 = arrayDeque3.listIterator(arrayDeque3.getSize());
                                        if (listIterator3.hasPrevious()) {
                                            DurationKt$$ExternalSyntheticCheckNotZero0.m(listIterator3.previous());
                                            throw null;
                                        }
                                        return unit;
                                    default:
                                        onBackPressedDispatcher.onBackPressed();
                                        return unit;
                                }
                        }
                    }
                });
            }
            this.onBackInvokedCallback = createOnBackInvokedCallback;
        }
    }

    public final void onBackPressed() {
        ArrayDeque arrayDeque = this.onBackPressedCallbacks;
        ListIterator listIterator = arrayDeque.listIterator(arrayDeque.getSize());
        if (listIterator.hasPrevious()) {
            DurationKt$$ExternalSyntheticCheckNotZero0.m(listIterator.previous());
            throw null;
        }
        Runnable runnable = this.fallbackOnBackPressed;
        if (runnable != null) {
            runnable.run();
        }
    }
}
