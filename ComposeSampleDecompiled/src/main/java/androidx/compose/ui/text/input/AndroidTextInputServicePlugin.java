package androidx.compose.ui.text.input;

/* loaded from: classes.dex */
public final class AndroidTextInputServicePlugin implements PlatformTextInputPlugin {
    public static final AndroidTextInputServicePlugin INSTANCE = new Object();

    /* loaded from: classes.dex */
    public final class Adapter implements PlatformTextInputAdapter {
        public final TextInputServiceAndroid androidService;
        public final TextInputService service;

        public Adapter(TextInputService textInputService, TextInputServiceAndroid textInputServiceAndroid) {
            this.service = textInputService;
            this.androidService = textInputServiceAndroid;
        }
    }
}
