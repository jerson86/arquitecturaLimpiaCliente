package com.pragma.cliente.service;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import com.pragma.cliente.entity.ImagenMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class NextSequenceService {
    @Autowired
    private MongoOperations mongo;

    public String getNextSequence(String seqName)
    {
        ImagenMongo counter = mongo.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq",1),
                options().returnNew(true).upsert(true),
                ImagenMongo.class);
        return String.valueOf(counter.getSeq());
    }
}
