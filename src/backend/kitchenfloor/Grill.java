package backend.kitchenfloor;

import backend.menu.MenuItem;

public class Grill implements IAppliance{
    @Override
    public String process(MenuItem item){
        return item.getName() + " is being prepared on the grill";
    }

}
