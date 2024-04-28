package androidx.compose.ui.text.input;

import _COROUTINE._BOUNDARY;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class ImeOptions {
    public static final ImeOptions Default = new ImeOptions();
    public final boolean singleLine = false;
    public final int capitalization = 0;
    public final boolean autoCorrect = true;
    public final int keyboardType = 1;
    public final int imeAction = 1;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImeOptions)) {
            return false;
        }
        ImeOptions imeOptions = (ImeOptions) obj;
        return this.singleLine == imeOptions.singleLine && _BOUNDARY.m6equalsimpl0(this.capitalization, imeOptions.capitalization) && this.autoCorrect == imeOptions.autoCorrect && ResultKt.m296equalsimpl0(this.keyboardType, imeOptions.keyboardType) && ImeAction.m261equalsimpl0(this.imeAction, imeOptions.imeAction);
    }

    public final int hashCode() {
        return Integer.hashCode(this.imeAction) + ((Integer.hashCode(this.keyboardType) + ((Boolean.hashCode(this.autoCorrect) + ((Integer.hashCode(this.capitalization) + (Boolean.hashCode(this.singleLine) * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ImeOptions(singleLine=");
        sb.append(this.singleLine);
        sb.append(", capitalization=");
        int i = this.capitalization;
        String str = "Invalid";
        sb.append((Object) (_BOUNDARY.m6equalsimpl0(i, 0) ? "None" : _BOUNDARY.m6equalsimpl0(i, 1) ? "Characters" : _BOUNDARY.m6equalsimpl0(i, 2) ? "Words" : _BOUNDARY.m6equalsimpl0(i, 3) ? "Sentences" : "Invalid"));
        sb.append(", autoCorrect=");
        sb.append(this.autoCorrect);
        sb.append(", keyboardType=");
        int i2 = this.keyboardType;
        if (ResultKt.m296equalsimpl0(i2, 1)) {
            str = "Text";
        } else if (ResultKt.m296equalsimpl0(i2, 2)) {
            str = "Ascii";
        } else if (ResultKt.m296equalsimpl0(i2, 3)) {
            str = "Number";
        } else if (ResultKt.m296equalsimpl0(i2, 4)) {
            str = "Phone";
        } else if (ResultKt.m296equalsimpl0(i2, 5)) {
            str = "Uri";
        } else if (ResultKt.m296equalsimpl0(i2, 6)) {
            str = "Email";
        } else if (ResultKt.m296equalsimpl0(i2, 7)) {
            str = "Password";
        } else if (ResultKt.m296equalsimpl0(i2, 8)) {
            str = "NumberPassword";
        } else if (ResultKt.m296equalsimpl0(i2, 9)) {
            str = "Decimal";
        }
        sb.append((Object) str);
        sb.append(", imeAction=");
        sb.append((Object) ImeAction.m262toStringimpl(this.imeAction));
        sb.append(')');
        return sb.toString();
    }
}
