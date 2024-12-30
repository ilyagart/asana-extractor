package http.client

import DTO.AsanaResponse
import localstorage.ResponseStorage.storeResponse
import localstorage.TokenFetcher.token
import sttp.client4.upicklejson.default.asJson
import sttp.client4.{SyncBackend, UriContext, basicRequest}
import sttp.model.Uri
import util.FetchType.{FetchType, Projects, Users}

object AsanaHttpClient {

  private val headers: Map[String, String] =
    Map(("accept", "application/json"), ("authorization", s"Bearer $token"))

  def fetchData(
      backend: SyncBackend,
      fetchType: FetchType
  ): Unit = {
    val response = basicRequest
      .get(getUri(fetchType))
      .headers(headers)
      .response(asJson[AsanaResponse])
      .send(backend)

    if (response.statusText.contains("429"))
      println("Rate limited, unfortunate!")

    response.body match {
      case Left(failure) => println(s"unfortunate, we got a $failure")
      case Right(response) =>
        storeResponse(response)
        println(s"$response")
    }
  }

  def changeBaseUri(uri: Uri): Unit = {
    // this is not good, and it's for the test, better solution is to set URI in the function to make it testable
    baseUri = uri
  }
  private var baseUri: Uri = uri"https://app.asana.com/api/1.0"
  private def getUri(fetchType: FetchType) = fetchType match {
    case Users    => uri"$baseUri/users"
    case Projects => uri"$baseUri/projects"
  }
}
