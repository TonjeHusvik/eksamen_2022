package no.shoppifly;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@RestController()
public class ShoppingCartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private MeterRegistry meterRegistry;

    ShoppingCartController( MeterRegistry meterRegistry, CartService cartService) {
        this.meterRegistry = meterRegistry;
        this.cartService = cartService;
    }

    @GetMapping(path = "/cart/{id}")
    public Cart getCart(@PathVariable String id) {
        return cartService.getCart(id);
    }

    @Timed
    @PostMapping(path = "/cart/checkout")
    public String checkout(@RequestBody Cart cart) {
        long startTime = System.currentTimeMillis();
        meterRegistry.counter("checkout").increment();
        meterRegistry.timer("checkout_latency")
                .record(Duration.ofMillis(System.currentTimeMillis() - startTime));
        return cartService.checkout(cart);
    }

    @PostMapping(path = "/cart")
    public Cart updateCart(@RequestBody Cart cart) {
        return cartService.update(cart);
    }

    @GetMapping(path = "/carts")
    public List<String> getAllCarts() {
        return cartService.getAllsCarts();
    }
}