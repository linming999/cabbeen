import { createI18n } from "vue-i18n";

// 语言包配置
const messages = {
  en: {
    title: "CABBEEN",
    welcome: "Welcome to our website!",

    home: {
      title: "Home Page",
    },
    store: {
      title: "Store Page",
    },
    contact: {
      title: "Contact Us | 联系我们",
      headOffice: "Cabbeen Malaysia",
      addressLabel: "Address",
      address:
        "S-20-11, WISMA YNH, KIARA 163, 8, Jalan Kiara, Mont Kiara, 50480 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur",
      navigateBtn: "Open Map Navigation",
    },
    offlineStore: {
      real: "real",
      navigateBtn: "Navigate to this store",
    },
    findStore: {
      title: "Find a Store",
      description:
        "Find our stores online via platforms like TikTok, Instagram, etc.",
      tiktok: "TikTok",
      instagram: "Instagram",
      amazon: "Amazon",
    },
    financialReport: {
      title: "Financial Report",
      description: "Display financial data and reports here.",
      all: "All",
      loadMore: "Load More",
    },
    navbar: {
      store: "Store",
      about: "About",
      offlineStore: "Navigation",
      findStore: "FindStore",
      contact: "ContactUs",
      cooperation: "Business",
      recruitment: "Careers",
      financialReport: "Finance",
      login: "Login",
      menFashion: "Men >",
      cabbeenShoes: "Shoes  >",
      newArrivals: "New  >",
      trendAlbum: "Album  >",
      news: "NEWS",
    },
    footer: {
      company: "CABBEEN FASHION (M) SDN BHD",
      address:
        "Address:S-20-11, WISMA YNH, KIARA 163, NO.8, JALAN KIARA, MONT KIARA, 50480 W.P. KUALA LUMPUR, KUALA LUMPUR.",
      mobile: "Mobile:03-8681 6097",
      email: "E-mail:cabbeen@ycinvest.com",
      website: "Website:http://WWW.CABBEENNY.COM",
      code: "Code:2030.HK",
      terms: "© Copyright 2025 CABBEEN FASHION (M) SDN BHD",
      //domainInfo: "ICP filing of domain name information",
    },

    //明星大图
    celebrityTitleHtml: '<span class="en">CELEBRITY STYLE</span>',
    // 品牌价值观
    values: {
      items: [
        {
          title: "CONCEPT",
          description: "Reshape the trend",
        },
        {
          title: "VISION",
          description: "Build a global fashion brand with Chinese culture.",
        },
        {
          title: "MISSION",
          description:
            "Focus on quality, pursue design creativity, and continue to meet people's needs for multi-scenario wear.",
        },
        {
          title: "CORE VALUES",
          description:
            "We work in fashion, pursue a tasteful life, and constantly improve and surpass ourselves. We create a personalised, harmonious and simple working environment.We are grateful, serve the community, and work hard to create a new force of fashion in China.",
        },
      ],
    },
    about: {
      look: "View",

      intro: [
        "Cabbeen Fashion (referred to as Cabbeen) was founded in 1997. With its brand philosophy of “disrupting trends,” its forward-thinking approach to individualistic fashion, and its dedication to original design, Cabbeen has grown into one of China’s leading designer brands today. In February 2007, Cabbeen became the first Chinese designer brand to appear at New York Fashion Week. In October 2013, Cabbeen was successfully listed in Hong Kong, becoming the first Chinese fashion designer brand to be publicly listed in Hong Kong (Stock Code: 2030).",
        "As an iconic designer brand that grew from China’s domestic market, Cabbeen focuses on original design, brand operations, and developing diverse retail channels for apparel, footwear, and accessories. In the authoritative rankings by the China National Garment Association, Cabbeen has consistently ranked among the industry leaders, thanks to its outstanding retail performance, extensive retail network, and strong brand influence — fully demonstrating its leading position in China’s fashion industry.",
        "Since its establishment, Cabbeen has adhered to the business philosophy of “enhancing brand value as the core of operations and developing human resources as the foundation of management,” continually building and refining an international business model. The company has developed a multi-brand portfolio centered around Cabbeen, including 2AM, 1%, Cabbeen Love, Cabbeen Home, KOYO, MARKET LIBERTY, and thisisIZI8, extending into the fields of stylish home living and lifestyle services.",
        "To enable overseas consumers to conveniently experience and purchase Cabbeen’s fashion, the brand officially announced the opening of its first overseas store at Pavilion Bukit Jalil in Kuala Lumpur, Malaysia. The grand opening ceremony, held on June 8, marks a key milestone in Cabbeen’s international expansion strategy.",
      ],
    },
    news: {
      look: "View",
      // 关于卡宾页面中的新闻模块卡片
      cards: [
        {
          title:
            "Cabbeen’s First Overseas Store Opens Its Doors in Malaysia’s Pavilion Bukit Jalil",
          summary:
            "Cabbeen Fashion, as a leading designer fashion brand in China, celebrated the grand opening of its first overseas store on June 8 at Pavilion Bukit Jalil in Kuala Lumpur....",
        },
        {
          title:
            "In 2013, A Designer Brand of China , CABBEEN was listed on the Hong Kong Stock Exchange.",
          summary:
            "On October 28, 2013, as one of China’s leading original designer brands, CABBEEN was officially listed on the Hong Kong Stock Exchange...",
        },
        {
          title:
            "CABBEEN has become the first Chinese designer brand to showcase at New York Fashion Week.",
          summary:
            "At 20:00 on February 3, 2007, New York time, the Chinese menswear brand CABBEEN held its 2007/08 autumn and winter fashion show at New York Fashion Week...",
        },
      ],
      malaysia: {
        title:
          "Cabbeen’s First Overseas Store Opens Its Doors in Malaysia’s Pavilion Bukit Jalil",
        content1:
          "Cabbeen Fashion, one of China’s leading designer fashion brands, celebrated the grand opening of its first overseas store on June 8 at Pavilion Bukit Jalil in Kuala Lumpur. Since its founding in 1997, Cabbeen has embraced a brand philosophy of “youth, fashion, and individuality,” blending Eastern aesthetics with global trends— earning the love of urban men around the world. The brand’s entry into Malaysia marks an important milestone in its international expansion strategy, aiming to provide Malaysian consumers with a fresh shopping experience that fuses trendsetting style and refined lifestyle taste.",
        content2:
          "Cabbeen not only commands strong brand recognition in China but has also expanded its fashion influence across multiple countries and regions in Asia. In Malaysia, in particular, Cabbeen has quickly won widespread recognition and affection from local consumers.",
        content3:
          "To make it more convenient for Malaysian shoppers to experience and purchase Cabbeen’s collections, the brand officially announced its first overseas store at Pavilion Bukit Jalil in Kuala Lumpur, with a grand opening ceremony held on June 8 — marking a key step forward in Cabbeen’s global growth strategy.",
        content4:
          "Cabbeen’s first overseas store was established at Pavilion Bukit Jalil in Kuala Lumpur and held its grand opening ceremony on June 8.",
        content5:
          "Mandy Mao, Marketing General Manager of Cabbeen Fashion (Malaysia) Sdn. Bhd. While delivering her speech at the event highlighted that since its founding, Cabbeen has been dedicated to creating a stylish and attitude-driven dressing experience for a new generation of urban men.",
        content6:
          "Mandy Mao: “After more than 20 years of steady growth, Cabbeen has built a strong brand presence in cities around the world and has won widespread recognition and support.”",
        content7:
          "She added, “Today, Cabbeen’s official debut in Malaysia marks not only a key milestone in our international expansion strategy but also reflects our strong commitment and confidence in the Southeast Asian fashion market. Kuala Lumpur — a vibrant, multicultural city with a keen sense of style — is the ideal stage for Cabbeen to open this exciting new chapter. I would especially like to thank our local partners in Malaysia, our retail team, and every colleague who poured their passion and professionalism into making this store a reality. It is your dedication that brought this flagship from concept to grand opening so perfectly.”",
        content8:
          "Distinguished guests in attendance included Mr. Lovell Ho, Senior Leasing Director of Pavilion, Cabbeen Executive Director Mr. Keith Hooi, Executive Assistant Ms. Kelley Thong, Store Managers Mr. Michael Chu and Mr. Benson Foo, among others.",
        content9:
          "From left (second to sixth): Benson Foo, Michael Chu, Keith Hooi, Lovell Ho, Mandy Mao, and Kelley Thong officiate the ribbon-cutting ceremony for Cabbeen’s first overseas flagship store.",
        content10:
          "■ 25 Years of Design Legacy, 3 Years of Planning — Now Officially Launched",
        content11:
          "Later, in an exclusive interview with China Press, Mandy Mao revealed that Cabbeen aims to blend Eastern design aesthetics with global fashion trends to deliver a fresh and exciting shopping experience for local consumers.She also shared that Cabbeen’s entry into Malaysia marks the starting point of its broader strategy to expand across the Southeast Asian market. Over the next three years, the brand plans to establish a presence in Indonesia, Singapore, Thailand, Vietnam, and Cambodia, positioning Malaysia as its regional hub for trendsetting design and marketing in Southeast Asia.",
        content12:
          "Mr. Lovell Ho also personally tried on Cabbeen’s apparel, experiencing its unique design and fashionable appeal firsthand.",
        content13:
          "When asked why Cabbeen chose Malaysia for its first overseas store, she explained that the decision was based on several factors. “First, the shared language and cultural similarities significantly lower the communication barriers between us and the local market and consumers. Moreover, Malaysia’s unique multicultural heritage — where Chinese, Malay, and Indian aesthetics blend harmoniously — aligns perfectly with Cabbeen’s brand DNA of ‘Eastern aesthetics, global expression.”",
        content14:
          "“At the same time, we place great importance on nurturing local talent and creating job opportunities. Through Cabbeen’s presence and operations here, we hope to help drive the development of the local retail, fashion, and related service industry, while building a bridge of friendship between China and Malaysia — fostering deeper and more diverse bilateral ties through the medium of our brand.”",
        content15:
          "“As for choosing Pavilion Bukit Jalil as the location for our first overseas store, it offers multiple advantages as an emerging commercial hub: strong growth potential, high foot traffic, and a vibrant business environment. In Malaysia’s premium fashion market, there is still a lack of brands dedicated exclusively to menswear. Cabbeen hopes to fill this gap, spark new possibilities, and open an exciting new chapter for our brand in Malaysia.”",
        content16:
          "Cabbeen’s first overseas store celebrates its grand opening, attracting crowds of visitors eager to explore and snap photos.",
        content17:
          "“Over the next three years, Cabbeen will steadily expand its brand presence in Malaysia, with plans to open six stores within two years and to have a presence in cities across the country within three years. We see Malaysia as a key starting point for Cabbeen’s international strategy,” she explained. “We will be making significant investments in advertising here, with an initial budget of no less than RM30 million. This will focus on three main areas: establishing a strong brand image through our store facades and recognizable visual identity, building brand awareness quickly through celebrity endorsements and collaborations with influencers — combining entertainment and social media promotion — and actively engaging in local community initiatives. Through these concrete actions, we hope to give back to society and build closer connections with local consumers.”",
        content18:
          "She also revealed that the next major focus will be preparing for a large-scale fashion show hosted by Cabbeen’s headquarters in Quanzhou, Fujian, China.",
        content19:
          "“We will be inviting our business partners and friends to attend the event. In addition, we will gradually roll out our store expansion plan and, when the time comes, we plan to invite TVB artists from Hong Kong or celebrities from mainland China to officiate ribbon-cutting ceremonies, further boosting our brand’s visibility and public interest. These activities will help strengthen our presence in the local market,” she said.",
        content20:
          "She added that in terms of location strategy, the first phase will focus on establishing a strong presence in Kuala Lumpur as the core hub, before expanding step by step to other major cities such as Johor Bahru, Penang, and Melaka — aiming to cover Malaysia’s key markets comprehensively. Cabbeen also plans to launch its online sales channels next year, enabling an integrated online and offline strategy to offer consumers a more convenient and diverse shopping experience.",
        content21:
          "“At Cabbeen, we firmly believe that ‘every task is worth doing well with focus and dedication.’ That’s why, at this stage, our top priority is the steady development of our core Cabbeen brand and building a solid foundation in the Malaysian market. Once we have established this strong base, we will then consider introducing our other sub-brands at the right time — such as 2AM, which targets a younger demographic, and Cabbeen Love, which focuses on childrenswear — to gradually expand our brand portfolio,” she said.",
        content22:
          "When asked about what sets Cabbeen apart from other menswear brands, Mandy Mao said she believes Cabbeen’s greatest strength lies in striking the ideal balance between distinctive design and quality craftsmanship.",
        content23:
          "“Our positioning sits within an accessible and reasonable premium segment, making it easy for more consumers to own fashion pieces that are both stylish and practical. Cabbeen has always maintained high standards when it comes to details, fabrics, and wearing comfort. Take our store’s collection, for example — from business polos to sporty casual jackets — each piece fully embodies Cabbeen’s design philosophy of ‘attitude and style.’ Many customers might worry that long-sleeve pieces could feel too warm at first glance, but in fact, we use lightweight and breathable fabrics that ensure excellent comfort even in Southeast Asia’s hot and humid climate. They’re perfect for outdoor activities or sportswear,” she explained.",
        content24:
          "She added that Cabbeen is not just a fashion brand, but also a reflection of a lifestyle and a sense of cultural taste. She hopes that everyone who wears Cabbeen pieces will not only express their style but also feel a sense of spiritual connection and identity.",
        content25:
          "“We hope that Cabbeen will not only become a menswear brand loved by Malaysian consumers but also create a fashion space that brings together trends, aesthetics, and cultural experiences. We want everyone who steps into our stores to enjoy a customer-first shopping experience while also feeling the lifestyle and brand spirit that Cabbeen stands for,” she said.",
        content26:
          "After the lion dance and “cai qing” blessing ceremony, distinguished guests and Cabbeen staff posed for a group photo, wishing the brand a prosperous business and a successful grand opening.",
      },
      nyShow: {
        title:
          "CABBEEN has become the first Chinese designer brand to showcase at New York Fashion Week.",
        content1:
          "At 8:00 PM on February 3, 2007, New York time, the Chinese menswear brand CABBEEN held its 2007/08 autumn/winter fashion show at New York Fashion Week. Models and fitters in the backstage, as well as the main designer Mr. Cabbeen and the CABBEEN performance delegation, received widespread attention. This marks the first appearance of a Chinese designer brand at the globally renowned New York Fashion Week, known for its fashion and commercial significance.",
        content2:
          "The 40 sets of 2007/08 autumn and winter menswear showcased by CABBEEN at their press conference boldly and uniquely interpreted elements of Chinese traditional culture, shocking and earning praise from the international fashion community and professional media. The New York Post wrote in its preview of Fashion Week: “There are over 130 shows at New York International Fashion Week, with ten must-see shows, and CABBEEN menswear ranks in the top five.” The magazine “VOGUE,” praised by designers, writers, and artists as an authority on style and fashion, also made a feature report titled “Chinese Brand Debuts at New York Fashion Week.”",
      },
      hklisting: {
        title:
          "In 2013, A Designer Brand of China , CABBEEN was listed on the Hong Kong Stock Exchange.",
        content1:
          "On October 28, 2013, as one of China’s leading original designer brands, CABBEEN was officially listed on the Hong Kong Stock Exchange.",
        content2:
          "As early as the 1990s, a wave of change in men’s aesthetics swept the globe, challenging centuries of conventional, purely functional menswear design. CABBEEN was among the first to take this bold step forward in China, redefining the male silhouette with designs that accentuated the body’s lines for a better fit. CABBEEN’s signature menswear waistline design became a watershed moment in China’s menswear history — reshaping the concept of masculine beauty and forging a new image for modern Chinese men.Looking back at CABBEEN’s journey, it’s clear that the brand has always stayed true to its philosophy of “disrupting trends.” Not only has it consistently broken old conventions in design, but it has also dared to push boundaries in its operations and management. CABBEEN’s successful public listing in Hong Kong reflects the brand’s fearless drive to forge ahead and its unwavering confidence to lead the way.",
      },
    },
  },
  zh: {
    title: "卡宾时尚（马来西亚）私人有限公司",
    welcome: "欢迎访问我们的官网!",
    home: {
      title: "首页",
    },
    store: {
      title: "精品店页面",
    },
    contact: {
      title: "Contact Us | 联系我们",
      headOffice: "Cabbeen Malaysia",
      addressLabel: "公司地址",
      address:
        "S-20-11, WISMA YNH, KIARA 163, 8, Jalan Kiara, Mont Kiara, 50480 Kuala Lumpur, Wilayah Persekutuan Kuala Lumpur",
      navigateBtn: "打开地图导航",
    },
    offlineStore: {
       real: "门店实景",
      navigateBtn: "导航到此门店",
    },
    findStore: {
      title: "寻找精品店",
      description: "通过平台如 TikTok、Instagram 等在线寻找我们的门店。",
      tiktok: "TikTok",
      instagram: "Instagram",
      amazon: "Amazon",
    },
    financialReport: {
      title: "财务报表",
      description: "在这里展示财务数据和报表。",
      all: "全部",
      loadMore: "加载更多",
    },
    // 添加更多翻译内容
    navbar: {
      store: "卡宾精品",
      about: "关于卡宾",
      offlineStore: "线下门店导航",
      findStore: "寻找精品店",
      contact: "联系我们",
      cooperation: "商务合作",
      recruitment: "人才招募",
      financialReport: " 财务报表/港股咨询",
      login: "登录",
      menFashion: "男士精品  >",
      cabbeenShoes: "卡宾潮鞋  >",
      newArrivals: "新品上新  >",
      trendAlbum: "潮流专辑  >",
      news: "品牌新闻",
    },
    footer: {
      company: "卡宾时尚（马来西亚）私人有限公司",
      address:
        "地址：S-20-11, WISMA YNH, KIARA 163, NO.8, JALAN KIARA, MONT KIARA, 50480 W.P. KUALA LUMPUR, KUALA LUMPUR.",
      mobile: "电话：03-8681 6097",
      email: "邮箱：cabbeen@ycinvest.com",
      website: "官网：http://WWW.CABBEENNY.COM",
      code: "股票代码：2030.HK",
      terms: "版权所有 © 2025 卡宾时尚（马来西亚）私人有限公司",
      //domainInfo: "域名信息备案",
    },

    // 明星大图
    celebrityTitleHtml:
      '<span class="zh">明星同款</span><span class="en">/CELEBRITY STYLE</span>',
    // 品牌价值观
    values: {
      items: [
        {
          title: "品牌理念",
          description: "卡宾服饰 颠覆流行",
        },
        {
          title: "品牌愿景",
          description: "用中华文化 树世界时尚品牌",
        },
        {
          title: "品牌使命",
          description: "专注品质，追求设计创意，持续满足人们多场景穿搭的需求",
        },
        {
          title: "核心价值观",
          description:
            "我们从事时尚工作，追求有品位的生活；不断提升和超越自己，我们营造展现个性、和谐、简单的工作环境；我们心怀感恩、服务社会，为中国创造时尚新生力量而努力。",
        },
      ],
    },
    about: {
      look: "查看",

      intro: [
        "卡宾服饰（Cabbeen Fashion，简称卡宾）创立于 1997 年，其'颠覆流行'的品牌理念，始终领先一步的个性时尚定位以及对原创设计的坚持与付出，使卡宾成为今日中国领先设计师品牌。2007 年 2 月，卡宾成为首个登上纽约时装周的中国设计师品牌。2013 年 10 月，卡宾服饰成功在香港上市，成为首家在香港上市的中国时尚服装设计师品牌（股票代码：2030）。",
        "作为从中国本土成长起来的标志性设计师品牌，卡宾专注于服装、鞋类与配饰的原创设计、品牌运营与多元零售渠道建设。在中国服装协会的权威评选中，卡宾凭借出色的零售收入、广泛的终端网络及卓越的品牌影响力，持续位列行业前列，充分展现其在中国时尚产业中的领先地位",
        "创建至今，卡宾服饰一直秉持'经营以提升品牌价值为纲，管理以建设人力资源为本'的经营理念，不断构建与完善国际化经营模式。旗下已形成以Cabbeen为核心、2AM、1%、Cabbeen、Love、CabbeenHome、KOYO、MARKETLIBERTY、thisisIZI8多品牌矩阵，延伸至时尚家居、时尚生活服务领域的品牌矩阵",
        "为了让海外的消费者能够更便捷地体验并选购卡宾的潮流服饰，该品牌正式宣布将其首家海外旗舰店落户于马来西亚吉隆坡Pavilion Bukit Jalil，并于6月8日举行盛大的开业典礼，标志著卡宾国际化战略迈出关键一步。",
      ],
    },
    news: {
      look: "查看",
      // 关于卡宾页面中的新闻模块卡片
      cards: [
    {
      title: "卡宾海外首店亮相大马Pavilion Bukit Jalil正式开业",
      summary: "卡宾服饰（Cabbeen Fashion）作为中国领先的设计师时尚品牌，6月8日在吉隆坡Pavilion Bukit Jalil迎来海外首店盛大开幕..."
    },
    {
      title: "2013年，中国设计师品牌CABBEEN卡宾在香港上市",
      summary: "2013年10月28日，作为中国领先的原创设计师品牌，CABBEEN卡宾公司在香港交易所挂牌上市。早在上世纪九十年代，全球掀起一场男性..."
    },
    {
      title: "CABBEEN卡宾成为首个登上纽约时装周的中国设计师品牌",
      summary: "纽约时间2007年2月3号 20:00，中国男装品牌CABBEEN卡宾在纽约时装周举行 2007/08 秋冬时装发布会..."
    }
  ],
      malaysia: {
        title: "卡宾海外首店亮相大马Pavilion Bukit Jalil正式开业",
        content1:
          "卡宾服饰（Cabbeen Fashion）作为中国领先的设计师时尚品牌，6月8日在吉隆坡Pavilion Bukit Jalil迎来海外首店盛大开幕。自1997年成立以来，卡宾始终秉持“年轻、时尚、个性”的理念，将东方美学融入国际潮流，深受全球都市男性喜爱。此次进驻大马，标志著卡宾国际化战略的重要一步，旨在为大马消费者打造融合潮流风格与生活品位的全新购物体验。",
        content2:
          "卡宾不仅在中国拥有强大的品牌号召力，其时尚影响力更已拓展至亚洲多个国家与地区，尤其是在我国，卡宾迅速赢得了本地消费者的广泛认可与喜爱。",
        content3:
          "为了让大马的消费者能够更便捷地体验并选购卡宾的潮流服饰，该品牌正式宣布将其首家海外首店落户于吉隆坡Pavilion Bukit Jalil，并于6月8日举行盛大的开业典礼，标志著卡宾国际化战略迈出关键一步。",
        content4:
          "ps：卡宾海外首店落户于吉隆坡Pavilion Bukit Jalil，并于6月8日举行盛大的开业典礼。",
        content5:
          "卡宾服饰（马）有限公司广告部经理毛倩倩（Mandy）在开业典礼上致词时指出，自品牌创立以来，卡宾始终致力于为新一代都市男性打造有态度、有风格的穿搭体验。",
        content6:
          "图说：毛倩倩：经过20多年的深耕发展，卡宾已在全球多个城市建立起稳固的品牌影响力，并赢得了广泛的认可与喜爱。",
        content7:
          "“今天，卡宾正式落地大马，不仅是我们国际化战略的关键一步，更体现了我们对东南亚时尚消费市场的高度重视与信心。吉隆坡作为一个活力四射、文化多元且极具时尚敏感度的城市，正是卡宾拓展新篇章的理想舞台。对此，我要特别感谢我们在大马的本地合作伙伴、零售团队以及每一位为本次开业倾情付出的同仁。正是因为你们的专业与热情，才让这家店铺从构想到落地，完美呈现在大家眼前。”",
        content8:
          "出席贵宾包括柏威年高级租务部董事何伟宏、卡宾执行董事许伟健、执行助理汤裕冰、店长朱立升及符福量等。",
        content9:
          "图说：符福量（左2起）、朱立升、许伟健、何伟宏、毛倩倩及汤裕冰为卡宾海外首店主持剪彩开幕仪式。",
        content10: "■25年设计沉淀 3年布局正式启动",
        content11:
          "随后，毛倩倩在接受《中国报》专访时透露，卡宾希望把东方设计美学与全球潮流趋势融合，为本地消费者带来耳目一新的购物体验。并且卡宾此次进军大马，以其为中心，开启面向东南亚市场的全布局，并在3年同步进入印尼、新加坡、泰国、越南、柬埔寨等国家，托管东南亚区域潮流设计和营销方向。",
        content12:
          "图说：何伟宏亦亲自试穿卡宾服饰，现场感受其独特设计与时尚魅力。",
        content13:
          "谈及为何选择将卡宾海外首店落户大马，她解释，选择我国是基于多方面的考量。首先，语言和文化的相通性，能够大幅度降低我们与市场、消费者之间的沟通成本。并且，正因为这里流淌着多元文化的基因，即华人、马来人、印度人的审美在此交融。这种文化包容性与卡宾“东方美学，国际表达”的品牌DNA高度契合。",
        content14:
          "“同时，我们也非常重视本地人才的发展与就业带动作用，希望通过卡宾的落地运营，推动本地零售,时尚及相关服务业的发展。在经济与文化层面搭建起中国与大马之间的友谊桥梁，以品牌为媒介，促进双边关系的深入与多元化。”",
        content15:
          "“我们之所以选择Pavilion Bukit Jalil作为海外首店的落点，是因为这里作为新兴商圈，兼具发展潜力、人流密集与商业氛围活跃等多重优势。同时，在大马的中高端时尚市场中，专注男装的品牌仍较为稀缺，卡宾希望借此切入点，填补市场空白，激发更多可能性，全面开启在大马的品牌新篇章。”",
        content16: "图说：卡宾海外首店盛大开业，吸引众多民众到场参观打卡。",
        content17:
          "“未来3年内，卡宾将在大马稳步推进品牌布局，计划在2年内开设6家门店，并在3年内实现全马的城市都布有卡宾的门。我们将大马作为卡宾国际化战略的重要起点。会在这里进行大规模的广告投入，初期投入预算将不低于3000万令吉。这将主要用于三个方面，即卡宾品牌门面形象的全面铺设，打造具辨识度的视觉形象、明星代言与博主合作推广，通过娱乐与社交媒体相结合的传播方式，快速建立品牌认知，以及积极参与本地公益项目，希望通过实际行动回馈社会，拉近与本地消费者的距离。”",
        content18:
          "她透露，接下来的重点工作是筹备由卡宾总部主导在中国福建泉州的大型时装秀活动。",
        content19:
          "“我们将会邀請各位商界友人前往參加。此外，我们也将陆续推进门店开业计划，届时会邀请香港TVB艺人或中国内地明星亲临剪彩，提升品牌声量和关注度。这些活动将进一步巩固我们在本地市场的影响力。”",
        content20:
          "她说，选址方面，第一阶段将以吉隆坡为核心区域进行布局，随后逐步拓展至新山、槟城、以及马六甲等主要城市，力求覆盖大马的重点市场。同时，卡宾也计划于明年正式上线线上销售渠道，实现线上线下双渠道同步发力，为消费者提供更便捷、多元的购物体验。",
        content21:
          "“卡宾始终坚信‘每一件事都值得专注地做好’。因此，在当前阶段，我们将以卡宾主品牌的稳健发展为首要任务，专注夯实品牌在大马市场的基础。待市场基础成熟后，才会适时考虑引入旗下其他子品牌，如面向年轻客群的2AM，以及聚焦童装市场的Cabbeen Love，以实现品牌矩阵的逐步拓展。",
        content22:
          "谈及卡宾与其他男装品牌的差异，毛倩倩认为卡宾最大的优势在于将设计的独特性与品质实现了理想的平衡。",
        content23:
          "“我们的定位处于亲民且合理的中高端区间，让更多消费者能够轻松拥有兼具设计感与实穿性的时尚单品。一直以来，卡宾始终坚持对细节、面料与穿著体验有著高标准要求。以我们这家首店的服装陈列为例，从商务Polo到运动休闲外套，都充分展现了卡宾‘有态度、有风格’的设计理念。许多顾客初看长袖单品会担心穿著过热，但实际上，我们采用的是轻盈和透气面料，即使在东南亚高温湿润的气候条件下，也能保持出色的舒适度，非常适合户外活动或运动穿著。”",
        content24:
          "她补充，卡宾不仅仅是一个时尚品牌，更是一种生活态度与文化品位的体现。她希望每一位穿上卡宾的消费者不仅穿出风格，更能感受到一种精神上的共鸣与认同。",
        content25:
          "“我们希望卡宾不仅成为大马消费者喜爱的男装品牌，更能打造一个融合潮流、美学与文化体验的时尚空间。希望大家在这里不仅能体验卡宾以客为尊的购物体验，也能感受到卡宾所倡导的生活方式与品牌精神。”",
        content26:
          "图说：舞狮采青仪式后，众贵宾与卡宾职员合影留念，齐声祝愿卡宾生意兴隆、开业大吉。",
      },
      nyShow: {
        title: "CABBEEN卡宾成为首个登上纽约时装周的中国设计师品牌",
        content1:
          "纽约时间2007年2月3号 20:00，中国男装品牌CABBEEN卡宾在纽约时装周举行 2007/08 秋冬时装发布会。从后台的模特、穿衣工到前台的主设计师卡宾先生及CABBEEN演出代表团均受到了广泛的关注，这是中国设计师品牌首次在以时尚性和商业性闻名全球的纽约时装周上亮相。",
        content2:
          "CABBEEN卡宾发布会展示的 40 套 2007/08 秋冬男装对中国传统文化元素进行了大胆独特的诠释，引起了国际时装界和专业媒体人的震惊与赞誉。纽约邮报在其关于时装周的预览报道中写道：“纽约国际时装周共有 130 多场秀，其中有十场是必看的秀，CABBEEN卡宾男装排前五”。被设计师、作家和艺术家推崇为风格与时尚权威的《VOGUE》杂志也以 “中国品牌首登纽约时装周” 为题作了重点报道。",
      },
      hklisting: {
        title: "2013年，中国设计师品牌CABBEEN卡宾在香港上市",
        content1:
          "2013年10月28日，作为中国领先的原创设计师品牌，CABBEEN卡宾公司在香港交易所挂牌上市。",
        content2:
          "早在上世纪九十年代，全球掀起一场男性审美变革，打破几百年来男装设计程式化、功能化的特点，CABBEEN卡宾率先跨出第一步，细致的表达人体线条，让服装更合体，卡宾的男装腰线设计在中国男装设计里程中犹如一座分水岭，重新界定了男性美，构建了一种全新的中国男性形象。回顾卡宾的发展脉络不难发现，卡宾一直秉承“颠覆流行”的品牌理念，不仅在设计上打破陈腐旧套，在经营管理上也敢于做出新的尝试。CABBEEN卡宾在港股的成功上市，展现出卡宾一往无前的勇气和舍我其谁的气势。",
      },
    },
  },
};

const i18n = createI18n({
  legacy: false, // ✅ 关键点：关闭 legacy 模式
  locale: "zh", // 默认语言为英文
  messages,
});

export default i18n;
