<script>
    let {
        label = '',
        type = 'text',
        value = $bindable(''),
        id = '',
        error = ''
    } = $props();
</script>

<div class="input-group">
    {#if label}
        <label for={id}>{label}</label>
    {/if}

    <input {type} {id} bind:value class:has-error={error !== ''} />

    {#if error}
        <span class="error-message">{error}</span>
    {/if}
</div>

<style>
    .input-group {
        display: flex;
        flex-direction: column;
        margin-bottom: 1rem;
    }

    input {
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    .has-error {
        border-color: red;
    }

    .error-message {
        color: red;
        font-size: 0.8rem;
        margin-top: 0.25rem;
    }
</style>