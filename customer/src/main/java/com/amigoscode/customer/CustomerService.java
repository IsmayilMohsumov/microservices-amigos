package com.amigoscode.customer;

import com.amigoscode.amqp.RabbitMQMessageProducer;
import com.amigoscode.commons.fraud.FraudCheckResponse;
import com.amigoscode.commons.fraud.FraudClient;
import com.amigoscode.commons.notification.NotificationClient;
import com.amigoscode.commons.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer messageProducer;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email()).build();

        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());


        if (fraudCheckResponse.isFraudster())
            throw new IllegalStateException("Fraudster");

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to the software...",
                        customer.getFirstName()));

        messageProducer.publish(notificationRequest,"internal.exchange","internal.notification.routing-key");
    }
}
