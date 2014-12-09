# Define a variable for classpath
CLASS_PATH = ../target/classes/

all : libAffinity.so

# Define a virtual path for .class in the bin directory
vpath %.class $(CLASS_PATH)

# $@ matches the target, $< matches the first dependancy
libAffinity.so : components_Worker.o
	gcc -m64 -Wall -g  -shared -o $@ $<
#--add-stdcall-alias -shared -o $@ $<

# $@ matches the target, $< matches the first dependancy
components_Worker.o : components_Worker.c components_Worker.h
	gcc -m64 -I"$JAVA_HOME/include/" -c -fPIC $< -o $@

# $* matches the target filename without the extension
components_Worker.h : ../target/classes/components/Worker.class
	javah -classpath $(CLASS_PATH) components.Worker
