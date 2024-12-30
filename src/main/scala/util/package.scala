package object util {
  object FetchType extends Enumeration {
    type FetchType = Value
    val Users, Projects = Value
  }

  object TimeInterval extends Enumeration {
    import scala.concurrent.duration._
    case class Duration(duration: scala.concurrent.duration.Duration)
    val FiveMinutes: Duration = Duration(5.minutes)
    val ThirtySeconds: Duration = Duration(30.seconds)
    val FiveSeconds: Duration = Duration(5.seconds)
    val TwoSeconds: Duration = Duration(2.seconds)
  }
}
