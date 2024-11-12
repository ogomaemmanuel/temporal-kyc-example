package com.safaricom.kyc.web;


import com.safaricom.kyc.domain.UserInfo;
import com.safaricom.kyc.temporal.KYCWorkflow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/api/kyc")
@RequiredArgsConstructor
public class KYCController {
    private final WorkflowClient workflowClient;
    @PostMapping
    public ResponseEntity<?> submitKycInfo(@RequestBody UserInfo userInfo) {
        KYCWorkflow kycWorkflow = workflowClient.newWorkflowStub(KYCWorkflow.class, WorkflowOptions.
                newBuilder()
                .setTaskQueue("KYC_QUEUE")
                .build());
        WorkflowClient.start(kycWorkflow::start, userInfo);
        return ResponseEntity.ok("Workflow started successfully");
    }

    @PostMapping("/progress/{workflowId}")
    public ResponseEntity<?> progressKycWorkflow(@PathVariable String workflowId){
        KYCWorkflow workFlow = workflowClient.newWorkflowStub(KYCWorkflow.class, workflowId);
        workFlow.progress();
        return ResponseEntity.ok("Workflow progressed successfully");
    }

}
