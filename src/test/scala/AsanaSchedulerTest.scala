import DTO.{AsanaResponse, ResponseData}
import http.client.AsanaHttpClient
import org.scalatest.flatspec.AnyFlatSpec
import sttp.client4.UriContext
import sttp.client4.testing._
import sttp.model._
import util.{FetchType, TimeInterval}

class AsanaSchedulerTest extends AnyFlatSpec {

  "Asana Http Client" should "get a json response for users and projects every two seconds" in {
    val testingBackend = BackendStub.synchronous
      .whenRequestMatches(_.uri.path.startsWith(List("users")))
      .thenRespond(
        Right(
          AsanaResponse(
            List(
              ResponseData("123", "John", "User"),
              ResponseData("124", "Alex", "User"),
              ResponseData("125", "Martin", "User")
            )
          )
        )
      )
      .whenRequestMatches(_.uri.path.startsWith(List("projects")))
      .thenRespond(
        Right(
          AsanaResponse(
            List(
              ResponseData("0", "First", "Project"),
              ResponseData("1", "Second", "Project")
            )
          )
        )
      )
      .whenRequestMatches(_.method == Method.POST)
      .thenRespondServerError()

    AsanaHttpClient.changeBaseUri(uri"http://testasana.com")

    Main.scheduleDataFetching(
      testingBackend,
      FetchType.Users,
      TimeInterval.TwoSeconds
    )

    Main.scheduleDataFetching(
      testingBackend,
      FetchType.Projects,
      TimeInterval.TwoSeconds
    )

    Thread.sleep(25000)
  }
}
