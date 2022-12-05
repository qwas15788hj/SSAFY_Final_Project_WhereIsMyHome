<template>
  <div class="main">
    <div class="section1">
      <div class="company">찾아방</div>
      <div>
        <div class="text1">
          <div class="intext intext1">대표&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
          <div class="intext">|</div>
          <div class="intext">민동주, 최현제</div>
        </div>
        <div class="text1">
          <div class="intext intext1">주소&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div>
          <div class="intext">|</div>
          <div class="intext">서울특별시 강남구 역삼동 테헤란로 212</div>
        </div>
        <div class="text1">
          <div class="intext intext1">업무 시간</div>
          <div class="intext">|</div>
          <div class="intext">평일 9:00~18:00</div>
        </div>
      </div>
      <div class="noShow">!</div>
    </div>
    <div class="section1">
      <div class="copyright">Copyright © All Rights Reserved.</div>
      <div class="noShow">---------------------------------------------------</div>
      <div class="noShow">!</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AppFooterView",
};
</script>

<style>
.main {
  width: 60%;
  padding: 50px 0 10px 0;
}
.section1 {
  display: flex;
  justify-content: space-between;
  vertical-align: top;
}
.section1 {
  text-align: left;
  margin: 0 0 25px 0;
}
.noShow {
  visibility: hidden;
}
.company {
  font-size: 1.2em;
  font-weight: bold;
  color: rgb(255, 255, 255);
}
.copyright {
  font-size: 0.85em;
  color: rgb(185, 185, 185);
}
.text1 {
  color: rgb(233, 233, 233);
  font-size: 0.9em;
  margin: 0 0 10px 0;
}
.text1 .intext {
  display: inline;
  text-align: left;
  margin: 10px;
}
.intext1 {
  font-weight: bold;
}
</style>
