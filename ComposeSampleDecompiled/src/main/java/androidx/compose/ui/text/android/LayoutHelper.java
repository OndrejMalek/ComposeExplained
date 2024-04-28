package androidx.compose.ui.text.android;

import android.text.Layout;
import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.text.StringsKt__StringsKt;

/* loaded from: classes.dex */
public final class LayoutHelper {
    public final boolean[] bidiProcessedParagraphs;
    public final Layout layout;
    public final ArrayList paragraphBidi;
    public final ArrayList paragraphEnds;
    public char[] tmpBuffer;

    /* loaded from: classes.dex */
    public final class BidiRun {
        public final int end;
        public final boolean isRtl;
        public final int start;

        public BidiRun(int i, int i2, boolean z) {
            this.start = i;
            this.end = i2;
            this.isRtl = z;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BidiRun)) {
                return false;
            }
            BidiRun bidiRun = (BidiRun) obj;
            return this.start == bidiRun.start && this.end == bidiRun.end && this.isRtl == bidiRun.isRtl;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r0v3, resolved type: boolean */
        /* JADX WARN: Multi-variable type inference failed */
        public final int hashCode() {
            int hashCode = (Integer.hashCode(this.end) + (Integer.hashCode(this.start) * 31)) * 31;
            boolean z = this.isRtl;
            int i = z;
            if (z != 0) {
                i = 1;
            }
            return hashCode + i;
        }

        public final String toString() {
            return "BidiRun(start=" + this.start + ", end=" + this.end + ", isRtl=" + this.isRtl + ')';
        }
    }

    public LayoutHelper(Layout layout) {
        ResultKt.checkNotNullParameter(layout, "layout");
        this.layout = layout;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        do {
            CharSequence text = this.layout.getText();
            ResultKt.checkNotNullExpressionValue(text, "layout.text");
            int indexOf$default = StringsKt__StringsKt.indexOf$default(text, '\n', i, false, 4);
            i = indexOf$default < 0 ? this.layout.getText().length() : indexOf$default + 1;
            arrayList.add(Integer.valueOf(i));
        } while (i < this.layout.getText().length());
        this.paragraphEnds = arrayList;
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList(size);
        for (int i2 = 0; i2 < size; i2++) {
            arrayList2.add(null);
        }
        this.paragraphBidi = arrayList2;
        this.bidiProcessedParagraphs = new boolean[this.paragraphEnds.size()];
        this.paragraphEnds.size();
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x0209, code lost:
    
        if (r3 == r1.isRtl) goto L129;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x017d, code lost:
    
        if (r6.getRunCount() == 1) goto L87;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final float getHorizontalPosition(int r28, boolean r29, boolean r30) {
        /*
            r27 = this;
            r0 = r27
            r1 = r28
            android.text.Layout r2 = r0.layout
            if (r30 != 0) goto L14
            if (r29 == 0) goto Lf
            float r1 = r2.getPrimaryHorizontal(r1)
            goto L13
        Lf:
            float r1 = r2.getSecondaryHorizontal(r1)
        L13:
            return r1
        L14:
            java.lang.String r3 = "<this>"
            kotlin.ResultKt.checkNotNullParameter(r2, r3)
            r5 = 1
            if (r1 > 0) goto L1e
            r6 = 0
            goto L4b
        L1e:
            java.lang.CharSequence r6 = r2.getText()
            int r6 = r6.length()
            if (r1 < r6) goto L2e
            int r6 = r2.getLineCount()
            int r6 = r6 - r5
            goto L4b
        L2e:
            int r6 = r2.getLineForOffset(r1)
            int r7 = r2.getLineStart(r6)
            int r8 = r2.getLineEnd(r6)
            if (r7 == r1) goto L3f
            if (r8 == r1) goto L3f
            goto L4b
        L3f:
            if (r7 != r1) goto L46
            if (r30 == 0) goto L4b
            int r6 = r6 + (-1)
            goto L4b
        L46:
            if (r30 == 0) goto L49
            goto L4b
        L49:
            int r6 = r6 + 1
        L4b:
            int r7 = r2.getLineStart(r6)
            int r8 = r2.getLineEnd(r6)
            if (r1 == r7) goto L63
            if (r1 == r8) goto L63
            if (r29 == 0) goto L5e
            float r1 = r2.getPrimaryHorizontal(r1)
            goto L62
        L5e:
            float r1 = r2.getSecondaryHorizontal(r1)
        L62:
            return r1
        L63:
            if (r1 == 0) goto L2f2
            java.lang.CharSequence r9 = r2.getText()
            int r9 = r9.length()
            if (r1 != r9) goto L71
            goto L2f2
        L71:
            java.util.ArrayList r9 = r0.paragraphEnds
            java.lang.Integer r10 = java.lang.Integer.valueOf(r28)
            int r11 = r9.size()
            kotlin.ResultKt.checkNotNullParameter(r9, r3)
            int r3 = r9.size()
            java.lang.String r12 = ")."
            if (r11 < 0) goto L2db
            if (r11 > r3) goto L2bc
            int r11 = r11 - r5
            r3 = 0
        L8a:
            if (r3 > r11) goto La3
            int r12 = r3 + r11
            int r12 = r12 >>> r5
            java.lang.Object r13 = r9.get(r12)
            java.lang.Comparable r13 = (java.lang.Comparable) r13
            int r13 = kotlin.ResultKt.compareValues(r13, r10)
            if (r13 >= 0) goto L9e
            int r3 = r12 + 1
            goto L8a
        L9e:
            if (r13 <= 0) goto La5
            int r11 = r12 + (-1)
            goto L8a
        La3:
            int r3 = r3 + r5
            int r12 = -r3
        La5:
            if (r12 >= 0) goto Laa
            int r12 = r12 + r5
            int r3 = -r12
            goto Lac
        Laa:
            int r3 = r12 + 1
        Lac:
            if (r30 == 0) goto Lbf
            if (r3 <= 0) goto Lbf
            int r10 = r3 + (-1)
            java.lang.Object r11 = r9.get(r10)
            java.lang.Number r11 = (java.lang.Number) r11
            int r11 = r11.intValue()
            if (r1 != r11) goto Lbf
            r3 = r10
        Lbf:
            if (r3 != 0) goto Lc3
            r10 = 0
            goto Lcf
        Lc3:
            int r10 = r3 + (-1)
            java.lang.Object r10 = r9.get(r10)
            java.lang.Number r10 = (java.lang.Number) r10
            int r10 = r10.intValue()
        Lcf:
            int r10 = r2.getLineForOffset(r10)
            int r10 = r2.getParagraphDirection(r10)
            r11 = -1
            if (r10 != r11) goto Ldc
            r10 = r5
            goto Ldd
        Ldc:
            r10 = 0
        Ldd:
            int r8 = r0.lineEndToVisibleEnd(r8)
            if (r3 != 0) goto Le5
            r12 = 0
            goto Lf1
        Le5:
            int r12 = r3 + (-1)
            java.lang.Object r12 = r9.get(r12)
            java.lang.Number r12 = (java.lang.Number) r12
            int r12 = r12.intValue()
        Lf1:
            int r13 = r7 - r12
            int r12 = r8 - r12
            boolean[] r14 = r0.bidiProcessedParagraphs
            boolean r15 = r14[r3]
            java.util.ArrayList r5 = r0.paragraphBidi
            r16 = 0
            if (r15 == 0) goto L10e
            java.lang.Object r3 = r5.get(r3)
            java.text.Bidi r3 = (java.text.Bidi) r3
            r25 = r6
            r24 = r8
            r26 = r10
            r9 = r11
            goto L197
        L10e:
            if (r3 != 0) goto L112
            r15 = 0
            goto L11e
        L112:
            int r15 = r3 + (-1)
            java.lang.Object r15 = r9.get(r15)
            java.lang.Number r15 = (java.lang.Number) r15
            int r15 = r15.intValue()
        L11e:
            java.lang.Object r17 = r9.get(r3)
            java.lang.Number r17 = (java.lang.Number) r17
            int r11 = r17.intValue()
            int r4 = r11 - r15
            r24 = r8
            char[] r8 = r0.tmpBuffer
            r25 = r6
            if (r8 == 0) goto L135
            int r6 = r8.length
            if (r6 >= r4) goto L137
        L135:
            char[] r8 = new char[r4]
        L137:
            java.lang.CharSequence r6 = r2.getText()
            r26 = r10
            r10 = 0
            android.text.TextUtils.getChars(r6, r15, r11, r8, r10)
            boolean r6 = java.text.Bidi.requiresBidi(r8, r10, r4)
            if (r6 == 0) goto L182
            if (r3 != 0) goto L14b
            r10 = 0
            goto L157
        L14b:
            int r6 = r3 + (-1)
            java.lang.Object r6 = r9.get(r6)
            java.lang.Number r6 = (java.lang.Number) r6
            int r10 = r6.intValue()
        L157:
            int r6 = r2.getLineForOffset(r10)
            int r6 = r2.getParagraphDirection(r6)
            r9 = -1
            if (r6 != r9) goto L165
            r23 = 1
            goto L167
        L165:
            r23 = 0
        L167:
            java.text.Bidi r6 = new java.text.Bidi
            r19 = 0
            r20 = 0
            r21 = 0
            r17 = r6
            r18 = r8
            r22 = r4
            r17.<init>(r18, r19, r20, r21, r22, r23)
            int r4 = r6.getRunCount()
            r10 = 1
            if (r4 != r10) goto L185
        L17f:
            r6 = r16
            goto L185
        L182:
            r9 = -1
            r10 = 1
            goto L17f
        L185:
            r5.set(r3, r6)
            r14[r3] = r10
            if (r6 == 0) goto L194
            char[] r3 = r0.tmpBuffer
            if (r8 != r3) goto L193
            r8 = r16
            goto L194
        L193:
            r8 = r3
        L194:
            r0.tmpBuffer = r8
            r3 = r6
        L197:
            if (r3 == 0) goto L19d
            java.text.Bidi r16 = r3.createLineBidi(r13, r12)
        L19d:
            r3 = r16
            if (r3 == 0) goto L294
            int r4 = r3.getRunCount()
            r5 = 1
            if (r4 != r5) goto L1b0
            r8 = r5
            r6 = r25
            r3 = r26
        L1ad:
            r10 = 0
            goto L29b
        L1b0:
            int r4 = r3.getRunCount()
            androidx.compose.ui.text.android.LayoutHelper$BidiRun[] r5 = new androidx.compose.ui.text.android.LayoutHelper.BidiRun[r4]
            r10 = 0
        L1b7:
            if (r10 >= r4) goto L1d9
            androidx.compose.ui.text.android.LayoutHelper$BidiRun r6 = new androidx.compose.ui.text.android.LayoutHelper$BidiRun
            int r8 = r3.getRunStart(r10)
            int r8 = r8 + r7
            int r11 = r3.getRunLimit(r10)
            int r11 = r11 + r7
            int r12 = r3.getRunLevel(r10)
            int r12 = r12 % 2
            r13 = 1
            if (r12 != r13) goto L1d0
            r12 = 1
            goto L1d1
        L1d0:
            r12 = 0
        L1d1:
            r6.<init>(r8, r11, r12)
            r5[r10] = r6
            int r10 = r10 + 1
            goto L1b7
        L1d9:
            int r6 = r3.getRunCount()
            byte[] r8 = new byte[r6]
            r10 = 0
        L1e0:
            if (r10 >= r6) goto L1ec
            int r11 = r3.getRunLevel(r10)
            byte r11 = (byte) r11
            r8[r10] = r11
            int r10 = r10 + 1
            goto L1e0
        L1ec:
            r10 = 0
            java.text.Bidi.reorderVisually(r8, r10, r5, r10, r4)
            if (r1 != r7) goto L241
            r3 = r10
        L1f3:
            if (r3 >= r4) goto L200
            r6 = r5[r3]
            int r6 = r6.start
            if (r6 != r1) goto L1fd
            r11 = r3
            goto L201
        L1fd:
            int r3 = r3 + 1
            goto L1f3
        L200:
            r11 = r9
        L201:
            r1 = r5[r11]
            if (r29 != 0) goto L20c
            boolean r1 = r1.isRtl
            r3 = r26
            if (r3 != r1) goto L213
            goto L20e
        L20c:
            r3 = r26
        L20e:
            if (r3 != 0) goto L212
            r3 = 1
            goto L213
        L212:
            r3 = r10
        L213:
            if (r11 != 0) goto L21e
            if (r3 == 0) goto L21e
            r6 = r25
            float r1 = r2.getLineLeft(r6)
            return r1
        L21e:
            r6 = r25
            r1 = 1
            int r4 = r4 - r1
            if (r11 != r4) goto L22b
            if (r3 != 0) goto L22b
            float r1 = r2.getLineRight(r6)
            return r1
        L22b:
            if (r3 == 0) goto L237
            int r11 = r11 - r1
            r1 = r5[r11]
            int r1 = r1.start
            float r1 = r2.getPrimaryHorizontal(r1)
            return r1
        L237:
            int r11 = r11 + r1
            r1 = r5[r11]
            int r1 = r1.start
            float r1 = r2.getPrimaryHorizontal(r1)
            return r1
        L241:
            r7 = r24
            r6 = r25
            r3 = r26
            if (r1 <= r7) goto L24d
            int r1 = r27.lineEndToVisibleEnd(r28)
        L24d:
            r7 = r10
        L24e:
            if (r7 >= r4) goto L25b
            r8 = r5[r7]
            int r8 = r8.end
            if (r8 != r1) goto L258
            r11 = r7
            goto L25c
        L258:
            int r7 = r7 + 1
            goto L24e
        L25b:
            r11 = r9
        L25c:
            r1 = r5[r11]
            if (r29 != 0) goto L26a
            boolean r1 = r1.isRtl
            if (r3 != r1) goto L265
            goto L26a
        L265:
            if (r3 != 0) goto L269
            r3 = 1
            goto L26a
        L269:
            r3 = r10
        L26a:
            if (r11 != 0) goto L273
            if (r3 == 0) goto L273
            float r1 = r2.getLineLeft(r6)
            return r1
        L273:
            r8 = 1
            int r4 = r4 - r8
            if (r11 != r4) goto L27e
            if (r3 != 0) goto L27e
            float r1 = r2.getLineRight(r6)
            return r1
        L27e:
            if (r3 == 0) goto L28a
            int r11 = r11 - r8
            r1 = r5[r11]
            int r1 = r1.end
            float r1 = r2.getPrimaryHorizontal(r1)
            return r1
        L28a:
            int r11 = r11 + r8
            r1 = r5[r11]
            int r1 = r1.end
            float r1 = r2.getPrimaryHorizontal(r1)
            return r1
        L294:
            r6 = r25
            r3 = r26
            r8 = 1
            goto L1ad
        L29b:
            boolean r4 = r2.isRtlCharAt(r7)
            if (r29 != 0) goto L2a6
            if (r3 != r4) goto L2a4
            goto L2a6
        L2a4:
            r4 = r3
            goto L2ab
        L2a6:
            if (r3 != 0) goto L2aa
            r4 = r8
            goto L2ab
        L2aa:
            r4 = r10
        L2ab:
            if (r1 != r7) goto L2b0
            if (r4 == 0) goto L2b7
            goto L2b2
        L2b0:
            if (r4 != 0) goto L2b7
        L2b2:
            float r1 = r2.getLineLeft(r6)
            goto L2bb
        L2b7:
            float r1 = r2.getLineRight(r6)
        L2bb:
            return r1
        L2bc:
            java.lang.IndexOutOfBoundsException r1 = new java.lang.IndexOutOfBoundsException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r4 = "toIndex ("
            r2.<init>(r4)
            r2.append(r11)
            java.lang.String r4 = ") is greater than size ("
            r2.append(r4)
            r2.append(r3)
            r2.append(r12)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L2db:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "fromIndex (0) is greater than toIndex ("
            r2.<init>(r3)
            r2.append(r11)
            r2.append(r12)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L2f2:
            if (r29 == 0) goto L2f9
            float r1 = r2.getPrimaryHorizontal(r1)
            goto L2fd
        L2f9:
            float r1 = r2.getSecondaryHorizontal(r1)
        L2fd:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.android.LayoutHelper.getHorizontalPosition(int, boolean, boolean):float");
    }

    public final int lineEndToVisibleEnd(int i) {
        while (i > 0) {
            char charAt = this.layout.getText().charAt(i - 1);
            if (charAt != ' ' && charAt != '\n' && charAt != 5760 && ((8192 > charAt || charAt >= 8203 || charAt == 8199) && charAt != 8287 && charAt != 12288)) {
                break;
            }
            i--;
        }
        return i;
    }
}
