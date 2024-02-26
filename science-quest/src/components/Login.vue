<template>
    <form>
    <h1 class="h3 mb-3 fw-normal">Se connecter</h1>

    <div class="form-floating">
      <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com">
      <label for="floatingInput">Email</label>
    </div>
    <div class="form-floating">
      <input type="password" class="form-control" id="floatingPassword" placeholder="Password">
      <label for="floatingPassword">Mot de passe</label>
    </div>

    <div class="checkbox mb-3">
      <label>
        <input type="checkbox" value="remember-me"> Remember me
      </label>
    </div>
    <button class="btn btn-lg btn-primary" type="submit">Sign in</button>
  </form>
</template>

<style>
form {
  display: flex;
  flex-direction:column;
  align-items: center;
}
</style>