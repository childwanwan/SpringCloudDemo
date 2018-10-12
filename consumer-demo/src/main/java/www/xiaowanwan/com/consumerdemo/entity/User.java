package www.xiaowanwan.com.consumerdemo.entity;

import lombok.*;

import java.io.Serializable;

/**
 * description:
 * Author:Xiaowanwan
 * Date:2018/10/2-11:38
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    private String id;
    private String username;
    private String password;
}
