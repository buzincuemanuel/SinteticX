import { describe, it, expect } from 'vitest';
import { validateMatchData } from './validation.js';

describe('validateMatchData Function', () => {
    it('should validate a correct match successfully', () => {
        const futureDate = new Date();
        futureDate.setDate(futureDate.getDate() + 5);
        const formatData = futureDate.toISOString().split('T')[0];

        const validMatch = { location: 'Sports Center', date: formatData, time: '18:00' };
        const result = validateMatchData(validMatch);

        expect(result.isValid).toBe(true);
        expect(result.errors.location).toBe('');
    });

    it('should return error if location is missing', () => {
        const match = { location: '', date: '2026-10-10', time: '18:00' };
        const result = validateMatchData(match);

        expect(result.isValid).toBe(false);
        expect(result.errors.location).toBe('Location is required');
    });

    it('should return error if date is in the past', () => {
        const match = { location: 'Park', date: '2000-01-01', time: '18:00' };
        const result = validateMatchData(match);

        expect(result.isValid).toBe(false);
        expect(result.errors.date).toBe('Cannot schedule a match in the past');
    });
});