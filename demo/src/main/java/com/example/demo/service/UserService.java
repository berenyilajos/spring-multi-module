package com.example.demo.service;

import com.example.demo.bd.BDUser;
import com.example.jpademo.dao.UserEntityDao;
import com.example.demo.helper.EntityHelper;
import com.example.jpademo.repository.UserRepository;
import com.example.jpademo.transactions.DemoTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEntityDao userEntityDao;

    public List<BDUser> getAllUsers(){
        return EntityHelper.entityToBd(userRepository.findAll());
    }

    public BDUser getUser(long id){
        return EntityHelper.entityToBd(userRepository.findOneById(id));
    }

    public Optional<BDUser> getUserOptional(long id){
        return EntityHelper.entityToBd(userRepository.findById(id));
    }

    public void addUser(BDUser bdUser){
        userRepository.save(EntityHelper.bdToEntity(bdUser));
    }

    @DemoTransactional
    public void saveUser(BDUser bdUser) {
        userEntityDao.save(EntityHelper.bdToEntity(bdUser));
    }

    public void delete(long id){
        userRepository.deleteById(id);
    }

    public List<BDUser> getUserByName(String name) {
        return EntityHelper.entityToBd(userRepository.findByName(name));
    }

    public List<BDUser> getByUserName(String username) {
        return EntityHelper.entityToBd(userRepository.getByUsername(username));
    }
}
