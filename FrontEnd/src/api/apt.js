import { apiInstance } from "./index.js";

const api = apiInstance();

function getApt(dongcode, success, fail) {
  api.get(`/map/info/${dongcode}`).then(success).catch(fail);
}

function favorite(param, success, fail) {
  console.log("param!!!>>>" + param.no);
  console.log("param!!!>>>" + param.userid);
  api.post(`/map/favorite`, JSON.stringify(param)).then(success).catch(fail);
}

// function findfavorite(params, success, fail) {
//   console.log("param!!!>>>" + params.no);
//   console.log("param!!!>>>" + params.userid);
//   api
//     .get(`/map/findfavorite/`, JSON.stringify(params))
//     .then(success)
//     .catch(fail);
// }

function findfavorite(param, success, fail) {
  api.get(`/map/findfavorite/${param.userid}/${param.deal_no}`).then(success).catch(fail);
}

function listComment(param, success, fail) {
  // console.log(param.pg);
  // console.log(param.spp);
  // console.log(param.aptCode );
  api.get(`/map/comment/${param}`).then(success).catch(fail);
}

export { getApt, favorite, findfavorite, listComment };
