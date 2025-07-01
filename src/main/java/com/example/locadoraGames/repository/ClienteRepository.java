package com.example.locadoraGames.repository;

import com.example.locadoraGames.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {
}
