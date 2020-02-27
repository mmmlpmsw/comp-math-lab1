package mmmlpmsw.comp_math.lab1.utils;

public class ResidualFormatter {

    public static String format(double residual) {
        String result = String.valueOf(residual);
        int cutIndex = result.indexOf("E");
        String number = result.substring(0, cutIndex);
        String order = result.substring(cutIndex + 1);
        result = number + " * 10 ^ (" + order + ")";
        return result;
    }
}
