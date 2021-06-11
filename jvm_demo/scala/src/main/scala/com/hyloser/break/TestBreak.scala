package com.hyloser.break

object TestBreak {
  def main(args: Array[String]): Unit = {
    testBreak()

    testContinue()
  }

  import util.control.Breaks._

  private def testBreak() = {
    println(" ----------------------------- ")
    breakable {
      for (i <- 0 until 10) {
        println(i)
        if (i == 5) {
          break
        }
      }
    }
  }

  private def testContinue(): Unit = {
    println(" ----------------------------- ")
    for (i <- 0 until 10) {
      breakable {
        if (i == 3 || i == 6) {
          break
        }
        println(i)
      }
    }
  }

}
