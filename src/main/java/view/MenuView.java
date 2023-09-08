package view;

import service.*;

public class MenuView {
    AlunoView alunoView=new AlunoView();
    AlunoService alunoService = new AlunoService();
    CursoView cursoView=new CursoView();
    MatriculaView matriculaView=new MatriculaView();
    ProfessorView professorView=new ProfessorView();
    InputService inputService = new InputService();
    ProfessorService professorService=new ProfessorService();
    EscolaService escolaService=new EscolaService();
    public void MenuPrincipal() {
        System.out.println("1 - Área para Aluno");
        System.out.println("2 - Área para Curso");
        System.out.println("3 - Área para Professor");
        System.out.println("4 - Área para Matricula");
        System.out.println("5 - Outros");
        System.out.println("6 - Sair.");
    }
    public void escolhaPrincipal() {
        int option;
        do {
            MenuPrincipal();
            option = inputService.lerIntDoUsuario("Digite opção desejada: ");

            switch (option) {
                case 1:{
                    alunoView.opcoesAluno();
                    break;
                }
                case 2:{
                    cursoView.opcoesCurso();
                    break;
                }
                case 3:{
                    professorView.opcoesProfessor();
                    break;
                }
                case 4:{
                    matriculaView.opcoesMatricula();
                    break;
                }
                case 5:{
                    outrasOpcoes();
                    break;
                }
                case 6:{
                    new MenuView();
                    break;
                }
                default:{
                    System.out.println("Opção inválida, tente novamente!");
                }
            }
        } while (option != 0);
    }
    public void MenuConsulta() {
        System.out.println("\n=====MENU CONSULTA=====\n");
        System.out.println("1 - Alunos matriculados em um curso específico.");
        System.out.println("2 - Cursos ministrados por um professor.");
        System.out.println("3 - Alunos que não estão matriculados em nenhum curso.");
        System.out.println("4 - Cursos sem alunos matriculados.");
        System.out.println("5 - Alunos matriculados em mais de um curso.");
        System.out.println("6 - Voltar ao menu principal.");
    }
    public void outrasOpcoes() {
        int option;
        do {
            MenuConsulta();
            option = inputService.lerIntDoUsuario("Digite opção desejada: ");

            switch (option) {
                case 1:{
                    alunoService.consultarAlunosPorCurso();
                    break;
                }
                case 2:{
                    professorService.consultarCursoPorProfessor();
                    break;
                }
                case 3:{
                    escolaService.consultarAlunosSemMatricula();
                    break;
                }
                case 4:{
                    escolaService.consultarCursosSemAlunos();
                    break;
                }
                case 5:{
                    escolaService.consultarAlunosComMaisDeUmCurso();
                    break;
                }
                case 6:{
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
