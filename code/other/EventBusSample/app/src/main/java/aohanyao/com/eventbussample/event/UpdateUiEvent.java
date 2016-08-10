package aohanyao.com.eventbussample.event;

/**
 * <p>作者：江俊超 on 2016/8/10 15:01</p>
 * <p>邮箱：928692385@qq.com</p>
 * <p>更新UI的事件对象</p>
 */
public class UpdateUiEvent {
    private String ms;
    public UpdateUiEvent(String msf) {
        this.ms = msf;
    }
    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }
}
