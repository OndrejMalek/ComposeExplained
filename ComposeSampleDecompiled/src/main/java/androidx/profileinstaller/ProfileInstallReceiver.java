package androidx.profileinstaller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import androidx.compose.runtime.Stack;
import java.io.File;
import kotlin.ResultKt;

/* loaded from: classes.dex */
public class ProfileInstallReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Bundle extras;
        if (intent == null) {
            return;
        }
        String action = intent.getAction();
        if ("androidx.profileinstaller.action.INSTALL_PROFILE".equals(action)) {
            ProfileVersion.writeProfile(context, new ProfileInstaller$$ExternalSyntheticLambda1(3), new Stack(18, this), true);
            return;
        }
        if ("androidx.profileinstaller.action.SKIP_FILE".equals(action)) {
            Bundle extras2 = intent.getExtras();
            if (extras2 != null) {
                String string = extras2.getString("EXTRA_SKIP_FILE_OPERATION");
                if (!"WRITE_SKIP_FILE".equals(string)) {
                    if ("DELETE_SKIP_FILE".equals(string)) {
                        Stack stack = new Stack(18, this);
                        new File(context.getFilesDir(), "profileinstaller_profileWrittenFor_lastUpdateTime.dat").delete();
                        stack.onResultReceived(11, null);
                        return;
                    }
                    return;
                }
                Stack stack2 = new Stack(18, this);
                try {
                    ProfileVersion.noteProfileWrittenFor(context.getPackageManager().getPackageInfo(context.getApplicationContext().getPackageName(), 0), context.getFilesDir());
                    stack2.onResultReceived(10, null);
                    return;
                } catch (PackageManager.NameNotFoundException e) {
                    stack2.onResultReceived(7, e);
                    return;
                }
            }
            return;
        }
        if ("androidx.profileinstaller.action.SAVE_PROFILE".equals(action)) {
            Process.sendSignal(Process.myPid(), 10);
            Log.d("ProfileInstaller", "");
            setResultCode(12);
        } else {
            if (!"androidx.profileinstaller.action.BENCHMARK_OPERATION".equals(action) || (extras = intent.getExtras()) == null) {
                return;
            }
            String string2 = extras.getString("EXTRA_BENCHMARK_OPERATION");
            Stack stack3 = new Stack(18, this);
            if (!"DROP_SHADER_CACHE".equals(string2)) {
                stack3.onResultReceived(16, null);
            } else if (ResultKt.deleteFilesRecursively(context.createDeviceProtectedStorageContext().getCodeCacheDir())) {
                stack3.onResultReceived(14, null);
            } else {
                stack3.onResultReceived(15, null);
            }
        }
    }
}
