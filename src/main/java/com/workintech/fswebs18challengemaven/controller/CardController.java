package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards") // Tüm istekler localhost:9000/cards üzerinden gelecek
public class CardController {

    private final CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @GetMapping("/byColor/{color}")
    public List<Card> findByColor(@PathVariable String color) {
        return cardRepository.findByColor(color);
    }

    @PostMapping
    public Card save(@RequestBody Card card) {
        return cardRepository.save(card);
    }

    @PutMapping
    public Card update(@RequestBody Card card) {
        return cardRepository.update(card);
    }

    @DeleteMapping("/{id}")
    public Card delete(@PathVariable Long id) {
        return cardRepository.remove(id);
    }

    @GetMapping("/byValue/{value}")
    public List<Card> findByValue(@PathVariable Integer value) {
        return cardRepository.findByValue(value);
    }

    @GetMapping("/byType/{type}")
    public List<Card> findByType(@PathVariable String type) {
        return cardRepository.findByType(type);
    }
}