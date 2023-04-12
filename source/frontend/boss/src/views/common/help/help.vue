<template>
  <div class="help">
    <el-row class="helpTitle">
      <el-col class="el-icon-user-solid" :span="18">目前等待人数：{{ num }}</el-col>
    </el-row>
    <div class="chatAll">
      <el-container>
        <el-aside width="200px">
          <el-header class="el-icon-user-solid">等待咨询人数：10</el-header>
          <el-main>
            <el-row v-for="(item,index) in data" :key="index" class="messageNum">
              <a ref="message" href="javascript:void(0)" :class="{changeBgc:isShow[index]}" @mouseleave="hidderIcon($event)" @mouseenter="showCloseIcon($event)" @click="getOnline($event,index,item)">{{ item.id }}
                <!-- <i v-if="closeIcon" class="el-icon-error" @click="deleteChat($event)"></i> -->
                <i :class="{'el-icon-error':iconIsShow[item.id]}" @click="deleteChat($event)"></i>
              </a>
              <span v-if="item.noticeNum" class="noticeNum">{{ item.noticeNum }}</span>
            <!-- {{ item.id }} -->
            </el-row>
          </el-main>
        </el-aside>
        <el-main class="main">
          <el-header>
            <el-row>
              <!-- <span v-for="(item,index) in tagData" :key="index">
                <el-tag v-if="item" :disable-transitions="false" closable :class="{ message:true,changeBgc:isShow[index] }" @close="handleClose(item)">
                  {{ item }}
                </el-tag>
              </span> -->
              <span v-if="id" :disable-transitions="false" closable @close="handleClose(item)">
                {{ id }}
              </span>
            </el-row>
          </el-header>
          <el-main>
            <div ref="chatContentRef" :contenteditable="false" class="chatContent clearfix" />
            <div class="chating">
              <a href="javascript:void(0)" class="emtionImg" :contenteditable="false" @click="emtionShow()"><img src="@/assets/images/icon_index_service_emtion.png" alt=""></a>
              <emotion v-if="emtionIsShow" class="emotion" :height="138" @emotion="handleEmotion" />
              <el-upload
                class="upload"
                action="https://jsonplaceholder.typicode.com/posts/"
                auto-upload
                multiple
                :before-upload="beforeUpload"
              >
                <img src="@/assets/images/icon_index_service_img.png" alt="">
              </el-upload>
              <div
                ref="textarea"
                class="textarea"
                :contenteditable="true"
                autofocus="autofocus"
                @keyup.enter="sendMessage()"
                @keydown.enter.prevent
                @blur="focusContent"
                v-html="content"
              />
              <div class="chatBtn">
                <el-button class="chatBtn_1" @click="closeChat()">结束会话</el-button>
                <el-button class="chatBtn_2" @click="sendMessage()">发送</el-button>
              </div>
            </div>
          <!-- {{ message.content }} -->
          </el-main>
        </el-main>
      </el-container>
      <div class="problem">
        请输入要收藏的问题
        <div ref="problem" contenteditable="true" placeholder="请输入要收藏的问题"></div>
        <el-button type="success" @click="handleProblem()">提交</el-button>
      </div>
    </div>
    <!-- 图片弹框 -->
    <el-dialog
      ref="previewDialog"
      :visible.sync="previewDialog"
      :width="imgWidth"
    >
      <img :src="imgSrc" alt="">
    </el-dialog>
  </div>
</template>
<script>
import Emotion from '../../../components/Emotion/index';
import SockJS from 'sockjs-client';
import Stomp from 'stompjs';
// import { Session } from 'inspector';
export default {
  components: {
    Emotion
  },
  data() {
    return {
      num: 20,
      data: [
        // { id: '1111111111111', content: '内容1' }
      ],
      tagData: [],
      isShow: [],
      iconIsShow: [],
      message: {},
      editorOption: { // 富文本配置
        modules: {
          toolbar: [
            ['bold', 'italic', 'underline', 'link'], // toggled buttons
            [{ header: 1 }, { header: 2 }], // 值字体大小
            [{ color: [] }],
            ['blockquote', 'code-block', 'image']
          ]
        }
      },
      content: null,
      emtionIsShow: false,
      users: null,
      user: null,
      previewDialog: false,
      imgWidth: '200px',
      imgSrc: null,
      idList: [],
      responseMsg: [],
      chatHtml: '',
      currentContent: '',
      id: '',
      noticeNum: '12', // 提醒消息条数
      // closeIcon: [],
      problem: '',
      index: 0
      // noticeNumShow: false
    };
  },
  created() {
    this.init();
  },
  mounted() {
    document.addEventListener('click', this.clickHidden);
  },
  methods: {
    getOnline(document, index, val) {
      // this.noticeNumShow = false;
      var currentIndex = (this.data).findIndex((item) => item.id === val.id);
      this.data[currentIndex].noticeNum = 0;
      this.$refs.chatContentRef.innerHTML = val.text;
      this.id = val.id;
      this.message = val;
      // if (document) {
      //   var id = document.target;
      // }
      if (this.isShow.length > 0) {
        for (let i = 0; i < this.isShow.length > 0; i++) {
          this.isShow[i] = false;
        }
      } else {
        this.isShow[index] = true;
      }
      this.isShow[index] = true;
      // console.log(this.isShow, 'isShow');
      this.$forceUpdate();
      // if (this.tagData.length > 0) {
      //   if (this.tagData.indexOf(id.innerText) === -1) {
      //     this.tagData[index] = id.innerText;
      //   }
      // } else {
      //   this.tagData[index] = id.innerText;
      // }
    },
    handleClose(tag) {
      this.tagData.splice(this.tagData.indexOf(tag), 1);
    },
    problemCollection() {
      this.problemCollectionDialog = true;
    },
    init() {
      if (typeof (WebSocket) === 'undefined') {
        alert('您的浏览器不支持socket');
      } else {
        this.connection();
      }
    },
    connection() {
      // 建立连接对象
      const socket = new SockJS('http://192.168.1.102:9632/web-websocket/webSocketServer');
      // 获取STOMP子协议的客户端对象
      this.stompClient = Stomp.over(socket);
      // const userId = Number(Math.random().toString().substr(3, 13) + Date.now()).toString(36);
      this.user = {
        username: 'admin',
        password: '123456'
      };
      // 向服务器发起websocket连接
      this.stompClient.connect(this.user,
        () => {
          this.stompClient.subscribe('/user/' + this.user.username + '/queue/getResponse', (response) => { // 订阅服务端提供的某个topic
            console.log(response.body, 'msg广播成功'); // msg.body存放的是服务端发送给我们的信息
            this.responseMsg = response.body;
            if (JSON.parse(response.body) && JSON.parse(response.body).userName && JSON.parse(response.body).text && JSON.parse(response.body).status !== 0) {
              this.users = JSON.parse(response.body).userName; // 拿到当前给客服的客户id
              // const data = JSON.parse(JSON.stringify(this.data));
              this.data.map(item => {
                if (this.idList.indexOf(item.id) === -1) { this.idList.push(item.id); } // 将不重复的用户名存到idList数组中
              });
              if (this.data.length > 0) { // 至少有一个聊天窗口时
                if (this.idList.indexOf(this.users) === -1) { // 判断用户名是否已经存在
                  // 是新用户名时
                  this.getMessage();// 拿到新用户输入的数据
                  this.data.push({ id: this.users, text: this.currentContent, noticeNum: 0 });// 将用户名和内容存入新数组中
                } else {
                  // 不是新用户，还是之前已经发起过聊天的用户
                  this.getMessage(); // 拿到新用户输入的数据
                  this.data.map((item, index) => {
                    if (item.id === this.users) {
                      this.data[index].text += this.currentContent;// 将id相同的聊天记录添加到一块
                    }
                    if (item.id === this.id && item.id === this.users) { // 判断当前选中聊天客户是否是此刻正在找你聊天的客户
                      this.$refs.chatContentRef.innerHTML += this.currentContent; // 直接累加返回的数据即可
                    }
                    return item;
                  });
                }
              } else {
                // 第一个用户
                this.getMessage();
                this.data.push({ id: this.users, text: this.currentContent, noticeNum: 0 });
              }
              // 处理未知消息展示
              if (this.users !== this.id) { // 排除不是当前打开的用户
                var currentIndex = (this.data).findIndex((item) => item.id === this.users); // 拿到对应的下标
                this.data[currentIndex].noticeNum += 1; // 把未读消息值进行累加
              }
            } else if (JSON.parse(response.body) && JSON.parse(response.body).userName && JSON.parse(response.body).status === 0) {
              this.users = JSON.parse(response.body).userName;
              const messageTip = `<div style="text-align: center;color:red;">客户已下线</div>`;
              if (this.users !== this.id) {
                var index = (this.data).findIndex((item) => item.id === this.users);
                if (this.data[index].text) {
                  this.data[index].text += messageTip;
                }
              } else {
                index = (this.data).findIndex((item) => item.id === this.users);
                this.$refs.chatContentRef.innerHTML += messageTip;
                if (this.data[index].text) {
                  this.data[index].text += messageTip;
                }
              }
            }
          });
        },
        (err) => {
        // 连接发生错误时的处理函数
          this.$message(err);
        }
      );
    },
    getMessage() {
      if (JSON.parse(this.responseMsg) && JSON.parse(this.responseMsg).text) {
        this.currentContent =
            `<div class="clearfix"><div style="float:left"> <span style="margin-right:8px;">用户ID：${JSON.parse(this.responseMsg).userName} ${this.$verify.NowDate3()}</span></div> </div>
              <div class="clearfix">
                <div style="background-color:#FFFFFF;color:black;padding:8px 8px;text-align:rigth;margin-top:8px;border-radius:6px;word-break: break-all;max-width: 500px;
                float:left"> ${JSON.parse(this.responseMsg).text}</div>
              </div>
              <br />`;
        if (this.$refs.chatContentRef) {
          this.$refs.chatContentRef.scrollTop = this.$refs.chatContentRef.scrollHeight;
        }
      }
      if (this.$refs.chatContentRef.innerHTML) {
        this.chatHtml = this.$refs.chatContentRef.innerHTML;
      }
    },
    // 断开连接
    closeChat() {
      if (this.stompClient) {
        this.stompClient.disconnect();
        this.$message.error('您已断开连接');
      }
    },
    sendMessage() {
      const chatContentRef = this.$refs.chatContentRef;
      const text = this.$refs.textarea.innerHTML;
      const time = this.$verify.NowDate3();
      const innerHTML =
       `<div class="clearfix"><div style="float:right"> <span style="margin-right:8px;">我</span>${time}</div> </div>
      <div class="clearfix">
        <div style="background-color:#5595FF;color:#fff;padding:8px 8px;text-align:rigth;margin-top:8px;margin-left:52px;border-radius:6px;
        float:right">${text} </div>
      </div>
      <br />`;
      this.data.map((item, index) => { // 将id相同的聊天记录添加到一块
        if (item.id === this.id) {
          this.data[index].text += innerHTML;
          this.$refs.chatContentRef.innerHTML = this.data[index].text;
        }
        return item;
      });
      // this.send();
      var msg = {
        'text': this.$refs.textarea.innerHTML,
        'userName': this.id
      };
      this.stompClient.send('/sendUser', {}, JSON.stringify(msg));
      if (text === '') {
        return;
      }
      // const time = this.$verify.NowDate3();
      // chatContentRef.innerHTML =
      // `<div class="clearfix"><div style="float:right"> <span style="margin-right:8px;">我</span>${time}</div> </div>
      // <div class="clearfix">
      //   <div style="background-color:#5595FF;color:#fff;padding:8px 8px;text-align:rigth;margin-top:8px;margin-left:52px;border-radius:6px;
      //   float:right">${text} </div>
      // </div>
      // <br />`;
      chatContentRef.scrollTop = chatContentRef.scrollHeight;
      this.$refs.textarea.innerHTML = '';
      this.content = '';
    },
    clickHidden(event) {
      const targetClassName = event.target.parentElement.parentElement.parentElement.parentElement.className;
      if (targetClassName !== 'emotion' && targetClassName !== 'el-main main') {
        this.emtionIsShow = false;
      }
      if (event.target.localName === 'img' && event.target.clientHeight !== 14 && event.target.clientHeight !== 16 && event.target.clientHeight !== 20) {
        this.previewDialog = true;
        this.imgSrc = event.srcElement.currentSrc;
        this.imgWidth = event.srcElement.naturalWidth + 40 + 'px';
      }
    },
    emtionShow() {
      const that = this;
      that.emtionIsShow = !that.emtionIsShow;
      this.$refs.textarea.focus();
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
    emotion(res) {
      const word = res.replace(/\#|\;/gi, '');
      return `<img style="width:20px;" src="https://twemoji.maxcdn.com/v/12.1.4/72x72/${word}.png" align="middle">`;
    },
    showCloseIcon(val) {
      const id = val.target.innerText.replace(/\s*/g, '');
      this.iconIsShow[id] = true;
      this.$forceUpdate();
    },
    hidderIcon(val) {
      const id = val.target.innerText.replace(/\s*/g, '');
      this.iconIsShow[id] = false;
      this.$forceUpdate();
    },
    focusContent() {
      this.selection = window.getSelection();
      this.lastEditRange = this.selection.getRangeAt(0);
    },
    beforeUpload(val) {
      const file = val;
      const reader = new FileReader();
      reader.readAsDataURL(file);
      // console.log('wenjianshangchaunqian', reader);
      // const chatContentRef = this.$refs.chatContentRef;
      const time = this.$verify.NowDate3();
      const stompClient = this.stompClient;
      reader.onload = () => {
        const innerHTMLChat =
      `<div class="clearfix"><div style="float:right"> <span style="margin-right:8px;">我</span>${time}</div> </div>
      <div class="clearfix">
        <div style="background-color:#5595FF;color:#fff;padding:8px 8px;text-align:rigth;margin-top:8px;margin-left:52px;border-radius:6px;
        float:right"> <img src=${reader.result} style="max-width:100px;"></img> </div>
      </div>
      <br />`;
        this.data.map((item, index) => { // 将id相同的聊天记录添加到一块
          if (item.id === this.id) {
            this.data[index].text += innerHTMLChat;
            this.$refs.chatContentRef.innerHTML = this.data[index].text;
          }
          return item;
        });
        const innerHTML =
      ` <img src=${reader.result} style="max-width:100px;"></img>`;
        var msg = {
          'text': innerHTML,
          'userName': this.id
        };
        stompClient.send('/sendUser', {}, JSON.stringify(msg));
      };
    },
    handleProblem() {
      const problem = this.$refs.problem.innerText;
      this.$http({
        url: '/common/feedback/save',
        method: 'post',
        params: { content: problem, status: 0, lang: 'zh_CN' }
      })
        .then(res => {
          if (res.code === 200) {
            this.$refs.problem.innerHTML = '';
            this.$message({
              message: '问题收藏成功',
              type: 'success'
            });
          } else {
            this.$message(res.message);
          }
        })
        .catch(error => {
          this.$message(error);
        });
    },
    deleteChat(e) {
      e.stopPropagation();
      const deleteID = e.target.parentElement.innerText.replace(/\s*/g, '');
      this.iconIsShow[deleteID] = false;
      var currentIndex = (this.data).findIndex((item) => item.id === deleteID);
      this.data.splice(currentIndex, 1);
      this.idList.splice(currentIndex, 1);
      this.$refs.chatContentRef.innerHTML = '';
      this.id = '';
    }
  }
};
</script>
<style lang="scss">
@import "help.scss";
</style>
