package androidx.core.provider;

import androidx.compose.ui.node.LayoutNode$$ExternalSyntheticLambda0;

/* loaded from: classes.dex */
public abstract class FontProvider {
    public static final LayoutNode$$ExternalSyntheticLambda0 sByteArrayComparator = new LayoutNode$$ExternalSyntheticLambda0(2);

    /* JADX WARN: Removed duplicated region for block: B:38:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x011a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static kotlinx.coroutines.flow.SafeFlow getFontFamilyResult(android.content.Context r21, androidx.core.provider.FontRequest r22) {
        /*
            r0 = r22
            android.content.pm.PackageManager r1 = r21.getPackageManager()
            android.content.res.Resources r2 = r21.getResources()
            java.lang.String r3 = r0.mProviderAuthority
            r4 = 0
            android.content.pm.ProviderInfo r5 = r1.resolveContentProvider(r3, r4)
            if (r5 == 0) goto L1d9
            java.lang.String r6 = r5.packageName
            java.lang.String r7 = r0.mProviderPackage
            boolean r6 = r6.equals(r7)
            if (r6 == 0) goto L1bd
            java.lang.String r3 = r5.packageName
            r6 = 64
            android.content.pm.PackageInfo r1 = r1.getPackageInfo(r3, r6)
            android.content.pm.Signature[] r1 = r1.signatures
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            int r6 = r1.length
            r7 = r4
        L2e:
            if (r7 >= r6) goto L3c
            r8 = r1[r7]
            byte[] r8 = r8.toByteArray()
            r3.add(r8)
            int r7 = r7 + 1
            goto L2e
        L3c:
            androidx.compose.ui.node.LayoutNode$$ExternalSyntheticLambda0 r1 = androidx.core.provider.FontProvider.sByteArrayComparator
            java.util.Collections.sort(r3, r1)
            java.util.List r6 = r0.mCertificates
            if (r6 == 0) goto L46
            goto L4a
        L46:
            java.util.List r6 = kotlin.ResultKt.readCerts(r2, r4)
        L4a:
            r2 = r4
        L4b:
            int r7 = r6.size()
            r8 = 0
            if (r2 >= r7) goto L8a
            java.util.ArrayList r7 = new java.util.ArrayList
            java.lang.Object r9 = r6.get(r2)
            java.util.Collection r9 = (java.util.Collection) r9
            r7.<init>(r9)
            java.util.Collections.sort(r7, r1)
            int r9 = r3.size()
            int r10 = r7.size()
            if (r9 == r10) goto L6b
            goto L84
        L6b:
            r9 = r4
        L6c:
            int r10 = r3.size()
            if (r9 >= r10) goto L8b
            java.lang.Object r10 = r3.get(r9)
            byte[] r10 = (byte[]) r10
            java.lang.Object r11 = r7.get(r9)
            byte[] r11 = (byte[]) r11
            boolean r10 = java.util.Arrays.equals(r10, r11)
            if (r10 != 0) goto L87
        L84:
            int r2 = r2 + 1
            goto L4b
        L87:
            int r9 = r9 + 1
            goto L6c
        L8a:
            r5 = r8
        L8b:
            r1 = 1
            if (r5 != 0) goto L94
            kotlinx.coroutines.flow.SafeFlow r0 = new kotlinx.coroutines.flow.SafeFlow
            r0.<init>(r1, r8)
            return r0
        L94:
            java.lang.String r2 = r5.authority
            java.lang.String r3 = "result_code"
            java.lang.String r5 = "font_italic"
            java.lang.String r6 = "font_weight"
            java.lang.String r7 = "font_ttc_index"
            java.lang.String r9 = "file_id"
            java.lang.String r10 = "_id"
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            android.net.Uri$Builder r12 = new android.net.Uri$Builder
            r12.<init>()
            java.lang.String r13 = "content"
            android.net.Uri$Builder r12 = r12.scheme(r13)
            android.net.Uri$Builder r12 = r12.authority(r2)
            android.net.Uri r12 = r12.build()
            android.net.Uri$Builder r14 = new android.net.Uri$Builder
            r14.<init>()
            android.net.Uri$Builder r13 = r14.scheme(r13)
            android.net.Uri$Builder r2 = r13.authority(r2)
            java.lang.String r13 = "file"
            android.net.Uri$Builder r2 = r2.appendPath(r13)
            android.net.Uri r2 = r2.build()
            android.content.ContentResolver r13 = r21.getContentResolver()
            android.content.ContentProviderClient r13 = r13.acquireUnstableContentProviderClient(r12)
            r14 = 7
            java.lang.String[] r15 = new java.lang.String[r14]     // Catch: java.lang.Throwable -> L1b0
            r15[r4] = r10     // Catch: java.lang.Throwable -> L1b0
            r15[r1] = r9     // Catch: java.lang.Throwable -> L1b0
            r14 = 2
            r15[r14] = r7     // Catch: java.lang.Throwable -> L1b0
            java.lang.String r14 = "font_variation_settings"
            r16 = 3
            r15[r16] = r14     // Catch: java.lang.Throwable -> L1b0
            r14 = 4
            r15[r14] = r6     // Catch: java.lang.Throwable -> L1b0
            r14 = 5
            r15[r14] = r5     // Catch: java.lang.Throwable -> L1b0
            r14 = 6
            r15[r14] = r3     // Catch: java.lang.Throwable -> L1b0
            java.lang.String r17 = "query = ?"
            java.lang.String[] r14 = new java.lang.String[r1]     // Catch: java.lang.Throwable -> L1b0
            java.lang.String r0 = r0.mQuery     // Catch: java.lang.Throwable -> L1b0
            r14[r4] = r0     // Catch: java.lang.Throwable -> L1b0
            java.lang.String r8 = "Unable to query the content provider"
            java.lang.String r4 = "FontsProvider"
            if (r13 != 0) goto L102
        L100:
            r8 = 0
            goto L118
        L102:
            r19 = 0
            r20 = 0
            r0 = r14
            r14 = r13
            r16 = r15
            r15 = r12
            r18 = r0
            android.database.Cursor r8 = r14.query(r15, r16, r17, r18, r19, r20)     // Catch: android.os.RemoteException -> L112 java.lang.Throwable -> L1b0
            goto L118
        L112:
            r0 = move-exception
            r14 = r0
            android.util.Log.w(r4, r8, r14)     // Catch: java.lang.Throwable -> L1b0
            goto L100
        L118:
            if (r8 == 0) goto L195
            int r0 = r8.getCount()     // Catch: java.lang.Throwable -> L14d
            if (r0 <= 0) goto L195
            int r0 = r8.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L14d
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L14d
            r11.<init>()     // Catch: java.lang.Throwable -> L14d
            int r3 = r8.getColumnIndex(r10)     // Catch: java.lang.Throwable -> L14d
            int r4 = r8.getColumnIndex(r9)     // Catch: java.lang.Throwable -> L14d
            int r7 = r8.getColumnIndex(r7)     // Catch: java.lang.Throwable -> L14d
            int r6 = r8.getColumnIndex(r6)     // Catch: java.lang.Throwable -> L14d
            int r5 = r8.getColumnIndex(r5)     // Catch: java.lang.Throwable -> L14d
        L13d:
            boolean r9 = r8.moveToNext()     // Catch: java.lang.Throwable -> L14d
            if (r9 == 0) goto L195
            r9 = -1
            if (r0 == r9) goto L150
            int r10 = r8.getInt(r0)     // Catch: java.lang.Throwable -> L14d
            r19 = r10
            goto L152
        L14d:
            r0 = move-exception
            goto L1b2
        L150:
            r19 = 0
        L152:
            if (r7 == r9) goto L15b
            int r10 = r8.getInt(r7)     // Catch: java.lang.Throwable -> L14d
            r16 = r10
            goto L15d
        L15b:
            r16 = 0
        L15d:
            if (r4 != r9) goto L169
            long r14 = r8.getLong(r3)     // Catch: java.lang.Throwable -> L14d
            android.net.Uri r10 = android.content.ContentUris.withAppendedId(r12, r14)     // Catch: java.lang.Throwable -> L14d
        L167:
            r15 = r10
            goto L172
        L169:
            long r14 = r8.getLong(r4)     // Catch: java.lang.Throwable -> L14d
            android.net.Uri r10 = android.content.ContentUris.withAppendedId(r2, r14)     // Catch: java.lang.Throwable -> L14d
            goto L167
        L172:
            if (r6 == r9) goto L17b
            int r10 = r8.getInt(r6)     // Catch: java.lang.Throwable -> L14d
        L178:
            r17 = r10
            goto L17e
        L17b:
            r10 = 400(0x190, float:5.6E-43)
            goto L178
        L17e:
            if (r5 == r9) goto L189
            int r9 = r8.getInt(r5)     // Catch: java.lang.Throwable -> L14d
            if (r9 != r1) goto L189
            r18 = r1
            goto L18b
        L189:
            r18 = 0
        L18b:
            androidx.core.provider.FontsContractCompat$FontInfo r9 = new androidx.core.provider.FontsContractCompat$FontInfo     // Catch: java.lang.Throwable -> L14d
            r14 = r9
            r14.<init>(r15, r16, r17, r18, r19)     // Catch: java.lang.Throwable -> L14d
            r11.add(r9)     // Catch: java.lang.Throwable -> L14d
            goto L13d
        L195:
            if (r8 == 0) goto L19a
            r8.close()
        L19a:
            if (r13 == 0) goto L19f
            r13.close()
        L19f:
            r1 = 0
            androidx.core.provider.FontsContractCompat$FontInfo[] r0 = new androidx.core.provider.FontsContractCompat$FontInfo[r1]
            java.lang.Object[] r0 = r11.toArray(r0)
            androidx.core.provider.FontsContractCompat$FontInfo[] r0 = (androidx.core.provider.FontsContractCompat$FontInfo[]) r0
            kotlinx.coroutines.flow.SafeFlow r2 = new kotlinx.coroutines.flow.SafeFlow
            r2.<init>(r1, r0)
            return r2
        L1ae:
            r8 = 0
            goto L1b2
        L1b0:
            r0 = move-exception
            goto L1ae
        L1b2:
            if (r8 == 0) goto L1b7
            r8.close()
        L1b7:
            if (r13 == 0) goto L1bc
            r13.close()
        L1bc:
            throw r0
        L1bd:
            android.content.pm.PackageManager$NameNotFoundException r0 = new android.content.pm.PackageManager$NameNotFoundException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Found content provider "
            r1.<init>(r2)
            r1.append(r3)
            java.lang.String r2 = ", but package was not "
            r1.append(r2)
            r1.append(r7)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L1d9:
            android.content.pm.PackageManager$NameNotFoundException r0 = new android.content.pm.PackageManager$NameNotFoundException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "No package found for authority: "
            r1.<init>(r2)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.provider.FontProvider.getFontFamilyResult(android.content.Context, androidx.core.provider.FontRequest):kotlinx.coroutines.flow.SafeFlow");
    }
}
