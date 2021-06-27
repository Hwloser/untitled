package com.hwloser.graph

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.graphx.{Graph, GraphLoader}
import org.apache.spark.rdd.RDD
import org.slf4j.LoggerFactory

object PageRank {

  val logger = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("local test graph from demo of spark")
    val sc = SparkContext.getOrCreate(conf)

    val originalFollowerGraph: Graph[Int, Int] = GraphLoader
      .edgeListFile(sc, "spark-demo/data/graph/followers.txt")
      .cache()

    logger.info("collect with cache persist for print graph triplets")
    originalFollowerGraph
      .triplets
      .collect()
      .foreach(println)

    logger.info("join the ranks with usernames")
    val users: RDD[(Long, String)] = sc.textFile("spark-demo/data/graph/users.txt").map { line =>
      val fields = line.split(",")
      (fields(0).toLong, fields(1))
    }.cache()

    users
      .collect()
      .foreach(println)

    // page rank
//    pageRankAlgorithm(originalFollowerGraph, users)



  }

  /**
   * 求解 ConnectedComponent 中的连通体，
   * 在途中任意两个顶点之间存在路径可达，则该图是<b>连通图</b>
   */
  private def connectedComponents(originalFollowerGraph: Graph[Int, Int], users: RDD[(Long, String)]) = {
    val cg = originalFollowerGraph
      .connectedComponents()
      .cache()

    logger.info("collect connected components graph")
    cg
      .triplets
      .collect()
      .foreach(println)

  }

  private def pageRankAlgorithm(originalFollowerGraph: Graph[Int, Int], users: RDD[(Long, String)]) = {
    val followerRanks = originalFollowerGraph
      .pageRank(0.0001)
    originalFollowerGraph.unpersist()

    logger.info("compute page ranks")
    followerRanks
      .cache()
      .triplets
      .collect()
      .foreach(println)

    val rankByUsernames = users
      .join(followerRanks.vertices)
      .map { case (_, (userName, rank)) =>
        (userName, rank)
      }

    logger.info("ranks by usernames")
    println(rankByUsernames.collect().mkString("\n"))
  }


}
