// @ts-ignore
/* eslint-disable */
import request from "@/libs/request";

/** addQuestionBankQuestion POST /api/questionBankQuestion/add */
export async function addQuestionBankQuestionUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.addQuestionBankQuestionUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseLong_>("/api/questionBankQuestion/add", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** deleteQuestionBankQuestion POST /api/questionBankQuestion/delete */
export async function deleteQuestionBankQuestionUsingPost(
  body: API.DeleteRequest,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/questionBankQuestion/delete", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** getQuestionBankQuestionById GET /api/questionBankQuestion/get/${param0} */
export async function getQuestionBankQuestionByIdUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getQuestionBankQuestionByIdUsingGETParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<API.BaseResponseQuestionBankQuestion_>(
    `/api/questionBankQuestion/get/${param0}`,
    {
      method: "GET",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** getQuestionBankQuestionVOById GET /api/questionBankQuestion/get/vo */
export async function getQuestionBankQuestionVoByIdUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getQuestionBankQuestionVOByIdUsingGETParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseQuestionBankQuestionVO_>(
    "/api/questionBankQuestion/get/vo",
    {
      method: "GET",
      params: {
        ...params,
      },
      ...(options || {}),
    }
  );
}

/** listQuestionBankQuestionByPage POST /api/questionBankQuestion/list/page */
export async function listQuestionBankQuestionByPageUsingPost(
  body: API.QuestionBankQuestionQueryRequest,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponsePageQuestionBankQuestion_>(
    "/api/questionBankQuestion/list/page",
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      data: body,
      ...(options || {}),
    }
  );
}

/** listQuestionBankVOByPage POST /api/questionBankQuestion/list/page/vo */
export async function listQuestionBankVoByPageUsingPost1(
  body: API.QuestionBankQuestionQueryRequest,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponsePageQuestionBankQuestionVO_>(
    "/api/questionBankQuestion/list/page/vo",
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      data: body,
      ...(options || {}),
    }
  );
}

/** updateQuestionBankQuestion POST /api/questionBankQuestion/update */
export async function updateQuestionBankQuestionUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.updateQuestionBankQuestionUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/questionBankQuestion/update", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}
