package com.safaricom.kyc.temporal.impl;

import com.safaricom.kyc.domain.UserInfo;
import com.safaricom.kyc.domain.UserService;
import com.safaricom.kyc.temporal.KYCActivities;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KYCActivitiesImpl implements KYCActivities {

    private final UserService userService;
    @Override
    public UserInfo saveUser(UserInfo userInfo) {
        return userService.createUser(userInfo);
    }

    @Override
    public Boolean verifyUserInfo(UserInfo userInfo) {
        log.info("Verifying user info");
        return  true;
    }

    @Override
    public Boolean approveKYCDetails(UserInfo userInfo) {
      log.info("Approving KYC Details");
      return true;
    }


}
