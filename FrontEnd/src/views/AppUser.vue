<template>
  <b-container class="bv-example-row mt-3 text-center">
    <div class="marginTop" style="visibility: hidden">marginarea</div>
    <!-- <h3 class="underline-steelblue">
      <b-icon icon="person-lines-fill"></b-icon> Member Service
    </h3> -->
    <router-view class="my-5"></router-view>
  </b-container>
</template>

<script>
export default {
  name: "AppUser",
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(72, 190, 233, 0.3) 30%);
}
.marginTop {
  margin-top: 80px;
}
</style>
