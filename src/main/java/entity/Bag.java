/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author pawel
 */
public final class Bag {
    public ArrayList<Card> cardsIn;
    public ArrayList<Card> cardsOut;
    private final Random random;
    private final int bagCapacity = 132;
    private final int cardTypeLimit = 22;
    
    public Bag(){
  
        random = new Random();
        cardsIn = new ArrayList<>();
        cardsOut = new ArrayList<>();
        addAllCards();
    }
    
    
    public void addCard(Card card){
    }
    
    public void addAllCards(){
        for(int i = 0;i<cardTypeLimit;i++)
            for(CardType s : CardType.values()){
                Card c = new Card(s);
                cardsIn.add(c);
            }
        
        
    }
    
    public Card pullRandom(){
        if(cardsIn.isEmpty())
            return null;
        Card pulled = cardsIn.remove(randomNumber(0, cardsIn.size()-1));
        if(pulled!=null){
            cardsOut.add(pulled);
        }
        return pulled;
    }
    
    public int randomNumber(int min,int max){
        int randomNum = random.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
    
    
    public void printAllCardsIn(){
        System.out.println(cardsIn.toString());

    }
    public void printAllCardsOut(){
        System.out.println(cardsOut.toString());
    }
    
    public void printCardsInSize(){
        System.out.println(cardsIn.size());
    }
    
    public void printCardsOutSize(){
        System.out.println(cardsOut.size());
    }
    
    
    
}
