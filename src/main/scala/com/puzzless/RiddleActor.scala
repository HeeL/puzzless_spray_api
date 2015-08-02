package com.puzzless

import akka.actor.{Actor, ActorLogging}
import com.puzzless.models.Riddle

class RiddleActor extends Actor with ActorLogging {

  def receive = {
    case "list" =>
      val riddles = Riddle.listAll
      val result = JsonUtil.toJson(riddles)
      sender ! result

    case ("show", uuid: String) =>
      val riddle = Riddle.findByUuid(uuid)
      val result = JsonUtil.toJson(riddle)
      sender ! result

    case ("create", category_uuid: String, title: String, text: String, answer: String) =>
      val riddle = Riddle.create(category_uuid, title, text, answer)
      val result = JsonUtil.toJson(riddle)
      sender ! result

    case ("update", uuid: String, title: String, text: String, answer: String) =>
      val riddle = Riddle.update(uuid, title, text, answer)
      val result = JsonUtil.toJson(riddle)
      sender ! result

    case ("delete", uuid: String) =>
      val riddle = Riddle.delete(uuid)
      val result = JsonUtil.toJson(riddle)
      sender ! result
  }


}
