import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class MyMapper1 extends Mapper<LongWritable, Text, LongWritable, Text> {
	private static final IntWritable one = new IntWritable(1);
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
		 throws IOException, InterruptedException {
		
		long fromNode, toNode;
		fromNode = toNode = -1;
		String[] tokens = value.toString().trim().split("\t");
		try{
			if(tokens.length == 2){
				fromNode = Long.parseLong(tokens[0]);
				toNode = Long.parseLong(tokens[1]);
			}
		}catch(NumberFormatException npe){
			System.err.println("Non numeric Node ; ignoring it");
			npe.getCause();
			return;
		}
		if(fromNode != -1 && toNode != -1){
		context.write(new LongWritable(toNode), new Text(String.valueOf(fromNode)));
		}
	}
}
