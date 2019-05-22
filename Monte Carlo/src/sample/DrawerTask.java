package sample;

import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class DrawerTask extends Task {
    private final double MIN = -8, MAX = 8;
    private GraphicsContext graphics;
    private double input;

    @Override
    protected Object call() {

        Random rand = new Random();
        double x, y, result, hit = 0;


        BufferedImage img = new BufferedImage((int) graphics.getCanvas().getWidth(),
                (int) graphics.getCanvas().getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < input; i++) {

            x = MIN + (MAX - MIN) * rand.nextDouble();
            y = MIN + (MAX - MIN) * rand.nextDouble();             //loswanie randoma


            if (Equation.calc(x, y)) {
                double x1 = graphics.getCanvas().getWidth() * (x - MIN) / (MAX - MIN);
                double y1 = graphics.getCanvas().getHeight() * (y - MIN) / (MAX - MIN); //skalowanie

                img.setRGB((int) x1, (int) (-y1 + graphics.getCanvas().getHeight()), Color.YELLOW.getRGB()); //ustawia pixel, dla punktow nalezacych do funkcji
                hit++;
            } else {
                double x1 = graphics.getCanvas().getWidth() * (x - MIN) / (MAX - MIN);
                double y1 = graphics.getCanvas().getHeight() * (y - MIN) / (MAX - MIN);

                img.setRGB((int) x1, (int) (-y1 + graphics.getCanvas().getHeight()), Color.BLUE.getRGB()); //same, dla punktow nie nalezacych do funkcji
            }

            if (i % 1000 == 0) {
                graphics.drawImage(SwingFXUtils.toFXImage(img, null), 0, 0);
            }

            updateProgress(i, input);
            if (isCancelled()) break;
        }

        graphics.drawImage(SwingFXUtils.toFXImage(img, null), 0, 0); //ostatnia aktualizacja
        result = hit * (MAX - MIN) * (MAX - MIN) / input;
        return result;         //value do hitow
    }

    public DrawerTask(GraphicsContext graphics, double input) {
        this.graphics = graphics;
        this.input = input;
    }
}
