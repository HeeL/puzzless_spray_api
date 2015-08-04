package com.puzzless

import com.puzzless.models._
import sorm._


object Db extends Instance(
  entities = Set() + Entity[Category]() + Entity[Riddle]() + Entity[Comment](),
  url = "jdbc:postgresql://localhost/puzzless_spray_api",
  user = "heel",
  password = "",
  initMode = InitMode.DropAllCreate
) {

  // seeding
  Category.create("Math")
  val category = Category.create("Logic")
  val riddle = Riddle.create(category.uuid, "Riddle title", "Text of the riddle", "The answer")
  Comment.create(riddle.uuid, "User Name", "Comment to the riddle")

  // generate random uuid
  def uuid = java.util.UUID.randomUUID.toString
}

