import { createRouter, createWebHistory } from 'vue-router'

import Home from '../views/Home.vue' // 首页
import Store from '../views/Store.vue' // 精品店页面
import About from '../views/About.vue' // 关于卡宾
import Contact from '../views/Contact.vue' // 联系我们
import OfflineStore from '../views/OfflineStore.vue' // 线下门店导流
import FindStore from '../views/FindStore.vue' // 寻找精品店
import FinancialReport from '../views/FinancialReport.vue' // 财务报表
import ProductDetail from '../views/product/ProductDetail.vue' //  商品详情页路由
// ✅ 新闻详情页组件
import NewsMalaysia from '../views/news/NewsMalaysia.vue';
import NewsHKListing from '../views/news/NewsHKListing.vue';
import NewsNYShow from '../views/news/NewsNYShow.vue';

const routes = [
  { path: '/', component: Home },
  { path: '/store', component: Store },
  { path: '/about', component: About },
  { path: '/contact', component: Contact },
  { path: '/offline-store', component: OfflineStore },
  { path: '/find-store', component: FindStore },
  { path: '/financial-report', component: FinancialReport },
  { path: '/product/detail/:id', component: ProductDetail },
    // ✅ 新增新闻详情页路由
  { path: '/news/malaysia', component: NewsMalaysia },
  { path: '/news/hk-listing', component: NewsHKListing },
  { path: '/news/ny-show', component: NewsNYShow },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior(to) {
    if (to.hash) {
      return {
        el: to.hash,
        behavior: 'smooth',
      }
    }
    return { top: 0 }
  },
})

export default router
