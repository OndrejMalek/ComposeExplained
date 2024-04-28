package kotlin;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.StrictMode;
import android.os.Trace;
import android.text.SpannableStringBuilder;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.Animation;
import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.animation.core.AnimationVector;
import androidx.compose.animation.core.Easing;
import androidx.compose.animation.core.SuspendAnimationKt$animate$4;
import androidx.compose.animation.core.TargetBasedAnimation;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.text.selection.MouseSelectionObserver;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.DerivedStateObserver;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MonotonicFrameClock;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ParcelableSnapshotMutableState;
import androidx.compose.runtime.RecomposeScope;
import androidx.compose.runtime.RecomposeScopeImpl;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt__DerivedStateKt;
import androidx.compose.runtime.StructuralEqualityPolicy;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.MotionDurationScale;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusInvalidationManager;
import androidx.compose.ui.focus.FocusOwnerImpl;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.focus.OneDimensionalFocusSearchKt$generateAndSearchChildren$1;
import androidx.compose.ui.layout.BeyondBoundsLayoutKt;
import androidx.compose.ui.modifier.ModifierLocal;
import androidx.compose.ui.modifier.ProvidableModifierLocal;
import androidx.compose.ui.node.ComposeUiNode$Companion$SetDensity$1;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.Snake;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.WeakCache;
import androidx.compose.ui.text.font.AndroidFontResolveInterceptor;
import androidx.compose.ui.text.font.FontFamilyResolverImpl;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.unit.TextUnitType;
import androidx.core.content.res.FontResourcesParserCompat$Api21Impl;
import androidx.emoji2.text.MetadataRepo;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1;
import eu.malek.composesample.MainActivityKt$Greetings$1;
import eu.malek.composesample.TestDataClass;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayAsCollection;
import kotlin.collections.EmptyList;
import kotlin.collections.MapsKt___MapsJvmKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda0;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.CoroutineContext$plus$1;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;
import kotlin.internal.PlatformImplementations$ReflectThrowable;
import kotlin.internal.jdk7.JDK7PlatformImplementations$ReflectSdkVersion;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.FunctionBase;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.math.MathKt;
import kotlin.ranges.IntProgression;
import kotlin.ranges.IntRange;
import kotlin.reflect.KClass;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt__StringsKt$rangesDelimitedBy$2;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.Active;
import kotlinx.coroutines.BlockingCoroutine;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletedContinuation;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.CoroutineContextKt$foldCopies$1;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DefaultExecutorKt;
import kotlinx.coroutines.Delay;
import kotlinx.coroutines.DispatchedCoroutine;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.EventLoopImplPlatform;
import kotlinx.coroutines.Incomplete;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobImpl;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.JobNode;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.LazyStandaloneCoroutine;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.ThreadLocalEventLoop;
import kotlinx.coroutines.UndispatchedCoroutine;
import kotlinx.coroutines.UndispatchedMarker;
import kotlinx.coroutines.android.HandlerContext;
import kotlinx.coroutines.internal.AtomicKt;
import kotlinx.coroutines.internal.ContextScope;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.ExceptionsConstructorKt$safeCtor$1;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.Symbol;
import kotlinx.coroutines.scheduling.DefaultScheduler;

/* loaded from: classes.dex */
public abstract class ResultKt implements MouseSelectionObserver {
    public static Method sIsTagEnabledMethod;
    public static long sTraceTagApp;

    public static Animatable Animatable$default() {
        return new Animatable(Float.valueOf(0.0f), VectorConvertersKt.FloatToVector, Float.valueOf(0.01f), 8);
    }

    public static final long Constraints(int i, int i2, int i3, int i4) {
        if (i2 < i) {
            throw new IllegalArgumentException(("maxWidth(" + i2 + ") must be >= than minWidth(" + i + ')').toString());
        }
        if (i4 < i3) {
            throw new IllegalArgumentException(("maxHeight(" + i4 + ") must be >= than minHeight(" + i3 + ')').toString());
        }
        if (i >= 0 && i3 >= 0) {
            return DrawResult.m65createConstraintsZbe2FdA$ui_unit_release(i, i2, i3, i4);
        }
        throw new IllegalArgumentException(("minWidth(" + i + ") and minHeight(" + i3 + ") must be >= 0").toString());
    }

    public static /* synthetic */ long Constraints$default(int i, int i2, int i3) {
        if ((i3 & 2) != 0) {
            i = Integer.MAX_VALUE;
        }
        if ((i3 & 8) != 0) {
            i2 = Integer.MAX_VALUE;
        }
        return Constraints(0, i, 0, i2);
    }

    public static final ContextScope CoroutineScope(CoroutineContext coroutineContext) {
        if (coroutineContext.get(Job.Key.$$INSTANCE) == null) {
            coroutineContext = coroutineContext.plus(new JobImpl(null));
        }
        return new ContextScope(coroutineContext);
    }

    /* renamed from: DpOffset-YgX7TsA, reason: not valid java name */
    public static final long m293DpOffsetYgX7TsA(float f, float f2) {
        long floatToIntBits = (Float.floatToIntBits(f2) & 4294967295L) | (Float.floatToIntBits(f) << 32);
        int i = DpOffset.$r8$clinit;
        return floatToIntBits;
    }

    /* renamed from: DpSize-YgX7TsA, reason: not valid java name */
    public static final long m294DpSizeYgX7TsA(float f, float f2) {
        long floatToIntBits = (Float.floatToIntBits(f2) & 4294967295L) | (Float.floatToIntBits(f) << 32);
        int i = DpSize.$r8$clinit;
        return floatToIntBits;
    }

    public static final void Greeting(final String str, Modifier modifier, final TestDataClass testDataClass, Composer composer, final int i, final int i2) {
        checkNotNullParameter(str, "textFhfg78");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(-1025646789);
        final Modifier modifier2 = (i2 & 2) != 0 ? Modifier.Companion.$$INSTANCE : modifier;
        int i3 = i & 112;
        TextKt.m58Text4IGK_g(str.concat("!"), modifier2, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerImpl, i3, 0, 131068);
        StringBuilder sb = new StringBuilder("dp=");
        sb.append(testDataClass != null ? testDataClass.text8798SDF : null);
        sb.append("}!");
        TextKt.m58Text4IGK_g(sb.toString(), modifier2, 0L, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composerImpl, i3, 0, 131068);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.block = new Function2() { // from class: eu.malek.composesample.MainActivityKt$Greeting$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                ((Number) obj2).intValue();
                int updateChangedFlags = ResultKt.updateChangedFlags(i | 1);
                Modifier modifier3 = modifier2;
                TestDataClass testDataClass2 = testDataClass;
                ResultKt.Greeting(str, modifier3, testDataClass2, (Composer) obj, updateChangedFlags, i2);
                return Unit.INSTANCE;
            }
        };
    }

    public static final void Greetings(TestDataClass testDataClass, Composer composer, int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(1378362812);
        StringBuilder sb = new StringBuilder();
        sb.append(testDataClass != null ? testDataClass.text8798SDF : null);
        sb.append('0');
        Greeting(sb.toString(), null, testDataClass, composerImpl, 512, 2);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(testDataClass != null ? testDataClass.text8798SDF : null);
        sb2.append('1');
        Greeting(sb2.toString(), null, testDataClass, composerImpl, 512, 2);
        StringBuilder sb3 = new StringBuilder();
        sb3.append(testDataClass != null ? testDataClass.text8798SDF : null);
        sb3.append('2');
        Greeting(sb3.toString(), null, testDataClass, composerImpl, 512, 2);
        StringBuilder sb4 = new StringBuilder();
        sb4.append(testDataClass != null ? testDataClass.text8798SDF : null);
        sb4.append('3');
        Greeting(sb4.toString(), null, testDataClass, composerImpl, 512, 2);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.block = new MainActivityKt$Greetings$1(i, 0, testDataClass);
    }

    public static final long IntOffset(int i, int i2) {
        long j = (i2 & 4294967295L) | (i << 32);
        int i3 = IntOffset.$r8$clinit;
        return j;
    }

    public static final long IntSize(int i, int i2) {
        return (i2 & 4294967295L) | (i << 32);
    }

    public static final void access$GreetingsGreetings(TestDataClass testDataClass, Composer composer, int i) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startRestartGroup(344898202);
        Greetings(testDataClass, composerImpl, 8);
        Greetings(testDataClass, composerImpl, 8);
        Greetings(testDataClass != null ? testDataClass.dp54jh45 : null, composerImpl, 8);
        RecomposeScopeImpl endRestartGroup = composerImpl.endRestartGroup();
        if (endRestartGroup == null) {
            return;
        }
        endRestartGroup.block = new MainActivityKt$Greetings$1(i, 1, testDataClass);
    }

    public static final void access$checkIndex(int i, List list) {
        int size = list.size();
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("Index " + i + " is out of bounds. The list has " + size + " elements.");
        }
    }

    public static final void access$checkSubIndex(List list, int i, int i2) {
        int size = list.size();
        if (i > i2) {
            throw new IllegalArgumentException("Indices are out of order. fromIndex (" + i + ") is greater than toIndex (" + i2 + ").");
        }
        if (i < 0) {
            throw new IndexOutOfBoundsException("fromIndex (" + i + ") is less than 0.");
        }
        if (i2 <= size) {
            return;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i2 + ") is more than than the list size (" + size + ')');
    }

    public static final boolean access$containsMark(int[] iArr, int i) {
        return (iArr[(i * 5) + 1] & 67108864) != 0;
    }

    public static final int access$dataAnchor(int[] iArr, int i) {
        return iArr[(i * 5) + 4];
    }

    public static final int access$groupSize(int[] iArr, int i) {
        return iArr[(i * 5) + 3];
    }

    public static final boolean access$hasAux(int[] iArr, int i) {
        return (iArr[(i * 5) + 1] & 268435456) != 0;
    }

    public static final boolean access$hasObjectKey(int[] iArr, int i) {
        return (iArr[(i * 5) + 1] & 536870912) != 0;
    }

    public static final Object[] access$insertEntryAtIndex(Object[] objArr, int i, Object obj, Object obj2) {
        Object[] objArr2 = new Object[objArr.length + 2];
        MapsKt___MapsJvmKt.copyInto$default(objArr, objArr2, 0, i, 6);
        MapsKt___MapsJvmKt.copyInto(objArr, objArr2, i + 2, i, objArr.length);
        objArr2[i] = obj;
        objArr2[i + 1] = obj2;
        return objArr2;
    }

    public static final boolean access$isNode(int[] iArr, int i) {
        return (iArr[(i * 5) + 1] & 1073741824) != 0;
    }

    public static final int access$locationOf(ArrayList arrayList, int i, int i2) {
        int search = search(arrayList, i, i2);
        return search >= 0 ? search : -(search + 1);
    }

    public static final int access$lowestBitOf(long j) {
        int i;
        if ((4294967295L & j) == 0) {
            i = 32;
            j >>= 32;
        } else {
            i = 0;
        }
        if ((65535 & j) == 0) {
            i += 16;
            j >>= 16;
        }
        if ((255 & j) == 0) {
            i += 8;
            j >>= 8;
        }
        if ((15 & j) == 0) {
            i += 4;
            j >>= 4;
        }
        if ((1 & j) != 0) {
            return i;
        }
        if ((2 & j) != 0) {
            return i + 1;
        }
        if ((4 & j) != 0) {
            return i + 2;
        }
        if ((j & 8) != 0) {
            return i + 3;
        }
        return -1;
    }

    public static final int access$nodeCount(int[] iArr, int i) {
        return iArr[(i * 5) + 1] & 67108863;
    }

    public static final int access$parentAnchor(int[] iArr, int i) {
        return iArr[(i * 5) + 2];
    }

    public static final Object[] access$removeEntryAtIndex(Object[] objArr, int i) {
        Object[] objArr2 = new Object[objArr.length - 2];
        MapsKt___MapsJvmKt.copyInto$default(objArr, objArr2, 0, i, 6);
        MapsKt___MapsJvmKt.copyInto(objArr, objArr2, i, i + 2, objArr.length);
        return objArr2;
    }

    public static final Object[] access$removeNodeAtIndex(Object[] objArr, int i) {
        Object[] objArr2 = new Object[objArr.length - 1];
        MapsKt___MapsJvmKt.copyInto$default(objArr, objArr2, 0, i, 6);
        MapsKt___MapsJvmKt.copyInto(objArr, objArr2, i, i + 1, objArr.length);
        return objArr2;
    }

    public static final int access$slotAnchor(int[] iArr, int i) {
        int i2 = i * 5;
        return countOneBits(iArr[i2 + 1] >> 28) + iArr[i2 + 4];
    }

    public static final void access$updateGroupSize(int[] iArr, int i, int i2) {
        EffectsKt.runtimeCheck(i2 >= 0);
        iArr[(i * 5) + 3] = i2;
    }

    public static final void access$updateNodeCount(int[] iArr, int i, int i2) {
        EffectsKt.runtimeCheck(i2 >= 0 && i2 < 67108863);
        int i3 = (i * 5) + 1;
        iArr[i3] = i2 | (iArr[i3] & (-67108864));
    }

    public static void addSuppressed(Throwable th, Throwable th2) {
        checkNotNullParameter(th, "<this>");
        checkNotNullParameter(th2, "exception");
        if (th != th2) {
            Integer num = JDK7PlatformImplementations$ReflectSdkVersion.sdkVersion;
            if (num == null || num.intValue() >= 19) {
                th.addSuppressed(th2);
                return;
            }
            Method method = PlatformImplementations$ReflectThrowable.addSuppressed;
            if (method != null) {
                method.invoke(th, th2);
            }
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:8:0x0027 */
    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: androidx.compose.animation.core.SuspendAnimationKt$animate$4 */
    /* JADX DEBUG: Multi-variable search result rejected for r2v10, resolved type: kotlin.jvm.internal.Ref$ObjectRef */
    /* JADX DEBUG: Multi-variable search result rejected for r2v7, resolved type: kotlin.jvm.internal.Ref$ObjectRef */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0029  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0104 A[Catch: CancellationException -> 0x003b, TRY_LEAVE, TryCatch #1 {CancellationException -> 0x003b, blocks: (B:13:0x0035, B:16:0x00ef, B:18:0x0104, B:31:0x004f), top: B:8:0x0027 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x012d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x013d  */
    /* JADX WARN: Type inference failed for: r15v0, types: [java.lang.Object, kotlin.jvm.internal.Ref$ObjectRef] */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v11, types: [kotlin.jvm.internal.Ref$ObjectRef] */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object animate(final androidx.compose.animation.core.AnimationState r25, androidx.compose.animation.core.Animation r26, long r27, final androidx.compose.animation.core.Animatable$runAnimation$2.AnonymousClass1 r29, kotlin.coroutines.Continuation r30) {
        /*
            r9 = r25
            r0 = r30
            boolean r1 = r0 instanceof androidx.compose.animation.core.SuspendAnimationKt$animate$4
            if (r1 == 0) goto L18
            r1 = r0
            androidx.compose.animation.core.SuspendAnimationKt$animate$4 r1 = (androidx.compose.animation.core.SuspendAnimationKt$animate$4) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L18
            int r2 = r2 - r3
            r1.label = r2
        L16:
            r0 = r1
            goto L1e
        L18:
            androidx.compose.animation.core.SuspendAnimationKt$animate$4 r1 = new androidx.compose.animation.core.SuspendAnimationKt$animate$4
            r1.<init>(r0)
            goto L16
        L1e:
            java.lang.Object r1 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r10 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r11 = 2
            r12 = 1
            r13 = 0
            if (r2 == 0) goto L54
            if (r2 == r12) goto L47
            if (r2 != r11) goto L3f
            kotlin.jvm.internal.Ref$ObjectRef r2 = r0.L$3
            kotlin.jvm.functions.Function1 r3 = r0.L$2
            androidx.compose.animation.core.Animation r4 = r0.L$1
            androidx.compose.animation.core.AnimationState r5 = r0.L$0
            throwOnFailure(r1)     // Catch: java.util.concurrent.CancellationException -> L3b
            r6 = r11
            goto Lef
        L3b:
            r0 = move-exception
            r9 = r5
            goto L136
        L3f:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L47:
            kotlin.jvm.internal.Ref$ObjectRef r2 = r0.L$3
            kotlin.jvm.functions.Function1 r3 = r0.L$2
            androidx.compose.animation.core.Animation r4 = r0.L$1
            androidx.compose.animation.core.AnimationState r5 = r0.L$0
            throwOnFailure(r1)     // Catch: java.util.concurrent.CancellationException -> L3b
            goto Lef
        L54:
            throwOnFailure(r1)
            r14 = r26
            androidx.compose.animation.core.TargetBasedAnimation r14 = (androidx.compose.animation.core.TargetBasedAnimation) r14
            r2 = 0
            java.lang.Object r16 = r14.getValueFromNanos(r2)
            long r4 = r14.durationNanos
            int r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r1 < 0) goto L6c
            androidx.compose.animation.core.AnimationVector r1 = r14.endVelocity
        L69:
            r18 = r1
            goto L79
        L6c:
            androidx.compose.animation.core.AnimationVector r5 = r14.targetValueVector
            androidx.compose.animation.core.AnimationVector r6 = r14.initialVelocityVector
            androidx.compose.animation.core.VectorizedFiniteAnimationSpec r1 = r14.animationSpec
            androidx.compose.animation.core.AnimationVector r4 = r14.initialValueVector
            androidx.compose.animation.core.AnimationVector r1 = r1.getVelocityFromNanos(r2, r4, r5, r6)
            goto L69
        L79:
            kotlin.jvm.internal.Ref$ObjectRef r15 = new kotlin.jvm.internal.Ref$ObjectRef
            r15.<init>()
            r1 = -9223372036854775808
            int r1 = (r27 > r1 ? 1 : (r27 == r1 ? 0 : -1))
            kotlin.coroutines.CoroutineContext r2 = r0._context
            if (r1 != 0) goto Lbb
            checkNotNull(r2)     // Catch: java.util.concurrent.CancellationException -> Lb9
            float r7 = getDurationScale(r2)     // Catch: java.util.concurrent.CancellationException -> Lb9
            androidx.compose.animation.core.SuspendAnimationKt$animate$6 r8 = new androidx.compose.animation.core.SuspendAnimationKt$animate$6     // Catch: java.util.concurrent.CancellationException -> Lb9
            r1 = r8
            r2 = r15
            r3 = r16
            r4 = r14
            r5 = r18
            r6 = r25
            r11 = r8
            r8 = r29
            r1.<init>()     // Catch: java.util.concurrent.CancellationException -> Lb9
            r0.L$0 = r9     // Catch: java.util.concurrent.CancellationException -> Lb9
            r0.L$1 = r14     // Catch: java.util.concurrent.CancellationException -> Lb9
            r8 = r29
            r0.L$2 = r8     // Catch: java.util.concurrent.CancellationException -> Lb9
            r0.L$3 = r15     // Catch: java.util.concurrent.CancellationException -> Lb9
            r0.label = r12     // Catch: java.util.concurrent.CancellationException -> Lb9
            java.lang.Object r1 = callWithFrameNanos(r14, r11, r0)     // Catch: java.util.concurrent.CancellationException -> Lb9
            if (r1 != r10) goto Lb1
            return r10
        Lb1:
            r3 = r8
            r5 = r9
            r4 = r14
            r2 = r15
            goto Lef
        Lb6:
            r2 = r15
            goto L136
        Lb9:
            r0 = move-exception
            goto Lb6
        Lbb:
            r8 = r29
            androidx.compose.animation.core.AnimationScope r11 = new androidx.compose.animation.core.AnimationScope     // Catch: java.util.concurrent.CancellationException -> L133
            androidx.compose.animation.core.TwoWayConverterImpl r1 = r14.typeConverter     // Catch: java.util.concurrent.CancellationException -> L133
            java.lang.Object r3 = r14.targetValue     // Catch: java.util.concurrent.CancellationException -> L133
            androidx.compose.animation.core.SuspendAnimationKt$animate$7 r4 = new androidx.compose.animation.core.SuspendAnimationKt$animate$7     // Catch: java.util.concurrent.CancellationException -> L133
            r4.<init>(r9, r13)     // Catch: java.util.concurrent.CancellationException -> L133
            r12 = r15
            r15 = r11
            r17 = r1
            r19 = r27
            r21 = r3
            r22 = r27
            r24 = r4
            r15.<init>(r16, r17, r18, r19, r21, r22, r24)     // Catch: java.util.concurrent.CancellationException -> L130
            checkNotNull(r2)     // Catch: java.util.concurrent.CancellationException -> L130
            float r4 = getDurationScale(r2)     // Catch: java.util.concurrent.CancellationException -> L130
            r1 = r11
            r2 = r27
            r5 = r14
            r6 = r25
            r7 = r29
            doAnimationFrameWithScale(r1, r2, r4, r5, r6, r7)     // Catch: java.util.concurrent.CancellationException -> L130
            r12.element = r11     // Catch: java.util.concurrent.CancellationException -> L130
            r3 = r8
            r5 = r9
            r2 = r12
            r4 = r14
        Lef:
            java.lang.Object r1 = r2.element     // Catch: java.util.concurrent.CancellationException -> L3b
            checkNotNull(r1)     // Catch: java.util.concurrent.CancellationException -> L3b
            androidx.compose.animation.core.AnimationScope r1 = (androidx.compose.animation.core.AnimationScope) r1     // Catch: java.util.concurrent.CancellationException -> L3b
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r1.isRunning$delegate     // Catch: java.util.concurrent.CancellationException -> L3b
            java.lang.Object r1 = r1.getValue()     // Catch: java.util.concurrent.CancellationException -> L3b
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch: java.util.concurrent.CancellationException -> L3b
            boolean r1 = r1.booleanValue()     // Catch: java.util.concurrent.CancellationException -> L3b
            if (r1 == 0) goto L12d
            kotlin.coroutines.CoroutineContext r1 = r0._context     // Catch: java.util.concurrent.CancellationException -> L3b
            checkNotNull(r1)     // Catch: java.util.concurrent.CancellationException -> L3b
            float r17 = getDurationScale(r1)     // Catch: java.util.concurrent.CancellationException -> L3b
            androidx.compose.animation.core.SuspendAnimationKt$animate$9 r1 = new androidx.compose.animation.core.SuspendAnimationKt$animate$9     // Catch: java.util.concurrent.CancellationException -> L3b
            r15 = r1
            r16 = r2
            r18 = r4
            r19 = r5
            r20 = r3
            r15.<init>()     // Catch: java.util.concurrent.CancellationException -> L3b
            r0.L$0 = r5     // Catch: java.util.concurrent.CancellationException -> L3b
            r0.L$1 = r4     // Catch: java.util.concurrent.CancellationException -> L3b
            r0.L$2 = r3     // Catch: java.util.concurrent.CancellationException -> L3b
            r0.L$3 = r2     // Catch: java.util.concurrent.CancellationException -> L3b
            r6 = 2
            r0.label = r6     // Catch: java.util.concurrent.CancellationException -> L3b
            java.lang.Object r1 = callWithFrameNanos(r4, r1, r0)     // Catch: java.util.concurrent.CancellationException -> L3b
            if (r1 != r10) goto Lef
            return r10
        L12d:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L130:
            r0 = move-exception
        L131:
            r2 = r12
            goto L136
        L133:
            r0 = move-exception
            r12 = r15
            goto L131
        L136:
            java.lang.Object r1 = r2.element
            androidx.compose.animation.core.AnimationScope r1 = (androidx.compose.animation.core.AnimationScope) r1
            if (r1 != 0) goto L13d
            goto L144
        L13d:
            java.lang.Boolean r3 = java.lang.Boolean.FALSE
            androidx.compose.runtime.ParcelableSnapshotMutableState r1 = r1.isRunning$delegate
            r1.setValue(r3)
        L144:
            java.lang.Object r1 = r2.element
            androidx.compose.animation.core.AnimationScope r1 = (androidx.compose.animation.core.AnimationScope) r1
            if (r1 == 0) goto L154
            long r1 = r1.lastFrameTimeNanos
            long r3 = r9.lastFrameTimeNanos
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L154
            r9.isRunning = r13
        L154:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ResultKt.animate(androidx.compose.animation.core.AnimationState, androidx.compose.animation.core.Animation, long, androidx.compose.animation.core.Animatable$runAnimation$2$1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static boolean areEqual(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public static ArrayList arrayListOf(Object... objArr) {
        return objArr.length == 0 ? new ArrayList() : new ArrayList(new ArrayAsCollection(objArr, true));
    }

    public static Map asMutableMap(AbstractMap abstractMap) {
        if (!(abstractMap instanceof KMappedMarker) || (abstractMap instanceof KMutableMap)) {
            return abstractMap;
        }
        throwCce(abstractMap, "kotlin.collections.MutableMap");
        throw null;
    }

    public static void beforeCheckcastToFunctionOfArity(int i, Object obj) {
        int i2;
        if (obj != null) {
            if (obj instanceof Function) {
                if (obj instanceof FunctionBase) {
                    i2 = ((FunctionBase) obj).getArity();
                } else if (obj instanceof Function0) {
                    i2 = 0;
                } else if (obj instanceof Function1) {
                    i2 = 1;
                } else if (obj instanceof Function2) {
                    i2 = 2;
                } else if (obj instanceof Function3) {
                    i2 = 3;
                } else if (obj instanceof Function4) {
                    i2 = 4;
                } else if (obj instanceof Function5) {
                    i2 = 5;
                } else {
                    boolean z = obj instanceof ComposableLambda;
                    i2 = z ? 6 : z ? 7 : z ? 8 : z ? 9 : z ? 10 : z ? 11 : z ? 13 : z ? 14 : z ? 15 : z ? 16 : z ? 17 : z ? 18 : z ? 19 : z ? 20 : z ? 21 : -1;
                }
                if (i2 == i) {
                    return;
                }
            }
            throwCce(obj, "kotlin.jvm.functions.Function" + i);
            throw null;
        }
    }

    public static final int binarySearch(int[] iArr, int i) {
        int length = iArr.length - 1;
        int i2 = 0;
        while (i2 <= length) {
            int i3 = (i2 + length) >>> 1;
            int i4 = iArr[i3];
            if (i > i4) {
                i2 = i3 + 1;
            } else {
                if (i >= i4) {
                    return i3;
                }
                length = i3 - 1;
            }
        }
        return -(i2 + 1);
    }

    public static final int bitsForSlot(int i, int i2) {
        return i << (((i2 % 10) * 3) + 1);
    }

    public static final Object callWithFrameNanos(Animation animation, Function1 function1, SuspendAnimationKt$animate$4 suspendAnimationKt$animate$4) {
        ((TargetBasedAnimation) animation).animationSpec.isInfinite();
        ExceptionsConstructorKt$safeCtor$1 exceptionsConstructorKt$safeCtor$1 = new ExceptionsConstructorKt$safeCtor$1(1, function1);
        CoroutineContext coroutineContext = suspendAnimationKt$animate$4._context;
        checkNotNull(coroutineContext);
        return getMonotonicFrameClock(coroutineContext).withFrameNanos(exceptionsConstructorKt$safeCtor$1, suspendAnimationKt$animate$4);
    }

    public static void checkNotNull(Object obj) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException();
        sanitizeStackTrace(ResultKt.class.getName(), nullPointerException);
        throw nullPointerException;
    }

    public static void checkNotNull$1(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static void checkNotNullExpressionValue(Object obj, String str) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException(str.concat(" must not be null"));
        sanitizeStackTrace(ResultKt.class.getName(), nullPointerException);
        throw nullPointerException;
    }

    public static void checkNotNullParameter(Object obj, String str) {
        if (obj == null) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String name = ResultKt.class.getName();
            int i = 0;
            while (!stackTrace[i].getClassName().equals(name)) {
                i++;
            }
            while (stackTrace[i].getClassName().equals(name)) {
                i++;
            }
            StackTraceElement stackTraceElement = stackTrace[i];
            NullPointerException nullPointerException = new NullPointerException("Parameter specified as non-null is null: method " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ", parameter " + str);
            sanitizeStackTrace(ResultKt.class.getName(), nullPointerException);
            throw nullPointerException;
        }
    }

    public static void checkRadix() {
        if (10 <= new IntProgression(2, 36, 1).last) {
            return;
        }
        throw new IllegalArgumentException("radix 10 was not in valid range " + new IntProgression(2, 36, 1));
    }

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static double coerceIn(double d, double d2, double d3) {
        if (d2 <= d3) {
            return d < d2 ? d2 : d > d3 ? d3 : d;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + d3 + " is less than minimum " + d2 + '.');
    }

    public static int compare(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [kotlin.comparisons.ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda0] */
    public static ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda0 compareBy(final Function1... function1Arr) {
        if (function1Arr.length > 0) {
            return new Comparator() { // from class: kotlin.comparisons.ComparisonsKt__ComparisonsKt$$ExternalSyntheticLambda0
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    Function1[] function1Arr2 = function1Arr;
                    ResultKt.checkNotNullParameter(function1Arr2, "$selectors");
                    for (Function1 function1 : function1Arr2) {
                        int compareValues = ResultKt.compareValues((Comparable) function1.invoke(obj), (Comparable) function1.invoke(obj2));
                        if (compareValues != 0) {
                            return compareValues;
                        }
                    }
                    return 0;
                }
            };
        }
        throw new IllegalArgumentException("Failed requirement.".toString());
    }

    public static int compareValues(Comparable comparable, Comparable comparable2) {
        if (comparable == comparable2) {
            return 0;
        }
        if (comparable == null) {
            return -1;
        }
        if (comparable2 == null) {
            return 1;
        }
        return comparable.compareTo(comparable2);
    }

    public static final ComposableLambdaImpl composableLambda(Composer composer, int i, Lambda lambda) {
        ComposableLambdaImpl composableLambdaImpl;
        checkNotNullParameter(composer, "composer");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(i);
        Object nextSlot = composerImpl.nextSlot();
        if (nextSlot == Composer.Companion.Empty) {
            composableLambdaImpl = new ComposableLambdaImpl(i, true);
            composerImpl.updateValue(composableLambdaImpl);
        } else {
            checkNotNull(nextSlot, "null cannot be cast to non-null type androidx.compose.runtime.internal.ComposableLambdaImpl");
            composableLambdaImpl = (ComposableLambdaImpl) nextSlot;
        }
        composableLambdaImpl.update(lambda);
        composerImpl.end(false);
        return composableLambdaImpl;
    }

    public static final ComposableLambdaImpl composableLambdaInstance(int i, Lambda lambda, boolean z) {
        ComposableLambdaImpl composableLambdaImpl = new ComposableLambdaImpl(i, z);
        composableLambdaImpl.update(lambda);
        return composableLambdaImpl;
    }

    public static byte[] compress(byte[] bArr) {
        Deflater deflater = new Deflater(1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
            try {
                deflaterOutputStream.write(bArr);
                deflaterOutputStream.close();
                deflater.end();
                return byteArrayOutputStream.toByteArray();
            } finally {
            }
        } catch (Throwable th) {
            deflater.end();
            throw th;
        }
    }

    /* renamed from: constrain-4WqzIAM, reason: not valid java name */
    public static final long m295constrain4WqzIAM(long j, long j2) {
        return IntSize(coerceIn((int) (j2 >> 32), Constraints.m278getMinWidthimpl(j), Constraints.m276getMaxWidthimpl(j)), coerceIn((int) (j2 & 4294967295L), Constraints.m277getMinHeightimpl(j), Constraints.m275getMaxHeightimpl(j)));
    }

    public static final AnimationVector copy(AnimationVector animationVector) {
        checkNotNullParameter(animationVector, "<this>");
        AnimationVector newVector$animation_core_release = animationVector.newVector$animation_core_release();
        int size$animation_core_release = newVector$animation_core_release.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            newVector$animation_core_release.set$animation_core_release(animationVector.get$animation_core_release(i), i);
        }
        return newVector$animation_core_release;
    }

    public static boolean copyToFile(File file, InputStream inputStream) {
        FileOutputStream fileOutputStream;
        StrictMode.ThreadPolicy allowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        FileOutputStream fileOutputStream2 = null;
        try {
            try {
                fileOutputStream = new FileOutputStream(file, false);
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    closeQuietly(fileOutputStream);
                    StrictMode.setThreadPolicy(allowThreadDiskWrites);
                    return true;
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (IOException e2) {
            e = e2;
            fileOutputStream2 = fileOutputStream;
            Log.e("TypefaceCompatUtil", "Error copying resource contents to temp file: " + e.getMessage());
            closeQuietly(fileOutputStream2);
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
            return false;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream2 = fileOutputStream;
            closeQuietly(fileOutputStream2);
            StrictMode.setThreadPolicy(allowThreadDiskWrites);
            throw th;
        }
    }

    public static final Object coroutineScope(Function2 function2, Continuation continuation) {
        ScopeCoroutine scopeCoroutine = new ScopeCoroutine(continuation, continuation.getContext());
        return AtomicKt.startUndispatchedOrReturn(scopeCoroutine, scopeCoroutine, function2);
    }

    public static final int countOneBits(int i) {
        switch (i) {
            case 0:
                return 0;
            case 1:
            case 2:
            case 4:
                return 1;
            case 3:
            case 5:
            case 6:
                return 2;
            default:
                return 3;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: kotlin.jvm.functions.Function2 */
    /* JADX WARN: Multi-variable type inference failed */
    public static Continuation createCoroutineUnintercepted(final Object obj, final Continuation continuation, final Function2 function2) {
        checkNotNullParameter(function2, "<this>");
        checkNotNullParameter(continuation, "completion");
        if (function2 instanceof BaseContinuationImpl) {
            return ((BaseContinuationImpl) function2).create(obj, continuation);
        }
        final CoroutineContext context = continuation.getContext();
        return context == EmptyCoroutineContext.INSTANCE ? new RestrictedContinuationImpl(obj, continuation, function2) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$3
            public final /* synthetic */ Object $receiver$inlined;
            public final /* synthetic */ Function2 $this_createCoroutineUnintercepted$inlined;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(continuation);
                this.$this_createCoroutineUnintercepted$inlined = function2;
                ResultKt.checkNotNull(continuation, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj2) {
                int i = this.label;
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("This coroutine had already completed".toString());
                    }
                    this.label = 2;
                    ResultKt.throwOnFailure(obj2);
                    return obj2;
                }
                this.label = 1;
                ResultKt.throwOnFailure(obj2);
                Function2 function22 = this.$this_createCoroutineUnintercepted$inlined;
                ResultKt.checkNotNull(function22, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1>, kotlin.Any?>");
                ResultKt.beforeCheckcastToFunctionOfArity(2, function22);
                return function22.invoke(this.$receiver$inlined, this);
            }
        } : new ContinuationImpl(continuation, context, function2, obj) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$4
            public final /* synthetic */ Object $receiver$inlined;
            public final /* synthetic */ Function2 $this_createCoroutineUnintercepted$inlined;
            public int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(continuation, context);
                this.$this_createCoroutineUnintercepted$inlined = function2;
                this.$receiver$inlined = obj;
                ResultKt.checkNotNull(continuation, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj2) {
                int i = this.label;
                if (i != 0) {
                    if (i != 1) {
                        throw new IllegalStateException("This coroutine had already completed".toString());
                    }
                    this.label = 2;
                    ResultKt.throwOnFailure(obj2);
                    return obj2;
                }
                this.label = 1;
                ResultKt.throwOnFailure(obj2);
                Function2 function22 = this.$this_createCoroutineUnintercepted$inlined;
                ResultKt.checkNotNull(function22, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1>, kotlin.Any?>");
                ResultKt.beforeCheckcastToFunctionOfArity(2, function22);
                return function22.invoke(this.$receiver$inlined, this);
            }
        };
    }

    public static final Result.Failure createFailure(Throwable th) {
        checkNotNullParameter(th, "exception");
        return new Result.Failure(th);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, androidx.compose.ui.draw.DrawResult] */
    public static final FontFamilyResolverImpl createFontFamilyResolver(Context context) {
        ?? obj = new Object();
        context.getApplicationContext();
        return new FontFamilyResolverImpl(obj, new AndroidFontResolveInterceptor(Build.VERSION.SDK_INT >= 31 ? context.getResources().getConfiguration().fontWeightAdjustment : 0));
    }

    public static final Object delay(long j, Continuation continuation) {
        Unit unit = Unit.INSTANCE;
        if (j <= 0) {
            return unit;
        }
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, intercepted(continuation));
        cancellableContinuationImpl.initCancellability();
        if (j < Long.MAX_VALUE) {
            getDelay(cancellableContinuationImpl.context).scheduleResumeAfterDelay(j, cancellableContinuationImpl);
        }
        Object result = cancellableContinuationImpl.getResult();
        return result == CoroutineSingletons.COROUTINE_SUSPENDED ? result : unit;
    }

    public static boolean deleteFilesRecursively(File file) {
        if (!file.isDirectory()) {
            file.delete();
            return true;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return false;
        }
        boolean z = true;
        for (File file2 : listFiles) {
            z = deleteFilesRecursively(file2) && z;
        }
        return z;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: androidx.compose.ui.platform.WeakCache */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public static final MutableVector derivedStateObservers() {
        WeakCache weakCache = SnapshotStateKt__DerivedStateKt.derivedStateObservers;
        MutableVector mutableVector = (MutableVector) weakCache.get();
        if (mutableVector != null) {
            return mutableVector;
        }
        ?? obj = new Object();
        obj.content = new DerivedStateObserver[0];
        obj.size = 0;
        weakCache.set(obj);
        return obj;
    }

    public static final void doAnimationFrameWithScale(AnimationScope animationScope, long j, float f, Animation animation, AnimationState animationState, Function1 function1) {
        AnimationVector velocityFromNanos;
        long j2 = f == 0.0f ? ((TargetBasedAnimation) animation).durationNanos : ((float) (j - animationScope.startTimeNanos)) / f;
        animationScope.lastFrameTimeNanos = j;
        TargetBasedAnimation targetBasedAnimation = (TargetBasedAnimation) animation;
        animationScope.value$delegate.setValue(targetBasedAnimation.getValueFromNanos(j2));
        long j3 = targetBasedAnimation.durationNanos;
        if (j2 >= j3) {
            velocityFromNanos = targetBasedAnimation.endVelocity;
        } else {
            velocityFromNanos = targetBasedAnimation.animationSpec.getVelocityFromNanos(j2, targetBasedAnimation.initialValueVector, targetBasedAnimation.targetValueVector, targetBasedAnimation.initialVelocityVector);
        }
        checkNotNullParameter(velocityFromNanos, "<set-?>");
        animationScope.velocityVector = velocityFromNanos;
        if (j2 >= j3) {
            animationScope.finishedTimeNanos = animationScope.lastFrameTimeNanos;
            animationScope.isRunning$delegate.setValue(Boolean.FALSE);
        }
        updateState(animationScope, animationState);
        function1.invoke(animationScope);
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m296equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.lang.Object, kotlin.jvm.internal.Ref$ObjectRef] */
    public static final CoroutineContext foldCopies(CoroutineContext coroutineContext, CoroutineContext coroutineContext2, boolean z) {
        Boolean bool = Boolean.FALSE;
        CoroutineContextKt$foldCopies$1 coroutineContextKt$foldCopies$1 = CoroutineContextKt$foldCopies$1.INSTANCE$1;
        boolean booleanValue = ((Boolean) coroutineContext.fold(bool, coroutineContextKt$foldCopies$1)).booleanValue();
        boolean booleanValue2 = ((Boolean) coroutineContext2.fold(bool, coroutineContextKt$foldCopies$1)).booleanValue();
        if (!booleanValue && !booleanValue2) {
            return coroutineContext.plus(coroutineContext2);
        }
        ?? obj = new Object();
        obj.element = coroutineContext2;
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        CoroutineContext coroutineContext3 = (CoroutineContext) coroutineContext.fold(emptyCoroutineContext, new StringsKt__StringsKt$rangesDelimitedBy$2(1, obj, z));
        if (booleanValue2) {
            obj.element = ((CoroutineContext) obj.element).fold(emptyCoroutineContext, CoroutineContextKt$foldCopies$1.INSTANCE);
        }
        return coroutineContext3.plus((CoroutineContext) obj.element);
    }

    public static final LifecycleOwner get(View view) {
        checkNotNullParameter(view, "<this>");
        return (LifecycleOwner) SequencesKt.firstOrNull(SequencesKt.mapNotNull(MathKt.generateSequence(view, ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1.INSTANCE), ViewTreeLifecycleOwner$findViewTreeLifecycleOwner$1.INSTANCE$1));
    }

    public static final Delay getDelay(CoroutineContext coroutineContext) {
        CoroutineContext.Element element = coroutineContext.get(ContinuationInterceptor.Key.$$INSTANCE);
        Delay delay = element instanceof Delay ? (Delay) element : null;
        return delay == null ? DefaultExecutorKt.DefaultDelay : delay;
    }

    public static final float getDurationScale(CoroutineContext coroutineContext) {
        MotionDurationScale motionDurationScale = (MotionDurationScale) coroutineContext.get(Alignment.Companion.$$INSTANCE);
        float scaleFactor = motionDurationScale != null ? motionDurationScale.getScaleFactor() : 1.0f;
        if (scaleFactor >= 0.0f) {
            return scaleFactor;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:25:0x005a */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:30:0x000b */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:31:0x000b */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:34:0x0060 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:85:0x00ed */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:90:0x00a3 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:91:0x00a3 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:94:0x00f3 */
    /* JADX DEBUG: Multi-variable search result rejected for r10v0, resolved type: androidx.compose.ui.focus.FocusEventModifierNode */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x0081, code lost:
    
        continue;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v6, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v15, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v21, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final androidx.compose.ui.focus.FocusStateImpl getFocusState(androidx.compose.ui.focus.FocusEventModifierNode r10) {
        /*
            java.lang.String r0 = "<this>"
            checkNotNullParameter(r10, r0)
            androidx.compose.ui.Modifier$Node r10 = (androidx.compose.ui.Modifier.Node) r10
            androidx.compose.ui.Modifier$Node r0 = r10.node
            r1 = 0
            r2 = r1
        Lb:
            r3 = 1
            r4 = 0
            r5 = 2
            r6 = 16
            if (r0 == 0) goto L65
            boolean r7 = r0 instanceof androidx.compose.ui.focus.FocusTargetNode
            if (r7 == 0) goto L26
            androidx.compose.ui.focus.FocusTargetNode r0 = (androidx.compose.ui.focus.FocusTargetNode) r0
            androidx.compose.ui.focus.FocusStateImpl r0 = r0.focusState
            int r4 = r0.ordinal()
            if (r4 == 0) goto L25
            if (r4 == r3) goto L25
            if (r4 == r5) goto L25
            goto L60
        L25:
            return r0
        L26:
            int r5 = r0.kindSet
            r5 = r5 & 1024(0x400, float:1.435E-42)
            if (r5 == 0) goto L60
            boolean r5 = r0 instanceof androidx.compose.ui.node.DelegatingNode
            if (r5 == 0) goto L60
            r5 = r0
            androidx.compose.ui.node.DelegatingNode r5 = (androidx.compose.ui.node.DelegatingNode) r5
            androidx.compose.ui.Modifier$Node r5 = r5.delegate
            r7 = r4
        L36:
            if (r5 == 0) goto L5d
            int r8 = r5.kindSet
            r8 = r8 & 1024(0x400, float:1.435E-42)
            if (r8 == 0) goto L5a
            int r7 = r7 + 1
            if (r7 != r3) goto L44
            r0 = r5
            goto L5a
        L44:
            if (r2 != 0) goto L51
            androidx.compose.runtime.collection.MutableVector r2 = new androidx.compose.runtime.collection.MutableVector
            androidx.compose.ui.Modifier$Node[] r8 = new androidx.compose.ui.Modifier.Node[r6]
            r2.<init>()
            r2.content = r8
            r2.size = r4
        L51:
            if (r0 == 0) goto L57
            r2.add(r0)
            r0 = r1
        L57:
            r2.add(r5)
        L5a:
            androidx.compose.ui.Modifier$Node r5 = r5.child
            goto L36
        L5d:
            if (r7 != r3) goto L60
            goto Lb
        L60:
            androidx.compose.ui.Modifier$Node r0 = androidx.compose.ui.node.Snake.access$pop(r2)
            goto Lb
        L65:
            androidx.compose.ui.Modifier$Node r10 = r10.node
            boolean r0 = r10.isAttached
            if (r0 == 0) goto Lfe
            androidx.compose.runtime.collection.MutableVector r0 = new androidx.compose.runtime.collection.MutableVector
            androidx.compose.ui.Modifier$Node[] r2 = new androidx.compose.ui.Modifier.Node[r6]
            r0.<init>()
            r0.content = r2
            r0.size = r4
            androidx.compose.ui.Modifier$Node r2 = r10.child
            if (r2 != 0) goto L7e
            androidx.compose.ui.node.Snake.access$addLayoutNodeChildren(r0, r10)
            goto L81
        L7e:
            r0.add(r2)
        L81:
            boolean r10 = r0.isNotEmpty()
            if (r10 == 0) goto Lfb
            int r10 = r0.size
            int r10 = r10 - r3
            java.lang.Object r10 = r0.removeAt(r10)
            androidx.compose.ui.Modifier$Node r10 = (androidx.compose.ui.Modifier.Node) r10
            int r2 = r10.aggregateChildKindSet
            r2 = r2 & 1024(0x400, float:1.435E-42)
            if (r2 != 0) goto L9a
            androidx.compose.ui.node.Snake.access$addLayoutNodeChildren(r0, r10)
            goto L81
        L9a:
            if (r10 == 0) goto L81
            int r2 = r10.kindSet
            r2 = r2 & 1024(0x400, float:1.435E-42)
            if (r2 == 0) goto Lf8
            r2 = r1
        La3:
            if (r10 == 0) goto L81
            boolean r7 = r10 instanceof androidx.compose.ui.focus.FocusTargetNode
            if (r7 == 0) goto Lb9
            androidx.compose.ui.focus.FocusTargetNode r10 = (androidx.compose.ui.focus.FocusTargetNode) r10
            androidx.compose.ui.focus.FocusStateImpl r10 = r10.focusState
            int r7 = r10.ordinal()
            if (r7 == 0) goto Lb8
            if (r7 == r3) goto Lb8
            if (r7 == r5) goto Lb8
            goto Lf3
        Lb8:
            return r10
        Lb9:
            int r7 = r10.kindSet
            r7 = r7 & 1024(0x400, float:1.435E-42)
            if (r7 == 0) goto Lf3
            boolean r7 = r10 instanceof androidx.compose.ui.node.DelegatingNode
            if (r7 == 0) goto Lf3
            r7 = r10
            androidx.compose.ui.node.DelegatingNode r7 = (androidx.compose.ui.node.DelegatingNode) r7
            androidx.compose.ui.Modifier$Node r7 = r7.delegate
            r8 = r4
        Lc9:
            if (r7 == 0) goto Lf0
            int r9 = r7.kindSet
            r9 = r9 & 1024(0x400, float:1.435E-42)
            if (r9 == 0) goto Led
            int r8 = r8 + 1
            if (r8 != r3) goto Ld7
            r10 = r7
            goto Led
        Ld7:
            if (r2 != 0) goto Le4
            androidx.compose.runtime.collection.MutableVector r2 = new androidx.compose.runtime.collection.MutableVector
            androidx.compose.ui.Modifier$Node[] r9 = new androidx.compose.ui.Modifier.Node[r6]
            r2.<init>()
            r2.content = r9
            r2.size = r4
        Le4:
            if (r10 == 0) goto Lea
            r2.add(r10)
            r10 = r1
        Lea:
            r2.add(r7)
        Led:
            androidx.compose.ui.Modifier$Node r7 = r7.child
            goto Lc9
        Lf0:
            if (r8 != r3) goto Lf3
            goto La3
        Lf3:
            androidx.compose.ui.Modifier$Node r10 = androidx.compose.ui.node.Snake.access$pop(r2)
            goto La3
        Lf8:
            androidx.compose.ui.Modifier$Node r10 = r10.child
            goto L9a
        Lfb:
            androidx.compose.ui.focus.FocusStateImpl r10 = androidx.compose.ui.focus.FocusStateImpl.Inactive
            return r10
        Lfe:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "visitChildren called on an unattached node"
            java.lang.String r0 = r0.toString()
            r10.<init>(r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ResultKt.getFocusState(androidx.compose.ui.focus.FocusEventModifierNode):androidx.compose.ui.focus.FocusStateImpl");
    }

    public static final Class getJavaObjectType(KClass kClass) {
        checkNotNullParameter(kClass, "<this>");
        Class jClass = ((ClassBasedDeclarationContainer) kClass).getJClass();
        if (!jClass.isPrimitive()) {
            return jClass;
        }
        String name = jClass.getName();
        switch (name.hashCode()) {
            case -1325958191:
                return !name.equals("double") ? jClass : Double.class;
            case 104431:
                return !name.equals("int") ? jClass : Integer.class;
            case 3039496:
                return !name.equals("byte") ? jClass : Byte.class;
            case 3052374:
                return !name.equals("char") ? jClass : Character.class;
            case 3327612:
                return !name.equals("long") ? jClass : Long.class;
            case 3625364:
                return !name.equals("void") ? jClass : Void.class;
            case 64711720:
                return !name.equals("boolean") ? jClass : Boolean.class;
            case 97526364:
                return !name.equals("float") ? jClass : Float.class;
            case 109413500:
                return !name.equals("short") ? jClass : Short.class;
            default:
                return jClass;
        }
    }

    public static int getLastIndex(List list) {
        checkNotNullParameter(list, "<this>");
        return list.size() - 1;
    }

    public static final MonotonicFrameClock getMonotonicFrameClock(CoroutineContext coroutineContext) {
        checkNotNullParameter(coroutineContext, "<this>");
        MonotonicFrameClock monotonicFrameClock = (MonotonicFrameClock) coroutineContext.get(Composer.Companion.$$INSTANCE);
        if (monotonicFrameClock != null) {
            return monotonicFrameClock;
        }
        throw new IllegalStateException("A MonotonicFrameClock is not available in this CoroutineContext. Callers should supply an appropriate MonotonicFrameClock using withContext.".toString());
    }

    public static final CancellableContinuationImpl getOrCreateCancellableContinuation(Continuation continuation) {
        CancellableContinuationImpl cancellableContinuationImpl;
        CancellableContinuationImpl cancellableContinuationImpl2;
        if (!(continuation instanceof DispatchedContinuation)) {
            return new CancellableContinuationImpl(1, continuation);
        }
        DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
        loop0: while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = DispatchedContinuation._reusableCancellableContinuation$FU;
            Object obj = atomicReferenceFieldUpdater.get(dispatchedContinuation);
            Symbol symbol = AtomicKt.REUSABLE_CLAIMED;
            cancellableContinuationImpl = null;
            if (obj == null) {
                atomicReferenceFieldUpdater.set(dispatchedContinuation, symbol);
                cancellableContinuationImpl2 = null;
                break;
            }
            if (obj instanceof CancellableContinuationImpl) {
                while (!atomicReferenceFieldUpdater.compareAndSet(dispatchedContinuation, obj, symbol)) {
                    if (atomicReferenceFieldUpdater.get(dispatchedContinuation) != obj) {
                        break;
                    }
                }
                cancellableContinuationImpl2 = (CancellableContinuationImpl) obj;
                break loop0;
            }
            if (obj != symbol && !(obj instanceof Throwable)) {
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        }
        if (cancellableContinuationImpl2 != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = CancellableContinuationImpl._state$FU;
            Object obj2 = atomicReferenceFieldUpdater2.get(cancellableContinuationImpl2);
            if (!(obj2 instanceof CompletedContinuation) || ((CompletedContinuation) obj2).idempotentResume == null) {
                CancellableContinuationImpl._decisionAndIndex$FU.set(cancellableContinuationImpl2, 536870911);
                atomicReferenceFieldUpdater2.set(cancellableContinuationImpl2, Active.INSTANCE);
                cancellableContinuationImpl = cancellableContinuationImpl2;
            } else {
                cancellableContinuationImpl2.detachChild$kotlinx_coroutines_core();
            }
            if (cancellableContinuationImpl != null) {
                return cancellableContinuationImpl;
            }
        }
        return new CancellableContinuationImpl(2, continuation);
    }

    public static final long getSp(double d) {
        return pack((float) d, 4294967296L);
    }

    public static File getTempFile(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            return null;
        }
        String str = ".font" + Process.myPid() + "-" + Process.myTid() + "-";
        for (int i = 0; i < 100; i++) {
            File file = new File(cacheDir, str + i);
            if (file.createNewFile()) {
                return file;
            }
        }
        return null;
    }

    public static final void handleCoroutineException(CoroutineContext coroutineContext, Throwable th) {
        try {
            CoroutineExceptionHandler coroutineExceptionHandler = (CoroutineExceptionHandler) coroutineContext.get(Job.Key.$$INSTANCE$1);
            if (coroutineExceptionHandler != null) {
                coroutineExceptionHandler.handleException(coroutineContext, th);
            } else {
                JobKt.handleUncaughtCoroutineException(coroutineContext, th);
            }
        } catch (Throwable th2) {
            if (th != th2) {
                RuntimeException runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                addSuppressed(runtimeException, th);
                th = runtimeException;
            }
            JobKt.handleUncaughtCoroutineException(coroutineContext, th);
        }
    }

    public static final int indexSegment(int i, int i2) {
        return (i >> i2) & 31;
    }

    public static final int indexSegment$1(int i, int i2) {
        return (i >> i2) & 31;
    }

    public static Continuation intercepted(Continuation continuation) {
        checkNotNullParameter(continuation, "<this>");
        ContinuationImpl continuationImpl = continuation instanceof ContinuationImpl ? (ContinuationImpl) continuation : null;
        if (continuationImpl == null) {
            return continuation;
        }
        Continuation continuation2 = continuationImpl.intercepted;
        if (continuation2 != null) {
            return continuation2;
        }
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) continuationImpl.getContext().get(ContinuationInterceptor.Key.$$INSTANCE);
        Continuation dispatchedContinuation = continuationInterceptor != null ? new DispatchedContinuation((CoroutineDispatcher) continuationInterceptor, continuationImpl) : continuationImpl;
        continuationImpl.intercepted = dispatchedContinuation;
        return dispatchedContinuation;
    }

    public static final void invalidateFocusEvent(FocusEventModifierNode focusEventModifierNode) {
        checkNotNullParameter(focusEventModifierNode, "<this>");
        FocusOwnerImpl focusOwnerImpl = (FocusOwnerImpl) ((AndroidComposeView) Snake.requireOwner(focusEventModifierNode)).getFocusOwner();
        focusOwnerImpl.getClass();
        FocusInvalidationManager focusInvalidationManager = focusOwnerImpl.focusInvalidationManager;
        focusInvalidationManager.getClass();
        focusInvalidationManager.scheduleInvalidation(focusInvalidationManager.focusEventNodes, focusEventModifierNode);
    }

    public static /* synthetic */ DisposableHandle invokeOnCompletion$default(Job job, boolean z, JobNode jobNode, int i) {
        if ((i & 1) != 0) {
            z = false;
        }
        return ((JobSupport) job).invokeOnCompletion(z, (i & 2) != 0, jobNode);
    }

    public static final boolean isCancellableMode(int i) {
        return i == 1 || i == 2;
    }

    public static boolean isEnabled() {
        boolean isEnabled;
        try {
            if (sIsTagEnabledMethod == null) {
                isEnabled = Trace.isEnabled();
                return isEnabled;
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        try {
            if (sIsTagEnabledMethod == null) {
                sTraceTagApp = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                sIsTagEnabledMethod = Trace.class.getMethod("isTagEnabled", Long.TYPE);
            }
            return ((Boolean) sIsTagEnabledMethod.invoke(null, Long.valueOf(sTraceTagApp))).booleanValue();
        } catch (Exception e) {
            if (!(e instanceof InvocationTargetException)) {
                Log.v("Trace", "Unable to call isTagEnabled via reflection", e);
                return false;
            }
            Throwable cause = e.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new RuntimeException(cause);
        }
    }

    /* renamed from: isUnspecified--R2X_6o, reason: not valid java name */
    public static final boolean m297isUnspecifiedR2X_6o(long j) {
        TextUnitType[] textUnitTypeArr = TextUnit.TextUnitTypes;
        return (j & 1095216660480L) == 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v4, types: [kotlinx.coroutines.AbstractCoroutine, kotlinx.coroutines.StandaloneCoroutine] */
    /* JADX WARN: Type inference failed for: r3v9 */
    public static StandaloneCoroutine launch$default(CoroutineScope coroutineScope, HandlerContext handlerContext, int i, Function2 function2, int i2) {
        CoroutineContext coroutineContext = handlerContext;
        if ((i2 & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        if ((i2 & 2) != 0) {
            i = 1;
        }
        CoroutineContext foldCopies = foldCopies(coroutineScope.getCoroutineContext(), coroutineContext, true);
        DefaultScheduler defaultScheduler = Dispatchers.Default;
        if (foldCopies != defaultScheduler && foldCopies.get(ContinuationInterceptor.Key.$$INSTANCE) == null) {
            foldCopies = foldCopies.plus(defaultScheduler);
        }
        if (i == 0) {
            throw null;
        }
        ?? lazyStandaloneCoroutine = i == 2 ? new LazyStandaloneCoroutine(foldCopies, function2) : new AbstractCoroutine(foldCopies, true);
        lazyStandaloneCoroutine.start(i, lazyStandaloneCoroutine, function2);
        return lazyStandaloneCoroutine;
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, kotlin.UnsafeLazyImpl, kotlin.Lazy] */
    public static Lazy lazy(Function0 function0) {
        UNINITIALIZED_VALUE uninitialized_value = UNINITIALIZED_VALUE.INSTANCE;
        ?? obj = new Object();
        obj.initializer = function0;
        obj._value = uninitialized_value;
        return obj;
    }

    public static List listOf(Object obj) {
        List singletonList = Collections.singletonList(obj);
        checkNotNullExpressionValue(singletonList, "singletonList(element)");
        return singletonList;
    }

    public static int mapCapacity(int i) {
        if (i < 0) {
            return i;
        }
        if (i < 3) {
            return i + 1;
        }
        if (i < 1073741824) {
            return (int) ((i / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static CoroutineContext minusKey(CoroutineContext.Element element, CoroutineContext.Key key) {
        checkNotNullParameter(key, "key");
        return areEqual(element.getKey(), key) ? EmptyCoroutineContext.INSTANCE : element;
    }

    /* JADX DEBUG: Another duplicated slice has different insns count: {[]}, finally: {[INVOKE, MOVE_EXCEPTION, INVOKE, MOVE_EXCEPTION] complete} */
    public static MappedByteBuffer mmap(Context context, Uri uri) {
        try {
            ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r", null);
            if (openFileDescriptor == null) {
                if (openFileDescriptor != null) {
                    openFileDescriptor.close();
                }
                return null;
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(openFileDescriptor.getFileDescriptor());
                try {
                    FileChannel channel = fileInputStream.getChannel();
                    MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                    fileInputStream.close();
                    openFileDescriptor.close();
                    return map;
                } finally {
                }
            } catch (Throwable th) {
                try {
                    openFileDescriptor.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException unused) {
            return null;
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.runtime.SnapshotMutableStateImpl, androidx.compose.runtime.ParcelableSnapshotMutableState] */
    public static final ParcelableSnapshotMutableState mutableStateOf(Object obj, SnapshotMutationPolicy snapshotMutationPolicy) {
        checkNotNullParameter(snapshotMutationPolicy, "policy");
        return new SnapshotMutableStateImpl(obj, snapshotMutationPolicy);
    }

    public static final AnimationVector newInstance(AnimationVector animationVector) {
        checkNotNullParameter(animationVector, "<this>");
        return animationVector.newVector$animation_core_release();
    }

    public static final long pack(float f, long j) {
        long floatToIntBits = j | (Float.floatToIntBits(f) & 4294967295L);
        TextUnitType[] textUnitTypeArr = TextUnit.TextUnitTypes;
        return floatToIntBits;
    }

    public static CoroutineContext plus(CoroutineContext.Element element, CoroutineContext coroutineContext) {
        checkNotNullParameter(coroutineContext, "context");
        return plus((CoroutineContext) element, coroutineContext);
    }

    public static byte[] read(InputStream inputStream, int i) {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = inputStream.read(bArr, i2, i - i2);
            if (read < 0) {
                throw new IllegalStateException("Not enough bytes to read: " + i);
            }
            i2 += read;
        }
        return bArr;
    }

    public static List readCerts(Resources resources, int i) {
        if (i == 0) {
            return Collections.emptyList();
        }
        TypedArray obtainTypedArray = resources.obtainTypedArray(i);
        try {
            if (obtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (FontResourcesParserCompat$Api21Impl.getType(obtainTypedArray, 0) == 1) {
                for (int i2 = 0; i2 < obtainTypedArray.length(); i2++) {
                    int resourceId = obtainTypedArray.getResourceId(i2, 0);
                    if (resourceId != 0) {
                        String[] stringArray = resources.getStringArray(resourceId);
                        ArrayList arrayList2 = new ArrayList();
                        for (String str : stringArray) {
                            arrayList2.add(Base64.decode(str, 0));
                        }
                        arrayList.add(arrayList2);
                    }
                }
            } else {
                String[] stringArray2 = resources.getStringArray(i);
                ArrayList arrayList3 = new ArrayList();
                for (String str2 : stringArray2) {
                    arrayList3.add(Base64.decode(str2, 0));
                }
                arrayList.add(arrayList3);
            }
            return arrayList;
        } finally {
            obtainTypedArray.recycle();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x005d, code lost:
    
        if (r0.finished() == false) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0062, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x006a, code lost:
    
        throw new java.lang.IllegalStateException("Inflater did not finish");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] readCompressed(java.io.FileInputStream r8, int r9, int r10) {
        /*
            java.util.zip.Inflater r0 = new java.util.zip.Inflater
            r0.<init>()
            byte[] r1 = new byte[r10]     // Catch: java.lang.Throwable -> L2e
            r2 = 2048(0x800, float:2.87E-42)
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> L2e
            r3 = 0
            r4 = r3
            r5 = r4
        Le:
            boolean r6 = r0.finished()     // Catch: java.lang.Throwable -> L2e
            if (r6 != 0) goto L57
            boolean r6 = r0.needsDictionary()     // Catch: java.lang.Throwable -> L2e
            if (r6 != 0) goto L57
            if (r4 >= r9) goto L57
            int r6 = r8.read(r2)     // Catch: java.lang.Throwable -> L2e
            if (r6 < 0) goto L3b
            r0.setInput(r2, r3, r6)     // Catch: java.lang.Throwable -> L2e
            int r7 = r10 - r5
            int r7 = r0.inflate(r1, r5, r7)     // Catch: java.lang.Throwable -> L2e java.util.zip.DataFormatException -> L30
            int r5 = r5 + r7
            int r4 = r4 + r6
            goto Le
        L2e:
            r8 = move-exception
            goto L8a
        L30:
            r8 = move-exception
            java.lang.String r8 = r8.getMessage()     // Catch: java.lang.Throwable -> L2e
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L2e
            r9.<init>(r8)     // Catch: java.lang.Throwable -> L2e
            throw r9     // Catch: java.lang.Throwable -> L2e
        L3b:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2e
            r8.<init>()     // Catch: java.lang.Throwable -> L2e
            java.lang.String r10 = "Invalid zip data. Stream ended after $totalBytesRead bytes. Expected "
            r8.append(r10)     // Catch: java.lang.Throwable -> L2e
            r8.append(r9)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r9 = " bytes"
            r8.append(r9)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L2e
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L2e
            r9.<init>(r8)     // Catch: java.lang.Throwable -> L2e
            throw r9     // Catch: java.lang.Throwable -> L2e
        L57:
            if (r4 != r9) goto L6b
            boolean r8 = r0.finished()     // Catch: java.lang.Throwable -> L2e
            if (r8 == 0) goto L63
            r0.end()
            return r1
        L63:
            java.lang.String r8 = "Inflater did not finish"
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L2e
            r9.<init>(r8)     // Catch: java.lang.Throwable -> L2e
            throw r9     // Catch: java.lang.Throwable -> L2e
        L6b:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2e
            r8.<init>()     // Catch: java.lang.Throwable -> L2e
            java.lang.String r10 = "Didn't read enough bytes during decompression. expected="
            r8.append(r10)     // Catch: java.lang.Throwable -> L2e
            r8.append(r9)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r9 = " actual="
            r8.append(r9)     // Catch: java.lang.Throwable -> L2e
            r8.append(r4)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L2e
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L2e
            r9.<init>(r8)     // Catch: java.lang.Throwable -> L2e
            throw r9     // Catch: java.lang.Throwable -> L2e
        L8a:
            r0.end()
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.ResultKt.readCompressed(java.io.FileInputStream, int, int):byte[]");
    }

    public static long readUInt(InputStream inputStream, int i) {
        byte[] read = read(inputStream, i);
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            j += (read[i2] & 255) << (i2 * 8);
        }
        return j;
    }

    public static final Object recoverResult(Object obj) {
        return obj instanceof CompletedExceptionally ? createFailure(((CompletedExceptionally) obj).cause) : obj;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:38:0x0072 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:42:0x007b */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:47:0x0033 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:48:0x0033 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:51:0x0081 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r4v8, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    public static final void refreshFocusEventNodes(FocusTargetNode focusTargetNode) {
        NodeChain nodeChain;
        checkNotNullParameter(focusTargetNode, "<this>");
        Modifier.Node node = focusTargetNode.node;
        if (!node.isAttached) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        LayoutNode requireLayoutNode = Snake.requireLayoutNode(focusTargetNode);
        Modifier.Node node2 = node;
        while (requireLayoutNode != null) {
            if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 5120) != 0) {
                while (node2 != null) {
                    int i = node2.kindSet;
                    if ((i & 5120) != 0) {
                        if (node2 != node && (i & 1024) != 0) {
                            return;
                        }
                        if ((i & 4096) != 0) {
                            DelegatingNode delegatingNode = node2;
                            ?? r4 = 0;
                            while (delegatingNode != 0) {
                                if (delegatingNode instanceof FocusEventModifierNode) {
                                    FocusEventModifierNode focusEventModifierNode = (FocusEventModifierNode) delegatingNode;
                                    focusEventModifierNode.onFocusEvent(getFocusState(focusEventModifierNode));
                                } else if ((delegatingNode.kindSet & 4096) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                    Modifier.Node node3 = delegatingNode.delegate;
                                    int i2 = 0;
                                    delegatingNode = delegatingNode;
                                    r4 = r4;
                                    while (node3 != null) {
                                        if ((node3.kindSet & 4096) != 0) {
                                            i2++;
                                            r4 = r4;
                                            if (i2 == 1) {
                                                delegatingNode = node3;
                                            } else {
                                                if (r4 == 0) {
                                                    ?? obj = new Object();
                                                    obj.content = new Modifier.Node[16];
                                                    obj.size = 0;
                                                    r4 = obj;
                                                }
                                                if (delegatingNode != 0) {
                                                    r4.add(delegatingNode);
                                                    delegatingNode = 0;
                                                }
                                                r4.add(node3);
                                            }
                                        }
                                        node3 = node3.child;
                                        delegatingNode = delegatingNode;
                                        r4 = r4;
                                    }
                                    if (i2 == 1) {
                                    }
                                }
                                delegatingNode = Snake.access$pop(r4);
                            }
                        }
                    }
                    node2 = node2.parent;
                }
            }
            requireLayoutNode = requireLayoutNode.getParent$ui_release();
            node2 = (requireLayoutNode == null || (nodeChain = requireLayoutNode.nodes) == null) ? null : nodeChain.tail;
        }
    }

    public static final MutableState rememberUpdatedState(Object obj, Composer composer) {
        ComposerImpl composerImpl = (ComposerImpl) composer;
        composerImpl.startReplaceableGroup(-1058319986);
        composerImpl.startReplaceableGroup(-492369756);
        Object nextSlot = composerImpl.nextSlot();
        if (nextSlot == Composer.Companion.Empty) {
            nextSlot = mutableStateOf(obj, StructuralEqualityPolicy.INSTANCE);
            composerImpl.updateValue(nextSlot);
        }
        composerImpl.end(false);
        MutableState mutableState = (MutableState) nextSlot;
        mutableState.setValue(obj);
        composerImpl.end(false);
        return mutableState;
    }

    public static final boolean replacableWith(RecomposeScope recomposeScope, RecomposeScopeImpl recomposeScopeImpl) {
        Anchor anchor;
        if (recomposeScope != null) {
            if (recomposeScope instanceof RecomposeScopeImpl) {
                RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) recomposeScope;
                if (recomposeScopeImpl2.owner == null || (anchor = recomposeScopeImpl2.anchor) == null || !anchor.getValid() || areEqual(recomposeScope, recomposeScopeImpl) || areEqual(recomposeScopeImpl2.anchor, recomposeScopeImpl.anchor)) {
                }
            }
            return false;
        }
        return true;
    }

    public static final void resume(DispatchedTask dispatchedTask, Continuation continuation, boolean z) {
        Object takeState$kotlinx_coroutines_core = dispatchedTask.takeState$kotlinx_coroutines_core();
        Throwable exceptionalResult$kotlinx_coroutines_core = dispatchedTask.getExceptionalResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core);
        Object createFailure = exceptionalResult$kotlinx_coroutines_core != null ? createFailure(exceptionalResult$kotlinx_coroutines_core) : dispatchedTask.getSuccessfulResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core);
        if (!z) {
            continuation.resumeWith(createFailure);
            return;
        }
        checkNotNull(continuation, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTaskKt.resume>");
        DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
        Continuation continuation2 = dispatchedContinuation.continuation;
        CoroutineContext context = continuation2.getContext();
        Object updateThreadContext = AtomicKt.updateThreadContext(context, dispatchedContinuation.countOrElement);
        UndispatchedCoroutine updateUndispatchedCompletion = updateThreadContext != AtomicKt.NO_THREAD_ELEMENTS ? updateUndispatchedCompletion(continuation2, context, updateThreadContext) : null;
        try {
            continuation2.resumeWith(createFailure);
        } finally {
            if (updateUndispatchedCompletion == null || updateUndispatchedCompletion.clearThreadContext()) {
                AtomicKt.restoreThreadContext(context, updateThreadContext);
            }
        }
    }

    public static int roundToInt(float f) {
        if (Float.isNaN(f)) {
            throw new IllegalArgumentException("Cannot round NaN value.");
        }
        return Math.round(f);
    }

    public static final Object runBlocking(MainCoroutineDispatcher mainCoroutineDispatcher, Function2 function2) {
        EventLoopImplPlatform eventLoopImplPlatform;
        CoroutineContext foldCopies;
        Thread currentThread = Thread.currentThread();
        ContinuationInterceptor.Key key = ContinuationInterceptor.Key.$$INSTANCE;
        ContinuationInterceptor continuationInterceptor = (ContinuationInterceptor) mainCoroutineDispatcher.get(key);
        EmptyCoroutineContext emptyCoroutineContext = EmptyCoroutineContext.INSTANCE;
        if (continuationInterceptor == null) {
            eventLoopImplPlatform = ThreadLocalEventLoop.getEventLoop$kotlinx_coroutines_core();
            foldCopies = foldCopies(emptyCoroutineContext, plus((CoroutineContext.Element) mainCoroutineDispatcher, (CoroutineContext) eventLoopImplPlatform), true);
            DefaultScheduler defaultScheduler = Dispatchers.Default;
            if (foldCopies != defaultScheduler && foldCopies.get(key) == null) {
                foldCopies = foldCopies.plus(defaultScheduler);
            }
        } else {
            if (continuationInterceptor instanceof EventLoopImplPlatform) {
            }
            eventLoopImplPlatform = (EventLoopImplPlatform) ThreadLocalEventLoop.ref.get();
            foldCopies = foldCopies(emptyCoroutineContext, mainCoroutineDispatcher, true);
            DefaultScheduler defaultScheduler2 = Dispatchers.Default;
            if (foldCopies != defaultScheduler2 && foldCopies.get(key) == null) {
                foldCopies = foldCopies.plus(defaultScheduler2);
            }
        }
        BlockingCoroutine blockingCoroutine = new BlockingCoroutine(foldCopies, currentThread, eventLoopImplPlatform);
        blockingCoroutine.start(1, blockingCoroutine, function2);
        EventLoopImplPlatform eventLoopImplPlatform2 = blockingCoroutine.eventLoop;
        if (eventLoopImplPlatform2 != null) {
            int i = EventLoopImplPlatform.$r8$clinit;
            eventLoopImplPlatform2.incrementUseCount(false);
        }
        while (!Thread.interrupted()) {
            try {
                long processNextEvent = eventLoopImplPlatform2 != null ? eventLoopImplPlatform2.processNextEvent() : Long.MAX_VALUE;
                if (!(blockingCoroutine.getState$kotlinx_coroutines_core() instanceof Incomplete)) {
                    if (eventLoopImplPlatform2 != null) {
                        int i2 = EventLoopImplPlatform.$r8$clinit;
                        eventLoopImplPlatform2.decrementUseCount(false);
                    }
                    Object unboxState = JobKt.unboxState(blockingCoroutine.getState$kotlinx_coroutines_core());
                    CompletedExceptionally completedExceptionally = unboxState instanceof CompletedExceptionally ? (CompletedExceptionally) unboxState : null;
                    if (completedExceptionally == null) {
                        return unboxState;
                    }
                    throw completedExceptionally.cause;
                }
                LockSupport.parkNanos(blockingCoroutine, processNextEvent);
            } catch (Throwable th) {
                if (eventLoopImplPlatform2 != null) {
                    int i3 = EventLoopImplPlatform.$r8$clinit;
                    eventLoopImplPlatform2.decrementUseCount(false);
                }
                throw th;
            }
        }
        InterruptedException interruptedException = new InterruptedException();
        blockingCoroutine.cancelImpl$kotlinx_coroutines_core(interruptedException);
        throw interruptedException;
    }

    public static void sanitizeStackTrace(String str, RuntimeException runtimeException) {
        StackTraceElement[] stackTrace = runtimeException.getStackTrace();
        int length = stackTrace.length;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            if (str.equals(stackTrace[i2].getClassName())) {
                i = i2;
            }
        }
        runtimeException.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i + 1, length));
    }

    public static final int search(ArrayList arrayList, int i, int i2) {
        int size = arrayList.size() - 1;
        int i3 = 0;
        while (i3 <= size) {
            int i4 = (i3 + size) >>> 1;
            int i5 = ((Anchor) arrayList.get(i4)).location;
            if (i5 < 0) {
                i5 += i2;
            }
            int compare = compare(i5, i);
            if (compare < 0) {
                i3 = i4 + 1;
            } else {
                if (compare <= 0) {
                    return i4;
                }
                size = i4 - 1;
            }
        }
        return -(i3 + 1);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:35:0x0062 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:40:0x0023 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:41:0x0023 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:44:0x0068 */
    /* JADX WARN: Type inference failed for: r3v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* renamed from: searchBeyondBounds--OM-vw8, reason: not valid java name */
    public static final void m298searchBeyondBoundsOMvw8(FocusTargetNode focusTargetNode, OneDimensionalFocusSearchKt$generateAndSearchChildren$1 oneDimensionalFocusSearchKt$generateAndSearchChildren$1) {
        Modifier.Node node;
        NodeChain nodeChain;
        Modifier.Node node2 = focusTargetNode.node;
        if (!node2.isAttached) {
            throw new IllegalStateException("visitAncestors called on an unattached node".toString());
        }
        Modifier.Node node3 = node2.parent;
        LayoutNode requireLayoutNode = Snake.requireLayoutNode(focusTargetNode);
        loop0: while (true) {
            if (requireLayoutNode == null) {
                node = null;
                break;
            }
            if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 1024) != 0) {
                while (node3 != null) {
                    if ((node3.kindSet & 1024) != 0) {
                        node = node3;
                        MutableVector mutableVector = null;
                        while (node != null) {
                            if (node instanceof FocusTargetNode) {
                                break loop0;
                            }
                            if ((node.kindSet & 1024) != 0 && (node instanceof DelegatingNode)) {
                                Modifier.Node node4 = ((DelegatingNode) node).delegate;
                                int i = 0;
                                mutableVector = mutableVector;
                                while (node4 != null) {
                                    if ((node4.kindSet & 1024) != 0) {
                                        i++;
                                        mutableVector = mutableVector;
                                        if (i == 1) {
                                            node = node4;
                                        } else {
                                            if (mutableVector == null) {
                                                ?? obj = new Object();
                                                obj.content = new Modifier.Node[16];
                                                obj.size = 0;
                                                mutableVector = obj;
                                            }
                                            if (node != null) {
                                                mutableVector.add(node);
                                                node = null;
                                            }
                                            mutableVector.add(node4);
                                        }
                                    }
                                    node4 = node4.child;
                                    mutableVector = mutableVector;
                                }
                                if (i == 1) {
                                }
                            }
                            node = Snake.access$pop(mutableVector);
                        }
                    }
                    node3 = node3.parent;
                }
            }
            requireLayoutNode = requireLayoutNode.getParent$ui_release();
            node3 = (requireLayoutNode == null || (nodeChain = requireLayoutNode.nodes) == null) ? null : nodeChain.tail;
        }
        FocusTargetNode focusTargetNode2 = (FocusTargetNode) node;
        if (focusTargetNode2 != null) {
            ProvidableModifierLocal providableModifierLocal = BeyondBoundsLayoutKt.ModifierLocalBeyondBoundsLayout;
            DurationKt$$ExternalSyntheticCheckNotZero0.m(focusTargetNode2.getCurrent(providableModifierLocal));
            DurationKt$$ExternalSyntheticCheckNotZero0.m(focusTargetNode.getCurrent(providableModifierLocal));
            if (areEqual(null, null)) {
                return;
            }
        }
        DurationKt$$ExternalSyntheticCheckNotZero0.m(focusTargetNode.getCurrent(BeyondBoundsLayoutKt.ModifierLocalBeyondBoundsLayout));
    }

    /* renamed from: set-impl, reason: not valid java name */
    public static final void m299setimpl(Composer composer, Object obj, ComposeUiNode$Companion$SetDensity$1 composeUiNode$Companion$SetDensity$1) {
        checkNotNullParameter(composeUiNode$Companion$SetDensity$1, "block");
        ComposerImpl composerImpl = (ComposerImpl) composer;
        if (composerImpl.inserting || !areEqual(composerImpl.nextSlot(), obj)) {
            composerImpl.updateValue(obj);
            composerImpl.apply(obj, composeUiNode$Companion$SetDensity$1);
        }
    }

    public static void setSurroundingText(EditorInfo editorInfo, CharSequence charSequence, int i, int i2) {
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        editorInfo.extras.putCharSequence("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT", charSequence != null ? new SpannableStringBuilder(charSequence) : null);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_HEAD", i);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_END", i2);
    }

    public static IntProgression step(IntRange intRange) {
        checkNotNullParameter(intRange, "<this>");
        return new IntProgression(intRange.first, intRange.last, intRange.step > 0 ? 2 : -2);
    }

    public static final TestDataClass testDataClassOf() {
        return new TestDataClass("Greeting", "", "", "", "", new TestDataClass("sub", "", "", "", "", null));
    }

    public static void throwCce(Object obj, String str) {
        ClassCastException classCastException = new ClassCastException((obj == null ? "null" : obj.getClass().getName()) + " cannot be cast to " + str);
        sanitizeStackTrace(ResultKt.class.getName(), classCastException);
        throw classCastException;
    }

    public static final void throwOnFailure(Object obj) {
        if (obj instanceof Result.Failure) {
            throw ((Result.Failure) obj).exception;
        }
    }

    public static void throwUninitializedPropertyAccessException(String str) {
        RuntimeException runtimeException = new RuntimeException("lateinit property " + str + " has not been initialized");
        sanitizeStackTrace(ResultKt.class.getName(), runtimeException);
        throw runtimeException;
    }

    /* renamed from: toSize-ozmzZPI, reason: not valid java name */
    public static final long m300toSizeozmzZPI(long j) {
        return _BOUNDARY.Size((int) (j >> 32), (int) (j & 4294967295L));
    }

    public static TweenSpec tween$default(int i, Easing easing) {
        checkNotNullParameter(easing, "easing");
        return new TweenSpec(i, 0, easing);
    }

    public static final double ulongToDouble(long j) {
        return ((j >>> 11) * 2048) + (j & 2047);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.ranges.IntProgression, kotlin.ranges.IntRange] */
    public static IntRange until(int i, int i2) {
        if (i2 > Integer.MIN_VALUE) {
            return new IntProgression(i, i2 - 1, 1);
        }
        IntRange intRange = IntRange.EMPTY;
        return IntRange.EMPTY;
    }

    public static final int updateChangedFlags(int i) {
        int i2 = 306783378 & i;
        int i3 = 613566756 & i;
        return (i & (-920350135)) | (i3 >> 1) | i2 | ((i2 << 1) & i3);
    }

    public static final void updateState(AnimationScope animationScope, AnimationState animationState) {
        checkNotNullParameter(animationScope, "<this>");
        checkNotNullParameter(animationState, "state");
        animationState.value$delegate.setValue(animationScope.value$delegate.getValue());
        AnimationVector animationVector = animationState.velocityVector;
        AnimationVector animationVector2 = animationScope.velocityVector;
        checkNotNullParameter(animationVector, "<this>");
        checkNotNullParameter(animationVector2, "source");
        int size$animation_core_release = animationVector.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            animationVector.set$animation_core_release(animationVector2.get$animation_core_release(i), i);
        }
        animationState.finishedTimeNanos = animationScope.finishedTimeNanos;
        animationState.lastFrameTimeNanos = animationScope.lastFrameTimeNanos;
        animationState.isRunning = ((Boolean) animationScope.isRunning$delegate.getValue()).booleanValue();
    }

    public static final UndispatchedCoroutine updateUndispatchedCompletion(Continuation continuation, CoroutineContext coroutineContext, Object obj) {
        UndispatchedCoroutine undispatchedCoroutine = null;
        if (!(continuation instanceof CoroutineStackFrame)) {
            return null;
        }
        if (coroutineContext.get(UndispatchedMarker.INSTANCE) != null) {
            CoroutineStackFrame coroutineStackFrame = (CoroutineStackFrame) continuation;
            while (true) {
                if ((coroutineStackFrame instanceof DispatchedCoroutine) || (coroutineStackFrame = coroutineStackFrame.getCallerFrame()) == null) {
                    break;
                }
                if (coroutineStackFrame instanceof UndispatchedCoroutine) {
                    undispatchedCoroutine = (UndispatchedCoroutine) coroutineStackFrame;
                    break;
                }
            }
            if (undispatchedCoroutine != null) {
                undispatchedCoroutine.saveThreadContext(coroutineContext, obj);
            }
        }
        return undispatchedCoroutine;
    }

    public static final Object withContext(CoroutineContext.Element element, Function2 function2, Continuation continuation) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;
        CoroutineContext context = continuation.getContext();
        CoroutineContext plus = !((Boolean) element.fold(Boolean.FALSE, CoroutineContextKt$foldCopies$1.INSTANCE$1)).booleanValue() ? context.plus(element) : foldCopies(context, element, false);
        JobKt.ensureActive(plus);
        if (plus == context) {
            ScopeCoroutine scopeCoroutine = new ScopeCoroutine(continuation, plus);
            return AtomicKt.startUndispatchedOrReturn(scopeCoroutine, scopeCoroutine, function2);
        }
        ContinuationInterceptor.Key key = ContinuationInterceptor.Key.$$INSTANCE;
        if (areEqual(plus.get(key), context.get(key))) {
            UndispatchedCoroutine undispatchedCoroutine = new UndispatchedCoroutine(continuation, plus);
            CoroutineContext coroutineContext = undispatchedCoroutine.context;
            Object updateThreadContext = AtomicKt.updateThreadContext(coroutineContext, null);
            try {
                return AtomicKt.startUndispatchedOrReturn(undispatchedCoroutine, undispatchedCoroutine, function2);
            } finally {
                AtomicKt.restoreThreadContext(coroutineContext, updateThreadContext);
            }
        }
        ScopeCoroutine scopeCoroutine2 = new ScopeCoroutine(continuation, plus);
        AtomicKt.startCoroutineCancellable$default(function2, scopeCoroutine2, scopeCoroutine2);
        do {
            atomicIntegerFieldUpdater = DispatchedCoroutine._decision$FU;
            int i = atomicIntegerFieldUpdater.get(scopeCoroutine2);
            if (i != 0) {
                if (i != 2) {
                    throw new IllegalStateException("Already suspended".toString());
                }
                Object unboxState = JobKt.unboxState(scopeCoroutine2.getState$kotlinx_coroutines_core());
                if (unboxState instanceof CompletedExceptionally) {
                    throw ((CompletedExceptionally) unboxState).cause;
                }
                return unboxState;
            }
        } while (!atomicIntegerFieldUpdater.compareAndSet(scopeCoroutine2, 0, 1));
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    public static void writeUInt(ByteArrayOutputStream byteArrayOutputStream, long j, int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) ((j >> (i2 * 8)) & 255);
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void writeUInt16(ByteArrayOutputStream byteArrayOutputStream, int i) {
        writeUInt(byteArrayOutputStream, i, 2);
    }

    public abstract boolean contains$ui_release(ModifierLocal modifierLocal);

    public abstract Object get$ui_release(ProvidableModifierLocal providableModifierLocal);

    public abstract void onFailed(Throwable th);

    public abstract void onLoaded(MetadataRepo metadataRepo);

    public static List listOf(Object... objArr) {
        if (objArr.length > 0) {
            List asList = Arrays.asList(objArr);
            checkNotNullExpressionValue(asList, "asList(this)");
            return asList;
        }
        return EmptyList.INSTANCE;
    }

    public static float coerceIn(float f, float f2, float f3) {
        if (f2 <= f3) {
            return f < f2 ? f2 : f > f3 ? f3 : f;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + f3 + " is less than minimum " + f2 + '.');
    }

    public static final long getSp(int i) {
        return pack(i, 4294967296L);
    }

    public static CoroutineContext plus(CoroutineContext coroutineContext, CoroutineContext coroutineContext2) {
        checkNotNullParameter(coroutineContext2, "context");
        return coroutineContext2 == EmptyCoroutineContext.INSTANCE ? coroutineContext : (CoroutineContext) coroutineContext2.fold(coroutineContext, CoroutineContext$plus$1.INSTANCE);
    }

    public static void checkNotNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException(str);
        sanitizeStackTrace(ResultKt.class.getName(), nullPointerException);
        throw nullPointerException;
    }

    public static int coerceIn(int i, int i2, int i3) {
        if (i2 <= i3) {
            return i < i2 ? i2 : i > i3 ? i3 : i;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + i3 + " is less than minimum " + i2 + '.');
    }

    public static CoroutineContext.Element get(CoroutineContext.Element element, CoroutineContext.Key key) {
        checkNotNullParameter(key, "key");
        if (areEqual(element.getKey(), key)) {
            return element;
        }
        return null;
    }
}
