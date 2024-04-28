### Educational sample to visualize how Compose Compiler works on decompiled code ###

## Modules ##
1. ComposeSample - Compose screen with vars named like `text8798SDF` to be easy to full text search
2. ComposeSampleDecompiled - Decompiled ComposeSample with (JADX decompiler)[https://github.com/skylot/jadx/]

Code obfuscation is disabled by `-dontobfuscate` in [proguard-rules.pro](ComposeSample%2Fproguard-rules.pro)

**Decompile cmd:**
```
./gradlew :ComposeSample:assembleRelease && \
\rm -rf ComposeSampleDecompiled/src/main/java/* ;\
\rm -rf ComposeSampleDecompiled/src/main/res/* ;\
jadx \
--comments-level debug \
--output-dir-src ComposeSampleDecompiled/src/main/java/ \
--output-dir-res ComposeSampleDecompiled/src/main/res \
ComposeSample/build/outputs/apk/release/ComposeSample-release.apk
```
