package com.puzzless

import akka.actor.Actor
import akka.util.Timeout
import spray.routing._
import spray.http._
import MediaTypes._
import com.puzzless.models._

import akka.actor.Props
import spray.routing.HttpService

import akka.pattern.ask
import spray.routing.authentication.BasicAuth
import scala.concurrent.duration._


// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class PuzzlessActor extends Actor with HttpService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(puzzlessRoute)

  implicit val timeout = Timeout(5.seconds)
  //implicit def executionContext = actorRefFactory.dispatcher
  import context.dispatcher

  val category = context.system.actorOf(Props[CategoryActor], "category")
  val riddle = context.system.actorOf(Props[RiddleActor], "riddle")

  val puzzlessRoute =
    path("") {
      get {
        respondWithMediaType(`text/plain`) {
          complete {
            "root"
          }
        }
      }
    } ~
    // categories section
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
              (category ? ("create", title)).mapTo[String]
            }
          }
        }
    } ~
    pathPrefix("categories" / Segment) { uuid =>
      get {
        complete {
          (category ? ("show", uuid)).mapTo[String]
        }
      } ~
        put {
          formFields('title.as[String]) { title =>
            complete {
              (category ? ("update", uuid, title)).mapTo[String]
            }
          }
        } ~
        authenticate(BasicAuth()) { user =>
          delete {
            complete {
              (category ? ("delete", uuid)).mapTo[String]
            }
          }
        }
    } ~
  // riddles section
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

