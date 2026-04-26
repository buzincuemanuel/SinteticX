package com.buzincuemanuel.sinteticx.service;


import com.buzincuemanuel.sinteticx.dto.MatchDTO;
import com.buzincuemanuel.sinteticx.mapper.MatchMapper;
import com.buzincuemanuel.sinteticx.model.Match;
import com.buzincuemanuel.sinteticx.repository.MatchRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Collections.min;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository repository;
    private final MatchMapper matchMapper;

    public List<MatchDTO> getPaginatedMatches(int page, int size){

        List<Match> matches = repository.findAll();

        int startIndex = page * size;
        int endIndex = Math.min(startIndex + size, matches.size());

        return matchMapper.listToDTO(matches.subList(startIndex, endIndex));
    }

    public Match createMatch(MatchDTO matchDTO){

        Match newMatch = Match.builder()
                .location(matchDTO.getLocation())
                .type(matchDTO.getType())
                .date(matchDTO.getDate())
                .time(matchDTO.getTime())
                .build();
        return repository.save(newMatch);
    }

    public Match getMatchById(Long id){
        return repository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find a match with this id")
        );
    }

    public Match updateMatch(Long id, MatchDTO dto) {
        Match existingMatch = getMatchById(id);

        existingMatch.setLocation(dto.getLocation());
        existingMatch.setType(dto.getType());
        existingMatch.setDate(dto.getDate());
        existingMatch.setTime(dto.getTime());

        return repository.save(existingMatch);
    }

    public boolean deleteMatch(Long id) {
        getMatchById(id);
        return repository.deleteById(id);
    }

}
