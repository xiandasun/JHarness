# Define a variable for classpath
CLASS_PATH = ../src/

all : libAffinity.so

# Define a virtual path for .class in the bin directory
vpath %.class $(CLASS_PATH)

# $@ matches the target, $< matches the first dependancy
libAffinity.so : Worker.o
	gcc -m64 -Wall -g  -shared -o $@ $<
#--add-stdcall-alias -shared -o $@ $<

# $@ matches the target, $< matches the first dependancy
Worker.o : Worker.c Worker.h
	gcc -m64 -I/usr/lib/jvm/java-7-openjdk-amd64/include/ -c -fPIC $< -o $@

# $* matches the target filename without the extension
Worker.h : ../src/Worker.class
	javah -classpath $(CLASS_PATH) Worker
