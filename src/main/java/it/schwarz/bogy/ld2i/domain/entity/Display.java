package it.schwarz.bogy.ld2i.domain.entity;

import java.util.Arrays;

public enum Display {
    DISPLAY_0ABE5B6D74D4_35("0ABE5B6D74D4", 384, 180),
    DISPLAY_0ABE5B7D74D5_35("0ABE5B7D74D5", 384, 180),
    DISPLAY_0AC0F23A70D8_16("0AC0F23A70D8", 200, 200);

    private final String displayId;
    private final int width;
    private final int height;

    Display(String displayId, int width, int height) {
        this.displayId = displayId;
        this.width = width;
        this.height = height;
    }

    public static Display byId(String displayId) {
        return Arrays.stream(Display.values())
                .filter(it -> it.getDisplayId().equals(displayId)).findFirst().orElse(null);
    }

    public String getDisplayId() {
        return displayId;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
