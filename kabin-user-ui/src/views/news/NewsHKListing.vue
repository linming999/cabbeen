<!-- src/views/news/NewsMalaysia.vue -->
<template>
  <div class="news-detail-page">
    <div class="news-hero">
      <img src="@/assets/images/新闻背景.png" alt="头图" />
      <div class="hero-text">
        <h1>品牌新闻<span class="en-title"> / NEWS CENTER</span></h1>
      </div>
    </div>

    <div class="news-detail-content">
      <h2>{{ $t("news.hklisting.title") }}</h2>
      <p class="meta">2013-10-28 ｜ 👁 1210{{ $t("news.look") }}</p>

      <img src="@/assets/images/新闻缩略图2.png" alt="香港上市" />
      <p>{{ $t("news.hklisting.content1") }}</p>
      <p>{{ $t("news.hklisting.content2") }}</p>
      <div class="back">
        <router-link to="/about" class="back-button">
          <span class="arrow">←</span> 返回 / return
        </router-link>
      </div>
    </div>
  </div>
</template>

<style scoped>
.news-detail-page {
  background: black;
  color: white;
  padding-bottom: 60px;
}

/* 修正后的hero部分样式 */
.news-hero {
  position: relative;
  height: 230px;
  overflow: hidden;
  text-align: center;
  white-space: nowrap; /* 防止文本换行 */
}
.news-hero img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  object-position: top;
}
.hero-text {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  width: 100%;
  text-align: center;
  padding: 0 20px; /* 添加内边距防止文字贴边 */
  white-space: nowrap; /* 防止文本换行 */
}
.hero-text h1 {
  font-size: 20px;
  font-weight: bold;
  margin: 0;
  color: white;
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.5); /* 添加文字阴影增强可读性 */
  display: inline-block; /* 让标题内容保持在一行 */
}
.en-title {
  display: inline; /* 改为inline使不换行 */
  font-size: 20px;
  font-weight: normal;
  margin-left: 8px; /* 将margin-top改为margin-left */
  letter-spacing: 0.5px; /* 减小字母间距 */
  color: #ffb100;
  text-shadow: 0 2px 4px rgba(0, 0, 0, 0.5);
}

/* 其余内容样式保持不变 */
.news-detail-content {
  max-width: 900px;
  margin: 40px auto;
  padding: 0 20px;
}
.news-detail-content h2 {
  color: #ffb100;
  font-size: 26px;
  margin-bottom: 40px;
  text-align: center; /* 添加这行使标题居中 */
  padding: 0 20px; /* 可选：添加内边距防止文字贴边 */
}
.news-detail-content .meta {
  font-size: 13px;
  color: #aaa;
  margin-bottom: 20px;
  text-align: center; /* 使文字居中 */
}
.news-detail-content p {
  line-height: 1.8;
  margin-bottom: 15px;
  text-align: justify;
  text-indent: 2em; /* 添加首行缩进2字符 */
  font-size: 16px; /* 可选：统一字体大小 */
}
.news-detail-content img {
  width: 100%;
  margin: 20px 0;
  border-radius: 8px;
}
.back {
  margin-top: 40px;
  text-align: center;
}

.back-button {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  padding: 10px 24px;
  background-color: #000;
  color: #ffb100;
  border: 1px solid #ffb100;
  border-radius: 30px;
  font-size: 16px;
  font-weight: 500;
  text-decoration: none;
  transition: all 0.3s ease;
  min-width: 120px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.back-button:hover {
  background-color: #ffb100;
  color: #000;
  border-color: #000;
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(255, 179, 0, 0.2);
}

.back-button:active {
  transform: translateY(0);
  box-shadow: 0 2px 4px rgba(255, 179, 0, 0.2);
}

.arrow {
  margin-right: 8px;
  font-size: 18px;
}

/* 自适应布局调整（针对移动端） */
@media (max-width: 768px) {
  /* 头部hero区域 */
  .news-hero {
    height: 180px; /* 改小高度以适应手机屏幕 */
  }
  .hero-text h1 {
    font-size: 18px; /* 减小字体大小 */
  }
  
  /* 内容部分 */
  .news-detail-content {
    max-width: 100%; /* 使内容区适应屏幕 */
    margin: 20px 10px;
    padding: 0 10px; /* 适当调整内边距 */
  }
  
  .news-detail-content h2 {
    font-size: 22px; /* 标题字号减小 */
  }
  
  /* meta信息和内容 */
  .news-detail-content .meta {
    font-size: 12px; /* 调整meta信息的字体大小 */
  }
  
  .news-detail-content p {
    font-size: 14px; /* 缩小文本大小，适应小屏 */
  }
  
  /* 图片和内容之间的间距 */
  .news-detail-content img {
    margin: 10px 0; /* 减少图片的上下间距 */
  }
  
  /* 返回按钮 */
  .back-button {
    padding: 8px 18px; /* 减小按钮的内边距 */
    font-size: 14px; /* 缩小字体 */
  }
}

</style>
