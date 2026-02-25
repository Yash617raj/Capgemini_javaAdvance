package caseStudy2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestaurantService {

    private DeliveryService deliveryService;

    public RestaurantService() {
        System.out.println("RestaurantService Bean Created");
    }
    
    @Autowired
    public void setDeliveryService(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    public void prepareOrder(String order) {
        System.out.println("Preparing order: " + order);
        deliveryService.deliverOrder(order);
    }
}