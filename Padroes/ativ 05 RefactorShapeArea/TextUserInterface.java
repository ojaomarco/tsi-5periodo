
import java.util.Scanner;

public class TextUserInterface {

    private final Scanner scanner = new Scanner(System.in);

    public float promptAndReadFloat(String promptMessage) {
        float number;
        do {
            System.out.println(promptMessage);
            number = scanner.nextFloat();
        } while (number < 0.0F);
        
        return number;
    }
    
    public void printArea(String formName, float area){
        System.out.printf("A área do %s é: %5.2f\n", formName, area);
    }
    
}
