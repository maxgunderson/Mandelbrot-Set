import javax.swing.*;
import java.awt.*;

public class MandelbrotSet extends JPanel {

    final int width = 600;
    final int height = 600;
    final int maxIterations = 100;
    final float lowerRange = -2;
    final float upperRange = 2;

    public MandelbrotSet() {
        setPreferredSize(new Dimension(width, height));
    }

    public static float map(float inputValue, float initialRange1, float initialRange2, float finalRange1,
            float finalRange2) {
        float initRange = initialRange2 - initialRange1;
        float proportion = inputValue / initRange;

        float newProportion = proportion * (finalRange2 - finalRange1);
        float mappedValue = finalRange1 + newProportion;

        return mappedValue;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                float cReal = map(x, 0, width, lowerRange, upperRange);
                float cImag = map(y, 0, height, lowerRange, upperRange);

                float zReal = 0;
                float zImag = 0;

                int i = 0;

                while (i < maxIterations && (zReal * zReal) + (zImag * zImag) < 4) {
                    float real = zReal * zReal - zImag * zImag + cReal;
                    float imag = 2 * zReal * zImag + cImag;

                    zReal = real;
                    zImag = imag;

                    i++;
                }

                if (i == maxIterations) {
                    Color color = new Color(0, 0, 0);
                    g.setColor(color);
                    g.fillRect(x, y, 1, 1);

                } else {
                    float h = (float) i / maxIterations * 2;
                    Color color = Color.getHSBColor(h, 1f, 1f);
                    g.setColor(color);
                    g.fillRect(x, y, 1, 1);

                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Mandelbrot Set");
        MandelbrotSet canvas = new MandelbrotSet();
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }
}