package com.puzzless

import org.scalatest._
import com.puzzless.apis._
import spray.testkit.ScalatestRouteTest
import spray.http.HttpEntity
import spray.http.ContentTypes
import spray.can.server.Stats
import spray.http.StatusCodes._

class RootApiSpec extends FreeSpec with Matchers with ScalatestRouteTest with RootApi {
  def actorRefFactory = system

  "Root request" - {
    "valid response" in {
      Get("/") ~> rootRoute ~> check {
        status should equal(OK)
        contentType.toString should include("text/plain")
        responseAs[String] should include("root")
      }
    }
  }

}
