package org.zhanpeng.magic_tree;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.zhanpeng.magic_tree.domain.menu.Menu;
import org.zhanpeng.magic_tree.domain.tree.IForestNode;
import org.zhanpeng.magic_tree.domain.tree.entity.ForestNode;
import org.zhanpeng.magic_tree.domain.tree.util.ForestUtil;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
class MagicTreeApplicationTests {
    public static ForestNode<Menu> menuForestNode;
    public static List<Menu> menus;

    @BeforeAll
    public static void contextLoads() {
        menus = new ArrayList<>();
        menuForestNode = initMenu();
    }

    private static ForestNode<Menu> initMenu() {
        Menu menu = buildMenu(1L, "o2.o2inv.inventory-select", "库存查询", "api", "/inventory-query", 0L);
        Menu menu1 = buildMenu(2L, "o2.o2inv.inventory-select.shop-stock", "网店库存查询", "api", "/inventory/shop-stock", 1L);
        Menu menu2 = buildMenu(3L, "o2.o2inv.inventory-select.warehouse-stock", "仓库库存查询", "api", "/inventory/warehouse-stock", 1L);
        Menu menu3 = buildMenu(4L, "o2.o2inv.stock-rule", "库存规则设置", "api", "/inventory-rule", 0L);
        Menu menu4 = buildMenu(5L, "o2.o2inv.stock-rule.shop-stock-rule", "网店上传比例设置", "api", "/inventory-rule/shop-stock-rule", 4L);
        Menu menu5 = buildMenu(6L, "o2.o2inv.stock-rule.shop-stock-rule.create", "新增网店上传比例", "button", "/inventory-rule/shop-stock-rule/create", 5L);
        Menu menu6 = buildMenu(7L, "o2.o2inv.stock-rule.shop-stock-rule.update", "修改网店上传比例", "button", "/inventory-rule/shop-stock-rule/update", 5L);

        menus.add(menu);
        menus.add(menu1);
        menus.add(menu2);
        menus.add(menu3);
        menus.add(menu4);
        menus.add(menu5);
        menus.add(menu6);

        ForestNode<Menu> rootMenu = new ForestNode<>(new Menu());
        rootMenu.setDummy(true);

        ForestNode<Menu> menu1Node = new ForestNode<>(menu);
        ForestNode<Menu> menu2Node = new ForestNode<>(menu1);
        ForestNode<Menu> menu3Node = new ForestNode<>(menu2);
        ForestNode<Menu> menu4Node = new ForestNode<>(menu3);
        ForestNode<Menu> menu5Node = new ForestNode<>(menu4);
        ForestNode<Menu> menu6Node = new ForestNode<>(menu5);
        ForestNode<Menu> menu7Node = new ForestNode<>(menu6);

        rootMenu.addChild(menu1Node);
        rootMenu.addChild(menu4Node);

        menu1Node.addChild(menu2Node);
        menu1Node.addChild(menu3Node);

        menu4Node.addChild(menu5Node);
        menu5Node.addChild(menu6Node);
        menu5Node.addChild(menu7Node);
        return rootMenu;
    }

    @Test
    void testForestPreOrderTraverse() {
        ForestUtil.preOrderTraverse(menuForestNode);
        System.out.println("------------------------------");
    }

    @Test
    void testForestPostOrderTraverse() {
        ForestUtil.postOrderTraverse(menuForestNode);
        System.out.println("------------------------------");
    }

    @Test
    void testForestBuilding() {
        List<IForestNode<Menu>> rootList = ForestUtil.buildForest(menus);
        // build a dummy root
        ForestNode<Menu> dummyRoot = new ForestNode<>(new Menu());
        dummyRoot.setDummy(true);
        dummyRoot.addChildren(rootList);
        ForestUtil.preOrderTraverse(dummyRoot);
        System.out.println("------------------------------");
    }

    private static Menu buildMenu(Long id, String code, String name, String permissionType, String route, Long parentId) {
        Menu menu = new Menu();
        menu.setId(id);
        menu.setCode(code);
        menu.setName(name);
        menu.setPermissionType(permissionType);
        menu.setRoute(route);
        menu.setParentId(parentId);
        return menu;
    }

}
