public abstract class Robo {
    // AQUI O ENCAPSULAMENTO COM PRIVATE E EMBAIXO OS GETTERS!
    private int codigo;
    private String nome;
    private int ataque;
    private int defesa;
    private int energiaAtual;
    private int vitorias;
    private int derrotas;
    private int pontos;
    private int combates;

    protected Robo(int codigo, String nome, int ataque, int defesa) {
        
        if (codigo <= 0) {
            throw new IllegalArgumentException("O código deve ser positivo!");
        }

        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ficar vazio!");
        }

        if (ataque < 10 || ataque > 30) {
            throw new IllegalArgumentException("Ataque inválido! Deve ser entre 10 e 30.");
        }

        if (defesa < 0 || defesa > 20) {
            throw new IllegalArgumentException("Defesa inválida! Deve ser entre 0 e 20.");
        }

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

    public int getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public int getAtaque() {
        return ataque;
    }
    public int getDefesa() {
        return defesa;
    }
    public int getEnergiaAtual() {
        return energiaAtual;
    }
    public int getVitorias() {
        return vitorias;
    }
    public int getDerrotas() {
        return derrotas;
    }
    public int getPontos() {
        return pontos;
    }
    public int getCombates() {
        return combates;
    }

    public abstract String getTipo();

    public int calcularAtaque() {
        return ataque;
    }

    public int reduzirDanoRecebido(int dano) {
        return dano;
    }

    public void receberDano(int dano) {
        if(dano <= 0){
            return;
        }

        energiaAtual -= dano;

        if(energiaAtual < 0) {
            energiaAtual = 0;
        }
    }

    public boolean estaDerrotado() {
        return energiaAtual == 0;
    }

    public void registrarVitoria() {
        vitorias++;
        pontos += 3;
    }

    public void registrarDerrota() {
        derrotas++;
    }

    public void registrarEmpate() {
        pontos++;
    }

    public String validarRecuperacao(int quantidade) {
        
        if(quantidade <= 0) {
            return "A quantidade deve ser positiva!";
        }

        if(quantidade % 10 != 0) {
            return "A quantidade deve ser múltipla de 10!";
        }

        int custo = quantidade / 10;

        if(pontos < custo) {
            return "Pontos inseficientes! Custo: " +custo + " | Pontos disponíveis: " + pontos;
        }

        if(energiaAtual + quantidade > 100) {
            return "A energia não pode ultrapassar de 100!";
        }

        return null;
    }

    public boolean recuperarEnergia(int quantidade) {
        
        if (validarRecuperacao(quantidade) != null){
            return false;
        }

        int custo = quantidade / 10;

        energiaAtual += quantidade;
        pontos -= custo;

        return true;
    }

    public void registrarCombate() {
        combates++;
    }

    public boolean podeExcluir() {
        return combates == 0;
    }

    public void exibirStatus() {
        System.out.println();
        System.out.println("---------------------------------");
        System.out.println("Código: " + codigo);
        System.out.println("Nome: " + nome);
        System.out.println("Tipo: " + getTipo());
        System.out.println("Ataque: " + ataque);
        System.out.println("Defesa: " + defesa);
        System.out.println("Energia atual: " + energiaAtual);
        System.out.println("Vitórias: " + vitorias);
        System.out.println("Derrotas: " + derrotas);
        System.out.println("Pontos: " + pontos);
        System.out.println("Combates: " + combates);
        System.out.println("---------------------------------");
    }

    public void exibirRobos() {
        System.out.println("Código: " + codigo);
        System.out.println("Nome: " + nome);
        System.out.println("Tipo: " + getTipo());
        System.out.println("Ataque:" + ataque);
        System.out.println("Defesa:" + defesa);

        if (energiaAtual < 30) {
            System.out.println("Situação: Em Recuperação");
        } else {
            System.out.println("Situação: Disponível");
        }
    }
}