package wjk.android.chart;


public class ColorDepth {

    public static boolean isLight(int rgb){
        int[] colors = new int[]{(rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF};

        return (colors[0] * 0.299 + colors[1] * 0.587 + colors[2] * 0.114) >= 192;
    }
}
