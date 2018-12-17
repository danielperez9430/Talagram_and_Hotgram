package org.telegram.ui.Components.Paint;

import android.graphics.Color;
import android.opengl.GLES20;
import java.util.HashMap;
import java.util.Map;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;

public class Shader {
    class CompilationResult {
        int shader;
        int status;

        CompilationResult(Shader arg1, int arg2, int arg3) {
            Shader.this = arg1;
            super();
            this.shader = arg2;
            this.status = arg3;
        }
    }

    private int fragmentShader;
    protected int program;
    protected Map uniformsMap;
    private int vertexShader;

    public Shader(String arg5, String arg6, String[] arg7, String[] arg8) {
        super();
        this.uniformsMap = new HashMap();
        this.program = GLES20.glCreateProgram();
        CompilationResult v5 = this.compileShader(35633, arg5);
        int v1 = 0;
        if(v5.status == 0) {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.e("Vertex shader compilation failed");
            }

            this.destroyShader(v5.shader, 0, this.program);
            return;
        }

        CompilationResult v6 = this.compileShader(35632, arg6);
        if(v6.status == 0) {
            if(BuildVars.LOGS_ENABLED) {
                FileLog.e("Fragment shader compilation failed");
            }

            this.destroyShader(v5.shader, v6.shader, this.program);
            return;
        }

        GLES20.glAttachShader(this.program, v5.shader);
        GLES20.glAttachShader(this.program, v6.shader);
        int v0;
        for(v0 = 0; v0 < arg7.length; ++v0) {
            GLES20.glBindAttribLocation(this.program, v0, arg7[v0]);
        }

        if(this.linkProgram(this.program) == 0) {
            this.destroyShader(v5.shader, v6.shader, this.program);
            return;
        }

        int v7 = arg8.length;
        while(v1 < v7) {
            this.uniformsMap.put(arg8[v1], Integer.valueOf(GLES20.glGetUniformLocation(this.program, arg8[v1])));
            ++v1;
        }

        if(v5.shader != 0) {
            GLES20.glDeleteShader(v5.shader);
        }

        if(v6.shader != 0) {
            GLES20.glDeleteShader(v6.shader);
        }
    }

    public static void SetColorUniform(int arg4, int arg5) {
        GLES20.glUniform4f(arg4, (((float)Color.red(arg5))) / 255f, (((float)Color.green(arg5))) / 255f, (((float)Color.blue(arg5))) / 255f, (((float)Color.alpha(arg5))) / 255f);
    }

    public void cleanResources() {
        if(this.program != 0) {
            GLES20.glDeleteProgram(this.vertexShader);
            this.program = 0;
        }
    }

    private CompilationResult compileShader(int arg3, String arg4) {
        arg3 = GLES20.glCreateShader(arg3);
        GLES20.glShaderSource(arg3, arg4);
        GLES20.glCompileShader(arg3);
        int[] v4 = new int[1];
        GLES20.glGetShaderiv(arg3, 35713, v4, 0);
        if(v4[0] == 0 && (BuildVars.LOGS_ENABLED)) {
            FileLog.e(GLES20.glGetShaderInfoLog(arg3));
        }

        return new CompilationResult(this, arg3, v4[0]);
    }

    private void destroyShader(int arg1, int arg2, int arg3) {
        if(arg1 != 0) {
            GLES20.glDeleteShader(arg1);
        }

        if(arg2 != 0) {
            GLES20.glDeleteShader(arg2);
        }

        if(arg3 != 0) {
            GLES20.glDeleteProgram(arg1);
        }
    }

    public int getUniform(String arg2) {
        return this.uniformsMap.get(arg2).intValue();
    }

    private int linkProgram(int arg4) {
        GLES20.glLinkProgram(arg4);
        int[] v0 = new int[1];
        GLES20.glGetProgramiv(arg4, 35714, v0, 0);
        if(v0[0] == 0 && (BuildVars.LOGS_ENABLED)) {
            FileLog.e(GLES20.glGetProgramInfoLog(arg4));
        }

        return v0[0];
    }
}

