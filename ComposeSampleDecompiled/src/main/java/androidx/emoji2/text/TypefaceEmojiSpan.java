package androidx.emoji2.text;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.MetricAffectingSpan;
import android.text.style.ReplacementSpan;
import androidx.emoji2.text.flatbuffer.MetadataItem;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public final class TypefaceEmojiSpan extends ReplacementSpan {
    public final TypefaceEmojiRasterizer mRasterizer;
    public TextPaint mWorkingPaint;
    public final Paint.FontMetricsInt mTmpFontMetrics = new Paint.FontMetricsInt();
    public short mWidth = -1;
    public float mRatio = 1.0f;

    public TypefaceEmojiSpan(TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
        ResultKt.checkNotNull$1(typefaceEmojiRasterizer, "rasterizer cannot be null");
        this.mRasterizer = typefaceEmojiRasterizer;
    }

    @Override // android.text.style.ReplacementSpan
    public final void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
        Paint paint2 = paint;
        TextPaint textPaint = null;
        if (charSequence instanceof Spanned) {
            CharacterStyle[] characterStyleArr = (CharacterStyle[]) ((Spanned) charSequence).getSpans(i, i2, CharacterStyle.class);
            if (characterStyleArr.length != 0) {
                if (characterStyleArr.length != 1 || characterStyleArr[0] != this) {
                    TextPaint textPaint2 = this.mWorkingPaint;
                    if (textPaint2 == null) {
                        textPaint2 = new TextPaint();
                        this.mWorkingPaint = textPaint2;
                    }
                    textPaint = textPaint2;
                    textPaint.set(paint2);
                    for (CharacterStyle characterStyle : characterStyleArr) {
                        if (!(characterStyle instanceof MetricAffectingSpan)) {
                            characterStyle.updateDrawState(textPaint);
                        }
                    }
                }
            }
            if (paint2 instanceof TextPaint) {
                textPaint = (TextPaint) paint2;
            }
        } else if (paint2 instanceof TextPaint) {
            textPaint = (TextPaint) paint2;
        }
        if (textPaint != null && textPaint.bgColor != 0) {
            int color = textPaint.getColor();
            Paint.Style style = textPaint.getStyle();
            textPaint.setColor(textPaint.bgColor);
            textPaint.setStyle(Paint.Style.FILL);
            canvas.drawRect(f, i3, f + this.mWidth, i5, textPaint);
            textPaint.setStyle(style);
            textPaint.setColor(color);
        }
        EmojiCompat.get().getClass();
        float f2 = i4;
        if (textPaint != null) {
            paint2 = textPaint;
        }
        TypefaceEmojiRasterizer typefaceEmojiRasterizer = this.mRasterizer;
        MetadataRepo metadataRepo = typefaceEmojiRasterizer.mMetadataRepo;
        Typeface typeface = metadataRepo.mTypeface;
        Typeface typeface2 = paint2.getTypeface();
        paint2.setTypeface(typeface);
        canvas.drawText(metadataRepo.mEmojiCharArray, typefaceEmojiRasterizer.mIndex * 2, 2, f, f2, paint2);
        paint2.setTypeface(typeface2);
    }

    /* JADX DEBUG: Method merged with bridge method: getSize(Landroid/graphics/Paint;Ljava/lang/CharSequence;IILandroid/graphics/Paint$FontMetricsInt;)I */
    @Override // android.text.style.ReplacementSpan
    /* renamed from: getSize$androidx$emoji2$text$EmojiSpan, reason: merged with bridge method [inline-methods] */
    public final int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
        Paint.FontMetricsInt fontMetricsInt2 = this.mTmpFontMetrics;
        paint.getFontMetricsInt(fontMetricsInt2);
        float abs = Math.abs(fontMetricsInt2.descent - fontMetricsInt2.ascent) * 1.0f;
        TypefaceEmojiRasterizer typefaceEmojiRasterizer = this.mRasterizer;
        this.mRatio = abs / (typefaceEmojiRasterizer.getMetadataItem().__offset(14) != 0 ? r8.bb.getShort(r1 + r8.bb_pos) : (short) 0);
        MetadataItem metadataItem = typefaceEmojiRasterizer.getMetadataItem();
        int __offset = metadataItem.__offset(14);
        if (__offset != 0) {
            metadataItem.bb.getShort(__offset + metadataItem.bb_pos);
        }
        short s = (short) ((typefaceEmojiRasterizer.getMetadataItem().__offset(12) != 0 ? r5.bb.getShort(r7 + r5.bb_pos) : (short) 0) * this.mRatio);
        this.mWidth = s;
        if (fontMetricsInt != null) {
            fontMetricsInt.ascent = fontMetricsInt2.ascent;
            fontMetricsInt.descent = fontMetricsInt2.descent;
            fontMetricsInt.top = fontMetricsInt2.top;
            fontMetricsInt.bottom = fontMetricsInt2.bottom;
        }
        return s;
    }
}
