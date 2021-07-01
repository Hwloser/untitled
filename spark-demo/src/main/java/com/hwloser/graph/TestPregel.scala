package com.hwloser.graph

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.graphx.util.GraphGenerators

object TestPregel {

  val sparkConf = new SparkConf()
    .setAppName("test pregel demo")
    .setMaster("local[*]")

  val sc = SparkContext.getOrCreate(sparkConf)

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
