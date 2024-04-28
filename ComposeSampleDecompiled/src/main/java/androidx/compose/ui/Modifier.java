package androidx.compose.ui;

import androidx.compose.ui.graphics.BlockGraphicsLayerModifier;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.ObserverNodeOwnerScope;
import androidx.compose.ui.node.Snake;
import androidx.compose.ui.platform.AndroidComposeView;
import kotlin.ResultKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobImpl;
import kotlinx.coroutines.flow.internal.ChildCancelledException;
import kotlinx.coroutines.internal.ContextScope;

/* loaded from: classes.dex */
public interface Modifier {

    /* loaded from: classes.dex */
    public final class Companion implements Modifier {
        public static final /* synthetic */ Companion $$INSTANCE = new Object();

        @Override // androidx.compose.ui.Modifier
        public final boolean all(Function1 function1) {
            return true;
        }

        @Override // androidx.compose.ui.Modifier
        public final Object foldIn(Object obj, Function2 function2) {
            return obj;
        }

        @Override // androidx.compose.ui.Modifier
        public final Modifier then(Modifier modifier) {
            ResultKt.checkNotNullParameter(modifier, "other");
            return modifier;
        }

        public final String toString() {
            return "Modifier";
        }
    }

    /* loaded from: classes.dex */
    public interface Element extends Modifier {
        @Override // androidx.compose.ui.Modifier
        default boolean all(Function1 function1) {
            return ((Boolean) function1.invoke(this)).booleanValue();
        }

        @Override // androidx.compose.ui.Modifier
        default Object foldIn(Object obj, Function2 function2) {
            return function2.invoke(obj, this);
        }
    }

    /* loaded from: classes.dex */
    public abstract class Node implements DelegatableNode {
        public Node child;
        public NodeCoordinator coordinator;
        public boolean insertedNodeAwaitingAttachForInvalidation;
        public boolean isAttached;
        public int kindSet;
        public boolean onAttachRunExpected;
        public boolean onDetachRunExpected;
        public ObserverNodeOwnerScope ownerScope;
        public Node parent;
        public ContextScope scope;
        public boolean updatedNodeAwaitingAttachForInvalidation;
        public Node node = this;
        public int aggregateChildKindSet = -1;

        public final CoroutineScope getCoroutineScope() {
            ContextScope contextScope = this.scope;
            if (contextScope != null) {
                return contextScope;
            }
            ContextScope CoroutineScope = ResultKt.CoroutineScope(((AndroidComposeView) Snake.requireOwner(this)).getCoroutineContext().plus(new JobImpl((Job) ((AndroidComposeView) Snake.requireOwner(this)).getCoroutineContext().get(Job.Key.$$INSTANCE))));
            this.scope = CoroutineScope;
            return CoroutineScope;
        }

        public boolean getShouldAutoInvalidate() {
            return !(this instanceof BlockGraphicsLayerModifier);
        }

        public void markAsAttached$ui_release() {
            if (!(!this.isAttached)) {
                throw new IllegalStateException("node attached multiple times".toString());
            }
            if (this.coordinator == null) {
                throw new IllegalStateException("attach invoked on a node without a coordinator".toString());
            }
            this.isAttached = true;
            this.onAttachRunExpected = true;
        }

        public void markAsDetached$ui_release() {
            if (!this.isAttached) {
                throw new IllegalStateException("Cannot detach a node that is not attached".toString());
            }
            if (!(!this.onAttachRunExpected)) {
                throw new IllegalStateException("Must run runAttachLifecycle() before markAsDetached()".toString());
            }
            if (!(!this.onDetachRunExpected)) {
                throw new IllegalStateException("Must run runDetachLifecycle() before markAsDetached()".toString());
            }
            this.isAttached = false;
            ContextScope contextScope = this.scope;
            if (contextScope != null) {
                ChildCancelledException childCancelledException = new ChildCancelledException(3);
                Job job = (Job) contextScope.coroutineContext.get(Job.Key.$$INSTANCE);
                if (job != null) {
                    job.cancel(childCancelledException);
                    this.scope = null;
                } else {
                    throw new IllegalStateException(("Scope cannot be cancelled because it does not have a job: " + contextScope).toString());
                }
            }
        }

        public void onAttach() {
        }

        public void onDetach() {
        }

        public void onReset() {
        }

        public void reset$ui_release() {
            if (!this.isAttached) {
                throw new IllegalStateException("Check failed.".toString());
            }
            onReset();
        }

        public void runAttachLifecycle$ui_release() {
            if (!this.isAttached) {
                throw new IllegalStateException("Must run markAsAttached() prior to runAttachLifecycle".toString());
            }
            if (!this.onAttachRunExpected) {
                throw new IllegalStateException("Must run runAttachLifecycle() only once after markAsAttached()".toString());
            }
            this.onAttachRunExpected = false;
            onAttach();
            this.onDetachRunExpected = true;
        }

        public void runDetachLifecycle$ui_release() {
            if (!this.isAttached) {
                throw new IllegalStateException("node detached multiple times".toString());
            }
            if (this.coordinator == null) {
                throw new IllegalStateException("detach invoked on a node without a coordinator".toString());
            }
            if (!this.onDetachRunExpected) {
                throw new IllegalStateException("Must run runDetachLifecycle() once after runAttachLifecycle() and before markAsDetached()".toString());
            }
            this.onDetachRunExpected = false;
            onDetach();
        }

        public void updateCoordinator$ui_release(NodeCoordinator nodeCoordinator) {
            this.coordinator = nodeCoordinator;
        }
    }

    boolean all(Function1 function1);

    Object foldIn(Object obj, Function2 function2);

    default Modifier then(Modifier modifier) {
        ResultKt.checkNotNullParameter(modifier, "other");
        return modifier == Companion.$$INSTANCE ? this : new CombinedModifier(this, modifier);
    }
}
