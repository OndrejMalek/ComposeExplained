package kotlinx.coroutines.flow;

import androidx.compose.animation.core.AnimationVector;
import androidx.compose.animation.core.Animations;
import androidx.compose.animation.core.FloatAnimationSpec;
import androidx.compose.animation.core.FloatSpringSpec;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode;
import androidx.core.provider.FontsContractCompat$FontInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.collections.MapsKt___MapsJvmKt;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.IntRange;

/* loaded from: classes.dex */
public final class SafeFlow implements Animations, Flow {
    public final int $r8$classId;
    public Object block;

    public SafeFlow() {
        SharingCommand sharingCommand = SharingCommand.START;
        this.$r8$classId = 1;
        this.block = sharingCommand;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0032  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0044  */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector r9, kotlin.coroutines.Continuation r10) {
        /*
            r8 = this;
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r8.$r8$classId
            java.lang.Object r3 = r8.block
            switch(r2) {
                case 0: goto L13;
                default: goto Lb;
            }
        Lb:
            java.lang.Object r9 = r9.emit(r3, r10)
            if (r9 != r1) goto L12
            r0 = r9
        L12:
            return r0
        L13:
            boolean r2 = r10 instanceof kotlinx.coroutines.flow.AbstractFlow$collect$1
            if (r2 == 0) goto L26
            r2 = r10
            kotlinx.coroutines.flow.AbstractFlow$collect$1 r2 = (kotlinx.coroutines.flow.AbstractFlow$collect$1) r2
            int r4 = r2.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = r4 & r5
            if (r6 == 0) goto L26
            int r4 = r4 - r5
            r2.label = r4
            goto L2b
        L26:
            kotlinx.coroutines.flow.AbstractFlow$collect$1 r2 = new kotlinx.coroutines.flow.AbstractFlow$collect$1
            r2.<init>(r8, r10)
        L2b:
            java.lang.Object r10 = r2.result
            int r4 = r2.label
            r5 = 1
            if (r4 == 0) goto L44
            if (r4 != r5) goto L3c
            kotlinx.coroutines.flow.internal.SafeCollector r9 = r2.L$0
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Throwable -> L3a
            goto L64
        L3a:
            r10 = move-exception
            goto L6e
        L3c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L44:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.coroutines.flow.internal.SafeCollector r10 = new kotlinx.coroutines.flow.internal.SafeCollector
            kotlin.coroutines.CoroutineContext r4 = r2._context
            kotlin.ResultKt.checkNotNull(r4)
            r10.<init>(r9, r4)
            r2.L$0 = r10     // Catch: java.lang.Throwable -> L6c
            r2.label = r5     // Catch: java.lang.Throwable -> L6c
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3     // Catch: java.lang.Throwable -> L6c
            java.lang.Object r9 = r3.invoke(r10, r2)     // Catch: java.lang.Throwable -> L6c
            if (r9 != r1) goto L5e
            goto L5f
        L5e:
            r9 = r0
        L5f:
            if (r9 != r1) goto L63
            r0 = r1
            goto L67
        L63:
            r9 = r10
        L64:
            r9.releaseIntercepted()
        L67:
            return r0
        L68:
            r7 = r10
            r10 = r9
            r9 = r7
            goto L6e
        L6c:
            r9 = move-exception
            goto L68
        L6e:
            r9.releaseIntercepted()
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.SafeFlow.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public FloatAnimationSpec get(int i) {
        Object obj = this.block;
        int i2 = this.$r8$classId;
        switch (i2) {
            case 0:
                return (FloatAnimationSpec) obj;
            case 1:
                switch (i2) {
                    case 1:
                        return (FloatSpringSpec) ((List) obj).get(i);
                    default:
                        return (FloatSpringSpec) obj;
                }
            default:
                switch (i2) {
                    case 1:
                        return (FloatSpringSpec) ((List) obj).get(i);
                    default:
                        return (FloatSpringSpec) obj;
                }
        }
    }

    public SafeFlow(TrieNode trieNode, int i) {
        ResultKt.checkNotNullParameter(trieNode, "node");
        this.block = trieNode;
        this.$r8$classId = i;
    }

    public /* synthetic */ SafeFlow(Object obj) {
        this.$r8$classId = 0;
        this.block = obj;
    }

    public SafeFlow(int i, FontsContractCompat$FontInfo[] fontsContractCompat$FontInfoArr) {
        this.$r8$classId = i;
        this.block = fontsContractCompat$FontInfoArr;
    }

    public SafeFlow(float f, float f2, AnimationVector animationVector) {
        this.$r8$classId = 1;
        IntRange until = ResultKt.until(0, animationVector.getSize$animation_core_release());
        ArrayList arrayList = new ArrayList(MapsKt___MapsJvmKt.collectionSizeOrDefault(until));
        Iterator it = until.iterator();
        while (((IntProgressionIterator) it).hasNext) {
            arrayList.add(new FloatSpringSpec(f, f2, animationVector.get$animation_core_release(((IntProgressionIterator) it).nextInt())));
        }
        this.block = arrayList;
    }

    public SafeFlow(float f, float f2) {
        this.$r8$classId = 2;
        this.block = new FloatSpringSpec(f, f2, 0.01f);
    }
}
