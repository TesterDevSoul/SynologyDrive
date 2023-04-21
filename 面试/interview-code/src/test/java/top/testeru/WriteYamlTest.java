package top.testeru;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.commons.util.StringUtils;
import org.slf4j.Logger;
import top.testeru.entity.InterViewEntity;
import top.testeru.entity.InterViewYaml;
import top.testeru.entity.LabelEntity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject interview-code
 * @Description 生成的每一个yaml文件进行格式化
 * @createTime 2023年04月19日 15:58:00
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WriteYamlTest {
    static final Logger logger = getLogger(lookup().lookupClass());

    List<InterViewYaml> endAll = new ArrayList<>();

    @Test
    @Order(1)
    public void delete(){
        //new File("src/test/resources/endYaml", "end1.yaml")
        //删除文件
        File file = new File("src/test/resources/endYaml", "end1.yaml");
        if(file.exists()){
            file.delete();
        }
    }

    @Test
    @Order(22)
    public void yaml() throws IOException {
        String path = "src/test/resources/note";        //要遍历的路径
        File file = new File(path);        //获取其file对象
        File[] fs = file.listFiles();
        for(File f:fs) {
            if (f.isFile()) {
                //System.out.println(f.getName());
                //若是文件，直接操作
                ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                TypeReference<InterViewYaml> typeReference = new TypeReference<>(){};

                // readValue  相当于加工出来最终的结果 是茶叶还是纯奶 还是酸奶
                InterViewYaml data = mapper.readValue(
                        new File(path + "/" + f.getName()), typeReference);

                //System.out.println(data);
                //整体的面试内容
                String subject = data.getSubject();
                String[] split = subject.split("\n");

                for(String a: split){
                    InterViewYaml interViewYaml = new InterViewYaml();
                    //放公司
                    LabelEntity labelEntity = new LabelEntity();
                    logger.info("before a:"+a);
                    if(a.length() >0 ){
                        if(a.contains("* ")){
                            a = a.replace("* ","");
                            if(a.substring(a.length()-1).equals("，")) {
                                a = a.substring(0, a.length() - 1);
                            }
                            if(a.contains("。")){
                                a = a.replace("。","");
                            }
                        }else if(a.substring(a.length()-1).equals("，")){
                            a = a.substring(0,a.length() - 1);

                        }else if(a.contains("：")){
                            a = a.replace("：","");
                        }
                        else if(a.contains("- ")){
                            logger.info("- ");
                            a = a.replace("- ","");
                        }
                        else if(a.contains(" ）")){
                            String[] bits = a.split("）");
                            a = bits[bits.length-1];
                        }else if(a.contains("- ")){
                            a = a.replace("- ","");
                        }
                        else if(a.contains("\\.")){
                            String[] bits = a.split("\\.");
                            a = bits[bits.length-1];
                        }
                    }

                    logger.info("a:"+a);

                    labelEntity.setCompany(data.getLabel().getCompany());
                    interViewYaml.setLabel(labelEntity);
                    if(!a.contains("## ")){
                        interViewYaml.setSubject(a);
                    }
                    if(!a.contains("# ")){
                        interViewYaml.setSubject(a);
                    }
                    if(!a.contains(" ")){
                        interViewYaml.setSubject(a);
                    }

                    if(!(a.length() ==0 | a.equals("") | a == null)){
                        endAll.add(interViewYaml);
                    }

                }

                //System.out.println(endAll);
                logger.info(endAll.toString());
                System.out.println("长度："+endAll.size());
                //YAML文件写入
                ObjectMapper mapper1 = new ObjectMapper(new YAMLFactory());

                try {
                    mapper1.writeValue(new File("src/test/resources/endYaml", "end1.yaml"), endAll);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }



            }

        }
        }

        


}
