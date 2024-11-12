package com.safaricom.kyc.temporal;

import com.safaricom.kyc.domain.UserInfo;
import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface KYCWorkflow {

    @WorkflowMethod
    public void start(UserInfo userInfo);

    @SignalMethod
    public void progress();

}
