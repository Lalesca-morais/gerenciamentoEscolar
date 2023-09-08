package view;

import service.InputService;
import service.MatriculaService;
public class MatriculaView {
    MatriculaService matriculaService=new MatriculaService();
    InputService inputService =new InputService();
    public void MenuMatricula() {
        System.out.println("\n=====MENU MATRICULA=====\n");
        System.out.println("1 - Fazer Matricula.");
        System.out.println("2 - Deletar Matricula.");
        System.out.println("3 - Listar Matricula.");
        System.out.println("4 - Voltar ao menu principal.");
    }
    public void opcoesMatricula() {
        int option;
        do {
            MenuMatricula();
            option = inputService.lerIntDoUsuario("Qual opção você deseja: ");

            switch (option) {
                case 1:{
                    matriculaService.adicionarMatricula();
                    break;
                }
                case 2:{
                    matriculaService.deletarMatriculaComInput();
                    break;
                }
                case 3:{
                    matriculaService.consultarTodasMatriculas();
                    break;
                }
                case 4:{
                    new MenuView();
                    break;
                }
                default:{
                    System.out.println("Opção inválida, tente novamente!");
                    break;
                }
            }
        } while (option != 0);
    }
}
