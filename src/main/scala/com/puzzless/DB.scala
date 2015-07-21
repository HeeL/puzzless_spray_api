package com.puzzless

import com.puzzless.models.Category
import sorm._


object Db extends Instance(
  entities = Set(Entity[Category]()),
  url = "jdbc:postgresql://localhost/puzzless_spray_api",
  user = "heel",
  password = "",
  initMode = InitMode.Create
)
