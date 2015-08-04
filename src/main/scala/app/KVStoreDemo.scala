package app

import effects.Script

object KVStoreDemo extends App {

  def insertAndGet() = for {
    _ <- Script.insert("name", "Freewind")
    value <- Script.get("name")
  } yield value

  def insertAndDelete() = for {
    _ <- Script.insert("name", "Freewind")
    _ <- Script.delete("name")
    value <- Script.get("name")
  } yield value

  def insertAndUpdateAndDelete() = for {
    _ <- Script.insert("name", "Freewind1")
    oriValue <- Script.update("name", "Freewind2")
    _ <- Script.delete("name")
    finalValue <- Script.get("name")
  } yield (oriValue, finalValue)

  def insertAndGetWithFailure() = for {
    _ <- Script.insert("name", "Freewind")
    _ <- Script.logInfo("Inserted :)")
    _ <- Script.failedWithMessage("Sorry, failed!!!")
    value <- Script.get("name")
  } yield value

  println("======== insertAndGet ==========")
  println(insertAndGet().runWith(new AppInterpreter))

  println("======== insertAndDelete ==========")
  println(insertAndDelete().runWith(new AppInterpreter))

  println("======== insertAndUpdateAndDelete ========")
  println(insertAndUpdateAndDelete().runWith(new AppInterpreter))

  println("======== insertAndGetWithFailure ========")
  println(insertAndGetWithFailure().runWith(new AppInterpreter))

}


