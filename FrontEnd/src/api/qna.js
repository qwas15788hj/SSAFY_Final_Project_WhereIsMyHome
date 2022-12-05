import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(param, success, fail) {
  api.get(`/qna`, { params: param }).then(success).catch(fail);
}

function getArticle(board_no, success, fail) {
  api.get(`/qna/${board_no}`).then(success).catch(fail);
}

function getBoardTotalCount(success, fail) {
  api.get(`/qna/count`).then(success).catch(fail);
}

function writeArticle(qna, success, fail) {
  api.post(`/qna`, JSON.stringify(qna)).then(success).catch(fail);
}

function modifyArticle(qna, success, fail) {
  api.put(`/qna`, JSON.stringify(qna)).then(success).catch(fail);
}

function deleteArticle(board_no, success, fail) {
  api.delete(`/qna/${board_no}`).then(success).catch(fail);
}

// function getAnswer(board_no, success, fail) {
//   api.get(`/qna/${board_no}`).then(success).catch(fail);
// }

function writeAnswer(qna, success, fail) {
  api.put(`/qna/answer`, JSON.stringify(qna)).then(success).catch(fail);
}

export {
  listArticle,
  getArticle,
  getBoardTotalCount,
  writeArticle,
  modifyArticle,
  deleteArticle,
  // getAnswer,
  writeAnswer,
};
