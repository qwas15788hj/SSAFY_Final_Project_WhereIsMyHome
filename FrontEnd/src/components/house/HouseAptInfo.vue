<template>
  <div class="aptMain" v-if="aptInfo != null">
    <div class="aptInfoArea">
      <div class="aptText text-left">
        <div class="aptname">
          {{ aptInfo.apartmentname }}
        </div>
        <div class="infotext">
          {{ aptInfo.address }}<br />
          {{ aptInfo.buildYear }}
        </div>
      </div>
      <div class="commentBtnArea">
        <b-icon
          class="commenticon"
          v-show="aptInfo.aptcode"
          @click="showComment"
          icon="chat-right-text"
          font-scale="1.3"
        ></b-icon>
      </div>
    </div>
    <div class="aptDealsArea mt-3">
      <house-apt-list></house-apt-list>
    </div>

    <!-- <house-comment class="commentArea" v-show="isComment"></house-comment> -->
    <!-- <div>{{ aptInfo.apartmentname }}아파트의 거래 목록</div> -->
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
// import axios from "axios";
import HouseAptList from "./HouseAptList.vue";

const aptStore = "aptStore";
export default {
  components: { HouseAptList },
  name: "HouseAptInfo",
  data() {
    return {};
  },
  computed: {
    ...mapState(aptStore, ["aptInfo", "aptComment", "isComment", "isWriteComment"]),
  },
  watch: {
    aptInfo() {
      //아파트 정보가 변경될때마다 댓글창 설정
      this.SET_IS_COMMNET(false);
      this.SET_IS_WRITE_COMMENT(false);
    },
  },
  methods: {
    ...mapMutations(aptStore, ["SET_APT_COMMENT", "CLEAR_APT_COMMENT", "SET_IS_COMMNET", "SET_IS_WRITE_COMMENT"]),
    showComment() {
      //클릭하면 댓글창 열림/닫힘 변환
      this.SET_IS_COMMNET(!this.isComment);
      //지금댓글창이 false면 작성창도 false로 변환
      if (!this.isComment) {
        this.SET_IS_WRITE_COMMENT(false);
      }
    },
  },
};
</script>

<style>
.aptMain {
  width: 350px;
  height: 620px;
  border-radius: 20px;
  background-color: white;
  padding: 30px;
  box-shadow: 0 5px 10px 0 rgb(0 0 0 / 10%);
}
.aptInfoArea {
  display: flex;
  justify-content: space-between;
}
.aptname {
  font-size: 1.2em;
  font-weight: bold;
  color: black;
}
.infotext {
  font-size: 0.95em;
  color: rgb(78, 78, 78);
}
.commenticon {
  cursor: pointer;
}
.aptDealsArea {
  height: 600px;
}
</style>
