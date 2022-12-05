<template>
  <div>
    <nav class="site-nav dark js-site-navbar mt-1 mb-3 site-navbar-target">
      <div class="container">
        <div class="site-navigation">
          <router-link :to="{ name: 'main' }" class="logo mr-3 float-left">
            <img src="@/assets/logo.png" alt="찾아방" /> <span class="logotext">.</span>
          </router-link>
          <ul class="js-clone-nav d-none d-lg-inline-block site-menu float-left">
            <li class="active">
              <router-link :to="{ name: 'main' }" class="nav-link">홈</router-link>
            </li>
            <li>
              <router-link :to="{ name: 'house' }" class="nav-link">실거래 검색</router-link>
            </li>
            <li>
              <router-link :to="{ name: 'board' }" class="nav-link">공지사항</router-link>
            </li>
            <li>
              <router-link :to="{ name: 'qna' }" class="nav-link">QnA</router-link>
            </li>
          </ul>
          <ul v-if="userInfo" class="js-clone-nav d-none mt-1 d-lg-inline-block site-menu float-right">
            <li class="mx-1 cta-button-outline">
              <a href="#">Welcome {{ userInfo.username }}!</a>
            </li>
            <li class="mx-1 cta-primary">
              <router-link :to="{ name: 'mypage' }">MyPage</router-link>
            </li>
            <li class="mx-1 cta-primary">
              <a @click.prevent="onClickLogout">Sign out</a>
            </li>
          </ul>
          <ul v-else class="js-clone-nav d-none mt-1 d-lg-inline-block site-menu float-right">
            <li class="mx-1 cta-button-outline">
              <router-link :to="{ name: 'login' }">Sign in</router-link>
            </li>
            <li class="mx-1 cta-primary">
              <router-link :to="{ name: 'join' }">Register</router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "TheHeaderNavbar",
  data() {
    return {};
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      console.log(this.userInfo.userid);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
      this.userLogout(this.userInfo.userid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
  },
};
</script>

<style scoped>
.logo {
  font-size: 24px;
  color: #ffffff;
  font-weight: 700;
  letter-spacing: -1.5px;
}

.logo:hover {
  color: #ffffff;
}
.logo img {
  height: 60px;
}
.logotext {
  color: #407bff;
}
.site-nav {
  /* position: absolute; */
  position: fixed;
  top: 0;
  width: 100%;
  z-index: 10;
}

.site-nav a {
  position: relative;
  z-index: 9;
  cursor: pointer;
}

.site-nav {
  padding-top: 20px;
  padding-bottom: 20px;
}

.site-nav h1 {
  margin: 0;
  padding: 0;
  font-size: 24px;
}

.site-nav .site-navigation .site-menu {
  margin-bottom: 0;
}

.site-nav .site-navigation .site-menu > li {
  display: inline-block;
}

.site-nav .site-navigation .site-menu > li > a {
  font-size: 14px;
  padding: 10px 15px;
  display: inline-block;
  text-decoration: none !important;
  color: rgba(255, 255, 255, 0.5);
  position: relative;
}

.site-nav .site-navigation .site-menu > li > a:hover {
  color: #ffffff;
}

.site-nav .site-navigation .site-menu > li > a.active {
  color: #407bff;
}

.site-nav .site-navigation .site-menu > li.cta-button-outline a {
  padding: 5px 22px;
  border: 1px solid rgba(0, 0, 0, 0.1);
  color: #000000;
  border-radius: 30px;
}

.site-nav .site-navigation .site-menu > li.cta-button-outline a:hover {
  color: #000000;
  background: #ffffff;
  border-color: #000000;
}

.site-nav .site-navigation .site-menu > li.cta-button-outline.active a {
  color: #000000;
  background: #ffffff;
  border-color: #ffffff;
}

.site-nav .site-navigation .site-menu > li.cta-primary a {
  padding: 5px 22px;
  border: 1px solid transparent;
  color: #ffffff !important;
  background: #407bff;
  border-radius: 30px;
}

.site-nav .site-navigation .site-menu > li.active > a {
  color: #ffffff;
}

.site-nav.dark a {
  color: #000000;
}

.site-nav.dark .site-navigation .site-menu > li > a {
  color: rgba(0, 0, 0, 0.5);
}

.site-nav.dark .site-navigation .site-menu > li > a:hover {
  color: #000000;
}

.site-nav.dark .site-navigation .site-menu > li.active > a {
  color: #000000;
}

.site-nav.dark .site-navigation .site-menu > li.has-children.dropdown:hover > a,
.site-nav.dark .site-navigation .site-menu > li.has-children.dropdown:focus > a,
.site-nav.dark .site-navigation .site-menu > li.has-children.dropdown:active > a {
  color: #407bff;
}

.site-nav.dark .site-navigation .site-menu > li.has-children:hover > a,
.site-nav.dark .site-navigation .site-menu > li.has-children:focus > a,
.site-nav.dark .site-navigation .site-menu > li.has-children:active > a {
  color: #000000;
}

.site-nav.scrolled {
  background-color: #ffffff;
  position: fixed;
  width: 100%;
  left: 0;
  right: 0;
  top: 0;
  margin-top: -130px;
  padding-top: 10px !important;
  padding-bottom: 10px !important;
  -webkit-box-shadow: 0 4px 15px -5px rgba(0, 0, 0, 0.1);
  box-shadow: 0 4px 15px -5px rgba(0, 0, 0, 0.1);
}

.site-nav.scrolled.awake {
  margin-top: 0px;
  -webkit-transition: 0.3s all ease-out;
  -o-transition: 0.3s all ease-out;
  transition: 0.3s all ease-out;
}

.site-nav.scrolled.sleep {
  -webkit-transition: 0.3s all ease-out;
  -o-transition: 0.3s all ease-out;
  transition: 0.3s all ease-out;
}

.site-nav.scrolled .site-navigation .site-menu li a {
  color: #000000;
}

.site-nav.scrolled .site-navigation .site-menu li a.active {
  color: #407bff;
}
</style>
