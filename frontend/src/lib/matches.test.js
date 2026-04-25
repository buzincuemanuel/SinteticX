import { describe, it, expect } from 'vitest';
import { matchesStore, addMatch, updateMatch, deleteMatch } from './matches.svelte.js';

describe('Matches Store (matches.svelte.js)', () => {

    it('should add a new match to the list', () => {
        const initialCount = matchesStore.list.length;

        const newMatch = { location: 'Test Arena', date: '2026-12-12', time: '20:00', type: '5v5' };
        addMatch(newMatch);

        const newCount = matchesStore.list.length;
        expect(newCount).toBe(initialCount + 1);
    });

    it('should generate a correct ID and set isMine to true', () => {
        const lastMatch = matchesStore.list[matchesStore.list.length - 1];
        expect(lastMatch.isMine).toBe(true);
        expect(lastMatch.id).toBeGreaterThan(0);
        expect(typeof lastMatch.id).toBe('number');
    });

    it('should update an existing match', () => {
        const testId = 1;
        const newFields = { location: 'Updated Location' };

        updateMatch(testId, newFields);

        const updatedMatch = matchesStore.list.find(m => m.id === testId);
        expect(updatedMatch.location).toBe('Updated Location');
    });

    it('should delete a match from the list', () => {
        const idToDelete = 1;
        const initialLength = matchesStore.list.length;

        deleteMatch(idToDelete);

        const matchExists = matchesStore.list.some(m => m.id === idToDelete);
        expect(matchExists).toBe(false);
        expect(matchesStore.list.length).toBe(initialLength - 1);
    });
});