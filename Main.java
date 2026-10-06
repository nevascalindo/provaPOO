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
                    System.out.println();
                    System.out.println("=================================");
                    System.out.println("       --CADASTRAR ROBÔ--");
                    System.out.println("=================================");

                    System.out.println("Digite o código do robô: ");
                    int codigo = s.nextInt();

                    if (codigo <= 0) {
                        System.out.println("O código deve ser positivo!");
                    } else {
                        boolean codigoExiste = false;

                        for (Robo r : robos) {
                            if (r.codigo == codigo) {
                                codigoExiste = true;
                                break;
                            }
                        }
                        if (codigoExiste) {
                            System.out.println("Código já cadastrado!");
                        } else {

                            System.out.println("Digite o nome do robô: ");
                            String nome = s.next();

                            System.out.println("Digite o ataque: ");
                            int ataque = s.nextInt();

                            if (ataque < 10 || ataque > 30) {
                                System.out.println("Ataque inválido! Deve estar entre 10 e 30.");
                            } else {

                                System.out.println("Digite a defesa: ");
                                int defesa = s.nextInt();

                                if (defesa < 0 || defesa > 20) {
                                    System.out.println("Defesa inválida! Deve estar entre 0 e 20.");
                                } else {

                                    Robo robo = new Robo(
                                            codigo,
                                            nome,
                                            ataque,
                                            defesa);

                                    robos.add(robo);

                                    System.out.println("Robô cadastrado com sucesso!");
                                }
                            }
                        }
                    }
                    break;

                case 2:

                    System.out.println();
                    System.out.println("=================================");
                    System.out.println("       --CONSULTAR ROBÔ--");
                    System.out.println("=================================");

                    System.out.println("Digite o código do robô: ");
                    int codigoRobo = s.nextInt();

                    Robo roboEncontrado = null;

                    for (Robo r : robos) {
                        if (r.codigo == codigoRobo) {
                            roboEncontrado = r;
                            break;
                        }

                    }
                    if (roboEncontrado != null) {
                        roboEncontrado.exibirStatus();
                    } else {
                        System.out.println();
                        System.out.println("---------------------------------");
                        System.out.println("     Robô não encontrado :(");
                        System.out.println("---------------------------------");
                    }
                    break;

                case 3:

                    System.out.println();
                    System.out.println("=================================");
                    System.out.println("        --LISTA DE ROBÔS--");
                    System.out.println("=================================");

                    for (Robo r : robos) {
                        r.exibirRobos();
                        System.out.println("---------------------------------");
                    }
                    break;

                case 4:

                    System.out.println();
                    System.out.println("=================================");
                    System.out.println("       --BATALHA DE ROBÔS--");
                    System.out.println("=================================");

                    System.out.println("Digite o código do primeiro robô: ");
                    int codigo1 = s.nextInt();

                    System.out.println("Digite o código do segundo robô: ");
                    int codigo2 = s.nextInt();

                    Robo robo1 = null;
                    Robo robo2 = null;

                    for (Robo r : robos) {
                        if (r.codigo == codigo1) {
                            robo1 = r;
                        }
                        if (r.codigo == codigo2) {
                            robo2 = r;
                        }
                    }

                    if (codigo1 == codigo2) {
                        System.out.println("Os robôs devem ser diferentes!");
                        break;
                    }

                    if (robo1 == null || robo2 == null) {
                        System.out.println("Um ou ambos os robôs não foram encontrados!");
                        break;
                    }

                    if (robo1.energiaAtual < 30 || robo2.energiaAtual < 30) {
                        System.out.println("Os dois robôs precisam possuir pelo menos 30 de energia!");
                        break;
                    }

                    System.out.println();
                    System.out.println("=================================");
                    System.out.println("        HORA DA PORRADA!");
                    System.out.println("=================================");

                    System.out.println(robo1.nome + " VS " + robo2.nome);

                    Robo primeiro;
                    Robo segundo;
                    

                    if (robo1.pontos < robo2.pontos) {

                        primeiro = robo1;
                        segundo = robo2;

                    } else if (robo2.pontos < robo1.pontos) {

                        primeiro = robo2;
                        segundo = robo1;

                    } else {

                        if (robo1.codigo < robo2.codigo) {

                            primeiro = robo1;
                            segundo = robo2;

                        } else {

                            primeiro = robo2;
                            segundo = robo1;
                        }
                    }

                    primeiro.registrarCombate();
                    segundo.registrarCombate();
                    System.out.println();
                    System.out.println("Primeiro a atacar: " + primeiro.nome);

                    for (int rodada = 1; rodada <= 5; rodada++) {

                        System.out.println();
                        System.out.println("---------------------------------");
                        System.out.println("            RODADA " + rodada);
                        System.out.println("---------------------------------");

                        int dano = primeiro.calcularDano(segundo);

                        if (rodada % 2 == 0) {
                            dano += 5;
                        }

                        segundo.receberDano(dano);

                        System.out.println(
                                primeiro.nome + " atacou " + segundo.nome);

                        System.out.println(
                                "Dano causado: " + dano);

                        System.out.println(
                                "Energia de " + segundo.nome +": " + segundo.energiaAtual);

                        if (segundo.estaDerrotado()) {

                            System.out.println(segundo.nome + " ficou sem energia!");

                            break;
                        }

                        dano = segundo.calcularDano(primeiro);

                        if (rodada % 2 == 0) {
                            dano += 5;
                        }

                        primeiro.receberDano(dano);

                        System.out.println();
                        System.out.println(
                                segundo.nome + " atacou " + primeiro.nome);

                        System.out.println(
                                "Dano causado: " + dano);

                        System.out.println(
                                "Energia de " + primeiro.nome +": " + primeiro.energiaAtual);

                        if (primeiro.estaDerrotado()) {

                            System.out.println(primeiro.nome + " ficou sem energia!");

                            break;
                        }
                    }

                    System.out.println();
                    System.out.println("=================================");
                    System.out.println("          RESULTADO FINAL");
                    System.out.println("=================================");

                    System.out.println(
                            robo1.nome + " - Energia: " +
                                    robo1.energiaAtual);

                    System.out.println(
                            robo2.nome + " - Energia: " +
                                    robo2.energiaAtual);

                    if (robo1.energiaAtual == 0) {

                        robo2.registrarVitoria();
                        robo1.registrarDerrota();

                        System.out.println();
                        System.out.println("Vencedor: " + robo2.nome);

                    } else if (robo2.energiaAtual == 0) {

                        robo1.registrarVitoria();
                        robo2.registrarDerrota();

                        System.out.println();
                        System.out.println("Vencedor: " + robo1.nome);

                    } else if (robo1.energiaAtual > robo2.energiaAtual) {

                        robo1.registrarVitoria();
                        robo2.registrarDerrota();

                        System.out.println();
                        System.out.println("Vencedor: " + robo1.nome);

                    } else if (robo2.energiaAtual > robo1.energiaAtual) {

                        robo2.registrarVitoria();
                        robo1.registrarDerrota();

                        System.out.println();
                        System.out.println("Vencedor: " + robo2.nome);

                    } else {

                        robo1.registrarEmpate();
                        robo2.registrarEmpate();

                        System.out.println();
                        System.out.println("EMPATE!");
                    }
                    break;

                case 5:
                    System.out.println();
                    System.out.println("=================================");
                    System.out.println("       --RECUPERAR ENERGIA--");
                    System.out.println("=================================");

                    System.out.println("Digite o código do robô: ");
                    int codigoRoboEnergia = s.nextInt();

                    Robo roboEncontradoEnergia = null;

                    for (Robo r : robos) {
                        if (r.codigo == codigoRoboEnergia) {
                            roboEncontradoEnergia = r;
                            break;
                        }
                    }

                    if (roboEncontradoEnergia == null) {

                        System.out.println();
                        System.out.println("---------------------------------");
                        System.out.println("     Robô não encontrado :(");
                        System.out.println("---------------------------------");

                        break;
                    }

                    System.out.println("Digite a quantidade de energia desejada: ");
                    int quantidade = s.nextInt();

                    if (quantidade <= 0) {

                        System.out.println();
                        System.out.println("A quantidade deve ser positiva!");

                    } else if (quantidade % 10 != 0) {

                        System.out.println();
                        System.out.println("A quantidade deve ser múltipla de 10!");

                    } else {

                        int custo = quantidade / 10;

                        if (roboEncontradoEnergia.pontos < custo) {

                            System.out.println();
                            System.out.println("Pontos insuficientes!");
                            System.out.println("Custo: " + custo);
                            System.out.println("Pontos disponíveis: " + roboEncontradoEnergia.pontos);

                        } else if (roboEncontradoEnergia.energiaAtual + quantidade > 100) {

                            System.out.println();
                            System.out.println("A energia não pode ultrapassar 100!");
                        } else {

                            roboEncontradoEnergia.recuperarEnergia(quantidade);

                            System.out.println();
                            System.out.println("---------------------------------");
                            System.out.println("Energia recuperada com sucesso!");
                            System.out.println("Energia atual: " + roboEncontradoEnergia.energiaAtual);
                            System.out.println("Pontos restantes: " + roboEncontradoEnergia.pontos);
                            System.out.println("---------------------------------");
                        }
                    }break;
                case 6:

                    System.out.println();
                    System.out.println("=================================");
                    System.out.println("       --EXCLUIR ROBÔ--");
                    System.out.println("=================================");

                    System.out.println("Digite o código do robô:");
                    int codigoExcluir = s.nextInt();

                    Robo roboExcluir = null;

                    for (Robo r : robos) {
                        if (r.codigo == codigoExcluir) {
                            roboExcluir = r;
                            break;
                            }
                        }

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
                    }break;
            }
        } while (opt != 0);
        s.close();
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