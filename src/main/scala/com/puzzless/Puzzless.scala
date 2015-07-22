package com.puzzless

import akka.actor.Actor
import spray.routing._
import spray.http._
import MediaTypes._
import com.puzzless.models._

import com.puzzless.models.CategoryJsonProtocol._
import akka.actor.Props
import spray.httpx.SprayJsonSupport._
import spray.routing.HttpService
import spray.httpx.SprayJsonSupport


// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class PuzzlessActor extends Actor with Puzzless {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}


// this trait defines our service behavior independently from the service actor
trait Puzzless extends HttpService {

  val myRoute =
    path("categories") {
      get {
        respondWithMediaType(`application/json`) {
          val categories = Db.query[Category].fetch()
          complete {
            categories.map(c => Category(c.uuid, c.title)).toList
          }
        }
      }
    }
}