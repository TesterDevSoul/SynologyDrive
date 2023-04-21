package top.testeru.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject interview-code
 * @Description 生成yaml文件
 *
 * @createTime 2023年04月19日 15:22:00
 */
@Getter
@Setter
@ToString
public class InterViewYaml {
    //答案
    private String answer;
    //
    private LabelEntity label;
    //面试题问题主干
    private String subject;
    //面试题问题
    private String type;
    //面试题格式选择题，空字符串
    private String options;
    //答案解析，空字符串
    private String introduction;

}
