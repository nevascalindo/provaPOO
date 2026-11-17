public class RoboDefensivo extends Robo { //EXTENDS PARA HERDAR DO ROBO!

    public RoboDefensivo(int codigo, String nome, int ataque, int defesa) {
        super(codigo, nome, ataque, defesa);
    }

    @Override //A REGRA EXCLUSIVA PARA ESSA SUBCLASSE
    public String getTipo() {
        return "Defensivo";
    }

    @Override //A REGRA EXCLUSIVA PARA ESSA SUBCLASSE
    public int reduzirDanoRecebido(int dano){
        return Math.max(2, dano - 3);
    }
}