<template>
  <div class="page-wrapper">
    <!-- 页面其他内容 -->

    <div class="footer">
      <div class="footer-content">
        <!-- 左侧二维码 -->
        <div class="footer-qr">
          <img src="@/assets/images/页脚二维码.png" alt="QR Code" />
        </div>

        <!-- 右侧信息 -->
        <div class="footer-info">
          <p class="company"><strong>{{ $t('footer.company') }}</strong></p>
          <p class="address">{{ $t('footer.address') }}</p>
          <p class="mobile">{{ $t('footer.mobile') }}</p>
          <p class="email">{{ $t('footer.email') }}</p>
          <p class="website">{{ $t('footer.website') }}</p>
          <p class="code">{{ $t('footer.code') }}</p>
        </div>
      </div>

      <!-- 页脚版权信息 -->
      <div class="footer-copyright">
        <p class="terms">{{ $t('footer.terms') }}</p>
        <p class="domain-info">{{ $t('footer.domainInfo') }}</p> <!-- 新增域名信息 -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Footer'
};
</script>

<style scoped>
/* 页面容器 */
/* .page-wrapper {
  display: flex;             设置为 flex 布局 
  flex-direction: column;     垂直排列内容 
  min-height: 100vh;          页面至少要有视口的高度 
} */

/* 页脚容器 */
.footer {
  background-color: #1a1a1a;   /* 设置背景颜色为深色 */
  color: white;                 /* 设置字体颜色为白色 */
  padding: 10px 0;              /* 设置页脚的上下内边距 */
  text-align: left;             /* 文本左对齐 */
  /* margin-top: auto;             使页脚位于页面底部 */
}

/* 页脚内容 */
.footer-content {
  display: flex;                /* 使用 flex 布局来排列二维码和信息 */
  justify-content: space-between;/* 使二维码和信息内容分别占据两边 */
  align-items: center;          /* 垂直居中 */
  max-width: 1500px;             /* 最大宽度为 1200px */
  margin: 0 auto;               /* 水平居中 */
  padding: 0 1px;              /* 内边距左右各20px */
  gap: 15px;                    /* 设置二维码和信息部分之间的间距为10px */
}

/* 右侧信息部分 */
.footer-info {
  width: 50%;                   /* 信息部分占用65%宽度 */
}

.footer-info p {
  margin: 10px 0;               /* 每项信息的上下间距为10px */
  font-size: 14px;               /* 设置字体大小为14px */
  color: rgba(255, 255, 255, 0.6);  /* 将信息部分字体颜色淡化，使用透明度为0.6 */
}

/* 公司名称的样式 */
.footer-info .company {
  font-weight: bold;            /* 设置加粗 */
  font-size: 16px;              /* 设置字体大小为16px */
  color: white;                 /* 设置公司名称字体颜色为白色 */
}

/* 版权信息的样式 */
.footer-info .terms {
  font-weight: bold;            /* 设置加粗 */
  font-size: 14px;              /* 设置字体大小为14px */
  color: white;                 /* 保持版权信息为白色 */
}

/* 左侧二维码部分 */
.footer-qr {
  width: 40%;                   /* 设置二维码占页面宽度的30% */
  text-align: center;           /* 设置二维码图片居中显示 */
  display: flex;                /* 使用 flex 布局确保内容居中 */
  justify-content: right;      /* 设置内容水平居中 */
  align-items: center;          /* 设置内容垂直居中 */
}

.footer-qr img {
  width: 140px;                 /* 设置二维码的宽度为100px */
  height: 140px;                /* 设置二维码的高度为100px */
}

/* 页脚版权信息 */
.footer-copyright {
  text-align: center;           /* 页脚版权信息居中 */
  margin-top: 20px;             /* 设置与上方内容的间距为40px */
  padding: 5px 0;              /* 设置上下内边距为20px */
  border-top: 1px solid #444;   /* 页脚顶部添加1px的边框 */
}

/* 版权信息的样式 */
.footer-copyright p {
  margin: 10px 0;               /* 每项信息的上下间距为10px */
  font-size: 13px;               /* 设置字体大小为14px */
}

/* 响应式设计：当屏幕宽度小于768px时进行布局调整 */
@media (max-width: 768px) {
  .footer-content {
    flex-direction: column;     /* 将布局改为垂直排列 */
    text-align: center;         /* 中心对齐内容 */
    align-items: center;
  }

  .footer-info {
    width: 100%;                /* 信息部分在小屏幕上占满100%宽度 */
    margin-bottom: 20px;        /* 设置底部间距为20px */
  }

  .footer-qr {
    width: 100%;                /* 二维码部分在小屏幕上占满100%宽度 */
    justify-content: center; 
    margin-bottom: 20px;        /* 设置底部间距为20px */
  }

  .footer-qr img {
    width: 160px;                /* 设置二维码在小屏幕上显示为80px */
    height: 160px;               /* 设置二维码在小屏幕上显示为80px */
  }
}
</style>
