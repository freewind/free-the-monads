package effects

import scalaz._

object Script {

  def insert(key: String, value: String): Script[Unit] = toScript(Insert(key, value))
  def update(key: String, value: String): Script[Option[String]] = toScript(Update(key, value))
  def get(key: String): Script[Option[String]] = toScript(Get(key))
  def delete(key: String): Script[Unit] = toScript(Delete(key))

  private def toScript[A](appAction: AppAction[A]): Script[A] = Free.liftFC(appAction)

}
