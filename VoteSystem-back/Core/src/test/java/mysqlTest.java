import com.alibaba.druid.pool.DruidDataSource;
import com.ccghhll.VoteSystemApplication;
import com.ccghhll.mapper.UserTableMapper;
import com.ccghhll.pojo.entity.UserTable;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;


@SpringBootTest(classes = VoteSystemApplication.class)
public class mysqlTest {

    @Resource
    private DruidDataSource dataSource;

    @Resource
    private UserTableMapper userTableMapper;


    @Test
    public void test() {
        System.out.println(dataSource);
    }

    @Test
    public void mybatisTest(){
        List<UserTable> allUser = userTableMapper.getAllUser();

        allUser.forEach(item -> System.out.println(item.getUserId()));
    }

    }



