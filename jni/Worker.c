/*
 * Worker.c
 *
 *  Created on: Dec 9, 2014
 *      Author: xianda
 */

#define _GNU_SOURCE

#include <jni.h>
#include <stdio.h>
#include <sched.h>
#include <pthread.h>
#include "Worker.h"

JNIEXPORT void JNICALL Java_Worker_cppSetAffinity
  (JNIEnv *env, jobject obj, jlong jid, jint jcpu) {
	long tid = jid;
	int cpu = jcpu;

#if defined(__linux)
	cpu_set_t mask;

	CPU_ZERO( &mask );

	CPU_SET( cpu, &mask );

	sched_setaffinity( 0, sizeof(cpu_set_t), &mask );
#endif

}
