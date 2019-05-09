package cn.yatus.chapter1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
public class UserDto {
    private Integer id;
    private String name;
    private String mobile;
    private Integer sex;
    @JsonProperty("create_time")
    @CreationTimestamp
    private Date createTime;

    @JsonProperty("update_type")
    @UpdateTimestamp
    private Date updateTime;
}
