package kotlinx.coroutines.flow;

import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.interaction.FocusInteraction$Focus;
import androidx.compose.foundation.interaction.FocusInteraction$Unfocus;
import androidx.compose.foundation.interaction.HoverInteraction$Enter;
import androidx.compose.foundation.interaction.HoverInteraction$Exit;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.PressInteraction$Cancel;
import androidx.compose.foundation.interaction.PressInteraction$Press;
import androidx.compose.foundation.interaction.PressInteraction$Release;
import androidx.compose.material.ripple.RippleAlpha;
import androidx.compose.material.ripple.RippleIndicationInstance;
import androidx.compose.material.ripple.RippleKt;
import androidx.compose.material.ripple.StateLayer$handleInteraction$1;
import androidx.compose.material.ripple.StateLayer$handleInteraction$2;
import androidx.compose.runtime.State;
import androidx.emoji2.text.EmojiProcessor;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes.dex */
public final class StartedLazily$command$1 extends SuspendLambda implements Function2 {
    public final /* synthetic */ StateFlow $subscriptionCount;
    public /* synthetic */ Object L$0;
    public int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StartedLazily$command$1(StateFlow stateFlow, Continuation continuation) {
        super(2, continuation);
        this.$subscriptionCount = stateFlow;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        StartedLazily$command$1 startedLazily$command$1 = new StartedLazily$command$1(this.$subscriptionCount, continuation);
        startedLazily$command$1.L$0 = obj;
        return startedLazily$command$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((StartedLazily$command$1) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(new Object(), 0, (FlowCollector) this.L$0);
            this.label = 1;
            if (this.$subscriptionCount.collect(anonymousClass1, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        throw new RuntimeException();
    }

    /* renamed from: kotlinx.coroutines.flow.StartedLazily$command$1$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements FlowCollector {
        public final /* synthetic */ Object $$this$flow;
        public final /* synthetic */ int $r8$classId;
        public final /* synthetic */ Object $started;

        public /* synthetic */ AnonymousClass1(Object obj, int i, Object obj2) {
            this.$r8$classId = i;
            this.$started = obj;
            this.$$this$flow = obj2;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object obj, Continuation continuation) {
            float f;
            switch (this.$r8$classId) {
                case 0:
                    return emit(((Number) obj).intValue(), continuation);
                default:
                    Interaction interaction = (Interaction) obj;
                    boolean z = interaction instanceof PressInteraction$Press;
                    Object obj2 = this.$$this$flow;
                    Object obj3 = this.$started;
                    if (z) {
                        ((RippleIndicationInstance) obj3).addRipple((PressInteraction$Press) interaction, (CoroutineScope) obj2);
                    } else if (interaction instanceof PressInteraction$Release) {
                        ((RippleIndicationInstance) obj3).removeRipple(((PressInteraction$Release) interaction).press);
                    } else if (interaction instanceof PressInteraction$Cancel) {
                        ((RippleIndicationInstance) obj3).removeRipple(((PressInteraction$Cancel) interaction).press);
                    } else {
                        RippleIndicationInstance rippleIndicationInstance = (RippleIndicationInstance) obj3;
                        CoroutineScope coroutineScope = (CoroutineScope) obj2;
                        rippleIndicationInstance.getClass();
                        ResultKt.checkNotNullParameter(interaction, "interaction");
                        ResultKt.checkNotNullParameter(coroutineScope, "scope");
                        EmojiProcessor emojiProcessor = rippleIndicationInstance.stateLayer;
                        emojiProcessor.getClass();
                        boolean z2 = interaction instanceof HoverInteraction$Enter;
                        Object obj4 = emojiProcessor.mGlyphChecker;
                        if (z2) {
                            ((List) obj4).add(interaction);
                        } else if (interaction instanceof HoverInteraction$Exit) {
                            ((List) obj4).remove(((HoverInteraction$Exit) interaction).enter);
                        } else if (interaction instanceof FocusInteraction$Focus) {
                            ((List) obj4).add(interaction);
                        } else if (interaction instanceof FocusInteraction$Unfocus) {
                            ((List) obj4).remove(((FocusInteraction$Unfocus) interaction).focus);
                        }
                        Interaction interaction2 = (Interaction) CollectionsKt___CollectionsKt.lastOrNull((List) obj4);
                        if (!ResultKt.areEqual((Interaction) emojiProcessor.mEmojiAsDefaultStyleExceptions, interaction2)) {
                            if (interaction2 != null) {
                                Object obj5 = emojiProcessor.mSpanFactory;
                                if (z2) {
                                    f = ((RippleAlpha) ((State) obj5).getValue()).hoveredAlpha;
                                } else {
                                    f = interaction instanceof FocusInteraction$Focus ? ((RippleAlpha) ((State) obj5).getValue()).focusedAlpha : 0.0f;
                                }
                                TweenSpec tweenSpec = RippleKt.DefaultTweenSpec;
                                boolean z3 = interaction2 instanceof HoverInteraction$Enter;
                                TweenSpec tweenSpec2 = RippleKt.DefaultTweenSpec;
                                if (!z3 && (interaction2 instanceof FocusInteraction$Focus)) {
                                    tweenSpec2 = new TweenSpec(45, 0, EasingKt.LinearEasing);
                                }
                                ResultKt.launch$default(coroutineScope, null, 0, new StateLayer$handleInteraction$1(emojiProcessor, f, tweenSpec2, null), 3);
                            } else {
                                Interaction interaction3 = (Interaction) emojiProcessor.mEmojiAsDefaultStyleExceptions;
                                TweenSpec tweenSpec3 = RippleKt.DefaultTweenSpec;
                                if (!(interaction3 instanceof HoverInteraction$Enter)) {
                                    boolean z4 = interaction3 instanceof FocusInteraction$Focus;
                                }
                                ResultKt.launch$default(coroutineScope, null, 0, new StateLayer$handleInteraction$2(emojiProcessor, RippleKt.DefaultTweenSpec, null), 3);
                            }
                            emojiProcessor.mEmojiAsDefaultStyleExceptions = interaction2;
                        }
                    }
                    return Unit.INSTANCE;
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(int r6, kotlin.coroutines.Continuation r7) {
            /*
                r5 = this;
                boolean r0 = r7 instanceof kotlinx.coroutines.flow.StartedLazily$command$1$1$emit$1
                if (r0 == 0) goto L13
                r0 = r7
                kotlinx.coroutines.flow.StartedLazily$command$1$1$emit$1 r0 = (kotlinx.coroutines.flow.StartedLazily$command$1$1$emit$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r1 & r2
                if (r3 == 0) goto L13
                int r1 = r1 - r2
                r0.label = r1
                goto L18
            L13:
                kotlinx.coroutines.flow.StartedLazily$command$1$1$emit$1 r0 = new kotlinx.coroutines.flow.StartedLazily$command$1$1$emit$1
                r0.<init>(r5, r7)
            L18:
                java.lang.Object r7 = r0.result
                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                int r2 = r0.label
                kotlin.Unit r3 = kotlin.Unit.INSTANCE
                r4 = 1
                if (r2 == 0) goto L31
                if (r2 != r4) goto L29
                kotlin.ResultKt.throwOnFailure(r7)
                goto L52
            L29:
                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                r6.<init>(r7)
                throw r6
            L31:
                kotlin.ResultKt.throwOnFailure(r7)
                if (r6 <= 0) goto L52
                java.lang.Object r6 = r5.$started
                r7 = r6
                kotlin.jvm.internal.Ref$BooleanRef r7 = (kotlin.jvm.internal.Ref$BooleanRef) r7
                boolean r7 = r7.element
                if (r7 != 0) goto L52
                kotlin.jvm.internal.Ref$BooleanRef r6 = (kotlin.jvm.internal.Ref$BooleanRef) r6
                r6.element = r4
                java.lang.Object r6 = r5.$$this$flow
                kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
                kotlinx.coroutines.flow.SharingCommand r7 = kotlinx.coroutines.flow.SharingCommand.START
                r0.label = r4
                java.lang.Object r6 = r6.emit(r7, r0)
                if (r6 != r1) goto L52
                return r1
            L52:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.StartedLazily$command$1.AnonymousClass1.emit(int, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }
}
