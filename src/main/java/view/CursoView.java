package view;

import service.CursoService;
import service.InputService;
public class CursoView {
    CursoService cursoService = new CursoService();
    InputService inputService = new InputService();
    public void MenuCurso() {
        System.out.println("\n=====MENU CURSOS=====\n");
        System.out.println("1 - Adicinar Curso");
        System.out.println("2 - Deletar Curso");
        System.out.println("3 - Atualizar informações de um curso");
        System.out.println("4 - Listar Cursos");
        System.out.println("5 - Voltar ao menu principal.");
    }
    public void opcoesCurso() {
        int option = 0;
        do {
            if(option != 5){
                MenuCurso();
            }
            option = inputService.lerIntDoUsuario("Digite a opção que deseja: ");

            switch (option) {
                case 1:{
                    cursoService.adicionarCurso();
                    break;
                }
                case 2:{
                    cursoService.deletarCursoComInput();
                    break;
                }
                case 3:{
                    cursoService.atualizarCurso();
                    break;
                }
                case 4:{
                    cursoService.consultarTodosCursos();
                    break;
                }
                case 5:{
                    new MenuView().MenuPrincipal();
                    break;
                }
                default:{
                    System.out.println("Opção inválida, tente novamente!");
                }
            }
        } while (option != 0);
    }
}