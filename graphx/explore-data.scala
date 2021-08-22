import org.apache.spark._
import org.apache.spark.rdd.RDD
import org.apache.spark.graphx._

// Import the data and create a graph representing the data
val data = spark.read.format("csv").option("header", "true").option("inferSchema", true).load("/root/bacon_mirror.csv")
val edges = data.map(row => Edge(row.getInt(1).toLong, row.getInt(3).toLong, 1))
val eRDD = sc.parallelize(edges.collect().toSeq)
val vertices = data.map(row => (row.getInt(1).toLong, row.getString(0)))
val vDD = sc.parallelize(vertices.collect().toSeq)
val graph = Graph(vDD, eRDD, "none")

//How many nodes and edges are there in the graph?

graph.numVertices
graph.numEdges

//Which films star more than 2 actors in the dataset?

val mapVertices = vertices.map { case ((_1), _2) => (_1 -> _2) }.collect.toMap

graph.inDegrees.filter(v => v._2 > 2 && v._1 >= 1000).map(v => ((mapVertices(v._1),v._2))).foreach(println)

// Which films did kevin bacon star in?

val kev_id = vertices.filter(v => v._2 == "Kevin Bacon (I)").map(v => v._1).take(1)(0)
val kev = graph.collectNeighbors(EdgeDirection.Out).lookup(kev_id).toArray
kev(0).map(e => e._2).foreach(println)

//Which actor has starred in the most films?

mapVertices(graph.degrees.reduce( (a,b) => if (a._2 > b._2) a else b )._1)
