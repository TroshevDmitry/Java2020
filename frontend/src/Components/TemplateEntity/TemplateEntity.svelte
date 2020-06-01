<script>
    import {link} from 'svelte-routing';

    export let isCreate = false;
    export let name;

</script>


<div class="grid">
    <div class="title">
        <div class="uk-text-large">
            {name}
        </div>

        {#if isCreate}
            <a href="{name.toLowerCase()}_create" use:link>Create</a>
        {/if}
    </div>


    <slot name="inner">

    </slot>
</div>

<style>
    .grid {
        display: grid;
        justify-content: left;
        align-content: start;
        gap: 1em;
    }

    .title {
        display: grid;
    }
</style>