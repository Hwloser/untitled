package com.hyloser.future

import com.hyloser.TestUtils

import java.util.concurrent.{Executors => JExecutors}
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}
import scala.util.{Failure, Success}

object TestSimpleFuture {
  def main(args: Array[String]): Unit = {
    simpleFutureDefine()
    testJExecutionContext()
    testScalaFuture()
    testScalaFutureError()
  }

  private def testScalaFutureError() = {
    TestUtils.setFunctionHeader("testFutureError")
    import scala.concurrent.ExecutionContext.Implicits.global
    val f = Future[Int] {
      1 / 0
    }

    f.onComplete {
      case Failure(exception) => exception.printStackTrace()
      case Success(value) => println(s"value: $value")
    }

    Await.result(f, Duration(5, "m"))
  }

  private def testScalaFuture() = {
    TestUtils.setFunctionHeader("testScalaFuture")
    import scala.concurrent.ExecutionContext.Implicits.global
    val sumFuture = Future[Int] {
      var sum = 0
      for (i <- 0 until 100) sum += i
      sum
    }

    sumFuture.onComplete { sum =>
      val s = sum
      println("s.get -- " + s)
    }

    Await.result(sumFuture, Duration.Inf)
  }


  private def testJExecutionContext() = {
    TestUtils.setFunctionHeader("testJExecutionContext")
    val jFixedExecutor = JExecutors.newSingleThreadExecutor()
    val executors = scala.concurrent.ExecutionContext.fromExecutorService(jFixedExecutor)
    val f = executors.submit(() => {
      var sum = 0
      for (i <- 0 until 100) sum += i
      sum
    })

    println("f.get() ---- " + f.get())

    executors.shutdown()
  }

  private def simpleFutureDefine() = {
    TestUtils.setFunctionHeader("simpleFutureDefine")
    import scala.concurrent.ExecutionContext.Implicits.global
    val sumFuture = Future[Int] {
      var sum = 0
      for (i <- 1 until 1000) sum += i
      sum
    }

    sumFuture match {
      case Future.never => println("never get future")
      case _ => println("default future")
    }

    sumFuture.onComplete(t => {
      println("sumFuture --- " + t.get)
    })
  }


}
