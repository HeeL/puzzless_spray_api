package com.puzzless.apis

import akka.actor.Props
import akka.pattern.ask
import com.puzzless.actors._
import spray.http.MediaTypes._
import spray.routing.authentication.BasicAuth


trait CategoryApi extends BaseHttpService with ActorHelper {

  val category = actorRefFactory.actorOf(Props[CategoryActor], "category")

  val categoryRoute =
      // categories section
      pathPrefix("v1") {
        path("categories") {
          get {
            respondWithMediaType(`application/json`) {
              complete {
                (category ? "list").mapTo[String]
              }
            }
          } ~
            post {
              formFields('title.as[String]) { title =>
                respondWithMediaType(`application/json`) {
                  complete {
                    (category ?("create", title)).mapTo[String]
                  }
                }
              }
            }
        } ~
        pathPrefix("categories" / Segment) { uuid =>
          get {
            complete {
              (category ?("show", uuid)).mapTo[String]
            }
          } ~
            put {
              formFields('title.as[String]) { title =>
                respondWithMediaType(`application/json`) {
                  complete {
                    (category ?("update", uuid, title)).mapTo[String]
                  }
                }
              }
            } ~
            authenticate(BasicAuth()) { user =>
              delete {
                complete {
                  (category ?("delete", uuid)).mapTo[String]
                }
              }
            }
        }
      }
}