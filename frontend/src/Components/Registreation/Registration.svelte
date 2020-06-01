<script>
    import {doQuery} from '../../utils.js';

    let username = '';
    let password = '';

    function handleSubmit() {
        const json = JSON.stringify({
            username: username,
            password: password,
            roles: ["USER"]
        });

        doQuery('/user', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: json
        });
    }

</script>

<div class="full">
    <form className="uk-position-center" on:submit={handleSubmit}>
        <label className="uk-text-large">Registration</label>
        <div class="uk-margin">
            <label class="uk-form-label" for="form-stacked-text">Username</label>
            <div class="uk-form-controls">
                <input class="uk-input" id="form-stacked-text" type="text" required placeholder="Enter username"
                       bind:value={username}>
            </div>
        </div>

        <div class="uk-margin">
            <label class="uk-form-label" for="form-stacked-text">Password</label>
            <div class="uk-form-controls">
                <input class="uk-input" id="form-stacked-text" required type="password" placeholder="Enter password"
                       bind:value={password}>
            </div>
        </div>

        <button class="uk-button uk-button-default" type="submit">Register</button>
    </form>
</div>

<style>
    .full {
        display: grid;
        justify-content: center;
        width: 100%;
        box-sizing: border-box;
    }

    .full > form {
        margin-top: 50%;
    }
</style>
