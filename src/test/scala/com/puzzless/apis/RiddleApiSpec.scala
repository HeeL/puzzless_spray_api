package com.puzzless.com.puzzless.apis

import com.puzzless.apis.RiddleApi
import com.puzzless.models.Riddle
import org.specs2.mutable.Specification
import spray.http.{BasicHttpCredentials, FormData}
import spray.http.MediaTypes._
import spray.http.StatusCodes._
import spray.testkit.Specs2RouteTest

import scala.concurrent.duration._
import scala.language.postfixOps

class RiddleApiSpec extends Specification with Specs2RouteTest with RiddleApi {
  val duration = new FiniteDuration(1L, MINUTES)
  implicit val routeTestTimeout = RouteTestTimeout(duration)

  def actorRefFactory = system

  val riddleData = Riddle.listAll.last

  "RiddleApi" should {

    "when calling GET v1/riddles should return list of riddles" in {
      Get("/v1/riddles") ~> riddleRoute ~> check {
        status should beEqualTo(OK)
        mediaType === `application/json`
        responseAs[String] must contain("Riddle title")
      }
    }

    s"when calling GET v1/riddles/${riddleData.uuid} should return riddle" in {
      Get(s"/v1/riddles/${riddleData.uuid}") ~> riddleRoute ~> check {
        status should beEqualTo(OK)
        mediaType === `application/json`
        responseAs[String] must contain("Riddle title")
      }
    }

    "when calling POST v1/riddles should return new created riddle instance" in {
      var riddleFormData = Seq(
        "category_uuid" -> riddleData.category.uuid,
        "title"         -> "Riddle Title",
        "text"          -> "Riddle Text",
        "answer"        -> "Riddle Answer"
      )

      Post("/v1/riddles", FormData(riddleFormData)) ~> riddleRoute ~> check {
        status should beEqualTo(OK)
        mediaType === `application/json`
        responseAs[String] must contain("Riddle Answer")
        responseAs[String] must contain("Logic")
      }
    }

    s"when calling DELETE v1/riddles/${riddleData.uuid} should delete exisisting riddle instance" in {
      val validCredentials = BasicHttpCredentials("user", "password")

      Delete(s"/v1/riddles/${riddleData.uuid}") ~> addCredentials(validCredentials) ~> riddleRoute ~> check {
        status should beEqualTo(OK)
      }
    }

  }
}
