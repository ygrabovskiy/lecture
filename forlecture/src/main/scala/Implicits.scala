object Implicits {
    implicit class Reversable(int: Int){
        def reverse: Int = {
           int.toString.reverse.toInt
        }
    }
}
object TestImplicit extends App{
    import Implicits._
    println(1234.reverse)
}
