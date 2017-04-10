import java.io.{File, FileInputStream}

trait IOUtils
{
  def ensureClose[T <: {def close()}, R](resource: T)(block: T => R): R =
  {
    try { block(resource) } finally { if (resource != null) resource.close() }
  }
}

object ScalaApp extends IOUtils {
  def is = new FileInputStream(new File(""))
  def main(args: Array[String]): Unit = {

    ensureClose(is){stream =>

        stream.read()

    }
  }
}

