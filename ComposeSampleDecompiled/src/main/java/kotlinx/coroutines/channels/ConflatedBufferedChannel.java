package kotlinx.coroutines.channels;

import androidx.emoji2.text.EmojiProcessor;
import androidx.startup.StartupException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.channels.ChannelResult;

/* loaded from: classes.dex */
public final class ConflatedBufferedChannel extends BufferedChannel {
    public final BufferOverflow onBufferOverflow;

    public ConflatedBufferedChannel(int i, BufferOverflow bufferOverflow, Function1 function1) {
        super(i, function1);
        String str;
        this.onBufferOverflow = bufferOverflow;
        if (bufferOverflow != BufferOverflow.SUSPEND) {
            if (i >= 1) {
                return;
            }
            throw new IllegalArgumentException(("Buffered channel capacity must be at least 1, but " + i + " was specified").toString());
        }
        StringBuilder sb = new StringBuilder("This implementation does not support suspension for senders, use ");
        Reflection.factory.getClass();
        Class cls = new ClassReference(BufferedChannel.class).jClass;
        ResultKt.checkNotNullParameter(cls, "jClass");
        String str2 = null;
        if (!cls.isAnonymousClass()) {
            if (cls.isLocalClass()) {
                String simpleName = cls.getSimpleName();
                Method enclosingMethod = cls.getEnclosingMethod();
                if (enclosingMethod == null) {
                    Constructor<?> enclosingConstructor = cls.getEnclosingConstructor();
                    if (enclosingConstructor == null) {
                        int indexOf$default = StringsKt__StringsKt.indexOf$default(simpleName, '$', 0, false, 6);
                        if (indexOf$default == -1) {
                            str2 = simpleName;
                        } else {
                            String substring = simpleName.substring(indexOf$default + 1, simpleName.length());
                            ResultKt.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                            str2 = substring;
                        }
                    } else {
                        str2 = StringsKt__StringsKt.substringAfter$default(simpleName, enclosingConstructor.getName() + '$');
                    }
                } else {
                    str2 = StringsKt__StringsKt.substringAfter$default(simpleName, enclosingMethod.getName() + '$');
                }
            } else {
                boolean isArray = cls.isArray();
                LinkedHashMap linkedHashMap = ClassReference.simpleNames;
                if (isArray) {
                    Class<?> componentType = cls.getComponentType();
                    if (componentType.isPrimitive() && (str = (String) linkedHashMap.get(componentType.getName())) != null) {
                        str2 = str.concat("Array");
                    }
                    if (str2 == null) {
                        str2 = "Array";
                    }
                } else {
                    str2 = (String) linkedHashMap.get(cls.getName());
                    if (str2 == null) {
                        str2 = cls.getSimpleName();
                    }
                }
            }
        }
        sb.append(str2);
        sb.append(" instead");
        throw new IllegalArgumentException(sb.toString().toString());
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel
    public final boolean isConflatedDropOldest() {
        return this.onBufferOverflow == BufferOverflow.DROP_OLDEST;
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    public final Object send(Object obj, Continuation continuation) {
        StartupException callUndeliveredElementCatchingException;
        Object m307trySendImplMj0NB7M = m307trySendImplMj0NB7M(obj, true);
        boolean z = m307trySendImplMj0NB7M instanceof ChannelResult.Closed;
        if (!z) {
            return Unit.INSTANCE;
        }
        if (z) {
        }
        Function1 function1 = this.onUndeliveredElement;
        if (function1 == null || (callUndeliveredElementCatchingException = JobKt.callUndeliveredElementCatchingException(function1, obj, null)) == null) {
            throw getSendException();
        }
        ResultKt.addSuppressed(callUndeliveredElementCatchingException, getSendException());
        throw callUndeliveredElementCatchingException;
    }

    @Override // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    /* renamed from: trySend-JP2dKIU */
    public final Object mo306trySendJP2dKIU(Object obj) {
        return m307trySendImplMj0NB7M(obj, false);
    }

    /* renamed from: trySendImpl-Mj0NB7M, reason: not valid java name */
    public final Object m307trySendImplMj0NB7M(Object obj, boolean z) {
        ChannelSegment channelSegment;
        Function1 function1;
        StartupException callUndeliveredElementCatchingException;
        BufferOverflow bufferOverflow = BufferOverflow.DROP_LATEST;
        Unit unit = Unit.INSTANCE;
        if (this.onBufferOverflow == bufferOverflow) {
            Object mo306trySendJP2dKIU = super.mo306trySendJP2dKIU(obj);
            if ((!(mo306trySendJP2dKIU instanceof ChannelResult.Failed)) || (mo306trySendJP2dKIU instanceof ChannelResult.Closed)) {
                return mo306trySendJP2dKIU;
            }
            if (!z || (function1 = this.onUndeliveredElement) == null || (callUndeliveredElementCatchingException = JobKt.callUndeliveredElementCatchingException(function1, obj, null)) == null) {
                return unit;
            }
            throw callUndeliveredElementCatchingException;
        }
        EmojiProcessor.EmojiProcessCallback emojiProcessCallback = BufferedChannelKt.BUFFERED;
        ChannelSegment channelSegment2 = (ChannelSegment) BufferedChannel.sendSegment$FU.get(this);
        while (true) {
            long andIncrement = BufferedChannel.sendersAndCloseStatus$FU.getAndIncrement(this);
            long j = andIncrement & 1152921504606846975L;
            boolean isClosed = isClosed(andIncrement, false);
            int i = BufferedChannelKt.SEGMENT_SIZE;
            long j2 = i;
            long j3 = j / j2;
            int i2 = (int) (j % j2);
            if (channelSegment2.id != j3) {
                ChannelSegment access$findSegmentSend = BufferedChannel.access$findSegmentSend(this, j3, channelSegment2);
                if (access$findSegmentSend != null) {
                    channelSegment = access$findSegmentSend;
                } else if (isClosed) {
                    return new ChannelResult.Closed(getSendException());
                }
            } else {
                channelSegment = channelSegment2;
            }
            int access$updateCellSend = BufferedChannel.access$updateCellSend(this, channelSegment, i2, obj, j, emojiProcessCallback, isClosed);
            if (access$updateCellSend == 0) {
                channelSegment.cleanPrev();
                return unit;
            }
            if (access$updateCellSend == 1) {
                return unit;
            }
            if (access$updateCellSend == 2) {
                if (isClosed) {
                    channelSegment.onSlotCleaned();
                    return new ChannelResult.Closed(getSendException());
                }
                Waiter waiter = emojiProcessCallback instanceof Waiter ? (Waiter) emojiProcessCallback : null;
                if (waiter != null) {
                    waiter.invokeOnCancellation(channelSegment, i2 + i);
                }
                dropFirstElementUntilTheSpecifiedCellIsInTheBuffer((channelSegment.id * j2) + i2);
                return unit;
            }
            if (access$updateCellSend == 3) {
                throw new IllegalStateException("unexpected".toString());
            }
            if (access$updateCellSend == 4) {
                if (j < BufferedChannel.receivers$FU.get(this)) {
                    channelSegment.cleanPrev();
                }
                return new ChannelResult.Closed(getSendException());
            }
            if (access$updateCellSend == 5) {
                channelSegment.cleanPrev();
            }
            channelSegment2 = channelSegment;
        }
    }
}
