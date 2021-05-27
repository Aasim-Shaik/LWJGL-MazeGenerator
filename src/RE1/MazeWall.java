package RE1;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;

public class MazeWall {

    public MazeWall(){}

    public void render(){
        borderWall();
    }

    public void borderWall() {

        float[] borderWall = {

                //positon                               color
                0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,//0
                500.0f, 500.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,//1
                500.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,//2
                0.0f, 500.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f//3

        };

        int[] borderWallElement = {

                0, 2,
                1, 3,
                0, 3,
                2, 1

        };


        //create float buffer of border vertices
        FloatBuffer vBuffer = BufferUtils.createFloatBuffer(borderWall.length);
        vBuffer.put(borderWall).flip();
        glBufferData(GL_ARRAY_BUFFER, vBuffer, GL_STATIC_DRAW);

        //create indices and upload of border
        IntBuffer eBuffer = BufferUtils.createIntBuffer(borderWallElement.length);
        eBuffer.put(borderWallElement).flip();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, eBuffer, GL_STATIC_DRAW);

        glDrawElements(GL_LINES, borderWallElement.length, GL_UNSIGNED_INT, 0);

    }



}
