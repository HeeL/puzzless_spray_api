package com.puzzless.models

import com.puzzless.Db

case class Category(uuid: String, title: String)

object Category {

  def findByUuid(uuid: String) = {
    Db.query[Category].whereEqual("uuid", uuid).fetchOne().getOrElse(false).asInstanceOf[Category]
  }

  def listAll = {
    Db.query[Category].fetch().asInstanceOf[Stream[Category]]
  }
}
