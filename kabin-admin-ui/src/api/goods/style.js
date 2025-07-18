import request from '@/utils/request'

// 新增商品款式图片
export function addStyle(data) {
  return request({
    url: '/goods/style',
    method: 'post',
    data: data
  })
}

// （如果未来你还需要批量获取款式图，也可以添加这个方法）
export function listStyle(query) {
  return request({
    url: '/goods/style/list',
    method: 'get',
    params: query
  })
}
// 替换某商品的所有图片
export function replaceStyle(goodId, data) {
  return request({
    url: `/goods/style/replace/${goodId}`,
    method: 'post',
    data: data
  })
}
