<template> 
  <div class="store-page">
    <!-- 分类菜单 -->
    <div class="category-section">
      <div class="category-block inline">
        <div class="category-title">{{ $t("categories.mensTops") }}：</div>
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
        <div class="category-title">{{ $t("categories.mensBottoms") }}：</div>
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
        <div class="category-title">{{ $t("categories.cabbeenShoes") }}：</div>
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
        <div class="category-title">{{ $t("categories.accessories") }}：</div>
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
        <div class="category-title">{{ $t("categories.newArrivals") }}：</div>
        <div class="category-items">
          <span @click="selectCategory($t('categories.newArrivals'))">{{ $t("categories.fashionistaDiscount") }}</span>
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
import { ref, watch, onActivated, computed } from "vue";
import { Swiper, SwiperSlide } from "swiper/vue";
import { Pagination } from "swiper/modules";
import "swiper/css";
import "swiper/css/pagination";
import request from "@/utils/request";
import { useRouter, useRoute } from "vue-router";
import { useI18n } from "vue-i18n";

const router = useRouter();
const route = useRoute();

// 分类数据 - 使用 i18n 翻译
const { t } = useI18n();

// 将分类数组改为计算属性，支持语言切换
const menswearup = computed(() => [
  t("categories.tshirt"), t("categories.shirt"), t("categories.suit"), 
  t("categories.vest"), t("categories.jacket"), t("categories.polo"), 
  t("categories.sweatshirt"), t("categories.knitwear"), t("categories.casualWear")
]);

const mensweardw = computed(() => [
  t("categories.casualPants"), t("categories.jeans"), t("categories.knitPants"), 
  t("categories.shorts"), t("categories.underwear")
]);

const shoes = computed(() => [
  t("categories.casualShoes"), t("categories.sneakers"), t("categories.sandals")
]);

const trend = computed(() => [
  t("categories.bags"), t("categories.hats"), t("categories.belts"), 
  t("categories.necklaces"), t("categories.socks"), t("categories.ties")
]);

// 当前分类 & 商品数据
const currentCategoryKey = ref('tshirt'); // 存储分类的key而不是翻译文本
const currentCategory = computed(() => t(`categories.${currentCategoryKey.value}`));

// 创建分类映射，用于在语言切换时保持选择状态
const categoryMap = {
  'T恤': 'tshirt',
  'T-shirt': 'tshirt',
  '衬衫': 'shirt', 
  'Shirt': 'shirt',
  '西服': 'suit',
  'Suit': 'suit',
  '马甲': 'vest',
  'Vest': 'vest',
  '夹克': 'jacket',
  'Jacket': 'jacket',
  'POLO': 'polo',
  'Polo': 'polo',
  '卫衣': 'sweatshirt',
  'Sweatshirt': 'sweatshirt',
  '线衫': 'knitwear',
  'Knitwear': 'knitwear',
  '休闲装': 'casualWear',
  'Casual Wear': 'casualWear',
  '休闲裤': 'casualPants',
  'Casual Pants': 'casualPants',
  '牛仔裤': 'jeans',
  'Jeans': 'jeans',
  '针织裤': 'knitPants',
  'Knit Pants': 'knitPants',
  '短裤': 'shorts',
  'Shorts': 'shorts',
  '内裤': 'underwear',
  'Underwear': 'underwear',
  '休闲鞋': 'casualShoes',
  'Casual Shoes': 'casualShoes',
  '板鞋': 'sneakers',
  'Sneakers': 'sneakers',
  '拖凉鞋': 'sandals',
  'Sandals': 'sandals',
  '箱包': 'bags',
  'Bags': 'bags',
  '帽子': 'hats',
  'Hats': 'hats',
  '腰带': 'belts',
  'Belts': 'belts',
  '项链': 'necklaces',
  'Necklaces': 'necklaces',
  '袜子': 'socks',
  'Socks': 'socks',
  '领带': 'ties',
  'Ties': 'ties',
  '新品上新': 'newArrivals',
  'New Arrivals': 'newArrivals'
};
const products = ref([]);

// 拉数
const fetchProducts = async () => {
  try {
    const res = await request.get("/goods/good/listByCategory", {
      params: { category: currentCategory.value },
    });
    products.value = res.data || [];
  } catch (e) {
    console.error("获取商品失败", e);
  }
};

// 页内点击切换
const selectCategory = (category) => {
  // 找到对应的分类key
  const categoryKey = categoryMap[category];
  if (categoryKey) {
    currentCategoryKey.value = categoryKey;
  } else {
    // 如果找不到映射，直接使用传入的分类
    currentCategoryKey.value = category;
  }
  fetchProducts();
};

// ✅ 把"根据路由设置分类并拉数"提成函数
const applyRouteCategory = () => {
  const q = route.query?.category;
  if (typeof q === "string" && q.trim()) {
    const categoryKey = categoryMap[q.trim()];
    if (categoryKey) {
      currentCategoryKey.value = categoryKey;
    } else {
      currentCategoryKey.value = q.trim();
    }
  }
  fetchProducts();
};

// ✅ 用 watch 监听 query 变化，并且 immediate 立即执行一次（替代你两个 onMounted）
watch(
  () => route.query.category,
  () => {
    applyRouteCategory();
    // （可选）移动端滚到商品区
    requestAnimationFrame(() => {
      const top = document.querySelector(".product-grid")?.getBoundingClientRect().top || 0;
      window.scrollTo({ top: window.scrollY + top - 12, behavior: "smooth" });
    });
  },
  { immediate: true }
);

// （可选）如果此页被 keep-alive 缓存，激活时也同步一次
onActivated(() => applyRouteCategory());

// 商品详情跳转（保持你的逻辑）
const goToDetail = (id) => {
  const scrollY = window.scrollY || document.documentElement.scrollTop;
  router.push({ path: `/product/detail/${id}`, query: { scrollY } });
};
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
  .category-section {
    display: none !important;
  }
  .category-divider {
    display: none !important;
  }

  /* 可选：略微收紧内边距，避免顶部显得空 */
  .store-page {
    padding-top: 12px;
  }
}
</style>
