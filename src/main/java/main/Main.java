package main;

import ohtu.ohtuvarasto.Varasto;

public class Main {

    static final double tilavuus = 100.0;
    static final double alkusaldo = 20.2;

    public static void main(String[] args) {

        Varasto mehua = new Varasto(tilavuus);
        Varasto olutta = new Varasto(tilavuus, alkusaldo);

        System.out.println("Luonnin jälkeen:");
        System.out.println("Mehuvarasto: " + mehua);
        System.out.println("Olutvarasto: " + olutta);

    }

}
