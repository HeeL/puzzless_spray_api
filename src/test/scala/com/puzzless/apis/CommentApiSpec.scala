package com.puzzless.com.puzzless.apis

import com.puzzless.apis.CommentApi
import com.puzzless.models.Comment
import org.specs2.mutable.Specification
import spray.http.{BasicHttpCredentials, FormData}
import spray.http.MediaTypes._
import spray.http.StatusCodes._
import spray.testkit.Specs2RouteTest

import scala.concurrent.duration._
import scala.language.postfixOps

class CommentApiSpec extends Specification with Specs2RouteTest with CommentApi {
  val duration = new FiniteDuration(1L, MINUTES)
  implicit val routeTestTimeout = RouteTestTimeout(duration)

  def actorRefFactory = system

  val commentData = Comment.listAll.last

  "CommentApi" should {

    "when calling GET v1/comments should return list of comments" in {
      Get("/v1/comments") ~> commentRoute ~> check {
        status should beEqualTo(OK)
        mediaType === `application/json`
        responseAs[String] must contain("Comment to the riddle")
      }
    }

    s"when calling GET v1/comments/${commentData.uuid} should return comment" in {
      Get(s"/v1/comments/${commentData.uuid}") ~> commentRoute ~> check {
        status should beEqualTo(OK)
        mediaType === `application/json`
        responseAs[String] must contain("User Name")
      }
    }

    "when calling POST v1/comments should return new created comment instance" in {
      var commentFormData = Seq(
        "riddle_uuid" -> commentData.riddle.uuid,
        "name"        -> "John",
        "text"        -> "Comment Text"
      )

      Post("/v1/comments", FormData(commentFormData)) ~> commentRoute ~> check {
        status should beEqualTo(OK)
        mediaType === `application/json`
        responseAs[String] must contain("Comment Text")
      }
    }

    s"when calling DELETE v1/comments/${commentData.uuid} should delete exisisting comment instance" in {
      val validCredentials = BasicHttpCredentials("user", "password")

      Delete(s"/v1/comments/${commentData.uuid}") ~> addCredentials(validCredentials) ~> commentRoute ~> check {
        status should beEqualTo(OK)
      }
    }

  }
}
