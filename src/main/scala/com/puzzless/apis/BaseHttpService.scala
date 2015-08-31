package com.puzzless.apis

import spray.routing.HttpService

trait BaseHttpService extends HttpService {
  //These implicit values allow to use futures and scheduler
  implicit def executionContext = actorRefFactory.dispatcher

}
