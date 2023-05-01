
public class Parallelogram {

    float base;
    float height;
    

    public Parallelogram(float base, float height) {
        this.base = base;
        this.height = height;
    }

    private float calculateParallelogramArea() {
        return (this.base + this.height);
    }

    public float getArea() {
        return calculateParallelogramArea();
    }
}
