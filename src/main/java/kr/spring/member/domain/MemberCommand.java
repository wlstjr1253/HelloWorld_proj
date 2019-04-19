package kr.spring.member.domain;

import java.sql.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class MemberCommand {
	@NotEmpty
	private String user_id;
	private int user_auth;
	@NotEmpty
	private String user_nm;
	@Size(min=4,max=20)
	private String user_pw;
	@NotEmpty
	private String user_phone;
	@Email
	@NotEmpty
	private String user_email;
	
	private Date user_apply_dt;
	private String user_guide_apply;
	private int user_mil;
	private byte[] user_profile; // DB에 저장된 파일
	private String user_filename; // 파일명
	
	@Size(min=4,max=20)
	private String old_pw;
	
	//결제 내역
	private String ph_pay_type;
	private int ph_pay;
	private Date ph_reg_dt;
	private int ph_knd;
	
	//호텔 내역
	private String st_nm,srl_check_in_dt,srl_check_out_dt,st_addr;
	private int srl_total_pc;
	
	//항공권 내역
	private String fsi_start_nation, fsi_start_city, fsi_pass1_nation, 
					fsi_pass1_city, fsi_pass2_nation, fsi_pass2_city, 
					fsi_arrive_nation, fsi_arrive_city, fsi_start_dt, 
					fsi_arrive_dt, fr_rsrv_seat_type, fi_nm; 
	public String getSt_nm() {
		return st_nm;
	}

	public void setSt_nm(String st_nm) {
		this.st_nm = st_nm;
	}

	public String getSrl_check_in_dt() {
		return srl_check_in_dt;
	}

	public void setSrl_check_in_dt(String srl_check_in_dt) {
		this.srl_check_in_dt = srl_check_in_dt;
	}

	public String getSrl_check_out_dt() {
		return srl_check_out_dt;
	}

	public void setSrl_check_out_dt(String srl_check_out_dt) {
		this.srl_check_out_dt = srl_check_out_dt;
	}

	public String getSt_addr() {
		return st_addr;
	}

	public void setSt_addr(String st_addr) {
		this.st_addr = st_addr;
	}

	public int getSrl_total_pc() {
		return srl_total_pc;
	}

	public void setSrl_total_pc(int srl_total_pc) {
		this.srl_total_pc = srl_total_pc;
	}

	public String getFsi_start_nation() {
		return fsi_start_nation;
	}

	public void setFsi_start_nation(String fsi_start_nation) {
		this.fsi_start_nation = fsi_start_nation;
	}

	public String getFsi_start_city() {
		return fsi_start_city;
	}

	public void setFsi_start_city(String fsi_start_city) {
		this.fsi_start_city = fsi_start_city;
	}

	public String getFsi_pass1_nation() {
		return fsi_pass1_nation;
	}

	public void setFsi_pass1_nation(String fsi_pass1_nation) {
		this.fsi_pass1_nation = fsi_pass1_nation;
	}

	public String getFsi_pass1_city() {
		return fsi_pass1_city;
	}

	public void setFsi_pass1_city(String fsi_pass1_city) {
		this.fsi_pass1_city = fsi_pass1_city;
	}

	public String getFsi_pass2_nation() {
		return fsi_pass2_nation;
	}

	public void setFsi_pass2_nation(String fsi_pass2_nation) {
		this.fsi_pass2_nation = fsi_pass2_nation;
	}

	public String getFsi_pass2_city() {
		return fsi_pass2_city;
	}

	public void setFsi_pass2_city(String fsi_pass2_city) {
		this.fsi_pass2_city = fsi_pass2_city;
	}

	public String getFsi_arrive_nation() {
		return fsi_arrive_nation;
	}

	public void setFsi_arrive_nation(String fsi_arrive_nation) {
		this.fsi_arrive_nation = fsi_arrive_nation;
	}

	public String getFsi_arrive_city() {
		return fsi_arrive_city;
	}

	public void setFsi_arrive_city(String fsi_arrive_city) {
		this.fsi_arrive_city = fsi_arrive_city;
	}

	public String getFsi_start_dt() {
		return fsi_start_dt;
	}

	public void setFsi_start_dt(String fsi_start_dt) {
		this.fsi_start_dt = fsi_start_dt;
	}

	public String getFsi_arrive_dt() {
		return fsi_arrive_dt;
	}

	public void setFsi_arrive_dt(String fsi_arrive_dt) {
		this.fsi_arrive_dt = fsi_arrive_dt;
	}

	public String getFr_rsrv_seat_type() {
		return fr_rsrv_seat_type;
	}

	public void setFr_rsrv_seat_type(String fr_rsrv_seat_type) {
		this.fr_rsrv_seat_type = fr_rsrv_seat_type;
	}

	public String getFi_nm() {
		return fi_nm;
	}

	public void setFi_nm(String fi_nm) {
		this.fi_nm = fi_nm;
	}

	public int getFr_total_pc() {
		return fr_total_pc;
	}

	public void setFr_total_pc(int fr_total_pc) {
		this.fr_total_pc = fr_total_pc;
	}

	private int fr_total_pc;
	
	public String getPh_pay_type() {
		return ph_pay_type;
	}

	public void setPh_pay_type(String ph_pay_type) {
		this.ph_pay_type = ph_pay_type;
	}

	public int getPh_pay() {
		return ph_pay;
	}

	public void setPh_pay(int ph_pay) {
		this.ph_pay = ph_pay;
	}

	public Date getPh_reg_dt() {
		return ph_reg_dt;
	}

	public void setPh_reg_dt(Date ph_reg_dt) {
		this.ph_reg_dt = ph_reg_dt;
	}

	public int getPh_knd() {
		return ph_knd;
	}

	public void setPh_knd(int ph_knd) {
		this.ph_knd = ph_knd;
	}

	// 비밀번호 일치 여부 체크
	public boolean isCheckedPasswd(String userPasswd) {
		if (user_auth > 0 && user_pw.equals(userPasswd)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "MemberCommand [user_id=" + user_id + ", user_auth=" + user_auth + ", user_nm=" + user_nm + ", user_pw="
				+ user_pw + ", user_phone=" + user_phone + ", user_email=" + user_email + ", user_apply_dt="
				+ user_apply_dt + ", user_guide_apply=" + user_guide_apply + ", user_mil=" + user_mil
				+ ", user_filename=" + user_filename + ", old_pw=" + old_pw + "]";
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getUser_auth() {
		return user_auth;
	}

	public void setUser_auth(int user_auth) {
		this.user_auth = user_auth;
	}

	public String getUser_nm() {
		return user_nm;
	}

	public void setUser_nm(String user_nm) {
		this.user_nm = user_nm;
	}

	public String getUser_pw() {
		return user_pw;
	}

	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public Date getUser_apply_dt() {
		return user_apply_dt;
	}

	public void setUser_apply_dt(Date user_apply_dt) {
		this.user_apply_dt = user_apply_dt;
	}

	public String getUser_guide_apply() {
		return user_guide_apply;
	}

	public void setUser_guide_apply(String user_guide_apply) {
		this.user_guide_apply = user_guide_apply;
	}

	public int getUser_mil() {
		return user_mil;
	}

	public void setUser_mil(int user_mil) {
		this.user_mil = user_mil;
	}

	public byte[] getUser_profile() {
		return user_profile;
	}

	public void setUser_profile(byte[] user_profile) {
		this.user_profile = user_profile;
	}

	public String getUser_filename() {
		return user_filename;
	}

	public void setUser_filename(String user_filename) {
		this.user_filename = user_filename;
	}

	public String getOld_pw() {
		return old_pw;
	}

	public void setOld_pw(String old_pw) {
		this.old_pw = old_pw;
	}
	
	
	
}
