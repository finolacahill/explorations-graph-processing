
READ ME

---------
MapReduce
--------

Each answer in the MapReduce section has its’ own folder. Within each folder you will find the relevant .jar and .class files, but also the original .java file should you wish to recompile it. In order to compile the .java file, and run it as a jar, please take the following steps in the docker image:

export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar
hadoop com.sun.tools.javac.Main  ClassName.java && jar cf NAME.jar ClassName*.class
hadoop jar NAME.jar ClassName /inputfolder /outputfolder

For question 4 and question 5 I use multiple mappers and reducers. To compile and run them please take the following steps.

hadoop com.sun.tools.javac.Main  My*.java && jar cf NAME.jar My*.class
hadoop jar NAME.jar MyDriver /inputfolder /outputfolder1 /outputfolder2

--------
GraphX
--------

Please note that the answers in the report are intended to be run sequentially. I.e. if you run the code for question 2 prior to running the code for question 1, it will not work as question 2 uses a variable generated in question 1. All code from the report is copy/pastable as last checked on a MacBook, copying and pasting from the PDF to the docker image.
