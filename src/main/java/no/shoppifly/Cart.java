package no.shoppifly;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Cart {
    private String id;
    List<Item> items;
}

@Data
class Item {
    private String description;
    private int qty;
    private Float unitPrice;

    public Float getUnitPrice() {
        return unitPrice;
    }

}