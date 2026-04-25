<script>
    import { addMatch } from '$lib/matches.svelte.js';
    import { validateMatchData } from '$lib/validation.js';
    import { setCookie, getCookie } from '$lib/cookies.js';
    import { goto } from '$app/navigation';
    import { onMount } from 'svelte';

    let location = $state('');
    let date = $state('');
    let time = $state('');
    let type = $state('5v5');
    let errors = $state({ location: '', date: '', time: '' });

    onMount(() => {
        const savedType = getCookie('preferredMatchType');
        if (savedType) {
            type = savedType;
        }
    });

    function handleSubmit(e) {
        e.preventDefault();

        const matchData = { location, date, time, type };
        const validation = validateMatchData(matchData);

        if (!validation.isValid) {
            errors.location = validation.errors.location;
            errors.date = validation.errors.date;
            errors.time = validation.errors.time;
            return;
        }

        setCookie('preferredMatchType', type, 30);

        addMatch(matchData);
        goto('/matches');
    }
</script>

<div class="form-container">
    <h2>Create Match</h2>
    <form onsubmit={handleSubmit}>
        <div class="input-group">
            <input type="text" placeholder="Location" bind:value={location} />
            {#if errors.location}
                <span class="error">{errors.location}</span>
            {/if}
        </div>

        <div class="input-group">
            <input type="date" bind:value={date} />
            {#if errors.date}
                <span class="error">{errors.date}</span>
            {/if}
        </div>

        <div class="input-group">
            <input type="time" bind:value={time} />
            {#if errors.time}
                <span class="error">{errors.time}</span>
            {/if}
        </div>

        <div class="input-group">
            <select bind:value={type}>
                <option value="5v5">5 vs 5</option>
                <option value="6v6">6 vs 6</option>
                <option value="7v7">7 vs 7</option>
            </select>
        </div>

        <button type="submit">Create</button>
    </form>
</div>

<style>
    .form-container {
        max-width: 400px;
        margin: 50px auto;
        padding: 2rem;
        background: #fff;
        border-radius: 8px;
        box-shadow: 0 4px 6px rgba(0,0,0,0.1);
    }
    h2 {
        text-align: center;
        color: #333;
        margin-bottom: 1.5rem;
    }
    form {
        display: flex;
        flex-direction: column;
        gap: 1.5rem;
    }
    .input-group {
        display: flex;
        flex-direction: column;
        gap: 0.25rem;
    }
    input, select {
        padding: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-family: inherit;
    }
    button {
        padding: 10px;
        background: #007bff;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        font-weight: bold;
    }
    button:hover {
        background: #0056b3;
    }
    .error {
        color: red;
        font-size: 0.8rem;
    }
</style>