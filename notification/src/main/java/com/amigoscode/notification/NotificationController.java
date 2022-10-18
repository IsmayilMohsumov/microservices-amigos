package com.amigoscode.notification;

import com.amigoscode.commons.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/notification")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest notificationRequest){
        log.info("New notification requested... {}",notificationRequest);
        notificationService.send(notificationRequest);
    }

}
