

package task1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Queue;
import java.util.LinkedList;
import Pizzeria;
import Cook;
import Ingredient;
import Order;
import Pizza;
import OrderQueue;



/** Complete the code relative to Task1 here.
 * 
 * task 1
 *
 * @author 
 */
public class BellaNapoli implements Pizzeria {

    Shelf shelf;
    private static final int MAX_DELIVERY_WAITS = 5;
    private static int DELIVERIES = 0;
    private final Cook cook;
    private OrderQueue orderQueue = new OrderQueue();
    private DeliveryChain deliverychain = new DeliveryChain();
    private int ingredient = 0;
    
    public BellaNapoli(){
        shelf = Shelf.getInstance();
        cook = new BellaNapoliCook("John", this);

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
    
    public void run(){
        while(true) {
            Order o = createRandomOrder();
            this.placeOrder(o);
            cook.prepareOrder(getNextOrder());
            Random random = new Random();
            if(random.nextInt(2)%2==0){
                refillShelf();
            }
        }
    }    

    public static void main(String[] args) {
        BellaNapoli p = new BellaNapoli();
        p.run();
    }

    @Override
    public void placeOrder(Order o) {
        System.out.println("Order " + o + " arrived!");
        orderQueue.placeNextOrder(o);
    }

    @Override
    public Order getNextOrder() {
        //get the next order in the queue
        return orderQueue.getNextOrder();
    }

    @Override
    public void deliverOrder(Order o) {
        if(DELIVERIES<MAX_DELIVERY_WAITS){
        DELIVERIES++;
        deliverychain.deliverPizza(o);
        }else{    
        }
        
    }
    
    public void orderDelivered(){
        DELIVERIES--;
    }

    @Override
    public int getNumOfWaitingOrders() {
        return orderQueue.getNumberOfOrders();
    }

    
    //Fetch an ingredient from the shelf. The shelf behaviour is handled by a shelf object.
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
    //refill ingredients to shelf
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
    
    //refill ingredients
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
    //return size of ingredient shelf
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
