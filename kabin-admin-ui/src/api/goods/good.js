import request from '@/utils/request'

// 查询商品信息列表
export function listGood(query) {
  return request({
    url: '/goods/good/list',
    method: 'get',
    params: query
  })
}

// 查询商品信息详细
export function getGood(id) {
  return request({
    url: '/goods/good/' + id,
    method: 'get'
  })
}

// 新增商品信息
export function addGood(data) {
  return request({
    url: '/goods/good',
    method: 'post',
    data: data
  })
}

// 修改商品信息
export function updateGood(data) {
  return request({
    url: '/goods/good',
    method: 'put',
    data: data
  })
}

// 删除商品信息
export function delGood(id) {
  return request({
    url: '/goods/good/' + id,
    method: 'delete'
  })
}

// 获取多级分类
export function getCategory() {
  return request({
    url: '/goods/good/getCategory',
    method: 'get'
  })
}

export function changeStatus(ids, status) {
  return request({
    url: '/goods/good/updateStatus',
    method: 'put',
    data: { ids, status }
  });
}
