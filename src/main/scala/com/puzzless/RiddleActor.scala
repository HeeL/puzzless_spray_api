package com.puzzless

import akka.actor.{Actor, ActorLogging}
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.puzzless.models.Riddle

class RiddleActor extends Actor with ActorLogging {

  val mapper = new OptionObjectMapper()
  mapper.registerModule(DefaultScalaModule)

  def receive = {
    case "list" =>
      val riddles = Riddle.listAll
      val result = mapper.writeValueAsString(riddles).asInstanceOf[Stream[Riddle]]
      sender ! result

    case ("show", uuid: String) =>
      val riddle = Riddle.findByUuid(uuid).getOrElse(false).asInstanceOf[Riddle]
      val result = mapper.writeValueAsString(riddle)
      sender ! result

    case ("create", title: String, text: String, answer: String) =>
      val riddle = Riddle.create(title, text, answer).asInstanceOf[Riddle]
      val result = mapper.writeValueAsString(riddle)
      sender ! result

    case ("update", uuid: String, title: String, text: String, answer: String) =>
      val riddle = Riddle.update(uuid, title, text, answer).getOrElse(false).asInstanceOf[Riddle]
      val result = mapper.writeValueAsString(riddle)
      sender ! result

    case ("delete", uuid: String) =>
      val result = Riddle.delete(uuid).getOrElse("ERROR").toString.asInstanceOf[String]
      sender ! result
  }

}
