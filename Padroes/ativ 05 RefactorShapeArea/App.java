
import java.text.Format;


public class App {

    private TextUserInterface textUserInterface = new TextUserInterface();
    private Rectangle rectangle = null;
    private Trapezium trapezium = null;
    private Parallelogram parallelogram = null;

    public void createRectangle() {
        float widht = textUserInterface.promptAndReadFloat("Digite o valor do primeiro lado do retangulo: ");
        float height = textUserInterface.promptAndReadFloat("Digite o valor do segundo lado do retangulo: : ");

        this.rectangle = new Rectangle(widht, height);
    }

    public void createTrapezium() {
        float baseA = textUserInterface.promptAndReadFloat("Digite o valor da base menor do trapézio: ");
        float baseB = textUserInterface.promptAndReadFloat("Digite o valor da base maior do trapézio:  ");
        float height = textUserInterface.promptAndReadFloat("Digite o valor da altura do trapézio:  ");
        
        this.trapezium = new Trapezium(baseA, baseB, height);
    }

    public void createParallelogram() {
        float base = textUserInterface.promptAndReadFloat("Digite o valor da base do paralelogramo: ");
        float height = textUserInterface.promptAndReadFloat("Digite o valor da altura do paralelogramo: ");

        this.parallelogram = new Parallelogram(base, height);
    }
    
    public void printAreas(){
        float rectangleArea = rectangle.getArea();
        float trapeziumArea = trapezium.getArea();
        float parallelogramArea = parallelogram.getArea();
        
        this.textUserInterface.printArea(rectangle.getClass().getName(), rectangleArea);
        this.textUserInterface.printArea(trapezium.getClass().getName(), trapeziumArea);
        this.textUserInterface.printArea(parallelogram.getClass().getName(), parallelogramArea);
    }
    
    public static void main(String[] args) {
        App app = new App();
        app.createParallelogram();
        app.createRectangle();
        app.createTrapezium();
        
        app.printAreas();
    }
    
    
}
