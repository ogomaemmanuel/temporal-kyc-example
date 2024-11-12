package com.safaricom.kyc.domain;


import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserInfoRepository userInfoRepository;

    public UserService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfo createUser(UserInfo userInfo) {
        return this.userInfoRepository.save(userInfo);
    }


}
