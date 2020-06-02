/**
 * @ProjectName: jvm
 * @Package: PACKAGE_NAME
 * @ClassName: ArrayListTest
 * @Description: java类作用描述
 * @Author: ricemin
 * @CreateDate: 2020-05-07 23:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-05-07 23:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ArrayListTest {

    /**
     * @method
     * @description 描述一下方法的作用
     * @date: 2020-05-07 23:25
     * @author: ricemin
    No such property: code for class: Script1
     * @return
     */
    public boolean findObject(int[][] array,int dest){
        int row=array.length;
        int cos=array[0].length;
        int i=0;
        int j=cos-1;
        while (i<row&&j>=0){
            if(dest==array[i][j]){
                return true;
            }else if(dest<array[i][j]){
                j--;
            }else {
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] array={{123},{456},{789}};
        boolean flag=new ArrayListTest().findObject(array,3);
        System.out.println("flag"+flag);

    }
}
