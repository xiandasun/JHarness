/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class Worker */

#ifndef _Included_Worker
#define _Included_Worker
#ifdef __cplusplus
extern "C" {
#endif
#undef Worker_MIN_PRIORITY
#define Worker_MIN_PRIORITY 1L
#undef Worker_NORM_PRIORITY
#define Worker_NORM_PRIORITY 5L
#undef Worker_MAX_PRIORITY
#define Worker_MAX_PRIORITY 10L
/*
 * Class:     Worker
 * Method:    cppSetAffinity
 * Signature: (JI)V
 */
JNIEXPORT void JNICALL Java_Worker_cppSetAffinity
  (JNIEnv *, jobject, jlong, jint);

#ifdef __cplusplus
}
#endif
#endif