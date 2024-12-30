package localstorage

import DTO.AsanaResponse
import sttp.client4.upicklejson.default.asJson

object ResponseStorage {
  def storeResponse(response: AsanaResponse): Unit = {
    response.data.foreach(dataEntity =>
      reflect.io
        .File(dataEntity.gid + ".json")
        .writeAll(asJson(dataEntity).s)
    )
  }
}
