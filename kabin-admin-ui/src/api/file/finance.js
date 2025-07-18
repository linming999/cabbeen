import request from '@/utils/request'

// 查询财务报信息列表
export function listFinance(query) {
  return request({
    url: '/file/finance/list',
    method: 'get',
    params: query
  })
}

// 查询财务报信息详细
export function getFinance(id) {
  return request({
    url: '/file/finance/' + id,
    method: 'get'
  })
}

// 新增财务报信息
export function addFinance(data) {
  return request({
    url: '/file/finance',
    method: 'post',
    data: data
  })
}

// 修改财务报信息
export function updateFinance(data) {
  return request({
    url: '/file/finance',
    method: 'put',
    data: data
  })
}

// 删除财务报信息
export function delFinance(id) {
  return request({
    url: '/file/finance/' + id,
    method: 'delete'
  })
}

// 上传财务报文件
export function uploadFinanceFile(data) {
  return request({
    url: '/file/finance/upload',
    method: 'post',
    data
  })
}
