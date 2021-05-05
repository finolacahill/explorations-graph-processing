import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class MyMapper1 extends Mapper<Object, Text, Text, Text> {
	
	@Override
	protected void map(Object key, Text value, Context context)
		 throws IOException, InterruptedException {
		
		String[] tokens = value.toString().trim().split(",");
               if (tokens[0].contains("Kevin Bacon") || tokens[0].equals("John Malkovich") || tokens[0].equals("Audrey Gelfund")){			
			context.write(new Text(tokens[2] + ';'), new Text(tokens[0]));
		}

	}
}
