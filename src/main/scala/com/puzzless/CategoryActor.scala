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
      val result = mapper.writeValueAsString(categories).asInstanceOf[Stream[Category]]
      sender ! result

    case ("show", uuid: String) =>
      val category = Category.findByUuid(uuid).getOrElse(false).asInstanceOf[Category]
      val result = mapper.writeValueAsString(category)
      sender ! result

    case ("create", title: String) =>
      val category = Category.create(title).asInstanceOf[Category]
      val result = mapper.writeValueAsString(category)
      sender ! result

    case ("update", uuid: String, title: String) =>
      val category = Category.update(uuid, title).getOrElse(false).asInstanceOf[Category]
      val result = mapper.writeValueAsString(category)
      sender ! result
  }

}
