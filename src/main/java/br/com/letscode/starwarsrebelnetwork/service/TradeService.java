package br.com.letscode.starwarsrebelnetwork.service;


import br.com.letscode.starwarsrebelnetwork.repository.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TradeService {

    private final RebelRepository repository;



}
