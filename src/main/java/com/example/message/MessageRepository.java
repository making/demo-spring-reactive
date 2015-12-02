package com.example.message;

import org.bson.Document;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import reactor.Publishers;
import reactor.rx.Streams;

import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

@Repository
public class MessageRepository {

    private final MongoCollection<Document> collection;

    @Autowired
    public MessageRepository(MongoDatabase database) {
        this.collection = database.getCollection("message");
    }

    public Publisher<Void> insert(Publisher<Message> messagePublisher) {
        return Streams.wrap(messagePublisher).flatMap(
                message -> this.collection
                        .insertOne(new Document("text", message.getText())))
                .flatMap(p -> Publishers.empty());
    }

    public Publisher<Message> findAll() {
        return Streams.wrap(this.collection.find()).map(
                doc -> new Message(doc.getString("text")));
    }
}
