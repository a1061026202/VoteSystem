<!-- eslint-disable vue/no-unused-vars -->
<template>
  <div>
    <div>投票列表</div>
    <el-row class="row1" type="flex">
      <el-col v-for="item in topic" :key="item.topicId" :span="4">
        <el-card :body-style="{ padding: '1px', margin: '1px' }">
          <img src="https://api.dujin.org/pic/fengjing" class="image" />
          <div style="padding: 0px">
            <span class="topsize">{{ item.topicName }}</span>
            <div class="bottom clearfix">
              <el-button type="text" class="button" @click="toVote(item)"
                >投票</el-button
              >
              <el-button
                type="text"
                class="button2"
                @click="getVoteResult(item)"
                >查看投票结果</el-button
              >
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-dialog
      :visible.sync="VoteDialogVisible"
      width="300px"
      center
      :title="temptopicName"
    >
      <!-- index + item.optionDec -->
      <div v-for="(item, index) in options" :key="item.optionId" class="optdiv">
        <el-radio v-model="radio" :label="item.optionId" class="radioSty">{{
          getOption(index) + ". " + item.optionDec
        }}</el-radio>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="VoteDialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="VoteSubmit">确 定</el-button>
      </span>
    </el-dialog>

    <el-dialog
      :visible.sync="VoteResultVisible"
      width="400px"
      center
      :title="temptopicName"
    >
      <!-- index + item.optionDec -->
      <el-table
        :data="sum"
        style="width: 100%"
        :row-class-name="tableRowClassName"
      >
        <el-table-column prop="optionDec" label="选项" width="170">
        </el-table-column>
        <el-table-column prop="optionSum" label="投票人数" width="170">
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="VoteResultVisible = false">关 闭</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import topicApi from "@/api/Topic.js";
export default {
  data() {
    return {
      // 投票对话框
      VoteDialogVisible: false,
      //投票结果
      VoteResultVisible: false,
      // 临时标题中间键
      temptopicName: "",
      topic: [],
      // 选项数据
      options: [],
      // 投票选项对应的id
      radio: "",
      sum:[]
    };
  },
  methods: {
    tableRowClassName({rowIndex}) {
        if (rowIndex === 1) {
          return 'warning-row';
        } else if (rowIndex === 3) {
          return 'success-row';
        }
        return '';
      },
    // 点击投票
    async toVote(topic) {
      // 调试打印topic对象
      console.log(topic);
      // 设置对话框标题
      this.temptopicName = topic.topicName;
      // 弹出对话框
      this.VoteDialogVisible = true;

      // 获取数据
      const data = await topicApi.getoptions(topic.topicId);
      this.options = data.data.optionList;
    },

     //获取投票结果
    async getVoteResult(topic){
      this.VoteResultVisible=true;
      this.show=true;
      const data = await topicApi.getVoteResult(topic.topicId);
      if (data.code != 1) {
        return this.$message.error("获取数据失败");
      }
      this.sum = data.data.optionTableList
    },


    // 获取所有标题
    async getTitleList() {
      const data = await topicApi.getTopic();
      if (data.code != 1) {
        return ELEMENT.Message.error("获取数据失败");
      }
      this.topic = data.data.topic;
      ELEMENT.Message.success("获取数据成功");
    },

    // 确认投票
    async VoteSubmit() {
      // 打印投票对应id
      //console.log(this.radio)
      const data = await topicApi.toVoteById(this.radio);
      if (data.code == 1) {
        ELEMENT.Message.success("投票成功");
      } else {
        ELEMENT.Message.error("投票失败");
      }
      this.VoteDialogVisible = !this.VoteDialogVisible;
    },
    // 遍历获取ABCD选项
    getOption(index) {
      const arr = [
        "A",
        "B",
        "C",
        "D",
        "E",
        "F",
        "G",
        "H",
        "I",
        "J",
        "K",
        "L",
        "M",
        "N",
        "O",
        "P",
        "Q",
        "R",
        "S",
        "T",
        "U",
        "V",
        "W",
        "X",
        "Y",
        "Z",
      ];
      return arr[index];
    },
  },

  // 加载页面后执行
  mounted() {
    this.getTitleList();
  },
};
</script>

<style scope lang="less">
.topsize {
  font-size: 5px;
}

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.button2 {
  padding: 0;
  float: left;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}
.row1 {
  flex-wrap: wrap;
}

.optdiv {
  margin: 3px;
  .radioSty {
    font-size: 20px;
  }
}
.el-table .warning-row {
    background: oldlace;
  }

  .el-table .success-row {
    background: #f0f9eb;
  }
</style>
