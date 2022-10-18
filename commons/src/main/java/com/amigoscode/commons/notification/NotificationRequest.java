package com.amigoscode.commons.notification;

public record NotificationRequest(
        Integer toCustomerId,
        String toCustomerEmail,
        String message
) {
}
