import java.util.*;

public class HoldEm {
  
  public static int[] Shuffle(int[] array){
		Random rng = new Random();  // Random number generator			
 
		for (int i=0; i<array.length; i++) {
		    int randomPosition = rng.nextInt(array.length);
		    int temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}//Randomly shuffles the deck by assigning a value to temp and then reassigning it to a random position
 
		return array;
	}//Returns shuffled array
  
  public static boolean pair(int[] check) {
    int[] array = new int[7];
     for  (int i=0; i< array.length; i++) {
      array[i] = check[i];
        }
    for  (int i=0; i< array.length; i++) {
      array[i] = (array[i] % 13);//For each value, modulo 13 to find the rank of the card
        }
    Arrays.sort(array);//Sorts the numbers from least to greatest
    for  (int i=0; i< array.length - 1; i++) {
      if(array[i] == array[i+1])//If a number is equal to the value next to it, the hand has a pair
      {
        return true;
      }//Returns true if there is a pair in the hand
    }
           return false;
    }//Returns false if there is no pair
  
  public static boolean twopair(int[] check) {
        int[] array = new int[7];
     for  (int i=0; i< array.length; i++) {
      array[i] = check[i];
        }
    int counter = 0;
    for  (int i=0; i< array.length; i++) {
      array[i] = (array[i] % 13);//For each value, modulo 13 to find the rank of the card
        }
    Arrays.sort(array);//Sorts the numbers from least to greatest
    for  (int i=0; i< array.length - 1; i++) {
      if(array[i] == array[i+1])//If a number is equal to the value next to it, the hand has a pair
      {
        counter++;
      }//Increases counter for each pair in the hand
    }
    if (counter >= 2) {
      return true;
    }//Returns true if there are two pairs
           return false;
    }//Returns false if there are less than two pairs
  
  
           
   public static boolean threeKind (int[] check)
   {
         int[] array = new int[7];
     for  (int i=0; i< array.length; i++) {
      array[i] = check[i];
        }
      for  (int i=0; i< array.length; i++) {
      array[i] = (array[i] % 13);//For each value, modulo 13 to find the rank of the card
        }
    Arrays.sort(array);//Sorts the numbers from least to greatest
    for  (int i=0; i< array.length - 2; i++) {
      if(array[i] == array[i+1] &&  array[i+1]  == array[i+2])//If a number is equal to two consecutive numbers in the array than there is a three of a kind
      {
        return true;
      }//Returns true if there is a three of a kind
    }
           return false;
   }//Returns false if there is no three of a kind
  
  
    public static boolean flush(int[] check){
          int[] array = new int[7];
     for  (int i=0; i< array.length; i++) {
      array[i] = check[i];
        }
            for  (int i=0; i< array.length; i++) {
      array[i] = ((array[i])/13);//For each value in the array, divide by 13 to find the suit of the card
        }
      Arrays.sort(array);//Sorts the numbers from least to greatest
      for  (int i=0; i< array.length - 4; i++) {
      if(array[i] == array[i+1] &&  array[i+1]  == array[i+2] && array[i+2]  == array[i+3] && array[i+3]  == array[i+4])//If 5 cards in a hand have the same suit then there is a flush
      {
        return true;
      }//Returns true if there is a flush
    }
           return false;
   }//Returns false if there is no flush
    
     public static boolean fullHouse(int[] check){
           int[] array = new int[7];
     for  (int i=0; i< array.length; i++) {
      array[i] = check[i];
        }
        int a = 0;
       int b = 0;
       for  (int i=0; i< array.length; i++) {
      array[i] = (array[i] % 13);//For each value, modulo 13 to find the rank of the card
        }
    Arrays.sort(array);//Sorts the numbers from least to greatest
    for  (int i=0; i< array.length - 2; i++) {
      if(array[i] == array[i+1] &&  array[i+1]  == array[i+2])//If a number is equal to two consecutive numbers then there is a three of a kind
      {
        array[i] = -1;
        array[i+1] = -2;
        array[i+2] = -3;//Re-assigning these values takes them out of the equaiton when calculating the pair
        a = 1;
      }
    }
      for  (int i=0; i< array.length - 1; i++) {
      if(array[i] == array[i+1])//If a number is equal to a consecutive number then there is a pair
      {
        b = 1;
      }
    }
      if(a == 1 && b == 1) {
        return true;
      }//If there is both a pair and three of a kind then there is a full house, so the method returns true
           return false;
     }//If there is no full house, then the method returns false
  
  
  public static String highCard(int[] check)
  {
       int[] array = new int[7];
     for  (int i=0; i< array.length; i++) {
      array[i] = check[i];
        }
    for  (int i=0; i< array.length; i++) {
      array[i] = (array[i] % 13);//For each value, modulo 13 to find the rank of the card
        }
     Arrays.sort(array);//Sorts the numbers from least to greatest
    if(array[array.length-1] == 0) {
      return "Ace";
    }
    else if(array[array.length-1] == 1) {
      return "two";
    }
    else if(array[array.length-1] == 2) {
      return "three";
    }
        else if(array[array.length-1] == 3) {
      return "four";
    }
        else if(array[array.length-1] == 4) {
      return "five";
    }
        else if(array[array.length-1] == 5) {
      return "six";
    }
        else if(array[array.length-1] == 6) {
      return "seven";
    }
        else if(array[array.length-1] == 7) {
      return "eight";
    }
        else if(array[array.length-1] == 8) {
      return "nine";
    }
        else if(array[array.length-1] == 9) {
      return "ten";
    }
        else if(array[array.length-1] == 10) {
      return "jack";
    }
        else if(array[array.length-1] == 11) {
      return "queen";
    }
        else {
          return "king";
        }//Returns the name of the highest card in the array
    
  }
    


  public static void main(String[] args) {
    int[] deck = new int[52];//Creates a deck
    int[] hand1 = new int[2];//Creates a hand
    int[] hand2 = new int[2];//Creates a second hand 
    int[] flop = new int[5];//Creates a flop
    
    int[] bh1= new int[7]; //Best hand 1
    int[] bh2 = new int[7]; //Best hand 2
      
    for (int i = 0; i <52; i++) {
      deck[i] = i;//Labels the values in the deck from 0 to 51
    }
    Shuffle(deck);//calls shuffle
  hand1[0] = deck[51];//Takes top cards for the hands
  hand2[0] = deck[50];
  hand1[1] = deck[49];
  hand2[1] = deck[48];
  System.out.println("hand1: "+hand1[0] +", "+hand1[1] );
  System.out.println("hand2: "+hand2[0] +", "+hand2[1] );
  flop[0] = deck[47];//Then takes cards for the flop
  flop[1] = deck[46];
  flop[2] = deck[45];
  flop[3] = deck[44];
  flop[4] = deck[43];
  System.out.print("Flop: " );
  for (int i = 0; i < 5; i++) {
    System.out.print(flop[i] + ",");
  }
    System.out.println("");
  bh1[0] = hand1[0];//Best hand has to include the cards in your hand
  bh1[1] = hand1[1];
    
  bh2[0] = hand2[0];
  bh2[1] = hand2[1];
  
   for (int i =2; i<7; i++) {
     bh1[i] = flop[i-2];//Best hand has to include three cards from the flop
     bh2[i] = flop [i-2];
   }
  System.out.println("hand one has pair: "+ pair(bh1));//Calls methods
  System.out.println("hand two has pair: "+ pair(bh2));
  System.out.println("hand one has two pair: "+ twopair(bh1));
  System.out.println("hand two has two pair: "+ twopair(bh2));
  System.out.println("hand one has three of a kind: "+ threeKind(bh1));
  System.out.println("hand two has three of a kind: "+ threeKind(bh2));
  System.out.println("hand one has a full house: "+ fullHouse(bh1));
  System.out.println("hand two has a full house: "+ fullHouse(bh2));
  System.out.println("hand one has a flush: "+ flush(bh1));
  System.out.println("hand two has a flush: "+ flush(bh2));
  System.out.println("hand one has high card: "+ highCard(bh1));
  System.out.println("hand two has high card: "+ highCard(bh2));
  if(fullHouse(bh1) && fullHouse(bh2))//Orders winning hand in order of full house, flush, three of a kind, two pairs, and one pair
     {
    System.out.println("both players have a full house");
     }
   else if(fullHouse(bh1))
   {
     System.out.println("player one wins with a full house");
   }
   else if (fullHouse(bh2))
   {
     System.out.println("player two wins with a full house");  
   }
   else if(flush(bh1) && flush(bh2))
     {
    System.out.println("both players have a flush");
     }
   else if(flush(bh1))
   {
     System.out.println("player one wins with a flush");
   }
       else if(flush(bh2))
   {
     System.out.println("player two wins with a flush");
   }
    else if(threeKind(bh1) && threeKind(bh2))
     {
    System.out.println("both players have three of a kind");
     }
    else if(threeKind(bh1))
     {
    System.out.println("player one has three of a kind");
     }
        else if(threeKind(bh2))
     {
    System.out.println("player two has three of a kind");
     }
     else if(twopair(bh1) && twopair(bh2))
     {
    System.out.println("both players have two pair");
     }
         else if(twopair(bh1))
     {
    System.out.println("player one has two pair");
     }
             else if(twopair(bh2))
     {
    System.out.println("player two has two pair");
     }
         else if(pair(bh1) && pair(bh2))
     {
    System.out.println("both players have a pair");
     }
         else if(pair(bh1))
     {
    System.out.println("player one has a pair");
     }
             else if(pair(bh2))
     {
    System.out.println("player two has a pair");
     }
    else {
      int[] array1 = new int[7];
      int[] array2 = new int[7];
     for  (int i=0; i< array1.length; i++) {//Retrieves highest card
      array1[i] = bh1[i];
        }
      for  (int i=0; i< array2.length; i++) {
      array2[i] = bh2[i];
        }
        for  (int i=0; i< array1.length; i++) {
      array1[i] = (array1[i] % 13);//Modulo by 13 to find the rank of each card in best hand 1
        }
     Arrays.sort(array1);//Sort from least to greatest
        for  (int i=0; i< array2.length; i++) {
      array2[i] = (array2[i] % 13);//Modulo by 12 to find the rank of each card in best hand 2
        }
     Arrays.sort(array2);
      if(array1[array1.length-1] > array2[array2.length-1])
      {
        System.out.println("player one wins with high card: " + highCard(bh1));//Player one has the highest card
      }
      else if (array1[array1.length-1] < array2[array2.length-1])
      {
        System.out.println("player two wins with high card: " + highCard(bh2));//Player two has the highest card
      }
       else 
       {
         highCard(bh1);
         highCard(bh2);
        System.out.println("it's a tie");//If highest cards are the same then the game is a tie
      }
    }
  }
}