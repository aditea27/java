import java.util.Scanner;

public class Lab1b {
   
    Lab1b(String word){
        int leftsum=0;
        int rightsum=0;
        int length=word.length();
        for(int i=0;i<length;i++){
            if (word.charAt(i)=='w'|| word.charAt(i)=='p'||word.charAt(i)=='b'||word.charAt(i)=='s'){
                if(word.charAt(i)=='w'){
                    leftsum+=4;
                }
                else if(word.charAt(i)=='p'){
                    leftsum+=3;
                }
                else if(word.charAt(i)=='b'){
                    leftsum+=2;
                }
                else if(word.charAt(i)=='s'){
                    leftsum+=1;
                }
            }
            else if (word.charAt(i)=='m'|| word.charAt(i)=='q'||word.charAt(i)=='d'||word.charAt(i)=='z'){
                if(word.charAt(i)=='m'){
                    rightsum+=4;
                }
                else if(word.charAt(i)=='q'){
                    rightsum+=3;
                }
                else if(word.charAt(i)=='d'){
                    rightsum+=2;
                }
                else if(word.charAt(i)=='z'){
                    rightsum+=1;
                }
            }
    }
    decideWinner(leftsum, rightsum);
    }
    Lab1b(String word,String word1){        
        int leftsum1=0;
        int leftsum2=0;
        int rightsum1=0;
        int rightsum2=0;
        int length1=word.length();
        int length2=word1.length();
        
        for (int i = 0; i < length1; i++) {
            if (word.charAt(i) == 'w' || word.charAt(i) == 'p' || word.charAt(i) == 'b' || word.charAt(i) == 's') {
                if (word.charAt(i) == 'w') {
                    leftsum1 += 4;
                } else if (word.charAt(i) == 'p') {
                    leftsum1 += 3;
                } else if (word.charAt(i) == 'b') {
                    leftsum1 += 2;
                } else if (word.charAt(i) == 's') {
                    leftsum1 += 1;
                }
            } else if (word.charAt(i) == 'm' || word.charAt(i) == 'q' || word.charAt(i) == 'd' || word.charAt(i) == 'z') {
                if (word.charAt(i) == 'm') {
                    rightsum1 += 4;
                } else if (word.charAt(i) == 'q') {
                    rightsum1 += 3;
                } else if (word.charAt(i) == 'd') {
                    rightsum1 += 2;
                } else if (word.charAt(i) == 'z') {
                    rightsum1 += 1;
                }
            }
        }

        // Calculate sums for the second word
        for (int j = 0; j < length2; j++) {
            if (word1.charAt(j) == 'w' || word1.charAt(j) == 'p' || word1.charAt(j) == 'b' || word1.charAt(j) == 's') {
                if (word1.charAt(j) == 'w') {
                    leftsum2 += 4;
                } else if (word1.charAt(j) == 'p') {
                    leftsum2 += 3;
                } else if (word1.charAt(j) == 'b') {
                    leftsum2 += 2;
                } else if (word1.charAt(j) == 's') {
                    leftsum2 += 1;
                }
            } else if (word1.charAt(j) == 'm' || word1.charAt(j) == 'q' || word1.charAt(j) == 'd' || word1.charAt(j) == 'z') {
                if (word1.charAt(j) == 'm') {
                    rightsum2 += 4;
                } else if (word1.charAt(j) == 'q') {
                    rightsum2 += 3;
                } else if (word1.charAt(j) == 'd') {
                    rightsum2 += 2;
                } else if (word1.charAt(j) == 'z') {
                    rightsum2 += 1;
                }
            }
        }
    decideWinner(leftsum1, rightsum1,leftsum2,rightsum2);
}
    

    public void decideWinner(int leftsum, int rightsum) {
        System.out.println("Left sum: " + leftsum);
        System.out.println("Right sum: " + rightsum);
        if (leftsum > rightsum) {
            System.out.println("Left side wins!");
        } else if (rightsum > leftsum) {
            System.out.println("Right side wins!");
        } else {
            System.out.println("It's a tie! Let's fight again.");
           
        }
    }
    public void decideWinner(int leftsum1, int rightsum1,int leftsum2,int rightsum2) {
        int total1=leftsum1+rightsum1;
        int total2=leftsum2+rightsum2;
        if (total1> total2) {
            System.out.println("Left side wins!");
        } else if (total2> total1) {
            System.out.println("Right side wins!");
        } else {
            System.out.println("It's a tie! Let's fight again.");
            
        }
    }
   
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String word = input.nextLine();
        System.out.print("Do you wish to add another word (Yes/No)? ");
        String response = input.nextLine();
        if (response.equalsIgnoreCase("No")) {
                new Lab1b(word);
        } else if (response.equalsIgnoreCase("Yes")) {
                System.out.print("Enter another word: ");
                String word1 = input.nextLine();
                new Lab1b(word, word1);
        }


    }
        
}