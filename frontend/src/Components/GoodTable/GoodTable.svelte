<script>
    import {onMount} from 'svelte';
    import {doQuery} from "../../utils";
    import {link} from 'svelte-routing';

    let goodList = Array();

    onMount(() => {
        doQuery('/api/good')
                .then(response => response.json())
                .then(json => {
                    goodList = Array.from(json);
                });
    });

    function handleDelete(id) {
        doQuery(`/api/good/${id}`, {
            method: 'DELETE'
        })
                .then(() => {
                    goodList = goodList.filter(good => good.id !== id);
                });
    }
</script>

<table class="uk-table uk-table-divider">
    <thead>
    <tr>
        <th>Name</th>
        <th>Priority</th>
    </tr>
    </thead>
    <tbody>
    {#each goodList as good}
        <tr>
            <td>{good.name}</td>
            <td>{good.priority}</td>
            <td><a href="/good/{good.id}" use:link>Edit</a></td>
            <td><a on:click={() => handleDelete(good.id)}>Delete</a></td>
        </tr>
    {/each}
    </tbody>
</table>
