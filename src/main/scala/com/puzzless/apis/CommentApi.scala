package com.puzzless.apis

import com.puzzless.actors._
import spray.http._
import MediaTypes._
import akka.actor.Props
import spray.routing.HttpService
import akka.pattern.ask
import spray.routing.authentication.BasicAuth


trait CommentApi extends HttpService with BaseActor {
  import context.dispatcher

  val comment = context.system.actorOf(Props[CommentActor], "comment")

  val commentRoute = pathPrefix("v1") {
    path("comments") {
      get {
        respondWithMediaType(`application/json`) {
          complete {
            (comment ? "list").mapTo[String]
          }
        }
      } ~
        post {
          formFields('riddle_uuid.as[String], 'name.as[String], 'text.as[String]) {
            (riddle_uuid, name, text) =>
              complete {
                (comment ? ("create", riddle_uuid, name, text)).mapTo[String]
              }
          }
        }
    } ~
      pathPrefix("comments" / "riddle" / Segment) { riddle_uuid =>
        get {
          complete {
            (comment ? ("riddle", riddle_uuid)).mapTo[String]
          }
        }
      } ~
      pathPrefix("comments" / Segment) { uuid =>
        get {
          complete {
            (comment ? ("show", uuid)).mapTo[String]
          }
        } ~
          put {
            formFields('name.as[String], 'text.as[String]) { (name, text) =>
              complete {
                (comment ? ("update", uuid, name, text)).mapTo[String]
              }
            }
          } ~
          authenticate(BasicAuth()) { user =>
            delete {
              complete {
                (comment ? ("delete", uuid)).mapTo[String]
              }
            }
          }
        }
      }

}
