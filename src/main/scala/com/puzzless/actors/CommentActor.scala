package com.puzzless.actors

import akka.actor.{Actor, ActorLogging}
import com.puzzless.JsonUtil
import com.puzzless.models.Comment

class CommentActor extends Actor with ActorLogging {

  def receive = {
    case "list" =>
      val comments = Comment.listAll
      val result = JsonUtil.toJson(comments)
      sender ! result

    case ("riddle", riddle_uuid: String) =>
      val comments = Comment.riddle(riddle_uuid)
      val result = JsonUtil.toJson(comments)
      sender ! result

    case ("show", uuid: String) =>
      val comment = Comment.findByUuid(uuid)
      val result = JsonUtil.toJson(comment)
      sender ! result

    case ("create", riddle_uuid: String, name: String, text: String) =>
      val comment = Comment.create(riddle_uuid, name, text)
      val result = JsonUtil.toJson(comment)
      sender ! result

    case ("update", uuid: String, name: String, text: String) =>
      val comment = Comment.update(uuid, name, text)
      val result = JsonUtil.toJson(comment)
      sender ! result

    case ("delete", uuid: String) =>
      val comment = Comment.delete(uuid)
      val result = JsonUtil.toJson(comment)
      sender ! result
  }

}
