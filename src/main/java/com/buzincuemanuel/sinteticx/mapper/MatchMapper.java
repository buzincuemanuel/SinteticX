package com.buzincuemanuel.sinteticx.mapper;

import com.buzincuemanuel.sinteticx.dto.MatchDTO;
import com.buzincuemanuel.sinteticx.model.Match;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MatchMapper {

    public MatchDTO toDTO(Match match){
        return MatchDTO.builder()
                .id(match.getId())
                .time(match.getTime())
                .date(match.getDate())
                .type(match.getType())
                .location(match.getLocation())
                .build();
    }

    public List<MatchDTO> listToDTO(List<Match> matches){

        return matches.stream().map(this::toDTO).toList();
    }
}
