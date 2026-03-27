package com.workintech.fswebs18challengemaven.controller;

import com.workintech.fswebs18challengemaven.entity.Card;
import com.workintech.fswebs18challengemaven.repository.CardRepository;
import com.workintech.fswebs18challengemaven.util.CardValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // Görev 3: Loglama için şart.
@CrossOrigin("*") // Görev 4: CORS hatasını backend'de bu şekilde çözüyoruz.
@RestController
@RequestMapping("/cards") // Testlerin geçmesi için /cards (Ödev /workintech/cards isterse başına ekle).
public class CardController {

    private final CardRepository cardRepository;

    @Autowired
    public CardController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @GetMapping
    public List<Card> findAll() {
        log.info("findAll cards endpoint called");
        return cardRepository.findAll();
    }

    @GetMapping("/byColor/{color}")
    public List<Card> findByColor(@PathVariable String color) {
        return cardRepository.findByColor(color);
    }

    @PostMapping
    public Card save(@RequestBody Card card) {
        // Görev 1: İş mantığı doğrulamaları util sınıfından çağrılıyor.
        CardValidation.validateCard(card);
        return cardRepository.save(card);
    }

    @PutMapping("/") // Testlerdeki o son slash hatasını çözmek için "/" ekledik.
    public Card update(@RequestBody Card card) {
        CardValidation.validateCard(card);
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