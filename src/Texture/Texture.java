package Texture;


import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Texture{

    private String vertexShaderSrc = "#version 330\n" +
            "layout (location=0) in vec3 aPos;\n" +
            "layout (location=1) in vec4 aColor;\n" +
            "\n" +
            "out vec4 fColor;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    fColor = aColor;\n" +
            "    gl_Position = vec4(aPos,1.0);\n" +
            "}";

    private String fragmentShaderSrc="#version 330 core\n" +
            "\n" +
            "in vec4 fColor;\n" +
            "out vec4 color;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    color = fColor;\n" +
            "}";

    private int vertexID, fragmentID, shaderProgram;

    public Texture(){
        init();

    }

    private float[] vertexArray = {
         0.5f,-0.5f,0.0f,    1.0f,0.0f,0.0f,1.0f,
        -0.5f,0.5f,0.0f,     0.0f,1.0f,0.0f,1.0f,
         0.5f,0.0f,0.0f,     0.0f,0.0f,1.0f,1.0f,
        -0.5f,-0.5f,0.0f,    1.0f,1.0f,0.0f,1.0f
    };

    private int[] elementArray = {
        2,1,0,
        0,1,3
    };

    private int vaoID,vboID,eboID;

    public void init(){
        //Link shaders
        vertexID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexID,vertexShaderSrc);
        glCompileShader(vertexID);

        //check for errers
        int success = glGetShaderi(vertexID,GL_COMPILE_STATUS);

        if(success == GL_FALSE) {
            int len = glGetShaderi(vertexID,GL_INFO_LOG_LENGTH);
            System.out.println("err testshader");
            System.out.println(glGetShaderInfoLog(vertexID,len));
            assert false : "";
        }

        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentID,fragmentShaderSrc);
        glCompileShader(fragmentID);

        //check for errers
        success = glGetShaderi(fragmentID,GL_COMPILE_STATUS);

        if(success == GL_FALSE) {
            int len = glGetShaderi(fragmentID,GL_INFO_LOG_LENGTH);
            System.out.println("err testshader");
            System.out.println(glGetShaderInfoLog(fragmentID,len));
            assert false : "";

        }

        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram,vertexID);
        glAttachShader(shaderProgram,fragmentID);
        glLinkProgram(shaderProgram);

        success = glGetProgrami(shaderProgram,GL_LINK_STATUS);

        if(success == GL_FALSE){
            int len = glGetProgrami(shaderProgram,GL_INFO_LOG_LENGTH);
            System.out.println("link ripped");
            System.out.println(glGetProgrami(shaderProgram,len));
            assert false : "";

        }

        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();

        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER,vboID);
        glBufferData(GL_ARRAY_BUFFER,vertexBuffer,GL_STATIC_DRAW);

        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER,elementBuffer,GL_STATIC_DRAW);

        int positionsSize = 3; //xyz
        int colorSize = 4; //rgba
        int floatSizeBytes = 4;
        int vertexSizeBytes = (positionsSize+colorSize)*floatSizeBytes;
        glVertexAttribPointer(0,positionsSize,GL_FLOAT,false,vertexSizeBytes,0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1,colorSize,GL_FLOAT,false,vertexSizeBytes,positionsSize*floatSizeBytes);
        glEnableVertexAttribArray(1);
    }

    public void update(){
        glUseProgram(shaderProgram);
        glBindVertexArray(vaoID);
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES,elementArray.length,GL_UNSIGNED_INT,0);

        glDisableVertexAttribArray(0);

        glDisableVertexAttribArray(1);
        glBindVertexArray(0);
        glUseProgram(0);
    }
}