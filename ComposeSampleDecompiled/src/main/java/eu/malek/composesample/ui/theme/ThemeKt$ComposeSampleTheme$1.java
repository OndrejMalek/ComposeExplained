package eu.malek.composesample.ui.theme;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;
import androidx.compose.material3.ColorScheme;
import androidx.compose.runtime.Stack;
import androidx.compose.ui.graphics.Brush;
import androidx.core.view.WindowInsetsControllerCompat$Impl20;
import androidx.savedstate.SavedStateRegistry;
import kotlin.ResultKt;
import kotlin.ULong;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* loaded from: classes.dex */
public final class ThemeKt$ComposeSampleTheme$1 extends Lambda implements Function0 {
    public final /* synthetic */ Object $colorScheme;
    public final /* synthetic */ boolean $darkTheme;
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ Object $view;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ThemeKt$ComposeSampleTheme$1(View view, ColorScheme colorScheme, boolean z) {
        super(0);
        this.$view = view;
        this.$colorScheme = colorScheme;
        this.$darkTheme = z;
    }

    @Override // kotlin.jvm.functions.Function0
    public final /* bridge */ /* synthetic */ Object invoke() {
        Unit unit = Unit.INSTANCE;
        switch (this.$r8$classId) {
            case 0:
                m291invoke();
                return unit;
            default:
                m291invoke();
                return unit;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ThemeKt$ComposeSampleTheme$1(boolean z, SavedStateRegistry savedStateRegistry, String str) {
        super(0);
        this.$darkTheme = z;
        this.$view = savedStateRegistry;
        this.$colorScheme = str;
    }

    /* JADX DEBUG: Possible override for method kotlin.jvm.functions.Function0.invoke()Ljava/lang/Object; */
    /* renamed from: invoke, reason: collision with other method in class */
    public final void m291invoke() {
        ULong.Companion windowInsetsControllerCompat$Impl20;
        int i = this.$r8$classId;
        Object obj = this.$colorScheme;
        Object obj2 = this.$view;
        boolean z = this.$darkTheme;
        switch (i) {
            case 0:
                View view = (View) obj2;
                Context context = view.getContext();
                ResultKt.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
                final Window window = ((Activity) context).getWindow();
                window.setStatusBarColor(Brush.m109toArgb8_81llA(((ColorScheme) obj).m52getPrimary0d7_KjU()));
                new Stack(view, 12);
                if (Build.VERSION.SDK_INT >= 30) {
                    windowInsetsControllerCompat$Impl20 = new ULong.Companion(window) { // from class: androidx.core.view.WindowInsetsControllerCompat$Impl30
                        public final WindowInsetsController mInsetsController;
                        public final Window mWindow;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(7);
                            WindowInsetsController insetsController;
                            insetsController = window.getInsetsController();
                            this.mInsetsController = insetsController;
                            this.mWindow = window;
                        }

                        @Override // kotlin.ULong.Companion
                        public final void setAppearanceLightStatusBars(boolean z2) {
                            Window window2 = this.mWindow;
                            WindowInsetsController windowInsetsController = this.mInsetsController;
                            if (z2) {
                                if (window2 != null) {
                                    View decorView = window2.getDecorView();
                                    decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | 8192);
                                }
                                windowInsetsController.setSystemBarsAppearance(8, 8);
                                return;
                            }
                            if (window2 != null) {
                                View decorView2 = window2.getDecorView();
                                decorView2.setSystemUiVisibility(decorView2.getSystemUiVisibility() & (-8193));
                            }
                            windowInsetsController.setSystemBarsAppearance(0, 8);
                        }
                    };
                } else {
                    windowInsetsControllerCompat$Impl20 = new WindowInsetsControllerCompat$Impl20(window);
                }
                windowInsetsControllerCompat$Impl20.setAppearanceLightStatusBars(z);
                return;
            default:
                if (z) {
                    SavedStateRegistry savedStateRegistry = (SavedStateRegistry) obj2;
                    String str = (String) obj;
                    savedStateRegistry.getClass();
                    ResultKt.checkNotNullParameter(str, "key");
                    savedStateRegistry.components.remove(str);
                    return;
                }
                return;
        }
    }
}
