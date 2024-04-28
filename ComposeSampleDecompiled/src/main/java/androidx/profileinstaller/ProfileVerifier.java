package androidx.profileinstaller;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import androidx.compose.ui.draw.DrawResult;
import androidx.concurrent.futures.AbstractResolvableFuture;
import androidx.concurrent.futures.ResolvableFuture;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Objects;

/* loaded from: classes.dex */
public abstract class ProfileVerifier {
    public static final ResolvableFuture sFuture = new Object();
    public static final Object SYNC_OBJ = new Object();
    public static DrawResult sCompilationStatus = null;

    /* loaded from: classes.dex */
    public abstract class Api33Impl {
        public static PackageInfo getPackageInfo(PackageManager packageManager, Context context) {
            return packageManager.getPackageInfo(context.getPackageName(), PackageManager.PackageInfoFlags.of(0L));
        }
    }

    /* loaded from: classes.dex */
    public final class Cache {
        public final long mInstalledCurrentProfileSize;
        public final long mPackageLastUpdateTime;
        public final int mResultCode;
        public final int mSchema;

        public Cache(int i, int i2, long j, long j2) {
            this.mSchema = i;
            this.mResultCode = i2;
            this.mPackageLastUpdateTime = j;
            this.mInstalledCurrentProfileSize = j2;
        }

        public static Cache readFromFile(File file) {
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
            try {
                Cache cache = new Cache(dataInputStream.readInt(), dataInputStream.readInt(), dataInputStream.readLong(), dataInputStream.readLong());
                dataInputStream.close();
                return cache;
            } catch (Throwable th) {
                try {
                    dataInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || !(obj instanceof Cache)) {
                return false;
            }
            Cache cache = (Cache) obj;
            return this.mResultCode == cache.mResultCode && this.mPackageLastUpdateTime == cache.mPackageLastUpdateTime && this.mSchema == cache.mSchema && this.mInstalledCurrentProfileSize == cache.mInstalledCurrentProfileSize;
        }

        public final int hashCode() {
            return Objects.hash(Integer.valueOf(this.mResultCode), Long.valueOf(this.mPackageLastUpdateTime), Integer.valueOf(this.mSchema), Long.valueOf(this.mInstalledCurrentProfileSize));
        }

        public final void writeOnFile(File file) {
            file.delete();
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            try {
                dataOutputStream.writeInt(this.mSchema);
                dataOutputStream.writeInt(this.mResultCode);
                dataOutputStream.writeLong(this.mPackageLastUpdateTime);
                dataOutputStream.writeLong(this.mInstalledCurrentProfileSize);
                dataOutputStream.close();
            } catch (Throwable th) {
                try {
                    dataOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
    }

    public static long getPackageLastUpdateTime(Context context) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        return Build.VERSION.SDK_INT >= 33 ? Api33Impl.getPackageInfo(packageManager, context).lastUpdateTime : packageManager.getPackageInfo(context.getPackageName(), 0).lastUpdateTime;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: _COROUTINE._BOUNDARY */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, androidx.compose.ui.draw.DrawResult] */
    public static DrawResult setCompilationStatus() {
        ?? obj = new Object();
        sCompilationStatus = obj;
        ResolvableFuture resolvableFuture = sFuture;
        resolvableFuture.getClass();
        if (AbstractResolvableFuture.ATOMIC_HELPER.casValue(resolvableFuture, null, obj)) {
            AbstractResolvableFuture.complete(resolvableFuture);
        }
        return sCompilationStatus;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(21:14|(1:79)(1:18)|19|(1:78)(1:23)|24|25|26|(2:64|65)(1:28)|29|(8:36|(1:40)|(1:59)(1:47)|48|(2:55|56)|52|53|54)|(1:63)|(1:40)|(1:42)|59|48|(1:50)|55|56|52|53|54) */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x009c, code lost:
    
        r4 = 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void writeProfileVerification(android.content.Context r18, boolean r19) {
        /*
            if (r19 != 0) goto L7
            androidx.compose.ui.draw.DrawResult r0 = androidx.profileinstaller.ProfileVerifier.sCompilationStatus
            if (r0 == 0) goto L7
            return
        L7:
            java.lang.Object r1 = androidx.profileinstaller.ProfileVerifier.SYNC_OBJ
            monitor-enter(r1)
            if (r19 != 0) goto L15
            androidx.compose.ui.draw.DrawResult r0 = androidx.profileinstaller.ProfileVerifier.sCompilationStatus     // Catch: java.lang.Throwable -> L12
            if (r0 == 0) goto L15
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L12
            return
        L12:
            r0 = move-exception
            goto Ld6
        L15:
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L12
            r2 = 30
            if (r0 != r2) goto L20
            setCompilationStatus()     // Catch: java.lang.Throwable -> L12
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L12
            return
        L20:
            java.io.File r0 = new java.io.File     // Catch: java.lang.Throwable -> L12
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L12
            java.lang.String r3 = "/data/misc/profiles/ref/"
            java.lang.String r4 = r18.getPackageName()     // Catch: java.lang.Throwable -> L12
            r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> L12
            java.lang.String r3 = "primary.prof"
            r0.<init>(r2, r3)     // Catch: java.lang.Throwable -> L12
            long r2 = r0.length()     // Catch: java.lang.Throwable -> L12
            boolean r0 = r0.exists()     // Catch: java.lang.Throwable -> L12
            r4 = 0
            r5 = 0
            r7 = 1
            if (r0 == 0) goto L46
            int r0 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r0 <= 0) goto L46
            r0 = r7
            goto L47
        L46:
            r0 = r4
        L47:
            java.io.File r8 = new java.io.File     // Catch: java.lang.Throwable -> L12
            java.io.File r9 = new java.io.File     // Catch: java.lang.Throwable -> L12
            java.lang.String r10 = "/data/misc/profiles/cur/0/"
            java.lang.String r11 = r18.getPackageName()     // Catch: java.lang.Throwable -> L12
            r9.<init>(r10, r11)     // Catch: java.lang.Throwable -> L12
            java.lang.String r10 = "primary.prof"
            r8.<init>(r9, r10)     // Catch: java.lang.Throwable -> L12
            long r16 = r8.length()     // Catch: java.lang.Throwable -> L12
            boolean r8 = r8.exists()     // Catch: java.lang.Throwable -> L12
            if (r8 == 0) goto L69
            int r5 = (r16 > r5 ? 1 : (r16 == r5 ? 0 : -1))
            if (r5 <= 0) goto L69
            r5 = r7
            goto L6a
        L69:
            r5 = r4
        L6a:
            long r14 = getPackageLastUpdateTime(r18)     // Catch: java.lang.Throwable -> L12 android.content.pm.PackageManager.NameNotFoundException -> Ld1
            java.io.File r6 = new java.io.File     // Catch: java.lang.Throwable -> L12
            java.io.File r8 = r18.getFilesDir()     // Catch: java.lang.Throwable -> L12
            java.lang.String r9 = "profileInstalled"
            r6.<init>(r8, r9)     // Catch: java.lang.Throwable -> L12
            boolean r8 = r6.exists()     // Catch: java.lang.Throwable -> L12
            if (r8 == 0) goto L89
            androidx.profileinstaller.ProfileVerifier$Cache r8 = androidx.profileinstaller.ProfileVerifier.Cache.readFromFile(r6)     // Catch: java.lang.Throwable -> L12 java.io.IOException -> L84
            goto L8a
        L84:
            setCompilationStatus()     // Catch: java.lang.Throwable -> L12
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L12
            return
        L89:
            r8 = 0
        L8a:
            r9 = 2
            if (r8 == 0) goto L9a
            long r10 = r8.mPackageLastUpdateTime     // Catch: java.lang.Throwable -> L12
            int r10 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r10 != 0) goto L9a
            int r10 = r8.mResultCode     // Catch: java.lang.Throwable -> L12
            if (r10 != r9) goto L98
            goto L9a
        L98:
            r4 = r10
            goto La1
        L9a:
            if (r0 == 0) goto L9e
            r4 = r7
            goto La1
        L9e:
            if (r5 == 0) goto La1
            r4 = r9
        La1:
            if (r19 == 0) goto La8
            if (r5 == 0) goto La8
            if (r4 == r7) goto La8
            r4 = r9
        La8:
            if (r8 == 0) goto Lb9
            int r0 = r8.mResultCode     // Catch: java.lang.Throwable -> L12
            if (r0 != r9) goto Lb9
            if (r4 != r7) goto Lb9
            long r9 = r8.mInstalledCurrentProfileSize     // Catch: java.lang.Throwable -> L12
            int r0 = (r2 > r9 ? 1 : (r2 == r9 ? 0 : -1))
            if (r0 >= 0) goto Lb9
            r0 = 3
            r13 = r0
            goto Lba
        Lb9:
            r13 = r4
        Lba:
            androidx.profileinstaller.ProfileVerifier$Cache r0 = new androidx.profileinstaller.ProfileVerifier$Cache     // Catch: java.lang.Throwable -> L12
            r12 = 1
            r11 = r0
            r11.<init>(r12, r13, r14, r16)     // Catch: java.lang.Throwable -> L12
            if (r8 == 0) goto Lc9
            boolean r2 = r8.equals(r0)     // Catch: java.lang.Throwable -> L12
            if (r2 != 0) goto Lcc
        Lc9:
            r0.writeOnFile(r6)     // Catch: java.lang.Throwable -> L12 java.io.IOException -> Lcc
        Lcc:
            setCompilationStatus()     // Catch: java.lang.Throwable -> L12
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L12
            return
        Ld1:
            setCompilationStatus()     // Catch: java.lang.Throwable -> L12
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L12
            return
        Ld6:
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L12
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.profileinstaller.ProfileVerifier.writeProfileVerification(android.content.Context, boolean):void");
    }
}
