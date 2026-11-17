import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        ArrayList<Robo> robos = new ArrayList<>();

        int opt = -1;

        do {
            System.out.println();
            System.out.println("========================================");
            System.out.println(" --ROBÔS MTO LOUCOS(chupa elon musk!)--");
            System.out.println("========================================");
            System.out.println("  0. Sair");
            System.out.println("  1. Cadastrar Robô");
            System.out.println("  2. Consultar Robôs");
            System.out.println("  3. Listar Robôs");
            System.out.println("  4. Porradaria");
            System.out.println("  5. Curar Robô");
            System.out.println("  6. Excluir Robô");
            System.out.println("---------------------------------");
            System.out.println("Digite a operação: ");

            opt = buscarOperacao(s);

            switch (opt) {
                case 0:
                    System.out.println();
                    System.out.println("=================================");
                    System.out.println("       Programa Encerrado!");
                    System.out.println("=================================");
                    break;
                case 1:
                    cadastrarRobo(s, robos);
                    break;
                case 2:
                    consultarRobo(s, robos);
                    break;
                case 3:
                    listarRobos(robos);
                    break;
                case 4:
                    realizarCombate(s, robos);
                    break;
                case 5:
                    recuperarRobo(s, robos);
                    break;
                case 6:
                    excluirRobo(s, robos);
                    break;
                default:
                    System.out.println("Operação inexistente!");
                    break;
            }

        } while (opt != 0);

        s.close();
    }

    private static void cadastrarRobo(Scanner s, ArrayList<Robo> robos) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("       --CADASTRAR ROBÔ--");
        System.out.println("=================================");

        System.out.println("Digite o código do robô:");
        int codigo = s.nextInt();

        if (buscarRobo(robos, codigo) != null) {
            System.out.println("Código já cadastrado!");
            return;
        }

        System.out.println("Digite o nome do robô:");
        s.nextLine();
        String nome = s.nextLine();

        System.out.println("Digite o ataque:");
        int ataque = s.nextInt();

        System.out.println("Digite a defesa:");
        int defesa = s.nextInt();

        System.out.println(); 
        System.out.println("Selecione o tipo de robô:"); //MENU PARA ESCOLHER O TIPO DO ROBÔ!
        System.out.println("1 - Ofensivo");
        System.out.println("2 - Defensivo");
        System.out.println("3 - Equilibrado");

        int tipo;
        do {
            System.out.println("Digite o tipo:");
            tipo = buscarOperacao(s);

            if (tipo < 1 || tipo > 3) {
                System.out.println("Tipo inválido! Escolha entre 1 e 3.");
            }
        } while (tipo < 1 || tipo > 3);

        try {
            Robo robo;

            switch (tipo) { // AQUI ELE CRIA O ROBÔ COM BASE NA TUA ESCOLHA, E TUDO ARMAZENA NO MESMO ARRAY, SEM NECESSIDADE DE CRIAR UM NOVO!
                case 1:
                    robo = new RoboOfensivo(codigo, nome, ataque, defesa);
                    break;
                case 2:
                    robo = new RoboDefensivo(codigo, nome, ataque, defesa);
                    break;
                default:
                    robo = new RoboEquilibrado(codigo, nome, ataque, defesa);
                    break;
            }

            robos.add(robo);
            System.out.println("Robô cadastrado com sucesso!");
            System.out.println("Tipo: " + robo.getTipo());

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void consultarRobo(Scanner s, ArrayList<Robo> robos) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("       --CONSULTAR ROBÔ--");
        System.out.println("=================================");

        System.out.println("Digite o código do robô:");
        int codigo = s.nextInt();

        Robo roboEncontrado = buscarRobo(robos, codigo);

        if (roboEncontrado != null) {
            roboEncontrado.exibirStatus();
        } else {
            System.out.println();
            System.out.println("---------------------------------");
            System.out.println("     Robô não encontrado :(");
            System.out.println("---------------------------------");
        }
    }

    private static void listarRobos(ArrayList<Robo> robos) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("        --LISTA DE ROBÔS--");
        System.out.println("=================================");

        if (robos.isEmpty()) {
            System.out.println("Nenhum robô cadastrado.");
            return;
        }

        for (Robo r : robos) {
            r.exibirRobos();
            System.out.println("---------------------------------");
        }
    }

    private static void realizarCombate(Scanner s, ArrayList<Robo> robos) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("       --BATALHA DE ROBÔS--");
        System.out.println("=================================");

        System.out.println("Digite o código do primeiro robô:");
        int codigo1 = s.nextInt();

        System.out.println("Digite o código do segundo robô:");
        int codigo2 = s.nextInt();

        if (codigo1 == codigo2) {
            System.out.println("Os robôs devem ser diferentes!");
            return;
        }

        Robo robo1 = buscarRobo(robos, codigo1);
        Robo robo2 = buscarRobo(robos, codigo2);

        if (robo1 == null || robo2 == null) {
            System.out.println("Um ou ambos os robôs não foram encontrados!");
            return;
        }

        if (robo1.getEnergiaAtual() < 30 || robo2.getEnergiaAtual() < 30) {
            System.out.println("Os dois robôs precisam ter pelo menos 30 de energia!");
            return;
        }

        robo1.registrarCombate();
        robo2.registrarCombate();

        Robo primeiro;
        Robo segundo;

        if (robo1.getPontos() < robo2.getPontos()) {
            primeiro = robo1;
            segundo = robo2;
        } else if (robo2.getPontos() < robo1.getPontos()) {
            primeiro = robo2;
            segundo = robo1;
        } else if (robo1.getCodigo() < robo2.getCodigo()) {
            primeiro = robo1;
            segundo = robo2;
        } else {
            primeiro = robo2;
            segundo = robo1;
        }

        System.out.println();
        System.out.println("=================================");
        System.out.println(primeiro.getNome() + " VS " + segundo.getNome());
        System.out.println("Primeiro a atacar: " + primeiro.getNome());
        System.out.println("=================================");

        for (int rodada = 1; rodada <= 5; rodada++) {

            System.out.println();
            System.out.println("RODADA " + rodada);

            atacar(primeiro, segundo, rodada);

            if (segundo.estaDerrotado()) {
                System.out.println(segundo.getNome() + " ficou sem energia!");
                break;
            }

            atacar(segundo, primeiro, rodada);

            if (primeiro.estaDerrotado()) {
                System.out.println(primeiro.getNome() + " ficou sem energia!");
                break;
            }
        }

        mostrarResultado(robo1, robo2);
    }

    private static void atacar(Robo atacante, Robo defensor, int rodada) { // CALCULAR O ATAQUE

        int dano = atacante.calcularAtaque();

        dano -= defensor.getDefesa();

        if (dano < 5) {
            dano = 5;
        }

        if (rodada % 2 == 0) {
            dano += 5;
        }

        dano = defensor.reduzirDanoRecebido(dano);

        defensor.receberDano(dano);

        System.out.println(atacante.getNome() + " atacou " + defensor.getNome());
        System.out.println("Dano causado: " + dano);
        System.out.println("Energia de " + defensor.getNome() + ": "
                + defensor.getEnergiaAtual());
    }

    private static void mostrarResultado(Robo robo1, Robo robo2) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("RESULTADO FINAL");
        System.out.println("=================================");

        System.out.println(robo1.getNome() + " - Energia: "
                + robo1.getEnergiaAtual());
        System.out.println(robo2.getNome() + " - Energia: "
                + robo2.getEnergiaAtual());

        if (robo1.estaDerrotado()) {
            robo2.registrarVitoria();
            robo1.registrarDerrota();
            System.out.println("Vencedor: " + robo2.getNome());

        } else if (robo2.estaDerrotado()) {
            robo1.registrarVitoria();
            robo2.registrarDerrota();
            System.out.println("Vencedor: " + robo1.getNome());

        } else if (robo1.getEnergiaAtual() > robo2.getEnergiaAtual()) {
            robo1.registrarVitoria();
            robo2.registrarDerrota();
            System.out.println("Vencedor: " + robo1.getNome());

        } else if (robo2.getEnergiaAtual() > robo1.getEnergiaAtual()) {
            robo2.registrarVitoria();
            robo1.registrarDerrota();
            System.out.println("Vencedor: " + robo2.getNome());

        } else {
            robo1.registrarEmpate();
            robo2.registrarEmpate();
            System.out.println("EMPATE!");
        }
    }

    private static void recuperarRobo(Scanner s, ArrayList<Robo> robos) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("       --RECUPERAR ENERGIA--");
        System.out.println("=================================");

        System.out.println("Digite o código do robô:");
        int codigo = s.nextInt();

        Robo robo = buscarRobo(robos, codigo);

        if (robo == null) {
            System.out.println();
            System.out.println("---------------------------------");
            System.out.println("     Robô não encontrado :(");
            System.out.println("---------------------------------");
            return;
        }

        System.out.println("Digite a quantidade de energia desejada:");
        int quantidade = s.nextInt();

        String erro = robo.validarRecuperacao(quantidade);

        if (erro != null) {
            System.out.println();
            System.out.println(erro);
            return;
        }

        if (robo.recuperarEnergia(quantidade)) {
            System.out.println();
            System.out.println("---------------------------------");
            System.out.println("Energia recuperada com sucesso!");
            System.out.println("Energia atual: " + robo.getEnergiaAtual());
            System.out.println("Pontos restantes: " + robo.getPontos());
            System.out.println("---------------------------------");
        }
    }

    private static void excluirRobo(Scanner s, ArrayList<Robo> robos) {

        System.out.println();
        System.out.println("=================================");
        System.out.println("       --EXCLUIR ROBÔ--");
        System.out.println("=================================");

        System.out.println("Digite o código do robô:");
        int codigo = s.nextInt();

        Robo roboExcluir = buscarRobo(robos, codigo);

        if (roboExcluir == null) {
            System.out.println();
            System.out.println("---------------------------------");
            System.out.println("Robô não encontrado!");
            System.out.println("---------------------------------");

        } else if (roboExcluir.podeExcluir()) {
            robos.remove(roboExcluir);
            System.out.println();
            System.out.println("---------------------------------");
            System.out.println("Robô excluído com sucesso!");
            System.out.println("---------------------------------");

        } else {
            System.out.println();
            System.out.println("---------------------------------");
            System.out.println("Não é possível excluir!");
            System.out.println("O robô já participou de um combate.");
            System.out.println("---------------------------------");
        }
    }

    private static Robo buscarRobo(ArrayList<Robo> robos, int codigo) {

        for (Robo r : robos) {
            if (r.getCodigo() == codigo) {
                return r;
            }
        }
        return null;
    }

    public static int buscarOperacao(Scanner s) {

        int opt = -1;

        do {
            try {
                opt = s.nextInt();
            } catch (InputMismatchException e) {
                s.next();

                System.out.println();
                System.out.println("=================================");
                System.out.println("         OPERAÇÃO INVÁLIDA");
                System.out.println("=================================");
                System.out.println("Digite novamente a informação!");

                opt = -1;
            }

        } while (opt < 0);
        return opt;
    }
}
