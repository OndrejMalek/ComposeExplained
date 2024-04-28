package androidx.compose.material3;

import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.StructuralEqualityPolicy;
import androidx.compose.ui.graphics.Color;
import kotlin.ResultKt;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public final class ColorScheme {
    public final ParcelableSnapshotMutableState background$delegate;
    public final ParcelableSnapshotMutableState error$delegate;
    public final ParcelableSnapshotMutableState errorContainer$delegate;
    public final ParcelableSnapshotMutableState inverseOnSurface$delegate;
    public final ParcelableSnapshotMutableState inversePrimary$delegate;
    public final ParcelableSnapshotMutableState inverseSurface$delegate;
    public final ParcelableSnapshotMutableState onBackground$delegate;
    public final ParcelableSnapshotMutableState onError$delegate;
    public final ParcelableSnapshotMutableState onErrorContainer$delegate;
    public final ParcelableSnapshotMutableState onPrimary$delegate;
    public final ParcelableSnapshotMutableState onPrimaryContainer$delegate;
    public final ParcelableSnapshotMutableState onSecondary$delegate;
    public final ParcelableSnapshotMutableState onSecondaryContainer$delegate;
    public final ParcelableSnapshotMutableState onSurface$delegate;
    public final ParcelableSnapshotMutableState onSurfaceVariant$delegate;
    public final ParcelableSnapshotMutableState onTertiary$delegate;
    public final ParcelableSnapshotMutableState onTertiaryContainer$delegate;
    public final ParcelableSnapshotMutableState outline$delegate;
    public final ParcelableSnapshotMutableState outlineVariant$delegate;
    public final ParcelableSnapshotMutableState primary$delegate;
    public final ParcelableSnapshotMutableState primaryContainer$delegate;
    public final ParcelableSnapshotMutableState scrim$delegate;
    public final ParcelableSnapshotMutableState secondary$delegate;
    public final ParcelableSnapshotMutableState secondaryContainer$delegate;
    public final ParcelableSnapshotMutableState surface$delegate;
    public final ParcelableSnapshotMutableState surfaceTint$delegate;
    public final ParcelableSnapshotMutableState surfaceVariant$delegate;
    public final ParcelableSnapshotMutableState tertiary$delegate;
    public final ParcelableSnapshotMutableState tertiaryContainer$delegate;

    public ColorScheme(long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, long j9, long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, long j22, long j23, long j24, long j25, long j26, long j27, long j28, long j29) {
        Color color = new Color(j);
        StructuralEqualityPolicy structuralEqualityPolicy = StructuralEqualityPolicy.INSTANCE;
        this.primary$delegate = ResultKt.mutableStateOf(color, structuralEqualityPolicy);
        this.onPrimary$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j2, structuralEqualityPolicy);
        this.primaryContainer$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j3, structuralEqualityPolicy);
        this.onPrimaryContainer$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j4, structuralEqualityPolicy);
        this.inversePrimary$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j5, structuralEqualityPolicy);
        this.secondary$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j6, structuralEqualityPolicy);
        this.onSecondary$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j7, structuralEqualityPolicy);
        this.secondaryContainer$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j8, structuralEqualityPolicy);
        this.onSecondaryContainer$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j9, structuralEqualityPolicy);
        this.tertiary$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j10, structuralEqualityPolicy);
        this.onTertiary$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j11, structuralEqualityPolicy);
        this.tertiaryContainer$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j12, structuralEqualityPolicy);
        this.onTertiaryContainer$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j13, structuralEqualityPolicy);
        this.background$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j14, structuralEqualityPolicy);
        this.onBackground$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j15, structuralEqualityPolicy);
        this.surface$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j16, structuralEqualityPolicy);
        this.onSurface$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j17, structuralEqualityPolicy);
        this.surfaceVariant$delegate = ResultKt.mutableStateOf(new Color(j18), structuralEqualityPolicy);
        this.onSurfaceVariant$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j19, structuralEqualityPolicy);
        this.surfaceTint$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j20, structuralEqualityPolicy);
        this.inverseSurface$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j21, structuralEqualityPolicy);
        this.inverseOnSurface$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j22, structuralEqualityPolicy);
        this.error$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j23, structuralEqualityPolicy);
        this.onError$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j24, structuralEqualityPolicy);
        this.errorContainer$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j25, structuralEqualityPolicy);
        this.onErrorContainer$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j26, structuralEqualityPolicy);
        this.outline$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j27, structuralEqualityPolicy);
        this.outlineVariant$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j28, structuralEqualityPolicy);
        this.scrim$delegate = DurationKt$$ExternalSyntheticCheckNotZero0.m(j29, structuralEqualityPolicy);
    }

    /* renamed from: getBackground-0d7_KjU, reason: not valid java name */
    public final long m51getBackground0d7_KjU() {
        return ((Color) this.background$delegate.getValue()).value;
    }

    /* renamed from: getPrimary-0d7_KjU, reason: not valid java name */
    public final long m52getPrimary0d7_KjU() {
        return ((Color) this.primary$delegate.getValue()).value;
    }

    /* renamed from: getSurface-0d7_KjU, reason: not valid java name */
    public final long m53getSurface0d7_KjU() {
        return ((Color) this.surface$delegate.getValue()).value;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ColorScheme(primary=");
        sb.append((Object) Color.m127toStringimpl(m52getPrimary0d7_KjU()));
        sb.append("onPrimary=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.onPrimary$delegate.getValue()).value, sb, "primaryContainer=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.primaryContainer$delegate.getValue()).value, sb, "onPrimaryContainer=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.onPrimaryContainer$delegate.getValue()).value, sb, "inversePrimary=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.inversePrimary$delegate.getValue()).value, sb, "secondary=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.secondary$delegate.getValue()).value, sb, "onSecondary=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.onSecondary$delegate.getValue()).value, sb, "secondaryContainer=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.secondaryContainer$delegate.getValue()).value, sb, "onSecondaryContainer=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.onSecondaryContainer$delegate.getValue()).value, sb, "tertiary=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.tertiary$delegate.getValue()).value, sb, "onTertiary=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.onTertiary$delegate.getValue()).value, sb, "tertiaryContainer=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.tertiaryContainer$delegate.getValue()).value, sb, "onTertiaryContainer=");
        sb.append((Object) Color.m127toStringimpl(((Color) this.onTertiaryContainer$delegate.getValue()).value));
        sb.append("background=");
        sb.append((Object) Color.m127toStringimpl(m51getBackground0d7_KjU()));
        sb.append("onBackground=");
        sb.append((Object) Color.m127toStringimpl(((Color) this.onBackground$delegate.getValue()).value));
        sb.append("surface=");
        sb.append((Object) Color.m127toStringimpl(m53getSurface0d7_KjU()));
        sb.append("onSurface=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.onSurface$delegate.getValue()).value, sb, "surfaceVariant=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.surfaceVariant$delegate.getValue()).value, sb, "onSurfaceVariant=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.onSurfaceVariant$delegate.getValue()).value, sb, "surfaceTint=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.surfaceTint$delegate.getValue()).value, sb, "inverseSurface=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.inverseSurface$delegate.getValue()).value, sb, "inverseOnSurface=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.inverseOnSurface$delegate.getValue()).value, sb, "error=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.error$delegate.getValue()).value, sb, "onError=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.onError$delegate.getValue()).value, sb, "errorContainer=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.errorContainer$delegate.getValue()).value, sb, "onErrorContainer=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.onErrorContainer$delegate.getValue()).value, sb, "outline=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.outline$delegate.getValue()).value, sb, "outlineVariant=");
        DurationKt$$ExternalSyntheticCheckNotZero0.m(((Color) this.outlineVariant$delegate.getValue()).value, sb, "scrim=");
        sb.append((Object) Color.m127toStringimpl(((Color) this.scrim$delegate.getValue()).value));
        sb.append(')');
        return sb.toString();
    }
}
