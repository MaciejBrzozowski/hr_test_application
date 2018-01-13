package pl.brzozowski.maciej.clis.utilities;

public class StringCalculations {

    public static double divide(String equation) {
        if (!equation.contains("/")) {
            return Double.valueOf(equation);
        }
        return (Double.valueOf(equation.split("/")[0])) / (Double.valueOf(equation.split("/")[1]));
    }
}
