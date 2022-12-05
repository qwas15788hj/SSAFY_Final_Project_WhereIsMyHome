<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1 mt-5">
      <b-col class="text-right">
        <p v-if="userInfo == null"></p>
        <b-col class="text-right" v-else-if="userInfo.userid === `admin`">
          <b-button
            v-if="userInfo.userid === `admin`"
            variant="primary"
            @click="moveModifyArticle"
            class="mr-2 btn btn-primary btn-lg button-size"
            >수정</b-button
          >
          <b-button
            v-if="userInfo.userid === `admin`"
            variant="danger"
            class="btn btn-primary btn-lg button-size"
            @click="deleteArticle"
            >삭제</b-button
          >
        </b-col>
      </b-col>
    </b-row>
    <div>
      <b-row class="mb-1">
        <b-col>
          <b-card
            :header-html="`
          <div class='titlenews'>
            <div class='news-title'><h4><span class='title-title'>제목: ${article.subject}</span></h4>
                    <span class='title-time'>${article.regtime}</span></div>
              <div class='title-writer'><h6>작성자: ${article.userid}</h6></div>
            </div>
            `"
            class="mb-2"
            border-variant="white"
          >
            <b-card-body class="text-left">
              <div v-html="message"></div>
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
      <hr />
    </div>
    <b-button
      variant="outline-primary"
      class="btn btn-lg button-size"
      @click="moveList"
      >목록</b-button
    >
  </b-container>
</template>

<script>
// import moment from "moment";
import { getArticle } from "@/api/board";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    let param = this.$route.params.articleno;
    getArticle(
      param,
      ({ data }) => {
        this.article = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveModifyArticle() {
      this.$router.replace({
        name: "boardmodify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        this.$router.replace({
          name: "boarddelete",
          params: { articleno: this.article.articleno },
        });
      }
    },
    moveList() {
      this.$router.push({ name: "boardlist" });
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style>
/* #titlenews {
  display: flex;
  justify-content: space-between;
  vertical-align: top;
}
.title-writer {
  margin-top: 20%;
  margin-right: 20%;
}
.title-time {
  margin-top: 1%;
}
.line {
  margin-bottom: 3%;
} */
/* .card-header {
  background-color: white;
} */

.news-title {
  width: 100%;
  height: 1px;
}
.title-title {
  width: 80%;
  text-align: left;
  float: left;
}
.title-time {
  width: 20%;
  float: right;
  font-size: 15px;
}
.title_writer {
  text-align: left;
}
.button-size {
  font-size: 15px;
  border-radius: 30px;
  padding: 10px 25px;
}
</style>
