package com.puzzless.actors

import akka.actor.Actor
import akka.util.Timeout
import scala.concurrent.duration._


trait BaseActor extends Actor {
  implicit val timeout = Timeout(5.seconds)
}
