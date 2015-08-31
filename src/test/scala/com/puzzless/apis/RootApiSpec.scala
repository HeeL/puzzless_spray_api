package com.puzzless.com.puzzless.apis

import com.puzzless.apis.RootApi
import org.specs2.mutable.Specification
import spray.http.MediaTypes._
import spray.http.StatusCodes._
import spray.testkit.Specs2RouteTest


class RootApiSpec extends Specification with Specs2RouteTest with RootApi {

  def actorRefFactory = system

  "Root request" should {

    "when calling GET / should return text root" in {
      Get("/") ~> rootRoute ~> check {
        status should beEqualTo(OK)
        mediaType === `text/plain`
        responseAs[String] must contain("root")
      }
    }

  }
}
