import com.ccghhll.utils.JwtCryptoUtil;
import org.junit.jupiter.api.Test;


public class jwtTest {

    @Test
    public void createJWt(){
        String token = JwtCryptoUtil.createToken("123456");
        System.out.println(token);
    }
}
