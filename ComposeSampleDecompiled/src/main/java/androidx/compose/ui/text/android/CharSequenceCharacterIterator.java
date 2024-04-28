package androidx.compose.ui.text.android;

import java.text.CharacterIterator;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class CharSequenceCharacterIterator implements CharacterIterator {
    public final CharSequence charSequence;
    public final int end;
    public final int start = 0;
    public int index = 0;

    public CharSequenceCharacterIterator(CharSequence charSequence, int i) {
        this.charSequence = charSequence;
        this.end = i;
    }

    @Override // java.text.CharacterIterator
    public final Object clone() {
        try {
            Object clone = super.clone();
            ResultKt.checkNotNullExpressionValue(clone, "{\n            @Suppress(…  super.clone()\n        }");
            return clone;
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }

    @Override // java.text.CharacterIterator
    public final char current() {
        int i = this.index;
        if (i == this.end) {
            return (char) 65535;
        }
        return this.charSequence.charAt(i);
    }

    @Override // java.text.CharacterIterator
    public final char first() {
        this.index = this.start;
        return current();
    }

    @Override // java.text.CharacterIterator
    public final int getBeginIndex() {
        return this.start;
    }

    @Override // java.text.CharacterIterator
    public final int getEndIndex() {
        return this.end;
    }

    @Override // java.text.CharacterIterator
    public final int getIndex() {
        return this.index;
    }

    @Override // java.text.CharacterIterator
    public final char last() {
        int i = this.start;
        int i2 = this.end;
        if (i == i2) {
            this.index = i2;
            return (char) 65535;
        }
        int i3 = i2 - 1;
        this.index = i3;
        return this.charSequence.charAt(i3);
    }

    @Override // java.text.CharacterIterator
    public final char next() {
        int i = this.index + 1;
        this.index = i;
        int i2 = this.end;
        if (i < i2) {
            return this.charSequence.charAt(i);
        }
        this.index = i2;
        return (char) 65535;
    }

    @Override // java.text.CharacterIterator
    public final char previous() {
        int i = this.index;
        if (i <= this.start) {
            return (char) 65535;
        }
        int i2 = i - 1;
        this.index = i2;
        return this.charSequence.charAt(i2);
    }

    @Override // java.text.CharacterIterator
    public final char setIndex(int i) {
        if (i > this.end || this.start > i) {
            throw new IllegalArgumentException("invalid position");
        }
        this.index = i;
        return current();
    }
}
