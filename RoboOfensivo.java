public class RoboOfensivo extends Robo{ //EXTENDS PARA HERDAR DO ROBO!
    
    public RoboOfensivo(int codigo, String nome, int ataque, int defesa) {
        super(codigo, nome, ataque, defesa);
    }

    @Override //A REGRA EXCLUSIVA PARA ESSA SUBCLASSE
    public String getTipo() {
        return "Ofensivo";
    }

    @Override //A REGRA EXCLUSIVA PARA ESSA SUBCLASSE
    public int calcularAtaque() {
        return getAtaque() + 5;
    }
}
