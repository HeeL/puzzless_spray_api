package com.puzzless.models

import com.puzzless.Db

case class Comment(uuid: String, name: String, text: String, riddle: Riddle)

object Comment {

  def findByUuid(uuid: String) = Db.query[Comment].whereEqual("uuid", uuid).fetchOne()

  def listAll = Db.query[Comment].fetch()

  def riddle(riddle_uuid: String) = Db.query[Comment].whereEqual("riddle.uuid", riddle_uuid).fetch()

  def create(riddle_uuid: String, name: String, text: String) = {
    val riddle = Riddle.findByUuid(riddle_uuid).get
    Db.save(Comment(Db.uuid, name, text, riddle))
  }

  def update(uuid: String, name: String, text: String) = {
    findByUuid(uuid).map(c => c.copy(name = name, text = text)).map(Db.save)
  }

  def delete(uuid: String) = findByUuid(uuid).map(c => Db.delete(c))

}
