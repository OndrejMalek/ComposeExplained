package androidx.lifecycle;

import android.os.Looper;
import androidx.activity.ComponentActivity;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.FastSafeIterableMap;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.ResultKt;
import kotlinx.coroutines.flow.StateFlowImpl;

/* loaded from: classes.dex */
public final class LifecycleRegistry extends Lifecycle {
    public final StateFlowImpl _currentStateFlow;
    public int addingObserverCounter;
    public final boolean enforceMainThread;
    public boolean handlingEvent;
    public final WeakReference lifecycleOwner;
    public boolean newEventOccurred;
    public FastSafeIterableMap observerMap;
    public final ArrayList parentStates;
    public Lifecycle.State state;

    /* loaded from: classes.dex */
    public final class ObserverWithState {
        public LifecycleEventObserver lifecycleObserver;
        public Lifecycle.State state;

        public final void dispatchEvent(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            Lifecycle.State targetState = event.getTargetState();
            Lifecycle.State state = this.state;
            ResultKt.checkNotNullParameter(state, "state1");
            if (targetState.compareTo(state) < 0) {
                state = targetState;
            }
            this.state = state;
            this.lifecycleObserver.onStateChanged(lifecycleOwner, event);
            this.state = targetState;
        }
    }

    public LifecycleRegistry(LifecycleOwner lifecycleOwner) {
        ResultKt.checkNotNullParameter(lifecycleOwner, "provider");
        new AtomicReference();
        this.enforceMainThread = true;
        this.observerMap = new FastSafeIterableMap();
        Lifecycle.State state = Lifecycle.State.INITIALIZED;
        this.state = state;
        this.parentStates = new ArrayList();
        this.lifecycleOwner = new WeakReference(lifecycleOwner);
        this._currentStateFlow = new StateFlowImpl(state);
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, androidx.lifecycle.LifecycleRegistry$ObserverWithState] */
    public final void addObserver(LifecycleObserver lifecycleObserver) {
        LifecycleEventObserver defaultLifecycleObserverAdapter;
        LifecycleOwner lifecycleOwner;
        ResultKt.checkNotNullParameter(lifecycleObserver, "observer");
        enforceMainThreadIfNeeded("addObserver");
        Lifecycle.State state = this.state;
        Lifecycle.State state2 = Lifecycle.State.DESTROYED;
        if (state != state2) {
            state2 = Lifecycle.State.INITIALIZED;
        }
        ?? obj = new Object();
        HashMap hashMap = Lifecycling.callbackCache;
        boolean z = lifecycleObserver instanceof LifecycleEventObserver;
        boolean z2 = lifecycleObserver instanceof DefaultLifecycleObserver;
        Object obj2 = null;
        if (z && z2) {
            defaultLifecycleObserverAdapter = new DefaultLifecycleObserverAdapter((DefaultLifecycleObserver) lifecycleObserver, (LifecycleEventObserver) lifecycleObserver);
        } else if (z2) {
            defaultLifecycleObserverAdapter = new DefaultLifecycleObserverAdapter((DefaultLifecycleObserver) lifecycleObserver, (LifecycleEventObserver) null);
        } else if (z) {
            defaultLifecycleObserverAdapter = (LifecycleEventObserver) lifecycleObserver;
        } else {
            Class<?> cls = lifecycleObserver.getClass();
            if (Lifecycling.getObserverConstructorType(cls) == 2) {
                Object obj3 = Lifecycling.classToAdapters.get(cls);
                ResultKt.checkNotNull(obj3);
                List list = (List) obj3;
                if (list.size() == 1) {
                    Lifecycling.createGeneratedAdapter((Constructor) list.get(0), lifecycleObserver);
                    throw null;
                }
                int size = list.size();
                GeneratedAdapter[] generatedAdapterArr = new GeneratedAdapter[size];
                if (size > 0) {
                    Lifecycling.createGeneratedAdapter((Constructor) list.get(0), lifecycleObserver);
                    throw null;
                }
                defaultLifecycleObserverAdapter = new ComponentActivity.AnonymousClass4(generatedAdapterArr);
            } else {
                defaultLifecycleObserverAdapter = new DefaultLifecycleObserverAdapter(lifecycleObserver);
            }
        }
        obj.lifecycleObserver = defaultLifecycleObserverAdapter;
        obj.state = state2;
        FastSafeIterableMap fastSafeIterableMap = this.observerMap;
        SafeIterableMap.Entry entry = fastSafeIterableMap.get(lifecycleObserver);
        if (entry != null) {
            obj2 = entry.mValue;
        } else {
            HashMap hashMap2 = fastSafeIterableMap.mHashMap;
            SafeIterableMap.Entry entry2 = new SafeIterableMap.Entry(lifecycleObserver, obj);
            fastSafeIterableMap.mSize++;
            SafeIterableMap.Entry entry3 = fastSafeIterableMap.mEnd;
            if (entry3 == null) {
                fastSafeIterableMap.mStart = entry2;
                fastSafeIterableMap.mEnd = entry2;
            } else {
                entry3.mNext = entry2;
                entry2.mPrevious = entry3;
                fastSafeIterableMap.mEnd = entry2;
            }
            hashMap2.put(lifecycleObserver, entry2);
        }
        if (((ObserverWithState) obj2) == null && (lifecycleOwner = (LifecycleOwner) this.lifecycleOwner.get()) != null) {
            boolean z3 = this.addingObserverCounter != 0 || this.handlingEvent;
            Lifecycle.State calculateTargetState = calculateTargetState(lifecycleObserver);
            this.addingObserverCounter++;
            while (obj.state.compareTo(calculateTargetState) < 0 && this.observerMap.mHashMap.containsKey(lifecycleObserver)) {
                this.parentStates.add(obj.state);
                Lifecycle.Event.Companion companion = Lifecycle.Event.Companion;
                Lifecycle.State state3 = obj.state;
                companion.getClass();
                Lifecycle.Event upFrom = Lifecycle.Event.Companion.upFrom(state3);
                if (upFrom == null) {
                    throw new IllegalStateException("no event up from " + obj.state);
                }
                obj.dispatchEvent(lifecycleOwner, upFrom);
                ArrayList arrayList = this.parentStates;
                arrayList.remove(arrayList.size() - 1);
                calculateTargetState = calculateTargetState(lifecycleObserver);
            }
            if (!z3) {
                sync();
            }
            this.addingObserverCounter--;
        }
    }

    public final Lifecycle.State calculateTargetState(LifecycleObserver lifecycleObserver) {
        ObserverWithState observerWithState;
        HashMap hashMap = this.observerMap.mHashMap;
        SafeIterableMap.Entry entry = hashMap.containsKey(lifecycleObserver) ? ((SafeIterableMap.Entry) hashMap.get(lifecycleObserver)).mPrevious : null;
        Lifecycle.State state = (entry == null || (observerWithState = (ObserverWithState) entry.mValue) == null) ? null : observerWithState.state;
        ArrayList arrayList = this.parentStates;
        Lifecycle.State state2 = arrayList.isEmpty() ^ true ? (Lifecycle.State) arrayList.get(arrayList.size() - 1) : null;
        Lifecycle.State state3 = this.state;
        ResultKt.checkNotNullParameter(state3, "state1");
        if (state == null || state.compareTo(state3) >= 0) {
            state = state3;
        }
        return (state2 == null || state2.compareTo(state) >= 0) ? state : state2;
    }

    public final void enforceMainThreadIfNeeded(String str) {
        if (this.enforceMainThread) {
            ArchTaskExecutor.getInstance().mDelegate.getClass();
            if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                return;
            }
            throw new IllegalStateException(("Method " + str + " must be called on the main thread").toString());
        }
    }

    public final void handleLifecycleEvent(Lifecycle.Event event) {
        ResultKt.checkNotNullParameter(event, "event");
        enforceMainThreadIfNeeded("handleLifecycleEvent");
        moveToState(event.getTargetState());
    }

    public final void moveToState(Lifecycle.State state) {
        Lifecycle.State state2 = this.state;
        if (state2 == state) {
            return;
        }
        Lifecycle.State state3 = Lifecycle.State.INITIALIZED;
        Lifecycle.State state4 = Lifecycle.State.DESTROYED;
        if (state2 == state3 && state == state4) {
            throw new IllegalStateException(("no event down from " + this.state + " in component " + this.lifecycleOwner.get()).toString());
        }
        this.state = state;
        if (this.handlingEvent || this.addingObserverCounter != 0) {
            this.newEventOccurred = true;
            return;
        }
        this.handlingEvent = true;
        sync();
        this.handlingEvent = false;
        if (this.state == state4) {
            this.observerMap = new FastSafeIterableMap();
        }
    }

    @Override // androidx.lifecycle.Lifecycle
    public final void removeObserver(LifecycleObserver lifecycleObserver) {
        ResultKt.checkNotNullParameter(lifecycleObserver, "observer");
        enforceMainThreadIfNeeded("removeObserver");
        this.observerMap.remove(lifecycleObserver);
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0030, code lost:
    
        r8.newEventOccurred = false;
        r8._currentStateFlow.setValue(r8.state);
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0039, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void sync() {
        /*
            r8 = this;
            java.lang.ref.WeakReference r0 = r8.lifecycleOwner
            java.lang.Object r0 = r0.get()
            androidx.lifecycle.LifecycleOwner r0 = (androidx.lifecycle.LifecycleOwner) r0
            if (r0 == 0) goto L184
        La:
            androidx.arch.core.internal.FastSafeIterableMap r1 = r8.observerMap
            int r2 = r1.mSize
            r3 = 0
            if (r2 != 0) goto L12
            goto L30
        L12:
            androidx.arch.core.internal.SafeIterableMap$Entry r1 = r1.mStart
            kotlin.ResultKt.checkNotNull(r1)
            java.lang.Object r1 = r1.mValue
            androidx.lifecycle.LifecycleRegistry$ObserverWithState r1 = (androidx.lifecycle.LifecycleRegistry.ObserverWithState) r1
            androidx.lifecycle.Lifecycle$State r1 = r1.state
            androidx.arch.core.internal.FastSafeIterableMap r2 = r8.observerMap
            androidx.arch.core.internal.SafeIterableMap$Entry r2 = r2.mEnd
            kotlin.ResultKt.checkNotNull(r2)
            java.lang.Object r2 = r2.mValue
            androidx.lifecycle.LifecycleRegistry$ObserverWithState r2 = (androidx.lifecycle.LifecycleRegistry.ObserverWithState) r2
            androidx.lifecycle.Lifecycle$State r2 = r2.state
            if (r1 != r2) goto L3a
            androidx.lifecycle.Lifecycle$State r1 = r8.state
            if (r1 != r2) goto L3a
        L30:
            r8.newEventOccurred = r3
            androidx.lifecycle.Lifecycle$State r0 = r8.state
            kotlinx.coroutines.flow.StateFlowImpl r1 = r8._currentStateFlow
            r1.setValue(r0)
            return
        L3a:
            r8.newEventOccurred = r3
            androidx.lifecycle.Lifecycle$State r1 = r8.state
            androidx.arch.core.internal.FastSafeIterableMap r2 = r8.observerMap
            androidx.arch.core.internal.SafeIterableMap$Entry r2 = r2.mStart
            kotlin.ResultKt.checkNotNull(r2)
            java.lang.Object r2 = r2.mValue
            androidx.lifecycle.LifecycleRegistry$ObserverWithState r2 = (androidx.lifecycle.LifecycleRegistry.ObserverWithState) r2
            androidx.lifecycle.Lifecycle$State r2 = r2.state
            int r1 = r1.compareTo(r2)
            r2 = 1
            if (r1 >= 0) goto Lef
            androidx.arch.core.internal.FastSafeIterableMap r1 = r8.observerMap
            androidx.arch.core.internal.SafeIterableMap$AscendingIterator r3 = new androidx.arch.core.internal.SafeIterableMap$AscendingIterator
            androidx.arch.core.internal.SafeIterableMap$Entry r4 = r1.mEnd
            androidx.arch.core.internal.SafeIterableMap$Entry r5 = r1.mStart
            r3.<init>(r4, r5, r2)
            java.util.WeakHashMap r1 = r1.mIterators
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            r1.put(r3, r4)
        L64:
            boolean r1 = r3.hasNext()
            if (r1 == 0) goto Lef
            boolean r1 = r8.newEventOccurred
            if (r1 != 0) goto Lef
            java.lang.Object r1 = r3.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.String r4 = "next()"
            kotlin.ResultKt.checkNotNullExpressionValue(r1, r4)
            java.lang.Object r4 = r1.getKey()
            androidx.lifecycle.LifecycleObserver r4 = (androidx.lifecycle.LifecycleObserver) r4
            java.lang.Object r1 = r1.getValue()
            androidx.lifecycle.LifecycleRegistry$ObserverWithState r1 = (androidx.lifecycle.LifecycleRegistry.ObserverWithState) r1
        L85:
            androidx.lifecycle.Lifecycle$State r5 = r1.state
            androidx.lifecycle.Lifecycle$State r6 = r8.state
            int r5 = r5.compareTo(r6)
            if (r5 <= 0) goto L64
            boolean r5 = r8.newEventOccurred
            if (r5 != 0) goto L64
            androidx.arch.core.internal.FastSafeIterableMap r5 = r8.observerMap
            java.util.HashMap r5 = r5.mHashMap
            boolean r5 = r5.containsKey(r4)
            if (r5 == 0) goto L64
            androidx.lifecycle.Lifecycle$Event$Companion r5 = androidx.lifecycle.Lifecycle.Event.Companion
            androidx.lifecycle.Lifecycle$State r6 = r1.state
            r5.getClass()
            java.lang.String r5 = "state"
            kotlin.ResultKt.checkNotNullParameter(r6, r5)
            int r5 = r6.ordinal()
            r6 = 2
            if (r5 == r6) goto Lbe
            r6 = 3
            if (r5 == r6) goto Lbb
            r6 = 4
            if (r5 == r6) goto Lb8
            r5 = 0
            goto Lc0
        Lb8:
            androidx.lifecycle.Lifecycle$Event r5 = androidx.lifecycle.Lifecycle.Event.ON_PAUSE
            goto Lc0
        Lbb:
            androidx.lifecycle.Lifecycle$Event r5 = androidx.lifecycle.Lifecycle.Event.ON_STOP
            goto Lc0
        Lbe:
            androidx.lifecycle.Lifecycle$Event r5 = androidx.lifecycle.Lifecycle.Event.ON_DESTROY
        Lc0:
            if (r5 == 0) goto Ld9
            androidx.lifecycle.Lifecycle$State r6 = r5.getTargetState()
            java.util.ArrayList r7 = r8.parentStates
            r7.add(r6)
            r1.dispatchEvent(r0, r5)
            java.util.ArrayList r5 = r8.parentStates
            int r6 = r5.size()
            int r6 = r6 - r2
            r5.remove(r6)
            goto L85
        Ld9:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "no event down from "
            r2.<init>(r3)
            androidx.lifecycle.Lifecycle$State r1 = r1.state
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.<init>(r1)
            throw r0
        Lef:
            androidx.arch.core.internal.FastSafeIterableMap r1 = r8.observerMap
            androidx.arch.core.internal.SafeIterableMap$Entry r1 = r1.mEnd
            boolean r3 = r8.newEventOccurred
            if (r3 != 0) goto La
            if (r1 == 0) goto La
            androidx.lifecycle.Lifecycle$State r3 = r8.state
            java.lang.Object r1 = r1.mValue
            androidx.lifecycle.LifecycleRegistry$ObserverWithState r1 = (androidx.lifecycle.LifecycleRegistry.ObserverWithState) r1
            androidx.lifecycle.Lifecycle$State r1 = r1.state
            int r1 = r3.compareTo(r1)
            if (r1 <= 0) goto La
            androidx.arch.core.internal.FastSafeIterableMap r1 = r8.observerMap
            r1.getClass()
            androidx.arch.core.internal.SafeIterableMap$IteratorWithAdditions r3 = new androidx.arch.core.internal.SafeIterableMap$IteratorWithAdditions
            r3.<init>()
            java.util.WeakHashMap r1 = r1.mIterators
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            r1.put(r3, r4)
        L118:
            boolean r1 = r3.hasNext()
            if (r1 == 0) goto La
            boolean r1 = r8.newEventOccurred
            if (r1 != 0) goto La
            java.lang.Object r1 = r3.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r4 = r1.getKey()
            androidx.lifecycle.LifecycleObserver r4 = (androidx.lifecycle.LifecycleObserver) r4
            java.lang.Object r1 = r1.getValue()
            androidx.lifecycle.LifecycleRegistry$ObserverWithState r1 = (androidx.lifecycle.LifecycleRegistry.ObserverWithState) r1
        L134:
            androidx.lifecycle.Lifecycle$State r5 = r1.state
            androidx.lifecycle.Lifecycle$State r6 = r8.state
            int r5 = r5.compareTo(r6)
            if (r5 >= 0) goto L118
            boolean r5 = r8.newEventOccurred
            if (r5 != 0) goto L118
            androidx.arch.core.internal.FastSafeIterableMap r5 = r8.observerMap
            java.util.HashMap r5 = r5.mHashMap
            boolean r5 = r5.containsKey(r4)
            if (r5 == 0) goto L118
            androidx.lifecycle.Lifecycle$State r5 = r1.state
            java.util.ArrayList r6 = r8.parentStates
            r6.add(r5)
            androidx.lifecycle.Lifecycle$Event$Companion r5 = androidx.lifecycle.Lifecycle.Event.Companion
            androidx.lifecycle.Lifecycle$State r6 = r1.state
            r5.getClass()
            androidx.lifecycle.Lifecycle$Event r5 = androidx.lifecycle.Lifecycle.Event.Companion.upFrom(r6)
            if (r5 == 0) goto L16e
            r1.dispatchEvent(r0, r5)
            java.util.ArrayList r5 = r8.parentStates
            int r6 = r5.size()
            int r6 = r6 - r2
            r5.remove(r6)
            goto L134
        L16e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "no event up from "
            r2.<init>(r3)
            androidx.lifecycle.Lifecycle$State r1 = r1.state
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.<init>(r1)
            throw r0
        L184:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "LifecycleOwner of this LifecycleRegistry is already garbage collected. It is too late to change lifecycle state."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.LifecycleRegistry.sync():void");
    }
}
