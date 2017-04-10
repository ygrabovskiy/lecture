/**
  * Created by ygrabovskiy on 4/10/17.
  */
object X extends App{

    Stream.from(0).scanLeft((1 to 1000000).toArray)((s, i) => {
        println(i)
        s.update(0, 9)
        s
    }).foreach{ s =>
        ()
    }

    trait Node
    object Leaf extends Node
    case class TreeNode(left: Node, right: Node) extends Node

    val tree = TreeNode(
        Leaf,
        TreeNode(
            TreeNode(
                Leaf,
                Leaf
            ),
            Leaf
        )
    )

    tree match {
        case TreeNode(TreeNode(_, Leaf), Leaf) => ()
        case TreeNode(Leaf, _) => () //will match
    }

    val x: Any = 0
    x match {
        case 42 => println(42)
        case "string" => println("строка")
        case i: Int => println("Всего лишь инт")
        case (_: String, b: Int, null) => println("tuple")
        case  head :: rest  => println(s"head is $head")
    }

}