<script>
    import { page } from '$app/stores';
    import { matchesStore, updateMatch, deleteMatch } from '$lib/matches.svelte.js';
    import { validateMatchData } from '$lib/validation.js';
    import { goto } from '$app/navigation';
    import Input from '$lib/components/Input.svelte';
    import { setCookie } from '$lib/cookies.js';
    import { onMount } from 'svelte';

    let matchId = $derived(Number($page.params.id));
    let match = $derived(matchesStore.list.find(m => m.id === matchId));

    let editData = $state({
        location: '',
        date: '',
        time: '',
        type: ''
    });

    let errors = $state({
        location: '',
        date: '',
        time: ''
    });

    let isEditing = $state(false);
    let isMine = $derived(match?.isMine);

    onMount(() => {
        if (matchId) {
            setCookie('lastVisitedMatch', matchId, 7);
        }
    });

    function startEditing() {
        editData = {
            location: match.location,
            date: match.date,
            time: match.time,
            type: match.type
        };
        errors = { location: '', date: '', time: '' };
        isEditing = true;
    }

    function cancelEditing() {
        isEditing = false;
    }

    function saveChanges() {
        const validation = validateMatchData(editData);
        errors = validation.errors;

        if (validation.isValid) {
            updateMatch(matchId, editData);
            isEditing = false;
        }
    }

    function handleDelete() {
        if (confirm("Are you sure you want to delete this match?")) {
            deleteMatch(matchId);
            goto('/matches');
        }
    }
</script>

<div class="page-container">
    <div class="detail-card">
        {#if match}
            <div class="header">
                <h2>Match #{match.id}</h2>
                <span class="badge">{match.type}</span>
            </div>

            {#if isEditing}
                <div class="edit-form">
                    <Input
                        id="edit-location"
                        label="Location"
                        bind:value={editData.location}
                        error={errors.location}
                    />

                    <Input
                        id="edit-date"
                        type="date"
                        label="Date"
                        bind:value={editData.date}
                        error={errors.date}
                    />

                    <Input
                        id="edit-time"
                        type="time"
                        label="Time"
                        bind:value={editData.time}
                        error={errors.time}
                    />

                    <label for="edit-type">Match type:</label>
                    <select id="edit-type" bind:value={editData.type} class="input-field">
                        <option value="5v5">5 vs 5</option>
                        <option value="6v6">6 vs 6</option>
                        <option value="7v7">7 vs 7</option>
                    </select>
                </div>

                <div class="action-buttons">
                    <button onclick={cancelEditing} class="btn btn-secondary">Cancel</button>
                    <button onclick={saveChanges} class="btn btn-primary">Save</button>
                </div>
            {:else}
                <div class="info-section">
                    <p><strong>📍 Location:</strong> {match.location}</p>
                    <p><strong>📅 Date:</strong> {match.date}</p>
                    <p><strong>⏰ Time:</strong> {match.time}</p>
                </div>

                <div class="action-buttons">
                    <a href="/matches" class="btn btn-secondary">Back</a>

                    {#if isMine}
                        <button onclick={startEditing} class="btn btn-primary">Edit</button>
                        <button onclick={handleDelete} class="btn btn-danger">Delete</button>
                    {:else}
                        <button class="btn btn-primary">Join match</button>
                    {/if}
                </div>
            {/if}
        {:else}
            <h2>Match not found!</h2>
            <a href="/matches" class="btn btn-secondary">Back</a>
        {/if}
    </div>
</div>

<style>
    .page-container {
        display: flex;
        justify-content: center;
        padding: 4rem 20px;
        background-color: #f4f7f6;
        min-height: calc(100vh - 80px);
    }
    .detail-card {
        background: white;
        padding: 2.5rem;
        border-radius: 12px;
        box-shadow: 0 5px 25px rgba(0,0,0,0.05);
        width: 100%;
        max-width: 600px;
    }
    .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        border-bottom: 2px solid #eee;
        padding-bottom: 1rem;
        margin-bottom: 1.5rem;
    }
    .header h2 { margin: 0; color: #333; }
    .badge {
        background: #007bff;
        color: white;
        padding: 5px 12px;
        border-radius: 20px;
        font-weight: bold;
    }
    .info-section p {
        font-size: 1.1rem;
        color: #555;
        margin-bottom: 15px;
    }
    .action-buttons {
        margin-top: 2rem;
        display: flex;
        gap: 15px;
        border-top: 2px solid #eee;
        padding-top: 1.5rem;
    }
    .btn {
        padding: 10px 20px;
        border: none;
        border-radius: 6px;
        font-weight: bold;
        cursor: pointer;
        text-decoration: none;
        text-align: center;
        flex: 1;
    }
    .edit-form {
        display: flex;
        flex-direction: column;
        gap: 5px;
    }
    .edit-form label {
        font-weight: bold;
        color: #555;
        font-size: 0.9rem;
        margin-bottom: 5px;
    }
    .input-field {
        padding: 8px 12px;
        border: 1px solid #ccc;
        border-radius: 6px;
        font-size: 1rem;
        margin-bottom: 10px;
    }
    .btn-secondary { background: #e0e0e0; color: #333; }
    .btn-secondary:hover { background: #d0d0d0; }
    .btn-primary { background: #007bff; color: white; }
    .btn-primary:hover { background: #0056b3; }
    .btn-danger { background: #dc3545; color: white; }
    .btn-danger:hover { background: #c82333; }
</style>