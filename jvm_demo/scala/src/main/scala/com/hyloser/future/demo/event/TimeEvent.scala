package com.hyloser.future.demo.event

import java.util.{Timer, TimerTask}
import scala.concurrent.{Future, Promise}

object TimeEvent {
  val timer = new Timer
  /**
   * return a future
   * which completes successfully
   * with the supplied value after seconds time
   */
  def delayedSuccess[T](seconds: Int, value: T): Future[T] = {
    val result = Promise[T]
    timer.schedule(new TimerTask {
      override def run(): Unit = {
        result.success(value)
      }
    }, seconds * 1000)
    result.future
  }

  def delayedFailure(seconds: Int, msg: String): Future[Int] = {
    val result = Promise[Int]
    timer.schedule(new TimerTask {
      override def run(): Unit = {
        result.failure(new IllegalArgumentException(msg))
      }
    }, seconds * 1000)

    result.future
  }

}
