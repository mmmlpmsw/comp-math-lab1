package mmmlpmsw.comp_math.lab1;

import mmmlpmsw.comp_math.lab1.Gaussian_elimination.Equation;
import mmmlpmsw.comp_math.lab1.Gaussian_elimination.LinearSystem;
import static mmmlpmsw.comp_math.lab1.utils.Utilities.colorize;

import java.io.*;

public class FileInputReader {

    public LinearSystem getFromFile(String filename) throws InputParseException {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            int numberOfUnknowns = Integer.parseInt(reader.readLine());
            ParseStringToEquation.check(numberOfUnknowns);

            Equation[] equations = new Equation[numberOfUnknowns];

            for (int i = 0; i < numberOfUnknowns; i ++) {
                String coefs = reader.readLine();
                Equation equation = ParseStringToEquation.trimStringToEquation(numberOfUnknowns, coefs);
                equations[i] = equation;
            }
            return new LinearSystem(equations);
        } catch (FileNotFoundException e) {
            throw new InputParseException(colorize("[[RED]]File not found.[[RESET]]"));
        } catch (NumberFormatException | IOException | NullPointerException e ) {
            throw new InputParseException("[[RED]]Incorrect input.[[RESET]]");
        }
    }

}
