<template>
  <b-container class="bv-example-row mt-3">
    <!-- <b-row>
      <b-col>
        <b-alert show><h3>QnA 수정</h3></b-alert>
      </b-col>
    </b-row> -->
    <h1 class="board-title">QnA 수정</h1>
    <qna-input-item type="modify" />
  </b-container>
</template>

<script>
import qnaInputItem from "@/components/qna/item/QnAInputItem";

export default {
  name: "QnAModify",
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
