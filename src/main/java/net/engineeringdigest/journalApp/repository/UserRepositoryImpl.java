package net.engineeringdigest.journalApp.repository;

import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.schema.JsonSchemaObject;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<User> listUserForSA(){
        Query query = new Query();
        Criteria emailCriteria = Criteria.where("email")
                .regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")
                .exists(true)
                .ne(null)
                .ne("");
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        //  More Example
//        query.addCriteria(Criteria.where("sentimentAnalysis").type(JsonSchemaObject.Type.BsonType.BOOLEAN));
//        query.addCriteria(Criteria.where("roles").in("ADMIN","USER"));
//        query.addCriteria(Criteria.where("userName").nin("Rajat","Shanu"));

//        Criteria criteria = new Criteria();
//        query.addCriteria(criteria.orOperator(
//                Criteria.where("email").exists(true),
//                Criteria.where("sentimentAnalysis").exists(true)));
//        List<User> users = mongoTemplate.find(query, User.class);
//        return users;
//        also we have less than .lt and lte greater than gt and gte (greater than equal to)
        query.addCriteria(emailCriteria);
        return mongoTemplate.find(query, User.class);
    }
}
