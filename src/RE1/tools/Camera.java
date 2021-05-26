package RE1.tools;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {

    private Matrix4f projectionMat , viewMat;
    public Vector2f pos;

    public Camera(Vector2f pos){

        this.pos = pos;
        this.projectionMat = new Matrix4f();
        this.viewMat = new Matrix4f();
        adjustProjection();

    }

    public void adjustProjection(){

        projectionMat.identity();
        projectionMat.ortho(0.0f , 22.3606f * 22.3606f , 0.0f , 22.3606f * 22.3606f , 0.0f , 100.0f);

    }

    public  Matrix4f getViewMat(){

        Vector3f camFront = new Vector3f(0.0f,0.0f,-1.0f);
        Vector3f camUp = new Vector3f(0.0f,1.0f,0.0f);
        this.viewMat.identity();
        viewMat.lookAt(new Vector3f(pos.x,pos.y,20.0f),camFront.add(pos.x,pos.y,0.0f),camUp);

        return this.viewMat;
    }

    public Matrix4f getProjectionMat(){

        return this.projectionMat;

    }

}
