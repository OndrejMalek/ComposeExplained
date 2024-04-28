package androidx.lifecycle;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import kotlin.ResultKt;
import kotlin.text.StringsKt__StringsKt;
import kotlin.time.DurationKt$$ExternalSyntheticCheckNotZero0;

/* loaded from: classes.dex */
public abstract class Lifecycling {
    public static final HashMap callbackCache = new HashMap();
    public static final HashMap classToAdapters = new HashMap();

    public static void createGeneratedAdapter(Constructor constructor, Object obj) {
        try {
            Object newInstance = constructor.newInstance(obj);
            ResultKt.checkNotNullExpressionValue(newInstance, "{\n            constructo…tance(`object`)\n        }");
            DurationKt$$ExternalSyntheticCheckNotZero0.m$1(newInstance);
            throw null;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    public static final String getAdapterName(String str) {
        StringBuilder sb = new StringBuilder();
        int indexOf = StringsKt__StringsKt.indexOf(str, ".", 0, false);
        if (indexOf >= 0) {
            int length = str.length();
            if (length < 0) {
                throw new OutOfMemoryError();
            }
            StringBuilder sb2 = new StringBuilder(length);
            int i = 0;
            do {
                sb2.append((CharSequence) str, i, indexOf);
                sb2.append("_");
                i = indexOf + 1;
                if (indexOf >= str.length()) {
                    break;
                }
                indexOf = StringsKt__StringsKt.indexOf(str, ".", i, false);
            } while (indexOf > 0);
            sb2.append((CharSequence) str, i, str.length());
            str = sb2.toString();
            ResultKt.checkNotNullExpressionValue(str, "stringBuilder.append(this, i, length).toString()");
        }
        sb.append(str);
        sb.append("_LifecycleAdapter");
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00ac, code lost:
    
        if (r7.booleanValue() != false) goto L71;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getObserverConstructorType(java.lang.Class r13) {
        /*
            java.util.HashMap r0 = androidx.lifecycle.Lifecycling.callbackCache
            java.lang.Object r1 = r0.get(r13)
            java.lang.Integer r1 = (java.lang.Integer) r1
            if (r1 == 0) goto Lf
            int r13 = r1.intValue()
            return r13
        Lf:
            java.lang.String r1 = r13.getCanonicalName()
            r2 = 1
            if (r1 != 0) goto L18
            goto L138
        L18:
            r1 = 0
            java.lang.Package r3 = r13.getPackage()     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            java.lang.String r4 = r13.getCanonicalName()     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            if (r3 == 0) goto L2a
            java.lang.String r3 = r3.getName()     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            goto L2c
        L28:
            r13 = move-exception
            goto L85
        L2a:
            java.lang.String r3 = ""
        L2c:
            java.lang.String r5 = "fullPackage"
            kotlin.ResultKt.checkNotNullExpressionValue(r3, r5)     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            int r5 = r3.length()     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            if (r5 != 0) goto L38
            goto L4b
        L38:
            java.lang.String r5 = "name"
            kotlin.ResultKt.checkNotNullExpressionValue(r4, r5)     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            int r5 = r3.length()     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            int r5 = r5 + r2
            java.lang.String r4 = r4.substring(r5)     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            java.lang.String r5 = "this as java.lang.String).substring(startIndex)"
            kotlin.ResultKt.checkNotNullExpressionValue(r4, r5)     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
        L4b:
            java.lang.String r5 = "if (fullPackage.isEmpty(…g(fullPackage.length + 1)"
            kotlin.ResultKt.checkNotNullExpressionValue(r4, r5)     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            java.lang.String r4 = getAdapterName(r4)     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            int r5 = r3.length()     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            if (r5 != 0) goto L5b
            goto L6f
        L5b:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            r5.<init>()     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            r5.append(r3)     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            r3 = 46
            r5.append(r3)     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            r5.append(r4)     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            java.lang.String r4 = r5.toString()     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
        L6f:
            java.lang.Class r3 = java.lang.Class.forName(r4)     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            java.lang.Class[] r4 = new java.lang.Class[]{r13}     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            java.lang.reflect.Constructor r3 = r3.getDeclaredConstructor(r4)     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            boolean r4 = r3.isAccessible()     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            if (r4 != 0) goto L8c
            r3.setAccessible(r2)     // Catch: java.lang.NoSuchMethodException -> L28 java.lang.ClassNotFoundException -> L8b
            goto L8c
        L85:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r13)
            throw r0
        L8b:
            r3 = r1
        L8c:
            java.util.HashMap r4 = androidx.lifecycle.Lifecycling.classToAdapters
            r5 = 2
            if (r3 == 0) goto L9b
            java.util.List r1 = kotlin.ResultKt.listOf(r3)
            r4.put(r13, r1)
        L98:
            r2 = r5
            goto L138
        L9b:
            androidx.lifecycle.ClassesInfoCache r3 = androidx.lifecycle.ClassesInfoCache.sInstance
            java.util.HashMap r6 = r3.mHasLifecycleMethods
            java.lang.Object r7 = r6.get(r13)
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            r8 = 0
            if (r7 == 0) goto Lb0
            boolean r3 = r7.booleanValue()
            if (r3 == 0) goto Ld1
            goto L138
        Lb0:
            java.lang.reflect.Method[] r7 = r13.getDeclaredMethods()     // Catch: java.lang.NoClassDefFoundError -> L140
            int r9 = r7.length
            r10 = r8
        Lb6:
            if (r10 >= r9) goto Lcc
            r11 = r7[r10]
            java.lang.Class<androidx.lifecycle.OnLifecycleEvent> r12 = androidx.lifecycle.OnLifecycleEvent.class
            java.lang.annotation.Annotation r11 = r11.getAnnotation(r12)
            androidx.lifecycle.OnLifecycleEvent r11 = (androidx.lifecycle.OnLifecycleEvent) r11
            if (r11 == 0) goto Lc9
            r3.createInfo(r13, r7)
            goto L138
        Lc9:
            int r10 = r10 + 1
            goto Lb6
        Lcc:
            java.lang.Boolean r3 = java.lang.Boolean.FALSE
            r6.put(r13, r3)
        Ld1:
            java.lang.Class r3 = r13.getSuperclass()
            java.lang.Class<androidx.lifecycle.LifecycleObserver> r6 = androidx.lifecycle.LifecycleObserver.class
            if (r3 == 0) goto Lf9
            boolean r7 = r6.isAssignableFrom(r3)
            if (r7 == 0) goto Lf9
            java.lang.String r1 = "superclass"
            kotlin.ResultKt.checkNotNullExpressionValue(r3, r1)
            int r1 = getObserverConstructorType(r3)
            if (r1 != r2) goto Leb
            goto L138
        Leb:
            java.util.ArrayList r1 = new java.util.ArrayList
            java.lang.Object r3 = r4.get(r3)
            kotlin.ResultKt.checkNotNull(r3)
            java.util.Collection r3 = (java.util.Collection) r3
            r1.<init>(r3)
        Lf9:
            java.lang.Class[] r3 = r13.getInterfaces()
            java.lang.String r7 = "klass.interfaces"
            kotlin.ResultKt.checkNotNullExpressionValue(r3, r7)
            int r7 = r3.length
        L103:
            if (r8 >= r7) goto L131
            r9 = r3[r8]
            if (r9 == 0) goto L12e
            boolean r10 = r6.isAssignableFrom(r9)
            if (r10 == 0) goto L12e
            java.lang.String r10 = "intrface"
            kotlin.ResultKt.checkNotNullExpressionValue(r9, r10)
            int r10 = getObserverConstructorType(r9)
            if (r10 != r2) goto L11b
            goto L138
        L11b:
            if (r1 != 0) goto L122
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
        L122:
            java.lang.Object r9 = r4.get(r9)
            kotlin.ResultKt.checkNotNull(r9)
            java.util.Collection r9 = (java.util.Collection) r9
            r1.addAll(r9)
        L12e:
            int r8 = r8 + 1
            goto L103
        L131:
            if (r1 == 0) goto L138
            r4.put(r13, r1)
            goto L98
        L138:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r2)
            r0.put(r13, r1)
            return r2
        L140:
            r13 = move-exception
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "The observer class has some methods that use newer APIs which are not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that your observer classes only access framework classes that are available in your min API level OR use lifecycle:compiler annotation processor."
            r0.<init>(r1, r13)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.Lifecycling.getObserverConstructorType(java.lang.Class):int");
    }
}
