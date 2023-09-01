import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author djm
 * @create 2021-09-19 21:30
 */
public class TestEasyExcel {
    public static void main(String[] args){
        String filename = "D:\\write.xlsx";
//        EasyExcel.write(filename,DemoData.class).sheet("表一").doWrite(getData());
EasyExcel.read(filename,DemoData.class,new ExcelListener()).sheet().doRead();
    }
    private static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy"+i);
            list.add(data);
        }
        return list;
    }
}
