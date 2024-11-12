package com.safaricom.kyc.temporal;


import com.safaricom.kyc.domain.UserInfo;
import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface KYCActivities {

    public UserInfo saveUser(UserInfo userInfo);

    public Boolean verifyUserInfo(UserInfo userInfo);

    public Boolean approveKYCDetails(UserInfo userInfo);

}
