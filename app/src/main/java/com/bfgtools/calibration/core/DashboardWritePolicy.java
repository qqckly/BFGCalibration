package com.bfgtools.calibration.core;

/** Exact firmware allowlist for DIS 0x92 writes. Unknown values fail closed. */
final class DashboardWritePolicy {
    static final int DASHBOARD = 0x0259;
    static final int COLOR_DISPLAY = 0x0155;
    static final int CENTRE = 0x05CA;
    static final int METER = 0x0429;

    private DashboardWritePolicy() { }

    /** 四项固件是否全部为已实车验证版本。 */
    static boolean isFullyValidated(int dashboard, int colorDisplay, int centre, int meter) {
        return dashboard == DASHBOARD && colorDisplay == COLOR_DISPLAY
                && centre == CENTRE && meter == METER;
    }

    /** 放开写入：只要求四个版本均可识别（>=0），未验证组合走额外风险确认。 */
    static boolean allows(int dashboard, int colorDisplay, int centre, int meter) {
        return dashboard >= 0 && colorDisplay >= 0 && centre >= 0 && meter >= 0;
    }

    /** 未验证版本组合需要额外的 30 秒高风险二次确认。 */
    static boolean needsExtraWarning(int dashboard, int colorDisplay, int centre, int meter) {
        return !isFullyValidated(dashboard, colorDisplay, centre, meter);
    }

    static String blockedMessage() {
        return "当前仪表盘不满足任何仪表盘写入条件，本次未发送写入指令。"
                + "请在参数写入页查看四项固件版本核对结果；计量模块中临时写入有独立的读取与风险确认条件。";
    }
}
