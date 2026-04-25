<script>
    import Hero from '$lib/components/Hero.svelte';
    import FeatureCard from '$lib/components/FeatureCard.svelte';
</script>

<main>
    <Hero />

    <section class="features">
        <FeatureCard
            icon="⚽"
            title="Găsește-ți meciul"
            description="Explorează lista de meciuri disponibile și alege-l pe cel care ți se potrivește."
        />
        <FeatureCard
            icon="⚡"
            title="Rezervare rapidă"
            description="Rezervă-ți locul în doar câteva secunde, fără bătăi de cap."
        />
        <FeatureCard
            icon="👥"
            title="Comunitate activă"
            description="Cunoaște oameni noi pasionați de fotbal și joacă împreună cu ei."
        />
    </section>
</main>

<style>
    .features {
        display: flex;
        justify-content: space-around;
        flex-wrap: wrap;
        padding: 5rem 10%;
        background-color: #fcfcfc;
    }
</style>