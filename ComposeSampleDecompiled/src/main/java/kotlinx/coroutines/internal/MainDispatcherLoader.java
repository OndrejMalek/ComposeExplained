package kotlinx.coroutines.internal;

import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.sequences.ConstrainedOnceSequence;
import kotlin.sequences.SequencesKt;
import kotlin.sequences.SequencesKt__SequencesKt$asSequence$$inlined$Sequence$1;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* loaded from: classes.dex */
public abstract class MainDispatcherLoader {
    public static final MainCoroutineDispatcher dispatcher;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v7, types: [kotlin.sequences.ConstrainedOnceSequence] */
    static {
        String str;
        Object next;
        int i = SystemPropsKt__SystemPropsKt.AVAILABLE_PROCESSORS;
        MainCoroutineDispatcher mainCoroutineDispatcher = null;
        try {
            str = System.getProperty("kotlinx.coroutines.fast.service.loader");
        } catch (SecurityException unused) {
            str = null;
        }
        if (str != null) {
            Boolean.parseBoolean(str);
        }
        Iterator m$1 = DurationKt$$ExternalSyntheticCheckNotZero0.m$1();
        ResultKt.checkNotNullParameter(m$1, "<this>");
        SequencesKt__SequencesKt$asSequence$$inlined$Sequence$1 sequencesKt__SequencesKt$asSequence$$inlined$Sequence$1 = new SequencesKt__SequencesKt$asSequence$$inlined$Sequence$1(0, m$1);
        if (!(sequencesKt__SequencesKt$asSequence$$inlined$Sequence$1 instanceof ConstrainedOnceSequence)) {
            sequencesKt__SequencesKt$asSequence$$inlined$Sequence$1 = new ConstrainedOnceSequence(sequencesKt__SequencesKt$asSequence$$inlined$Sequence$1);
        }
        List list = SequencesKt.toList(sequencesKt__SequencesKt$asSequence$$inlined$Sequence$1);
        Iterator it = list.iterator();
        if (it.hasNext()) {
            next = it.next();
            if (it.hasNext()) {
                int loadPriority = ((MainDispatcherFactory) next).getLoadPriority();
                do {
                    Object next2 = it.next();
                    int loadPriority2 = ((MainDispatcherFactory) next2).getLoadPriority();
                    if (loadPriority < loadPriority2) {
                        next = next2;
                        loadPriority = loadPriority2;
                    }
                } while (it.hasNext());
            }
        } else {
            next = null;
        }
        MainDispatcherFactory mainDispatcherFactory = (MainDispatcherFactory) next;
        if (mainDispatcherFactory != null) {
            try {
                mainCoroutineDispatcher = mainDispatcherFactory.createDispatcher(list);
            } catch (Throwable unused2) {
                mainDispatcherFactory.hintOnError();
            }
            if (mainCoroutineDispatcher != null) {
                dispatcher = mainCoroutineDispatcher;
                return;
            }
        }
        throw new IllegalStateException("Module with the Main dispatcher is missing. Add dependency providing the Main dispatcher, e.g. 'kotlinx-coroutines-android' and ensure it has the same version as 'kotlinx-coroutines-core'");
    }
}
