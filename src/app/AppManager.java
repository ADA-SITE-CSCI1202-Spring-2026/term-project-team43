package app;

import backend.kitchenfloor.DrinkDispenser;
import backend.kitchenfloor.Grill;
import backend.menu.Beverage;
import backend.menu.HotFood;
import backend.menu.MenuItem;
import backend.order.Order;
import backend.pantry.Ingredient;
import backend.pantry.InventoryManager;
import frontend.InventoryPanel;
import frontend.OrderPanel;
import frontend.SystemLogPanel;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;


public class AppManager {
    private final Queue<Order> orderQueue = new ArrayDeque<>();
    private final InventoryManager inventoryManager;
    private final ArrayList<Ingredient> listOfIngredients = new ArrayList<>();
    private final Grill grill = new Grill();
    private final DrinkDispenser drinkDispenser = new DrinkDispenser();
    private final ArrayList<HotFood> listOfHotFood = new ArrayList<>();
    private final ArrayList<Beverage> listOfBeverages = new ArrayList<>();

    private static <K, V> void putAllToMap(Map<K, V> map,  Object... pairs){
        if (pairs.length % 2 == 1){
            throw new IllegalArgumentException("The number of items passed as" +
                    "key, value must be even,");
        }
        K key;
        V value;
        for (int i = 0; i < pairs.length - 1; i+=2){
            key = (K) pairs[i];
            value = (V) pairs[i+1];
            map.put(key, value);
        }
    }

    private static void createFile(File file){
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static InventoryManager loadInventoryFromCsv(
            String filePath,
            Map<String, Ingredient> ingredientByCsvName
    ){
        HashMap<Ingredient, Integer> inventoryStock = new HashMap<>();
        String line;
        BigDecimal balance = new BigDecimal("0");
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            while((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                if (!row[0].equals("Balance")){
                    inventoryStock.put(
                            ingredientByCsvName.get(row[0]), Integer.valueOf(row[1])
                    );
                }
                else {
                    balance = new BigDecimal(row[1]);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new InventoryManager(inventoryStock, balance);
    }

    private static Queue<Order> loadOrderQueue(
            String filePath,
            Map<String, MenuItem> menuItemByCsvName
    ){
        ArrayDeque<Order> orderQueue = new ArrayDeque<>();
        String line;
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            HotFood hotFood;
            Beverage beverage;
            Order order;
            reader.readLine();
            while((line = reader.readLine()) != null) {
                String[] row = line.split(",");
                beverage = (Beverage) menuItemByCsvName.get(row[1]);
                if (!row[0].equals("null")){
                    hotFood = (HotFood) menuItemByCsvName.get(row[0]);
                    order = new Order(hotFood, beverage);
                }
                else {
                    order = new Order(beverage);
                }
                orderQueue.offerLast(order);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return orderQueue;
    }

    public AppManager(){
        // File Paths
        String INVENTORY_CSV_PATH = "data/inventory.csv";
        String ORDER_QUEUE_CSV_PATH = "data/orders.csv";

        // Ingredients
        Ingredient tomato = new Ingredient("Tomato", new BigDecimal("0.32"));
        Ingredient onion = new Ingredient("Onion", new BigDecimal("0.48"));
        Ingredient pickles = new Ingredient("Pickles", new BigDecimal("0.20"));
        Ingredient sauce = new Ingredient("Sauce", new BigDecimal("0.5"));
        Ingredient chicken = new Ingredient("Chicken", new BigDecimal("1.9"));
        Ingredient beef = new Ingredient("Beef", new BigDecimal("2"));
        Ingredient patty = new Ingredient("Beef Patty", new BigDecimal("3"));
        Ingredient bun = new Ingredient("Bun", new BigDecimal("1"));
        Ingredient cucumber = new Ingredient("Cucumber", new BigDecimal("0.23"));
        Ingredient turkishBread = new Ingredient("Turkish Bread", new BigDecimal("2"));
        Ingredient lavash = new Ingredient("Lavash", new BigDecimal("1.5"));
        Ingredient lemon = new Ingredient("Lemon", new BigDecimal("0.5"));
        Ingredient cheese = new Ingredient("Cheese", new BigDecimal("0.75"));
        Ingredient parsley = new Ingredient("Parsley", new BigDecimal("0.1"));
        Ingredient tacoShell = new Ingredient("Taco Shell", new BigDecimal("1.1"));

        Ingredient cola = new Ingredient("Coca-Cola", new BigDecimal("1"));
        Ingredient pepsi = new Ingredient("Pepsi", new BigDecimal("1"));
        Ingredient sprite = new Ingredient("Sprite", new BigDecimal("1"));
        Ingredient fanta = new Ingredient("Fanta", new BigDecimal("1"));
        Ingredient water = new Ingredient("Water", new BigDecimal("0.5"));

        Collections.addAll(listOfIngredients,
                tomato, onion, pickles, sauce, chicken, beef, patty, bun,
                cucumber, turkishBread, lavash, lemon, cheese,
                parsley, tacoShell, cola, pepsi, sprite, fanta, water
        );

        // HotFood ingredients
        HashMap<Ingredient, Integer> burgerIngredients = new HashMap<>();
        HashMap<Ingredient, Integer> donerIngredients = new HashMap<>();
        HashMap<Ingredient, Integer> shawarmaIngredients = new HashMap<>();
        HashMap<Ingredient, Integer> tacoIngredients = new HashMap<>();
        HashMap<Ingredient, Integer> lahmacunIngredients = new HashMap<>();
        HashMap<Ingredient, Integer> tantuniIngredients = new HashMap<>();

        putAllToMap(burgerIngredients,
                bun, 2, patty, 1, tomato, 1, onion, 1, cheese, 1, sauce, 1
        );
        putAllToMap(donerIngredients,
                turkishBread, 1, beef, 2, tomato, 1, cucumber, 1, onion, 1,
                pickles, 1, sauce, 1
        );
        putAllToMap(tacoIngredients,
                tacoShell, 2, beef, 1, cheese, 1, tomato, 1, sauce, 1
        );
        putAllToMap(lahmacunIngredients,
                lavash, 1, beef, 1, tomato, 1, onion, 1, lemon, 1, parsley, 1
        );
        putAllToMap(shawarmaIngredients,
                lavash, 1, chicken, 1, tomato, 1, pickles, 1, sauce, 1
        );
        putAllToMap(tantuniIngredients,
                lavash, 1, beef, 1, tomato, 1, onion, 1, parsley, 1
        );

        // Beverage ingredient
        HashMap<Ingredient, Integer> colaIngredient = new HashMap<>();
        HashMap<Ingredient, Integer> pepsiIngredient = new HashMap<>();
        HashMap<Ingredient, Integer> fantaIngredient = new HashMap<>();
        HashMap<Ingredient, Integer> spriteIngredient = new HashMap<>();
        HashMap<Ingredient, Integer> waterIngredient = new HashMap<>();

        colaIngredient.put(cola, 1);
        pepsiIngredient.put(pepsi, 1);
        fantaIngredient.put(fanta, 1);
        spriteIngredient.put(sprite, 1);
        waterIngredient.put(water, 1);

        // Hot Food
        HotFood burger = new HotFood(
                "Burger", new BigDecimal("10"), burgerIngredients, true
        );
        HotFood doner = new HotFood(
                "Doner", new BigDecimal("8"), donerIngredients, true
        );
        HotFood shawarma = new HotFood(
                "Shawarma", new BigDecimal("8.50"), shawarmaIngredients, true
        );
        HotFood taco = new HotFood(
                "Taco", new BigDecimal("6.50"), tacoIngredients, false
        );
        HotFood lahmacun = new HotFood(
                "Lahmacun", new BigDecimal("7.20"), lahmacunIngredients, false
        );
        HotFood tantuni = new HotFood(
                "Tantuni", new BigDecimal("6.80"), tantuniIngredients, false
        );

        Collections.addAll(listOfHotFood,
                burger, doner, shawarma, taco, lahmacun, tantuni
        );

        // Beverage
        Beverage beverageCola = new Beverage(
                "Coca-cola", new BigDecimal("3"), colaIngredient, true
        );
        Beverage beveragePepsi = new Beverage(
                "Pepsi", new BigDecimal("3"), pepsiIngredient, true
        );
        Beverage beverageSprite = new Beverage(
                "Sprite", new BigDecimal("3"), spriteIngredient, true
        );
        Beverage beverageFanta = new Beverage(
                "Fanta", new BigDecimal("3"), fantaIngredient, false
        );
        Beverage beverageWater = new Beverage(
                "Water", new BigDecimal("2.50"), waterIngredient, false
        );

        Collections.addAll(listOfBeverages,
                beverageCola, beveragePepsi, beverageSprite, beverageFanta,
                beverageWater
        );
        HashMap<String, MenuItem> menuItemByCsvName = new HashMap<>();
        for (MenuItem item: listOfHotFood){
            menuItemByCsvName.put(item.getName(), item);
        }
        for (MenuItem item: listOfBeverages){
            menuItemByCsvName.put(item.getName(), item);
        }
        orderQueue.addAll(loadOrderQueue(
                ORDER_QUEUE_CSV_PATH, menuItemByCsvName)
        );

        HashMap<String, Ingredient> ingredientByCsvName = new HashMap<>();
        for (Ingredient ingredient: listOfIngredients){
            ingredientByCsvName.put(ingredient.getName(), ingredient);
        }

        inventoryManager = loadInventoryFromCsv(
                INVENTORY_CSV_PATH, ingredientByCsvName
        );
    }

    public Queue<Order> getOrderQueue() {
        return orderQueue;
    }

    public String getInventoryInfo(){
        return inventoryManager.toString();
    }

    public ArrayList<Ingredient> getListOfIngredients() {
        return listOfIngredients;
    }

    public ArrayList<HotFood> getListOfHotFood() {
        return listOfHotFood;
    }

    public ArrayList<Beverage> getListOfBeverages() {
        return listOfBeverages;
    }

    // Methods for Cook Next Order Button
    public boolean canCookOrder(){
        Order order = orderQueue.peek();
        if (order == null) {
            return false;
        }
        return inventoryManager.canProcessOrder(order);
    }

    public void updateOrderStatus(){
        Order order = orderQueue.peek();
        if (order == null) {
            SystemLogPanel.appendLog("There aren't any orders to cook");
        }
        else if (inventoryManager.canProcessOrder(order)){
            SystemLogPanel.appendLog(order + " will be prepared soon");
        }
        else {
            Ingredient missingIngredient = inventoryManager.getMissingIngredient(order);
            SystemLogPanel.appendLog(
                    order + " can't be processed, because " +
                            missingIngredient.getName() + " is missing"
            );
        }
    }

    public void cookOrder(){
        Order order = orderQueue.poll();
        assert order != null;
        HotFood hotFood = order.getHotFood();
        Beverage beverage = order.getBeverage();
        if (hotFood != null){
            SystemLogPanel.appendLog(grill.process(hotFood));
        }
        if (beverage != null){
            SystemLogPanel.appendLog(drinkDispenser.process(beverage));
        }
        inventoryManager.processOrder(order);
    }

    public void updateInventoryInfo(){
        String inventoryInfo = inventoryManager.toString();
        InventoryPanel.setInventoryInfo(inventoryInfo);
    }

    // Methods for Buy ingredient Button
    public boolean canBuyIngredient(Ingredient ingredient, int count){
        return inventoryManager.canBuyIngredient(ingredient, count);
    }

    public void updateBuyIngredientStatus(Ingredient ingredient, int count){
        if (inventoryManager.canBuyIngredient(ingredient, count)){
            SystemLogPanel.appendLog(
                    "Purchased: " + count + " x " + ingredient.getName()
            );
        }
        else {
            SystemLogPanel.appendLog(
                    "Purchase Failed: " + count + " x " + ingredient.getName()
            );
        }

    }

    public void updateBalanceStatus(){
        BigDecimal inventoryBalance = inventoryManager.getBalance();
        SystemLogPanel.appendLog(
                String.format("Balance: $%.2f", inventoryBalance.doubleValue())
        );
    }

    public void buyIngredient(Ingredient ingredient, int count){
        inventoryManager.buyIngredient(ingredient, count);
    }

    // Methods for random order generation
    public void addOrder(Order order){
        orderQueue.offer(order);
        OrderPanel.addOrder(order);
        SystemLogPanel.appendLog("New Order: " + order);
    }

    // Methods for writing app data
    public void writeOrderQueue(){
        File csvFile = new File("data/orders.csv");
        if (!csvFile.exists()){
            createFile(csvFile);
        }
        try(PrintWriter fileWriter = new PrintWriter(csvFile)){
            fileWriter.println("HotFood,Beverage");
            for (Order order: orderQueue){
                fileWriter.println(order.toCsv());
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public void writeInventoryData(){
        File csvFile = new File("data/inventory.csv");
        if (!csvFile.exists()){
            createFile(csvFile);
        }
        String inventoryInfo = inventoryManager.toCsv();
        try(PrintWriter fileWrite = new PrintWriter(csvFile)){
            fileWrite.print(inventoryInfo);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }


}
