import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.ArrayList;
import java.util.Collections; 
public class MyReducer1 extends Reducer<Text,Text,Text, Text>{
	protected void reduce(Text key, Iterable<Text> values, Context context)
		throws IOException, InterruptedException {
		
	StringBuilder path = new StringBuilder();
	ArrayList<String> actors = new ArrayList<String>();

	for (Text value: values)
		actors.add(value.toString());
	if (actors.size() > 1){
		Collections.sort(actors);
		if (actors.get(0).equals("Audrey Gelfund"))
			path.append(actors.get(0));
		path.append(" -> ");
		path.append(key.toString());
		path.append(" -> ");
		path.append(actors.get(1));
		context.write(new Text("Path: "), new Text(path.toString()));
		}
	}
}

