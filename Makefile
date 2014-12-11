.PHONY : clean jni-so

all : Harness.class SpinLockDictionary.class RwLockDictionary.class JavaConcurrentDictionary.class jni-so # build all executables

%.class : %.java ${MAKEFILE_NAME}
	javac $<

jni-so:
	${MAKE} -C jni

clean :						# remove files that can be regenerated
	rm -f *.class jni/*.so jni/*.o
