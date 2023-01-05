<template>
  <el-row>
    <h1>请选择功能</h1>
    <el-col :span="12" class="col1">
      <el-button type="primary" round @click="toVote">我要投票</el-button>
    </el-col>
    <el-col :span="12" class="col2">
      <el-button type="success" round @click="toCreateVote">创建投票</el-button>
    </el-col>
    <el-dialog
      title="创建投票"
      :visible.sync="createVotedialogVisible"
      width="400px"
    >
      <el-form ref="CreateformRef" :model="Createform" label-width="80px" :rules="CreateformRules">
        <el-form-item label="投票主题" prop="title">
          <el-input v-model.trim="Createform.title"></el-input>
        </el-form-item>
        <el-form-item label="选项" prop="options">
          <!-- <el-input v-for="(item,index) in Createform.options" :key="index" :v-model="item"></el-input> -->
          <div v-for="(item, index) in Createform.options" :key="index" class="itemdiv">
              <el-input v-model.trim="item.msg" style="width: 80%"></el-input>
              <el-button
                type="danger"
                icon="el-icon-delete"
                circle
                style="margin-left: 2px"
                @click="deleteItems(Createform, item, index)"
                size="mini"
              ></el-button>
          </div>
        </el-form-item>
        <el-form-item>
          <el-button
            type="text"
            @click="addItem(Createform)"
            style="margin-left: 40%"
            ><i class="el-icon-circle-plus"></i>新增选项</el-button
          >
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="createVoteClose">取 消</el-button>
        <el-button type="primary" @click="subadd"
          >新增</el-button
        >
      </span>
    </el-dialog>
  </el-row>
</template>
<script>
import { getToken } from "@/utils/auth.js";
import topicApi from '@/api/Topic.js'
export default {
  data() {
    return {
      createVotedialogVisible: false,
      // 创建投票题目
      Createform: {
        title: "",
        options: [
          {
            msg: "",
          },
          {
            msg: "",
          },
          {
            msg: "",
          }
        ],
      },
      CreateformRules:{
        // 验证题目是否为空
        title: [
          {
            required: true,
            message: "请输入题目",
            trigger: "blur",
          }
          ],
      }
    };
  },
  methods: {
    // 跳转投票列表
    toVote() {
      this.$router.push({ name: "Votelist" });
    },
    // 打开创建投票窗口
    toCreateVote() {
      if (getToken() == undefined) {
        return ELEMENT.Message.warning("注意!创建前请先登陆账户");
      }
      this.createVotedialogVisible = !this.createVotedialogVisible;
    },
    // 删除选项
    deleteItems(items, item, index) {
      this.index = items.options.indexOf(item);
      if (index !== 0) {
        items.options.splice(index, 1);
      }
      // console.log(items);
      // console.log(item);
      // console.log(index);
    },
    // 新增选项
    addItem(items) {
      items.options.push({
        msg: "",
      });
    },
    // 提交投票
    subadd(){
      this.$refs['CreateformRef'].validate(async (valid) => {
        if(valid){
          const data = await topicApi.addTopic(this.Createform)
          if(data.code == 1){
            this.createVotedialogVisible = !this.createVotedialogVisible
            return ELEMENT.Message.success("创建成功")
          }else{
            return ELEMENT.Message.error("创建失败")
          }
        }

      })
    },
    // 关闭创建表单
    createVoteClose(){
      // 清除表单数据
      this.$refs['CreateformRef'].resetFields()
      // 恢复初始化
      this.Createform.options = [
          {
            msg: "",
          },
          {
            msg: "",
          },
          {
            msg: "",
          }
        ]
        this.createVotedialogVisible = !this.createVotedialogVisible
    }

  },
};
</script>

<style scoped>
.OptionV {
  height: 100%;
  width: 100%;
}
.el-row {
  margin-top: 200px;
  max-width: 100%;
}
.row-bg {
  background-color: #f9fafc;
}
.itemdiv{
  margin: 5px;
}
</style>
