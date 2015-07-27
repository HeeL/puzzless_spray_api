package com.puzzless

import akka.actor.{Actor, ActorLogging}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.puzzless.models.Category

class CategoryActor extends Actor with ActorLogging {

  val mapper = new OptionObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  def receive = {
    case "list" =>
      val categories = Category.listAll
      val result = mapper.writeValueAsString(categories)
      sender ! result
    case ("show", uuid: String) =>
      val category = Category.findByUuid(uuid)
      val result = mapper.writeValueAsString(category)
      sender ! result
  }

}
