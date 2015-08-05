package com.puzzless.apis

import com.puzzless.actors._
import spray.http._
import MediaTypes._
import akka.actor.Props
import spray.routing.HttpService
import akka.pattern.ask
import spray.routing.authentication.BasicAuth


trait RiddleApi extends HttpService with BaseActor {
  import context.dispatcher

  val riddle = context.system.actorOf(Props[RiddleActor], "riddle")

  val riddleRoute = pathPrefix("v1") {
    path("riddles") {
      get {
        respondWithMediaType(`application/json`) {
          complete {
            (riddle ? "list").mapTo[String]
          }
        }
      } ~
        post {
          formFields('category_uuid.as[String], 'title.as[String], 'text.as[String], 'answer.as[String]) {
            (category_uuid, title, text, answer) =>
              complete {
                (riddle ? ("create", category_uuid, title, text, answer)).mapTo[String]
              }
          }
        }
    } ~
      pathPrefix("riddles" / "category" / Segment) { category_uuid =>
        get {
          complete {
            (riddle ? ("category", category_uuid)).mapTo[String]
          }
        }
      } ~
      pathPrefix("riddles" / Segment) { uuid =>
        get {
          complete {
            (riddle ? ("show", uuid)).mapTo[String]
          }
        } ~
          put {
            formFields('title.as[String], 'text.as[String], 'answer.as[String]) { (title, text, answer) =>
              complete {
                (riddle ? ("update", uuid, title, text, answer)).mapTo[String]
              }
            }
          } ~
          authenticate(BasicAuth()) { user =>
            delete {
              complete {
                (riddle ? ("delete", uuid)).mapTo[String]
              }
            }
          }
        }
      }
}
