JHarness
========

Harness for dictionaries in Java version

========
0. Set JAVA_HOME to the jdk directory so that the header file jni.h is in $JAVA_HOME/include directory.

1. Build
  Under root directory:

  ant clean
  
  ant build
  
  cd jni
  
  make
  
  cd ..
  
  It should generate all the .class files and the .so file.
  
2. Run harness

  ./runall will test all implemented dictionaries [seems there is still bug with AtomicDictionary]
  
  ./run1 SychronizedDictionary 100 to run a single dictionary with an operation delay of 100
