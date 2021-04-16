import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class MyReducer1 extends Reducer<LongWritable,Text,LongWritable, Text>{
	protected void reduce(LongWritable key, Iterable<Text> values, Context context)
		throws IOException, InterruptedException {
		
	StringBuilder nNodes = new StringBuilder();
	if(key.get() == 1)
		nNodes.append("(0),");
	else
		nNodes.append("(I),");
	for(Text n: values){
		nNodes.append(n.toString()).append(",");
		}
	context.write(key, new Text(nNodes.toString()));
	}	
}
