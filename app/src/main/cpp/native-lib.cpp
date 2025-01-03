#include <jni.h>
#include <string>
#include <android/log.h>

#define LOG_TAG "AccelerometerCanvas"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

extern "C"
JNIEXPORT void JNICALL
Java_com_gui_toledo_accelerometersample_MainActivity_updateAccelerometerData(JNIEnv *env,
                                                                             jobject thiz, jfloat x,
                                                                             jfloat y, jfloat z) {
    LOGI("Accelerometer Data - X: %f, Y: %f, Z: %f", x, y, z);

    jclass clazz = env->GetObjectClass(thiz);
    jmethodID methodId = env->GetMethodID(clazz, "updateValues", "(FF)V");

    if (methodId != nullptr) {
        env->CallVoidMethod(thiz, methodId, x, y);
    }
}


