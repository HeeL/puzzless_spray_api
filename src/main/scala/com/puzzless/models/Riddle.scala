package com.puzzless.models

import com.puzzless.Db

case class Riddle(uuid: String, title: String, text: String, answer: String, rating: Int)

object Riddle {

  def findByUuid(uuid: String) = {
    Db.query[Riddle].whereEqual("uuid", uuid).fetchOne()
  }

  def listAll = {
    Db.query[Riddle].fetch()
  }

  def create(title: String, text: String, answer: String) = {
    Db.save(Riddle(Db.uuid, title, text, answer, 0))
  }

  def update(uuid: String, title: String, text: String, answer: String) = {
    findByUuid(uuid).map(c => c.copy(title = title, text = text, answer = answer)).map(Db.save)
  }

  def delete(uuid: String) = {
    findByUuid(uuid).map(c => Db.delete(c))
  }

}
