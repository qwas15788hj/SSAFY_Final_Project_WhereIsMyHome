<template>
  <b-container class="bv-example-row mt-3">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css" />
    <!-- <b-row>
      <b-col>
        <b-alert show><h3>공지사항</h3></b-alert>
      </b-col>
    </b-row> -->
    <h1 class="board-title">공지사항</h1>
    <b-row class="mb-1 mt-4">
      <b-col class="text-right">
        <p v-if="userInfo == null"></p>
        <b-button
          class="btn btn-circle btn-lg mt-3"
          variant="outline-primary"
          @click="moveWrite()"
          v-else-if="userInfo.userid === `admin`"
          >글작성</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mt-2">
      <b-col>
        <b-table
          hover
          :items="articles"
          :fields="fields"
          class="text-align mt-4"
          @row-clicked="viewArticle"
        >
          <template #cell(subject)="data">
            <router-link
              :to="{
                name: 'boardview',
                params: { articleno: data.item.articleno },
              }"
            >
              {{ data.item.subject }}
            </router-link>
          </template>
        </b-table>
      </b-col>
    </b-row>
    <div class="page-navigation">
      <div class="page-nav">
        <span class="page-total">전체 페이지 수: {{ this.totalCount }}</span> |
        <span class="page-now">현재 페이지 수: {{ this.nowPage }}</span>
      </div>
      <div>
        <div class="input-gruop"></div>
        <input
          class="w2-input w2-border w3-round-large"
          v-model="param.pg"
          @keyup.enter="movepage"
        />
        <b-button
          class="btn-lg button-size1"
          variant="outline-primary"
          @click="movepage"
          >이동</b-button
        >
      </div>
    </div>
  </b-container>
</template>

<script>
import { listArticle, getBoardTotalCount } from "@/api/board";
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "BoardList",
  data() {
    return {
      articles: [],
      totalCount: "",
      fields: [
        { key: "articleno", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
      ],
      param: {
        pg: "1",
        spp: "20",
        key: null,
        word: null,
      },
      nowPage: "1",
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    // let param = {
    //   pg: 1,
    //   spp: 20,
    //   key: null,
    //   word: null,
    // };
    listArticle(
      this.param,
      ({ data }) => {
        this.articles = data;
        // for (let i = 0; i < this.articles.length; i++) {
        //   this.articles[i].articleno = this.articles.length - i;
        //   console.log("")
        // }
      },
      (error) => {
        console.log(error);
      }
    );
    getBoardTotalCount(
      ({ data }) => {
        this.totalCount = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    // getBoardTotalCount() {
    //   console.log("호출~!");
    //   axios({
    //     url: `http://localhost:9999/vue/board/count`,
    //     method: "get",
    //   })
    //     .then((data) => {
    //       console.log(data);
    //     })
    //     .catch((error) => console.log(error))
    //     .finally();
    // },
    movepage() {
      (this.param.pg = Math.min(this.param.pg, this.totalCount)),
        listArticle(
          this.param,
          ({ data }) => {
            this.articles = data;
          },
          (error) => {
            console.log(error);
          }
        );
      this.nowPage = this.param.pg;
    },

    moveWrite() {
      this.$router.push({ name: "boardwrite" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "boardview",
        params: { articleno: article.articleno },
      });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}

h1.board-title:after {
  content: "";
  display: block;
  width: 80px;
  border-bottom: 5px solid #1667c9;
  margin: 20px auto;
}

.page-navigation {
  float: center;
  margin-bottom: 50px;
}

.page-total {
  margin-right: 10px;
}

.page-now {
  margin-left: 10px;
}

.page-nav {
  margin-bottom: 10px;
}

.page-move {
  margin-left: 3px;
}

.button {
  border-radius: 20px;
  background-color: #407bff;
  color: white;
  /* padding: 10px 10px; */
  /* width: 80px; */

  /* font-size: 15px; */
}

.btn-circle {
  font-size: 15px;
  border-radius: 30px;
  padding: 10px 25px;
}

.button-size1 {
  font-size: 10px;
  border-radius: 30px;
  padding: 8px 12px;
}
</style>
