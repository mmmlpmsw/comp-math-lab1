package mmmlpmsw.comp_math.lab1;

import mmmlpmsw.comp_math.lab1.Gaussian_elimination.Equation;
import mmmlpmsw.comp_math.lab1.Gaussian_elimination.LinearSystem;

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
            throw new InputParseException("File not found.");
        } catch (NumberFormatException | IOException | NullPointerException e ) {
            throw new InputParseException("Incorrect input");
        }
    }

}
