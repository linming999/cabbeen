import request from '@/utils/request'

// 查询商品类型列表
export function listType(query) {
  return request({
    url: '/goods/type/list',
    method: 'get',
    params: query
  })
}

// 查询商品类型详细
export function getType(id) {
  return request({
    url: '/goods/type/' + id,
    method: 'get'
  })
}

// 新增商品类型
export function addType(data) {
  return request({
    url: '/goods/type',
    method: 'post',
    data: data
  })
}

// 修改商品类型
export function updateType(data) {
  return request({
    url: '/goods/type',
    method: 'put',
    data: data
  })
}

// 删除商品类型
export function delType(id) {
  return request({
    url: '/goods/type/' + id,
    method: 'delete'
  })
}

// 查询商品类型树
export function getTypeTree() {
  return request({
    url: '/goods/type/tree',
    method: 'get'
  })
}
