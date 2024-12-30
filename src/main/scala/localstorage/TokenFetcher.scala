package localstorage

import scala.io.Source
import scala.util.{Failure, Success, Using}

object TokenFetcher {
  val token: String =
    Using(Source.fromFile("src/main/scala/localstorage/token.txt")) { source =>
      source.mkString.replaceAll("\"", "")
    } match {
      case Failure(exception) =>
        println(s"bad token, unlucky $exception")
        ""
      case Success(token) => token
    }
}
