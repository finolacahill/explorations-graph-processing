import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class MyMapper2 extends Mapper<Object, Text, Text, Text> {
	
	@Override
	protected void map(Object key, Text value, Context context)
		 throws IOException, InterruptedException {
		
		String[] tokens = value.toString().trim().split(";");
		if (tokens[1].contains("Kevin Bacon")){
			String[] actors = tokens[1].trim().split(",");
			for (String actor: actors){
			if (!actor.contains("Kevin Bacon"))
				context.write(new Text("Kevin Bacon"), new Text(actor));
			}
		}

	}
}
