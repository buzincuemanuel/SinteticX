
export const matchesStore = $state({
    list: [
    { id: 1, location: 'Baza Sportivă Gheorgheni', date: '2026-05-01', time: '18:00', type: '5v5', isMine: false },
    { id: 8, location: 'Baza Sportivă Gheorgheni', date: '2026-05-01', time: '18:00', type: '5v5', isMine: false },
    { id: 2, location: 'Teren Sintetic Zorilor', date: '2026-05-02', time: '20:00', type: '6v6', isMine: false },
    { id: 3, location: 'Baza Unirea', date: '2026-05-03', time: '19:00', type: '5v5', isMine: true },
    { id: 4, location: 'Baza Dan Anca', date: '2026-05-05', time: '17:00', type: '5v5', isMine: false },
    { id: 5, location: 'Baza Sportivă CMC', date: '2026-05-06', time: '21:00', type: '6v6', isMine: false },
    { id: 6, location: 'Teren Hoia', date: '2026-05-08', time: '18:30', type: '5v5', isMine: false },
    { id: 7, location: 'Teren Mănăștur', date: '2026-05-10', time: '19:00', type: '7v7', isMine: true }
]
});

export function addMatch(newMatch) {

    const maxId = matchesStore.list.length > 0
        ? Math.max(...matchesStore.list.map(m => m.id))
        : 0;

    newMatch.id = maxId + 1;
    newMatch.isMine = true;

    matchesStore.list.push(newMatch);
}

export function updateMatch(id, updatedData) {
    const index = matchesStore.list.findIndex(m => m.id === id);
    if (index !== -1) {
        matchesStore.list[index] = {
            ...matchesStore.list[index],
            ...updatedData,
            id: id,
            isMine: true
        };
    }
}

export function deleteMatch(id) {
    matchesStore.list = matchesStore.list.filter(m => m.id !== id);
}