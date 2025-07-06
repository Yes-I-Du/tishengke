declare namespace API {
  type addQuestionBankQuestionUsingPOSTParams = {
    questionBankId?: number;
    questionId?: number;
  };

  type addQuestionBankUsingPOSTParams = {
    description?: string;
    picture?: string;
    title?: string;
  };

  type addQuestionUsingPOSTParams = {
    answer?: string;
    content?: string;
    tags?: string[];
    title?: string;
  };

  type BaseResponseBoolean_ = {
    code?: number;
    data?: boolean;
    message?: string;
  };

  type BaseResponseLoginUserVO_ = {
    code?: number;
    data?: LoginUserVO;
    message?: string;
  };

  type BaseResponseLong_ = {
    code?: number;
    data?: number;
    message?: string;
  };

  type BaseResponsePageQuestion_ = {
    code?: number;
    data?: PageQuestion_;
    message?: string;
  };

  type BaseResponsePageQuestionBank_ = {
    code?: number;
    data?: PageQuestionBank_;
    message?: string;
  };

  type BaseResponsePageQuestionBankQuestion_ = {
    code?: number;
    data?: PageQuestionBankQuestion_;
    message?: string;
  };

  type BaseResponsePageQuestionBankQuestionVO_ = {
    code?: number;
    data?: PageQuestionBankQuestionVO_;
    message?: string;
  };

  type BaseResponsePageQuestionBankVO_ = {
    code?: number;
    data?: PageQuestionBankVO_;
    message?: string;
  };

  type BaseResponsePageQuestionVO_ = {
    code?: number;
    data?: PageQuestionVO_;
    message?: string;
  };

  type BaseResponsePageUser_ = {
    code?: number;
    data?: PageUser_;
    message?: string;
  };

  type BaseResponsePageUserVO_ = {
    code?: number;
    data?: PageUserVO_;
    message?: string;
  };

  type BaseResponseQuestion_ = {
    code?: number;
    data?: Question;
    message?: string;
  };

  type BaseResponseQuestionBank_ = {
    code?: number;
    data?: QuestionBank;
    message?: string;
  };

  type BaseResponseQuestionBankQuestion_ = {
    code?: number;
    data?: QuestionBankQuestion;
    message?: string;
  };

  type BaseResponseQuestionBankQuestionVO_ = {
    code?: number;
    data?: QuestionBankQuestionVO;
    message?: string;
  };

  type BaseResponseQuestionBankVO_ = {
    code?: number;
    data?: QuestionBankVO;
    message?: string;
  };

  type BaseResponseQuestionVO_ = {
    code?: number;
    data?: QuestionVO;
    message?: string;
  };

  type BaseResponseUser_ = {
    code?: number;
    data?: User;
    message?: string;
  };

  type BaseResponseUserVO_ = {
    code?: number;
    data?: UserVO;
    message?: string;
  };

  type DeleteRequest = {
    id?: number;
  };

  type getQuestionBankByIdUsingGETParams = {
    /** id */
    id: number;
  };

  type getQuestionBankQuestionByIdUsingGETParams = {
    /** id */
    id: number;
  };

  type getQuestionBankQuestionVOByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type getQuestionByIdUsingGETParams = {
    /** id */
    id: number;
  };

  type getQuestionVOByIdUsingGETParams = {
    /** id */
    id?: number;
  };

  type getUserByIdUsingGETParams = {
    /** id */
    id: number;
  };

  type getUserVOByIdUsingGETParams = {
    /** id */
    id: number;
  };

  type LoginUserVO = {
    createTime?: string;
    id?: number;
    updateTime?: string;
    userAccount?: string;
    userAvatar?: string;
    userName?: string;
    userProfile?: string;
    userRole?: string;
  };

  type PageQuestion_ = {
    current?: number;
    pages?: number;
    records?: Question[];
    size?: number;
    total?: number;
  };

  type PageQuestionBank_ = {
    current?: number;
    pages?: number;
    records?: QuestionBank[];
    size?: number;
    total?: number;
  };

  type PageQuestionBankQuestion_ = {
    current?: number;
    pages?: number;
    records?: QuestionBankQuestion[];
    size?: number;
    total?: number;
  };

  type PageQuestionBankQuestionVO_ = {
    current?: number;
    pages?: number;
    records?: QuestionBankQuestionVO[];
    size?: number;
    total?: number;
  };

  type PageQuestionBankVO_ = {
    current?: number;
    pages?: number;
    records?: QuestionBankVO[];
    size?: number;
    total?: number;
  };

  type PageQuestionVO_ = {
    current?: number;
    pages?: number;
    records?: QuestionVO[];
    size?: number;
    total?: number;
  };

  type PageUser_ = {
    current?: number;
    pages?: number;
    records?: User[];
    size?: number;
    total?: number;
  };

  type PageUserVO_ = {
    current?: number;
    pages?: number;
    records?: UserVO[];
    size?: number;
    total?: number;
  };

  type Question = {
    answer?: string;
    content?: string;
    createTime?: string;
    editTime?: string;
    favourNum?: number;
    id?: number;
    isDelete?: number;
    needVip?: number;
    priority?: number;
    reviewMessage?: string;
    reviewStatus?: number;
    reviewTime?: string;
    reviewerId?: number;
    source?: string;
    tags?: string;
    thumbNum?: number;
    title?: string;
    updateTime?: string;
    userId?: number;
    viewNum?: number;
  };

  type QuestionBank = {
    createTime?: string;
    description?: string;
    editTime?: string;
    id?: number;
    isDelete?: number;
    picture?: string;
    priority?: number;
    reviewMessage?: string;
    reviewStatus?: number;
    reviewTime?: string;
    reviewerId?: number;
    title?: string;
    updateTime?: string;
    userId?: number;
    viewNum?: number;
  };

  type QuestionBankQueryRequest = {
    current?: number;
    description?: string;
    id?: number;
    needQueryQuestionList?: boolean;
    notId?: number;
    pageSize?: number;
    picture?: string;
    searchText?: string;
    sortField?: string;
    sortOrder?: string;
    title?: string;
    userId?: number;
  };

  type QuestionBankQuestion = {
    createTime?: string;
    id?: number;
    questionBankId?: number;
    questionId?: number;
    questionOrder?: number;
    updateTime?: string;
    userId?: number;
  };

  type QuestionBankQuestionQueryRequest = {
    current?: number;
    id?: number;
    notId?: number;
    pageSize?: number;
    questionBankId?: number;
    questionId?: number;
    sortField?: string;
    sortOrder?: string;
    userId?: number;
  };

  type QuestionBankQuestionVO = {
    createTime?: string;
    id?: number;
    questionBankId?: number;
    questionId?: number;
    tagList?: string[];
    updateTime?: string;
    user?: UserVO;
    userId?: number;
  };

  type QuestionBankVO = {
    createTime?: string;
    description?: string;
    id?: number;
    picture?: string;
    questionPage?: PageQuestionVO_;
    title?: string;
    updateTime?: string;
    user?: UserVO;
    userId?: number;
    viewNum?: number;
  };

  type QuestionQueryRequest = {
    answer?: string;
    content?: string;
    current?: number;
    id?: number;
    notId?: number;
    pageSize?: number;
    questionBankId?: number;
    searchText?: string;
    sortField?: string;
    sortOrder?: string;
    tags?: string[];
    title?: string;
    userId?: number;
  };

  type QuestionVO = {
    answer?: string;
    content?: string;
    createTime?: string;
    favourNum?: number;
    id?: number;
    questionPage?: PageQuestionVO_;
    source?: string;
    tagList?: string[];
    thumbNum?: number;
    title?: string;
    updateTime?: string;
    user?: UserVO;
    userId?: number;
    viewNum?: number;
  };

  type updateQuestionBankQuestionUsingPOSTParams = {
    id?: number;
    questionBankId?: number;
    questionId?: number;
  };

  type updateQuestionBankUsingPOSTParams = {
    description?: string;
    id?: number;
    picture?: string;
    title?: string;
  };

  type updateQuestionUsingPOSTParams = {
    answer?: string;
    content?: string;
    id?: number;
    tags?: string[];
    title?: string;
  };

  type User = {
    admin?: boolean;
    createTime?: string;
    editTime?: string;
    id?: number;
    inviteUser?: number;
    isDelete?: number;
    mpOpenId?: string;
    shareCode?: string;
    unionId?: string;
    updateTime?: string;
    userAccount?: string;
    userAvatar?: string;
    userName?: string;
    userPassword?: string;
    userProfile?: string;
    userRole?: string;
    vipCode?: string;
    vipExpireTime?: string;
    vipNumber?: number;
  };

  type UserAddRequest = {
    userAccount?: string;
    userAvatar?: string;
    userName?: string;
    userProfile?: string;
    userRole?: string;
  };

  type UserLoginRequest = {
    userAccount?: string;
    userPassword?: string;
  };

  type UserQueryRequest = {
    current?: number;
    id?: number;
    pageSize?: number;
    sortField?: string;
    sortOrder?: string;
    userAccount?: string;
    userAvatar?: string;
    userName?: string;
    userProfile?: string;
    userRole?: string;
  };

  type UserRegisterRequest = {
    checkPassword?: string;
    userAccount?: string;
    userPassword?: string;
  };

  type UserUpdateRequest = {
    id?: number;
    userAvatar?: string;
    userName?: string;
    userProfile?: string;
    userRole?: string;
  };

  type UserVO = {
    createTime?: string;
    id?: number;
    userAccount?: string;
    userAvatar?: string;
    userName?: string;
    userProfile?: string;
    userRole?: string;
  };
}
