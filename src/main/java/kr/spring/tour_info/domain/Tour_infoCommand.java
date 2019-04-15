package kr.spring.tour_info.domain;

import java.io.IOException;
import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

public class Tour_infoCommand {
	private int ti_id;//���� ID 1
	@NotEmpty
	private String ti_nm;//������ 2
	/*@NotEmpty*/
	private String ti_img;//�����̹��� (���� �̸�) 3 
	
	private MultipartFile upload;//���ε� ���� 
	private byte[] uploadfile;//DB�� ����� ���� 4  
	/*private String nc_cd;//���� �ڵ� 5
*/	@NotEmpty
	private String ti_start_day;//���� ������ 6
	@NotEmpty
	private String ti_end_day;//���� ������ 7

	@Range(min=1,max=20)
	private int ti_min_pp;//���� �ּ� �ο� 8
	@Range(min=1,max=20)

	private int ti_max_pp;//���� �ִ� �ο� 9
	@NotEmpty
	private String ti_pickup_place;//�Ⱦ� ��� 10
	@NotEmpty
	private String ti_pickup_time;//�Ⱦ� �ð� 11
	@NotEmpty
	private String ti_content;//�� ���� 12

	@Range(min=1,max=50000)

	private int ti_pc;//���� �δ� ���� 13
	private int ti_state;//���� ���� 14
	private String user_id;//���̵� ID 15
	
	private int re_cnt;//��� �� 16
	
	private Date reg_date;//������� 17
	
	private int ti_select;//���� ��û ���� (0:�̽�û, 1:��û)
	
	//MultipartFile -> byte[] ��ȯ
	//���ϸ� ���ϱ�
	public void setUpload(MultipartFile upload) throws IOException {
		this.upload = upload;
		              //byte[] ������ ����
		setUploadfile(upload.getBytes());
		                 //���ϸ�
		setTi_img(upload.getOriginalFilename());
	}

	public int getTi_id() {
		return ti_id;
	}

	public void setTi_id(int ti_id) {
		this.ti_id = ti_id;
	}

	public String getTi_nm() {
		return ti_nm;
	}

	public void setTi_nm(String ti_nm) {
		this.ti_nm = ti_nm;
	}

	public String getTi_img() {
		return ti_img;
	}

	public void setTi_img(String ti_img) {
		this.ti_img = ti_img;
	}

	public byte[] getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(byte[] uploadfile) {
		this.uploadfile = uploadfile;
	}

	public String getTi_start_day() {
		return ti_start_day;
	}

	public void setTi_start_day(String ti_start_day) {
		this.ti_start_day = ti_start_day;
	}

	public String getTi_end_day() {
		return ti_end_day;
	}

	public void setTi_end_day(String ti_end_day) {
		this.ti_end_day = ti_end_day;
	}

	public int getTi_min_pp() {
		return ti_min_pp;
	}

	public void setTi_min_pp(int ti_min_pp) {
		this.ti_min_pp = ti_min_pp;
	}

	public int getTi_max_pp() {
		return ti_max_pp;
	}

	public void setTi_max_pp(int ti_max_pp) {
		this.ti_max_pp = ti_max_pp;
	}

	public String getTi_pickup_place() {
		return ti_pickup_place;
	}

	public void setTi_pickup_place(String ti_pickup_place) {
		this.ti_pickup_place = ti_pickup_place;
	}

	public String getTi_pickup_time() {
		return ti_pickup_time;
	}

	public void setTi_pickup_time(String ti_pickup_time) {
		this.ti_pickup_time = ti_pickup_time;
	}

	public String getTi_content() {
		return ti_content;
	}

	public void setTi_content(String ti_content) {
		this.ti_content = ti_content;
	}

	public int getTi_pc() {
		return ti_pc;
	}

	public void setTi_pc(int ti_pc) {
		this.ti_pc = ti_pc;
	}

	public int getTi_state() {
		return ti_state;
	}

	public void setTi_state(int ti_state) {
		this.ti_state = ti_state;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getRe_cnt() {
		return re_cnt;
	}

	public void setRe_cnt(int re_cnt) {
		this.re_cnt = re_cnt;
	}

	public MultipartFile getUpload() {
		return upload;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public int getTi_select() {
		return ti_select;
	}

	public void setTi_select(int ti_select) {
		this.ti_select = ti_select;
	}
	//(����) byte[] uploadfile�� ����!!!
	@Override
	public String toString() {
		return "Tour_infoCommand [ti_id=" + ti_id + ", ti_nm=" + ti_nm + ", ti_img=" + ti_img + ", upload=" + upload
				+ ", ti_start_day=" + ti_start_day + ", ti_end_day=" + ti_end_day + ", ti_min_pp=" + ti_min_pp
				+ ", ti_max_pp=" + ti_max_pp + ", ti_pickup_place=" + ti_pickup_place + ", ti_pickup_time="
				+ ti_pickup_time + ", ti_content=" + ti_content + ", ti_pc=" + ti_pc + ", ti_state=" + ti_state
				+ ", user_id=" + user_id + ", re_cnt=" + re_cnt + ", reg_date=" + reg_date + ", ti_select=" + ti_select
				+ "]";
	}
	
}


