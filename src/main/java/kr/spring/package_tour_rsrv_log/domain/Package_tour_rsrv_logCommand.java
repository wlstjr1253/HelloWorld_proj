package kr.spring.package_tour_rsrv_log.domain;

public class Package_tour_rsrv_logCommand {
	private int ptr_id;//��Ű��/���� ���� ID
	private String uesr_id;//ȸ�� ID
	private int pi_id;//��Ű�� ID
	private int ti_id;//���� ID
	private int ph_idx;//���� ���� IDX
	private int ptr_pp;//���� �ο�
	private int pte_state;//���� ����
	public int getPtr_id() {
		return ptr_id;
	}
	public void setPtr_id(int ptr_id) {
		this.ptr_id = ptr_id;
	}
	public String getUesr_id() {
		return uesr_id;
	}
	public void setUesr_id(String uesr_id) {
		this.uesr_id = uesr_id;
	}
	public int getPi_id() {
		return pi_id;
	}
	public void setPi_id(int pi_id) {
		this.pi_id = pi_id;
	}
	public int getTi_id() {
		return ti_id;
	}
	public void setTi_id(int ti_id) {
		this.ti_id = ti_id;
	}
	public int getPh_idx() {
		return ph_idx;
	}
	public void setPh_idx(int ph_idx) {
		this.ph_idx = ph_idx;
	}
	public int getPtr_pp() {
		return ptr_pp;
	}
	public void setPtr_pp(int ptr_pp) {
		this.ptr_pp = ptr_pp;
	}
	public int getPte_state() {
		return pte_state;
	}
	public void setPte_state(int pte_state) {
		this.pte_state = pte_state;
	}
	@Override
	public String toString() {
		return "Package_tour_rsrv_logCommand [ptr_id=" + ptr_id + ", uesr_id=" + uesr_id + ", pi_id=" + pi_id
				+ ", ti_id=" + ti_id + ", ph_idx=" + ph_idx + ", ptr_pp=" + ptr_pp + ", pte_state=" + pte_state + "]";
	}
	
	

}
