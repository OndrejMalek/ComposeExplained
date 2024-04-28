package androidx.compose.ui.text;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes.dex */
public final class AnnotationType {
    public static final /* synthetic */ AnnotationType[] $VALUES;
    public static final AnnotationType Paragraph;
    public static final AnnotationType Span;
    public static final AnnotationType String;
    public static final AnnotationType Url;
    public static final AnnotationType VerbatimTts;

    /* JADX WARN: Type inference failed for: r0v0, types: [androidx.compose.ui.text.AnnotationType, java.lang.Enum] */
    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.compose.ui.text.AnnotationType, java.lang.Enum] */
    /* JADX WARN: Type inference failed for: r2v2, types: [androidx.compose.ui.text.AnnotationType, java.lang.Enum] */
    /* JADX WARN: Type inference failed for: r3v2, types: [androidx.compose.ui.text.AnnotationType, java.lang.Enum] */
    /* JADX WARN: Type inference failed for: r4v2, types: [androidx.compose.ui.text.AnnotationType, java.lang.Enum] */
    static {
        ?? r0 = new Enum("Paragraph", 0);
        Paragraph = r0;
        ?? r1 = new Enum("Span", 1);
        Span = r1;
        ?? r2 = new Enum("VerbatimTts", 2);
        VerbatimTts = r2;
        ?? r3 = new Enum("Url", 3);
        Url = r3;
        ?? r4 = new Enum("String", 4);
        String = r4;
        $VALUES = new AnnotationType[]{r0, r1, r2, r3, r4};
    }

    public static AnnotationType valueOf(String str) {
        return (AnnotationType) Enum.valueOf(AnnotationType.class, str);
    }

    public static AnnotationType[] values() {
        return (AnnotationType[]) $VALUES.clone();
    }
}
