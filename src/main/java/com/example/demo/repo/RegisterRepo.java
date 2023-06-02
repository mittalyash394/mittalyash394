package com.example.demo.repo;

import com.example.demo.entity.RegisterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

//@Repository
public interface RegisterRepo extends MongoRepository<RegisterEntity, String> {


    @Query("{'emailId' : ?0}")
    RegisterEntity findByEmailId(String emailId);
    @Query("{'emailId' : ?0 , 'password' : ?1}")
    RegisterEntity findUserByEmailIdAndPassword(String emailId, String password);

    @Query(value="{'_id' : ?0}", delete = true)
    RegisterEntity deleteUserByUserId(String userId);

}
