<script>
    import Navbar from '$lib/components/Navbar.svelte';
    import '../lib/styles/global.css';
    let { children } = $props();
</script>

<Navbar />
{@render children()}