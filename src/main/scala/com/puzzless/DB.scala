package com.puzzless

import com.puzzless.models._
import sorm._


object Db extends Instance(
  entities = Set() + Entity[Category]() + Entity[Riddle](),
  url = "jdbc:postgresql://localhost/puzzless_spray_api",
  user = "heel",
  password = "",
  initMode = InitMode.DropAllCreate
) {

  // seeding
  Category.create("Math")
  Category.create("Logic")
  Riddle.create("Riddle title", "Text of the riddle", "The answer")

  // generate random uuid
  def uuid = java.util.UUID.randomUUID.toString
}

