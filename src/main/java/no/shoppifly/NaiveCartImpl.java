package no.shoppifly;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
class NaiveCartImpl implements CartService, ApplicationListener<ApplicationReadyEvent> {

    private final Map<String, Cart> shoppingCarts = new HashMap<>();

    @Autowired
    private MeterRegistry meterRegistry;

    @Override
    public Cart getCart(String id) {
        return shoppingCarts.get(id);
    }

    @Override
    public Cart update(Cart cart) {
        if (cart.getId() == null) {
            cart.setId(UUID.randomUUID().toString());
        }
        shoppingCarts.put(cart.getId(), cart);
        return shoppingCarts.put(cart.getId(), cart);
    }

    @Override
    public String checkout(Cart cart) {
        shoppingCarts.remove(cart.getId());
        return UUID.randomUUID().toString();
    }

    @Override
    public List<String> getAllsCarts() {
        return new ArrayList<>(shoppingCarts.keySet());
    }

    // @author Jim; I'm so proud of this one, took me one week to figure out !!!
    /*public float total() {
        return shoppingCarts.values().stream()
                .flatMap(c -> c.getItems().stream()
                        .map(i -> i.getUnitPrice() * i.getQty()))
                .reduce(0f, Float::sum);
    } */

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Gauge.builder("carts", shoppingCarts,
                b -> b.values().size()).register(meterRegistry);

        Gauge.builder("cartsvalue", shoppingCarts,
                        b -> b.values().stream()
                                .flatMap(c -> c.getItems().stream().map(i -> i.getUnitPrice() * i.getQty()))
                                .mapToDouble(Float::doubleValue)
                                .sum())
                .register(meterRegistry);

    }
}
