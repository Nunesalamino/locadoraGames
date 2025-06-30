package com.example.locadoraGames;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoRepository extends JpaRepository<Jogo, Long> {
    // Você pode adicionar consultas personalizadas aqui se quiser
}

