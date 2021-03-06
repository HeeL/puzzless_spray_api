package com.puzzless

import akka.actor.Actor
import com.puzzless.apis._

class PuzzlessActor extends Actor with RootApi with CategoryApi with RiddleApi with CommentApi {

  def actorRefFactory = context

  def receive = runRoute(rootRoute ~ categoryRoute ~ riddleRoute ~ commentRoute)

}
