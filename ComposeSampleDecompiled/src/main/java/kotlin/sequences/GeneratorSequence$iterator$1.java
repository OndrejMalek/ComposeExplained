package kotlin.sequences;

import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.Links;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;

/* loaded from: classes.dex */
public final class GeneratorSequence$iterator$1 implements Iterator, KMappedMarker {
    public final /* synthetic */ int $r8$classId;
    public Object nextItem;
    public int nextState;
    public final Object this$0;

    public GeneratorSequence$iterator$1(Object obj, PersistentHashMap persistentHashMap) {
        this.$r8$classId = 1;
        ResultKt.checkNotNullParameter(persistentHashMap, "map");
        this.nextItem = obj;
        this.this$0 = persistentHashMap;
    }

    public final void calcNext$1() {
        Object invoke;
        int i = this.nextState;
        GeneratorSequence generatorSequence = (GeneratorSequence) this.this$0;
        if (i == -2) {
            invoke = ((Function0) generatorSequence.getInitialValue).invoke();
        } else {
            Function1 function1 = generatorSequence.getNextValue;
            Object obj = this.nextItem;
            ResultKt.checkNotNull(obj);
            invoke = function1.invoke(obj);
        }
        this.nextItem = invoke;
        this.nextState = invoke == null ? 0 : 1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        switch (this.$r8$classId) {
            case 0:
                if (this.nextState < 0) {
                    calcNext$1();
                }
                return this.nextState == 1;
            default:
                return this.nextState < ((Map) this.this$0).size();
        }
    }

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.$r8$classId) {
            case 0:
                if (this.nextState < 0) {
                    calcNext$1();
                }
                if (this.nextState == 0) {
                    throw new NoSuchElementException();
                }
                Object obj = this.nextItem;
                ResultKt.checkNotNull(obj, "null cannot be cast to non-null type T of kotlin.sequences.GeneratorSequence");
                this.nextState = -1;
                return obj;
            default:
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Object obj2 = this.nextItem;
                this.nextState++;
                Object obj3 = ((Map) this.this$0).get(obj2);
                if (obj3 != null) {
                    this.nextItem = ((Links) obj3).next;
                    return obj2;
                }
                throw new ConcurrentModificationException("Hash code of an element (" + obj2 + ") has changed after it was added to the persistent set.");
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        switch (this.$r8$classId) {
            case 0:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            default:
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public GeneratorSequence$iterator$1(GeneratorSequence generatorSequence) {
        this.$r8$classId = 0;
        this.this$0 = generatorSequence;
        this.nextState = -2;
    }
}
