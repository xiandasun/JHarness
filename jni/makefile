CLASS_PATH = ..

all : libAffinity.so

vpath %.class $(CLASS_PATH)

libAffinity.so : Worker.o
	gcc -m64 -Wall -g  -shared -o $@ $<

Worker.o : Worker.c Worker.h
	gcc -m64 -I${JAVA_HOME}/include/ -c -fPIC $< -o $@

Worker.h : ${CLASS_PATH}/Worker.class
	javah -classpath $(CLASS_PATH) Worker

clean:
	rm -rf *.o *.so
