<template>
  <b-container class="mt-4" v-if="userInfo">
    <h1 class="board-title">내 정보</h1>
    <b-row>
      <b-col></b-col>
      <b-col cols="8" class="mt-5">
        <!-- <b-jumbotron class="jumbotr"> -->
        <hr class="my-4" />
        <b-container class="mt-4">
          <b-row>
            <b-col cols="2"></b-col>
            <b-col cols="2" align-self="end" class="keyword">아이디</b-col
            ><b-col cols="4" align-self="start">{{ userInfo.userid }}</b-col>
            <b-col cols="2"></b-col>
          </b-row>
          <b-row class="mt-3">
            <b-col cols="2"></b-col>
            <b-col cols="2" align-self="end" class="keyword">이름</b-col
            ><b-col cols="4" align-self="start">{{ userInfo.username }}</b-col>
            <b-col cols="2"></b-col>
          </b-row>
          <b-row class="mt-3">
            <b-col cols="2"></b-col>
            <b-col cols="2" align-self="end" class="keyword">이메일</b-col
            ><b-col cols="4" align-self="start">{{ userInfo.email }}</b-col>
            <b-col cols="2"></b-col>
          </b-row>
          <b-row class="mt-3">
            <b-col cols="2"></b-col>
            <b-col cols="2" align-self="end" class="keyword">가입일</b-col
            ><b-col cols="4" align-self="start">{{ userInfo.joindate }}</b-col>
            <b-col cols="2"></b-col>
          </b-row>
        </b-container>
        <hr class="my-4" />
        <div class="mt-5 button-size">
          <b-button
            variant="primary"
            @click="movePage"
            class="mr-1 btn btn-primary btn-lg button-size"
            >정보수정</b-button
          >
          <b-button
            variant="danger"
            @click="remove"
            class="btn btn-primary btn-lg button-size"
            >회원탈퇴</b-button
          >
          <!-- </b-jumbotron> -->
        </div>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserMyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo", "isRemoveUser"]),
  },
  methods: {
    ...mapActions(memberStore, ["removeUser"]),
    async remove() {
      await this.removeUser(this.userInfo.userid);
      if (this.isRemoveUser) {
        this.$router.push({ name: "main" });
      }
    },
    movePage() {
      this.$router.push({ name: "update" });
    },
  },
};
</script>

<style>
h1.board-title:after {
  content: "";
  display: block;
  width: 80px;
  border-bottom: 5px solid #1667c9;
  margin: 20px auto;
}
/* .jumbotron {
  background-color: ;
} */

.button-size {
  font-size: 15px;
  border-radius: 30px;
  padding: 10px 25px;
}
.a {
  background-color: white;
}

input[read-only] {
  border: 0;
  box-shadow: none;
  background-color: white !important;
}
.keyword {
  font-weight: bold;
}
.button-size {
  margin-top: 1%;
}
</style>
