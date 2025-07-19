<template>
  <div class="store-page">
    <!-- 分类菜单 -->
    <div class="category-section">
      <div class="category-block inline">
        <div class="category-title">男士精品上装：</div>
        <div class="category-items">
          <span
            v-for="item in menswearup"
            :key="item"
            @click="selectCategory(item)"
            :class="{ active: currentCategory === item }"
          >
            {{ item }}
          </span>
        </div>
      </div>

      <div class="category-block inline">
        <div class="category-title">男士精品下装：</div>
        <div class="category-items">
          <span
            v-for="item in mensweardw"
            :key="item"
            @click="selectCategory(item)"
            :class="{ active: currentCategory === item }"
          >
            {{ item }}
          </span>
        </div>
      </div>

      <div class="category-block inline">
        <div class="category-title">卡宾潮鞋：</div>
        <div class="category-items">
          <span
            v-for="item in shoes"
            :key="item"
            @click="selectCategory(item)"
            :class="{ active: currentCategory === item }"
          >
            {{ item }}
          </span>
        </div>
      </div>

      <div class="category-block inline">
        <div class="category-title">配件：</div>
        <div class="category-items">
          <span
            v-for="item in trend"
            :key="item"
            @click="selectCategory(item)"
            :class="{ active: currentCategory === item }"
          >
            {{ item }}
          </span>
        </div>
      </div>

      <div class="category-block inline">
        <div class="category-title">新品上新：</div>
        <div class="category-items">
          <span @click="selectCategory('新品上新')">时尚达人限时折扣入口</span>
        </div>
      </div>
    </div>

    <hr class="category-divider" />

    <!-- 商品展示区域 -->
    <div class="product-grid">
      <div v-for="item in products" :key="item.id" class="product-card">
        <Swiper
          :modules="[Autoplay, Pagination]"
          :loop="true"
          :autoplay="{ delay: 2000 }"
          class="swiper-box"
        >
          <SwiperSlide
            v-for="(img, i) in item.imageList"
            :key="i"
          >
            <img :src="img" class="product-img" />
          </SwiperSlide>
        </Swiper>
        <div class="product-info">
          <div class="product-name">{{ item.name }}</div>
          <div class="product-price">¥{{ item.price }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { Swiper, SwiperSlide } from 'swiper/vue'
import { Autoplay, Pagination } from 'swiper'
import 'swiper/css'
import 'swiper/css/pagination'
import request from '@/utils/request'

// 分类数据
const menswearup = ['T恤', '衬衫', '西服', '马甲', '夹克', 'POLO', '卫衣', '线衫', '休闲装']
const mensweardw = ['休闲裤', '牛仔裤', '针织裤', '短裤', '内裤']
const shoes = ['休闲鞋', '板鞋', '拖凉鞋']
const trend = ['箱包', '帽子', '腰带', '项链', '袜子', '领带']

// 当前分类 & 商品数据
const currentCategory = ref('衬衫')
const products = ref([])

// 选择分类
const selectCategory = (category) => {
  currentCategory.value = category
  fetchProducts()
}

// 获取商品
const fetchProducts = async () => {
  try {
    const res = await request.get('/goods/good/listByCategory', {
      params: { category: currentCategory.value }
    })
    products.value = res.data.data || []
  } catch (err) {
    console.error('获取商品失败', err)
  }
}

// 初始加载
onMounted(fetchProducts)
</script>

<style scoped>
.store-page {
  padding: 20px;
  background-color: #111;
  color: #fff;
  min-height: 100vh;
}

.category-block {
  margin-bottom: 20px;
}

.category-title {
  font-weight: bold;
  font-size: 16px;
  margin-right: 10px;
  color: #f39c12;
}

.category-block.inline {
  display: flex;
  align-items: center;
  flex-wrap: nowrap;
}

.category-block.inline .category-items {
  display: flex;
  flex-wrap: nowrap;
  overflow-x: auto;
  white-space: nowrap;
}

.category-items span {
  display: inline-block;
  margin-right: 12px;
  font-size: 14px;
  color: #fff;
  cursor: pointer;
  transition: all 0.2s;
}

.category-items span.active,
.category-items span:hover {
  color: #f39c12;
}

.category-divider {
  border: 0;
  border-top: 1px solid #555;
  margin-top: 20px;
  margin-bottom: 20px;
}

.product-grid {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  gap: 24px;
}

.product-card {
  width: calc(33.333% - 16px);
  background-color: #fff;
  color: #000;
  border-radius: 8px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.swiper-box {
  width: 100%;
  height: 360px;
}

.product-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.product-info {
  width: 100%;
  padding: 10px 0;
  text-align: center;
}

.product-name {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 4px;
}

.product-price {
  font-size: 14px;
  color: #e67e22;
}

/* 移动端适配 */
@media (max-width: 768px) {
  .product-card {
    width: 100%;
  }
}
</style>
