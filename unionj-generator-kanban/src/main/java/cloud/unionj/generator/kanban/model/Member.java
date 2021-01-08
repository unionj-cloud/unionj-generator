package cloud.unionj.generator.kanban.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @version v0.1 cloud.unionj.generator
 * @author created by Johnny Ting
 * description: description
 * date: 2021-01-05 11:54
 **/
@Data
@AllArgsConstructor
public class Member {
    private String userId;
    private int role;
}
