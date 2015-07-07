package effects

import scalaz._
import Scalaz._

trait Interpreter extends (AppAction ~> Id) {
  def apply[A](action: AppAction[A]): A
}
