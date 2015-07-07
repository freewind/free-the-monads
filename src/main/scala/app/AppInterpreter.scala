package app

import effects.{Update, Delete, Get, Insert, AppAction, Interpreter}

class AppInterpreter extends Interpreter {

  var kvstore = Map.empty[String, String]

  override def apply[A](action: AppAction[A]): A = action match {
    case Insert(k, v) => kvstore += (k -> v)
    case Get(k) => kvstore.get(k)
    case Delete(k) => kvstore -= k
    case Update(k, v) =>
      val old = kvstore.get(k)
      kvstore += (k -> v)
      old
  }

}