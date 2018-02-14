/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjack;
import static blackjack.Blackjack.cardsUsed;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author admin
 */
public class Blackjack {
    static Scanner sc = new Scanner(System.in);
    //static int[] cardsUsed = {24, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
    static int[] cardsUsed = {24, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static String[] cardValues = {"blank", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    static int money, shoe;
    static double bank;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        bank = getBank();
        
        int run = 0;
  
        do {
            checkShoe();
            fullHand();           
            System.out.println("Type x to continue.");
            run = sc.nextInt();
            for (int i = 0; i < 14; i++)
            {
                System.out.print(cardsUsed[i] + ", ");
            }           
        } while (run == 0);

        //System.out.println(run);
    }
    
    public static int getCardPlayer(String sequence) {
        int x, ace;
        int a = 0;
        do
        {            
        Random rand = new Random ();
        x = rand.nextInt(13) + 1;       
        } while (cardsUsed[x] == 24);
        cardsUsed[x] = cardsUsed[x] + 1;
        String cardname = cardValues[x];
        System.out.println(sequence + " card is a(n) " + cardname + ".");
        shoe = shoe + 1;
        if (x > 10)
        {        
            x = 10;
            //System.out.println("Its value in the game is " + x + ".");
        }
        else if (x == 1)
        {
        do {          
            try {               
                System.out.println("Enter a value of 1 or 11: ");
                ace = sc.nextInt();
                    if (ace != 1 && ace != 11){
                    System.out.println("Nice try asshole.  1 or 11 please");
                    }
                } catch (Exception e){
                    System.out.println("Illegal Value - non-numeric.");
                    sc.nextLine();
                    ace = -1;
                }
            } while (ace != 1 && ace != 11);
        x = ace;
        System.out.println("Your ace is worth " + x + ".");    
        }
        else
        {
        //System.out.println("Its value in the game is " + x + ".");
        }
        return x;
    }
    
        public static int getCardDealer(String sequence) {
        int x;
        int a = 0;
        do
        {            
        Random rand = new Random ();
        x = rand.nextInt(13) + 1;       
        } while (cardsUsed[x] == 24);
        cardsUsed[x] = cardsUsed[x] + 1;
        String cardname = cardValues[x];
        System.out.println(sequence + " card is a(n) " + cardname + ".");
        shoe = shoe + 1;
        if (x > 10)
        {        
            x = 10;
            //System.out.println("Its value in the game is " + x + ".");
        }
        return x;
    }
    
    public static int playerHand() {
        int[] playerhand = {0, 0, 0, 0, 0};
        int playertotal;
        int hit;
        playerhand[0] = getCardPlayer("First");
        playerhand[1] = getCardPlayer("Second");     
        playertotal = playerhand[0] + playerhand[1] + playerhand[2] + playerhand[3] + playerhand[4];
        /*while (playertotal < 22)
        {
            for (int i = 2; i < 5; i++)
            {

                do
                {
                System.out.println("Hit? Press '1'");
                hit = sc.nextInt();      
                playerhand[i] = getCardPlayer("hit");
                playertotal = playerhand[0] + playerhand[1] + playerhand[2] + playerhand[3] + playerhand[4];
                } while (hit == 1);
                 
            }
        }*/
        return playertotal;
    }
    
    public static int dealerHand() {
        boolean blackjack = false;
        boolean[] aces = {false, false, false, false, false};
        int totalHand = 0;
        int[] dealerhand = {0, 0, 0, 0, 0};
        int[] dealerhandsorted = {0, 0, 0, 0, 0};
        int dealertotal;
        dealerhand[0] = getCardDealer("dealer");
        dealerhand[1] = getCardDealer("dealer");
        if (dealerhand[0] == 1)
        {
            aces[0] = true;
        }
        if (dealerhand[1] == 1)
        {
            aces[1] = true;
        }
        if ((dealerhand[0] == 1 && dealerhand[1] == 10) || (dealerhand[0] == 10 && dealerhand[1] == 1))
        {
            dealertotal = 21;
            blackjack = true;
        }
        else
        {
            dealertotal = 21;
            /*do
            {
                
            } while (dealertotal < 17);*/
        }

        
        return dealertotal;
    }

    public static String fullHand () {
        int plTot, dlTot;
        String outcome;
        plTot = playerHand();
        dlTot = dealerHand();
        System.out.println("You: " + plTot + "."); 
        System.out.println("Dealer: " + dlTot + ".");
        if (plTot > 21)
        {
            outcome = "lose";
        }
        else if (dlTot > 21)
        {
            outcome = "win";
        }
        else
        {
            if (plTot > dlTot)
            {
                outcome = "win";
            }
            else if (plTot < dlTot)
            {
                outcome = "lose";          
            }
            else
            {
                outcome = "push";
            }
        }    
        System.out.println("You " + outcome + ".");
        return outcome;
                
    }
    
    public static void reShuffle() {
        for (int i = 1; i < 14; i++)
        {
        cardsUsed[i] = 0;
        }   
    }
    public static void checkShoe() {
        if (shoe >= 260)
        {
            reShuffle();
            shoe = 0;
                    
        }
    }
    
    public static double getBank() {
        double coh = 0;
        do {
            try {
                System.out.print("Enter starting amount: ");
                coh = sc.nextDouble();
                if (coh <= 0){
                    System.out.println("Must be greater than 0.");
                }
            } catch (Exception e){
                System.out.println("Illegal Value - non-numeric.");
                sc.nextLine();
                coh = -1;
            }
        } while (coh <= 0);
        return coh;
    }
}
