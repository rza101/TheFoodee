#include <string.h>
#include <jni.h>

extern "C" JNIEXPORT jstring JNICALL
Java_com_rhezarijaya_core_utils_CppUtil_generateAPIKey(JNIEnv *env, jobject thiz) {
    // API key sebenarnya adalah "1"
    // 49 -> '1'
    // 0 -> '\0', null terminated string
    // dibuat operasi matematika agar lebih sulit untuk mengetahui nilai asli
    char apiKey[2] = {(char) (2 * 2 * 2 * 5 + 3 * 3), (char) 0};
    return env->NewStringUTF(apiKey);
}