package app

import effects.Script

object BigKVStoreDemo extends App {

  private def isLongName(name: String): Script[Boolean] = for {
    size <- Script.getLongNameConfig
  } yield size.exists(name.length > _)

  def initStore() = for {
    _ <- Script.insert("name1", "Freewind")
    _ <- Script.insert("name2", "Lily")
    _ <- Script.insert("name3", "Sarah")
    _ <- Script.insert("name4", "Long-long-long-name")
    _ <- Script.insert("name5", "Sea1234")
  } yield ()

  // FIXME how to make this method looks better? e.g.
  // for {
  //   ...
  //   ...
  //   ...
  // } yield ???
  def upcaseLongName(key: String): Script[Option[String]] = {
    Script.get(key) flatMap {
      case Some(n) => for {
        isLong <- isLongName(n)
      } yield isLong match {
          case true => Some(n.toUpperCase)
          case false => Some(n)
        }
      case _ => Script.pure(None)
    }
  }

  val config = Config(7)
  val interpreter = new AppInterpreter(Some(config))
  initStore().runWith(interpreter)

  println(upcaseLongName("name1").runWith(interpreter))
  println(upcaseLongName("name2").runWith(interpreter))
  println(upcaseLongName("name3").runWith(interpreter))
  println(upcaseLongName("name4").runWith(interpreter))
  println(upcaseLongName("name5").runWith(interpreter))
  println(upcaseLongName("name100").runWith(interpreter))


}
