#version 330

uniform sampler2D InSampler;

in vec2 texCoord;

layout(std140) uniform SamplerInfo {
    vec2 OutSize;
    vec2 InSize;
};

layout(std140) uniform AshenwoodConfig {
    float GrayscaleAmount;
};

out vec4 fragColor;

void main() {
    vec4 sourceColor = texture(InSampler, texCoord);
    float luminance = dot(sourceColor.rgb, vec3(0.2126, 0.7152, 0.0722));
    vec3 grayscaleColor = vec3(luminance);
    vec3 result = mix(sourceColor.rgb, grayscaleColor, clamp(GrayscaleAmount, 0.0, 1.0));
    fragColor = vec4(result, sourceColor.a);
}
