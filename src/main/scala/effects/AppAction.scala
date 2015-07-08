package effects

sealed trait AppAction[A]

case class Insert(key: String, value: String) extends AppAction[Unit]
case class Update(key: String, value: String) extends AppAction[Option[String]]
case class Get(key: String) extends AppAction[Option[String]]
case class Delete(key: String) extends AppAction[Unit]
case class LogInfo(message: String) extends AppAction[Unit]
case object GetLongNameConfig extends AppAction[Option[Int]]
