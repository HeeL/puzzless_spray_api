package com.puzzless.apis

import com.puzzless.actors._
import spray.http._
import MediaTypes._
import akka.actor.Props
import spray.routing.HttpService
import akka.pattern.ask
import spray.routing.authentication.BasicAuth


trait CategoryApi extends HttpService with BaseActor {
  import context.dispatcher

  val category = context.system.actorOf(Props[CategoryActor], "category")

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
                complete {
                  (category ?("create", title)).mapTo[String]
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
                complete {
                  (category ?("update", uuid, title)).mapTo[String]
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