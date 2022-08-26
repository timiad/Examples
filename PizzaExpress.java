

package task2;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import Cook;
import Ingredient;
import Order;
import Pizza;
import Pizzeria;
import task2.OrderQueue;

/** Task 2
 * Feel free to add more classes/interfaces if necessary.
 * The competing  access to the shelves problem is solved in the shelf class.
 * The shelf class uses a lock boolean that makes a cook thread wait if the boolean is set to true
 * @author 
 */
public class PizzaExpress implements Pizzeria {
    ExpressShelf shelf;
    private final OrderQueue deliveryOrderQueue = new OrderQueue();
    private final OrderQueue eatInOrderQueue = new OrderQueue();
    private int ingredient = 0;
    private static Thread cook1;
    private static Thread cook2;
    private DeliveryChain deliveryChain = new DeliveryChain();
    private DeliveryChain eatInDeliveryChain = new DeliveryChain();
    
    public PizzaExpress(){
        cook1 = new PizzaExpressCook("Mark", this, 1);
        cook2 = new PizzaExpressCook("Jane", this, 0);
        shelf = ExpressShelf.getInstance();
    }
    
        private Order createRandomOrder() {
    Random random = new Random();

    ArrayList<Pizza> p = new ArrayList<>();

    // create at most 5 random pizzas
    int quantity = random.nextInt(5)+1;
    for (int i = 0; i < quantity; i++) {
        int type = random.nextInt(Pizza.PIZZA_TYPES);
        switch (type) {
            case Pizza.MARGHERITA: 
                p.add(Pizza.createMargherita());
                break;
            case Pizza.ROMANA:
                p.add(Pizza.createRomana());
                break;
            case Pizza.VEGETARIAN:
                p.add(Pizza.createVegetarian());
                break;
            default:
                p.add(Pizza.createHawaiian());
                break;
        }
    }
    return new Order(p);
}
        
    
    public static void main(String[] args) throws InterruptedException {
        PizzaExpress p = new PizzaExpress();
        cook1.start();
        cook2.start();
        p.run();
    }
    public void run() throws InterruptedException{
        while(true){
            Order o = createRandomOrder();
            this.placeOrder(o);
            synchronized(this){
                this.wait(1000);
            }
        }

    }
    @Override
    public void placeOrder(Order o) {
        System.out.println("Order " + o + " arrived!");
        deliveryOrderQueue.placeNextOrder(o);
        eatInOrderQueue.placeNextOrder(o);
    }
    
    @Override
    public Order getNextOrder() {
        return deliveryOrderQueue.getNextOrder();
    }
    
    public Order getNextEatInOrder(){
        return eatInOrderQueue.getNextOrder();
    }

    //deliveries
    @Override
    public void deliverOrder(Order o) {
        deliveryChain.deliverPizza(o);
    }
    
    public void deliverEatInOrder(Order o){
        eatInDeliveryChain.deliverPizza(o);
    }

    @Override
    public int getNumOfWaitingOrders() {
        return deliveryOrderQueue.getNumberOfOrders();
    }
    
    public int getNumOfWaitingEatInOrders(){
        return eatInOrderQueue.getNumberOfOrders();
    }

    @Override
    public Ingredient fetchSauce() {
        return shelf.fetchSauce();
    }

    @Override
    public Ingredient fetchHam() {
        return shelf.fetchHam();
    }

    @Override
    public Ingredient fetchVeggies() {
        return shelf.fetchVeggies();
    }

    @Override
    public Ingredient fetchCheese() {
        return shelf.fetchCheese();
    }

    @Override
    public Ingredient fetchPineapple() {
        return shelf.fetchPineapple();
    }

    @Override
    public void refillSauce() {
        shelf.stockShelf("sauce");
    }

    @Override
    public void refillHam() {
        shelf.stockShelf("ham");
    }

    @Override
    public void refillVeggies() {
        shelf.stockShelf("veggie");
    }

    @Override
    public void refillCheese() {
        shelf.stockShelf("cheese");
    }

    @Override
    public void refillPineapple() {
        shelf.stockShelf("pineapple");
    }
    
    public void accessShelf() throws InterruptedException{
        shelf.accessShelf();
    }
    public void closeShelf(){
        shelf.closeShelf();
    }
    
    
    public void refillShelf(){
        int ingredientvalue = ingredient%4;
        switch(ingredientvalue){
            case 0:
                refillSauce();
                ingredient++;
                break;
            case 1:
                refillHam();
                ingredient++;
                break;
            case 2:
                refillVeggies();
                ingredient++;
                break;
            case 3:
                refillCheese();
                ingredient++;
                break;
            case 4:
                refillPineapple();
                ingredient++;
                break;
        }
    }

    @Override
    public int getSauceStorageLevel() {
        return shelf.getSauceStorageLevel();
    }

    @Override
    public int getHamStorageLevel() {
        return shelf.getHamStorageLevel();
    }

    @Override
    public int getVeggiesStorageLevel() {
        return shelf.getVeggiesStorageLevel();
    }

    @Override
    public int getCheeseStorageLevel() {
        return shelf.getCheeseStorageLevel();
    }

    @Override
    public int getPineappleStorageLevel() {
        return shelf.getPineappleStorageLevel();
    }
}
