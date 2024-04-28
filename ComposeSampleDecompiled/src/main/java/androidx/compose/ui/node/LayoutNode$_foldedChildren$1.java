package androidx.compose.ui.node;

import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.InputMethodManager;
import androidx.compose.material.ripple.AndroidRippleIndicationInstance;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.Stack;
import androidx.compose.runtime.collection.IdentityArraySet;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusEventModifierNode;
import androidx.compose.ui.focus.FocusInvalidationManager;
import androidx.compose.ui.focus.FocusPropertiesModifierNode;
import androidx.compose.ui.focus.FocusStateImpl;
import androidx.compose.ui.focus.FocusTargetNode;
import androidx.compose.ui.modifier.ModifierLocal;
import androidx.compose.ui.modifier.ModifierLocalManager;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.text.AndroidParagraph;
import androidx.compose.ui.text.android.LayoutHelper;
import androidx.compose.ui.text.android.TextLayout;
import androidx.compose.ui.text.input.InputMethodManagerImpl;
import androidx.compose.ui.text.input.TextInputServiceAndroid;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelStoreOwner;
import eu.malek.composesample2.DataPackage;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class LayoutNode$_foldedChildren$1 extends Lambda implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ LayoutNode$_foldedChildren$1(int i, Object obj) {
        super(0);
        this.$r8$classId = i;
        this.this$0 = obj;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Unit unit = Unit.INSTANCE;
        int i = this.$r8$classId;
        Object obj = this.this$0;
        switch (i) {
            case 0:
                m175invoke();
                return unit;
            case 1:
                return invoke();
            case 2:
                return invoke();
            case 3:
                return ((Function0) obj).invoke();
            case 4:
                m175invoke();
                return unit;
            case 5:
                m175invoke();
                return unit;
            case 6:
                m175invoke();
                return unit;
            case 7:
                m175invoke();
                return unit;
            case 8:
                m175invoke();
                return unit;
            case 9:
                m175invoke();
                return unit;
            case 10:
                m175invoke();
                return unit;
            case 11:
            default:
                return obj;
            case 12:
                AndroidParagraph androidParagraph = (AndroidParagraph) obj;
                Locale textLocale = androidParagraph.paragraphIntrinsics.textPaint.getTextLocale();
                ResultKt.checkNotNullExpressionValue(textLocale, "paragraphIntrinsics.textPaint.textLocale");
                return new Stack(textLocale, androidParagraph.layout.getText());
            case 13:
                return new LayoutHelper(((TextLayout) obj).layout);
            case 14:
                Object systemService = ((InputMethodManagerImpl) obj).view.getContext().getSystemService("input_method");
                ResultKt.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
                return (InputMethodManager) systemService;
            case 15:
                return invoke();
            case 16:
                return new BaseInputConnection(((TextInputServiceAndroid) obj).view, false);
            case 17:
                return Lifecycle.getSavedStateHandlesVM((ViewModelStoreOwner) obj);
            case 18:
                m175invoke();
                return unit;
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:122:0x01d6 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:127:0x0194 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:128:0x0194 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:131:0x01dc */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:189:0x0270 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:191:0x0276 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:193:0x0279 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:234:0x0320 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:238:0x0329 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:240:0x02c7 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:241:0x02c7 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:70:0x014b */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:75:0x0109 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:76:0x0109 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:79:0x0151 */
    /* JADX DEBUG: Multi-variable search result rejected for r9v3, resolved type: androidx.compose.ui.focus.FocusEventModifierNode */
    /* JADX DEBUG: Possible override for method kotlin.jvm.functions.Function0.invoke()Ljava/lang/Object; */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v28, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r14v7, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v10, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v4, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v18, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v20, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* renamed from: invoke, reason: collision with other method in class */
    public final void m175invoke() {
        char c;
        FocusStateImpl focusStateImpl;
        FocusStateImpl focusStateImpl2;
        int i = 0;
        int i2 = 1;
        switch (this.$r8$classId) {
            case 0:
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = ((LayoutNode) this.this$0).layoutDelegate;
                layoutNodeLayoutDelegate.measurePassDelegate.childDelegatesDirty = true;
                LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate;
                if (lookaheadPassDelegate != null) {
                    lookaheadPassDelegate.childDelegatesDirty = true;
                    return;
                }
                return;
            case 1:
            case 2:
            case 3:
            default:
                MutableState mutableState = (MutableState) this.this$0;
                DataPackage dataPackage = (DataPackage) mutableState.getValue();
                String str = ((DataPackage) mutableState.getValue()).text8798SDF + '?';
                String str2 = dataPackage.redundant1;
                String str3 = dataPackage.redundant2;
                String str4 = dataPackage.redundant3;
                String str5 = dataPackage.redundant4;
                DataPackage dataPackage2 = dataPackage.dp;
                ResultKt.checkNotNullParameter(str, "text8798SDF");
                ResultKt.checkNotNullParameter(str2, "redundant1");
                ResultKt.checkNotNullParameter(str3, "redundant2");
                ResultKt.checkNotNullParameter(str4, "redundant3");
                ResultKt.checkNotNullParameter(str5, "redundant4");
                mutableState.setValue(new DataPackage(str, str2, str3, str4, str5, dataPackage2));
                return;
            case 4:
                ((AndroidRippleIndicationInstance) this.this$0).invalidateTick$delegate.setValue(Boolean.valueOf(!((Boolean) r0.invalidateTick$delegate.getValue()).booleanValue()));
                return;
            case 5:
                break;
            case 6:
                FocusInvalidationManager focusInvalidationManager = (FocusInvalidationManager) this.this$0;
                Iterator it = focusInvalidationManager.focusPropertiesNodes.iterator();
                while (true) {
                    char c2 = 16;
                    if (it.hasNext()) {
                        Modifier.Node node = (Modifier.Node) ((FocusPropertiesModifierNode) it.next());
                        Modifier.Node node2 = node.node;
                        if (node2.isAttached) {
                            MutableVector mutableVector = null;
                            while (node2 != null) {
                                if (node2 instanceof FocusTargetNode) {
                                    focusInvalidationManager.focusTargetNodes.add((FocusTargetNode) node2);
                                } else if ((node2.kindSet & 1024) != 0 && (node2 instanceof DelegatingNode)) {
                                    Modifier.Node node3 = ((DelegatingNode) node2).delegate;
                                    int i3 = 0;
                                    mutableVector = mutableVector;
                                    while (node3 != null) {
                                        if ((node3.kindSet & 1024) != 0) {
                                            i3++;
                                            mutableVector = mutableVector;
                                            if (i3 == 1) {
                                                node2 = node3;
                                            } else {
                                                if (mutableVector == null) {
                                                    ?? obj = new Object();
                                                    obj.content = new Modifier.Node[16];
                                                    obj.size = 0;
                                                    mutableVector = obj;
                                                }
                                                if (node2 != null) {
                                                    mutableVector.add(node2);
                                                    node2 = null;
                                                }
                                                mutableVector.add(node3);
                                            }
                                        }
                                        node3 = node3.child;
                                        mutableVector = mutableVector;
                                    }
                                    if (i3 == 1) {
                                    }
                                }
                                node2 = Snake.access$pop(mutableVector);
                            }
                            Modifier.Node node4 = node.node;
                            if (node4.isAttached) {
                                ?? obj2 = new Object();
                                obj2.content = new Modifier.Node[16];
                                obj2.size = 0;
                                Modifier.Node node5 = node4.child;
                                if (node5 == null) {
                                    Snake.access$addLayoutNodeChildren(obj2, node4);
                                } else {
                                    obj2.add(node5);
                                }
                                while (obj2.isNotEmpty()) {
                                    Modifier.Node node6 = (Modifier.Node) obj2.removeAt(obj2.size - 1);
                                    if ((node6.aggregateChildKindSet & 1024) == 0) {
                                        Snake.access$addLayoutNodeChildren(obj2, node6);
                                    } else {
                                        while (true) {
                                            if (node6 == null) {
                                                break;
                                            }
                                            if ((node6.kindSet & 1024) != 0) {
                                                MutableVector mutableVector2 = null;
                                                while (node6 != null) {
                                                    if (node6 instanceof FocusTargetNode) {
                                                        focusInvalidationManager.focusTargetNodes.add((FocusTargetNode) node6);
                                                    } else if ((node6.kindSet & 1024) != 0 && (node6 instanceof DelegatingNode)) {
                                                        Modifier.Node node7 = ((DelegatingNode) node6).delegate;
                                                        int i4 = 0;
                                                        mutableVector2 = mutableVector2;
                                                        while (node7 != null) {
                                                            if ((node7.kindSet & 1024) != 0) {
                                                                i4++;
                                                                mutableVector2 = mutableVector2;
                                                                if (i4 == 1) {
                                                                    node6 = node7;
                                                                } else {
                                                                    if (mutableVector2 == null) {
                                                                        ?? obj3 = new Object();
                                                                        obj3.content = new Modifier.Node[16];
                                                                        obj3.size = 0;
                                                                        mutableVector2 = obj3;
                                                                    }
                                                                    if (node6 != null) {
                                                                        mutableVector2.add(node6);
                                                                        node6 = null;
                                                                    }
                                                                    mutableVector2.add(node7);
                                                                }
                                                            }
                                                            node7 = node7.child;
                                                            mutableVector2 = mutableVector2;
                                                        }
                                                        if (i4 == 1) {
                                                        }
                                                    }
                                                    node6 = Snake.access$pop(mutableVector2);
                                                }
                                            } else {
                                                node6 = node6.child;
                                            }
                                        }
                                    }
                                }
                            } else {
                                throw new IllegalStateException("visitChildren called on an unattached node".toString());
                            }
                        }
                    } else {
                        focusInvalidationManager.focusPropertiesNodes.clear();
                        LinkedHashSet linkedHashSet = new LinkedHashSet();
                        for (FocusEventModifierNode focusEventModifierNode : focusInvalidationManager.focusEventNodes) {
                            Modifier.Node node8 = (Modifier.Node) focusEventModifierNode;
                            Modifier.Node node9 = node8.node;
                            boolean z = node9.isAttached;
                            FocusStateImpl focusStateImpl3 = FocusStateImpl.Inactive;
                            if (!z) {
                                focusEventModifierNode.onFocusEvent(focusStateImpl3);
                                c = c2;
                            } else {
                                int i5 = i;
                                int i6 = i2;
                                FocusTargetNode focusTargetNode = null;
                                MutableVector mutableVector3 = null;
                                while (node9 != null) {
                                    if (node9 instanceof FocusTargetNode) {
                                        FocusTargetNode focusTargetNode2 = (FocusTargetNode) node9;
                                        if (focusTargetNode != null) {
                                            i5 = i2;
                                        }
                                        if (focusInvalidationManager.focusTargetNodes.contains(focusTargetNode2)) {
                                            linkedHashSet.add(focusTargetNode2);
                                            i6 = i;
                                        }
                                        focusTargetNode = focusTargetNode2;
                                    } else if ((node9.kindSet & 1024) != 0 && (node9 instanceof DelegatingNode)) {
                                        Modifier.Node node10 = ((DelegatingNode) node9).delegate;
                                        mutableVector3 = mutableVector3;
                                        while (node10 != null) {
                                            if ((node10.kindSet & 1024) != 0) {
                                                i++;
                                                mutableVector3 = mutableVector3;
                                                if (i == i2) {
                                                    node9 = node10;
                                                } else {
                                                    if (mutableVector3 == null) {
                                                        ?? obj4 = new Object();
                                                        obj4.content = new Modifier.Node[16];
                                                        obj4.size = 0;
                                                        mutableVector3 = obj4;
                                                    }
                                                    if (node9 != null) {
                                                        mutableVector3.add(node9);
                                                        node9 = null;
                                                    }
                                                    mutableVector3.add(node10);
                                                }
                                            }
                                            node10 = node10.child;
                                            i2 = 1;
                                            mutableVector3 = mutableVector3;
                                        }
                                        if (i == i2) {
                                            i = 0;
                                            mutableVector3 = mutableVector3;
                                        }
                                    }
                                    node9 = Snake.access$pop(mutableVector3);
                                    i = 0;
                                    i2 = 1;
                                    mutableVector3 = mutableVector3;
                                }
                                Modifier.Node node11 = node8.node;
                                if (node11.isAttached) {
                                    ?? obj5 = new Object();
                                    obj5.content = new Modifier.Node[16];
                                    obj5.size = 0;
                                    Modifier.Node node12 = node11.child;
                                    if (node12 == null) {
                                        Snake.access$addLayoutNodeChildren(obj5, node11);
                                    } else {
                                        obj5.add(node12);
                                    }
                                    while (obj5.isNotEmpty()) {
                                        Modifier.Node node13 = (Modifier.Node) obj5.removeAt(obj5.size - 1);
                                        if ((node13.aggregateChildKindSet & 1024) == 0) {
                                            Snake.access$addLayoutNodeChildren(obj5, node13);
                                        } else {
                                            while (node13 != null) {
                                                if ((node13.kindSet & 1024) != 0) {
                                                    MutableVector mutableVector4 = null;
                                                    while (node13 != null) {
                                                        if (node13 instanceof FocusTargetNode) {
                                                            FocusTargetNode focusTargetNode3 = (FocusTargetNode) node13;
                                                            if (focusTargetNode != null) {
                                                                i5 = 1;
                                                            }
                                                            if (focusInvalidationManager.focusTargetNodes.contains(focusTargetNode3)) {
                                                                linkedHashSet.add(focusTargetNode3);
                                                                i6 = 0;
                                                            }
                                                            focusTargetNode = focusTargetNode3;
                                                        } else if ((node13.kindSet & 1024) != 0 && (node13 instanceof DelegatingNode)) {
                                                            Modifier.Node node14 = ((DelegatingNode) node13).delegate;
                                                            int i7 = 0;
                                                            mutableVector4 = mutableVector4;
                                                            while (node14 != null) {
                                                                if ((node14.kindSet & 1024) != 0) {
                                                                    i7++;
                                                                    if (i7 == 1) {
                                                                        node13 = node14;
                                                                    } else {
                                                                        if (mutableVector4 == null) {
                                                                            ?? obj6 = new Object();
                                                                            obj6.content = new Modifier.Node[16];
                                                                            obj6.size = 0;
                                                                            mutableVector4 = obj6;
                                                                        } else {
                                                                            mutableVector4 = mutableVector4;
                                                                        }
                                                                        if (node13 != null) {
                                                                            mutableVector4.add(node13);
                                                                            node13 = null;
                                                                        }
                                                                        mutableVector4.add(node14);
                                                                        node14 = node14.child;
                                                                        mutableVector4 = mutableVector4;
                                                                    }
                                                                }
                                                                node14 = node14.child;
                                                                mutableVector4 = mutableVector4;
                                                            }
                                                            if (i7 != 1) {
                                                                node13 = Snake.access$pop(mutableVector4);
                                                            }
                                                        }
                                                        node13 = Snake.access$pop(mutableVector4);
                                                    }
                                                } else {
                                                    node13 = node13.child;
                                                }
                                            }
                                        }
                                    }
                                    c = 16;
                                    if (i6 != 0) {
                                        if (i5 != 0) {
                                            focusStateImpl = ResultKt.getFocusState(focusEventModifierNode);
                                        } else {
                                            if (focusTargetNode != null && (focusStateImpl2 = focusTargetNode.focusState) != null) {
                                                focusStateImpl3 = focusStateImpl2;
                                            }
                                            focusStateImpl = focusStateImpl3;
                                        }
                                        focusEventModifierNode.onFocusEvent(focusStateImpl);
                                    }
                                } else {
                                    throw new IllegalStateException("visitChildren called on an unattached node".toString());
                                }
                            }
                            c2 = c;
                            i = 0;
                            i2 = 1;
                        }
                        focusInvalidationManager.focusEventNodes.clear();
                        for (FocusTargetNode focusTargetNode4 : focusInvalidationManager.focusTargetNodes) {
                            if (focusTargetNode4.isAttached) {
                                FocusStateImpl focusStateImpl4 = focusTargetNode4.focusState;
                                focusTargetNode4.invalidateFocus$ui_release();
                                if (focusStateImpl4 != focusTargetNode4.focusState || linkedHashSet.contains(focusTargetNode4)) {
                                    ResultKt.refreshFocusEventNodes(focusTargetNode4);
                                }
                            }
                        }
                        focusInvalidationManager.focusTargetNodes.clear();
                        linkedHashSet.clear();
                        if (focusInvalidationManager.focusPropertiesNodes.isEmpty()) {
                            if (focusInvalidationManager.focusEventNodes.isEmpty()) {
                                if (!focusInvalidationManager.focusTargetNodes.isEmpty()) {
                                    throw new IllegalStateException("Unprocessed FocusTarget nodes".toString());
                                }
                                return;
                            }
                            throw new IllegalStateException("Unprocessed FocusEvent nodes".toString());
                        }
                        throw new IllegalStateException("Unprocessed FocusProperties nodes".toString());
                    }
                }
                break;
            case 7:
                ((FocusTargetNode) this.this$0).fetchFocusProperties$ui_release();
                return;
            case 8:
                ModifierLocalManager modifierLocalManager = (ModifierLocalManager) this.this$0;
                modifierLocalManager.invalidated = false;
                HashSet hashSet = new HashSet();
                MutableVector mutableVector5 = modifierLocalManager.removed;
                int i8 = mutableVector5.size;
                MutableVector mutableVector6 = modifierLocalManager.removedLocal;
                if (i8 > 0) {
                    Object[] objArr = mutableVector5.content;
                    int i9 = 0;
                    do {
                        LayoutNode layoutNode = (LayoutNode) objArr[i9];
                        ModifierLocal modifierLocal = (ModifierLocal) mutableVector6.content[i9];
                        Modifier.Node node15 = layoutNode.nodes.head;
                        if (node15.isAttached) {
                            ModifierLocalManager.invalidateConsumersOfNodeForKey(node15, modifierLocal, hashSet);
                        }
                        i9++;
                    } while (i9 < i8);
                }
                mutableVector5.clear();
                mutableVector6.clear();
                MutableVector mutableVector7 = modifierLocalManager.inserted;
                int i10 = mutableVector7.size;
                MutableVector mutableVector8 = modifierLocalManager.insertedLocal;
                if (i10 > 0) {
                    Object[] objArr2 = mutableVector7.content;
                    do {
                        BackwardsCompatNode backwardsCompatNode = (BackwardsCompatNode) objArr2[i];
                        ModifierLocal modifierLocal2 = (ModifierLocal) mutableVector8.content[i];
                        if (backwardsCompatNode.isAttached) {
                            ModifierLocalManager.invalidateConsumersOfNodeForKey(backwardsCompatNode, modifierLocal2, hashSet);
                        }
                        i++;
                    } while (i < i10);
                }
                mutableVector7.clear();
                mutableVector8.clear();
                Iterator it2 = hashSet.iterator();
                while (it2.hasNext()) {
                    ((BackwardsCompatNode) it2.next()).updateModifierLocalConsumer();
                }
                return;
            case 9:
                NodeCoordinator nodeCoordinator = ((NodeCoordinator) this.this$0).wrappedBy;
                if (nodeCoordinator != null) {
                    nodeCoordinator.invalidateLayer();
                    return;
                }
                return;
            case 10:
                ((Function1) this.this$0).invoke(NodeCoordinator.graphicsLayerScope);
                return;
        }
        do {
            SnapshotStateObserver snapshotStateObserver = (SnapshotStateObserver) this.this$0;
            synchronized (snapshotStateObserver.observedScopeMaps) {
                if (!snapshotStateObserver.sendingNotifications) {
                    snapshotStateObserver.sendingNotifications = true;
                    try {
                        MutableVector mutableVector9 = snapshotStateObserver.observedScopeMaps;
                        int i11 = mutableVector9.size;
                        if (i11 > 0) {
                            Object[] objArr3 = mutableVector9.content;
                            int i12 = 0;
                            do {
                                SnapshotStateObserver.ObservedScopeMap observedScopeMap = (SnapshotStateObserver.ObservedScopeMap) objArr3[i12];
                                IdentityArraySet identityArraySet = observedScopeMap.invalidated;
                                Object[] objArr4 = identityArraySet.values;
                                int i13 = identityArraySet.size;
                                for (int i14 = 0; i14 < i13; i14++) {
                                    Object obj7 = objArr4[i14];
                                    ResultKt.checkNotNull(obj7, "null cannot be cast to non-null type T of androidx.compose.runtime.collection.IdentityArraySet");
                                    observedScopeMap.onChanged.invoke(obj7);
                                }
                                identityArraySet.clear();
                                i12++;
                            } while (i12 < i11);
                        }
                        snapshotStateObserver.sendingNotifications = false;
                    } catch (Throwable th) {
                        snapshotStateObserver.sendingNotifications = false;
                        throw th;
                    }
                }
            }
        } while (SnapshotStateObserver.access$drainChanges((SnapshotStateObserver) this.this$0));
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:100:0x0111 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:101:0x0111 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:104:0x015d */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:37:0x00c8 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:42:0x0073 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:43:0x0073 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:46:0x00ce */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:95:0x0157 */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x011f, code lost:
    
        if (r0.fetchFocusProperties$ui_release().canFocus != false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x00ef, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0086, code lost:
    
        if (r0.fetchFocusProperties$ui_release().canFocus != false) goto L20;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0088, code lost:
    
        r0 = _COROUTINE._BOUNDARY.requestFocus(r0);
     */
    /* JADX WARN: Type inference failed for: r2v6, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v15, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v21, types: [androidx.compose.runtime.collection.MutableVector, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Boolean invoke() {
        /*
            r12 = this;
            int r0 = r12.$r8$classId
            r1 = 0
            java.lang.Object r2 = r12.this$0
            r3 = 1
            switch(r0) {
                case 1: goto L176;
                case 2: goto L68;
                default: goto L9;
            }
        L9:
            androidx.compose.ui.text.input.PlatformTextInputPluginRegistryImpl$AdapterWithRefCount r2 = (androidx.compose.ui.text.input.PlatformTextInputPluginRegistryImpl.AdapterWithRefCount) r2
            androidx.compose.runtime.ParcelableSnapshotMutableIntState r0 = r2.refCount$delegate
            androidx.compose.runtime.SnapshotMutableIntStateImpl$IntStateStateRecord r4 = r0.next
            androidx.compose.runtime.snapshots.StateRecord r0 = androidx.compose.runtime.snapshots.SnapshotKt.readable(r4, r0)
            androidx.compose.runtime.SnapshotMutableIntStateImpl$IntStateStateRecord r0 = (androidx.compose.runtime.SnapshotMutableIntStateImpl.IntStateStateRecord) r0
            int r0 = r0.value
            int r0 = r0 + (-1)
            androidx.compose.runtime.ParcelableSnapshotMutableIntState r4 = r2.refCount$delegate
            r4.setIntValue(r0)
            androidx.compose.runtime.SnapshotMutableIntStateImpl$IntStateStateRecord r0 = r4.next
            androidx.compose.runtime.snapshots.StateRecord r0 = androidx.compose.runtime.snapshots.SnapshotKt.readable(r0, r4)
            androidx.compose.runtime.SnapshotMutableIntStateImpl$IntStateStateRecord r0 = (androidx.compose.runtime.SnapshotMutableIntStateImpl.IntStateStateRecord) r0
            int r0 = r0.value
            if (r0 < 0) goto L41
            androidx.compose.runtime.SnapshotMutableIntStateImpl$IntStateStateRecord r0 = r4.next
            androidx.compose.runtime.snapshots.StateRecord r0 = androidx.compose.runtime.snapshots.SnapshotKt.readable(r0, r4)
            androidx.compose.runtime.SnapshotMutableIntStateImpl$IntStateStateRecord r0 = (androidx.compose.runtime.SnapshotMutableIntStateImpl.IntStateStateRecord) r0
            int r0 = r0.value
            if (r0 != 0) goto L3c
            androidx.compose.ui.text.input.PlatformTextInputPluginRegistryImpl r0 = androidx.compose.ui.text.input.PlatformTextInputPluginRegistryImpl.this
            r0.getClass()
            r1 = r3
        L3c:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            return r0
        L41:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "AdapterWithRefCount.decrementRefCount called too many times (refCount="
            r0.<init>(r1)
            androidx.compose.runtime.SnapshotMutableIntStateImpl$IntStateStateRecord r1 = r4.next
            androidx.compose.runtime.snapshots.StateRecord r1 = androidx.compose.runtime.snapshots.SnapshotKt.readable(r1, r4)
            androidx.compose.runtime.SnapshotMutableIntStateImpl$IntStateStateRecord r1 = (androidx.compose.runtime.SnapshotMutableIntStateImpl.IntStateStateRecord) r1
            int r1 = r1.value
            r0.append(r1)
            r1 = 41
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L68:
            androidx.compose.foundation.FocusableSemanticsNode r2 = (androidx.compose.foundation.FocusableSemanticsNode) r2
            java.lang.String r0 = "<this>"
            kotlin.ResultKt.checkNotNullParameter(r2, r0)
            androidx.compose.ui.Modifier$Node r0 = r2.node
            r4 = 0
            r5 = r4
        L73:
            androidx.compose.ui.focus.FocusProperties$exit$1 r6 = androidx.compose.ui.focus.FocusProperties$exit$1.INSTANCE$5
            r7 = 7
            r8 = 16
            if (r0 == 0) goto Ld3
            boolean r9 = r0 instanceof androidx.compose.ui.focus.FocusTargetNode
            if (r9 == 0) goto L94
            androidx.compose.ui.focus.FocusTargetNode r0 = (androidx.compose.ui.focus.FocusTargetNode) r0
            androidx.compose.ui.focus.FocusPropertiesImpl r1 = r0.fetchFocusProperties$ui_release()
            boolean r1 = r1.canFocus
            if (r1 == 0) goto L8f
        L88:
            boolean r0 = _COROUTINE._BOUNDARY.requestFocus(r0)
        L8c:
            r1 = r0
            goto L165
        L8f:
            boolean r0 = _COROUTINE._BOUNDARY.m10findChildCorrespondingToFocusEnterOMvw8(r0, r7, r6)
            goto L8c
        L94:
            int r6 = r0.kindSet
            r6 = r6 & 1024(0x400, float:1.435E-42)
            if (r6 == 0) goto Lce
            boolean r6 = r0 instanceof androidx.compose.ui.node.DelegatingNode
            if (r6 == 0) goto Lce
            r6 = r0
            androidx.compose.ui.node.DelegatingNode r6 = (androidx.compose.ui.node.DelegatingNode) r6
            androidx.compose.ui.Modifier$Node r6 = r6.delegate
            r7 = r1
        La4:
            if (r6 == 0) goto Lcb
            int r9 = r6.kindSet
            r9 = r9 & 1024(0x400, float:1.435E-42)
            if (r9 == 0) goto Lc8
            int r7 = r7 + 1
            if (r7 != r3) goto Lb2
            r0 = r6
            goto Lc8
        Lb2:
            if (r5 != 0) goto Lbf
            androidx.compose.runtime.collection.MutableVector r5 = new androidx.compose.runtime.collection.MutableVector
            androidx.compose.ui.Modifier$Node[] r9 = new androidx.compose.ui.Modifier.Node[r8]
            r5.<init>()
            r5.content = r9
            r5.size = r1
        Lbf:
            if (r0 == 0) goto Lc5
            r5.add(r0)
            r0 = r4
        Lc5:
            r5.add(r6)
        Lc8:
            androidx.compose.ui.Modifier$Node r6 = r6.child
            goto La4
        Lcb:
            if (r7 != r3) goto Lce
            goto L73
        Lce:
            androidx.compose.ui.Modifier$Node r0 = androidx.compose.ui.node.Snake.access$pop(r5)
            goto L73
        Ld3:
            androidx.compose.ui.Modifier$Node r0 = r2.node
            boolean r2 = r0.isAttached
            if (r2 == 0) goto L16a
            androidx.compose.runtime.collection.MutableVector r2 = new androidx.compose.runtime.collection.MutableVector
            androidx.compose.ui.Modifier$Node[] r5 = new androidx.compose.ui.Modifier.Node[r8]
            r2.<init>()
            r2.content = r5
            r2.size = r1
            androidx.compose.ui.Modifier$Node r5 = r0.child
            if (r5 != 0) goto Lec
            androidx.compose.ui.node.Snake.access$addLayoutNodeChildren(r2, r0)
            goto Lef
        Lec:
            r2.add(r5)
        Lef:
            boolean r0 = r2.isNotEmpty()
            if (r0 == 0) goto L165
            int r0 = r2.size
            int r0 = r0 - r3
            java.lang.Object r0 = r2.removeAt(r0)
            androidx.compose.ui.Modifier$Node r0 = (androidx.compose.ui.Modifier.Node) r0
            int r5 = r0.aggregateChildKindSet
            r5 = r5 & 1024(0x400, float:1.435E-42)
            if (r5 != 0) goto L108
            androidx.compose.ui.node.Snake.access$addLayoutNodeChildren(r2, r0)
            goto Lef
        L108:
            if (r0 == 0) goto Lef
            int r5 = r0.kindSet
            r5 = r5 & 1024(0x400, float:1.435E-42)
            if (r5 == 0) goto L162
            r5 = r4
        L111:
            if (r0 == 0) goto Lef
            boolean r9 = r0 instanceof androidx.compose.ui.focus.FocusTargetNode
            if (r9 == 0) goto L123
            androidx.compose.ui.focus.FocusTargetNode r0 = (androidx.compose.ui.focus.FocusTargetNode) r0
            androidx.compose.ui.focus.FocusPropertiesImpl r1 = r0.fetchFocusProperties$ui_release()
            boolean r1 = r1.canFocus
            if (r1 == 0) goto L8f
            goto L88
        L123:
            int r9 = r0.kindSet
            r9 = r9 & 1024(0x400, float:1.435E-42)
            if (r9 == 0) goto L15d
            boolean r9 = r0 instanceof androidx.compose.ui.node.DelegatingNode
            if (r9 == 0) goto L15d
            r9 = r0
            androidx.compose.ui.node.DelegatingNode r9 = (androidx.compose.ui.node.DelegatingNode) r9
            androidx.compose.ui.Modifier$Node r9 = r9.delegate
            r10 = r1
        L133:
            if (r9 == 0) goto L15a
            int r11 = r9.kindSet
            r11 = r11 & 1024(0x400, float:1.435E-42)
            if (r11 == 0) goto L157
            int r10 = r10 + 1
            if (r10 != r3) goto L141
            r0 = r9
            goto L157
        L141:
            if (r5 != 0) goto L14e
            androidx.compose.runtime.collection.MutableVector r5 = new androidx.compose.runtime.collection.MutableVector
            androidx.compose.ui.Modifier$Node[] r11 = new androidx.compose.ui.Modifier.Node[r8]
            r5.<init>()
            r5.content = r11
            r5.size = r1
        L14e:
            if (r0 == 0) goto L154
            r5.add(r0)
            r0 = r4
        L154:
            r5.add(r9)
        L157:
            androidx.compose.ui.Modifier$Node r9 = r9.child
            goto L133
        L15a:
            if (r10 != r3) goto L15d
            goto L111
        L15d:
            androidx.compose.ui.Modifier$Node r0 = androidx.compose.ui.node.Snake.access$pop(r5)
            goto L111
        L162:
            androidx.compose.ui.Modifier$Node r0 = r0.child
            goto L108
        L165:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            return r0
        L16a:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "visitChildren called on an unattached node"
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L176:
            androidx.compose.foundation.AbstractClickablePointerInputNode r2 = (androidx.compose.foundation.AbstractClickablePointerInputNode) r2
            androidx.compose.ui.modifier.ProvidableModifierLocal r0 = androidx.compose.foundation.gestures.ScrollableKt.ModifierLocalScrollableContainer
            java.lang.Object r0 = r2.getCurrent(r0)
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 != 0) goto L1a8
            int r0 = androidx.compose.foundation.Clickable_androidKt.$r8$clinit
            androidx.compose.runtime.StaticProvidableCompositionLocal r0 = androidx.compose.ui.platform.AndroidCompositionLocals_androidKt.LocalView
            java.lang.Object r0 = androidx.compose.ui.node.Snake.currentValueOf(r2, r0)
            android.view.View r0 = (android.view.View) r0
            android.view.ViewParent r0 = r0.getParent()
        L194:
            if (r0 == 0) goto L1a9
            boolean r2 = r0 instanceof android.view.ViewGroup
            if (r2 == 0) goto L1a9
            android.view.ViewGroup r0 = (android.view.ViewGroup) r0
            boolean r2 = r0.shouldDelayChildPressedState()
            if (r2 == 0) goto L1a3
            goto L1a8
        L1a3:
            android.view.ViewParent r0 = r0.getParent()
            goto L194
        L1a8:
            r1 = r3
        L1a9:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.LayoutNode$_foldedChildren$1.invoke():java.lang.Boolean");
    }
}
