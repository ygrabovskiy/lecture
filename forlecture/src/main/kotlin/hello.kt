fun main(args: Array<String>) {
    val x = 1_000

    println(foo("Hello, World!") + x)
}

fun foo(s: String): String {
    return s +s
}