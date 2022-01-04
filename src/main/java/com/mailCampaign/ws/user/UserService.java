package com.mailCampaign.ws.user;


import org.springframework.stereotype.Service;


@Service
public class UserService {

    UserRepository userRepository;


    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }
}
