<template>
  <div id="app" class="background">
    <the-header-navbar></the-header-navbar>
    <router-view></router-view>
    <div class="back"><app-footer-view class="footer"></app-footer-view></div>
  </div>
</template>

<script>
import TheHeaderNavbar from "@/components/TheHeaderNavbar";
import AppFooterView from "@/components/AppFooterView.vue";

export default {
  name: "App",
  components: {
    TheHeaderNavbar,
    AppFooterView,
  },
};
</script>

<style>
html,
body {
  overflow-x: hidden;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

a.router-link-exact-active {
  color: #89bfef;
}

a.router-link-exact-active:hover {
  text-decoration: none;
}
.back {
  width: 100%;
  background-color: #222222;
}
.footer {
  margin: 0 auto;
}
</style>
