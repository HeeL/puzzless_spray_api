package com.puzzless.models

import com.puzzless.Db

case class Category(uuid: String, title: String)

object Category {

  def findByUuid(uuid: String) = Db.query[Category].whereEqual("uuid", uuid).fetchOne()

  def listAll = Db.query[Category].fetch()

  def create(title: String) = Db.save(Category(Db.uuid, title))

  def update(uuid: String, title: String) = findByUuid(uuid).map(c => c.copy(title = title)).map(Db.save)

  def delete(uuid: String) = findByUuid(uuid).map(c => Db.delete(c))

}
