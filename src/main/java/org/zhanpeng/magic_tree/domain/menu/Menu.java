package org.zhanpeng.magic_tree.domain.menu;

import lombok.Data;
import org.zhanpeng.magic_tree.domain.tree.ITreeNode;

/**
 * 菜单实体
 *
 * @author zhanpeng.jiang@hand-china.com 2023/1/2
 */
@Data
public class Menu implements ITreeNode {
    private Long id;
    private String code;
    private String name;
    private String permissionType;
    private String route;
    private Long parentId;
    private Long tenantId;

    @Override
    public Long id() {
        return id;
    }

    @Override
    public Long parentId() {
        return parentId;
    }
}
