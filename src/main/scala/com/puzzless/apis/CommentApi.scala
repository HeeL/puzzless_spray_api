package com.puzzless.apis

import akka.actor.Props
import akka.pattern.ask
import com.puzzless.actors._
import spray.http.MediaTypes._
import spray.routing.authentication.BasicAuth


trait CommentApi extends BaseHttpService with ActorHelper {

  val comment = actorRefFactory.actorOf(Props[CommentActor], "comment")

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
              respondWithMediaType(`application/json`) {
                complete {
                  (comment ? ("create", riddle_uuid, name, text)).mapTo[String]
                }
              }
          }
        }
    } ~
      pathPrefix("comments" / "riddle" / Segment) { riddle_uuid =>
        get {
          respondWithMediaType(`application/json`) {
            complete {
              (comment ? ("riddle", riddle_uuid)).mapTo[String]
            }
          }
        }
      } ~
      pathPrefix("comments" / Segment) { uuid =>
        get {
          respondWithMediaType(`application/json`) {
            complete {
              (comment ? ("show", uuid)).mapTo[String]
            }
          }
        } ~
          put {
            formFields('name.as[String], 'text.as[String]) { (name, text) =>
              respondWithMediaType(`application/json`) {
                complete {
                  (comment ? ("update", uuid, name, text)).mapTo[String]
                }
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
