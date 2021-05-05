import java.io.IOException;

import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class PeterCushing {

  public static class StringMapper
       extends Mapper<Object, Text, Text, Text>{

    private final static Text movie = new Text();
    private Text actor = new Text("Peter Cushing starred in: ");

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
        String[] words = value.toString().split(",");
        if (words[0].equals("Peter Cushing")) {
		movie.set(words[2].trim());
        	context.write(actor, movie);
    		}
  	}
}

  public static class TextReducer
       extends Reducer<Text,Text,Text,Text> {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Text values,
                       Context context
                       ) throws IOException, InterruptedException {
      context.write(key, values);
  	}
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "Peter Cushing");
    job.setJarByClass(PeterCushing.class);
    job.setMapperClass(StringMapper.class);
    job.setReducerClass(TextReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
