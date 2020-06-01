<script>
    import {link} from 'svelte-routing';
    import {USER} from '../../store.js';

</script>


<nav class="uk-navbar-container uk-navbar">
    <div class="uk-navbar-left">
        <ul class="uk-navbar-nav">
            <li class="uk-active"><a href="/">Home</a></li>
            {#if $USER != null}
                <li>
                    <a href="/good" use:link>Goods</a>
                </li>
                <li>
                    <a href="/sale" use:link>Sales</a>
                </li>
                <li>
                    <a href="/warehouse1" use:link>Warehouse 1</a>
                </li>
                <li>
                    <a href="/warehouse2" use:link>Warehouse 2</a>
                </li>
            {/if}
        </ul>
    </div>

    <div class="uk-navbar-right">
        <ul class="uk-navbar-nav">
            {#if $USER !== null}
                <li>
                    <a href="/logout">Log out</a>
                </li>

            {:else}
                <li>
                    <a href="/registration" use:link>Registration</a>
                </li>

                <li>
                    <a href="/login">Login</a>
                </li>
            {/if}
        </ul>
    </div>
</nav>
