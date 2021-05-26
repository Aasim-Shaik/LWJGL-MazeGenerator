package RE1.tools;

import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

    private int shaderProgram , vID , fID;
    private  boolean beingUsed = false;

    private String vSrc , fSrc , filePath;

    public Shader(String filePath) {

        this.filePath = filePath;
        String src = null;
        try {
            src = new String(Files.readAllBytes(Paths.get(filePath)));

        String[] spString = src.split("(#type)( )+([a-zA-Z]+)");

        // Find the first pattern after #type 'pattern'
        int index = src.indexOf("#type") + 6;
        int eol = src.indexOf("\r\n",index);

        String FirstPattern = src.substring(index,eol).trim();

        // Find the second pattern after #type 'pattern'
        index = src.indexOf("#type",eol) + 6;
        eol = src.indexOf("\r\n",index);

        String SecondPattern = src.substring(index,eol).trim();

        if(FirstPattern.equals("vertex")){

            vSrc = spString[1];

        } else if (FirstPattern.equals("fragment")){

            fSrc = spString[1];

        } else {

            throw new IOException("wrong token '"+FirstPattern+"'");

        }

        if(SecondPattern.equals("vertex")){

            vSrc = spString[2];

        } else if (SecondPattern.equals("fragment")){

            fSrc = spString[2];

        } else {

            throw new IOException("wrong token '"+SecondPattern+"'");

        }

        } catch (IOException e) {
            e.printStackTrace();
            assert false : "error cant open file "+filePath+" ";
        }

    }

    public void compile(){


        /* compile and link shaders */

        //load and compile shader
        vID = glCreateShader(GL_VERTEX_SHADER);

        //pass shader source to GPU \ gpu
        glShaderSource(vID,vSrc);
        glCompileShader(vID);

        //check for errors
        int success = glGetShaderi(vID,GL_COMPILE_STATUS);
        if(success==0) {

            int len = glGetShaderi(vID, GL_INFO_LOG_LENGTH);
            System.out.println("error"+filePath+" shader compilation stopped");
            System.out.println(glGetShaderInfoLog(vID, len));

            assert false : "";}

        //load and compile shader
        fID = glCreateShader(GL_FRAGMENT_SHADER);

        //pass shader source to GPU \ gpu
        glShaderSource(fID, fSrc);
        glCompileShader(fID);

        //check for errors
        int successf = glGetShaderi(fID, GL_COMPILE_STATUS);
        if (successf == 0) {

            int lenf = glGetShaderi(fID, GL_INFO_LOG_LENGTH);
            System.out.println("error"+filePath+" shader compilation stopped");
            System.out.println(glGetShaderInfoLog(fID, lenf));

            assert false : "";
        }

            //link shader and check error
            shaderProgram = glCreateProgram();
            glAttachShader(shaderProgram, vID);
            glAttachShader(shaderProgram, fID);
            glLinkProgram(shaderProgram);

            //check for error
            success = glGetProgrami(shaderProgram, GL_LINK_STATUS);
            if (success == 0) {

                int len = glGetProgrami(shaderProgram,GL_INFO_LOG_LENGTH);
                System.out.println("ERror "+filePath+", linking stopped");
                System.out.print(glGetProgramInfoLog(shaderProgram,len));
                assert false : "";
            }

        }

    public void use(){
        if(!beingUsed) {
            //bind shader program
            beingUsed = true;
            glUseProgram(shaderProgram);
        }
    }

    public void disableShader(){
        beingUsed = false;
        glUseProgram(0);

    }

    public void uploadMat4(String varName , Matrix4f mat4){

        int varLocation = glGetUniformLocation(shaderProgram,varName);
        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(16);
        mat4.get(matBuffer);
        glUniformMatrix4fv(varLocation,false,matBuffer);

    }

    public void uploadMat3(String varName , Matrix3f mat3){

        int varLocation = glGetUniformLocation(shaderProgram,varName);
        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(9);
        mat3.get(matBuffer);
        glUniformMatrix3fv(varLocation,false,matBuffer);

    }

    public void uploadVec4f(String varName , Vector4f vec){

        int varLocation = glGetUniformLocation(shaderProgram,varName);
        use();
        glUniform4f(varLocation,vec.x,vec.y,vec.z,vec.w);

    }

    public void uploadVec3f(String varName , Vector4f vec){

        int varLocation = glGetUniformLocation(shaderProgram,varName);
        use();
        glUniform3f(varLocation,vec.x,vec.y,vec.z);

    }

    public void uploadVec2f(String varName , Vector4f vec){

        int varLocation = glGetUniformLocation(shaderProgram,varName);
        use();
        glUniform2f(varLocation,vec.x,vec.y);

    }

    public void uploadFloat(String varName , float val){

        int varLocation = glGetUniformLocation(shaderProgram,varName);
        use();
        glUniform1f(varLocation,val);

    }

    public void uploadInt(String varName , int val){

        int varLocation = glGetUniformLocation(shaderProgram,varName);
        use();
        glUniform1i(varLocation,val);

    }

}
