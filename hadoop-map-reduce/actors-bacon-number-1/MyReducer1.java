import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class MyReducer1 extends Reducer<Text,Text,Text, Text>{
	protected void reduce(Text key, Iterable<Text> values, Context context)
		throws IOException, InterruptedException {
		
	StringBuilder nNodes = new StringBuilder();
	for(Text n: values){
		if (!nNodes.toString().contains(n.toString()))
			nNodes.append(n.toString()).append(",");
		}
	context.write(key, new Text(nNodes.toString()));
	}	
}
