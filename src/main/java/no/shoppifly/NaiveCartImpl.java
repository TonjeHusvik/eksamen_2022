package no.shoppifly;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
class NaiveCartImpl implements CartService {

    private final Map<String, Cart> shoppingCarts = new HashMap<>();

    private MeterRegistry meterRegistry;

    NaiveCartImpl(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    /*
    "✅carts" - Antall handlekurver på et gitt tidspunkt i tid - verdien kan gå opp og ned ettersom kunder sjekker ut handlekurver og nye blir laget.
    "✅cartsvalue" - Total sum med penger i handlekurver på et gitt tidspunkt i tid - verdien kan gå opp og ned ettersom kunder sjekker ut handlekurver og nye blir laget.
    "✅checkouts" - Totalt antall handlevogner er blitt sjekket ut
    "✅checkout_latency" - Gjennomsnittlig responstid for Checkout metoden i Controller-klassen.
     */

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

    @Timed
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
    public float total() {
        return shoppingCarts.values().stream()
                .flatMap(c -> c.getItems().stream()
                        .map(i -> i.getUnitPrice() * i.getQty()))
                .reduce(0f, Float::sum);
    }

    /** Denne meter-typen "Gauge" rapporterer en verdi hver gang noen kaller "size" metoden på
     * Verdisettet til HashMap
     *
     * @param applicationReadyEvent */
    /*public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //antall handlekurver
        Gauge.builder("carts", shoppingCarts,
                b -> b.values().size()).register(meterRegistry);

        //sum penger
        Gauge.builder("cartsvalue", shoppingCarts,
                        b -> b.values().stream()
                                .flatMap(c -> c.getItems().stream().map(Item::getUnitPrice))
                                .mapToDouble(Float::doubleValue)
                                .sum())
                .register(meterRegistry);
    }*/
}
