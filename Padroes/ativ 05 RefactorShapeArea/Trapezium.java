
public class Trapezium {

    float baseA;
    float baseB;
    float height;

    public Trapezium(float baseA, float baseB, float height) {
        this.baseA = baseA;
        this.baseB = baseB;
        this.height = height;
    }

    private float calculateTrapeziumArea() {
        return ((this.baseA + this.baseB) * this.height) / 2;
    }

    public float getArea() {
        return calculateTrapeziumArea();
    }
}
