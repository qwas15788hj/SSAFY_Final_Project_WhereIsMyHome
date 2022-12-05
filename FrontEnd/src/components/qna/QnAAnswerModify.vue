<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>답변 수정</h3></b-alert>
      </b-col>
    </b-row>
    <qna-answer-input-item type="modify" />
  </b-container>
</template>

<script>
import qnaAnswerInputItem from "@/components/qna/item/QnAAnswerInputItem";

export default {
  name: "QnAAnswer",
  components: {
    qnaAnswerInputItem,
  },
};
</script>

<style></style>
