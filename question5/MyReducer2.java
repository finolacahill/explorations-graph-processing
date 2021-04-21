import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.ArrayList;
import java.util.Collections; 
public class MyReducer2 extends Reducer<Text,Text,Text, Text>{
	protected void reduce(Text key, Iterable<Text> values, Context context)
		throws IOException, InterruptedException {
		
	StringBuilder path = new StringBuilder();
	ArrayList<String> actors = new ArrayList<String>();

	for (Text value: values)
		context.write(key, value);
	}
}

