package kr.spring.tour_info.domain;

public class Tour_infoApplyCommand {
	private int ta_idx; //��û ���� IDX
	private String user_id;// ȸ�� ID
	private int ti_id;//���� ID 
	private int ti_state;// ��û ���� (0:��û,1:����,2:�ź�)
	private String ti_reg_dt;//��û����
	public int getTa_idx() {
		return ta_idx;
	}
	public void setTa_idx(int ta_idx) {
		this.ta_idx = ta_idx;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getTi_id() {
		return ti_id;
	}
	public void setTi_id(int ti_id) {
		this.ti_id = ti_id;
	}
	public int getTi_state() {
		return ti_state;
	}
	public void setTi_state(int ti_state) {
		this.ti_state = ti_state;
	}
	public String getTi_reg_dt() {
		return ti_reg_dt;
	}
	public void setTi_reg_dt(String ti_reg_dt) {
		this.ti_reg_dt = ti_reg_dt;
	}
	@Override
	public String toString() {
		return "Tour_infoApplyCommand [ta_idx=" + ta_idx + ", user_id=" + user_id + ", ti_id=" + ti_id + ", ti_state="
				+ ti_state + ", ti_reg_dt=" + ti_reg_dt + "]";
	}
	
	
}
