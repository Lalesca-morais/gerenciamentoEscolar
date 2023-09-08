package view;

import model.ProfessorModel;
import service.InputService;
import service.ProfessorService;

public class ProfessorView {
    ProfessorService professorService = new ProfessorService();
    InputService inputService = new InputService();
    public void MenuProfessor() {
        System.out.println("\n=====MENU PROFESSOR=====\n");
        System.out.println("1 - Adicinar Professor");
        System.out.println("2 - Deletar Professor");
        System.out.println("3 - Alterar informações do professor.");
        System.out.println("4 - Listar Professor");
        System.out.println("5 - Voltar");
    }
    public void opcoesProfessor() {
        int option;
        do {
            MenuProfessor();
            option = inputService.lerIntDoUsuario("Qual opção você deseja: ");
            switch (option) {
                case 1:{
                    professorService.adicionarProfessor();
                    break;
                }
                case 2:{
                    professorService.deletarProfessor();
                    break;
                }
                case 3:{
                    professorService.alterarInformacoes();
                    break;
                }
                case 4:{
                    professorService.consultarTodosProfessores();
                    break;
                }
                case 0: {
                    new MenuView();
                    break;
                }
                default:{
                    System.out.println("Opção inválida, tente novamente!");
                }
            }
        } while (option != 0);
    }
}
