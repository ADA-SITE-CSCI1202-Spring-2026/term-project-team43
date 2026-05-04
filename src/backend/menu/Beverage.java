package backend.menu;

import backend.pantry.Ingredient;

import java.math.BigDecimal;
import java.util.HashMap;

public class Beverage extends MenuItem{
    private boolean hasIce;

    public Beverage(String name, BigDecimal price,
                    HashMap<Ingredient, Integer> ingredientCounts,
                    boolean hasIce
    ){
        super(name, price, ingredientCounts);
        this.hasIce = hasIce;
    }

    public boolean getHasIce(){
        return hasIce;
    }

    public void setHasIce(boolean hasIce){
        this.hasIce = hasIce;
    }

    @Override
    public String toString(){
        if (hasIce){
            return super.toString() + " with ice";
        } else {
            return super.toString();
        }
    }

}
