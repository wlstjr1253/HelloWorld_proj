package kr.spring.item.domain;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class ItemCommand {
	private int i_num;
	private String i_nm;
	private int i_pc;
	private int i_dispc;
	private int i_quan;
	private byte[] i_img;//DB�� ����� ����
	private MultipartFile upload;//���ε� ����
	private String i_content;
	private int i_state;
	private int ict_num;

	// ���ϸ� ���ϱ�
	public void setUpload(MultipartFile upload) throws IOException {
		this.upload = upload;
		// byte[] ������ ����
		setI_img(upload.getBytes());
	}

	

	public int getI_num() {
		return i_num;
	}
	public void setI_num(int i_num) {
		this.i_num = i_num;
	}
	public String getI_nm() {
		return i_nm;
	}
	public void setI_nm(String i_nm) {
		this.i_nm = i_nm;
	}
	public int getI_pc() {
		return i_pc;
	}
	public void setI_pc(int i_pc) {
		this.i_pc = i_pc;
	}
	public int getI_dispc() {
		return i_dispc;
	}
	public void setI_dispc(int i_dispc) {
		this.i_dispc = i_dispc;
	}
	public int getI_quan() {
		return i_quan;
	}
	public void setI_quan(int i_quan) {
		this.i_quan = i_quan;
	}

	public byte[] getI_img() {
		return i_img;
	}
	public void setI_img(byte[] i_img) {
		this.i_img = i_img;
	}
	public String getI_content() {
		return i_content;
	}
	public void setI_content(String i_content) {
		this.i_content = i_content;
	}
	
	public int getI_state() {
		return i_state;
	}
	public void setI_state(int i_state) {
		this.i_state = i_state;
	}
	public int getIct_num() {
		return ict_num;
	}
	public void setIct_num(int ict_num) {
		this.ict_num = ict_num;
	}


	public MultipartFile getUpload() {
		return upload;
	}



	@Override
	public String toString() {
		return "ItemCommand [i_num=" + i_num + ", i_nm=" + i_nm + ", i_pc=" + i_pc + ", i_dispc=" + i_dispc
				+ ", i_quan=" + i_quan + ", upload=" + upload + ", i_content="
				+ i_content + ", i_state=" + i_state + ", ict_num=" + ict_num + "]";
	}

	

}
