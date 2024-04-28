package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import _COROUTINE.ArtificialStackFrames;
import androidx.compose.runtime.Stack;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.DeltaCounter;
import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.collections.MapsKt___MapsJvmKt;
import kotlin.ranges.IntProgression;

/* loaded from: classes.dex */
public final class TrieNode {
    public static final TrieNode EMPTY = new TrieNode(0, 0, new Object[0], null);
    public Object[] buffer;
    public int dataMap;
    public int nodeMap;
    public final ArtificialStackFrames ownedBy;

    public TrieNode(int i, int i2, Object[] objArr, ArtificialStackFrames artificialStackFrames) {
        this.dataMap = i;
        this.nodeMap = i2;
        this.ownedBy = artificialStackFrames;
        this.buffer = objArr;
    }

    public static TrieNode makeNode(int i, Object obj, Object obj2, int i2, Object obj3, Object obj4, int i3, ArtificialStackFrames artificialStackFrames) {
        if (i3 > 30) {
            return new TrieNode(0, 0, new Object[]{obj, obj2, obj3, obj4}, artificialStackFrames);
        }
        int indexSegment$1 = ResultKt.indexSegment$1(i, i3);
        int indexSegment$12 = ResultKt.indexSegment$1(i2, i3);
        if (indexSegment$1 != indexSegment$12) {
            return new TrieNode((1 << indexSegment$1) | (1 << indexSegment$12), 0, indexSegment$1 < indexSegment$12 ? new Object[]{obj, obj2, obj3, obj4} : new Object[]{obj3, obj4, obj, obj2}, artificialStackFrames);
        }
        return new TrieNode(0, 1 << indexSegment$1, new Object[]{makeNode(i, obj, obj2, i2, obj3, obj4, i3 + 5, artificialStackFrames)}, artificialStackFrames);
    }

    public final Object[] bufferMoveEntryToNode(int i, int i2, int i3, Object obj, Object obj2, int i4, ArtificialStackFrames artificialStackFrames) {
        Object obj3 = this.buffer[i];
        TrieNode makeNode = makeNode(obj3 != null ? obj3.hashCode() : 0, obj3, valueAtKeyIndex(i), i3, obj, obj2, i4 + 5, artificialStackFrames);
        int nodeIndex$runtime_release = nodeIndex$runtime_release(i2);
        int i5 = nodeIndex$runtime_release + 1;
        Object[] objArr = this.buffer;
        Object[] objArr2 = new Object[objArr.length - 1];
        MapsKt___MapsJvmKt.copyInto$default(objArr, objArr2, 0, i, 6);
        MapsKt___MapsJvmKt.copyInto(objArr, objArr2, i, i + 2, i5);
        objArr2[nodeIndex$runtime_release - 1] = makeNode;
        MapsKt___MapsJvmKt.copyInto(objArr, objArr2, nodeIndex$runtime_release, i5, objArr.length);
        return objArr2;
    }

    public final int calculateSize() {
        if (this.nodeMap == 0) {
            return this.buffer.length / 2;
        }
        int bitCount = Integer.bitCount(this.dataMap);
        int length = this.buffer.length;
        for (int i = bitCount * 2; i < length; i++) {
            bitCount += nodeAtIndex$runtime_release(i).calculateSize();
        }
        return bitCount;
    }

    public final boolean collisionContainsKey(Object obj) {
        IntProgression step = ResultKt.step(ResultKt.until(0, this.buffer.length));
        int i = step.first;
        int i2 = step.last;
        int i3 = step.step;
        if ((i3 > 0 && i <= i2) || (i3 < 0 && i2 <= i)) {
            while (!ResultKt.areEqual(obj, this.buffer[i])) {
                if (i != i2) {
                    i += i3;
                }
            }
            return true;
        }
        return false;
    }

    public final boolean containsKey(int i, int i2, Object obj) {
        int indexSegment$1 = 1 << ResultKt.indexSegment$1(i, i2);
        if (hasEntryAt$runtime_release(indexSegment$1)) {
            return ResultKt.areEqual(obj, this.buffer[entryKeyIndex$runtime_release(indexSegment$1)]);
        }
        if (!hasNodeAt(indexSegment$1)) {
            return false;
        }
        TrieNode nodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release(indexSegment$1));
        return i2 == 30 ? nodeAtIndex$runtime_release.collisionContainsKey(obj) : nodeAtIndex$runtime_release.containsKey(i, i2 + 5, obj);
    }

    public final boolean elementsIdentityEquals(TrieNode trieNode) {
        if (this == trieNode) {
            return true;
        }
        if (this.nodeMap != trieNode.nodeMap || this.dataMap != trieNode.dataMap) {
            return false;
        }
        int length = this.buffer.length;
        for (int i = 0; i < length; i++) {
            if (this.buffer[i] != trieNode.buffer[i]) {
                return false;
            }
        }
        return true;
    }

    public final int entryKeyIndex$runtime_release(int i) {
        return Integer.bitCount((i - 1) & this.dataMap) * 2;
    }

    public final Object get(int i, int i2, Object obj) {
        int indexSegment$1 = 1 << ResultKt.indexSegment$1(i, i2);
        if (hasEntryAt$runtime_release(indexSegment$1)) {
            int entryKeyIndex$runtime_release = entryKeyIndex$runtime_release(indexSegment$1);
            if (ResultKt.areEqual(obj, this.buffer[entryKeyIndex$runtime_release])) {
                return valueAtKeyIndex(entryKeyIndex$runtime_release);
            }
            return null;
        }
        if (!hasNodeAt(indexSegment$1)) {
            return null;
        }
        TrieNode nodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release(indexSegment$1));
        if (i2 != 30) {
            return nodeAtIndex$runtime_release.get(i, i2 + 5, obj);
        }
        IntProgression step = ResultKt.step(ResultKt.until(0, nodeAtIndex$runtime_release.buffer.length));
        int i3 = step.first;
        int i4 = step.last;
        int i5 = step.step;
        if ((i5 <= 0 || i3 > i4) && (i5 >= 0 || i4 > i3)) {
            return null;
        }
        while (!ResultKt.areEqual(obj, nodeAtIndex$runtime_release.buffer[i3])) {
            if (i3 == i4) {
                return null;
            }
            i3 += i5;
        }
        return nodeAtIndex$runtime_release.valueAtKeyIndex(i3);
    }

    public final boolean hasEntryAt$runtime_release(int i) {
        return (i & this.dataMap) != 0;
    }

    public final boolean hasNodeAt(int i) {
        return (i & this.nodeMap) != 0;
    }

    public final TrieNode mutableCollisionRemoveEntryAtIndex(int i, PersistentHashMapBuilder persistentHashMapBuilder) {
        persistentHashMapBuilder.setSize(persistentHashMapBuilder.size - 1);
        persistentHashMapBuilder.operationResult = valueAtKeyIndex(i);
        Object[] objArr = this.buffer;
        if (objArr.length == 2) {
            return null;
        }
        if (this.ownedBy != persistentHashMapBuilder.ownership) {
            return new TrieNode(0, 0, ResultKt.access$removeEntryAtIndex(objArr, i), persistentHashMapBuilder.ownership);
        }
        this.buffer = ResultKt.access$removeEntryAtIndex(objArr, i);
        return this;
    }

    public final TrieNode mutablePut(int i, Object obj, Object obj2, int i2, PersistentHashMapBuilder persistentHashMapBuilder) {
        TrieNode mutablePut;
        ResultKt.checkNotNullParameter(persistentHashMapBuilder, "mutator");
        int indexSegment$1 = 1 << ResultKt.indexSegment$1(i, i2);
        boolean hasEntryAt$runtime_release = hasEntryAt$runtime_release(indexSegment$1);
        ArtificialStackFrames artificialStackFrames = this.ownedBy;
        if (hasEntryAt$runtime_release) {
            int entryKeyIndex$runtime_release = entryKeyIndex$runtime_release(indexSegment$1);
            if (!ResultKt.areEqual(obj, this.buffer[entryKeyIndex$runtime_release])) {
                persistentHashMapBuilder.setSize(persistentHashMapBuilder.size + 1);
                ArtificialStackFrames artificialStackFrames2 = persistentHashMapBuilder.ownership;
                if (artificialStackFrames != artificialStackFrames2) {
                    return new TrieNode(this.dataMap ^ indexSegment$1, this.nodeMap | indexSegment$1, bufferMoveEntryToNode(entryKeyIndex$runtime_release, indexSegment$1, i, obj, obj2, i2, artificialStackFrames2), artificialStackFrames2);
                }
                this.buffer = bufferMoveEntryToNode(entryKeyIndex$runtime_release, indexSegment$1, i, obj, obj2, i2, artificialStackFrames2);
                this.dataMap ^= indexSegment$1;
                this.nodeMap |= indexSegment$1;
                return this;
            }
            persistentHashMapBuilder.operationResult = valueAtKeyIndex(entryKeyIndex$runtime_release);
            if (valueAtKeyIndex(entryKeyIndex$runtime_release) == obj2) {
                return this;
            }
            if (artificialStackFrames == persistentHashMapBuilder.ownership) {
                this.buffer[entryKeyIndex$runtime_release + 1] = obj2;
                return this;
            }
            persistentHashMapBuilder.modCount++;
            Object[] objArr = this.buffer;
            Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
            ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
            copyOf[entryKeyIndex$runtime_release + 1] = obj2;
            return new TrieNode(this.dataMap, this.nodeMap, copyOf, persistentHashMapBuilder.ownership);
        }
        if (!hasNodeAt(indexSegment$1)) {
            persistentHashMapBuilder.setSize(persistentHashMapBuilder.size + 1);
            ArtificialStackFrames artificialStackFrames3 = persistentHashMapBuilder.ownership;
            int entryKeyIndex$runtime_release2 = entryKeyIndex$runtime_release(indexSegment$1);
            if (artificialStackFrames != artificialStackFrames3) {
                return new TrieNode(this.dataMap | indexSegment$1, this.nodeMap, ResultKt.access$insertEntryAtIndex(this.buffer, entryKeyIndex$runtime_release2, obj, obj2), artificialStackFrames3);
            }
            this.buffer = ResultKt.access$insertEntryAtIndex(this.buffer, entryKeyIndex$runtime_release2, obj, obj2);
            this.dataMap |= indexSegment$1;
            return this;
        }
        int nodeIndex$runtime_release = nodeIndex$runtime_release(indexSegment$1);
        TrieNode nodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release);
        if (i2 == 30) {
            IntProgression step = ResultKt.step(ResultKt.until(0, nodeAtIndex$runtime_release.buffer.length));
            int i3 = step.first;
            int i4 = step.last;
            int i5 = step.step;
            if ((i5 > 0 && i3 <= i4) || (i5 < 0 && i4 <= i3)) {
                while (!ResultKt.areEqual(obj, nodeAtIndex$runtime_release.buffer[i3])) {
                    if (i3 != i4) {
                        i3 += i5;
                    }
                }
                persistentHashMapBuilder.operationResult = nodeAtIndex$runtime_release.valueAtKeyIndex(i3);
                if (nodeAtIndex$runtime_release.ownedBy == persistentHashMapBuilder.ownership) {
                    nodeAtIndex$runtime_release.buffer[i3 + 1] = obj2;
                    mutablePut = nodeAtIndex$runtime_release;
                } else {
                    persistentHashMapBuilder.modCount++;
                    Object[] objArr2 = nodeAtIndex$runtime_release.buffer;
                    Object[] copyOf2 = Arrays.copyOf(objArr2, objArr2.length);
                    ResultKt.checkNotNullExpressionValue(copyOf2, "copyOf(this, size)");
                    copyOf2[i3 + 1] = obj2;
                    mutablePut = new TrieNode(0, 0, copyOf2, persistentHashMapBuilder.ownership);
                }
            }
            persistentHashMapBuilder.setSize(persistentHashMapBuilder.size + 1);
            mutablePut = new TrieNode(0, 0, ResultKt.access$insertEntryAtIndex(nodeAtIndex$runtime_release.buffer, 0, obj, obj2), persistentHashMapBuilder.ownership);
            break;
        }
        mutablePut = nodeAtIndex$runtime_release.mutablePut(i, obj, obj2, i2 + 5, persistentHashMapBuilder);
        return nodeAtIndex$runtime_release == mutablePut ? this : mutableUpdateNodeAtIndex(nodeIndex$runtime_release, mutablePut, persistentHashMapBuilder.ownership);
    }

    public final TrieNode mutablePutAll(TrieNode trieNode, int i, DeltaCounter deltaCounter, PersistentHashMapBuilder persistentHashMapBuilder) {
        Object[] objArr;
        int i2;
        int i3;
        TrieNode makeNode;
        ResultKt.checkNotNullParameter(persistentHashMapBuilder, "mutator");
        if (this == trieNode) {
            deltaCounter.count += calculateSize();
            return this;
        }
        int i4 = 0;
        if (i > 30) {
            ArtificialStackFrames artificialStackFrames = persistentHashMapBuilder.ownership;
            Object[] objArr2 = this.buffer;
            Object[] copyOf = Arrays.copyOf(objArr2, objArr2.length + trieNode.buffer.length);
            ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            int length = this.buffer.length;
            IntProgression step = ResultKt.step(ResultKt.until(0, trieNode.buffer.length));
            int i5 = step.first;
            int i6 = step.last;
            int i7 = step.step;
            if ((i7 > 0 && i5 <= i6) || (i7 < 0 && i6 <= i5)) {
                while (true) {
                    if (collisionContainsKey(trieNode.buffer[i5])) {
                        deltaCounter.count++;
                    } else {
                        Object[] objArr3 = trieNode.buffer;
                        copyOf[length] = objArr3[i5];
                        copyOf[length + 1] = objArr3[i5 + 1];
                        length += 2;
                    }
                    if (i5 == i6) {
                        break;
                    }
                    i5 += i7;
                }
            }
            if (length == this.buffer.length) {
                return this;
            }
            if (length == trieNode.buffer.length) {
                return trieNode;
            }
            if (length == copyOf.length) {
                return new TrieNode(0, 0, copyOf, artificialStackFrames);
            }
            Object[] copyOf2 = Arrays.copyOf(copyOf, length);
            ResultKt.checkNotNullExpressionValue(copyOf2, "copyOf(this, newSize)");
            return new TrieNode(0, 0, copyOf2, artificialStackFrames);
        }
        int i8 = this.nodeMap | trieNode.nodeMap;
        int i9 = this.dataMap;
        int i10 = trieNode.dataMap;
        int i11 = (i9 ^ i10) & (~i8);
        int i12 = i9 & i10;
        int i13 = i11;
        while (i12 != 0) {
            int lowestOneBit = Integer.lowestOneBit(i12);
            if (ResultKt.areEqual(this.buffer[entryKeyIndex$runtime_release(lowestOneBit)], trieNode.buffer[trieNode.entryKeyIndex$runtime_release(lowestOneBit)])) {
                i13 |= lowestOneBit;
            } else {
                i8 |= lowestOneBit;
            }
            i12 ^= lowestOneBit;
        }
        if ((i8 & i13) != 0) {
            throw new IllegalStateException("Check failed.".toString());
        }
        TrieNode trieNode2 = (ResultKt.areEqual(this.ownedBy, persistentHashMapBuilder.ownership) && this.dataMap == i13 && this.nodeMap == i8) ? this : new TrieNode(i13, i8, new Object[Integer.bitCount(i8) + (Integer.bitCount(i13) * 2)], null);
        int i14 = i8;
        int i15 = 0;
        while (i14 != 0) {
            int lowestOneBit2 = Integer.lowestOneBit(i14);
            Object[] objArr4 = trieNode2.buffer;
            int length2 = (objArr4.length - 1) - i15;
            if (hasNodeAt(lowestOneBit2)) {
                makeNode = nodeAtIndex$runtime_release(nodeIndex$runtime_release(lowestOneBit2));
                if (trieNode.hasNodeAt(lowestOneBit2)) {
                    makeNode = makeNode.mutablePutAll(trieNode.nodeAtIndex$runtime_release(trieNode.nodeIndex$runtime_release(lowestOneBit2)), i + 5, deltaCounter, persistentHashMapBuilder);
                } else if (trieNode.hasEntryAt$runtime_release(lowestOneBit2)) {
                    int entryKeyIndex$runtime_release = trieNode.entryKeyIndex$runtime_release(lowestOneBit2);
                    Object obj = trieNode.buffer[entryKeyIndex$runtime_release];
                    Object valueAtKeyIndex = trieNode.valueAtKeyIndex(entryKeyIndex$runtime_release);
                    int i16 = persistentHashMapBuilder.size;
                    objArr = objArr4;
                    i2 = i13;
                    i3 = lowestOneBit2;
                    makeNode = makeNode.mutablePut(obj != null ? obj.hashCode() : i4, obj, valueAtKeyIndex, i + 5, persistentHashMapBuilder);
                    if (persistentHashMapBuilder.size == i16) {
                        deltaCounter.count++;
                    }
                }
                objArr = objArr4;
                i2 = i13;
                i3 = lowestOneBit2;
            } else {
                objArr = objArr4;
                i2 = i13;
                i3 = lowestOneBit2;
                if (trieNode.hasNodeAt(i3)) {
                    makeNode = trieNode.nodeAtIndex$runtime_release(trieNode.nodeIndex$runtime_release(i3));
                    if (hasEntryAt$runtime_release(i3)) {
                        int entryKeyIndex$runtime_release2 = entryKeyIndex$runtime_release(i3);
                        Object obj2 = this.buffer[entryKeyIndex$runtime_release2];
                        int i17 = i + 5;
                        if (makeNode.containsKey(obj2 != null ? obj2.hashCode() : 0, i17, obj2)) {
                            deltaCounter.count++;
                        } else {
                            makeNode = makeNode.mutablePut(obj2 != null ? obj2.hashCode() : 0, obj2, valueAtKeyIndex(entryKeyIndex$runtime_release2), i17, persistentHashMapBuilder);
                        }
                    }
                } else {
                    int entryKeyIndex$runtime_release3 = entryKeyIndex$runtime_release(i3);
                    Object obj3 = this.buffer[entryKeyIndex$runtime_release3];
                    Object valueAtKeyIndex2 = valueAtKeyIndex(entryKeyIndex$runtime_release3);
                    int entryKeyIndex$runtime_release4 = trieNode.entryKeyIndex$runtime_release(i3);
                    Object obj4 = trieNode.buffer[entryKeyIndex$runtime_release4];
                    makeNode = makeNode(obj3 != null ? obj3.hashCode() : 0, obj3, valueAtKeyIndex2, obj4 != null ? obj4.hashCode() : 0, obj4, trieNode.valueAtKeyIndex(entryKeyIndex$runtime_release4), i + 5, persistentHashMapBuilder.ownership);
                }
            }
            objArr[length2] = makeNode;
            i15++;
            i14 ^= i3;
            i13 = i2;
            i4 = 0;
        }
        int i18 = 0;
        while (i13 != 0) {
            int lowestOneBit3 = Integer.lowestOneBit(i13);
            int i19 = i18 * 2;
            if (trieNode.hasEntryAt$runtime_release(lowestOneBit3)) {
                int entryKeyIndex$runtime_release5 = trieNode.entryKeyIndex$runtime_release(lowestOneBit3);
                Object[] objArr5 = trieNode2.buffer;
                objArr5[i19] = trieNode.buffer[entryKeyIndex$runtime_release5];
                objArr5[i19 + 1] = trieNode.valueAtKeyIndex(entryKeyIndex$runtime_release5);
                if (hasEntryAt$runtime_release(lowestOneBit3)) {
                    deltaCounter.count++;
                }
            } else {
                int entryKeyIndex$runtime_release6 = entryKeyIndex$runtime_release(lowestOneBit3);
                Object[] objArr6 = trieNode2.buffer;
                objArr6[i19] = this.buffer[entryKeyIndex$runtime_release6];
                objArr6[i19 + 1] = valueAtKeyIndex(entryKeyIndex$runtime_release6);
            }
            i18++;
            i13 ^= lowestOneBit3;
        }
        return elementsIdentityEquals(trieNode2) ? this : trieNode.elementsIdentityEquals(trieNode2) ? trieNode : trieNode2;
    }

    public final TrieNode mutableRemove(int i, Object obj, int i2, PersistentHashMapBuilder persistentHashMapBuilder) {
        TrieNode mutableRemove;
        ResultKt.checkNotNullParameter(persistentHashMapBuilder, "mutator");
        int indexSegment$1 = 1 << ResultKt.indexSegment$1(i, i2);
        if (hasEntryAt$runtime_release(indexSegment$1)) {
            int entryKeyIndex$runtime_release = entryKeyIndex$runtime_release(indexSegment$1);
            return ResultKt.areEqual(obj, this.buffer[entryKeyIndex$runtime_release]) ? mutableRemoveEntryAtIndex(entryKeyIndex$runtime_release, indexSegment$1, persistentHashMapBuilder) : this;
        }
        if (!hasNodeAt(indexSegment$1)) {
            return this;
        }
        int nodeIndex$runtime_release = nodeIndex$runtime_release(indexSegment$1);
        TrieNode nodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release);
        if (i2 == 30) {
            IntProgression step = ResultKt.step(ResultKt.until(0, nodeAtIndex$runtime_release.buffer.length));
            int i3 = step.first;
            int i4 = step.last;
            int i5 = step.step;
            if ((i5 > 0 && i3 <= i4) || (i5 < 0 && i4 <= i3)) {
                while (!ResultKt.areEqual(obj, nodeAtIndex$runtime_release.buffer[i3])) {
                    if (i3 != i4) {
                        i3 += i5;
                    }
                }
                mutableRemove = nodeAtIndex$runtime_release.mutableCollisionRemoveEntryAtIndex(i3, persistentHashMapBuilder);
            }
            mutableRemove = nodeAtIndex$runtime_release;
            break;
        }
        mutableRemove = nodeAtIndex$runtime_release.mutableRemove(i, obj, i2 + 5, persistentHashMapBuilder);
        return mutableReplaceNode(nodeAtIndex$runtime_release, mutableRemove, nodeIndex$runtime_release, indexSegment$1, persistentHashMapBuilder.ownership);
    }

    public final TrieNode mutableRemoveEntryAtIndex(int i, int i2, PersistentHashMapBuilder persistentHashMapBuilder) {
        persistentHashMapBuilder.setSize(persistentHashMapBuilder.size - 1);
        persistentHashMapBuilder.operationResult = valueAtKeyIndex(i);
        Object[] objArr = this.buffer;
        if (objArr.length == 2) {
            return null;
        }
        if (this.ownedBy != persistentHashMapBuilder.ownership) {
            return new TrieNode(i2 ^ this.dataMap, this.nodeMap, ResultKt.access$removeEntryAtIndex(objArr, i), persistentHashMapBuilder.ownership);
        }
        this.buffer = ResultKt.access$removeEntryAtIndex(objArr, i);
        this.dataMap ^= i2;
        return this;
    }

    public final TrieNode mutableReplaceNode(TrieNode trieNode, TrieNode trieNode2, int i, int i2, ArtificialStackFrames artificialStackFrames) {
        ArtificialStackFrames artificialStackFrames2 = this.ownedBy;
        if (trieNode2 == null) {
            Object[] objArr = this.buffer;
            if (objArr.length == 1) {
                return null;
            }
            if (artificialStackFrames2 != artificialStackFrames) {
                return new TrieNode(this.dataMap, i2 ^ this.nodeMap, ResultKt.access$removeNodeAtIndex(objArr, i), artificialStackFrames);
            }
            this.buffer = ResultKt.access$removeNodeAtIndex(objArr, i);
            this.nodeMap ^= i2;
        } else if (artificialStackFrames2 == artificialStackFrames || trieNode != trieNode2) {
            return mutableUpdateNodeAtIndex(i, trieNode2, artificialStackFrames);
        }
        return this;
    }

    public final TrieNode mutableUpdateNodeAtIndex(int i, TrieNode trieNode, ArtificialStackFrames artificialStackFrames) {
        Object[] objArr = this.buffer;
        if (objArr.length == 1 && trieNode.buffer.length == 2 && trieNode.nodeMap == 0) {
            trieNode.dataMap = this.nodeMap;
            return trieNode;
        }
        if (this.ownedBy == artificialStackFrames) {
            objArr[i] = trieNode;
            return this;
        }
        Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
        ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
        copyOf[i] = trieNode;
        return new TrieNode(this.dataMap, this.nodeMap, copyOf, artificialStackFrames);
    }

    public final TrieNode nodeAtIndex$runtime_release(int i) {
        Object obj = this.buffer[i];
        ResultKt.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode<K of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode, V of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode>");
        return (TrieNode) obj;
    }

    public final int nodeIndex$runtime_release(int i) {
        return (this.buffer.length - 1) - Integer.bitCount((i - 1) & this.nodeMap);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00ca A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final kotlinx.coroutines.flow.SafeFlow put(int r12, int r13, java.lang.Object r14, androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.Links r15) {
        /*
            r11 = this;
            int r0 = kotlin.ResultKt.indexSegment$1(r12, r13)
            r1 = 1
            int r0 = r1 << r0
            boolean r2 = r11.hasEntryAt$runtime_release(r0)
            r3 = 0
            java.lang.String r4 = "copyOf(this, size)"
            r10 = 0
            if (r2 == 0) goto L5f
            int r5 = r11.entryKeyIndex$runtime_release(r0)
            java.lang.Object[] r2 = r11.buffer
            r2 = r2[r5]
            boolean r2 = kotlin.ResultKt.areEqual(r14, r2)
            if (r2 == 0) goto L42
            java.lang.Object r12 = r11.valueAtKeyIndex(r5)
            if (r12 != r15) goto L26
            return r10
        L26:
            java.lang.Object[] r12 = r11.buffer
            int r13 = r12.length
            java.lang.Object[] r12 = java.util.Arrays.copyOf(r12, r13)
            kotlin.ResultKt.checkNotNullExpressionValue(r12, r4)
            int r5 = r5 + r1
            r12[r5] = r15
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode r13 = new androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode
            int r14 = r11.dataMap
            int r15 = r11.nodeMap
            r13.<init>(r14, r15, r12, r10)
            kotlinx.coroutines.flow.SafeFlow r12 = new kotlinx.coroutines.flow.SafeFlow
            r12.<init>(r13, r3)
            return r12
        L42:
            r9 = 0
            r2 = r11
            r3 = r5
            r4 = r0
            r5 = r12
            r6 = r14
            r7 = r15
            r8 = r13
            java.lang.Object[] r12 = r2.bufferMoveEntryToNode(r3, r4, r5, r6, r7, r8, r9)
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode r13 = new androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode
            int r14 = r11.dataMap
            r14 = r14 ^ r0
            int r15 = r11.nodeMap
            r15 = r15 | r0
            r13.<init>(r14, r15, r12, r10)
            kotlinx.coroutines.flow.SafeFlow r12 = new kotlinx.coroutines.flow.SafeFlow
            r12.<init>(r13, r1)
            return r12
        L5f:
            boolean r2 = r11.hasNodeAt(r0)
            if (r2 == 0) goto Ldf
            int r2 = r11.nodeIndex$runtime_release(r0)
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode r5 = r11.nodeAtIndex$runtime_release(r2)
            r6 = 30
            if (r13 != r6) goto Lcb
            java.lang.Object[] r12 = r5.buffer
            int r12 = r12.length
            kotlin.ranges.IntRange r12 = kotlin.ResultKt.until(r3, r12)
            kotlin.ranges.IntProgression r12 = kotlin.ResultKt.step(r12)
            int r13 = r12.first
            int r6 = r12.last
            int r12 = r12.step
            if (r12 <= 0) goto L86
            if (r13 <= r6) goto L8a
        L86:
            if (r12 >= 0) goto Lb8
            if (r6 > r13) goto Lb8
        L8a:
            java.lang.Object[] r7 = r5.buffer
            r7 = r7[r13]
            boolean r7 = kotlin.ResultKt.areEqual(r14, r7)
            if (r7 == 0) goto Lb4
            java.lang.Object r12 = r5.valueAtKeyIndex(r13)
            if (r15 != r12) goto L9c
            r12 = r10
            goto Lc8
        L9c:
            java.lang.Object[] r12 = r5.buffer
            int r14 = r12.length
            java.lang.Object[] r12 = java.util.Arrays.copyOf(r12, r14)
            kotlin.ResultKt.checkNotNullExpressionValue(r12, r4)
            int r13 = r13 + r1
            r12[r13] = r15
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode r13 = new androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode
            r13.<init>(r3, r3, r12, r10)
            kotlinx.coroutines.flow.SafeFlow r12 = new kotlinx.coroutines.flow.SafeFlow
            r12.<init>(r13, r3)
            goto Lc8
        Lb4:
            if (r13 == r6) goto Lb8
            int r13 = r13 + r12
            goto L8a
        Lb8:
            java.lang.Object[] r12 = r5.buffer
            java.lang.Object[] r12 = kotlin.ResultKt.access$insertEntryAtIndex(r12, r3, r14, r15)
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode r13 = new androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode
            r13.<init>(r3, r3, r12, r10)
            kotlinx.coroutines.flow.SafeFlow r12 = new kotlinx.coroutines.flow.SafeFlow
            r12.<init>(r13, r1)
        Lc8:
            if (r12 != 0) goto Ld4
            return r10
        Lcb:
            int r13 = r13 + 5
            kotlinx.coroutines.flow.SafeFlow r12 = r5.put(r12, r13, r14, r15)
            if (r12 != 0) goto Ld4
            return r10
        Ld4:
            java.lang.Object r13 = r12.block
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode r13 = (androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode) r13
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode r13 = r11.updateNodeAtIndex(r2, r0, r13)
            r12.block = r13
            return r12
        Ldf:
            int r12 = r11.entryKeyIndex$runtime_release(r0)
            java.lang.Object[] r13 = r11.buffer
            java.lang.Object[] r12 = kotlin.ResultKt.access$insertEntryAtIndex(r13, r12, r14, r15)
            androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode r13 = new androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode
            int r14 = r11.dataMap
            r14 = r14 | r0
            int r15 = r11.nodeMap
            r13.<init>(r14, r15, r12, r10)
            kotlinx.coroutines.flow.SafeFlow r12 = new kotlinx.coroutines.flow.SafeFlow
            r12.<init>(r13, r1)
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode.put(int, int, java.lang.Object, androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.Links):kotlinx.coroutines.flow.SafeFlow");
    }

    public final TrieNode remove(int i, int i2, Stack stack) {
        TrieNode remove;
        int indexSegment$1 = 1 << ResultKt.indexSegment$1(i, i2);
        if (hasEntryAt$runtime_release(indexSegment$1)) {
            int entryKeyIndex$runtime_release = entryKeyIndex$runtime_release(indexSegment$1);
            if (!ResultKt.areEqual(stack, this.buffer[entryKeyIndex$runtime_release])) {
                return this;
            }
            Object[] objArr = this.buffer;
            if (objArr.length == 2) {
                return null;
            }
            return new TrieNode(this.dataMap ^ indexSegment$1, this.nodeMap, ResultKt.access$removeEntryAtIndex(objArr, entryKeyIndex$runtime_release), null);
        }
        if (!hasNodeAt(indexSegment$1)) {
            return this;
        }
        int nodeIndex$runtime_release = nodeIndex$runtime_release(indexSegment$1);
        TrieNode nodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release);
        if (i2 == 30) {
            IntProgression step = ResultKt.step(ResultKt.until(0, nodeAtIndex$runtime_release.buffer.length));
            int i3 = step.first;
            int i4 = step.last;
            int i5 = step.step;
            if ((i5 > 0 && i3 <= i4) || (i5 < 0 && i4 <= i3)) {
                while (!ResultKt.areEqual(stack, nodeAtIndex$runtime_release.buffer[i3])) {
                    if (i3 != i4) {
                        i3 += i5;
                    }
                }
                Object[] objArr2 = nodeAtIndex$runtime_release.buffer;
                remove = objArr2.length == 2 ? null : new TrieNode(0, 0, ResultKt.access$removeEntryAtIndex(objArr2, i3), null);
            }
            remove = nodeAtIndex$runtime_release;
            break;
        }
        remove = nodeAtIndex$runtime_release.remove(i, i2 + 5, stack);
        if (remove != null) {
            return nodeAtIndex$runtime_release != remove ? updateNodeAtIndex(nodeIndex$runtime_release, indexSegment$1, remove) : this;
        }
        Object[] objArr3 = this.buffer;
        if (objArr3.length == 1) {
            return null;
        }
        return new TrieNode(this.dataMap, indexSegment$1 ^ this.nodeMap, ResultKt.access$removeNodeAtIndex(objArr3, nodeIndex$runtime_release), null);
    }

    public final TrieNode updateNodeAtIndex(int i, int i2, TrieNode trieNode) {
        Object[] objArr = trieNode.buffer;
        if (objArr.length != 2 || trieNode.nodeMap != 0) {
            Object[] objArr2 = this.buffer;
            Object[] copyOf = Arrays.copyOf(objArr2, objArr2.length);
            ResultKt.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            copyOf[i] = trieNode;
            return new TrieNode(this.dataMap, this.nodeMap, copyOf, null);
        }
        if (this.buffer.length == 1) {
            trieNode.dataMap = this.nodeMap;
            return trieNode;
        }
        int entryKeyIndex$runtime_release = entryKeyIndex$runtime_release(i2);
        Object[] objArr3 = this.buffer;
        Object obj = objArr[0];
        Object obj2 = objArr[1];
        Object[] copyOf2 = Arrays.copyOf(objArr3, objArr3.length + 1);
        ResultKt.checkNotNullExpressionValue(copyOf2, "copyOf(this, newSize)");
        MapsKt___MapsJvmKt.copyInto(copyOf2, copyOf2, i + 2, i + 1, objArr3.length);
        MapsKt___MapsJvmKt.copyInto(copyOf2, copyOf2, entryKeyIndex$runtime_release + 2, entryKeyIndex$runtime_release, i);
        copyOf2[entryKeyIndex$runtime_release] = obj;
        copyOf2[entryKeyIndex$runtime_release + 1] = obj2;
        return new TrieNode(this.dataMap ^ i2, i2 ^ this.nodeMap, copyOf2, null);
    }

    public final Object valueAtKeyIndex(int i) {
        return this.buffer[i + 1];
    }

    public final TrieNode mutableRemove(int i, Object obj, Object obj2, int i2, PersistentHashMapBuilder persistentHashMapBuilder) {
        TrieNode mutableRemove;
        ResultKt.checkNotNullParameter(persistentHashMapBuilder, "mutator");
        int indexSegment$1 = 1 << ResultKt.indexSegment$1(i, i2);
        if (hasEntryAt$runtime_release(indexSegment$1)) {
            int entryKeyIndex$runtime_release = entryKeyIndex$runtime_release(indexSegment$1);
            return (ResultKt.areEqual(obj, this.buffer[entryKeyIndex$runtime_release]) && ResultKt.areEqual(obj2, valueAtKeyIndex(entryKeyIndex$runtime_release))) ? mutableRemoveEntryAtIndex(entryKeyIndex$runtime_release, indexSegment$1, persistentHashMapBuilder) : this;
        }
        if (!hasNodeAt(indexSegment$1)) {
            return this;
        }
        int nodeIndex$runtime_release = nodeIndex$runtime_release(indexSegment$1);
        TrieNode nodeAtIndex$runtime_release = nodeAtIndex$runtime_release(nodeIndex$runtime_release);
        if (i2 == 30) {
            IntProgression step = ResultKt.step(ResultKt.until(0, nodeAtIndex$runtime_release.buffer.length));
            int i3 = step.first;
            int i4 = step.last;
            int i5 = step.step;
            if ((i5 > 0 && i3 <= i4) || (i5 < 0 && i4 <= i3)) {
                while (true) {
                    if (!ResultKt.areEqual(obj, nodeAtIndex$runtime_release.buffer[i3]) || !ResultKt.areEqual(obj2, nodeAtIndex$runtime_release.valueAtKeyIndex(i3))) {
                        if (i3 == i4) {
                            break;
                        }
                        i3 += i5;
                    } else {
                        mutableRemove = nodeAtIndex$runtime_release.mutableCollisionRemoveEntryAtIndex(i3, persistentHashMapBuilder);
                        break;
                    }
                }
            }
            mutableRemove = nodeAtIndex$runtime_release;
        } else {
            mutableRemove = nodeAtIndex$runtime_release.mutableRemove(i, obj, obj2, i2 + 5, persistentHashMapBuilder);
        }
        return mutableReplaceNode(nodeAtIndex$runtime_release, mutableRemove, nodeIndex$runtime_release, indexSegment$1, persistentHashMapBuilder.ownership);
    }
}
