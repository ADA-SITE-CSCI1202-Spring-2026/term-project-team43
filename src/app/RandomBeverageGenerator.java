package app;

import backend.menu.Beverage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomBeverageGenerator implements Runnable{
    private Beverage beverage;
    private final List<Beverage> beverages = new ArrayList<>();

    public RandomBeverageGenerator(List<Beverage> beverages){
        this.beverages.addAll(beverages);
    }

    @Override
    public void run(){
        int randomIdx = ThreadLocalRandom.current().nextInt(0, beverages.size());
        beverage = beverages.get(randomIdx);
    }

    public Beverage getBeverage() {
        return beverage;
    }
}
