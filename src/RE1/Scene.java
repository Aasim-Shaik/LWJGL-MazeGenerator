package RE1;

import RE1.tools.Camera;

import java.io.IOException;

public abstract class Scene  {

    protected Camera cam;

    public  Scene() {



    }

    public void init() throws IOException {



    }

    public abstract void update(float dt);



}
