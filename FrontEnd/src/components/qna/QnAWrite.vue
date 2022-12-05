<template>
  <b-container class="bv-example-row mt-3">
    <!-- <b-row>
      <b-col>
        <b-alert show><h3>QnA 작성</h3></b-alert>
      </b-col>
    </b-row> -->
    <h1 class="board-title">QnA 작성</h1>
    <qna-input-item type="register" />
  </b-container>
</template>

<script>
import qnaInputItem from "@/components/qna/item/QnAInputItem";

export default {
  name: "QnAWrite",
  components: {
    qnaInputItem,
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
</style>
