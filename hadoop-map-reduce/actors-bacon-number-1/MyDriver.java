import java.io.BufferedReader;

import java.io.InputStreamReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
public class MyDriver {
public static void main(String[] arg0) throws Exception{
Configuration conf = new Configuration();
Job job1 = Job.getInstance(conf, "adjacency-list");

job1.setJarByClass(MyDriver.class);
job1.setMapperClass(MyMapper1.class);
job1.setReducerClass(MyReducer1.class);

job1.setMapOutputKeyClass(Text.class);
job1.setMapOutputValueClass(Text.class);

FileInputFormat.addInputPath(job1, new Path(arg0[0]));
FileOutputFormat.setOutputPath(job1, new Path(arg0[1]));
job1.waitForCompletion(true);

Configuration conf2 = new Configuration();
Job job2 = Job.getInstance(conf2, "get-path");
job2.setJarByClass(MyDriver.class);
job2.setMapperClass(MyMapper2.class);
job2.setReducerClass(MyReducer1.class);
job2.setMapOutputKeyClass(Text.class);
job2.setMapOutputValueClass(Text.class);

FileInputFormat.addInputPath(job2, new Path(arg0[1]));
FileOutputFormat.setOutputPath(job2, new Path(arg0[2]));
System.exit(job2.waitForCompletion(true) ? 0 : 1);
}
}
