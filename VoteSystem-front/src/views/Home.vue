<template>
  <el-container class="con">
  <el-header class="he">
    <HomeHeader/>
  </el-header>
  <el-main class="ma">
    <VoteMain/>
  </el-main>
  <el-footer class="foo">
    <Footer/>
  </el-footer>
</el-container>
</template>

<script>
import Footer from '../components/footer/footer.vue'
import HomeHeader from '../components/header/HomeHeader.vue'
import VoteMain from '../components/main/VoteMain.vue'

export default {
  name: 'Home',
  components: {
    HomeHeader,
    Footer,
    VoteMain

  }
}
</script>

<style scoped>
.con{
  width: 100%;
  height: 100%;
  margin: 0%;
  padding: 0%;
  min-width: 400px;
}
.ma{
  margin: 0%;
  padding: 0%;
  height: 80%;
}
.foo{
  margin: 0%;
  padding: 0%;
}
.he{
  margin: 0%;
  padding: 0%;
}
</style>
