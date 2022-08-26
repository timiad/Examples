/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task2;
import Ingredient;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import Ingredient;

/**
 * 
*implementation of a shelf as a collection of arraylists of ingredient objects to simulate ingredient shelves
*This is a Shelf class that uses the Singleton Pattern.
* The singleton pattern has been chosen because having only one instance of the shelf object at runtime is desireable.
* @author 
*/

public class ExpressShelf {
    public static final int MAX_INGREDIENT = 25;
    
  
    
    private final ArrayList<Ingredient> veggie = new ArrayList<Ingredient>();
    private final ArrayList<Ingredient> sauce = new ArrayList<Ingredient>();
    private final ArrayList<Ingredient> pineapple = new ArrayList<Ingredient>();
    private final ArrayList<Ingredient> ham = new ArrayList<Ingredient>();
    private final ArrayList<Ingredient> cheese = new ArrayList<Ingredient>();
    private int veggieStock = 0;
    private int sauceStock = 0;
    private int pineappleStock = 0;
    private int hamStock = 0;
    private int cheeseStock = 0;
    private boolean lock = false;
    
    private static final ExpressShelf instance = new ExpressShelf();
    
    private ExpressShelf(){
        //construct each shelf to be only 50% full of ingredients
        for(int i=0;i<MAX_INGREDIENT/2;i++){
            veggie.add(Ingredient.createVeggies());
            veggieStock++;
            sauce.add(Ingredient.createSauce());
            sauceStock++;
            pineapple.add(Ingredient.createPineapple());
            pineappleStock++;
            ham.add(Ingredient.createHam());
            hamStock++;
            cheese.add(Ingredient.createCheese());
            cheeseStock++;
        }
    }
    public static ExpressShelf getInstance(){
        return instance;
    }
    public synchronized void accessShelf() throws InterruptedException{
        while(lock){
            wait();
        }
        lock = true;
    }
    public synchronized void closeShelf(){
        lock = false;
        notifyAll();
    }
    
    public void stockShelf(String ingredient){
        switch(ingredient){
                case "sauce":
                    if(sauceStock != MAX_INGREDIENT){
                        sauce.add(Ingredient.createSauce());
                        sauceStock++;
                        System.out.println("Added 1 " + ingredient + " to shelf.");
                        break;
                    } else{
                        System.out.println(ingredient + " cannot be stocked as the shelf is already full. Discarding...");
                        break;
                    }
                case "cheese":
                    if(cheeseStock != MAX_INGREDIENT){
                        cheese.add(Ingredient.createCheese());
                        cheeseStock++;
                        System.out.println("Added 1 " + ingredient + " to shelf.");
                        break;
                    } else{
                        System.out.println(ingredient + " cannot be stocked as the shelf is already full. Discarding...");
                        break;
                    }
                case "pineapple":
                    if(pineappleStock != MAX_INGREDIENT){
                        pineapple.add(Ingredient.createPineapple());
                        pineappleStock++;
                        System.out.println("Added 1 " + ingredient + " to shelf.");
                        break;
                    } else{
                        System.out.println(ingredient + " cannot be stocked as the shelf is already full. Discarding...");
                        break;
                    }
                case "ham":
                    if(hamStock != MAX_INGREDIENT){
                        ham.add(Ingredient.createHam());
                        hamStock++;
                        System.out.println("Added 1 " + ingredient + " to shelf.");
                        break;
                    } else{
                        System.out.println(ingredient + " cannot be stocked as the shelf is already full. Discarding...");
                        break;
                    }
                case "veggie":
                    if(veggieStock != MAX_INGREDIENT){
                        veggie.add(Ingredient.createVeggies());
                        veggieStock++;
                        System.out.println("Added 1 " + ingredient + " to shelf.");
                        break;
                    } else{
                        System.out.println(ingredient + " cannot be stocked as the shelf is already full. Discarding...");
                        break;
                        
                    }
                default: throw new IllegalArgumentException("Invalid Ingredient type");
        }
    }
    
    /*Methods that return the last element of a chosen ingredient in the shelf.
     Returns null if the ingredient is unavailable*/
    public Ingredient fetchSauce() {
        if(!sauce.isEmpty()){
            sauceStock--;
            return sauce.remove(sauce.size()-1);
        } else{
            System.out.println("Sauce shelf is empty");
            return null;
        }
    }
    
    public Ingredient fetchHam() {
        if(!ham.isEmpty()){
            sauceStock--;
            return ham.remove(ham.size()-1);
        } else{
            System.out.println("Ham shelf is empty");
            return null;
        }
    }

    public Ingredient fetchVeggies() {
        if(!veggie.isEmpty()){
        veggieStock--;
        return veggie.remove(veggie.size()-1);
        } else{
            System.out.println("Veggie shelf is empty");
            return null;
        }
    }
    
    public Ingredient fetchCheese() {
        if(!cheese.isEmpty()){
            cheeseStock--;
            return cheese.remove(cheese.size()-1);
        } else{
            System.out.println("Cheese shelf is empty");
            return null;
        }
    }
    
    public Ingredient fetchPineapple() {
        if(!pineapple.isEmpty()){
            pineappleStock--;
            return pineapple.remove(pineapple.size()-1);
        } else{
            System.out.println("Pineapple shelf is empty");
            return null;
        }
    }

    //Methods that return how many of each ingredient are on the shelf
    public int getSauceStorageLevel() {
        return sauce.size();
    }
    
    public int getHamStorageLevel() {
        return ham.size();
    }
     
    public int getVeggiesStorageLevel() {
        return veggie.size();
    }

    public int getCheeseStorageLevel() {
        return cheese.size();
    }

    public int getPineappleStorageLevel() {
        return pineapple.size();
    }
    
}
