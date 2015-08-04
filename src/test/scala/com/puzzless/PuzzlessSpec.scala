package com.puzzless

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes._


//class PuzzlessSpec extends Specification with Specs2RouteTest with PuzzlessService {
//  def actorRefFactory = system
//
//  "Puzzless" should {
//
//    "return a greeting for GET requests to the root path" in {
//      Get() ~> puzzlessRoute ~> check {
//        responseAs[String] === "root"
//      }
//    }
//
//    "leave GET requests to other paths unhandled" in {
//      Get("/not_found") ~> puzzlessRoute ~> check {
//        handled must beFalse
//      }
//    }
//
//    "return a MethodNotAllowed error for PUT requests to the root path" in {
//      Put() ~> sealRoute(puzzlessRoute) ~> check {
//        status === MethodNotAllowed
//        responseAs[String] === "HTTP method not allowed, supported methods: GET"
//      }
//    }
//  }
//}

