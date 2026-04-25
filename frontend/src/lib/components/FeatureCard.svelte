<script>
    let { title, description, icon } = $props();
</script>

<div class="card">
    <div class="icon">{icon}</div>
    <h3>{title}</h3>
    <p>{description}</p>
</div>

<style>
    .card {
        background: #fff;
        padding: 2.5rem;
        border-radius: 15px;
        box-shadow: 0 10px 30px rgba(0,0,0,0.05);
        text-align: center;
        flex: 1;
        margin: 1rem;
        min-width: 280px;
        transition: transform 0.3s;
        border: 1px solid #eee;
    }
    .card:hover {
        transform: translateY(-10px);
    }
    .icon {
        font-size: 3rem;
        margin-bottom: 1.5rem;
    }
    h3 {
        margin-bottom: 1rem;
        color: #222;
        font-size: 1.5rem;
    }
    p {
        color: #666;
        line-height: 1.6;
        font-size: 1rem;
    }
</style>