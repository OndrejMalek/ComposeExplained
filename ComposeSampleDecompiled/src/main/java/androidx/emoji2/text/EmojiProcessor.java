package androidx.emoji2.text;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.util.SparseArray;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.input.pointer.NodeParent;
import androidx.compose.ui.node.HitTestResult;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.platform.WeakCache;
import androidx.core.graphics.PaintCompat;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.MetadataRepo;
import androidx.emoji2.text.flatbuffer.MetadataItem;
import androidx.lifecycle.ViewModelStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.ULong;
import kotlinx.coroutines.internal.Symbol;

/* loaded from: classes.dex */
public final class EmojiProcessor {
    public Object mEmojiAsDefaultStyleExceptions;
    public final Object mGlyphChecker;
    public final Object mMetadataRepo;
    public final Object mSpanFactory;
    public boolean mUseEmojiAsDefaultStyle;

    /* loaded from: classes.dex */
    public final class EmojiProcessAddSpanCallback implements EmojiProcessCallback {
        public final ULong.Companion mSpanFactory;
        public UnprecomputeTextOnModificationSpannable spannable;

        public EmojiProcessAddSpanCallback(UnprecomputeTextOnModificationSpannable unprecomputeTextOnModificationSpannable, ULong.Companion companion) {
            this.spannable = unprecomputeTextOnModificationSpannable;
            this.mSpanFactory = companion;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public final Object getResult() {
            return this.spannable;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.lang.String */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public final boolean handleEmoji(String str, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
            if ((typefaceEmojiRasterizer.mCache & 4) > 0) {
                return true;
            }
            if (this.spannable == null) {
                this.spannable = new UnprecomputeTextOnModificationSpannable(str instanceof Spannable ? (Spannable) str : new SpannableString(str));
            }
            this.mSpanFactory.getClass();
            this.spannable.setSpan(new TypefaceEmojiSpan(typefaceEmojiRasterizer), i, i2, 33);
            return true;
        }
    }

    /* loaded from: classes.dex */
    public interface EmojiProcessCallback {
        Object getResult();

        boolean handleEmoji(String str, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer);
    }

    /* loaded from: classes.dex */
    public final class ProcessorSm {
        public int mCurrentDepth;
        public MetadataRepo.Node mCurrentNode;
        public final int[] mEmojiAsDefaultStyleExceptions;
        public MetadataRepo.Node mFlushNode;
        public int mLastCodepoint;
        public final MetadataRepo.Node mRootNode;
        public int mState = 1;
        public final boolean mUseEmojiAsDefaultStyle;

        public ProcessorSm(MetadataRepo.Node node, boolean z, int[] iArr) {
            this.mRootNode = node;
            this.mCurrentNode = node;
            this.mUseEmojiAsDefaultStyle = z;
            this.mEmojiAsDefaultStyleExceptions = iArr;
        }

        public final void reset() {
            this.mState = 1;
            this.mCurrentNode = this.mRootNode;
            this.mCurrentDepth = 0;
        }

        public final boolean shouldUseEmojiPresentationStyleForSingleCodepoint() {
            int[] iArr;
            MetadataItem metadataItem = this.mCurrentNode.mData.getMetadataItem();
            int __offset = metadataItem.__offset(6);
            if ((__offset == 0 || metadataItem.bb.get(__offset + metadataItem.bb_pos) == 0) && this.mLastCodepoint != 65039) {
                return this.mUseEmojiAsDefaultStyle && ((iArr = this.mEmojiAsDefaultStyleExceptions) == null || Arrays.binarySearch(iArr, this.mCurrentNode.mData.getCodepointAt(0)) < 0);
            }
            return true;
        }
    }

    public EmojiProcessor(LayoutNode layoutNode) {
        ResultKt.checkNotNullParameter(layoutNode, "root");
        this.mSpanFactory = layoutNode;
        this.mMetadataRepo = new WeakCache(layoutNode.nodes.innerCoordinator);
        this.mGlyphChecker = new ViewModelStore(1);
        this.mEmojiAsDefaultStyleExceptions = new HitTestResult();
    }

    public final boolean hasGlyph(String str, int i, int i2, TypefaceEmojiRasterizer typefaceEmojiRasterizer) {
        if ((typefaceEmojiRasterizer.mCache & 3) == 0) {
            EmojiCompat.GlyphChecker glyphChecker = (EmojiCompat.GlyphChecker) this.mGlyphChecker;
            MetadataItem metadataItem = typefaceEmojiRasterizer.getMetadataItem();
            int __offset = metadataItem.__offset(8);
            if (__offset != 0) {
                metadataItem.bb.getShort(__offset + metadataItem.bb_pos);
            }
            DefaultGlyphChecker defaultGlyphChecker = (DefaultGlyphChecker) glyphChecker;
            defaultGlyphChecker.getClass();
            ThreadLocal threadLocal = DefaultGlyphChecker.sStringBuilder;
            if (threadLocal.get() == null) {
                threadLocal.set(new StringBuilder());
            }
            StringBuilder sb = (StringBuilder) threadLocal.get();
            sb.setLength(0);
            while (i < i2) {
                sb.append(str.charAt(i));
                i++;
            }
            TextPaint textPaint = defaultGlyphChecker.mTextPaint;
            String sb2 = sb.toString();
            int i3 = PaintCompat.$r8$clinit;
            boolean hasGlyph = PaintCompat.Api23Impl.hasGlyph(textPaint, sb2);
            int i4 = typefaceEmojiRasterizer.mCache & 4;
            typefaceEmojiRasterizer.mCache = hasGlyph ? i4 | 2 : i4 | 1;
        }
        return (typefaceEmojiRasterizer.mCache & 3) == 2;
    }

    public final Object process(String str, int i, int i2, int i3, boolean z, EmojiProcessCallback emojiProcessCallback) {
        int i4;
        char c;
        ProcessorSm processorSm = new ProcessorSm(((MetadataRepo) this.mMetadataRepo).mRootNode, this.mUseEmojiAsDefaultStyle, (int[]) this.mEmojiAsDefaultStyleExceptions);
        int codePointAt = Character.codePointAt(str, i);
        boolean z2 = true;
        int i5 = 0;
        int i6 = i;
        loop0: while (true) {
            i4 = i6;
            while (i6 < i2 && i5 < i3 && z2) {
                SparseArray sparseArray = processorSm.mCurrentNode.mChildren;
                MetadataRepo.Node node = sparseArray == null ? null : (MetadataRepo.Node) sparseArray.get(codePointAt);
                if (processorSm.mState == 2) {
                    if (node != null) {
                        processorSm.mCurrentNode = node;
                        processorSm.mCurrentDepth++;
                    } else {
                        if (codePointAt == 65038) {
                            processorSm.reset();
                        } else if (codePointAt != 65039) {
                            MetadataRepo.Node node2 = processorSm.mCurrentNode;
                            if (node2.mData != null) {
                                if (processorSm.mCurrentDepth != 1) {
                                    processorSm.mFlushNode = node2;
                                    processorSm.reset();
                                } else if (processorSm.shouldUseEmojiPresentationStyleForSingleCodepoint()) {
                                    processorSm.mFlushNode = processorSm.mCurrentNode;
                                    processorSm.reset();
                                } else {
                                    processorSm.reset();
                                }
                                c = 3;
                            } else {
                                processorSm.reset();
                            }
                        }
                        c = 1;
                    }
                    c = 2;
                } else if (node == null) {
                    processorSm.reset();
                    c = 1;
                } else {
                    processorSm.mState = 2;
                    processorSm.mCurrentNode = node;
                    processorSm.mCurrentDepth = 1;
                    c = 2;
                }
                processorSm.mLastCodepoint = codePointAt;
                if (c == 1) {
                    i6 = Character.charCount(Character.codePointAt(str, i4)) + i4;
                    if (i6 < i2) {
                        codePointAt = Character.codePointAt(str, i6);
                    }
                } else if (c == 2) {
                    int charCount = Character.charCount(codePointAt) + i6;
                    if (charCount < i2) {
                        codePointAt = Character.codePointAt(str, charCount);
                    }
                    i6 = charCount;
                } else if (c == 3) {
                    if (z || !hasGlyph(str, i4, i6, processorSm.mFlushNode.mData)) {
                        z2 = emojiProcessCallback.handleEmoji(str, i4, i6, processorSm.mFlushNode.mData);
                        i5++;
                    }
                }
            }
        }
        if (processorSm.mState == 2 && processorSm.mCurrentNode.mData != null && ((processorSm.mCurrentDepth > 1 || processorSm.shouldUseEmojiPresentationStyleForSingleCodepoint()) && i5 < i3 && z2 && (z || !hasGlyph(str, i4, i6, processorSm.mCurrentNode.mData)))) {
            emojiProcessCallback.handleEmoji(str, i4, i6, processorSm.mCurrentNode.mData);
        }
        return emojiProcessCallback.getResult();
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0110 A[Catch: all -> 0x0034, TryCatch #0 {all -> 0x0034, blocks: (B:7:0x0012, B:9:0x0020, B:11:0x002a, B:15:0x0052, B:16:0x005c, B:19:0x0064, B:21:0x006c, B:24:0x0072, B:27:0x007b, B:30:0x00aa, B:38:0x00bd, B:41:0x00f2, B:43:0x00fc, B:49:0x0106, B:50:0x010a, B:52:0x0110, B:55:0x012c, B:64:0x00de, B:68:0x0037, B:69:0x003b, B:71:0x0041, B:73:0x004b), top: B:6:0x0012 }] */
    /* renamed from: process-BIzXfog, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final int m289processBIzXfog(androidx.compose.ui.input.pointer.PointerInputEvent r19, androidx.compose.ui.input.pointer.PositionCalculator r20, boolean r21) {
        /*
            r18 = this;
            r1 = r18
            r0 = r20
            r2 = r21
            java.lang.String r3 = "positionCalculator"
            kotlin.ResultKt.checkNotNullParameter(r0, r3)
            boolean r3 = r1.mUseEmojiAsDefaultStyle
            r4 = 0
            if (r3 == 0) goto L11
            return r4
        L11:
            r3 = 1
            r1.mUseEmojiAsDefaultStyle = r3     // Catch: java.lang.Throwable -> L34
            java.lang.Object r5 = r1.mGlyphChecker     // Catch: java.lang.Throwable -> L34
            androidx.lifecycle.ViewModelStore r5 = (androidx.lifecycle.ViewModelStore) r5     // Catch: java.lang.Throwable -> L34
            r6 = r19
            androidx.compose.ui.input.pointer.InternalPointerEvent r0 = r5.produce(r6, r0)     // Catch: java.lang.Throwable -> L34
            java.util.Map r5 = r0.changes
            java.util.Collection r6 = r5.values()     // Catch: java.lang.Throwable -> L34
            java.lang.Iterable r6 = (java.lang.Iterable) r6     // Catch: java.lang.Throwable -> L34
            boolean r7 = r6 instanceof java.util.Collection     // Catch: java.lang.Throwable -> L34
            if (r7 == 0) goto L37
            r7 = r6
            java.util.Collection r7 = (java.util.Collection) r7     // Catch: java.lang.Throwable -> L34
            boolean r7 = r7.isEmpty()     // Catch: java.lang.Throwable -> L34
            if (r7 == 0) goto L37
            goto L51
        L34:
            r0 = move-exception
            goto L139
        L37:
            java.util.Iterator r6 = r6.iterator()     // Catch: java.lang.Throwable -> L34
        L3b:
            boolean r7 = r6.hasNext()     // Catch: java.lang.Throwable -> L34
            if (r7 == 0) goto L51
            java.lang.Object r7 = r6.next()     // Catch: java.lang.Throwable -> L34
            androidx.compose.ui.input.pointer.PointerInputChange r7 = (androidx.compose.ui.input.pointer.PointerInputChange) r7     // Catch: java.lang.Throwable -> L34
            boolean r8 = r7.pressed     // Catch: java.lang.Throwable -> L34
            if (r8 != 0) goto L4f
            boolean r7 = r7.previousPressed     // Catch: java.lang.Throwable -> L34
            if (r7 == 0) goto L3b
        L4f:
            r6 = r4
            goto L52
        L51:
            r6 = r3
        L52:
            java.util.Collection r7 = r5.values()     // Catch: java.lang.Throwable -> L34
            java.lang.Iterable r7 = (java.lang.Iterable) r7     // Catch: java.lang.Throwable -> L34
            java.util.Iterator r7 = r7.iterator()     // Catch: java.lang.Throwable -> L34
        L5c:
            boolean r8 = r7.hasNext()     // Catch: java.lang.Throwable -> L34
            java.lang.Object r9 = r1.mMetadataRepo
            if (r8 == 0) goto Lbd
            java.lang.Object r8 = r7.next()     // Catch: java.lang.Throwable -> L34
            androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8     // Catch: java.lang.Throwable -> L34
            if (r6 != 0) goto L72
            boolean r10 = _COROUTINE._BOUNDARY.changedToDownIgnoreConsumed(r8)     // Catch: java.lang.Throwable -> L34
            if (r10 == 0) goto L5c
        L72:
            int r10 = r8.type     // Catch: java.lang.Throwable -> L34
            if (r10 != r3) goto L79
            r16 = r3
            goto L7b
        L79:
            r16 = r4
        L7b:
            java.lang.Object r10 = r1.mSpanFactory     // Catch: java.lang.Throwable -> L34
            androidx.compose.ui.node.LayoutNode r10 = (androidx.compose.ui.node.LayoutNode) r10     // Catch: java.lang.Throwable -> L34
            long r11 = r8.position     // Catch: java.lang.Throwable -> L34
            java.lang.Object r13 = r1.mEmojiAsDefaultStyleExceptions     // Catch: java.lang.Throwable -> L34
            r15 = r13
            androidx.compose.ui.node.HitTestResult r15 = (androidx.compose.ui.node.HitTestResult) r15     // Catch: java.lang.Throwable -> L34
            r10.getClass()     // Catch: java.lang.Throwable -> L34
            java.lang.String r13 = "hitTestResult"
            kotlin.ResultKt.checkNotNullParameter(r15, r13)     // Catch: java.lang.Throwable -> L34
            androidx.compose.ui.node.NodeChain r10 = r10.nodes     // Catch: java.lang.Throwable -> L34
            androidx.compose.ui.node.NodeCoordinator r13 = r10.outerCoordinator     // Catch: java.lang.Throwable -> L34
            long r13 = r13.m194fromParentPositionMKHz9U(r11)     // Catch: java.lang.Throwable -> L34
            androidx.compose.ui.node.NodeCoordinator r11 = r10.outerCoordinator     // Catch: java.lang.Throwable -> L34
            kotlin.ULong$Companion r12 = androidx.compose.ui.node.NodeCoordinator.PointerInputSource     // Catch: java.lang.Throwable -> L34
            r17 = 1
            r11.m199hitTestYqVAtuI(r12, r13, r15, r16, r17)     // Catch: java.lang.Throwable -> L34
            java.lang.Object r10 = r1.mEmojiAsDefaultStyleExceptions     // Catch: java.lang.Throwable -> L34
            androidx.compose.ui.node.HitTestResult r10 = (androidx.compose.ui.node.HitTestResult) r10     // Catch: java.lang.Throwable -> L34
            boolean r10 = r10.isEmpty()     // Catch: java.lang.Throwable -> L34
            r10 = r10 ^ r3
            if (r10 == 0) goto L5c
            androidx.compose.ui.platform.WeakCache r9 = (androidx.compose.ui.platform.WeakCache) r9     // Catch: java.lang.Throwable -> L34
            long r10 = r8.id     // Catch: java.lang.Throwable -> L34
            java.lang.Object r8 = r1.mEmojiAsDefaultStyleExceptions     // Catch: java.lang.Throwable -> L34
            androidx.compose.ui.node.HitTestResult r8 = (androidx.compose.ui.node.HitTestResult) r8     // Catch: java.lang.Throwable -> L34
            r9.m237addHitPathKNwqfcY(r10, r8)     // Catch: java.lang.Throwable -> L34
            java.lang.Object r8 = r1.mEmojiAsDefaultStyleExceptions     // Catch: java.lang.Throwable -> L34
            androidx.compose.ui.node.HitTestResult r8 = (androidx.compose.ui.node.HitTestResult) r8     // Catch: java.lang.Throwable -> L34
            r8.clear()     // Catch: java.lang.Throwable -> L34
            goto L5c
        Lbd:
            r6 = r9
            androidx.compose.ui.platform.WeakCache r6 = (androidx.compose.ui.platform.WeakCache) r6     // Catch: java.lang.Throwable -> L34
            java.lang.Object r6 = r6.referenceQueue     // Catch: java.lang.Throwable -> L34
            androidx.compose.ui.input.pointer.NodeParent r6 = (androidx.compose.ui.input.pointer.NodeParent) r6     // Catch: java.lang.Throwable -> L34
            r6.removeDetachedPointerInputFilters()     // Catch: java.lang.Throwable -> L34
            androidx.compose.ui.platform.WeakCache r9 = (androidx.compose.ui.platform.WeakCache) r9     // Catch: java.lang.Throwable -> L34
            r9.getClass()     // Catch: java.lang.Throwable -> L34
            java.lang.Object r6 = r9.referenceQueue     // Catch: java.lang.Throwable -> L34
            r7 = r6
            androidx.compose.ui.input.pointer.NodeParent r7 = (androidx.compose.ui.input.pointer.NodeParent) r7     // Catch: java.lang.Throwable -> L34
            java.lang.Object r8 = r9.values     // Catch: java.lang.Throwable -> L34
            r9 = r8
            androidx.compose.ui.layout.LayoutCoordinates r9 = (androidx.compose.ui.layout.LayoutCoordinates) r9     // Catch: java.lang.Throwable -> L34
            boolean r7 = r7.buildCache(r5, r9, r0, r2)     // Catch: java.lang.Throwable -> L34
            if (r7 != 0) goto Lde
        Ldc:
            r0 = r4
            goto Lf2
        Lde:
            r7 = r6
            androidx.compose.ui.input.pointer.NodeParent r7 = (androidx.compose.ui.input.pointer.NodeParent) r7     // Catch: java.lang.Throwable -> L34
            androidx.compose.ui.layout.LayoutCoordinates r8 = (androidx.compose.ui.layout.LayoutCoordinates) r8     // Catch: java.lang.Throwable -> L34
            boolean r2 = r7.dispatchMainEventPass(r5, r8, r0, r2)     // Catch: java.lang.Throwable -> L34
            androidx.compose.ui.input.pointer.NodeParent r6 = (androidx.compose.ui.input.pointer.NodeParent) r6     // Catch: java.lang.Throwable -> L34
            boolean r0 = r6.dispatchFinalEventPass(r0)     // Catch: java.lang.Throwable -> L34
            if (r0 != 0) goto Lf1
            if (r2 == 0) goto Ldc
        Lf1:
            r0 = r3
        Lf2:
            java.util.Collection r2 = r5.values()     // Catch: java.lang.Throwable -> L34
            java.lang.Iterable r2 = (java.lang.Iterable) r2     // Catch: java.lang.Throwable -> L34
            boolean r5 = r2 instanceof java.util.Collection     // Catch: java.lang.Throwable -> L34
            if (r5 == 0) goto L106
            r5 = r2
            java.util.Collection r5 = (java.util.Collection) r5     // Catch: java.lang.Throwable -> L34
            boolean r5 = r5.isEmpty()     // Catch: java.lang.Throwable -> L34
            if (r5 == 0) goto L106
            goto L134
        L106:
            java.util.Iterator r2 = r2.iterator()     // Catch: java.lang.Throwable -> L34
        L10a:
            boolean r5 = r2.hasNext()     // Catch: java.lang.Throwable -> L34
            if (r5 == 0) goto L134
            java.lang.Object r5 = r2.next()     // Catch: java.lang.Throwable -> L34
            androidx.compose.ui.input.pointer.PointerInputChange r5 = (androidx.compose.ui.input.pointer.PointerInputChange) r5     // Catch: java.lang.Throwable -> L34
            java.lang.String r6 = "<this>"
            kotlin.ResultKt.checkNotNullParameter(r5, r6)     // Catch: java.lang.Throwable -> L34
            long r6 = r5.position     // Catch: java.lang.Throwable -> L34
            long r8 = r5.previousPosition     // Catch: java.lang.Throwable -> L34
            long r6 = androidx.compose.ui.geometry.Offset.m78minusMKHz9U(r6, r8)     // Catch: java.lang.Throwable -> L34
            long r8 = androidx.compose.ui.geometry.Offset.Zero     // Catch: java.lang.Throwable -> L34
            boolean r6 = androidx.compose.ui.geometry.Offset.m75equalsimpl0(r6, r8)     // Catch: java.lang.Throwable -> L34
            r6 = r6 ^ r3
            if (r6 == 0) goto L10a
            boolean r5 = r5.isConsumed()     // Catch: java.lang.Throwable -> L34
            if (r5 == 0) goto L10a
            r2 = 2
            goto L135
        L134:
            r2 = r4
        L135:
            r0 = r0 | r2
            r1.mUseEmojiAsDefaultStyle = r4
            return r0
        L139:
            r1.mUseEmojiAsDefaultStyle = r4
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.emoji2.text.EmojiProcessor.m289processBIzXfog(androidx.compose.ui.input.pointer.PointerInputEvent, androidx.compose.ui.input.pointer.PositionCalculator, boolean):int");
    }

    public final void processCancel() {
        if (this.mUseEmojiAsDefaultStyle) {
            return;
        }
        ((ViewModelStore) this.mGlyphChecker).clear();
        NodeParent nodeParent = (NodeParent) ((WeakCache) this.mMetadataRepo).referenceQueue;
        nodeParent.dispatchCancel();
        nodeParent.children.clear();
    }

    public EmojiProcessor(MetadataRepo metadataRepo, ULong.Companion companion, DefaultGlyphChecker defaultGlyphChecker, Set set) {
        this.mSpanFactory = companion;
        this.mMetadataRepo = metadataRepo;
        this.mGlyphChecker = defaultGlyphChecker;
        this.mUseEmojiAsDefaultStyle = false;
        this.mEmojiAsDefaultStyleExceptions = null;
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            int[] iArr = (int[]) it.next();
            String str = new String(iArr, 0, iArr.length);
            process(str, 0, str.length(), 1, true, new Symbol(1, str));
        }
    }

    public EmojiProcessor(boolean z, MutableState mutableState) {
        this.mUseEmojiAsDefaultStyle = z;
        this.mSpanFactory = mutableState;
        this.mMetadataRepo = ResultKt.Animatable$default();
        this.mGlyphChecker = new ArrayList();
    }
}
