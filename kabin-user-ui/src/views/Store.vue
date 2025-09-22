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
          :modules="[Pagination]"
          :loop="true"
          :pagination="{ clickable: true }"
          class="swiper-box"
        >
          <SwiperSlide v-for="(img, i) in item.imageList" :key="i">
            <img
              :src="img"
              class="product-img"
              @click="goToDetail(item.id)"
              style="cursor: pointer"
            />
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
import { ref, onMounted, onBeforeMount } from "vue";
import { Swiper, SwiperSlide } from "swiper/vue";
import { Pagination } from "swiper/modules";
import "swiper/css";
import "swiper/css/pagination";
import request from "@/utils/request";
import { useRouter, useRoute } from "vue-router";
const router = useRouter();
const route = useRoute();
const storePage = ref(null);

onMounted(() => {
  if (route.query.scrollY) {
    setTimeout(() => {
      window.scrollTo(0, Number(route.query.scrollY));
    }, 0);
  }
});

// 点击商品时保存滚动位置
const goToDetail = (id) => {
  const scrollY = window.scrollY || document.documentElement.scrollTop;
  router.push({
    path: `/product/detail/${id}`,
    query: { scrollY },
  });
};
// 分类数据
const menswearup = [
  "T恤",
  "衬衫",
  "西服",
  "马甲",
  "夹克",
  "POLO",
  "卫衣",
  "线衫",
  "休闲装",
];
const mensweardw = ["休闲裤", "牛仔裤", "针织裤", "短裤", "内裤"];
const shoes = ["休闲鞋", "板鞋", "拖凉鞋"];
const trend = ["箱包", "帽子", "腰带", "项链", "袜子", "领带"];

// 当前分类 & 商品数据
const currentCategory = ref("T恤");
const products = ref([]);

// 选择分类
const selectCategory = (category) => {
  currentCategory.value = category;
  fetchProducts();
};

// 获取商品
const fetchProducts = async () => {
  try {
    const res = await request.get("/goods/good/listByCategory", {
      params: { category: currentCategory.value },
    });
    // console.log(res.data);

    products.value = res.data || [];
  } catch (err) {
    console.error("获取商品失败", err);
  }
};

// 初始加载
onMounted(fetchProducts);
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
  justify-content: center;
  gap: 24px;
}

.product-card {
  width: calc(25% - 18px);
  background-color: #fff;
  border-radius: 10px;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s ease;
}
.product-card:hover {
  transform: translateY(-4px);
}

.swiper-box {
  width: 100%;
  aspect-ratio: 3 / 4;
  overflow: hidden;
  border-radius: 8px;
  background-color: #fff;
}

.product-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
}

.product-info {
  padding: 12px;
  text-align: center;
  background-color: #000000;
  font-size: 14px;
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

.swiper-box :deep(.swiper-button-next),
.swiper-box :deep(.swiper-button-prev) {
  display: none !important;
}


.swiper-box :deep(.swiper-button-next)::after,
.swiper-box :deep(.swiper-button-prev)::after {
  font-size: 14px;
}

.swiper-box :deep(.swiper-pagination) {
  position: absolute;
  bottom: 10px;
  left: 0;
  width: 100%;
  display: flex;
  justify-content: center;
  z-index: 10;
}

.swiper-box :deep(.swiper-pagination-bullet) {
  width: 6px;
  height: 6px;
  margin: 0 3px !important;
  background-color: rgba(255, 255, 255, 0.5);
  opacity: 1;
  transition: all 0.3s;
}

.swiper-box :deep(.swiper-pagination-bullet-active) {
  background-color: #fff;
  width: 12px;
  border-radius: 3px;
}


@media (max-width: 768px) {
  .product-grid {
    gap: 12px;
    padding: 0 8px;
    justify-content: space-between;
    margin: 0 -4px;
  }

  .product-card {
    width: calc(50% - 6px);
    margin-bottom: 12px;
    border-radius: 8px;
  }

  .swiper-box {
    aspect-ratio: 3 / 4; /* 改为正方形比例 */
  }

  .product-info {
    padding: 8px;
  }

  .product-name {
    font-size: 14px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  .product-price {
    font-size: 13px;
  }

  /* 缩小导航按钮 */
  .swiper-box :deep(.swiper-button-next),
  .swiper-box :deep(.swiper-button-prev) {
    width: 20px;
    height: 20px;
  }

  .swiper-box :deep(.swiper-button-next)::after,
  .swiper-box :deep(.swiper-button-prev)::after {
    font-size: 10px;
  }

  /* 分类菜单调整 */
  .category-block.inline {
    flex-direction: column;
    align-items: flex-start;
  }

  .category-title {
    margin-bottom: 8px;
    font-size: 15px;
  }

  .category-items {
    width: 100%;
    padding-bottom: 8px;
  }

  .category-items span {
    font-size: 13px;
    margin-right: 10px;
  }

  .swiper-box :deep(.swiper-pagination-bullet) {
    width: 4px;
    height: 4px;
    margin: 0 2px !important;
  }
  
  .swiper-box :deep(.swiper-pagination-bullet-active) {
    width: 8px;
  }
}
</style>
