package org.zhanpeng.magic_tree.domain.tree.entity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.zhanpeng.magic_tree.domain.tree.IForestNode;
import org.zhanpeng.magic_tree.domain.tree.ITreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Forest Node
 *
 * @author zhanpeng.jiang@hand-china.com 2023/1/2
 */
@Slf4j
public class ForestNode<T extends ITreeNode> implements IForestNode<T> {
    private final T data;
    private List<IForestNode<T>> children;
    private boolean dummy;

    public ForestNode(T data) {
        this.data = data;
    }

    @Override
    public boolean hasChild() {
        return !CollectionUtils.isEmpty(children);
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public List<IForestNode<T>> getChildren() {
        return children;
    }

    @Override
    public void printData() {
        if (dummy()) {
            // dummy node does not print any info
            return;
        }
        log.info("node: {}", this.data);
    }

    @Override
    public boolean dummy() {
        return this.dummy;
    }

    @Override
    public void addChildren(List<IForestNode<T>> passedChildren) {
        if (CollectionUtils.isEmpty(passedChildren)) {
            return;
        }
        if (CollectionUtils.isEmpty(this.children)) {
            this.children = new ArrayList<>();
        }
        this.children.addAll(passedChildren);
    }

    @Override
    public void addChild(IForestNode<T> child) {
        if (CollectionUtils.isEmpty(this.children)) {
            this.children = new ArrayList<>();
        }
        this.children.add(child);
    }

    public void setDummy(boolean dummy) {
        this.dummy = dummy;
    }
}
