<script>
    import { goto } from '$app/navigation';
</script>

<section class="hero">
    <div class="hero-content">
        <div class="presentation-box">
            <div class="logo-container">
                <span class="logo-icon">⚽</span>
                <h1 class="app-name">SinteticX</h1>
            </div>

            <p class="tagline">Connect. Play. Dominate.</p>

            <div class="description">
                <p>
                    SinteticX is a smart management platform built for the amateur football community.
                    Our mission is to bridge the gap between players and the pitch, making it effortless
                    to organize, discover, and join matches on synthetic turf fields near you.
                </p>
                <p>
                    Whether you're a team captain looking for opponents or a solo player
                    ready to jump into a game, SinteticX handles the logistics so you can
                    focus on the sport.
                </p>
            </div>

            <div class="hero-buttons">
                <a href="/matches" class="btn btn-primary">Find a Match</a>
                <a href="/create-match" class="btn btn-secondary">Host a Game</a>
            </div>
        </div>
    </div>
</section>

<style>
    .hero {
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 4rem 20px;
        background: #f8f9fa;
        min-height: 80vh;
    }
    .hero-content {
        max-width: 900px;
        width: 100%;
    }
    .presentation-box {
        background: white;
        padding: 3rem;
        border-radius: 16px;
        box-shadow: 0 10px 30px rgba(0,0,0,0.05);
        text-align: center;
    }
    .logo-container {
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-bottom: 0.5rem;
    }
    .logo-icon {
        font-size: 3rem;
    }
    .app-name {
        font-size: 3.5rem;
        font-weight: 800;
        margin: 0.5rem 0;
        color: #007bff;
        letter-spacing: -1px;
    }
    .tagline {
        font-size: 1.5rem;
        font-weight: 600;
        color: #555;
        margin-bottom: 2rem;
        font-style: italic;
    }
    .description {
        max-width: 700px;
        margin: 0 auto 2.5rem;
        text-align: center;
    }
    .description p {
        font-size: 1.1rem;
        color: #666;
        line-height: 1.6;
        margin-bottom: 1rem;
    }
    .hero-buttons {
        display: flex;
        justify-content: center;
        gap: 20px;
    }
    .btn {
        padding: 14px 32px;
        border-radius: 8px;
        font-weight: bold;
        text-decoration: none;
        transition: all 0.2s;
    }
    .btn:hover {
        transform: translateY(-2px);
    }
    .btn-primary {
        background-color: #007bff;
        color: white;
    }
    .btn-primary:hover {
        background-color: #0056b3;
    }
    .btn-secondary {
        border: 2px solid #007bff;
        color: #007bff;
    }
    .btn-secondary:hover {
        background-color: rgba(0, 123, 255, 0.05);
    }
</style>