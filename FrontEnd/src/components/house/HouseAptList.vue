<template>
  <div id="aptlistinfo" class="scrollbar">
    <div class="title text-left my-2">실거래가</div>
    <table class="dtable">
      <tr class="dheader">
        <td>계약일</td>
        <td>거래가격</td>
        <td>층수</td>
        <td>면적</td>
      </tr>
      <tr class="dlist" v-for="(deal, index) in aptDeals" :key="index">
        <td>{{ deal.fullDay }}</td>
        <td style="font-weight: bold">{{ deal.dealAmount }}만</td>
        <td>{{ deal.floor }}층</td>
        <td>{{ deal.area }}</td>
      </tr>
    </table>
  </div>
</template>

<script>
import { mapState } from "vuex";

const aptStore = "aptStore";

export default {
  name: "HouseAptList",
  data() {
    return {
      aptname: "",
    };
  },
  computed: {
    ...mapState(aptStore, ["aptInfo", "aptDeals"]),
  },
};
</script>

<style scpoed>
#aptlistinfo {
  width: 100%;
  height: 80%;
  overflow: scroll;
  overflow-x: hidden;
}
.title {
  font-size: 0.95em;
  color: black;
  font-weight: bold;
  padding-left: 5px;
}
.dtable {
  width: 100%;
  font-size: 0.95em;
  border-spacing: 0;
  border-collapse: collapse;
  display: table;
  border-color: grey;
  text-indent: initial;
}
.dtable .dheader {
  font-weight: bold;
  border-bottom: 1px solid #e5e5e5;
  border-top: 1px solid #7790ad;
}
.dtable .dheader td {
  padding: 8px 0;
  color: #666666;
  font-size: 13px;
  font-weight: 500;
  line-height: 17px;
}
.dtable .dlist td {
  padding: 8px 3px;
  border-bottom: 1px solid;
  font-size: 13px;
  color: #222;
  line-height: 17px;
  letter-spacing: -0.5px;
  border-bottom: 1px solid #e5e5e5;
}
.dtable td {
  max-width: 130px;
  text-align: center;
  line-height: 14px;
  word-break: break-all;
  word-wrap: break-word;
}
/* Customize website's scrollbar like Mac OS
Not supports in Firefox and IE */

/* total width */
.scrollbar::-webkit-scrollbar {
  background-color: #fff;
  width: 16px;
}

/* background of the scrollbar except button or resizer */
.scrollbar::-webkit-scrollbar-track {
  background-color: #fff;
}
.scrollbar::-webkit-scrollbar-track:hover {
  background-color: #f4f4f4;
}

/* scrollbar itself */
.scrollbar::-webkit-scrollbar-thumb {
  background-color: #babac0;
  border-radius: 16px;
  border: 5px solid #fff;
}
.scrollbar::-webkit-scrollbar-thumb:hover {
  background-color: #a0a0a5;
  border: 4px solid #f4f4f4;
}

/* set button(top and bottom of the scrollbar) */
.scrollbar::-webkit-scrollbar-button {
  display: none;
}
</style>
