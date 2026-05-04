package backend.menu;

import backend.pantry.Ingredient;

import java.math.BigDecimal;
import java.util.HashMap;

public class HotFood extends MenuItem{
    // $5 = 500 cents
    private final static BigDecimal priceFries = new BigDecimal(5);
    private boolean hasFries;


    public HotFood(String name, BigDecimal price,
                   HashMap<Ingredient, Integer> ingredientCounts, boolean hasFries){
        super(name, price, ingredientCounts);
        this.hasFries = hasFries;

        if (hasFries){
            this.setPrice(price.add(priceFries));
        }
    }

    public boolean getHasFries() {
        return hasFries;
    }

    public void setHasFries(boolean hasFries) {
        BigDecimal currentPrice = this.getPrice();
        if (this.hasFries && !hasFries){
            this.setPrice(currentPrice.subtract(priceFries));
        } else if (!this.hasFries && hasFries) {
            this.setPrice(currentPrice.add(priceFries));
        }
        this.hasFries = hasFries;
    }

    @Override
    public String toString(){
        if (hasFries){
            return super.toString() + " with fries";
        } else {
            return super.toString();
        }
    }

    public static void main(String[] args) {
        Ingredient ing1 = new Ingredient("Potato", new BigDecimal("3.22"));
        Ingredient ing2 = new Ingredient("Tomoto", new BigDecimal("3.17"));
        HashMap<Ingredient, Integer> ingredientIntegerHashMap = new HashMap<>();
        ingredientIntegerHashMap.put(ing1, 3);
        ingredientIntegerHashMap.put(ing2, 4);
        HotFood hotFood = new HotFood("Hf1", new BigDecimal("22.37"),
                ingredientIntegerHashMap,
                true);
        System.out.println(hotFood.getPrice());
        hotFood.setHasFries(false);
        System.out.println(hotFood.getPrice());
        hotFood.setHasFries(false);
        System.out.println(hotFood.getPrice());
        hotFood.setHasFries(true);
        System.out.println(hotFood.getPrice());
    }
}
