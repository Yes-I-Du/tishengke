// @ts-ignore
/* eslint-disable */
import request from "@/libs/request";

/** addQuestionBank POST /api/questionBank/add */
export async function addQuestionBankUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.addQuestionBankUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseLong_>("/api/questionBank/add", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** deleteQuestionBank POST /api/questionBank/delete */
export async function deleteQuestionBankUsingPost(
  body: API.DeleteRequest,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/questionBank/delete", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** getQuestionBankById GET /api/questionBank/get/${param0} */
export async function getQuestionBankByIdUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getQuestionBankByIdUsingGETParams,
  options?: { [key: string]: any }
) {
  const { id: param0, ...queryParams } = params;
  return request<API.BaseResponseQuestionBank_>(
    `/api/questionBank/get/${param0}`,
    {
      method: "GET",
      params: { ...queryParams },
      ...(options || {}),
    }
  );
}

/** getQuestionBankVOById GET /api/questionBank/get/vo */
export async function getQuestionBankVoByIdUsingGet(
  body: API.QuestionBankQueryRequest,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseQuestionBankVO_>("/api/questionBank/get/vo", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** listQuestionBankByPage POST /api/questionBank/list/page */
export async function listQuestionBankByPageUsingPost(
  body: API.QuestionBankQueryRequest,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponsePageQuestionBank_>(
    "/api/questionBank/list/page",
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

/** listQuestionBankVOByPage POST /api/questionBank/list/page/vo */
export async function listQuestionBankVoByPageUsingPost(
  body: API.QuestionBankQueryRequest,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponsePageQuestionBankVO_>(
    "/api/questionBank/list/page/vo",
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

/** updateQuestionBank POST /api/questionBank/update */
export async function updateQuestionBankUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.updateQuestionBankUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/questionBank/update", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}
