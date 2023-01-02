package org.zhanpeng.magic_tree.domain.tree;

/**
 * Tree Node
 *
 * @author zhanpeng.jiang@hand-china.com 2023/1/2
 */
public interface ITreeNode {
    /**
     * Node Id
     *
     * @return Node Id
     */
    Long id();

    /**
     * Node parentId
     *
     * @return Node parentId
     */
    Long parentId();
}
