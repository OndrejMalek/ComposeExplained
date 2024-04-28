package androidx.compose.ui.text.input;

import android.R;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputContentInfo;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextRange;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.text.StringsKt__StringsKt;

/* loaded from: classes.dex */
public final class RecordingInputConnection implements InputConnection {
    public final boolean autoCorrect;
    public int batchDepth;
    public final ArrayList editCommands;
    public final TextInputServiceAndroid$createInputConnection$1 eventCallback;
    public boolean isActive;
    public final TextFieldValue mTextFieldValue;

    public RecordingInputConnection(TextFieldValue textFieldValue, TextInputServiceAndroid$createInputConnection$1 textInputServiceAndroid$createInputConnection$1, boolean z) {
        ResultKt.checkNotNullParameter(textFieldValue, "initState");
        this.eventCallback = textInputServiceAndroid$createInputConnection$1;
        this.autoCorrect = z;
        this.mTextFieldValue = textFieldValue;
        this.editCommands = new ArrayList();
        this.isActive = true;
    }

    public final void addEditCommandWithBatch(EditCommand editCommand) {
        this.batchDepth++;
        try {
            this.editCommands.add(editCommand);
        } finally {
            endBatchEditInternal();
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean beginBatchEdit() {
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        this.batchDepth++;
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean clearMetaKeyStates(int i) {
        boolean z = this.isActive;
        if (z) {
            return false;
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final void closeConnection() {
        this.editCommands.clear();
        this.batchDepth = 0;
        this.isActive = false;
        TextInputServiceAndroid$createInputConnection$1 textInputServiceAndroid$createInputConnection$1 = this.eventCallback;
        textInputServiceAndroid$createInputConnection$1.getClass();
        TextInputServiceAndroid textInputServiceAndroid = textInputServiceAndroid$createInputConnection$1.this$0;
        int size = textInputServiceAndroid.ics.size();
        for (int i = 0; i < size; i++) {
            ArrayList arrayList = textInputServiceAndroid.ics;
            if (ResultKt.areEqual(((WeakReference) arrayList.get(i)).get(), this)) {
                arrayList.remove(i);
                return;
            }
        }
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitCompletion(CompletionInfo completionInfo) {
        boolean z = this.isActive;
        if (z) {
            return false;
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitContent(InputContentInfo inputContentInfo, int i, Bundle bundle) {
        ResultKt.checkNotNullParameter(inputContentInfo, "inputContentInfo");
        boolean z = this.isActive;
        if (z) {
            return false;
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitCorrection(CorrectionInfo correctionInfo) {
        boolean z = this.isActive;
        return z ? this.autoCorrect : z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean commitText(CharSequence charSequence, int i) {
        boolean z = this.isActive;
        if (z) {
            addEditCommandWithBatch(new CommitTextCommand(i, String.valueOf(charSequence)));
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingText(int i, int i2) {
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        addEditCommandWithBatch(new DeleteSurroundingTextCommand(i, i2));
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean deleteSurroundingTextInCodePoints(int i, int i2) {
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        addEditCommandWithBatch(new DeleteSurroundingTextInCodePointsCommand(i, i2));
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean endBatchEdit() {
        return endBatchEditInternal();
    }

    public final boolean endBatchEditInternal() {
        int i = this.batchDepth - 1;
        this.batchDepth = i;
        if (i == 0) {
            ArrayList arrayList = this.editCommands;
            if (!arrayList.isEmpty()) {
                ArrayList mutableList = CollectionsKt___CollectionsKt.toMutableList(arrayList);
                TextInputServiceAndroid$createInputConnection$1 textInputServiceAndroid$createInputConnection$1 = this.eventCallback;
                textInputServiceAndroid$createInputConnection$1.getClass();
                textInputServiceAndroid$createInputConnection$1.this$0.onEditCommand.invoke(mutableList);
                arrayList.clear();
            }
        }
        return this.batchDepth > 0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: androidx.compose.ui.text.input.RecordingInputConnection */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, androidx.compose.ui.text.input.EditCommand] */
    @Override // android.view.inputmethod.InputConnection
    public final boolean finishComposingText() {
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        addEditCommandWithBatch(new Object());
        return true;
    }

    @Override // android.view.inputmethod.InputConnection
    public final int getCursorCapsMode(int i) {
        TextFieldValue textFieldValue = this.mTextFieldValue;
        return TextUtils.getCapsMode(textFieldValue.annotatedString.text, TextRange.m247getMinimpl(textFieldValue.selection), i);
    }

    @Override // android.view.inputmethod.InputConnection
    public final ExtractedText getExtractedText(ExtractedTextRequest extractedTextRequest, int i) {
        TextFieldValue textFieldValue = this.mTextFieldValue;
        ResultKt.checkNotNullParameter(textFieldValue, "<this>");
        ExtractedText extractedText = new ExtractedText();
        AnnotatedString annotatedString = textFieldValue.annotatedString;
        String str = annotatedString.text;
        extractedText.text = str;
        extractedText.startOffset = 0;
        extractedText.partialEndOffset = str.length();
        extractedText.partialStartOffset = -1;
        long j = textFieldValue.selection;
        extractedText.selectionStart = TextRange.m247getMinimpl(j);
        extractedText.selectionEnd = TextRange.m246getMaximpl(j);
        String str2 = annotatedString.text;
        ResultKt.checkNotNullParameter(str2, "<this>");
        extractedText.flags = (StringsKt__StringsKt.indexOf$default(str2, '\n', 0, false, 2) >= 0 ? 1 : 0) ^ 1;
        return extractedText;
    }

    @Override // android.view.inputmethod.InputConnection
    public final Handler getHandler() {
        return null;
    }

    @Override // android.view.inputmethod.InputConnection
    public final CharSequence getSelectedText(int i) {
        TextFieldValue textFieldValue = this.mTextFieldValue;
        long j = textFieldValue.selection;
        int i2 = TextRange.$r8$clinit;
        if (((int) (j >> 32)) == ((int) (j & 4294967295L))) {
            return null;
        }
        ResultKt.checkNotNullParameter(textFieldValue, "<this>");
        AnnotatedString annotatedString = textFieldValue.annotatedString;
        annotatedString.getClass();
        long j2 = textFieldValue.selection;
        return annotatedString.subSequence(TextRange.m247getMinimpl(j2), TextRange.m246getMaximpl(j2)).text;
    }

    @Override // android.view.inputmethod.InputConnection
    public final CharSequence getTextAfterCursor(int i, int i2) {
        TextFieldValue textFieldValue = this.mTextFieldValue;
        ResultKt.checkNotNullParameter(textFieldValue, "<this>");
        long j = textFieldValue.selection;
        int m246getMaximpl = TextRange.m246getMaximpl(j);
        int m246getMaximpl2 = TextRange.m246getMaximpl(j) + i;
        AnnotatedString annotatedString = textFieldValue.annotatedString;
        return annotatedString.subSequence(m246getMaximpl, Math.min(m246getMaximpl2, annotatedString.text.length())).text;
    }

    @Override // android.view.inputmethod.InputConnection
    public final CharSequence getTextBeforeCursor(int i, int i2) {
        TextFieldValue textFieldValue = this.mTextFieldValue;
        ResultKt.checkNotNullParameter(textFieldValue, "<this>");
        long j = textFieldValue.selection;
        return textFieldValue.annotatedString.subSequence(Math.max(0, TextRange.m247getMinimpl(j) - i), TextRange.m247getMinimpl(j)).text;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.inputmethod.InputConnection
    public final boolean performContextMenuAction(int i) {
        boolean z = this.isActive;
        if (z) {
            z = false;
            switch (i) {
                case R.id.selectAll:
                    addEditCommandWithBatch(new SetSelectionCommand(0, this.mTextFieldValue.annotatedString.text.length()));
                    break;
                case R.id.cut:
                    sendSynthesizedKeyEvent(277);
                    break;
                case R.id.copy:
                    sendSynthesizedKeyEvent(278);
                    break;
                case R.id.paste:
                    sendSynthesizedKeyEvent(279);
                    break;
            }
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean performEditorAction(int i) {
        int i2;
        boolean z = this.isActive;
        if (z) {
            z = true;
            if (i != 0) {
                switch (i) {
                    case 2:
                        i2 = 2;
                        break;
                    case 3:
                        i2 = 3;
                        break;
                    case 4:
                        i2 = 4;
                        break;
                    case 5:
                        i2 = 6;
                        break;
                    case 6:
                        i2 = 7;
                        break;
                    case 7:
                        i2 = 5;
                        break;
                    default:
                        Log.w("RecordingIC", "IME sends unsupported Editor Action: " + i);
                        break;
                }
                this.eventCallback.this$0.onImeActionPerformed.invoke(new ImeAction(i2));
            }
            i2 = 1;
            this.eventCallback.this$0.onImeActionPerformed.invoke(new ImeAction(i2));
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean performPrivateCommand(String str, Bundle bundle) {
        boolean z = this.isActive;
        if (z) {
            return true;
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean reportFullscreenMode(boolean z) {
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean requestCursorUpdates(int i) {
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        Log.w("RecordingIC", "requestCursorUpdates is not supported");
        return false;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean sendKeyEvent(KeyEvent keyEvent) {
        ResultKt.checkNotNullParameter(keyEvent, "event");
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        TextInputServiceAndroid$createInputConnection$1 textInputServiceAndroid$createInputConnection$1 = this.eventCallback;
        textInputServiceAndroid$createInputConnection$1.getClass();
        ((BaseInputConnection) textInputServiceAndroid$createInputConnection$1.this$0.baseInputConnection$delegate.getValue()).sendKeyEvent(keyEvent);
        return true;
    }

    public final void sendSynthesizedKeyEvent(int i) {
        sendKeyEvent(new KeyEvent(0, i));
        sendKeyEvent(new KeyEvent(1, i));
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean setComposingRegion(int i, int i2) {
        boolean z = this.isActive;
        if (z) {
            addEditCommandWithBatch(new SetComposingRegionCommand(i, i2));
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean setComposingText(CharSequence charSequence, int i) {
        boolean z = this.isActive;
        if (z) {
            addEditCommandWithBatch(new SetComposingTextCommand(i, String.valueOf(charSequence)));
        }
        return z;
    }

    @Override // android.view.inputmethod.InputConnection
    public final boolean setSelection(int i, int i2) {
        boolean z = this.isActive;
        if (!z) {
            return z;
        }
        addEditCommandWithBatch(new SetSelectionCommand(i, i2));
        return true;
    }
}
