package com.hwloser;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * timer and timer task are java util class used to schedule tasks in a background thread.
 *
 * <p>
 * TimerTask is the task to perform Timer is the scheduler
 * </p>
 */
public class TestTimer {
  public static void main(String[] args) {
    useTimerScheduleTimerTask();
  }

  private static void useTimerScheduleTimerTask() {
    // create a new timer task
    TimerTask timerTask = new TimerTask() {
      @Override
      public void run() {
        System.out.println("task performed on:" + new Date() + " -- " +
            "Thread`s name: " + Thread.currentThread().getName());
      }
    };

    // create timer,
    Timer timer = new Timer();

//    timer
  }
}
