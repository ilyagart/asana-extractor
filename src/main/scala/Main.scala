import http.client.AsanaHttpClient.fetchData
import scheduler.TaskScheduler
import sttp.client4._
import util.FetchType._
import util.TimeInterval

object Main {
  def main(args: Array[String]): Unit = {
    val backend = DefaultSyncBackend()
    scheduleDataFetching(backend, Users, TimeInterval.ThirtySeconds)
  }

  def scheduleDataFetching(
      backend: SyncBackend,
      fetchType: FetchType,
      timeInterval: TimeInterval.Duration
  ): Unit = {
    val task: Unit = fetchType match {
      case Users    => fetchData(backend, Users)
      case Projects => fetchData(backend, Projects)
    }
    TaskScheduler.scheduleFetching(timeInterval, task)
  }
}
