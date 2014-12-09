# Define a variable for classpath
CLASS_PATH = ../target/classes/

# Define a virtual path for .class in the bin directory
vpath %.class $(CLASS_PATH)

# $* matches the target filename without the extension
components_Worker.h : ../target/classes/components/Worker.class
	javah -classpath $(CLASS_PATH) components.Worker