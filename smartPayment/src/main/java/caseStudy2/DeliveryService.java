package caseStudy2;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class DeliveryService {

    public DeliveryService() {
        System.out.println("DeliveryService Bean Created");
    }

    @PostConstruct
    public void init() {
        System.out.println("Delivery Service Ready");
    }

    public void deliverOrder(String order) {
        System.out.println("Delivering order: " + order);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Delivery Service Closed");
    }
}