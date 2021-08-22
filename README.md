# explorations-graph-processing
Explorations in graph processing using Hadoop MapReduce and Spark GraphX on the Kevin Bacon movie dataset. 

In order to compile and run the MapReduce jobs please use the following flow in an appropriate environment or docker image:

export HADOOP_CLASSPATH=$JAVA_HOME/lib/tools.jar
hadoop com.sun.tools.javac.Main  ClassName.java && jar cf NAME.jar ClassName*.class
hadoop jar NAME.jar ClassName /inputfolder /outputfolder

For multi-file jobs, they can be compiled as follows:

hadoop com.sun.tools.javac.Main  My*.java && jar cf NAME.jar My*.class
hadoop jar NAME.jar MyDriver /inputfolder /outputfolder1 /outputfolder2


The graphx section was tested/ran from within the Spark-shell of a Spark docker container. 
