package backend.pantry;


import backend.order.Order;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


public class InventoryManager {
    private final HashMap<Ingredient, Integer> ingredientStock = new HashMap<>();
    private BigDecimal balance;

    public InventoryManager(
            HashMap<Ingredient, Integer> ingredientStock,
            BigDecimal balance
    ){
        if (balance.scale() > 2){
            throw new IllegalArgumentException("Balance precision can be at " +
                    "most 2 decimal digits.");
        }
        for (int value: ingredientStock.values()){
            if (value < 0) {
                throw new IllegalArgumentException("Item count in stock, " +
                        "can't be negative");
            }
        }
        this.ingredientStock.putAll(ingredientStock);
        this.balance = balance;
    }

    public BigDecimal getBalance(){
        return balance;
    }

    public void setBalance(BigDecimal balance){
        if (balance.scale() > 2){
            throw new IllegalArgumentException("Balance precision can be at " +
                    "most 2 decimal digits.");
        }
        this.balance = balance;
    }

    public boolean canProcessOrder(Order order){
        HashMap<Ingredient, Integer> orderIngredientCounts =
                order.getAllIngredientCounts();
        Ingredient ingredient;
        int count;
        for (Map.Entry<Ingredient, Integer> entry: orderIngredientCounts.entrySet()){
            ingredient = entry.getKey();
            count = entry.getValue();

            if (!ingredientStock.containsKey(ingredient)){
                return false;
            } else if (count > ingredientStock.get(ingredient)) {
                return false;
            }
        }
        return true;
    }

    public void processOrder(Order order){
        HashMap<Ingredient, Integer> orderIngredientCounts =
                order.getAllIngredientCounts();
        Ingredient ingredient;
        int count;
        int remainingIngredientCount;
        for(Map.Entry<Ingredient, Integer> entry: orderIngredientCounts.entrySet()){
            ingredient = entry.getKey();
            count = entry.getValue();
            remainingIngredientCount = ingredientStock.get(ingredient) - count;
            ingredientStock.put(ingredient, remainingIngredientCount);
        }

        BigDecimal revenue = order.calcPrice();
        balance = balance.add(revenue);
    }

    /**
     * Return one of the missing ingredients, or insufficient in stock for the
     * given order. If all ingredients are available it will return null.
     */
    public Ingredient getMissingIngredient(Order order){
        HashMap<Ingredient, Integer> orderIngredientCounts =
                order.getAllIngredientCounts();
        Ingredient ingredient;
        int count;
        for (Map.Entry<Ingredient, Integer> entry: orderIngredientCounts.entrySet()){
            ingredient = entry.getKey();
            count = entry.getValue();

            if (!ingredientStock.containsKey(ingredient)){
                return ingredient;
            } else if (count > ingredientStock.get(ingredient)) {
                return ingredient;
            }
        }
        return null;
    }

    public boolean canBuyIngredient(Ingredient ingredient, int count){
        BigDecimal ingredientPrice = ingredient.getPrice();
        BigDecimal purchaseCost = ingredientPrice.multiply(new BigDecimal(count));
        BigDecimal remainingBalance = balance.subtract(purchaseCost);
        if (remainingBalance.doubleValue() >= 0){
            return true;
        } else {
            return false;
        }
    }

    public void buyIngredient(Ingredient ingredient, int count){
        BigDecimal ingredientPrice = ingredient.getPrice();
        BigDecimal purchaseCost = ingredientPrice.multiply(new BigDecimal(count));
        BigDecimal remainingBalance = balance.subtract(purchaseCost);
        balance = remainingBalance;

        int currentCount = ingredientStock.get(ingredient);
        ingredientStock.put(ingredient, currentCount + count);
    }

    public String toCsv(){
        StringBuilder stringBuilder = new StringBuilder();
        Ingredient ingredient;
        int count;

        for (Map.Entry<Ingredient, Integer> entry: ingredientStock.entrySet()){
            ingredient = entry.getKey();
            count = entry.getValue();
            stringBuilder.append(ingredient.getName());
            stringBuilder.append(",");
            stringBuilder.append(count);
            stringBuilder.append("\n");
        }
        stringBuilder.append("Balance,");
        stringBuilder.append(balance);

        return String.valueOf(stringBuilder);
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        Ingredient ingredient;
        int count;
        for (Map.Entry<Ingredient, Integer> entry: ingredientStock.entrySet()){
            ingredient = entry.getKey();
            count = entry.getValue();
            result.append(String.format("%s: %d, ",  ingredient.getName(), count));
        }
        result.append("Balance: $");
        result.append(balance.doubleValue());
        return String.valueOf(result);
    }
}

