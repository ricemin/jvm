/**
 * @ProjectName: jvm
 * @Package: PACKAGE_NAME
 * @ClassName: SingleTest
 * @Description: java类作用描述
 * @Author: ricemin
 * @CreateDate: 2020-05-07 23:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-05-07 23:04
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SingleTest {

    private static volatile SingleTest singleTest;

    private SingleTest() {
    }

    public static SingleTest getInstance(){
        if(singleTest==null){
            synchronized (singleTest){
                if(singleTest==null){
                    singleTest=new SingleTest();
                }
            }
        }

        return singleTest;
    }
}
