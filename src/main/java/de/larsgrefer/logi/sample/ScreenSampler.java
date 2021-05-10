package de.larsgrefer.logi.sample;

import com.logitech.gaming.LogiLED;
import de.larsgrefer.logi.ColorService;
import de.larsgrefer.logi.LogiLedHandler;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;

import java.awt.*;
import java.awt.peer.RobotPeer;
import java.lang.reflect.Constructor;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Setter
@RequiredArgsConstructor
public class ScreenSampler implements Runnable {

    private final LogiLedHandler logiLedHandler;

    private final Rectangle area;

    private int zone;

    private boolean enabled = true;

    @SneakyThrows
    @Override
    public void run() {

        Class<?> peerClass = Class.forName("sun.awt.windows.WRobotPeer");

        Constructor<?> declaredConstructor = peerClass.getDeclaredConstructor(GraphicsDevice.class);
        declaredConstructor.setAccessible(true);
        RobotPeer peer = (RobotPeer) declaredConstructor.newInstance((Object) null);

        List<Long> captureTimes = new ArrayList<>(100);
        List<Long> avgTimes = new ArrayList<>(100);
        List<Long> setTimes = new ArrayList<>(100);

        while (enabled) {

            long start = System.nanoTime();

            int[] rgbPixels = peer.getRGBPixels(area);
            //BufferedImage screenCapture = robot.createScreenCapture(area);
            captureTimes.add(System.nanoTime() - start);

            start = System.nanoTime();
            Color avgColor = ColorService.getAvgColor(rgbPixels);
            avgTimes.add(System.nanoTime() - start);

            start = System.nanoTime();
            logiLedHandler.setLightingForTargetZone(LogiLED.DeviceType_Speaker, zone, avgColor);
            setTimes.add(System.nanoTime() - start);

            if (captureTimes.size() == 100) {
                double ns = captureTimes.stream().mapToLong(l -> l).average().getAsDouble();
                System.out.print("Capture: ");
                System.out.println(Duration.ofNanos((long) ns).toMillis());
                captureTimes.clear();
            }
            if (avgTimes.size() == 100) {
                double ns = avgTimes.stream().mapToLong(l -> l).average().getAsDouble();
                System.out.print("avgTimes: ");
                System.out.println(Duration.ofNanos((long) ns).toMillis());
                avgTimes.clear();
            }
            if (setTimes.size() == 100) {
                double ns = setTimes.stream().mapToLong(l -> l).average().getAsDouble();
                System.out.print("setTimes: ");
                System.out.println(Duration.ofNanos((long) ns).toMillis());
                setTimes.clear();
            }

            Thread.sleep(10);

        }
    }
}
