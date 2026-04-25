<script>
    import { authStore, logout } from '$lib/auth.svelte.js';
    import { goto } from '$app/navigation';

    let { activePage = 'Home' } = $props();

    function handleLogout() {
        logout();
        goto('/login');
    }
</script>

<nav class="navbar">
    <div class="logo">SinteticX</div>
    <div class="links">
        <a href="/" class:active={activePage === 'Home'}>Home</a>
        <a href="/create-match">Create Match</a>
        <a href="/matches">Matches</a>
    </div>
    <div class="user-profile">
        {#if authStore.currentUser}
            <span class="greeting">Hello, {authStore.currentUser}</span>
            <button class="auth-btn logout" onclick={handleLogout}>Logout</button>
            <div class="avatar"></div>
        {:else}
            <a href="/login" class="auth-btn">Login</a>
            <a href="/register" class="auth-btn register">Register</a>
        {/if}
    </div>
</nav>

<style>
    .navbar {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 1rem 5%;
        background-color: #121212;
        color: white;
        border-bottom: 1px solid #333;
    }
    .logo {
        font-size: 1.5rem;
        font-weight: bold;
        color: #007bff;
        letter-spacing: 1px;
    }
    .links a {
        margin: 0 1rem;
        color: #aaa;
        text-decoration: none;
        font-weight: 500;
        transition: color 0.3s;
    }
    .links a:hover, .links a.active {
        color: #007bff;
    }
    .user-profile {
        display: flex;
        align-items: center;
        gap: 1rem;
    }
    .greeting {
        color: #aaa;
        font-weight: 500;
    }
    .auth-btn {
        padding: 6px 12px;
        border: 1px solid #007bff;
        border-radius: 4px;
        color: #007bff;
        text-decoration: none;
        background: transparent;
        cursor: pointer;
        transition: all 0.3s;
    }
    .auth-btn:hover {
        background: #007bff;
        color: white;
    }
    .auth-btn.register {
        background: #007bff;
        color: white;
    }
    .auth-btn.register:hover {
        background: #0056b3;
        border-color: #0056b3;
    }
    .auth-btn.logout {
        border-color: #dc3545;
        color: #dc3545;
    }
    .auth-btn.logout:hover {
        background: #dc3545;
        color: white;
    }
    .avatar {
        width: 35px;
        height: 35px;
        background-color: #333;
        border-radius: 50%;
        border: 2px solid #007bff;
    }
</style>