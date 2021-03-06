package RE1;

import RE1.tools.Camera;
import RE1.tools.Shader;
import RE1.tools.Time;
import org.joml.Vector2f;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class MainScene extends Scene {

//    private float[] vArray = {
//
//            //pos                   //color
//            100.5f,0.5f,0.0f,         1.0f,0.0f,0.0f,1.0f,//br 0
//            0.5f,100.5f,0.0f,         0.0f,1.0f,1.0f,0.0f,//tl 1
//            100.5f,100.5f,0.0f,       1.0f,0.0f,1.0f,0.0f,//tr 2
//                0.5f,0.5f,0.0f,       0.0f,0.0f,0.0f,0.0f,//bl 3
//
//    };
//   private int[] eArray = {
//
//           2,1,0,
//           0,1,3
//
//   };

    private int vaoID,vboID,eboID;

    private Shader defaultShader;

    public MainScene() {

    }

    @Override
    public void init(){

        this.cam = new Camera(new Vector2f());

        cam.pos.x = 0;
        cam.pos.y = 0;

            defaultShader = new Shader("Asset/shaders/default.glsl");
            defaultShader.compile();

        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        //creat vbo upload the vBuffer
        vboID=glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER,vboID);

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,eboID);

        //add the vertex attribute
        int pSize = 3;
        int colorSize=4;
        int floatSizeBytes = 4;
        int vertexSizeBytes = (pSize+colorSize)*floatSizeBytes;
        glVertexAttribPointer(0,pSize,GL_FLOAT,false,vertexSizeBytes,0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1,colorSize,GL_FLOAT,false,vertexSizeBytes,pSize*floatSizeBytes);
        glEnableVertexAttribArray(1);


    }

    @Override
    public void update(float dt) {

        System.out.println("X POS : "+cam.pos.x);
        System.out.println("y POS : "+cam.pos.y);

        defaultShader.use();
        defaultShader.uploadMat4("uProjectionMat",cam.getProjectionMat());
        defaultShader.uploadMat4("uView",cam.getViewMat());
        defaultShader.uploadFloat("uTime", Time.getTime());

        //bind vao
                glBindVertexArray(vaoID);
                //enable vertex attrib pointers
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glLineWidth(10.0f);

        MazeWall mz = new MazeWall();
        mz.borderWall();

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);

        defaultShader.disableShader();

    }

}
