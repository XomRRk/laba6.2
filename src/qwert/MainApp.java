package qwert;

import java.util.Arrays;

public class MainApp {
    private static final int size = 60000000;

    public static void main(String[] args) {

        oTherd();
        tTherd();
    }

    /*public static float[] calculate(float[] array,int n) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + (i+n) / 5) * Math.cos(0.2f + (i+n) / 5) * Math.cos(0.4f + (i+n) / 2));
        }
        return array;
    }*/
    public static void calculate(float[] array, int n) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) (array[i] * Math.sin(0.2f + (i + n) / 5) * Math.cos(0.2f + (i + n) / 5) * Math.cos(0.4f + (i + n) / 2));
        }

    }

    public static void oTherd() {
        float[] array = new float[size];
        Arrays.fill(array, 1.0f);
        long start = System.currentTimeMillis();
        calculate(array, 0);
        System.out.printf("Общее время: %s мсек\n", System.currentTimeMillis() - start);
        System.out.println("Ответ 1 =" + array[array.length - 1]);

    }

    public static void tTherd() {
        float[] array = new float[size];
        float[] arrayl = new float[size / 2];
        float[] arrayr = new float[size / 2];
        Arrays.fill(array, 1.0f);
        System.arraycopy(array, 0, arrayl, 0, size / 2);
        System.arraycopy(array, size / 2, arrayr, 0, size / 2);
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            calculate(arrayl, 0);
        });
        Thread t2 = new Thread(() -> {
            calculate(arrayr, size / 2);

        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("Общее время: %s мсек\n", System.currentTimeMillis() - start);
        System.arraycopy(arrayl, 0, array, 0, size / 2);
        System.arraycopy(arrayr, 0, array, size / 2, size / 2);
        
        System.out.println("Ответ 2 =" + arrayr[arrayr.length - 1]);

    }
}
