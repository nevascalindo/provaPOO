public class RoboEquilibrado extends Robo { //EXTENDS PARA HERDAR DO ROBO!
    
    public RoboEquilibrado(int codigo, String nome, int ataque, int defesa) {
        super(codigo, nome, ataque, defesa);
    }

    @Override //A REGRA EXCLUSIVA PARA ESSA SUBCLASSE
    public String getTipo() {
        return "Equilibrado";
    }
}
