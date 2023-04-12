<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="{span:12,offset:6}" :lg="{span:10,offset:7}" :xl="{span:10,offset:7}">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>个人信息</span>
          </div>
          <div>
            <ul class="user-info">
              <li>
                <span>登录名</span>
                <div class="user-right">{{ user.userName }}</div></li>
              <li>
                <span>身份证</span>
                <div class="user-right">{{ user.idCard }}</div>
              </li>
              <li>
                <span>性别</span>
                <div class="user-right">
                  <span v-if="user.gender === true">男</span>
                  <span v-else>女</span>
                </div>
              </li>
              <li>
                <span>启用状态</span>
                <div class="user-right">
                  <span v-if="user.active === true">启用</span>
                  <span v-else>禁用</span>
                </div>
              </li>
              <li>
                <span>安全设置</span>
                <div class="user-right">
                  <a @click="dialogChangePwd">修改密码</a>
                </div>
              </li>
            </ul>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-dialog title="用户信息" :visible.sync="dialog" width="450px">
      <el-form ref="changePwdForm" :model="changePwdForm" :rules="rules" label-width="80px" class="demo-ruleForm">
        <el-form-item label="旧密码" prop="oldPwd">
          <el-input v-model="changePwdForm.oldPwd" type="password" />
        </el-form-item>
        <el-form-item label="新密码" prop="newPwd">
          <el-input v-model="changePwdForm.newPwd" type="password" />
        </el-form-item>
        <el-form-item label="确认密码" prop="comfirmPwd">
          <el-input v-model="changePwdForm.comfirmPwd" type="password" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="doChangePwd('changePwdForm')">提交</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'Center',
  data() {
    const validPwd = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.changePwdForm.newPwd) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      user: '',
      dialog: false,
      changePwdForm: {
        oldPwd: '',
        newPwd: '',
        comfirmPwd: ''
      },
      rules: {
        oldPwd: [
          { required: true, message: '请输入旧密码', trigger: 'blur' }
        ],
        newPwd: [
          { required: true, message: '请输入新密码', trigger: 'blur' }
        ],
        comfirmPwd: [
          { required: true, validator: validPwd, trigger: 'blur' }
        ]
      }
    };
  },
  mounted: function() {
    this.getUserInfo();
  },
  methods: {
    dialogChangePwd: function() {
      this.changePwdForm = {
        oldPwd: '',
        newPwd: ''
      };
      this.dialog = true;
    },
    getUserInfo: function() {
      this.$http({
        url: '/system/user/info',
        method: 'get'
      }).then(res => {
        if (res.code === 200) {
          this.user = res.object;
        }
      }).catch(error => {
        this.$message.error(error);
      });
    },
    doChangePwd(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$http({
            url: '/system/user/changePwd',
            method: 'post',
            params: this.changePwdForm
          }).then(res => {
            if (res.code === 200) {
              this.$message.success(res.message);
            } else {
              this.$message.error(res.message || 'Has Error');
            }
          }).catch(error => {
            this.$message.error(error);
          });
          this.dialog = false;
        }
      });
    }
  }
};
</script>

<style lang="scss" scoped>
  .box-card{
    margin-top: 40px;
  }
  .user-info {
    margin-top: 0;
    padding-left: 0;
    list-style: none;
    li{
      border-bottom: 1px solid #F0F3F4;
      padding: 15px 5px;
      font-size: 13px;
    }
    .user-right {
      float: right;
      a{
        color: #317EF3;
      }
    }
  }
</style>
