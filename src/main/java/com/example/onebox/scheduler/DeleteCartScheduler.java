package com.example.onebox.scheduler;

import com.example.onebox.domain.Cart;
import com.example.onebox.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Slf4j
public class DeleteCartScheduler {

    @Autowired
    private CartRepository cartRepository;

    @Value("${cart.scheduler.enabled}")
    private boolean isEnabled;

    @Value("${cart.scheduler.expire.minutes}")
    private Integer expiresInMinutes;

    @Scheduled(fixedDelayString = "${cart.scheduler.delay}" )
    public void execute() {
        if (isEnabled) {
            log.info("Scheduled task is disabled");
            return;
        }

        log.info("Starting scheduled task");
        cartRepository.findAll().stream()
                .filter(this::isExpired)
                .forEach(cart -> {
                    log.info("Deleting cart {}", cart.getId());
                    cartRepository.deleteById(cart.getId());
                });
        log.info("Ending scheduled task");
    }

    private boolean isExpired(Cart cart) {
        return cart.getCreationDate()
                .plusMinutes(expiresInMinutes)
                .isBefore(LocalDateTime.now());
    }

}
