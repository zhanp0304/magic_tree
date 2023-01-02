package org.zhanpeng.magic_tree.domain.tree.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.zhanpeng.magic_tree.domain.tree.IForestNode;
import org.zhanpeng.magic_tree.domain.tree.ITreeNode;
import org.zhanpeng.magic_tree.domain.tree.entity.ForestNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ForestUtil
 *
 * @author zhanpeng.jiang@hand-china.com 2023/1/2
 */
@Slf4j
public class ForestUtil {
    private ForestUtil() {
    }

    /**
     * level-order traverse
     *
     * @param forestNode root node of forest
     */
    public static <T extends ITreeNode> void levelOrderTraverse(IForestNode<T> forestNode) {
        if (forestNode == null) {
            return;
        }
        // 1. pop
        // 2. print
        // 3. push queue
        // 4. loop
        LinkedList<IForestNode<T>> queue = new LinkedList<>();
        queue.add(forestNode);
        while (!queue.isEmpty()) {
            IForestNode<T> node = queue.pop();
            Assert.notNull(node, "node cannot be null");
            node.printData();
            if (node.hasChild()) {
                queue.addAll(node.getChildren());
            }
        }
    }

    /**
     * post-order traverse
     *
     * @param forestNode root node of forest
     * @param <T>        ITreeNode
     */
    public static <T extends ITreeNode> void postOrderTraverse(IForestNode<T> forestNode) {
        if (forestNode == null) {
            return;
        }
        postOrderTraverseInner(forestNode);
        forestNode.printData();
    }

    /**
     * 根据树型存储数据，构建出Forest，并返回Forest Root Node List
     *
     * @param dataList dataList
     * @param <T>      ITreeNode
     * @return Forest Root Node List
     */
    public static <T extends ITreeNode> List<IForestNode<T>> buildForest(List<T> dataList) {
        if (CollectionUtils.isEmpty(dataList)) {
            return Collections.emptyList();
        }
        List<IForestNode<T>> rootNodeList = new ArrayList<>();
        Map<Long, ForestNode<T>> forestMap = dataList.stream().collect(Collectors.toMap(T::id, ForestNode::new));
        for (T data : dataList) {
            ForestNode<T> currentNode = forestMap.get(data.id());
            Assert.notNull(currentNode, "currentNode cannot be null");
            if (data.parentId() != null) {
                ForestNode<T> parentNode = forestMap.get(data.parentId());
                if (parentNode == null) {
                    // this is a root node
                    rootNodeList.add(currentNode);
                    continue;
                }
                parentNode.addChild(currentNode);
            } else {
                // this is a root node
                rootNodeList.add(currentNode);
            }
        }
        return rootNodeList;
    }

    public <T extends ITreeNode> void printForest(IForestNode<T> forestNode) {
        // TODO: 2023/1/2 print the whole forest
    }

    private static <T extends ITreeNode> void postOrderTraverseInner(IForestNode<T> forestNode) {
        if (!forestNode.hasChild()) {
            return;
        }

        for (IForestNode<T> node : forestNode.getChildren()) {
            postOrderTraverseInner(node);
            node.printData();
        }
    }
}
