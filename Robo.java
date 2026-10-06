public class Robo {

    public int codigo;
    public String nome;
    public int ataque;
    public int defesa;
    public int energiaAtual;
    public int vitorias;
    public int derrotas;
    public int pontos;
    public int combates;

    Robo(int codigo, String nome, int ataque, int defesa) {
        this.codigo = codigo;
        this.nome = nome;
        this.ataque = ataque;
        this.defesa = defesa;

        this.energiaAtual = 100;
        this.vitorias = 0;
        this.derrotas = 0;
        this.pontos = 0;
        this.combates = 0;
    }

    public void exibirStatus() {
        System.out.println();
        System.out.println("---------------------------------");
        System.out.println("Código: " + this.codigo);
        System.out.println("Nome: " + this.nome);
        System.out.println("Ataque: " + this.ataque);
        System.out.println("Defesa: " + this.defesa);
        System.out.println("Energia atual: " + this.energiaAtual);
        System.out.println("Vitórias: " + this.vitorias);
        System.out.println("Derrotas: " + this.derrotas);
        System.out.println("Pontos: " + this.pontos);
        System.out.println("---------------------------------");
    }

    public void exibirRobos() {
        System.out.println("Código: " + this.codigo);
        System.out.println("Nome: " + this.nome);
        System.out.println("Ataque:" + this.ataque);
        System.out.println("Defesa:" + this.defesa);

        if (this.energiaAtual < 30) {
            System.out.println("Situação: Em Recuperação");
        } else {
            System.out.println("Situação: Disponível");
        }
    }

    public int calcularDano(Robo adversario) {

        int dano = this.ataque - adversario.defesa;
        if (dano < 5) {
            dano = 5;
        }
        return dano;
    }

    public void receberDano(int dano) {

        this.energiaAtual -= dano;

        if (this.energiaAtual < 0) {
            this.energiaAtual = 0;
        }
    }

    public boolean estaDerrotado() {
        return this.energiaAtual == 0;
    }

    public void registrarVitoria() {
        this.vitorias++;
        this.pontos += 3;
    }

    public void registrarDerrota() {
        this.derrotas++;
    }

    public void registrarEmpate() {
        this.pontos++;
    }

    public boolean podeRecuperar(int quantidade) {
        if (quantidade <= 0) {
            return false;
        }
        if (quantidade % 10 != 0) {
            return false;
        }

        int custo = quantidade / 10;

        if (this.pontos < custo) {
            return false;
        }
        if (this.energiaAtual + quantidade > 100) {
            return false;
        }

        return true;
    }

    public void recuperarEnergia(int quantidade) {

        int custo = quantidade / 10;

        this.energiaAtual += quantidade;
        this.pontos -= custo;
    }

    public void registrarCombate() {
    this.combates++;
    }

    public boolean podeExcluir() {
    return this.combates == 0;
    }

}