/*
 * components_Worker.c
 *
 *  Created on: Dec 9, 2014
 *      Author: xianda
 */

#define _GNU_SOURCE

#include <jni.h>
#include <stdio.h>
#include <sched.h>
#include <pthread.h>
#include "components_Worker.h"

JNIEXPORT void JNICALL Java_components_Worker_cppSetAffinity
  (JNIEnv *env, jobject obj, jlong jid, jint jcpu) {
	long tid = jid;
	int cpu = jcpu;

#if defined(__linux)
	cpu_set_t mask;

	CPU_ZERO( &mask );

	CPU_SET( cpu, &mask );

	sched_setaffinity( tid, sizeof(cpu_set_t), &mask );
#endif

}
