package RE1.tools;

import org.joml.Vector2f;
import org.joml.Vector3f;

public class Line2D {

    private Vector2f from , to;
    private Vector3f color;
    private int timeDisplay;

    public int beginFrame(){

        this.timeDisplay--;
        return this.timeDisplay;

    }

    public Line2D(Vector2f from, Vector2f to, Vector3f color, int timeDisplay) {
        this.from = from;
        this.to = to;
        this.color = color;
        this.timeDisplay = timeDisplay;
    }

    public Vector2f getFrom() {
        return from;
    }

    public Vector2f getTo() {
        return to;
    }

    public Vector3f getColor() {
        return color;
    }

}
