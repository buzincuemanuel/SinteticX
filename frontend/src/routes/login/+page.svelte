<script>
    import { loginUser } from '$lib/auth.svelte.js';
    import { validateAuthData } from '$lib/validation.js';
    import { goto } from '$app/navigation';

    let username = $state('');
    let password = $state('');
    let errors = $state({ username: '', password: '', form: '' });

    function handleLogin(e) {
        e.preventDefault();

        const validation = validateAuthData(username, password);

        if (!validation.isValid) {
            errors.username = validation.errors.username;
            errors.password = validation.errors.password;
            errors.form = '';
            return;
        }

        errors.username = '';
        errors.password = '';

        const result = loginUser(username, password);

        if (result.success) {
            goto('/');
        } else {
            errors.form = result.message;
        }
    }
</script>

<div class="auth-container">
    <h2>Login</h2>
    <form onsubmit={handleLogin}>
        <div class="input-group">
            <input type="text" placeholder="Username" bind:value={username} />
            {#if errors.username}
                <span class="field-error">{errors.username}</span>
            {/if}
        </div>

        <div class="input-group">
            <input type="password" placeholder="Password" bind:value={password} />
            {#if errors.password}
                <span class="field-error">{errors.password}</span>
            {/if}
        </div>

        {#if errors.form}
            <p class="error">{errors.form}</p>
        {/if}

        <button type="submit">Login</button>
    </form>
    <p>Don't have an account? <a href="/register">Register here</a>.</p>
</div>

<style>
    .auth-container { max-width: 400px; margin: 50px auto; padding: 2rem; background: #fff; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); }
    h2 { text-align: center; color: #333; }
    form { display: flex; flex-direction: column; gap: 1.5rem; }
    .input-group { display: flex; flex-direction: column; gap: 0.25rem; }
    input { padding: 10px; border: 1px solid #ccc; border-radius: 4px; }
    button { padding: 10px; background: #007bff; color: white; border: none; border-radius: 4px; cursor: pointer; }
    button:hover { background: #0056b3; }
    .field-error { color: red; font-size: 0.8rem; }
    .error { color: red; font-size: 0.9rem; margin: 0; text-align: center; }
    p { text-align: center; color: #666; font-size: 0.9rem; }
    a { color: #007bff; text-decoration: none; }
    a:hover { text-decoration: underline; }
</style>