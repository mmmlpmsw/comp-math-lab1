package mmmlpmsw.comp_math.lab1;

import mmmlpmsw.comp_math.lab1.Gaussian_elimination.Equation;
import mmmlpmsw.comp_math.lab1.Gaussian_elimination.LinearSystem;
import static mmmlpmsw.comp_math.lab1.utils.Utilities.colorize;

import java.io.*;
import java.util.Scanner;

public class InputReader {
    private Scanner scanner;
    public InputReader() {
        scanner = new Scanner(System.in);
    }
    public void process() throws IOException {
        System.out.println(getHelp());
        while (true) {
            try {
                System.out.print(">>> ");
                String response = processCommand(getNextCommand());
                System.out.println(response);
            } catch (InputParseException e) {
                System.out.print(e.getMessage());
            }
        }
    }

    private String processCommand(String request) throws InputParseException {
        if (request == null) {
            System.exit(0);
        }
        request = request.trim();
        UserCommand command = divideCommand(request);

        switch (command.name) {
            case "exit":
                System.exit(0);
            case "file":
                LinearSystem linearSystem = new FileInputReader().getFromFile(command.value);
                OutputCombiner combiner = new OutputCombiner(linearSystem);
                if (!combiner.combineOutput())
                    return "";
                return "";

            case "random": {
                if (command.value.equals("0") || command.value == null)
                    throw new InputParseException("incorrect command");
                int numberOfUnknowns = parse(command.value);
                ParseStringToEquation.check(numberOfUnknowns);
                Equation[] equations = new Equation[numberOfUnknowns];
                System.out.println("Coefficients: ");
                for (int i = 0; i < numberOfUnknowns; i++) {
                    double[] coefficients = new double[numberOfUnknowns + 1];
                    for (int j = 0; j < numberOfUnknowns + 1; j++) {
                        double coefficient = Math.random() * 200 - 100;
//                        coefficient = Math.round(coefficient);
                        coefficient = Math.round(coefficient * 100) / 100.0;
                        coefficients[j] = coefficient;
                        System.out.print(coefficient + " ");
                    }
                    System.out.println();
                    equations[i] = new Equation(coefficients);
                }
                LinearSystem system = new LinearSystem(equations);
                OutputCombiner outputCombiner = new OutputCombiner(system);
                outputCombiner.combineOutput();
                return "";
            }
            //todo
            // fixme цветной текст
            case "enter": {
                System.out.print("Enter the number of unknowns >>>");
                int count = scanner.nextInt();
                scanner.nextLine();

                System.out.println("In each string enter the coefficients of the system.");
                Equation[] equations = new Equation[count];
                for (int i = 0; i < count; i++)
                    equations[i] = ParseStringToEquation.trimStringToEquation(count, scanner.nextLine());
                LinearSystem system = new LinearSystem(equations);
                OutputCombiner outputCombiner = new OutputCombiner(system);
                outputCombiner.combineOutput();
                return "";
            }

            case "help":
                return getHelp();

            default:
                return "No such command: " + command.name + " please, use 'help;' to know, how to use it.";
        }
    }

    private UserCommand divideCommand(String request) {
        int spacePosition = request.indexOf(' ');
        if (spacePosition == -1) {return new UserCommand(request, null);}
        else return new UserCommand(request.substring(0, spacePosition), request.substring(spacePosition+1));
    }

    private String getNextCommand() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();

        boolean inString = false;
        loop: do {
            String raw = reader.readLine();
            if (raw == null) return null;
            char[] data = raw.toCharArray();
            for (char current : data) {
                if (current != ';' || inString) {  builder.append(current); }
                if (current == '"') { inString = !inString;}
                if (current == ';' && !inString) break loop;
            }
        } while (true);
        return builder.toString();
    }

    private String getHelp() {
        return "To enter from file - use 'file <filename>', " +
                "\nto randomize coefficients use 'random <number of unknowns>;',  " +
                "\nto enter equation coefficients from the keyboard use 'enter;' and wait for further directions, " +
                "\nto exit enter 'exit;'" +
                "\nto get help use 'help;'. All commands must end in ';'.";
    }

    private int parse (String s) throws InputParseException {
        try {
            Integer d = Integer.parseInt(s);
            return d;
        } catch (NumberFormatException e) {
            throw new InputParseException("Not an integer, try again.");
        }
    }

    private static class UserCommand {
        String name;
        String value;

        UserCommand (String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}