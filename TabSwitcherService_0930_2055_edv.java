// 代码生成时间: 2025-09-30 20:55:48
package com.example.tabswitcher;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tabSwitcher")
public class TabSwitcherService {

    // 定义标签页状态，这里简单使用一个字符串数组模拟
    private final String[] TAB_STATES = {"Tab1", "Tab2", "Tab3"};

    /**
     * 获取当前激活的标签页
     * @param activeTab 要激活的标签页索引（1-based index）
     * @return 返回激活标签页的响应
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getActiveTab(@QueryParam("activeTab") int activeTab) {
        try {
            // 检查索引是否有效
            if (activeTab < 1 || activeTab > TAB_STATES.length) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid tab index.").build();
            }

            // 获取并返回当前激活的标签页名称
            String activeTabName = TAB_STATES[activeTab - 1];
            return Response.ok(activeTabName).build();
        } catch (Exception e) {
            // 返回服务器错误响应
            return Response.serverError().entity("Server error: " + e.getMessage()).build();
        }
    }

    /**
     * 设置当前激活的标签页
     * @param activeTab 要激活的标签页索引（1-based index）
     * @return 返回设置结果的响应
     */
    @GET
    @Path("/setActiveTab")
    @Produces(MediaType.TEXT_PLAIN)
    public Response setActiveTab(@QueryParam("activeTab") int activeTab) {
        try {
            // 检查索引是否有效
            if (activeTab < 1 || activeTab > TAB_STATES.length) {
                return Response.status(Response.Status.BAD_REQUEST).entity("Invalid tab index.").build();
            }

            // 模拟设置当前激活的标签页
            // 在实际应用中，这里可以是更新数据库或状态管理的代码
            // 此处直接返回成功消息
            return Response.ok("Tab " + activeTab + " set as active.").build();
        } catch (Exception e) {
            // 返回服务器错误响应
            return Response.serverError().entity("Server error: " + e.getMessage()).build();
        }
    }
}
