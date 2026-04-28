package com.buzincuemanuel.sinteticx.repository;


import com.buzincuemanuel.sinteticx.model.Match;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MatchRepository {

    private List<Match> matches = new ArrayList<>();
    private Long currentId;

    public Match save(Match match) {
        if (match.getId() == null) {
            match.setId(currentId++);
            matches.add(match);
        } else {
            int index = matches.indexOf(findById(match.getId()).orElse(null));
            if (index != -1) {
                matches.set(index, match);
            }
        }
        return match;
    }

    public List<Match> findAll() {
        return new ArrayList<>(matches);
    }

    public Optional<Match> findById(Long id) {
        return matches.stream()
                .filter(m -> m.getId().equals(id))
                .findFirst();
    }

    public boolean deleteById(Long id) {
        return matches.removeIf(m -> m.getId().equals(id));
    }

    @PostConstruct
    public void initMockData() {
        // Folosim .plusDays() ca să fim siguri că datele sunt mereu în viitor
        // și nu pică eventualele validări de @FutureOrPresent

        save(Match.builder()
                .location("Baza Sportivă Unirea")
                .type("Fotbal 5v5")
                .date(LocalDate.now().plusDays(1))
                .time(LocalTime.of(18, 0))
                .build());

        save(Match.builder()
                .location("Sala Polivalentă")
                .type("Baschet")
                .date(LocalDate.now().plusDays(2))
                .time(LocalTime.of(19, 30))
                .build());

        save(Match.builder()
                .location("Teren Parcul Central")
                .type("Tenis de câmp")
                .date(LocalDate.now().plusDays(3))
                .time(LocalTime.of(10, 0))
                .build());

        save(Match.builder()
                .location("Baza Sportivă Gheorgheni")
                .type("Fotbal 6v6")
                .date(LocalDate.now().plusDays(1))
                .time(LocalTime.of(20, 0))
                .build());

        save(Match.builder()
                .location("Sala de Sport Universitară")
                .type("Volei")
                .date(LocalDate.now().plusDays(4))
                .time(LocalTime.of(17, 15))
                .build());
    }
}
