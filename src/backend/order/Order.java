package backend.order;

import backend.menu.Beverage;
import backend.menu.HotFood;
import backend.pantry.Ingredient;

import java.math.BigDecimal;
import java.util.HashMap;

public class Order {
    private HotFood hotFood = null;
    private Beverage beverage = null;

    public Order(HotFood hotFood, Beverage beverage){
        this.hotFood = hotFood;
        this.beverage = beverage;
    }

    public Order(HotFood hotFood){
        this.hotFood = hotFood;
    }

    public Order(Beverage beverage){
        this.beverage = beverage;
    }

    public HotFood getHotFood() {
        return hotFood;
    }

    public void setHotFood(HotFood hotFood) {
        this.hotFood = hotFood;
    }

    public Beverage getBeverage() {
        return beverage;
    }

    public void setBeverage(Beverage beverage) {
        this.beverage = beverage;
    }

    public HashMap<Ingredient, Integer> getAllIngredientCounts(){
        HashMap<Ingredient, Integer> allIngredientCounts = new HashMap<>();
        if (hotFood != null){
            allIngredientCounts.putAll(hotFood.getIngredientCounts());
        }
        if (beverage != null){
            allIngredientCounts.putAll(beverage.getIngredientCounts());
        }
        return allIngredientCounts;
    }

    public BigDecimal calcPrice(){
        BigDecimal totalPrice = new BigDecimal(0);
        if (hotFood != null){
            totalPrice = totalPrice.add(hotFood.getPrice());
        }
        if (beverage != null){
            totalPrice = totalPrice.add(beverage.getPrice());
        }
        return totalPrice;
    }

    /*
     * Return a formatted string representation of order. This will be used in
     * writing the orders to csv file. Note the null values will be written as
     * null string. The format is: HootFood,Beverage
     * */
    public String toCsv(){
        String res;
        if (hotFood != null && beverage != null){
            res = hotFood.getName() + "," + beverage.getName();
        }
        else if (hotFood != null){
            res = hotFood.getName() + "," + "null";
        }
        else {
            res = "null" + "," + beverage.getName();
        }
        return res;
    }

    @Override
    public String toString(){
        String res;
        if (hotFood != null && beverage != null){
            res = hotFood.toString() + " and " + beverage.toString();
        } else if (hotFood != null){
            res = hotFood.toString();
        } else {
            res = beverage.toString();
        }
        return res;
    }
}
