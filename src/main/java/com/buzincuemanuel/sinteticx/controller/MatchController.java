package com.buzincuemanuel.sinteticx.controller;


import com.buzincuemanuel.sinteticx.dto.MatchDTO;
import com.buzincuemanuel.sinteticx.model.Match;
import com.buzincuemanuel.sinteticx.service.MatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    @GetMapping
    public ResponseEntity<List<MatchDTO>> getMatches(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){

        List<MatchDTO> matches = matchService.getPaginatedMatches(page, size);
        return ResponseEntity.ok(matches);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long id) {
        Match match = matchService.getMatchById(id);
        return ResponseEntity.ok(match);
    }

    @PostMapping
    public ResponseEntity<Match> createMatch(@Valid @RequestBody MatchDTO matchDTO) {
        Match createdMatch = matchService.createMatch(matchDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMatch);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Match> updateMatch(@PathVariable Long id, @Valid @RequestBody MatchDTO matchDTO) {
        Match updatedMatch = matchService.updateMatch(id, matchDTO);
        return ResponseEntity.ok(updatedMatch);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
}
