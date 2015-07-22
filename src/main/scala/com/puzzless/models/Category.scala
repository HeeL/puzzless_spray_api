package com.puzzless.models

import spray.json.DefaultJsonProtocol


case class Category(uuid: String, title: String)

object CategoryJsonProtocol extends DefaultJsonProtocol {
  implicit val categoryFormat = jsonFormat2(Category)
}