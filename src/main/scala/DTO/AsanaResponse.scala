package DTO
import upickle.default._

case class AsanaResponse(data: List[ResponseData])

object AsanaResponse {
  implicit val rw: ReadWriter[AsanaResponse] = macroRW
}

case class ResponseData(gid: String, name: String, resource_type: String)

object ResponseData {
  implicit val rw: ReadWriter[ResponseData] = macroRW
}
