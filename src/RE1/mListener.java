package RE1;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class mListener {

    private static mListener instance;
    private double scrollX,scrollY,posX,posY,lastX,lastY;
    private boolean mButtonPressed[] = new boolean[5];
    private boolean isDragging;

    private mListener(){

        this.scrollX=0.0;
        this.scrollY=0.0;
        this.posX=0.0;
        this.posY=0.0;
        this.lastY=0.0;
        this.lastX=0.0;

    }

    public static mListener get(){

        if(mListener.instance == null){

            mListener.instance = new mListener();

        }

        return mListener.instance;

    }

    public static void mPosCallback(long window , double xpos , double ypos){

        get().lastX = get().posX;
        get().lastY = get().posY;
        get().posX = xpos;
        get().posY = ypos;
        get().isDragging = get().mButtonPressed[0] || get().mButtonPressed[1] || get().mButtonPressed[2];

    }

    public static void mButtonCallback(long window , int button , int action , int mods){
        if(action == GLFW_PRESS) {
            if(button < get().mButtonPressed.length)
                get().mButtonPressed[button] = true;
        }
        else if (action == GLFW_RELEASE){

            get().mButtonPressed[button] = false;
            get().isDragging = false;

        }

    }

    public static void mScrollCallback(long window ,double xOffset,double yOffset){

        get().scrollX = xOffset;
        get().scrollY  = yOffset;

    }

    public  static void endFrame(){

        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX =get().posX;
        get().lastY =get().posY;
    }

    public static float getX(){

        return (float) get().posX;

    }

    public static float gety(){

        return (float) get().posY;

    }

    public static float getDX(){

        return(float)( get().lastX-get().posX);

    }

    public static float getDy(){

        return(float)( get().lastY-get().posY);

    }

    public static float getScrollX(){

        return (float)get().scrollX;

    }

    public static float getScrollY(){

        return (float)get().scrollY;

    }

    public static boolean isDragging(){

        return get().isDragging;

    }

    public static boolean mButtonDown(int button){

        if(button < get().mButtonPressed.length){

            return get().mButtonPressed[button];

        }

        return false;

    }

}
