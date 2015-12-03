package com.example.message;

import org.bson.Document;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import reactor.rx.Streams;

import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

@Repository
public class MessageRepository {
    private static final Logger logger = LoggerFactory
            .getLogger(MessageRepository.class);

    private final MongoCollection<Document> collection;

    @Autowired
    public MessageRepository(MongoDatabase database) {
        this.collection = database.getCollection("message");
    }

    public Publisher<Long> insert(Publisher<Message> messagePublisher) {
        return Streams.wrap(messagePublisher)
                .flatMap(
                        message -> {
                            logger.info("insert {}", message);
                            return this.collection
                                    .insertOne(new Document("text", message
                                            .getText()));
                        }).reduce(0L, (x, y) -> x + 1);
    }

    public Publisher<Message> findAll() {
        return Streams.wrap(this.collection.find()).map(
                doc -> new Message(doc.getString("text")));
    }
}
