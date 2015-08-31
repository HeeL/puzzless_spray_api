package com.puzzless.apis

import akka.actor.Props
import akka.pattern.ask
import com.puzzless.actors._
import spray.http.MediaTypes._
import spray.routing.authentication.BasicAuth


trait RiddleApi extends BaseHttpService with ActorHelper {

  val riddle = actorRefFactory.actorOf(Props[RiddleActor], "riddle")

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
              respondWithMediaType(`application/json`) {
                complete {
                  (riddle ? ("create", category_uuid, title, text, answer)).mapTo[String]
                }
              }
          }
        }
    } ~
      pathPrefix("riddles" / "category" / Segment) { category_uuid =>
        get {
          respondWithMediaType(`application/json`) {
            complete {
              (riddle ? ("category", category_uuid)).mapTo[String]
            }
          }
        }
      } ~
      pathPrefix("riddles" / Segment) { uuid =>
        get {
          respondWithMediaType(`application/json`) {
            complete {
              (riddle ? ("show", uuid)).mapTo[String]
            }
          }
        } ~
          put {
            formFields('title.as[String], 'text.as[String], 'answer.as[String]) { (title, text, answer) =>
              respondWithMediaType(`application/json`) {
                complete {
                  (riddle ? ("update", uuid, title, text, answer)).mapTo[String]
                }
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
