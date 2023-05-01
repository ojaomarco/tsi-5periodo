
public class Rectangle {

    float widht;
    float height;

    public Rectangle(float widht, float height) {
        this.widht = widht;
        this.height = height;
    }

    private float calculateRectangleArea() {
        return (this.widht * this.height);
    }

    public float getArea() {
        return calculateRectangleArea();
    }
}
