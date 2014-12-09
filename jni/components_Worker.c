/*
 * components_Worker.c
 *
 *  Created on: Dec 9, 2014
 *      Author: xianda
 */

#include <jni.h>
#include <stdio.h>
#include <sched.h>
#include <pthread.h>
#include "components_Worker.h"

JNIEXPORT void JNICALL Java_components_Worker_cppSetAffinity
  (JNIEnv *, jobject, jlong jid, jint jcpu) {
	long tid = jid;
	int cpu = jcpu;

#if defined(__linux)
	cpu_set_t mask;

	CPU_ZERO( &mask );

	CPU_SET( cpu, &mask );

	int rc = pthread_setaffinity_np( tid, sizeof(cpu_set_t), &mask );

	if ( rc != 0 ) {
		perror( "set affinity" );
		abort();
	} // if
#endif
}
