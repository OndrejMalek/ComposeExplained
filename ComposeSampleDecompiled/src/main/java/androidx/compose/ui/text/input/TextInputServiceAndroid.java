package androidx.compose.ui.text.input;

import android.view.Choreographer;
import android.view.View;
import androidx.compose.ui.node.LayoutNode$_foldedChildren$1;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class TextInputServiceAndroid {
    public final Lazy baseInputConnection$delegate;
    public final ArrayList ics;
    public final ImeOptions imeOptions;
    public final TextFieldValue$Companion$Saver$2 onEditCommand;
    public final TextFieldValue$Companion$Saver$2 onImeActionPerformed;
    public final TextFieldValue state;
    public final View view;

    public TextInputServiceAndroid(AndroidComposeView androidComposeView) {
        new InputMethodManagerImpl(androidComposeView);
        ResultKt.checkNotNullExpressionValue(Choreographer.getInstance(), "getInstance()");
        this.view = androidComposeView;
        this.onEditCommand = TextFieldValue$Companion$Saver$2.INSTANCE$1;
        this.onImeActionPerformed = TextFieldValue$Companion$Saver$2.INSTANCE$2;
        this.state = new TextFieldValue(new AnnotatedString(""), TextRange.Zero, null);
        this.imeOptions = ImeOptions.Default;
        this.ics = new ArrayList();
        this.baseInputConnection$delegate = ResultKt.lazy(new LayoutNode$_foldedChildren$1(16, this));
    }
}
