package no.shoppifly;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController()
public class ShoppingCartController implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private CartService cartService;

    @Autowired
    private MeterRegistry meterRegistry;

    private final Map<String, Cart> shoppingCarts = new HashMap<>();

    ShoppingCartController( MeterRegistry meterRegistry, CartService cartService) {
        this.meterRegistry = meterRegistry;
        this.cartService = cartService;
    }

    @GetMapping(path = "/cart/{id}")
    public Cart getCart(@PathVariable String id) {
        return cartService.getCart(id);
    }
    @Timed
    /*Checks out a shopping cart. Removes the cart, and returns an order ID @return an order ID */
    @PostMapping(path = "/cart/checkout")
    public String checkout(@RequestBody Cart cart) {
        long startTime = System.currentTimeMillis();
        meterRegistry.counter("checkout").increment();
        meterRegistry.timer("checkout_latency")
                .record(Duration.ofMillis(System.currentTimeMillis() - startTime));
        shoppingCarts.remove(cart.getId(), cart);
        return cartService.checkout(cart);
    }

    /** Updates a shopping cart, replacing it's contents if it already exists. If no cart exists (id is null)
     * a new cart is created. @return the updated cart */
    @PostMapping(path = "/cart")
    public Cart updateCart(@RequestBody Cart cart) {
        shoppingCarts.put(cart.getId(), cart);
        return cartService.update(cart);
    }

    /** return all cart IDs @return */
    @GetMapping(path = "/carts")
    public List<String> getAllCarts() {
        return cartService.getAllsCarts();
    }

    /** Denne meter-typen "Gauge" rapporterer en verdi hver gang noen kaller "size" metoden på
     * Verdisettet til HashMap @param applicationReadyEvent */

    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //antall handlekurver
        /*Gauge.builder("carts", shoppingCarts,
                b -> b.values().size()).register(meterRegistry); Fungerer ikke som jeg vil.*/
        Gauge.builder("carts", cartService,
                s -> s.getAllsCarts().size()).register(meterRegistry);

        //sum penger
        Gauge.builder("cartsvalue", shoppingCarts,
                b -> b.values().stream()
                        .flatMap(c -> c.getItems().stream().map(i -> i.getUnitPrice() * i.getQty()))
                        .mapToDouble(Float::doubleValue)
                        .sum())
                .register(meterRegistry);


    }
}