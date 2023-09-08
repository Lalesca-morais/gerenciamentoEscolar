package view;

import model.AlunosModel;
import service.AlunoService;
import service.InputService;

public class AlunoView {
    AlunoService alunoService= new AlunoService();
    AlunosModel alunosModel= new AlunosModel();
    InputService inputService = new InputService();
    public void MenuAluno() {
        System.out.println("\n=====MENU DO ALUNO=====\n");
        System.out.println("Digite uma das seguinte opções");
        System.out.println("1 - Adicinar Aluno.");
        System.out.println("2 - Deletar Aluno.");
        System.out.println("3 - Atualizar Aluno.");
        System.out.println("4 - Listar Alunos.");
        System.out.println("5 - Voltar ao menu principal.");
    }

    public void opcoesAluno() {
        int option = 0;
        do {
            if(option != 5){
                MenuAluno();
            }
            option = inputService.lerIntDoUsuario("Digite a opção que deseja: ");

            switch (option) {
                case 1: {
                    alunoService.adicionarAluno();
                    break;
                }
                case 2:{
                    alunoService.deletarAlunoComInput();
                    break;
                }
                case 3:{
                    alunoService.atualizarAluno();
                    break;
                }
                case 4:{
                    alunoService.listarAlunos();
                    break;
                }
                case 5:{
                    new MenuView().MenuPrincipal();
                    break;
                }
                default: {
                    System.out.println("Opção inválida, tente novamente!");
                }
            }
        } while (option != 0);
    }
}
