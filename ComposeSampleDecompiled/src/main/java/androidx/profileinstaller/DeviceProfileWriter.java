package androidx.profileinstaller;

import android.content.res.AssetManager;
import android.os.Build;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.concurrent.Executor;

/* loaded from: classes.dex */
public final class DeviceProfileWriter {
    public final String mApkName;
    public final File mCurProfile;
    public final byte[] mDesiredVersion;
    public boolean mDeviceSupportsAotProfile = false;
    public final ProfileInstaller$DiagnosticsCallback mDiagnostics;
    public final Executor mExecutor;
    public DexProfileData[] mProfile;
    public byte[] mTranscodedProfile;

    public DeviceProfileWriter(AssetManager assetManager, ProfileInstaller$$ExternalSyntheticLambda1 profileInstaller$$ExternalSyntheticLambda1, ProfileInstaller$DiagnosticsCallback profileInstaller$DiagnosticsCallback, String str, File file) {
        this.mExecutor = profileInstaller$$ExternalSyntheticLambda1;
        this.mDiagnostics = profileInstaller$DiagnosticsCallback;
        this.mApkName = str;
        this.mCurProfile = file;
        int i = Build.VERSION.SDK_INT;
        byte[] bArr = null;
        if (i <= 34) {
            switch (i) {
                case 28:
                case 29:
                case 30:
                    bArr = ProfileVersion.V010_P;
                    break;
                case 31:
                case 32:
                case 33:
                case 34:
                    bArr = ProfileVersion.V015_S;
                    break;
            }
        }
        this.mDesiredVersion = bArr;
    }

    public final FileInputStream openStreamFromAssets(AssetManager assetManager, String str) {
        try {
            return assetManager.openFd(str).createInputStream();
        } catch (FileNotFoundException e) {
            String message = e.getMessage();
            if (message != null && message.contains("compressed")) {
                this.mDiagnostics.onDiagnosticReceived();
            }
            return null;
        }
    }

    public final void result(final int i, final Serializable serializable) {
        final int i2 = 1;
        this.mExecutor.execute(new Runnable() { // from class: androidx.profileinstaller.ProfileInstaller$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                int i3 = i2;
                Object obj = serializable;
                int i4 = i;
                Object obj2 = this;
                switch (i3) {
                    case 0:
                        ((ProfileInstaller$DiagnosticsCallback) obj2).onResultReceived(i4, obj);
                        return;
                    default:
                        ((DeviceProfileWriter) obj2).mDiagnostics.onResultReceived(i4, obj);
                        return;
                }
            }
        });
    }
}
