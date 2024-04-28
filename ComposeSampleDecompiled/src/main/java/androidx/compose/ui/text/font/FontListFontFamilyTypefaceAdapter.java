package androidx.compose.ui.text.font;

import androidx.compose.ui.draw.DrawResult;
import kotlin.ResultKt;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobImpl;

/* loaded from: classes.dex */
public final class FontListFontFamilyTypefaceAdapter {
    public static final FontListFontFamilyTypefaceAdapter$special$$inlined$CoroutineExceptionHandler$1 DropExceptionHandler = new AbstractCoroutineContextElement(Job.Key.$$INSTANCE$1);

    public FontListFontFamilyTypefaceAdapter(DrawResult drawResult) {
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        ResultKt.checkNotNullParameter(drawResult, "asyncTypefaceCache");
        FontListFontFamilyTypefaceAdapter$special$$inlined$CoroutineExceptionHandler$1 fontListFontFamilyTypefaceAdapter$special$$inlined$CoroutineExceptionHandler$1 = DropExceptionHandler;
        fontListFontFamilyTypefaceAdapter$special$$inlined$CoroutineExceptionHandler$1.getClass();
        ResultKt.CoroutineScope(ResultKt.plus((CoroutineContext) fontListFontFamilyTypefaceAdapter$special$$inlined$CoroutineExceptionHandler$1, (CoroutineContext) emptyCoroutineContext).plus(new JobImpl(null)));
    }
}
