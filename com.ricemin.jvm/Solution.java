/**
 * @ProjectName: jvm-test
 * @Package: PACKAGE_NAME
 * @ClassName: Solution
 * @Description: java类作用描述
 * @Author: ricemin
 * @CreateDate: 2020-05-02 21:33
 * @UpdateUser: 更新者
 * @UpdateDate: 2020-05-02 21:33
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class Solution {

    /**
     * @method
     * @description 描述一下方法的作用
     * @date: 2020-05-02 21:38
     * @author: ricemin
    首先，A.length >= 3

    其次，在 0 < i < A.length - 1 条件下，存在 i 使得：

    A[0] < A[1] < ... A[i-1] < A[i]
    A[i] > A[i+1] > ... > A[A.length - 1]

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/find-in-mountain-array
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @return
     */
    public int findInMountainArray(int target, int[] mountainArr) {
        int start=0;
        int end=mountainArr.length-1;
        int top=findMountainTop(mountainArr,start,end);
        if(mountainArr[top]==target){
            return top;
        }else if(mountainArr[top]<target){
            return -1;
        }
            //二分查找，先左后右
        int  index=binarySearchLeft(target,mountainArr,0,top-1);
        if(index==-1){
            index=binarySearchRight(target,mountainArr,top+1,end);
        }
        return index;

    }

   private int findMountainTop(int [] mountainArr,int start,int end){
        int top=-1;
        int middle=(start+end)/2;
        int middleLeft=middle-1;
        int midlleRight=middle+1;
        if(mountainArr[middleLeft]<mountainArr[middle]&&mountainArr[middle]>mountainArr[midlleRight]){
            top= middle;
        }else if(mountainArr[middleLeft]<mountainArr[middle]&&mountainArr[middle]<mountainArr[midlleRight]){
            top=findMountainTop(mountainArr,middle,end);
        }else {
            top=findMountainTop(mountainArr,start,middle);
        }
          return top;
    }


    /**
     * @method  二分查找
     * @description 描述一下方法的作用
     * @date: 2020-05-02 21:48
     * @author: ricemin
    No such property: code for class: Script1
     * @return
     */
    private int binarySearchRight(int target, int[] mountainArr,int start,int end) {
        while (end >= start) {
            int middle = (start + end) / 2;
            if (target == mountainArr[middle]) {
                return middle;
            } else if(target<mountainArr[middle]){
                start = middle + 1;
            }else {
                end=middle-1;
            }
        }
        return -1;
    }

    /**
     * @method  二分查找
     * @description 描述一下方法的作用
     * @date: 2020-05-02 21:42
     * @author: ricemin
    No such property: code for class: Script1
     * @return
     */
    private int binarySearchLeft(int target, int[] mountainArr,int start,int end) {
        while (end >= start) {
            int middle = (start + end) / 2;
            if (target == mountainArr[middle]) {
                return middle;
            } else if(target>mountainArr[middle]){
                start = middle + 1;
            }else {
                end=middle-1;
            }
        }
        return -1;
    }


    public static void main(String args[]){
        int[] mountArr=new int[]{1,2,3,4,5,6,7,8,5,4,3,2,1};
        Solution solution=new Solution();
        int index= solution.findInMountainArray(5,mountArr);
        System.out.println(index);
    }




}
