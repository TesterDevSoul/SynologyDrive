package top.testeru;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.opencsv.CSVReader;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import top.testeru.entity.InterViewEntity;
import top.testeru.entity.InterViewYaml;
import top.testeru.entity.LabelEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject interview-code
 * @Description csv导出的文件读取
 * 每一个issue切割成一个yaml文件
 * @createTime 2023年04月19日 14:15:00
 */
public class ReadCSVTest {
    List<InterViewEntity> all = new ArrayList<>();

    // 示例：从CSV文件中读取数据
    @Test
    public void all() throws Exception {
        String filePath = "src/test/resources/issues.csv";
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String[] nextLine;

        // 获取第7列 主题
        int columnToGet = 7;
        int columnToGet47 = 47;
        int columnToGet48 = 48;

        while ((nextLine = reader.readNext()) != null) {
            String theme = nextLine[columnToGet];
            String desc = nextLine[columnToGet47];
            String note = nextLine[columnToGet48];

            //放入面试实体类中
            InterViewEntity interViewEntity = new InterViewEntity();
            interViewEntity.setTheme(theme);
            interViewEntity.setDesc(desc);
            interViewEntity.setNote(note);
            //放入list
            all.add(interViewEntity);
        }
        reader.close();

        //while ((nextLine = reader.readNext()) != null) {
        //    String a = "";
        //    for (String value : nextLine) {
        //        System.out.print(value + " ");
        //        a += value;
        //    }
        //    a += "\n";
        //    all.add(a);
        //
        //
        //    System.out.println("-------");
        //}

        reader.close();
        System.out.println(all);
        List<InterViewYaml> interViewYamls = new ArrayList<>();
        //放入yaml文件对象
        all.forEach(interViewEntity -> {
            InterViewYaml interViewYaml = new InterViewYaml();
            LabelEntity labelEntity = new LabelEntity();
            String interview = interViewEntity.getNote();
            String view = interViewEntity.getDesc();
            if(view.length()!=0){
                interViewYaml.setSubject(view);
            }else if(interview.length() != 0 ){
                interViewYaml.setSubject(interview);
            }
            //公司
            labelEntity.setCompany(interViewEntity.getTheme());
            interViewYaml.setLabel(labelEntity);
            //每一个interViewYaml对象写入一个yaml文件
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

            try {
                mapper.writeValue(new File("src/test/resources/note",String.valueOf(System.currentTimeMillis()) + ".yaml"), interViewYaml);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            interViewYamls.add(interViewYaml);
        });

        System.out.println("=======================");

        System.out.println(interViewYamls);
    }



}
