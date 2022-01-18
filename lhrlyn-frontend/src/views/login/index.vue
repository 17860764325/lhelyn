<template>
  <div class="page">
    <!-- <img class="img" src="../../static/1198282.jpg"> -->
    <div class="login">
      <el-form ref="form" class="loginForm" :model="form" :inline="false" size="normal">
        <h1 class="title">芝麻开门</h1>
        <el-form-item>
          <el-input v-model="form.username" placeholder="账号" class="username" />
        </el-form-item>
        <el-form-item>
          <el-input v-model="form.password" placeholder="密码" class="password" />
        </el-form-item>
        <el-form-item>
          <el-input v-model="form.code" placeholder="请输入正确答案" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="begin()">begin</el-button>
          <el-button>centrl</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import { login } from '../../api/user'
export default {
  data() {
    return {
      login,
      form: {
        username: 'admin',
        password: 123456
      }
    }
  },
  methods: {
    async begin() {
      // console.log(this.form.username)
      // console.log(this.form.password)
      // this.user.username = this.form.username
      // this.user.password = this.form.password
      // const response = await login(this.user)
      // console.log(response)
      // if (response.status === 100) {
      //   localStorage.setItem('token', response.data.token) // 缓存用户信息
      //   // 登录成功跳转到主页
      //   this.$router.push({ path: this.redirect || '/' }, () => {})
      // }
      this.loading = true
      this.$store.dispatch('user/login', this.form)
        .then(() => {
          this.$router.push({ path: this.redirect || '/' })
          this.loading = false
        })
        .catch(() => {
          this.loading = false
        })
    }
  }
}
</script>

<style lang="scss" scoped>
.page {

}
.img {
  width: 1700px;
  position: absolute;
  z-index: -1;
}
.login{
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;

}
.loginForm{
  height: 350px;
  width: 400px;
  box-shadow: 0 0 25px #cacac6;
}
.username{

}
.password{
}
.title{
  text-align: center;
  color: wheat
}
</style>
