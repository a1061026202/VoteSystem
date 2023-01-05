<template>
  <el-row type="flex" class="row-bg">
    <el-col :span="18"><h1 class="title">ccghhll投票系统</h1></el-col>
    <el-col :span="6" class="col2">
      <div v-if="!$store.getters.getloginflag" class="login1">
        <el-button type="text" size="medium" @click="loginbtn">登陆</el-button>
        <el-button type="text" size="medium" @click="registbtn">注册</el-button>
      </div>
      <div v-else>
        {{ $store.getters.getUsername }}
        <el-button type="text" size="medium" @click="Outbtn">登出</el-button>
      </div>
    </el-col>

    <el-dialog
      title="登陆"
      :visible.sync="loginDialogVisible"
      width="350px"
      center
    >
      <el-form
        ref="LoginformRef"
        :model="Loginform"
        :rules="loginFormRules"
        label-width="80px"
        label-position="left"
      >
        <el-form-item label="账号:" prop="userName">
          <el-input
            v-model.trim="Loginform.userName"
            :maxlength="15"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="密码:" prop="password">
          <el-input
            v-model.trim="Loginform.password"
            :maxlength="15"
            show-password
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="resetLogin">关闭</el-button>
        <el-button type="primary" @click="submitLogin">登陆</el-button>
      </span>
    </el-dialog>

    <el-dialog
      title="注册账户"
      :visible.sync="RegisterDialogVisible"
      width="350px"
      center
      :before-close="closeRegist"
    >
      <el-form
        ref="RegisterformRef"
        :rules="RegistFormRules"
        :model="Registerform"
        label-width="100px"
        label-position="left"
      >
        <el-form-item label="账号:" prop="username">
          <el-input
            v-model.trim="Registerform.username"
            :maxlength="15"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="密码:" prop="password">
          <el-input
            v-model.trim="Registerform.password"
            :maxlength="15"
            show-password
          ></el-input>
        </el-form-item>
        <el-form-item label="确认密码:" prop="validpassword">
          <el-input
            v-model="Registerform.validpassword"
            show-password
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="resetRegist">重置</el-button>
        <el-button type="primary" @click="submitRegist">确认注册</el-button>
      </span>
    </el-dialog>
  </el-row>
</template>

<script>
// eslint-disable-next-line no-unused-vars
import { getToken, setToken, removeToken,setUser } from "@/utils/auth.js";
import loginApi from "@/api/Login.js";

export default {
  name: "HomeHeader",
  data() {
    // 校验策略
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("再次输入密码"));
      } else if (value !== this.Registerform.password) {
        callback(new Error("密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      // 登陆参数
      loginDialogVisible: false,
      Loginform: {
        userName: "",
        password: "",
      },
      // 登陆表单校验
      loginFormRules: {
        // 验证用户名是否合法
        userName: [
          {
            required: true,
            message: "请输入账号",
            trigger: "blur",
          },
          {
            min: 3,
            max: 10,
            message: "长度在 3 到 15 个字符",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur",
          },
        ],
      },

      // 注册参数
      RegisterDialogVisible: false,
      Registerform: {
        userame: "",
        password: "",
        // 校验密码
        validpassword: "",
      },
      // 校验注册
      RegistFormRules: {
        username: [
          {
            required: true,
            message: "请输入账号",
            trigger: "blur",
          },
          {
            min: 3,
            max: 10,
            message: "长度在 3 到 15 个字符",
            trigger: "blur",
          },
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur",
          },
          {
            pattern:
              /^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\W_!@#$%^&*`~()-+=]+$)(?![a-z0-9]+$)(?![a-z\W_!@#$%^&*`~()-+=]+$)(?![0-9\W_!@#$%^&*`~()-+=]+$)[a-zA-Z0-9\W_!@#$%^&*`~()-+=]/,
            message: "密码8~15个字母数字特殊符号组合",
            trigger: "blur",
          },
        ],
        validpassword: [
          {
            required: true,
            validator: validatePass2,
            trigger: "blur",
          },
        ],
      },
    };
  },

  methods: {
    // 执行登陆
    submitLogin() {
      // 调试测试项
      this.$refs["LoginformRef"].validate(async (valid) => {
        if (valid) {
          const data = await loginApi.tologin(this.Loginform);
          // 登陆成功
          if (data.code != -1) {
            this.loginDialogVisible = !this.loginDialogVisible
            setToken(data.data.token);
            setUser(this.Loginform.userName)
            location.reload();
            return ELEMENT.Message.success("登陆成功");
          }
          ELEMENT.Message.error(data.message);
        } else {
          return;
        }
      });
    },

    // 重置登陆并退出表单
    resetLogin() {
      this.$refs["LoginformRef"].resetFields();
      this.loginDialogVisible = !this.loginDialogVisible;
    },
    //重置注册表单
    resetRegist(){
      this.$refs["RegisterformRef"].resetFields();
    },

    // 监听注册表单关闭后回调清空表单数据
    closeRegist(){
      this.$refs["RegisterformRef"].resetFields();
      this.RegisterDialogVisible = !this.RegisterDialogVisible
    },

    // 退出登陆
    Outbtn() {
      removeToken();
      // 刷新页面
      location.reload();
    },
    // 弹出登陆对话框
    loginbtn() {
      this.loginDialogVisible = !this.loginDialogVisible;
    },

    // 弹出注册对话框
    registbtn() {
      this.RegisterDialogVisible = !this.RegisterDialogVisible;
    },

    // 提交注册
    submitRegist() {
      this.$refs["RegisterformRef"].validate(async (valid) => {
        if (valid) {
          const data = await loginApi.toRegister(this.Registerform);
          if (data.code != -1) {
          this.RegisterDialogVisible = !this.RegisterDialogVisible
          ELEMENT.Message.info(data.message);
          }else{
            ELEMENT.Message.error(data.message);
            this.RegisterDialogVisible = !this.RegisterDialogVisible
          }

        } else {
          return;
        }
      });
    },
  },
  mounted() {
    // 加载页面读取cookie是否存在(bug)
    this.$store.dispatch('tologinflag')
    this.$store.dispatch('toUser')

  },
};
</script>

<style scoped lang="less">
.el-row {
  margin: 0px;
  padding: 0%;
  background-color: #f9fafc;

  .el-col {
    max-height: 60px;
    .login1 {
      padding-top: 15px;
      margin-right: 30px;
      text-align: right;
    }
  }
  .title {
    padding: 0%;
    margin: 0%;
    height: 60px;
  }
}

@media screen and (max-width: 500px) {
  .el-dialog__wrapper .el-dialog {
    width: 300px !important;
    .el-dialog__body {
      padding: 10px 20px !important;
      .el-form-item__label {
        width: 68px !important;
      }
      .el-select,
      .el-input {
        width: 180px !important;
      }
    }
  }
}
</style>
