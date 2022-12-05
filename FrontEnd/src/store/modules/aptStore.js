const aptStore = {
  namespaced: true,
  state: {
    dongCode: null,
    aptInfo: null,
    aptDeals: null,
    aptComment: null, //한 아파트의 댓글목록들
    isComment: false, //댓글창 열림 여부
    isWriteComment: false, //댓글 작성하기 열림 여부
  },
  getters: {},
  mutations: {
    //검색할 지역 설정
    SET_DONGCODE(state, dongcode) {
      state.dongCode = dongcode;
    },
    CLEAR_DONGCODE(state) {
      state.dongCode = null;
    },
    // 아파트 하나의 정보 설정
    SET_APT_INFO(state, apt) {
      state.aptInfo = apt;
    },
    CLEAR_APT_INFO(state) {
      state.aptInfo = null;
    },
    //아파트 하나의 거래 목록들 설정
    SET_APT_DEALS(state, dealList) {
      state.aptDeals = dealList;
    },
    CLEAR_APT_DEALS(state) {
      state.aptDeals = null;
    },
    //아파트 댓글 설정
    SET_APT_COMMENT(state, comment) {
      state.aptComment = comment;
    },
    CLEAR_APT_COMMENT(state) {
      state.aptComment = null;
    },
    //댓글창 열림/닫힘 설정
    SET_IS_COMMNET(state, isComment) {
      state.isComment = isComment;
    },
    //댓글 작성하기 창 열림/닫힘 설정
    SET_IS_WRITE_COMMENT(state, isWriteComment) {
      state.isWriteComment = isWriteComment;
    },
  },
};

export default aptStore;
