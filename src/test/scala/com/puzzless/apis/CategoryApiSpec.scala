package com.puzzless.com.puzzless.apis

import com.puzzless.apis.CategoryApi
import com.puzzless.models.Category
import org.specs2.mutable.Specification
import spray.http.{BasicHttpCredentials, FormData}
import spray.http.MediaTypes._
import spray.http.StatusCodes._
import spray.testkit.Specs2RouteTest

import scala.concurrent.duration._
import scala.language.postfixOps

class CategoryApiSpec extends Specification with Specs2RouteTest with CategoryApi {
  val duration = new FiniteDuration(1L, MINUTES)
  implicit val routeTestTimeout = RouteTestTimeout(duration)

  def actorRefFactory = system

  val categoryData = Category.listAll.last

  "CategoryApi" should {

    "when calling GET v1/categories should return list of categories" in {
      Get("/v1/categories") ~> categoryRoute ~> check {
        status should beEqualTo(OK)
        mediaType === `application/json`
        responseAs[String] must contain("Math")
      }
    }

    s"when calling GET v1/categories/${categoryData.uuid} should return category" in {
      Get(s"/v1/categories/${categoryData.uuid}") ~> categoryRoute ~> check {
        status should beEqualTo(OK)
        mediaType === `text/plain`
        responseAs[String] must contain("Math")
      }
    }

    "when calling POST v1/categories should return new created category instance" in {
      Post("/v1/categories", FormData(Seq("title" -> "Informatics"))) ~> categoryRoute ~> check {
        status should beEqualTo(OK)
        mediaType === `application/json`
        responseAs[String] must contain("Informatics")
      }
    }

    s"when calling PUT v1/categories/${categoryData.uuid} should return update exisisting category instance" in {
      Put(s"/v1/categories/${categoryData.uuid}", FormData(Seq("title" -> "Informatics2"))) ~> categoryRoute ~> check {
        status should beEqualTo(OK)
        mediaType === `application/json`
        responseAs[String] must contain("Informatics2")
      }
    }

    s"when calling DELETE v1/categories/${categoryData.uuid} should delete exisisting category instance" in {
      val validCredentials = BasicHttpCredentials("user", "password")

      Delete(s"/v1/categories/${categoryData.uuid}") ~> addCredentials(validCredentials) ~> categoryRoute ~> check {
        status should beEqualTo(OK)
      }
    }

  }
}
