

const API_URL = "http://localhost:8080/api/matches";

export function createMatchesStore() {
    let matches = $state([]);
    let isLoading = $state(false);
    let error = $state(null);

    // 1. Încărcarea meciurilor de pe Backend (cu paginare)
    async function fetchMatches(page = 0, size = 10) {
        isLoading = true;
        try {
            const response = await fetch(`${API_URL}?page=${page}&size=${size}`);
            if (!response.ok) throw new Error("Nu am putut încărca meciurile");
            matches = await response.ok ? await response.json() : [];
        } catch (e) {
            error = e.message;
        } finally {
            isLoading = false;
        }
    }

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