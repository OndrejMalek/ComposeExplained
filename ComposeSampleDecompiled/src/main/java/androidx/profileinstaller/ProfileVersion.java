package androidx.profileinstaller;

import android.content.pm.PackageInfo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import kotlin.ResultKt;
import kotlin.ULong;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public abstract class ProfileVersion {
    public static final ULong.Companion EMPTY_DIAGNOSTICS = new ULong.Companion(19);
    public static final byte[] MAGIC_PROF = {112, 114, 111, 0};
    public static final byte[] MAGIC_PROFM = {112, 114, 109, 0};
    public static final byte[] V015_S = {48, 49, 53, 0};
    public static final byte[] V010_P = {48, 49, 48, 0};
    public static final byte[] V009_O_MR1 = {48, 48, 57, 0};
    public static final byte[] V005_O = {48, 48, 53, 0};
    public static final byte[] V001_N = {48, 48, 49, 0};
    public static final byte[] METADATA_V001_N = {48, 48, 49, 0};
    public static final byte[] METADATA_V002 = {48, 48, 50, 0};

    public static byte[] createCompressibleBody(DexProfileData[] dexProfileDataArr, byte[] bArr) {
        int i = 0;
        for (DexProfileData dexProfileData : dexProfileDataArr) {
            i += ((((dexProfileData.numMethodIds * 2) + 7) & (-8)) / 8) + (dexProfileData.classSetSize * 2) + generateDexKey(dexProfileData.apkName, dexProfileData.dexName, bArr).getBytes(StandardCharsets.UTF_8).length + 16 + dexProfileData.hotMethodRegionSize;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i);
        if (Arrays.equals(bArr, V009_O_MR1)) {
            for (DexProfileData dexProfileData2 : dexProfileDataArr) {
                writeLineHeader(byteArrayOutputStream, dexProfileData2, generateDexKey(dexProfileData2.apkName, dexProfileData2.dexName, bArr));
                writeMethodsWithInlineCaches(byteArrayOutputStream, dexProfileData2);
                int[] iArr = dexProfileData2.classes;
                int length = iArr.length;
                int i2 = 0;
                int i3 = 0;
                while (i2 < length) {
                    int i4 = iArr[i2];
                    ResultKt.writeUInt16(byteArrayOutputStream, i4 - i3);
                    i2++;
                    i3 = i4;
                }
                writeMethodBitmap(byteArrayOutputStream, dexProfileData2);
            }
        } else {
            for (DexProfileData dexProfileData3 : dexProfileDataArr) {
                writeLineHeader(byteArrayOutputStream, dexProfileData3, generateDexKey(dexProfileData3.apkName, dexProfileData3.dexName, bArr));
            }
            for (DexProfileData dexProfileData4 : dexProfileDataArr) {
                writeMethodsWithInlineCaches(byteArrayOutputStream, dexProfileData4);
                int[] iArr2 = dexProfileData4.classes;
                int length2 = iArr2.length;
                int i5 = 0;
                int i6 = 0;
                while (i5 < length2) {
                    int i7 = iArr2[i5];
                    ResultKt.writeUInt16(byteArrayOutputStream, i7 - i6);
                    i5++;
                    i6 = i7;
                }
                writeMethodBitmap(byteArrayOutputStream, dexProfileData4);
            }
        }
        if (byteArrayOutputStream.size() == i) {
            return byteArrayOutputStream.toByteArray();
        }
        throw new IllegalStateException("The bytes saved do not match expectation. actual=" + byteArrayOutputStream.size() + " expected=" + i);
    }

    public static String generateDexKey(String str, String str2, byte[] bArr) {
        byte[] bArr2 = V001_N;
        boolean equals = Arrays.equals(bArr, bArr2);
        byte[] bArr3 = V005_O;
        String str3 = (equals || Arrays.equals(bArr, bArr3)) ? ":" : "!";
        if (str.length() <= 0) {
            return "!".equals(str3) ? str2.replace(":", "!") : ":".equals(str3) ? str2.replace("!", ":") : str2;
        }
        if (str2.equals("classes.dex")) {
            return str;
        }
        if (str2.contains("!") || str2.contains(":")) {
            return "!".equals(str3) ? str2.replace(":", "!") : ":".equals(str3) ? str2.replace("!", ":") : str2;
        }
        if (str2.endsWith(".apk")) {
            return str2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append((Arrays.equals(bArr, bArr2) || Arrays.equals(bArr, bArr3)) ? ":" : "!");
        sb.append(str2);
        return sb.toString();
    }

    public static int methodFlagBitmapIndex(int i, int i2, int i3) {
        if (i == 1) {
            throw new IllegalStateException("HOT methods are not stored in the bitmap");
        }
        if (i == 2) {
            return i2;
        }
        if (i == 4) {
            return i2 + i3;
        }
        throw new IllegalStateException("Unexpected flag: " + i);
    }

    public static void noteProfileWrittenFor(PackageInfo packageInfo, File file) {
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(new File(file, "profileinstaller_profileWrittenFor_lastUpdateTime.dat")));
            try {
                dataOutputStream.writeLong(packageInfo.lastUpdateTime);
                dataOutputStream.close();
            } finally {
            }
        } catch (IOException unused) {
        }
    }

    public static int[] readClasses(ByteArrayInputStream byteArrayInputStream, int i) {
        int[] iArr = new int[i];
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += (int) ResultKt.readUInt(byteArrayInputStream, 2);
            iArr[i3] = i2;
        }
        return iArr;
    }

    /* JADX DEBUG: Another duplicated slice has different insns count: {[]}, finally: {[INVOKE] complete} */
    public static DexProfileData[] readMeta(FileInputStream fileInputStream, byte[] bArr, byte[] bArr2, DexProfileData[] dexProfileDataArr) {
        byte[] bArr3 = METADATA_V001_N;
        if (!Arrays.equals(bArr, bArr3)) {
            if (!Arrays.equals(bArr, METADATA_V002)) {
                throw new IllegalStateException("Unsupported meta version");
            }
            int readUInt = (int) ResultKt.readUInt(fileInputStream, 2);
            byte[] readCompressed = ResultKt.readCompressed(fileInputStream, (int) ResultKt.readUInt(fileInputStream, 4), (int) ResultKt.readUInt(fileInputStream, 4));
            if (fileInputStream.read() > 0) {
                throw new IllegalStateException("Content found after the end of file");
            }
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(readCompressed);
            try {
                DexProfileData[] readMetadataV002Body = readMetadataV002Body(byteArrayInputStream, bArr2, readUInt, dexProfileDataArr);
                byteArrayInputStream.close();
                return readMetadataV002Body;
            } catch (Throwable th) {
                try {
                    byteArrayInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        if (Arrays.equals(V015_S, bArr2)) {
            throw new IllegalStateException("Requires new Baseline Profile Metadata. Please rebuild the APK with Android Gradle Plugin 7.2 Canary 7 or higher");
        }
        if (!Arrays.equals(bArr, bArr3)) {
            throw new IllegalStateException("Unsupported meta version");
        }
        int readUInt2 = (int) ResultKt.readUInt(fileInputStream, 1);
        byte[] readCompressed2 = ResultKt.readCompressed(fileInputStream, (int) ResultKt.readUInt(fileInputStream, 4), (int) ResultKt.readUInt(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(readCompressed2);
        try {
            DexProfileData[] readMetadataForNBody = readMetadataForNBody(byteArrayInputStream2, readUInt2, dexProfileDataArr);
            byteArrayInputStream2.close();
            return readMetadataForNBody;
        } catch (Throwable th3) {
            try {
                byteArrayInputStream2.close();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    public static DexProfileData[] readMetadataForNBody(ByteArrayInputStream byteArrayInputStream, int i, DexProfileData[] dexProfileDataArr) {
        if (byteArrayInputStream.available() == 0) {
            return new DexProfileData[0];
        }
        if (i != dexProfileDataArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        String[] strArr = new String[i];
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            int readUInt = (int) ResultKt.readUInt(byteArrayInputStream, 2);
            iArr[i2] = (int) ResultKt.readUInt(byteArrayInputStream, 2);
            strArr[i2] = new String(ResultKt.read(byteArrayInputStream, readUInt), StandardCharsets.UTF_8);
        }
        for (int i3 = 0; i3 < i; i3++) {
            DexProfileData dexProfileData = dexProfileDataArr[i3];
            if (!dexProfileData.dexName.equals(strArr[i3])) {
                throw new IllegalStateException("Order of dexfiles in metadata did not match baseline");
            }
            int i4 = iArr[i3];
            dexProfileData.classSetSize = i4;
            dexProfileData.classes = readClasses(byteArrayInputStream, i4);
        }
        return dexProfileDataArr;
    }

    public static DexProfileData[] readMetadataV002Body(ByteArrayInputStream byteArrayInputStream, byte[] bArr, int i, DexProfileData[] dexProfileDataArr) {
        if (byteArrayInputStream.available() == 0) {
            return new DexProfileData[0];
        }
        if (i != dexProfileDataArr.length) {
            throw new IllegalStateException("Mismatched number of dex files found in metadata");
        }
        for (int i2 = 0; i2 < i; i2++) {
            ResultKt.readUInt(byteArrayInputStream, 2);
            String str = new String(ResultKt.read(byteArrayInputStream, (int) ResultKt.readUInt(byteArrayInputStream, 2)), StandardCharsets.UTF_8);
            long readUInt = ResultKt.readUInt(byteArrayInputStream, 4);
            int readUInt2 = (int) ResultKt.readUInt(byteArrayInputStream, 2);
            DexProfileData dexProfileData = null;
            if (dexProfileDataArr.length > 0) {
                int indexOf = str.indexOf("!");
                if (indexOf < 0) {
                    indexOf = str.indexOf(":");
                }
                String substring = indexOf > 0 ? str.substring(indexOf + 1) : str;
                int i3 = 0;
                while (true) {
                    if (i3 >= dexProfileDataArr.length) {
                        break;
                    }
                    if (dexProfileDataArr[i3].dexName.equals(substring)) {
                        dexProfileData = dexProfileDataArr[i3];
                        break;
                    }
                    i3++;
                }
            }
            if (dexProfileData == null) {
                throw new IllegalStateException("Missing profile key: ".concat(str));
            }
            dexProfileData.mTypeIdCount = readUInt;
            int[] readClasses = readClasses(byteArrayInputStream, readUInt2);
            if (Arrays.equals(bArr, V001_N)) {
                dexProfileData.classSetSize = readUInt2;
                dexProfileData.classes = readClasses;
            }
        }
        return dexProfileDataArr;
    }

    public static DexProfileData[] readProfile(FileInputStream fileInputStream, byte[] bArr, String str) {
        if (!Arrays.equals(bArr, V010_P)) {
            throw new IllegalStateException("Unsupported version");
        }
        int readUInt = (int) ResultKt.readUInt(fileInputStream, 1);
        byte[] readCompressed = ResultKt.readCompressed(fileInputStream, (int) ResultKt.readUInt(fileInputStream, 4), (int) ResultKt.readUInt(fileInputStream, 4));
        if (fileInputStream.read() > 0) {
            throw new IllegalStateException("Content found after the end of file");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(readCompressed);
        try {
            DexProfileData[] readUncompressedBody = readUncompressedBody(byteArrayInputStream, str, readUInt);
            byteArrayInputStream.close();
            return readUncompressedBody;
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static DexProfileData[] readUncompressedBody(ByteArrayInputStream byteArrayInputStream, String str, int i) {
        TreeMap treeMap;
        if (byteArrayInputStream.available() == 0) {
            return new DexProfileData[0];
        }
        DexProfileData[] dexProfileDataArr = new DexProfileData[i];
        for (int i2 = 0; i2 < i; i2++) {
            int readUInt = (int) ResultKt.readUInt(byteArrayInputStream, 2);
            int readUInt2 = (int) ResultKt.readUInt(byteArrayInputStream, 2);
            dexProfileDataArr[i2] = new DexProfileData(str, new String(ResultKt.read(byteArrayInputStream, readUInt), StandardCharsets.UTF_8), ResultKt.readUInt(byteArrayInputStream, 4), readUInt2, (int) ResultKt.readUInt(byteArrayInputStream, 4), (int) ResultKt.readUInt(byteArrayInputStream, 4), new int[readUInt2], new TreeMap());
        }
        for (int i3 = 0; i3 < i; i3++) {
            DexProfileData dexProfileData = dexProfileDataArr[i3];
            int available = byteArrayInputStream.available() - dexProfileData.hotMethodRegionSize;
            int i4 = 0;
            while (true) {
                int available2 = byteArrayInputStream.available();
                treeMap = dexProfileData.methods;
                if (available2 <= available) {
                    break;
                }
                i4 += (int) ResultKt.readUInt(byteArrayInputStream, 2);
                treeMap.put(Integer.valueOf(i4), 1);
                for (int readUInt3 = (int) ResultKt.readUInt(byteArrayInputStream, 2); readUInt3 > 0; readUInt3--) {
                    ResultKt.readUInt(byteArrayInputStream, 2);
                    int readUInt4 = (int) ResultKt.readUInt(byteArrayInputStream, 1);
                    if (readUInt4 != 6 && readUInt4 != 7) {
                        while (readUInt4 > 0) {
                            ResultKt.readUInt(byteArrayInputStream, 1);
                            for (int readUInt5 = (int) ResultKt.readUInt(byteArrayInputStream, 1); readUInt5 > 0; readUInt5--) {
                                ResultKt.readUInt(byteArrayInputStream, 2);
                            }
                            readUInt4--;
                        }
                    }
                }
            }
            if (byteArrayInputStream.available() != available) {
                throw new IllegalStateException("Read too much data during profile line parse");
            }
            dexProfileData.classes = readClasses(byteArrayInputStream, dexProfileData.classSetSize);
            int i5 = dexProfileData.numMethodIds;
            BitSet valueOf = BitSet.valueOf(ResultKt.read(byteArrayInputStream, (((i5 * 2) + 7) & (-8)) / 8));
            for (int i6 = 0; i6 < i5; i6++) {
                int i7 = valueOf.get(methodFlagBitmapIndex(2, i6, i5)) ? 2 : 0;
                if (valueOf.get(methodFlagBitmapIndex(4, i6, i5))) {
                    i7 |= 4;
                }
                if (i7 != 0) {
                    Integer num = (Integer) treeMap.get(Integer.valueOf(i6));
                    if (num == null) {
                        num = 0;
                    }
                    treeMap.put(Integer.valueOf(i6), Integer.valueOf(i7 | num.intValue()));
                }
            }
        }
        return dexProfileDataArr;
    }

    /* JADX DEBUG: Another duplicated slice has different insns count: {[GOTO]}, finally: {[GOTO, THROW, INVOKE, MOVE_EXCEPTION, THROW, INVOKE, MOVE_EXCEPTION, GOTO] complete} */
    /* JADX DEBUG: Another duplicated slice has different insns count: {[]}, finally: {[THROW, INVOKE, MOVE_EXCEPTION, THROW, INVOKE, MOVE_EXCEPTION] complete} */
    /* JADX DEBUG: Finally have unexpected throw blocks count: 2, expect 1 */
    /* JADX DEBUG: Incorrect finally slice size: {[GOTO] complete}, expected: {[GOTO, THROW, INVOKE, MOVE_EXCEPTION, THROW, INVOKE, MOVE_EXCEPTION, GOTO] complete} */
    /* JADX WARN: Finally extract failed */
    public static boolean transcodeAndWriteBody(ByteArrayOutputStream byteArrayOutputStream, byte[] bArr, DexProfileData[] dexProfileDataArr) {
        ArrayList arrayList;
        int length;
        byte[] bArr2 = V015_S;
        int i = 0;
        if (!Arrays.equals(bArr, bArr2)) {
            byte[] bArr3 = V010_P;
            if (Arrays.equals(bArr, bArr3)) {
                byte[] createCompressibleBody = createCompressibleBody(dexProfileDataArr, bArr3);
                ResultKt.writeUInt(byteArrayOutputStream, dexProfileDataArr.length, 1);
                ResultKt.writeUInt(byteArrayOutputStream, createCompressibleBody.length, 4);
                byte[] compress = ResultKt.compress(createCompressibleBody);
                ResultKt.writeUInt(byteArrayOutputStream, compress.length, 4);
                byteArrayOutputStream.write(compress);
                return true;
            }
            byte[] bArr4 = V005_O;
            if (Arrays.equals(bArr, bArr4)) {
                ResultKt.writeUInt(byteArrayOutputStream, dexProfileDataArr.length, 1);
                for (DexProfileData dexProfileData : dexProfileDataArr) {
                    int size = dexProfileData.methods.size() * 4;
                    String generateDexKey = generateDexKey(dexProfileData.apkName, dexProfileData.dexName, bArr4);
                    Charset charset = StandardCharsets.UTF_8;
                    ResultKt.writeUInt16(byteArrayOutputStream, generateDexKey.getBytes(charset).length);
                    ResultKt.writeUInt16(byteArrayOutputStream, dexProfileData.classes.length);
                    ResultKt.writeUInt(byteArrayOutputStream, size, 4);
                    ResultKt.writeUInt(byteArrayOutputStream, dexProfileData.dexChecksum, 4);
                    byteArrayOutputStream.write(generateDexKey.getBytes(charset));
                    Iterator it = dexProfileData.methods.keySet().iterator();
                    while (it.hasNext()) {
                        ResultKt.writeUInt16(byteArrayOutputStream, ((Integer) it.next()).intValue());
                        ResultKt.writeUInt16(byteArrayOutputStream, 0);
                    }
                    for (int i2 : dexProfileData.classes) {
                        ResultKt.writeUInt16(byteArrayOutputStream, i2);
                    }
                }
                return true;
            }
            byte[] bArr5 = V009_O_MR1;
            if (Arrays.equals(bArr, bArr5)) {
                byte[] createCompressibleBody2 = createCompressibleBody(dexProfileDataArr, bArr5);
                ResultKt.writeUInt(byteArrayOutputStream, dexProfileDataArr.length, 1);
                ResultKt.writeUInt(byteArrayOutputStream, createCompressibleBody2.length, 4);
                byte[] compress2 = ResultKt.compress(createCompressibleBody2);
                ResultKt.writeUInt(byteArrayOutputStream, compress2.length, 4);
                byteArrayOutputStream.write(compress2);
                return true;
            }
            byte[] bArr6 = V001_N;
            if (!Arrays.equals(bArr, bArr6)) {
                return false;
            }
            ResultKt.writeUInt16(byteArrayOutputStream, dexProfileDataArr.length);
            for (DexProfileData dexProfileData2 : dexProfileDataArr) {
                String generateDexKey2 = generateDexKey(dexProfileData2.apkName, dexProfileData2.dexName, bArr6);
                Charset charset2 = StandardCharsets.UTF_8;
                ResultKt.writeUInt16(byteArrayOutputStream, generateDexKey2.getBytes(charset2).length);
                TreeMap treeMap = dexProfileData2.methods;
                ResultKt.writeUInt16(byteArrayOutputStream, treeMap.size());
                ResultKt.writeUInt16(byteArrayOutputStream, dexProfileData2.classes.length);
                ResultKt.writeUInt(byteArrayOutputStream, dexProfileData2.dexChecksum, 4);
                byteArrayOutputStream.write(generateDexKey2.getBytes(charset2));
                Iterator it2 = treeMap.keySet().iterator();
                while (it2.hasNext()) {
                    ResultKt.writeUInt16(byteArrayOutputStream, ((Integer) it2.next()).intValue());
                }
                for (int i3 : dexProfileData2.classes) {
                    ResultKt.writeUInt16(byteArrayOutputStream, i3);
                }
            }
            return true;
        }
        ArrayList arrayList2 = new ArrayList(3);
        ArrayList arrayList3 = new ArrayList(3);
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        try {
            ResultKt.writeUInt16(byteArrayOutputStream2, dexProfileDataArr.length);
            int i4 = 2;
            int i5 = 2;
            for (DexProfileData dexProfileData3 : dexProfileDataArr) {
                ResultKt.writeUInt(byteArrayOutputStream2, dexProfileData3.dexChecksum, 4);
                ResultKt.writeUInt(byteArrayOutputStream2, dexProfileData3.mTypeIdCount, 4);
                ResultKt.writeUInt(byteArrayOutputStream2, dexProfileData3.numMethodIds, 4);
                String generateDexKey3 = generateDexKey(dexProfileData3.apkName, dexProfileData3.dexName, bArr2);
                Charset charset3 = StandardCharsets.UTF_8;
                int length2 = generateDexKey3.getBytes(charset3).length;
                ResultKt.writeUInt16(byteArrayOutputStream2, length2);
                i5 = i5 + 14 + length2;
                byteArrayOutputStream2.write(generateDexKey3.getBytes(charset3));
            }
            byte[] byteArray = byteArrayOutputStream2.toByteArray();
            if (i5 != byteArray.length) {
                throw new IllegalStateException("Expected size " + i5 + ", does not match actual size " + byteArray.length);
            }
            WritableFileSection writableFileSection = new WritableFileSection(1, byteArray, false);
            byteArrayOutputStream2.close();
            arrayList2.add(writableFileSection);
            ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
            int i6 = 0;
            int i7 = 0;
            while (i6 < dexProfileDataArr.length) {
                try {
                    DexProfileData dexProfileData4 = dexProfileDataArr[i6];
                    ResultKt.writeUInt16(byteArrayOutputStream3, i6);
                    ResultKt.writeUInt16(byteArrayOutputStream3, dexProfileData4.classSetSize);
                    i7 = i7 + 4 + (dexProfileData4.classSetSize * 2);
                    int[] iArr = dexProfileData4.classes;
                    int length3 = iArr.length;
                    int i8 = i;
                    while (i < length3) {
                        int i9 = iArr[i];
                        ResultKt.writeUInt16(byteArrayOutputStream3, i9 - i8);
                        i++;
                        i8 = i9;
                    }
                    i6++;
                    i = 0;
                } catch (Throwable th) {
                }
            }
            byte[] byteArray2 = byteArrayOutputStream3.toByteArray();
            if (i7 != byteArray2.length) {
                throw new IllegalStateException("Expected size " + i7 + ", does not match actual size " + byteArray2.length);
            }
            WritableFileSection writableFileSection2 = new WritableFileSection(3, byteArray2, true);
            byteArrayOutputStream3.close();
            arrayList2.add(writableFileSection2);
            byteArrayOutputStream3 = new ByteArrayOutputStream();
            int i10 = 0;
            int i11 = 0;
            while (i10 < dexProfileDataArr.length) {
                try {
                    DexProfileData dexProfileData5 = dexProfileDataArr[i10];
                    Iterator it3 = dexProfileData5.methods.entrySet().iterator();
                    int i12 = 0;
                    while (it3.hasNext()) {
                        i12 |= ((Integer) ((Map.Entry) it3.next()).getValue()).intValue();
                    }
                    ByteArrayOutputStream byteArrayOutputStream4 = new ByteArrayOutputStream();
                    try {
                        writeMethodBitmap(byteArrayOutputStream4, dexProfileData5);
                        byte[] byteArray3 = byteArrayOutputStream4.toByteArray();
                        byteArrayOutputStream4.close();
                        byteArrayOutputStream4 = new ByteArrayOutputStream();
                        try {
                            writeMethodsWithInlineCaches(byteArrayOutputStream4, dexProfileData5);
                            byte[] byteArray4 = byteArrayOutputStream4.toByteArray();
                            byteArrayOutputStream4.close();
                            ResultKt.writeUInt16(byteArrayOutputStream3, i10);
                            int length4 = byteArray3.length + i4 + byteArray4.length;
                            int i13 = i11 + 6;
                            ArrayList arrayList4 = arrayList3;
                            ResultKt.writeUInt(byteArrayOutputStream3, length4, 4);
                            ResultKt.writeUInt16(byteArrayOutputStream3, i12);
                            byteArrayOutputStream3.write(byteArray3);
                            byteArrayOutputStream3.write(byteArray4);
                            i11 = i13 + length4;
                            i10++;
                            arrayList3 = arrayList4;
                            i4 = 2;
                        } finally {
                        }
                    } finally {
                    }
                } finally {
                    try {
                        byteArrayOutputStream3.close();
                        throw th;
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
            }
            ArrayList arrayList5 = arrayList3;
            byte[] byteArray5 = byteArrayOutputStream3.toByteArray();
            if (i11 != byteArray5.length) {
                throw new IllegalStateException("Expected size " + i11 + ", does not match actual size " + byteArray5.length);
            }
            WritableFileSection writableFileSection3 = new WritableFileSection(4, byteArray5, true);
            byteArrayOutputStream3.close();
            arrayList2.add(writableFileSection3);
            long j = 4;
            long size2 = j + j + 4 + (arrayList2.size() * 16);
            int i14 = 4;
            ResultKt.writeUInt(byteArrayOutputStream, arrayList2.size(), 4);
            int i15 = 0;
            while (i15 < arrayList2.size()) {
                WritableFileSection writableFileSection4 = (WritableFileSection) arrayList2.get(i15);
                ResultKt.writeUInt(byteArrayOutputStream, DurationKt$$ExternalSyntheticCheckNotZero0.getMValue(writableFileSection4.mType), i14);
                ResultKt.writeUInt(byteArrayOutputStream, size2, i14);
                boolean z = writableFileSection4.mNeedsCompression;
                byte[] bArr7 = writableFileSection4.mContents;
                if (z) {
                    long length5 = bArr7.length;
                    byte[] compress3 = ResultKt.compress(bArr7);
                    arrayList = arrayList5;
                    arrayList.add(compress3);
                    ResultKt.writeUInt(byteArrayOutputStream, compress3.length, 4);
                    ResultKt.writeUInt(byteArrayOutputStream, length5, 4);
                    length = compress3.length;
                } else {
                    arrayList = arrayList5;
                    arrayList.add(bArr7);
                    ResultKt.writeUInt(byteArrayOutputStream, bArr7.length, 4);
                    ResultKt.writeUInt(byteArrayOutputStream, 0L, 4);
                    length = bArr7.length;
                }
                size2 += length;
                i15++;
                arrayList5 = arrayList;
                i14 = 4;
            }
            ArrayList arrayList6 = arrayList5;
            for (int i16 = 0; i16 < arrayList6.size(); i16++) {
                byteArrayOutputStream.write((byte[]) arrayList6.get(i16));
            }
            return true;
        } catch (Throwable th3) {
            try {
                byteArrayOutputStream2.close();
                throw th3;
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
                throw th3;
            }
        }
    }

    public static void writeLineHeader(ByteArrayOutputStream byteArrayOutputStream, DexProfileData dexProfileData, String str) {
        Charset charset = StandardCharsets.UTF_8;
        ResultKt.writeUInt16(byteArrayOutputStream, str.getBytes(charset).length);
        ResultKt.writeUInt16(byteArrayOutputStream, dexProfileData.classSetSize);
        ResultKt.writeUInt(byteArrayOutputStream, dexProfileData.hotMethodRegionSize, 4);
        ResultKt.writeUInt(byteArrayOutputStream, dexProfileData.dexChecksum, 4);
        ResultKt.writeUInt(byteArrayOutputStream, dexProfileData.numMethodIds, 4);
        byteArrayOutputStream.write(str.getBytes(charset));
    }

    public static void writeMethodBitmap(ByteArrayOutputStream byteArrayOutputStream, DexProfileData dexProfileData) {
        byte[] bArr = new byte[(((dexProfileData.numMethodIds * 2) + 7) & (-8)) / 8];
        for (Map.Entry entry : dexProfileData.methods.entrySet()) {
            int intValue = ((Integer) entry.getKey()).intValue();
            int intValue2 = ((Integer) entry.getValue()).intValue();
            int i = intValue2 & 2;
            int i2 = dexProfileData.numMethodIds;
            if (i != 0) {
                int methodFlagBitmapIndex = methodFlagBitmapIndex(2, intValue, i2);
                int i3 = methodFlagBitmapIndex / 8;
                bArr[i3] = (byte) ((1 << (methodFlagBitmapIndex % 8)) | bArr[i3]);
            }
            if ((intValue2 & 4) != 0) {
                int methodFlagBitmapIndex2 = methodFlagBitmapIndex(4, intValue, i2);
                int i4 = methodFlagBitmapIndex2 / 8;
                bArr[i4] = (byte) ((1 << (methodFlagBitmapIndex2 % 8)) | bArr[i4]);
            }
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void writeMethodsWithInlineCaches(ByteArrayOutputStream byteArrayOutputStream, DexProfileData dexProfileData) {
        int i = 0;
        for (Map.Entry entry : dexProfileData.methods.entrySet()) {
            int intValue = ((Integer) entry.getKey()).intValue();
            if ((((Integer) entry.getValue()).intValue() & 1) != 0) {
                ResultKt.writeUInt16(byteArrayOutputStream, intValue - i);
                ResultKt.writeUInt16(byteArrayOutputStream, 0);
                i = intValue;
            }
        }
    }

    /* JADX DEBUG: Another duplicated slice has different insns count: {[]}, finally: {[THROW, INVOKE, MOVE_EXCEPTION, THROW, INVOKE, MOVE_EXCEPTION] complete} */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:166:0x0253 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:167:0x0250 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:52:0x00fe */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:64:0x01bb */
    /* JADX DEBUG: Finally have unexpected throw blocks count: 2, expect 1 */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x01bb, code lost:
    
        if (r5 == null) goto L124;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:57:0x0164. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:100:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x028b  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0290 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:106:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0100 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01c7  */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v22, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r6v24 */
    /* JADX WARN: Type inference failed for: r6v25 */
    /* JADX WARN: Type inference failed for: r6v28 */
    /* JADX WARN: Type inference failed for: r6v29 */
    /* JADX WARN: Type inference failed for: r6v30 */
    /* JADX WARN: Type inference failed for: r6v31 */
    /* JADX WARN: Type inference failed for: r6v39 */
    /* JADX WARN: Type inference failed for: r6v4, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r6v40 */
    /* JADX WARN: Type inference failed for: r6v41 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.io.OutputStream, java.io.ByteArrayOutputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void writeProfile(android.content.Context r19, androidx.profileinstaller.ProfileInstaller$$ExternalSyntheticLambda1 r20, androidx.profileinstaller.ProfileInstaller$DiagnosticsCallback r21, boolean r22) {
        /*
            r1 = r19
            r8 = r21
            android.content.Context r0 = r19.getApplicationContext()
            java.lang.String r2 = r0.getPackageName()
            android.content.pm.ApplicationInfo r3 = r0.getApplicationInfo()
            android.content.res.AssetManager r9 = r0.getAssets()
            java.io.File r0 = new java.io.File
            java.lang.String r3 = r3.sourceDir
            r0.<init>(r3)
            java.lang.String r6 = r0.getName()
            android.content.pm.PackageManager r0 = r19.getPackageManager()
            r11 = 0
            android.content.pm.PackageInfo r12 = r0.getPackageInfo(r2, r11)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> L2a7
            java.io.File r13 = r19.getFilesDir()
            java.lang.String r3 = "ProfileInstaller"
            r14 = 0
            r15 = 1
            if (r22 != 0) goto L89
            java.io.File r0 = new java.io.File
            java.lang.String r4 = "profileinstaller_profileWrittenFor_lastUpdateTime.dat"
            r0.<init>(r13, r4)
            boolean r4 = r0.exists()
            if (r4 != 0) goto L40
            goto L89
        L40:
            java.io.DataInputStream r4 = new java.io.DataInputStream     // Catch: java.io.IOException -> L89
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch: java.io.IOException -> L89
            r5.<init>(r0)     // Catch: java.io.IOException -> L89
            r4.<init>(r5)     // Catch: java.io.IOException -> L89
            long r16 = r4.readLong()     // Catch: java.lang.Throwable -> L7d
            r4.close()     // Catch: java.io.IOException -> L89
            long r4 = r12.lastUpdateTime
            int r0 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r0 != 0) goto L59
            r0 = r15
            goto L5a
        L59:
            r0 = r11
        L5a:
            if (r0 == 0) goto L60
            r4 = 2
            r8.onResultReceived(r4, r14)
        L60:
            if (r0 != 0) goto L63
            goto L89
        L63:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Skipping profile installation for "
            r0.<init>(r2)
            java.lang.String r2 = r19.getPackageName()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r3, r0)
            androidx.profileinstaller.ProfileVerifier.writeProfileVerification(r1, r11)
            goto L2a6
        L7d:
            r0 = move-exception
            r5 = r0
            r4.close()     // Catch: java.lang.Throwable -> L83
            goto L88
        L83:
            r0 = move-exception
            r4 = r0
            r5.addSuppressed(r4)     // Catch: java.io.IOException -> L89
        L88:
            throw r5     // Catch: java.io.IOException -> L89
        L89:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r4 = "Installing profile for "
            r0.<init>(r4)
            java.lang.String r4 = r19.getPackageName()
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r3, r0)
            int r0 = android.os.Build.VERSION.SDK_INT
            java.io.File r7 = new java.io.File
            java.io.File r3 = new java.io.File
            java.lang.String r4 = "/data/misc/profiles/cur/0"
            r3.<init>(r4, r2)
            java.lang.String r2 = "primary.prof"
            r7.<init>(r3, r2)
            androidx.profileinstaller.DeviceProfileWriter r5 = new androidx.profileinstaller.DeviceProfileWriter
            java.lang.String r4 = "dexopt/baseline.prof"
            r2 = r5
            r3 = r9
            r11 = r4
            r4 = r20
            r10 = r5
            r5 = r21
            r18 = r7
            r2.<init>(r3, r4, r5, r6, r7)
            byte[] r2 = r10.mDesiredVersion
            if (r2 != 0) goto Lcd
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r2 = 3
            r10.result(r2, r0)
            goto L2a2
        Lcd:
            boolean r0 = r18.exists()
            r3 = 4
            if (r0 == 0) goto Ldf
            boolean r0 = r18.canWrite()
            if (r0 != 0) goto Le2
            r10.result(r3, r14)
            goto L2a2
        Ldf:
            r18.createNewFile()     // Catch: java.io.IOException -> L29f
        Le2:
            r10.mDeviceSupportsAotProfile = r15
            byte[] r4 = androidx.profileinstaller.ProfileVersion.MAGIC_PROF
            r5 = 6
            java.io.FileInputStream r0 = r10.openStreamFromAssets(r9, r11)     // Catch: java.io.IOException -> Led java.io.FileNotFoundException -> Lf4
            r6 = r0
            goto Lfa
        Led:
            r0 = move-exception
            r6 = r0
            r7 = 7
            r8.onResultReceived(r7, r6)
            goto Lf9
        Lf4:
            r0 = move-exception
            r6 = r0
            r8.onResultReceived(r5, r6)
        Lf9:
            r6 = r14
        Lfa:
            java.lang.String r7 = "Invalid magic"
            r11 = 8
            if (r6 == 0) goto L159
            byte[] r0 = kotlin.ResultKt.read(r6, r3)     // Catch: java.lang.Throwable -> L123 java.lang.IllegalStateException -> L125 java.io.IOException -> L127
            boolean r0 = java.util.Arrays.equals(r4, r0)     // Catch: java.lang.Throwable -> L123 java.lang.IllegalStateException -> L125 java.io.IOException -> L127
            if (r0 == 0) goto L129
            byte[] r0 = kotlin.ResultKt.read(r6, r3)     // Catch: java.lang.Throwable -> L123 java.lang.IllegalStateException -> L125 java.io.IOException -> L127
            java.lang.String r5 = r10.mApkName     // Catch: java.lang.Throwable -> L123 java.lang.IllegalStateException -> L125 java.io.IOException -> L127
            androidx.profileinstaller.DexProfileData[] r5 = readProfile(r6, r0, r5)     // Catch: java.lang.Throwable -> L123 java.lang.IllegalStateException -> L125 java.io.IOException -> L127
            r6.close()     // Catch: java.io.IOException -> L118
            goto L14b
        L118:
            r0 = move-exception
            r6 = r0
            r15 = 7
            r8.onResultReceived(r15, r6)
            goto L14b
        L11f:
            r1 = r0
            goto L14e
        L121:
            r15 = 7
            goto L140
        L123:
            r0 = move-exception
            goto L11f
        L125:
            r0 = move-exception
            goto L12f
        L127:
            r0 = move-exception
            goto L121
        L129:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L123 java.lang.IllegalStateException -> L125 java.io.IOException -> L127
            r0.<init>(r7)     // Catch: java.lang.Throwable -> L123 java.lang.IllegalStateException -> L125 java.io.IOException -> L127
            throw r0     // Catch: java.lang.Throwable -> L123 java.lang.IllegalStateException -> L125 java.io.IOException -> L127
        L12f:
            r8.onResultReceived(r11, r0)     // Catch: java.lang.Throwable -> L13d
            r6.close()     // Catch: java.io.IOException -> L136
            goto L14a
        L136:
            r0 = move-exception
            r5 = r0
            r15 = 7
        L139:
            r8.onResultReceived(r15, r5)
            goto L14a
        L13d:
            r0 = move-exception
            r15 = 7
            goto L11f
        L140:
            r8.onResultReceived(r15, r0)     // Catch: java.lang.Throwable -> L123
            r6.close()     // Catch: java.io.IOException -> L147
            goto L14a
        L147:
            r0 = move-exception
            r5 = r0
            goto L139
        L14a:
            r5 = r14
        L14b:
            r10.mProfile = r5
            goto L159
        L14e:
            r6.close()     // Catch: java.io.IOException -> L152
            goto L158
        L152:
            r0 = move-exception
            r2 = r0
            r3 = 7
            r8.onResultReceived(r3, r2)
        L158:
            throw r1
        L159:
            androidx.profileinstaller.DexProfileData[] r0 = r10.mProfile
            if (r0 == 0) goto L1be
            int r5 = android.os.Build.VERSION.SDK_INT
            r6 = 34
            if (r5 <= r6) goto L164
            goto L1be
        L164:
            switch(r5) {
                case 31: goto L168;
                case 32: goto L168;
                case 33: goto L168;
                case 34: goto L168;
                default: goto L167;
            }
        L167:
            goto L1be
        L168:
            java.lang.String r5 = "dexopt/baseline.profm"
            java.io.FileInputStream r5 = r10.openStreamFromAssets(r9, r5)     // Catch: java.lang.IllegalStateException -> L18b java.io.IOException -> L18d java.io.FileNotFoundException -> L190
            if (r5 == 0) goto L1a5
            byte[] r6 = androidx.profileinstaller.ProfileVersion.MAGIC_PROFM     // Catch: java.lang.Throwable -> L192
            byte[] r9 = kotlin.ResultKt.read(r5, r3)     // Catch: java.lang.Throwable -> L192
            boolean r6 = java.util.Arrays.equals(r6, r9)     // Catch: java.lang.Throwable -> L192
            if (r6 == 0) goto L195
            byte[] r3 = kotlin.ResultKt.read(r5, r3)     // Catch: java.lang.Throwable -> L192
            androidx.profileinstaller.DexProfileData[] r0 = readMeta(r5, r3, r2, r0)     // Catch: java.lang.Throwable -> L192
            r10.mProfile = r0     // Catch: java.lang.Throwable -> L192
            r5.close()     // Catch: java.lang.IllegalStateException -> L18b java.io.IOException -> L18d java.io.FileNotFoundException -> L190
            r5 = r10
            goto L1bb
        L18b:
            r0 = move-exception
            goto L1ab
        L18d:
            r0 = move-exception
            r2 = 7
            goto L1b1
        L190:
            r0 = move-exception
            goto L1b5
        L192:
            r0 = move-exception
            r2 = r0
            goto L19b
        L195:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L192
            r0.<init>(r7)     // Catch: java.lang.Throwable -> L192
            throw r0     // Catch: java.lang.Throwable -> L192
        L19b:
            r5.close()     // Catch: java.lang.Throwable -> L19f
            goto L1a4
        L19f:
            r0 = move-exception
            r3 = r0
            r2.addSuppressed(r3)     // Catch: java.lang.IllegalStateException -> L18b java.io.IOException -> L18d java.io.FileNotFoundException -> L190
        L1a4:
            throw r2     // Catch: java.lang.IllegalStateException -> L18b java.io.IOException -> L18d java.io.FileNotFoundException -> L190
        L1a5:
            if (r5 == 0) goto L1ba
            r5.close()     // Catch: java.lang.IllegalStateException -> L18b java.io.IOException -> L18d java.io.FileNotFoundException -> L190
            goto L1ba
        L1ab:
            r10.mProfile = r14
            r8.onResultReceived(r11, r0)
            goto L1ba
        L1b1:
            r8.onResultReceived(r2, r0)
            goto L1ba
        L1b5:
            r2 = 9
            r8.onResultReceived(r2, r0)
        L1ba:
            r5 = r14
        L1bb:
            if (r5 == 0) goto L1be
            goto L1bf
        L1be:
            r5 = r10
        L1bf:
            androidx.profileinstaller.ProfileInstaller$DiagnosticsCallback r2 = r5.mDiagnostics
            androidx.profileinstaller.DexProfileData[] r0 = r5.mProfile
            java.lang.String r3 = "This device doesn't support aot. Did you call deviceSupportsAotProfile()?"
            if (r0 == 0) goto L217
            byte[] r6 = r5.mDesiredVersion
            if (r6 != 0) goto L1cc
            goto L217
        L1cc:
            boolean r7 = r5.mDeviceSupportsAotProfile
            if (r7 == 0) goto L211
            java.io.ByteArrayOutputStream r7 = new java.io.ByteArrayOutputStream     // Catch: java.lang.IllegalStateException -> L1eb java.io.IOException -> L1ed
            r7.<init>()     // Catch: java.lang.IllegalStateException -> L1eb java.io.IOException -> L1ed
            r7.write(r4)     // Catch: java.lang.Throwable -> L1f0
            r7.write(r6)     // Catch: java.lang.Throwable -> L1f0
            boolean r0 = transcodeAndWriteBody(r7, r6, r0)     // Catch: java.lang.Throwable -> L1f0
            if (r0 != 0) goto L1f3
            r0 = 5
            r2.onResultReceived(r0, r14)     // Catch: java.lang.Throwable -> L1f0
            r5.mProfile = r14     // Catch: java.lang.Throwable -> L1f0
            r7.close()     // Catch: java.lang.IllegalStateException -> L1eb java.io.IOException -> L1ed
            goto L217
        L1eb:
            r0 = move-exception
            goto L207
        L1ed:
            r0 = move-exception
            r4 = 7
            goto L20b
        L1f0:
            r0 = move-exception
            r4 = r0
            goto L1fd
        L1f3:
            byte[] r0 = r7.toByteArray()     // Catch: java.lang.Throwable -> L1f0
            r5.mTranscodedProfile = r0     // Catch: java.lang.Throwable -> L1f0
            r7.close()     // Catch: java.lang.IllegalStateException -> L1eb java.io.IOException -> L1ed
            goto L20e
        L1fd:
            r7.close()     // Catch: java.lang.Throwable -> L201
            goto L206
        L201:
            r0 = move-exception
            r6 = r0
            r4.addSuppressed(r6)     // Catch: java.lang.IllegalStateException -> L1eb java.io.IOException -> L1ed
        L206:
            throw r4     // Catch: java.lang.IllegalStateException -> L1eb java.io.IOException -> L1ed
        L207:
            r2.onResultReceived(r11, r0)
            goto L20e
        L20b:
            r2.onResultReceived(r4, r0)
        L20e:
            r5.mProfile = r14
            goto L217
        L211:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r3)
            throw r0
        L217:
            byte[] r0 = r5.mTranscodedProfile
            if (r0 != 0) goto L21f
            r0 = 0
            r6 = 1
            goto L289
        L21f:
            boolean r2 = r5.mDeviceSupportsAotProfile
            if (r2 == 0) goto L299
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L24e java.io.IOException -> L276 java.io.FileNotFoundException -> L279
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L24e java.io.IOException -> L276 java.io.FileNotFoundException -> L279
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L269
            java.io.File r0 = r5.mCurProfile     // Catch: java.lang.Throwable -> L269
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L269
            r0 = 512(0x200, float:7.175E-43)
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L25c
        L233:
            int r4 = r2.read(r0)     // Catch: java.lang.Throwable -> L25c
            if (r4 <= 0) goto L23e
            r6 = 0
            r3.write(r0, r6, r4)     // Catch: java.lang.Throwable -> L25c
            goto L233
        L23e:
            r6 = 1
            r5.result(r6, r14)     // Catch: java.lang.Throwable -> L259
            r3.close()     // Catch: java.lang.Throwable -> L256
            r2.close()     // Catch: java.lang.Throwable -> L24e java.io.IOException -> L250 java.io.FileNotFoundException -> L253
            r5.mTranscodedProfile = r14
            r5.mProfile = r14
            r0 = r6
            goto L289
        L24e:
            r0 = move-exception
            goto L294
        L250:
            r0 = move-exception
        L251:
            r2 = 7
            goto L27c
        L253:
            r0 = move-exception
        L254:
            r2 = 6
            goto L284
        L256:
            r0 = move-exception
        L257:
            r3 = r0
            goto L26c
        L259:
            r0 = move-exception
        L25a:
            r4 = r0
            goto L25f
        L25c:
            r0 = move-exception
            r6 = 1
            goto L25a
        L25f:
            r3.close()     // Catch: java.lang.Throwable -> L263
            goto L268
        L263:
            r0 = move-exception
            r3 = r0
            r4.addSuppressed(r3)     // Catch: java.lang.Throwable -> L256
        L268:
            throw r4     // Catch: java.lang.Throwable -> L256
        L269:
            r0 = move-exception
            r6 = 1
            goto L257
        L26c:
            r2.close()     // Catch: java.lang.Throwable -> L270
            goto L275
        L270:
            r0 = move-exception
            r2 = r0
            r3.addSuppressed(r2)     // Catch: java.lang.Throwable -> L24e java.io.IOException -> L250 java.io.FileNotFoundException -> L253
        L275:
            throw r3     // Catch: java.lang.Throwable -> L24e java.io.IOException -> L250 java.io.FileNotFoundException -> L253
        L276:
            r0 = move-exception
            r6 = 1
            goto L251
        L279:
            r0 = move-exception
            r6 = 1
            goto L254
        L27c:
            r5.result(r2, r0)     // Catch: java.lang.Throwable -> L24e
        L27f:
            r5.mTranscodedProfile = r14
            r5.mProfile = r14
            goto L288
        L284:
            r5.result(r2, r0)     // Catch: java.lang.Throwable -> L24e
            goto L27f
        L288:
            r0 = 0
        L289:
            if (r0 == 0) goto L28e
            noteProfileWrittenFor(r12, r13)
        L28e:
            if (r0 == 0) goto L2a2
            if (r22 == 0) goto L2a2
            r11 = r6
            goto L2a3
        L294:
            r5.mTranscodedProfile = r14
            r5.mProfile = r14
            throw r0
        L299:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r3)
            throw r0
        L29f:
            r10.result(r3, r14)
        L2a2:
            r11 = 0
        L2a3:
            androidx.profileinstaller.ProfileVerifier.writeProfileVerification(r1, r11)
        L2a6:
            return
        L2a7:
            r0 = move-exception
            r2 = r0
            r3 = 7
            r8.onResultReceived(r3, r2)
            r2 = 0
            androidx.profileinstaller.ProfileVerifier.writeProfileVerification(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.profileinstaller.ProfileVersion.writeProfile(android.content.Context, androidx.profileinstaller.ProfileInstaller$$ExternalSyntheticLambda1, androidx.profileinstaller.ProfileInstaller$DiagnosticsCallback, boolean):void");
    }
}
