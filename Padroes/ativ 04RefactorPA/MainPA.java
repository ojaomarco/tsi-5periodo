
public class MainPA {

    public static void main(String[] args) {
        ArithmeticProgression AP = new ArithmeticProgression();
        int length = AP.readLength();
        int firstTerm = AP.readFirstTerm();
        int commonDifference = AP.readCommonDiffence();
        
        int nTerm = AP.findNthTerm(length, firstTerm, commonDifference);

        AP.printAllTerms(length, firstTerm, commonDifference);
        System.out.printf("A soma é %d", AP.sumTerms(length, firstTerm, nTerm));
    }
}
