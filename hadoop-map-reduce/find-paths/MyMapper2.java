import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class MyMapper2 extends Mapper<Object, Text, Text, Text> {
	
	@Override
	protected void map(Object key, Text value, Context context)
		 throws IOException, InterruptedException {
		
		String[] tokens = value.toString().trim().split(":");
		context.write(new Text(tokens[0]), new Text(tokens[1]));
		

	}
}
