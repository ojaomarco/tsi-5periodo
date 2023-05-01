
import java.util.Scanner;

public class ArithmeticProgression{
   
    
    public int readLength() {
        Scanner sc = new Scanner(System.in);
        int length;

        do {
            System.out.println("Informe o número de termos da PA:");
            length = sc.nextInt();
        } while (length < 2);

        
        return length;
    }

    public int readFirstTerm() {
        Scanner sc = new Scanner(System.in);
        int firstTerm;

        do {
            System.out.println("Informe o primeiro termo da PA:");
            firstTerm = sc.nextInt();
        } while (firstTerm < 1);

        
        return firstTerm;
    }

    public int readCommonDiffence() {
        Scanner sc = new Scanner(System.in);
        int commonDifference;

        do {
            System.out.println("Informe a razão da PA:");
            commonDifference = sc.nextInt();
        } while (commonDifference < 1);

        sc.close();
        return commonDifference;
    }

    public int findNthTerm(int length, int firstTerm, int commonDifference) {
        int nTerm = firstTerm + (length - 1) * commonDifference;
        return nTerm;
    }

    public int sumTerms(int length, int firstTerm, int nTerm) {
        int sum = ((firstTerm + nTerm) * length) / 2;
        return sum;
    }

    public void printAllTerms(int length, int firstTerm, int commonDifference) {
        for (int nTerm = 1; nTerm <= length; nTerm++) {
            int termValue = findNthTerm(nTerm, firstTerm, commonDifference);
            System.out.printf("a%d = %d\n", nTerm, termValue);
        }
    }

}
