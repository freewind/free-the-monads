package app

import effects._

case class Config(longNameSize: Int)

class AppInterpreter(config: Option[Config] = None) extends Interpreter {

  var kvstore = Map.empty[String, String]

  override def apply[A](action: AppAction[A]): A = action match {
    case Insert(k, v) => kvstore += (k -> v)
    case Get(k) => kvstore.get(k)
    case Delete(k) => kvstore -= k
    case Update(k, v) =>
      val old = kvstore.get(k)
      kvstore += (k -> v)
      old
    case LogInfo(msg) => println(msg)
    case GetLongNameConfig => config.map(_.longNameSize)
    case FailedWithMessage(msg) => throw new RuntimeException(msg)
  }

}
