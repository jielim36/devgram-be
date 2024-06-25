package com.jielim36.devgram.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonSerializer;

public enum PostVisibilityDurationEnum {
    ONE_DAY(1),
    THREE_DAYS(3),
    SEVEN_DAYS(7),
    FOURTEEN_DAYS(14),
    THIRTY_DAYS(30),
    NINETY_DAYS(90),
    FOREVER(0);

    @JsonValue
    private final int duration;

    PostVisibilityDurationEnum(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public static PostVisibilityDurationEnum fromDuration(int duration) {
        for (PostVisibilityDurationEnum value : PostVisibilityDurationEnum.values()) {
            if (value.duration == duration) {
                return value;
            }
        }
        throw new IllegalArgumentException("Unknown duration: " + duration);
    }

}
