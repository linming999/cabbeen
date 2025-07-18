<template>
  <div class="contact-page">
    <!-- 一级标题 -->
    <h1 class="section-title" >Cabbeen Malaysia | 卡宾马来西亚</h1>
    <!-- 二级标题 -->
    <h2 class="sub-title" id="cooperation">Business | 商务合作</h2>

    <!-- 三个横向并排的容器（黑色背景） -->
    <div class="contact-bg">
      <div class="contact-container">
        <!-- 公司总部 -->
        <div class="contact-card">
          <h3 class="card-title">🏢 公司总部 / Headquarters</h3>
          <div class="card-content">
            <p><strong>CABBEEN FASHION (M) SDN BHD</strong></p>
            <p>
              地址: S-20-11, WISMA YNH, KIARA 163, 8, Jalan Kiara, Mont Kiara,
              50480 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur
            </p>
            <p>邮箱: cabbeen@ycinvest.com</p>
            <p>工作时间: Monday – Friday, 9:00AM – 6:00PM</p>
          </div>
        </div>

        <!-- 市场合作 -->
        <div class="contact-card">
          <h3 class="card-title">📈 市场合作 / Marketing & Partnerships</h3>
          <div class="card-content">
            <p>如有媒体、广告、活动合作需求，欢迎联系我们的市场部团队：</p>
            <p><strong>Marketing Department 市场部</strong></p>
            <p>联系人：Ms. Mandy</p>
            <p>职位：市场经理 Marketing Manager</p>
            <p>邮箱：cabbeen.marketing@ycinvest.com</p>
            <p>WhatsApp：+60 1111323755</p>
          </div>
        </div>

        <!-- 招商联络 -->
        <div class="contact-card">
          <h3 class="card-title">🏬 招商联络 / Leasing & Expansion</h3>
          <div class="card-content">
            <p>欢迎商场方、店铺资源方、物业代理联系 Cabbeen 品牌入驻事宜：</p>
            <p><strong>招商对接人：Mrs.Aston</strong></p>
            <p>职位：招商经理 Leasing Manager</p>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- 人才招募模块 -->
  <div class="contact-bg" id="recruitment">
    <div style="text-align: center">
      <h2 class="join-title">Join Our Team | 加入卡宾马来西亚</h2>
    </div>
    >
    <div class="contact-container">
      <div class="contact-card">
        <h3 class="card-title">👥 人才招募 / Join Our Team</h3>
        <div class="card-content">
          <p>
            <strong>品牌招聘理念：</strong
            ><br />我们正在寻找热爱时尚、充满创意的伙伴加入我们的团队。
          </p>

          <p><strong>开放职位：</strong></p>
          <ul class="recruit-list">
            <li>销售顾问（初级/中级/高级）Sales Consultant</li>
            <li>店铺主管 Store Supervisor</li>
            <li>视觉陈列助理 Visual Merchandising Assistant</li>
            <li>市场部实习生 Marketing Intern</li>
          </ul>

          <p><strong>简历投递方式：</strong></p>
          <p>
            📧 招聘邮箱：<a href="mailto:hr001@ycinvest.com" class="email-link"
              >hr001@ycinvest.com</a
            >
          </p>
          <p>
            📲 微信/WhatsApp：<a href="tel:+60176500805" class="phone-link"
              >0176500805</a
            >
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
// 可以在这里添加任何需要的逻辑
</script>

<style scoped>
.contact-page {
  max-width: 100%;
  background-color: #000; /* 添加这行 */
  color: #fff;
  padding: 40px 0 0; /* 添加适当的上内边距 */
}

.section-title {
  text-align: center;
  font-size: 32px;
  margin-bottom: 20px;
  color: #f39c12;
  border-bottom: 2px solid #f39c12; /* 金色底边框 */
  padding-bottom: 10px;
  display: inline-block;
  margin-left: 50%;
  transform: translateX(-50%);
}

.sub-title {
  text-align: center;
  font-size: 24px;
  margin-bottom: 20px;
  color: #f39c12; /* 金色 */
}
.join-title {
  color: #f39c12; /* 金色文字 */
  text-align: center;
  font-size: 24px;
  margin: 40px auto 20px; /* 上边距40px，下边距20px */
  padding-bottom: 10px;
  position: relative; /* 为伪元素定位做准备 */
  display: inline-block; /* 使下划线长度随内容宽度变化 */
  background-color: #000;
}

/* 使用伪元素创建自定义下划线 */
.join-title::after {
  content: "";
  position: absolute;
  bottom: 0;
  left: 50%; /* 从中间开始 */
  transform: translateX(-50%); /* 居中 */
  width: 80%; /* 控制下划线长度为标题宽度的80% */
  height: 2px; /* 下划线粗细 */
  background-color: #f39c12; /* 金色下划线 */
}

/* 新增的黑色背景容器 */
.contact-bg {
  width: 100%;
  background-color: #000;
  padding: 20px 0; /* 上下内边距 */
}

.contact-container {
  display: flex;
  justify-content: space-between;
  gap: 30px;
  flex-wrap: wrap;
  max-width: 90%;
  margin: 0 auto;
  padding: 0 20px;
}

.contact-card {
  flex: 1 1 30%; /* 更精确的宽度控制 */
  min-width: 280px; /* 更小的最小宽度 */
  background: rgba(255, 255, 255, 0.1);
  border-radius: 8px;
  border: 1px solid rgba(241, 196, 15, 0.3);
  padding: 20px; /* 减小内边距 */
  transition: all 0.3s ease;
  color: #fff;
}

.contact-card:hover {
  transform: translateY(-5px);
  background: rgba(255, 255, 255, 0.15);
  border-color: #f39c12;
  box-shadow: 0 10px 20px rgba(241, 196, 15, 0.1);
}

.card-title {
  font-size: 20px;
  color: #f39c12; /* 金色 */
  margin-bottom: 20px;
  padding-bottom: 10px;
  border-bottom: 1px solid rgba(241, 196, 15, 0.3);
}

.card-content {
  line-height: 1.8;
}

.card-content p {
  margin-bottom: 12px;
}

.card-content strong {
  color: #f39c12; /* 金色强调 */
}
.recruit-list {
  padding-left: 20px;
  margin-bottom: 16px;
}
.recruit-list li {
  list-style: disc;
  margin-bottom: 8px;
  font-size: 15px;
}

.email-link,
.phone-link {
  color: #f39c12;
  text-decoration: underline;
  word-break: break-all;
}
.email-link:hover,
.phone-link:hover {
  color: #fff;
  text-decoration: none;
}

/* 响应式设计 */
@media (max-width: 1024px) {
  /* 平板设备 */
  .contact-container {
    max-width: 95%; /* 增加容器宽度占比 */
    gap: 20px; /* 减小卡片间距 */
  }

  .contact-card {
    padding: 15px; /* 减小内边距 */
  }

  .card-title {
    font-size: 18px; /* 调小标题字号 */
    margin-bottom: 15px;
  }

  .card-content p {
    font-size: 14px; /* 调小正文字号 */
    line-height: 1.6;
  }
}

@media (max-width: 768px) {
  /* 小尺寸平板 */
  .section-title {
    font-size: 28px;
    margin-bottom: 15px;
  }

  .sub-title {
    font-size: 20px;
    margin-bottom: 15px;
  }

  .contact-bg {
    padding: 15px 0; /* 调整背景容器内边距 */
  }

  .contact-container {
    flex-wrap: wrap; /* 允许换行 */
  }

  .contact-card {
    flex: 1 1 45%; /* 两列布局 */
    min-width: 45%; /* 确保两列显示 */
  }
}

@media (max-width: 480px) {
  /* 移动设备 */
  .section-title {
    font-size: 24px;
    padding-bottom: 8px;
    border-bottom-width: 1px;
  }

  .sub-title {
    font-size: 18px;
  }

  .contact-container {
    flex-direction: column;
    gap: 15px;
    padding: 0 15px;
  }

  .contact-card {
    flex: 1 1 100%; /* 单列布局 */
    min-width: 100%;
    padding: 15px;
  }

  .card-title {
    font-size: 16px;
  }

  .card-content p {
    font-size: 13px;
  }
}
</style>
