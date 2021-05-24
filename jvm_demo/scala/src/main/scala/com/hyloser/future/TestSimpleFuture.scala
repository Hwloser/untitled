package com.hyloser.future

import com.hyloser.TestUtils

import java.util.concurrent.{TimeUnit, Executors => JExecutors}
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, ExecutionContextExecutorService, Future, Promise}
import scala.util.{Failure, Success, Try}

object TestSimpleFuture {
  def main(args: Array[String]): Unit = {
    simpleFutureDefine()
    testJExecutionContext()
    testScalaFuture()
    Try(testScalaFutureError())
    testScalaPromise
    testPromise
  }

  private def testPromise = {
    Try {
      TestUtils.setFunctionHeader("testPromise failure try")
//      import scala.concurrent.ExecutionContext.Implicits.global
      val promise = Promise[Int]()

      val a = promise success 1
      val b = promise failure new IllegalArgumentException("error")

      println(a)
      println(b)
    } match {
      case Failure(exception) => println(exception.getMessage)
    }

    Try {

    }


  }

  private def testScalaPromise = {
    TestUtils.setFunctionHeader("testScalaPromise")
    import scala.concurrent.ExecutionContext.Implicits.global

    // define a promise of type int
    val promise = Promise[Int]()

    // define a future from promise
    val future = promise.future

    future.onComplete {
      case Failure(exception) => println(s"future on failure, exception:${exception.getMessage}")
      case Success(value) => println(s" future on success, value:$value")
    }

    promise.success(generateMessage("success validate"))
    val failedPromise = Promise[Int]()
    failedPromise.failure(Try(generateMessage("failure validate")).failed.get)

    // completing promise use promise.complete method
    val promise2 = Promise[Int]()
    val future2 = promise.future

    future2.onComplete {
      case Success(stock) => println(s"Stock for vanilla donut = $stock")
      case Failure(e) => println(s"Failed to find vanilla donut stock, exception = $e")
    }

    Await.result(future2, Duration.Inf)

    promise2.complete(Try(generateMessage("failure validate")))
  }

  def generateMessage(donut: String): Int = {
    if (donut == "success validate") 10
    else throw new IllegalStateException("Out of stock")
  }

  private def testScalaFutureError() = {
    Try {
      TestUtils.setFunctionHeader("testFutureError")
      import scala.concurrent.ExecutionContext.Implicits.global
      val f = Future[Int] {
        1 / 0
      }

      f.onComplete {
        case Failure(exception) => exception.printStackTrace()
        case Success(value) => println(s"value: $value")
      }

      Await.result(f, Duration(5, TimeUnit.MINUTES))
    } match {
      case Failure(e) => println(e.getMessage)
    }
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

  private def customizeExecutionContextExecutorService: ExecutionContextExecutorService = {
    TestUtils.setFunctionHeader("customizeExecutionContextExecutorService")
    val jFixedExecutor = JExecutors.newSingleThreadExecutor()
    return scala.concurrent.ExecutionContext
      .fromExecutorService(jFixedExecutor)
  }

  private def testJExecutionContext() = {
    TestUtils.setFunctionHeader("testJExecutionContext")

    val executors = customizeExecutionContextExecutorService

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
