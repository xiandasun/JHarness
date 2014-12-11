SOURCE :=$(wildcard *.java)
CLASS_NAMES := $(SOURCE:.java=.class)

.PHONY : clean jni-so
 
all : $(CLASS_NAMES) jni-so # build all executables

%.class : %.java ${MAKEFILE_NAME}
	javac $<

jni-so:
	${MAKE} -C jni

clean :						# remove files that can be regenerated
	rm -f *.class jni/*.so jni/*.o
