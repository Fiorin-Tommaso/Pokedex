package org.example;

public class Pokemon {
    private int index;
    private String nome;
    private String tipo1;
    private String tipo2;
    private int hp;

    public Pokemon(int index, String nome, String tipo1, String tipo2, int hp) {
        this.index = index;
        this.nome = nome;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.hp = hp;
    }

    public int getIndex() {
        return index;
    }
    public String getNome() {
        return nome;
    }
    public String getTipo1() {
        return tipo1;
    }
    public String getTipo2() {
        return tipo2;
    }
    public int getHp() {
        return hp;
    }

    @Override
    public String toString() {
        return "Pokemon {" +
                "index=" + index +
                ", nome='" + nome + '\'' +
                ", tipo1='" + tipo1 + '\'' +
                ", tipo2='" + (tipo2 != null ? tipo2 : "nessuno") + '\'' +
                ", HP=" + hp +
                '}';
    }
}
