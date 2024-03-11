package com.ohgiraffers.section02.javaconfig;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface MenuMapper {

    @Results(id="menuResultMap", value = {
            @Result(id = true, property = "code", column="MENU_CODE"),
            @Result(property = "name", column="MENU_NAME"),
            @Result(property = "price", column="MENU_PRICE"),
            @Result(property = "categoryCode", column="CATEGORY_CODE"),
            @Result(property = "orderableStatus", column="ORDERABLE_STATUS")
    })
    @Select("SELECT\n" +
            "            MENU_CODE,\n" +
            "            MENU_NAME,\n" +
            "            MENU_PRICE,\n" +
            "            CATEGORY_CODE,\n" +
            "            ORDERABLE_STATUS\n" +
            "        FROM\n" +
            "            TBL_MENU\n" +
            "        WHERE\n" +
            "            ORDERABLE_STATUS = 'Y'\n" +
            "        ORDER BY\n" +
            "            MENU_CODE")
    List<MenuDTO> selectAllMenu();

    @Select("SELECT\n" +
            "            MENU_CODE,\n" +
            "            MENU_NAME,\n" +
            "            MENU_PRICE,\n" +
            "            CATEGORY_CODE,\n" +
            "            ORDERABLE_STATUS\n" +
            "        FROM\n" +
            "            TBL_MENU\n" +
            "        WHERE\n" +
            "            ORDERABLE_STATUS = 'Y' AND\n" +
            "            MENU_CODE = #{ code }")
    @ResultMap("menuResultMap")
    MenuDTO selectMenuByCode(int code);

    @Insert("INSERT INTO TBL_MENU (\n" +
            "            MENU_NAME,\n" +
            "            MENU_PRICE,\n" +
            "            CATEGORY_CODE,\n" +
            "            ORDERABLE_STATUS)\n" +
            "    VALUES (\n" +
            "            #{ name },\n" +
            "            #{ price },\n" +
            "            #{ categoryCode },\n" +
            "            'Y')")
    int insertMenu(MenuDTO menu);

    @Update("UPDATE TBL_MENU SET\n" +
            "            MENU_NAME = #{ name },\n" +
            "            MENU_PRICE = #{ price },\n" +
            "            CATEGORY_CODE = #{ categoryCode }\n" +
            "        WHERE MENU_CODE = #{ code }")
    int updateMenu(MenuDTO menu);

    @Delete("DELETE\n" +
            "        FROM TBL_MENU\n" +
            "        WHERE MENU_CODE = #{ code }")
    int deleteMenu(int code);
}

