package kotlin.time;

import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.StructuralEqualityPolicy;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.input.key.Key_androidKt;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ServiceConfigurationError;
import kotlin.ResultKt;
import kotlinx.coroutines.android.AndroidDispatcherFactory;
import kotlinx.coroutines.android.AndroidExceptionPreHandler;

/* loaded from: classes.dex */
public abstract /* synthetic */ class DurationKt$$ExternalSyntheticCheckNotZero0 {
    public static /* synthetic */ long getMValue(int i) {
        if (i == 1) {
            return 0L;
        }
        if (i == 2) {
            return 1L;
        }
        if (i == 3) {
            return 2L;
        }
        if (i == 4) {
            return 3L;
        }
        if (i == 5) {
            return 4L;
        }
        throw null;
    }

    public static ParcelableSnapshotMutableState m(long j, StructuralEqualityPolicy structuralEqualityPolicy) {
        return ResultKt.mutableStateOf(new Color(j), structuralEqualityPolicy);
    }

    public static /* synthetic */ Iterator m$1() {
        try {
            return Arrays.asList(new AndroidDispatcherFactory()).iterator();
        } catch (Throwable th) {
            throw new ServiceConfigurationError(th.getMessage(), th);
        }
    }

    public static /* synthetic */ String stringValueOf(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? "null" : "Idle" : "LookaheadLayingOut" : "LayingOut" : "LookaheadMeasuring" : "Measuring";
    }

    public static /* synthetic */ void m$1(Object obj) {
        throw new ClassCastException();
    }

    public static /* synthetic */ Iterator m() {
        try {
            return Arrays.asList(new AndroidExceptionPreHandler()).iterator();
        } catch (Throwable th) {
            throw new ServiceConfigurationError(th.getMessage(), th);
        }
    }

    public static void m(int i, int i2, int i3, int i4, int i5) {
        Key_androidKt.Key(i);
        Key_androidKt.Key(i2);
        Key_androidKt.Key(i3);
        Key_androidKt.Key(i4);
        Key_androidKt.Key(i5);
    }

    public static void m(long j, StringBuilder sb, String str) {
        sb.append((Object) Color.m127toStringimpl(j));
        sb.append(str);
    }

    public static /* synthetic */ void m(Modifier.Element element) {
        throw new ClassCastException();
    }

    public static /* synthetic */ void m(Object obj) {
        if (obj != null) {
            throw new ClassCastException();
        }
    }

    public static /* synthetic */ void m(int i, String str) {
        if (i == 0) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String name = ResultKt.class.getName();
            int i2 = 0;
            while (!stackTrace[i2].getClassName().equals(name)) {
                i2++;
            }
            while (stackTrace[i2].getClassName().equals(name)) {
                i2++;
            }
            StackTraceElement stackTraceElement = stackTrace[i2];
            NullPointerException nullPointerException = new NullPointerException("Parameter specified as non-null is null: method " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ", parameter " + str);
            ResultKt.sanitizeStackTrace(ResultKt.class.getName(), nullPointerException);
            throw nullPointerException;
        }
    }
}
