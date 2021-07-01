package com.hwloser.graph

import org.apache.spark.graphx.util.GraphGenerators
import org.apache.spark.sql.SparkSession

object TestPregel {

  val spark = SparkSession
    .builder()
    .appName("test pregel demo")
    .getOrCreate()

  val sc = spark.sparkContext

  def main(args: Array[String]): Unit = {
    val graph = GraphGenerators
      .logNormalGraph(sc, 100)
      .mapVertices((id, _) => id.toDouble)

    println("println cached triplets")
    graph
      .cache()
      .triplets
      .collect()
      .foreach(println)


  }
}
