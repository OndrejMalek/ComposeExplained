package androidx.compose.runtime;

import _COROUTINE._BOUNDARY;
import androidx.compose.animation.core.AnimationEndReason$EnumUnboxingLocalUtility;
import androidx.compose.runtime.collection.IdentityArrayIntMap;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.SnapshotWeakSet;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.Snake;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt___MapsJvmKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlinx.coroutines.flow.SharingConfig;

/* loaded from: classes.dex */
public final class RecomposeScopeImpl$end$1$2 extends Lambda implements Function1 {
    public final /* synthetic */ Object $instances;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ int $token;
    public final /* synthetic */ Object this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ RecomposeScopeImpl$end$1$2(int i, int i2, Object obj, Object obj2) {
        super(1);
        this.$r8$classId = i2;
        this.this$0 = obj;
        this.$token = i;
        this.$instances = obj2;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:40:0x0086 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:45:0x0048 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:46:0x0048 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:49:0x008c */
    /* JADX WARN: Type inference failed for: r10v8, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Composition composition;
        int i;
        boolean z;
        SnapshotWeakSet snapshotWeakSet;
        Modifier.Node node;
        NodeChain nodeChain;
        Unit unit = Unit.INSTANCE;
        int i2 = this.$r8$classId;
        boolean z2 = false;
        Object obj2 = this.$instances;
        int i3 = this.$token;
        Object obj3 = this.this$0;
        boolean z3 = true;
        switch (i2) {
            case 0:
                Composition composition2 = (Composition) obj;
                ResultKt.checkNotNullParameter(composition2, "composition");
                RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) obj3;
                if (recomposeScopeImpl.currentToken == i3) {
                    IdentityArrayIntMap identityArrayIntMap = (IdentityArrayIntMap) obj2;
                    if (ResultKt.areEqual(identityArrayIntMap, recomposeScopeImpl.trackedInstances) && (composition2 instanceof CompositionImpl)) {
                        Object[] objArr = identityArrayIntMap.keys;
                        int[] iArr = identityArrayIntMap.values;
                        int i4 = identityArrayIntMap.size;
                        int i5 = 0;
                        int i6 = 0;
                        while (i5 < i4) {
                            Object obj4 = objArr[i5];
                            ResultKt.checkNotNull(obj4, "null cannot be cast to non-null type kotlin.Any");
                            int i7 = iArr[i5];
                            boolean z4 = i7 != i3 ? z3 : z2;
                            if (z4) {
                                CompositionImpl compositionImpl = (CompositionImpl) composition2;
                                SharingConfig sharingConfig = compositionImpl.observations;
                                sharingConfig.remove(obj4, recomposeScopeImpl);
                                DerivedSnapshotState derivedSnapshotState = obj4 instanceof DerivedSnapshotState ? (DerivedSnapshotState) obj4 : null;
                                if (derivedSnapshotState != null) {
                                    if (!sharingConfig.contains(derivedSnapshotState)) {
                                        compositionImpl.derivedStates.removeScope(derivedSnapshotState);
                                    }
                                    SnapshotWeakSet snapshotWeakSet2 = recomposeScopeImpl.trackedDependencies;
                                    if (snapshotWeakSet2 != null) {
                                        int find = snapshotWeakSet2.find(derivedSnapshotState);
                                        if (find >= 0) {
                                            Object[] objArr2 = snapshotWeakSet2.values;
                                            Object obj5 = objArr2[find];
                                            int i8 = snapshotWeakSet2.size;
                                            composition = composition2;
                                            Object[] objArr3 = (Object[]) snapshotWeakSet2.hashes;
                                            i = i3;
                                            int i9 = find + 1;
                                            MapsKt___MapsJvmKt.copyInto(objArr3, objArr3, find, i9, i8);
                                            MapsKt___MapsJvmKt.copyInto(objArr2, objArr2, find, i9, i8);
                                            z = true;
                                            int i10 = i8 - 1;
                                            snapshotWeakSet = null;
                                            objArr3[i10] = null;
                                            objArr2[i10] = null;
                                            snapshotWeakSet2.size = i10;
                                        } else {
                                            composition = composition2;
                                            i = i3;
                                            z = true;
                                            snapshotWeakSet = null;
                                        }
                                        if (snapshotWeakSet2.size == 0) {
                                            recomposeScopeImpl.trackedDependencies = snapshotWeakSet;
                                        }
                                    }
                                }
                                composition = composition2;
                                i = i3;
                                z = true;
                            } else {
                                composition = composition2;
                                i = i3;
                                z = z3;
                            }
                            if (!z4) {
                                if (i6 != i5) {
                                    objArr[i6] = obj4;
                                    iArr[i6] = i7;
                                }
                                i6++;
                            }
                            i5++;
                            composition2 = composition;
                            z3 = z;
                            i3 = i;
                            z2 = false;
                        }
                        for (int i11 = i6; i11 < i4; i11++) {
                            objArr[i11] = null;
                        }
                        identityArrayIntMap.size = i6;
                        if (i6 == 0) {
                            recomposeScopeImpl.trackedInstances = null;
                        }
                    }
                }
                return unit;
            case 1:
                ResultKt.checkNotNullParameter(obj, "it");
                if (obj == ((DerivedSnapshotState) obj3)) {
                    throw new IllegalStateException("A derived state calculation cannot read itself".toString());
                }
                if (obj instanceof StateObject) {
                    Object obj6 = SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel.get();
                    ResultKt.checkNotNull(obj6);
                    SnapshotWeakSet snapshotWeakSet3 = (SnapshotWeakSet) obj2;
                    int intValue = ((Number) obj6).intValue() - i3;
                    Integer num = (Integer) snapshotWeakSet3.get(obj);
                    snapshotWeakSet3.set(obj, Integer.valueOf(Math.min(intValue, num != null ? num.intValue() : Integer.MAX_VALUE)));
                }
                return unit;
            default:
                FocusTargetNode focusTargetNode = (FocusTargetNode) obj;
                ResultKt.checkNotNullParameter(focusTargetNode, "destination");
                if (ResultKt.areEqual(focusTargetNode, (FocusTargetNode) obj3)) {
                    return Boolean.FALSE;
                }
                Modifier.Node node2 = focusTargetNode.node;
                if (!node2.isAttached) {
                    throw new IllegalStateException("visitAncestors called on an unattached node".toString());
                }
                Modifier.Node node3 = node2.parent;
                LayoutNode requireLayoutNode = Snake.requireLayoutNode(focusTargetNode);
                while (true) {
                    if (requireLayoutNode != null) {
                        if ((requireLayoutNode.nodes.head.aggregateChildKindSet & 1024) != 0) {
                            while (node3 != null) {
                                if ((node3.kindSet & 1024) != 0) {
                                    Modifier.Node node4 = node3;
                                    MutableVector mutableVector = null;
                                    while (node4 != null) {
                                        if (node4 instanceof FocusTargetNode) {
                                            node = node4;
                                        } else {
                                            if ((node4.kindSet & 1024) != 0 && (node4 instanceof DelegatingNode)) {
                                                Modifier.Node node5 = ((DelegatingNode) node4).delegate;
                                                int i12 = 0;
                                                mutableVector = mutableVector;
                                                while (node5 != null) {
                                                    if ((node5.kindSet & 1024) != 0) {
                                                        i12++;
                                                        mutableVector = mutableVector;
                                                        if (i12 == 1) {
                                                            node4 = node5;
                                                        } else {
                                                            if (mutableVector == null) {
                                                                ?? obj7 = new Object();
                                                                obj7.content = new Modifier.Node[16];
                                                                obj7.size = 0;
                                                                mutableVector = obj7;
                                                            }
                                                            if (node4 != null) {
                                                                mutableVector.add(node4);
                                                                node4 = null;
                                                            }
                                                            mutableVector.add(node5);
                                                        }
                                                    }
                                                    node5 = node5.child;
                                                    mutableVector = mutableVector;
                                                }
                                                if (i12 == 1) {
                                                }
                                            }
                                            node4 = Snake.access$pop(mutableVector);
                                        }
                                    }
                                }
                                node3 = node3.parent;
                            }
                        }
                        requireLayoutNode = requireLayoutNode.getParent$ui_release();
                        node3 = (requireLayoutNode == null || (nodeChain = requireLayoutNode.nodes) == null) ? null : nodeChain.tail;
                    } else {
                        node = null;
                    }
                }
                if (node == null) {
                    throw new IllegalStateException("Focus search landed at the root.".toString());
                }
                int ordinal = AnimationEndReason$EnumUnboxingLocalUtility.ordinal(_BOUNDARY.m17performCustomRequestFocusMxy_nc0(focusTargetNode, i3));
                if (ordinal != 0) {
                    if (ordinal != 1) {
                        if (ordinal != 2) {
                            if (ordinal != 3) {
                                throw new RuntimeException();
                            }
                        }
                    }
                    ((Ref$BooleanRef) obj2).element = true;
                } else {
                    z3 = _BOUNDARY.performRequestFocus(focusTargetNode);
                }
                return Boolean.valueOf(z3);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RecomposeScopeImpl$end$1$2(DerivedSnapshotState derivedSnapshotState, SnapshotWeakSet snapshotWeakSet, int i) {
        super(1);
        this.$r8$classId = 1;
        this.this$0 = derivedSnapshotState;
        this.$instances = snapshotWeakSet;
        this.$token = i;
    }
}
