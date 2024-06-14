package com.food.ordering.system.order.service.messaging.listener.kafka;

import com.food.ordering.system.kafka.consumer.KafkaConsumer;
import com.food.ordering.system.kafka.order.avro.model.OrderApprovalStatus;
import com.food.ordering.system.kafka.order.avro.model.RestaurantApprovalResponseAvroModel;
import com.food.ordering.system.order.service.domain.ports.input.message.listener.restaurantapproval.RestaurantApprovalResponseMessageListener;
import com.food.ordering.system.order.service.messaging.mapper.OrderMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.food.ordering.system.order.service.domain.entity.Order.DELIMITER_COMMA;

@Slf4j
@Component
public class RestaurantApprovalResponseKafkaListener implements KafkaConsumer<RestaurantApprovalResponseAvroModel> {

    private final RestaurantApprovalResponseMessageListener listener;
    private final OrderMessagingDataMapper mapper;

    public RestaurantApprovalResponseKafkaListener(RestaurantApprovalResponseMessageListener listener,
                                                   OrderMessagingDataMapper mapper) {
        this.listener = listener;
        this.mapper = mapper;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.restaurant-approval-consumer-group-id}",
        topics = "${order-service.restaurant-approval-response-topic-name}")
    public void receive(@Payload List<RestaurantApprovalResponseAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        messages.forEach(restaurantApprovalResponseAvroModel -> {
            if (OrderApprovalStatus.APPROVED == restaurantApprovalResponseAvroModel.getOrderApprovalStatus()) {
                log.info("Processing approved order for order id: {}",
                        restaurantApprovalResponseAvroModel.getOrderId());
                listener.orderApproved(mapper
                        .approvalResponseAvroModelToRestaurantApprovalResponse(restaurantApprovalResponseAvroModel));
            } else if(OrderApprovalStatus.REJECTED == restaurantApprovalResponseAvroModel.getOrderApprovalStatus()) {
                log.info("Process rejected order id: {}, with failure messages: {}",
                        restaurantApprovalResponseAvroModel.getOrderId(),
                        String.join(DELIMITER_COMMA,
                                restaurantApprovalResponseAvroModel.getFailureMessages()));
                listener.orderRejected(mapper
                        .approvalResponseAvroModelToRestaurantApprovalResponse(restaurantApprovalResponseAvroModel));
            }
        });

    }
}
