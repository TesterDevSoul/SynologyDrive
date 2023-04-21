package top.testeru.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Prpject interview-code
 * @Description 面试题格式
 * @createTime 2023年04月19日 14:48:00
 */
@Getter
@Setter
@ToString
public class InterViewEntity {
    //主题
    private String theme;
    //描述
    private String desc;
    //最近批注
    private String note;


}
