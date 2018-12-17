package org.telegram.ui.Components.Paint;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map$Entry;
import java.util.Map;

public class ShaderSet {
    private static final String ATTRIBUTES = "attributes";
    private static final Map AVAILBALBE_SHADERS = null;
    private static final String FRAGMENT = "fragment";
    private static final String PAINT_BLITWITHMASKLIGHT_FSH = "precision highp float; varying vec2 varTexcoord; uniform sampler2D texture; uniform sampler2D mask; uniform vec4 color; void main (void) { vec4 dst = texture2D(texture, varTexcoord.st, 0.0); vec3 maskColor = texture2D(mask, varTexcoord.st, 0.0).rgb; float srcAlpha = clamp(0.78 * maskColor.r + maskColor.b + maskColor.g, 0.0, 1.0); vec3 borderColor = mix(color.rgb, vec3(1.0, 1.0, 1.0), 0.86); vec3 finalColor = mix(color.rgb, borderColor, maskColor.g); finalColor = mix(finalColor.rgb, vec3(1.0, 1.0, 1.0), maskColor.b); float outAlpha = srcAlpha + dst.a * (1.0 - srcAlpha); gl_FragColor.rgb = (finalColor * srcAlpha + dst.rgb * dst.a * (1.0 - srcAlpha)) / outAlpha; gl_FragColor.a = outAlpha; gl_FragColor.rgb *= gl_FragColor.a; }";
    private static final String PAINT_BLITWITHMASK_FSH = "precision highp float; varying vec2 varTexcoord; uniform sampler2D texture; uniform sampler2D mask; uniform vec4 color; void main (void) { vec4 dst = texture2D(texture, varTexcoord.st, 0.0); float srcAlpha = color.a * texture2D(mask, varTexcoord.st, 0.0).a; float outAlpha = srcAlpha + dst.a * (1.0 - srcAlpha); gl_FragColor.rgb = (color.rgb * srcAlpha + dst.rgb * dst.a * (1.0 - srcAlpha)) / outAlpha; gl_FragColor.a = outAlpha; gl_FragColor.rgb *= gl_FragColor.a; }";
    private static final String PAINT_BLIT_FSH = "precision highp float; varying vec2 varTexcoord; uniform sampler2D texture; void main (void) { vec4 tex = texture2D(texture, varTexcoord.st, 0.0); gl_FragColor = texture2D(texture, varTexcoord.st, 0.0); gl_FragColor.rgb *= gl_FragColor.a; }";
    private static final String PAINT_BLIT_VSH = "precision highp float; uniform mat4 mvpMatrix; attribute vec4 inPosition; attribute vec2 inTexcoord; varying vec2 varTexcoord; void main (void) { gl_Position = mvpMatrix * inPosition; varTexcoord = inTexcoord; }";
    private static final String PAINT_BRUSHLIGHT_FSH = "precision highp float; varying vec2 varTexcoord; varying float varIntensity; uniform sampler2D texture; void main (void) { vec4 f = texture2D(texture, varTexcoord.st, 0.0); gl_FragColor = vec4(f.r * varIntensity, f.g, f.b, 0.0); }";
    private static final String PAINT_BRUSH_FSH = "precision highp float; varying vec2 varTexcoord; varying float varIntensity; uniform sampler2D texture; void main (void) { gl_FragColor = vec4(0, 0, 0, varIntensity * texture2D(texture, varTexcoord.st, 0.0).r); }";
    private static final String PAINT_BRUSH_VSH = "precision highp float; uniform mat4 mvpMatrix; attribute vec4 inPosition; attribute vec2 inTexcoord; attribute float alpha; varying vec2 varTexcoord; varying float varIntensity; void main (void) { gl_Position = mvpMatrix * inPosition; varTexcoord = inTexcoord; varIntensity = alpha; }";
    private static final String PAINT_COMPOSITEWITHMASKLIGHT_FSH = "precision highp float; varying vec2 varTexcoord; uniform sampler2D mask; uniform vec4 color; void main (void) { vec3 maskColor = texture2D(mask, varTexcoord.st, 0.0).rgb; float alpha = clamp(0.78 * maskColor.r + maskColor.b + maskColor.g, 0.0, 1.0); vec3 borderColor = mix(color.rgb, vec3(1.0, 1.0, 1.0), 0.86); vec3 finalColor = mix(color.rgb, borderColor, maskColor.g); finalColor = mix(finalColor.rgb, vec3(1.0, 1.0, 1.0), maskColor.b); gl_FragColor.rgb = finalColor; gl_FragColor.a = alpha; }";
    private static final String PAINT_COMPOSITEWITHMASK_FSH = "precision highp float; varying vec2 varTexcoord; uniform sampler2D mask; uniform vec4 color; void main(void) { float alpha = color.a * texture2D(mask, varTexcoord.st, 0.0).a; gl_FragColor.rgb = color.rgb; gl_FragColor.a = alpha; }";
    private static final String PAINT_NONPREMULTIPLIEDBLIT_FSH = "precision highp float; varying vec2 varTexcoord; uniform sampler2D texture; void main (void) { gl_FragColor = texture2D(texture, varTexcoord.st, 0.0); }";
    private static final String UNIFORMS = "uniforms";
    private static final String VERTEX = "vertex";

    static {
        ShaderSet.AVAILBALBE_SHADERS = ShaderSet.createMap();
    }

    public ShaderSet() {
        super();
    }

    private static Map createMap() {
        HashMap v0 = new HashMap();
        HashMap v1 = new HashMap();
        ((Map)v1).put("vertex", "precision highp float; uniform mat4 mvpMatrix; attribute vec4 inPosition; attribute vec2 inTexcoord; attribute float alpha; varying vec2 varTexcoord; varying float varIntensity; void main (void) { gl_Position = mvpMatrix * inPosition; varTexcoord = inTexcoord; varIntensity = alpha; }");
        ((Map)v1).put("fragment", "precision highp float; varying vec2 varTexcoord; varying float varIntensity; uniform sampler2D texture; void main (void) { gl_FragColor = vec4(0, 0, 0, varIntensity * texture2D(texture, varTexcoord.st, 0.0).r); }");
        ((Map)v1).put("attributes", new String[]{"inPosition", "inTexcoord", "alpha"});
        ((Map)v1).put("uniforms", new String[]{"mvpMatrix", "texture"});
        ((Map)v0).put("brush", Collections.unmodifiableMap(((Map)v1)));
        v1 = new HashMap();
        ((Map)v1).put("vertex", "precision highp float; uniform mat4 mvpMatrix; attribute vec4 inPosition; attribute vec2 inTexcoord; attribute float alpha; varying vec2 varTexcoord; varying float varIntensity; void main (void) { gl_Position = mvpMatrix * inPosition; varTexcoord = inTexcoord; varIntensity = alpha; }");
        ((Map)v1).put("fragment", "precision highp float; varying vec2 varTexcoord; varying float varIntensity; uniform sampler2D texture; void main (void) { vec4 f = texture2D(texture, varTexcoord.st, 0.0); gl_FragColor = vec4(f.r * varIntensity, f.g, f.b, 0.0); }");
        ((Map)v1).put("attributes", new String[]{"inPosition", "inTexcoord", "alpha"});
        ((Map)v1).put("uniforms", new String[]{"mvpMatrix", "texture"});
        ((Map)v0).put("brushLight", Collections.unmodifiableMap(((Map)v1)));
        v1 = new HashMap();
        ((Map)v1).put("vertex", "precision highp float; uniform mat4 mvpMatrix; attribute vec4 inPosition; attribute vec2 inTexcoord; varying vec2 varTexcoord; void main (void) { gl_Position = mvpMatrix * inPosition; varTexcoord = inTexcoord; }");
        ((Map)v1).put("fragment", "precision highp float; varying vec2 varTexcoord; uniform sampler2D texture; void main (void) { vec4 tex = texture2D(texture, varTexcoord.st, 0.0); gl_FragColor = texture2D(texture, varTexcoord.st, 0.0); gl_FragColor.rgb *= gl_FragColor.a; }");
        ((Map)v1).put("attributes", new String[]{"inPosition", "inTexcoord"});
        ((Map)v1).put("uniforms", new String[]{"mvpMatrix", "texture"});
        ((Map)v0).put("blit", Collections.unmodifiableMap(((Map)v1)));
        v1 = new HashMap();
        ((Map)v1).put("vertex", "precision highp float; uniform mat4 mvpMatrix; attribute vec4 inPosition; attribute vec2 inTexcoord; varying vec2 varTexcoord; void main (void) { gl_Position = mvpMatrix * inPosition; varTexcoord = inTexcoord; }");
        ((Map)v1).put("fragment", "precision highp float; varying vec2 varTexcoord; uniform sampler2D texture; uniform sampler2D mask; uniform vec4 color; void main (void) { vec4 dst = texture2D(texture, varTexcoord.st, 0.0); vec3 maskColor = texture2D(mask, varTexcoord.st, 0.0).rgb; float srcAlpha = clamp(0.78 * maskColor.r + maskColor.b + maskColor.g, 0.0, 1.0); vec3 borderColor = mix(color.rgb, vec3(1.0, 1.0, 1.0), 0.86); vec3 finalColor = mix(color.rgb, borderColor, maskColor.g); finalColor = mix(finalColor.rgb, vec3(1.0, 1.0, 1.0), maskColor.b); float outAlpha = srcAlpha + dst.a * (1.0 - srcAlpha); gl_FragColor.rgb = (finalColor * srcAlpha + dst.rgb * dst.a * (1.0 - srcAlpha)) / outAlpha; gl_FragColor.a = outAlpha; gl_FragColor.rgb *= gl_FragColor.a; }");
        ((Map)v1).put("attributes", new String[]{"inPosition", "inTexcoord"});
        ((Map)v1).put("uniforms", new String[]{"mvpMatrix", "texture", "mask", "color"});
        ((Map)v0).put("blitWithMaskLight", Collections.unmodifiableMap(((Map)v1)));
        v1 = new HashMap();
        ((Map)v1).put("vertex", "precision highp float; uniform mat4 mvpMatrix; attribute vec4 inPosition; attribute vec2 inTexcoord; varying vec2 varTexcoord; void main (void) { gl_Position = mvpMatrix * inPosition; varTexcoord = inTexcoord; }");
        ((Map)v1).put("fragment", "precision highp float; varying vec2 varTexcoord; uniform sampler2D texture; uniform sampler2D mask; uniform vec4 color; void main (void) { vec4 dst = texture2D(texture, varTexcoord.st, 0.0); float srcAlpha = color.a * texture2D(mask, varTexcoord.st, 0.0).a; float outAlpha = srcAlpha + dst.a * (1.0 - srcAlpha); gl_FragColor.rgb = (color.rgb * srcAlpha + dst.rgb * dst.a * (1.0 - srcAlpha)) / outAlpha; gl_FragColor.a = outAlpha; gl_FragColor.rgb *= gl_FragColor.a; }");
        ((Map)v1).put("attributes", new String[]{"inPosition", "inTexcoord"});
        ((Map)v1).put("uniforms", new String[]{"mvpMatrix", "texture", "mask", "color"});
        ((Map)v0).put("blitWithMask", Collections.unmodifiableMap(((Map)v1)));
        v1 = new HashMap();
        ((Map)v1).put("vertex", "precision highp float; uniform mat4 mvpMatrix; attribute vec4 inPosition; attribute vec2 inTexcoord; varying vec2 varTexcoord; void main (void) { gl_Position = mvpMatrix * inPosition; varTexcoord = inTexcoord; }");
        ((Map)v1).put("fragment", "precision highp float; varying vec2 varTexcoord; uniform sampler2D mask; uniform vec4 color; void main(void) { float alpha = color.a * texture2D(mask, varTexcoord.st, 0.0).a; gl_FragColor.rgb = color.rgb; gl_FragColor.a = alpha; }");
        ((Map)v1).put("attributes", new String[]{"inPosition", "inTexcoord"});
        ((Map)v1).put("uniforms", new String[]{"mvpMatrix", "mask", "color"});
        ((Map)v0).put("compositeWithMask", Collections.unmodifiableMap(((Map)v1)));
        v1 = new HashMap();
        ((Map)v1).put("vertex", "precision highp float; uniform mat4 mvpMatrix; attribute vec4 inPosition; attribute vec2 inTexcoord; varying vec2 varTexcoord; void main (void) { gl_Position = mvpMatrix * inPosition; varTexcoord = inTexcoord; }");
        ((Map)v1).put("fragment", "precision highp float; varying vec2 varTexcoord; uniform sampler2D mask; uniform vec4 color; void main (void) { vec3 maskColor = texture2D(mask, varTexcoord.st, 0.0).rgb; float alpha = clamp(0.78 * maskColor.r + maskColor.b + maskColor.g, 0.0, 1.0); vec3 borderColor = mix(color.rgb, vec3(1.0, 1.0, 1.0), 0.86); vec3 finalColor = mix(color.rgb, borderColor, maskColor.g); finalColor = mix(finalColor.rgb, vec3(1.0, 1.0, 1.0), maskColor.b); gl_FragColor.rgb = finalColor; gl_FragColor.a = alpha; }");
        ((Map)v1).put("attributes", new String[]{"inPosition", "inTexcoord"});
        ((Map)v1).put("uniforms", new String[]{"mvpMatrix", "texture", "mask", "color"});
        ((Map)v0).put("compositeWithMaskLight", Collections.unmodifiableMap(((Map)v1)));
        v1 = new HashMap();
        ((Map)v1).put("vertex", "precision highp float; uniform mat4 mvpMatrix; attribute vec4 inPosition; attribute vec2 inTexcoord; varying vec2 varTexcoord; void main (void) { gl_Position = mvpMatrix * inPosition; varTexcoord = inTexcoord; }");
        ((Map)v1).put("fragment", "precision highp float; varying vec2 varTexcoord; uniform sampler2D texture; void main (void) { gl_FragColor = texture2D(texture, varTexcoord.st, 0.0); }");
        ((Map)v1).put("attributes", new String[]{"inPosition", "inTexcoord"});
        ((Map)v1).put("uniforms", new String[]{"mvpMatrix", "texture"});
        ((Map)v0).put("nonPremultipliedBlit", Collections.unmodifiableMap(((Map)v1)));
        return Collections.unmodifiableMap(((Map)v0));
    }

    public static Map setup() {
        HashMap v0 = new HashMap();
        Iterator v1 = ShaderSet.AVAILBALBE_SHADERS.entrySet().iterator();
        while(v1.hasNext()) {
            Object v2 = v1.next();
            Object v3 = ((Map$Entry)v2).getValue();
            ((Map)v0).put(((Map$Entry)v2).getKey(), new Shader(((Map)v3).get("vertex"), ((Map)v3).get("fragment"), ((Map)v3).get("attributes"), ((Map)v3).get("uniforms")));
        }

        return Collections.unmodifiableMap(((Map)v0));
    }
}

