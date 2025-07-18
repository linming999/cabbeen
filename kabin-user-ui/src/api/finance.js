// src/api/finance.js
import request from '@/utils/request';

export function getFinancialList(params) {
  return request({
    url: '/file/finance/list',
    method: 'get',
    params
  });
}
