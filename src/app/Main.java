package app;


import frontend.*;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;


public class Main {
    public static void main(String[] args) {
        int THREE_SECONDS_IN_MILLI_SECONDS = 3000;

        AppManager appManager = new AppManager();
        OrderPanel.addOrders(appManager.getOrderQueue());
        InventoryPanel.setInventoryInfo(appManager.getInventoryInfo());
        RestockPanel.addAllIngredients(appManager.getListOfIngredients());
        JFrame appFrame = new AppFrame(appManager);

        Timer timer = new Timer();

        RandomOrderGenerator randomOrderGenerator = new RandomOrderGenerator(
                appManager
        );

        timer.scheduleAtFixedRate(
                randomOrderGenerator, 0,
                THREE_SECONDS_IN_MILLI_SECONDS
        );

        // Prevent Sys.Exit and shutting down the whole code
        appFrame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e){
                        timer.cancel();
                        timer.purge();
                        // Prevent one more order being added to the queue
                        // when the app is closed
                        new Thread(() -> {
                            appManager.writeOrderQueue();
                            appManager.writeInventoryData();
                        }
                        ).start();

                    }
                }
        );
    }
}
