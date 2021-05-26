package RE1;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class kListener {

    private static kListener instance;
    private boolean keyPressed[] = new boolean[350];

    private kListener() {

    }

        public static kListener get() {

            if (kListener.instance == null) {
                kListener.instance = new kListener();
            }
            return kListener.instance;

        }

        public static void keyCallback(long window , int key, int scancode,int action , int mods){

        if(action == GLFW_PRESS) {
            get().keyPressed[key] = true;
        }else if (action == GLFW_RELEASE){

            get().keyPressed[key] = false;

        }

        }

        public static boolean isKeyPressed(int keyCode){

        return get().keyPressed[keyCode];

        }




}
