<template>
  <div class="store-page">
    <div class="category-section">
      <!-- 男士精品 -->
      <div class="category-block inline">
        <div class="category-title">男士精品：</div>
        <div class="category-items">
          <span v-for="item in menswear" :key="item">{{ item }}</span>
        </div>
      </div>

      <!-- 卡宾潮鞋 -->
      <div class="category-block inline">
        <div class="category-title">卡宾潮鞋：</div>
        <div class="category-items">
          <span class="coming-soon">敬请期待</span>
        </div>
      </div>

      <!-- 潮流专辑 -->
      <div class="category-block inline">
        <div class="category-title">潮流专辑：</div>
        <div class="category-items">
          <span v-for="item in trend" :key="item">{{ item }}</span>
        </div>
      </div>

      <!-- 新品上新 -->
      <div class="category-block inline">
        <div class="category-title">新品上新：</div>
        <div class="category-items">
          <span>时尚达人限时折扣入口</span>
        </div>
      </div>
    </div>

    <!-- 添加分割线 -->
    <hr class="category-divider"/>
  </div>
</template>

<script>
export default {
  name: 'Store',
  data() {
    return {
      menswear: ['T恤', '衬衫', '卫衣', 'POLO', '夹克', '马甲', '线衫', '外套', '羽绒服'],
      trend: ['明星同款', '秀场同款', '都市轻商务', '街头潮流']
    };
  }
};
</script>

<style scoped>
.store-page {
  padding: 20px;
  background-color: #111; /* 黑色背景 */
  color: #fff; /* 白字 */
  min-height: 100vh;
}

.category-block {
  margin-bottom: 20px;
}

.category-title {
  font-weight: bold;
  font-size: 16px;
  margin-right: 10px;
  color: #f39c12; /* 金黄色标题，可根据品牌色调整 */
}

.category-block.inline {
  display: flex;
  align-items: center;
  flex-wrap: nowrap;
}

.category-block.inline .category-title {
  flex-shrink: 0;
}

.category-block.inline .category-items {
  display: flex;
  flex-wrap: nowrap;
  overflow-x: auto;
  white-space: nowrap;
}

.category-items span {
  display: inline-block;
  margin-right: 12px; /* 缩小每行之间的间距 */
  font-size: 14px;
  color: #fff;
  cursor: pointer;
  transition: all 0.2s;
}

.category-items span:hover {
  color: #f39c12; /* 悬浮时文字颜色变化 */
}

.coming-soon {
  color: #888;
  font-style: italic;
}

/* 分割线样式 */
.category-divider {
  border: 0;
  border-top: 1px solid #555;
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>
