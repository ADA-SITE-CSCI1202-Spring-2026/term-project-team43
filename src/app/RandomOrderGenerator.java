package app;

import backend.menu.Beverage;
import backend.menu.HotFood;
import backend.order.Order;
import java.util.TimerTask;

// Extending the Abstract class TimerTask, for repetitive tasks
public class RandomOrderGenerator extends TimerTask {
    private Order randomOrder;
    private AppManager appManager;

    public RandomOrderGenerator(AppManager appManager){
        this.appManager = appManager;
    }

    @Override
    public void run(){
        RandomHotFoodGenerator hootFoodGenerator = new RandomHotFoodGenerator(
                appManager.getListOfHotFood()
        );
        Thread hotFoodThread = new Thread(hootFoodGenerator);

        RandomBeverageGenerator randomBeverageGenerator = new RandomBeverageGenerator(
                appManager.getListOfBeverages()
        );
        Thread beverageThread = new Thread(randomBeverageGenerator);

        hotFoodThread.start();
        beverageThread.start();
        try {
            hotFoodThread.join();
            beverageThread.join();
        } catch (InterruptedException e){
            // pass
        }
        HotFood randomHotFood = hootFoodGenerator.getHotFood();
        Beverage randomBeverage = randomBeverageGenerator.getBeverage();
        randomOrder = new Order(randomHotFood, randomBeverage);
        if(!Thread.currentThread().isInterrupted()) {
            appManager.addOrder(randomOrder);
        }
    }
}
