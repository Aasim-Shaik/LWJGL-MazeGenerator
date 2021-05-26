package RE1;

import RE1.tools.Time;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import java.io.IOException;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Window {

    private int width,height;
    private String topic;
    private long win;

    public float r,g,b,a;


    private static Window window = null;

    //private static int currentScene = -1;
    private static Scene currentScene;

    private Window(){

        int w = 500,h = 500;
        String t="null";

        this.height=h;
        this.width=w;
        this.topic=t;

        r=1;
        g=1;
        b=1;
        a=1;

    }

    public static void changeScene(int newScene) throws IOException {

        switch(newScene){

            case 0 : currentScene = new MainScene();
                    System.out.println("stoppoint");
                     currentScene.init();
                break;

            default :
                assert false: "Scene not available";
                break;

        }

    }

    public static Window get(){

        if(Window.window==null){
            Window.window=new Window();
        }

        return Window.window;
    }

    public void run(){

        System.out.println("Running");

        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loop();

        //free mem
        glfwFreeCallbacks(win);
        glfwDestroyWindow(win);

        //stop glfw and free error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();

    }

    public void init() throws IOException {

    GLFWErrorCallback.createPrint(System.err).set();

        if(!glfwInit()){
            throw new IllegalStateException("Cant initialize GLFW");
        }

        //config
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE,0);
        glfwWindowHint(GLFW_RESIZABLE,1);
        glfwWindowHint(GLFW_MAXIMIZED,0);

        //create window
        win = glfwCreateWindow(this.width,this.height,this.topic,0,0);

        if(win == 0){
            throw new IllegalStateException("Can't create window");
        }

        //mouse callbacks
        glfwSetCursorPosCallback(win,mListener::mPosCallback);
        glfwSetMouseButtonCallback(win,mListener::mButtonCallback);
        glfwSetScrollCallback(win,mListener::mScrollCallback);

        //key callbacks
        glfwSetKeyCallback(win,kListener::keyCallback);

        //make openGL context current
        glfwMakeContextCurrent(win);

        //use V-Sync
        glfwSwapInterval(1);

        //setup and show window
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(win , (vidMode.width()-this.width)/2, (vidMode.height()-this.height)/2);
        glfwShowWindow(win);
        GL.createCapabilities();

        Window.changeScene(0);

    }

    public void loop(){

        float beginTime = Time.getTime();
        float endTime = Time.getTime();
        float dt = -1.0f;

        while(!glfwWindowShouldClose(win)){

            //poll events
            glfwPollEvents();

            glClearColor(r,g,b,a);
            glClear(GL_COLOR_BUFFER_BIT);

            if(dt >=0)
            currentScene.update(dt);

            if(kListener.isKeyPressed(GLFW_KEY_SPACE) )
                System.out.println("spacebar pressed");

            glfwSwapBuffers(win);

            //time loop
            endTime = Time.getTime();
            dt = endTime - beginTime;
            beginTime = endTime;

        }

    }

}
