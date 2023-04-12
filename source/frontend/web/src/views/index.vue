<template>
  <div class="app-container">
    <div class="first-banner">
      <img style="width: 100%;position: absolute;" :src="getImgUrl($t('banner_image_1'+'_'+$i18n.locale))">
      <div ref="firstWrapper" />
      <div class="first-wrapper">
        <el-row :gutter="20" justify="space-around">
          <el-col v-for="data in firstCard" :key="data.title" class="card-container">
            <el-card class="card-wrapper">
              <img class="card-img" :src="getImgUrl(data.image)">
              <div class="card-txt" style="height: 114px;">
                <p class="card-title">{{ $t('index.first_card.' + data.title) }}</p>
                <p class="card-description">{{ $t('index.first_card.' + data.description) }}</p>
                <router-link :to="data.route" class="card-more"><!--了解更多-->{{ $t('index.first_card.read_more') }}</router-link>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
      <!-- 公告 -->
      <el-row v-if="noticesIsShow" class="notices clearfix">
        <div class="noticesLeft">
          <img src="@/assets/images/index/notice_icon.png" alt="">
          <el-tooltip ref="tooltip" class="tooltip" effect="dark" :content="tipText" placement="top" :disabled="tooltipShow">
            <router-link ref="newNoticeTitle" class="newNoticeTitle" target="_blank" :to="{path:'/notice',query: {id:newNotice.id}}">【{{ $t('index.first_card.notice_platform_announcement') }}】{{ newNotice.title }} </router-link>
          </el-tooltip>
        </div>
        <div class="noticesRight"><router-link target="_blank" to="/noticeList">{{ newNotice.releaseDate + '' }}&nbsp;&nbsp; {{ $t('index.first_card.notice_read_more') }}<span class="moreNotice">></span></router-link></div>
      </el-row>
      <!-- 聊天悬浮图标 -->
      <div v-if="false" class="chatIcon">
        <div v-if="langIsShow" class="langCheck">
          <div>请选择语言</div>
          <div class="lang"><span><a href="javascript:void(0)">中文</a> </span>|<span> <a href="javascript:void(0)">English</a> </span></div>
        </div>
        <el-row>
          <img src="@/assets/images/index/icon_index_service_1.png" alt="" class="serviceIcon" @click="getChatDialog()">
        </el-row>
        <el-row>
          <img src="@/assets/images/index/icon_index_goTop_1.png" alt="" class="goTopIcon" @click="goTop()">
        </el-row>
      </div>
      <!-- 聊天弹框 -->
      <el-dialog
        v-if="false"
        ref="chatDialog"
        :visible.sync="chatDialog"
        width="360px"
        class="chatDialog"
        :close-on-click-modal="false"
        title="国瑞在线"
        @close="chatClose"
      >
        <div ref="chatContentRef" :contenteditable="false" class="chatContent clearfix" />
        <div class="chating">
          <a href="javascript:void(0)" class="emtionImg" :contenteditable="false" @click="emtionShow()"><img src="@/assets/images/index/icon_index_service_emtion.png" alt=""></a>
          <emotion v-if="emtionIsShow" class="emotion" :height="138" @emotion="handleEmotion" />
          <el-upload
            class="upload"
            action="https://jsonplaceholder.typicode.com/posts/"
            auto-upload
            multiple
            :before-upload="beforeUpload"
          >
            <img src="@/assets/images/index/icon_index_service_img.png" alt="">
          </el-upload>
          <div
            ref="textarea"
            class="textarea"
            :contenteditable="contenteditable"
            @keyup.enter="sendMessage()"
            @keydown.enter.prevent
            @blur="focusContent"
            v-html="content"
          />
          <div class="chatBtn">
            <el-button class="chatBtn_1" @click="closeChatDialog()">结束会话</el-button>
            <el-button class="chatBtn_2" @click="sendMessage()">发送</el-button>
          </div>
        </div>
        <img src="@/assets/images/index/icon_index_service_logo.png" alt="">
      </el-dialog>
      <!-- 图片弹框 -->
      <el-dialog
        ref="previewDialog"
        :visible.sync="previewDialog"
        :width="imgWidth"
      >
        <img :src="imgSrc" alt="">
      </el-dialog>
      <!-- 结束会话弹框 -->
      <el-dialog
        :visible.sync="closeChatCheckDialog"
        width="240px"
        class="closeChatCheck"
      >
        <span>是否结束当前会话</span>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="closeChatCheck()">是</el-button>
          <el-button @click="closeChatCheckDialog = false">否</el-button>
        </span>
      </el-dialog>
    </div>
    <div ref="secondWrapper" />
    <div class="second-banner">
      <div class="second-wrapper">
        <p class="second-title">{{ $t('index.second_card.title') }}</p>
        <el-row :gutter="20" justify="space-around">
          <el-col v-for="data in secondCard" :key="data.title" class="card-container">
            <el-card class="card-wrapper">
              <img class="card-img" :src="getImgUrl(data.image)">
              <div class="card-txt">
                <p class="card-title">{{ $t('index.second_card.' + data.title) }}</p>
                <p class="card-description">
                  <span style="font-size: 13px">{{ $t('index.second_card.' + data.description) }}</span>
                  <router-link :to="data.route" class="card-go el-icon-arrow-right" />
                </p>
              </div>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </div>
    <div class="third-banner">
      <div class="third-wrapper">
        <p class="third-title">{{ $t('index.third_card.title') }}</p>
        <el-row :gutter="20" justify="space-around">
          <el-col v-for="data in thirdCard" :key="data.title" class="card-container">
            <router-link :to="data.route">
              <el-card class="card-wrapper">
                <img class="card-img" :src="getImgUrl(data.image)">
                <div class="card-txt" style="height: 100px;">
                  <p class="card-title">{{ $t('index.third_card.' + data.title) }}</p>
                  <p class="card-description" style="min-height: 43px">
                    <span>{{ $t('index.third_card.' + data.description) }}</span>
                  </p>
                </div>
              </el-card>
            </router-link>
          </el-col>
        </el-row>
      </div>
    </div>
    <div class="fourth-banner">
      <div class="fourth-wrapper">
        <p class="fourth-title">{{ $t('index.fourth_card.title') }}</p>
        <el-row :gutter="20" justify="space-around">
          <el-col v-for="data in fourthCard" :key="data.title" class="card-container">
            <router-link :to="data.route">
              <el-card class="card-wrapper">
                <!--<img class="card-img" :src="getImgUrl(data.image)">-->
                <div class="card-txt">
                  <p class="card-title">{{ $t('index.fourth_card.' + data.title) }}</p>
                  <div class="line" />
                  <p class="card-description">
                    <span>{{ $t('index.fourth_card.' + data.description) }}</span>
                  </p>
                </div>
              </el-card>
            </router-link>
          </el-col>
        </el-row>
      </div>
    </div>
    <div class="fifth-banner">
      <div v-for="data in fifthCard" :key="data.title" class="fifth-wrapper">
        <img class="fifth-image" :src="getImgUrl(data.image)">
        <div class="fifth-txtCon">
          <p class="fifth-title">{{ $t('index.fifth_card.' + data.title) }}</p>
          <div style="width:60px;height:4px;background:rgba(255,255,255,1);margin-bottom: 30px;" />
          <p style="line-height: 22px">{{ $t('index.fifth_card.' + data.description) }}</p>
          <router-link :to="data.route" class="fifth-more">{{ $t('index.fifth_card.read_more') }}</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex';
import Emotion from '../components/Emotion/index';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
import cookies from 'js-cookie';
export default {
  name: 'Index',
  components: {
    Emotion
  },
  data() {
    return {
      firstCard: [
        {
          image: 'first_card_image_1',
          title: 'first_card_title_1',
          description: 'first_card_description_1',
          route: '/transaction'
        },
        {
          image: 'first_card_image_2',
          title: 'first_card_title_2',
          description: 'first_card_description_2',
          route: '/cornucopia'
        },
        {
          image: 'first_card_image_3',
          title: 'first_card_title_3',
          description: 'first_card_description_3',
          route: '/foreignExchange'
        },
        {
          image: 'first_card_image_4',
          title: 'first_card_title_4',
          description: 'first_card_description_4',
          route: '/insuranceServices'
        },
        {
          image: 'first_card_image_5',
          title: 'first_card_title_5',
          description: 'first_card_description_5',
          route: '/bondServices'
        }
      ],
      secondCard: [
        {
          image: 'second_card_image_1',
          title: 'second_card_title_1',
          description: 'second_card_description_1',
          route: '/product/stock'
        },
        {
          image: 'second_card_image_2',
          title: 'second_card_title_2',
          description: 'second_card_description_2',
          route: '/product/fund'
        },
        {
          image: 'second_card_image_3',
          title: 'second_card_title_3',
          description: 'second_card_description_3',
          route: '/product/foreignEx'
        },
        {
          image: 'second_card_image_4',
          title: 'second_card_title_4',
          description: 'second_card_description_4',
          route: '/product/option'
        },
        {
          image: 'second_card_image_5',
          title: 'second_card_title_5',
          description: 'second_card_description_5',
          route: '/product/futures'
        },
        {
          image: 'second_card_image_6',
          title: 'second_card_title_6',
          description: 'second_card_description_6',
          route: '/product/bond'
        }
      ],
      thirdCard: [
        {
          image: 'third_card_image_1',
          title: 'third_card_title_1',
          description: 'third_card_description_1',
          route: '/financialCommodityService/wmpDisclaimer'
        },
        {
          image: 'third_card_image_2',
          title: 'third_card_title_2',
          description: 'third_card_description_2',
          route: '/financialCommodityService/warrant'
        },
        {
          image: 'third_card_image_3',
          title: 'third_card_title_3',
          description: 'third_card_description_3',
          route: '/financialCommodityService/cbbc'
        }
      ],
      fourthCard: [
        {
          image: 'fourth_card_image_1',
          title: 'fourth_card_title_1',
          description: 'fourth_card_description_1',
          route: '/informationService/antiMoney'
        },
        {
          image: 'fourth_card_image_2',
          title: 'fourth_card_title_2',
          description: 'fourth_card_description_2',
          route: '/informationService/crs'
        },
        {
          image: 'fourth_card_image_3',
          title: 'fourth_card_title_3',
          description: 'fourth_card_description_3',
          route: '/informationService/fatca'
        },
        {
          image: 'fourth_card_image_4',
          title: 'fourth_card_title_4',
          description: 'fourth_card_description_4',
          route: '/informationService/informationDownload'
        }
      ],
      fifthCard: [
        {
          image: 'fifth_card_image_1',
          title: 'fifth_card_title_1',
          description: 'fifth_card_description_1',
          route: '/comprehensiveAccount'
        },
        {
          image: 'fifth_card_image_2',
          title: 'fifth_card_title_2',
          description: 'fifth_card_description_2',
          route: '/offshoreAccount'
        }
      ],
      pageParams: {
        rows: 1,
        page: 1, // 当前页
        totalPage: 0
      },
      noticesIsShow: true,
      newNotice: {},
      tipText: null,
      tooltipShow: false,
      chatDialog: false,
      socket: null,
      content: '',
      emtionIsShow: false,
      lastEditRange: null,
      selection: null,
      stompClient: null,
      user: null,
      users: null,
      langIsShow: false,
      imgWidth: '200px',
      imgSrc: null,
      previewDialog: false,
      closeChatCheckDialog: false,
      contenteditable: true
    };
  },
  computed: {
    ...mapGetters([
    ])
  },
  mounted() {
    const _this = this;
    _this.doResize();
    window.onresize = function() {
      _this.doResize();
    };
    // _this.init();
    _this.getNewNotice();
    document.addEventListener('click', _this.clickHidden);
  },
  // updated() {
  //   const _this = this;
  //   if (_this.$refs.textarea) {
  //     _this.$refs.textarea.addEventListener('click', _this.clickEmtion);
  //   }
  // },
  beforeDestroy() {
    window.onresize = function() {
    };
  },
  methods: {
    getImgUrl(image) {
      return require('@/assets/images/index/' + image + '.png');
    },
    doResize() {
      this.$refs.firstWrapper.style.height = (window.innerWidth * 0.14) + 'px';
      this.$refs.secondWrapper.style.height = (window.innerWidth * 0.13) + 'px';
    },
    // 获得最新一条公告
    getNewNotice() {
      this.$http({
        url: '/common/newsBulletin/data',
        method: 'post',
        params: Object.assign(this.pageParams, { clientType: 'OfficialWebsite', lang: cookies.get('locale') })
      }).then(res => {
        if (res.code === 200) {
          if (res.rows.length === 0) {
            this.noticesIsShow = false;
          }
          this.newNotice = res.rows[0];
          this.newNotice.releaseDate = this.$verify.NowDate4(this.newNotice.releaseDate);
          this.tipText = `【平台公告】${this.newNotice.title}`;
          this.$nextTick(() => {
            if (this.$refs.tooltip.referenceElm.clientWidth < 912) {
              this.tooltipShow = true;
            }
          });
        } else {
          this.$message(res.message);
        }
      }).catch(error => {
        this.$message(error);
      });
    },
    getChatDialog() {
      this.init();
      this.chatDialog = true;
      this.langIsShow = false;
    },
    goTop() {
      document.documentElement.scrollTop = 0;
    },
    sendMessage() {
      const text = this.$refs.textarea.innerHTML;
      var msg = {
        'text': this.$refs.textarea.innerHTML,
        'userName': this.users
      };
      this.stompClient.send('/sendUser', {}, JSON.stringify(msg));
      if (text === '') {
        return;
      }
      const chatContentRef = this.$refs.chatContentRef;
      const time = this.$verify.NowDate3();
      chatContentRef.innerHTML +=
      `<div class="clearfix"><div style="float:right"> <span style="margin-right:8px;">我</span>${time}</div> </div>
      <div class="clearfix">
        <div style="background-color:#5595FF;color:#fff;padding:8px 8px;text-align:rigth;margin-top:8px;margin-left:52px;border-radius:6px;
        float:right">${text} </div>
      </div>
      <br />`;
      chatContentRef.scrollTop = chatContentRef.scrollHeight;
      this.$refs.textarea.innerHTML = '';
      this.content = '';
      // if (text.indexOf('img') !== -1) {
      //   this.content = '';
      // } else {
      //   this.$refs.textarea.innerHTML = '';
      // }
    },
    closeChatDialog() {
      this.closeChatCheckDialog = true;
      // this.$refs.chatContentRef.innerHTML = '';
    },
    chatClose() {
      this.$refs.chatContentRef.innerHTML = '';
      this.disconnect();
    },
    emtionShow() {
      const that = this;
      that.emtionIsShow = !that.emtionIsShow;
      this.$refs.textarea.focus();
    },
    clickHidden(event) {
      if (event.target.parentElement && event.target.parentElement.parentElement &&
      event.target.parentElement.parentElement.parentElement && event.target.parentElement.parentElement.parentElement.parentElement) {
        const targetClassName = event.target.parentElement.parentElement.parentElement.parentElement.className;
        if (targetClassName !== 'emotion' && targetClassName !== 'el-dialog') {
          this.emtionIsShow = false;
        }
        if (event.srcElement.offsetParent && event.srcElement.offsetParent.className === 'el-dialog' && event.target.localName === 'img') {
        // event.target.style.maxWidth = '200px !important';
          this.$refs.textarea.focus();
          this.$refs.previewDialog.innerHTML = event.srcElement.currentSrc;
          this.imgSrc = event.srcElement.currentSrc;
          this.imgWidth = event.srcElement.naturalWidth + 40 + 'px';
        }
      }
    },
    checkLang() {
      this.langIsShow = !this.langIsShow;
    },
    closeLang() {
      this.langIsShow = false;
    },
    // langShow() {
    //   this.langIsShow = true;
    // },
    init() {
      if (typeof (WebSocket) === 'undefined') {
        alert('您的浏览器不支持socket');
      } else {
        this.connection();
        // const that = this;
        // 断开重连机制,尝试发送消息,捕获异常发生时重连
        // this.timer = setInterval(() => {
        //   try {
        //     that.stompClient.send('test');
        //   } catch (err) {
        //     console.log('断线了: ' + err);
        //     that.connection();
        //   }
        // }, 5000);
      }
    },
    connection() {
      // 建立连接对象
      const socket = new SockJS('http://192.168.1.102:9632/web-websocket/webSocketServer');
      // 获取STOMP子协议的客户端对象
      this.stompClient = Stomp.over(socket);
      // console.log(this.stompClient, 'stompClient');
      // const userId = Number(Math.random().toString().substr(3, 13) + Date.now()).toString(36);
      const userId = Number(Math.random().toString().substr(3, 8) + Number(Date.now().toString().substr(3, 8))).toString(36);
      this.user = {
        username: userId,
        password: '1111'
      };
      // 向服务器发起websocket连接
      this.stompClient.connect(this.user,
        () => {
          this.stompClient.subscribe('/user/' + userId + '/queue/getResponse', (response) => { // 订阅服务端提供的某个topic
            if (JSON.parse(response.body) && JSON.parse(response.body).type === 'NOTICE' && JSON.parse(response.body).status === 2) {
              this.users = JSON.parse(response.body).userName;
              this.contenteditable = true;
              const noticeMsg = `<div style="margin-top: 20px;text-align:center;margin-bottom: 10px;">您已成功连接</div>`;
              this.$refs.chatContentRef.innerHTML += noticeMsg;
            } else if (JSON.parse(response.body) && JSON.parse(response.body).type === 'NOTICE' && JSON.parse(response.body).status === 1) {
              const noticeMsg = `<div style="margin-top: 20px;text-align:center;margin-bottom: 10px;">当前连接人数较多，请耐心稍等</div>`;
              this.$refs.chatContentRef.innerHTML += noticeMsg;
              this.contenteditable = false;
            }
            if (JSON.parse(response.body).text) {
              this.$refs.chatContentRef.innerHTML +=
            `<div class="clearfix"><div style="float:left"> <span style="margin-right:8px;">卑微客服在线答疑 ${this.$verify.NowDate3()}</span></div> </div>
              <div class="clearfix">
                <div style="background-color:#FFFFFF;color:black;padding:8px 8px;text-align:rigth;margin-top:8px;border-radius:6px;
                float:left"> ${JSON.parse(response.body).text}</div>
              </div>
              <br />`;
              this.$refs.chatContentRef.scrollTop = this.$refs.chatContentRef.scrollHeight;
            }
            console.log(response, '订阅成功'); // msg.body存放的是服务端发送给我们的信息
          });
        },
        (err) => {
          // 连接发生错误时的处理函数
          const noticeMsg = `<div style="text-align:center;margin-bottom: 10px;">您已断开连接，请重新刷新页面连接...</div>`;
          this.$refs.chatContentRef.innerHTML += noticeMsg;
          console.log('失败', err);
        }
      );
    },
    disconnect() {
      if (this.stompClient) {
        this.stompClient.disconnect();
      }
    },
    handleEmotion(i) {
      this.lastEditRange.deleteContents(); // 删除文档的区域
      var el = document.createElement('div');
      el.innerHTML = this.emotion(i);
      var frag = document.createDocumentFragment(); // 创建一个虚拟的dom
      var node;
      var lastNode;
      while ((node = el.firstChild)) {
        lastNode = frag.appendChild(node);
      }
      this.lastEditRange.insertNode(frag);
      // Preserve the selection
      if (lastNode) {
        this.lastEditRange = this.lastEditRange.cloneRange();
        this.lastEditRange.setStartAfter(lastNode);
        this.lastEditRange.collapse(true);
        this.selection.removeAllRanges();
        this.selection.addRange(this.lastEditRange);
      }
    },
    // 将匹配结果替换表情图片
    emotion(res) {
      const word = res.replace(/\#|\;/gi, '');
      return `<img style="width:20px;" src="https://twemoji.maxcdn.com/v/12.1.4/72x72/${word}.png" align="middle">`;
    },
    focusContent() {
      this.selection = window.getSelection();
      this.lastEditRange = this.selection.getRangeAt(0);
    },
    beforeUpload(val) {
      const size = val.size / 1024 / 1024;
      if (size > 1) {
        this.$message.error('图片大小要小于1M');
        return false;
      }
      console.log(val, 'val');
      const file = val;
      const reader = new FileReader();
      reader.readAsDataURL(file);
      const chatContentRef = this.$refs.chatContentRef;
      const time = this.$verify.NowDate3();
      const stompClient = this.stompClient;
      const users = this.users;
      reader.onload = function() {
        chatContentRef.innerHTML +=
      `<div class="clearfix"><div style="float:right"> <span style="margin-right:8px;">我</span>${time}</div> </div>
      <div class="clearfix">
        <div style="background-color:#5595FF;color:#fff;padding:8px 8px;text-align:rigth;margin-top:8px;margin-left:52px;border-radius:6px;
        float:right"> <img src=${reader.result} style="max-width:100px;"></img> </div>
      </div>
      <br />`;
        const innerHTML =
      // `<div class="clearfix">
      //   <div style="background-color:#fff;color:#fff;padding:8px 8px;text-align:rigth;margin-top:8px;border-radius:6px;
      //   float:right"> <img src=${reader.result} style="max-width:100px;"></img> </div>
      // </div>
      // <br />`;
      ` <img src=${reader.result} style="max-width:100px;"></img>`;
        var msg = {
          'text': innerHTML,
          'userName': users
        };
        stompClient.send('/sendUser', {}, JSON.stringify(msg));
      };
    },
    closeChatCheck() {
      this.chatDialog = false;
      this.closeChatCheckDialog = false;
      this.disconnect();
    }
    // clickEmtion() {
    // this.selection = window.getSelection();
    // this.lastEditRange = this.selection.getRangeAt(0);
    // }
  }
};
</script>

<style>
  .card-wrapper .el-card__body {
    padding: 0;
  }
</style>

<style lang="scss" scoped>
  .app-container {
    .card-wrapper {
      min-height: 200px;
      border: none;
      .card-img {
        width: 100%;
      }
      .card-txt {
        padding: 20px;
        background: #FFFFFF;
        height: 60px;
      }
      .card-title {
        margin-top: 0;
        font-size: 18px;
      }
      .line {
        width:30px;
        height:2px;
        background:rgba(255,62,77,1);
        margin: 20px 0 10px;
      }
      .card-description {
        color: #999999;
        font-size: 14px;
        line-height: 22px;
      }
      .card-more {
        border: 1px solid #999999;
        border-radius: 10px;
        color: #999999;
        font-size: 12px;
        padding: 3px 10px;
        display: inline-block;
        margin-top: 10px;
        letter-spacing: 1px;
      }
      .card-go {
        border: 1px solid #999999;
        color: #999999;
        font-size: 18px;
        padding: 2px;
        float: right;
        margin-top: -4px;
        border-radius: 15px;
      }
    }
    .first-banner {
      height: 430px;
      position: relative;
      background-size: 100%;
      .first-wrapper {
        width: 1200px;
        margin: 0 auto;
        .card-container {
          width: 20%;
        }
        .card-description {
          min-height: 50px;
        }
      }
    }
    .second-banner {
      height: 820px;
      position: relative;
      background: url('../assets/images/index/banner_image_2.png') no-repeat;
      background-size: 100% 820px;
      .second-wrapper {
        width: 1200px;
        margin: 0 auto;
        .card-container {
          width: 33%;
          margin-bottom: 20px;
        }
      }
      .second-title {
        color: #FFFFFF;
        font-size: 24px;
        padding-top: 50px;
        margin-bottom: 30px;
      }
    }
    .third-banner {
      margin-bottom: 30px;
      position: relative;
      .third-wrapper {
        width: 1200px;
        margin: 0 auto;
        .card-container {
          width: 33%;
          margin-bottom: 20px;
        }
      }
      .third-title {
        color: #000000;
        font-size: 24px;
        padding-top: 50px;
        margin-bottom: 20px;
      }
    }
    .fourth-banner {
      position: relative;
      .fourth-wrapper {
        width: 1200px;
        margin: 0 auto;
        .card-container {
          width: 25%;
          margin-bottom: 50px;
        }
      }
      .fourth-title {
        color: #000000;
        font-size: 24px;
        padding-top: 10px;
        margin-bottom: 20px;
      }
    }
    .fifth-banner {
      position: relative;
      .fifth-wrapper {
        float: left;
        width: 50%;
        height: 300px;
        text-align: center;
        color: #FFFFFF;
        position: relative;
        .fifth-txtCon {
          top: 80px;
          text-align: left;
          position: absolute;
          .fifth-title {
            font-size: 24px;
            margin-bottom: 40px;
          }
          .fifth-more {
            border: 1px solid #FFFFFF;
            border-radius: 10px;
            color: #FFFFFF;
            font-size: 12px;
            padding: 3px 10px;
            display: inline-block;
            margin-top: 10px;
            letter-spacing: 1px;
          }
          .fifth-more:hover {
            border: 1px solid #FF4057;
            border-radius: 10px;
            color: #FF4057;
            font-size: 12px;
            padding: 3px 10px;
            display: inline-block;
            margin-top: 10px;
            letter-spacing: 1px;
          }
        }
        .fifth-image {
          width: 100%;
          height: 300px;
        }
      }
      .fifth-wrapper:first-child .fifth-txtCon {
        width: 400px;
        right: 200px;
      }
      .fifth-wrapper:last-child .fifth-txtCon {
        width: 400px;
        left: 200px;
      }
    }
    .notices{
      width: 1200px;
      margin: 0 auto;
      color: #999999;
      .noticesLeft{
        width: 80%;
        float: left;
        margin-top: 20px;
        img{
          vertical-align: text-top;
          margin-right: 8px;
        }
        .newNoticeTitle{
          max-width: 95%;
          display: inline-block;
          overflow: hidden;
          text-overflow: ellipsis;
          white-space: nowrap;
          vertical-align: middle;
          height: 18px;
          line-height: 18px;
        }
      }
      .noticesRight{
        float: right;
        margin-top: 20px;
        a{
          color: #999999;
        }
        .moreNotice{
          margin-left: 8px;
        }
      }
    }
    .clearfix:after{/*伪元素是行内元素 正常浏览器清除浮动方法*/
      content: "";
      display: block;
      height: 0;
      clear:both;
      visibility: hidden;
     }
    .clearfix{
      *zoom: 1;/*ie6清除浮动的方式 *号只有IE6-IE7执行，其他浏览器不执行*/
    }
    .chatIcon{
      position: fixed;
      z-index: 999;
      right: 50px;
      bottom: 326px;
      .langCheck{
        position: fixed;
        right: 90px;
        box-sizing: border-box;
        width: 160px;
        height: 66px;
        border-radius: 4px;
        background-color: #fff;
        padding: 15px 0 15px 12px;
        >div{
          color:#434343;
          font-size: 12px;
          padding-bottom: 13px;
        }
        .lang{
          color:#D7DADF;
          span{
            font-size: 12px;
            &:nth-child(1){
              padding-right: 7px;
            }
            &:nth-child(2){
              padding-left: 7px;
            }
            a{
              color: #FF2222;
            }
          }
        }
      }
      .goTopIcon{
        &:hover{
          content: url('../assets/images/index/icon_index_goTop_2.png');
          cursor: pointer;
        }
      }
      .serviceIcon{
        &:hover{
          content: url('../assets/images/index/icon_index_service_2.png');
          cursor: pointer;
        }
      }
    }
  }
</style>
<style lang="scss">
  .app-container{
    .closeChatCheck{
      .el-dialog{
        position: fixed;
        margin: 0 !important;
        bottom: 164px;
        right: 154px;
        height: 100px;
        .el-dialog__header{
          padding: 0;
          .el-dialog__headerbtn{
            top: 6px;
          }
        }
        .el-dialog__body{
          text-align: center;
          padding: 24px 0;
        }
        .el-dialog__footer{
          padding: 0;
          text-align: center;
          .el-button{
            &:first-child{
              background-color: #FF2222ff;
              border-color: #FF2222;
              :hover{
                background-color: #FF4E4E;
              }
            }
            height: 27px;
            line-height:0;
          }
        }
      }
    }
    .chatDialog{
      .el-dialog{
        // position: relative;
        position: fixed;
        right: 95px;
        bottom: 0px;
        margin: 0;
        .el-dialog__header{
          background-color: rgb(255, 34, 34);
          height: 36px;
          padding: 0;
          span{
            color: #fff;
            margin-left: 44px;
            line-height: 37px;
            font-size: 12px;
          }
           .el-dialog__headerbtn{
            top: 9px;
          }
          .el-dialog__close{
            color: #FFFFFF;
          }
        }
        .el-dialog__body{
          height: 384px;
          padding: 0;
          >img{
            position: absolute;
            left: 12px;
            top: 6px;
          }
          .chatContent{
            background-color: rgb(246, 247, 250);
            height: 254px;
            padding: 20px 12px 0 12px;
            overflow: auto;
          }
          .chating{
            position: relative;
            padding:8px 12px;
            background-color: #fff;
            .emotion{
              position: absolute;
              top: -138px;
              left: 0;
              .emotion-box{
                width: 360px;
                border-color: #eee;
                box-shadow: 0px 0px 7px -1px #ddd;
                background-color: #fff;
              }
            }
            .textarea{
              height: 28px;
              width: 100%;
              padding: 5px 0;
              overflow: auto;
            }
            .upload{
              display: inline-block;
              ul{
                display: none;
              }
            }
            .emtionImg{
              margin-right: 9px;
            }
            textarea{
              resize: none;
              border: none;
              display: block;
              margin-top: 12px;
              width: 100%;
              height: 34px;
              &:focus{
                outline: none;
              }
            }
            .chatBtn{
              text-align:right;
              .el-button{
                padding: 8px 16px;
              }
              span{
                margin-right: 0;
              }
              .chatBtn_1{
                &:hover{
                  background-color: #fff;
                  border-color: #DCDFE6;
                  color:#606266;
                }
              }
              .chatBtn_2{
                background-color: #ff2222;
                color: #fff;
                border-color: #ff2222;
                &:hover{
                  background-color: #ff4e4e;
                  border-color: #ff4e4e;
                }
              }
            }
          }
        }
      }
    }
  }
</style>
