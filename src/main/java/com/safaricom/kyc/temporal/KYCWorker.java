package com.safaricom.kyc.temporal;


import com.safaricom.kyc.temporal.impl.KYCWorkflowImpl;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KYCWorker {
    private final WorkerFactory workerFactory;
    private final KYCActivities kycActivities;

    @PostConstruct
    public void init() {
        Worker worker = workerFactory.newWorker("KYC_QUEUE");
        worker.registerWorkflowImplementationTypes(KYCWorkflowImpl.class);
        worker.registerActivitiesImplementations(kycActivities);
        workerFactory.start();
    }
    @PreDestroy
    public void destroy() {
        if (workerFactory != null) {
            workerFactory.shutdown();
        }
    }

}
