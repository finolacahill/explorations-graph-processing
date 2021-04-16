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
Job job = Job.getInstance(conf, "Give a nice name to the job
,! here");
Page 1 of - 6
COMP47470 Lab 6
job.setJarByClass(MyDriver.class);
job.setMapperClass(MyMapper1.class);
job.setReducerClass(MyReducer1.class);
job.setMapOutputKeyClass(LongWritable.class);
job.setMapOutputValueClass(Text.class);
FileInputFormat.addInputPath(job, new Path(arg0[0]));
FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
System.exit(job.waitForCompletion(true) ? 0 : 1);
}
}
