package RE1.tools;

import java.util.ArrayList;
import java.util.List;

public class DebugDraw {

    private static int MAX_LINES = 500;

    private static List<Line2D> Lines = new ArrayList<>();
    //6 floats per vertex, and 2 veritce per line
    private static float[] vArray = new float[MAX_LINES * 6 * 2];
   // private static Shader shader = AssetPool.getShader("Asset/shaders/debugLine.glsl");

}
