package app;

import backend.menu.HotFood;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomHotFoodGenerator implements Runnable{
    private HotFood hotFood;
    private final List<HotFood> hotFoods = new ArrayList<>();

    public RandomHotFoodGenerator(List<HotFood> hootFoods){
        this.hotFoods.addAll(hootFoods);
    }

    @Override
    public void run() {
        // will be depended only on this thread, not the main thread
        int randomIdx = ThreadLocalRandom.current().nextInt(-1, hotFoods.size());
        if (randomIdx == -1){
            hotFood = null;
        }
        else {
            hotFood = hotFoods.get(randomIdx);
        }
    }

    public HotFood getHotFood() {
        return hotFood;
    }
}

