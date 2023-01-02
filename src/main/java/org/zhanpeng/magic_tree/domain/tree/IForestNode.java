package org.zhanpeng.magic_tree.domain.tree;

import java.util.List;

/**
 * IForestNode
 *
 * @author zhanpeng.jiang@hand-china.com 2023/1/2
 */
public interface IForestNode<T extends ITreeNode> {

    /**
     * whether dummy node or not
     *
     * @return true: current node is a dummy node
     */
    boolean dummy();

    /**
     * addAll passedChildren passed into your passedChildren
     *
     * @param passedChildren passedChildren
     */
    void addChildren(List<IForestNode<T>> passedChildren);

    /**
     * add child into your children
     *
     * @param child child
     */
    void addChild(IForestNode<T> child);

    /**
     * get node data
     *
     * @return node data
     */
    T getData();

    /**
     * whether hasChild or not
     *
     * @return true: it has children
     */
    boolean hasChild();

    /**
     * get children
     *
     * @return its children
     */
    List<IForestNode<T>> getChildren();

    /**
     * print data
     */
    void printData();
}
