package backend.menu;

import backend.pantry.Ingredient;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public abstract class MenuItem {
    private String name;
    private BigDecimal price;
    private final HashMap<Ingredient, Integer> ingredientCounts = new HashMap<>();

    private void validatePrice(BigDecimal price,
                               HashMap<Ingredient, Integer> ingredientCounts
    ){
        // Check if the price has two decimals
        if (price.scale() > 2){
            throw new IllegalArgumentException("Price precision can be at " +
                    "most 2 decimal digits.");
        }
        BigDecimal costToMake = new BigDecimal("0");
        BigDecimal ingredientPrice;
        BigDecimal count;
        for (Map.Entry<Ingredient, Integer> entry: ingredientCounts.entrySet()){
            ingredientPrice = entry.getKey().getPrice();
            count = new BigDecimal(entry.getValue());
            costToMake = costToMake.add(ingredientPrice.multiply(count));
        }
        if (price.compareTo(costToMake) < 0){
            throw new IllegalArgumentException("Price of an item, can't be " +
                    "less than total cost for making it.");
        }
    }

    public MenuItem(String name, BigDecimal price,
                    HashMap<Ingredient, Integer> ingredientCounts){
        validatePrice(price, ingredientCounts);
        for (int value: ingredientCounts.values()){
            if (value < 0) {
                throw new IllegalArgumentException("Ingredient count in Menu " +
                        "Item, can't be negative");
            }
        }
        this.name = name;
        this.price = price;
        this.ingredientCounts.putAll(ingredientCounts);
    }

    public String getName(){
        return name;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public HashMap<Ingredient, Integer> getIngredientCounts(){
        return ingredientCounts;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        validatePrice(price, ingredientCounts);
        this.price = price;
    }

    public void setIngredientCounts(HashMap<Ingredient, Integer> ingredientCounts) {
        this.ingredientCounts.putAll(ingredientCounts);
    }

    @Override
    public String toString(){
        return name;
    }
}
