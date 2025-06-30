package com.example.locadoraGames;

public enum Console {
    PS5("PlayStation 5"),
    XBOX_SERIES_X("Xbox Series X"),
    SWITCH("Nintendo Switch"),
    PC("Computador"),
    NINTENDO ("Super Nes"),
    PS4("Playstation 4");

    private final String nomeAmigavel;

    Console(String nomeAmigavel) {
        this.nomeAmigavel = nomeAmigavel;
    }

    public String getNomeAmigavel() {
        return nomeAmigavel;
    }
}
