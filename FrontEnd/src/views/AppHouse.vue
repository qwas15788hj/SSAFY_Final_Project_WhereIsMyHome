<template>
  <div class="text-center">
    <div class="marginTop" style="visibility: hidden">marginarea</div>
    <house-search-bar class="housesearch"></house-search-bar>
    <kakao-map class="maparea my-2"></kakao-map>
    <div class="apt">
      <house-apt-info class="aptarea mx-2"></house-apt-info>
      <house-comment class="commentArea" v-show="isComment"></house-comment>
    </div>
  </div>
</template>
<script>
import { mapMutations, mapState } from "vuex";
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import KakaoMap from "@/components/house/KakaoMap.vue";
import HouseAptInfo from "@/components/house/HouseAptInfo.vue";
import HouseComment from "@/components/house/HouseComment.vue";
const aptStore = "aptStore";
export default {
  name: "AppHouse",
  components: {
    HouseSearchBar,
    KakaoMap,
    HouseAptInfo,
    HouseComment,
  },
  computed: {
    ...mapState(aptStore, ["aptInfo", "isComment", "isWriteComment"]),
  },
  watch: {
    aptInfo() {
      //아파트 정보가 변경될때마다 댓글창 설정
      this.SET_IS_COMMNET(false);
      this.SET_IS_WRITE_COMMENT(false);
    },
  },
  methods: {
    ...mapMutations(aptStore, ["SET_IS_COMMNET", "SET_IS_WRITE_COMMENT"]),
  },
};
</script>
<style scoped>
.marginTop {
  margin-top: 80px;
}
.housesearch {
  position: absolute;
  top: 120px;
  left: 207px;
  z-index: 2;
}
.maparea {
  /* display: flex;
  justify-content: center;
  align-items: center; */
  margin: 0 auto;
}
.apt {
  position: absolute;
  top: 170px;
  left: 210px;
  z-index: 1;
  display: flex;
  justify-content: flex-start;
}
</style>
