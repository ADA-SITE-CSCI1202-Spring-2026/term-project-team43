package backend.pantry;

import java.math.BigDecimal;
import java.util.Objects;

public class Ingredient {
    private String name;
    private BigDecimal price;

    private static void validatePrice(BigDecimal price){
        // Check if the price has two decimals
        if (price.scale() > 2){
            throw new IllegalArgumentException("Price precision can be at " +
                    "most 2 decimal digits.");
        }

        if (price.doubleValue() <= 0){
            throw new IllegalArgumentException("Price of an ingredient must " +
                    "be a positive floating point number");
        }
    }

    public Ingredient(String name, BigDecimal price){
        validatePrice(price);
        this.name = name;
        this.price = price;
    }

    public String getName(){
        return name;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(BigDecimal price){
        validatePrice(price);
        this.price = price;
    }

    @Override
    public boolean equals(Object other){
        if (other == null || other.getClass() != getClass()){
            return false;
        }
        Ingredient otherIngredient = (Ingredient) other;
        return name.equals(otherIngredient.getName()) &&
                price.equals(otherIngredient.getPrice());
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, price.doubleValue());
    }

    @Override
    public String toString(){
        return String.format("%s, for $%.2f", name, price.doubleValue());
    }
}


