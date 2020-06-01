<script>
    import {onMount} from 'svelte';
    import {doQuery} from "../../utils";
    import {link} from 'svelte-routing';

    let saleList = Array();

    onMount(() => {
        doQuery('/api/sale')
                .then(response => response.json())
                .then(json => {
                    saleList = Array.from(json);
                });
    });

    function handleDelete(id) {
        doQuery(`/api/sale/${id}`, {
            method: 'DELETE'
        })
                .then(() => {
                    saleList = saleList.filter(good => good.id !== id);
                });
    }
</script>

<table class="uk-table uk-table-divider">
    <thead>
    <tr>
        <th>Good</th>
        <th>Good count</th>
        <th>Create date</th>
    </tr>
    </thead>
    <tbody>
    {#each saleList as sale}
        <tr>
            <td>{sale.good.name}</td>
            <td>{sale.good_count}</td>
            <td>{sale.createDate}</td>
            <td><a href="/sale/{sale.id}" use:link>Edit</a></td>
            <td><a on:click={() => handleDelete(sale.id)}>Delete</a></td>
        </tr>
    {/each}
    </tbody>
</table>
