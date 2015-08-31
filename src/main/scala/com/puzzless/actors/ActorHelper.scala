package com.puzzless.actors

import akka.util.Timeout

import scala.concurrent.duration._


trait ActorHelper {
  //value should be a little bit bigger for the first time being called
  //until actors are created and database populated
  implicit val timeout = Timeout(60.seconds)
}
