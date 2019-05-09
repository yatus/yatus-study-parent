package cn.yatus.chapter1.config;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DSContextHolder {
    private static final ThreadLocal<DSRouteTypeEnum> holder = ThreadLocal.withInitial(() ->
            DSRouteTypeEnum.MASTER);

    public static void set(DSRouteTypeEnum typeEnum) {
        holder.set(typeEnum);
    }

    public static DSRouteTypeEnum get() {
        return holder.get();
    }

    public static void master() {
        log.info("数据源切换写库");
        set(DSRouteTypeEnum.MASTER);
    }

    public static void slave() {
        log.info("数据源切换读库");
        set(DSRouteTypeEnum.SLAVE);
    }


}
