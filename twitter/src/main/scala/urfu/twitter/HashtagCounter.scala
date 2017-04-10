package urfu.twitter

import ru.urfu.model.Message

object HashtagCounter {

    def topHashtags(msgs: Iterable[Message]): Map[String, Int] = ???


    private def extractHashtags(msg: Message):Seq[String] =  ???

    def calculateTop(tags: Iterable[String]): Map[String, Int] = ???

}
