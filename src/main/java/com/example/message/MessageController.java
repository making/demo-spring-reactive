package com.example.message;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {
    @Autowired
    MessageRepository messageRepository;

    @ResponseBody
    @RequestMapping(path = "/message", method = RequestMethod.GET)
    Publisher<Message> getAll() {
        return messageRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(path = "/message", method = RequestMethod.POST)
    Publisher<Void> post(@RequestBody Publisher<Message> messagePublisher) {
        return messageRepository.insert(messagePublisher);
    }

}
