package com.safaricom.kyc.temporal.impl;

import com.safaricom.kyc.domain.UserInfo;
import com.safaricom.kyc.temporal.KYCActivities;
import com.safaricom.kyc.temporal.KYCWorkflow;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;


public class KYCWorkflowImpl implements KYCWorkflow {


    Boolean approveKyc;

    public final RetryOptions retryOptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(100))
            .setBackoffCoefficient(2)
            .build();

    public final ActivityOptions options = ActivityOptions.newBuilder()
            // Timeout options specify when to automatically timeout Activities if the process is taking too long.
            .setStartToCloseTimeout(Duration.ofSeconds(30))
            .setRetryOptions(retryOptions)
            .build();

    public final KYCActivities activity = Workflow.newActivityStub(KYCActivities.class, options);
    @Override
    public void start(UserInfo userInfo) {
      UserInfo user=  activity.saveUser(userInfo);
      activity.verifyUserInfo(user);
      Workflow.await(()->approveKyc);
      activity.approveKYCDetails(user);
    }

    @Override
    public void progress() {
      this.approveKyc=true;
    }
}
