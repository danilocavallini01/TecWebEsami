package Beans;

import java.util.Random;

public class Cameriere extends Thread{
    private Drink drink;

    
    public Cameriere(Drink drink) {
        this.drink = drink;
    }


    public void run() {
        Random r = new Random(System.currentTimeMillis());

        try {
            Thread.sleep(r.nextInt(20000) + 1000);
            this.drink.setDelivered(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
