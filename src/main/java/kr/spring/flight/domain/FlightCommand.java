package kr.spring.flight.domain;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class FlightCommand {
	
	private int fi_id;
	@NotEmpty
	private String fi_nm;
	//private MultipartFile upload_fi_logo; // �װ��� �ΰ�
	private String fi_logo; // DB�� ����� �ΰ�
	
	private int fsi_idx; // �װ��� idx
	@NotEmpty
	private String fsi_start_nation;  // ��߱���
	private String fsi_start_city; // ��ߵ���
	@NotEmpty
	private String fsi_arrive_nation;  // ��������
	private String fsi_arrive_city;  // ��������
	private String fsi_pass1_nation;  // ������1����
	private String fsi_pass1_city;  // ������1����
	private String fsi_pass2_nation;  // ������2����
	private String fsi_pass2_city;  // ������2����
	@NotEmpty
	private String fsi_start_dt;     // ��� �ð�
	@NotEmpty
	private String fsi_arrive_dt;    // ���� �ð�
	private String fsi_pass1_dt;     // ������1 �ð� 
	private String fsi_pass2_dt;    // ������2 �ð� 
	private Date fsi_reg_dt;  // �װ��� ��� �ð�
	private String fsi_state;  // ���� ���� ����

	private int fsi_fir_seat;  // FisrtClass �¼� ��
	private int fsi_bus_seat;  // Business �¼� ��
	private int fsi_eco_seat;  // Economy �¼� ��
	private int fsi_fir_pc;  // FisrtClass ����
	private int fsi_bus_pc;  // Business ����
	private int fsi_eco_pc;  // Economy ����
	private int fsi_fir_mil;  // FisrtClass ���ϸ���
	private int fsi_bus_mil;  // Business ���ϸ���
	private int fsi_eco_mil;  // Economy ���ϸ���
	
	public int getFi_id() {
		return fi_id;
	}
	public void setFi_id(int fi_id) {
		this.fi_id = fi_id;
	}
	public String getFi_nm() {
		return fi_nm;
	}
	public void setFi_nm(String fi_nm) {
		this.fi_nm = fi_nm;
	}
	public String getFi_logo() {
		return fi_logo;
	}
	public void setFi_logo(String fi_logo) {
		this.fi_logo = fi_logo;
	}
	public int getFsi_idx() {
		return fsi_idx;
	}
	public void setFsi_idx(int fsi_idx) {
		this.fsi_idx = fsi_idx;
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
	public String getFsi_pass1_dt() {
		return fsi_pass1_dt;
	}
	public void setFsi_pass1_dt(String fsi_pass1_dt) {
		this.fsi_pass1_dt = fsi_pass1_dt;
	}
	public String getFsi_pass2_dt() {
		return fsi_pass2_dt;
	}
	public void setFsi_pass2_dt(String fsi_pass2_dt) {
		this.fsi_pass2_dt = fsi_pass2_dt;
	}
	public Date getFsi_reg_dt() {
		return fsi_reg_dt;
	}
	public void setFsi_reg_dt(Date fsi_reg_dt) {
		this.fsi_reg_dt = fsi_reg_dt;
	}
	public String getFsi_state() {
		return fsi_state;
	}
	public void setFsi_state(String fsi_state) {
		this.fsi_state = fsi_state;
	}
	public int getFsi_fir_seat() {
		return fsi_fir_seat;
	}
	public void setFsi_fir_seat(int fsi_fir_seat) {
		this.fsi_fir_seat = fsi_fir_seat;
	}
	public int getFsi_bus_seat() {
		return fsi_bus_seat;
	}
	public void setFsi_bus_seat(int fsi_bus_seat) {
		this.fsi_bus_seat = fsi_bus_seat;
	}
	public int getFsi_eco_seat() {
		return fsi_eco_seat;
	}
	public void setFsi_eco_seat(int fsi_eco_seat) {
		this.fsi_eco_seat = fsi_eco_seat;
	}
	public int getFsi_fir_pc() {
		return fsi_fir_pc;
	}
	public void setFsi_fir_pc(int fsi_fir_pc) {
		this.fsi_fir_pc = fsi_fir_pc;
	}
	public int getFsi_bus_pc() {
		return fsi_bus_pc;
	}
	public void setFsi_bus_pc(int fsi_bus_pc) {
		this.fsi_bus_pc = fsi_bus_pc;
	}
	public int getFsi_eco_pc() {
		return fsi_eco_pc;
	}
	public void setFsi_eco_pc(int fsi_eco_pc) {
		this.fsi_eco_pc = fsi_eco_pc;
	}
	public int getFsi_fir_mil() {
		return fsi_fir_mil;
	}
	public void setFsi_fir_mil(int fsi_fir_mil) {
		this.fsi_fir_mil = fsi_fir_mil;
	}
	public int getFsi_bus_mil() {
		return fsi_bus_mil;
	}
	public void setFsi_bus_mil(int fsi_bus_mil) {
		this.fsi_bus_mil = fsi_bus_mil;
	}
	public int getFsi_eco_mil() {
		return fsi_eco_mil;
	}
	public void setFsi_eco_mil(int fsi_eco_mil) {
		this.fsi_eco_mil = fsi_eco_mil;
	}
	
	@Override
	public String toString() {
		return "FlightCommand [fi_id=" + fi_id + ", fi_nm=" + fi_nm + ", fi_logo=" + fi_logo + ", fsi_idx=" + fsi_idx
				+ ", fsi_start_nation=" + fsi_start_nation + ", fsi_start_city=" + fsi_start_city
				+ ", fsi_arrive_nation=" + fsi_arrive_nation + ", fsi_arrive_city=" + fsi_arrive_city
				+ ", fsi_pass1_nation=" + fsi_pass1_nation + ", fsi_pass1_city=" + fsi_pass1_city
				+ ", fsi_pass2_nation=" + fsi_pass2_nation + ", fsi_pass2_city=" + fsi_pass2_city + ", fsi_start_dt="
				+ fsi_start_dt + ", fsi_arrive_dt=" + fsi_arrive_dt + ", fsi_pass1_dt=" + fsi_pass1_dt
				+ ", fsi_pass2_dt=" + fsi_pass2_dt + ", fsi_reg_dt=" + fsi_reg_dt + ", fsi_state=" + fsi_state
				+ ", fsi_fir_seat=" + fsi_fir_seat + ", fsi_bus_seat=" + fsi_bus_seat + ", fsi_eco_seat=" + fsi_eco_seat
				+ ", fsi_fir_pc=" + fsi_fir_pc + ", fsi_bus_pc=" + fsi_bus_pc + ", fsi_eco_pc=" + fsi_eco_pc
				+ ", fsi_fir_mil=" + fsi_fir_mil + ", fsi_bus_mil=" + fsi_bus_mil + ", fsi_eco_mil=" + fsi_eco_mil
				+ "]";
	}

}
