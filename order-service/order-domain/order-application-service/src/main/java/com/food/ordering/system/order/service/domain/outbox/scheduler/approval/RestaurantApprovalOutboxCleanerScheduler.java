package com.food.ordering.system.order.service.domain.outbox.scheduler.approval;

import com.food.ordering.outbox.OutboxScheduler;
import com.food.ordering.outbox.OutboxStatus;
import com.food.ordering.system.order.service.domain.outbox.model.approval.OrderApprovalOutboxMessage;
import com.food.ordering.system.saga.SagaStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RestaurantApprovalOutboxCleanerScheduler implements OutboxScheduler {

    private final ApprovalOutboxHelper approvalOutboxHelper;

    public RestaurantApprovalOutboxCleanerScheduler(ApprovalOutboxHelper approvalOutboxHelper) {
        this.approvalOutboxHelper = approvalOutboxHelper;
    }

    @Override
    @Scheduled(cron = "@midnight")
    public void processOutboxMessage() {
        Optional<List<OrderApprovalOutboxMessage>> status = approvalOutboxHelper.getApprovalOutboxMessageByOutboxStatusAndSagaStatus(
                OutboxStatus.COMPLETED,
                SagaStatus.SUCCEEDED,
                SagaStatus.FAILED,
                SagaStatus.COMPENSATED
        );

        if (status.isPresent()) {
            List<OrderApprovalOutboxMessage> orderApprovalOutboxMessages = status.get();
            log.info("Received {} OrderApprovalOutboxMessage for clean-up. the payloads: {}",
                    orderApprovalOutboxMessages.size(),
                    orderApprovalOutboxMessages.stream().map(OrderApprovalOutboxMessage::getPayload)
                            .collect(Collectors.joining("\n")));
            approvalOutboxHelper.deleteApprovalOutboxMessageByOutboxStatusAndSagaStatus(
                    OutboxStatus.COMPLETED,
                    SagaStatus.SUCCEEDED,
                    SagaStatus.FAILED,
                    SagaStatus.COMPENSATED);
            log.info("{} OrderApprovalOutboxMessage deleted", orderApprovalOutboxMessages.size());
        }
    }
}
