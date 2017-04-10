package pictures

import java.awt.Color
import java.io.File

import com.gif4j.light.{GifEncoder, GifFrame, GifImage}

/**
  * Created by ygrabovskiy on 4/7/17.
  */
object Workbench
    extends App {
    val maxX = 100
    val maxY = 100;
    val writer = new GifImage()
    writer.setDefaultDelay(2)
    writer.setLoopNumber(0)
    val initialDots = Set(Dot(50, 50))
    //createDots()
    val dotsEvolutions = Stream.continually(0)
                         .scanLeft(initialDots)((old, _) => dotsEvolution(old))
                         .take(300)
    val frames = dotsEvolutions.map(createImageWithDots) // get a sequence of BufferedImage instances
    for (frame <- frames) {
        writer.addGifFrame(new GifFrame(frame))
    }
    GifEncoder.encode(writer, new File("animated.gif"))

    import java.awt.image.BufferedImage

    private def createDots(): Set[Dot] = {
        (0 to 100).map(i => Dot(i, i)).toSet
    }

    private def dotsEvolution(oldDots: Set[Dot]) = {
        (oldDots.flatMap(d => Seq(
            Dot(d.x - 1, d.y),
            Dot(d.x + 1, d.y),
            Dot(d.x, d.y - 1),
            Dot(d.x, d.y + 1)

        )) -- oldDots).filterNot(d => d.y > maxY || d.x > maxX)

    }
    private def theGameEvolution(oldDots: Set[Dot]) = {

        (oldDots.flatMap(d => Seq(
            Dot(d.x - 1, d.y),
            Dot(d.x + 1, d.y),
            Dot(d.x, d.y - 1),
            Dot(d.x, d.y + 1)

        )) -- oldDots).filterNot(d => d.y > maxY || d.x > maxX)

    }

    private def createImageWithDots(dots: Set[Dot]) = {
        val bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB)
        val g = bufferedImage.getGraphics
        dots.foreach { d =>
            g.drawRect(d.x, d.y, 0, 0)
        }

        bufferedImage
    }

}

case class Dot(x: Int, y: Int)
