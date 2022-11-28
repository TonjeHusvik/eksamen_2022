package no.shoppifly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
public class ShoppingCartController {

    /*@Autowired
    private final CartService cartService;*/

    @Autowired
    private final NaiveCartImpl naiveCart;

    public ShoppingCartController(/*CartService cartService, */NaiveCartImpl naiveCart) {
        //this.cartService = cartService;
        this.naiveCart = naiveCart;
    }

    @GetMapping(path = "/cart/{id}")
    public Cart getCart(@PathVariable String id) {
        return naiveCart.getCart(id);
        //return cartService.getCart(id);
    }

    /**
     * Checks out a shopping cart. Removes the cart, and returns an order ID
     *
     * @return an order ID
     */
    @PostMapping(path = "/cart/checkout")
    public String checkout(@RequestBody Cart cart) {
        return naiveCart.checkout(cart);
        //return cartService.checkout(cart);
    }

    /**
     * Updates a shopping cart, replacing it's contents if it already exists. If no cart exists (id is null)
     * a new cart is created.
     *
     * @return the updated cart
     */
    @PostMapping(path = "/cart")
    public Cart updateCart(@RequestBody Cart cart) {
        return naiveCart.update(cart);
        //return cartService.update(cart);
    }

    /**
     * return all cart IDs
     *
     * @return
     */
    @GetMapping(path = "/carts")
    public List<String> getAllCarts() {
        return naiveCart.getAllsCarts();
        //return cartService.getAllsCarts();
    }


}