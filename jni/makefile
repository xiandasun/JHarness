CLASS_PATH = ../target/classes/

all : libAffinity.so

vpath %.class $(CLASS_PATH)

libAffinity.so : components_Worker.o
	gcc -m64 -Wall -g  -shared -o $@ $<

components_Worker.o : components_Worker.c components_Worker.h
	gcc -m64 -I"$JAVA_HOME/include/" -c -fPIC $< -o $@

components_Worker.h : ../target/classes/components/Worker.class
	javah -classpath $(CLASS_PATH) components.Worker
