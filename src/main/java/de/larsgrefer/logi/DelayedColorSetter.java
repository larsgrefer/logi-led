package de.larsgrefer.logi;

import lombok.RequiredArgsConstructor;

import java.awt.*;
import java.time.Duration;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class DelayedColorSetter {

    private final Consumer<Color> colorConsumer;

    private Color lastColor;

    public void setColor(Color color, Duration duration) {
        Color start = lastColor;
        lastColor = color;

        if (start == null) {
            colorConsumer.accept(color);
            return;
        }

        long startTime = System.nanoTime();
        long durationNanos = duration.toNanos();
        long endTime = startTime + durationNanos;

        int startR = start.getRed();
        int startG = start.getGreen();
        int startB = start.getBlue();

        int deltaR = color.getRed() - startR;
        int deltaG = color.getGreen() - startG;
        int deltaB = color.getBlue() - startB;

        for (long currentTime = startTime; currentTime < endTime; currentTime = System.nanoTime()) {

            double alreadyElapsedNs = currentTime - startTime;
            double factor = alreadyElapsedNs / durationNanos;

            int r = (int) (startR + (deltaR * factor));
            int g = (int) (startG + (deltaG * factor));
            int b = (int) (startB + (deltaB * factor));

            colorConsumer.accept(new Color(r, g, b));

        }

        colorConsumer.accept(color);
    }
}
