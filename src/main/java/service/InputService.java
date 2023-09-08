package service;

import java.util.Scanner;

public class InputService {
    private final Scanner scanner;
    public InputService() {
        scanner = new Scanner(System.in);
    }
    public int lerIntDoUsuario(String mensagem) {
        while (true) {
            System.out.println(mensagem);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro válido.");
            }
        }
    }
    public String lerStringDoUsuario(String mensagem) {
        while (true) {
            System.out.println(mensagem);
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Entrada inválida. Digite um valor válido.");
            }
        }
    }
}
