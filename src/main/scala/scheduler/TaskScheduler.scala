package scheduler

import util.TimeInterval.Duration

import java.util.concurrent.TimeUnit

object TaskScheduler {
  def scheduleFetching(
      timeInterval: Duration,
      scheduledTask: => Unit
  ): Unit = {
    val now = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
    val t = new java.util.Timer()
    val task = new java.util.TimerTask {
      def run(): Unit = {
        println(
          s"task execution took ${TimeUnit.MILLISECONDS
            .toSeconds(System.currentTimeMillis()) - now} seconds"
        )
        scheduledTask
      }
    }
    t.schedule(task, 1000L, timeInterval.duration.toMillis)
  }
}
