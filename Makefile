.PHONY : clean

all : Harness.class SpinLockDictionary.class RwLockDictionary.class JavaConcurrentDictionary.class # build all executables

%.class : %.java ${MAKEFILE_NAME}
	javac $<

clean :						# remove files that can be regenerated
	rm -f *.class
