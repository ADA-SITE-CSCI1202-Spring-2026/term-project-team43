package backend.kitchenfloor;

import backend.menu.MenuItem;

public class DrinkDispenser implements IAppliance{
    public String process(MenuItem item){
        return item.getName() + " is being dispensed from the drink dispenser";
    }
}
