package application.tratandoeventos;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class MainApplication extends Application {
    private Queue queue = new Queue();
    private Stack stack = new Stack();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculadora Pós-Fixada");

        TextField inputField = new TextField();
        inputField.setPromptText("Digite a expressão em notação pós-fixada");

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        Button calcularBtn = new Button("Calcular");

        calcularBtn.setOnAction(e -> {
            queue.clear();
            stack.clear();
            resultArea.clear();

            String input = inputField.getText().trim();
            if (input.isEmpty()) {
                resultArea.setText("Expressão vazia.");
                return;
            }

            for (String token : input.split("\\s+")) {
                queue.enqueue(token);
            }

            try {
                while (!queue.isEmpty()) {
                    String token = queue.dequeue();
                    if (isNumeric(token)) {
                        stack.push(Double.parseDouble(token));
                    } else if (isOperator(token)) {
                        if (stack.size() < 2) throw new IllegalArgumentException("Operandos insuficientes.");
                        double b = stack.pop();
                        double a = stack.pop();
                        double res = evaluate(a, b, token);
                        stack.push(res);
                    } else {
                        throw new IllegalArgumentException("Operador inválido: " + token);
                    }
                }

                if (stack.size() != 1) throw new IllegalStateException("Expressão malformada.");

                resultArea.setText("Resultado: " + stack.pop());
            } catch (Exception ex) {
                resultArea.setText("Erro: " + ex.getMessage());
            }

            if (!stack.isEmpty() || !queue.isEmpty()) {
                resultArea.appendText("\nErro: estruturas não vazias após o cálculo. Expressão inválida.");
            }
        });

        VBox layout = new VBox(10, inputField, calcularBtn, resultArea);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String token) {
        return "+-*/%".contains(token);
    }

    private double evaluate(double a, double b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) throw new ArithmeticException("Divisão por zero.");
                return a / b;
            case "%":
                if (b == 0) throw new ArithmeticException("Divisão por zero.");
                return a % b;
            default: throw new IllegalArgumentException("Operador desconhecido.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}