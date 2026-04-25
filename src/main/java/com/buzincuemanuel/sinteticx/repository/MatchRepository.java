package com.buzincuemanuel.sinteticx.repository;


import com.buzincuemanuel.sinteticx.model.Match;
import org.springframework.stereotype.Repository;

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
}
