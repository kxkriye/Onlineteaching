import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author djm
 * @create 2021-09-19 21:30
 */
@Data
public class DemoData {
    //设置excel表头名称
    @ExcelProperty(value = "学生编号")
    private Integer sno;
    @ExcelProperty(value = "学生姓名")
    private String sname;
}

