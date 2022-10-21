package com.caciTechTest.app.utils;

import com.caciTechTest.app.entity.Order;
import com.caciTechTest.app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Utils {

    @Autowired
    OrderRepository orderRepository;

    public String generateUniqueOrderReference() {
        String characterPoolBase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        char[] characterPoolArray = characterPoolBase.toCharArray();
        Order possibleDuplicate;
        String reference;
        Random random = new Random();

        do {
            char[] buf = new char[10];
            for (int idx = 0; idx < buf.length; ++idx)
                buf[idx] = characterPoolArray[random.nextInt(characterPoolArray.length)];
            reference = new String(buf);
            possibleDuplicate = orderRepository.findByReference(reference);
        } while (possibleDuplicate != null);
        return reference;
    }
}
