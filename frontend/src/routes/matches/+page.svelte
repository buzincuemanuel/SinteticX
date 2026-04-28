<script>
    import { matchesStore } from '$lib/matches.svelte.js';
    import { getCookie } from '$lib/cookies.js';
    import { onMount } from 'svelte';

    let currentPage = $state(1);
    let itemsPerPage = 3;
    let lastVisitedId = $state(null);

    onMount(() => {
        lastVisitedId = getCookie('lastVisitedMatch');
        loadPage(currentPage);
    });

    // Funcția care cere datele de la server
    function loadPage(pageNumber) {
        // Trimitem pageNumber - 1 pentru că în Spring Boot paginile încep de la 0
        matchesStore.fetchMatches(pageNumber - 1, itemsPerPage);
    }

    function nextPage() {
        // Permitem Next doar dacă pagina curentă este plină (adică mai sunt elemente de arătat)
        if (matchesStore.matches.length === itemsPerPage) {
            currentPage += 1;
            loadPage(currentPage);
        }
    }

    function prevPage() {
        if (currentPage > 1) {
            currentPage -= 1;
            loadPage(currentPage);
        }
    }
</script>

<div class="container">
    <div class="header-actions">
        <h1>Available Matches</h1>
        <a href="/create-match" class="btn-create">+ Create Match</a>
    </div>

    {#if lastVisitedId}
        <div class="activity-banner">
            You recently viewed a match.
            <a href="/matches/{lastVisitedId}">Click here to go back to Match #{lastVisitedId}</a>.
        </div>
    {/if}

    <div class="table-container">
        {#if matchesStore.isLoading}
            <div style="padding: 2rem; text-align: center;">Loading matches from server...</div>
        {:else if matchesStore.error}
            <div style="padding: 2rem; color: red; text-align: center;">Error: {matchesStore.error}</div>
        {:else if matchesStore.matches.length === 0}
            <div style="padding: 2rem; text-align: center; color: #555;">No matches available on this page.</div>
        {:else}
            <table class="matches-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Location</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Type</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {#each matchesStore.matches as match}
                        <tr>
                            <td>#{match.id}</td>
                            <td>{match.location}</td>
                            <td>{match.date}</td>
                            <td>{match.time}</td>
                            <td><span class="badge">{match.type}</span></td>
                            <td>
                                <a href="/matches/{match.id}" class="btn-view">Details</a>
                            </td>
                        </tr>
                    {/each}
                </tbody>
            </table>
        {/if}
    </div>

    <div class="pagination">
        <button onclick={prevPage} disabled={currentPage === 1}>Previous</button>
        <span>Page {currentPage}</span>
        <button onclick={nextPage} disabled={matchesStore.matches.length < itemsPerPage}>Next</button>
    </div>
</div>

<style>
    /* CSS-ul tău rămâne EXACT LA FEL! Nu am modificat nimic aici,
       design-ul tău este perfect așa cum l-ai scris. */
    .container {
        padding: 3rem 10%;
        background-color: #fcfcfc;
        min-height: calc(100vh - 70px);
    }
    .header-actions {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 2rem;
    }
    h1 {
        margin: 0;
        color: #222;
    }
    .btn-create {
        padding: 0.75rem 1.5rem;
        background-color: #007bff;
        color: white;
        text-decoration: none;
        border-radius: 8px;
        font-weight: bold;
        transition: background-color 0.2s;
    }
    .btn-create:hover {
        background-color: #0056b3;
    }
    .activity-banner {
        background-color: #e0f7fa;
        color: #006064;
        padding: 1rem;
        border-radius: 8px;
        margin-bottom: 2rem;
        border-left: 5px solid #00acc1;
    }
    .activity-banner a {
        color: #00838f;
        font-weight: bold;
        text-decoration: none;
        margin-left: 5px;
    }
    .activity-banner a:hover {
        text-decoration: underline;
    }
    .table-container {
        background: white;
        border-radius: 12px;
        box-shadow: 0 4px 15px rgba(0,0,0,0.05);
        overflow: hidden;
    }
    .matches-table {
        width: 100%;
        border-collapse: collapse;
        text-align: left;
    }
    .matches-table th, .matches-table td {
        padding: 1.2rem;
        border-bottom: 1px solid #eee;
    }
    .matches-table th {
        background-color: #f8f9fa;
        color: #555;
        font-weight: 600;
        text-transform: uppercase;
        font-size: 0.85rem;
    }
    .matches-table tr:hover {
        background-color: #fdfdfd;
    }
    .badge {
        background-color: #e9ecef;
        padding: 0.3rem 0.6rem;
        border-radius: 20px;
        font-size: 0.85rem;
        font-weight: 600;
        color: #495057;
    }
    .btn-view {
        color: #007bff;
        text-decoration: none;
        font-weight: 500;
    }
    .btn-view:hover {
        text-decoration: underline;
    }
    .pagination {
        display: flex;
        justify-content: center;
        align-items: center;
        margin-top: 2rem;
        gap: 1rem;
    }
    .pagination button {
        padding: 0.5rem 1rem;
        border: 1px solid #ddd;
        background-color: white;
        border-radius: 6px;
        cursor: pointer;
    }
    .pagination button:disabled {
        color: #aaa;
        cursor: not-allowed;
    }
</style>