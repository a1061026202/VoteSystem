package com.ccghhll.controller;

import com.ccghhll.pojo.entity.OptionTable;
import com.ccghhll.pojo.entity.TopicTable;
import com.ccghhll.result.R;
import com.ccghhll.service.OptionTableService;
import com.ccghhll.service.StateTableService;
import com.ccghhll.service.TopicTableService;
import com.ccghhll.service.UserTableService;
import com.ccghhll.utils.JwtCryptoUtil;
import com.ccghhll.vo.loginVo;
import com.ccghhll.vo.topicVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/vote")
@Api(value = "投票及登陆接口")
@CrossOrigin
@Slf4j
public class voteController {

    @Autowired
    UserTableService userTableService;
    @Autowired
    TopicTableService topicTableService;
    @Autowired
    StateTableService stateTableService;
    @Autowired
    OptionTableService optionTableService;

    // 登录
    @PostMapping("/login")
    @ApiOperation(value = "登陆接口")
    public R login(
            @RequestBody
            @ApiParam(value = "登陆对象", required = true) loginVo loginvo) {
        Map<String, Object> map = userTableService.login(loginvo);
        return R.ok().data(map);
    }

    // 注册
    @PostMapping("/regist")
    @ApiOperation(value = "注册接口")
    public R regist(
            @RequestBody
            @ApiParam(value = "注册对象", required = true) loginVo loginvo) {
        userTableService.regist(loginvo);
        return R.ok();
    }


    // 查询所有题目
    @GetMapping("/getVoteTopicList")
    @ApiOperation(value = "查询所有题目接口")
    public R getVoteTopicList() {
        List<TopicTable> topicList = topicTableService.getVoteTopicList();
        return R.ok().data("topic", topicList);
    }

    // 根据题目id查询题目选项
    @GetMapping("/getVoteOptionList/{id}")
    @ApiOperation(value = "通过id查询题目选项")
    public R getVoteOptionList(
            @PathVariable
            @ApiParam(value = "题目id", required = true) String id) {
        List optionList = optionTableService.getVoteOptionList(id);
        return R.ok().data("optionList", optionList);
    }

    // 创建投票
    @PostMapping("/createVote")
    @ApiOperation(value = "创建投票接口")
    public R createVote(
            @RequestBody
            @ApiParam(value = "投票对象信息", required = true) topicVo topicVo,
            HttpServletRequest request) {
        String token = request.getHeader("Token");
        //解析token
        boolean checkToken = JwtCryptoUtil.checkToken(token);
        if(checkToken){
            String account = (String) JwtCryptoUtil.getPayload(token).getClaim("account");
            topicTableService.saveTopic(account,topicVo);}

        return R.ok();
    }

    // 投票
    @PutMapping("voteOption/{id}/{topicId}")
    @ApiOperation(value = "投票接口")
    public R voteOption(
            @PathVariable("id")
            @ApiParam(value = "选项id", required = true) Integer id ,
            @PathVariable("topicId") String topicId,
            HttpServletRequest request) {
        String token = request.getHeader("Token");
//解析token
        boolean checkToken = JwtCryptoUtil.checkToken(token);
        if(checkToken){
            String account = (String) JwtCryptoUtil.getPayload(token).getClaim("account");
            optionTableService.voteOption(id,account,topicId);
        }
        return R.ok().data("插入成功topicId是",topicId);
    }

    // 判断用户是否已完成投票
    @GetMapping("isVote/{topicId}")
    @ApiOperation(value = "判断用户是否投票接口")
    public R isVote(
            @PathVariable
            @ApiParam(value = "题目id", required = true) String topicId,
            HttpServletRequest request) {
        String token = request.getHeader("Token");
        //解析token
        boolean checkToken = JwtCryptoUtil.checkToken(token);
        if(checkToken){
            String account = (String) JwtCryptoUtil.getPayload(token).getClaim("account");
            boolean isvote = stateTableService.isVote(account, topicId);
            return R.ok().data("isvote", isvote);
        }
            return R.error();
    }

    // 根据题目ID查询投票结果
    @GetMapping("getVoteResult/{topicId}")
    @ApiOperation(value = "查看投票状况")
    public R voteResult(
            @PathVariable
            @ApiParam(value = "题目id", required = true)String topicId){
        List<OptionTable> optinTableList = optionTableService.getVoteResult(topicId);
        return R.ok().data("optionTableList",optinTableList);
    }
    //删除话题
    @DeleteMapping("deleteTopic/{topicId}")
    @ApiOperation(value = "删除投票")
    public R deleteTopic(
            @PathVariable
            @ApiParam(value = "题目id",required = true)String topicId){
        topicTableService.deleteTopic(topicId);
        return R.ok();
    }
    // 根据用户名查询题目
    @GetMapping("/getTopicByUsername")
    @ApiOperation(value = "根据用户名查询题目")
    public R getTopicByUsername(
            HttpServletRequest request){
        String token = request.getHeader("Token");

        //解析token
        boolean checkToken = JwtCryptoUtil.checkToken(token);
        if(checkToken){
            String account = (String) JwtCryptoUtil.getPayload(token).getClaim("account");
            List<TopicTable> topicList = topicTableService.getTopicByUser(account);
            return R.ok().data("topic",topicList);
            }
        return R.error();
    }
    //根据opsum查询靠热度排名的题目
    @GetMapping("getHotTopic")
    @ApiOperation(value = "查询靠热度排名的题目")
    public R getHotTopic(){
    optionTableService.getMostOption();
        return R.ok();
    }
}
