package ru.urfu.controllers

import java.util
import java.util.Date

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RequestParam, RestController}
import ru.urfu.model.{Message, User}
import ru.urfu.storage.{MessageStorage, UserStorage}

@RestController
@RequestMapping(Array("/api")) class RestApiController {
    @Autowired private var messages: MessageStorage = null
    @Autowired private var users: UserStorage = null
    @RequestMapping(value = Array("/addMessage"), method = Array(RequestMethod.POST))
    def addMessage(@RequestParam(defaultValue = "") text: String): String =
        if (1 <= text.length && text.length <= 140) {
            val curUser = users.findUser(SecurityContextHolder.getContext.getAuthentication.getPrincipal.asInstanceOf[UserDetails].getUsername).get
            val message = new Message(curUser, new Date, text)
            messages.addMessage(message)
            "ok"
        }
        else
            "error"

    @RequestMapping(
        value = Array("/getMessages"),
        method = Array(RequestMethod.POST)
    )
    def getMessages(
                       @RequestParam(defaultValue = "20") count: Int,
                       @RequestParam(defaultValue = "99999999") maxID: Long
                   ): util.List[Message] = messages.getMessages(count, maxID)

    @RequestMapping(
        value = Array("/getHashtags"),
        method = Array(RequestMethod.POST)
    )
    def getHashtags(): String = {
        """[{"hashtag": "test", "count": "1"}]"""
    }

    @RequestMapping(value = Array("/removeMessage"), method = Array(RequestMethod.POST))
    def removeMessage(@RequestParam(defaultValue = "-1") id: Long): String = {
        val message = messages.getMessageById(id)
        if (message.isPresent) {
            val curUser = users.findUser(SecurityContextHolder.getContext.getAuthentication.getPrincipal.asInstanceOf[UserDetails].getUsername).get
            if (message.get.getUser == curUser) messages.removeById(id)
        }
        "ok"
    }

    @RequestMapping(value = Array("/getMyself"), method = Array(RequestMethod.POST))
    def getMessages: User = users.findUser(SecurityContextHolder.getContext.getAuthentication.getPrincipal.asInstanceOf[UserDetails].getUsername).get
}
