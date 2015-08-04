package com.puzzless.apis

import spray.http._
import MediaTypes._
import spray.routing.HttpService


trait RootApi extends HttpService {

  val rootRoute = path("") {
    get {
      respondWithMediaType(`text/plain`) {
        complete {
          "root"
        }
      }
    }
  }

}
