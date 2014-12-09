# Define a variable for classpath
CLASS_PATH = ../target/classes/components

# Define a virtual path for .class in the bin directory
vpath %.class $(CLASS_PATH)

# $* matches the target filename without the extension
Worker.h : Worker.class
	javah -classpath $(CLASS_PATH) $*