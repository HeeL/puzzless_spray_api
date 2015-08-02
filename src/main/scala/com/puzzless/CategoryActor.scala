package com.puzzless

import akka.actor.{Actor, ActorLogging}
import com.puzzless.models.Category

class CategoryActor extends Actor with ActorLogging {

  def receive = {
    case "list" =>
      val categories = Category.listAll
      val result = JsonUtil.toJson(categories)
      sender ! result

    case ("show", uuid: String) =>
      val category = Category.findByUuid(uuid)
      val result = JsonUtil.toJson(category)
      sender ! result

    case ("create", title: String) =>
      val category = Category.create(title)
      val result = JsonUtil.toJson(category)
      sender ! result

    case ("update", uuid: String, title: String) =>
      val category = Category.update(uuid, title)
      val result = JsonUtil.toJson(category)
      sender ! result

    case ("delete", uuid: String) =>
      val category = Category.delete(uuid)
      val result = JsonUtil.toJson(category)
      sender ! result
  }

}
