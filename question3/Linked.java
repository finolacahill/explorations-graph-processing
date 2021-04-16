import java.io.IOException;

import java.util.StringTokenizer;
import java.util.HashMap;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Linked {

  public static class StringMapper
       extends Mapper<Object, Text, Text, Text>{

    private final static Text actor = new Text();
    private Text movie = new Text();

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
        String[] words = value.toString().split(",");
        if (words[0].equals("Cate Blanchett") || words[0].equals("Christina Ricci")) {
		actor.set(words[0].trim());
		movie.set(words[2].trim());
        	context.write(movie, actor);
    		}
  	}
}

  public static class TextReducer
       extends Reducer<Text,Text,Text,Text> {

    public void reduce(Text key, Iterable<Text> values,
                       Context context
                       ) throws IOException, InterruptedException {
	HashMap<Text, Integer> actors = new HashMap<Text, Integer>();  
	Text empty = new Text("");
	for (Text val: values){
		actors.put(val, 1);
		}
	if (actors.size() == 2)
		context.write(key, empty);
  	}
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "Linked");
    job.setJarByClass(Linked.class);
    job.setMapperClass(StringMapper.class);
    job.setReducerClass(TextReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
